package LIBSYS_System;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
//import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

interface panelSwitch {
	
	void panelSwap(int index);
	
}

public class LibsysUI{
	
	static int i = 0;
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//creates format pattern for date and time
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);
		
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
		
//-----------MENU SET UP--------------------------//
		
		//Initialize menu bar at the top of the GUI
		JMenuBar menuBar = new JMenuBar();
		
		/*
		 * //Create a button on the menu bar for a file system (MAY BE REMOVED) JMenu
		 * fileMenu = new JMenu("File");
		 * 
		 * //Set hotkey for file menu option fileMenu.setMnemonic(KeyEvent.VK_F);
		 * 
		 * //Does not do anything, but is nice for tracking
		 * fileMenu.getAccessibleContext().setAccessibleDescription("File Area");
		 * 
		 * //Adds file menu to menu bar menuBar.add(fileMenu);
		 * 
		 * //initialize an option within the File menu with hotkey JMenuItem
		 * fileMenuItem = new JMenuItem("File Away", KeyEvent.VK_T);
		 * 
		 * //create hotkey combination of ALT+F1
		 * fileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
		 * ActionEvent.ALT_MASK));
		 * 
		 * //Does nothing
		 * fileMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		 * 
		 * //Adds menu item to menu option on menu bar fileMenu.add(fileMenuItem);
		 */
		JMenu booksMenu = new JMenu("Books");
		booksMenu.setMnemonic(KeyEvent.VK_B);
		booksMenu.getAccessibleContext().setAccessibleDescription("Book Area");
		menuBar.add(booksMenu);
		
		JMenuItem checkoutBooks = new JMenuItem("Check Out Books", KeyEvent.VK_D);
		checkoutBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		checkoutBooks.getAccessibleContext().setAccessibleDescription("Help");
		booksMenu.add(checkoutBooks);
		
		JMenuItem searchBooks = new JMenuItem("Search Books", KeyEvent.VK_U);
		searchBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		searchBooks.getAccessibleContext().setAccessibleDescription("Help");
		booksMenu.add(searchBooks);
		
		JMenuItem addBooks = new JMenuItem("Add Books", KeyEvent.VK_V);
		addBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		addBooks.getAccessibleContext().setAccessibleDescription("Help");
		booksMenu.add(addBooks);
		
		JMenuItem removeBooks = new JMenuItem("Remove Books", KeyEvent.VK_W);
		removeBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		removeBooks.getAccessibleContext().setAccessibleDescription("Help");
		booksMenu.add(removeBooks);
		
		JMenuItem editBooks = new JMenuItem("Edit Books", KeyEvent.VK_X);
		editBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
		editBooks.getAccessibleContext().setAccessibleDescription("Help");
		booksMenu.add(editBooks);
		
		JMenu membersMenu = new JMenu("Members");
		membersMenu.setMnemonic(KeyEvent.VK_M);
		membersMenu.getAccessibleContext().setAccessibleDescription("Member Area");
		menuBar.add(membersMenu);
		
		JMenuItem memberReturn = new JMenuItem("Member Book Return", KeyEvent.VK_Y);
		memberReturn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, ActionEvent.ALT_MASK));
		memberReturn.getAccessibleContext().setAccessibleDescription("Help");
		membersMenu.add(memberReturn);
		
		JMenuItem createMember = new JMenuItem("Create a Member", KeyEvent.VK_Z);
		createMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, ActionEvent.ALT_MASK));
		createMember.getAccessibleContext().setAccessibleDescription("Help");
		membersMenu.add(createMember);
		
		JMenuItem updateMemberInfo = new JMenuItem("Update Member Info", KeyEvent.VK_A);
		updateMemberInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_8, ActionEvent.ALT_MASK));
		updateMemberInfo.getAccessibleContext().setAccessibleDescription("Help");
		membersMenu.add(updateMemberInfo);
		
		JMenuItem removeMember = new JMenuItem("Remove Member", KeyEvent.VK_C);
		removeMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9, ActionEvent.ALT_MASK));
		removeMember.getAccessibleContext().setAccessibleDescription("Help");
		membersMenu.add(removeMember);
		
		/*
		 * JMenu helpMenu = new JMenu("Help"); helpMenu.setMnemonic(KeyEvent.VK_H);
		 * helpMenu.getAccessibleContext().setAccessibleDescription("Help Area");
		 * menuBar.add(helpMenu);
		 * 
		 * JMenuItem helpMenuItem = new JMenuItem("Help Us", KeyEvent.VK_W);
		 * helpMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,
		 * ActionEvent.ALT_MASK));
		 * helpMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		 * helpMenu.add(helpMenuItem);
		 * 
		 * JMenu aboutMenu = new JMenu("About"); aboutMenu.setMnemonic(KeyEvent.VK_A);
		 * aboutMenu.getAccessibleContext().setAccessibleDescription("About Area");
		 * menuBar.add(aboutMenu);
		 * 
		 * JMenuItem aboutMenuItem = new JMenuItem("About Page", KeyEvent.VK_X);
		 * aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,
		 * ActionEvent.ALT_MASK));
		 * aboutMenuItem.getAccessibleContext().setAccessibleDescription("Help");
		 * aboutMenu.add(aboutMenuItem);
		 */

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
		
