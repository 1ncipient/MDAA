package analysis;

import java.util.HashMap;

import viewer.DisplayViewers;
import viewer.Observer;

/**
 * This class defines the ResultObject - parses data that can be utilized by the observers to convert into data that the viewer can use.
 * @author Henry So
 *
 */
public class ResultObject implements CreateResult {
	//instance variables
	private AnalysisObject analysis;
	private Observer observers;
	
	/**
	 * Parameterized constructor, set instance variables
	 * @param analysisObj the completed analysis object to be processed
	 */
	public ResultObject(AnalysisObject analysisObj) {
		analysis = analysisObj;
		observers = new DisplayViewers(analysis);
	}
	
	/**
	 * Mutator method for resultData; invokes processData, helper method, and calls mutator method setResultData to set instance variable resultData.
	 * @param analysis the analysis object to be processed
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
	
	/*
	 * Helper method; parses data and formats it so to create an array that contains all vital information that the Observer needs.
	 * @param analysis parameter pulls information utilizing accessor methods and sets to respective elements in the array.
	 * @return
	 */
	private void processData (AnalysisObject analysis) {
		String analysisName = analysis.getClass().getSimpleName();
		//formatted as ["analysis", "country", "startYear", "endYear", [0,0,0,0,0]
		DataObject[] process = analysis.getData();
		HashMap<Integer, Double> dataChange;
		if (analysisName.equals("Analysis6")) {
			for (int i = 0; i < process.length; i++) {
				if (process[i].getDataName().equals("SH.XPD.CHEX.PC.CD")) {
					dataChange = process[i].getDataRecovered();
					for (Integer name: dataChange.keySet()) {
						if (dataChange.get(name) != -1) {
							dataChange.replace(name, dataChange.get(name)*1000);
						}
					}
				}
			}
			analysis.setData(process);
		}
		
		else if (analysisName.equals("Analysis4")) {
			int counter = 0;
			double total = 0;
			dataChange = process[0].getDataRecovered();
			for (Integer name: dataChange.keySet()) {
				if (dataChange.get(name) != -1) {
						total += dataChange.get(name);
						counter++;
				}
			}
			dataChange.clear();
			dataChange.put(0, total/counter);
			analysis.setData(process);
		}
		
		else if (analysisName.equals("Analysis5")) {
			int counter = 0;
			double total = 0;
			dataChange = process[0].getDataRecovered();
			for (Integer name: dataChange.keySet()) {
				if (dataChange.get(name) != -1) {
						total += dataChange.get(name);
						counter++;
				}
			}
			dataChange.clear();
			dataChange.put(0, total/counter);
			analysis.setData(process);
		}
		
		else if (analysisName.equals("Analysis3")) {
            int CO2Pos = 0;
            int GDPPos = 1;
            if (process[1].getDataName().equals("EN.ATM.CO2E.PC")) {
                CO2Pos = 1;
                GDPPos = 0;
            }

            HashMap<Integer, Double> newData = new HashMap<Integer, Double>();
            HashMap<Integer, Double> CO2 = process[CO2Pos].getDataRecovered();
            HashMap<Integer, Double> GDP = process[GDPPos].getDataRecovered();

            for (Integer name: CO2.keySet()) {
                if (CO2.get(name) == -1 || GDP.get(name) == -1) {
                    newData.put(name, Double.valueOf("-1"));
                }
                else {
                    newData.put(name, CO2.get(name) / GDP.get(name));
                }
            }

            DataObject temp = new DataObject("RATIO");
            temp.setDataRecovered(newData);
            DataObject[] tempHolder = {temp};

            analysis.setData(tempHolder);
        }
	}
	
	/*
	 * Helper method; invokes observers.update(), lets Observer know that processes are complete and ready to update.
	 */
	private void notifyObserver() {
		observers.update();
	}
	
	/*
	 * Helper method. Sets observer.
	 * @param observers
	 */
	private void setObserver (Observer observers) {
		this.observers = observers;
	}
}
