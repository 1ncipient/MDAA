package analysis;

import selection.SelectionObject;

public class AnalysisCreator4 extends Creator{
	public void create(SelectionObject select) {
		Analysis4 analysis = new Analysis4();
		analysis.calculate(select);
		
	}
}
