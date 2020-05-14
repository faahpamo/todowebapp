<%-- 
    Document   : details
    Created on : 20 Apr 2020, 21:43:38
    Author     : Faisal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.github.faimoh.todoapp.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToDoApp - Task Details</title>
    </head>
    <body>
        <h1>Task Details</h1>
        <p>Hello! ${sessionScope.account.firstName}</p>
        <a href="<c:url value="/app/tasks/new"/>">New Task</a>
        <a href="<c:url value="/app/tasks/dashboard"/>">Dashboard</a>  
        <a href="<c:url value="/app/users/profile"/>">My Profile</a>
        <a href="<c:url value="/app/logout"/>">Logout</a>
        <br><br>
        <b>${requestScope.message}</b>
        <form method="POST" action="<c:url value="/app/tasks/update"/>">              
            <fieldset>
                <legend>Details</legend>
                <input type="hidden" name="taskID" value="${requestScope.task.taskID}">

                <br>
                <label>Task ID:</label>${requestScope.task.taskID}
                <br>            
                <br>
                <label>Created At:</label>
                <br>
                ${requestScope.task.createdAt}
                <br>            
                <br> 
                <label>Last Updated:</label>
                <br>
                ${requestScope.task.lastUpdated}
                <br>            
                <br> 
                <label>What do you want to accomplish?</label>
                <br>
                <textarea name="details" rows="4" columns="16">${requestScope.task.details}</textarea>            
                <br><br>  
                <label for="date">By what date and time?</label>

                <c:choose>
                    <c:when test="${requestScope.task.deadline != null}">                                     
                        <input name="date" type="date" 
                               value="<fmt:formatDate value="${requestScope.task.deadline}" pattern="yyyy-MM-dd"/>" 
                               min="<fmt:formatDate value="${requestScope.task.deadline}" pattern="yyyy-MM-dd"/>"/>
                        <input name="time" type="time" 
                               value="<fmt:formatDate value="${requestScope.task.deadline}" pattern="HH:mm:ss"/>"
                               min="<fmt:formatDate value="${requestScope.task.deadline}" pattern="HH:mm:ss"/>"/>
                    </c:when>
                    <c:otherwise>
                        <jsp:useBean id="tomorrow" class="java.util.Date" scope="page" /> 
                        <jsp:setProperty name="tomorrow" property="time" value="${tomorrow.time + 86400000}"/>                    
                        <input name="date" type="date" 
                               value="<fmt:formatDate value="${tomorrow}" pattern="yyyy-MM-dd"/>" 
                               min="<fmt:formatDate value="${tomorrow}" pattern="yyyy-MM-dd"/>"/>
                        <input name="time" type="time" 
                               value="<fmt:formatDate value="${tomorrow}" pattern="HH:mm:ss"/>"
                               min="<fmt:formatDate value="${tomorrow}" pattern="HH:mm:ss"/>"/>
                    </c:otherwise>
                </c:choose>

                <br><br>
                <label for="priorityID">Task priority:</label>
                <select id="priorityID" name="priorityID" required>                
                    <option value="1" ${requestScope.task.priorityID==1?'selected':''}>Important & Urgent</option>
                    <option value="2" ${requestScope.task.priorityID==2?'selected':''}>Important but Not Urgent</option>
                    <option value="3" ${requestScope.task.priorityID==3?'selected':''}>Not Important but Urgent</option>
                    <option value="4" ${requestScope.task.priorityID==4?'selected':''}>Not Important & Not Urgent</option>
                </select>
                <br><br>
                <label for="statusID">Task status:</label>
                <input type="radio" id="1" name="statusID" value="1" ${requestScope.task.statusID==1?'checked':''}>
                <label for="1">To Do</label>
                <input type="radio" id="2" name="statusID" value="2" ${requestScope.task.statusID==2?'checked':''}>
                <label for="2">In Progress</label>
                <input type="radio" id="3" name="statusID" value="3" ${requestScope.task.statusID==3?'checked':''}>
                <label for="3">Done</label>
                <br><br>
                <input type="submit" value="Update">

            </fieldset>
        </form>
    </body>
</html>
