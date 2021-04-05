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
	
	private static HashMap<String, String> labelNames = new HashMap<String, String>();
	
	private static void fillLabels() {
		labelNames.put("RATIO", "CO2 Emissions to GDP per capita (US$) Ratio");
        labelNames.put("SP.POP.TOTL", "Population");
        labelNames.put("EN.ATM.CO2E.PC", "CO2 Emissions (tons/capita)");
        labelNames.put("EN.ATM.PM25.MC.M3", "PM2.5 Air Pollution (micrograms/cubic meter)");
        labelNames.put("AG.LND.FRST.ZS", "Forest Area (% of land area)");
        labelNames.put("EG.USE.PCAP.KG.OE", "Energy Use (kg oil equivalent/capita)");
        labelNames.put("NY.GDP.PCAP.CD", "GDP/capita (US$)");
        labelNames.put("SH.MED.BEDS.ZS", "Hospital Beds/1,000 people");
        labelNames.put("SE.XPD.TOTL.GD.ZS", "Government Education Expenditure (% of GDP)");
        labelNames.put("SH.STA.MMRT", "Maternal Mortality Ratio/100,000 births)");
        labelNames.put("SH.XPD.CHEX.PC.CD", "Current Health Expenditure/capita (current US$)");
        labelNames.put("SH.XPD.CHEX.GD.ZS", "Current Health Expenditure (% of GDP)");
        labelNames.put("SP.DYN.IMRT.IN", "Infant Mortality/1,000 births)");	
	}
    
    
    /**Method to create text box report for display
	 * @param analysis AnalysisObject object containing data to be displayed
	 */
	public JComponent createViewer(AnalysisObject analysis) {
		fillLabels();														
		
		DataObject[] data = analysis.getData();							
		
		int start = analysis.getStart();								
		int end = analysis.getEnd();									
		
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(475, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage;

		reportMessage = analysis.getSelect().getAnalysisType() + "\n" + "==============================\n";
		
		for (int i = end; i >= start; i--) {
			reportMessage = reportMessage + "Year " + Integer.toString(i) + ":\n";
			
			for (DataObject element : data) {
				String printValue = "n/a";
				double value = element.getDataRecovered().get(i);
				if (value != -1) printValue = Double.toString((double)Math.round(value * 1000d) / 1000d);
				
				reportMessage += "\t" + labelNames.get(element.getDataName()) + " => " + printValue + "\n";
			}
			reportMessage += "\n";
		}

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		return outputScrollPane;
	}
}
