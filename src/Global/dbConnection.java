/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Global;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
            String url = "jdbc:sqlite:SistemaAlumnos.db";
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
}
