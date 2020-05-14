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
        <title>ToDoApp - Admin - Dashboard</title>
    </head>
    <body>
        <h1>Accounts Dashboard</h1>
        <p>Hello! ${sessionScope.account.firstName}</p>        
        <a href="<c:url value="/app/admin/accounts/dashboard"/>">Dashboard</a> 
        <a href="<c:url value="/app/admin/accounts/new"/>">New Account</a>
        <a href="<c:url value="/app/users/profile"/>">My Profile</a>
        <a href="<c:url value="/app/logout"/>">Logout</a>
        <br><br>
        
        <table border="1">
            <thead>
            <th>ID</th>
            <th>Username</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Password</th>
            <th>Created At</th>
            <th>Status</th>
        </thead>
        <c:forEach var="user" items="${requestScope.accountsList}">
        <tr>
            <td>
                <a href="${pageContext.servletContext.contextPath}/app/admin/accounts/details?id=${user.accountID}">
                    ${user.accountID}
                </a>
            </td>
            <td>${user.username}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.password}</td>
            <td>${user.createdAt}</td>
            <td>${user.statusID==1?'Enabled':'Disabled'}</td>
        </tr>
        </c:forEach>
        
    </table>
</body>
</html>
