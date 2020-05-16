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
package io.github.faimoh.todowebapp.controllers;

import io.github.faimoh.todowebapp.actions.ActionFactory;
import io.github.faimoh.todowebapp.actions.Action;
import io.github.faimoh.todowebapp.actions.ActionResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;

/**
 * The servlet that implements the controller part of the MVC for the web application.
 * This is the only controller for the entire web application. It handles all requests from
 * the client and generates appropriate response for each request.
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class Main extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Action action = ActionFactory.getAction(request);
        try {
            ActionResponse actionResponse = action.execute(request, response);
            if (actionResponse.getMethod().equalsIgnoreCase("forward")) {
                System.out.println(this.getClass().getCanonicalName() + ":forward:" + actionResponse);
                this.getServletContext().getRequestDispatcher(actionResponse.getViewPath()).forward(request, response);
            } else if (actionResponse.getMethod().equalsIgnoreCase("redirect")) {
                System.out.println(this.getClass().getCanonicalName() + ":redirect:" + actionResponse);
                if (actionResponse.getViewPath().equals(request.getContextPath())) {                    
                    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
                    response.setHeader("Pragma", "no-cache");
                    response.setDateHeader("Expires", 0);
                }
                response.sendRedirect(actionResponse.getViewPath());
            } else if (actionResponse.getMethod().equalsIgnoreCase("error")) {
                System.out.println(this.getClass().getCanonicalName() + ":error:" + actionResponse);
                response.sendError(401);
            } else {
                System.out.println(this.getClass().getCanonicalName() + ":" + actionResponse);
                response.sendRedirect(request.getContextPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
