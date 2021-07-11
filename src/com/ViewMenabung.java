package com;

import java.awt.event.*;
// import java.sql.*;
import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class ViewMenabung extends JFrame implements ActionListener, ListenerMenabung {
    ControllerMenabung controllerMenabung;
    ModelMenabung modelMenabung;

    JLabel labelJudul = new JLabel("Masukan nominal deposit (minimum deposit Rp100.000)");
    JLabel labelKonversi = new JLabel("1 gram Emas = Rp845.907");
    JLabel labelNasabah = new JLabel("Nama Nasabah : <n/a>");
    JLabel labelSaldo = new JLabel("Saldo saat ini : <n/a>");
    JLabel labelTabungan = new JLabel("Nominal Deposit Rp.");
    JTextField textTabungan = new JTextField(30);
    JButton buttonDeposit = new JButton("Deposit");
    JButton buttonCancel = new JButton("Batalkan");

    public ViewMenabung(int iduser) {
        controllerMenabung = new ControllerMenabung();
        modelMenabung = new ModelMenabung(iduser);
        modelMenabung.setMenabungListener(this);
        controllerMenabung.setMo(modelMenabung);

        labelNasabah.setText("Nama Nasabah : "+modelMenabung.getNama());
        labelSaldo.setText("Saldo saat ini : "+modelMenabung.getEmas()+" gram");

        setTitle("Deposit Tabungan");
        setSize(395, 300);
        setLayout(null);

        add(labelJudul);
        add(labelKonversi);
        add(labelNasabah);
        add(labelSaldo);
        add(labelTabungan);
        add(textTabungan);
        add(buttonDeposit);
        add(buttonCancel);

        labelJudul.setBounds(30, 30, 400, 20);
        labelKonversi.setBounds(115, 60, 400, 20);
        labelNasabah.setBounds(30, 100, 200, 20);
        labelSaldo.setBounds(30, 130, 200, 20);
        labelTabungan.setBounds(30, 160, 200, 20);
        textTabungan.setBounds(148, 160, 170, 20);
        buttonDeposit.setBounds(80, 205, 100, 20);
        buttonCancel.setBounds(200, 205, 100, 20);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttonDeposit.addActionListener(this);
        buttonCancel.addActionListener(this);
    }

    public JTextField getTextTabungan() {
        return textTabungan;        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonDeposit) {
            controllerMenabung.submitDeposit(this);
        }
        if (e.getSource() == buttonCancel) {
            controllerMenabung.goToHomePage(this);
        }
    }

    @Override
    public void onChange(ModelMenabung modelMenabung) {
        textTabungan.setText(modelMenabung.getTextTabungan());
    }
}    
