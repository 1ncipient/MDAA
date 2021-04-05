package analysis;

import viewer.DisplayViewers;
import viewer.Observer;

/**
 * This class defines the ResultObject - parses data that can be utilized by the observers to convert into data that the viewer can use.
 * @author Henry So, Jacob Chun, Samuel Su, Yan Qing Niu
 *
 */
public class ResultObject {
	//instance variables
	private AnalysisObject analysis;
	private String [] resultData;
	private Observer observers;
	
	/**
	 * Parameterized constructor, set instance variables
	 * @param analysisObj
	 * @param observer
	 */
	public ResultObject(AnalysisObject analysisObj) {
		analysis = analysisObj;
		resultData = new String[10]; //tentative variable (type and length-wise not known)
		observers = new DisplayViewers(analysis);
	}
	
	/**
	 * Mutator method for resultData; invokes processData, helper method, and calls mutator method setResultData to set instance variable resultData.
	 * @param analysis
	 */
	public void setResult (AnalysisObject analysis) {
		notifyObserver();
	}
	
	/**
	 * Mutator method for resultData
	 * @param resultData parameter set to the variable referenced by this object
	 */
	public void setResultData (String [] resultData) {
		this.resultData = resultData;
	}
	
	/**
	 * Accessor method for resultData variable.
	 * @return resultData, String array type
	 */
	public String [] getResultData() {
		return resultData;
	}
	
	/**
	 * Accessor method for analysis variable
	 * @return analysis, AnalysisObject type
	 */
	public AnalysisObject getAnalysis() {
		return analysis;
	}
	
	/**
	 * Mutator method for analysis variable
	 * @param analysis parameter used to set to the variable referenced by this object.
	 */
	public void setAnalysis (AnalysisObject analysis) {
		this.analysis = analysis;
	}
	
	
	/**
	 * Helper method; invokes observers.update(), lets Observer know that processes are complete and ready to update.
	 */
	private void notifyObserver() {
		observers.update();
	}
	
	/**
	 * Helper method. Sets observer.
	 * @param observers
	 */
	private void setObserver (Observer observers) {
		this.observers = observers;
	}
}
