package com.global;

import com.raven.form.Form_1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            System.err.println("El JAR no está correctamente agregado\n" + e.getMessage());
            JOptionPane.showMessageDialog(null, "No se ha agregado la libreria correspondiente");
            System.exit(0);
        }
    }

    public Object[] getAlumnos() {
        Statement stmt;
        int index = 0;
        Object[] objRows = new Object[50];
        try {
            stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Alumnos");
            while (rs.next()) {
                objRows[index] = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
                index++;
            }
            return objRows;
        } catch (SQLException ex) {
            Logger.getLogger(dbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
                    int role = rs.getInt("role"); // Retrieve the 'rol' attribute

                    if (username.equals(dbusername) && password.equals(dbpassword)) {
                        System.out.println("Logueado");
                        if (role == 0) {
                            loginUser = 1; // Administrator login
                        } else if (role == 1) {
                            loginUser = 1; // Student login
                        } else {
                            // Handle unexpected role values (optional)
                            JOptionPane.showMessageDialog(null, "Rol no reconocido en la base de datos");
                            loginUser = 2; // Indicate invalid login
                        }
                        break;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                loginUser = 0;
            }
        } else {
            loginUser = 2; // Invalid email format
        }
        return loginUser;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w\\.-]+@([\\w\\.-]+\\.)+[\\w\\.-]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
