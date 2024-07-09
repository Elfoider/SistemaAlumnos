/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author jl54n
 */
public class dbConnection {

    private static Connection connect;

    public void connectDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:DB/SistemaAlumnos.db";
            connect = DriverManager.getConnection(url);
            if (connect != null) {
                System.out.print("Conexion exitosa");
            }
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
            JOptionPane.showMessageDialog(null, "No se ha podido conectar a la base de datos\n" + ex.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("El JAR no está correctamente agregado\n"
                    + e.getMessage());
            JOptionPane.showMessageDialog(null, "No se ha agregado la libreria correspondiente");
            System.exit(0);
        }
    }

    public int loginCheck(String username, String password) {
        int loginUser = 0;
        try {
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                String dbusername = rs.getString("username"), dbpassword = rs.getString("password");
                if (username.equals(dbusername) && password.equals(dbpassword)) {
                    System.out.println("logueado");
                    loginUser = 1;
                    break;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            loginUser = 0;
        }
        return loginUser;
    }
}
