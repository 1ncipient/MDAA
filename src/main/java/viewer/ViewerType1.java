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


/**
 * Class allowing for pie charts to be created
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public class ViewerType1 implements ViewerCreation{

	/**Method to create pie chart viewers for display
	 * @param analysis AnalysisObject object containing data to be graphed
	 */
	public ChartPanel createViewer(AnalysisObject analysis) {
		DefaultPieDataset dataset = new DefaultPieDataset();			//create empty dataset to populate
		
		DataObject[] data = analysis.getData();							//array containing data from analysis

		String type = analysis.getAnalysisType();						//type of analysis used

		HashMap<Integer, Double> dataRecovered;							//hashmap containing data and year
		JFreeChart pieChart = null;										//initialize pie chart
		dataRecovered = data[0].getDataRecovered();
		double counter = 0;												//counter keeping track of sum of percentages
		if (type == "AG.LND.FRST.ZS") {									//first "if" handles forested area percentage
			for (Integer key: dataRecovered.keySet()) {					//for each year of data, add percentage to counter
				counter+=dataRecovered.get(key);
			}
			counter = counter/dataRecovered.size();						//after adding all percentages, divide by number of years
			dataset.setValue("Forested Area %", counter);
			dataset.setValue("Unforested Area %",  1-counter);			//unforested area is just 1-forested area
			pieChart = ChartFactory.createPieChart(analysis.getAnalysisType(), 
					dataset, true, true, false);
		}
		
		else if (type == "SE.XPD.TOTL.GD.ZS") {							//second "if" handles Education expenditure percentage
			for (Integer key: dataRecovered.keySet()) {					//same concept as above, but is for Education expenditure rather than forested area
				counter+=dataRecovered.get(key);
			}
			counter = counter/dataRecovered.size();
			dataset.setValue("Education Expenditure %", counter);
			dataset.setValue("Other Expenditure %",  1-counter);
			pieChart = ChartFactory.createPieChart(analysis.getAnalysisType(), 
					dataset, true, true, false);
		}
		ChartPanel chartPanel = new ChartPanel(pieChart);				//create and return chartpanel
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		return chartPanel;
	}
}
