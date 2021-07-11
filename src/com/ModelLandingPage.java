package com;

// import java.awt.event.*;
// import java.sql.*;
// import javax.swing.*;
// import javax.swing.event.*;
// import javax.swing.table.DefaultTableModel;

public class ModelLandingPage {
    private ListenerLandingPage listenerLandingPage;

    public void setLandingPageListener(ListenerLandingPage listenerLandingPage) {
        this.listenerLandingPage = listenerLandingPage;
    }

    protected void fireOnChange() {
        if (listenerLandingPage != null) {
            listenerLandingPage.onChange(this);
        }
    }

    public ListenerLandingPage getLandingPageListener() {
        return listenerLandingPage;
    }
}
