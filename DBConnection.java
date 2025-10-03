package com.placement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // JDBC URL, user, and password for MySQL
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/placement?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Noufia@786"; // Change this to your MySQL password

    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found.");
        }
    }
}