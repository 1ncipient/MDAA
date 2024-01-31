package frontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 * This class represents the login UI that the user interacts with which implements LoginCreds
 * 
 * @author Henry So
 *
 */
public class LoginService extends JFrame implements LoginCreds {
	
	/**
	 * Instance variables representing the unique singleton instance, 
	 * the JPanel, username textfield, password textfield, and a
	 * valid boolean
	 */
	private static LoginService uniqueInstance;
	private JPanel panel;
	private JTextField username;
	private JPasswordField password;
	private boolean valid;

	/**
	 * Method implements the singleton design pattern
	 * to return a unique instance
	 * @return the unique instance of LoginService
	 */
	public static LoginService getInstance() {
	
		if (uniqueInstance == null)
			uniqueInstance = new LoginService();

		return uniqueInstance;
	
	}

	/*
	 * Constructor of the class will initialize the GUI and begin
	 * the login process
	 * 
	 */
	private LoginService() {

		// Set window title
		super("Login");
		
		// Initialize the panel
		panel = new JPanel();

		// Set up the username label with padding
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setBounds(10, 20, 80, 25);
		panel.add(usernameLabel);
		
		// Set up the username textbox
		username = new JTextField(20);
		username.setBounds(100, 20, 165, 20);
		panel.add(username);
		
		// Set up the password label with padding
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(100, 50, 165, 20);
		panel.add(passwordLabel);
		
		// Set up the password protected textbox
		password = new JPasswordField(20);
		password.setBounds(100, 20, 165, 25);
		panel.add(password);
		
		// Set up the submit button
		JButton submit = new JButton("Submit!");
		submit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        login(username.getText(), String.valueOf(password.getPassword()));
		    }
		});
		panel.add(submit);

		getRootPane().setDefaultButton(submit);
		getContentPane().add(panel);
		
	}
	
	/**
	 * Method initializes the login process when the user presses submit
	 * 
	 * @param username the user's supplied username
	 * @param password the user's supplied password
	 * 
	 */
	public void login(String username, String password) {
		
		VerificationServer verifyLogin = new VerificationServer();
		valid = verifyLogin.checkPin(username, password);
		
		if (valid) {
			JFrame frame = LoginService.getInstance();
			frame.setVisible(false);
			frame.dispose();
			MainUI launch = new MainUI();
			launch.launchMainUI();
		}
		else {
			JOptionPane.showMessageDialog(panel, "Invalid username/password combination!", "Please try again", JOptionPane.WARNING_MESSAGE);
		}	
	}
	
}
