package com.mysoft.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Class for making connection to DB
public class DBConnection {

    private final String HOST     = "jdbc:mysql://localhost:3306/company_manager";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    private Connection connection;

    public DBConnection() {
        try {
            connection = DriverManager.getConnection( HOST, USERNAME, PASSWORD );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
