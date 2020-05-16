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
package io.github.faimoh.todowebapp.model;

import java.sql.Timestamp;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class Task {

    private Integer taskID;
    private Integer accountID;
    private String details;
    private Integer statusID;
    private Integer priorityID;
    private Timestamp createdAt;
    private Timestamp deadline;
    private Timestamp lastUpdated;

    public Task() {

    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Integer getTaskID() {
        return this.taskID;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public Integer getAccountID() {
        return this.accountID;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return this.details;
    }

    public void setStatusID(Integer statusID) {
        this.statusID = statusID;
    }

    public Integer getStatusID() {
        return this.statusID;
    }

    public void setPriorityID(Integer priorityID) {
        this.priorityID = priorityID;
    }

    public Integer getPriorityID() {
        return this.priorityID;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }
    
    public Timestamp getDeadline() {
        return this.deadline;
    }

    public Timestamp getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Task[id=" + this.taskID
                + ", username=" + this.accountID
                + ", details=" + this.details
                + ", createdAt=" + this.createdAt
                + ", deadline=" + this.deadline
                + ", lastUpdated=" + this.lastUpdated
                + ", status=" + this.statusID
                + ", priority=" + this.priorityID
                + "]";
    }
}
