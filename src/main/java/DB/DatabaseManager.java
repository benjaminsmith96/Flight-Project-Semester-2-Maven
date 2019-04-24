package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private static DatabaseManager instance = null;

	public static DatabaseManager getInstance() {
		if(instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}

	public void connectDB() throws Exception {
		try
		{
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/abcs?"
							+ "user=root&password=");
			statement = connect.createStatement();
		}
		catch (Exception e) {
			throw e;
		}
	}

	public Connection returnConnection() throws Exception {
	    return connect;
	}

	protected void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
