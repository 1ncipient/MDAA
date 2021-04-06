package frontEnd;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import selection.Populator;

public class MainUI extends JFrame implements Launch, ActionListener{

     // instance variables
	private static final long serialVersionUID = 1L;
	
	public static JPanel west;
	
    private JComboBox<String> countriesList;
    private JComboBox<String> fromList;
    private JComboBox<String> toList;
    private JComboBox<String> viewsList;
    private JComboBox<String> methodsList;
    private Vector<String> methodsNames;
    private Vector<String> viewsNames;
    private Vector<String> countriesNames;
    private JButton recalculate;
    private JButton addView;
    private JButton removeView;
    private int[] startYears;
    private int[] endYears;
    private int[] viewerList;
    private String previousAnalysis;
    private Populator populator;
    private static String countryFile = "country_list_populator.csv";
    private static TreeMap<String, String> countryMap;
    private static boolean allPass = false;
    private static String bannedCountryFile = "country_list_restrictor.csv";
    private static ArrayList<String> bannedCountries;
    private static JFrame frame;
    
    /**
    Constructor
    */
	public MainUI() {
		// Set window title
		super("Country Statistics");
		populator = new Populator();
		// initially set the analysis type and country abbreviation in case they do not want to change this selection
		populator.setSelectionType("CO2 Emissions vs Energy Use vs PM2.5 Air Pollution", 10);
		populator.setSelectionType("afg", 15);
		
		// initialize all the restrictions
		startYears = new int[] {1990, 1990, 1962, 1990, 1970, 1962, 2000, 1970};
		endYears = new int[] {2015, 2017, 2016, 2018, 2019, 2018, 2018, 2018};
		viewerList = new int[] {0,0,0,0,0};
		bannedCountries = initBannedCountries();
		
		// initialize all the countries and their abbreviations
		countryMap = loadcountryMap();
		
		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		countriesNames = new Vector<String>();
		for (String name: countryMap.keySet()) {
			countriesNames.add(name);
		}
		countriesNames.sort(null);
		countriesList = new JComboBox<String>(countriesNames);

        countriesList.addActionListener(this);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		for (int i = 2021; i >= 1962; i--) {
			years.add("" + i);
		}
		fromList = new JComboBox<String>(years);
		toList = new JComboBox<String>(years);
        fromList.addActionListener(this);
        toList.addActionListener(this);
        
		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		recalculate = new JButton("Recalculate");
        recalculate.addActionListener(this);

		JLabel viewsLabel = new JLabel("Available Views: ");

		viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		
		viewsList = new JComboBox<String>(viewsNames);
		addView = new JButton("+");
		removeView = new JButton("-");

        viewsList.addActionListener(this);
        addView.addActionListener(this);
        removeView.addActionListener(this);

		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		methodsNames = new Vector<String>();
		methodsNames.add("CO2 Emissions vs Energy Use vs PM2.5 Air Pollution");
		methodsNames.add("PM 2.5 Air Pollution vs Forest Area");
		methodsNames.add("Ratio of CO2 Emissions and GDP per Capita (current US$)");
		methodsNames.add("Average Forest Area (% of land) for selected years");
        methodsNames.add("Average Expenditure on Education (% of GDP)");
		methodsNames.add("Ratio of Hospital Beds (per 1000) and Current Health Expenditure (per 1000)");
		methodsNames.add("Healthcare Expenditure per Capita (current US$) vs Infant Mortality Rate (per 1000)");
        methodsNames.add("Government Education Expenditure (% of GDP) vs Health Expenditure (% of GDP)");

		methodsList = new JComboBox<String>(methodsNames);

        methodsList.addActionListener(this);
        previousAnalysis = (String) methodsList.getSelectedItem();

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		JPanel east = new JPanel();

		// Set charts region
		west = new JPanel();
		west.setBorder(new EmptyBorder(20, 70, 20, 20));
		west.setLayout(new GridLayout(2, 0));
		
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
	}

