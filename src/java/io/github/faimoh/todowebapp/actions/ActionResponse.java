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

/**
 * ActionResponse object carries the response generated from Action.execute().
 * Servlet API provides us methods like forward, redirect and include to work with
 * the response that our web application sends back to the client. We also have viewPath
 * to collect the path of the resource to whom the request should be handed over to. For
 * example JSP, HTML or another servlet etc.
 * 
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class ActionResponse {
    private String method;
    private String viewPath;
    
    public ActionResponse() {
        this.method = "";
        this.viewPath = "";
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
    
    public String getMethod() {
        return this.method;
    }
    
    public void setViewPath(String viewPath) {
        this.viewPath = viewPath;
    }
    
    public String getViewPath() {
        return this.viewPath;
    }
    
    @Override
    public String toString() {
        return this.getClass().getName()+"["+this.method+":"+this.viewPath+"]";
    }
}
