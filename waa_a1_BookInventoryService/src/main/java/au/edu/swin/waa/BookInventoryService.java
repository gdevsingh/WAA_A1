package au.edu.swin.waa;

import java.sql.SQLException;
import java.util.ArrayList;


public class BookInventoryService{
	/*private String id;
	private String title;
	private String authorList;
	private String isbn10;
	private String isbn13;
	private String publisher;
	private String publishedDate;
	private String status;
	 * 
	 * 
	 * */
	DatabaseConnection dbConn = new DatabaseConnection();
	public BookInventoryService() {
		
		
	}
	
	// Add a book to the database
	public String addBook( String title, String authorList, String isbn10, 
			String isbn13, String publisher, String publishedDate, String status) throws SQLException
	{
		Book book = new Book();
		
		book.setauthorsList(authorList);
		book.setIsbn10(isbn10);
		book.setIsbn13(isbn13);
		book.setPublishedDate(publishedDate);
		book.setPublisher(publisher);
		book.setStatus(status);
		book.setTitle(title);
		
		dbConn.createConnection();
		if(dbConn.createBook(book)==0)
		{
			dbConn.closeConenction();
			return "No Book Added!";

		}
		else
		{			
			dbConn.closeConenction();
			return "Book added successfully!";
		}
	}
	
	// Update an existing Book
	public String updateBook(String bookId, String status) throws SQLException 
	{
		
		dbConn.createConnection();
		if(dbConn.updateBookStatus(bookId, status)==0)
		{
			dbConn.closeConenction();
			return "No such book exists, update failed !";
		}
		else
		{
			dbConn.closeConenction();
			return "Book status updated successfully!";
		}
		
	}
	public String createBookBorrow(String studentId, String bookId, String bookStatus) throws SQLException
	{
		BorrowBook borrow = new BorrowBook();
		borrow.setBookId(bookId);
		borrow.setBookStatus(bookStatus);
		borrow.setStudentId(studentId);
		
		dbConn.createConnection();
		
		if(dbConn.createBorrowBook(borrow)==0)
		{
			dbConn.closeConenction();
			return "No borrow details Added!";
		}
		else
		{
			dbConn.closeConenction();
			return "Borrow details added successfully!";
		}
	}
	
	
	// Retrieve a book by isbn 
	public String getBookByISBN(String ISBN) throws SQLException 
	{
		dbConn.createConnection();
		return "<Books>"+dbConn.getBookByISBN(ISBN).toString()+"</Books>";
	}
	
	// Retrieve a book by id
	public String getBookById(String id) throws SQLException 
	{
		dbConn.createConnection();
		return "<Books>"+dbConn.getBookById(id).toString()+"</Books>";
	}
	
		// Retrieve all books borrowed by student
	public String getBookByStudent(String studentId) throws SQLException 
	{
		
		ArrayList<BorrowBook> borrowBooks = new ArrayList<BorrowBook>();
		String data = "<BorrowBooks>";
		dbConn.createConnection();
		
		borrowBooks = dbConn.getBookByStudent(studentId);
		if(borrowBooks.size()==0)
		{
			data += "<BorrowBook><bookId>null</bookId></BorrowBook>";
		}
		else
		{
			for(int loop=0; loop<borrowBooks.size(); loop++)
			{
				data += borrowBooks.get(loop).toString();
			}
		}
		dbConn.closeConenction();
		return data+"</BorrowBooks>";
	}
	
	public String getAllBooks() throws SQLException 
	{
		dbConn.createConnection();
		
		ArrayList<Book> books = new ArrayList<Book>();
		String data = "<Books>";
		books = dbConn.getAllBooks();
		for(int loop=0; loop<books.size(); loop++)
		{
			data += books.get(loop).toString();
		}
		dbConn.closeConenction();
		return data+"</Books>";
	}
	
