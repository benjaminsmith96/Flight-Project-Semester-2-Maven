package Interfaces;

public interface I_Login {

	/**
	 * Checks the username and password of a user
	 * @param username
	 * @param password
	 * @return true if username and password are correct and the user is in the database
	 * @throws Exception
	 */
	public boolean login(String username, String password) throws Exception;

}
