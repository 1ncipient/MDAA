package analysis;

import selection.SelectionObject;

public class AnalysisCreator1 extends Creator{

	public void create(SelectionObject select) {
		Analysis1 analysis = new Analysis1();
		analysis.calculate(select);
		
	}
	
}
