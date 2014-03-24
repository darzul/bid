package alert;

import bid.Bid;
import bid.User;

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
		String mess = "The reserved price of the bid "+ getBid().getItem().getId() + " is reached by an offer.";
		sendMessage(mess);
		System.err.println("lol");
		
		return true;
	}
}
