package au.edu.swin.waa;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang.StringUtils;

import au.edu.swin.waa.StudentServiceSOAPStub.AddStudent;
import au.edu.swin.waa.StudentServiceSOAPStub.AddStudentResponse;
import au.edu.swin.waa.StudentServiceSOAPStub.ValidateStudent;
import au.edu.swin.waa.StudentServiceSOAPStub.ValidateStudentResponse;

public class StudentServiceSOAPClient {
	public String addStudent(String aStudentId, String aName, String aPin) {
		int student, pin = 0;
		if (StringUtils.isNumeric(aStudentId) && StringUtils.isNumeric(aPin)) {
			student = Integer.parseInt(aStudentId);
			pin = Integer.parseInt(aPin);
		} else {
			return ("Make sure your pin and student id is numeric.");
		}
		if (pin > 9999 || pin < 1000) {
			return ("Your pin should be a 4-digit number.");
		}
		try {
			StudentServiceSOAPStub stub = new StudentServiceSOAPStub();

			AddStudent addStudent = new AddStudent();
			addStudent.setStudentId(student);
			addStudent.setString(aName);
			addStudent.setPin(pin);
			AddStudentResponse addStudentResponse = stub.addStudent(addStudent);

			return (addStudentResponse.get_return());

		} catch (AxisFault e) {
			return e.getMessage();
		} catch (RemoteException e) {
			return e.getMessage();
		} catch (StudentServiceSOAPSQLExceptionException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	public String validateStudent(String aStudentId, String aPin) {

		int student, pin = 0;
		if (StringUtils.isNumeric(aStudentId) && StringUtils.isNumeric(aPin)) {
			student = Integer.parseInt(aStudentId);
			pin = Integer.parseInt(aPin);
		} else {
			return ("Make sure your pin and student id is numeric.");
		}
		if (pin > 9999 || pin < 1000) {
			return ("Your pin should be a 4-digit number.");
		}

		try {
			StudentServiceSOAPStub stub = new StudentServiceSOAPStub();

			ValidateStudent vs = new ValidateStudent();
			vs.setStudentId(student);
			vs.setPin(pin);
			ValidateStudentResponse validateStudentResponse = stub
					.validateStudent(vs);

			return (validateStudentResponse.get_return());

		} catch (AxisFault e) {
			return e.getMessage();
		} catch (RemoteException e) {
			return e.getMessage();
		} catch (StudentServiceSOAPSQLExceptionException e) {
			return e.getMessage();
		}
	}
}