package Poker;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Deck deck = new Deck();
		Card C;
		int i=0;
		
		System.out.println(deck.getTotalCards());
		
		/*while(i!=6){
			C = deck.drawFromDeck();
			System.out.println(C.toString());
			i++;
			
		}*/
		
		
			
			Hand hand = new Hand(deck);
			Hand hand2 = new Hand(deck);
			hand.display();
			hand.displayAll();
			hand2.display();
			hand2.displayAll();
			

			
			if(hand.compareTo(hand2)==1){
				System.out.println("First player won");
				
			}
			else if(hand.compareTo(hand2)==-1){
				System.out.println("Player 2 won");
			}
			else{
				System.out.println("Draw");
				
			}
			
		}
	}
