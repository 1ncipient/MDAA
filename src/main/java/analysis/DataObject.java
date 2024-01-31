package analysis;

import java.util.HashMap;

/**
 * Represents a data object that contains a data type name
 * and also a HashMap of the Year:Value pairs
 * 
 * @author Henry So
 *
 */
public class DataObject {
	
	/**
	 * Instance variable for the name and data stored
	 */
	private String dataName;
	private HashMap<Integer, Double> dataRecovered;
	
	/**
	 * Constructor will initiate the instance variables
	 * and create a new DataObject
	 * 
	 * @param name the name of the DataObject to be initialized
	 */
	public DataObject(String name) {
		this.dataName = name;
		this.dataRecovered = null;
	}

	/**
	 * Method to return the dataName associated
	 * 
	 * @return the objects dataName
	 * 
	 */
	public String getDataName() {
		return this.dataName;
	}
	
	/**
	 * Method to set the dataName associated
	 * 
	 * @param name the objects new dataName to be updated to
	 * 
	 */
	public void setDataName(String name) {
		this.dataName = name;
	}
	
	/**
	 * Method to return the data of the DataObject
	 * 
	 * @return the objects data HashMap
	 * 
	 */
	public HashMap<Integer, Double> getDataRecovered() {
		return this.dataRecovered;
	}
	
	/**
	 * Method to set the data of the DataObject
	 * 
	 * @param recovered the objects new data HashMap to be updated to
	 * 
	 */
	public void setDataRecovered(HashMap<Integer, Double> recovered) {
		this.dataRecovered = recovered;
	}
	
}
