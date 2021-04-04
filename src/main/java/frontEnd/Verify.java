package frontEnd;

/**
 * Defines the interface for VerificationServer to implement
 * 
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public interface Verify {
	
	public boolean checkPin(String username, String password);
	
}
