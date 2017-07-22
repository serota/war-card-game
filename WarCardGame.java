import java.util.Random;

public class WarCardGame {
	public static final int MAX_ROUNDS = 15;
	public static final int N_P = 4;
	private Player[] players;
	private LLQueue trick;					//also known as the table
	private DeckOfCards deck;
	private int firstPlayer;
	
	public WarCardGame() {
		players = new Player[N_P];
		trick = new LLQueue();
		deck = new DeckOfCards();
		firstPlayer = 0;
		
		for(int i=0; i<N_P; i++)			//populate player array
			players[i] = new Player("Player " + (i + 1));
	}
	
	public void start() {
		Boolean noOneHasWon = true;
		int round = 0;
		
		deck.randomize();
		decideWhoGoesFirst();
		dealCards();
		
		while(noOneHasWon && round < MAX_ROUNDS) {
			System.out.println("\n--Round " + ++round + "--");
			
			playTrick();
			
			for(int i=0; i<N_P; i++)		//for each player
				if(players[i].getHandSize() == DeckOfCards.RANKS * DeckOfCards.SUITS)
					noOneHasWon = false;	//if anyone has all the cards
		}

		handleWin();
	}
	
	private void decideWhoGoesFirst() {
		Random rand = new Random();
		
		firstPlayer = rand.nextInt(N_P);
		
		System.out.println(players[firstPlayer].toString() + " plays first.");
	}

	private void dealCards() {
		for(int i=firstPlayer; !deck.isEmpty(); i++)		//until the deck is empty, deal one card to each player in turn
			players[i % N_P].pickUp(deck.dealFromTop());
	}
	
	private void playTrick() {
		PlayingCard temp = new PlayingCard(Rank.lowest(), Suit.lowest());
		PlayingCard best = new PlayingCard(Rank.lowest(), Suit.lowest());
		int winner = firstPlayer;
		
		for(int i=0; i<N_P; i++) {							//for each player
			int current = (firstPlayer + i) % N_P;			//starting with the last person to win a trick
			temp = players[current].playFromTop();
			trick.offer(temp);								//put the top card of their hand onto the table
			
			if(temp == null)
				System.out.println(players[current].toString() + " has no cards to play.");
			else
				System.out.println(players[current].toString() + " plays the " + temp.toString() + ".");
			
			if(temp != null && temp.compareTo(best) > 0) {
				best = temp;								//if it's the best card played so far
				winner = current;							//current player is winning
			}
		}
		
		System.out.println("\nOn the table:");
		
		for(int i=0; i<N_P; i++) {
			if(trick.peek() == null)
				trick.poll();								//flush the queue of null objects when someone had no cards
			else {											//otherwise the winner picks up the trick one at a time
				System.out.print(" > " + trick.peek().toString());
				players[winner].pickUp((PlayingCard) trick.poll());
			}
		}
		
		System.out.println("\n\n" + players[winner].toString() + " picks up the trick.");
		firstPlayer = winner;								//trick winner plays first next round
		
		for(int i=0; i<N_P; i++) {
			System.out.println("\n" + players[i].toString() + "'s hand:");
			players[i].displayHand();
		}
	}
	
	private void handleWin() {
		LLQueue winners = new LLQueue();
		int winner = 0;
		int winningSize = 0;
		
		for(int i=0; i<N_P; i++)							//for each player
			if(players[i].getHandSize() > players[winner].getHandSize())
				winner = i;									//if they have the biggest hand or are tied for biggest
		
		winningSize = players[winner].getHandSize();		//remember the size of their hand
		
		for(int i=0; i<N_P; i++)							//for each player
			if(players[i].getHandSize() == winningSize)
				winners.offer(players[i]);					//write their names down if they had the highest number of cards
		
		if(winners.getSize() > 1) {
			System.out.println("\nIt's a tie!  The winners are:");
			
			while(!winners.isEmpty())
				System.out.println(winners.poll().toString());	//read off the written name(s)
		} else
			System.out.println("\n" + winners.poll().toString() + " won!");
	}
}
