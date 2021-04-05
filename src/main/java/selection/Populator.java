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

public class Populator implements Populate {
    private SelectionObject select;
    private ComputeServer compute;
    
    public Populator() {
    	select = new SelectionObject();
    }
    
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
    
    public void setSelectionType (int[] type, int value) {
    	select.setViewers(type);
    }
    
}
