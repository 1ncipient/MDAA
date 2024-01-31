package selection;


/**
 * Contains all selected values to be sent to ComputeServer
 * @author Henry
 *
 */
public class SelectionObject {
	private int start;					//start year
	private int end;					//end year
	private String country;				//country selected
	private String analysisType;		//type of analysis chosen
	private int[] viewers;				//array of viewers chosen
	
	/**
	 * constructor for SelectionObject
	 */
	public SelectionObject() {
		
	}
	
	/**
	 * Setter method for start year
	 * @param start New integer for start year
	 */
	public void setStart(int start) {
		this.start = start;
	}
	
	/**
	 * Setter method for end year
	 * @param end New integer for end year
	 */
	public void setEnd(int end) {
		this.end = end;
	}
	
	/**
	 * Setter method for country variable
	 * @param country New String value for country
	 */
	public void setCountry(String country) {
		this.country = new String(country);
	}
	
	/**
	 * Setter method for analysisType string
	 * @param analysisType New String value for analysisType
	 */
	public void setAnalysisType(String analysisType) {
		this.analysisType = new String(analysisType);
	}
	
	/**
	 * Setter method for viewers
	 * @param viewers New int array representing viewers
	 */
	public void setViewers(int[] viewers) {
		this.viewers = viewers;
	}
	
	/**
	 * Getter method that returns starting year
	 * @return int of start year
	 */
	public int getStart() {
		return start;
	}
	
	/**
	 * Getter method that returns ending year
	 * @return int of end year
	 */
	public int getEnd() {
		return end;
	}
	
	/**
	 * Getter method that returns country of current SelectionObject
	 * @return String representing country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * Getter method that returns type of analysis
	 * @return String representing analysisType
	 */
	public String getAnalysisType() {
		return analysisType;
	}
	
	/**
	 * Getter method that returns array containing currently used viewers
	 * @return Array of integers
	 */
	public int[] getViewers() {
		return viewers;
	}

}