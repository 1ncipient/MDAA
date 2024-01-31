import javax.swing.JFrame;

import frontEnd.LoginService;

/**
 * This class represents the starting point to use the McBal Demographic Analysis System
 * 
 * @author Henry So
 *
 */
public class InitializeProgram {

	/**
	 * Main method initializes the McBal Demographic Analysis System
	 * 
	 * @param args no additional arguments needed
	 */
	public static void main(String[] args) {

		JFrame frame = LoginService.getInstance();
		frame.setSize(350, 140);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
