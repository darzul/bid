package alert;

import bid.Bid;
import bid.User;

public class AlertOutReservedPriceReached extends Alert {

	public int publicTest=0;

	public AlertOutReservedPriceReached(User user, Bid bid, AlertType type) {
		super(user, bid, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean trigger() {

		return false;
	}
}
