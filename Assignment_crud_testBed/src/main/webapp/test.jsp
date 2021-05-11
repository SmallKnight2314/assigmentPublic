<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center><h2>LEVEL2 Admin's Home</h2></center>
 
Welcome <!-- <%=request.getAttribute("userName") %> -->
 
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div> <!-- ignore -->

<body style = "font-family:arial,serif;">
 <div align="center" cellpadding=10>

 <table >
 <h2>List of Enrollments Summary</h2>
 <h3><a href="${pageContext.request.contextPath}/DataCenterServlet?action">List Enrollment Summary</a></h3>
<center>
 <h4>
 <a href="${pageContext.request.contextPath}/DataCenterServlet?action=newStudent">Add New
Student</a>
<a href="${pageContext.request.contextPath}/DataCenterServlet?action=newSubject">Add New
Subject</a>
<a href="${pageContext.request.contextPath}/DataCenterServlet?action=enrol">Enroll</a>
 </h4>
</center>
 <tr align=center>
 <th>ID</th>
 <th>Name</th>
 <th>Student Type</th>
 <th>Major</th>
 <th>Subject Count</th>

  </tr>
 <c:forEach var="Sub" items="${listEnrolmentsSummary}">
 <tr align=center>
 <td><c:out value="${Sub.getStudentID()}" /></td>
 <td><c:out value="${Sub.getStudentName()}" /></td>
 <td><c:out value="${Sub.getStudentType()}" /></td>
 <td><c:out value="${Sub.getMajor()}" /></td>
 <td><c:out value="${Sub.getSubjectCount()}" /></td>
 <td>
 |<a
href="${pageContext.request.contextPath}/DataCenterServlet?action=edit&id=<c:out
value='${emp.getEid()}' />">Edit</a>|
 |<a
href="${pageContext.request.contextPath}/DataCenterServlet?action=delete&id=<c:out
value='${emp.getEid()}' />">Delete</a>|
 </td>
 </tr>
 </c:forEach>
 </table>
 </div>
</body>

</html>