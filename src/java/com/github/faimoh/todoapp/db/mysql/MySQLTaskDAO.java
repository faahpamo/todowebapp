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
package com.github.faimoh.todoapp.db.mysql;

import com.github.faimoh.todoapp.model.Account;
import com.github.faimoh.todoapp.model.Task;
import com.github.faimoh.todoapp.db.DAOFactory;
import com.github.faimoh.todoapp.db.TaskDAO;
import com.github.faimoh.todoapp.db.mysql.MySQLDataSourceDAOFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class MySQLTaskDAO implements TaskDAO {

    public ArrayList<Task> getAllTasks(Account account) {
        MySQLDataSourceDAOFactory mysqlDSDAOFactory = (MySQLDataSourceDAOFactory) DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
        Connection connection = mysqlDSDAOFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        int accountID = account.getAccountID();

        String query = "SELECT * FROM tasks WHERE account_id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountID);
            rs = ps.executeQuery();
            ArrayList<Task> tasksList = new ArrayList<Task>();
            Task task = null;
            while (rs.next()) {
                task = new Task();
                task.setTaskID(rs.getInt("task_id"));
                task.setAccountID(rs.getInt("account_id"));
                task.setDetails(rs.getString("details"));
                task.setStatusID(rs.getInt("status_id"));
                task.setPriorityID(rs.getInt("priority_id"));
                task.setCreatedAt(rs.getTimestamp("created_at"));
                task.setDeadline(rs.getTimestamp("deadline"));
                task.setLastUpdated(rs.getTimestamp("last_updated"));
                tasksList.add(task);
            }
            return tasksList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            mysqlDSDAOFactory.freeConnection(connection);
        }
    }

    public Task findTask(int id) {
        MySQLDataSourceDAOFactory mysqlDSDAOFactory = (MySQLDataSourceDAOFactory) DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
        Connection connection = mysqlDSDAOFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM tasks WHERE task_id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Task task = null;
            while (rs.next()) {
                task = new Task();
                task.setTaskID(rs.getInt("task_id"));
                task.setAccountID(rs.getInt("account_id"));
                task.setDetails(rs.getString("details"));
                task.setStatusID(rs.getInt("status_id"));
                task.setPriorityID(rs.getInt("priority_id"));
                task.setCreatedAt(rs.getTimestamp("created_at"));
                task.setDeadline(rs.getTimestamp("deadline"));
                task.setLastUpdated(rs.getTimestamp("last_updated"));
            }
            return task;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            mysqlDSDAOFactory.freeConnection(connection);
        }
    }

    public Boolean insertTask(Task task) {
        MySQLDataSourceDAOFactory mysqlDSDAOFactory = (MySQLDataSourceDAOFactory) DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
        Connection connection = mysqlDSDAOFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "INSERT INTO TASKS (account_id, details, deadline, priority_id) VALUES (?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, task.getAccountID());
            ps.setString(2, task.getDetails());
            ps.setTimestamp(3, task.getDeadline());
            ps.setInt(4, task.getPriorityID());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mysqlDSDAOFactory.freeConnection(connection);
        }
    }

    public Boolean updateTask(Task task) {
        MySQLDataSourceDAOFactory mysqlDSDAOFactory = (MySQLDataSourceDAOFactory) DAOFactory.getDAOFactory(DAOFactory.MySQLDataSource);
        Connection connection = mysqlDSDAOFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE TASKS SET last_updated=?, details=?, status_id=?, deadline=?, priority_id=? WHERE task_id=?";

        try {
            if (task == null) {
                return false;
            } else {
                ps = connection.prepareStatement(query);
                ps.setInt(6, task.getTaskID());                
                ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                ps.setString(2, task.getDetails());
                ps.setInt(3, task.getStatusID());
                ps.setTimestamp(4, task.getDeadline());
                ps.setInt(5, task.getPriorityID());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            mysqlDSDAOFactory.freeConnection(connection);
        }
    }
}
