package bid;

import java.sql.Date;
import java.util.List;

public class Bid {

	private Date deadLine;
	private BidState state;
	private float minPrice;
	private float reservedPrice;
	private User seller;
	private Offer lastOffer;
	static private List<Bid> bids;
	
	private void checkAlerts ()
	{
		
	}
	
	static public List<Bid> getBids (User user)
	{
		return Bid.bids;
	}
}
