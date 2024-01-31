package viewer;

import javax.swing.JComponent;

import analysis.AnalysisObject;

/**
 * Defines the interface implemented by the various ViewerType classes, for creation of charts.
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public interface ViewerCreation {
	
	public JComponent createViewer(AnalysisObject analysis);

}
