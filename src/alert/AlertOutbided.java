package alert;

import bid.Bid;
import bid.User;

public class AlertOutbided extends Alert {

	public AlertOutbided(User user, Bid bid, AlertType type) {
		super(user, bid, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean trigger() {

		return false;
	}
}
