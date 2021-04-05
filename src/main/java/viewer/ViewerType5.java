package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import analysis.AnalysisObject;
import analysis.DataObject;


/**
 * Class allowing for creation of report text box as a viewer
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public class ViewerType5 implements ViewerCreation{
	private static HashMap<String, String> labelNames = new HashMap<String, String>();		//hashmap containing label names

	
	/**
	 * Function to convert names received and used but World Bank database and the names to be actually displayed
	 */
    private static void fillLabels() {
        labelNames.put("SP.POP.TOTL", "Population");
        labelNames.put("EN.ATM.CO2E.PC", "CO2 emissions (metric tons/capita)");
        labelNames.put("EN.ATM.PM25.MC.M3", "PM2.5 air pollution (micrograms/cubic meter)");
        labelNames.put("AG.LND.FRST.ZS", "Forest area (% of land area)");
        labelNames.put("EG.USE.PCAP.KG.OE", "Energy use (kg oil equivalent/capita)");
        labelNames.put("NY.GDP.PCAP.CD", "GDP/capita (US$)");
        labelNames.put("SH.MED.BEDS.ZS", "Hospital beds/1,000 people");
        labelNames.put("SE.XPD.TOTL.GD.ZS", "Government education expenditure (% of GDP)");
        labelNames.put("SH.STA.MMRT", "Maternal mortality ratio/100,000 births)");
        labelNames.put("SH.XPD.CHEX.PC.CD", "Current health expenditure/capita (current US$)");
        labelNames.put("SH.XPD.CHEX.GD.ZS", "Current health expenditure (% of GDP)");
        labelNames.put("SP.DYN.IMRT.IN", "Infant mortality/1,000 births)");
    }
    
    
    /**Method to create text box report for display
	 * @param analysis AnalysisObject object containing data to be displayed
	 */
	public JComponent createViewer(AnalysisObject analysis) {
		fillLabels();															//fill in hashmap of label names
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();			//create empty dataset to populate
		
		DataObject[] data = analysis.getData();							//array containing data from analysis

		String type = analysis.getAnalysisType();						//type of analysis used
		int start = analysis.getStart();								//start year
		int end = analysis.getEnd();									//end year

		HashMap<Integer, Double> dataRecovered;							//hashmap containing data and year
		dataRecovered = data[0].getDataRecovered();
		
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		
		String reportMessage;
		reportMessage = "";

		reportMessage += type + "\n";									//title
		reportMessage += "==============================\n";
		
		for (int i = end; i>=start; i--) {								//for each year
			reportMessage+="Year " + i + ":\n";
			for (int j = 0; j < data.length; j++) {						//go through each series, and add data to string
				dataRecovered = data[j].getDataRecovered();
				double value = dataRecovered.get(i);
				String name = labelNames.get(data[i].getDataName()); 	
				reportMessage += "\t" + name + " => " + value + "\n";
			}
			reportMessage+="\n";
		}

		report.setText(reportMessage);									//set string at the end
		JScrollPane outputScrollPane = new JScrollPane(report);
		return outputScrollPane;
	}
}
