import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.sql.*;

public class Libsys {

	public final String connectCode = "jdbc:mysql://localhost:3306/libsys";
	
	public static void main(String[] args) {
		
		
	//-----------------------SQL Stuff----------------------------------//
	/*	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
					
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libsys","root","CSC331");   
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from books");  
			//while(rs.next())  
				//System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3));
		}catch(Exception e){ System.out.println(e);}
	*/
	//------------------------------------------------------------------/
		//initialize array of JPanels for display on GUI
		JPanel[] panelArray = new JPanel[10];
		JPanel emptyPanel = new JPanel();
		panelArray[1] = emptyPanel;
	
//-----------------------Set Up Frame-------------------------------//
		
		//Create JFrame with title
		JFrame libFrame = new JFrame("LIBSYS Library Management System");
		
		//Set location for the Frame
		libFrame.setLocation(300, 300);
		
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
		
		//initialize the label for the system date
		JLabel dateLabel = new JLabel("System Date: ");
		
		//initialize the field for the system date
		JTextField dateField = new JTextField(5);
		
		//makes the date field uneditable from within the GUI
		dateField.setEditable(false);
		
		//initialize the label for the system time
		JLabel timeLabel = new JLabel("System Time: ");
		JTextField timeField = new JTextField(5);
		timeField.setEditable(false);
		
		//create button labeled with string in quotes
		JButton textButton = new JButton("Does Not Work Anymore");
		
		//Creates a panel to hold the checkout buttons and fields
		JPanel checkOutPanel = new JPanel();
		
		//creates a panel to hold the date, time, and "Does Not Work" button
		JPanel footPanel = new JPanel();
		
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
		
		JTextField userEmailText = new JTextField(25);
		
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
		
		//store check out panel into panel array
		panelArray[0] = checkOutPanel;
		
		//set layout for the foot panel
		footPanel.add(BorderLayout.WEST, timeLabel);
		footPanel.add(BorderLayout.WEST, timeField);
		footPanel.add(BorderLayout.CENTER, textButton);
		footPanel.add(BorderLayout.EAST, dateLabel);
		footPanel.add(BorderLayout.EAST, dateField);
		
		//add menu bar to Frame and set layout for the frame
		libFrame.setJMenuBar(menuBar);
		libFrame.getContentPane().add(BorderLayout.NORTH, panelArray[0]);
		libFrame.getContentPane().add(BorderLayout.SOUTH, footPanel);
		
		//creates actual function for the buttons
		//changes visible panel when south center button pushed
				textButton.addActionListener(new ActionListener() {
					
						public void actionPerformed(ActionEvent e) {
							
							//changes the current panel
							libFrame.setContentPane(panelArray[1]);
							libFrame.getContentPane().add(BorderLayout.SOUTH, footPanel);
							libFrame.revalidate();
							
						}
					
					});
				
		//gives action to "Search ISBN" button
				bookNumButton.addActionListener(new ActionListener() {
					
						public void actionPerformed(ActionEvent e) {
							
							//create temporary variable for SQL query
							String bookISBN = bookNumText.getText();
							//Print the number to console for testing
							//System.out.println(bookISBN);
							//PLACE QUERY CODE HERE
							try {
								Class.forName("com.mysql.cj.jdbc.Driver");
										
								Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libsys","root","CSC331");   
								Statement stmt=con.createStatement();  
								String queryStatement = "select * from books where isbn = " + bookISBN;
								ResultSet rs=stmt.executeQuery(queryStatement);  
								while(rs.next()){
									String hello = (rs.getString("ISBN"));
									String goodbye = (rs.getString("title"));
									String maybe = (rs.getString("author"));
									bookTitleText.setText(goodbye);
									bookAuthorText.setText(maybe);
								}
							}catch(Exception a){ System.out.println(a);}
						}
					
					});
				
		//gives action to "Search User" button
				userIDButton.addActionListener(new ActionListener() {
					
						public void actionPerformed(ActionEvent e) {
							
							//create temporary variable for SQL query
							String memberID = userIDText.getText();
							//Print the number to console for testing
							System.out.println(memberID);
							
							//PLACE QUERY CODE HERE
							try {
								Class.forName("com.mysql.cj.jdbc.Driver");
										
								Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libsys","root","CSC331");   
								Statement stmt=con.createStatement();  
								String queryStatement = "select * from readers where id_number = " + memberID;
								ResultSet rs=stmt.executeQuery(queryStatement);  
								while(rs.next()){
									String hello = (rs.getString("id_number"));
									String goodbye = (rs.getString("name"));
									String maybe = (rs.getString("email"));
									userNameText.setText(goodbye);
									userEmailText.setText(maybe);
								}
							}catch(Exception a){ System.out.println(a);}
						}
					
					});
		
		//resize the frame to fit components
		libFrame.pack();
		
		//set the frame visible
		libFrame.setVisible(true);

	}

}
