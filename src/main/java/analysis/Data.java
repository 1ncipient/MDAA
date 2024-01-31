package analysis;

import reader.CreateData;
import selection.SelectionObject;

/**
 * Represents the class retrieves all the required 
 * statistics for an analysis objects
 * 
 * @author Henry So
 *
 */
public class Data implements FindData {
	
	/**
	 * Instance variable that contains all DataObjects
	 */
	private DataObject[] dataObjArray;
	
	/**
	 * Constructor will initiate the array instance variable
	 * 
	 */
	public Data() {
		this.dataObjArray = null;
	}

	/**
	 * Method will retrieve all required data through an interface
	 * and will use it to construct a DataObject array to return
	 * 
	 * @param requiredStats a string array of the required stats to be retrieved
	 * @param select the SelectionObject which contains useful data
	 * @return a DataObject array containing all the newly constructed objects
	 * 
	 */
	public DataObject[] getData(String[] requiredStats, SelectionObject select) {
		String startYear = Integer.toString(select.getStart());
		String endYear = Integer.toString(select.getEnd());
		String country = select.getCountry();
		
		int totalData = requiredStats.length;
		
		CreateData callAPI = new CreateData();
		this.dataObjArray = new DataObject[totalData];
		
		for (int i = 0; i < requiredStats.length; i++) {
			DataObject data = new DataObject(requiredStats[i]);
			data.setDataRecovered(callAPI.readData(country, requiredStats[i], startYear, endYear));
			
			this.dataObjArray[i] = data;
		}
		
		return this.dataObjArray;

	}

}
