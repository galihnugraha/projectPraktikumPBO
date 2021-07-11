package com;

//import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
//import javax.swing.event.*;
//import javax.swing.table.DefaultTableModel;

public class ModelEdit {
    private int idUser;
    private String username;
    private String nama;
    private String umur;
    private int umurAngka;
    private String alamat;

    private ListenerEdit listenerEdit;
    ThenConnector connector = new ThenConnector();

    public ModelEdit(int iduser) {
        String data[][] = new String[500][5];
        
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
                jmlData++; 
            }
            connector.statement.close();

            this.setIdUser(Integer.parseInt(data[0][0]));
            this.setUsername(data[0][1]);
            this.setNama(data[0][2]);
            this.setUmur(data[0][3]);
            this.setAlamat(data[0][4]);

        } catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
    }

    protected void fireOnChange() {
        if (listenerEdit != null) {
            listenerEdit.onChange(this);
        }
    }

    public ListenerEdit getEditListener() {
        return listenerEdit;
    }

    public void setEditListener(ListenerEdit listenerEdit) {
        this.listenerEdit = listenerEdit;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
    public void resetForm() {
        setNama("");
        setUmur("");
        setAlamat("");
    }

    public void submitEdit(ViewEdit viewEdit) {
        try {
            String query = "UPDATE user SET nama = '"+getNama()+"', umur = "+getUmurAngka()+", alamat = '"+getAlamat()+"' WHERE iduser = "+getIdUser();
            
            connector.statement = connector.connection.createStatement();
            connector.statement.executeUpdate(query);

            System.out.println("\nEdit profile Berhasil, iduser: "+getIdUser());
            JOptionPane.showMessageDialog(viewEdit, "Edit Profil Berhasil");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
