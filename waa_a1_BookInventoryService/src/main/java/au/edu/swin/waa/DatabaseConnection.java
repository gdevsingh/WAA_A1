package au.edu.swin.waa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

		try {
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/waa_a1", "root", "root");
		} catch (SQLException e) {
			System.out.println("Connection Failed! ");
			e.printStackTrace();
			return;
		}

		if (connect != null) {
			System.out.println("Connection Successful!");
		} else {
			System.out.println("Failed to connect to DB!");
		}

	}

	public void closeConenction() throws SQLException {
		connect.close();
	}

	public int createBook(Book book) throws SQLException {
		String query = "insert into books(book_title,author,isbn10, isbn13,"
				+ " publisher, publish_date, book_status)"
				+ " values (?,?,?,?,?,?,?)";
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setString(1, book.getTitle());
		preparedStatement.setString(2, book.getauthorsList());
		preparedStatement.setString(3, book.getIsbn10());
		preparedStatement.setString(4, book.getIsbn13());
		preparedStatement.setString(5, book.getPublisher());
		preparedStatement.setString(6, book.getPublishedDate());
		preparedStatement.setString(7, book.getStatus());

		// 0 for 0 rows updated n for n row(s)
		return (preparedStatement.executeUpdate());

	}

	public ArrayList<Book> getAllBooks() throws SQLException {
		preparedStatement = connect.prepareStatement("SELECT *"
				+ " from books ");
		resultSet = preparedStatement.executeQuery();
		ArrayList<Book> books = demoWriteResultSetForAllBooks(resultSet);
		return books;
	}

	public Book getBookById(String id) throws SQLException {
		preparedStatement = connect.prepareStatement("SELECT *"
				+ " from books " + "where book_id ='" + id + "';");
		resultSet = preparedStatement.executeQuery();

		Book bookReturn = demoWriteResultSet(resultSet);

		return bookReturn;
	}

	public Book getBookByISBN(String isbn) throws SQLException {
		String query = (isbn.length() == 10) ? (query = "SELECT *"
				+ " from books " + "where isbn10 ='" + isbn + "';")
				: (query = "SELECT *" + " from books " + "where isbn13 ='"
						+ isbn + "';");

		preparedStatement = connect.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		Book bookReturn = demoWriteResultSet(resultSet);
		return bookReturn;
	}

	public ArrayList<BorrowBook> getBookByStudent(String studentId)
			throws SQLException {
		ArrayList<BorrowBook> borrowBooks = new ArrayList<BorrowBook>();

		preparedStatement = connect
				.prepareStatement("SELECT book_id, book_status, date_time"
						+ " from book_borrow " + "where student_id ='"
						+ studentId + "';");

		resultSet = preparedStatement.executeQuery();
		borrowBooks = demoWriteResultSetForBorrow(resultSet);
		return borrowBooks;
	}

	public int updateBookStatus(String bookId, String status)
			throws SQLException {
		preparedStatement = connect.prepareStatement("update books"
				+ " set book_status = '" + status + "'" + " where book_id = '"
				+ bookId + "'");

		// return 0 for 0 rows updated and n for n row()
		return (preparedStatement.executeUpdate());

	}

	public int createBorrowBook(BorrowBook borrowBook) throws SQLException {
		String query = "insert into book_borrow(student_id, book_id, book_status)"
				+ " values (?,?,?)";
		preparedStatement = connect.prepareStatement(query);
		preparedStatement.setString(1, borrowBook.getStudentId());
		preparedStatement.setString(2, borrowBook.getBookId());
		preparedStatement.setString(3, borrowBook.getBookStatus());

		// 0 for 0 rows inserted n for n row(s)
		return (preparedStatement.executeUpdate());

	}

	private Book demoWriteResultSet(ResultSet resultSet) throws SQLException {
		Book book = new Book();
		while (resultSet.next()) {
			String bookId = resultSet.getInt("book_id") + "";
			String bookTitle = resultSet.getString("book_title");
			String bookAuthorsList = resultSet.getString("author");
			String bookPublisher = resultSet.getString("publisher");
			String bookPublishDate = resultSet.getString("publish_date");
			String bookStatus = resultSet.getString("book_status");
			String bookISBN10 = resultSet.getString("isbn10");
			String bookISBN13 = resultSet.getString("isbn13");

			book.setId(bookId);
			book.setTitle(bookTitle);
			book.setauthorsList(bookAuthorsList);
			book.setPublisher(bookPublisher);
			book.setPublishedDate(bookPublishDate);
			book.setStatus(bookStatus);
			book.setIsbn10(bookISBN10);
			book.setIsbn13(bookISBN13);

		}
		return book;
	}

	private ArrayList<Book> demoWriteResultSetForAllBooks(ResultSet resultSet)
			throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		while (resultSet.next()) {
			Book book = new Book();

			String bookId = resultSet.getInt("book_id") + "";
			String bookTitle = resultSet.getString("book_title");
			String bookAuthorsList = resultSet.getString("author");
			String bookPublisher = resultSet.getString("publisher");
			String bookPublishDate = resultSet.getString("publish_date");
			String bookStatus = resultSet.getString("book_status");
			String bookISBN10 = resultSet.getString("isbn10");
			String bookISBN13 = resultSet.getString("isbn13");

			book.setId(bookId);
			book.setTitle(bookTitle);
			book.setauthorsList(bookAuthorsList);
			book.setPublisher(bookPublisher);
			book.setPublishedDate(bookPublishDate);
			book.setStatus(bookStatus);
			book.setIsbn10(bookISBN10);
			book.setIsbn13(bookISBN13);

			books.add(book);

		}
		return books;
	}

	private ArrayList<BorrowBook> demoWriteResultSetForBorrow(
			ResultSet resultSet) throws SQLException {
		ArrayList<BorrowBook> borrows = new ArrayList<BorrowBook>();
		while (resultSet.next()) {
			BorrowBook borrow = new BorrowBook();

			String bookId = resultSet.getInt("book_id") + "";
			String bookStatus = resultSet.getString("book_status");
			String borrowDateTime = resultSet.getString("date_time");

			borrow.setBookId(bookId);
			borrow.setBookStatus(bookStatus);
			borrow.setBorrowDateTime(borrowDateTime);
			borrows.add(borrow);

		}
		return borrows;
	}

}