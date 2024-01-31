package analysis;

import selection.SelectionObject;

/**
 * This class defines the Computation Server; responsible for handling business logic associated with the analysis subsystem. Recieves a selection object and parses it for all necessary information.
 * Utilizes extracted information to create AnalysisCreator(1..8) objects. Utilizes factory design pattern.
 * @author Henry So
 *
 */
public class ComputeServer implements Recalculate{
	//instance variables
	private SelectionObject select;
	private Creator create;
	
	/**
	 * Parameterized constructor. Sets instance variable create through setter.
	 * @param create a creator object
	 */
	public ComputeServer(Creator create) {
		this.setCreator(create);
	}
	
	/*
	 * Helper method; Mutator method for instance variable create is set with the parameter passed in
	 * @param create - Creator object that is set to instance variable create
	 */
	private void setCreator(Creator create) {
		this.create = create;
	}
	
	/**
	 * Mutator method and invocation method; sets variable select and invokes method execute().
	 * @param select Selection object set to instance variable select
	 */
	public void	doThis (SelectionObject select) {
		this.select = select;
		this.execute();
	}
	
	/*
	 * Helper method; invokes instance variable create's create method, passing in the SelectionObject select instance variable to create the AnalysisCreators(1..8)
	 */
	private void execute() {
		create.create(select);
	}
}
