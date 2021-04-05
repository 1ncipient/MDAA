package viewer;

import analysis.AnalysisObject;
import analysis.ResultObject;


/**
 * Parent observer function.
 * @author Yan Qing Niu
 *
 */
public class Observer implements SendUpdate {
	
	private ResultObject result;		//holds current ResultObject
	private AnalysisObject analysis;	//holds current AnalysisObject
	private boolean status;				//flag for whether result/analysis have been updated or not
	
	
	
	/**
	 * Constructor for Observer class.
	 * @param result New ResultObject for Observer
	 * @param analysis New ObserverObject for Observer
	 */
	public Observer(ResultObject result, AnalysisObject analysis) {
		this.result = result;
		this.analysis = analysis;
		status = false;
	}
	
	/**
	 * Setter method for result.
	 * @param result New ResultObject for current object
	 */
	public void setResult(ResultObject result) {
		this.result = result;
	}
	/**
	 * Setter method for analysis.
	 * @param result New AnalysisObject for current object
	 */
	public void setAnalysis(AnalysisObject analysis) {
		this.analysis = analysis;
	}
	
	/**
	 * Getter method that returns ResultObject.
	 * @return Returns stored ResultObject
	 */
	public ResultObject getResult() {
		return result;
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
	 * @param result The new status.
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
