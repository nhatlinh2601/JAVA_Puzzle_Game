package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHelper {

    public static Connection getConnection() throws Exception {

        String url = "jdbc:mysql://localhost:3306/ta_group6_puzzle";
        var user = "root";
        var password = "";
        Connection con = DriverManager.getConnection(url, user, password);

        return con;
    }
}
