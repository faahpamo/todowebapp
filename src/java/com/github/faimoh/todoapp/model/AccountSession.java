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
public class AccountSession {

    private String sessionID;
    private int accountID;
    private Timestamp sessionCreated;
    private Timestamp lastAccessed;
    private Timestamp sessionEnd;

    public AccountSession() {

    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public Integer getAccountID() {
        return this.accountID;
    }

    public void setSessionCreated(Timestamp sessionCreated) {
        this.sessionCreated = sessionCreated;
    }

    public Timestamp getSessionCreated() {
        return this.sessionCreated;
    }

    public void setLastAccessed(Timestamp lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public Timestamp getLastAccessed() {
        return this.lastAccessed;
    }

    public void setSessionEnd(Timestamp sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public Timestamp getSessionEnd() {
        return this.sessionEnd;
    }

    @Override
    public String toString() {
        return "AccountSession[" 
                + this.sessionID + ", "
                + this.accountID + ", "
                + this.sessionCreated + ", "
                + this.lastAccessed + ", "
                + this.sessionEnd
                + "]";
    }
}
