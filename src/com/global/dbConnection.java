/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.global;

import com.raven.form.Form_1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
        boolean isValid = isValidEmail(username);
        if (isValid) {
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
        } else {
            loginUser = 2;
        }
        return loginUser;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w\\.-]+@([\\w\\.-]+\\.)+[\\w\\.-]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public void mostrar(String tabla) throws SQLException {
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM alumnos");
        System.out.println(rs);
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("ID");
         model.addColumn("Apellidos");
          model.addColumn("ID");
        
    }
        
    
    }
    
    
    
    
    
    
    