	public void launchMainUI() {
		frame = this;
		
		// edit these values to resize window accordingly
		frame.setSize(1150, 800);
		frame.setResizable(false);
		
		// centers the screen
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack(); // messes up the centering
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
        //get selected item
        if (e.getSource() == fromList){
            int selected = Integer.parseInt((String) fromList.getSelectedItem());
            String analysisSelected = (String) methodsList.getSelectedItem();
            int min = startYears[methodsNames.indexOf(analysisSelected)];
            int max = endYears[methodsNames.indexOf(analysisSelected)];
            if (selected < min || selected > max) {
            	errorMsg("Please select another start year, current year not valid.");
            	allPass = false;
            }
            else {
            	populator.setSelectionType("startYr", selected);
            }
        }
        
        //get selected item
        else if (e.getSource() == toList) {
        	int selected = Integer.parseInt((String) toList.getSelectedItem());
            String analysisSelected = (String) methodsList.getSelectedItem();
            int min = startYears[methodsNames.indexOf(analysisSelected)];
            int max = endYears[methodsNames.indexOf(analysisSelected)];
            if (selected < min || selected > max) {
            	errorMsg("Please select another end year, current year not valid.");
            	allPass = false;
            }
            else {
            	populator.setSelectionType("endYr", selected);
            }
        }
        
        else if(e.getSource() == methodsList) {
        	if (!((String) methodsList.getSelectedItem()).equals(previousAnalysis)) {
        		Arrays.fill(viewerList, 0);
        		populator.setSelectionType(viewerList, 0);
        		previousAnalysis = (String) methodsList.getSelectedItem();
        	}
        	populator.setSelectionType((String) methodsList.getSelectedItem(), 10);
        }
        
        else if (e.getSource() == countriesList) {
        	String selected = (String) countriesList.getSelectedItem();
        	if (bannedCountries.contains(selected)) {
        		errorMsg("This country is missing data and has been restricted! Please select another country.");
            	allPass = false;
        	}
        	else {
        		populator.setSelectionType(countryMap.get(selected), 15);
        	}
        }
        
        // when plus button is pressed (add viewer button)
        else if (e.getSource() == addView) {
        	String selected = (String) viewsList.getSelectedItem();
        	String analysisSelected = (String) methodsList.getSelectedItem();
        	int index = methodsNames.indexOf(analysisSelected);
            if (selected.equals("Pie Chart")){
            	if (index != 3 && index != 4 ) {
            		errorMsg("Failed adding viewer. Viewer cannot be used for this analysis type");
            	}
            	else if (checkViewer(0,"add")) {
            		populator.setSelectionType(viewerList, 0);
            	}
            	else {
            		errorMsg("Failed adding viewer. Already Added");
            	}
            }
            else if (selected.equals("Line Chart")){
            	if (index == 3 || index == 4 ) {
            		errorMsg("Failed adding viewer. Viewer cannot be used for this analysis type");
            	}
            	else if (checkViewer(1,"add")) {
            		populator.setSelectionType(viewerList, 1);
            	}
            	else {
            		errorMsg("Failed adding viewer. Already Added");
            	}
            }
            else if (selected.equals("Bar Chart")){
            	if (index == 3 || index == 4 ) {
            		errorMsg("Failed adding viewer. Viewer cannot be used for this analysis type");
            	}
            	else if (checkViewer(2,"add")) {
            		populator.setSelectionType(viewerList, 2);
            	}
            	else {
            		errorMsg("Failed adding viewer. Already Added");
            	}
            }
            else if (selected.equals("Scatter Chart")){
            	if (index == 3 || index == 4 ) {
            		errorMsg("Failed adding viewer. Viewer cannot be used for this analysis type");
            	}
            	else if (checkViewer(3,"add")) {
            		populator.setSelectionType(viewerList, 3);
            	}
            	else {
            		errorMsg("Failed adding viewer. Already Added");
            	}
            }
            else {
            	if (checkViewer(4,"add")) {
            		populator.setSelectionType(viewerList, 4);
            	}
            	else {
            		errorMsg("Failed adding viewer. Already Added");
            	}
            }
        }
        // when minus button is pressed (remove viewer button)
        else if (e.getSource() == removeView) {
        	String selected = (String) viewsList.getSelectedItem();
            if (selected.equals("Pie Chart")){
            	if (checkViewer(0,"remove")) {
            		populator.setSelectionType(viewerList, 0);
            	}
            	else {
            		errorMsg("Failed removing viewer. Not currently added");
            	}
            }
            else if (selected.equals("Line Chart")){
            	if (checkViewer(1,"remove")) {
            		populator.setSelectionType(viewerList, 1);
            	}
            	else {
            		errorMsg("Failed removing viewer. Not currently added");
            	}
            }
            else if (selected.equals("Bar Chart")){
            	if (checkViewer(2,"remove")) {
            		populator.setSelectionType(viewerList, 2);
            	}
            	else {
            		errorMsg("Failed removing viewer. Not currently added");
            	}
            }
            else if (selected.equals("Scatter Chart")){
            	if (checkViewer(3,"remove")) {
            		populator.setSelectionType(viewerList, 3);
            	}
            	else {
            		errorMsg("Failed removing viewer. Not currently added");
            	}
            }
            else {
            	if (checkViewer(4,"remove")) {
            		populator.setSelectionType(viewerList, 4);
            	}
            	else {
            		errorMsg("Failed removing viewer. Not currently added");
            	}
            }
        }
        
        else if(e.getSource() == recalculate) {
        	// submit a new event so the actionPerformed() will run again to verify selections
        	allPass = true;
        	countriesList.setSelectedIndex(countriesList.getSelectedIndex());
        	fromList.setSelectedIndex(fromList.getSelectedIndex());
        	toList.setSelectedIndex(toList.getSelectedIndex());
        	if (Integer.parseInt((String) fromList.getSelectedItem()) > Integer.parseInt((String) toList.getSelectedItem())) {
        		allPass = false;
        		errorMsg("Please make sure the year range is valid.");
        	}
        	if (!contains(viewerList, 1)) {
        		allPass = false;
        		errorMsg("No viewers have been added.");
        	}
        	
        	if (allPass == true) {
        		west.removeAll();
                frame.revalidate();
        		frame.repaint();
        		populator.setSelectionType("finished", methodsNames.indexOf(methodsList.getSelectedItem()));
        		frame.revalidate();
        		frame.repaint();
        	}
        }
	}
	
