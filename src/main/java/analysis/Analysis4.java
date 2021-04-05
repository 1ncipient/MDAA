package analysis;

import selection.SelectionObject;

public class Analysis4 extends AnalysisObject{
	private String[] requiredStats;
	private Data receive;
	
	public void calculate(SelectionObject select) {
		requiredStats = new String[] {"AG.LND.FRST.ZS", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3"};
		this.setSelect(select);
		receive = new Data();
		this.setData(receive.getData(requiredStats, this.getSelect()));
	}
	
	public void setRequiredStats(String[] requiredStats) {
		this.requiredStats = requiredStats;
	}
	
	public String[] getRequiredStats() {
		return requiredStats;
	}
}
