package bid;

public class Offer {

	public User user;
	public float price;
	public Bid bid;
	
	// TODO: pas besoin de private ?
	
	// constructor
	public Offer(User user, float price, Bid bid) {
		this.user = user;
		this.price = price;
		this.bid = bid;
		applyOffer();
	}
	
	// fake Offer with no user with can't be apply by the caller
	// only used to initiate a bid
	public Offer(float price, Bid bid){
		this.user = null;
		this.price = price;
		this.bid = bid;
	}

	private boolean applyOffer() {
		if(this.bid.setLastOffer(this))
			return true;
		else
			return false;
	}
	
	
}
