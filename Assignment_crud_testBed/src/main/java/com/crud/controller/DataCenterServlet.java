package com.crud.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.crud.bean.DataCenter;
import com.crud.DAO.DataCenterDAO;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class DataCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataCenterDAO dc_DAO; 
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataCenterServlet() {
        super();
       
    }
    public void init() {
    	dc_DAO = new DataCenterDAO();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if (action==null) {
		action="No action";
		}
		RequestDispatcher dispatcher;
		try {
		 switch (action) {
//		 case "new":
//		 showNewEmployee(request, response);
//		 break;
		 case "newSubject":
			 newSubject(request, response);
			 break;
		 case "newStudent":
			 newStudent(request, response);
			 break;
		 case "enrol":
			 enrol(request, response);
			 break;
//		 case "insert":
//		 insertEmployee(request, response);
//		 break;
		 case "insertSubject":
			 insertSubject(request, response);
			 break;
		 case "insertStudent":
			 insertStudent(request, response);
			 break;
		 case "insertEnrol":
			 insertEnrol(request, response);
			 break;
//		 case "delete":
//		 deleteEmployee(request, response);
//		 break;
		 case "deleteSubject":
			 deleteSubject(request, response);
			 break;
		 case "deleteStudent":
			 deleteStudent(request, response);
			 break;
//		 case "edit":
//		 showEditEmployee(request, response);
//		 break;
		 case "editSubject":
			 editSubject(request, response);
			 break;
		 case "editStudent":
			 editStudent(request, response);
			 break;
//		 case "update":
//		 updateEmployee(request, response);
//		 break;
		 case "updateSubject":
			 updateSubject(request, response);
			 break;
		 case "updateStudent":
			 updateStudent(request, response);
			 break;
		 case "listAllSubjects":
			 listAllSubjects(request, response);
			 break;
		 case "listAllStudents":
			 listAllStudents(request, response);
			 break;
		 case "listAllEnrolments":
			 listAllEnrolments(request, response);
			 break;
//		 default:
//		 listEmployee(request, response);
		 default:
			 listEnrolmentsSummary(request, response);
		 break;
		 }
		} catch (Exception ex) {
		 throw new ServletException(ex);
		 }
		 }//End of doPost method 
		
//	private void listEmployee(HttpServletRequest request, HttpServletResponse
//			response) throws SQLException, IOException, ServletException {
//			List < Employee > listEmployee = empDAO.selectAllEmployees();
//			request.setAttribute("listEmployee", listEmployee);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
//			dispatcher.forward(request, response);
//			}
	private void listAllSubjects(HttpServletRequest request, HttpServletResponse
			response) throws SQLException, IOException, ServletException {
			List < DataCenter > listSubjects = dc_DAO.selectAllSubjects();
			request.setAttribute("listSubjects", listSubjects);
			RequestDispatcher dispatcher = request.getRequestDispatcher("listAllSubjects.jsp");//may change later on
			dispatcher.forward(request, response);
			}
	private void listAllStudents(HttpServletRequest request, HttpServletResponse
			response) throws SQLException, IOException, ServletException {
			List < DataCenter > listStudents = dc_DAO.selectAllStudents();
			request.setAttribute("listStudents", listStudents);
			RequestDispatcher dispatcher = request.getRequestDispatcher("listAllStudents.jsp");//may change later on
			dispatcher.forward(request, response);
			}
	private void listAllEnrolments(HttpServletRequest request, HttpServletResponse
			response) throws SQLException, IOException, ServletException {
			List < DataCenter > listEnrolments = dc_DAO.selectAllEnrolments();
			request.setAttribute("listEnrolments", listEnrolments);
			RequestDispatcher dispatcher = request.getRequestDispatcher("listAllEnrolments.jsp");//may change later on
			dispatcher.forward(request, response);
			}
	private void listEnrolmentsSummary(HttpServletRequest request, HttpServletResponse
			response) throws SQLException, IOException, ServletException {
			List < DataCenter > listEnrolmentsSummary = dc_DAO.selectEnrolmentsSummary();
			request.setAttribute("listEnrolmentsSummary", listEnrolmentsSummary);
			RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp");//will change later on so both levels can see
			dispatcher.forward(request, response);
			}
	
//	private void showNewEmployee(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("employeeform.jsp");
//			dispatcher.forward(request, response);
//			}
	private void newSubject(HttpServletRequest request, HttpServletResponse response)//----------------------------oo
			throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Classform.jsp");
			dispatcher.forward(request, response);
			}
	private void newStudent(HttpServletRequest request, HttpServletResponse response)//----------------------------oo
			throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Studentform.jsp");
			dispatcher.forward(request, response);
			}
	private void enrol(HttpServletRequest request, HttpServletResponse response)//----------------------------oo
			throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Enrolform.jsp");
			dispatcher.forward(request, response);
			}
	
