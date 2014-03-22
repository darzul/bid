package bid;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import alert.Alert;

public class Bid {

	private Date deadLine;
	private BidState state;
	private float minPrice;
	private float reservedPrice;
	Item item;
	private User seller;
	private Offer lastOffer;
	static private ArrayList<Bid> bids;
	
	// constructor
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
	
	// getter
	public Date getDeadLine() {
		return deadLine;
	}

	// getter
	public int getItemId() {
		return this.item.getId();
	}
	
	// getter
	public String getItemDescription() {
		return this.item.getDescription();
	}

	// setter
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	// getter
	public BidState getState() {
		return state;
	}

	// setter
	public void setState(BidState state) {
		this.state = state;
	}

	//getter
	public float getMinPrice() {
		return minPrice;
	}

	// setter
	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	// getter
	public float getReservedPrice() {
		return reservedPrice;
	}

	// setter
	public void setReservedPrice(float reservedPrice) {
		this.reservedPrice = reservedPrice;
	}

	// search alerts corresponding to the last event and triggers it
	private void checkAlerts()
	{
		List<Alert> alerts = Alert.getAlerts(this);
		for(Alert alert : alerts) {
			// TODO
		}
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
