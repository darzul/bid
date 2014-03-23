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
	public boolean trigger() 
	{
		String mess = "The bid "+ getBid().getItem().getId() + " is canceled.";
		sendMessage(mess);
		
		return true;
	}
}
