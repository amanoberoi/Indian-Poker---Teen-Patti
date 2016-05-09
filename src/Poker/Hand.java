package Poker;



public class Hand {
	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	private int[] val;
	
	private Card[] cards;
	public Card[] getCards() {
		return cards;
	}

public Hand(Card[] cards){
	val = new int[4];
	this.cards = cards;

	// assign value to val goes here
	int[] ranks = new int[14];
	int[] orderedRanks = new int[3];
	int groupRank=0, topStraightValue=0, index=0,sameCards = 1;
	boolean flush = true, straight= false;
	
	for(int i=0;i<=12;i++){
		ranks[i]=0;
	}
	
	// Checks which card the rank is of and increments it
	for(int i=0;i<=2;i++){
		ranks[cards[i].getRank()]++;
	}
	
	// This is used to check for trail (i.e if there is any pair)
	for(int i=12;i>=0;i--){
		if(ranks[i]> sameCards){
			sameCards = ranks[i];
			groupRank=i;
		}
	}
	
	// This is used to check if all cards are of the same suit
	for(int i=0;i<2;i++){
		if(cards[i].getSuit() != cards[i+1].getSuit()){
			flush = false;
		}
	}
	
	// This is used to check if all cards are in sequence
	for(int i=0;i<=10;i++){
		if(ranks[i]==1 && ranks[i+1]==1 && ranks[i+2]==1){
			straight = true;
			topStraightValue = i+2;
			break;
		}
	}
	
	// This is used for the case when the case AKQ
	if(ranks[0]==1 && ranks[12]==1 && ranks[11]==1){
		straight = true;
		topStraightValue= 13;
	}
	
	if(ranks[0]==1){
		orderedRanks[index]=13;
		index++;
	}
	
	for(int i=12;i>=1;i--){
		if(ranks[i]==1){
			orderedRanks[index]=i;
			index++;
		}
	}
	
	// Now the hand evaluation starts
	
	if(sameCards==1){// This means there is no pair and it is lowest ranked hand
		val[0]=1;
		val[1]=orderedRanks[0];
		val[2]=orderedRanks[1];
		val[3]=orderedRanks[2];
		
	}
	
	if(sameCards==2){// This means there are two cards with same rank
		val[0]=2;
		val[1]=groupRank;
		val[2]=orderedRanks[0]; //Not sure about this logic
	}
	
	if(flush && (!straight)){ // This means there are three cards of same suit but not in sequence
		val[0]=3;
		val[1]=orderedRanks[0];
		val[2]=orderedRanks[1];
		val[3]=orderedRanks[2];
	
	}
	
	if(straight && (!flush)){ // This means there are three consecutive cards but not in same suit
		val[0]=4;
		val[1]=orderedRanks[0];
		val[2]=orderedRanks[1];
		val[3]=orderedRanks[2];
	}
	
	if(straight && flush){ // This mean three consecutive cards of same suit
		val[0]=5;
		val[1]=orderedRanks[0];
		val[2]=orderedRanks[1];
		val[3]=orderedRanks[2];
	}
	
	if(sameCards==3){ // This mean all three cards are of same ranks
		val[0]=6;
		val[1]=groupRank;
	}
}



	public Hand(Deck d){
		
		val = new int[4];
		cards = new Card[3];
		
		for(int i=0;i<3;i++){
			cards[i]=d.drawFromDeck();
		}
		
		
		// assign value to val goes here
		int[] ranks = new int[14];
		int[] orderedRanks = new int[3];
		int groupRank=0, topStraightValue=0, index=0,sameCards = 1;
		boolean flush = true, straight= false;
		
		for(int i=0;i<=12;i++){
			ranks[i]=0;
		}
		
		// Checks which card the rank is of and increments it
		for(int i=0;i<=2;i++){
			ranks[cards[i].getRank()]++;
		}
		
		// This is used to check for trail (i.e if there is any pair)
		for(int i=12;i>=0;i--){
			if(ranks[i]> sameCards){
				sameCards = ranks[i];
				groupRank=i;
			}
		}
		
		// This is used to check if all cards are of the same suit
		for(int i=0;i<2;i++){
			if(cards[i].getSuit() != cards[i+1].getSuit()){
				flush = false;
			}
		}
		
		// This is used to check if all cards are in sequence
		for(int i=0;i<=10;i++){
			if(ranks[i]==1 && ranks[i+1]==1 && ranks[i+2]==1){
				straight = true;
				topStraightValue = i+2;
				break;
			}
		}
		
		// This is used for the case when the case AKQ
		if(ranks[0]==1 && ranks[12]==1 && ranks[11]==1){
			straight = true;
			topStraightValue= 13;
		}
		
		if(ranks[0]==1){
			orderedRanks[index]=13;
			index++;
		}
		
		for(int i=12;i>=1;i--){
			if(ranks[i]==1){
				orderedRanks[index]=i;
				index++;
			}
		}
		
		// Now the hand evaluation starts
		
		if(sameCards==1){// This means there is no pair and it is lowest ranked hand
			val[0]=1;
			val[1]=orderedRanks[0];
			val[2]=orderedRanks[1];
			val[3]=orderedRanks[2];
			
		}
		
		if(sameCards==2){// This means there are two cards with same rank
			val[0]=2;
			val[1]=groupRank;
			val[2]=orderedRanks[0]; //Not sure about this logic
		}
		
		if(flush && (!straight)){ // This means there are three cards of same suit but not in sequence
			val[0]=3;
			val[1]=orderedRanks[0];
			val[2]=orderedRanks[1];
			val[3]=orderedRanks[2];
		
		}
		
		if(straight && (!flush)){ // This means there are three consecutive cards but not in same suit
			val[0]=4;
			val[1]=orderedRanks[0];
			val[2]=orderedRanks[1];
			val[3]=orderedRanks[2];
		}
		
		if(straight && flush){ // This mean three consecutive cards of same suit
			val[0]=5;
			val[1]=orderedRanks[0];
			val[2]=orderedRanks[1];
			val[3]=orderedRanks[2];
		}
		
		if(sameCards==3){ // This mean all three cards are of same ranks
			val[0]=6;
			val[1]=groupRank;
		}
	}
	
	

	public void displayAll(){
		for(int i=0;i<3;i++){
			System.out.println(cards[i]);
		}
	}
	
	public int compareTo(Hand that){
		for(int i=0;i<4;i++){
			if(this.val[i]>that.val[i]){
				return 1;
			}
			
			else if(this.val[i]<that.val[i]){
				return -1;
			}
		}
		
		return 0;
	}
	
	public void display(){
		String s;
		switch(val[0]){
		
		case 1: s= "high card";
				break;
				
		case 2: s="Pair of "+Card.rankToString(val[1])+"\'s";
				break;
				
		case 3: s="Three Cards of same suit";
				break;
				
		case 4: s="Three Consecutive Cards but not of same suit";
				break;
				
		case 5: s="Three consecutive cards of same suit";
				break;
				
		case 6: s="All three cards of same rank";
				break;
				
		default: s="Error";
				
		
		}
		
		s="      "+s;
		System.out.println(s);
	}
	
		
	}