//------------------DEFAULT PANEL SETUP-----------------------------------------------------------------//
		
		JPanel defaultDisplay = new JPanel();
		JLabel defaultLabel = new JLabel("WELCOME TO LIBSYS!");
		defaultLabel.setFont(defaultLabel.getFont().deriveFont(32.0f));
		defaultDisplay.add(defaultLabel);
		defaultPanel.add(defaultDisplay);
		
//-----------------END DEFAULT PANEL--------------------------------------------------------------------//
		
//-----------------CHECKOUT PANEL SETUP-------------------------------------------------------------------------//
		
		//creates the labels, fields, and buttons for the checkout panel
		JLabel bookNum = new JLabel("ISBN: ");
		JTextField bookNumText = new JTextField(15);
		JButton bookNumButton = new JButton("Update Book Data");
		
		JLabel bookTitle = new JLabel("Title: ");
		JTextField bookTitleText = new JTextField(15);
		bookTitleText.setEditable(false);
		
		JLabel bookAuthor = new JLabel("Author: ");
		JTextField bookAuthorText = new JTextField(15);
		bookAuthorText.setEditable(false);
		
		JLabel userID = new JLabel("User: ");
		JTextField userIDText = new JTextField(15);
		JButton userIDButton = new JButton("Update Member Data");
		
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
		JButton returnISBNButton = new JButton("Update Book Data");
		
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
		JButton returnUserButton = new JButton("Update User Data");
		
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
		returnBookInfoISBNPanel.add(returnISBNButton);
		
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
		returnBookUserInfoPanel.add(returnUserButton);
		
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
		JLabel searchISBNLabel = new JLabel("ISBN: ");
		JTextField searchISBNField = new JTextField(15);
		JLabel searchTitleLabel = new JLabel("Title: ");
		JTextField searchTitleField = new JTextField(15);
		JLabel searchAuthorLabel = new JLabel("Author: ");
		JTextField searchAuthorField = new JTextField(15);
		JButton searchSearchButton = new JButton("Search");
		
		JPanel searchLabelPanel = new JPanel();
		searchLabelPanel.add(searchLabel);
		
		JPanel searchCatalogLabelPanel = new JPanel();
		searchCatalogLabelPanel.add(searchCatalogLabel);
		
		JPanel searchInfoPanel = new JPanel();
		searchInfoPanel.setLayout(new BorderLayout());
		
		JPanel searchSearchingPanel = new JPanel();
		searchSearchingPanel.setLayout(new BorderLayout());
		
		JPanel searchButtonPanel = new JPanel();
		searchButtonPanel.add(searchSearchButton);
		
		JPanel searchISBNPanel = new JPanel();
		searchISBNPanel.add(searchISBNLabel);
		searchISBNPanel.add(searchISBNField);
		
		JPanel searchTitlePanel = new JPanel();
		searchTitlePanel.add(searchTitleLabel);
		searchTitlePanel.add(searchTitleField);
		
		JPanel searchAuthorPanel = new JPanel();
		searchAuthorPanel.add(searchAuthorLabel);
		searchAuthorPanel.add(searchAuthorField);
		
		searchInfoPanel.add(BorderLayout.NORTH,searchISBNPanel);
		searchInfoPanel.add(BorderLayout.CENTER,searchTitlePanel);
		searchInfoPanel.add(BorderLayout.SOUTH,searchAuthorPanel);
		
		searchSearchingPanel.add(BorderLayout.NORTH, searchInfoPanel);
		searchSearchingPanel.add(BorderLayout.SOUTH, searchButtonPanel);
		
		searchPanel.add(BorderLayout.NORTH, searchLabelPanel);
		searchPanel.add(BorderLayout.CENTER, searchSearchingPanel);
		searchPanel.add(BorderLayout.SOUTH, searchCatalogLabelPanel);
		
