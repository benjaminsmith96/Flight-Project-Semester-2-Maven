package Interfaces;

public interface I_Membership {

	/**
	 * Adds points to the user's membership
	 * @param points
	 * @param userID
	 * @throws Exception
	 */
	public void addPoints(int points, int userID) throws Exception;

	/**
	 * Subtracts points to the user's membership
	 * @param points
	 * @param userID
	 * @throws Exception
	 */
	public void subtractPoints(int points, int userID) throws Exception;
        
        public void cancelFlightPoints(int bookingID, int userID) throws Exception;
        
        

}
