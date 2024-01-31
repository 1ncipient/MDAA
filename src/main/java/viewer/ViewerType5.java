package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import analysis.AnalysisObject;
import analysis.DataObject;


/**
 * Class allowing for creation of report text box as a viewer
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public class ViewerType5 implements ViewerCreation{
	
	//dictionary matching labels used by World Bank database to labels that will be printed
	private static HashMap<String, String> labelNames = new HashMap<String, String>();
	
	/**
	 * Function to fill in labelNames HashMap with needed labels
	 * 
	 */
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
    
    
    /** 
     * Method to create text box report for display
     * @param analysis AnalysisObject object containing data to be displayed
     * @return Returns scrollable text box for display
     */
	public JComponent createViewer(AnalysisObject analysis) {
		fillLabels();														
		
		String analysisNumberType = analysis.getClass().getSimpleName();
		if (analysisNumberType.equals("Analysis4") || analysisNumberType.equals("Analysis5")) {
			return createRatio(analysis);
		}
		else {
			return createStandard(analysis);
		}
	}
	
	private static JComponent createStandard(AnalysisObject analysis) {
		DataObject[] data = analysis.getData();							
		
		// start and end year
		int start = analysis.getStart();								
		int end = analysis.getEnd();									
		
		// create text area
		JTextArea report = new JTextArea();
		report.setEditable(false);
//		report.setPreferredSize(new Dimension(500, 500));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage;

		reportMessage = analysis.getSelect().getAnalysisType() + "\n" + "==============================\n";
		
		// for each year from end to start inclusive
		// add the year to the output string
		// go through each data series
		// and print type of data, and associated value
		int lines = 2;
		for (int i = end; i >= start; i--) {
			reportMessage = reportMessage + "Year " + Integer.toString(i) + ":\n";
			lines++;
			
			// for each data series, add name and value to output string
			for (DataObject element : data) {
				String printValue = "n/a";
				double value = element.getDataRecovered().get(i);
				if (value != -1) printValue = Double.toString((double)Math.round(value * 10000d) / 10000d);
				
				reportMessage += "\t" + labelNames.get(element.getDataName()) + " => " + printValue + "\n";
				lines++;
			}
			reportMessage += "\n";
			lines++;
		}

		// set text box output to the output string
		report.setText(reportMessage);
		
		// reset the height using printed lines and pixel height plus one more line spacing
		int height = (lines * 16) + 16;
		report.setPreferredSize(new Dimension(500,height));
		
		// set the scroll bar position
		report.setCaretPosition(0);
		
		JScrollPane outputScrollPane = new JScrollPane(report);
		outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		outputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return outputScrollPane;
	}
	
	private static JComponent createRatio(AnalysisObject analysis) {
		HashMap<Integer, Double> dataRec = analysis.getData()[0].getDataRecovered();						

		// start and end year
		int start = analysis.getStart();								
		int end = analysis.getEnd();	
		
		// create text area
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(500, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage;

		reportMessage = analysis.getSelect().getAnalysisType() + "\n" + "==============================\n";
		
		// find the correct analysis type to print out the two different ratios
		if (analysis.getClass().getSimpleName().equals("Analysis4")) {
			reportMessage = reportMessage + "Year " + Integer.toString(start) + "-" + Integer.toString(end) + ":\n";
			// compute the percentage and round
			double percentage = (double)Math.round(dataRec.get(0) * 10000d) / 10000d;
			reportMessage += "\tForested Area % => " + Double.toString(percentage) + "\n";
			reportMessage += "\tUnforested Area % => " + Double.toString(100 - percentage) + "\n";
		}
		else {
			reportMessage = reportMessage + "Year " + Integer.toString(start) + "-" + Integer.toString(end) + ":\n";
			// compute the percentage and round
			double percentage = (double)Math.round(dataRec.get(0) * 10000d) / 10000d;
			reportMessage += "\tEducation Expenditure % => " + Double.toString(percentage) + "\n";
			reportMessage += "\tOther Expenditure % => " + Double.toString(100 - percentage) + "\n";
		}

		// set text box output to the output string
		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		return outputScrollPane;
	}
}
