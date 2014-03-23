package bid;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BidTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private Item item;
	private User seller;
	private User buyer1;
	private User buyer2;
	private Bid bidPublished;
	private Bid bidUnpublished;
	
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		item = new Item (0, "Un poney");
		
		seller = new User("DarzuL", "Bourderye", "Guillaume");
		seller.createBid(item, 10, 100);
		seller.createBid(item, 10, 100);
		
		bidPublished = seller.getOwnedBids().get(0);
		seller.publishBid(bidPublished);
	
		bidUnpublished = seller.getOwnedBids().get(0);
		
		buyer1 = new User("Karibou", "Bouvard","Francois");
		buyer2 = new User("Hoshiyo", "Guyen", "Anna");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void seePublishedBidTest() {
		assertEquals(1, Bid.getPublishedBids().size());
	}
	
	@Test
	public void makeOfferTest() {
		assertTrue(buyer1.makeOffer( this.bidPublished, 500 ));
	}
	
	@Test
	public void makeOfferOnHisOwnBidTest() {
		assertFalse(seller.makeOffer( this.bidPublished, 150 ));
	}
	
	@Test
	public void makeOfferOnUnpublishedBidTest() {
		assertFalse(buyer1.makeOffer( this.bidUnpublished, 150 ));
	}
	
	@Test
	public void makeOfferWithNegativePriceTest() {
		assertFalse(buyer1.makeOffer( this.bidPublished, -50 ));
	}
	
	@Test
	public void makeOfferUnderMinPriceTest() {
		// TODO: Il faut faire le test du prix > prix mini avant de cr�er l'offre
		assertFalse(buyer1.makeOffer( this.bidPublished, 50 ));
	}
	
	@Test
	public void makeOfferUnderMaxOfferTest() {
		assertFalse(buyer2.makeOffer( this.bidPublished, 150 ));
	}

	@Test
	public void makeOfferOnOutdatedBidTest() {
		assertFalse(buyer1.makeOffer( this.bidPublished, 150 ));
	}
}
