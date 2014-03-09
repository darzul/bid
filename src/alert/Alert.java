package alert;

import java.util.List;

import bid.Bid;
import bid.User;

public abstract class Alert {

	User user;
	Bid bid;
	AlertType type;
	private static List<Alert> alerts;
	
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