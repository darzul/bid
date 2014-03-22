package bid;

public enum BidState {
	CREATED, // can't be accessed + viewed by other users
	PUBLISHED, // can be accessed + viewed by other users
	ENDED, // can be viewed (but not accessed) by users who made an offer
	CANCELED; // can't be viewed (nor accessed) by other users
}

// TODO:
// vrifier le sens des diffrents tats par rapport ˆ la visiblit
// CANCELED et ENDED sont-ils des tats de non retour ?
// peut-on passer de CANCELED ˆ ENDED et inversement ?
// l'auteur peut-il voir les CANCELED ?
// -> changer les get/set de Bid en consquence (cf getDeadLine())