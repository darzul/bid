package alert;

import java.util.ArrayList;

import bid.Bid;
import bid.BidState;
import bid.User;

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

		System.err.println(type+" "+bid.getState());
		if(bid != null && bid.getState() == BidState.PUBLISHED)
		{
			if (user == bid.getSeller())
				return false;
			
			Alert newAlert = AlertFactory.structureAlert(user, bid, type);
			for (Alert alert: alerts){
				if (alert.equals(newAlert)){
					return false;
				}
			}
			System.err.println("OK");
			alerts.add(newAlert);
			
			return true;
		}
		return false;
	}

	public boolean deleteAlert(User user, Bid bid, AlertType type) {
		for (Alert alert: alerts){
			if (alert.getUser() == user && alert.getBid() == bid && 
					alert.getType() == type){
				return true;
			}
		}
		return false;
	}
}
