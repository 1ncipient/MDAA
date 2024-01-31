package analysis;

import selection.SelectionObject;

/**
 * This class creates the analysis type PM 2.5 Air Pollution vs Forest Area. Subclass of Creator.
 * @author Henry So
 *
 */
public class AnalysisCreator2 extends Creator{
	
	/**
	 * This method creates the Analysis2 object 
	 * @param select utilizes the parameter to invoke method calculate from Analysis2.
	 */
	public void create(SelectionObject select) {
		Analysis2 analysis = new Analysis2();
		analysis.calculate(select);
		
	}
}
