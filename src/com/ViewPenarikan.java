package com;

import java.awt.event.*;
// import java.sql.*;
import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class ViewPenarikan extends JFrame implements ActionListener, ListenerPenarikan {
    ControllerPenarikan controllerPenarikan;
    ModelPenarikan modelPenarikan;

    JLabel labelJudul = new JLabel("Masukan nominal penarikan (minimum penarikan 1 gram)");
    JLabel labelKonversi = new JLabel("1 gram Emas = Rp845.907");
    JLabel labelNasabah = new JLabel("Nama Nasabah : <n/a>");
    JLabel labelSaldo = new JLabel("Saldo saat ini : <n/a>");
    JLabel labelTabungan = new JLabel("Nominal Penarikan : ");
    JLabel labelGram = new JLabel("gram");
    JTextField textTabungan = new JTextField(30);
    JButton buttonPenarikan = new JButton("Tarik Emas");
    JButton buttonCancel = new JButton("Batalkan");

    public ViewPenarikan(int iduser) {
        controllerPenarikan = new ControllerPenarikan();
        modelPenarikan = new ModelPenarikan(iduser);
        modelPenarikan.setPenarikanListener(this);
        controllerPenarikan.setMo(modelPenarikan);

        labelNasabah.setText("Nama Nasabah : "+modelPenarikan.getNama());
        labelSaldo.setText("Saldo saat ini : "+modelPenarikan.getEmas()+" gram");

        setTitle("Penarikan Emas");
        setSize(395, 300);
        setLayout(null);

        add(labelJudul);
        add(labelKonversi);
        add(labelNasabah);
        add(labelSaldo);
        add(labelTabungan);
        add(labelGram);
        add(textTabungan);
        add(buttonPenarikan);
        add(buttonCancel);

        labelJudul.setBounds(30, 30, 400, 20);
        labelKonversi.setBounds(115, 60, 400, 20);
        labelNasabah.setBounds(30, 100, 200, 20);
        labelSaldo.setBounds(30, 130, 200, 20);
        labelTabungan.setBounds(30, 160, 200, 20);
        textTabungan.setBounds(148, 160, 65, 20);
        labelGram.setBounds(218, 160, 100, 20);
        buttonPenarikan.setBounds(80, 205, 100, 20);
        buttonCancel.setBounds(200, 205, 100, 20);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttonPenarikan.addActionListener(this);
        buttonCancel.addActionListener(this);
    }

    public JTextField getTextTabungan() {
        return textTabungan;        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonPenarikan) {
            controllerPenarikan.submitPenarikan(this);
        }
        if (e.getSource() == buttonCancel) {
            controllerPenarikan.goToHomePage(this);
        }
    }

    @Override
    public void onChange(ModelPenarikan modelPenarikan) {
        textTabungan.setText(modelPenarikan.getTextTabungan());
    }
}    
