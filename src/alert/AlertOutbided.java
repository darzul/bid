package alert;

import bid.Bid;
import bid.User;

public class AlertOutbided extends Alert {

	// constructor
	public AlertOutbided(User user, Bid bid, AlertType type) {
		super(user, bid, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean trigger()
	{
		String mess = "For the bid "+ getBid().getItem().getId() + ", an upper offer has been made.";
		sendMessage(mess);
		
		return true;
	}
}
