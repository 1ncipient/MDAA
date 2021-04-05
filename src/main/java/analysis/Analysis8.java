package analysis;

import selection.SelectionObject;

public class Analysis8 extends AnalysisObject{
	private String[] requiredStats;
	private Data receive;
	
	public void calculate(SelectionObject select) {
		requiredStats = new String[] {"SE.XPD.TOTL.GD.ZS", "SH.XPD.CHEX.GD.ZS"};
		this.setSelect(select);
		receive = new Data();
		this.setData(receive.getData(this.getRequiredStats(), this.getSelect()));
		ResultObject result = new ResultObject(this);
		result.setResult(this);
	}
	
	public void setRequiredStats(String[] requiredStats) {
		this.requiredStats = requiredStats;
	}
	
	public String[] getRequiredStats() {
		return requiredStats;
	}
}
