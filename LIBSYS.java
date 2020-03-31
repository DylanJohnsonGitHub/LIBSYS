package lib;

import java.sql.*;
import java.util.ArrayList;

import SQLFunctions.*;
import data.*;

public class LIBSYS {
	
	private Statement statementManager;
	private Connection dataConnection;
	private ArrayList<Book> localBooks = new ArrayList<Book>();
	private ArrayList<Member> localMembers = new ArrayList<Member>();
	private ArrayList<Checkout> localCheckouts = new ArrayList<Checkout>();
	private Book currentBook;
	private Member currentMember;
	private Checkout currentCheckout;
	
	// Open a connection to the database
	public LIBSYS() 
	{
		try {
			dataConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/libsys", "root", "SimpCity");
			dataConnection.setAutoCommit(false);
			statementManager = dataConnection.createStatement();
			statementManager.execute("START TRANSACTION;");
		} catch (Exception ex) {
			System.out.println("An error has occurred. The database could not be reached."
							+"\nPlease close the program, ensure the database has been started correctly, and try again.");
			System.exit(1);
		}
		currentBook = new Book();
		currentMember = new Member();
		currentCheckout = new Checkout();
		
	}
	
	public int getLocalBooksLength() {
		return localBooks.size();
	}
	
	// Commits changes made to the database
	private void commit() {
		try {
			dataConnection.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Set the currently selected book's 
	public void updateCurrentBook(String meta, boolean title) {
		if(title)
			currentBook.setTitle(meta);
		else
			currentBook.setAuthor(meta);
	}
	
	// Use the currently selected book to update the same ISBN's data in the database
	public boolean commitBookUpdate() {
		try {
			if (currentBook.isValid()) {
				statementManager.executeUpdate(BookQueries.editBook(currentBook));
				commit();
				return true;
			}
			else return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Search the database for books, then put returned books into the local book list
	public boolean getBooksFromDB(String isbn, String title, String author) {
		try {
			ResultSet books = statementManager.executeQuery(BookQueries.getBooks(isbn, title, author));
			localBooks.clear();
			if (books.next()) {
				do {
					currentBook = new Book(
							books.getString("ISBN"), 
							books.getString("title"), 
							books.getString("author"), 
							books.getInt("copies"), 
							books.getInt("checked")
							);
					localBooks.add(currentBook);
				} while (books.next());
				books.close();
				return true;
			}
			books.close();
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
			}
	}
	
	// Add a new book to the database constructed from the values entered
	public boolean addBookToDB(String isbn, String title, String author, int copies) {
		currentBook = new Book(isbn, title, author, copies, 0);
		try {
			if (!statementManager.executeQuery("SELECT 1 FROM books WHERE ISBN = " + currentBook.getISBN()).isBeforeFirst() && currentBook.isValid()) {
				statementManager.executeUpdate(BookQueries.addBook(currentBook));
				commit();
				return true;
			}
			else return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Remove currently selected book from the database
	public boolean removeBookFromDB() {
		try {
			if (statementManager.executeQuery("SELECT 1 FROM books WHERE ISBN = "+currentBook.getISBN()+';').isBeforeFirst()) {
				statementManager.executeUpdate(BookQueries.deleteBook(currentBook.getISBN()));
				commit();
				return true;
			}
			else return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Return a string of all the books collected from the database
	public String[] printBooks() {
		String temp[] = new String[localBooks.size()];
		int i = 0;
		for (Book book : localBooks)
			temp[i++] = printBookHTMLFormat(book);
		return temp;
	}
	
	private String printBookHTMLFormat(Book bookToPrint) {
		String temp = "<html>"
				+"-----------------------------------------------"
				+"<br>&emsp;ISBN:&emsp;"  + bookToPrint.getISBN()
				+"<br>&emsp;Title:&emsp;" + bookToPrint.getTitle()
				+"<br>&emsp;Author:&emsp;" + bookToPrint.getAuthor()
				+"<br>&emsp;Copies:&emsp;" + bookToPrint.getCopies()
				+"<br>&emsp;Copies Available:&emsp;" + (bookToPrint.getCopies()-bookToPrint.getCheckedOut())
				+"<br>-----------------------------------------------"
				+"</span></html>";
		return temp;
	}
	
	// Print all the members collected from the database
	public String printMembers() {
		String temp = "";
		for (int i = 0; i < localMembers.size(); i++)
			temp += localMembers.get(i).print();
		return temp;
	}
	
	public String printMember() {
		String temp = currentMember.getName() + '\n';
		temp += currentMember.getEmail() + '\n';
		return temp + currentMember.readableDate();
	}
	
	// Print all the checkouts collected from the database
	public void printCheckouts() {
		for (int i = 0; i < localCheckouts.size(); i++)
			localCheckouts.get(i).print();
	}
	
	// Get member either by using their member ID or their name
	public boolean getMembersFromDB(String meta, boolean searchByID) {
		try {
			ResultSet members;
			if(searchByID)
				members = statementManager.executeQuery(MemberQueries.getMembers(Integer.parseInt(meta)));
			else
				members = statementManager.executeQuery(MemberQueries.getMembers(meta));
			if (members.next())
			{
				do {
					currentMember = new Member(
							members.getInt("id"),
							members.getString("username"),
							members.getString("membership_end"),
							members.getDouble("dues"),
							members.getString("email")
							);
					localMembers.add(currentMember);
				} while (members.next());
				return true;
			}
			members.close();
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Get all members in the database
	public boolean getMembersFromDB() {
		try {
			ResultSet members = statementManager.executeQuery(MemberQueries.getMembers());
			if (members.next())
			{
				localMembers.clear();
				do {
					currentMember = new Member(
							members.getInt("id"),
							members.getString("username"),
							members.getString("membership_end"),
							members.getDouble("dues"),
							members.getString("email")
							);
					localMembers.add(currentMember);
				} while (members.next());
				return true;
			}
			members.close();
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Add a member to the database with specified name and email
	public boolean addMemberToDB(String name, String email) {
		currentMember = new Member(name, email);
		try {
			if (currentMember.isValid()) {
				statementManager.executeUpdate(MemberQueries.addMember(currentMember));
				commit();
				return true;
			}
			else return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Find and set currently selected book from the local books using the given ISBN
	public boolean selectBookFromLocal(int index) {
		if(localBooks.size() > 0) {
			currentBook = localBooks.get(index);
			return true;
		}
		return false;
	}
	
	public String[] getCurrentBookInfo() {
		String temp[] = new String[5];
		temp[0] = currentBook.getISBN();
		temp[1] = currentBook.getTitle();
		temp[2] = currentBook.getAuthor();
		temp[3] = ""+currentBook.getCopies();
		temp[4] = ""+(currentBook.getCopies()-currentBook.getCheckedOut());
		return temp;
	}
	
	// Set the currently selected member to one in the list with the given user ID
	public boolean selectMemberFromLocal(int id) {
		for (Member member : localMembers) 
		{
			if (id == member.getID()) {
				currentMember = new Member(member);
				return true;
			}
		}
		return false;
	}
	
	// Update an attribute of the current 
	public void updateCurrentMember(String meta, boolean setName) {
		if (setName)
			currentMember.setName(meta);
		else
			currentMember.setEmail(meta);
	}
	
	// Update the currently selected member's dues in the database, or reset it to zero
	public boolean updateCurrentMemberDues(boolean resetToZero) {
		if (resetToZero)
			currentMember.setDues(0);
		else
			calculateDuesCurrentMember();
		try {
			if (currentMember.isValid()) {
				statementManager.executeUpdate(MemberQueries.setDues(currentMember.getID(), currentMember.getDues()));
				commit();
				return true;
			}
			else return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Set the current member's dues equal to whatever the current due balance is plus their currently overdue checkouts
	public String calculateDuesCurrentMember() {
		double accumulator = currentMember.getDues();
		getCheckoutsFromDBInvolvingCurrentMember();
		for (Checkout checkout : localCheckouts) 
		{
			accumulator += 0.15 * checkout.daysSinceDue();
		}
		currentMember.setDues(accumulator);
		return currentMember.formatDues();
	}
	
	public boolean removeMemberFromDB() {
		try {
			if (statementManager.executeQuery("SELECT 1 FROM members WHERE ID = "+currentMember.getID()+';').isBeforeFirst()) {
				statementManager.executeUpdate(MemberQueries.deleteMember(currentMember.getID()));
				commit();
				return true;
			}
			else return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Commit the changes made to the local current member to the database
	public boolean commitMemberUpdate() {
		try {
			currentMember.updateEndOfMembership();
			if (currentMember.isValid()) {
				statementManager.executeUpdate(MemberQueries.editMember(currentMember));
				commit();
				return true;
			}
			else return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Check out the currently selected book for the currently selected member
	public boolean checkoutBookForMember() {
		if (currentBook.isValid() && currentBook.canCheckOut() && currentMember.isValid())
			currentCheckout = new Checkout(currentBook.getISBN(), currentMember.getID());
		else
			return false;
		try {
			if (currentCheckout.isValid())
				statementManager.executeUpdate(CheckoutQueries.addCheckout(currentCheckout));
			else return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
		currentBook.addCheckedOut();
		commitBookUpdate();
		return true;
	}
	
	// Return a book using the currently selected checkout as a reference
	public boolean returnBook() {
		try {
			if (statementManager.executeQuery
					("SELECT 1 FROM checkouts WHERE isbn = "+currentCheckout.getISBN()+" AND reader_id = "+currentCheckout.getReaderID()+';').isBeforeFirst() 
					&& getBooksFromDB(currentCheckout.getISBN(),"","")) {
				statementManager.executeUpdate(CheckoutQueries.removeCheckout(currentCheckout));
			}
			else return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		currentBook.removeCheckedOut();
		return true;
	}
	
	// Get all checkouts from the database
	public boolean getCheckoutsFromDB() {
		try {
			ResultSet checkouts = statementManager.executeQuery(CheckoutQueries.getCheckouts());
			if (checkouts.next())
			{
				localCheckouts.clear();
				do {
					currentCheckout = new Checkout(
							checkouts.getString("isbn"),
							checkouts.getInt("reader_id"),
							checkouts.getString("due_date"),
							checkouts.getBoolean("renewed")
							);
					localCheckouts.add(currentCheckout);
				} while (checkouts.next());
				checkouts.close();
				return true;
			}
			checkouts.close();
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	// Use the currently selected member to get all of the checkouts under their name
	public boolean getCheckoutsFromDBInvolvingCurrentMember() {
		try {
			ResultSet checkouts = statementManager.executeQuery(CheckoutQueries.getCheckouts(currentMember.getID()+"", 2));
			if (checkouts.next()) 
			{
				localCheckouts.clear();
				do {
					currentCheckout = new Checkout(
							checkouts.getString("isbn"),
							checkouts.getInt("reader_id"),
							checkouts.getString("due_date"),
							checkouts.getBoolean("renewed")
							);
					localCheckouts.add(currentCheckout);
				} while (checkouts.next());
				checkouts.close();
				return true;
			}
			checkouts.close();
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
