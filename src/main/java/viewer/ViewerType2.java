package viewer;

import java.util.HashMap;
import java.util.Map;
import analysis.AnalysisObject;
import analysis.DataObject;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ViewerType2 extends JFrame implements ViewerCreation{
	private static HashMap<String, String> labelNames = new HashMap<String, String>();
	private AnalysisObject analysisObject;
	
	
	public ChartPanel createViewer(AnalysisObject result) {
		fillLabels();
		XYSeriesCollection dataset = new XYSeriesCollection();
		String title = "";
		
		for (DataObject analysis : result.getData()) {
			Map<Integer,Double> map = analysis.getDataRecovered();
			XYSeries series = new XYSeries(labelNames.get(analysis.getDataName()));
			
			for (Map.Entry<Integer,Double> entry : map.entrySet()) {
				series.add(entry.getKey(), entry.getValue() );
			}
			dataset.addSeries(series);
			title += labelNames.get(analysis.getDataName()) + " vs";
		}
		title = title.substring(0, title.length()-2);
		
		
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
		chartPanel.setPreferredSize(new Dimension (400,300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		chartPanel.setBackground(Color.white);
		
		return chartPanel;
	}
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
}
