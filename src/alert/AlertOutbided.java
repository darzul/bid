package alert;

import user.User;
import bid.Bid;

public class AlertOutbided extends Alert {

	// constructor
	public AlertOutbided(User user, Bid bid, AlertType type) {
		super(user, bid, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean trigger()
	{
		String mess = "For the bid "+ getBid().getItem(user).getId() + ", an upper offer has been made.";
		sendMessage(mess);
		
		return true;
	}
}
