package alert;

import bid.Bid;
import bid.User;

public class AlertBidCanceled extends Alert {

	public AlertBidCanceled (User user, Bid bid, AlertType type)
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
