package alert;

import java.util.List;

import bid.Bid;
import bid.User;

public abstract class Alert {

	User user;
	Bid bid;
	AlertType type;
	private static List<Alert> alerts;
	
	public Alert(User user, Bid bid, AlertType alertType) {
		this.user = user;
		this.bid = bid;
		this.type = type;
	}
	
	public static Alert factory(User user, Bid bid, AlertType alertType){
		// TODO
		return null;
	}

	protected abstract boolean trigger ();
	
	public List<Alert> getAlerts (User user)
	{
		return alerts;
	}
	
	public List<Alert> getAlerts (Bid bid)
	{
		return alerts;
	}
	
	public void sendMessage(User user, String mess)
	{
		System.out.println("You have got a message.");
	}
}