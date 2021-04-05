package frontEnd;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import selection.Populator;

public class MainUI extends JFrame implements Launch, ActionListener{
	/**
	 * 
	 */

     // instance variables
	private static final long serialVersionUID = 1L;

    private JComboBox<String> countriesList;
    private JComboBox<String> fromList;
    private JComboBox<String> toList;
    private JComboBox<String> viewsList;
    private JComboBox<String> methodsList;


    private JButton recalculate;
    private JButton addView;
    private JButton removeView;
    private String[] analysisTypes;
    private int[] startYears;
    private int[] endYears;
    Populator populator;
    
    private int[] viewerList;
    /**
    Constructor
    */
	public MainUI() {
		// Set window title
		super("Country Statistics");
		populator = new Populator();
		
		analysisTypes = new String[]{"CO2 emissions vs Energy use vs PM2.5 air pollution", "PM 2.5 air pollution vs Forest area",
		    "Ratio of C02 emissions and GDP per capita", "Average forest area (% of land) for the selected years", 
		    "Average expenditure on education (% of GDP)", "Ratio of hospital beds (per 1 000) and current health expenditure (per 1 000)", 
		    "Current healthcare expenditure per capita (current USD) vs Mortality rate, infant (per 1 000 live births)", 
		    "Government expenditure on education (% of GDP) vs health expenditure (% of GDP)"};
		startYears = new int[] {1990, 1990, 1962, 1990, 1970, 1962, 2000, 1970};
		endYears = new int[] {2015, 2017, 2016, 2018, 2019, 2018, 2018, 2018};
		viewerList = new int[] {0,0,0,0,0};
		
		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		countriesNames.add("Australia");
		countriesNames.add("Canada");
		countriesNames.add("Chile");
		countriesNames.add("Germany");
		countriesNames.add("Israel");
        countriesNames.add("Japan");
        countriesNames.add("Kenya");
        countriesNames.add("Korea");
        countriesNames.add("USA");
        countriesNames.add("United Kingdom");
		countriesNames.sort(null);
		countriesList = new JComboBox<String>(countriesNames);

        countriesList.addActionListener(this);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		for (int i = 2021; i >= 1960; i--) {
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

		Vector<String> viewsNames = new Vector<String>();
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

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("CO2 emissions vs Energy use vs PM2.5 air pollution");
		methodsNames.add("PM 2.5 air pollution vs Forest area");
		methodsNames.add("Ratio of C02 emissions and GDP per capita");
		methodsNames.add("Average forest area (% of land) for the selected years");
        methodsNames.add("Average expenditure on education (% of GDP)");
		methodsNames.add("Ratio of hospital beds (per 1 000) and current health expenditure (per 1 000)");
		methodsNames.add("Current healthcare expenditure per capita (current USD) vs Mortality rate, infant (per 1 000 live births)");
        methodsNames.add("Government expenditure on education (% of GDP) vs health expenditure (% of GDP)");

		methodsList = new JComboBox<String>(methodsNames);

        methodsList.addActionListener(this);

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
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 0));

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
	}



	public void launchMainUI() {
		JFrame frame = this;
		
		// edit these values to resize window accordingly
		frame.setSize(1220, 800);
//		frame.setResizable(false);
		
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
            int min = startYears[indexOf(analysisSelected, analysisTypes)];
            int max = endYears[indexOf(analysisSelected, analysisTypes)];
            if (selected < min || selected > max) {
            	errorMsg("Please select another start year, current year not valid.");
            }
            else {
            	populator.setSelectionType("startYr", selected);
            }
        }
        
        //get selected item
        else if (e.getSource() == toList) {
        	int selected = Integer.parseInt((String) toList.getSelectedItem());
            String analysisSelected = (String) methodsList.getSelectedItem();
            int min = startYears[indexOf(analysisSelected, analysisTypes)];
            int max = endYears[indexOf(analysisSelected, analysisTypes)];
            if (selected < min || selected > max) {
            	errorMsg("Please select another end year, current year not valid");
            }
            else {
            	populator.setSelectionType("endYr", selected);
            }
        }
        
        else if(e.getSource() == methodsList) {
        	populator.setSelectionType((String) methodsList.getSelectedItem(), 10);
        }
        // when plus button is pressed (add viewer button)
        else if (e.getSource() == addView) {
        	String selected = (String) viewsList.getSelectedItem();
            if (selected.equals("Pie Chart")){
            	if (checkViewer(0,"add")) {
            		populator.setSelectionType(viewerList, 0);
            	}
            	else {
            		errorMsg("Failed adding viewer. Already Added");
            	}
            }
            else if (selected.equals("Line Chart")){
            	if (checkViewer(1,"add")) {
            		populator.setSelectionType(viewerList, 1);
            	}
            	else {
            		errorMsg("Failed adding viewer. Already Added");
            	}
            }
            else if (selected.equals("Bar Chart")){
            	if (checkViewer(2,"add")) {
            		populator.setSelectionType(viewerList, 2);
            	}
            	else {
            		errorMsg("Failed adding viewer. Already Added");
            	}
            }
            else if (selected.equals("Scatter Chart")){
            	if (checkViewer(3,"add")) {
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
        	populator.setSelectionType("finished", indexOf((String) methodsList.getSelectedItem(), analysisTypes));
        }
	}
	
	private <T> int indexOf(T item, T[] arr) {
		for (int i=0; i<arr.length; i++)
	    {
	        if (arr[i] != null && arr[i].equals(item)){
	        	return i;
	        }
	    }

	    return -1;
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

}