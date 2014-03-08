package alert;

import bid.Bid;
import bid.User;

public class AlertSeller extends Alert {
	
	public AlertSeller (User user, Bid bid, AlertType type)
	{
		this.bid = bid;
		this.user = user;
		this.type = type;
	}

	@Override
	protected boolean trigger() 
	{
		System.out.println("AlertSeller");
		return false;
	}
}
