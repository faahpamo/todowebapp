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

import java.util.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class ActionFactory {
    private static Map<String, Action> actions = new HashMap<String, Action>() {
        {
            put(new String("POST/login"), new LoginAction());
            put(new String("GET/login"), new LoginAction());
            put(new String("GET/logout"), new LogoutAction());
            put(new String("GET/admin/accounts/dashboard"), new AdminAccountsDashboardAction());
            put(new String("GET/admin/accounts/new"), new AdminNewAccountFormAction());
            put(new String("POST/admin/accounts/create"), new AdminCreateAccountAction());
            put(new String("GET/admin/accounts/details"), new AdminReadAccountDetailsAction());
            put(new String("POST/admin/accounts/update"), new AdminUpdateAccountAction());
            put(new String("GET/tasks/dashboard"), new UserTasksDashboardAction());
            put(new String("GET/tasks/new"), new UserNewTaskFormAction());
            put(new String("GET/tasks/details"), new UserReadTaskDetailsAction());
            put(new String("POST/tasks/create"), new UserCreateTaskAction());
            put(new String("POST/tasks/update"), new UserUpdateTaskAction());            
            put(new String("GET/users/profile"), new UserReadProfileAction());  
            put(new String("POST/users/update"), new UserUpdateProfileAction()); 
        }
    ;
    };
    
    public static Action getAction(HttpServletRequest request) {
        Action action = actions.get(request.getMethod() + request.getPathInfo());
        if (action == null) {
            return new UnknownAction();
        } else {
            return action;
        }
    }
}
