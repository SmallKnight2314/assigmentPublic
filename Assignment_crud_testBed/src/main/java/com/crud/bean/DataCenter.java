package com.crud.bean;

public class DataCenter {

	protected int StudentID;
	protected String StudentName;
	protected String StudentType;
	
	protected String SubjectID;
	protected String Major;
	protected int SurrogateKey;
	protected int SubjectCount;//used in the printing of summary
	protected int subject_count;//used in the checking of count
	
	protected int EnrolID;
	
	
	public DataCenter() {}
	
	public DataCenter(int studentID, String studentName, String studentType, String subjectID, String major, int surrogateKey, int subjectCount, int subject_count, int enrolID) {

		this.StudentID = studentID;
		this.StudentName = studentName;
		this.StudentType = studentType;
		this.SubjectID = subjectID;
		this.Major = major;
		this.SurrogateKey = surrogateKey;
		this.SubjectCount = subjectCount;
		this.subject_count = subject_count;
		this.EnrolID = enrolID;
	}
	
	
//	 public DataCenter(int studentID, String studentName, String studentType, String subjectID, String major, int surrogateKey) {
//		this.StudentName = studentName;
//		this.StudentType = studentType;
//		this.SubjectID = subjectID;
//		this.Major = major;
//		this.SurrogateKey = surrogateKey;
//
//	 }
//	 public DataCenter(int studentID, String studentName, String studentType, String subjectID, String major, int enrolID, int surrogateKey, int subject_count) {
//
//		this.StudentID = studentID;
//		this.StudentName = studentName;
//		this.StudentType = studentType;
//		this.SubjectID = subjectID;
//		this.Major = major;
//		this.EnrolID = enrolID;
//		this.SurrogateKey = surrogateKey;
//		this.subject_count = subject_count;
//		
//
//	 }
	
	public DataCenter(int surrogateKey, String subjectID, String major) {
		this.SurrogateKey = surrogateKey;
		this.SubjectID = subjectID;
		this.Major = major;
	}
	
	public DataCenter(String studentName, int studentID, String studentType) {
		this.StudentName = studentName;
		this.StudentID = studentID;
		this.StudentType = studentType;
		}
	
	public DataCenter(int enrolID, String subjectID, int studentID) {
		this.EnrolID = enrolID;
		this.StudentID = studentID;
		this.SubjectID = subjectID;
	}
	

	
	public DataCenter(int enrolID, int studentID, String studentName, String subjectID) {
		this.EnrolID = enrolID;
		this.StudentID = studentID;
		this.StudentName = studentName;
		this.SubjectID = subjectID;
	}
	public DataCenter(int studentID, String studentName, String studentType, String major, int subjectCount) {
		this.StudentID = studentID;
		this.StudentName = studentName;
		this.StudentType = studentType;
		this.Major = major;
		this.SubjectCount = subjectCount;
	}
		
	//	public DataCenter(int surrogateKey, String subjectID, String major) {
//		// TODO Auto-generated constructor stub
//	}
	
	
	public DataCenter(String subjectID, String major) {
		this.SubjectID = subjectID;
		this.Major = major;
	}
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		this.StudentID = studentID;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		this.StudentName = studentName;
	}
	public String getStudentType() {
		return StudentType;
	}
	public void setStudentType(String studentType) {
		this.StudentType = studentType;
	}
	public String getSubjectID() {
		return SubjectID;
	}
	public void setSubjectID(String subjectID) {
		this.SubjectID = subjectID;
	}
	public String getMajor() {
		return Major;
	}
	public void setMajor(String major) {
		this.Major = major;
	}
	public int getEnrolID() {
		return EnrolID;
	}
	public void setEnrolID(int enrolID) {
		this.EnrolID = enrolID;
	}
	public int getSurrogateKey() {
		return SurrogateKey;
	}
	public void setSurrogateKey(int surrogateKey) {
		this.SurrogateKey = surrogateKey;
	}
	public int getSubjectCount() {
		return SubjectCount;
	}
	public void setSubjectCount(int subjectCount) {
		SubjectCount = subjectCount;
	}
	public int getSubject_count() {
		return subject_count;
	}
	public void setSubject_count(int subject_count) {
		this.subject_count = subject_count;
	}
	
	
	
}
