package com;
//import java.awt.event.*;
import java.sql.*;
//import javax.swing.*;
//import javax.swing.event.*;
//import javax.swing.table.DefaultTableModel;

public class ModelHome {
    private int idUser;
    private String username;
    private String nama;
    private String umur;
    private String alamat;
    private String tabungan;

    private ListenerHome listenerHome;
    ThenConnector connector = new ThenConnector();

    public ModelHome(int iduser) {
        String data[][] = new String[500][6];
        
        try {
            int jmlData = 0;
            String query = "SELECT * FROM `user` WHERE iduser = '"+iduser+"'";
            connector.statement = connector.connection.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            
            while(resultSet.next()) {
                data[jmlData][0] = resultSet.getString("iduser"); 
                data[jmlData][1] = resultSet.getString("email"); 
                data[jmlData][2] = resultSet.getString("nama");
                data[jmlData][3] = resultSet.getString("umur");
                data[jmlData][4] = resultSet.getString("alamat");
                data[jmlData][5] = resultSet.getString("tabungan");
                jmlData++; 
            }
            connector.statement.close();

            this.setIdUser(Integer.parseInt(data[0][0]));
            this.setUsername(data[0][1]);
            this.setNama(data[0][2]);
            this.setUmur(data[0][3]);
            this.setAlamat(data[0][4]);
            this.setTabungan(data[0][5]);

        } catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
    }

    protected void fireOnChange() {
        if (listenerHome != null) {
            listenerHome.onChange(this);
        }
    }

    public ListenerHome getHomeListener() {
        return listenerHome;
    }

    public void setHomeListener(ListenerHome listenerHome) {
        this.listenerHome = listenerHome;
    }
    
    public void setIdUser(int idUser) {
        this.idUser = idUser;
        fireOnChange();
    } 

    public int getIdUser() {
        return this.idUser;
    }

    public void setUsername(String username) {
        this.username = username;
        fireOnChange();
    } 

    public String getUsername() {
        return this.username;
    }

    public void setNama(String nama) {
        this.nama = nama;
        fireOnChange();
    }

    public String getNama() {
        return this.nama;
    }

    public void setUmur(String umur) {
        this.umur = umur;
        fireOnChange();
    }

    public String getUmur() {
        return this.umur;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
        fireOnChange();
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setTabungan(String tabungan) {
        this.tabungan = tabungan;
        fireOnChange();
    }

    public String getTabungan() {
        return this.tabungan;
    }
}
