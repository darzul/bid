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
		String mess = "An offer has been made for your bid "+ bid.getItemId() + ".";
		sendMessage(this.user, mess);
		
		return true;
	}
}
