package reader;

import java.util.HashMap;

/**
 * Test Reader class - utilized to test API invocation with the multiple World Bank Data Repo. tickers. 
 * @author Henry So
 *
 */
public class testReader {
	
	public static void main(String[] args) {
		//data indicators
		String[] data = {"EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS", 
				"NY.GDP.PCAP.CD", "SE.XPD.TOTL.GD.ZS", "SH.MED.BEDS.ZS", "SH.XPD.CHEX.GD.ZS", "SH.XPD.CHEX.PC.CD", "SP.DYN.IMRT.IN"};
		
		CreateData test = new CreateData();
		
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
			//test data
			HashMap<Integer, Double> dataRec = test.readData("can", data[i], "2000", "2003");
			
			for (Integer name: dataRec.keySet()) {
			    String key = name.toString();
			    String value = dataRec.get(name).toString();
			    System.out.println(key + " " + value);
			}
			System.out.println("");
			
		}
		
	}
	
}
