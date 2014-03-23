package bid;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Stack;

import alert.Alert;
import alert.AlertFactory;
import alert.AlertManager;
import alert.AlertType;

public class User {

	private String login;
	private String lastName;
	private String firstName;
	//create stack to stock alert
	private Stack<String> messages = new Stack<String>(); 
	private ArrayList<Bid> ownedBids;
	
	// constructor
	public User(String login, String lastName, String firstName) {
		super();
		this.login = login;
		this.lastName = lastName;
		this.firstName = firstName;
		this.ownedBids = new ArrayList<Bid>();
	}

	
	// create a bid without any reservedPrice
	public boolean createBid (Item item, int nbDay, float minPrice)
	{
		//TODO : quelle valeur pour le reservePrice � minPrice ?

		if(item != null && nbDay > 0 && minPrice >= 0)
		{
			Date deadLine = new Date (Clock.getSingleton().getTime() + 1000 * 3600 * 24 * nbDay);

			Bid newBid = new Bid(deadLine, BidState.CREATED, minPrice, minPrice, this);
			this.ownedBids.add(newBid);
			
			if(newBid != null)
				return true;
		}
		
		return false;
	}

	// create a bid with a reservedPrice
	public boolean createBid (Item item, int nbDay, float minPrice, float reservedPrice)
	{
		// checks validity of parameters
		if(item != null && nbDay > 0 && minPrice >= 0 && reservedPrice >= minPrice)
		{
			Date deadLine = new Date (Clock.getSingleton().getTime() + 1000 * 3600 * 24 * nbDay);
			
			Bid newBid = new Bid(deadLine, BidState.CREATED, minPrice, reservedPrice, this);
			this.ownedBids.add(newBid);
			
			if(newBid != null)
				return true;
		}
		
		return false;
	}
	
	
	
	// removes a bid
	public boolean cancelBid (Bid bid)
	{
		return bid.setState(BidState.CANCELED, this);
	}
	
	// publish a bid
	public boolean publishBid (Bid bid)
	{
		return bid.setState(BidState.PUBLISHED, this);
	}
	
	// unpublish a bid = hide a bid
	public boolean hideBid (Bid bid)
	{
		return bid.setState(BidState.CREATED, this);
	}
	
	// change the reservedPrice of a bid
	public boolean setReservedPrice (float reservedPrice, Bid bid)
	{
		return bid.setReservedPrice(reservedPrice, this);
	}
	
	// change the minPrice of a bid
	public boolean setMinPrice (float minPrice, Bid bid)
	{
		return bid.setMinPrice(minPrice, this);
	}
	
	// make an offer on a bid
	public boolean makeOffer (Bid bid, float price)
	{
		// checks if the user is not the bid's owner
		for(Bid ownedBid : ownedBids)
		{
			if(ownedBid == bid)
				return false;
		}
		
		// if not, checks validity of parameters
		if(bid != null && bid.getState() == BidState.PUBLISHED && 
				price > bid.getMinPrice() && 
				bid.getDeadLine().getTime() > Clock.getSingleton().getTime())
		{
			if (bid.getBestOffer() != null && price <= bid.getBestOffer().getPrice()){
					return false;
			}
			
			Offer newOffer = new Offer(this, price, bid);
			if(newOffer != null)
				return true;
		}
		return false;
	}
	
	// creates an alert on a bid
	public boolean createAlert (Bid bid, AlertType alertType)
	{
		// checks validity of parameters
		if(bid != null && AlertType.checkValidity(alertType))
		{
			Alert newAlert = AlertFactory.structureAlert(this, bid, alertType);
			if(newAlert != null)
				return true;
		}
		return false;
	}
	
	public boolean sendMessage(String mess) {
		messages.push(mess);
		
		return true;
	}
	
	public int getNumberMessage(Stack<String> messages) {
		return messages.size();
	}
	
	//getter
	public ArrayList<Bid> getOwnedBids ()
	{
		return this.ownedBids;
	}	
}
