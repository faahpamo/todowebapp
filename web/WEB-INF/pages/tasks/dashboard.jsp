<%-- 
    Document   : dashboard
    Created on : 27 Apr 2020, 09:29:32
    Author     : Faisal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.github.faimoh.todoapp.model.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>ToDoApp - Tasks Dashboard</title>
    </head>
    <body>
        <h1>Tasks Dashboard</h1>
        <p>Hello! ${sessionScope.account.firstName}</p>
        <a href="<c:url value="/app/tasks/new"/>">New Task</a>
        <a href="<c:url value="/app/tasks/dashboard"/>">Dashboard</a>  
        <a href="<c:url value="/app/users/profile"/>">My Profile</a>
        <a href="<c:url value="/app/logout"/>">Logout</a>
        <br><br>        

        <table border="1">            
            <thead><th>Task ID</th><th>Details</th><th>Created At</th><th>Deadline</th>
            <th>Last Updated</th><th>Priority</th><th>Status</th></thead>                
                <c:forEach var="task" items="${requestScope.tasksList}">
            <tr>
                <td>
                    <a href="<c:url value="/app/tasks/details?id=${task.taskID}"/>">
                        ${task.taskID}
                    </a>
                </td>                
                <td>${task.details}</td>
                <td>${task.createdAt}</td>
                <td>${task.deadline}</td>
                <td>${task.lastUpdated}</td>
                <c:choose>
                    <c:when test="${task.priorityID==1}">
                        <td>Important & Urgent</td>
                    </c:when>
                    <c:when test="${task.priorityID==2}">
                        <td>Important but Not Urgent</td>
                    </c:when>
                    <c:when test="${task.priorityID==3}">
                        <td>Not Important but Urgent</td>
                    </c:when>
                    <c:when test="${task.priorityID==4}">
                        <td>Not Important & Not Urgent</td>
                    </c:when>
                </c:choose>   
                <c:choose>
                    <c:when test="${task.statusID==1}">
                        <td>To do</td>
                    </c:when>
                    <c:when test="${task.statusID==2}">
                        <td>In progress</td>
                    </c:when>
                    <c:when test="${task.statusID==3}">
                        <td>Done</td>
                    </c:when>
                </c:choose>                     
            </tr>
        </c:forEach>
    </table>
</body>
</html>
