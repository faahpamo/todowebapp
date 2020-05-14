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
import java.sql.*;
import java.text.*;
import java.util.Date;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed
 */
public class UserCreateTaskAction implements Action {

    private ActionResponse actionResponse = new ActionResponse();

    public ActionResponse execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String context = request.getContextPath();
        String servlet = request.getServletPath();
        HttpSession session = request.getSession(false);
        Account sessionUser = (Account) session.getAttribute("account");
        if (sessionUser == null) {
            this.actionResponse.setMethod("redirect");
            this.actionResponse.setViewPath(context);
        } else {
            String stringAccountID = request.getParameter("accountID");
            String details = request.getParameter("details");
            String stringPriorityID = request.getParameter("priorityID");
            String stringDate = request.getParameter("date");
            String stringTime = request.getParameter("time");
            String message = "";

            if (stringAccountID == null) {
                stringAccountID = "";
            }
            if (details == null) {
                details = "";
            }
            if (stringPriorityID == null) {
                stringPriorityID = "";
            }
            int accountID = Utilities.parseWithDefault(stringAccountID, 0);
            int priorityID = Utilities.parseWithDefault(stringPriorityID, 1);
            if (sessionUser.getAccountID() != accountID) {
                message = "Forbidden.";
                request.setAttribute("message", message);
                this.actionResponse.setMethod("forward");
                this.actionResponse.setViewPath("/WEB-INF/pages/tasks/createTaskResult.jsp");
            } else {
                if (details.isEmpty()) {
                    message = "Make sure your task details are not empty.";
                    request.setAttribute("message", message);
                    this.actionResponse.setMethod("forward");
                    this.actionResponse.setViewPath("/WEB-INF/pages/tasks/createTaskResult.jsp");
                } else {
                    DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
                    TaskDAO taskDAO = daoFactory.getTaskDAO();
                    Task task = new Task();
                    task.setAccountID(sessionUser.getAccountID());
                    task.setDetails(details);
                    task.setDeadline(Utilities.parseDateAndTime(stringDate, stringTime));
                    task.setPriorityID(priorityID);
                    boolean isTaskCreated = taskDAO.insertTask(task);
                    if (isTaskCreated) {
                        /*message = "Successfully created a new task.<br> Create another task:";
                            request.setAttribute("message", message);
                            this.actionResponse.setMethod("forward");
                            this.actionResponse.setViewPath("/WEB-INF/pages/tasks/createTaskResult.jsp");*/
                        this.actionResponse.setMethod("redirect");
                        this.actionResponse.setViewPath(context + servlet + "/tasks/dashboard");
                    } else {
                        message = "Technical error. Please try again later.";
                        request.setAttribute("message", message);
                        this.actionResponse.setMethod("forward");
                        this.actionResponse.setViewPath("/WEB-INF/pages/tasks/createTaskResult.jsp");
                    }
                }
            }
        }
        return this.actionResponse;
    }

}
