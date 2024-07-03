/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Global;

import java.sql.*;
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
            DriverManager.getConnection(url);
            if (connect != null) {
                System.out.println("Conexion exitosa");
            }
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n"
                    + ex.getMessage());

            JOptionPane.showMessageDialog(null, "No se ha podido conectar a la base de datos\n"
                    + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {

            System.err.println("El Driver no está correctamente agregado\n"
                    + e.getMessage());
            JOptionPane.showMessageDialog(null, "El Driver no está correctamente agregado\n"
                    + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            System.exit(0);
        }
    }

    public void loginCheck(String username, String password) {
        try {
            Statement stmt = connect.createStatement();
            Boolean loginUser = Boolean.FALSE;
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                int id = rs.getInt("id");
                String dbusername = rs.getString("username"), dbpassword = rs.getString("password");
                if (username.equals(dbusername) && password.equals(dbpassword)) {
                    System.out.println("logueado");
                    loginUser = Boolean.TRUE;
                    break;
                }
            }
            if (loginUser) {
                System.out.println("Login");
                JOptionPane.showMessageDialog(null, "Usuario Logueado");
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
