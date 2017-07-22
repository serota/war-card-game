
public enum Suit {
	Spades, Hearts, Diamonds, Clubs;
	//most valuable suit on the right
	
	static Suit lowest() {
		return Suit.values()[0];
	}
}
