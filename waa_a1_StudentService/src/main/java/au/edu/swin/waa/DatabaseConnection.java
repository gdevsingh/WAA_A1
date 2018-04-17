package au.edu.swin.waa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void createConnection() throws SQLException {
		// check driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC MISSING!");
			e.printStackTrace();
			return;
		}
		System.out.println("MySQLJDBCDriver Registered!");
		connect = null;

		//
		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/waa_a1", "root", "root");
		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
			return;
		}

		if (connect != null) {
			System.out.println("Connection Successful!");
		} else {
			System.out.println("Failed to conenct to DB");
		}

	}

	public void closeConenction() throws SQLException {
		connect.close();
	}

	public void createStudent(int studentId, String fullName, int pin)
			throws SQLException {
		String query = "insert into student(student_id,full_name,pin)"
				+ " values (?,?,?)";
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setInt(1, studentId);
		preparedStatement.setString(2, fullName);
		preparedStatement.setInt(3, pin);
		preparedStatement.executeUpdate();

	}

	public Student getStudent(int studentId, int pin) throws SQLException {
		preparedStatement = connect.prepareStatement("SELECT *"
				+ " from student " + "where student_id =" + studentId
				+ " and pin=" + pin + ";");
		resultSet = preparedStatement.executeQuery();
		Student studentReturn = demoWriteResultSet(resultSet);
		return studentReturn;
	}

	private Student demoWriteResultSet(ResultSet resultSet) throws SQLException {
		Student student = new Student();
		while (resultSet.next()) {
			int studentId = resultSet.getInt("student_id");
			int pin = resultSet.getInt("pin");
			String fullName = resultSet.getString("full_name");

			student.setStudentId(studentId);
			student.setPin(pin);
			student.setFullName(fullName);

			System.out.println("StudentID: " + studentId);
			System.out.println("pin: " + pin);
			System.out.println("fullName: " + fullName);
		}
		return student;
	}

}