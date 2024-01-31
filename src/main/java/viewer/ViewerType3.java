package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import analysis.AnalysisObject;
import analysis.DataObject;

/**
 * Class allowing for bar charts to be created
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public class ViewerType3 implements ViewerCreation{
	
	// dictionary matching labels used by World Bank database to labels that will be printed
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
	 * Function that calls appropriate function for bar chart creation based on number of data series to be charted
	 * (eg. 1-,2-,or 3-series data)
	 * 
	 * @param analysis AnalysisObject object containing data to be graphed
	 * @return Returns completed chart for display
	 */
	public ChartPanel createViewer(AnalysisObject analysis) {
		if (analysis.getData().length == 3) {
			return createTriple(analysis);
		}
		else if (analysis.getData().length > 1) {
			if (analysis.getClass().getSimpleName().equals("Analysis7")) {
				return createDouble(analysis);
			}
		}
		return createRegular(analysis);
	}

	/**
	 * Method to create bar chart for display
	 * @param analysis AnalysisObject object containing data to be graphed
	 * @return Returns bar chart for display
	 */
	private static ChartPanel createRegular(AnalysisObject analysis) {
		// initialize label set
		fillLabels();
		DataObject[] data = analysis.getData();
		HashMap<Integer, Double> dataRec = data[0].getDataRecovered();
		
		// number of data series to graph
		int count = analysis.getData().length;
		
		// 1 object
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Integer year: dataRec.keySet()) {
			if (dataRec.get(year) == -1) continue;
			else dataset.setValue(dataRec.get(year), labelNames.get(data[0].getDataName()), Integer.toString(year));
		}
		
		// 2 objects
		// add in second series data, with appropriate column names
		if (count >= 2) {
			dataRec = data[1].getDataRecovered();
			for (Integer year: dataRec.keySet()) {
				if (dataRec.get(year) == -1) continue;
				else dataset.setValue(dataRec.get(year), labelNames.get(data[1].getDataName()), Integer.toString(year));
			}
		}
		
		// initialize the plot
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		
		// create actual chart
		JFreeChart barChart = new JFreeChart(analysis.getSelect().getAnalysisType(),
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(500, 500));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		return chartPanel;
	}

	/**
	 * Method to create bar chart for display. Specific for graph with unusual axis scales
	 * @param analysis AnalysisObject object containing data to be graphed
	 * @return Returns bar chart for display
	 */
	private static ChartPanel createDouble(AnalysisObject analysis) {
		// initialize the label set
		fillLabels();
		DataObject[] data = analysis.getData();
		HashMap<Integer, Double> dataRec = data[0].getDataRecovered();
		
		// number of data series to graph
		int count = analysis.getData().length;
		
		// 1 object
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Integer year: dataRec.keySet()) {
			if (dataRec.get(year) == -1) continue;
			else dataset.setValue(dataRec.get(year), labelNames.get(data[0].getDataName()), Integer.toString(year));
		}
		
		// 2 objects
		// add in second series data, with appropriate column names
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		if (count >= 2) {
			dataRec = data[1].getDataRecovered();
			for (Integer year: dataRec.keySet()) {
				if (dataRec.get(year) == -1) continue;
				else dataset2.setValue(dataRec.get(year), labelNames.get(data[1].getDataName()), Integer.toString(year));
			}
		}
		
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();
		BarRenderer barrenderer2 = new BarRenderer();

		// set axis
		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis("US$"));

		plot.setDataset(1, dataset2);
		plot.setRenderer(1, barrenderer2);
		plot.setRangeAxis(1, new NumberAxis(""));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		// create chart
		JFreeChart barChart = new JFreeChart(analysis.getSelect().getAnalysisType(),
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(500, 500));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		return chartPanel;
	}
	
	/**
	 * Method to create bar chart for display. Specific for 3-series
	 * @param analysis AnalysisObject object containing data to be graphed
	 * @return Returns bar chart for display
	 */
	private static ChartPanel createTriple(AnalysisObject analysis) {
		// initialize the label set
		fillLabels();
		DataObject[] data = analysis.getData();
		HashMap<Integer, Double> dataRec = data[0].getDataRecovered();
		
		// number of data series to graph
		int count = analysis.getData().length;
		
		// 1 object
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Integer year: dataRec.keySet()) {
			if (dataRec.get(year) == -1) continue;
			else dataset.setValue(dataRec.get(year), labelNames.get(data[0].getDataName()), Integer.toString(year));
		}
		
		// 2nd object
		//add in second data series data into dataset, with appropriate column names
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		if (count >= 2) {
			dataRec = data[1].getDataRecovered();
			for (Integer year: dataRec.keySet()) {
				if (dataRec.get(year) == -1) continue;
				else dataset2.setValue(dataRec.get(year), labelNames.get(data[1].getDataName()), Integer.toString(year));
			}
		}
		
		// 3rd object
		//add in first data series data into dataset, with appropriate column names
		if (count == 3) {
			dataRec = data[2].getDataRecovered();
			for (Integer year: dataRec.keySet()) {
				if (dataRec.get(year) == -1) continue;
				else dataset.setValue(dataRec.get(year), labelNames.get(data[2].getDataName()), Integer.toString(year));
			}
		}
		
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();
		BarRenderer barrenderer2 = new BarRenderer();

		// set axis names
		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.setDataset(1, dataset2);
		plot.setRenderer(1, barrenderer2);
		plot.setRangeAxis(1, new NumberAxis(""));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		// create chart
		JFreeChart barChart = new JFreeChart(analysis.getSelect().getAnalysisType(),
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(500, 500));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		return chartPanel;
	}
}
