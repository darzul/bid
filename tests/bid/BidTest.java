package bid;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BidTest {

	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static Item item;
	private static User seller;
	private static User buyer1;
	private static User buyer2;
	private static Bid bid;
	
	@BeforeClass
	public static void initialize() throws Exception {
		System.setOut(new PrintStream(outContent));
		item = new Item (0, "Un poney");
		
		seller = new User("DarzuL", "Bourderye", "Guillaume");
		seller.createBid(item, 10, 100);
		
		bid = seller.getOwnedBids().get(0);
		
		buyer1 = new User("Karibou", "Bouvard","Francois");
		buyer2 = new User("Hoshiyo", "Guyen", "Anna");
	}
	
	@AfterClass
	public static void clean() throws Exception {
		BidManager.getInstance().clearBids();
	}
	
	@Test
	// We can't see the unpublished bid
	public void seeUnpublishedBidTest() {
		int nbBid = seller.getOwnedBids().size();
		assertTrue(seller.hideBid(bid));
		assertEquals(nbBid - 1, buyer1.getPublishedBids().size());
	}
	
	@Test
	// We can see the published bid
	public void seePublishedBidTest() {
		int nbBid = seller.getOwnedBids().size();
		assertTrue(seller.publishBid(bid));
		assertEquals(nbBid, buyer1.getPublishedBids().size());
	}
	
	@Test
	// A buyer can't make an offer on a unpublished bid
	public void makeOfferOnUnpublishedBidTest() {
		assertTrue(seller.hideBid(bid));
		assertFalse(buyer1.makeOffer( bid, 150 ));
	}
	
	@Test
	// A buyer can make an offer on a published bid
	public void makeOfferTest() {
		assertTrue(seller.publishBid(bid));
		assertTrue(buyer1.makeOffer( bid, 500 ));
	}
	
	@Test
	// A user can't make an offer on his own bid
	public void makeOfferOnHisOwnBidTest() {
		assertFalse(seller.makeOffer( bid, 150 ));
	}
	
	@Test
	// A buyer can't make a negative offer
	public void makeOfferWithNegativePriceTest() {
		assertFalse(buyer1.makeOffer( bid, -50 ));
	}
	
	@Test
	// A buyer can't make an offer under the min price
	public void makeOfferUnderMinPriceTest() {
		assertFalse(buyer1.makeOffer( bid, 50 ));
	}
	
	@Test
	// A buyer can't make an offer under or equal to another offer on the same bid
	public void makeOfferUnderMaxOfferTest() {
		assertFalse(buyer2.makeOffer( bid, 150 ));
	}

	@Test
	// A buyer can't make an offer on a outdated bid
	public void makeOfferOnOutdatedBidTest() {
		Clock.getSingleton().addOneYear();
		assertFalse(buyer1.makeOffer( bid, 150 ));
	}
}
