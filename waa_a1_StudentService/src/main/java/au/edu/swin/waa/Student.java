package au.edu.swin.waa;

class Student
{
	int studentId;
	int pin;
	String fullName;
	
	public Student()
	{
		this.studentId = 00;
		this.pin = 00;
		this.fullName = "";
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}