	private void errorMsg(String errorStr) {
		JOptionPane.showMessageDialog(this, errorStr);
	}
	
	private boolean checkViewer(int index, String type) {
		if (type.equals("add")) {
			if (viewerList[index] == 0) {
				viewerList[index] = 1;
				return true;
			}
			return false;	
		}
		else {
			if (viewerList[index] == 0) {
				return false;
			}
			viewerList[index] = 0;
			return true;
		}
	}
	
	private static TreeMap<String, String> loadcountryMap() {
		TreeMap<String, String> returnList = new TreeMap<String, String>();
		
		String content = "";
		try {
		      File myObj = new File(countryFile);
		      Scanner myReader = new Scanner(myObj, "utf-8");
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        content += data + ",";
		      }
		      myReader.close();
		}
		// display error and terminate program if database error
		catch (FileNotFoundException e) {
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "The country database is corrupted! Application now terminating...", "Fatal error", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		// loop through the csv and store all country abbreviation combos in the hashmap
		String[] tempcountryMap = content.split(",");
		
		for (int i = 0; i < tempcountryMap.length; i += 2) {
			returnList.put(tempcountryMap[i], tempcountryMap[i+1].toLowerCase());
		}
		
		return returnList;
	}
	
	private static boolean contains(int[] suppliedArr, int value) {
		for (int i = 0; i< suppliedArr.length; i++) {
			if (suppliedArr[i] == 1) {
				return true;
			}
		}
		return false;
	}
	
	private static ArrayList<String> initBannedCountries() {
		ArrayList<String> returnList = new ArrayList<String>();
		
		String content = "";
		try {
		      File myObj = new File(bannedCountryFile);
		      Scanner myReader = new Scanner(myObj, "utf-8");
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        content += data + ",";
		      }
		      myReader.close();
		}
		// display error and terminate program if database error
		catch (FileNotFoundException e) {
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "The restricted country database is corrupted! Application now terminating...", "Fatal error", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		// loop through the csv and store all country abbreviation combos in the hashmap
		String[] tempRestrictedCountries = content.split(",");
		
		for (int i = 0; i < tempRestrictedCountries.length; i++) {
			returnList.add(tempRestrictedCountries[i]);
		}
		
		return returnList;
	}
}