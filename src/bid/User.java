package bid;

import java.util.ArrayList;
import java.util.Date;

import alert.AlertType;

public class User {

	String login;
	String lastName;
	String firstName;
	ArrayList<Bid> ownedBids = null;
	
	public boolean createBid (Item item, Date deadLine, float price)
	{
		return false;
	}
	
	public boolean createBid (Item item, Date deadLine, float price, float reservedPrice)
	{
		return false;
	}
	
	public boolean cancelBid (Bid bid)
	{
		return false;
	}
	
	public boolean publishBid (Bid bid)
	{
		return false;
	}
	
	public boolean hideBid (Bid bid)
	{
		return false;
	}
	
	public boolean setReservedPrice (float price)
	{
		return false;
	}
	
	public boolean setMinPrice (float price)
	{
		return false;
	}
	
	public boolean makeOffer (Bid bid, float price)
	{
		return false;
	}
	
	public boolean createAlert (Bid bid, AlertType alertType)
	{
		return false;
	}
	
	public ArrayList<Bid> getOwnedBids ()
	{
		return this.ownedBids;
	}	
}
