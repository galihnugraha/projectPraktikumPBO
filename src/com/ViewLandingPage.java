package com;

import java.awt.event.*;
//import java.sql.*;
import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class ViewLandingPage extends JFrame implements ActionListener, ListenerLandingPage {
    ControllerLandingPage controllerLandingPage;
    ModelLandingPage modelLandingPage;

    JLabel labelJudul = new JLabel("Aplikasi Tabungan Emas");
    JButton buttonLogin = new JButton("Log In");
    JButton buttonDaftar = new JButton("Daftar Akun");

    public ViewLandingPage() {
        setTitle("Simpan Pinjam");
        setSize(320, 260);

        setLayout(null);
        add(labelJudul);
        add(buttonLogin);
        add(buttonDaftar);

        labelJudul.setBounds(90, 40, 200, 20);
        buttonLogin.setBounds(100, 100, 120, 20);
        buttonDaftar.setBounds(100, 130, 120, 20);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttonLogin.addActionListener(this);
        buttonDaftar.addActionListener(this);

        controllerLandingPage = new ControllerLandingPage();
        modelLandingPage = new ModelLandingPage();
        modelLandingPage.setLandingPageListener(this);
        controllerLandingPage.setMo(modelLandingPage);

    }
    
    @Override
    public void onChange(ModelLandingPage modelLandingPage) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonLogin) {
            controllerLandingPage.goToLogin(this);
        }
        if (e.getSource() == buttonDaftar) {
            controllerLandingPage.goToDaftar(this);
        }
    }

}
