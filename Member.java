package data;

import java.time.LocalDate;
import java.time.format.*;

import java.text.NumberFormat;
import java.util.Locale;

public class Member {
	
	// Fields declared in same order as SQL
	private int ID;
	private String name;
	private LocalDate endMembership;
	private double dues = 0;
	private String email;
	
	// Lorem Ipsum member constructor
	public Member() 
	{
		ID = 1;
		name = "Michael Bego";
		endMembership = LocalDate.now();
		endMembership = endMembership.plusYears(3);
		email = "N/A";
	}
	
	// Constructor for adding to the database; ID will auto-assign so it's not necessary
	public Member(String n, String eml) 
	{
		ID = 1;
		name = n;
		endMembership = LocalDate.now().plusYears(3);
		email = eml;
	}
	
	// Constructor for reading members from the database
	public Member(int id, String n, String endM, double ds, String eml) 
	{
		ID = id;
		name = n;
		endMembership = LocalDate.parse(endM, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		dues = ds;
		email = eml;
	}
	
	// Constructor for cloning a member
	public Member(Member copyFrom) 
	{
		ID = copyFrom.getID();
		name = copyFrom.getName();
		endMembership = copyFrom.getMembershipEndDate();
		dues = copyFrom.getDues();
		email = copyFrom.getEmail();
	}
	
	// Getters and setters
	public int getID() {return ID;}
	public String getName() {return name;}
	public LocalDate getMembershipEndDate() {return endMembership;}
	public double getDues() {return dues;}
	public String getEmail() {return email;}
	
	public void setID(int id) {ID = id;}
	public void setName(String n) {name = n;}
	public void setEndOfMembership(LocalDate eMem) {endMembership = eMem;}
	public void setDues(double d) {
		if (d >= 0)
			dues = d;
		else
			System.out.println("ERROR: dues cannot be less than zero");
	}
	public void setEmail(String e) {email = e;}
	
	// Used for writing the member to SQL
	public String toString() {
		return '\'' + name + "', '" + email + "', '" + formatDate() + '\'';
	}
	
	// Checks if the member will return a valid toString for SQL writes
	public boolean isValid() {
		return (ID > 0 && name != null && name.length() <= 40 && email != null && email.length() <= 60 && dues >= 0);
	}
	
	// Formats the date into the correct string format for SQL
	public String formatDate() {
		return endMembership.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	// Formats the date into a user-friendly format
		public String readableDate() {
			return endMembership.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy"));
		}
	
	// Updates the end of membership to the current date plus 3 years
	public void updateEndOfMembership() {endMembership = LocalDate.now().plusYears(3);}
	
	// Formats the dues to the form $(dollars).(cents) ex: $4.00
	public String formatDues() {
		return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(dues);
	}
	
	public String print() {
		return "ID: "+ID
				+"\nName: "+name
				+"\nMembership End Date: "+formatDate()
				+"\nDues: "+formatDues()
				+"\nUser Email: "+email;
	}
	
}