//	private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException {
//			 String ename = request.getParameter("name");
//			 int eage = Integer.parseInt(request.getParameter("age"));
//			 Employee e = new Employee(ename, eage);
//			 empDAO.insertEmployee(e);
//			response.sendRedirect(request.getContextPath()+"/EmployeeServlet?action=list");
//			}
	private void insertSubject(HttpServletRequest request, HttpServletResponse response)//---------------------oo
			throws SQLException, IOException {
			 String subjectID = request.getParameter("newSubCode");//from jsp file will be called newSubCode
			 String major = request.getParameter("MajorSymbol");//from jsp file will be called MajorSymbol
			 DataCenter dc = new DataCenter(subjectID, major);
			 dc_DAO.insertSubject(dc);
			response.sendRedirect(request.getContextPath()+"/DataCenterServlet?action=list");
			}
	private void insertStudent(HttpServletRequest request, HttpServletResponse response)//---------------------oo
			throws SQLException, IOException {
			 String studentName = request.getParameter("newStudentName");//from jsp file will be called newStudentName
			 String studentType = request.getParameter("newStudentType");//from jsp file will be called newStudentType
			 DataCenter dc = new DataCenter(studentName, studentType);
			 dc_DAO.insertStudent(dc);
			response.sendRedirect(request.getContextPath()+"/DataCenterServlet?action=list");
			}
	private void insertEnrol(HttpServletRequest request, HttpServletResponse response)//---------------------oo  
			throws SQLException, IOException {
			 String subjectID = request.getParameter("courseEnrol");//from jsp file will be called courseEnrol
			 String surrogateKey = request.getParameter("newStudentType");//from jsp file will be called newStudentType
			 DataCenter dc = new DataCenter(subjectID, surrogateKey);
			 dc_DAO.insertEnrolment(dc, 0);//IMPORTANT! GET HELP AT THIS SECTION! YOU STILL DON'T KNOW WHAT YOU ARE DOING EXACTLY HERE
			response.sendRedirect(request.getContextPath()+"/DataCenterServlet?action=list");
			}
	
//	private void showEditEmployee(HttpServletRequest request, HttpServletResponse
//			response) throws SQLException, ServletException, IOException {
//			 int id = Integer.parseInt(request.getParameter("id"));
//			 Employee existingEmployee = empDAO.selectEmployee(id);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("employeeform.jsp");
//			request.setAttribute("employee", existingEmployee);
//			dispatcher.forward(request, response);
//			}
	private void editSubject(HttpServletRequest request, HttpServletResponse //sub_surr
			response) throws SQLException, ServletException, IOException {
			 int surrogateKey = Integer.parseInt(request.getParameter("surrogateKay"));//from jsp file will be called surrogateKay
			 DataCenter dataC = dc_DAO.selectSubject(surrogateKey);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Classform.jsp");
			request.setAttribute("dataK", surrogateKey);//YOU DON'T KNOW WHAT THE dataK PLACEHOLDER ACTUALLY DOES 
			dispatcher.forward(request, response);
			}
	private void editStudent(HttpServletRequest request, HttpServletResponse //stu_id
			response) throws SQLException, ServletException, IOException {
			 int studentID = Integer.parseInt(request.getParameter("studentKode"));//from jsp file will be called studentKode
			 DataCenter dataC = dc_DAO.selectStudent(studentID);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Classform.jsp");
			request.setAttribute("dataK", studentID);//YOU DON'T KNOW WHAT THE dataK PLACEHOLDER ACTUALLY DOES 
			dispatcher.forward(request, response);
			}

//	private void updateEmployee(HttpServletRequest request, HttpServletResponse
//			response) throws SQLException, IOException {
//			 int id = Integer.parseInt(request.getParameter("id"));
//			 String ename = request.getParameter("name");
//			 int eage = Integer.parseInt(request.getParameter("age"));
//			 Employee e = new Employee(id, ename, eage);
//			 empDAO.updateEmployee(e);
//			response.sendRedirect(request.getContextPath() +"/EmployeeServlet?action=list");
//			}
	private void updateSubject(HttpServletRequest request, HttpServletResponse //sub_id, sub_surr, major
			response) throws SQLException, IOException {
			 String subjectID = request.getParameter("subjektCode");//from jsp file will be called subjektCode
			 int surrogateKey = Integer.parseInt(request.getParameter("surrogateKeey"));//from jsp file will be called surrogateKeey
			 String major = request.getParameter("majorArtefact");//from jsp file will be called majorArtefact
			 DataCenter dc = new DataCenter(subjectID, surrogateKey, major);
			 dc_DAO.updateSubject(dc);
			response.sendRedirect(request.getContextPath() +"/DataCenterServlet?action=list");
			}
	private void updateStudent(HttpServletRequest request, HttpServletResponse
			response) throws SQLException, IOException {//stu_name, stu_type, stu_id
			 int studentID = Integer.parseInt(request.getParameter("student-id"));//from jsp file will be called student-id
			 String studentName = request.getParameter("nameOfKid");//from jsp file will be called nameOfKid
			 String studentType = request.getParameter("kidKapacity");//from jsp file will be called kidKapacity
			 DataCenter dc = new DataCenter(studentID, studentName, studentType);
			 dc_DAO.updateStudent(dc);
			response.sendRedirect(request.getContextPath() +"/DataCenterServlet?action=list");
			}
	
//	private void deleteEmployee(HttpServletRequest request, HttpServletResponse
//			response)
//			 throws SQLException, IOException {
//			 int id = Integer.parseInt(request.getParameter("id"));
//			 empDAO.deleteEmployee(id);
//			 response.sendRedirect(request.getContextPath()+"/EmployeeServlet?action=list");
//			 }
	private void deleteSubject(HttpServletRequest request, HttpServletResponse response)
			 throws SQLException, IOException {//sub_surr
			 int surrogateKey = Integer.parseInt(request.getParameter("subjectSurrogateCode"));//from jsp file will be called subjectSurrogateCode
			 dc_DAO.deleteSubject(surrogateKey);
			 response.sendRedirect(request.getContextPath()+"/DataCenterServlet?action=list");
			 }
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			 throws SQLException, IOException {//stu_id
			 int studentID = Integer.parseInt(request.getParameter("studentCode"));//from jsp file will be called subjectSurrogateCode
			 dc_DAO.deleteStudent(studentID);
			 response.sendRedirect(request.getContextPath()+"/DataCenterServlet?action=list");
			 }
	
}

