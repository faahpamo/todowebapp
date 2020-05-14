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
package com.github.faimoh.todoapp.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class Account implements Serializable {

    private Integer accountID;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Timestamp createdAt;    
    private Integer statusID;    

    public Account() {

    }

    public Integer getAccountID() {
        return this.accountID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String emailAddress) {
        this.username = emailAddress;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public Integer getStatusID() {
        return this.statusID;
    }

    @Override
    public String toString() {
        return "Account[id=" + this.accountID
                + ", username=" + this.username
                + ", firstName=" + this.firstName
                + ", lastName=" + this.lastName
                + ", password=" + this.password
                + ", createdAt=" + this.createdAt
                + ", status=" + this.statusID                
                + "]";
    }

}
