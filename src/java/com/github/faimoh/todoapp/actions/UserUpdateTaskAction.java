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

import com.github.faimoh.todoapp.db.DAOFactory;
import com.github.faimoh.todoapp.db.TaskDAO;
import com.github.faimoh.todoapp.model.Account;
import com.github.faimoh.todoapp.model.Task;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class UserUpdateTaskAction implements Action {

    private ActionResponse actionResponse = new ActionResponse();
    
    public ActionResponse execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String message = "";
        String context = request.getContextPath();
        String servlet = request.getServletPath();
        HttpSession session = request.getSession(false);
        Account sessionUser = (Account) session.getAttribute("account");

        String stringTaskID = request.getParameter("taskID");
        String details = request.getParameter("details");
        String stringStatusID = request.getParameter("statusID");
        String stringPriorityID = request.getParameter("priorityID");
        String stringDate = request.getParameter("date");
        String stringTime = request.getParameter("time");
        int taskID = Utilities.parseWithDefault(stringTaskID, 0);
        int statusID = Utilities.parseWithDefault(stringStatusID, 1);
        int priorityID = Utilities.parseWithDefault(stringPriorityID, 1);
        Timestamp dateTime = Utilities.parseDateAndTime(stringDate, stringTime);

        if (details == null) {
            details = "";
        }

        if (taskID == 0) {
            message = "Invalid task ID.";
            request.setAttribute("message", message);
            this.actionResponse.setMethod("forward");
            this.actionResponse.setViewPath("/WEB-INF/pages/tasks/taskDetails.jsp");
        } else if (details.equals("")) {
            message = "Make sure your task details are not empty.";
            request.setAttribute("message", message);
            this.actionResponse.setMethod("forward");
            this.actionResponse.setViewPath("/WEB-INF/pages/tasks/updateTaskResult.jsp");
        } else {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
            TaskDAO taskDAO = daoFactory.getTaskDAO();
            Task task = taskDAO.findTask(taskID);
            if (task == null) {
                message = "No such task exists.";
                request.setAttribute("message", message);
                this.actionResponse.setMethod("forward");
                this.actionResponse.setViewPath("/WEB-INF/pages/tasks/taskDetails.jsp");
            } else if (sessionUser.getAccountID().intValue() != task.getAccountID().intValue()) {
                message = "Forbidden. This is not your task.";
                request.setAttribute("message", message);
                this.actionResponse.setMethod("forward");
                this.actionResponse.setViewPath("/WEB-INF/pages/tasks/taskDetails.jsp");
            } else {
                task.setAccountID(sessionUser.getAccountID());
                task.setDetails(details);
                task.setStatusID(statusID);
                task.setDeadline(dateTime);
                task.setPriorityID(priorityID);
                boolean isTaskUpdated = taskDAO.updateTask(task);
                if (isTaskUpdated) {
                    this.actionResponse.setMethod("redirect");
                    this.actionResponse.setViewPath(context + servlet + "/tasks/dashboard");
                } else {
                    message = "Technical error. Please try again later. Or contact administrator.";
                    request.setAttribute("message", message);
                    this.actionResponse.setMethod("forward");
                    this.actionResponse.setViewPath("/WEB-INF/pages/tasks/updateTaskResult.jsp");
                }
            }
        }
        return this.actionResponse;
    }
}
