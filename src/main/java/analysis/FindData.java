package analysis;

import selection.SelectionObject;

/**
 * Defines the interface for Data to implement
 * 
 * @author Henry So
 *
 */
public interface FindData {

	public DataObject[] getData(String[] requireStats, SelectionObject select);
	
}
