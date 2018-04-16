package au.edu.swin.waa;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BorrowBooks")
public class BorrowBooks {
	  private List<BorrowBook> borrowBook;
	  
	  @XmlElement(name="BorrowBook")
	  public List<BorrowBook> getBorrowBook() {
	      return borrowBook;
	  }

	  public void setBorrowBook(List<BorrowBook> borrowBookList) {
	      this.borrowBook = borrowBookList;
	  }
}




