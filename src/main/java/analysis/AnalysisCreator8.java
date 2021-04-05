package analysis;

import selection.SelectionObject;

public class AnalysisCreator8 extends Creator{
	public void create(SelectionObject select) {
		Analysis8 analysis = new Analysis8();
		analysis.calculate(select);
		
	}
}
