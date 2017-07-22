
public enum Rank {
	Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King;
	//move Ace to the end to make it high
	
	static Rank lowest() {
		return Rank.values()[0];
	}
}
