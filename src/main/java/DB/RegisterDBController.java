package DB;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterDBController {
    
        private Connection connect = null;
        private PreparedStatement preparedStatement = null;
        private ResultSet resultSet = null;
    
        public RegisterDBController(){
            
        }
        
        public boolean registerCheck(String username) throws SQLException, Exception{

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();

		
		String email = "";
		

		preparedStatement = connect
				.prepareStatement("SELECT * FROM abcs.user where email = ?;");
		preparedStatement.setString(1, username);
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			email = resultSet.getString("email");	
		}
		
                if(!(email.equals(username))){
				return true;
                }
                
                
		db.close();

		return false;
	}
        
        public void insertNewUser(String firstName, String lastName, String email, String password, String phoneNumber, String dateOfBirth, String gender) throws Exception{
               
                DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                
                int userID = 0;
                int genderDB = 0;
                
                //Insert email password and role into user table
		preparedStatement = connect
				.prepareStatement("insert into abcs.user values (default, ?, ?, ?)");

		
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		preparedStatement.setString(3, "1");
		preparedStatement.executeUpdate();
                
                
                //Select UserID from user Table
                preparedStatement = connect
				.prepareStatement("SELECT user_id FROM abcs.user where email = ?;");
                
                preparedStatement.setString(1, email);
                resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()){
			userID = resultSet.getInt("user_id");
                }
		
                
                //Insert userID and 0 points to membership Table
                preparedStatement = connect
				.prepareStatement("insert into abcs.membership values (?, ?)");

		
		preparedStatement.setInt(1, userID);
		preparedStatement.setInt(2, 0);
		preparedStatement.executeUpdate();
                
                
                //Assign Male or Female to 0 OR 1
                if(gender.equals("Male")){
                    genderDB = 0;
                }
                else{
                    genderDB = 1;
                }
                
                
                //Insert user details to user_details Table
                preparedStatement = connect
				.prepareStatement("insert into abcs.user_details values (?, ?, ?, ?, ?, ?)");

		preparedStatement.setInt(1, userID);
		preparedStatement.setString(2, firstName);
		preparedStatement.setString(3, lastName);
		preparedStatement.setString(4, dateOfBirth);
                preparedStatement.setInt(5, genderDB);
                preparedStatement.setString(6, phoneNumber);
		preparedStatement.executeUpdate();
                
                
		db.close();
         
        }
}
