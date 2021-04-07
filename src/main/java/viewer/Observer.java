package viewer;

import analysis.AnalysisObject;


/**
 * Parent observer class. Notified by ResultObject when that is finished.
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public class Observer implements SendUpdate {
	
	private AnalysisObject analysis;	//holds current AnalysisObject
	private boolean status;				//flag for whether result/analysis have been updated or not
	
	
	
	/**
	 * Constructor for Observer class.
	 * @param analysis New AnalysisObject for Observer
	 */
	public Observer(AnalysisObject analysis) {
		this.analysis = analysis;
		status = false;
	}
	
	/**
	 * Setter method for analysis.
	 * @param analysis New AnalysisObject for current object
	 */
	public void setAnalysis(AnalysisObject analysis) {
		this.analysis = analysis;
	}
	
	
	/**
	 * Getter method that returns AnalysisObject.
	 * @return Returns stored AnalysisObject
	 */
	public AnalysisObject getAnalysis() {
		return analysis;
	}
	
	/**
	 * Sets status flag to true to indicate start of process.
	 */
	public void update() {
		status = true;
	}
	
	/**
	 * Setter method for status flag.
	 * @param status The new status.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * Getter method that returns status flag
	 * @return Current status flag, in the form of a boolean.
	 */
	public boolean getStatus() {
		return status;
	}

}
