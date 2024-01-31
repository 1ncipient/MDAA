package analysis;

import selection.SelectionObject;

/**
 * This class creates the analysis type Government Education Expenditure (% of GDP) vs Health Expenditure (% of GDP). Subclass of Creator.
 * @author Henry So
 *
 */
public class AnalysisCreator8 extends Creator{
	
	/**
	 * This method creates the Analysis8 object 
	 * @param select utilizes the parameter to invoke method calculate from Analysis8.
	 */
	public void create(SelectionObject select) {
		Analysis8 analysis = new Analysis8();
		analysis.calculate(select);
		
	}
}
