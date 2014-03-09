package alert;

import bid.Bid;
import bid.User;

public class AlertOutReservedPriceReached extends Alert {

	public int publicTest=0;
	
	public AlertOutReservedPriceReached (User user, Bid bid, AlertType type)
	{
		this.bid = bid;
		this.user = user;
		this.type = type;
	}

	@Override
	protected boolean trigger()
	{
		String mess = "The reserved price of the bid "+ bid.getItemId() + " is reached by an offer.";
		sendMessage(this.user, mess);
		
		return true;
	}
}
