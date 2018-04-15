package au.edu.swin.waa;


public class Book {
	private String id;
	private String title;
	private String authorsList;
	private String isbn10;
	private String isbn13;
	private String publisher;
	private String publishedDate;
	private String status;
	
	public Book(String id, String title, String authorsList,
			String isbn10, String isbn13, String publisher,
			String publishedDate, String status) {
		
		super();
		
	}
	

	public Book() {
		// TODO Auto-generated constructor stub
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getauthorsList() {		
		return authorsList;
	}

	public void setauthorsList(String authorsList) {
		this.authorsList = authorsList;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	@Override
	public String toString() {
		return "<book>"
	+"<id>"+id+"</id>"
	+"<title>"+title+"</title>"
	+"<authorsList>"+authorsList+"</authorsList>"
	+"<isbn10>"+isbn10+"</isbn10>"
	+"<isbn13>"+isbn13+"</isbn13>"
	+"<publisher>"+publisher+"</publisher>"
	+"<publishedDate>"+publishedDate+"</publishedDate>"
	+"<status>"+status+"</status>"
	+"</book>";
	}
	
	
	
	
	

}
