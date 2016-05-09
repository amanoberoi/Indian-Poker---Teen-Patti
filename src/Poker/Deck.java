package Poker;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	
	public Deck(){
		cards = new ArrayList<Card>();
		int value1,value2,value3, size;
		Random generator = new Random();
		Card temp;
		
		for(int i=0;i<4;i++){
			for(int j=0;j<13;j++){
				cards.add(new Card(i,j));
			}
		}
		
		for(int i=0;i<100;i++){
			
			value1= generator.nextInt(cards.size()-1);
			value2= generator.nextInt(cards.size()-1);
			value3= generator.nextInt(cards.size()-1);
			
			temp=cards.get(value2);
			cards.set(value2, cards.get(value1));
			cards.set(value1,temp);
			
		/*	// What to add here for shuffling?? Confused
			temp=cards.get(value1);
			cards.set(value1, cards.get(value3));
			cards.set(value3, temp);*/
		}
		/*System.out.println("a");
		for(Card c : cards){
			System.out.println(c.toString());
		}*/
		
	}
	
	public Card drawFromDeck(){
		return cards.remove(0);
	}
	
	public int getTotalCards(){
		return cards.size();
	}
	

}