//------------------------END SEARCH PANEL SETUP---------------------------------------//
		
//------------------------SEARCH POPUP FRAME SETUP-------------------------------------//
		
		JFrame searchCatalogFrame = new JFrame("Search Catalog Results");
		
		JButton searchSelectButton  = new JButton("Select Highlighted Result");
		
		JPanel searchSelectButtonPanel = new JPanel();
		
		searchSelectButtonPanel.add(searchSelectButton);
		
		//Creates List Model for containing the JList info
		@SuppressWarnings("rawtypes")
		DefaultListModel bookListModel = new DefaultListModel();
		
		//initiates the JList and sets its formatting
		@SuppressWarnings("rawtypes")
		JList bookList = new JList(bookListModel);
		
		//overrides the default renderer for bookList to use a different horizontal alignment
		/*
		 * DefaultListCellRenderer renderer =
		 * (DefaultListCellRenderer)bookList.getCellRenderer();
		 * renderer.setHorizontalAlignment(SwingConstants.LEFT);
		 */
		
		//allows only a single selection in the list
		bookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//sets the layout to be a vertical list
		bookList.setLayoutOrientation(JList.VERTICAL);
		
		//sets the default selection to be off the list, meaning no item is selected by default
		bookList.setVisibleRowCount(-1);
		
		//gives bookListModel some temporary elements to test alignment
		
		bookListModel.addElement("<html><center>----------------------------------<br><h1>Test Title</h1><br>Mary had a little lamb<br>----------------------------------------------</center></span></html>");
		bookListModel.addElement("<html>Mary had a<br> little lamb</span></html>");
		bookListModel.addElement("<html>Mary<br> had<br> a<br> little<br> lamb</span></html>");
		bookListModel.addElement("<html>1234<br>Test Book Title<br>Test Author</span></html>");
		bookListModel.addElement(LocalDate.now());
		
		JScrollPane bookListScroller = new JScrollPane(bookList);
		
		searchCatalogFrame.add(BorderLayout.NORTH, searchSelectButtonPanel);
		searchCatalogFrame.add(BorderLayout.CENTER, bookListScroller);
		
		searchCatalogFrame.setLocation(450, 450);
		
		searchCatalogFrame.setSize(600, 600);
		searchCatalogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		searchCatalogFrame.setVisible(true);
		
//-----------------------END SEARCH POPUP FRAME SETUP----------------------------------//
		
