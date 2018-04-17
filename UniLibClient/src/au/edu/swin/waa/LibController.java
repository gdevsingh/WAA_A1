package au.edu.swin.waa;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.apache.axis2.AxisFault;

public class LibController {

	BookInventoryServiceREST bis = new BookInventoryServiceREST();
	StudentServiceSOAPClient ss = new StudentServiceSOAPClient();
	GoogleBooksServiceREST gbc = new GoogleBooksServiceREST();
	public static final String SS_ERROR1 = "Your pin should be a 4-digit number.";
	public static final String SS_ERROR2 = "Make sure your pin and student id is numeric.";
	public static final String SS_FOUND = "Student Found!";
	public static final String BIS_NOBOOKFOUND = "No such Book exists!";
	public static final String STATUS_A = "available";
	public static final String STATUS_B = "back order";
	public static final String STATUS_O = "on loan";
	public static final String NOT_NULL = "NOT_NULL";
	
	
	public String viewAllBooks() throws AxisFault, JAXBException
	{
		return bis.getAllBooks();
	}
	
	public String borrowBook(String aStudentId, String aPin, String aBookId) throws AxisFault, JAXBException
	{
		String data = ss.validateStudent(aStudentId, aPin);
		if(data.equals(SS_FOUND))
		{
			if(bis.getBookById(aBookId).contains("ID :"))
			{
				return bis.createBookBorrow(aStudentId, aBookId, STATUS_O)+
						"\n"+bis.updateBook(aBookId, STATUS_O);
				
			}
			else
			{
				return bis.getBookById(aBookId);
			}
		}
		else
		{
			return data;
		}
		
	}
	
	public String requestBook(String aStudentId, String aPin, String aISBN) throws Exception
	{
		HashMap<String, String> book = new HashMap<String, String>();
		
		String data = ss.validateStudent(aStudentId, aPin);
		//System.out.println("out \n");
		if(data.equals(SS_FOUND))
		{
			//System.out.println("in 1st if \n");
			if(bis.getBookByISBN(aISBN).equals(BIS_NOBOOKFOUND) ||
					bis.getBookByISBN(aISBN).contains(STATUS_B))
			{
				//System.out.println((gbc.getGoogleBook(aISBN)).get("status_g"));
				//System.out.println("in 2st if \n");
				
				if((book = gbc.getGoogleBook(aISBN)).get("status_g").equals(NOT_NULL))
				{
					//System.out.println("in 3rd if \n");
					String aTitle = book.get("idBook"),
					aAuthorList = book.get("authorsBook"),
					aISBN10 = book.get("isbn10Book"),
					aISBN13 = book.get("isbn13Book"),
					aPublisher = book.get("publisherBook"),
					aPublishedDate = book.get("publishDateBook"),
					aStatus = STATUS_B;
					
					String output = "";
					output = bis.addBook(aTitle, aAuthorList, aISBN10, aISBN13, aPublisher, aPublishedDate, aStatus) +"\n";
					String aBookId=bis.getSingleBookIdByISBN(aISBN13);
					output += bis.createBookBorrow(aStudentId, aBookId, aStatus) +"\n";
					output += "Your book has been ordered!";
					return output;
					
				}
				else
				{
					return book.get("status_g")+"(google books)";
				}
			}
			else
			{
				return "Book already exists in the library!";
			}
			//return bis.getBookByStudent(aStudentId);
		}
		else
		{
			return data;
		}
	}
	
	public String viewStudentRecords(String aStudentId, String aPin) throws AxisFault, JAXBException
	{
		String data = ss.validateStudent(aStudentId, aPin);
		if(data.equals(SS_FOUND))
		{
			return bis.getBookByStudent(aStudentId);
		}
		else
		{
			return data;
		}
	}
	/*public static void main(String args[]) throws Exception
	{
		LibController lc = new LibController();
		System.out.println(lc.requestBook("123", "1234", "9780647508541"));
		//System.out.println(lc.viewStudentRecords("1233","1234"));
		//System.out.println(lc.viewAllBooks());
		//System.out.println(lc.borrowBook("123", "1234", "66"));
		
		//GoogleBooksServiceREST gbs = new GoogleBooksServiceREST();
		//HashMap<String, String> hm = new HashMap<String, String>();
		//hm = gbs.getGoogleBook("0522845231");
		//System.out.println(hm.get("status"));
		
		//StudentServiceSOAPClient ss = new StudentServiceSOAPClient();
		//System.out.println(ss.addStudent("123aasdas45", "Gurdev SIngh", "1aas24"));
		//System.out.println(ss.validateStudent("123", "1234"));
		
		//BookInventoryServiceREST bis = new BookInventoryServiceREST();
		//System.out.println(bis.addBook("z", "z", "z", "z", "z", "z", "z"));
		//System.out.println(bis.createBookBorrow("2", "3", "on loan"));
		//System.out.println(bis.getAllBooks());
		//System.out.println(bis.getBookById("15"));//check it out after deploying new bis
		//System.out.println(bis.getBookByISBN("5"));//check it out after deploying new bis
		//System.out.println(bis.getBookByStudent("2"));
		//System.out.println(bis.updateBook("1", "just updated"));
	}*/
	
}
