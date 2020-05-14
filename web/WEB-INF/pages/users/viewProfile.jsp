<%-- 
    Document   : profile
    Created on : 3 May 2020, 22:01:53
    Author     : Faisal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ToDoApp - User Profile</title>
    </head>
    <body>
        <h1>Your profile</h1>
        <p>Hello! ${sessionScope.account.firstName}</p> 
        <a href="<c:url value="/app/tasks/dashboard"/>">Dashboard</a>
        <a href="<c:url value="${sessionScope.account.accountID==1?'/app/admin/accounts/new':'/app/tasks/new'}"/>">
            ${sessionScope.account.accountID==1?'New Account':'New Task'}</a>                
        <a href="<c:url value="/app/users/profile"/>">My Profile</a>
        <a href="<c:url value="/app/logout"/>">Logout</a>
        <br><br>
        <b>${requestScope.message}</b><br>
        <form method="POST" action="<c:url value="/app/users/update"/>">
            <fieldset>
                <legend>Profile details</legend>
                <label for="accountID">Account ID: </label>${sessionScope.account.accountID}<br><br>
                <label for="username">Username: </label>${sessionScope.account.username}<br><br>
                <label>Created at: </label>${sessionScope.account.createdAt}<br><br>
                <label>Last login at: </label>${sessionScope.accountPreviousSession.sessionCreated}<br><br>
                <label for="firstName">First name: </label>
                <input type="text" id="firstName" name="firstName" value="${sessionScope.account.firstName}" required><br><br>
                <label for="lastName">Last name: </label>
                <input type="text" id="lastName" name="lastName" value="${sessionScope.account.lastName}" required><br><br>
                <label for="password">New password: </label>
                <input type="password" name="password" value=""><br><br>
                <input type="hidden" name="accountID" value="${requestScope.account.accountID}">
                <input type="hidden" name="username" value="${requestScope.account.username}">
                <input type="submit" value="Update">
            </fieldset>
        </form>
        <br><br>
        <p>If you face trouble signing in to your account after changing your password, ask administrator for help.<br>
            Your administrator can help reset your password.
        </p>

    </body>
</html>
