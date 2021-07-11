package com;

import javax.swing.*;

public class ControllerLogin {
    private ModelLogin mo;
    ThenConnector connector = new ThenConnector();

    public void setMo(ModelLogin mo) {
        this.mo = mo;
    }

    public void resetForm(ViewLogin viewLogin) {
        mo.resetForm();
    }

    public void submitLogin(ViewLogin viewLogin) {
        String username = viewLogin.getTextUsername().getText();
        String password = viewLogin.getTextPassword().getText();

        mo.setUsername(username);
        mo.setPassword(password);

        if (username.trim().equals("")) {
            JOptionPane.showMessageDialog(viewLogin, "Kolom Email Masih Kosong!");
        } else if (password.trim().equals("")) {
            JOptionPane.showMessageDialog(viewLogin, "Password Masih Kosong!");
        } else {
            int lanjut = mo.submitLogin(viewLogin);
            
            if (lanjut == 0) {
                JOptionPane.showMessageDialog(viewLogin, "Email Tidak Ditemukan!");
                this.resetForm(viewLogin);
            } else {
                if (mo.getUsernameData().equals("admin")) {
                    if ( password.trim().equals(mo.getPasswordData()) ) {
                        JOptionPane.showMessageDialog(viewLogin, "Login Admin Berhasil");
                        System.out.println("\nLogin Admin Berhasil");
                        new Admin();
                        viewLogin.dispose();
                    } else {
                        JOptionPane.showMessageDialog(viewLogin, "Password Admin Salah!");
                        this.resetForm(viewLogin);
                    }
                } else {
                    if ( password.trim().equals(mo.getPasswordData()) ) {
                        JOptionPane.showMessageDialog(viewLogin, "Login Berhasil");
                        System.out.println("\nLogin Berhasil, Id Login : " + lanjut);
                        new ViewHome(lanjut);
                        viewLogin.dispose();
                    } else {
                        JOptionPane.showMessageDialog(viewLogin, "Email : "+username+", Password Salah!");
                        this.resetForm(viewLogin);
                    }
                }
            }
        }
    }

    public void goToLandingPage(ViewLogin viewLogin) {
        new ViewLandingPage();
        viewLogin.dispose();
    }
}
