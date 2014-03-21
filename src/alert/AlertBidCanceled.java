package alert;

import bid.Bid;
import bid.User;

public class AlertBidCanceled extends Alert {

	// constructor
	public AlertBidCanceled(User user, Bid bid, AlertType type) {
		super(user, bid, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean trigger() 
	{
		String mess = "The bid "+ bid.getItemId() + " is canceled.";
		sendMessage(this.user, mess);
		
		return true;
	}
}
