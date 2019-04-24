package DB;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDBController {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public LoginDBController() {

	}
        
	public boolean loginCheck(String username, String password) throws SQLException, Exception{

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();

		boolean login = false;
		String email = "";
		String pass = "";

		preparedStatement = connect
				.prepareStatement("SELECT email, password FROM abcs.user where email = ? AND password = ?;");
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			email = resultSet.getString("email");
			pass = resultSet.getString("password");

			if(email.equals(username) && pass.equals(password)){
				login = true;
			}   
		}

		db.close();

		return login;
	}
	
}
