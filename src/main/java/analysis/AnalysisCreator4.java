package analysis;

import selection.SelectionObject;

/**
 * This class creates the analysis type Average Forest Area (% of land) for selected years. Subclass of Creator.
 * @author Henry So
 *
 */
public class AnalysisCreator4 extends Creator{
	
	/**
	 * This method creates the Analysis4 object 
	 * @param select utilizes the parameter to invoke method calculate from Analysis4.
	 */
	public void create(SelectionObject select) {
		Analysis4 analysis = new Analysis4();
		analysis.calculate(select);
		
	}
}
