package SQLFunctions;

import data.Book;

// List of static methods for formatting MySQL queries for getting books from the database
public class BookQueries {
	
	public static final int RESULT_LIMIT = 30;	// The maximum number of search results that can be returned
	
	
	//TODO: change functionality to make searches specific depending on entered fields
	public static String getBooks(String isbn, String title, String author) {
		// Start of query
		String query = "SELECT * FROM books WHERE ";
		
		// Initialize that there are no additional clauses
		String andStr = "";
		
		// Check if ISBN exists, then allow for next existent field to AND itself on
		if (!isbn.isEmpty()) {
			query += "ISBN = '"+isbn+"' ";
			andStr = "AND ";
		}
		
		// Check if title exists, then allow for author to AND itself on
		if (!title.isEmpty()) {
			query += andStr + "title LIKE '%"+title+"%' ";
			andStr = "AND ";
		}
		
		// Check if author exists
		if (!author.isEmpty())
			query += andStr + "author LIKE '%"+author+"%' ";
		
		// Set a limit to the number of book results returned
		return query+"LIMIT "+RESULT_LIMIT;
	}
	public static String getBooks() {return "SELECT * FROM books LIMIT "+RESULT_LIMIT;}
	
	public static String addBook(Book bookToWrite) {
		if (bookToWrite.isValid()) {
			String query = "INSERT INTO books VALUES (";
			query += bookToWrite.toString();
			query += ')';
			return query;
		} else {
			return "ERROR: query cannot be made due to formatting reasons.";
		}
	}
	
	public static String deleteBook(String ISBN) {
		return "DELETE FROM books WHERE ISBN = '"+ISBN+'\'';
	}
	
	public static String editBook(Book editor) {
		if (editor.isValid()) {
			String query = "UPDATE books SET"
					+ " title = '"+editor.getTitle()
					+ "' author = '"+editor.getAuthor()
					+ "' copies = "+editor.getCopies()
					+ " checked = "+editor.getCheckedOut()
					+ " WHERE ISBN = '"+editor.getISBN()+'\'';
			return query;
		}
		else return "ERROR: query cannot be made due to formatting reasons.";
	}
	
	public static String updateCheckedOut(String isbn, int checked) {
		String isbnString = '\''+isbn+'\'';
		return "UPDATE books SET checked = "+checked+" WHERE ISBN = "+isbnString;
	}

}
