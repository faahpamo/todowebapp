/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.faimoh.todowebapp.filters;

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
import io.github.faimoh.todowebapp.model.Account;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class AdminPortalFilter implements Filter {

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AdminPortalFilter() {

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

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            HttpSession session = httpRequest.getSession(false);
            boolean isLoggedIn = (session != null) && (session.getAttribute("account") != null);
            boolean isAdminURI = false;
            Account sessionUser = (Account) (session.getAttribute("account"));
            boolean isAdminUser = sessionUser.getAccountID() == 1;
            if (httpRequest.getRequestURI().contains("/app/admin/")) {
                isAdminURI = true;
            }

            if (isAdminUser && isAdminURI) {
                chain.doFilter(request, response);
            } else if (!isAdminUser && isAdminURI) {
                httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/app/tasks/dashboard");
            } else if (isAdminUser && !isAdminURI) {
                httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/app/admin/accouonts/dashboard");
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
            return ("AdminPortalFilter()");
        }
        StringBuffer sb = new StringBuffer("AdminPortalFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
}
