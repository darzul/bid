package bid;

import java.util.Date;
import java.util.List;

import alert.AlertType;

public class User {

	String login;
	String lastName;
	String firstName;
	List<Bid> ownedBids;
	
	private boolean createBid (Item item, Date deadLine, float price)
	{
		return false;
	}
	
	private boolean createBid (Item item, Date deadLine, float price, float reservedPrice)
	{
		return false;
	}
	
	private boolean cancelBid (Bid bid)
	{
		return false;
	}
	
	private boolean publishBid (Bid bid)
	{
		return false;
	}
	
	private boolean hideBid (Bid bid)
	{
		return false;
	}
	
	private boolean setReservedPrice (float price)
	{
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
}
