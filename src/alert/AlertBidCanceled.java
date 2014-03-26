package alert;

import user.User;
import bid.Bid;

public class AlertBidCanceled extends Alert {

	// constructor
	public AlertBidCanceled(User user, Bid bid, AlertType type) {
		super(user, bid, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean trigger() 
	{
		String mess = "The bid "+ getBid().getItem(user).getId() + " is canceled.";
		sendMessage(mess);
		
		return true;
	}
}
