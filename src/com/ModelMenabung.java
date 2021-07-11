package com;

//import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
//import javax.swing.event.*;
import java.util.Date;  
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ModelMenabung {
    private String textTabungan;
    private int idUser;
    private String username;
    private String nama;
    private String umur;
    private String alamat;
    private int tabungan;
    private double emas;
    private double tabunganEmas;

    DecimalFormat df = new DecimalFormat();
    private ListenerMenabung listenerMenabung;
    ThenConnector connector = new ThenConnector();

    public ModelMenabung(int iduser) {
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
        if (listenerMenabung != null) {
            listenerMenabung.onChange(this);
        }
    }

    public ListenerMenabung getMenabungListener() {
        return listenerMenabung;
    }

    public void setMenabungListener(ListenerMenabung listenerMenabung) {
        this.listenerMenabung = listenerMenabung;
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

    public void setTabunganEmas(double tabunganEmas) {
        this.tabunganEmas = tabunganEmas;
    }

    public void konversiEmas(ViewMenabung viewMenabung) {
        double bonus = 0;
        int jmlData = getRiwayatTransaksi();

        if (jmlData >= 29) {
            jmlData++;
            bonus = 0.1;
            JOptionPane.showMessageDialog(null,"Anda mendapatkan bonus 10%, karena menabung ke-"+jmlData+" kalinya!");
        } else if (jmlData >= 19) {
            jmlData++;
            bonus = 0.075;
            JOptionPane.showMessageDialog(null,"Anda mendapatkan bonus 7,5%, karena menabung ke-"+jmlData+" kalinya!");
        } else if (jmlData >= 9) {
            jmlData++;
            bonus = 0.05;
            JOptionPane.showMessageDialog(null,"Anda mendapatkan bonus 5%, karena menabung ke-"+jmlData+" kalinya!");
        } else if (jmlData >= 4) {
            jmlData++;
            bonus = 0.025;
            JOptionPane.showMessageDialog(null,"Anda mendapatkan bonus 2,5%, karena menabung ke-"+jmlData+" kalinya!");
        } else {
            bonus = 0;
        }
        
        double hasilTabungan = Double.valueOf(this.tabungan) / 845907;
        df.setMaximumFractionDigits(3);
        String konversi3digit = df.format(hasilTabungan);
        hasilTabungan = Double.parseDouble(konversi3digit);

        System.out.println("\nhasil konversi = "+hasilTabungan);
        
        bonus = bonus * hasilTabungan;
        konversi3digit = df.format(bonus);
        bonus = Double.parseDouble(konversi3digit);
        System.out.println("bonus = "+bonus);

        hasilTabungan = hasilTabungan + bonus;

        this.setTabunganEmas(hasilTabungan);

        hasilTabungan = hasilTabungan + this.getEmas();
        konversi3digit = df.format(hasilTabungan);
        hasilTabungan = Double.parseDouble(konversi3digit);

        this.setEmas(hasilTabungan);
    }

    public int getRiwayatTransaksi() {
        String data[][] = new String[500][1];
        int jmlData = 0;
        
        try {
            String query = "SELECT * FROM `transaksi` WHERE iduser = "+this.getIdUser()+" AND jenis = 'simpanan'";
            connector.statement = connector.connection.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            
            while(resultSet.next()) {
                data[jmlData][0] = resultSet.getString("idtrans");
                jmlData++; 
            }
            connector.statement.close();

        } catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }

        return jmlData;
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

    public void submitDeposit(ViewMenabung viewMenabung) {
        String waktu = getTanggal()+" "+getWaktu();

        try {
            String query1 = "UPDATE user SET tabungan = "+getEmas()+" WHERE iduser = "+getIdUser();

            String query2 = "INSERT INTO `transaksi`(`iduser`,`alamat`,`waktu`,`jenis`,`nominal`,`total`) VALUES ("+getIdUser()+",'"+getAlamat()+"','"+waktu+"','simpanan',"+tabunganEmas+","+getEmas()+")";
            
            connector.statement = connector.connection.createStatement();
            connector.statement.executeUpdate(query1);
            connector.statement.executeUpdate(query2);

            System.out.println("Insert Transaksi Berhasil");
            System.out.println("Transaksi idUser "+getIdUser()+" Berhasil");
            JOptionPane.showMessageDialog(null,"Deposit Emas "+tabunganEmas+" gram Berhasil!!");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SQL Error");
        }
    }
}
