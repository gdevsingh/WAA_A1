package au.edu.swin.waa;

import javax.xml.bind.JAXBException;

import org.apache.axis2.AxisFault;

public class UI 
{
	
	
	public UI()
	{
	
	}
	public static void main(String args[]) throws Exception
	{
		LibController lc = new LibController();
		UI ui = new UI();
		
		int getOut=-1;
        while(getOut!=0)
        {
            switch(menu())
            {
                case "1":ui.viewAllBooks(lc);break;
                case "2":ui.borrowBook(lc);break;
                case "3":ui.requestBook(lc);break;
                case "4":ui.viewStudentRecords(lc);break;
                case "0":getOut=0;System.out.println("Bye :)");break;
                default:IOSupport.printIt("\n***\nPlease enter a valid choice!\n***\n");
            }   
        }
               
	}
	public static String menu()
    {
        System.out.println("------Welcome to UniLib-----------------");
        System.out.println("1) View all books");
        System.out.println("2) Borrow a book");
        System.out.println("3) Request a book");
        System.out.println("4) Check my ordered/borrowed books");
        System.out.println("----------------------------------------");
        
        return IOSupport.getString("Choose an option:").trim();    
    }
	
	void viewAllBooks(LibController lc) throws AxisFault, JAXBException
	{
		IOSupport.printIt(lc.viewAllBooks());
		
	}
	void borrowBook(LibController lc) throws AxisFault, JAXBException
	{
		String aStudentId = IOSupport.getString("Enter your student ID:"),
		aPin = IOSupport.getString("Enter your 4-digit student Pin:"),
		aBookId = IOSupport.getString("Enter the book ID you wish to borrow:");
		IOSupport.printIt(lc.borrowBook(aStudentId, aPin, aBookId));
		
	}
	void requestBook(LibController lc) throws Exception
	{
		String aStudentId = IOSupport.getString("Enter your student ID:"),
		aPin = IOSupport.getString("Enter your 4-digit student Pin:"),
		aISBN = IOSupport.getString("Enter the book ISBN you wish to borrow:");
				
		IOSupport.printIt(lc.requestBook(aStudentId, aPin, aISBN));
		
	}
	void viewStudentRecords(LibController lc) throws AxisFault, JAXBException
	{
		String aStudentId = IOSupport.getString("Enter your student ID:"),
		aPin = IOSupport.getString("Enter your 4-digit student Pin:");
		IOSupport.printIt(lc.viewStudentRecords(aStudentId, aPin));
	}
	
	

}
