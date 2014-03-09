package alert;

import bid.Bid;
import bid.User;

public class AlertBidCanceled extends Alert {

	public AlertBidCanceled (User user, Bid bid, AlertType type)
	{
		this.bid = bid;
		this.user = user;
		this.type = type;
	}

	@Override
	protected boolean trigger() 
	{
		String mess = "The bid "+ bid.getItemId() + " is canceled.";
		sendMessage(this.user, mess);
		
		return true;
	}
}
