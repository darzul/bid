package alert;

public enum AlertType {

	SELLER,
	RESERVEDPRICEREACHED,
	BIDCANCELED,
	OUTBIDED;
	
	// checks if the alertTypeRequested exists
	// returns true if it does, false if not
	public static boolean checkValidity(AlertType alertTypeRequested)
	{
		AlertType[] alertTypes = AlertType.values();
		for (AlertType alertType : alertTypes)
		{
			if (alertType.equals(alertTypeRequested))
				return true;
		}
		return false;
	}
}