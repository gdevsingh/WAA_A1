package au.edu.swin.waa;


public class Book {
	private String id;
	private String title;
	private String authorList;
	private String isbn10;
	private String isbn13;
	private String publisher;
	private String publishedDate;
	private String status;
	
	public Book(String id, String title, String authorList,
			String isbn10, String isbn13, String publisher,
			String publishedDate, String status) throws BookException {
		super();
		if(!id.trim().equals("") && !title.trim().equals("") && !authorList.equals("") 
				&& !isbn10.equals("") && !isbn13.equals("") && !publisher.equals("")
				&& !publishedDate.equals("") && !status.equals("")){
			this.id = id;
			this.title = title;
			this.authorList = authorList;
			this.isbn10 = isbn10;
			this.isbn13 = isbn13;
			this.publisher = publisher;
			this.publishedDate = publishedDate;
			if(status.toLowerCase().equals("available") || 
					status.toLowerCase().equals("borrowed") || 
					status.toLowerCase().equals("backorder")){
						this.status = status.toLowerCase(); 
			}else{
				throw new BookException("Status can either be available, Borrowed or Backorder");
			}
		}else
		{
			throw new BookException("Fields Can't be blank! Enter valid details");
		}		
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

	public String getAuthorList() {		
		return authorList;
	}

	public void setAuthorList(String authorList) {
		this.authorList = authorList;
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
		return "Book [id=" + id + ", title=" + title + ", authorList="
				+ authorList + ", isbn10=" + isbn10 + ", isbn13="
				+ isbn13 + ", publisher=" + publisher + ", publishedDate="
				+ publishedDate + "]";
	};
	
	
	
	
	

}
