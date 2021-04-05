package analysis;

import selection.SelectionObject;

public class AnalysisCreator5 extends Creator{
	public void create(SelectionObject select) {
		Analysis5 analysis = new Analysis5();
		analysis.calculate(select);
		
	}
}
