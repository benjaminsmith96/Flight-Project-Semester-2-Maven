
package DB;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class CouponDBController {
    
        private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
    
        
        public int couponOnPurchase(String code, int flightPoints) throws SQLException, Exception {
            
            
            DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                
                
        String type = ""; 
        int id = 0;
        double pointsFactor = 0;
        preparedStatement = connect
        .prepareStatement("SELECT type, generator_id FROM coupon WHERE coupon_code = ?;");
        preparedStatement.setString(1, code);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            type = resultSet.getString("type");
            id = resultSet.getInt("generator_id");
        }
        

        if(type.equals("STANDARD"))
            pointsFactor = 0.10;
        else if(type.equals("BRONZE"))
            pointsFactor = 0.15;
        else if(type.equals("SILVER"))
            pointsFactor = 0.20;
        else if(type.equals("GOLD"))
            pointsFactor = 0.25;
        pointsFactor = flightPoints * pointsFactor;
        flightPoints = (int) (flightPoints + pointsFactor);
        //Adds the points for the user who created the code
        addGeneratorCouponPoints(id, flightPoints);
        //Updates the amount of times the coupon has been used
        updateCouponInfo(code);
        //returns the new flight points
        
        return flightPoints;
    }
    
    public void updateCouponInfo(String code) throws SQLException, Exception{
        
        
        DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                
                
        int usedTimes = 0;
        preparedStatement = connect
        .prepareStatement("SELECT used_times FROM coupon WHERE coupon_code = ?;");
        preparedStatement.setString(1, code);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
          usedTimes = resultSet.getInt("used_times");
        }
        usedTimes++;
        preparedStatement = connect
        .prepareStatement("UPDATE coupon SET used_times = ? WHERE coupon_code = ?;");
        preparedStatement.setInt(1, usedTimes);
        preparedStatement.setString(2, code);
        preparedStatement.executeUpdate();
        
        
        db.close();
    }
    
    public void addGeneratorCouponPoints(int id, double couponPoints) throws SQLException, Exception {
        
        
        DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                
                
        int points = 0;
        preparedStatement = connect
        .prepareStatement("SELECT points FROM membership WHERE user_id = ?;");
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
          points = resultSet.getInt("points");
        }
        points = (int) (couponPoints + points);
        preparedStatement = connect
        .prepareStatement("UPDATE membership SET points = ? WHERE user_id = ?;");
        preparedStatement.setInt(1, points);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        
        
        db.close();
    }
    
    
    /*-------------------------------------------------------------------------------------------------------------*/
    
    
    public boolean checkCouponValid(String code, int userID) throws SQLException, ParseException, Exception{
        
        DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
        
        Date activeDS = null, expireDS = null;
        String type = null;
        int usedTimes = 0;
        boolean check = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        preparedStatement = connect
        .prepareStatement("SELECT * FROM coupon WHERE coupon_code = ? AND use_by_id = ?;");
        preparedStatement.setString(1, code);
        preparedStatement.setInt(2, userID);
        resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()){
          activeDS = resultSet.getDate("active_date");
          expireDS = resultSet.getDate("expire_date");
          type = resultSet.getString("type");
          usedTimes = resultSet.getInt("used_times");
        }
        db.close();
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date activeD = activeDS;
        Date expireD = expireDS;
        String  today = format.format(new Date()) ;
        Date todayDate = format.parse(today);
        
        if(!checkUniqueCode(code)){
            if(checkCouponDates(activeD, expireD, todayDate, check)){
                if(checkCouponUsed(type, usedTimes, check)){
                    check = true;
                }
                else{check = false;}
            }
            else{check = false;}
        }
        else{check = false;}
        return check;
    }
    public boolean checkCouponUsed(String type, int usedTimes, boolean check){
        check = false;
        if(usedTimes == 0){
            System.out.println("Use It");
            check = true;
        }
        else if(type == "GOLD" && usedTimes < 3){
            System.out.println("Use It");
            check = true;
        }
        else if(type == "SILVER" && usedTimes < 2){
            System.out.println("Use It");
            check = true;
        }
        else{
            System.out.println("Fail 3");
            check = false;
        }
        return check;  
    }
    
    public boolean checkCouponDates(Date active, Date expire, Date today, boolean check){
        check = false;
        if(active.after(today) || active.equals(today)){
            if(today.before(expire)){
                check = true;
            }
            else{
                System.out.println("Fail 2");
                check = false;
            }
        }
        else{
            
            System.out.println("Fail 1");
            check = false;
        }
        return check;
    }
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public String generateOnClick(int userId, String targetEmail) throws Exception{
        String code = generateCode();
        addCoupon(code, userId, targetEmail);
        
        
        return code;
    }
    
    
    public void addCoupon(String code, int generaterID, String targetEmail) throws Exception{
        
       int targetID = getID(targetEmail);
        
       String type = userLevel(generaterID);
       
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Date activeDate = new Date();
       LocalDate localDate = activeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
       LocalDate newDate = localDate.plusMonths(4);
       Date expireDate = Date.from(newDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
       
       
        // PreparedStatements can use variables and are more efficient
      preparedStatement = connect
          .prepareStatement("INSERT INTO coupon VALUES (default, ?, ?, ?, ? , ?, ?, ?)");
      // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
      // Parameters start with 1
      preparedStatement.setString(1, code);
      preparedStatement.setInt(2, generaterID);
      preparedStatement.setInt(3, targetID);
      preparedStatement.setObject(4, activeDate);
      preparedStatement.setObject(5, expireDate);
      preparedStatement.setString(6, type);
      preparedStatement.setInt(7, 0);
      preparedStatement.executeUpdate();
      
      
      
      
        
    }
    
    public int getID(String email) throws SQLException, Exception{
              
        int id =0;
        preparedStatement = connect
        .prepareStatement("SELECT user_id FROM user WHERE email = ?;");
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()){
            id = resultSet.getInt("user_id");
        }
        
        
        return id;      
    }
    
    public String generateCode() throws Exception{
        
        boolean unique = false;
        do{
            String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            SecureRandom rnd = new SecureRandom();
            int len = 12;
            StringBuilder sb = new StringBuilder( len );
               for( int i = 0; i < len; i++ )
                  sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
            System.out.println(sb);
            unique = checkUniqueCode(sb.toString());
            if(unique == true)
                return sb.toString();
        }while(unique == false);
        return null;
    }
    
    public boolean checkUniqueCode(String code) throws SQLException, Exception{
        DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                
        boolean unique = false;
        preparedStatement = connect
        .prepareStatement("SELECT coupon_code FROM coupon WHERE coupon_code = ?;");
        preparedStatement.setString(1, code);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            unique = false;
        }else {
            unique = true;
        }
        
        return unique;
    }
    /*-----------FOUND IN MYSQL ALREADY!!!!!!!!!!!!--------------*/
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
        
        
        return level;
    }
    
    public int checkUserApplied(String coupon) throws Exception{
        int userId = 0;
        
        DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                
                preparedStatement = connect
        .prepareStatement("SELECT * FROM coupon WHERE coupon_code = ?;");
        preparedStatement.setString(1, coupon);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            userId = resultSet.getInt("generator_id");
        }
        
        db.close();
        
        return userId;
    }
    public boolean canGenerateAnotherCode(int userID) throws SQLException, ParseException, Exception
    {
        
        DatabaseManager db;
		db = DatabaseManager.getInstance();
		db.connectDB();
		this.connect = db.returnConnection();
                
        boolean canGenerate = false;
        String date = null;
        Date dateDB;
        int couponCount = 0;
        int allowedCoupons = 3;
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        preparedStatement = connect
        .prepareStatement("SELECT expire_date FROM coupon WHERE generator_id = ?;");
        preparedStatement.setInt(1, userID);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next() && couponCount < allowedCoupons){
            date = resultSet.getString("expire_date");
            dateDB = sdf.parse(date);
            if(todayDate.before(dateDB)){
                couponCount++;
            }
        }
        if(couponCount < allowedCoupons){
            canGenerate = true;
        }
        else{
            canGenerate = false;
        }
        
        db.close();
        
        return canGenerate;
    }
    
    public boolean emailCheck(String username) throws SQLException, Exception{

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
		
                if(email.equals(username)){
                        return true;
                }
                
                
		db.close();

		return false;
	}
}
