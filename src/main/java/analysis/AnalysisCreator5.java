package analysis;

import selection.SelectionObject;

/**
 * This class creates the analysis type Average Expenditure on Education (% of GDP). Subclass of Creator.
 * @author Henry So
 *
 */
public class AnalysisCreator5 extends Creator{
	
	/**
	 * This method creates the Analysis5 object 
	 * @param select utilizes the parameter to invoke method calculate from Analysis5.
	 */
	public void create(SelectionObject select) {
		Analysis5 analysis = new Analysis5();
		analysis.calculate(select);
		
	}
}
