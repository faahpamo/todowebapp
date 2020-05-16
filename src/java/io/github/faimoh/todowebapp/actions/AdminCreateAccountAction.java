/* 
 * Copyright (c) 2020, Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package io.github.faimoh.todowebapp.actions;

import io.github.faimoh.todowebapp.dao.AccountDAO;
import io.github.faimoh.todowebapp.dao.DAOFactory;
import io.github.faimoh.todowebapp.model.Account;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class relates with the admin user's use case of creating a new account.
 * A new user account can be created with three required account properties: 
 * username, firstName and lastName.
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class AdminCreateAccountAction implements Action {

    private ActionResponse actionResponse = new ActionResponse();

    public ActionResponse execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String username = Utilities.parseRequestParameterWithDefault(request.getParameter("username"),"");
        String firstName = Utilities.parseRequestParameterWithDefault(request.getParameter("firstName"),"");
        String lastName = Utilities.parseRequestParameterWithDefault(request.getParameter("lastName"),"");

        String message = "";
        /*
        HTML has the ability to make sure empty parameters are not sent. But, still we check.
        The below 'if' should never get executed because we set 'required' for all input fields in the HTML form.
        So, we shouldn't receive any empty/blank/null parameters.
        */
        if (username == "" || firstName == "" || lastName == "") {
            message = "One or more required fields are empty.";
            request.setAttribute("username", username);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("message", message);
            this.actionResponse.setMethod("forward");
            this.actionResponse.setViewPath("/WEB-INF/pages/admin/accounts/createAccountResult.jsp");
        } else {
            //First, check if the username is already taken.
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
            AccountDAO accountDAO = daoFactory.getAccountDAO();
            Account account = accountDAO.findAccount(username);
            boolean isUsernameTaken = account != null ? true : false;
            if (isUsernameTaken) {
                message = "Username already exists. Try another.";
                request.setAttribute("username", username);
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("message", message);
                this.actionResponse.setMethod("forward");
                this.actionResponse.setViewPath("/WEB-INF/pages/admin/accounts/createAccountResult.jsp");
            } else {
                //The username is not yet taken. So, we can go ahead.
                Account newAccount = new Account();
                newAccount.setUsername(username);
                newAccount.setFirstName(firstName);
                newAccount.setLastName(lastName);
                boolean isAccountCreated = accountDAO.insertAccount(newAccount);
                if (isAccountCreated) {
                    message = "Successfully created a new account.<br> Create another account:";
                    request.setAttribute("message", message);
                    this.actionResponse.setMethod("forward");
                    this.actionResponse.setViewPath("/WEB-INF/pages/admin/accounts/createAccountResult.jsp");
                } else {
                    //If we are here, that means something wrong has happened with database query execution.
                    message = "Technical error. Please try again later.";
                    request.setAttribute("message", message);
                    this.actionResponse.setMethod("forward");
                    this.actionResponse.setViewPath("/WEB-INF/pages/admin/accounts/createAccountResult.jsp");
                }
            }
        }
        return this.actionResponse;
    }
}
