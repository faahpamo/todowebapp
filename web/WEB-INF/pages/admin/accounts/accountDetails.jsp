<%-- 
    Document   : details
    Created on : 20 Apr 2020, 21:43:38
    Author     : Faisal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToDoApp - Account Details</title>        
    </head>
    <body>
        <h1>Account Information</h1>
        <p>Hello! ${sessionScope.account.firstName}</p>
        <a href="<c:url value="/app/admin/accounts/dashboard"/>">Dashboard</a> 
        <a href="<c:url value="/app/admin/accounts/new"/>">New Account</a>
        <a href="<c:url value="/app/users/profile"/>">My Profile</a>
        <a href="<c:url value="/app/logout"/>">Logout</a>
        <br><br>
        <b>${requestScope.message}</b>
        <br><br>
        <form method="POST" action="<c:url value="/app/admin/accounts/update"/>">
            <fieldset>
                <legend>Details</legend>
                <input type="hidden" name="accountID" value="${requestScope.account.accountID}">
                <input type="hidden" name="username" value="${requestScope.account.username}">
                <label for="accountID">Account ID: </label>${requestScope.account.accountID}
                <br><br>            
                <label for="username">Username: </label>${requestScope.account.username}
                <br><br>            
                <label for="firstName">First name:</label>
                <input type="text" id="firstName" name="firstName" value="${requestScope.account.firstName}" required><br><br>
                <label for="lastName">Last name:</label>
                <input type="text" id="lastName" name="lastName" value="${requestScope.account.lastName}" required><br><br>
                <label for="password">Reset password to default:</label>
                <input type="checkbox" id="password" name="password" value="reset"><br><br>
                <label for="status">Account status:</label>
                <input type="radio" id="enabled" name="status" value="1" ${requestScope.account.statusID==1?'checked':''}>
                <label for="enabled">Enabled</label>
                <input type="radio" ${requestScope.account.accountID==1?'disabled':''} id="disabled" name="status" value="2" ${requestScope.account.statusID==2?'checked':''}>
                <label for="disabled">Disabled</label><br><br>
                <input type="submit" value="Update">
            </fieldset>

        </form>
    </body>
</html>
