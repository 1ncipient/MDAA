package viewer;

import javax.swing.JComponent;

import analysis.AnalysisObject;
import frontEnd.MainUI;

/**
 * Class for calling appropriate functions for display of viewers
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public class DisplayViewers extends Observer{
	
	private int[] viewers;								//array containing which viewers are to be made (0=no, 1=yes)
	private JComponent[] viewerObjects;					//array of finished viewer objects
	
	
	/**
	 * Constructor for DisplayViewer class
	 * @param analysis AnalysisObject object containing data to be displayed
	 */
	public DisplayViewers(AnalysisObject analysis) {
		super(analysis);
		viewers = this.getAnalysis().getViewers();
		viewerObjects = new JComponent[5];
	}
	
	/**
	 * Function to read through viewers array, create requested viewers, and display them
	 */
	public void update() {
		this.setStatus(true);
		viewers = this.getAnalysis().getViewers();
		if (viewers[0] == 1) {
			viewerObjects[0] = createViewer1();
		}
		if (viewers[1] == 1) {
			viewerObjects[1] = createViewer2();
		}
		if (viewers[2] == 1) {
			viewerObjects[2] = createViewer3();
		}
		if (viewers[3] == 1) {
			viewerObjects[3] = createViewer4();
		}
		if (viewers[4] == 1) {
			viewerObjects[4] = createViewer5();
		}
		
		render();
	}
	
	/**
	 * Getter function for viewers array
	 * @return Array of viewer statuses
	 */
	public int[] getViewers() {
		return viewers;
	}
	
	/**
	 * Setter method for viewers array
	 * @param viewers New array for viewers
	 */
	public void setViewers(int[] viewers) {
		this.viewers = viewers;
	}
	
	/**
	 * Private method for creation of pie chart
	 * @return Returns completed pie chart
	 */
	private JComponent createViewer1() {
		ViewerType1 view = new ViewerType1();
		return view.createViewer(this.getAnalysis());
	}

	/**
	 * Private method for creation of line chart
	 * @return Returns completed line chart
	 */
	private JComponent createViewer2() {
		ViewerType2 view = new ViewerType2();
		return view.createViewer(this.getAnalysis());
	}
	
	/**
	 * Private method for creation of bar chart
	 * @return Returns completed bar chart
	 */
	private JComponent createViewer3() {
		ViewerType3 view = new ViewerType3();
		return view.createViewer(this.getAnalysis());
	}
	
	/**
	 * Private method for creation of scatter chart
	 * @return Returns completed scatter chart
	 */
	private JComponent createViewer4() {
		ViewerType4 view = new ViewerType4();
		return view.createViewer(this.getAnalysis());
	}
	
	/**
	 * Private method for creation of report viewer
	 * @return Returns completed text box
	 */
	private JComponent createViewer5() {
		ViewerType5 view = new ViewerType5();
		return view.createViewer(this.getAnalysis());
	}
	
	/**
	 * Private method which sends completed viewers to the MainUI
	 */
	private void render() {
		for(int i = 0; i < 5; i++) {
			if (viewerObjects[i] != null) {
				MainUI.west.add(viewerObjects[i]);
			}
		}
	}
}
