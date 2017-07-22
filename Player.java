
public class Player {
	private LLQueue hand;
	private String name;
	
	public Player(String name) {
		hand = new LLQueue();
		this.name = name;
	}
	
	public void pickUp(PlayingCard card) {
		hand.offer(card);
	}
	
	public PlayingCard playFromTop() {
		if(hand.isEmpty())
			return null;
		
		return (PlayingCard) hand.poll();
	}
	
	public int getHandSize() {
		return hand.getSize();
	}
	
	public Boolean emptyHand() {
		return hand.isEmpty();
	}
	
	public String toString() {
		return name;
	}
	
	public void displayHand() {
		hand.display();
	}
}
