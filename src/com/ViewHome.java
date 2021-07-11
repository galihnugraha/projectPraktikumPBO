package com;

import java.awt.event.*;
// import java.sql.*;
import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class ViewHome extends JFrame implements ActionListener, ListenerHome {
    ControllerHome controllerHome;
    ModelHome modelHome;

    JLabel labelJudul = new JLabel("Aplikasi Tabungan Emas");
    JLabel labelProfil = new JLabel("Profil Nasabah : ");
    JLabel labelEmail = new JLabel("Email : ");
    JLabel textEmail = new JLabel("<n/a>");
    JLabel labelNama = new JLabel("Nama : ");
    JLabel textNama = new JLabel("<n/a>");
    JLabel labelUmur = new JLabel("Umur : ");
    JLabel textUmur = new JLabel("<n/a>");
    JLabel labelAlamat = new JLabel("Alamat : ");
    JLabel textAlamat = new JLabel("<n/a>");
    JLabel labelTabungan = new JLabel("Saldo Tabungan Emas : ");
    JLabel textTabungan = new JLabel("<n/a>");

    JButton buttonTabung = new JButton("Menabung Emas");
    JButton buttonPenarikan = new JButton("Tarik Emas");
    JButton buttonRiwayat = new JButton("Riwayat Transaksi");
    JButton buttonEdit = new JButton("Edit Profil");
    JButton buttonLogout = new JButton("Logout");
    
    public ViewHome(int iduser) {
        controllerHome = new ControllerHome();
        modelHome = new ModelHome(iduser);
        modelHome.setHomeListener(this);
        controllerHome.setMo(modelHome);

        textEmail.setText(modelHome.getUsername());
        textNama.setText(modelHome.getNama());
        textUmur.setText(modelHome.getUmur()+" tahun");
        textAlamat.setText(modelHome.getAlamat());
        textTabungan.setText(modelHome.getTabungan()+" gram");

        setTitle("Home Panel");
        setSize(430, 480);

        setLayout(null);
        add(labelJudul);
        add(labelProfil);
        add(labelEmail);
        add(textEmail);
        add(labelNama);
        add(textNama);
        add(labelUmur);
        add(textUmur);
        add(labelAlamat);
        add(textAlamat);
        add(labelTabungan);
        add(textTabungan);
        add(buttonTabung);
        add(buttonPenarikan);
        add(buttonRiwayat);
        add(buttonEdit);
        add(buttonLogout);

        labelJudul.setBounds(145, 25, 200, 20);
        labelProfil.setBounds(40, 70, 200, 20);       
        labelEmail.setBounds(40, 100, 200, 20);       
        textEmail.setBounds(100, 100, 200, 20);       
        labelNama.setBounds(40, 130, 200, 20);       
        textNama.setBounds(100, 130, 200, 20);       
        labelUmur.setBounds(40, 160, 200, 20);       
        textUmur.setBounds(100, 160, 200, 20);       
        labelAlamat.setBounds(40, 190, 200, 20);       
        textAlamat.setBounds(100, 190, 200, 20);       
        labelTabungan.setBounds(115, 260, 200, 20);       
        textTabungan.setBounds(255, 260, 200, 20);       
        buttonTabung.setBounds(120, 290, 190, 20);       
        buttonPenarikan.setBounds(120, 320, 190, 20);       
        buttonRiwayat.setBounds(120, 350, 190, 20);       
        buttonEdit.setBounds(120, 380, 90, 20);       
        buttonLogout.setBounds(220, 380, 90, 20);       

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttonTabung.addActionListener(this);
        buttonPenarikan.addActionListener(this);
        buttonRiwayat.addActionListener(this);
        buttonEdit.addActionListener(this);
        buttonLogout.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonTabung) {
            controllerHome.goToMenabung(this);
        }
        if (e.getSource() == buttonPenarikan) {
            controllerHome.goToPenarikan(this);
        }
        if (e.getSource() == buttonRiwayat) {
            controllerHome.goToRiwayat(this);
        }
        if (e.getSource() == buttonEdit) {
            controllerHome.goToEdit(this);
        }
        if (e.getSource() == buttonLogout) {
            controllerHome.logout(this);
        }
    }

    @Override
    public void onChange(ModelHome modelHome) {
        // TODO Auto-generated method stub
        
    }
}
