package analysis;

import selection.SelectionObject;

/**
 * This class creates the analysis type Ratio of CO2 Emissions and GDP per Capita (current US$). Subclass of Creator.
 * @author Henry So
 *
 */
public class AnalysisCreator3 extends Creator{
	
	/**
	 * This method creates the Analysis3 object 
	 * @param select utilizes the parameter to invoke method calculate from Analysis3.
	 */
	public void create(SelectionObject select) {
		Analysis3 analysis = new Analysis3();
		analysis.calculate(select);
		
	}
}
