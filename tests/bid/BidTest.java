package bid;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import user.User;
import alert.AlertManager;

public class BidTest {

	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static Item item;
	private static User seller;
	private static User buyer1;
	private static User buyer2;
	
	@BeforeClass
	public static void initialize() throws Exception {
		System.setOut(new PrintStream(outContent));
		item = new Item (0, "Un poney");
		
		seller = new User("DarzuL", "Bourderye", "Guillaume");
		seller.createBid(item, 10, 100);
		
		buyer1 = new User("Karibou", "Bouvard","Francois");
		buyer2 = new User("Hoshiyo", "Guyen", "Anna");
	}
	
	@After
	public void tearDown() throws Exception {
		BidManager.getInstance().clearBids();
		AlertManager.getInstance().clearAlerts();
	}
	
	@Test
	// We can't see the unpublished bid
	public void seeUnpublishedBidTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		assertEquals(0, buyer1.getPublishedBids().size());
	}
	
	@Test
	// We can see the published bid
	public void seePublishedBidTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		
		assertTrue(seller.publishBid(bid));
		assertEquals(1, buyer1.getPublishedBids().size());
	}
	
	@Test
	// A buyer can't make an offer on a unpublished bid
	public void makeOfferOnUnpublishedBidTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		assertFalse(buyer1.makeOffer( bid, 150 ));
	}
	
	@Test
	// A buyer can make an offer on a published bid
	public void makeOfferTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		assertTrue(seller.publishBid(bid));
		
		assertTrue(buyer1.makeOffer( bid, 500 ));
	}
	
	@Test
	// A user can't make an offer on his own bid
	public void makeOfferOnHisOwnBidTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		assertTrue(seller.publishBid(bid));
		
		assertFalse(seller.makeOffer( bid, 150 ));
	}
	
	@Test
	// A buyer can't make a negative offer
	public void makeOfferWithNegativePriceTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		assertTrue(seller.publishBid(bid));
		
		assertFalse(buyer1.makeOffer( bid, -50 ));
	}
	
	@Test
	// A buyer can't make an offer under the min price
	public void makeOfferUnderMinPriceTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		assertTrue(seller.publishBid(bid));
		
		assertFalse(buyer1.makeOffer( bid, 50 ));
	}
	
	@Test
	// A buyer can't make an offer under or equal to another offer on the same bid
	public void makeOfferUnderMaxOfferTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		assertTrue(seller.publishBid(bid));

		assertTrue(buyer1.makeOffer( bid, 150 ));
		assertFalse(buyer2.makeOffer( bid, 150 ));
	}

	@Test
	// A buyer can't make an offer on a outdated bid
	public void makeOfferOnOutdatedBidTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		assertTrue(seller.publishBid(bid));
		
		Clock.getSingleton().addOneYear();
		assertFalse(buyer1.makeOffer( bid, 150 ));
	}
}
