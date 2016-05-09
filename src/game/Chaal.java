package game;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import gameSetup.Setup;

public class Chaal extends ActionSupport implements ModelDriven {
	
	private static final long serialVersionUID = 1L;
	int bet ;
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

	public int getBet() {
		return bet;
	}
	
	public String[] getDisplayCards() {
		return displayCards;
	}

	public void setDisplayCards(String[] displayCards) {
		this.displayCards = displayCards;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public String execute() throws ClassNotFoundException, SQLException{
		
		
		ServletContext context = ServletActionContext.getServletContext();
		Map session = ActionContext.getContext().getSession();
		String email=(String)session.get("email");
		displayCards = Setup.display(email);
		int turn =  (int) context.getAttribute(email);
		int t =  (int) context.getAttribute("turn");
		//System.out.println(t+"   "+turn);
		
		//System.out.println("bets set value" +(int)context.getAttribute("bet"));
		//System.out.println(bet);
		if(turn==t){
			if((int)context.getAttribute("bet")>bet) {	
				addFieldError("bet", "your amount is less than the previous bet amount of "+(int)context.getAttribute("bet") );
				return INPUT;
			}
			else {
				
				context.setAttribute("bet", bet);
			}
			context.setAttribute("turn", (turn+1)%3);
			minBet = (int) context.getAttribute("bet");
			currentTurn=(int)context.getAttribute("turn");
			yourTurn = t;
			//System.out.println(""+ turn +session.get("email"));
		}
		else{
			minBet = (int) context.getAttribute("bet");
			currentTurn=(int)context.getAttribute("turn");
			yourTurn = t;
			addFieldError("bet", "Wait for your Turn");
			//System.out.println(""+ turn +session.get("email"));
			return INPUT;
			
		}
		
		return SUCCESS;
	}
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
