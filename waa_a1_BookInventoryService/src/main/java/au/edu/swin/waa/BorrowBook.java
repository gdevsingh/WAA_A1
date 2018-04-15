package au.edu.swin.waa;

public class BorrowBook {
	
	private String studentId;
	private String bookId;
	private String bookStatus;
	private String borrowDateTime;
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}
	public String getBorrowDateTime() {
		return borrowDateTime;
	}
	public void setBorrowDateTime(String borrowDateTime) {
		this.borrowDateTime = borrowDateTime;
	}
	@Override
	public String toString() {
		return "<studentId>"+studentId+"</studentId>"+"<bookId>"
	+bookId+"</bookId>"+"<bookStatus>"+bookStatus+"</bookStatus>"+"<borrowDateTime>"
				+borrowDateTime+"</borrowDateTime>";
	}
	
	
}
