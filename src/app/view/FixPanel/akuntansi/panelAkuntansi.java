/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.FixPanel.akuntansi;

//import app.table.Akuntansi;
import app.table.Bank;
import app.table.Laporan;
import com.joobar.csvbless.CSVUtil;
import com.joobar.csvbless.WriteStep;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaslang.Tuple;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
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
    private java.util.List<Akun> LabaList = new ArrayList<>();
    private java.util.List<Akun> LaporanPenyesuaian = new ArrayList<>();

    public List<Akun> getLaporanPenyesuaian() {
        return LaporanPenyesuaian;
    }

    public void setLaporanPenyesuaian(List<Akun> LaporanPenyesuaian) {
        this.LaporanPenyesuaian = LaporanPenyesuaian;
    }

    public List<Akun> getAkuntansiList() {
        return AkuntansiList;
    }

    public void setAkuntansiList(List<Akun> AkuntansiList) {
        this.AkuntansiList = AkuntansiList;
    }
    public List getList(Class kelas)
    {
        String que = "SELECT en FROM " + kelas.getSimpleName() + " en "
                + "where en.tanggal BETWEEN :startDate AND :endDate"
                ;
        TypedQuery createQuery = entityManager.createQuery(que, kelas)
                .setParameter("startDate", awalBulan, TemporalType.TIMESTAMP)
                .setParameter("endDate", akhirBulan, TemporalType.TIMESTAMP)  
                ;
        return createQuery.getResultList();
    }
    private BigInteger sumAll(List<? extends Laporan> laporanList)
    {
        BigInteger temp = new BigInteger("0");
        for (Laporan list : laporanList) {
            temp = temp.add(list.getJumlah());
        }
        return temp;
    }
            Calendar cal = Calendar.getInstance();
            Date akhirBulan;
            Date awalBulan;
    public panelAkuntansi() {
        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();        
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));            
        akhirBulan = cal.getTime();
        awalBulan = new Date(akhirBulan.getYear(), akhirBulan.getMonth(), 0);
        System.out.println("awalBulan = " + awalBulan);
        System.out.println("akhirBulan = " + akhirBulan);
        Akun total = new Akun().setAkun("Neraca Total");
        int X =1;
        List<app.table.Bank> list = entityManager.createQuery("SELECT en FROM Bank en", app.table.Bank.class).getResultList();       
        Akun Kas = new Akun(X++)
                .setAkun("Gabungan Kas");                
        for (Bank bank : list) {
            Kas.addPemasukan(bank.getFoo());
        }
        AkuntansiList.add(Kas);            
         Akun Modal = new Akun(X++)
                 .setAkun("Modal")
                 .setPengeluaran(sumAll(getList(app.table.Modal.class)));
         BigInteger mod = BigInteger.ZERO;
         List<Laporan> rangkuman = entityManager.createQuery(
                "SELECT l FROM Laporan l where l.tanggal < :endDate")
                .setParameter("endDate", awalBulan, TemporalType.DATE)  
                .getResultList();
         for (Laporan laporan : rangkuman) {
             mod = mod.add(laporan.getPemasukan());
             mod = mod.subtract(laporan.getPengeluaran());
        }
         Akun ModalSebelumnya = new Akun(X++)
                 .setAkun("Modal Sebelumnya")
                 .setPengeluaran(mod)
                 ;
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
        Akun  bebanPeminjaman = new Akun()
                .setAkun("Beban Peminjaman/Piutang")
                .setPemasukan(sumAll(getList(app.table.BayarhutangPengeluaran.class)));
        Akun  Peminjaman = new Akun()
                .setAkun("Pemasukan Peminjaman")
                .setPengeluaran(sumAll(getList(app.table.BayarhutangPemasukan.class)));
        Akun  bebanHutang = new Akun()
                .setAkun("Pelunasan Hutang ")
                .setPemasukan(sumAll(getList(app.table.BayarPihutangPengeluaran.class)));
        Akun  sisaHutang = new Akun()
                .setAkun("Sisa Hutang ")                
                ;
        Akun Mobil = new Akun()
                .setAkun("Pemasukan Mobil")
                .setPengeluaran(sumAll(getList(app.table.MobilPemasukan.class)));
        Akun bebanMobil = new Akun()
                .setAkun("Beban Mobil")
                .setPemasukan(sumAll(getList(app.table.MobilPengeluaran.class)));
        Akun PembagianLaba = new Akun()
                .setAkun("Pembagian Laba")
                .setPemasukan(sumAll(getList(app.table.pembagianLaba.class)));
        BigInteger bayarhutang = bebanHutang.getPemasukan();
        BigInteger jumlahhutang = Hutang.getPengeluaran();
        int res = jumlahhutang.compareTo(bayarhutang);
        if (res == 0) {
        } else if (res==-1) {
            bayarhutang = bayarhutang.subtract(jumlahhutang);
            sisaHutang.setPemasukan(bayarhutang);
        } else if (res == 1) {
            jumlahhutang = jumlahhutang.subtract(bayarhutang);
            sisaHutang.setPengeluaran(jumlahhutang);
        }
        AkuntansiList.add(ModalSebelumnya);
        AkuntansiList.add(Modal.subPengeluaran(Prive.getPemasukan()));
        AkuntansiList.add(PembagianLaba);
        AkuntansiList.add(Mobil);        
        AkuntansiList.add(Rental);        
        AkuntansiList.add(Jasa);        
        AkuntansiList.add(Pemasukan);        
        AkuntansiList.add(Peminjaman);        
        LabaList.add(Mobil);
        LabaList.add(Rental);
        LabaList.add(Jasa);
        LabaList.add(Pemasukan);
        LabaList.add(Peminjaman);
        //Akun Pengeluaran        
        AkuntansiList.add(sisaHutang);
        AkuntansiList.add(bebanMobil);
        AkuntansiList.add(bebanPeminjaman);
        AkuntansiList.add(pengeluaranRental);
        AkuntansiList.add(bebanJasa);
        AkuntansiList.add(Perjalanan);
        AkuntansiList.add(Operasional);
        AkuntansiList.add(Pegawai);                    
        AkuntansiList.add(Asset);
        LabaList.add(bebanMobil);
        LabaList.add(bebanPeminjaman);
        LabaList.add(pengeluaranRental);
        LabaList.add(bebanJasa);
        LabaList.add(Perjalanan);
        LabaList.add(Operasional);
        LabaList.add(Pegawai);                    
        LabaList.add(Asset);

        X = 1;
        total.setPemasukan(BigInteger.ZERO);
        total.setPengeluaran(BigInteger.ZERO);
        for (Akun akun : AkuntansiList) {
            akun.setNomor(X++);
            total.addPengeluaran(akun.getPengeluaran());
            total.addPemasukan(akun.getPemasukan());
        }
        
        Akun laba = new Akun()
                .setAkun("Total");
        for (Akun akun : LabaList) {
            laba.addPemasukan(akun.getPemasukan());
            laba.addPengeluaran(akun.getPengeluaran());
        }
        LabaList.add(laba);
        Akun kosong = new Akun()
                .setAkun("")
                .setPengeluaran(null)
                .setPemasukan(null);
        LabaList.add( kosong );
        LabaList.add(
                new Akun()
                .setAkun("Total Pemasukan")
                .setPengeluaran(laba.getPengeluaran())
        );
        LabaList.add(
                new Akun()
                .setAkun("Total Pengeluaran")
                .setPemasukan(laba.getPemasukan())
        );
        LabaList.add( kosong );
        Akun Profit = new Akun()
                .setAkun("Profit")
                .setPengeluaran(laba.getPengeluaran())
                .subPengeluaran(laba.getPemasukan());
        Akun ProfitBersih = new Akun()
                .setAkun("Profit Bersih")
                .setPengeluaran(laba.getPengeluaran())
                .subPengeluaran(laba.getPemasukan());
        Akun LabaTahan = new Akun()
                .setAkun("25% Laba yang di tahan")
                .setPengeluaran(laba.getPengeluaran())
                .subPengeluaran(laba.getPemasukan())
                .DividePengeluaran();
        ProfitBersih.subPengeluaran(LabaTahan.getPengeluaran());
        ProfitBersih.subPengeluaran(PembagianLaba.getPemasukan());
        LabaList.add(
                Profit
        );
        LabaList.add(
                PembagianLaba
        );
        LabaList.add(
              LabaTahan   
        );
        
        LabaList.add(
              ProfitBersih   
        );        
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
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jFileChooser1 = new javax.swing.JFileChooser();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        FormListener formListener = new FormListener();

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LAPORAN PERUBAHAN MODAL", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable2.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable3.setAutoCreateRowSorter(true);
        jTable3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NERACA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable2.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable4.setAutoCreateRowSorter(true);
        jTable4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jFileChooser1.setApproveButtonText("Print");
        jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        jFileChooser1.addActionListener(formListener);

        setPreferredSize(new java.awt.Dimension(1500, 1000));
        setLayout(new java.awt.BorderLayout());

        jButton1.setText("Print");
        jButton1.addActionListener(formListener);
        add(jButton1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NERACA SALDO BULAN INI", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

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
        columnBinding.setColumnName("Aktiva(Harta + Pengeluaran)");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pasiva(Hutang + Modal + Pemasukan)");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jPanel1.add(jScrollPane1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TABEL LABA/RUGI BULAN INI", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable2.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable2.setAutoCreateRowSorter(true);
        jTable2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${labaList}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable2);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomor}"));
        columnBinding.setColumnName("Nomor");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${akun}"));
        columnBinding.setColumnName("Akun");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pemasukan");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemasukan}"));
        columnBinding.setColumnName("Pengeluaran");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jPanel1.add(jScrollPane2);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButton1) {
                panelAkuntansi.this.jButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == jFileChooser1) {
                panelAkuntansi.this.jFileChooser1ActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jFileChooser1.showSaveDialog(jPanel1);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        File root = jFileChooser1.getSelectedFile();
        System.out.println("root = " + root);
        Date p = new Date();
        final SimpleDateFormat formator = new SimpleDateFormat("dd-MM-yyyy");
        File akun = new File(root, "Neraca "+formator.format(p)+".CSV");
        File laba = new File(root, "Laba-Rugi "+formator.format(p)+".CSV");
        List a = AkuntansiList;
        List b = LabaList;
        final DecimalFormat IDR = new DecimalFormat("#,##0");              
        Function f = d -> d==null?"0":IDR.format(d);
        WriteStep AkunPrinter = CSVUtil.of(akun)
                .type(app.view.FixPanel.akuntansi.Akun.class)
                .properties(
                        Tuple.of("No", "nomor", null),
                        Tuple.of("Akun", "akun", null),
                        Tuple.of("Aktiva(Harta + Pengeluaran)", "pemasukan", f),
                        Tuple.of("Pasiva(Hutang + Modal + Pemasukan)", "pengeluaran", f)
                )
                .dataList(a);
        WriteStep LabaPrinter = CSVUtil.of(laba)
                .type(app.view.FixPanel.akuntansi.Akun.class)
                .properties(
                        Tuple.of("No", "nomor", null),
                        Tuple.of("Akun", "akun", null),
                        Tuple.of("Pemasukan", "pengeluaran", f),
                        Tuple.of("Pengeluaran", "pemasukan", f)
                )
                .dataList(b);        
        try {
                 AkunPrinter.write();
                 LabaPrinter.write();
         
        } catch (Exception e) {
            javax
                    .swing
                    .JOptionPane.showMessageDialog(null, 
                    "Gagal Print, Karena file sementara terbuka\n"+e);                
        }
            int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
                    "Berhasil Print apakah anada ingin membuka File?"
                            + "\nPath Neraca File= "+ akun
                            + "\nPath Laba-Rugi File= "+ laba
            );
            Desktop desktop = Desktop.getDesktop();
            try {
                if(!Desktop.isDesktopSupported()){
                        System.out.println("Desktop is not supported");
                        return;
                }
                if (confirm == 0) {
                    if(akun.exists()) desktop.open(akun);
                    if(laba.exists()) desktop.open(laba);                    
                }
            } catch (Exception ex) {
                    Logger.getLogger(panelAkuntansi.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("end");
        // TODO add your handling code here:
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    public List<Akun> getLabaList() {
        return LabaList;
    }

    public void setLabaList(List<Akun> LabaList) {
        this.LabaList = LabaList;
    }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
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
                System.out.println("app.view.FixPanel.akuntansi.panelAkuntansi.main()");
                javax.swing.JFrame jDialog1 = new JFrame("Akuntasi");
                try {
                EntityManagerFactory fact = Persistence.createEntityManagerFactory("blessingPU");
                EntityManager manager = fact.createEntityManager();
                boolean active = manager.getTransaction().isActive();
                if (!active) {
                    manager.getTransaction().begin();            
                }
                 Query query = manager.createQuery("SELECT l FROM Laporan l");
                 java.util.List<app.table.Laporan> data = query.getResultList();
                 data.forEach((laporan) -> {
                        manager.refresh(laporan);
                    });
                 Query bank = manager.createQuery("SELECT b From Bank b ORDER BY b");
                 List<Bank> result = bank.getResultList();
                 result.forEach(a -> manager.refresh(a));
                jDialog1.show();
                jDialog1.setSize(1200, 700);
                jDialog1.setLocationRelativeTo(null);
                jDialog1.getContentPane().add(new panelAkuntansi());
                jDialog1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null, e);
                jDialog1.dispose();
                System.exit(100);
            }
        });
    }
    
}
