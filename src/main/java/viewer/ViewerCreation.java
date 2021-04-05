package viewer;

import javax.swing.JComponent;

import analysis.AnalysisObject;

public interface ViewerCreation {
	
	public JComponent createViewer(AnalysisObject analysis);

}
