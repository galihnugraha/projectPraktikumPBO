package com;

// import java.awt.event.*;
// import java.sql.*;
// import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class ControllerLandingPage {
    private ModelLandingPage modelLandingPage;

    public void setMo(ModelLandingPage modelLandingPage) {
        this.modelLandingPage = modelLandingPage;
    }

    public void goToDaftar(ViewLandingPage viewLandingPage) {
        new ViewDaftar();
        viewLandingPage.dispose();
    }

    public void goToLogin(ViewLandingPage viewLandingPage) {
        new ViewLogin();
        viewLandingPage.dispose();
    }

}
