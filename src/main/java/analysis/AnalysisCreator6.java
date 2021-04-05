package analysis;

import selection.SelectionObject;

public class AnalysisCreator6 extends Creator{
	public void create(SelectionObject select) {
		Analysis6 analysis = new Analysis6();
		analysis.calculate(select);
		
	}
}
