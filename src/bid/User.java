package bid;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.TimeZone;

import alert.AlertType;

public class User {

	String login;
	String lastName;
	String firstName;
	ArrayList<Bid> ownedBids = null;
	
	// create a bid without any reservedPrice
	private Bid createBid (Item item, Date deadLine, float minPrice)
	{
		Bid newBid = new Bid(deadLine, BidState.CREATED, minPrice, minPrice, this);
		//TODO : quelle valeur pour le reservePrice ˆ minPrice ?
		return newBid;
	}
	
	// create a bid with a reservedPrice
	private Bid createBid (Item item, Date deadLine, float minPrice, float reservedPrice)
	{
		Bid newBid = new Bid(deadLine, BidState.CREATED, minPrice, reservedPrice, this);
		return newBid;
	}
	
	// removes a bid
	private Bid cancelBid (Bid bid)
	{
		for(Bid bidToCancel : ownedBids) {
		    if(bidToCancel == bid){
		    	bidToCancel.setState(BidState.CANCELED);
		    }
		}
		return bid;
	}
	
	private Bid publishBid (Bid bid)
	{
		for(Bid bidToPublish : ownedBids) {
		    if(bidToPublish == bid){
		    	bidToPublish.setState(BidState.PUBLISHED);
		    }
		}
		return bid;
	}
	
	private Bid hideBid (Bid bid)
	{
		for(Bid bidToHide : ownedBids) {
		    if(bidToHide == bid){
		    	bidToHide.setState(BidState.CREATED);
		    }
		}
		return bid;
	}
	
	private boolean setReservedPrice (float reservedPrice, Bid bid)
	{
		for(Bid bidToEdit : ownedBids) {
		    if(bidToEdit == bid){
		    	bidToEdit.setReservedPrice(reservedPrice);
		    }
		}
		return false;
	}
	
	private boolean setMinPrice (float price)
	{
		return false;
	}
	
	private boolean makeOffer (Bid bid, float price)
	{
		return false;
	}
	
	private boolean createAlert (Bid bid, AlertType alertType)
	{
		return false;
	}
	
	public ArrayList<Bid> getOwnedBids ()
	{
		return ownedBids;
	}
}
