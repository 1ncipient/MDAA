package frontEnd;

/**
 * Defines the interface for VerificationServer to implement
 * 
 * @author Henry So
 *
 */
public interface Verify {
	
	public boolean checkPin(String username, String password);
	
}
