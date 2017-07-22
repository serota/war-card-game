
public class PlayingCard {
	private Rank rank;
	private Suit suit;
	
	public PlayingCard(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public String toString() {
		return rank + " of " + suit;
	}
	
	public int compareTo(PlayingCard card) {
		int rDiff = 0;
		int sDiff = 0;
		
		rDiff = rank.compareTo(card.getRank());
		sDiff = suit.compareTo(card.getSuit());
		return rDiff * DeckOfCards.SUITS + sDiff;
		//really just spreads the ranks out 4 times and fills it wit the suits in the proper order
		//kind of like the memory indices of a 2d array
	}
}
