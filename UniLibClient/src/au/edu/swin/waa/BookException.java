package au.edu.swin.waa;

public class BookException extends Exception{
	String errorMsg;
	
	public BookException(String msg)
	{
		this.errorMsg=msg;
	}


	@Override
	public String getMessage() {
		return "BookException [errorMsg=" + errorMsg + "]";
	}
	

}
