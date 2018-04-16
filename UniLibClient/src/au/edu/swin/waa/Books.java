package au.edu.swin.waa;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Books")
public class Books {
	  private List<Book> book;
	  
	  @XmlElement(name="book")
	  public List<Book> getBook() {
	      return book;
	  }

	  public void setBook(List<Book> bookList) {
	      this.book = bookList;
	  }
}




