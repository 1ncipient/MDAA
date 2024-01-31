package reader;

import java.util.HashMap;

/**
 * Defines the interface for CreateData to implement
 * 
 * @author Henry So
 *
 */
public interface Search {

	public HashMap<Integer, Double> readData(String country, String data, String start, String end);
	
}
