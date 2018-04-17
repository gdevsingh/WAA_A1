package au.edu.swin.waa;
import java.util.HashMap;

public class GoogleBooksServiceREST {

	 
	public HashMap<String,String> getGoogleBook(String aISBN) throws Exception
	{
		HashMap<String, String> gbDetails = new HashMap<String, String>();
		String queryString = "isbn:"+aISBN;
		GoogleBooksClient gbc = new GoogleBooksClient();
		gbDetails = gbc.getGBookDetails(queryString);
		
		if(gbDetails != null)
		{
			if(gbDetails.get("countryBook").toString().equals("AU"))
			{
				if(gbDetails.get("isForSaleBook").toString().equals("FOR_SALE"))
				{
					if(Double.parseDouble(gbDetails.get("ratingBook").toString())>=3.5)
					{
						gbDetails.put("status_g","NOT_NULL");
						return gbDetails;
					}
					else
					{
						gbDetails.put("status_g","We can't order it, Rating is less than 3.5 :(");
						return gbDetails;
					}
				}
				else
				{
					gbDetails.put("status_g","This book is Not for Sale");
					return gbDetails;
				}
			}
			else
			{
				gbDetails.put("status_g","This book is Not available in Australia");
				return gbDetails;
			}
		}
		else
		{
			gbDetails = new HashMap<String, String>();
			gbDetails.put("status_g","No such Book exists in the market!");
			return gbDetails;
		}
		
	}
}
