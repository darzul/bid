package bid;


import java.util.ArrayList;
import java.util.Date;

import alert.Alert;
import alert.AlertFactory;
import alert.AlertType;

public class User {

	private String login;
	private String lastName;
	private String firstName;
	private ArrayList<Bid> ownedBids = null;
	private Date deadline;
	
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
	public boolean cancelBid (Bid bid)
	{
		for(Bid bidToCancel : ownedBids) {
		    if(bidToCancel == bid){
		    	bidToCancel.setState(BidState.CANCELED);
		    	return true;
		    }
		}
		return false;
	}
	
	// publish a bid
	public boolean publishBid (Bid bid)
	{
		for(Bid bidToPublish : ownedBids) {
		    if(bidToPublish == bid){
		    	bidToPublish.setState(BidState.PUBLISHED);
		    	return true;
		    }
		}
		return false;
	}
	
	// unpublish a bid = hide a bid
	public boolean hideBid (Bid bid)
	{
		for(Bid bidToHide : ownedBids) {
		    if(bidToHide == bid){
		    	bidToHide.setState(BidState.CREATED);
		    	return true;
		    }
		}
		return false;
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
		Alert newAlert = AlertFactory.structureAlert(this, bid, alertType);
		return newAlert;
	}
	
	//getter
	public ArrayList<Bid> getOwnedBids ()
	{
		return this.ownedBids;
	}	
}
