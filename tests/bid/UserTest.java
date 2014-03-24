package bid;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest {

	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static Item item;
	private static User seller;
	private static User buyer;
	private static Bid bid;
	
	@BeforeClass
	public static void initialize() throws Exception {
		System.setOut(new PrintStream(outContent));
		item = new Item (0, "Un poney");
		
		seller = new User("DarzuL", "Bourderye", "Guillaume");
		seller.createBid(item, 10, 100, 200);
		
		bid = BidManager.getInstance().getOwnedBids(seller).get(0);
		
		buyer = new User("Hoshiyo", "Guyen", "Anna");
	}
	
	@AfterClass
	public static void clean() throws Exception {
		BidManager.getInstance().clearBids();
	}

	@Test
	// A user can publish his bid
	public void publishOwnedBid() {
		assertTrue(seller.publishBid(bid));
	}
	
	@Test
	// // A user can't publish a bid owned by another user
	public void publishNotOwnedBid() {
		assertFalse(buyer.publishBid(bid));
	}
	
	@Test
	// An user can create a bid without a reserved price
	public void createBidWithoutReservedPriceTest() {
		assertTrue(seller.createBid(item, 10, 100));
	}
	
	@Test
	// An user can't cancel a bid which owned to another user
	public void cancelNoOwnedBidTest() {
		assertFalse(buyer.cancelBid(BidManager.getInstance().getOwnedBids(seller).get(1)));
	}
	
	@Test
	// An user can cancel a his bid
	public void cancelOwnedBidTest() {
		assertTrue(seller.cancelBid(BidManager.getInstance().getOwnedBids(seller).get(0)));
	}
	
	@Test
	// An user can create a bid with a reserved price
	public void createBidWithReservedPriceTest() {
		assertTrue(seller.createBid(item, 10, 100, 200));
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
		assertTrue( bid.getReservedPrice(seller) == 200 );
	}
	
	@Test
	// An user can't see the reserved price of his bid
	public void getReservedPriceToBuyerTest() {
		assertTrue( bid.getReservedPrice(buyer) == -1 );
	}	
	
	@Test
	// An user can get his owned bid
	public void getOwnedBidTest() {
		assertEquals (2, BidManager.getInstance().getOwnedBids(seller).size());
	}
	
	@Test
	// An user can't create a bid with a negative price
	public void createBidNegativePriceTest() {
		assertFalse(buyer.createBid(item, 10, -100));
	}
}
