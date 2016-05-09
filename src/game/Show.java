package game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import Poker.Card;
import Poker.Hand;
import gameSetup.Setup;

public class Show extends ActionSupport implements ModelDriven {
	
	private static final long serialVersionUID = 1L;
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/Poker";

	//  Database credentials
	static final String USER = "amano";
	static final String PASS = "amanO1992!";
	ServletContext context= ServletActionContext.getServletContext(); 
	String[] displayCards = new String[3];
	
	public String[] getDisplayCards() {
		return displayCards;
	}

	public void setDisplayCards(String[] displayCards) {
		this.displayCards = displayCards;
	}

	@SuppressWarnings("rawtypes")
	Map session = ActionContext.getContext().getSession();
	String email=(String)session.get("email");
	String winner = new String();
	String[] playerCards = new String[6];
	String[] player1Cards = new String[3];
	String[] player2Cards = new String[3];
	
	public String[] getPlayer1Cards() {
		return player1Cards;
	}

	public void setPlayer1Cards(String[] player1Cards) {
		this.player1Cards = player1Cards;
	}

	public String[] getPlayer2Cards() {
		return player2Cards;
	}

	public void setPlayer2Cards(String[] player2Cards) {
		this.player2Cards = player2Cards;
	}
	
	public String[] getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(String[] playerCards) {
		this.playerCards = playerCards;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean showButton() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
		PreparedStatement st = null;
		ResultSet rs = null;
		int count=0;
		st = con.prepareStatement("Select count(*) from cards");
		rs = st.executeQuery();
		if(rs.next()) {
			count = Integer.parseInt(rs.getString(1));
		}
		
		if(count<=2) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public String execute() throws ClassNotFoundException, SQLException{
		
		boolean canShow = showButton();
		
		if (canShow == true) {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
		PreparedStatement st = null;
		ResultSet rs = null;
		
		st = con.prepareStatement("Select card1,card2,card3,object,player from cards;");
		rs = st.executeQuery();
		String[] users = new String[2];
		
		int suit,rank,i=0;
		int p=0;
		Card [] c = new Card[3];
		Hand [] Players = new Hand[2];
		while(rs.next()) {
			
			users[i] = rs.getString(5);
			String object = rs.getString(4);
			String[] objectArray = object.split(" ");
			c = new Card[3];
			for(int j =0;j<3;j++){
			suit = Integer.parseInt(objectArray[j].substring(0,1));
			rank = Integer.parseInt(objectArray[j].substring(1));
			c[j] = new Card(suit,rank);
			playerCards[p] = c[j].toString();
			p++;
			}
			Players[i]= new Hand(c);
			i++;
		}
		
		int y=0;
		for(int z=0;z<6;z++) {
			if(z<3) {
				player1Cards[z] = playerCards[z];
			}
			else {
				player2Cards[y] = playerCards[z];
				y++;
			}
		}
		
		if(Players[0].compareTo(Players[1])==1) {
			st = con.prepareStatement("Select name,email from player where email=?;");
			st.setString(1, users[0]);
			rs = st.executeQuery();
			if(rs.next()) {
				winner = rs.getString(1);
			}
			//System.out.println("Player "+users[0]+"won!!");
		}
		else {
			st = con.prepareStatement("Select name,email from player where email=?;");
			st.setString(1, users[1]);
			rs = st.executeQuery();
			if(rs.next()) {
				winner = rs.getString(1);
			}
			//System.out.println("Player "+users[1]+"won!!");
		}
		
		int gameid = Integer.parseInt((String)context.getAttribute("gameId"));
		st = con.prepareStatement("Select status from game where ID=?");
		st.setInt(1, gameid);
		rs = st.executeQuery();
		
		if(rs.next()) {
			if(rs.getString(1)=="Completed") {
				st = con.prepareStatement("Delete from cards;");
				st.executeUpdate();
			}
		}
		
		st = con.prepareStatement("Update game set Status='Completed' where Status='Active';");
		st.executeUpdate();
		
		return SUCCESS;
		}
		
		else {
			displayCards = Setup.display(email);
			addFieldError("bet", "You can only demand for show when there are 2 people left in the game.");
			return INPUT;
		}
	}
	
}
