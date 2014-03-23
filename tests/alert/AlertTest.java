package alert;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.BeforeClass;
import org.junit.Test;

import bid.Bid;
import bid.BidManager;
import bid.Item;
import bid.User;

public class AlertTest {

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
		
		bid = BidManager.getInstance().getOwnedBids(seller).get(0);
		
		buyer1 = new User("Karibou", "Bouvard","Francois");
		buyer2 = new User("Hoshiyo", "Guyen", "Anna");
	}

	@Test
	// An user can create an Alert on not-owned bid
	public void createAlertOnBidTest() {
		assertTrue(buyer1.createAlert(bid, AlertType.BIDCANCELED));
	}
	
	@Test
	// An alert pop when an offer has been made on your owned bid
	public void checkAlertSellerBidTest() {
		buyer1.makeOffer( bid, 110 );
		assertTrue(outContent.toString().contains("An offer has been made for your bid"));
	}

	@Test
	// An alert pop when the reserved price is reached
	public void checkAlertOutReservedPriceReachedTest() {
		buyer1.makeOffer( bid, 200 );
		assertTrue(outContent.toString().contains("The reserved price of the bid "));
	}
	
	@Test
	// An alert pop when an upper offer than your has been made
	public void checkAlertOutBidedTest() {
		buyer2.makeOffer( bid, 250 );
		assertTrue(outContent.toString().contains(", an upper offer has been made."));
	}
	
	@Test
	// An alert pop when a bid has been cancelled
	public void checkAlertBidCanceledTest() {
		seller.cancelBid(bid);
		assertTrue(outContent.toString().contains(" is canceled."));
	}
}