	/*public static void main(String args[]) throws SQLException
	{
	//	String data="&lt;books&gt;&lt;book&gt;&lt;id&gt;9&lt;/id&gt;&lt;title&gt;1&lt;/title&gt;&lt;authorsList&gt;1&lt;/authorsList&gt;&lt;isbn10&gt;1&lt;/isbn10&gt;&lt;isbn13&gt;2&lt;/isbn13&gt;&lt;publisher&gt;2&lt;/publisher&gt;&lt;publishedDate&gt;2&lt;/publishedDate&gt;&lt;status&gt;2&lt;/status&gt;&lt;/book&gt;&lt;book&gt;&lt;id&gt;9&lt;/id&gt;&lt;title&gt;1&lt;/title&gt;&lt;authorsList&gt;1&lt;/authorsList&gt;&lt;isbn10&gt;1&lt;/isbn10&gt;&lt;isbn13&gt;2&lt;/isbn13&gt;&lt;publisher&gt;2&lt;/publisher&gt;&lt;publishedDate&gt;2&lt;/publishedDate&gt;&lt;status&gt;2&lt;/status&gt;&lt;/book&gt;&lt;book&gt;&lt;id&gt;9&lt;/id&gt;&lt;title&gt;1&lt;/title&gt;&lt;authorsList&gt;1&lt;/authorsList&gt;&lt;isbn10&gt;1&lt;/isbn10&gt;&lt;isbn13&gt;2&lt;/isbn13&gt;&lt;publisher&gt;2&lt;/publisher&gt;&lt;publishedDate&gt;2&lt;/publishedDate&gt;&lt;status&gt;2&lt;/status&gt;&lt;/book&gt;&lt;book&gt;&lt;id&gt;9&lt;/id&gt;&lt;title&gt;1&lt;/title&gt;&lt;authorsList&gt;1&lt;/authorsList&gt;&lt;isbn10&gt;1&lt;/isbn10&gt;&lt;isbn13&gt;2&lt;/isbn13&gt;&lt;publisher&gt;2&lt;/publisher&gt;&lt;publishedDate&gt;2&lt;/publishedDate&gt;&lt;status&gt;2&lt;/status&gt;&lt;/book&gt;&lt;book&gt;&lt;id&gt;9&lt;/id&gt;&lt;title&gt;1&lt;/title&gt;&lt;authorsList&gt;1&lt;/authorsList&gt;&lt;isbn10&gt;1&lt;/isbn10&gt;&lt;isbn13&gt;2&lt;/isbn13&gt;&lt;publisher&gt;2&lt;/publisher&gt;&lt;publishedDate&gt;2&lt;/publishedDate&gt;&lt;status&gt;2&lt;/status&gt;&lt;/book&gt;&lt;book&gt;&lt;id&gt;9&lt;/id&gt;&lt;title&gt;1&lt;/title&gt;&lt;authorsList&gt;1&lt;/authorsList&gt;&lt;isbn10&gt;1&lt;/isbn10&gt;&lt;isbn13&gt;2&lt;/isbn13&gt;&lt;publisher&gt;2&lt;/publisher&gt;&lt;publishedDate&gt;2&lt;/publishedDate&gt;&lt;status&gt;2&lt;/status&gt;&lt;/book&gt;&lt;book&gt;&lt;id&gt;9&lt;/id&gt;&lt;title&gt;1&lt;/title&gt;&lt;authorsList&gt;1&lt;/authorsList&gt;&lt;isbn10&gt;1&lt;/isbn10&gt;&lt;isbn13&gt;2&lt;/isbn13&gt;&lt;publisher&gt;2&lt;/publisher&gt;&lt;publishedDate&gt;2&lt;/publishedDate&gt;&lt;status&gt;2&lt;/status&gt;&lt;/book&gt;&lt;book&gt;&lt;id&gt;9&lt;/id&gt;&lt;title&gt;1&lt;/title&gt;&lt;authorsList&gt;1&lt;/authorsList&gt;&lt;isbn10&gt;1&lt;/isbn10&gt;&lt;isbn13&gt;2&lt;/isbn13&gt;&lt;publisher&gt;2&lt;/publisher&gt;&lt;publishedDate&gt;2&lt;/publishedDate&gt;&lt;status&gt;2&lt;/status&gt;&lt;/book&gt;&lt;book&gt;&lt;id&gt;9&lt;/id&gt;&lt;title&gt;1&lt;/title&gt;&lt;authorsList&gt;1&lt;/authorsList&gt;&lt;isbn10&gt;1&lt;/isbn10&gt;&lt;isbn13&gt;2&lt;/isbn13&gt;&lt;publisher&gt;2&lt;/publisher&gt;&lt;publishedDate&gt;2&lt;/publishedDate&gt;&lt;status&gt;2&lt;/status&gt;&lt;/book&gt;&lt;/books&gt;";
		//System.out.println(StringEscapeUtils.unescapeHtml4(data));
		BookInventoryService bis = new BookInventoryService();
		//System.out.println(bis.addBook("a","a", "a", "a", "a", "a", "a"));
		//System.out.println(bis.updateBook("1", "b"));
		//System.out.println(bis.createBorrow("1","2", "c"));
		//System.out.println(bis.getBookById("1"));
		//System.out.println(bis.getBookByISBN("a"));
		//System.out.println(bis.getBookByStudent("1"));
		System.out.println(bis.getAllBooks());
		
	}*/
	
	
	
	
	
	
}