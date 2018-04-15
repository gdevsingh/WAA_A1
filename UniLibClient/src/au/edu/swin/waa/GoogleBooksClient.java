package au.edu.swin.waa;
import java.util.HashMap;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;

/**
 * @author : Ravi Mistry
 * @editor : Gurdev Singh
 * @Source : from https://github.com/google/
 * google-api-java-client-samples/blob/master/
 * books-cmdline-sample/src/main/java/com
 * /google/api/services/samples/books/cmdline/
 * BooksSample.java
 * 
 * */

public class GoogleBooksClient 
{

	private final String APP_NAME = "unilibclient";

	public HashMap < String,
	String > queryGoogleBooks(JsonFactory jsonFactory, String queryString) throws Exception 
	{

		final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null).setApplicationName(APP_NAME).build();
		List volumesList = books.volumes().list(queryString);
		HashMap < String,
		String > hashMap = new HashMap < >();
		
		// query execution.
		Volumes volumes = volumesList.execute();
		if (volumes.getTotalItems() == 0 || volumes.getItems() == null) 
		{

			return null;

		}
		Volume volume = volumes.getItems().get(0);

		//fetch BookId
		Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
		
		//fetch saleInfo
				Volume.SaleInfo saleInfo = volume.getSaleInfo();

		//fetch authors
		String authorsData = "";
		java.util.List < String > authors = volumeInfo.getAuthors();
		if (authors != null && !authors.isEmpty()) {
			for (int i = 0; i < authors.size(); i++) {
				authorsData += authors.get(i);
				if (i < authors.size() - 1) {
					authorsData += ",";
				}
			}

		}

		// fetch ratings
		Double rating = 0.0;
		if (volumeInfo.getRatingsCount() != null && volumeInfo.getRatingsCount() > 0) 
		{
			rating = volumeInfo.getAverageRating().doubleValue();
		}

		
		hashMap.put("id", volume.getId());
		hashMap.put("title", volumeInfo.getTitle());
		hashMap.put("authors", authorsData);
		hashMap.put("isbn10", volumeInfo.getIndustryIdentifiers().get(1).get("identifier").toString());
		hashMap.put("isbn13", volumeInfo.getIndustryIdentifiers().get(0).get("identifier").toString());
		hashMap.put("publisher", volumeInfo.getPublisher());
		hashMap.put("publishedDate", volumeInfo.getPublishedDate());
		hashMap.put("country", saleInfo.getCountry());
		hashMap.put("isForSale", saleInfo.getSaleability());
		hashMap.put("rating", rating.toString());

		System.out.println("***********");
		System.out.println(hashMap.get("id"));
		System.out.println(hashMap.get("title"));
		System.out.println(hashMap.get("authors"));
		System.out.println(hashMap.get("isbn10"));
		System.out.println(hashMap.get("isbn13"));
		System.out.println(hashMap.get("publisher"));
		System.out.println(hashMap.get("publishedDate"));
		System.out.println(hashMap.get("country"));
		System.out.println(hashMap.get("isForSale"));
		System.out.println(hashMap.get("rating"));
		System.out.println("***********");

		return hashMap;

	}

	public HashMap < String,
	String > getGBookDetails(String queryString) throws Exception 
	{

		try 
		{
			JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
			HashMap < String,
			String > hashMap = new HashMap < >();

			hashMap = queryGoogleBooks(jsonFactory, queryString);
			return hashMap;

		}
		catch(Exception e) 
		{
			return null;
		}

	}

}