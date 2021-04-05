package viewer;

import analysis.AnalysisObject;
import analysis.ResultObject;

public class DisplayViewers extends Observer{
	
	private int[] viewers;
	
	public DisplayViewers(ResultObject result, AnalysisObject analysis) {
		super(result, analysis);
		viewers = this.getAnalysis().getViewers();
	}
	
	public void update() {
		this.setStatus(true);
		viewers = this.getAnalysis().getViewers();
		for (int i = 0; i < viewers.length; i++) {
		}
	}
	
	public int[] getViewers() {
		return viewers;
	}
	
	public void setViewers(int[] viewers) {
		this.viewers = viewers;
	}
	
	private void createViewer1() {
		
	}

	private void createViewer2() {
		
	}
	
	private void createViewer3() {
		
	}
	
	private void createViewer4() {
		
	}
	
	private void createViewer5() {
		
	}
	
	private void render() {
		
	}
}
