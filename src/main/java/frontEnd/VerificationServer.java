package frontEnd;

/**
 * This class represents the verification server
 * which will handle the checking of user passwords
 * 
 * @author Henry So
 *
 */
public class VerificationServer implements Verify {
	
	/**
	 * Instance variable to indicate if valid
	 */
	private boolean loginValid;
	
	/**
	 * Constructor will initiate the instance variable
	 * 
	 */
	public VerificationServer() {
		this.loginValid = false;
	}
	
	/**
	 * Method checks to see if the supplied username and password is valid
	 * 
	 * @param username the user's supplied username
	 * @param password the user's supplied password
	 * @return true or false if the password is valid
	 * 
	 */
	public boolean checkPin(String username, String password) {
		LoginDatabase data = new LoginDatabase();
		String returnPassword = data.loginCheck(username);
		
		if (password.equals(returnPassword)) {
			loginValid = true;
		}
		
		return loginValid;
	}
	
}
