package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Users.Customer;
import Users.UFactory;
import Users.User;

public class UserDBController {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
        private ResultSet resultSet2 = null;

	public UserDBController() {

	}
        
        
        public void removeUser(int userID) throws Exception
        {
            DatabaseManager db;
            db = DatabaseManager.getInstance();
            db.connectDB();
            this.connect = db.returnConnection();
            
            preparedStatement = connect.prepareStatement("DELETE FROM abcs.user WHERE user_id = ?");
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
            
            preparedStatement = connect.prepareStatement("DELETE FROM abcs.user_details WHERE user_id = ?");
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        
            db.close();
        
        
        
        }
        public ArrayList<Customer> getUserList() throws Exception
        {
            DatabaseManager db;
            db = DatabaseManager.getInstance();
            db.connectDB();
            this.connect = db.returnConnection();
            
            int userID = 0;
            String email = "";
            int role = 0;
            String fname = "";
            String lname = "";
            int sex = 0;
            String dob = "";
            String contact = "";
            String password = "";
            
            ArrayList<Customer> userList = new ArrayList<Customer>();
            
            preparedStatement = connect
				.prepareStatement("SELECT * FROM abcs.user;");
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			userID = resultSet.getInt("user_id");
			email = resultSet.getString("email");
			role = resultSet.getInt("role_id");
                        password = resultSet.getString("password");
                        
                        preparedStatement = connect
				.prepareStatement("SELECT * FROM abcs.user_details WHERE user_id = ?;");
                        preparedStatement.setInt(1, userID);
                        resultSet2 = preparedStatement.executeQuery();

                        while(resultSet2.next()){
                                fname = resultSet2.getString("first_name");
                                lname = resultSet2.getString("last_name");
                                dob = resultSet2.getString("dob");
                                sex = resultSet2.getInt("sex");
                                contact = resultSet2.getString("contact_number");
                        }
                        
                        //User_details uDetails = new User_details(fname, lname, dob, sex, contact);
                        
                        UFactory factory = new UFactory();
                        User user = factory.createUser(role, email, password);
                        user.setContactNumber(contact);
                        user.setDateOfBirth(dob);
                        user.setEmail(email);
                        user.setFirstName(fname);
                        user.setGender(sex);
                        user.setLastName(lname);
                        user.setRole(role);
                        user.setUserID(userID);
                        
                        userList.add((Customer) user);
		}

                System.out.println(userList);
                db.close();
                return userList;
        
        
        }

	public int getRole(String email, String password) throws SQLException, Exception{

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();

		int role = 0;

		preparedStatement = connect
				.prepareStatement("SELECT role_id FROM abcs.user WHERE email = ? AND password = ?;");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			role = resultSet.getInt("role_id");
		}

		db.close();

		System.out.println(role);

		return role;
	}

	public User createUser(User user, String email, String password) throws Exception{

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                

		int role = 0;
		int userID = 0;
		String userEmail = "";
		String  pass = "";
		//User_details details = new User_details();

		preparedStatement = connect
				.prepareStatement("SELECT * FROM abcs.user WHERE email = ? AND password = ?;");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, password);
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			userID = resultSet.getInt("user_id");
			userEmail = resultSet.getString("email");
			pass = resultSet.getString("password");
			role = resultSet.getInt("role_id");
		}

		preparedStatement = connect
				.prepareStatement("SELECT * FROM abcs.user_details WHERE user_id = ?;");
		preparedStatement.setInt(1, userID);
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			user.setFirstName(resultSet.getString("first_name"));
			user.setLastName(resultSet.getString("last_name"));
			user.setDateOfBirth(resultSet.getString("dob"));
			user.setGender(resultSet.getInt("sex"));
			user.setContactNumber(resultSet.getString("contact_number"));
		}
                

		db.close();

		user.setUserID(userID);
		user.setEmail(userEmail);
		user.setPassword(pass);
		user.setRole(role);
                
                return user;

	}
        
        public User getUser(int userID) throws Exception{

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();

		int role = 0;
		String userEmail = "";
		String  pass = "";
		//User_details details = new User_details();
                User user = new Customer();

		preparedStatement = connect
				.prepareStatement("SELECT * FROM abcs.user WHERE user_id = ?;");
		preparedStatement.setInt(1, userID);
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			userID = resultSet.getInt("user_id");
			userEmail = resultSet.getString("email");
			pass = resultSet.getString("password");
			role = resultSet.getInt("role_id");
		}

		preparedStatement = connect
				.prepareStatement("SELECT * FROM abcs.user_details WHERE user_id = ?;");
		preparedStatement.setInt(1, userID);
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			user.setFirstName(resultSet.getString("first_name"));
			user.setLastName(resultSet.getString("last_name"));
			user.setDateOfBirth(resultSet.getString("dob"));
			user.setGender(resultSet.getInt("sex"));
			user.setContactNumber(resultSet.getString("contact_number"));
		}

		db.close();

		user.setUserID(userID);
		user.setEmail(userEmail);
		user.setPassword(pass);
		user.setRole(role);
                return user;

	}


}
