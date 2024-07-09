/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.global;

import Forms.ULogin;
import Forms.UMain;
import Forms.USplash;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author jl54n
 */
public class UDM {

    static dbConnection db;
    static ULogin loginForm;
    static USplash splashScreen;
    static UMain mainForm;
    int contador = 0;
    Timer mTimer;

    public static void main(String[] args) {
        try {
            db = new dbConnection();
            loginForm = new ULogin();
            splashScreen = new USplash();
            mainForm = new UMain();
            db.connectDB();
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    loginForm.setVisible(true);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al iniciar el programa, saliendo... " + e.toString());
            System.exit(0);
        }
    }

    public void loginUser(String username, String password) {
        int loginResult = db.loginCheck(username, password);

        if (loginResult == 1) {
            loginForm.setVisible(false);
            splashScreen.setVisible(true);
            mTimer = new Timer(5000, e -> {
                splashScreen.dispose();
                mainForm.setVisible(true);
                mTimer.stop();
            });
            mTimer.start();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo loguear");

        }
    }
}
