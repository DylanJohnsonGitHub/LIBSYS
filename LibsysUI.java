//package LIBSYS_System;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import java.sql.*;

public class LibsysUI {
	
	static int i = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//-----------------------SQL Stuff----------------------------------//
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
						
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libsys","root","CSC331");   
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("select * from books");  
				//while(rs.next())  
					//System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3));
			}catch(Exception e){ System.out.println(e);}
		
		//------------------------------------------------------------------/
		
		//initialize array of JPanels for display on GUI
		JPanel[] panelArray = new JPanel[6];
		JPanel defaultPanel = new JPanel();
		panelArray[0] = defaultPanel;
		
		//Creates a panel to hold the checkout buttons and fields
		JPanel checkOutPanel = new JPanel();
		//store check out panel into panel array
		panelArray[1] = checkOutPanel;
		
		//Creates panel to hold the book return buttons and fields
		JPanel bookReturnPanel = new JPanel();
		//stores the return panel to the array
		panelArray[2] = bookReturnPanel;
		
		//Creates the panel for the search buttons and fields
		JPanel searchPanel = new JPanel();
		//stores the search panel to the array
		panelArray[3] = searchPanel;
		
		//Creates panel for the buttons and fields to manage current users
		JPanel manageUsersPanel = new JPanel();
		//stores manage user panel to array
		panelArray[4] = manageUsersPanel;
		
		//creates panel to create new users
		JPanel createUserPanel = new JPanel();
		//stores create user panel to array
		panelArray[5] = createUserPanel;
		
	
//-----------------------Set Up Frame-------------------------------//
		
		//Create JFrame with title
		JFrame libFrame = new JFrame("LIBSYS Library Management System");
		
		//Set location for the Frame
		libFrame.setLocation(300, 300);
		
		//libFrame.setSize(500, 500);
		
