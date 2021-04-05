package analysis;

import selection.SelectionObject;

public class ComputeServer implements Recalculate{
	private SelectionObject select;
	private Creator create;
	
	public ComputeServer(Creator create) {
		this.setCreator(create);
	}
	
	private void setCreator(Creator create) {
		this.create = create;
	}
	
	public void	doThis (SelectionObject select) {
		this.select = select;
		this.execute();
	}
	
	private void execute() {
		create.create(select);
	}
}
