import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WindowFrameWork {
	// Global Variables

	// All(OR)
	static JRadioButton Name = new JRadioButton("Name"); //Name replacement
	// Exact(PHRASE)
	static JRadioButton Route = new JRadioButton("Route"); //Route replacement
	// Any(AND)
	static JRadioButton Type = new JRadioButton("Type"); //Type replacement

	// Integer
	static int indexedFileTotal;

	// Search Fields
	static JTextField searchName = new JTextField(); //searchName replacement
	static JTextField searchRoute = new JTextField(); 
	static JTextField searchType = new JTextField(); 
	
	// Level Fields
	static JTextField lowLvlLimit = new JTextField(); 
	static JTextField HiLvlLimit = new JTextField(); 

	// Text Area
	static JTextArea searchNameArea = new JTextArea();

	/*
	 * Search Phrase Section Current Layout: searchPhrase() will search the
	 * phrase within the searchName will be assigned to variable and set to the
	 * current contents of the searchName. The radiobutton checkstates' will be
	 * checked to find out which phrase is being searched
	 */
	public static void searchPhrase() throws FileNotFoundException {

		// Gets text within the searchName - Can be set to console output for
		String searchText = searchName.getText();

		// Debugging
		// System.out.println(searchText);

		File index = new File("Index.txt");

		// Data Validation
		if (Name.isSelected() == false
				&& Route.isSelected() == false
				&& Type.isSelected() == false) {
			System.out.println("Select a Search Phrase");
			searchNameArea.setText("Select a Search Phrase");
		}

		// Creates scanner to read from file and text box string
		Scanner indexScanner = new Scanner(index);
		String phrase = indexScanner.findInLine("[A-Za-z].*[A-Za-z]");

		// Creates Switches to handle search phrases
		int searchSwitchOR = 1;
		int searchSwitchAND = 1;
		int searchSwitchPHRASE = 1;

		/*
		 * Handles PHRASE search phrase: Route - Searches for an EXACT
		 * text string from the Search.txt file
		 */
		if (Route.isSelected() == true) {
			switch (searchSwitchPHRASE) {

			// Data Validation
			case 1:
				if (searchText.isEmpty()) {
					searchNameArea.setText("Enter a File Name");
					break;
				}
				// Search Functionality
			case 2:
				searchNameArea.setText("");

				Scanner lineScanner = new Scanner(new File("Search.txt"));

				while (lineScanner.hasNextLine()) {
					String lineToRead = lineScanner.nextLine();
					if ((lineToRead.equals(searchText))
							&& (phrase.length() > 0)) {
						searchNameArea.append(String.format("%s %n",
								lineToRead));
					}
				}
			}
		}

		/*
		 * Handles AND search phrase: Type - Searches through the
		 * Search.txt file for text not entered into the text box.
		 */
		if (Type.isSelected() == true) {
			switch (searchSwitchAND) {

			// Data Validation
			case 1:
				if (searchText.isEmpty()) {
					searchNameArea.setText("Enter a File Name");
					break;
				}
				// Search Functionality
			case 2:
				searchNameArea.setText("");

				Scanner lineScanner = new Scanner(new File("Search.txt"));

				while (lineScanner.hasNextLine()) {
					String lineToRead = lineScanner.nextLine();
					if ((!lineToRead.contains(searchText))
							&& (phrase.length() > 0)) {
						searchNameArea.append(String.format("%s %n",
								lineToRead));
					}
				}
			}
		}

		/*
		 * Handles OR search phrase: [EXPLAIN WORKFLOW FOR "OR" SEARCH] Searches
		 * through the Search.txt file for all text entered.
		 */
		if (Name.isSelected() == true) {
			switch (searchSwitchOR) {

			// Data Validation
			case 1:
				if (searchText.isEmpty()) {
					searchNameArea.setText("Enter a File Name");
					break;
				}

				// Search Functionality
			case 2:
				if (Name.isSelected() == true) {
					searchNameArea.setText("");

					Scanner lineScanner = new Scanner(new File("Search.txt"));

					while (lineScanner.hasNext()) {
						String lineToRead = lineScanner.nextLine();

						if ((lineToRead.contains(searchText))
								&& (phrase.length() > 0)) {
							searchNameArea.append(String.format("%s %n",
									lineToRead));
						}

					}

				}

			}

		}

	}

	public static void createSearchEngine() {
		// Frame
		final JFrame frame = new JFrame("Pokemon Randomizer");

		// Label
		JLabel titleLabel = new JLabel("Enter Criteria");

		// Button Group
		ButtonGroup radioButtons = new ButtonGroup();

		// Button
		JButton buttonSearch = new JButton("Go!");

		// Menu Bar
		JMenuBar menubar = new JMenuBar();

		// Menu Additions
		JMenu menuFile = new JMenu("File");
		JMenu menuEdit = new JMenu("Edit");
		JMenu menuMaintenance = new JMenu("Maintenance");
		JMenu menuHelp = new JMenu("Help");

		// Menu Items
		JMenuItem subMaintenanceMenu = new JMenuItem("Maintenance Panel");
		JMenuItem subAboutPage = new JMenuItem("About");

		// Set size and location of frame
		frame.setSize(750, 550);
		frame.setLocation(100, 150);
		frame.setLocationRelativeTo(null);

		// Set Background Color
		frame.getContentPane().setBackground(Color.cyan);

		// Close on exit
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Sets labels and sizes
		titleLabel.setBounds(280, 10, 250, 50);
		titleLabel.setFont(new Font("Serif", Font.BOLD, 30));

		// Set size of the text box
		searchName.setBounds(155, 60, 200, 30);
		searchType.setBounds(155, 100, 200, 30);
		searchRoute.setBounds(155, 140, 200, 30);
		lowLvlLimit.setBounds(375, 100, 75, 30);
		HiLvlLimit.setBounds(475, 100, 75, 30);


		
		// Set size of the text Area
		searchNameArea.setBounds(10, 230, 715, 250);
		// Non-editable
		searchNameArea.setEditable(false);
		// Wraps lines automatically
		searchNameArea.setLineWrap(true);
		// Breaks lines
		searchNameArea.setWrapStyleWord(true);

		// Buttons
		buttonSearch.setBounds(600, 100, 75, 30);
		buttonSearch.setMnemonic(KeyEvent.VK_S);

		// Radio Button Hotkeys
		Name.setMnemonic(KeyEvent.VK_A);
		Route.setMnemonic(KeyEvent.VK_X);
		Type.setMnemonic(KeyEvent.VK_N);

		// Positioning
		Name.setBounds(50, 60, 75, 30);
		Name.setBackground(Color.cyan);
		Route.setBounds(50, 140, 75, 30);
		Route.setBackground(Color.cyan);
		Type.setBounds(50, 100, 75, 30);
		Type.setBackground(Color.cyan);

		// Radio Button Group Additions
		radioButtons.add(Name);
		radioButtons.add(Route);
		radioButtons.add(Type);

		// MenuBar additions
		menuFile.setMnemonic(KeyEvent.VK_F);
		menuEdit.setMnemonic(KeyEvent.VK_E);
		menuMaintenance.setMnemonic(KeyEvent.VK_M);
		menuHelp.setMnemonic(KeyEvent.VK_H);

		subMaintenanceMenu.setMnemonic(KeyEvent.VK_M);
		subAboutPage.setMnemonic(KeyEvent.VK_A);

		// SubMenu additions
		menuMaintenance.add(subMaintenanceMenu);
		menuHelp.add(subAboutPage);

		// Elements added
		frame.add(titleLabel);
		frame.add(searchName);
		frame.add(searchRoute);
		frame.add(searchType);
		frame.add(lowLvlLimit);
		frame.add(HiLvlLimit);
		frame.add(searchNameArea);
		frame.add(buttonSearch);
		frame.add(Name);
		frame.add(Route);
		frame.add(Type);

		frame.setJMenuBar(menubar);
		frame.setLayout(null);
		frame.setVisible(true);

		// Adding MenuBar Items
		menubar.add(menuFile);
		menubar.add(menuEdit);
		menubar.add(menuMaintenance);
		menubar.add(menuHelp);

		// Tool Tips
		buttonSearch.setToolTipText("Search Function");
		searchName.setToolTipText("Enter Text ");
		Name.setToolTipText("Search through everything");
		Route.setToolTipText("Search for exact word or phrase");
		subMaintenanceMenu.setToolTipText("Opens Maintenance page");
		subAboutPage.setToolTipText("Shows information about this project");

		/*
		 * Everything from here to the end is commented out
		 * because we might not need it and I don't know how
		 * to use it :D LOL
		 * 
		 */
		
		
		/*
		
		// Creates Maintenance Form
		subMaintenanceMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchItMaintenance.createSubMaintenance();
			}
		});

		// Handles Search Button Functionality
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					searchPhrase();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		// Creates new frame for About page
		subAboutPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchItAbout.createSubAbout();
			}
		});
		*/
		
	}
}
