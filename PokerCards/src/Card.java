public class Card {
	private String suit;
	private int rank;

	public Card(String suit, int rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "[" + rank + "," + suit + "]";
	}

	@Override
	public boolean equals(Object c2) {
		if (!(c2 instanceof Card)) {
			throw new RuntimeException("Illegal argument to Card.equals()");
		}
		Card card2 = (Card) c2;
		return ((this.getSuit().equals(card2.getSuit())) && (this.getRank() == card2.getRank()));
	}

	public String getSuit () {
		return suit;
	}

	public int getRank() {
		return rank;
	}

	public boolean sameSuitAs (Card  card2) {
		return (this.getSuit().equals(card2.getSuit()));
	}

	public boolean sameRankAs (Card card2) {
		return (this.getRank() == card2.getRank());
	}

	public boolean isAnA () {
		return (this.getRank() == 14);
	}

	public boolean isPair(Card c) {
		return (this.sameRankAs(c));
	}

	public boolean isTrio(Card c1, Card c2) {
		return (this.isPair(c1) && this.isPair(c2));
	}

	public boolean isFourTuple(Card c1, Card c2, Card c3) {
		return (this.isTrio(c1, c2) && this.isPair(c3));
	}

	static int countCardsBySuit (Card[] deck, Card targetCard) {
		int sameSuitCardsCount = 0;
		for (int i=0; i < deck.length; i++) {
			if (targetCard.sameSuitAs(deck[i])) {
				sameSuitCardsCount++;
			}
		}
		return sameSuitCardsCount;
	}

	static boolean cardExists (Card[] deck, Card targetCard) {
		for (int i=0; i < deck.length; i++) {
			if (targetCard.sameRankAs(deck[i]) && targetCard.sameSuitAs(deck[i])) {return true;}
		}
		return false;
	}

	static boolean consecutivePairExists (Card[] deck) {
		for (int i=0; i < deck.length -1; i++) {
			if (deck[i].sameRankAs(deck[i+1])) {return true;}
		}		
		return false;
	}

	static boolean pairExists (Card[] deck) {
		for (int i=0; i < deck.length-1; i++) {
			for(int j=i+1; j < deck.length; j++) {
				if (deck[i].sameRankAs(deck[j])) {return true;}
			}	
		}
		return false;
	}

	static boolean isConsecutiveStraightFlush (Card[] deck) {
		if (deck.length >= 5) {
			for (int i=0; i < deck.length; i++) {
				Card[] testArray = new Card[4];
				int counter = 0;
				for (int j=1; j < 5; j++) {
					int newRank = deck[i].getRank()+j;
					if (newRank > 14)
						newRank = newRank%14 + 1;
					testArray[j-1] = new Card(deck[i].getSuit(), newRank);
					if (cardExists(deck, testArray[j-1]))
						counter++;
				}
				if (counter == 4) {return true;}
			}
		}
		return false;
	}

}