package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import analysis.AnalysisObject;
import analysis.DataObject;

public class ViewerType1 implements ViewerCreation{
	private static HashMap<String, String> labelNames = new HashMap<String, String>();

    private static void fillLabels() {
        labelNames.put("SP.POP.TOTL", "Population");
        labelNames.put("EN.ATM.CO2E.PC", "CO2 emissions (metric tons/capita)");
        labelNames.put("EN.ATM.PM25.MC.M3", "PM2.5 air pollution (micrograms/cubic meter)");
        labelNames.put("AG.LND.FRST.ZS", "Forest area (% of land area)");
        labelNames.put("EG.USE.PCAP.KG.OE", "GDP/capita (US$)");
        labelNames.put("SH.MED.BEDS.ZS", "Hospital beds/1,000 people");
        labelNames.put("SE.XPD.TOTL.GD.ZS", "Government education expenditure (% of GDP)");
        labelNames.put("SH.STA.MMRT", "Maternal mortality ratio/100,000 births)");
        labelNames.put("SH.XPD.CHEX.PC.CD", "Current health expenditure/capita (current US$)");
        labelNames.put("SH.XPD.CHEX.GD.ZS", "Current health expenditure (% of GDP)");
        labelNames.put("SP.DYN.IMRT.IN", "Infant mortality/1,000 births)");
    }
	public ChartPanel createViewer(AnalysisObject analysis) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		DataObject[] data = analysis.getData();
		String country = analysis.getCountry();
		String type = analysis.getAnalysisType();
		int start = analysis.getStart();
		int end = analysis.getEnd();
		
		HashMap<Integer, Double> dataRecovered;
		JFreeChart pieChart = null;
		dataRecovered = data[0].getDataRecovered();
		if (type == "AG.LND.FRST.ZS") {
			double counter = 0;
			for (Integer key: dataRecovered.keySet()) {
				counter+=dataRecovered.get(key);
			}
			counter = counter/dataRecovered.size();
			dataset.setValue("Forested Area %", counter);
			dataset.setValue("Unforested Area %",  1-counter);
			pieChart = ChartFactory.createPieChart(analysis.getAnalysisType(), 
					dataset, true, true, false);
		}
		
		else if (type == "SE.XPD.TOTL.GD.ZS") {
			double counter = 0;
			for (Integer key: dataRecovered.keySet()) {
				counter+=dataRecovered.get(key);
			}
			counter = counter/dataRecovered.size();
			dataset.setValue("Education Expenditure %", counter);
			dataset.setValue("Other Expenditure %",  1-counter);
			pieChart = ChartFactory.createPieChart(analysis.getAnalysisType(), 
					dataset, true, true, false);
		}
		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		return chartPanel;
	}
}
