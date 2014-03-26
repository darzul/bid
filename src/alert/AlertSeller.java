package alert;

import user.User;
import bid.Bid;

public class AlertSeller extends Alert {

	// constructor
	public AlertSeller(User user, Bid bid, AlertType type) {
		super(user, bid, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean trigger() 
	{
		String mess = "An offer has been made for your bid "+ getBid().getItem(user).getId() + ".";
		sendMessage(mess);
		
		return true;
	}
}
