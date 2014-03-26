package alert;

import user.User;
import bid.Bid;

public class AlertOutReservedPriceReached extends Alert {

	public int publicTest=0;

	// constructor
	public AlertOutReservedPriceReached(User user, Bid bid, AlertType type) {
		super(user, bid, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean trigger()
	{
		String mess = "The reserved price of the bid "+ bid.getItem(user).getId() + " is reached by an offer.";
		sendMessage(mess);
		
		return true;
	}
}
