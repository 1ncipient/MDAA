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

public class ViewerType3 implements ViewerCreation{
	
	private static HashMap<String, String> labelNames = new HashMap<String, String>();
	
	private static void fillLabels() {
		labelNames.put("RATIO", "CO2 emissions to GDP per capita (US$) ratio");
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
	
	public ChartPanel createViewer(AnalysisObject analysis) {
		if (analysis.getData().length == 3) {
			return createTriple(analysis);
		}
		else if (analysis.getData().length > 1) {
			if (analysis.getData()[1].getDataName() == "SP.DYN.IMRT.IN") {
				return createDouble(analysis);
			}
		}
		return createRegular(analysis);
	}

	public ChartPanel createRegular(AnalysisObject analysis) {
		DataObject[] data = analysis.getData();
		HashMap<Integer, Double> dataRec = data[0].getDataRecovered();
		fillLabels();
		
		int count = analysis.getData().length;
		
		// 1 object
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Integer year: dataRec.keySet()) {
			if (dataRec.get(year) == -1) continue;
			else dataset.setValue(dataRec.get(year), labelNames.get(data[0].getDataName()), Integer.toString(year));
		}
		
		// 2 objects
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
		BarRenderer barrenderer2 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		
		
		// 3 objects
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		if (count == 3) {
			dataRec = data[2].getDataRecovered();
			for (Integer year: dataRec.keySet()) {
				if (dataRec.get(year) == -1) continue;
				else dataset2.setValue(dataRec.get(year), labelNames.get(data[2].getDataName()), Integer.toString(year));
			}
			
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis(""));
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		}


		JFreeChart barChart = new JFreeChart(analysis.getSelect().getAnalysisType(),
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		return chartPanel;
	}

	public ChartPanel createDouble(AnalysisObject analysis) {
		DataObject[] data = analysis.getData();
		HashMap<Integer, Double> dataRec = data[0].getDataRecovered();
		fillLabels();
		
		int count = analysis.getData().length;
		
		// 1 object
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Integer year: dataRec.keySet()) {
			if (dataRec.get(year) == -1) continue;
			else dataset.setValue(dataRec.get(year), labelNames.get(data[0].getDataName()), Integer.toString(year));
		}
		
		// 2 objects
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


		JFreeChart barChart = new JFreeChart(analysis.getSelect().getAnalysisType(),
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		return chartPanel;
	}
	
	public ChartPanel createTriple(AnalysisObject analysis) {
		DataObject[] data = analysis.getData();
		HashMap<Integer, Double> dataRec = data[0].getDataRecovered();
		fillLabels();
		
		int count = analysis.getData().length;
		
		// 1 object
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Integer year: dataRec.keySet()) {
			if (dataRec.get(year) == -1) continue;
			else dataset.setValue(dataRec.get(year), labelNames.get(data[0].getDataName()), Integer.toString(year));
		}
		
		// 2 objects
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		if (count >= 2) {
			dataRec = data[1].getDataRecovered();
			for (Integer year: dataRec.keySet()) {
				if (dataRec.get(year) == -1) continue;
				else dataset2.setValue(dataRec.get(year), labelNames.get(data[1].getDataName()), Integer.toString(year));
			}
		}
		
		// 3 objects
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


		JFreeChart barChart = new JFreeChart(analysis.getSelect().getAnalysisType(),
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		
		return chartPanel;
	}
}
