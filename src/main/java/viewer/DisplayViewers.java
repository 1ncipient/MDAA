package viewer;

import javax.swing.JComponent;

import analysis.AnalysisObject;
import frontEnd.MainUI;

public class DisplayViewers extends Observer{
	
	private int[] viewers;
	private JComponent[] viewerObjects;
	
	public DisplayViewers(AnalysisObject analysis) {
		super(analysis);
		viewers = this.getAnalysis().getViewers();
		viewerObjects = new JComponent[5];
	}
	
	public void update() {
		this.setStatus(true);
		viewers = this.getAnalysis().getViewers();
		if (viewers[0] == 1) {
			viewerObjects[0] = createViewer1();
		}
		if (viewers[1] == 1) {
			viewerObjects[1] = createViewer2();
		}
		if (viewers[2] == 1) {
			viewerObjects[2] = createViewer3();
		}
		if (viewers[3] == 1) {
			viewerObjects[3] = createViewer4();
		}
		if (viewers[4] == 1) {
			viewerObjects[4] = createViewer5();
		}
		
		render();
	}
	
	
	public int[] getViewers() {
		return viewers;
	}
	
	public void setViewers(int[] viewers) {
		this.viewers = viewers;
	}
	
	private JComponent createViewer1() {
		ViewerType1 view = new ViewerType1();
		return view.createViewer(this.getAnalysis());
	}

	private JComponent createViewer2() {
		ViewerType2 view = new ViewerType2();
		return view.createViewer(this.getAnalysis());
	}
	
	private JComponent createViewer3() {
		ViewerType3 view = new ViewerType3();
		return view.createViewer(this.getAnalysis());
	}
	
	private JComponent createViewer4() {
		ViewerType4 view = new ViewerType4();
		return view.createViewer(this.getAnalysis());
	}
	
	private JComponent createViewer5() {
		ViewerType5 view = new ViewerType5();
		return view.createViewer(this.getAnalysis());
	}
	
	private void render() {
		System.out.println("test");
		for(int i = 0; i < 5; i++) {
			if (viewerObjects[i] != null) {
				MainUI.west.add(viewerObjects[i]);
			}
		}
	}
}
