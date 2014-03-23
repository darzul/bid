package alert;

import java.util.ArrayList;

import bid.Bid;
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

	public boolean addAlert(Alert newAlert) {
		return alerts.add(newAlert);
	}
	
}
