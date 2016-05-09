package login;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class Login extends ActionSupport implements ModelDriven<Loginpageinfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Loginpageinfo lpi = new Loginpageinfo();	

	public Loginpageinfo getLpi() {
		return lpi;
	}

	public void setLpi(Loginpageinfo lpi) {
		this.lpi = lpi;
	}

	@Override
	public Loginpageinfo getModel() {
		// TODO Auto-generated method stub
		return lpi;
	}

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/Poker";

	//  Database credentials
	static final String USER = "amano";
	static final String PASS = "amanO1992!";
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String execute() throws SQLException, NoSuchAlgorithmException, ClassNotFoundException, InvalidKeySpecException {

		String result="ERROR";
		//System.out.println("global_email in execute="+global_email);
		//System.out.println("emailid in execute="+emailid);
		//System.out.println("pwd in execute="+pwd);
		String emailid=lpi.getEmailid();
		String pwd=lpi.getPwd();
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement("SELECT Name, Email, Password, Salt, LoggedIn FROM Player where Email = ? ");
			st.setString(1, emailid);	
			rs = st.executeQuery();
			// get the number of rows from the result set
			if(!rs.next()){
				return "login2";
			}
			else {
				int rowCount = 1;//rs.getInt(1);

				if(rowCount>0)
				{
					final int ITERATIONS = 1000;
					final int KEY_LENGTH = 192; // bits

					//System.out.println(pwd);
					char[] passwordChars = pwd.toCharArray();
					String saltBytes = rs.getString(4);

					PBEKeySpec spec = new PBEKeySpec(
							passwordChars,
							saltBytes.getBytes(),
							ITERATIONS,
							KEY_LENGTH
							);
					SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
					byte[] hashedPassword = key.generateSecret(spec).getEncoded();
					String password = String.format("%x", new BigInteger(hashedPassword));
					
					//System.out.println("Password="+password);
					//System.out.println("rs.getString(3)="+rs.getString(3));
					if(password.equals(rs.getString(3)))
					{	
						
						Map session = ActionContext.getContext().getSession();
						if(!session.containsKey("email")) {
							session.put("email",emailid);
							session.put("context", new Date());
						}
						
						//System.out.println("reached ="+ rs.getInt(5));
						if(rs.getString(5).equals("0"))
						{	
							st = con.prepareStatement("UPDATE player SET LoggedIn = 1 where Email = ?");
							st.setString(1, emailid);
							st.executeUpdate();
							st.close();
							result = "Success";
						}
						else {
							result = "Success";
						}
					}
					else {
						result = "login2";
					}
				}
			}
			if(rs!=null) {
				rs.close();
			}
			if(con != null){
				con.close();
			}
		}
		catch(SQLException exxx){
			System.out.println("Error:SQLEXCEPTION");
			exxx.printStackTrace();
			System.exit(4);
		}
		
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public String logout() throws ClassNotFoundException, SQLException {
		
		String result="ERROR";
		Map session = ActionContext.getContext().getSession();
		//System.out.println("global_email in logout="+global_email);
		String emailid=(String)session.get("email");
		//System.out.println("emailid in logout="+emailid);

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = con.prepareStatement("SELECT Name, Email, Password, Salt, LoggedIn FROM Player where Email = ? ");
			st.setString(1, emailid);	
			rs = st.executeQuery();
			if(!rs.next()){
				return "ERROR";
			} 
			if(rs.getString(5).equals("1")) {
				
				session.remove("email");
				session.remove("context");
				st = con.prepareStatement("UPDATE player SET LoggedIn = 0 where Email = ?");
				st.setString(1, emailid);
				st.executeUpdate();
				st.close();
				result = "Success";
			}
			
		}
		catch(SQLException exxx){
			System.out.println("Error:SQLEXCEPTION");
			exxx.printStackTrace();
			System.exit(4);
		} 
		
		return result;
	}
}
