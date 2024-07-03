/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Global;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author jl54n
 */
public class dbConnection {

    private static Connection connect;
    private final String swFolder = System.getProperty("user.dir");

    public dbConnection() {
        //
    }

    public void connectDB() {
        try {
            String url = "jdbc:sqlite:" + swFolder + "/DB/SistemaAlumnos.db";
            DriverManager.getConnection(url);
        } catch (Exception e) {
        }
    }
}
