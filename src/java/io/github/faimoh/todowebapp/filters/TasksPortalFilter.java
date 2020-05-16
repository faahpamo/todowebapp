/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.faimoh.todowebapp.filters;

import io.github.faimoh.todowebapp.model.Account;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class TasksPortalFilter implements Filter {

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public TasksPortalFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        Throwable problem = null;
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            HttpSession session = httpRequest.getSession(false);
            boolean isLoggedIn = (session != null) && (session.getAttribute("account") != null);
            boolean isTasksURI = false;
            Account sessionUser = (Account) (session.getAttribute("account"));
            boolean isNonAdminUser = sessionUser.getAccountID() != 1;
            if (httpRequest.getRequestURI().contains("/app/tasks/")) {
                isTasksURI = true;
            }

            if (isNonAdminUser && isTasksURI) {
                chain.doFilter(request, response);
            } else if (!isNonAdminUser && isTasksURI) {
                httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/app/admin/accounts/dashboard");
            } else if (isNonAdminUser && !isTasksURI) {
                httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/app/tasks/dashboard");
            } else {
                httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("TasksPortalFilter()");
        }
        StringBuffer sb = new StringBuffer("TasksPortalFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
}
