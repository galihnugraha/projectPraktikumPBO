package com;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
//import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class Admin extends JFrame implements ActionListener, MouseListener {
    String data[][] = new String[500][5];
    ThenConnector connector = new ThenConnector();

    JTable tabel;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"Id User", "Email", "Nama Nasabah", "Umur (tahun)", "Saldo (gram)"};
    
    JLabel labelJudul = new JLabel("Pilih salah satu akun untuk dihapus");
    JButton buttonKembali = new JButton("Logout Admin");

    public Admin(){
        try {
            int jmlData = 0;
            String query = "SELECT * FROM `user` WHERE email != 'admin'";
            connector.statement = connector.connection.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            
            while (resultSet.next()) { 
                data[jmlData][0] = resultSet.getString("iduser"); 
                data[jmlData][1] = resultSet.getString("email"); 
                data[jmlData][2] = resultSet.getString("nama");
                data[jmlData][3] = resultSet.getString("umur");
                data[jmlData][4] = resultSet.getString("tabungan");
                jmlData++; 
            }
            connector.statement.close();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }

        setTitle("Panel Admin");
        setSize(605, 425);
        setLayout(null);
        add(labelJudul);
        add(buttonKembali);
        labelJudul.setBounds(30, 20, 200, 20);
        buttonKembali.setBounds(210, 330, 180, 20);
        
        tabel = new JTable(data, namaKolom);
        scrollPane = new JScrollPane(tabel);
        add(scrollPane);
        
        scrollPane.setBounds(30, 55, 530, 250);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buttonKembali.addActionListener(this);
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabel.addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonKembali) {
            JOptionPane.showMessageDialog(null, "Telah Logout Admin");
            System.out.println("\nAdmin telah Logout");
            new ViewLandingPage();
            this.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            String valueInFirstColumn = null;

            int row = tabel.getSelectedRow();
            valueInFirstColumn = (String)tabel.getValueAt(row, 0);
            
            if (valueInFirstColumn == null) {
                JOptionPane.showMessageDialog(null,"Anda memilih kolom kosong");
            } else {
                System.out.println("\nSelected ID to delete : "+valueInFirstColumn);
                int id = Integer.parseInt(valueInFirstColumn);
                new DeleteUser(id);
                this.dispose();
            }

        } else {}
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
