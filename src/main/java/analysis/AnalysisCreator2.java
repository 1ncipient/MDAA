package analysis;

import selection.SelectionObject;

public class AnalysisCreator2 extends Creator{
	public void create(SelectionObject select) {
		Analysis2 analysis = new Analysis2();
		analysis.calculate(select);
		
	}
}
