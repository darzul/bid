package bid;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BidTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private Item item;
	private User buyer;
	private User seller1;
	private User seller2;
	private Bid bid;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		item = new Item ();
		buyer = new User("DarzuL", "Bourderye", "Guillaume");
		seller1 = new User("Karibou", "Bouvard","François");
		seller2 = new User("Hoshiyo", "Guyen", "Anna");
		bid = buyer.createBid(item, 10, 100);
		bid.setState(BidState.PUBLISHED);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void makeOfferOnNotPublishedBidTest() {
		buyer.hideBid(bid);
		Offer res = buyer.makeOffer( this.bid, 150 );
		assertNull(res);
	}
	
	@Test
	public void makeOfferWithNegativePriceTest() {
		Offer res = seller1.makeOffer( this.bid, -50 );
		assertNull(res);
	}
	
	@Test
	public void makeOfferUnderMinPriceTest() {
		Offer res = seller1.makeOffer( this.bid, 50 );
		assertNull(res);
	}
	
	@Test
	public void makeOfferTest() {
		Offer res = seller1.makeOffer( this.bid, 500 );
		assertNotNull(res);
	}
	
	@Test
	public void makeOfferUnderMaxOfferTest() {
		Offer res = seller1.makeOffer( this.bid, 150 );
		assertNull(res);
	}

	@Test
	public void makeOfferOnHisOwnBidTest() {
		Offer res = buyer.makeOffer( this.bid, 150 );
		assertNull(res);
	}

	@Test
	public void makeOfferOnOutdatedBidTest() {
		
		Offer res = buyer.makeOffer( this.bid, 150 );
		assertNull(res);
	}
	
	@Test
	public void seeNotPublishedBidTest() {
		Offer res = seller1.makeOffer( this.bid, 150 );
		assertNull(res);
	}
	
	@Test
	public void seePublishedBidTest() {
		assertNotNull(Bid.getBids(seller1));
	}
}
