package com.crud.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crud.bean.DataCenter;

public class DataCenterDAO {  //just do full crud

	//Define instance variables
	 private String DBURL = "jdbc:mysql://localhost:3306/testing_sandbox";
	 private String DBUsername = "root";
	 private String DBPassword = "CaptainNemo123";
	 
	 private String CreateNewSubject = "INSERT INTO subject_s (sub_id, major) VALUES(?, ?);"; 
	 private String CreateNewStudent = "INSERT INTO student (stu_name, stu_type) VALUES(?, ?);"; 
	 private String CreateNewEnrolment = "INSERT INTO enrol (stu_id, sub_surr) VALUES(?, ?);";//add the sorting loop
	 
	 private String SelectSubject = "SELECT sub_surr, sub_id, major FROM subject_s WHERE sub_surr = ?";
	 private String SelectStudent = "SELECT stu_id, stu_name, stu_type FROM student WHERE stu_id = ?;";
//	 private String SelectEnrolment = "select enrol_id, stu_id, sub_surr from enrol where enrol_id = ?; ";
	 
	 private String SelectAllSubjects = "select * from subject_s;";
	 private String SelectAllStudents = "select * from student;";
	 private String SelectAllEnrolments = "select e.enrol_id, e.stu_id, p.stu_name, s.sub_id from student p , enrol e , subject_s s where p.stu_id=e.stu_id and  s.sub_surr=e.sub_surr order by e.stu_id;";
	
//	 private String SelectEnrolmentsSummary = "select e.stu_id, p.stu_name, p.stu_type, s.major, count(e.sub_surr) as subCount from student p , enrol e , subject_s s where p.stu_id=e.stu_id and  s.sub_surr=e.sub_surr group by e.stu_id;";
	private String SelectEnrolmentsSummary = "select e.stu_id, p.stu_name, p.stu_type, s.major, count(e.sub_surr) from student p , enrol e , subject_s s where p.stu_id=e.stu_id and  s.sub_surr=e.sub_surr group by e.stu_id;";
	 private String DeleteSubject = "delete from enrol where sub_surr = ? ; delete from subject_s where sub_surr = ?;"; // deletes subject completely from enrolments and subjects
	 private String DeleteStudent = "delete from enrol where stu_id = ? ; delete from student where stu_id= ? ;"; // deletes student completely from enrolments and subjects
	 
	 private String UpdateSubject = "update subject_s set sub_id = ? , major=? where sub_surr = ? ;";
	 private String UpdateStudent = "update student set stu_name = ? , stu_type = ? where stu_id = ?;";
	 
