package bid;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import alert.Alert;
import alert.AlertManager;
import alert.AlertType;

public class Bid {

	private Date deadLine;
	private BidState state;
	private float minPrice;
	private float reservedPrice;
	private Item item;
	private User seller;
	private Offer bestOffer;
	private HashSet<Offer> previousOffers;
	
	// constructor
	public Bid(Date deadLine, BidState state, float minPrice,
			float reservedPrice, User seller) {
		this.deadLine = deadLine;
		this.state = state;
		this.minPrice = minPrice;
		this.reservedPrice = reservedPrice;
		this.seller = seller;
		this.bestOffer = null;
		this.previousOffers = new HashSet<Offer>();
	}
	
	// ---------------
	// bestOffer access
	// ---------------
	// getter simple
	// returns the bestOffer if it was allowed, null if not
	public Offer getBestOffer()
	{
		if(this.state == BidState.PUBLISHED
				|| this.state == BidState.ENDED)
			return this.bestOffer;
		return null;
	}
	// getter with authentication
	// returns the bestOffer if it was allowed, null if not
	public Offer getBestOffer(User user)
	{
		if(this.state == BidState.PUBLISHED
				|| this.state == BidState.ENDED
				|| this.seller == user)
			return this.bestOffer;
		for(Offer offer : previousOffers) {
		    if(offer.getUser() == user && this.state == BidState.CANCELED)
		    	return this.bestOffer;
		}
		return null;
	}
	// setter
	// returns true if it was allowed, false if not
	public boolean setBestOffer(Offer newOffer)
	{
		// to be applied, the new offer should :
		// - be higher than the last offer
		// - be higher the the minimum price
		// - be set on a published bid
		// - have a associated user
		if(this.bestOffer == null)
		{
			if (newOffer.getPrice() > this.minPrice
					&& this.state == BidState.PUBLISHED
					&& newOffer.getUser() != null){

				this.bestOffer = newOffer;
				this.previousOffers.add(newOffer);
				
				return true;
			}
			else
				return false;
		}
		else {
		if(newOffer.getPrice() > this.bestOffer.getPrice()
				&& newOffer.getPrice() > this.minPrice
				&& this.state == BidState.PUBLISHED
				&& newOffer.getUser() != null){
			this.bestOffer = newOffer;
			this.previousOffers.add(newOffer);
			
			return true;
		}
		else
			return false;
		}
	}

	
	// ---------------
	// deadLine access
	// ---------------
	// getter simple
	// returns the date if it was allowed, null if not
	public Date getDeadLine()
	{
		if(this.state == BidState.PUBLISHED
				|| this.state == BidState.ENDED)
			return this.deadLine;
		return null;
	}
	// getter with authentication
	// returns the date if it was allowed, null if not
	public Date getDeadLine(User user)
	{
		if(this.state == BidState.PUBLISHED
				|| this.state == BidState.ENDED
				|| this.seller == user)
			return this.deadLine;
		for(Offer offer : previousOffers) {
		    if(offer.getUser() == user && this.state == BidState.CANCELED)
		    	return this.deadLine;
		}
		return null;
	}
	// setter with authentication
	// returns true if it was allowed, false if not
	public boolean setDeadLine(Date newDeadLine, User user)
	{
		if(this.seller == user && this.state == BidState.CREATED){
			this.deadLine = newDeadLine;
			return true;
		}
		return false;
	}

	
	// ---------------
	// item access
	// ---------------
	// getter simple
	// returns the item if it was allowed, null if not
	public Item getItem()
	{
		if(this.state == BidState.PUBLISHED
				|| this.state == BidState.ENDED)
			return this.item;
		return null;
	}
	// getter with authentication
	// returns the item if it was allowed, null if not
	public Item getItem(User user)
	{
		if(this.state == BidState.PUBLISHED
				|| this.state == BidState.ENDED
				|| this.seller == user)
			return this.item;
		for(Offer offer : previousOffers) {
		    if(offer.getUser() == user && this.state == BidState.CANCELED)
		    	return this.item;
		}
		return null;
	}
	
	
	// ---------------
	// seller access
	// ---------------
	// getter simple
	// returns the seller if it was allowed, null if not
	//TODO: WTF ?
/*	bid.getSeller()
	{
		if(this.state == BidState.PUBLISHED
				|| this.state == BidState.ENDED)
			return this.seller;
		return null;
	}
	// getter with authentication
	// returns the seller if it was allowed, null if not
	public User getSeller(User user)
	{
		if(this.state == BidState.PUBLISHED
				|| this.state == BidState.ENDED
				|| this.seller == user)
			return this.seller;
		for(Offer offer : previousOffers) {
		    if(offer.getUser() == user && this.state == BidState.CANCELED)
		    	return this.seller;
		}
		return null;
	}
*/
	public User getSeller() {
		return this.seller;
	}

