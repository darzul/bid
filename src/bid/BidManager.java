package bid;

import java.util.ArrayList;

public class BidManager {
	
	private static BidManager bidInstance;
	private ArrayList<Bid> bids = new ArrayList<Bid>();
	
	private BidManager () {
	}

	public static BidManager getInstance () {
		if(bidInstance == null) {
			bidInstance = new BidManager();
		}
		return bidInstance;
	}
	
	// ----------
	// bids access
	// ----------
	// search for all the bids owned by a user in the bid list
	public ArrayList<Bid> getOwnedBids (User user)
	{	
		ArrayList<Bid> ownedBids = new ArrayList<Bid>();
		for(Bid bid : bids) {
		    if(user == bid.getSeller())
		    	ownedBids.add(bid);
		}
		return ownedBids;
	}

	// search for all the published bids
	public ArrayList<Bid> getPublishedBids ()
	{	
		ArrayList<Bid> publishedBids = new ArrayList<Bid>();
		for(Bid bid : bids) {
		    if(BidState.PUBLISHED == bid.getState())
		    	publishedBids.add(bid);
		}
		return publishedBids;
	}
}
