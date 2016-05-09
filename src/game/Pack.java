package game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Pack extends ActionSupport implements ModelDriven {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			// JDBC driver name and database URL
			static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
			static final String DB_URL = "jdbc:mysql://localhost:3306/Poker";

			//  Database credentials
			static final String USER = "amano";
			static final String PASS = "amanO1992!";
			
			@SuppressWarnings("rawtypes")
			Map session = ActionContext.getContext().getSession();
			String email=(String)session.get("email");
			
			public String execute() throws ClassNotFoundException, SQLException{
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
				PreparedStatement st = null;
				
				st = con.prepareStatement("Delete from cards where player=?");
				st.setString(1, email);
				st.executeUpdate();
				return SUCCESS;
				
			}

			@Override
			public Object getModel() {
				// TODO Auto-generated method stub
				return null;
			}
}
