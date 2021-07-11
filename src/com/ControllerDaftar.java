package com;

// import java.awt.event.*;
// import java.sql.*;
import java.util.regex.*;
import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class ControllerDaftar {
    private ModelDaftar mo;
    ThenConnector connector = new ThenConnector();

    public void setMo(ModelDaftar mo) {
        this.mo = mo;
    }

    public void resetForm(ViewDaftar viewDaftar) {
        mo.resetForm();
    }

    public void submitDaftar(ViewDaftar viewDaftar) {
        boolean cekUmur = true, validationEmail = true;
        int umurAngka = 0;

        String username = viewDaftar.getTextUsername().getText();
        String nama = viewDaftar.getTextNama().getText();
        String umur = viewDaftar.getTextUmur().getText();
        String alamat = viewDaftar.getTextAlamat().getText();
        String password = new String(viewDaftar.getTextPassword().getPassword());
        String rePassword = new String(viewDaftar.getTextRePassword().getPassword());

        mo.setUsername(username);
        mo.setNama(nama);
        mo.setUmur(umur);
        mo.setAlamat(alamat);
        mo.setPassword(password);
        mo.setRePassword(rePassword);

        if (username.trim().equals("")) {
            JOptionPane.showMessageDialog(viewDaftar, "Kolom Email Masih Kosong!");
        } else if (nama.trim().equals("")) {
            JOptionPane.showMessageDialog(viewDaftar, "Kolom Nama Masih Kosong!");
        } else if (umur.trim().equals("")) {
            JOptionPane.showMessageDialog(viewDaftar, "Kolom Umur Masih Kosong!");
        } else if (alamat.trim().equals("")) {
            JOptionPane.showMessageDialog(viewDaftar, "Kolom Alamat Masih Kosong!");
        } else if (password.trim().equals("")) {
            JOptionPane.showMessageDialog(viewDaftar, "Password Masih Kosong!");
        } else if (rePassword.trim().equals("")) {
            JOptionPane.showMessageDialog(viewDaftar, "Konfirmasi Password Masih Kosong!");
        } else {
            
            try {
                umurAngka = Integer.parseInt(umur);
                mo.setUmurAngka(umurAngka);
            } catch (NumberFormatException error) {
                cekUmur = false;
                JOptionPane.showMessageDialog(viewDaftar, "Kolom Umur Harus merupakan bilangan bulat!");
                mo.setUmur("");
            } catch (Exception error) {
                cekUmur = false;
                JOptionPane.showMessageDialog(viewDaftar, error.getMessage());
                mo.setUmur("");
            }
            
            if (cekUmur) {
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(username);
                if (!matcher.matches()) {
                    validationEmail = false;
                    JOptionPane.showMessageDialog(viewDaftar, "Email tidak valid!");
                    mo.setUsername("");
                }

                if (validationEmail) {
                    if (password.equals(rePassword)) {

                        if (umurAngka < 17) {
                            JOptionPane.showMessageDialog(viewDaftar, "Anda belum cukup usia untuk melakukan transaksi!");
                            this.goToLandingPage(viewDaftar);
                            viewDaftar.dispose();
                        } else {
                            int cekEmail = mo.cekEmail(viewDaftar);

                            if (cekEmail == 1) {
                                mo.submitDaftar(viewDaftar);
                                new ViewLogin();
                                viewDaftar.dispose();
                            } else if (cekEmail == 0) {
                                JOptionPane.showMessageDialog(viewDaftar, "Email sudah digunakan, gunakan email yang lain!");
                                mo.setUsername("");
                                mo.setPassword("");
                                mo.setRePassword("");
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(viewDaftar, "Password tidak sesuai dengan konfirmasi Password");
                        mo.setRePassword("");
                    }
                }
            } 
        }
    }

    public void goToLandingPage(ViewDaftar viewDaftar) {
        new ViewLandingPage();
        viewDaftar.dispose();
    }
}
