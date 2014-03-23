package alert;

import bid.Bid;
import bid.User;

public class AlertSeller extends Alert {

	// constructor
	public AlertSeller(User user, Bid bid, AlertType type) {
		super(user, bid, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean trigger() 
	{
		String mess = "An offer has been made for your bid "+ getBid().getItem().getId() + ".";
		sendMessage(mess);
		
		return true;
	}
}
