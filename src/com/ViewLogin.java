package com;

import java.awt.event.*;
// import java.sql.*;
import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class ViewLogin extends JFrame implements ActionListener, ListenerLogin {
    ControllerLogin controllerLogin;
    ModelLogin modelLogin;

    JLabel labelJudul = new JLabel("Log In Panel");
    JLabel labelUsername = new JLabel("Email : ");
    JLabel labelPassword = new JLabel("Password : ");
    JTextField textUsername = new JTextField(30);
    JPasswordField textPassword = new JPasswordField(30);
    JButton buttonLogin = new JButton("Log In");
    JButton buttonReset = new JButton("Reset");
    JButton buttonKembali = new JButton("Kembali ke Panel Utama");

    public ViewLogin() {
        setTitle("Login Panel");
        setSize(430, 280);

        setLayout(null);
        add(labelJudul);
        add(textUsername);
        add(labelUsername);
        add(textPassword);
        add(labelPassword);
        add(buttonLogin);
        add(buttonReset);
        add(buttonKembali);

        labelJudul.setBounds(170, 40, 200, 20);
        labelUsername.setBounds(60, 90, 200, 20);
        textUsername.setBounds(140, 90, 200, 20);
        labelPassword.setBounds(60, 120, 200, 20);
        textPassword.setBounds(140, 120, 200, 20);
        buttonLogin.setBounds(115, 160, 90, 20);
        buttonReset.setBounds(215, 160, 90, 20);
        buttonKembali.setBounds(115, 190, 190, 20);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttonLogin.addActionListener(this);
        buttonReset.addActionListener(this);
        buttonKembali.addActionListener(this);

        controllerLogin = new ControllerLogin();
        modelLogin = new ModelLogin();
        modelLogin.setLoginListener(this);
        controllerLogin.setMo(modelLogin);
    }

    public JTextField getTextUsername() {
        return textUsername;
    }

    public JTextField getTextPassword() {
        return textPassword;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonLogin) {
            controllerLogin.submitLogin(this);
        }
        if (e.getSource() == buttonReset) {
            controllerLogin.resetForm(this);
        }
        if (e.getSource() == buttonKembali) {
            controllerLogin.goToLandingPage(this);
        }
    }

    @Override
    public void onChange(ModelLogin modelLogin) {
        textUsername.setText(modelLogin.getUsername());
        textPassword.setText(modelLogin.getPassword());
    }
}
