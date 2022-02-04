/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aluno
 */
public class Conn {

    private static Connection connection = null;
    private final static String ADRESS = "jdbc:mysql://localhost";
    private final static String DATABASE = "cemdesigner";
    private final static String USER = "root";
    private final static String PASSWORD = "josuemorais";
    private final static String PORT = "3306";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(ADRESS + ":" + PORT + "/" + DATABASE, USER, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            throw (SQLException) ex;
        }
    }
}
