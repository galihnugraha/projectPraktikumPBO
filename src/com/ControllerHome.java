package com;

import javax.swing.*;

public class ControllerHome {
    private ModelHome mo;
    ThenConnector connector = new ThenConnector();

    public void setMo(ModelHome mo) {
        this.mo = mo;
    }

    public void goToMenabung(ViewHome viewHome) {
        new ViewMenabung(mo.getIdUser());
        viewHome.dispose();
    }

    public void goToPenarikan(ViewHome viewHome) {
        new ViewPenarikan(mo.getIdUser());
        viewHome.dispose();
    }

    public void goToRiwayat(ViewHome viewHome) {
        new RiwayatTransaksi(mo.getIdUser());
        viewHome.dispose();
    }

    public void goToEdit(ViewHome viewHome) {
        new ViewEdit(mo.getIdUser());
        viewHome.dispose();
    }

    public void logout(ViewHome viewHome) {
        JOptionPane.showMessageDialog(viewHome, "Anda telah logout akun");
        new ViewLandingPage();
        viewHome.dispose();
        System.out.println("\nidUser "+mo.getIdUser()+" telah logout");
    }
}
