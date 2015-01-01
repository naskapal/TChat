import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Connector {

	String password = "", userName = "tChat", dbName = "tchat", query = "";
	public Connector()
	{}
	
	public void setQuery(String query)
	{
		this.query = query;
	}
	
	private Connection login() throws SQLException
	{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","tchat","");
	}
	public boolean userLogin(String userName, String password)
	{
		String tableName = "userlogin";
		String query = "SELECT password FROM "+dbName+"."+tableName+" WHERE username = '"+userName+"'";
		try
		{
			Connection conn = login();
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
			{
				if (rs.getString(1).equals(password))
					return true;
			}
			
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		return false;
	}
}
