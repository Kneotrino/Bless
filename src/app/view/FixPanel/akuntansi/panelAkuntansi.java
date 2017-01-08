/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.FixPanel.akuntansi;

//import app.table.Akuntansi;
import app.table.Asset;
import app.table.Bank;
import app.table.Laporan;
import app.table.Modal;
import app.table.Pegawaigaji;
import app.table.Pemasukan;
import app.table.Pengeluaran;
import app.view.FixPanel.PanelTransaksi;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.Beans;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author SEED
 */
public class panelAkuntansi extends JPanel {
    private java.util.List<Akun> AkuntansiList = new ArrayList<>();

    public List<Akun> getAkuntansiList() {
        return AkuntansiList;
    }

    public void setAkuntansiList(List<Akun> AkuntansiList) {
        this.AkuntansiList = AkuntansiList;
    }
    public List getList(Class kelas)
    {
        String que = "SELECT en FROM " + kelas.getSimpleName() + " en";
//        System.out.println("que = " + que);
        TypedQuery createQuery = entityManager.createQuery(que, kelas);
        return createQuery.getResultList();
    }
    public BigInteger sumAll(List<? extends Laporan> laporanList)
    {
        BigInteger temp = new BigInteger("0");
        for (Laporan list : laporanList) {
            temp = temp.add(list.getJumlah());
        }
        return temp;
    }
    public void addList(Akun akun)
    {
    AkuntansiList.add(akun);
    }
    public panelAkuntansi() {
        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();        
        Akun total = new Akun().setAkun("Neraca Total");
        int X =1;
        List<app.table.Bank> list = getList(app.table.Bank.class);       
        for (Bank bank : list) {
        Akun Kas = new Akun(X++)
                .setAkun(bank.getNamaBank())
                .setPemasukan(bank.getFoo());
        AkuntansiList.add(Kas);            
        }
         Akun Modal = new Akun(X++)
                 .setAkun("Modal")
                 .setPengeluaran(sumAll(getList(app.table.Modal.class)));
         Akun Prive = new Akun(X++)
                 .setAkun("Penarikan Modal/Prive")
                 .setPemasukan(sumAll(getList(app.table.Prive.class)));
         Akun Hutang = new Akun(X++)
                 .setAkun("Hutang")
                 .setPengeluaran(sumAll(getList(app.table.BayarPihutangPemasukan.class)));
         Akun Operasional = new Akun(X++)
                 .setAkun("Beban Operasional")
                 .setPemasukan(sumAll(getList(app.table.Pengeluaran.class)))
                 ;
         Akun Pegawai = new Akun(X++)
                 .setAkun("Beban Gaji Pegawai")
                 .setPemasukan(sumAll(getList(app.table.Pegawaigaji.class)));
         Akun Asset = new Akun(X++)
                 .setAkun("Beban Biaya Asset")
                 .setPemasukan(sumAll(getList(app.table.Asset.class)));                  
        Akun Pemasukan = new Akun(X++)
                 .setAkun("Pemasukan Lainnya")
                .setPengeluaran(sumAll(getList(app.table.Pemasukan.class)))
                ;
        Akun Perjalanan = new Akun(X++)
                .setAkun("Beban Perjalanan")
                .setPemasukan(sumAll(getList(app.table.PerjalananPengeluaran.class)));
                ;
        Akun Rental = new Akun(X++)
                .setAkun("Pemasukan Rental")
                .setPengeluaran(sumAll(getList(app.table.BayarRentalPemasukan.class)))
                ;
        Akun pengeluaranRental = new Akun(X++)
                .setAkun("Beban Rental")
                .setPemasukan(sumAll(getList(app.table.BayarRentalPenngeluaran.class)));        
        Akun Jasa = new Akun()
                .setAkun("Pemasukan Jasa")
                .setPengeluaran(sumAll(getList(app.table.BayarJasaPemasukan.class)));
        Akun  bebanJasa = new Akun()
                .setAkun("Beban Jasa")
                .setPemasukan(sumAll(getList(app.table.BayarJasaPengeluaran.class)));
//        AkuntansiList.add(Rental);        
        Akun  bebanPeminjaman = new Akun()
                .setAkun("Beban Peminjaman/Piutang")
                .setPemasukan(sumAll(getList(app.table.BayarhutangPengeluaran.class)));
        Akun  Peminjaman = new Akun()
                .setAkun("Pemasukan Peminjaman")
                .setPengeluaran(sumAll(getList(app.table.BayarhutangPemasukan.class)));
        Akun  bebanHutang = new Akun()
                .setAkun("Pelunasan Hutang ")
                .setPemasukan(sumAll(getList(app.table.BayarPihutangPengeluaran.class)));
        Akun Mobil = new Akun()
                .setAkun("Pemasukan Mobil")
                .setPengeluaran(sumAll(getList(app.table.MobilPemasukan.class)));
        Akun bebanMobil = new Akun()
                .setAkun("Beban Mobil")
                .setPemasukan(sumAll(getList(app.table.MobilPengeluaran.class)));
        //Akun Pemasukan
        BigInteger Transfer  = sumAll(getList(app.table.PerjalananPemasukan.class)).negate();
        Akun SaldoPerjalanan = new Akun()
                .setAkun("Saldo Perjalanan")
                .setPemasukan(Transfer);
        Akun PembagianLaba = new Akun()
                .setAkun("Pembagian Laba")
//                .addPengeluaran(Transfer)
                ;
//                .setPengeluaran(sumAll(getList(app.table.PerjalananPemasukan.class)));      
        AkuntansiList.add(Modal);
        AkuntansiList.add(Prive);
        AkuntansiList.add(Hutang);
        AkuntansiList.add(SaldoPerjalanan);        
        AkuntansiList.add(Mobil);        
        AkuntansiList.add(Rental);        
        AkuntansiList.add(Jasa);        
        AkuntansiList.add(Pemasukan);        
        AkuntansiList.add(Peminjaman);        
        //Akun Pengeluaran
        AkuntansiList.add(bebanMobil);
        AkuntansiList.add(bebanHutang);
        AkuntansiList.add(bebanPeminjaman);
        AkuntansiList.add(pengeluaranRental);
        AkuntansiList.add(bebanJasa);
        AkuntansiList.add(Perjalanan);
        AkuntansiList.add(Operasional);
        AkuntansiList.add(Pegawai);                    
        AkuntansiList.add(Asset);
        X = 1;
        total.setPemasukan(BigInteger.ZERO);
        total.setPengeluaran(BigInteger.ZERO);
        for (Akun akun : AkuntansiList) {
            akun.setNomor(X++);
            total.addPengeluaran(akun.getPengeluaran());
            total.addPemasukan(akun.getPemasukan());
        }
        AkuntansiList.add(total);                    
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NERACA SALDO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable1.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${akuntansiList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomor}"));
        columnBinding.setColumnName("No");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${akun}"));
        columnBinding.setColumnName("Akun");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemasukan}"));
        columnBinding.setColumnName("Aktiva");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pasiva");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        add(jScrollPane1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TABEL LABA/RUGI", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable1.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable2.setAutoCreateRowSorter(true);
        jTable2.setBorder(javax.swing.BorderFactory.createTitledBorder("TABEL LABA/RUGI"));
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        add(jScrollPane2);

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(panelAkuntansi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(panelAkuntansi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(panelAkuntansi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(panelAkuntansi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(() -> {
                app.table.Util.RefreshBank();
                app.table.Util.RefreshLaporan();
                javax.swing.JDialog jDialog1 = new JDialog();
                jDialog1.setSize(1000, 700);
                jDialog1.setLocationRelativeTo(null);
                jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
                jDialog1.getContentPane().add(new panelAkuntansi());
                jDialog1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jDialog1.show();
        });
    }
    
}
