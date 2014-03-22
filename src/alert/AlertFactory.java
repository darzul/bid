package alert;

import bid.Bid;
import bid.User;

public abstract class AlertFactory {
	
	public static Alert structureAlert(User user, Bid bid, AlertType type)
	{
		Alert alert=null;
		
		switch(type)
		{
			case USER:
				alert = new AlertSeller(user, bid, type);
				break;
				
			case RESERVEDPRICEREACHED:
				alert = new AlertOutReservedPriceReached(user, bid, type);
				break;
				
			case BIDCANCELED:
				alert = new AlertBidCanceled(user, bid, type);
				break;
				
			case OUTBIDED:
				alert = new AlertOutbided(user, bid, type);
				break;
				
			default:
				break;
		}
		
		return alert;
	}
	
}