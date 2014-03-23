package alert;

import java.util.List;

import bid.Bid;
import bid.User;

public abstract class Alert {

	User user;
	Bid bid;
	AlertType type;
	
	public Alert(User user, Bid bid, AlertType alertType) {
		this.user = user;
		this.bid = bid;
		this.type = alertType;
	}

	protected abstract boolean trigger ();
	
	public void sendMessage(User user, String mess)
	{
		user.sendMessage(mess);
	}
}