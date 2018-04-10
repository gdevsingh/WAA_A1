package au.edu.swin.waa;

import java.sql.SQLException;


public class StudentServiceSOAP{
	

	/*public static void main(String args[]) throws SQLException
	{
		PostalCodeServiceSOAP pcs = new PostalCodeServiceSOAP();
		pcs.addStudent(1, "1", 1);
	}*/
		// This map contains a list of association between postCode and suburb name
		
		// Add a suburb to the database
		DatabaseConnection dbConn = new DatabaseConnection();
		
		public String addStudent(int studentId, String string, int pin) throws SQLException 
		{
			dbConn.createConnection();
			if(!dbConn.getStudent(studentId, pin).getFullName().equals(""))
			{
				dbConn.closeConenction();
				return "Student already exists!";
			}
			else
			{
				dbConn.createStudent(studentId, string, pin);
				dbConn.closeConenction();
				return "Student created successfully!";
			}
			
		}	
		
		
		
		/*// Update an existing suburb
		public String updateSuburb(Integer postCode, String suburbName) {
		if (map.keySet().contains(postCode)) {
		map.put(postCode, suburbName);
		return "The postcode " + postCode + " has been updated";
		}
		return "The postcode " + postCode + " does not exist!";
		}*/
		
		// Retrieve a suburb name from a postCode
		public String validateStudent(int studentId, int pin) throws SQLException {
			dbConn.createConnection();
			Student student = dbConn.getStudent(studentId, pin);
			if(student.getFullName().equals(""))
			{
				dbConn.closeConenction();
				return "No such student exists!";
			}
			else
			{	
				dbConn.closeConenction();
				return "Student Found!";
			}
			
		}
		
		/*// Delete an existing suburb
		public String deleteSuburb(Integer postCode) {
		if (map.keySet().contains(postCode)) {
		map.remove(postCode);
		return "The postcode " + postCode + " has been deleted!";
		}
		return "The postcode " + postCode + " does not exist!";
		}*/
	
}