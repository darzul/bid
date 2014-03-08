package bid;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bid.User;

public class UserTest {

	User seller;
	User buyerA;
	User buyerB;
	Item item;
	
	@Before
	public void setUp() throws Exception {
		seller = new User ("Karibou", "Francois", "Bouvard");
		buyerA = new User ("Hoshiyo", "Anna", "Guyen");
		buyerB = new User ("DarzuL", "Guillaume", "Bourderye");
		
		item = new Item ("Lapin en peluche", 100);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createBidTest() throws Exception {
		seller.createBid(item, deadLine, 100);
		assertEquals( 1, seller.getBids().count );
	}

}
