package bid;

import alert.AlertType;

public enum BidState {
	CREATED,	// can be viewed by owner
	PUBLISHED,	// can be viewed by owner and every other users
	ENDED,		// can be viewed by owner and every other users & NO RETURN FROM THIS STATE
	CANCELED;	// can be viewed by owner and users who made an offer & NO RETURN FROM THIS STATE
	
	// checks if the bidStateRequested exists
	// returns true if it does, false if not
	public static boolean checkValidity(BidState bidStateRequested)
	{
		BidState[] bidStates = BidState.values();
		for (BidState bidState : bidStates)
		{
			if (bidState.equals(bidStateRequested))
				return true;
		}
		return false;
	}
}