import java.util.Random;

public class DeckOfCards {
	public static final int SUITS = Suit.values().length;
	public static final int RANKS = Rank.values().length;
	private LLQueue cards;
	
	public DeckOfCards() {
		cards = new LLQueue();
		
		for(Suit s: Suit.values())
			for(Rank r: Rank.values())
				cards.offer(new PlayingCard(r, s));
	}
	
	public void riffleShuffle() {
		Random rand = new Random();
		LLQueue left = new LLQueue();
		LLQueue right = new LLQueue();
		
		//split the deck in half
		for(int i=0; i<count(); i++)
			left.offer(cards.poll());
		while(!cards.isEmpty())
			right.offer(cards.poll());
		
		//drop one card at a time
		while(!(left.isEmpty() && right.isEmpty()))
			//obviously if one half is empty, the other half is the only choice
			if(left.isEmpty())
				cards.offer(right.poll());
			else if(right.isEmpty())
				cards.offer(left.poll());
			else //otherwise randomly select the half the drops the card
				if(rand.nextBoolean())
					cards.offer(left.poll());
				else
					cards.offer(right.poll());
	}
	
	public void randomize() {
		if(isEmpty())
			return;
		
		int shuffles = (int) (3*Math.log(count())/Math.log(2)/2);
		//super scientific algorithm for how many times to shuffle for randomization
		// 3/2 * log2(n) is 8.55 for 52 cards
		//http://statweb.stanford.edu/~cgates/PERSI/papers/bayer92.pdf
		
		for(int i=0; i<shuffles; i++)
			riffleShuffle();
	}
	
	public PlayingCard dealFromTop() {
		return (PlayingCard) cards.poll();
	}
	
	public void placeOnBottom(PlayingCard card) {
		cards.offer(card);
	}
	
	public int count() {
		return cards.getSize();
	}
	
	public Boolean isEmpty() {
		return count() == 0;
	}
}