	// ------------
	// state access
	// ------------
	// getter with authentication

	//TODO: Pourquoi une auth ? 
	/*public BidState getState(User user)
	{
		if(this.seller == user)
			return state;
		return null;
	}*/
	public BidState getState()
	{
		return state;
	}
	// setter
	// returns true if it was allowed, false if not
	public boolean setState(BidState newState, User user)
	{
		// to change the state from outside :
		//   - the user must be the seller
		if(this.seller != user){
			return false;
		}

		if (newState == BidState.CANCELED){
			
			ArrayList <Alert> alerts = AlertManager.getInstance().getAlerts(this);
			
				for (Alert alert: alerts){
					if (alert.getType().equals(AlertType.BIDCANCELED)){
						alert.trigger();
					}
				}
			BidManager.getInstance().deleteBid(this, user);
			
			return true;
		 }
		// to change the state from outside :
		// - the user must be the seller
		// - the current state must be different from ENDED
		if(this.state == BidState.ENDED)
			return false;
		// if the seller wants to cancel or hide a bid :
		// - it must have not reach the reservedPrice
		if(newState == BidState.CREATED){
			if (bestOffer == null) {
				this.state = newState;
				return true;
			}
			else if (bestOffer.getPrice() >= this.reservedPrice) {
				this.state = newState;
				return true;
			}
		}

		// you can always publish a bid
		if(newState == BidState.PUBLISHED){
			this.state = newState;
			return true;
		}
		return false;
	}
	
	
	// ---------------
	// minPrice access
	// ---------------
	// getter simple
	// returns the minPrice
	public float getMinPrice()
	{
		return minPrice;
	}
	// setter with authentication
	// returns true if it was allowed, false if not
	public boolean setMinPrice(float minPrice, User user)
	{
		if(user == this.seller && this.state == BidState.CREATED){
			this.minPrice = minPrice;
			return true;
		}
		return false;
	}
	
	
	// -------------------
	// reservedPrice access
	// -------------------
	// getter with authentication
	// returns the reservedPrice if it was allowed, -1 in not
	public float getReservedPrice(User user)
	{
		if(this.seller == user)
			return reservedPrice;
		return -1;
	}
	// setter with authentication
	// returns true if it was allowed, false if not
	public boolean setReservedPrice(float reservedPrice, User user)
	{
		if(user == this.seller && this.state == BidState.CREATED){
			this.reservedPrice = reservedPrice;
			return true;
		}
		return false;
	}
	
	// -------------------
	// previousOffer access
	// -------------------
	// get with authentication
	// returns the list if it was allowed, null in not
	public HashSet<Offer> getPreviousOffers(User user) {
		if(user == this.seller)
			return previousOffers;
		return null;
	}
	
	
	// search alerts corresponding to the last event and triggers it
	private void checkAlerts()
	{
		List<Alert> alerts = AlertManager.getInstance().getAlerts(this);
		for(Alert alert : alerts) {
			// TODO
		}
	}
	
}
