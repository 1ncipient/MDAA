package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import analysis.AnalysisObject;
import analysis.DataObject;

public class ViewerType4 implements ViewerCreation{
	
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
	 * Method to create scatter chart for display
	 * @param analysis AnalysisObject object containing data to be graphed
	 * @return Returns scatter chart for display
	 */
	public ChartPanel createViewer(AnalysisObject analysis) {
		// initialize the label set
		fillLabels();
		DataObject[] data = analysis.getData();
		HashMap<Integer, Double> dataRec = data[0].getDataRecovered();
		
		// 1st series of data
		TimeSeries series1 = new TimeSeries(labelNames.get(data[0].getDataName()));
		for (Integer name: dataRec.keySet()) {
			if (dataRec.get(name) != -1) {
				series1.add(new Year(name), dataRec.get(name));
			}
		}
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		
		// 2nd series of data, if it exists
		TimeSeriesCollection dataset2 = new TimeSeriesCollection();
		if (data.length >= 2) {
			TimeSeries series2 = new TimeSeries(labelNames.get(data[1].getDataName()));
			dataRec = data[1].getDataRecovered();
			for (Integer name: dataRec.keySet()) {
				if (dataRec.get(name) != -1) {
					series2.add(new Year(name), dataRec.get(name));
				}
			}
			
			dataset2.addSeries(series2);
		}
		
		// 3rd series of data, if it exists
		if (data.length == 3) {

			TimeSeries series3 = new TimeSeries(labelNames.get(data[2].getDataName()));
			dataRec = data[2].getDataRecovered();
			for (Integer name: dataRec.keySet()) {
				if (dataRec.get(name) != -1) {
					series3.add(new Year(name), dataRec.get(name));
				}
			}
			dataset.addSeries(series3);
		}

		
		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
		XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

		String check = analysis.getClass().getSimpleName();
		
		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		if (check.equals("Analysis7")) {
			plot.setRangeAxis(new NumberAxis("US$"));
		}
		else {
			plot.setRangeAxis(new NumberAxis(""));
		}
		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		
		// if 2 or more series, add 2nd y-axis
		if (data.length >= 2) {
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, itemrenderer2);
			
			// check needed for special case
			if (check.equals("Analysis6")) {
				plot.setRangeAxis(1, new NumberAxis("US$"));
			}
			else {
				plot.setRangeAxis(1, new NumberAxis(""));
			}
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		}
		
		// create chart
		JFreeChart scatterChart = new JFreeChart(analysis.getSelect().getAnalysisType(),
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(500, 500));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		return chartPanel;
	}
}
