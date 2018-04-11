
/**
 * StudentServiceSOAPSQLExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v14  Built on : Jul 25, 2015 (11:19:54 IST)
 */

package au.edu.swin.waa;

public class StudentServiceSOAPSQLExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1523439352116L;
    
    private au.edu.swin.waa.StudentServiceSOAPStub.StudentServiceSOAPSQLException faultMessage;

    
        public StudentServiceSOAPSQLExceptionException() {
            super("StudentServiceSOAPSQLExceptionException");
        }

        public StudentServiceSOAPSQLExceptionException(java.lang.String s) {
           super(s);
        }

        public StudentServiceSOAPSQLExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public StudentServiceSOAPSQLExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(au.edu.swin.waa.StudentServiceSOAPStub.StudentServiceSOAPSQLException msg){
       faultMessage = msg;
    }
    
    public au.edu.swin.waa.StudentServiceSOAPStub.StudentServiceSOAPSQLException getFaultMessage(){
       return faultMessage;
    }
}
    