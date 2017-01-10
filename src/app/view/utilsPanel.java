/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view;

import app.view.FixPanel.panelMaster;
import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author SEED
 */
public class utilsPanel {
     public static void LaporanPengeluaran()
    {
                EventQueue.invokeLater(() -> {
                app.table.Util.RefreshBank();
                app.table.Util.RefreshLaporan();
                javax.swing.JDialog jDialog1 = new JDialog();
                jDialog1.setSize(1200, 700);
                jDialog1.setLocationRelativeTo(null);
                jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
                jDialog1.getContentPane().add(new panelMaster(0));
                jDialog1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jDialog1.show();
        });
    }
}
