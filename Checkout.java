package data;

import java.time.LocalDate;
import java.time.format.*;
import java.time.temporal.ChronoUnit;

public class Checkout {
	
	// Amount due per day overdue
	public static final double COST_PER_DAY = 0.15;
	
	// Fields declared in same order as SQL
	private String ISBN;
	private int readerID;
	private LocalDate dueDate;
	private boolean renewed;
	
	// Create generic checkout
	public Checkout()
	{
		ISBN = "NULL";
		readerID = 0;
		dueDate = LocalDate.now();
		dueDate = dueDate.plusWeeks(2);
		renewed = false;
	}
	
	// Used for actually checking out a book
	public Checkout(String isbn, int rID)
	{
		ISBN = isbn;
		readerID = rID;
		dueDate = LocalDate.now();
		dueDate = dueDate.plusWeeks(2);
		renewed = false;
	}
	
	// Used for reading checkouts from the database
	public Checkout(String bn, int rID, String dDate, boolean r)
	{
		ISBN = bn;
		readerID = rID;
		dueDate = LocalDate.parse(dDate, DateTimeFormatter.ofPattern("E, MMM dd yyyy"));
		renewed = r;
	}
	
	// Getters and Setters
	public String getISBN() {return ISBN;}
	public int getReaderID() {return readerID;}
	public LocalDate getDueDate() {return dueDate;}
	public boolean isRenewed() {return renewed;}
	
	public void setISBN(String isbn) {
		if (isbn.chars().allMatch(Character::isDigit) && ISBN.length() == 13) 
		{
			ISBN = isbn;
		} else {System.out.println("ERROR: ISBN input format incorrect.");}
	}
	public void setReaderID(int rID) {
		if (rID > 0) {
			readerID = rID;
		}
	}
	public void setDueDate(LocalDate dDate) {
		if (dDate.compareTo(LocalDate.now()) >= 0)
			dueDate = (LocalDate)dDate.adjustInto(dueDate);
	}
	public void setRenewed(boolean rwd) {
		renewed = rwd;
	}
	
	// Gets the total number of days that the book is overdue
	public int daysSinceDue() {
		if (getDueDate().isBefore(LocalDate.now()))
			return (int)ChronoUnit.DAYS.between(getDueDate(), LocalDate.now());
		else
			return 0;
	}
	
	public void print() {
		System.out.println("ISBN of book: "+ISBN
				+"\nMember Name: "+readerID
				+"\nDue Date: "+dueDate
				+"\nRenewed: "+renewed);
		System.out.println();
	}
	
	// Formats the checkout for SQL writing
	public String toString() {
		String renewedVal = renewed ? "True":"False";
		return '\''+ISBN + "', " + readerID + ", '" + formatDate() + "', " + renewedVal;
	}
	
	public String formatDate() {
		return dueDate.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy"));
	}
	
	public boolean isValid() {
		return ISBN.chars().allMatch(Character::isDigit) && ISBN.length() == 13 && readerID > 0;
	}

}
