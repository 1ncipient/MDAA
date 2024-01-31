package analysis;

import selection.SelectionObject;

/**
 * This class creates the analysis type Healthcare Expenditure per Capita (current US$) vs Infant Mortality Rate (per 1000). Subclass of Creator.
 * @author Henry So
 *
 */
public class AnalysisCreator7 extends Creator{
	
	/**
	 * This method creates the Analysis7 object 
	 * @param select utilizes the parameter to invoke method calculate from Analysis7.
	 */
	public void create(SelectionObject select) {
		Analysis7 analysis = new Analysis7();
		analysis.calculate(select);
		
	}
}
