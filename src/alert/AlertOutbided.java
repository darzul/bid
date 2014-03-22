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
	protected boolean trigger()
	{
		String mess = "For the bid "+ bid.getItem().getId() + ", an upper offer has been made.";
		sendMessage(this.user, mess);
		
		return true;
	}
}
