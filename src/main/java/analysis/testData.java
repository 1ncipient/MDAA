package analysis;

import java.util.HashMap;

import selection.SelectionObject;
/**
 * Class for testing behavior amongst classes and API invocation.
 * @author Henry So
 *
 */
public class testData {

	public static void main(String[] args) {
//		String[] data = {"EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3", "AG.LND.FRST.ZS", 
//				"NY.GDP.PCAP.CD", "SE.XPD.TOTL.GD.ZS", "SH.MED.BEDS.ZS", "SH.XPD.CHEX.GD.ZS", "SH.XPD.CHEX.PC.CD", "SP.DYN.IMRT.IN"};
		
		String[] data = {"EN.ATM.CO2E.PC", "EG.USE.PCAP.KG.OE", "EN.ATM.PM25.MC.M3"};
		
		Data test = new Data();
		SelectionObject select = new SelectionObject();
		select.setStart(2000);
		select.setEnd(2004);
		select.setCountry("can");
		
		DataObject[] array = test.getData(data, select);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i].getDataName());
			HashMap<Integer, Double> dataRec = array[i].getDataRecovered();
			
			for (Integer name: dataRec.keySet()) {
			    String key = name.toString();
			    String value = dataRec.get(name).toString();
			    System.out.println(key + " " + value);
			}
			System.out.println("");
		}
	}
}
