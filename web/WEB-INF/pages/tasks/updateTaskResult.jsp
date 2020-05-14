<%-- 
    Document   : updateTaskResult
    Created on : 5 May 2020, 06:31:48
    Author     : Faisal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToDoApp - Update Task</title>
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
                <input type="hidden" name="accountID" value="${requestScope.task.accountID}">
                <input type="hidden" name="taskID" value="${requestScope.task.taskID}">
                <input type="hidden" name="createdAt" value="${requestScope.task.createdAt}">            
                <br>
                <label for="username">Task ID: </label>${requestScope.task.taskID}
                <br><br>
                <label for="lastName">Created At: </label>
                <br>
                ${requestScope.task.createdAt}
                <br><br> 
                <label for="firstName">What do you want to accomplish?</label>
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
                        <jsp:useBean id="today" class="java.util.Date" scope="page" />            
                        <input name="date" type="date" 
                               value="<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>" 
                               min="<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>"/>
                        <input name="time" type="time" 
                               value="<fmt:formatDate value="${today}" pattern="HH:mm:ss"/>"
                               min="<fmt:formatDate value="${today}" pattern="HH:mm:ss"/>"/>
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
                <label for="status">Task status:</label>
                <input type="radio" name="status" value="enabled" ${requestScope.task.statusID==1?'checked':''}>
                <label for="enabled">To Do</label>
                <input type="radio" name="status" value="disabled" ${requestScope.task.statusID==2?'checked':''}>
                <label for="disabled">In Progress</label>
                <input type="radio" name="status" value="disabled" ${requestScope.task.statusID==3?'checked':''}>
                <label for="disabled">Done</label>
                <br><br>
                <input type="submit" value="Update">
            </fieldset>
        </form>
    </body>
</html>
