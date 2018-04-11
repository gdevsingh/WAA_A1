package au.edu.swin.waa;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import au.edu.swin.waa.StudentServiceSOAPStub.AddStudent;
import au.edu.swin.waa.StudentServiceSOAPStub.AddStudentResponse;
import au.edu.swin.waa.StudentServiceSOAPStub.ValidateStudent;
import au.edu.swin.waa.StudentServiceSOAPStub.ValidateStudentResponse;
public class StudentServiceSOAPClient {
 public static void main(String[] args) {
 try {
 StudentServiceSOAPStub stub = new StudentServiceSOAPStub();
 // Add a suburb
AddStudent addStudent = new AddStudent();
addStudent.setStudentId(123);
addStudent.setString("Kartik Chadha");
addStudent.setPin(1234);
AddStudentResponse addStudentResponse = stub.addStudent(addStudent);

ValidateStudent student = new ValidateStudent();
student.setStudentId(123);
student.setPin(1234);
ValidateStudentResponse validateStudentResponse = stub.validateStudent(student);


//System.out.println(addStudentResponse.get_return());
System.out.println(validateStudentResponse.get_return());

 } catch (AxisFault e) {
 e.printStackTrace();
 } catch (RemoteException e) {
 e.printStackTrace();
 } catch (StudentServiceSOAPSQLExceptionException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 }
}