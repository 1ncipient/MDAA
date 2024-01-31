package analysis;

import selection.SelectionObject;

/**
 * Interface exposed by AnalysisObject. Needed for communication with it.
 * @author Henry So
 *
 */
public interface Calculate {
	
	/**
	 * Abstract method, implemented by AnalysisObject
	 * @param select New SelectionObject for current AnalysisObject
	 */
	public void calculate(SelectionObject select);

}
