package analysis;

import selection.SelectionObject;

public class AnalysisCreator3 extends Creator{
	public void create(SelectionObject select) {
		Analysis3 analysis = new Analysis3();
		analysis.calculate(select);
		
	}
}
