package bid;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import user.User;
import alert.AlertManager;

public class UserTest {

	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static Item item;
	private static User seller;
	private static User buyer;
	
	@BeforeClass
	public static void initialize() throws Exception {
		System.setOut(new PrintStream(outContent));
		item = new Item (0, "Un poney qui boite");
		seller = new User("DarzuL", "Bourderye", "Guillaume");
		buyer = new User("Hoshiyo", "Guyen", "Anna");
	}

	@After
	public void tearDown() throws Exception {
		BidManager.getInstance().clearBids();
		AlertManager.getInstance().clearAlerts();
	}

	@Test
	// An user can create a bid without a reserved price
	public void createBidWithoutReservedPriceTest() {
		assertTrue(seller.createBid(item, 10, 100));
	}
	
	@Test
	// An user can create a bid with a reserved price
	public void createBidWithReservedPriceTest() {
		assertTrue(seller.createBid(item, 10, 100, 200));
	}
	
	@Test
	// A user can publish his bid
	public void publishOwnedBid() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		assertTrue(seller.publishBid(bid));
	}
	
	@Test
	// // A user can't publish a bid owned by another user
	public void publishNotOwnedBid() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		assertFalse(buyer.publishBid(bid));
	}
	
	@Test
	// An user can't cancel a bid which owned to another user
	public void cancelNoOwnedBidTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		assertFalse(buyer.cancelBid(BidManager.getInstance().getOwnedBids(seller).get(0)));
	}
	
	@Test
	// An user can cancel a his bid
	public void cancelOwnedBidTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		assertTrue(seller.cancelBid(BidManager.getInstance().getOwnedBids(seller).get(0)));
	}


	@Test
	// An user can't create a outpassed bid
	public void createPassedDateBidTest() {
		assertFalse(seller.createBid(item, -1, 100));
	}

	@Test
	// An user can't create a bid with a min price upper than reserved price
	public void createLowerReservedPriceThanMinPriceTest() {
		assertFalse(seller.createBid(item, 10, 100, 50));
	}
	
	@Test
	// An user can see the reserved price of his bid
	public void getReservedPriceToSellerTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		assertTrue( bid.getReservedPrice(seller) == 200 );
	}
	
	@Test
	// An user can't see the reserved price of his bid
	public void getReservedPriceToBuyerTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		Bid bid = seller.getOwnedBids().get(0);
		assertTrue( bid.getReservedPrice(buyer) == -1 );
	}	
	
	@Test
	// An user can get his owned bid
	public void getOwnedBidTest() {
		assertTrue (seller.createBid(item, 10, 100, 200));
		assertEquals (1, BidManager.getInstance().getOwnedBids(seller).size());
	}
	
	@Test
	// An user can't create a bid with a negative price
	public void createBidNegativePriceTest() {
		assertFalse(buyer.createBid(item, 10, -100));
	}
}
