package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import analysis.AnalysisObject;
import analysis.DataObject;


/**
 * Class allowing for pie charts to be created
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public class ViewerType1 implements ViewerCreation{

	// dictionary matching labels used by World Bank database to labels that will be printed
	private static HashMap<String, String> labelNames = new HashMap<String, String>(); 
	
	/**
	 * Function to fill in labelNames HashMap with needed labels
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
	
	/**Method to create pie chart viewers for display
	 * 
	 * @param analysis AnalysisObject object containing data to be graphed
	 * @return Returns pie chart for display
	 */
	public ChartPanel createViewer(AnalysisObject analysis) {
		// initialize the label set
		fillLabels();
		
		// parse the data object
		DataObject[] data = analysis.getData();
		HashMap<Integer, Double> dataRec = data[0].getDataRecovered();

		// add the data
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if (analysis.getClass().getSimpleName().equals("Analysis4")) {
			dataset.addValue(dataRec.get(0), "Forested", "");
			dataset.addValue(100-dataRec.get(0), "Unforested", "");
		}
		else {
			dataset.addValue(dataRec.get(0), "Education Expenditure", "");
			dataset.addValue(100-dataRec.get(0), "Other Expenditure", "");
		}

		// get the name
		JFreeChart pieChart = ChartFactory.createMultiplePieChart(analysis.getSelect().getAnalysisType(), dataset,
				TableOrder.BY_COLUMN, true, true, false);

		// initialize the chartPanel
		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(500, 500));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		return chartPanel;
	}
}
