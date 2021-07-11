package com;

import java.awt.event.*;
// import java.sql.*;
import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class ViewEdit extends JFrame implements ActionListener, ListenerEdit {
    ControllerEdit controllerEdit;
    ModelEdit modelEdit;

    JLabel labelJudul = new JLabel("Edit Profil");
    JLabel labelUsername = new JLabel("Email : ");
    JLabel labelNama = new JLabel("Nama Lengkap : ");
    JLabel labelUmur = new JLabel("Umur : ");
    JLabel labelAlamat = new JLabel("Alamat : ");
    
    JLabel textUsername = new JLabel("<n/a>");
    JTextField textNama = new JTextField(30);
    JTextField textUmur = new JTextField(30);
    JTextArea textAlamat = new JTextArea();
    
    JButton buttonEdit = new JButton("Simpan Perubahan");
    JButton buttonReset = new JButton("Reset Form");
    JButton buttonKembali = new JButton("Kembali ke Panel Home");

    public ViewEdit(int iduser) {
        controllerEdit = new ControllerEdit();
        modelEdit = new ModelEdit(iduser);
        modelEdit.setEditListener(this);
        controllerEdit.setMo(modelEdit);

        textUsername.setText(modelEdit.getUsername());
        textNama.setText(modelEdit.getNama());
        textUmur.setText(modelEdit.getUmur());
        textAlamat.setText(modelEdit.getAlamat());

        setTitle("Edit Profile Panel");
        setSize(470, 420);

        setLayout(null);
        add(labelJudul);
        add(labelUsername);
        add(textUsername);
        add(labelNama);
        add(textNama);
        add(labelUmur);
        add(textUmur);
        add(labelAlamat);
        add(textAlamat);
        add(buttonEdit);
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
        
        buttonEdit.setBounds(85, 290, 145, 20);
        buttonReset.setBounds(240, 290, 145, 20);
        buttonKembali.setBounds(85, 320, 300, 20);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttonEdit.addActionListener(this);
        buttonReset.addActionListener(this);
        buttonKembali.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonEdit) {
            controllerEdit.submitEdit(this);
        }
        if (e.getSource() == buttonReset) {
            controllerEdit.resetForm(this);
        }
        if (e.getSource() == buttonKembali) {
            controllerEdit.goToLandingPage(this);
        }
        
    }

    @Override
    public void onChange(ModelEdit modelEdit) {
        textNama.setText(modelEdit.getNama());
        textUmur.setText(modelEdit.getUmur());        
        textAlamat.setText(modelEdit.getAlamat());      
    }
}
