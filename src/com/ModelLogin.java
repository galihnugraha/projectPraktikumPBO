package com;

import java.sql.*;

public class ModelLogin {
    private String username;
    private String password;
    private String usernameData;
    private String passwordData;

    private ListenerLogin listenerLogin;
    ThenConnector connector = new ThenConnector();

    protected void fireOnChange() {
        if (listenerLogin != null) {
            listenerLogin.onChange(this);
        }
    }

    public ListenerLogin getLoginListener() {
        return listenerLogin;
    }

    public void setLoginListener(ListenerLogin listenerLogin) {
        this.listenerLogin = listenerLogin;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
        fireOnChange();
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        fireOnChange();
    }

    public void setUsernameData(String username) {
        this.usernameData = username;
    }

    public String getUsernameData() {
        return this.usernameData;
    }

    public void setPasswordData(String password) {
        this.passwordData = password;
    }

    public String getPasswordData() {
        return this.passwordData;
    }

    public void resetForm() {
        setUsername("");
        setPassword("");
    }
    
    public int submitLogin(ViewLogin viewLogin) {
        String data[][] = new String[500][3];
        
        try {
            int jmlData = 0;
            String query = "SELECT * FROM `user` WHERE email = '"+this.username+"'";
            connector.statement = connector.connection.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            
            while(resultSet.next()) {
                data[jmlData][0] = resultSet.getString("iduser"); 
                data[jmlData][1] = resultSet.getString("email"); 
                data[jmlData][2] = resultSet.getString("password");
                jmlData++; 
            }
            connector.statement.close();

        } catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
        
        if (data[0][0] == null) {
            return 0;
        } else {
            this.setUsernameData(data[0][1]);
            this.setPasswordData(data[0][2]);
            return Integer.parseInt(data[0][0]);
        }
    }
}
