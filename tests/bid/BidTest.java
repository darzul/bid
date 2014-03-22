package bid;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BidTest {

	Item item;
	User buyer;
	User seller1;
	User seller2;
	
	@Before
	public void setUp() throws Exception {
		item = new Item ();
		buyer = new User();
		seller1 = new User();
		seller2 = new User();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		buyer.createBid(item, 10, 100);
		buyer.
	}

}