	 private String CheckSubCount = "select count(sub_surr) as subCount from enrol where stu_id = ?;";
	 PreparedStatement countCaller = null; //call the count query
		ResultSet countChecker = null;//retreive the count query results
		
	 
	 //constructor
	 public DataCenterDAO() {}
	 protected Connection getConnection() {
	 Connection connection = null;
	 
	 try {
	 Class.forName("com.mysql.jdbc.Driver");
	 connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
	 } 
	 
	 catch (SQLException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 } 
	 
	 catch (ClassNotFoundException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	 
	 return connection;
	 }//----------------------------------------------------------------------------------------connection-----------------------------------------------------
	 
	 public void insertSubject(DataCenter DC) throws SQLException {//NEW SUBJECT
	 System.out.println(CreateNewSubject);
	 Connection connection= null;
	 PreparedStatement preparedStatement = null;
	 // try-with-resource statement will auto close the connection.
	 
	 try {
	 connection = getConnection();
	 preparedStatement = connection.prepareStatement(CreateNewSubject);
	 preparedStatement.setString(1, DC.getSubjectID());
	 preparedStatement.setString(2, DC.getMajor());
	 System.out.println(preparedStatement);
	 preparedStatement.executeUpdate();
	 } 
	 
	 catch (SQLException e) {
	 printSQLException(e);
	 } 
	 
	 finally {
	 finallySQLException(connection,preparedStatement,null);
	 }
	 }
	 
	 public void insertStudent(DataCenter DC) throws SQLException {//NEW STUDENT
		 System.out.println(CreateNewStudent);
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 // try-with-resource statement will auto close the connection.
		 
		 try {
		 connection = getConnection();
		 preparedStatement = connection.prepareStatement(CreateNewStudent);
		 preparedStatement.setString(1, DC.getStudentName());
		 preparedStatement.setString(2, DC.getStudentType());
		 System.out.println(preparedStatement);
		 preparedStatement.executeUpdate();
		 } 
		 
		 catch (SQLException e) {
		 printSQLException(e);
		 } 
		 
		 finally {
		 finallySQLException(connection,preparedStatement,null);
		 }
	 
	 }
	 
	 public void insertEnrolment(DataCenter DC, int subject_count) throws SQLException {//ENROLL
		 System.out.println(CreateNewEnrolment);
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 // try-with-resource statement will auto close the connection.
		 
		 try {
		 connection = getConnection();
	        countCaller = connection.prepareStatement(CheckSubCount);
			countCaller.setInt(1, DC.getStudentID());
			System.out.println(countCaller);
			countChecker = countCaller.executeQuery();
	        
		 
		 while(countChecker.next())
	        {  
			 subject_count = countChecker.getInt("subCount");
	 
	            if(subject_count==5) {
	            	DC.setSubject_count(subject_count);//stop
	            }
	            else if(subject_count==4) {
	            	DC.setSubject_count(subject_count);//warn
	            }
	            else if(subject_count<4){
	            	DC.setSubject_count(subject_count);//ok
	            }
	        }
		 preparedStatement = connection.prepareStatement(CreateNewEnrolment);
		 preparedStatement.setInt(1, DC.getStudentID());
		 preparedStatement.setString(2, DC.getSubjectID());
		 System.out.println(preparedStatement);
		 preparedStatement.executeUpdate();
		 
		 } 
		 
		 catch (SQLException e) {
		 printSQLException(e);
		 } 
		 
		 finally {
		 finallySQLException(connection,preparedStatement,null);
		 }
	 
	 }
	 
	 //----------------------------------------------------------------------------------------end insert statements---------------------------------------------------
	 
	 public DataCenter selectSubject(int surrogateKey) {//
	 DataCenter val = null;
	 Connection connection = null;
	 PreparedStatement preparedStatement = null;
	 ResultSet rs=null;
	 // Step 1: Establishing a Connection
	 
	 try {
	 connection = getConnection();
	 // Step 2:Create a statement using connection object
	 preparedStatement = connection.prepareStatement(SelectSubject);
	 preparedStatement.setInt(1, surrogateKey);
	 System.out.println(preparedStatement);
	 // Step 3: Execute the query or update query
	 rs = preparedStatement.executeQuery();
	 // Step 4: Process the ResultSet object.
	
	 while (rs.next()) {
	 String subjectID = rs.getString("sub_id");
	 String major = rs.getString("major");
	 val = new DataCenter( surrogateKey,  subjectID,  major);
	 }
	 
	 } 
	 
	 catch (SQLException e) {
	 printSQLException(e);
	 }
	 
	 finally {
	 finallySQLException(connection,preparedStatement,rs);
	 }
	 
	 return val;
	 
	 
	 }
	 
	 public DataCenter selectStudent(int studentID ) {
		 DataCenter val = null;
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs=null;
		 // Step 1: Establishing a Connection
		 
		 try {
		 connection = getConnection();
		 // Step 2:Create a statement using connection object
		 preparedStatement = connection.prepareStatement(SelectStudent);
		 preparedStatement.setInt(1, studentID);
		 System.out.println(preparedStatement);
		 // Step 3: Execute the query or update query
		 rs = preparedStatement.executeQuery();
		 // Step 4: Process the ResultSet object.
		
		 while (rs.next()) {
		 String studentName = rs.getString("stu_name");
		 String studentType = rs.getString("stu_type");
		 val = new DataCenter( studentID, studentName, studentType);
		 }
		 
		 } 
		 
		 catch (SQLException e) {
		 printSQLException(e);
		 }
		 
		 finally {
		 finallySQLException(connection,preparedStatement,rs);
		 }
		 
		 return val;
	 }
	 
//	 public DataCenter selectEnrolment(int enrolID ) { //bring back for the list section
//		 DataCenter val = null;
//		 Connection connection = null;
//		 PreparedStatement preparedStatement = null;
//		 ResultSet rs=null;
//		 // Step 1: Establishing a Connection
//		 
//		 try {
//		 connection = getConnection();
//		 // Step 2:Create a statement using connection object
//		 preparedStatement = connection.prepareStatement(SelectEnrolment);
//		 preparedStatement.setInt(1, enrolID);
//		 System.out.println(preparedStatement);
//		 // Step 3: Execute the query or update query
//		 rs = preparedStatement.executeQuery();
//		 // Step 4: Process the ResultSet object.
//		
//		 while (rs.next()) {
//		 String studentID = rs.getString("stu_id");
//		 String subjectID = rs.getString("sub_id");
//		 val = new DataCenter(enrolID,studentID, subjectID);
//		 }
//		 
//		 } 
//		 
//		 catch (SQLException e) {
//		 printSQLException(e);
//		 }
//		 
//		 finally {
//		 finallySQLException(connection,preparedStatement,rs);
//		 }
//		 
//		 return val;
//	 }
	 
	 
	 
	 
	 //----------------------------------------------------------------------------------------end select statements-------------------------------------------------
	 
	 public List < DataCenter > selectAllSubjects() {
	 //Employee emp = null;
	 Connection connection = null;
	 PreparedStatement preparedStatement = null;
	 ResultSet rs=null;
	 // using try-with-resources to avoid closing resources (boiler plate code)
	 List < DataCenter > Sub = new ArrayList < > ();
	 // Step 1: Establishing a Connection
	 try {
	 connection = getConnection();
	 // Step 2:Create a statement using connection object
	 preparedStatement =
	connection.prepareStatement(SelectAllSubjects);
	 System.out.println(preparedStatement);
	 // Step 3: Execute the query or update query
	 rs = preparedStatement.executeQuery();
	 // Step 4: Process the ResultSet object.
	 while (rs.next()) {
	 int surrogateKey = rs.getInt("sub_surr");
	 String subjectID = rs.getString("sub_id");
	 int major = rs.getInt("major");

	 Sub.add(new DataCenter(surrogateKey, subjectID, major));
	 }
	 } catch (SQLException e) {
	 printSQLException(e);
	 }
	 finally {
	 finallySQLException(connection,preparedStatement,rs);
	 }
	 return Sub;
	 }
	 
	 public List < DataCenter > selectAllStudents() {
		 //Employee emp = null;
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs=null;
		 // using try-with-resources to avoid closing resources (boiler plate code)
		 List < DataCenter > Sub = new ArrayList < > ();
		 // Step 1: Establishing a Connection
		 try {
		 connection = getConnection();
		 // Step 2:Create a statement using connection object
		 preparedStatement =
		connection.prepareStatement(SelectAllStudents);
		 System.out.println(preparedStatement);
		 // Step 3: Execute the query or update query
		 rs = preparedStatement.executeQuery();
		 // Step 4: Process the ResultSet object.
		 while (rs.next()) {
		 int studentID = rs.getInt("stu_id");
		 String studentName = rs.getString("stu_name");
		 String studentType = rs.getString("stu_type");

		 Sub.add(new DataCenter(studentID, studentName, studentType));
		 }
		 } catch (SQLException e) {
		 printSQLException(e);
		 }
		 finally {
		 finallySQLException(connection,preparedStatement,rs);
		 }
		 return Sub;
		 }
	 
	 public List < DataCenter > selectAllEnrolments() {
		 //Employee emp = null;
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs=null;
		 // using try-with-resources to avoid closing resources (boiler plate code)
		 List < DataCenter > Sub = new ArrayList < > ();
		 // Step 1: Establishing a Connection
		 try {
		 connection = getConnection();
		 // Step 2:Create a statement using connection object
		 preparedStatement =
		connection.prepareStatement(SelectAllEnrolments);
		 System.out.println(preparedStatement);
		 // Step 3: Execute the query or update query
		 rs = preparedStatement.executeQuery();
		 // Step 4: Process the ResultSet object.
		 while (rs.next()) {
		 int enrolID = rs.getInt("e.enrol_id");
		 int studentID = rs.getInt("e.stu_id");
		 String studentName = rs.getString("p.stu_name"); 
		 String subjectID = rs.getString("s.sub_id");

		 Sub.add(new DataCenter(enrolID, studentID, studentName, subjectID));
		 }
		 } catch (SQLException e) {
		 printSQLException(e);
		 }
		 finally {
		 finallySQLException(connection,preparedStatement,rs);
		 }
		 return Sub;
		 }
	 
	 
	 public List < DataCenter > selectEnrolmentsSummary() {
		 //Employee emp = null;
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs=null;
		 // using try-with-resources to avoid closing resources (boiler plate code)
		 List < DataCenter > Sub = new ArrayList < > ();
		 // Step 1: Establishing a Connection
		 try {
		 connection = getConnection();
		 // Step 2:Create a statement using connection object
		 preparedStatement = connection.prepareStatement(SelectEnrolmentsSummary);
		 System.out.println(preparedStatement);
		 // Step 3: Execute the query or update query
		 rs = preparedStatement.executeQuery();
		 // Step 4: Process the ResultSet object.
		 while (rs.next()) {
		 int studentID = rs.getInt("stu_id"); //e.stu_id
		 String studentName = rs.getString("stu_name");//p.stu_name
		 String studentType = rs.getString("stu_type"); //p.stu_type
		 String major= rs.getString("major");//s.major
		 int subjectCount = rs.getInt("count(e.sub_surr)");//subCount

		 Sub.add(new DataCenter(studentID, studentName, studentType, major,subjectCount));
		 }
		 } catch (SQLException e) {
		 printSQLException(e);
		 }
		 finally {
		 finallySQLException(connection,preparedStatement,rs);
		 }
		 return Sub;
		 }
	 
	 
	 //----------------------------------------------------------------------------------------end list statement (select all )-------------------------------------------------

	 public boolean deleteSubject(int surrogateKey) throws SQLException {
		 boolean subDeleted = false;
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 
		 try {
		 connection = getConnection();
		 preparedStatement = connection.prepareStatement(DeleteSubject);
		 preparedStatement.setInt(1, surrogateKey);
		 preparedStatement.setInt(2, surrogateKey);
		 System.out.println(preparedStatement);
		 subDeleted = preparedStatement.executeUpdate() > 0 ?
		true:false;
		 }
		 
		 finally {
		 finallySQLException(connection,preparedStatement,null);
		 }
		 
		 return subDeleted;
		 }
	 
	 public boolean deleteStudent(int surrogateKey) throws SQLException {
		 boolean subDeleted = false;
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 
		 try {
		 connection = getConnection();
		 preparedStatement = connection.prepareStatement(DeleteStudent);
		 preparedStatement.setInt(1, surrogateKey);
		 preparedStatement.setInt(2, surrogateKey);
		 System.out.println(preparedStatement);
		 subDeleted = preparedStatement.executeUpdate() > 0 ?
		true:false;
		 }
		 
		 finally {
		 finallySQLException(connection,preparedStatement,null);
		 }
		 
		 return subDeleted;
		 }
	 
	 //-----------------------------------------------------------------------------------------end deletion----------------------------------------------
	 
		 public boolean updateSubject(DataCenter DC) throws SQLException {
		 boolean DCUpdated = false;
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 
		 try {
		 connection = getConnection();
		 preparedStatement = connection.prepareStatement(UpdateSubject);
		 preparedStatement.setString(1, DC.getSubjectID());//sub_id
		 preparedStatement.setString(2, DC.getMajor());//major
		 System.out.println(preparedStatement);
		 DCUpdated = preparedStatement.executeUpdate() > 0 ?
		true:false;
		 }
		 
		 catch (SQLException e) {
		 printSQLException (e);
		 }
		 
		 finally {
		 finallySQLException(connection,preparedStatement,null);
		 }
		 
		 return DCUpdated;
		 }
		 
		 
		 public boolean updateStudent(DataCenter DC) throws SQLException {
			 boolean DCUpdated = false;
			 Connection connection = null;
			 PreparedStatement preparedStatement = null;
			 
			 try {
			 connection = getConnection();
			 preparedStatement = connection.prepareStatement(UpdateStudent);
			 preparedStatement.setString(1, DC.getStudentName());//stu_name
			 preparedStatement.setString(2, DC.getStudentType());//stu_type
			 System.out.println(preparedStatement);
			 DCUpdated = preparedStatement.executeUpdate() > 0 ?
			true:false;
			 }
			 
			 catch (SQLException e) {
			 printSQLException (e);
			 }
			 
			 finally {
			 finallySQLException(connection,preparedStatement,null);
			 }
			 
			 return DCUpdated;
			 }
		 
		 
		 //---------------------------------------------------------------------------------------end update------------------------------------------
	 
	 private void printSQLException(SQLException ex) {
	 
		 for (Throwable e: ex) {
	 
			 if (e instanceof SQLException) {
	 e.printStackTrace(System.err);
	 System.err.println("SQLState: " + ((SQLException)
	e).getSQLState());
	 System.err.println("Error Code: " + ((SQLException)
	e).getErrorCode());
	 System.err.println("Message: " + e.getMessage());
	 Throwable t = ex.getCause();
	 
	 while (t != null) {
	 System.out.println("Cause: " + t);
	t = t.getCause();
	 }
	 
	 }
			 
	 }
		 
	 }
	 
	 private void finallySQLException(Connection c, PreparedStatement p,
	ResultSet r){
	 if (r != null) {
	 try {
	 r.close();
	 } 
	 catch (Exception e) {}
	 r = null;
	 }
	 if (p != null) {
	 try {
	 p.close();
	 } 
	 catch (Exception e) {}
	 p = null;
	 }
	 if (c != null) {
	 try {
	 c.close();
	 } 
	 catch (Exception e) {
	 c = null;
	 }
	 }
	 }
}


