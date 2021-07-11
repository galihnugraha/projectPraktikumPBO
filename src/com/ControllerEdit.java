package com;

// import java.awt.event.*;
// import java.sql.*;
// import java.util.regex.*;
import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class ControllerEdit {
    private ModelEdit mo;
    ThenConnector connector = new ThenConnector();

    public void setMo(ModelEdit mo) {
        this.mo = mo;
    }

    public void resetForm(ViewEdit viewEdit) {
        mo.resetForm();
    }

    public void submitEdit(ViewEdit viewEdit) {
        boolean cekUmur = true;
        int umurAngka = 0;

        String nama = viewEdit.getTextNama().getText();
        String umur = viewEdit.getTextUmur().getText();
        String alamat = viewEdit.getTextAlamat().getText();

        mo.setNama(nama);
        mo.setUmur(umur);
        mo.setAlamat(alamat);

        if (nama.trim().equals("")) {
            JOptionPane.showMessageDialog(viewEdit, "Kolom Nama Masih Kosong!");
        } else if (umur.trim().equals("")) {
            JOptionPane.showMessageDialog(viewEdit, "Kolom Umur Masih Kosong!");
        } else if (alamat.trim().equals("")) {
            JOptionPane.showMessageDialog(viewEdit, "Kolom Alamat Masih Kosong!");
        } else {
            
            try {
                umurAngka = Integer.parseInt(umur);
                mo.setUmurAngka(umurAngka);
            } catch (NumberFormatException error) {
                cekUmur = false;
                JOptionPane.showMessageDialog(viewEdit, "Kolom Umur Harus merupakan bilangan bulat!");
                mo.setUmur("");
            } catch (Exception error) {
                cekUmur = false;
                JOptionPane.showMessageDialog(viewEdit, error.getMessage());
                mo.setUmur("");
            }
            
            if (cekUmur) {

                if (umurAngka < 17) {
                    JOptionPane.showMessageDialog(viewEdit, "Anda belum cukup usia untuk melakukan transaksi!");
                    new ViewHome(mo.getIdUser());
                    viewEdit.dispose();
                } else {
                    mo.submitEdit(viewEdit);
                    new ViewHome(mo.getIdUser());
                    viewEdit.dispose();
                } 
            } 
        }
    }

    public void goToLandingPage(ViewEdit viewEdit) {
        new ViewHome(mo.getIdUser());
        viewEdit.dispose();
    }
}
