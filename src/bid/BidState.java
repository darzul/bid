package bid;

public enum BidState {
	CREATED, // can't be accessed by other users yet
	PUBLISHED, // can be accessed by everybody
	CANCELED,
	ENDED;
}
