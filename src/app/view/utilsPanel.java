/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view;

import app.table.Laporan;
import app.view.FixPanel.panelMaster;
import java.awt.EventQueue;
import java.math.BigInteger;
import java.text.DecimalFormat;
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
     public static boolean simpan(javax.persistence.EntityManager entityManager, Laporan obj)
     {
         BigInteger bank = obj.getTransaksi().getBankId().getFoo();
//         System.out.println("Saldo Bank = " + bank);
         BigInteger keluar = obj.getPengeluaran();
//         System.out.println("Keluar = " + keluar);
         bank = bank.subtract(keluar);
//         System.out.println("Sisa = " + bank);
         boolean minus;
         minus = bank.min(BigInteger.ZERO).equals(BigInteger.ZERO);
         if (minus) {
//             javax.swing.JOptionPane.showMessageDialog(null,  "Gagal Menyimpan");                          
             entityManager.persist(obj);
             return true;
         }
         else {
             DecimalFormat nf = new DecimalFormat("IDR #,##0");
             javax.swing.JOptionPane.showMessageDialog(null,  "Gagal Menyimpan\n"
             +"Saldo Bank = "+ nf.format(bank)
             +"\nPengeluaran = "+ nf.format(keluar)
                     );  
             return false;
         }
     
     }
}
