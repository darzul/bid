package bid;

public class Offer {

	private User user;
	private float price;
	private Bid bid;
	private boolean applied = false;
		
	// constructor
	public Offer(User user, float price, Bid bid) {
		this.user = user;
		this.price = price;
		this.bid = bid;
		
		if (user != null && price > 0 && bid != null)
		{
			if(this.bid.setBestOffer(this) == false)
			{
				throw new IllegalArgumentException("Price too low or bid not published");
			}
		}
		else {
			throw new NullPointerException("User or Bid is NULL");
		}
			
	}

	public boolean isApplied() {
		return applied;
	}

	public float getPrice() {
		return price;
	}

	public User getUser() {
		return user;
	}
}
