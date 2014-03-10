package bid;


import java.util.ArrayList;
import java.util.Date;

import alert.Alert;
import alert.AlertType;

public class User {

	String login;
	String lastName;
	String firstName;
	ArrayList<Bid> ownedBids = null;
	
	// constructor
	public User(String login, String lastName, String firstName) {
		super();
		this.login = login;
		this.lastName = lastName;
		this.firstName = firstName;
		this.ownedBids = null;
	}

	// create a bid without any reservedPrice
	public Bid createBid (Item item, Date deadLine, float minPrice)
	{
		Bid newBid = new Bid(deadLine, BidState.CREATED, minPrice, minPrice, this);
		//TODO : quelle valeur pour le reservePrice ˆ minPrice ?
		return newBid;
	}
	
	// create a bid with a reservedPrice
	public Bid createBid (Item item, Date deadLine, float minPrice, float reservedPrice)
	{
		Bid newBid = new Bid(deadLine, BidState.CREATED, minPrice, reservedPrice, this);
		return newBid;
	}
	
	// removes a bid
	public Bid cancelBid (Bid bid)
	{
		for(Bid bidToCancel : ownedBids) {
		    if(bidToCancel == bid){
		    	bidToCancel.setState(BidState.CANCELED);
		    }
		}
		return bid;
	}
	
	// publish a bid
	public Bid publishBid (Bid bid)
	{
		for(Bid bidToPublish : ownedBids) {
		    if(bidToPublish == bid){
		    	bidToPublish.setState(BidState.PUBLISHED);
		    }
		}
		return bid;
	}
	
	// unpublish a bid = hide a bid
	public Bid hideBid (Bid bid)
	{
		for(Bid bidToHide : ownedBids) {
		    if(bidToHide == bid){
		    	bidToHide.setState(BidState.CREATED);
		    }
		}
		return bid;
	}
	
	// change the reservedPrice of a bid
	public boolean setReservedPrice (float reservedPrice, Bid bid)
	{
		for(Bid bidToEdit : ownedBids) {
		    if(bidToEdit == bid){
		    	bidToEdit.setReservedPrice(reservedPrice);
		    	return true;
		    }
		}
		return false;
	}
	
	// change the minPrice of a bid
	public boolean setMinPrice (float minPrice, Bid bid)
	{
		for(Bid bidToEdit : ownedBids) {
		    if(bidToEdit == bid){
		    	bidToEdit.setMinPrice(minPrice);
		    	return true;
		    }
		}
		return false;
	}
	
	// make an offer on a bid
	public Offer makeOffer (Bid bid, float price)
	{
		Offer newOffer = new Offer(this, price, bid);
		return newOffer;
	}
	
	// creates an alert on a bid
	public Alert createAlert (Bid bid, AlertType alertType)
	{
		Alert newAlert = Alert.factory(this, bid, alertType);
		// TODO ce sera peu etre pas "factory"
		return newAlert;
	}
	
	//getter
	public ArrayList<Bid> getOwnedBids ()
	{
		return this.ownedBids;
	}	
}
