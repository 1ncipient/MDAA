package analysis;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import selection.SelectionObject;

/**
 * This class defines the analysis for analysis type Government Education Expenditure (% of GDP) vs Health Expenditure (% of GDP). This class is a subclass of class AnalysisObject.
 * 
 * @author Henry So
 *
 */
public class Analysis8 extends AnalysisObject{
	//instance variables
	private String[] requiredStats;
	private Data receive;
	
	/**
	 * This public method calculate obtains the data based on the analysis tickers and checks if it returns an empty data set. An error message is invoked if no data is found (dataset empty)
	 * @param select SelectionObject passed in; based on this object's attributes
	 */
	public void calculate(SelectionObject select) {
		requiredStats = new String[] {"SE.XPD.TOTL.GD.ZS", "SH.XPD.CHEX.GD.ZS"};
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
	
	/**
	 * Mutator method for requiredStats
	 * @param requiredStats sets this object to parameter passed in.
	 */
	public void setRequiredStats(String[] requiredStats) {
		this.requiredStats = requiredStats;
	}
	
	/**
	 * Accessor method for requiredStats.
	 * @return requiredStats instance variable
	 */
	public String[] getRequiredStats() {
		return requiredStats;
	}
}
