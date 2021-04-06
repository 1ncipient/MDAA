package analysis;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import selection.SelectionObject;

public class Analysis5 extends AnalysisObject{
	private String[] requiredStats;
	private Data receive;
	
	public void calculate(SelectionObject select) {
		requiredStats = new String[] {"SE.XPD.TOTL.GD.ZS"};
		this.setSelect(select);
		receive = new Data();
		this.setData(receive.getData(this.getRequiredStats(), this.getSelect()));
		if (!hasData(this.getData())) {
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "No data was found! Please change your search options.", "Error", JOptionPane.WARNING_MESSAGE);
		}
		else {
			ResultObject result = new ResultObject(this);
			result.setResult(this);
		}
	}
	
	public void setRequiredStats(String[] requiredStats) {
		this.requiredStats = requiredStats;
	}
	
	public String[] getRequiredStats() {
		return requiredStats;
	}
}
