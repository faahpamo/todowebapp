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
package com.github.faimoh.todoapp.actions;

import com.github.faimoh.todoapp.model.Account;
import com.github.faimoh.todoapp.db.DAOFactory;
import com.github.faimoh.todoapp.db.AccountDAO;
import com.github.faimoh.todoapp.db.AccountSessionDAO;
import com.github.faimoh.todoapp.model.AccountSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ProfileUpdateAction supports updating the profile by a currently signed in
 * user. Profile details that can be modified through this action are first
 * name, last name and password. Currently signed in user can be obtained from
 * the HttpSession object.
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class UserUpdateProfileAction implements Action {

    private ActionResponse actionResponse = new ActionResponse();

    public ActionResponse execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");

        String message = "";
        String context = request.getContextPath();
        String servlet = request.getServletPath();

        Account sessionUser = (Account) request.getSession(false).getAttribute("account");
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
        AccountDAO accountDAO = daoFactory.getAccountDAO();        

        if (sessionUser == null) {
            this.actionResponse.setMethod("redirect");
            this.actionResponse.setViewPath("");
        } else {
            sessionUser.setFirstName(firstName);
            sessionUser.setLastName(lastName);
            boolean isAccountUpdated = false;
            boolean isPasswordChanged = false;
            if (password == null || password.equals("")) {
                isAccountUpdated = accountDAO.updateAccount(sessionUser);
                message = "Successfully updated your account.";
                request.setAttribute("message", message);                
                this.actionResponse.setMethod("forward");
                this.actionResponse.setViewPath("/WEB-INF/pages/users/updateProfileResult.jsp");
            } else {
                sessionUser.setPassword(password);
                isPasswordChanged = accountDAO.changePassword(sessionUser);
                isAccountUpdated = accountDAO.updateAccount(sessionUser);
                isAccountUpdated = isPasswordChanged & isAccountUpdated;
                if (isAccountUpdated) {
                    message = "Successfully updated your account.";                    
                    request.setAttribute("message", message);                    
                    this.actionResponse.setMethod("forward");
                    this.actionResponse.setViewPath("/WEB-INF/pages/users/updateProfileResult.jsp");
                } else {
                    message = "Technical error. Please try again.";
                    request.setAttribute("message", message);
                    this.actionResponse.setMethod("forward");
                    this.actionResponse.setViewPath("/WEB-INF/pages/users/updateProfileResult.jsp");
                }
            }
        }
        return this.actionResponse;
    }
}
