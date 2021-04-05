package analysis;

import selection.SelectionObject;

public class Analysis3 extends AnalysisObject{
	private String[] requiredStats;
	private Data receive;
	
	public void calculate(SelectionObject select) {
		requiredStats = new String[] {"EN.ATM.CO2E.PC", "NY.GDP.PCAP.CD"};
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
