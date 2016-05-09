package gameSetup;

import com.opensymphony.xwork2.ActionContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import Poker.Card;
import Poker.Deck;
import Poker.Hand;

public class Setup extends ActionSupport implements ModelDriven {
	
	// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost:3306/Poker";

		//  Database credentials
		static final String USER = "amano";
		static final String PASS = "amanO1992!";
		StringBuilder moves = new StringBuilder();
		String[] displayCards = new String[3];
		
		int currentTurn ;
		int yourTurn;
		int minBet;
		
		public int getCurrentTurn() {
			return currentTurn;
		}

		public void setCurrentTurn(int currentTurn) {
			this.currentTurn = currentTurn;
		}

		public int getYourTurn() {
			return yourTurn;
		}

		public void setYourTurn(int yourTurn) {
			this.yourTurn = yourTurn;
		}

		public int getMinBet() {
			return minBet;
		}

		public void setMinBet(int minBet) {
			this.minBet = minBet;
		}

		

		public String[] getDisplayCards() {
			return displayCards;
		}


		public void setDisplayCards(String[] displayCards) {
			this.displayCards = displayCards;
		}


		public StringBuilder getMoves() {
			return moves;
		}


		public void setMoves(StringBuilder moves) {
			this.moves = moves;
		}


	public String execute() throws ClassNotFoundException, SQLException{
		
		Map session = ActionContext.getContext().getSession();
		String email=(String)session.get("email");
//		if(!set.contains(email)){
//			set.add(email);
//		}
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
		PreparedStatement st = null;
		ResultSet rs = null;
		ServletContext context= ServletActionContext.getServletContext();
		try {
			st = con.prepareStatement("SELECT ID, Status, Player1, Player2, Player3, Moves FROM Game where Status='Waiting'");
			rs = st.executeQuery();
			if(!rs.next()) {
				st = con.prepareStatement("SELECT ID, Status, Player1, Player2, Player3, Moves FROM Game where Status='Active'");
				ResultSet result  = st.executeQuery();
				if(!result.next()){
					if(context.getAttribute("gameId")!=null){
						st = con.prepareStatement("select status FROM Game where id = ?");
						st.setString(1,(String) context.getAttribute("gameId"));
						rs = st.executeQuery();
						if(rs.next()&&rs.getString(1).equals("Completed")){
							return "show";
						}
					}else{
					st = con.prepareStatement("Insert into game(Player1,Status) values(?,'Waiting')");
					st.setString(1, email);
					st.executeUpdate();
					return "wait";
					}
				}else{
					
					minBet = (int) context.getAttribute("bet");
					currentTurn=(int)context.getAttribute("turn");
					yourTurn =(int) context.getAttribute(email);
					displayCards = display(email);
					return SUCCESS;
				}
			}
			else {
//				System.out.println("rs.getString(4)="+rs.getString(4));
//				System.out.println("email="+email);
//				System.out.println("rs.getString(3)="+rs.getString(3));
				
				if(rs.getString(4)==null && !email.equals(rs.getString(3))) {
					st = con.prepareStatement("update game set Player2=? Where Status='Waiting'");
					st.setString(1, email);
					st.executeUpdate();
					return "wait";
				}
				else {
					if(!email.equals(rs.getString(3)) && !email.equals(rs.getString(4))) {
						st = con.prepareStatement("update game set Player3=? Where Status='Waiting'");
						st.setString(1, email);
						st.executeUpdate();
						
						Deck deck = new Deck();
						Hand player1Cards = new Hand(deck);
						Hand player2Cards = new Hand(deck);
						Hand player3Cards = new Hand(deck);
						Card [] c = player1Cards.getCards();
						Card [] c2 = player2Cards.getCards();
						Card [] c3 = player3Cards.getCards();
						String temp =c[0].getSuit()+""+ c[0].getRank()+" "+c[1].getSuit()+""+ c[1].getRank()+" "+c[2].getSuit()+""+ c[2].getRank(); 
						String temp2 =c2[0].getSuit()+""+ c2[0].getRank()+" "+c2[1].getSuit()+""+ c2[1].getRank()+" "+c2[2].getSuit()+""+ c2[2].getRank();	
						String temp3 =c3[0].getSuit()+""+ c3[0].getRank()+" "+c3[1].getSuit()+""+ c3[1].getRank()+" "+c3[2].getSuit()+""+ c3[2].getRank();
						
						st=con.prepareStatement("Insert into cards values(?,?,?,?,?)");
						st.setString(1, rs.getString(3));
						st.setString(2, c[0].toString());
						st.setString(3, c[1].toString());
						st.setString(4, c[2].toString());
						st.setString(5, temp);
						st.executeUpdate();
						st=con.prepareStatement("Insert into cards values(?,?,?,?,?)");
						st.setString(1, rs.getString(4));
						st.setString(2, c2[0].toString());
						st.setString(3, c2[1].toString());
						st.setString(4, c2[2].toString());
						st.setString(5, temp2);
						st.executeUpdate();
						st=con.prepareStatement("Insert into cards values(?,?,?,?,?)");
						st.setString(1, email);
						st.setString(2, c3[0].toString());
						st.setString(3, c3[1].toString());
						st.setString(4, c3[2].toString());
						st.setString(5, temp3);
						st.executeUpdate();
						
						st = con.prepareStatement("update game set Status='Active' where Player3=? AND Status='Waiting'");
						st.setString(1, email);
						st.executeUpdate();
						
						st = con.prepareStatement("select Player1, Player2, Player3,ID Moves FROM Game where Status= 'Active'");
						rs = st.executeQuery();
						 context = ServletActionContext.getServletContext();
						if(rs.next()){
							context.setAttribute( rs.getString(1),0);
							context.setAttribute( rs.getString(2),1);
							context.setAttribute( rs.getString(3),2);
							context.setAttribute("gameId", rs.getString(4));
						}
						
						context.setAttribute("turn", 0);
						context.setAttribute("bet", 20);
						minBet = (int) context.getAttribute("bet");
						currentTurn=(int)context.getAttribute("turn");
						yourTurn =(int) context.getAttribute(email);
						displayCards = display(email);
						return SUCCESS;
					}
				}
				return "wait";
			}
		}
		catch(SQLException exxx){
			System.out.println("Error:SQLEXCEPTION");
			exxx.printStackTrace();
			System.exit(4);
		}
		
		//System.out.println("test");
		return "ERROR";
	}
	
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static String[] display(String id) throws ClassNotFoundException, SQLException{
		
		String[] cards = new String[3];
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
		PreparedStatement st = null;
		ResultSet rs = null;
		
		st = con.prepareStatement("SELECT card1,card2,card3 from cards where player=?");
		st.setString(1, id);
		rs = st.executeQuery();
		if(rs.next()) {
			cards[0] = rs.getString(1);
			cards[1] = rs.getString(2);
			cards[2] = rs.getString(3);
		}
		
		
		return cards;
	}

}
