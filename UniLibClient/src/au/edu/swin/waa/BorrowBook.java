package au.edu.swin.waa;

import javax.xml.bind.annotation.XmlElement;

public class BorrowBook {
	
	private String studentId;
	private String bookId;
	private String bookStatus;
	private String borrowDateTime;
	
	@XmlElement
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	@XmlElement
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	@XmlElement
	public String getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	@XmlElement
	public String getBorrowDateTime() {
		return borrowDateTime;
	}
	public void setBorrowDateTime(String borrowDateTime) {
		this.borrowDateTime = borrowDateTime;
	}
	@Override
	public String toString() {
		return "<borrow><studentId>"+studentId+"</studentId>"+"<bookId>"
	+bookId+"</bookId>"+"<bookStatus>"+bookStatus+"</bookStatus>"+"<borrowDateTime>"
				+borrowDateTime+"</borrowDateTime></borrow>";
	}
	
	
}
