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
import com.github.faimoh.todoapp.model.Task;
import com.github.faimoh.todoapp.db.DAOFactory;
import com.github.faimoh.todoapp.db.TaskDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Read the given task ID's details from database. Make sure the task is
 * associated with the currently logged in user.
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class UserReadTaskDetailsAction implements Action {

    private ActionResponse actionResponse = new ActionResponse();    

    public ActionResponse execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String stringID = request.getParameter("id");
        if (stringID == null) {
            stringID = "";
        }
        String message = "";
        Account sessionUser = (Account) request.getSession(false).getAttribute("account");
        try {
            if (stringID.isEmpty()) {
                message = "Task ID can't be empty.";
                request.setAttribute("message", message);
            } else {
                int id = Utilities.parseWithDefault(stringID, 0);                
                DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
                TaskDAO taskDAO = daoFactory.getTaskDAO();
                Task task = taskDAO.findTask(id);
                if (task == null) {
                    message = "No such task exists.";
                    request.setAttribute("message", message);
                } else if (sessionUser.getAccountID().intValue() != task.getAccountID().intValue()) {
                    message = "Forbidden. You are not allowed to see others' task.";
                    request.setAttribute("message", message);
                } else {
                    request.setAttribute("task", task);
                }
            }
            this.actionResponse.setMethod("forward");
            this.actionResponse.setViewPath("/WEB-INF/pages/tasks/taskDetails.jsp");
            return this.actionResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.actionResponse;
    }

}
