package com.solvd.bankapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    private static ConnectionPool connectionPool = null;
    private List<Connection> connectionsList = new ArrayList<>();
    public ConnectionPool() {
        IntStream.range(1, 5).forEach(i -> {
//            Connection connection = new Connection();
            connectionsList.add(createNewConnectionForPool());
        });
    }

    private Connection createNewConnectionForPool() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapp", "root", "password");
            System.out.println("Connection: " + connection);
        } catch (SQLException sqlException) {
            System.err.println("SQLException: " + sqlException);
            return null;
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("ClassNotFoundException: " + classNotFoundException);
            return null;
        }
        return connection;
    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        if (connectionsList.size() > 0) {
            connection = connectionsList.get(0);
            connectionsList.remove(connection);
        }
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        connectionsList.add(connection);
    }
}
