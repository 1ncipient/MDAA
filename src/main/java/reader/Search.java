package reader;

import java.util.HashMap;

/**
 * Defines the interface for CreateData to implement
 * 
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public interface Search {

	public HashMap<Integer, Double> readData(String country, String data, String start, String end);
	
}
