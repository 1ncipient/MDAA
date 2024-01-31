package viewer;

import java.util.HashMap;
import java.util.Map;
import analysis.AnalysisObject;
import analysis.DataObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Class allowing for line charts to be created
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public class ViewerType2 implements ViewerCreation{
	
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
	
	/**Method to create line chart viewers for display
	 * 
	 * @param analysis AnalysisObject object containing data to be graphed
	 * @return Returns line chart for display
	 */
	public ChartPanel createViewer(AnalysisObject analysis) {
		// initialize the label set
		fillLabels();
		XYSeriesCollection dataset = new XYSeriesCollection();
		String title = "";
		
		// add in data from AnalysisObject into dataset
		for (DataObject data : analysis.getData()) {
			Map<Integer,Double> map = data.getDataRecovered();
			XYSeries series = new XYSeries(labelNames.get(data.getDataName()));
			
			for (Map.Entry<Integer,Double> entry : map.entrySet()) {
				if (entry.getValue() != -1) {
					series.add(entry.getKey(), entry.getValue() );
				}
			}
			dataset.addSeries(series);
			title += labelNames.get(data.getDataName()) + " vs";
		}
		title = title.substring(0, title.length()-2); // remove last "vs"
		
		// create bar plot
		JFreeChart chart = ChartFactory.createXYLineChart(title, "Year", "", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		XYPlot plot = chart.getXYPlot();
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		
		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);
		
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		
		chart.getLegend().setFrame(BlockBorder.NONE);
		
		chart.setTitle(new TextTitle (title, new Font ("Serif", java.awt.Font.BOLD, 18))); //set title based on code
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension (500,500));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		chartPanel.setBackground(Color.white);
		
		return chartPanel;
	}
}
