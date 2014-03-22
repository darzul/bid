package alert;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bid.Bid;
import bid.Item;
import bid.User;

public class AlertTest {

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
		bid = buyer.createBid(item, 10, 100, 150);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void checkAlertSellerBidTest() {
		seller1.makeOffer( this.bid, 110 );
		assertTrue(outContent.toString().contains("An offer has been made for your bid"));
	}

	@Test
	public void checkAlertOutReservedPriceReachedTest() {
		seller1.makeOffer( this.bid, 200 );
		assertTrue(outContent.toString().contains("The reserved price of the bid "));
	}
	
	@Test
	public void checkAlertOutBidedTest() {
		seller2.makeOffer( this.bid, 250 );
		assertTrue(outContent.toString().contains(", an upper offer has been made."));
	}
	
	@Test
	public void checkAlertBidCanceledTest() {
		buyer.cancelBid(bid);
		assertTrue(outContent.toString().contains(" is canceled."));
	}
}