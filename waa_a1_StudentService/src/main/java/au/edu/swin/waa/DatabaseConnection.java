package au.edu.swin.waa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseConnection
{
	
	 private Connection connect = null;
	 //private Statement statement = null;
	 private PreparedStatement preparedStatement = null;
	 private ResultSet resultSet = null;
	
	public void createConnection() throws SQLException
	{
		//check driver
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		System.out.println("MySQL JDBC Driver Registered!");
		connect = null;
		
		//
		try 
		{
		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/waa_a1", "root","root");
		}
		catch(SQLException e)
		{
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		
		if (connect != null) 
		{
			System.out.println("You made it, take control your database now!");
		}
		else
		{
			System.out.println("Failed to make connection!");
		}
		
	}
	
	public void closeConenction() throws SQLException
	{
		connect.close();
	}
	
	public void createStudent(int studentId, String fullName, int pin) throws SQLException
	{
		String query = "insert into student(student_id,full_name,pin)"
				+ " values (?,?,?)";
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, studentId);
		preparedStatement.setString(2, fullName);
		preparedStatement.setInt(3, pin);
		preparedStatement.executeUpdate();
		
	}
	
	public Student getStudent(int studentId, int pin) throws SQLException
	{
//		System.out.println("SELECT *"
//		 		+ " from student "
//		 		+ "where student_id ="+studentId+" and pin="+pin+";");
		 preparedStatement = connect.prepareStatement("SELECT *"
		 		+ " from student "
		 		+ "where student_id ="+studentId+" and pin="+pin+";");
         resultSet = preparedStatement.executeQuery();
         Student studentReturn = demoWriteResultSet(resultSet);
         return studentReturn;
         /*// Remove again the insert comment
         preparedStatement = connect
         .prepareStatement("delete from feedback.comments where myuser= ? ; ");
         preparedStatement.setString(1, "Test");
         preparedStatement.executeUpdate();

         resultSet = statement
         .executeQuery("select * from feedback.comments");
         writeMetaData(resultSet);*/
	}
	 private Student demoWriteResultSet(ResultSet resultSet) throws SQLException 
	 {
	        Student student = new Student();
		 	while (resultSet.next()) {
	            int studentId = resultSet.getInt("student_id");
	            int pin = resultSet.getInt("pin");
	            String fullName = resultSet.getString("full_name");
	            
	            //setting student to be returned
	            student.setStudentId(studentId);
	            student.setPin(pin);
	            student.setFullName(fullName);
	            
	            /*Date date = resultSet.getDate("datum");
	            String comment = resultSet.getString("comments");*/
	            System.out.println("StudentID: " + studentId);
	            System.out.println("pin: " + pin);
	            System.out.println("fullName: " + fullName);   
	        }
		 	return student;
	 }
	
}