//-----------------------MANAGE MEMBER FRAME SETUP------------------------------------//
		
		manageUsersPanel.setLayout(new BorderLayout());
		
		JLabel manageUserIDLabel = new JLabel("ID: ");
		JTextField manageUserIDText = new JTextField(10);
		JButton manageUserSearchButton = new JButton("Search Member ID");
		
		JPanel manageUserIDPanel = new JPanel();
		manageUserIDPanel.add(manageUserIDLabel);
		manageUserIDPanel.add(manageUserIDText);
		manageUserIDPanel.add(manageUserSearchButton);
		
		JLabel managerUserNameLabel = new JLabel("Name: ");
		JTextField manageUserNameText = new JTextField(10);
		manageUserNameText.setEditable(false);
		
		JPanel manageUserNamePanel = new JPanel();
		manageUserNamePanel.add(managerUserNameLabel);
		manageUserNamePanel.add(manageUserNameText);
		
		JLabel manageUserEmailLabel = new JLabel("Email: ");
		JTextField manageUserEmailText = new JTextField(10);
		manageUserEmailText.setEditable(false);
		
		JPanel manageUserEmailPanel = new JPanel();
		manageUserEmailPanel.add(manageUserEmailLabel);
		manageUserEmailPanel.add(manageUserEmailText);
		
		JLabel manageUserMemberTilLabel = new JLabel("Member Until: ");
		JTextField manageUserMemberTilText = new JTextField(10);
		manageUserMemberTilText.setEditable(false);
		
		JPanel manageUserMemberUntilPanel = new JPanel();
		manageUserMemberUntilPanel.add(manageUserMemberTilLabel);
		manageUserMemberUntilPanel.add(manageUserMemberTilText);
		
		JPanel manageUserInfoPanel = new JPanel();
		manageUserInfoPanel.setLayout(new BorderLayout());
		manageUserInfoPanel.add(BorderLayout.NORTH, manageUserNamePanel);
		manageUserInfoPanel.add(BorderLayout.CENTER, manageUserEmailPanel);
		manageUserInfoPanel.add(BorderLayout.SOUTH, manageUserMemberUntilPanel);
		
		JLabel checkedOutLabel = new JLabel("Checked Out Books");
		JTextArea checkedOutArea = new JTextArea("[Checked Out Books Show Up Here]");
		checkedOutArea.setSize(200, 100);
		
		JPanel checkedOutLabelPanel = new JPanel();
		checkedOutLabelPanel.add(checkedOutLabel);
		
		JPanel checkedOutAreaPanel = new JPanel();
		checkedOutAreaPanel.add(checkedOutArea);
		
		JPanel checkedOutPanel = new JPanel();
		checkedOutPanel.setLayout(new BorderLayout());
		checkedOutPanel.add(BorderLayout.NORTH, checkedOutLabelPanel);
		checkedOutPanel.add(BorderLayout.SOUTH, checkedOutAreaPanel);
		
		JButton manageUserManageButton = new JButton("MANAGE");
		
		JPanel manageUserManageButtonPanel = new JPanel();
		manageUserManageButtonPanel.add(manageUserManageButton);
		
		JButton manageUserRenewButton = new JButton("RENEW MEMBERSHIP");
		JButton manageUserDeleteButton = new JButton("DELETE MEMBER");
		
		JPanel manageUserRenewDeletePanel = new JPanel();
		manageUserRenewDeletePanel.add(manageUserRenewButton);
		manageUserRenewDeletePanel.add(manageUserDeleteButton);
		
		JLabel manageUserManageLabel = new JLabel("(Manage User)");
		
		JPanel manageUserLabelPanel = new JPanel();
		manageUserLabelPanel.add(manageUserManageLabel);
		
		JPanel manageUserMemberInfoPanel = new JPanel();
		manageUserMemberInfoPanel.setLayout(new BorderLayout());
		manageUserMemberInfoPanel.add(BorderLayout.NORTH, manageUserIDPanel);
		manageUserMemberInfoPanel.add(BorderLayout.SOUTH, manageUserInfoPanel);
		
		JPanel manageUserButtonsPanel = new JPanel();
		manageUserButtonsPanel.setLayout(new BorderLayout());
		manageUserButtonsPanel.add(BorderLayout.NORTH, manageUserManageButtonPanel);
		manageUserButtonsPanel.add(BorderLayout.CENTER, manageUserRenewDeletePanel);
		manageUserButtonsPanel.add(BorderLayout.SOUTH, manageUserLabelPanel);
		
		JPanel manageSouthPanel = new JPanel();
		manageSouthPanel.setLayout(new BorderLayout());
		manageSouthPanel.add(BorderLayout.NORTH, checkedOutPanel);
		manageSouthPanel.add(BorderLayout.SOUTH, manageUserButtonsPanel);
		
		manageUsersPanel.add(BorderLayout.NORTH, manageUserMemberInfoPanel);
		manageUsersPanel.add(BorderLayout.SOUTH, manageSouthPanel);

