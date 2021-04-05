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
			pieChart = ChartFactory.createPieChart("Forest Area (% of land area), " + country + ", " + start + "-" + end, 
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
			pieChart = ChartFactory.createPieChart("Government Education Expenditure (% of GDP), " + country + ", " + start + "-" + end, 
					dataset, true, true, false);
		}
		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		return chartPanel;
	}
}
