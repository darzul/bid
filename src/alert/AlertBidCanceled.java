package alert;

import bid.Bid;
import bid.User;

public class AlertBidCanceled extends Alert {

	public AlertBidCanceled(User user, Bid bid, AlertType type) {
		super(user, bid, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean trigger() {

		return false;
	}
}
