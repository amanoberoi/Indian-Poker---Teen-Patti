package Poker;

public class Card {
	
	private int rank,suit;

	private static String[] suits = {"clubs","diamonds","hearts","spades"};
	private static String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
	
	public Card(int suit, int rank){
		this.suit = suit;
		this.rank = rank;
	}
	
	public static String rankToString(int rankValue){
		return ranks[rankValue];
	}
	
	@Override
	public String toString(){
		return ranks[rank]+ "of"+ suits[suit];
	}
	
	public int getRank(){
		return rank;
	}
	
	public int getSuit(){
		return suit;
	}
	
	
}


