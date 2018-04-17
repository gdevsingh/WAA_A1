package au.edu.swin.waa;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;


public class BookInventoryServiceREST{

	/**
	 * @param args
	 * @throws AxisFault 
	 * @throws JAXBException 
	 */
	
	
	
	public String addBook(String aTitle, String aAuthorList, String aISBN10, String aISBN13, String aPublisher, String aPublishedDate, String aStatus ) throws AxisFault, JAXBException
	{
		ServiceClient sender = createSender();
		OMElement response = sender.sendReceive(createRequestPayloadAddBook(aTitle, aAuthorList, aISBN10, aISBN13, aPublisher, aPublishedDate, aStatus));
		return processResponsePayload(response);
	}
	
	public String updateBook(String aBookId, String aBookStatus) throws AxisFault, JAXBException
	{
		ServiceClient sender = createSender();
		OMElement response = sender.sendReceive(createRequestPayloadUpdateBook(aBookId, aBookStatus));
		return processResponsePayload(response);
	}
	
	public String createBookBorrow(String aStudentId, String aBookId, String aBookStatus) throws AxisFault, JAXBException
	{	
		ServiceClient sender = createSender();
		OMElement response = sender.sendReceive(createRequestPayloadCreateBookBorrow(aStudentId, aBookId, aBookStatus));
		return processResponsePayload(response);
	}
	
	public String getBookByISBN(String aISBN) throws AxisFault, JAXBException
	{
		ServiceClient sender = createSender();
		OMElement response = sender.sendReceive(createRequestPayloadGetBookByISBN(aISBN));
		return processResponsePayloadBook(response);
	}
	public String getSingleBookIdByISBN(String aISBN) throws AxisFault, JAXBException
	{
		ServiceClient sender = createSender();
		OMElement response = sender.sendReceive(createRequestPayloadGetBookByISBN(aISBN));
		return processResponsePayloadBook(response);
	}
	
	public String getBookById(String aBookId) throws AxisFault, JAXBException
	{
		ServiceClient sender = createSender();
		OMElement response = sender.sendReceive(createRequestPayloadGetBookById(aBookId));
		return processResponsePayloadBook(response);
	}
	
	public String getBookByStudent(String aStudentId) throws AxisFault, JAXBException
	{
		ServiceClient sender = createSender();
		OMElement response = sender.sendReceive(createRequestPayloadGetBookByStudent(aStudentId));
		return processResponsePayloadBorrow(response);
	}
	
	public String getAllBooks() throws AxisFault, JAXBException
	{
		ServiceClient sender = createSender();
		OMElement response = sender.sendReceive(createRequestPayloadGetAllBooks());
		return processResponsePayloadBook(response);
	}
	
	
	public ServiceClient createSender() throws AxisFault, JAXBException {
		
		String epr = "http://192.168.35.1:9763/services/BookInventoryService.BookInventoryServiceHttpEndpoint/";
		EndpointReference targetEPR = new EndpointReference(epr);

		Options options = new Options();
		options.setTo(targetEPR);
		options.setProperty(Constants.Configuration.MESSAGE_TYPE, "text/xml");
		options.setProperty(Constants.Configuration.HTTP_METHOD, Constants.Configuration.HTTP_METHOD_POST);
		options.setProperty(Constants.Configuration.ENABLE_REST, Constants.VALUE_TRUE);

		ServiceClient sender = new ServiceClient();
		sender.setOptions(options);
		return sender;
	}

	
	
	private static OMElement createRequestPayloadGetAllBooks() {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://waa.swin.edu.au", "ns");
        
        OMElement method = fac.createOMElement("getAllBooks", omNs);
        return method;
    }
	
	private static OMElement createRequestPayloadUpdateBook(String aBookId, String aBookStatus) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://waa.swin.edu.au", "ns");
        
        OMElement method = fac.createOMElement("updateBook", omNs);
        
        OMElement bookId = fac.createOMElement("bookId", omNs);
        bookId.addChild(fac.createOMText(bookId, aBookId));
        method.addChild(bookId);
        
        OMElement bookStatus = fac.createOMElement("status", omNs);
        bookStatus.addChild(fac.createOMText(bookStatus, aBookStatus));
        method.addChild(bookStatus);

