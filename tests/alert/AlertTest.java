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
	public void checkAlertSellerBidTest() {
		buyer1.makeOffer( this.bid, 110 );
		assertTrue(outContent.toString().contains("An offer has been made for your bid"));
	}

	@Test
	public void checkAlertOutReservedPriceReachedTest() {
		buyer1.makeOffer( this.bid, 200 );
		assertTrue(outContent.toString().contains("The reserved price of the bid "));
	}
	
	@Test
	public void checkAlertOutBidedTest() {
		buyer2.makeOffer( this.bid, 250 );
		assertTrue(outContent.toString().contains(", an upper offer has been made."));
	}
	
	@Test
	public void checkAlertBidCanceledTest() {
		seller.cancelBid(bid);
		assertTrue(outContent.toString().contains(" is canceled."));
	}
}