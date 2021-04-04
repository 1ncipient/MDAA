package selection;

public class Populator implements Populate {
    private SelectionObject select;
    
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
        else if (type.equals("finished")) {
        	ComputeServer compute = new ComputeServer();
        	compute.doThis(select);
        }
    }
    
    public void setSelectionType (int[] type, int value) {
    	select.setViewers(type);
    }
    
}
