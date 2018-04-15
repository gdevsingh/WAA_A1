package au.edu.swin.waa;

import java.util.HashMap;

public class TestGoogleBooks {

	public static void main(String args[]) throws Exception
	{
		HashMap<String, String> bookDetails = new HashMap<String, String>();
				
		GoogleBooksClient gbc=new GoogleBooksClient();
		{
			
			bookDetails=gbc.getGBookDetails("isbn:0606030085");
		
			if(bookDetails!=null)
			{					
				if(bookDetails.get("saleCountry").toString().equals("AU"))
				{								
					if(bookDetails.get("saleability").equals("FOR_SALE"))
					{
						if(Double.parseDouble(bookDetails.get("rating").toString())>3.5)
						{
//							finalString=myController.getResponseAddBook(epr_addBook, bookDetails);
//							if(!finalString.contains("ERROR"))
//							{
//								IO_Support.println("---Book Added to Database---");
//								finalString=myController.addStudentLoan(epr_addLoan, studId, bookDetails.get("id"), "requested", LocalDate.now());
//								if(!finalString.contains("ERROR"))
//								{
//									IO_Support.println("---Loan Added to Database---");
//								}
//								else
//								{
//									IO_Support.println("***Error : Loan Could not be added to Database***");
//									return;
//								}
//									
//							}
//							else
//							{
//								IO_Support.println("***Error : Book Could not be added to Database***");
//								return;
//							}
							
							
						}
						else
						{
							System.out.println("Sorry. The average rating of the book is below 3.5. Can't Proceed!");
							return;
						}
					}
					else
					{
						System.out.println("Sorry. Book is NOT FOR SALE in Australia.");
						return;
					}
					
				}
				else
				{
					System.out.println("Sorry. Book is unavailable in Australia.");
					return;
				}
			}
			else
			{
				System.out.println("Error Occurred while validating with Google Books.");
				System.out.println("Sorry. Try Again Later.");	
			}
		}
		

	}
}
