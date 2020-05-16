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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class AdminUpdateAccountAction implements Action {

    private ActionResponse actionResponse = new ActionResponse();

    public ActionResponse execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {        
        String message = "";
        String accountID = request.getParameter("accountID");
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String status = request.getParameter("status");
        System.out.println(this.getClass().getCanonicalName() + ":" + status);
        if (username == null || username == "" || firstName == "" || firstName == null || lastName == "" || lastName == null) {
            message = "One or more required fields are empty.";
            Account account = new Account();
            account.setAccountID(Integer.parseInt(accountID));
            account.setUsername(username);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setStatusID(Integer.parseInt(status));
            request.setAttribute("account", account);
            request.setAttribute("message", message);
            this.actionResponse.setMethod("forward");
            this.actionResponse.setViewPath("/WEB-INF/pages/admin/accounts/updateAccountResult.jsp");
        } else {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
            AccountDAO accountDAO = daoFactory.getAccountDAO();
            Account accountToUpdate = accountDAO.findAccount(username);
            if (accountToUpdate == null) {
                message = "Update failed - Account doesn't exist!";
                request.setAttribute("accountID", accountID);
                request.setAttribute("username", username);
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                request.setAttribute("message", message);
                this.actionResponse.setMethod("forward");
                this.actionResponse.setViewPath("/WEB-INF/pages/admin/accounts/updateAccountResult.jsp");
            } else {                
                accountToUpdate.setFirstName(firstName);
                accountToUpdate.setLastName(lastName);
                if (status.equals("1")) {
                    accountToUpdate.setStatusID(1);
                } else {
                    accountToUpdate.setStatusID(2);
                }
                boolean isAccountUpdated = false;
                if (password == null) {
                    isAccountUpdated = accountDAO.updateAccount(accountToUpdate, false);
                } else {
                    isAccountUpdated = accountDAO.updateAccount(accountToUpdate, true);
                }
                if (isAccountUpdated) {
                    message = "Successfully updated the account.";
                    //Maybe admin has updated his own account.
                    Account sessionUser = (Account) request.getSession(false).getAttribute("account");
                    if (accountToUpdate.getAccountID() == sessionUser.getAccountID()) {
                        sessionUser.setFirstName(accountToUpdate.getFirstName());
                        sessionUser.setLastName(accountToUpdate.getLastName());
                    }
                    request.setAttribute("account", accountToUpdate);
                    request.setAttribute("message", message);
                    this.actionResponse.setMethod("forward");
                    this.actionResponse.setViewPath("/WEB-INF/pages/admin/accounts/updateAccountResult.jsp");
                } else {
                    message = "Technical error. Please try again.";                   
                    request.setAttribute("account", accountToUpdate);
                    request.setAttribute("message", message);
                    this.actionResponse.setMethod("forward");
                    this.actionResponse.setViewPath("/WEB-INF/pages/admin/accounts/updateAccountResult.jsp");
                }
            }
        }
        return this.actionResponse;
    }
}
