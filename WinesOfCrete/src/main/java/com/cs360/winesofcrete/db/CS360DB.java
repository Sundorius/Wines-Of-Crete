package com.cs360.winesofcrete.db;

import java.sql.*;


/**
 *
 * @author manos katsifarakis <csd3195@csd.uoc.gr>
 */
public class CS360DB
{
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/cs360db?";
    private static final String UNAME = "root";
     private static final String PASS = "";

    /**
     * Attempts to establish a database connection Using jdbc
       *
     * @return a connection to the database
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.gjt.mm.mysql.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/cs360db?user=root&password=");
    }
}
