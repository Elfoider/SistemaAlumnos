/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Global;

import Forms.ULogin;
import javax.swing.JOptionPane;

/**
 *
 * @author jl54n
 */
public class UDM {
    
    static dbConnection db;
    
    public static void main(String[] args) {
        db = new dbConnection();
        try {
            db.connectDB();
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new ULogin().setVisible(true);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al iniciar el programa, saliendo...");
            System.exit(0);
        }
    }
}
