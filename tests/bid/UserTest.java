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
	private User seller1;
	private User seller2;
	private Bid bid;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		item = new Item ();
		buyer = new User("DarzuL", "Bourderye", "Guillaume");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createBidTest() {
		this.bid = buyer.createBid(item, 10, 100);
		assertNotNull(this.bid);
	}

	@Test
	public void createPassedDateBidTest() {
		Bid res = buyer.createBid(item, -1, 100);
		assertNull(res);
	}

	@Test
	public void createLowerReservedPriceThanMinPriceTest() {
		Bid res = buyer.createBid(item, -10, 100, 50);
		assertNull(res);
	}
	
	@Test
	public void getReservedPriceTest() {
		bid.getReservedPrice();
	}	
	
	@Test
	public void getOwnedBidTest() {
		assertEquals (1, buyer.getOwnedBids().size());
	}
	
	@Test
	public void createBidNegativePriceTest() {
		Bid res = buyer.createBid(item, 10, -100);
		assertNull (res);
	}
}
