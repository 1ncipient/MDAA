package analysis;

import selection.SelectionObject;

/**
 * This class creates the analysis type Ratio of Hospital Beds (per 1000) and Current Health Expenditure (per 1000). Subclass of Creator.
 * @author Henry So
 *
 */
public class AnalysisCreator6 extends Creator{
	
	/**
	 * This method creates the Analysis6 object 
	 * @param select utilizes the parameter to invoke method calculate from Analysis6.
	 */
	public void create(SelectionObject select) {
		Analysis6 analysis = new Analysis6();
		analysis.calculate(select);
		
	}
}
