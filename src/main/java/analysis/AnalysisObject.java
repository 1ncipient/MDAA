package analysis;

import java.util.HashMap;

import selection.SelectionObject;


/**
 * Holds user selections, in the form of a SelectionObject, and contains data that will be received from the API.
 * @author Henry So
 *
 */
public class AnalysisObject implements Calculate{
	private SelectionObject select;					//SelectionObject containing information from user
	private DataObject[] data;								//data from API
	
	
	
	/**
	 * Setter method for start year in SelectionObject
	 * @param start New integer for start year in SelectionObject
	 */
	public void setStart(int start) {
		select.setStart(start);
	}
	
	/**
	 * Setter method for end year in SelectionObject
	 * @param end New integer for end year in SelectionObject
	 */
	public void setEnd(int end) {
		select.setEnd(end);
	}
	
	/**
	 * Setter method for country variable in SelectionObject
	 * @param country New String value for country in SelectionObject
	 */
	public void setCountry(String country) {
		select.setCountry(country);
	}
	
	/**
	 * Setter method for analysisType string in SelectionObject
	 * @param analysisType New String value for analysisType in SelectionObject
	 */
	public void setAnalysisType(String analysisType) {
		select.setAnalysisType(analysisType);
	}
	
	/**
	 * Setter method for data
	 * @param data New array for data
	 */
	public void setData(DataObject[] data) {
		this.data = data;
	}
	
	/**
	 * Setter method for viewers in SelectionObject
	 * @param viewers New array of viewers in SelectionObject
	 */
	public void setViewers(int[] viewers) {
		select.setViewers(viewers);
	}
	
	/**
	 * Setter method for select
	 * @param select New SelectionObject
	 */
	public void setSelect(SelectionObject select) {
		this.select = select;
	}
	
	/**
	 * Getter method that returns starting year from SelectionObject
	 * @return Start year
	 */
	public int getStart() {
		return select.getStart();
	}
	
	/**
	 * Getter method that returns ending year
	 * @return End year
	 */
	public int getEnd() {
		return select.getEnd();
	}
	
	/**
	 * Getter method that returns country of current SelectionObject
	 * @return String representing country
	 */
	public String getCountry() {
		return select.getCountry();
	}
	
	/**
	 * Getter method that returns the type of analysis from SelectionObject
	 * @return String representing analysisType
	 */
	public String getAnalysisType() {
		return select.getAnalysisType();
	}
	
	/**
	 * Getter method that returns array of data obtained from API
	 * @return String array
	 */
	public DataObject[] getData() {
		return data;
	}
	
	/**
	 * Inherited method from Calculate interface. Sets select to a new SelectionObject
	 * @param select New SelectionObject for current AnalysisObject
	 */
	public void calculate(selection.SelectionObject select) {
		this.select = select;	
	}
	
	/**
	 * Getter method that returns array containing currently used viewers from SelectionObject
	 * @return Array of int
	 */
	public int[] getViewers() {
		return select.getViewers();
	}
	
	/**
	 * Getter method that returns currently stored SelectionObject
	 * @return SelectionObject
	 */
	public SelectionObject getSelect() {
		return select;
	}
	
	/**
	 * Helper method to check if the DataObject array has valid entries
	 * @param dataArr the DataObject array to be checked for data
	 * @return true of false indicating whether there exists data
	 */
	public static boolean hasData(DataObject[] dataArr) {
		boolean valueExists = false;
		
		for (DataObject element : dataArr) {
			HashMap<Integer, Double> tempMap = element.getDataRecovered();
			
			for (Integer name: tempMap.keySet()) {
			    double value = tempMap.get(name);
			    if (value != -1) valueExists = true;
			}
			if (!valueExists) return false;
			valueExists = false;
		}
		
		return true;
	}
}
