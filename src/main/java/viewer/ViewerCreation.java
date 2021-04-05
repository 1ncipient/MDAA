package viewer;

import org.jfree.chart.ChartPanel;

import analysis.AnalysisObject;

public interface ViewerCreation {
	
	public ChartPanel createViewer(AnalysisObject analysis);

}