//----------------------END MANAGER MEMBER FRAME SETUP---------------------------------//
		
//----------------------MANAGE MEMBER POPUP WINDOW SETUP-------------------------------//

		JFrame manageMemberFrame = new JFrame();
		JPanel memberPopupPanel = new JPanel();
		memberPopupPanel.setLayout(new BorderLayout());
		JPanel memberPopupNamePanel = new JPanel();
		JPanel memberPopupEmailPanel = new JPanel();
		JPanel memberPopupButtonPanel = new JPanel();
		
		JLabel memberPopupNameLabel = new JLabel("NAME: ");
		JTextField memberPopupNameText = new JTextField(10);
		
		JLabel memberPopupEmailLabel = new JLabel("EMAIL: ");
		JTextField memberPopupEmailText = new JTextField(10);
		
		JButton memberPopupButton = new JButton("Update");
		
		memberPopupNamePanel.add(memberPopupNameLabel);
		memberPopupNamePanel.add(memberPopupNameText);
		memberPopupEmailPanel.add(memberPopupEmailLabel);
		memberPopupEmailPanel.add(memberPopupEmailText);
		memberPopupButtonPanel.add(memberPopupButton);
		
		memberPopupPanel.add(BorderLayout.NORTH, memberPopupNamePanel);
		memberPopupPanel.add(BorderLayout.CENTER, memberPopupEmailPanel);
		memberPopupPanel.add(BorderLayout.SOUTH, memberPopupButtonPanel);
		
		manageMemberFrame.add(memberPopupPanel);
		manageMemberFrame.setLocation(300, 300);
		manageMemberFrame.pack();
		
//----------------------END MANAGE MEMBER POPUP WINDOW--------------------------------//
		
//----------------------CREATE USER PANEL SETUP---------------------------------------//
		
		createUserPanel.setLayout(new BorderLayout());
		
		JPanel createUserSouthPanel = new JPanel();
		 createUserSouthPanel.setLayout(new BorderLayout());
		JPanel createUserInfoPanel = new JPanel();
		createUserInfoPanel.setLayout(new BorderLayout());
		JPanel createUserCenterPanel = new JPanel();
		createUserCenterPanel.setLayout(new BorderLayout());
		
		JPanel createUserNamePanel = new JPanel();
		JLabel createUserNameLabel = new JLabel("Name: ");
		JTextField createUserNameText = new JTextField(10);
		createUserNamePanel.add(createUserNameLabel);
		createUserNamePanel.add(createUserNameText);
		
		JPanel createUserEmailPanel = new JPanel();
		JLabel createUserEmailLabel = new JLabel("EMail: ");
		JTextField createUserEmailText = new JTextField(10);
		createUserEmailPanel.add(createUserEmailLabel);
		createUserEmailPanel.add(createUserEmailText);

		createUserInfoPanel.add(BorderLayout.NORTH, createUserNamePanel);
		createUserInfoPanel.add(BorderLayout.SOUTH, createUserEmailPanel);
		
		JPanel createUserLabelPanel = new JPanel();
		JLabel createUserLabel = new JLabel("(Create User)");
		createUserLabelPanel.add(createUserLabel);
		
		JPanel createUserButtonPanel = new JPanel();
		JButton createUserButton = new JButton("Create");
		createUserButtonPanel.add(createUserButton);
		
		JPanel createUserAreaPanel = new JPanel();
		JTextArea createUserArea = new JTextArea("[Resulting Database Entry Here]");
		createUserArea.setSize(100, 100);
		createUserAreaPanel.add(createUserArea);
		
		JPanel createUserUpdatePanel = new JPanel();
		JButton createUserUpdateButton = new JButton("Update Database Entry Preview");
		createUserUpdatePanel.add(createUserUpdateButton);
		
		createUserCenterPanel.add(BorderLayout.NORTH, createUserUpdatePanel);
		createUserCenterPanel.add(BorderLayout.SOUTH, createUserAreaPanel);
		
		createUserSouthPanel.add(BorderLayout.NORTH, createUserButtonPanel);
		createUserSouthPanel.add(BorderLayout.SOUTH, createUserLabelPanel);
		
		createUserPanel.add(BorderLayout.NORTH, createUserInfoPanel);
		createUserPanel.add(BorderLayout.CENTER, createUserCenterPanel);
		createUserPanel.add(BorderLayout.SOUTH, createUserSouthPanel);
		
