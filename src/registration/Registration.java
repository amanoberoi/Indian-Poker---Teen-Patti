package registration;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class Registration extends ActionSupport implements ModelDriven<PlayerInfo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayerInfo pi = new PlayerInfo();

	public PlayerInfo getPi() {
		return pi;
	}

	public void setPi(PlayerInfo pi) {
		this.pi = pi;
	}

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/Poker";

	//  Database credentials
	static final String USER = "amano";
	static final String PASS = "amanO1992!";

	@Override
	public String execute() throws SQLException {
		
		String result="ERROR";
		try {
			result = storeData(pi);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String storeData(PlayerInfo player) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException{

		String name=player.getName();
		String emailid=player.getEmailid();
		String phone=player.getPhone();
		String pwd=player.getPwd();
		String loggedin="0";

		String saltofpwd="";
		   
	    final int ITERATIONS = 1000;
	    final int KEY_LENGTH = 192; // bits
	    
	    final Random r = new SecureRandom();
		 
	    byte[] salt = new byte[32];
		
	    r.nextBytes(salt);
	    
	    saltofpwd = String.format("%x", new BigInteger(salt));
	    
	    
	    char[] passwordChars = pwd.toCharArray();

	    PBEKeySpec spec = new PBEKeySpec(
	    	       passwordChars,
	    	       saltofpwd.getBytes(),
	    	       ITERATIONS,
	    	       KEY_LENGTH
	    	   );
	    SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    byte[] hashedPassword = key.generateSecret(spec).getEncoded();
	    
	    pwd =  String.format("%x", new BigInteger(hashedPassword));
		Connection conn = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			if(emailid!=null)
		    {
				prepStmt =  conn.prepareStatement("SELECT COUNT(*) FROM player where Email = ? ");
				prepStmt.setString(1, emailid);
				rs = prepStmt.executeQuery();
		    }
		    rs.next();
		    int rowCount = rs.getInt(1);
		    
		    if(rowCount>0)
		    {
		            return "Unsuccessful";  
		    }
		

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			String sql;
			sql = "Insert into player(Name, Email, Contact, Password, Salt, LoggedIn) values (?,?,?,?,?,?)";
			prepStmt = conn.prepareStatement(sql);
			prepStmt.setString(1,name);
			prepStmt.setString(2,emailid);
			prepStmt.setString(3,phone);
			prepStmt.setString(4,pwd);
			prepStmt.setString(5,saltofpwd);
			prepStmt.setString(6,loggedin);
			
			prepStmt.executeUpdate();
			prepStmt.close();
			if(rs!=null) {
				rs.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			ex.printStackTrace();
			System.exit(1);
		}
		catch(SQLException exxx){
			System.out.println("Error:SQLEXCEPTION");
			exxx.printStackTrace();
			System.exit(4);
		}
		
		return "Thankyou";
	}

		@Override
		public PlayerInfo getModel() {
			// TODO Auto-generated method stub
			return pi;
		}
	}
