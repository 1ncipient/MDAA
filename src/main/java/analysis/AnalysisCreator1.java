package analysis;

import selection.SelectionObject;

/**
 * This class creates the analysis type CO2 Emissions vs Energy Use vs PM 2.5 Air Pollution object. Subclass of Creator.
 * @author Henry So
 *
 */
public class AnalysisCreator1 extends Creator{

	/**
	 * This method creates the Analysis1 object 
	 * @param select utilizes the parameter to invoke method calculate from Analysis1.
	 */
	public void create(SelectionObject select) {
		Analysis1 analysis = new Analysis1();
		analysis.calculate(select);
		
	}
	
}
