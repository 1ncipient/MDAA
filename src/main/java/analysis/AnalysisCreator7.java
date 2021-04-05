package analysis;

import selection.SelectionObject;

public class AnalysisCreator7 extends Creator{
	public void create(SelectionObject select) {
		Analysis7 analysis = new Analysis7();
		analysis.calculate(select);
		
	}
}
