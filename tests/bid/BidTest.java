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
	private Bid bidPublished;
	private Bid bidUnpublished;
	
	@Before
	public void initialize() throws Exception {
		System.setOut(new PrintStream(outContent));
		item = new Item (0, "Un poney");
		
		seller = new User("DarzuL", "Bourderye", "Guillaume");
		seller.createBid(item, 10, 100);
		seller.createBid(item, 10, 100);
		
		bidPublished = seller.getOwnedBids().get(0);
		seller.publishBid(bidPublished);

		bidUnpublished = seller.getOwnedBids().get(1);
		
		buyer1 = new User("Karibou", "Bouvard","Francois");
		buyer2 = new User("Hoshiyo", "Guyen", "Anna");
	}

	@After
	public void tearDown() throws Exception {
		Bid.clearBid();
	}
	
	@Test
	// We can see the published bid but not the unpublished bid
	public void seePublishedBidTest() {
		assertEquals(1, Bid.getPublishedBids().size());
	}
	
	@Test
	// A buyer can make an offer on a published bid
	public void makeOfferTest() {
		assertTrue(buyer1.makeOffer( this.bidPublished, 500 ));
	}
	
	@Test
	// A buyer can't make an offer on a unpublished bid
	public void makeOfferOnUnpublishedBidTest() {
		assertFalse(buyer1.makeOffer( this.bidUnpublished, 150 ));
	}
	
	@Test
	// A user can't make an offer on his own bid
	public void makeOfferOnHisOwnBidTest() {
		assertFalse(seller.makeOffer( this.bidPublished, 150 ));
	}
	
	@Test
	// A buyer can't make a negative offer
	public void makeOfferWithNegativePriceTest() {
		assertFalse(buyer1.makeOffer( this.bidPublished, -50 ));
	}
	
	@Test
	// A buyer can't make an offer under the min price
	public void makeOfferUnderMinPriceTest() {
		// TODO: Il faut faire le test du prix > prix mini avant de cr�er l'offre
		assertFalse(buyer1.makeOffer( this.bidPublished, 50 ));
	}
	
	@Test
	// A buyer can't make an offer under or equal to another offer on the same bid
	public void makeOfferUnderMaxOfferTest() {
		assertTrue(buyer1.makeOffer( this.bidPublished, 150 ));
		assertFalse(buyer2.makeOffer( this.bidPublished, 150 ));
	}

	@Test
	// A buyer can't make an offer on a outdated bid
	public void makeOfferOnOutdatedBidTest() {
		Clock.getSingleton().addOneYear();
		assertFalse(buyer1.makeOffer( this.bidPublished, 150 ));
	}
}