//----------------------END CREATE USER PANEL-----------------------------------------//
		
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
		
		panelSwitch switcher = new panelSwitch() {
			
			@Override
			public void panelSwap(int index) {
				
				//changes the current panel
				//removes everything from the current frame
				libFrame.getContentPane().removeAll();
				//adds the ith panel according to the button presses
				libFrame.getContentPane().add(BorderLayout.NORTH, panelArray[index]);
				//adds the foot panel with the time, date, and increment button
				libFrame.getContentPane().add(BorderLayout.SOUTH, footPanel);
				//ensures everything is added to frame, repacks it, and sets it visible
				libFrame.revalidate();
				libFrame.pack();
				
			}
			
		};
			
//--------------------------------END FRAME DISPLAY---------------------------------------------------------//
				
//--------------------------------FUNCTION-------------------------------------------------------------------//

		//gives action to button on Search Panel
		searchSearchButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				bookListModel.clear();
				
				//temporary string variables for testing
				String searchISBN = searchISBNField.getText();
				String searchTitle = searchTitleField.getText();
				String searchAuthor = searchAuthorField.getText();
				
				if(!searchISBN.isEmpty() || !searchTitle.isEmpty() || !searchAuthor.isEmpty()) {
					
					//creates a test element in the list using the string variables
					bookListModel.addElement("" + searchISBN + "   " + searchTitle + "    " + searchAuthor);
					
					//PLACE SQL CODE NEEDED HERE
					
					//System.out.println(searchISBN + "     " + searchTitle + "     " + searchAuthor);
					
					searchCatalogFrame.setVisible(true);
					
					
				}
				
				else {
					
					bookListModel.addElement("SEARCH IS EMPTY");
					
					searchCatalogFrame.setVisible(true);
					
				}

			}
		
		});
		
		//gives action to "Search ISBN" button
		searchSelectButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				//PLACE QUERY CODE HERE
				
				searchCatalogFrame.dispose();	
				
			}
				
		});
		
		//gives action to "Manage" button on user panel
		manageUserManageButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				manageMemberFrame.setVisible(true);
				
			}
		
		});	
		
		//gives action to search selection button on search panel
		searchSelectButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				//gets the index of the selected item in the list and prints it to console
				System.out.println(bookList.getSelectedIndex());
				
			}
		
		});
		
		searchBooks.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				switcher.panelSwap(3);

			}
		
		});
		
		//updates system date and time throughout operation of program
		while (true) {
			
			//updates date and time fields
			//*****date and return date fields are in this section for the possibility in which the system is used at midnight, where
			//otherwise the system date and therefore the return date would not properly update to match the correct dates.********
			dateField.setText(dateFormat.format(LocalDate.now()));
			timeField.setText(timeFormat.format(LocalTime.now()));
			
			//updates the return date field to show current date plus 14 days
			returnDateText.setText(dateFormat.format(LocalDate.now().plusDays(14)));
			
			//searchCatalogFrame.setVisible(true);
			
		}
		
		
//-------------------------------END FUNCTION-----------------------------------------------------------------//
		
	}

}
