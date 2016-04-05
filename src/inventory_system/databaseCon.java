/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system;

import javax.swing.*;
import java.sql.*;
/**
 *
 * @author imran
 */
public class databaseCon {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    //private static final String JAVA_DRIVER = "com.mysql.jdbc.Driver";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/myinventory";
    private static Connection con = null;

    public static Connection getConn() {
        try {
            //Class.forName(JAVA_DRIVER);
            con = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            //JOptionPane.showMessageDialog(null, "OK");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return con;

    }

}
