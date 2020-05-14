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

import com.github.faimoh.todoapp.db.*;
import com.github.faimoh.todoapp.model.*;
import javax.servlet.http.*;
import java.util.*;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class LoginAction implements Action {

    private ActionResponse actionResponse = new ActionResponse();

    public ActionResponse execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String context = request.getContextPath();
            String servlet = request.getServletPath();

            DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
            AccountDAO accountDAO = daoFactory.getAccountDAO();
            Account account = accountDAO.findAccount(username);
            System.out.println(this.getClass().getName() + ":" + account);

            if (account == null) {
                request.setAttribute("message", "Account doesn't exist.");
                this.actionResponse.setMethod("forward");
                this.actionResponse.setViewPath("/WEB-INF/pages/login.jsp");
            } else if (account.getStatusID().intValue() == 2) {
                request.setAttribute("message", "Account disabled. Contact administrator.");
                this.actionResponse.setMethod("forward");
                this.actionResponse.setViewPath("/WEB-INF/pages/login.jsp");
            } else {
                if (!account.getPassword().equals(password)) {
                    request.setAttribute("message", "Authentication failed. Check credentials supplied.");
                    this.actionResponse.setMethod("forward");
                    this.actionResponse.setViewPath("/WEB-INF/pages/login.jsp");
                }
                if (account.getPassword().equals(password)) {
                    HttpSession oldSession = request.getSession(false);
                    if (oldSession != null) {
                        oldSession.invalidate();
                    }
                    HttpSession session = request.getSession(true);                  
                    //session.setMaxInactiveInterval(30 * 60);

                    session.setAttribute("account", account);
                    AccountSession accountSession = new AccountSession();
                    accountSession.setSessionID(session.getId());
                    accountSession.setAccountID(account.getAccountID());
                    accountSession.setSessionCreated(new java.sql.Timestamp(session.getCreationTime()));
                    AccountSessionDAO accountSessionDAO = daoFactory.getAccountSessionDAO();
                    accountSessionDAO.insertAccountSession(accountSession);
                    AccountSession accountPreviousSession = accountSessionDAO.findLastAccountSession(account.getAccountID());
                    session.setAttribute("accountPreviousSession", accountPreviousSession);                    
                    java.sql.Timestamp timeStamp = new java.sql.Timestamp(session.getCreationTime());                    
                    this.actionResponse.setMethod("redirect");
                    if (account.getUsername().equals("admin")) {
                        this.actionResponse.setViewPath(context + servlet + "/admin/accounts/dashboard");
                    } else {
                        this.actionResponse.setViewPath(context + servlet + "/tasks/dashboard");
                    }
                }
            }
            System.out.println(this.getClass().getName() + ":" + this.actionResponse);
            return this.actionResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.actionResponse;
    }
}