        return method;
    }

	private static OMElement createRequestPayloadCreateBookBorrow(String aStudentId, String aBookId, String aBookStatus) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://waa.swin.edu.au", "ns");
        
        OMElement method = fac.createOMElement("createBookBorrow", omNs);
        
        OMElement studentId = fac.createOMElement("studentId", omNs);
        studentId.addChild(fac.createOMText(studentId,aStudentId));
        method.addChild(studentId);
        
        OMElement bookId = fac.createOMElement("bookId", omNs);
        bookId.addChild(fac.createOMText(bookId, aBookId));
        method.addChild(bookId);
        
        OMElement status = fac.createOMElement("bookStatus", omNs);
        status.addChild(fac.createOMText(status, aBookStatus));
        method.addChild(status);

        return method;
    }
	private static OMElement createRequestPayloadGetBookById(String aBookId) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://waa.swin.edu.au", "ns");
        
        OMElement method = fac.createOMElement("getBookById", omNs);
        
        OMElement bookId= fac.createOMElement("id", omNs);
        bookId.addChild(fac.createOMText(bookId, aBookId));
        method.addChild(bookId);

        return method;
    }
	
	private static OMElement createRequestPayloadGetBookByISBN(String aISBN) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://waa.swin.edu.au", "ns");
        
        OMElement method = fac.createOMElement("getBookByISBN", omNs);
        
        OMElement isbn= fac.createOMElement("ISBN", omNs);
        isbn.addChild(fac.createOMText(isbn, aISBN));
        method.addChild(isbn);
        
        return method;
    }
	private static OMElement createRequestPayloadGetBookByStudent(String aStudentId) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://waa.swin.edu.au", "ns");
        
        OMElement method = fac.createOMElement("getBookByStudent", omNs);

        OMElement studentId= fac.createOMElement("studentId", omNs);
        studentId.addChild(fac.createOMText(studentId, aStudentId));
        method.addChild(studentId);
        
        return method;
    }
	
	private static OMElement createRequestPayloadAddBook(String aTitle, String aAuthorList, String aISBN10,
			String aISBN13, String aPublisher, String aPublishedDate, String aStatus) {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://waa.swin.edu.au", "ns");
        
        OMElement method = fac.createOMElement("addBook", omNs);
        
        OMElement title= fac.createOMElement("title", omNs);
        title.addChild(fac.createOMText(title, aTitle));
        method.addChild(title);
        
        OMElement authorList= fac.createOMElement("authorList", omNs);
        authorList.addChild(fac.createOMText(authorList, aAuthorList));
        method.addChild(authorList);
        
        OMElement isbn10= fac.createOMElement("isbn10", omNs);
        isbn10.addChild(fac.createOMText(isbn10, aISBN10));
        method.addChild(isbn10);
        
        OMElement isbn13= fac.createOMElement("isbn13", omNs);
        isbn13.addChild(fac.createOMText(isbn13, aISBN13));
        method.addChild(isbn13);
        
        OMElement publisher= fac.createOMElement("publisher", omNs);
        publisher.addChild(fac.createOMText(publisher, aPublisher));
        method.addChild(publisher);
        
        OMElement publishedDate= fac.createOMElement("publishedDate", omNs);
        publishedDate.addChild(fac.createOMText(publishedDate, aPublishedDate));
        method.addChild(publishedDate);
        
        OMElement status= fac.createOMElement("status", omNs);
        status.addChild(fac.createOMText(status, aStatus));
        method.addChild(status);
        
        return method;
    }
	
	private static String processResponsePayloadBook(OMElement response) throws JAXBException {
		Iterator iterator = response.getChildrenWithLocalName("return");
		OMElement returnElement = (OMElement)iterator.next();
		System.out.println(returnElement.getText());
		
		
		String xml = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>"+returnElement.getText();
        StringReader reader = new StringReader(xml);
       
		JAXBContext context = JAXBContext.newInstance(Books.class);
		Unmarshaller un = context.createUnmarshaller();
		//convert to desired object
		Books bookData = (Books)un.unmarshal(reader);
		List<Book> books =  bookData.getBook();
		
	        //iterate over object
		String dataToSend = "";
		for(Book e: books){
			
			if(e.getId().equals("null"))
			{
				dataToSend = "No such Book exists!";
				break;
			}
			dataToSend += ("ID : "+e.getId())+"\n"+
			"Title: "+e.getTitle()+"\n"+
			"Author(s) : "+e.getauthorsList()+"\n"+
			"ISBN10 : "+e.getIsbn10()+"\n"+
			"ISBN13 : "+e.getIsbn13()+"\n"+
			"Publisher : "+e.getPublisher()+"\n"+
			"Pub Date : "+e.getPublishedDate()+"\n"+
			"Status : "+e.getStatus()+" \n"+
			"--------------------------"+"\n";
	    }
		return ((dataToSend == "") ? "no data" : dataToSend);
	}
	
	private static String processResponsePayloadSingleBook(OMElement response) throws JAXBException {
		Iterator iterator = response.getChildrenWithLocalName("return");
		OMElement returnElement = (OMElement)iterator.next();
		System.out.println(returnElement.getText());
		
		
		String xml = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>"+returnElement.getText();
        StringReader reader = new StringReader(xml);
       
		JAXBContext context = JAXBContext.newInstance(Books.class);
		Unmarshaller un = context.createUnmarshaller();
		//convert to desired object
		Books bookData = (Books)un.unmarshal(reader);
		List<Book> books =  bookData.getBook();
		
	        //iterate over object
		String dataToSend = "";
		for(Book e: books){
			
			if(e.getId().equals("null"))
			{
				dataToSend = "No such Book exists!";
				break;
			}
			dataToSend = e.getId();
	    }
		return ((dataToSend == "") ? "no data" : dataToSend);
	}
	
	private static String processResponsePayloadBorrow(OMElement response) throws JAXBException {
		Iterator iterator = response.getChildrenWithLocalName("return");
		OMElement returnElement = (OMElement)iterator.next();
		System.out.println(returnElement.getText());
		
		
		String xml = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>"+returnElement.getText();
        StringReader reader = new StringReader(xml);
       
		JAXBContext context = JAXBContext.newInstance(BorrowBooks.class);
		Unmarshaller un = context.createUnmarshaller();
		//convert to desired object
		BorrowBooks borrowData = (BorrowBooks)un.unmarshal(reader);
		List<BorrowBook> borrowBooks =  borrowData.getBorrowBook();
		
	        //iterate over object
		String dataToSend = "";
		for(BorrowBook e: borrowBooks){
			
			if(e.getBookId().equals("null"))
			{
				dataToSend = "No such student/book exists!";
				break;
			}
			
			dataToSend += "Book ID : "+e.getBookId()+"\n"+
			"Book Status : "+e.getBookStatus()+"\n"+
			"Borrow Date/Time : "+e.getBorrowDateTime()+"\n"+
			"--------------------------"+"\n";
	    }
		return ((dataToSend == "") ? "no data" : dataToSend);
	}
	
	private static String processResponsePayload(OMElement response) {
		Iterator iterator = response.getChildrenWithLocalName("return");
		OMElement returnElement = (OMElement)iterator.next();
		return (returnElement.getText());
	}
}
