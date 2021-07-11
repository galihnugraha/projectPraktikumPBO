package com;

import java.awt.event.*;
// import java.sql.*;
import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class ViewDaftar extends JFrame implements ActionListener, ListenerDaftar {
    ControllerDaftar controllerDaftar;
    ModelDaftar modelDaftar;

    JLabel labelJudul = new JLabel("Daftar Akun");
    JLabel labelUsername = new JLabel("Email : ");
    JLabel labelNama = new JLabel("Nama Lengkap : ");
    JLabel labelUmur = new JLabel("Umur : ");
    JLabel labelAlamat = new JLabel("Alamat : ");
    JLabel labelPassword = new JLabel("Password : ");
    JLabel labelRePassword = new JLabel("Re-enter Password : ");
    
    JTextField textUsername = new JTextField(30);
    JTextField textNama = new JTextField(30);
    JTextField textUmur = new JTextField(30);
    JTextArea textAlamat = new JTextArea();
    JPasswordField textPassword = new JPasswordField(30);
    JPasswordField textRePassword = new JPasswordField(30);
    
    JButton buttonDaftar = new JButton("Daftar Akun");
    JButton buttonReset = new JButton("Reset Form");
    JButton buttonKembali = new JButton("Kembali ke Panel Utama");

    public ViewDaftar() {
        controllerDaftar = new ControllerDaftar();
        modelDaftar = new ModelDaftar();
        modelDaftar.setDaftarListener(this);
        controllerDaftar.setMo(modelDaftar);

        setTitle("Sign Up Panel");
        setSize(470, 510);

        setLayout(null);
        add(labelJudul);
        add(textUsername);
        add(labelUsername);
        add(textNama);
        add(labelNama);
        add(labelUmur);
        add(textUmur);
        add(labelAlamat);
        add(textAlamat);
        add(textPassword);
        add(labelPassword);
        add(textRePassword);
        add(labelRePassword);
        add(buttonDaftar);
        add(buttonReset);
        add(buttonKembali);

        labelJudul.setBounds(205, 40, 200, 20);
        labelUsername.setBounds(60, 90, 200, 20);
        textUsername.setBounds(190, 90, 200, 20);
        labelNama.setBounds(60, 120, 200, 20);
        textNama.setBounds(190, 120, 200, 20);
        labelUmur.setBounds(60, 150, 200, 20);
        textUmur.setBounds(190, 150, 200, 20);
        labelAlamat.setBounds(60, 180, 200, 20);
        textAlamat.setBounds(190, 180, 200, 70);
        labelPassword.setBounds(60, 260, 200, 20);
        textPassword.setBounds(190, 260, 200, 20);
        labelRePassword.setBounds(60, 290, 200, 20);
        textRePassword.setBounds(190, 290, 200, 20);
        
        buttonDaftar.setBounds(110, 350, 120, 20);
        buttonReset.setBounds(240, 350, 120, 20);
        buttonKembali.setBounds(110, 380, 250, 20);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttonDaftar.addActionListener(this);
        buttonReset.addActionListener(this);
        buttonKembali.addActionListener(this);
    }

    public JTextField getTextUsername() {
        return textUsername;   
    }

    public JTextField getTextNama() {
        return textNama;        
    }

    public JTextField getTextUmur() {
        return textUmur;       
    }

    public JTextArea getTextAlamat() {
        return textAlamat;        
    }

    public JPasswordField getTextPassword() {
        return textPassword;       
    }

    public JPasswordField getTextRePassword() {
        return textRePassword;        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonDaftar) {
            controllerDaftar.submitDaftar(this);
        }
        if (e.getSource() == buttonReset) {
            controllerDaftar.resetForm(this);
        }
        if (e.getSource() == buttonKembali) {
            controllerDaftar.goToLandingPage(this);
        }
        
    }

    @Override
    public void onChange(ModelDaftar modelDaftar) {
        textUsername.setText(modelDaftar.getUsername());
        textNama.setText(modelDaftar.getNama());
        textUmur.setText(modelDaftar.getUmur());        
        textAlamat.setText(modelDaftar.getAlamat());        
        textPassword.setText(modelDaftar.getPassword());        
        textRePassword.setText(modelDaftar.getRePassword());        
    }
}
