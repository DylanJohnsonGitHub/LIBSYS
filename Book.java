package data;

public class Book {
	
	// Fields declared in same order as SQL
	private String ISBN = "";
	private String Title = "";
	private String Author = "";
	private int copies = 1;			// Number of total copies owned by library
	private int checked = 0;		// Number of copies checked out
	
	// Lorem Ipsum book
	public Book() 
	{
		ISBN = "9781503280786";
		Title = "Moby Dick";
		Author = "Herman Melville";
		copies = 1;
		checked = 0;
	}
	
	public Book(String isbn, String title, String author, int cops, int chk)	//Used when adding book to the database
	{
		setISBN(isbn);
		setTitle(title);
		setAuthor(author);
		setCopies(cops);
		setCheckedOut(chk);
	}
	
	public Book(Book writeFrom) 	// Creates a new copied instance of the writeFrom Book
	{
		setISBN(writeFrom.getISBN());
		setTitle(writeFrom.getTitle());
		setAuthor(writeFrom.getAuthor());
		setCopies(writeFrom.getCopies());
		setCheckedOut(writeFrom.getCheckedOut());
	}
	
	// Get methods
	public String getISBN() {return ISBN;}
	public String getTitle() {return Title;}
	public String getAuthor() {return Author;}
	public int getCopies() {return copies;}
	public int getCheckedOut() {return checked;}
	
	// Set methods, designed to be respective of formatting
	public void setISBN(String isbn) {
		if (isbn.chars().allMatch(Character::isDigit) && isbn.length() == 13) 
			ISBN = isbn;
		else 
			System.out.println("ERROR: ISBN input format incorrect.");
	}
	public void setTitle(String newTitle) {
		if (newTitle.length() > 0 && newTitle.length() <= 6000)
			Title = newTitle;
		else 
			System.out.println("ERROR: Title input format incorrect.");
	}
	public void setAuthor(String newAuthor) {
		if(newAuthor.length() > 0 && newAuthor.length() <= 500)
			Author = newAuthor;
		else
			System.out.println("ERROR: Author input format incorrect.");
	}
	public void setCopies(int amount) {
		if (amount > 0)
			copies = amount;
		else
			System.out.println("ERROR: Amount of copies cannot be less than 1.");
	}
	public void setCheckedOut(int amount) {
		if (amount >= 0 && amount <= copies)
			checked = amount;
		else
			System.out.println("ERROR: Amount of copies checked out cannot be negative or greater than copies that exist.");
	}
	
	// Adds one to checkout if the book can be checked out
	public void addCheckedOut() {
		if (canCheckOut())
			checked++;
	}
	
	// Removes a copy from those checked out
	public void removeCheckedOut() {
		if (checked > 0)
			checked--;
	}
	
	// toString method, mainly used for query formatting purposes
	public String toString() {
		return "\"" + ISBN + "\", \"" + Title + "\", \"" + Author + "\", " + copies + ", " + checked;
	}
	
	// Checks to see if the book will return a valid toString for SQL query
	public boolean isValid() {
		return (ISBN.chars().allMatch(Character::isDigit) && ISBN.length() == 13 && Title != null && Author != null && copies > 0 && checked >= 0 && copies >= checked);
	}
	
	// Checks to see if a book has less checked out copies than exist
	public boolean canCheckOut() { return (copies - checked) > 0;}
	
	// Get a string for testing purposes
	public String print() {
		return "ISBN: "+ISBN
			+"\nTitle: "+Title
			+"\nAuthor: "+Author
			+"\nCopies: "+copies
			+"\nCopies Checked Out: "+checked
			+"\nCopies Available: "+(copies-checked)
			+'\n';
	}

}
