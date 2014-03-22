package bid;

public class Offer {

	public User user;
	public float price;
	public Bid bid;
	public boolean applied = false;
	
	// TODO: pas besoin de private ?
	
	// constructor
	public Offer(User user, float price, Bid bid) {
		this.user = user;
		this.price = price;
		this.bid = bid;
		applyOffer();
	}

	private boolean applyOffer() {
		if(this.bid.setBestOffer(this))
		{
			this.applied = true;
			return true;
		}
		return false;
	}
	
	
}
