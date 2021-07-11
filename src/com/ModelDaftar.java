package com;

//import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
//import javax.swing.event.*;
//import javax.swing.table.DefaultTableModel;

public class ModelDaftar {
    private String username;
    private String nama;
    private String umur;
    private int umurAngka;
    private String alamat;
    private String password;
    private String rePassword;

    private ListenerDaftar listenerDaftar;
    ThenConnector connector = new ThenConnector();

    protected void fireOnChange() {
        if (listenerDaftar != null) {
            listenerDaftar.onChange(this);
        }
    }

    public ListenerDaftar getDaftarListener() {
        return listenerDaftar;
    }

    public void setDaftarListener(ListenerDaftar listenerDaftar) {
        this.listenerDaftar = listenerDaftar;
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

    public void setUmurAngka(int umurAngka) {
        this.umurAngka = umurAngka;
        fireOnChange();
    }

    public int getUmurAngka() {
        return this.umurAngka;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
        fireOnChange();
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setPassword(String password) {
        this.password = password;
        fireOnChange();
    }

    public String getPassword() {
        return this.password;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
        fireOnChange();
    }

    public String getRePassword() {
        return this.rePassword;
    }
    
    public void resetForm() {
        setUsername("");
        setNama("");
        setUmur("");
        setAlamat("");
        setPassword("");
        setRePassword("");
    }

    public int cekEmail(ViewDaftar viewDaftar) {
        String data[][] = new String[500][1];
        
        try {
            int jmlData = 0;
            String query = "SELECT iduser, email, password FROM `user` WHERE email = '"+this.username+"'";
            connector.statement = connector.connection.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            
            while(resultSet.next()) {
                data[jmlData][0] = resultSet.getString("iduser");
                jmlData++; 
            }
            connector.statement.close();

        } catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
        
        if (data[0][0] == null) {
            return 1;
        } else {
            return 0;
        }
    }

    public void submitDaftar(ViewDaftar viewDaftar) {
        try {
            String query = "INSERT INTO `user`(`email`,`password`,`nama`,`umur`,`alamat`) VALUES ('"+username+"','"+password+"','"+nama+"',"+umurAngka+",'"+alamat+"')";
            
            connector.statement = connector.connection.createStatement();
            connector.statement.executeUpdate(query);

            System.out.println("Insert User Berhasil, email: "+username);
            JOptionPane.showMessageDialog(viewDaftar, "Daftar Akun Berhasil, Silahkan Login!");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
