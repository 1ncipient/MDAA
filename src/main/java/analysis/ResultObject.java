package analysis;

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
	public ResultObject(AnalysisObject analysisObj, Observer observer) {
		analysis = analysisObj;
		resultData = new String[10]; //tentative variable (type and length-wise not known)
		observers = new Observer(this, analysis);
	}
	
	/**
	 * Mutator method for resultData; invokes processData, helper method, and calls mutator method setResultData to set instance variable resultData.
	 * @param analysis
	 */
	public void setResult (AnalysisObject analysis) {
		String [] resultData = processData(analysis);
		setResultData(resultData);
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
	 * Helper method; parses data and formats it so to create an array that contains all vital information that the Observer needs.
	 * @param analysis parameter pulls information utilizing accessor methods and sets to respective elements in the array.
	 * @return
	 */
	private String [] processData (AnalysisObject analysis) {
		//formatted as ["analysis", "country", "startYear", "endYear", [0,0,0,0,0]
		resultData[0] = analysis.getAnalysisType();
		resultData[1] = analysis.getCountry();
		resultData[2] = Integer.toString(analysis.getStart());
		resultData[3] = Integer.toString(analysis.getStart());
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
