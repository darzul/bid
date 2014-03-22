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
	private User seller;
	private User buyer1;
	private User buyer2;
	private Bid bid;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		item = new Item ();
		
		seller = new User("DarzuL", "Bourderye", "Guillaume");
		seller.createBid(item, 10, 100);
		bid = seller.getOwnedBids().get(0);
		
		buyer1 = new User("Karibou", "Bouvard","François");
		buyer2 = new User("Hoshiyo", "Guyen", "Anna");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void seeNotPublishedBidTest() {
		assertNull(Bid.getBids(buyer1));
	}
	
	@Test
	public void seePublishedBidTest() {
		bid.setState(BidState.PUBLISHED, seller);
		assertNotNull(Bid.getBids(buyer1));
	}
	
	@Test
	public void makeOfferTest() {
		assertTrue(buyer1.makeOffer( this.bid, 500 ));
	}
	
	@Test
	public void makeOfferOnHisOwnBidTest() {
		assertFalse(seller.makeOffer( this.bid, 150 ));
	}
	
	@Test
	public void makeOfferOnNotPublishedBidTest() {
		seller.hideBid(bid);
		assertFalse(buyer1.makeOffer( this.bid, 150 ));
	}
	
	@Test
	public void makeOfferWithNegativePriceTest() {
		assertFalse(buyer1.makeOffer( this.bid, -50 ));
	}
	
	@Test
	public void makeOfferUnderMinPriceTest() {
		assertFalse(buyer1.makeOffer( this.bid, 50 ));
	}
	
	@Test
	public void makeOfferUnderMaxOfferTest() {
		assertFalse(buyer2.makeOffer( this.bid, 150 ));
	}

	@Test
	public void makeOfferOnOutdatedBidTest() {
		assertFalse(buyer1.makeOffer( this.bid, 150 ));
	}
}
