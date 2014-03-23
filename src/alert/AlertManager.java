package alert;

import java.util.ArrayList;
import java.util.List;

import bid.Bid;
import bid.User;

public class AlertManager {
	
	private static AlertManager alertInstance;
	private static List<Alert> alerts;
	
	private AlertManager () {
	}

	public static AlertManager getInstance () {
		if(alertInstance == null) {
			alertInstance = new AlertManager();
		}
		return alertInstance;
	}
	
	public static List<Alert> getAlerts (User user)
	{
		ArrayList<Alert> alertsToOneUser = new ArrayList<Alert>();
		for(Alert alert : alerts)
		{
			if(user == alert.user) {
				alertsToOneUser.add(alert);
			}
		}
		
		return alertsToOneUser;
	}
	
	public static List<Alert> getAlerts (Bid bid)
	{
		ArrayList<Alert> alertsToOneBid = new ArrayList<Alert>();
		for(Alert alert : alerts)
		{
			if(bid == alert.bid) {
				alertsToOneBid.add(alert);
			}
		}
		
		return alertsToOneBid;
	}
	
}
