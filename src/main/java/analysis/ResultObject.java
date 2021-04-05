package analysis;

import java.util.HashMap;

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
	private Observer observers;
	
	/**
	 * Parameterized constructor, set instance variables
	 * @param analysisObj
	 * @param observer
	 */
	public ResultObject(AnalysisObject analysisObj) {
		analysis = analysisObj;
		observers = new DisplayViewers(analysis);
	}
	
	/**
	 * Mutator method for resultData; invokes processData, helper method, and calls mutator method setResultData to set instance variable resultData.
	 * @param analysis
	 */
	public void setResult (AnalysisObject analysis) {
		processData(analysis);
		notifyObserver();
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
	 * Helper method; parses data and formats it so to create an array that contains all vital information that the Observer needs.
	 * @param analysis parameter pulls information utilizing accessor methods and sets to respective elements in the array.
	 * @return
	 */
	private void processData (AnalysisObject analysis) {
		//formatted as ["analysis", "country", "startYear", "endYear", [0,0,0,0,0]
		DataObject[] process = analysis.getData();
		HashMap<Integer, Double> dataChange;
		if (analysis.getSelect().getAnalysisType().equals("Ratio of hospital beds (per 1 000) and current health expenditure (per 1 000)")) {
			for (int i = 0; i < process.length; i++) {
				if (process[i].getDataName().equals("SH.XPD.CHEX.PC.CD")) {
					dataChange = process[i].getDataRecovered();
					for (Integer name: dataChange.keySet()) {
						dataChange.replace(name, dataChange.get(name)*1000);
					}
				}
			}
		}
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
