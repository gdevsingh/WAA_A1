package au.edu.swin.waa;

import javax.xml.bind.JAXBException;

import org.apache.axis2.AxisFault;

public class LibController {

	public String viewAllBooks()
	{
		return "";
	}
	
	public String borrowBook()
	{
		return "";
	}
	
	public String requestBook()
	{
		return "";
	}
	
	public String viewStudentRecords()
	{
		return "";
	}
	public static void main(String args[]) throws AxisFault, JAXBException
	{
		
		StudentServiceSOAPClient ss = new StudentServiceSOAPClient();
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
	}
	
}
