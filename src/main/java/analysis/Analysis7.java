package analysis;

import selection.SelectionObject;

public class Analysis7 extends AnalysisObject{
	private String[] requiredStats;
	private Data receive;
	
	public void calculate(SelectionObject select) {
		requiredStats = new String[] {"SH.XPD.CHEX.PC.CD", "SP.DYN.IMRT.IN"};
		this.setSelect(select);
		receive = new Data();
		this.setData(receive.getData(this.getRequiredStats(), this.getSelect()));
	}
	
	public void setRequiredStats(String[] requiredStats) {
		this.requiredStats = requiredStats;
	}
	
	public String[] getRequiredStats() {
		return requiredStats;
	}
}
