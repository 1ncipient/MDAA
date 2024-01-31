package reader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Represents the class that controls all API retrival and return of information
 * in the form of a HashMap with Year:Value pairs
 * 
 * @author Henry So
 *
 */
public class CreateData implements Search {
	
	/**
	 * Instance variable for the URL string, the data returned, and the parsed return data
	 */
	private String sendString;
	private JsonArray rawData;
	private HashMap<Integer, Double> filteredData;
	
	/**
	 * Constructor will initiate the instance variable for URL
	 * 
	 */
	public CreateData() {
		sendString = "";
	}

	/**
	 * Method control the flow of requesting and returning
	 * 
	 * @param country the desired country to search
	 * @param data the statistic name to search
	 * @param start the start year to search
	 * @param end the end year to search
	 * @return a HashMap containing all the data for a specific statistic
	 * 
	 */
	public HashMap<Integer, Double> readData(String country, String data, String start, String end) {
		// create the url request send string
		this.sendString = processSendString(country, data, start, end);
		
		// invoke HTTP GET request
		this.rawData = invokeAPI(this.sendString);
		
		// process data
		this.filteredData = new HashMap<Integer, Double>();
		
		double value = 0;

		int sizeOfResults = rawData.get(1).getAsJsonArray().size();
		int year;
		
		for (int i = 0; i < sizeOfResults; i++) {
			year = rawData.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			
			if (rawData.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()) {
				value = -1;
			}
			else {
				value = rawData.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();
			}
				
			filteredData.put(year, value);
			
		}

		return this.filteredData;
	}
	
	/**
	 * Method to construct the API request string
	 * 
	 * @param country the desired country to search
	 * @param data the statistic name to search
	 * @param start the start year to search
	 * @param end the end year to search
	 * @return a constructed API request string
	 * 
	 */
	public static String processSendString(String country, String data, String start, String end) {
		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, data, start, end);
		return urlString;
	}
	
	/**
	 * Method to submit the API request
	 * 
	 * @param send the well constructed API string
	 * @return a JsonArray containing all the retrieved data
	 * 
	 */
	public static JsonArray invokeAPI(String send) {
		JsonArray jsonArray = null;
		try {
			URL url = new URL(send);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				jsonArray = new JsonParser().parse(inline).getAsJsonArray();
			}
		} 
		catch (IOException e) {
			// force close the program if there's an issue with World Bank Data Repository
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "There was an error retriveing data! Application now terminating...", "Fatal error", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		return jsonArray;
	}
	
	/**
	 * Method to get the well constructed API string
	 * 
	 * @return the API string to be used
	 * 
	 */
	public String getSendString() {
		return this.sendString;
	}
	
	/**
	 * Method to send the well constructed API string
	 * 
	 * @param send the API string to be set
	 * 
	 */
	public void setSendString(String send) {
		this.sendString = send;
	}
	
}