//-----------MENU SET UP | NEW INFORMATION --------------------------//
		
		//Initialize menu bar at the top of the GUI
		JMenuBar menuBar = new JMenuBar();
		
		//Create a button on the menu bar for a file system (MAY BE REMOVED)
		JMenu fileMenu = new JMenu("File");
		
		//Set hotkey for file menu option
		fileMenu.setMnemonic(KeyEvent.VK_F);
		
		//Does not do anything, but is nice for tracking
		fileMenu.getAccessibleContext().setAccessibleDescription("File Area");
		
		//Adds file menu to menu bar
		menuBar.add(fileMenu);
		
		//initialize an option within the File menu with hotkey
		JMenuItem fileMenuItem = new JMenuItem("File Away", KeyEvent.VK_T);
		
		//create hotkey combination of ALT+F1
		fileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		
		//Does nothing
		fileMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		
		//Adds menu item to menu option on menu bar
		fileMenu.add(fileMenuItem);
		
		JMenu booksMenu = new JMenu("Books");
		booksMenu.setMnemonic(KeyEvent.VK_B);
		booksMenu.getAccessibleContext().setAccessibleDescription("Book Area");
		menuBar.add(booksMenu);
		
		JMenuItem booksMenuItem = new JMenuItem("Books a Boy", KeyEvent.VK_U);
		booksMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		booksMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		booksMenu.add(booksMenuItem);
		
		JMenu membersMenu = new JMenu("Members");
		membersMenu.setMnemonic(KeyEvent.VK_M);
		membersMenu.getAccessibleContext().setAccessibleDescription("Member Area");
		menuBar.add(membersMenu);
		
		JMenuItem membersMenuItem = new JMenuItem("Member Access", KeyEvent.VK_V);
		membersMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		membersMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		membersMenu.add(membersMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.getAccessibleContext().setAccessibleDescription("Help Area");
		menuBar.add(helpMenu);
		
		JMenuItem helpMenuItem = new JMenuItem("Help Us", KeyEvent.VK_W);
		helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		helpMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		helpMenu.add(helpMenuItem);
		
		JMenu aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		aboutMenu.getAccessibleContext().setAccessibleDescription("About Area");
		menuBar.add(aboutMenu);
		
		JMenuItem aboutMenuItem = new JMenuItem("About Page", KeyEvent.VK_X);
		aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
		aboutMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		aboutMenu.add(aboutMenuItem);

//------------------END MENU SET UP --------------------------------------------------------------------//

//------------------FOOT PANEL SETUP-------------------------------------------------------------------//
		//initialize the label for the system date
		JLabel dateLabel = new JLabel("System Date: ");
		
		//initialize the field for the system date
		JTextField dateField = new JTextField(6);
		
		//makes the date field uneditable from within the GUI
		dateField.setEditable(false);
		
		//initialize the label for the system time
		JLabel timeLabel = new JLabel("System Time: ");
		JTextField timeField = new JTextField(5);
		timeField.setEditable(false);
		
		//create button labeled with string in quotes
		JButton textButton = new JButton("CHANGE PANEL");
		
		//creates a panel to hold the date, time, and "Does Not Work" button
		JPanel footPanel = new JPanel();
		
		//set layout for the foot panel
		footPanel.add(BorderLayout.WEST, timeLabel);
		footPanel.add(BorderLayout.WEST, timeField);
		footPanel.add(BorderLayout.CENTER, textButton);
		footPanel.add(BorderLayout.EAST, dateLabel);
		footPanel.add(BorderLayout.EAST, dateField);
		
//------------------END FOOT PANEL SETUP----------------------------------------------------------------------------//
		
//-----------------CHECKOUT PANEL SETUP-------------------------------------------------------------------------//
		
		//creates the labels, fields, and buttons for the checkout panel
		JLabel bookNum = new JLabel("ISBN: ");
		JTextField bookNumText = new JTextField(15);
		JButton bookNumButton = new JButton("Search ISBN");
		
		JLabel bookTitle = new JLabel("Title: ");
		JTextField bookTitleText = new JTextField(15);
		bookTitleText.setEditable(false);
		
		JLabel bookAuthor = new JLabel("Author: ");
		JTextField bookAuthorText = new JTextField(15);
		bookAuthorText.setEditable(false);
		
		JLabel userID = new JLabel("User: ");
		JTextField userIDText = new JTextField(15);
		JButton userIDButton = new JButton("Search Member ID");
		
		JLabel userName = new JLabel("Name: ");
		JTextField userNameText = new JTextField(15);
		userNameText.setEditable(false);
		
		JLabel userEmail = new JLabel("Email: ");
		JTextField userEmailText = new JTextField(15);
		userEmailText.setEditable(false);
		
		JLabel returnDate = new JLabel("RETURN DATE: ");
		JTextField returnDateText = new JTextField(15);
		returnDateText.setEditable(false);
		
		//create panel to hold the buttons and return date for the checkout panel
		JPanel checkoutFootPanel = new JPanel();
		
		//overrides the default FlowLayout system for JPanel and uses BorderLayout for quad-directional control of components
		checkoutFootPanel.setLayout(new BorderLayout());
		
		//create buttons and labels for the checkout panel
		JButton checkoutButton = new JButton("CHECK OUT");
		JButton reserveButton = new JButton("RESERVE");
		JLabel borrowReserve = new JLabel("(Borrowing/Reserving)");
		
		//label specially for the "Borrow/Reserve" tag on the panel
		JPanel reserveLabelPanel = new JPanel();
		reserveLabelPanel.add(borrowReserve);
		
		//panel for the checkout and reserve buttons
		JPanel borrowReservePanel = new JPanel();
		borrowReservePanel.add(checkoutButton);
		borrowReservePanel.add(reserveButton);
		
		//panel specifically for the Return Date fields
		JPanel returnPanel = new JPanel();
		returnPanel.add(returnDate);
		returnPanel.add(returnDateText);
		
		//set layout for the foot panel of the checkout screen
		checkoutFootPanel.add(BorderLayout.NORTH, returnPanel);
		checkoutFootPanel.add(BorderLayout.CENTER, borrowReservePanel);
		checkoutFootPanel.add(BorderLayout.SOUTH, reserveLabelPanel);
		
		//create panel to handle the information for the books on the checkout pane
		JPanel checkoutBookPanel = new JPanel();
		checkoutBookPanel.setLayout(new BorderLayout());
		
		//create panels for each trait of the book
		JPanel checkoutISBNPanel = new JPanel();
		JPanel checkoutTitlePanel = new JPanel();
		JPanel checkoutAuthorPanel = new JPanel();
		
		//set items within each respective book info panel
		checkoutISBNPanel.add(bookNum);
		checkoutISBNPanel.add(bookNumText);
		checkoutISBNPanel.add(bookNumButton);
		checkoutTitlePanel.add(bookTitle);
		checkoutTitlePanel.add(bookTitleText);
		checkoutAuthorPanel.add(bookAuthor);
		checkoutAuthorPanel.add(bookAuthorText);
		
		//sets orientation of the book info panel
		checkoutBookPanel.add(BorderLayout.NORTH, checkoutISBNPanel);
		checkoutBookPanel.add(BorderLayout.CENTER, checkoutTitlePanel);
		checkoutBookPanel.add(BorderLayout.SOUTH, checkoutAuthorPanel);
		
		//panel for member info on checkout panel
		JPanel checkoutMemberPanel = new JPanel();
		checkoutMemberPanel.setLayout(new BorderLayout());
		
		//create panel for each member trait
		JPanel checkoutUserPanel = new JPanel();
		JPanel checkoutNamePanel = new JPanel();
		JPanel checkoutEmailPanel = new JPanel();
		
		//add each member trait to its respective panel
		checkoutUserPanel.add(userID);
		checkoutUserPanel.add(userIDText);
		checkoutUserPanel.add(userIDButton);
		checkoutNamePanel.add(userName);
		checkoutNamePanel.add(userNameText);
		checkoutEmailPanel.add(userEmail);
		checkoutEmailPanel.add(userEmailText);
		
		//create orientation of Member panel
		checkoutMemberPanel.add(BorderLayout.NORTH, checkoutUserPanel);
		checkoutMemberPanel.add(BorderLayout.CENTER, checkoutNamePanel);
		checkoutMemberPanel.add(BorderLayout.SOUTH, checkoutEmailPanel);
		
		//set border layout as layout style for the entire Check Out Panel 
		checkOutPanel.setLayout(new BorderLayout());
		
		//set layout of check out panel
		checkOutPanel.add(BorderLayout.NORTH, checkoutBookPanel);
		checkOutPanel.add(BorderLayout.CENTER, checkoutMemberPanel);
		checkOutPanel.add(BorderLayout.SOUTH, checkoutFootPanel);
	
//---------------------END CHECKOUT PANEL SETUP--------------------------------------//
		
//--------------------RETURN PANEL SETUP---------------------------------------------//
		
		//creates the panel for checking out books
		bookReturnPanel.setLayout(new BorderLayout());
		
		//creates label and field to display the ISBN to checkout
		JLabel returnISBNLabel = new JLabel("ISBN: ");
		JTextField returnISBNText = new JTextField(15);
		
		//creates the label and field for the book's title
		JLabel returnTitleLabel = new JLabel("Title: ");
		JTextField returnTitleText = new JTextField(15);
		returnTitleText.setEditable(false);
		
		//creates the label and field for the books author
		JLabel returnAuthorLabel = new JLabel("Author: ");
		JTextField returnAuthorText = new JTextField(15);
		returnAuthorText.setEditable(false);
		
		//creates the label and field for the return date of the book
		JLabel returnReturnDateLabel = new JLabel("Return Date: ");
		JTextField returnReturnDateText = new JTextField(15);
		returnReturnDateText.setEditable(false);
		
		//creates label and field for current date
		JLabel returnCurrentDateLabel = new JLabel("Today's Date: ");
		JTextField returnCurrentDateText = new JTextField(15);
		returnCurrentDateText.setEditable(false);
		
		//displays the member that currently has the books checked out
		JLabel returnUserLabel = new JLabel("User: ");
		JTextField returnUserText = new JTextField(15);
		//returnUserText.setEditable(false);
		
		//label and field for # days late
		JLabel returnDaysLateLabel = new JLabel("Days Late: ");
		JTextField returnDaysLateText = new JTextField(15);
		returnDaysLateText.setEditable(false);
		
		//label and field for late fees
		JLabel returnLateFeesLabel = new JLabel("Late Fees: ");
		JTextField returnLateFeesText = new JTextField(15);
		returnLateFeesText.setEditable(false);
		
		//buttons for returning books
		JButton returnBook = new JButton("RETURN");
		JButton renewBook = new JButton("RENEW");
		
		//label for the panel
		JLabel returnRenew = new JLabel("(Returning/Renewing)");
		
		//creates the nesting panels necessary to create the return/renewPanel
		JPanel returnBookInfoPanel = new JPanel();
		returnBookInfoPanel.setLayout(new BorderLayout());
		
		JPanel returnBookInfoISBNPanel = new JPanel();
		
		returnBookInfoISBNPanel.add(returnISBNLabel);
		returnBookInfoISBNPanel.add(returnISBNText);
		
		JPanel returnBookInfoTitlePanel = new JPanel();
		
		returnBookInfoTitlePanel.add(returnTitleLabel);
		returnBookInfoTitlePanel.add(returnTitleText);
		
		JPanel returnBookInfoAuthorPanel = new JPanel();
		
		returnBookInfoAuthorPanel.add(returnAuthorLabel);
		returnBookInfoAuthorPanel.add(returnAuthorText);
		
		JPanel returnBookInfoReturnDatePanel = new JPanel();
		returnBookInfoReturnDatePanel.add(returnReturnDateLabel);
		returnBookInfoReturnDatePanel.add(returnReturnDateText);
		
		JPanel returnBookInfoCurrentDatePanel = new JPanel();
		returnBookInfoCurrentDatePanel.add(returnCurrentDateLabel);
		returnBookInfoCurrentDatePanel.add(returnCurrentDateText);
		
		JPanel returnBookInfoDatesPanel = new JPanel();
		returnBookInfoDatesPanel.setLayout(new BorderLayout());
		returnBookInfoDatesPanel.add(BorderLayout.NORTH, returnBookInfoReturnDatePanel);
		returnBookInfoDatesPanel.add(BorderLayout.SOUTH, returnBookInfoCurrentDatePanel);
		
		
		JPanel returnBookInfoBookPanel = new JPanel();
		returnBookInfoBookPanel.setLayout(new BorderLayout());
		returnBookInfoBookPanel.add(BorderLayout.NORTH, returnBookInfoTitlePanel);
		returnBookInfoBookPanel.add(BorderLayout.CENTER, returnBookInfoAuthorPanel);
		returnBookInfoBookPanel.add(BorderLayout.SOUTH, returnBookInfoDatesPanel);
		
		returnBookInfoPanel.add(BorderLayout.NORTH, returnBookInfoISBNPanel);
		returnBookInfoPanel.add(BorderLayout.CENTER, returnBookInfoBookPanel);
		returnBookInfoPanel.add(BorderLayout.SOUTH, returnBookInfoDatesPanel);
		
		
		JPanel returnBookUserPanel = new JPanel();
		returnBookUserPanel.setLayout(new BorderLayout());
		
		JPanel returnBookUserInfoPanel = new JPanel();
		returnBookUserInfoPanel.add(returnUserLabel);
		returnBookUserInfoPanel.add(returnUserText);
		
		JPanel returnBookUserLatePanel = new JPanel();
		returnBookUserLatePanel.add(returnDaysLateLabel);
		returnBookUserLatePanel.add(returnDaysLateText);
		
		JPanel returnBookUserFeePanel = new JPanel();
		returnBookUserFeePanel.add(returnLateFeesLabel);
		returnBookUserFeePanel.add(returnLateFeesText);
		
		returnBookUserPanel.add(BorderLayout.NORTH, returnBookUserInfoPanel);
		returnBookUserPanel.add(BorderLayout.CENTER, returnBookUserLatePanel);
		returnBookUserPanel.add(BorderLayout.SOUTH, returnBookUserFeePanel);
		
		JPanel returnFootPanel = new JPanel();
		returnFootPanel.setLayout(new BorderLayout());
		
		JPanel returnButtonPanel = new JPanel();
		returnButtonPanel.add(returnBook);
		returnButtonPanel.add(renewBook);
		
		JPanel returnLabelPanel = new JPanel();
		returnLabelPanel.add(returnRenew);
		
		returnFootPanel.add(BorderLayout.NORTH, returnButtonPanel);
		returnFootPanel.add(BorderLayout.SOUTH, returnLabelPanel);
		
		bookReturnPanel.add(BorderLayout.NORTH, returnBookInfoPanel);
		bookReturnPanel.add(BorderLayout.CENTER, returnBookUserPanel);
		bookReturnPanel.add(BorderLayout.SOUTH, returnFootPanel);
		
//---------------------------END RETURN PANEL SETUP-------------------------------//
		
//---------------------------SEARCH PANEL SETUP--------------------------------------//
		
		searchPanel.setLayout(new BorderLayout());
		
		//Create labels and buttons for search panel
		JLabel searchLabel = new JLabel("Search By:");
		JLabel searchCatalogLabel = new JLabel("(Searching Catalog)");
		JButton searchISBNButton = new JButton("ISBN");
		JTextField searchISBNField = new JTextField(15);
		JButton searchTitleButton = new JButton("Title");
		JTextField searchTitleField = new JTextField(15);
		JButton searchAuthorButton = new JButton("Author");
		JTextField searchAuthorField = new JTextField(15);
		
		JPanel searchLabelPanel = new JPanel();
		searchLabelPanel.add(searchLabel);
		
		JPanel searchCatalogLabelPanel = new JPanel();
		searchCatalogLabelPanel.add(searchCatalogLabel);
		
		JPanel searchButtonPanel = new JPanel();
		searchButtonPanel.setLayout(new BorderLayout());
		
		JPanel searchISBNPanel = new JPanel();
		searchISBNPanel.add(searchISBNButton);
		searchISBNPanel.add(searchISBNField);
		
		JPanel searchTitlePanel = new JPanel();
		searchTitlePanel.add(searchTitleButton);
		searchTitlePanel.add(searchTitleField);
		
		JPanel searchAuthorPanel = new JPanel();
		searchAuthorPanel.add(searchAuthorButton);
		searchAuthorPanel.add(searchAuthorField);
		
		searchButtonPanel.add(BorderLayout.NORTH,searchISBNPanel);
		searchButtonPanel.add(BorderLayout.CENTER,searchTitlePanel);
		searchButtonPanel.add(BorderLayout.SOUTH,searchAuthorPanel);
		
		searchPanel.add(BorderLayout.NORTH, searchLabelPanel);
		searchPanel.add(BorderLayout.CENTER, searchButtonPanel);
		searchPanel.add(BorderLayout.SOUTH, searchCatalogLabelPanel);
		
//------------------------END SEARCH PANEL SETUP---------------------------------------//
		
//------------------------SEARCH POPUP FRAME SETUP-------------------------------------//
		
		JFrame searchCatalogFrame = new JFrame("Search Catalog Results");
		
		JTextArea catalogResults = new JTextArea("[Catalog Results Show Up Here]");
		
		searchCatalogFrame.setLocation(450, 450);
		
		searchCatalogFrame.add(catalogResults);
		
		searchCatalogFrame.setSize(600, 600);
		
//-----------------------END SEARCH POPUP FRAME SETUP----------------------------------//	
		
//---------------------------FRAME DISPLAY--------------------------------------------//
		
		//add menu bar to Frame and set layout for the frame
		libFrame.setJMenuBar(menuBar);
		libFrame.getContentPane().add(BorderLayout.NORTH, panelArray[i]);
		libFrame.getContentPane().add(BorderLayout.SOUTH, footPanel);
		
		//creates actual function for the buttons
		//changes visible panel when south center button pushed
		textButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
						
				//checks to see if the panel array has been looped through
				if(i < panelArray.length - 1) {
					//if so, increments i
					i++;
							
				}
						
				else {
					//otherwise, resets i to beginning of loop
					i = 0;
							
				}
							
				//changes the current panel
				//removes everything from the current frame
				libFrame.getContentPane().removeAll();
				//adds the ith panel according to the button presses
				libFrame.getContentPane().add(BorderLayout.NORTH, panelArray[i]);
				//adds the foot panel with the time, date, and increment button
				libFrame.getContentPane().add(BorderLayout.SOUTH, footPanel);
				//ensures everything is added to frame, repacks it, and sets it visible
				libFrame.revalidate();
				libFrame.pack();
					
			}
		
		});
				
		//resize the frame to fit components
		libFrame.pack();
				
		//set the frame visible
		libFrame.setVisible(true);
				
		libFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			
