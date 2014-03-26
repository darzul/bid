package alert;

import java.util.ArrayList;

import user.User;
import bid.Bid;
import bid.BidState;

public class AlertManager {
	
	private static AlertManager alertInstance;
	private static ArrayList<Alert> alerts = new ArrayList <Alert> ();
	
	private AlertManager () {
	}

	public static AlertManager getInstance () {
		if(alertInstance == null) {
			alertInstance = new AlertManager();
		}
		return alertInstance;
	}
	
	public ArrayList<Alert> getAlerts (User user)
	{
		ArrayList<Alert> alertsToOneUser = new ArrayList<Alert>();
		for(Alert alert : alerts)
		{
			if(user == alert.getUser()) {
				alertsToOneUser.add(alert);
			}
		}
		
		return alertsToOneUser;
	}
	
	public ArrayList<Alert> getAlerts (Bid bid)
	{
		ArrayList<Alert> alertsToOneBid = new ArrayList<Alert>();
		for(Alert alert : alerts)
		{
			if(bid == alert.getBid()) {
				alertsToOneBid.add(alert);
			}
		}
		return alertsToOneBid;
	}

	public boolean addAlert(User user, Bid bid, AlertType type) {

		if (bid == null)
			return false;
		
		if(user == bid.getSeller(user))
		{
			if (bid.getState(user) == BidState.CREATED
				&& type == AlertType.SELLER) {

					Alert newAlert = AlertFactory.structureAlert(user, bid, type);
					for (Alert alert: alerts){
						if (alert.equals(newAlert)){
							return false;
						}
					}
					return alerts.add(newAlert);
				}
		}
		else {
			if(bid.getState(user) == BidState.PUBLISHED)
			{
				Alert newAlert = AlertFactory.structureAlert(user, bid, type);
				for (Alert alert: alerts){
					if (alert.equals(newAlert)){
						return false;
					}
				}
				return alerts.add(newAlert);
			}
		}
		return false;
	}

	public boolean deleteAlert(User user, Bid bid, AlertType type) {
		
		if (type == AlertType.SELLER)
			return false;
		
		for (Alert alert: alerts){
			if (alert.getUser() == user && alert.getBid() == bid && 
					alert.getType() == type){
				
				alerts.remove(alert);
				return true;
			}
		}
		return false;
	}

	public void clearAlerts() {
		alerts.clear();
	}

	public Alert getAlert(User user, Bid bid, AlertType type) {
		for (Alert alert: alerts) {
			if (alert.getUser() == user && alert.getBid() == bid
					&& alert.getType() == type)
				return alert;
		}
		return null;
	}
}
