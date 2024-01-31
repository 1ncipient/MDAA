package frontEnd;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class represents the LoginDatabase that
 * controls all database functions
 * 
 * @author Henry So
 * 
 */
public class LoginDatabase implements CheckDatabase {
	
	/**
	 * Instance variable to store all username
	 * and password combos, instance variable
	 * to store the database filename
	 */
	private HashMap<String, String> userData;
	private String dataFilename = "Database";
	
	/**
	 * Constructor of the class will initialize the database
	 * and store all user/password combinations in the HashMap
	 * 
	 */
	public LoginDatabase() {
		userData = new HashMap<String, String>();
		
		String content = "";
		try {
			Scanner in = new Scanner(new FileReader(dataFilename));
			StringBuilder sb = new StringBuilder();
			while(in.hasNext()) {
			    sb.append(in.next());
			}
			in.close();
			content = sb.toString();	
		}
		// display error and terminate program if database error
		catch (FileNotFoundException e) {
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "The database is corrupted! Application now terminating...", "Fatal error", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		// loop through the database and store all combos in the hashmap
		String[] userPassCombo = content.split(",");
		for (int i = 0; i < userPassCombo.length; i += 2) {
			userData.put(userPassCombo[i], userPassCombo[i+1]);
		}
	}
	
	/**
	 * Method gets the matching username password combination
	 * 
	 * @param username the user's supplied username
	 * @return the registered password
	 * 
	 */
	public String loginCheck(String username) {
		return userData.get(username);
	}
}
