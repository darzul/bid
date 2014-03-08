package alert;

import bid.Bid;
import bid.User;

public class AlertOutbided extends Alert {

	public AlertOutbided (User user, Bid bid, AlertType type)
	{
		this.bid = bid;
		this.user = user;
		this.type = type;
	}

	@Override
	protected boolean trigger() {

		return false;
	}
}
