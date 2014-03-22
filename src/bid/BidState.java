package bid;

public enum BidState {
	CREATED,	// can be viewed by owner
	PUBLISHED,	// can be viewed by owner and every other users
	ENDED,		// can be viewed by owner and every other users
	CANCELED;	// can be viewed by owner and users who made an offer
}

// TODO:
// vrifier le sens des diffrents tats par rapport ˆ la visiblit
// CANCELED et ENDED sont-ils des tats de non retour ?
// peut-on passer de CANCELED ˆ ENDED et inversement ?
// l'auteur peut-il voir les CANCELED ?
// -> changer les get/set de Bid en consquence (cf getDeadLine())