package com;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
// import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class RiwayatTransaksi extends JFrame implements ActionListener {

    int idUser;
    String nama;
    double tabungan;
    String dataUser[][] = new String[500][2];
    String dataTransaksi[][] = new String[500][5];

    ThenConnector connector = new ThenConnector();

    JLabel labelNama = new JLabel("Nama Nasabah : <n/a>");
    JLabel labelTabungan = new JLabel("Tabungan Emas : <n/a>");
    JTable tabel;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"Alamat Nasabah", "Waktu (WIB)","Jenis Transaksi", "Nominal (gram)", "Total Tabungan (gram)"};
    
    JButton buttonKembali = new JButton("Kembali");

    public RiwayatTransaksi(int iduser) {
        this.idUser = iduser;

        try {
            int jmlData = 0;
            String query = "SELECT * FROM `user` WHERE iduser = "+iduser;
            connector.statement = connector.connection.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);

            while(resultSet.next()) {
                dataUser[jmlData][0] = resultSet.getString("nama");
                dataUser[jmlData][1] = resultSet.getString("tabungan");
                jmlData++; 
            }
            connector.statement.close();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }

        this.nama = dataUser[0][0];
        this.tabungan = Double.parseDouble(dataUser[0][1]);

        labelNama.setText("Nama Nasabah : "+this.nama);
        labelTabungan.setText("Saldo Tabungan Emas : "+this.tabungan+ " gram");

        try {
            int jmlData = 0;
            String query = "SELECT * FROM `transaksi` WHERE iduser = "+iduser;
            connector.statement = connector.connection.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);

            while(resultSet.next()) {
                dataTransaksi[jmlData][0] = resultSet.getString("alamat"); 
                dataTransaksi[jmlData][1] = resultSet.getString("waktu"); 
                dataTransaksi[jmlData][2] = resultSet.getString("jenis"); 
                dataTransaksi[jmlData][3] = resultSet.getString("nominal");
                dataTransaksi[jmlData][4] = resultSet.getString("total");
                jmlData++; 
            }
            connector.statement.close();
        } catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }

        setTitle("Riwayat Transaksi "+this.nama);
        setSize(800, 500);
        setLayout(null);
        add(labelNama);
        add(labelTabungan);
        add(buttonKembali);

        labelNama.setBounds(30, 20, 200, 20);
        labelTabungan.setBounds(30, 45, 200, 20);
        buttonKembali.setBounds(320, 410, 170, 20);
        
        tabel = new JTable(dataTransaksi,namaKolom);
        scrollPane = new JScrollPane(tabel);
        add(scrollPane);
        
        scrollPane.setBounds(30, 85, 720, 300);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buttonKembali.addActionListener(this);
    }

    public int getIdUser() {
        return this.idUser;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonKembali) {
            new ViewHome(getIdUser());
            this.dispose();
        }
    }
}
