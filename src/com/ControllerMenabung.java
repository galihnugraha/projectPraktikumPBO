package com;

import javax.swing.*;

public class ControllerMenabung {
    private ModelMenabung mo;
    ThenConnector connector = new ThenConnector();

    public void setMo(ModelMenabung mo) {
        this.mo = mo;
    }

    public void submitDeposit(ViewMenabung viewMenabung) {
        int angkaTabungan = 0;
        boolean cekHuruf = true;

        String textTabungan = viewMenabung.getTextTabungan().getText();
        mo.setTextTabungan(textTabungan);

        if (textTabungan.trim().equals("")) {
            JOptionPane.showMessageDialog(viewMenabung, "Kolom Masih Kosong!");
        } else {

            try {
                angkaTabungan = Integer.parseInt(textTabungan);
                mo.setTabungan(angkaTabungan);
            } catch (NumberFormatException error) {
                cekHuruf = false;
                JOptionPane.showMessageDialog(viewMenabung, "Harus merupakan bilangan bulat!");
                mo.setTextTabungan("");
            } catch (Exception error) {
                cekHuruf = false;
                JOptionPane.showMessageDialog(viewMenabung, error.getMessage());
                mo.setTextTabungan("");
            }

            if (cekHuruf) {
                if (angkaTabungan < 100000) {
                    JOptionPane.showMessageDialog(viewMenabung, "Minimal deposit Rp100.000!");
                    mo.setTextTabungan("");
                } else {
                    mo.konversiEmas(viewMenabung);
                    mo.submitDeposit(viewMenabung);
                    new ViewHome(mo.getIdUser());
                    viewMenabung.dispose();
                }
            }
        }
    }

    public void goToHomePage(ViewMenabung viewMenabung) {
        new ViewHome(mo.getIdUser());
        viewMenabung.dispose();
    }
}
