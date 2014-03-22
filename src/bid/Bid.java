package bid;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
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
	private HashSet<Offer> previousOffers;
	static private ArrayList<Bid> bids;
	
	// constructor
	public Bid(Date deadLine, BidState state, float minPrice,
			float reservedPrice, User seller) {
		this.deadLine = deadLine;
		this.state = state;
		this.minPrice = minPrice;
		this.reservedPrice = reservedPrice;
		this.seller = seller;
		this.lastOffer = new Offer(minPrice, this);
	}
	
	// getter
	// returns the date if it was allowed, null if not
	public Offer getLastOffer(User user){
		if(this.seller == user)
			return lastOffer;
		else
			return null;
	}

	// setter
	// returns true if it was allowed, false if not
	public boolean setLastOffer(Offer newOffer) {
		if(newOffer.price > this.lastOffer.price
				&& this.state == BidState.PUBLISHED
				&& newOffer.user != null){
			this.lastOffer = newOffer;
			this.previousOffers.add(newOffer);
			return true;
		}
		return false;
	}

	// getter simple
	// returns the date if it was allowed, null if not
	public Date getDeadLine() {
		if(this.state == BidState.PUBLISHED
				|| this.state == BidState.ENDED)
			return deadLine;
		else
			return null;
	}
	
	// getter with authentification
	// returns the date if it was allowed, null if not
	public Date getDeadLine(User user) {
		if(this.state == BidState.PUBLISHED
				|| this.state == BidState.ENDED
				|| this.seller == user)
			return deadLine;
		else
			return null;
	}
	
	// setter
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	// getter
	public int getItemId() {
		return this.item.getId();
	}
	
	// getter
	public String getItemDescription() {
		return this.item.getDescription();
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
