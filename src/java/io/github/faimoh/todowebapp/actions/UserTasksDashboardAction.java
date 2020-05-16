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

import io.github.faimoh.todowebapp.model.Account;
import io.github.faimoh.todowebapp.model.Task;
import io.github.faimoh.todowebapp.dao.TaskDAO;
import io.github.faimoh.todowebapp.dao.DAOFactory;
import java.util.ArrayList;
import javax.servlet.http.*;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class UserTasksDashboardAction implements Action {

    private ActionResponse actionResponse = new ActionResponse();

    public ActionResponse execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Account sessionUser = (Account) request.getSession(false).getAttribute("account");
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
            TaskDAO taskDAO = daoFactory.getTaskDAO();
            ArrayList<Task> tasksList = taskDAO.getAllTasks(sessionUser);
            request.setAttribute("tasksList", tasksList);
            this.actionResponse.setMethod("forward");
            this.actionResponse.setViewPath("/WEB-INF/pages/tasks/dashboard.jsp");
            return this.actionResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }        
        return this.actionResponse;
    }
}
