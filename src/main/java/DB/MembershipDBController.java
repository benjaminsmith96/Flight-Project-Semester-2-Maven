package DB;

import Users.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MembershipDBController {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private int update;

	public MembershipDBController() {

	}

	public int getMembership(Customer userID) throws SQLException, Exception{

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                System.out.print(userID.getUserID());
		int points = 0;

		preparedStatement = connect
				.prepareStatement("SELECT points FROM abcs.membership WHERE user_id = ?;");
		preparedStatement.setInt(1, userID.getUserID());
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			points = resultSet.getInt("points");
                        System.out.print("found");
		}

		db.close();
                System.out.println("Memp" +points);
		userID.setPoints(points);
		return points;
	}

	public void addMembershipPoints(int userID, int points) throws SQLException, Exception{

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                

		preparedStatement = connect
				.prepareStatement("UPDATE abcs.membership SET points = ? WHERE user_id = ?;");
		preparedStatement.setInt(1, points);
		preparedStatement.setInt(2, userID);
		update = preparedStatement.executeUpdate();

		db.close();
	}

	public void substractMembershipPoints(int userID, int points) throws SQLException, Exception{

		DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                int currentPoints = 0;
                
                preparedStatement = connect
				.prepareStatement("SELECT points FROM abcs.membership WHERE user_id = ?;");
		preparedStatement.setInt(1, userID);
		resultSet = preparedStatement.executeQuery();

		while(resultSet.next()){
			currentPoints = resultSet.getInt("points");
		}
                
                currentPoints = currentPoints - points;

		preparedStatement = connect
				.prepareStatement("UPDATE abcs.membership SET points = ? WHERE user_id = ?;");
		preparedStatement.setInt(1, currentPoints);
		preparedStatement.setInt(2, userID);
		update = preparedStatement.executeUpdate();

		db.close();
	}
        
        public int cancelFlightPoints(int bookingId) throws Exception{
        DatabaseManager db;
	db = DatabaseManager.getInstance();
	db.connectDB();
        this.connect = db.returnConnection();
        
        int userId = 0;
        int flightId = 0;
        double bookingPrice = 0;
        Date bookingDate = null;
        String stringBookingDate = "";
        int removeAddPoints = 0;
          
        preparedStatement = connect
        .prepareStatement("SELECT user_id, flight_number, booking_price, booking_date FROM flight_bookings WHERE booking_id = ?;");
        preparedStatement.setInt(1, bookingId);
        resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()){
            userId = resultSet.getInt("user_id");
            flightId = resultSet.getInt("flight_number");
            bookingPrice = resultSet.getDouble("booking_price");
            bookingDate = resultSet.getDate("booking_date");
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        stringBookingDate = dateFormat.format(bookingDate);
        
        int genPoints = calculatePoints(bookingPrice, flightId, stringBookingDate);
        
        int daysBe;
        daysBe = daysBetween(stringBookingDate, flightId);
        
        switch (userLevel(userId)) { 
        case "GOLD": 
            double[] goldMulti = {0.4, 0.6, 0.8, 1.0, 1.2, 1.5, 1.7, 2.0};
            removeAddPoints = calculateCancelPoints(genPoints, daysBe, goldMulti); 
            break; 
        case "SILVER": 
            double[] silverMulti = {0.0, 0.0, 0.2, 0.4, 0.6, 0.8, 1.0, 1.3};
            removeAddPoints = calculateCancelPoints(genPoints, daysBe, silverMulti);
            break; 
        case "BRONZE": 
            double[] bronzeMulti = {0.0, 0.0, 0.0, 0.0, 0.2, 0.4, 0.6, 0.8};
            removeAddPoints = calculateCancelPoints(genPoints, daysBe, bronzeMulti);
            break; 
        default: 
            break; 
        }
        if(genPoints > removeAddPoints){
            removeAddPoints = genPoints - removeAddPoints;
            //remove points
        }
        else if(genPoints < removeAddPoints){
            removeAddPoints = genPoints + removeAddPoints;
            //add points
        }
        else{
            //Do nothing points are the same
        }
        
        db.close();
        
        System.out.println("Points removed:" + removeAddPoints);
        return removeAddPoints;
    }
    
    
    public int calculateCancelPoints(int points, int daysBe, double[] discountFactor) throws Exception{
        DatabaseManager db;
	db = DatabaseManager.getInstance();
	db.connectDB();
	this.connect = db.returnConnection();
        
        int[] discountDays = {7,14,21,30,60,90,180};
        double finalPoints = 0.0;
        if(daysBe <= discountDays[0]){
            finalPoints = points * discountFactor[0];

        }
        else if(daysBe <= discountDays[1]){
            finalPoints = points * discountFactor[1];

        }
        else if(daysBe <= discountDays[2]){
            finalPoints = points * discountFactor[2];

        }
        else if(daysBe <= discountDays[3]){
            finalPoints = points * discountFactor[3];

        }
        else if(daysBe <= discountDays[4]){
            finalPoints = points * discountFactor[4];

        }
        else if(daysBe <= discountDays[5]){
            finalPoints = points * discountFactor[5];

        }
        else if(daysBe <= discountDays[6]){
            finalPoints = points * discountFactor[6];

        }
        else if(daysBe > discountDays[6]){
            finalPoints = points * discountFactor[7];

        }
        System.out.println(finalPoints);
        
        db.close();
        return(int) (finalPoints);
    }
    
    public String userLevel(int userId) throws SQLException, Exception{
        DatabaseManager db;
	db = DatabaseManager.getInstance();
	db.connectDB();
	this.connect = db.returnConnection();
        String level = null;
        int points = 0;
        preparedStatement = connect
        .prepareStatement("SELECT points FROM membership WHERE user_id = ?;");
        preparedStatement.setInt(1, userId);
        resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()){
            points = resultSet.getInt("points");
        }
        if(points > 15000)
            level = "GOLD";
        else if(points > 7000)
            level = "SILVER";
        else if(points > 1000)
            level = "BRONZE";
        else
            level = "STANDARD";
        
        System.out.println(level);
        db.close();
        return level;
    }
    
    public int calculatePoints(double flightPrice, int flightId, String todayDateS) throws Exception{
        DatabaseManager db;
	db = DatabaseManager.getInstance();
	db.connectDB();
	this.connect = db.returnConnection();
        
        int pointsAfterMultiplier = 0;
        double finalPoints = 0;
        int daysBe;
        daysBe = daysBetween(todayDateS, flightId);
        int[] discountDays = {7,14,21,30,60,90,180};
        double[] discountFactor = {2.0, 1.7, 1.5, 1.0, 0.8, 0.7, 0.6, 0.5};
        if(daysBe <= discountDays[0]){
            finalPoints = flightPrice * discountFactor[0];
            System.out.println(finalPoints);
        }
        else if(daysBe <= discountDays[1]){
            finalPoints = flightPrice * discountFactor[1];
            System.out.println(finalPoints);
        }
        else if(daysBe <= discountDays[2]){
            finalPoints = flightPrice * discountFactor[2];
            System.out.println(finalPoints);
        }
        else if(daysBe <= discountDays[3]){
            finalPoints = flightPrice * discountFactor[3];
            System.out.println(finalPoints);
        }
        else if(daysBe <= discountDays[4]){
            finalPoints = flightPrice * discountFactor[4];
            System.out.println(finalPoints);
        }
        else if(daysBe <= discountDays[5]){
            finalPoints = flightPrice * discountFactor[5];
            System.out.println(finalPoints);
        }
        else if(daysBe <= discountDays[6]){
            finalPoints = flightPrice * discountFactor[6];
            System.out.println(finalPoints);
        }
        else if(daysBe > discountDays[6]){
            finalPoints = flightPrice * discountFactor[7];
            System.out.println(finalPoints);
        }
     
        
        db.close();
        return (int) finalPoints;
    }
    
    public int daysBetween(String todayDateS, int flightId) throws SQLException, ParseException, Exception{
        DatabaseManager db;
	db = DatabaseManager.getInstance();
	db.connectDB();
	this.connect = db.returnConnection();SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate;
        if(todayDateS == null){
            todayDate = new Date();
        }
        else{
            todayDate = sdf.parse(todayDateS);
        }
	System.out.println(sdf.format(todayDate));
        String departDateS = null;
        preparedStatement = connect
        .prepareStatement("SELECT departure_date FROM flight WHERE flight_number = ?;");
        preparedStatement.setInt(1, flightId);
        resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()){
          departDateS = resultSet.getString("departure_date");
        }
        
        Date departDate = sdf.parse(departDateS);
        System.out.println("date1 : " + sdf.format(departDate));
        
        long diff = departDate.getTime() - todayDate.getTime();
        //System.out.println ("Days: " + (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1));
        //int daysBe = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1;
        int daysBe = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        daysBe += 1;
        System.out.println (daysBe);
        db.close();
        return daysBe;
    }

}
