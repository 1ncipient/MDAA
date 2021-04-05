package analysis;

import selection.SelectionObject;

/**
 * Defines the interface for Data to implement
 * 
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public interface FindData {

	public DataObject[] getData(String[] requireStats, SelectionObject select);
	
}
