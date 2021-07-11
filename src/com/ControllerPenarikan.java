package com;

import javax.swing.*;

public class ControllerPenarikan {
    private ModelPenarikan mo;
    ThenConnector connector = new ThenConnector();

    public void setMo(ModelPenarikan mo) {
        this.mo = mo;
    }

    public void submitPenarikan(ViewPenarikan viewPenarikan) {
        int angkaTabungan = 0;
        boolean cekHuruf = true;

        String textTabungan = viewPenarikan.getTextTabungan().getText();
        mo.setTextTabungan(textTabungan);

        if (textTabungan.trim().equals("")) {
            JOptionPane.showMessageDialog(viewPenarikan, "Kolom Masih Kosong!");
        } else {

            try {
                angkaTabungan = Integer.parseInt(textTabungan);
                mo.setTabungan(angkaTabungan);
            } catch (NumberFormatException error) {
                cekHuruf = false;
                JOptionPane.showMessageDialog(viewPenarikan, "Harus merupakan bilangan bulat!");
                mo.setTextTabungan("");
            } catch (Exception error) {
                cekHuruf = false;
                JOptionPane.showMessageDialog(viewPenarikan, error.getMessage());
                mo.setTextTabungan("");
            }

            if (cekHuruf) {
                if (angkaTabungan < 1) {
                    JOptionPane.showMessageDialog(viewPenarikan, "Minimal penarikan 1 gram!");
                    mo.setTextTabungan("");
                } else {
                    int cekAkhir = mo.cekSelisih(viewPenarikan);

                    if (cekAkhir == 1) {
                        mo.submitPenarikan(viewPenarikan);
                        new ViewHome(mo.getIdUser());
                        viewPenarikan.dispose();
                    } else if (cekAkhir == 0) {
                        JOptionPane.showMessageDialog(viewPenarikan, "Saldo Emas Tidak Cukup!");
                    }
                }
            }
        }
    }

    public void goToHomePage(ViewPenarikan viewPenarikan) {
        new ViewHome(mo.getIdUser());
        viewPenarikan.dispose();
    }
}
