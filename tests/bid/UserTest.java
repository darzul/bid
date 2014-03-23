package bid;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private Item item;
	private User buyer;
	private User seller;
	private Bid bid;

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		item = new Item (0, "Un poney");
		buyer = new User("DarzuL", "Bourderye", "Guillaume");
		
		seller = new User("Hoshiyo", "Guyen", "Anna");
		seller.createBid(item, 10, 100, 200);
		bid = seller.getOwnedBids().get(0);
	}

	@After
	public void tearDown() throws Exception {
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
	// An user can see his owned bid
	public void getOwnedBidTest() {
		assertEquals (1, seller.getOwnedBids().size());
	}
	
	@Test
	// An user can't create a bid with a negative price
	public void createBidNegativePriceTest() {
		assertFalse(buyer.createBid(item, 10, -100));
	}
}
