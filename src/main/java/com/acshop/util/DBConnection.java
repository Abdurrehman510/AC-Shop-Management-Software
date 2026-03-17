package com.acshop.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static Connection connection = null;

    private DBConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties props = new Properties();
                InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                if (in != null) {
                    props.load(in);
                    Class.forName(props.getProperty("db.driver"));
                    connection = DriverManager.getConnection(
                            props.getProperty("db.url"),
                            props.getProperty("db.user"),
                            props.getProperty("db.password")
                    );
                } else {
                    System.err.println("db.properties not found!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
