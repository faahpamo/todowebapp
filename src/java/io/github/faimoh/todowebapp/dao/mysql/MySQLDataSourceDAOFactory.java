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
package io.github.faimoh.todowebapp.dao.mysql;

import io.github.faimoh.todowebapp.dao.TaskDAO;
import io.github.faimoh.todowebapp.dao.AccountDAO;
import io.github.faimoh.todowebapp.dao.DAOFactory;
import io.github.faimoh.todowebapp.dao.AccountSessionDAO;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Faisal Ahmed Pasha Mohammed https://github.com/faimoh
 */
public class MySQLDataSourceDAOFactory extends DAOFactory {
    private static DataSource dataSource = null;
    private static MySQLDataSourceDAOFactory mySQLDataSourceDAOFactory = null;
    
    private MySQLDataSourceDAOFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/todo");            
        }catch(NamingException e) {
            e.printStackTrace();
        }
    }
    
    public static MySQLDataSourceDAOFactory getInstance() {
        if (mySQLDataSourceDAOFactory == null) {
            mySQLDataSourceDAOFactory = new MySQLDataSourceDAOFactory(); 
        }
        return mySQLDataSourceDAOFactory;
    }
    
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void freeConnection(Connection c) {
        try {
            c.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public AccountDAO getAccountDAO() {
        return new MySQLDataSourceAccountDAO();
    }
    
    public TaskDAO getTaskDAO() {
        return new MySQLDataSourceTaskDAO();
    }
    
    public AccountSessionDAO getAccountSessionDAO() {
        return new MySQLDataSourceAccountSessionDAO();
    }
}
