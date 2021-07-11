package com;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class DeleteUser extends JFrame implements ActionListener {
    int iduser;
    String username;
    String data[][] = new String[500][3];
    ThenConnector connector = new ThenConnector();

    JLabel labelJudul = new JLabel("Apakah anda ingin menghapus akun ini?");
    JLabel labelUsername = new JLabel("Email : ");
    JLabel textUsername = new JLabel("example@mail.com");
    JLabel labelNama = new JLabel("Nama : ");
    JLabel textNama = new JLabel("gal");
    JLabel labelTabungan = new JLabel("Saldo Emas : ");
    JLabel textTabungan = new JLabel("0.123 gram");

    JButton buttonYes = new JButton("Hapus");
    JButton buttonNo = new JButton("Batal");

    public DeleteUser(int iduser) {
        this.setIdUser(iduser);

        try {
            int jmlData = 0;
            String query = "SELECT * FROM `user` WHERE iduser = "+iduser;
            connector.statement = connector.connection.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            
            while (resultSet.next()) { 
                data[jmlData][0] = resultSet.getString("email"); 
                data[jmlData][1] = resultSet.getString("nama");
                data[jmlData][2] = resultSet.getString("tabungan");
                jmlData++; 
            }
            connector.statement.close();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }

        this.setUsername(data[0][0]);
        textUsername.setText(data[0][0]);
        textNama.setText(data[0][1]);
        textTabungan.setText(data[0][2]+" gram");

        setTitle("Hapus Akun");
        setSize(365, 225);
        setLayout(null);

        add(labelJudul);
        add(labelUsername);
        add(textUsername);
        add(labelNama);
        add(textNama);
        add(labelTabungan);
        add(textTabungan);
        add(buttonYes);
        add(buttonNo);

        labelJudul.setBounds(30, 20, 250, 20);
        labelUsername.setBounds(50, 48, 200, 20);
        textUsername.setBounds(130, 48, 200, 20);
        labelNama.setBounds(50, 71, 200, 20);
        textNama.setBounds(130, 71, 200, 20);
        labelTabungan.setBounds(50, 94, 200, 20);
        textTabungan.setBounds(130, 94, 200, 20);
        buttonYes.setBounds(145, 137, 80, 20);
        buttonNo.setBounds(235, 137, 80, 20);

        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buttonYes.addActionListener(this);
        buttonNo.addActionListener(this);
    }

    public void setIdUser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdUser() {
        return this.iduser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonYes) {
            try {
                String query = "DELETE FROM user WHERE iduser = "+this.getIdUser();
                
                connector.statement = connector.connection.createStatement();
                connector.statement.executeUpdate(query);
    
                System.out.println("\nHapus akun berhasil, iduser: "+getIdUser());
                JOptionPane.showMessageDialog(null, "Akun (email : "+this.getUsername()+") telah dihapus dari database");
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }

            new Admin();
            this.dispose();
        }
        if (e.getSource() == buttonNo) {
            new Admin();
            this.dispose();
        }
    }
}
