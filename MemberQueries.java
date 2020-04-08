package SQLFunctions;

import data.Member;

//List of static methods for formatting MySQL queries for getting members from the database
public class MemberQueries {
	
	public static final int RESULT_LIMIT = 30;	// The maximum number of search results that can be returned
	
	public static String getMembers(int id) {return "SELECT * FROM members WHERE ID = "+ id + " ORDER BY username";}
	
	public static String getMembers(String id, String name, String email) {
		String query = "SELECT * FROM members WHERE ";
		String andStr = "";
		if (!id.isEmpty()) {
			query += "ID = "+id;
			andStr = " AND ";
		}
		if (!name.isEmpty()) {
			query += andStr+"username LIKE '%"+name+"%'";
			andStr = " AND ";
		}
		if (!email.isEmpty()) {
			query += andStr+"email LIKE '%"+email+"%'";
		}
		return query+" ORDER BY username LIMIT "+RESULT_LIMIT;
		}
	
	public static String getMembers() {return "SELECT * FROM members LIMIT "+RESULT_LIMIT;}
	
	public static String addMember(Member memberToWrite) {
		if (memberToWrite.isValid()) {
			String query = "INSERT INTO members (username, email, membership_end) VALUES (";
			query += memberToWrite.toString();
			query += ')';
			return query;
		} else {
			return "ERROR: query cannot be made due to formatting reasons";
		}
	}
	
	public static String deleteMember(int id) {
		return "DELETE FROM members WHERE id = "+id;
	}
	
	public static String editMember(Member editor) {
		if(editor.isValid()) { 
			String query = "UPDATE members SET"
					+ " username = '"+editor.getName()
					+ "', email = '"+editor.getEmail()
					+ "', membership_end = '"+editor.formatDate()
					+ "', dues = "+editor.getDues()
					+ " WHERE id = "+editor.getID();
			return query;
		}
		else return "ERROR: query cannot be made due to formatting reasons";
	}
	
	public static String setDues(int id, double dues) {
		return "UPDATE members SET dues = "+dues+" WHERE id = "+id;
	}

}
