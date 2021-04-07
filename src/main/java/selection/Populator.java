package selection;

import analysis.AnalysisCreator1;
import analysis.AnalysisCreator2;
import analysis.AnalysisCreator3;
import analysis.AnalysisCreator4;
import analysis.AnalysisCreator5;
import analysis.AnalysisCreator6;
import analysis.AnalysisCreator7;
import analysis.AnalysisCreator8;
import analysis.ComputeServer;

/**
 * Populator class. Implements Populate interface. This class purpose is to serve as a populator for the SelectionObject based on the user's selections.
 * @author Henry
 *
 */
public class Populator implements Populate {
	//instance variables
    private SelectionObject select;
    private ComputeServer compute;
    
    /**
     * Default constructor. Sets instance variable select.
     */
    public Populator() {
    	select = new SelectionObject();
    }
    
    /**
     * Mutator method; based on the user's selection type, when selecting the start year, end year, analysis type or country, the mutator invokes other mutator methods to set the appropriate
     * instance variable for the SelectionObject select.
     * @param type denotes the type of selection (analysis, country, startYr, endYr)
     * @param value denotes the value to differentiate the selection.
     */
    public void setSelectionType (String type, int value){
    	
        if (type.equals("startYr")){
            select.setStart(value);
        }
        else if (type.equals("endYr")){
            select.setEnd(value);
        }
        else if (value == 10){
            select.setAnalysisType(type);
        }
        else if (value == 15) {
        	select.setCountry(type);
        }
        else if (type.equals("finished")) {
        	//switch statements; creates a new ComputeServer object based on value.
        	switch (value) {
        	case 0:
        		compute = new ComputeServer(new AnalysisCreator1());
        		break;
        	case 1:
        		compute = new ComputeServer(new AnalysisCreator2());
        		break;
        	case 2:
        		compute = new ComputeServer(new AnalysisCreator3());
        		break;
        	case 3:
        		compute = new ComputeServer(new AnalysisCreator4());
        		break;
        	case 4:
        		compute = new ComputeServer(new AnalysisCreator5());
        		break;
        	case 5:
        		compute = new ComputeServer(new AnalysisCreator6());
        		break;
        	case 6:
        		compute = new ComputeServer(new AnalysisCreator7());
        		break;
        	default:
        		compute = new ComputeServer(new AnalysisCreator8());
        	}
        	compute.doThis(select);
        }
    }
    
    /**
     * An integer array is passed in: The elements (if index has value 1, use this viewer) in the type array will denote which viewers that will be used when Recalculate is pressed by the User in the mainUI. 
     * @param type denotes viewers to create graphical representations of the SelectionObject
     * @param value the integer value denoting selection option number
     */
    public void setSelectionType (int[] type, int value) {
    	select.setViewers(type);
    }
    
}
