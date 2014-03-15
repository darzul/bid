package bid;

import java.util.Date;
import java.util.ArrayList;

public class Bid {

	private Date deadLine;
	private BidState state;
	private float minPrice;
	private float reservedPrice;
	Item item;
	public Date getDeadLine() {
		return deadLine;
	}

	public int getItemId() {
		return this.item.getId();
	}
	
	public String getItemDescription() {
		return this.item.getDescription();
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}



	public BidState getState() {
		return state;
	}



	public void setState(BidState state) {
		this.state = state;
	}



	public float getMinPrice() {
		return minPrice;
	}



	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}



	public float getReservedPrice() {
		return reservedPrice;
	}



	public void setReservedPrice(float reservedPrice) {
		this.reservedPrice = reservedPrice;
	}

	private User seller;
	private Offer lastOffer;
	static private ArrayList<Bid> bids;
	
	public Bid(Date deadLine, BidState state, float minPrice,
			float reservedPrice, User seller) {
		super();
		this.deadLine = deadLine;
		this.state = state;
		this.minPrice = minPrice;
		this.reservedPrice = reservedPrice;
		this.seller = seller;
		this.lastOffer = null;
	}



	// search alerts corresponding to the last event
	private void checkAlerts ()
	{
		
	}
	
	// search for all the bids owned by a user in the bid list
	static public ArrayList<Bid> getBids (User user)
	{
		ArrayList<Bid> ownedBids = new ArrayList<Bid>();
		
		for(Bid bid : bids) {
		    if(bid.seller == user)
		    	ownedBids.add(bid);
		}
		return ownedBids;
	}
}
