
package Interfaces;

public interface I_Register {
        /**
	 * Checks the first name, Last name, email, password and phone number of a new user
	 * @param firstName
         * @param lastName
         * @param email
         * @param password
	 * @param phoneNumber
	 * @return true if first name, Last name, email, password and phone number are correct and the user is not in the database
	 * @throws Exception
	 */
	public boolean Register(String firstName, String lastName, String email,  String password, String phoneNumber, String dateOfBirth, String gender) throws Exception;
}
