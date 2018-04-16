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
	public static void main(String args[]) throws AxisFault, JAXBException {
		
		String epr = "http://192.168.35.1:9763/services/BookInventoryService.BookInventoryServiceHttpEndpoint/";
		EndpointReference targetEPR = new EndpointReference(epr);

		Options options = new Options();
		options.setTo(targetEPR);
		options.setProperty(Constants.Configuration.MESSAGE_TYPE, "text/xml");
		options.setProperty(Constants.Configuration.HTTP_METHOD, Constants.Configuration.HTTP_METHOD_POST);
		options.setProperty(Constants.Configuration.ENABLE_REST, Constants.VALUE_TRUE);

		ServiceClient sender = new ServiceClient();
		sender.setOptions(options);

		OMElement response = sender.sendReceive(createRequestPayload());
//		OMElement response = sender.sendReceive(createRequestPayloadFromXML());
		processResponsePayload(response);
	}

	private static OMElement createRequestPayload() {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://waa.swin.edu.au", "ns");
        
        OMElement method = fac.createOMElement("getAllBooks", omNs);
        //OMElement postCode = fac.createOMElement("studentId", omNs);
        //postCode.addChild(fac.createOMText(postCode, "1"));
        //method.addChild(postCode);
        
        //OMElement suburbName = fac.createOMElement("status", omNs);
        //suburbName.addChild(fac.createOMText(suburbName, "newdone"));
        //method.addChild(suburbName);

        return method;
    }

	private static void processResponsePayload(OMElement response) throws JAXBException {
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
		for(Book e: books){
			System.out.println("Name : "+e.getId());
			System.out.println("Department : "+e.getTitle());
			System.out.println("--------------------------");
	    }
	}

	private static OMElement createRequestPayloadFromXML(){
		try {
			String request = "<ns:addSuburb xmlns:ns=\"http://waa.swin.edu.au\">" +
								"<ns:postCode>3123</ns:postCode>" +
								"<ns:suburbName>Hawthorn East</ns:suburbName>" +
							 "</ns:addSuburb>";
			return new StAXOMBuilder(new ByteArrayInputStream(request.getBytes())).getDocumentElement();
		} catch (XMLStreamException e) {
			e.printStackTrace();
			return null;
		}
	}
}