//--------------------------------END FRAME DISPLAY---------------------------------------------------------//
				
//--------------------------------FUNCTION-------------------------------------------------------------------//
				
		//gives action to "Search ISBN" button
		bookNumButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
						
				//create temporary variable for SQL query
				String bookISBN = bookNumText.getText();
				//Print the number to console for testing
				System.out.println(bookISBN);
				
				//PLACE QUERY CODE HERE
						
			}
		
		});
				
		//gives action to "Search ISBN" button
		userIDButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				//create temporary variable for SQL query
				String memberID = userIDText.getText();
				//Print the number to console for testing
				System.out.println(memberID);
				
				//PLACE QUERY CODE HERE
						
			}
		
		});
		
		//gives action to buttons on Search Panel
		searchISBNButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String searchISBN = searchISBNField.getText(); 
				
				//PLACE SQL CODE NEEDED HERE
				
				System.out.println(searchISBN);
				
				searchCatalogFrame.setVisible(true);
				
			}
		
		});
		
		searchTitleButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String searchTitle = searchTitleField.getText();
				
				//PLACE SQL CODE NEEDED HERE
				
				System.out.println(searchTitle);
				
				searchCatalogFrame.setVisible(true);
				
			}
		
		});
		
		searchAuthorButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String searchAuthor = searchAuthorField.getText();
				
				//PLACE SQL CODE NEEDED HERE
				
				System.out.println(searchAuthor);
				
				searchCatalogFrame.setVisible(true);
				
			}
		
		});
		
		//updates system date and time throughout operation of program
		while (true) {
			
			//creates instances of local time and date
			LocalDate localDate = LocalDate.now();
			LocalTime localTime = LocalTime.now();
			
			//creates format pattern for date and time
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/YYYY");
			DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
			
			//updates corresponding date and time fields
			dateField.setText(dateFormat.format(localDate));
			timeField.setText(timeFormat.format(localTime));
			
		}
		
//-------------------------------END FUNCTION-----------------------------------------------------------------//
		
	}

}
