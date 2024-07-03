/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Global;

import Forms.ULogin;

/**
 *
 * @author jl54n
 */
public class UDM {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ULogin().setVisible(true);
            }
        });
    }
}
