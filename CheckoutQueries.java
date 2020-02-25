package SQLFunctions;

import data.Checkout;

//List of static methods for formatting MySQL queries for getting books from the database
public class CheckoutQueries {
	
	public static void lookForReaderCheckouts(int reader_ID) {
		
	}
	
	public static String getCheckouts(String meta, int query_case) {
		String query = "SELECT * FROM checkouts WHERE ";
		switch (query_case) 
		{
		case 1:
			query += "isbn = '"+meta+'\'';
			break;
		case 2:
			query += "reader_id = "+meta;
			break;
		case 3:
			query += "due_date = '"+meta+'\'';
			break;
		default:
			return "Sorry, you have entered an invalid query type.";
		}
		return query;
	}
	
	public static String getCheckouts() {
		return "SELECT * FROM checkouts;";
	}
	
	public static String removeCheckout(Checkout checkToRemove) {
		return "DELETE FROM checkouts WHERE isbn = '"+ checkToRemove.getISBN()+"' and reader_id = "+checkToRemove.getReaderID()+';';
	}
	
	public static String addCheckout(Checkout checkoutToWrite) {
		if (checkoutToWrite.isValid()) {
			String query = "INSERT INTO checkouts VALUES (";
			query += checkoutToWrite.toString();
			query += " );";
			return query;
		} else {
			return "ERROR: query cannot be made due to formatting reasons";
		}
	}

}
