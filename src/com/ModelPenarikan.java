package com;

//import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
//import javax.swing.event.*;
import java.util.Date;  
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ModelPenarikan {
    private String textTabungan;
    private int idUser;
    private String username;
    private String nama;
    private String umur;
    private String alamat;
    private int tabungan;
    private double emas;

    DecimalFormat df = new DecimalFormat();
    private ListenerPenarikan listenerPenarikan;
    ThenConnector connector = new ThenConnector();

    public ModelPenarikan(int iduser) {
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
            this.setEmas(Double.parseDouble(data[0][5]));

        } catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
    }

    protected void fireOnChange() {
        if (listenerPenarikan != null) {
            listenerPenarikan.onChange(this);
        }
    }

    public ListenerPenarikan getPenarikanListener() {
        return listenerPenarikan;
    }

    public void setPenarikanListener(ListenerPenarikan listenerPenarikan) {
        this.listenerPenarikan = listenerPenarikan;
    }
    
    public void setTextTabungan(String textTabungan) {
        this.textTabungan = textTabungan;
        fireOnChange();
    } 

    public String getTextTabungan() {
        return this.textTabungan;
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

    public void setTabungan(int tabungan) {
        this.tabungan = tabungan;
    }

    public int getTabungan() {
        return this.tabungan;
    }

    public void setEmas(double emas) {
        this.emas = emas;
    }

    public double getEmas() {
        return this.emas;
    }

    public int cekSelisih(ViewPenarikan viewPenarikan) {
        double tabungan = Double.valueOf(this.tabungan);
        double emas = this.emas;
        
        if (tabungan < emas) {
            double selisih = emas - tabungan;
            df.setMaximumFractionDigits(3);
            String konversi3digit = df.format(selisih);
            selisih = Double.parseDouble(konversi3digit);

            this.setEmas(selisih);
            return 1;
        } else {
            return 0;
        }
    }

    private String getTanggal() {  
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
        Date date = new Date();  
        return dateFormat.format(date);  
    }  
     
    private String getWaktu() {  
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  
        Date date = new Date();  
        return dateFormat.format(date);  
    }

    public void submitPenarikan(ViewPenarikan viewPenarikan) {
        String waktu = getTanggal()+" "+getWaktu();

        try {
            String query1 = "UPDATE user SET tabungan = "+getEmas()+" WHERE iduser = "+getIdUser();

            String query2 = "INSERT INTO `transaksi`(`iduser`,`alamat`,`waktu`,`jenis`,`nominal`,`total`) VALUES ("+getIdUser()+",'"+getAlamat()+"','"+waktu+"','penarikan',"+tabungan+","+getEmas()+")";
            
            connector.statement = connector.connection.createStatement();
            connector.statement.executeUpdate(query1);
            connector.statement.executeUpdate(query2);

            System.out.println("\nTransaksi idUser "+getIdUser()+" Berhasil");
            System.out.println("Penarikan Emas "+tabungan+" gram Berhasil!!");
            System.out.println("Insert Transaksi Berhasil");
            JOptionPane.showMessageDialog(null,"Penarikan Emas "+tabungan+" gram Berhasil!!");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SQL Error");
        }
    }
}
