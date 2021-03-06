/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.panel.mobil;

import app.table.BagiLaba;
import app.table.Bank;
import app.table.Bpkb;
import app.table.Debitur;
import app.table.KeuanganMobil;
import app.table.Leasing;
import app.table.Listleasing;
import app.table.Mobil;
import app.table.MobilPemasukan;
import app.table.MobilPengeluaran;
import app.table.Perjalanan;
import app.table.Saldo;
import app.table.Trips;
import static app.utils.ExcelConverter.ExcelConverter;
import app.view.FixPanel.akuntansi.Akun;
import static app.view.FixPanel.panelLaporan.toTableModel;
//import static app.utils.Printer.getDataList;
import app.view.FixPanel.panelLeasing;
import app.view.ShowRoom;
import app.view.utilsPanel;
import static au.com.bytecode.opencsv.CSVParser.DEFAULT_SEPARATOR;
import au.com.bytecode.opencsv.CSVWriter;
import com.joobar.csvbless.CSVUtil;
import com.joobar.csvbless.WriteStep;
import com.toedter.calendar.JDateChooserCellEditor;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javaslang.Tuple;
import javax.imageio.ImageIO;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static org.apache.commons.collections.CollectionUtils.collect;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author SEED
 */
public class panelMobil extends javax.swing.JPanel {

    /**
     * Creates new form Mobil
     */
    public panelMobil() {
        try {
            String fullPath = app.utils.TableUtils.ExportResource("/1.PNG");
            URL systemResource = getClass().getResource("1.PNG");
                if (systemResource == null) {
                    throw new RuntimeException("null di sini");
                }
            System.out.println("fullPath = " + fullPath);
            this.gambar1 = new File(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("app.view.panel.mobil.panelMobil.<init>()");
        gambar6 =gambar5 =gambar4 =gambar3 =gambar2 = gambar1;
        initComponents();
        for (int i = 0; i < this.jTable1.getColumnCount(); i++) {
                this.jTable1.getColumnModel().getColumn(i).setPreferredWidth(220);
        }
        for (int i = 0; i < this.jTable2.getColumnCount(); i++) {
                this.jTable2.getColumnModel().getColumn(i).setPreferredWidth(220);
        }
        
    }
public void Refresh()
{
    System.out.println("app.view.panel.mobil.panelMobil.Refresh()");
//    jButton22ActionPerformed(null);
    this.leasingList.clear();
    this.PerjalananList.clear();
    app.table.Util.RefreshBank();
    List L = leasingQuery.getResultList();
    for (Object object : L) {
        blessingPUEntityManager.refresh(object);
    }
    List resultList = query.getResultList();
    for (Object object : resultList) {
        blessingPUEntityManager.refresh(object);        
    }
//    List B = bankQuery.getResultList();
//    for (Object object : B) {
//        blessingPUEntityManager.refresh(object);                
//    }
    try {
    ((app.view.FixPanel.PanelBank)ShowRoom.jPanel5).Reset();
        
    } catch (Exception e) {
    }
    this.bankList.clear();
//    this.bankList.add(null);    
    this.bankList.addAll(bankQuery.getResultList());
    this.leasingList.add(null);    
    this.leasingList.addAll(leasingQuery.getResultList());
    this.PerjalananList.add(null);    
    this.PerjalananList.addAll(query.getResultList());
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

        blessingPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();
        mobilQuery = java.beans.Beans.isDesignTime() ? null : blessingPUEntityManager.createQuery("SELECT m FROM Mobil m order by m.tangglPelunasanPembelian asc ");
        mobilList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(mobilQuery.getResultList());
        debiturQuery1 = java.beans.Beans.isDesignTime() ? null : blessingPUEntityManager.createQuery("SELECT d FROM Debitur d order by d desc");
        debiturList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(debiturQuery1.getResultList());
        bpkbQuery1 = java.beans.Beans.isDesignTime() ? null : blessingPUEntityManager.createQuery("SELECT b FROM Bpkb b order by b desc");
        bpkbList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(bpkbQuery1.getResultList());
        query = java.beans.Beans.isDesignTime() ? null : blessingPUEntityManager.createQuery("SELECT t FROM Trips t");
        PerjalananList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
        jTextField8 = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        addMobil = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel102 = new javax.swing.JLabel();
        jTextField65 = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jTextField66 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jTextField64 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel26 = new javax.swing.JLabel();
        jYearChooser2 = new com.toedter.calendar.JYearChooser();
        jLabel31 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel83 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jTextField43 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jYearChooser3 = new com.toedter.calendar.JYearChooser();
        jLabel98 = new javax.swing.JLabel();
        jDateChooser14 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton33 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        editMobil = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButton35 = new javax.swing.JButton();
        jLabel107 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jTextField27 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jTextField51 = new javax.swing.JTextField();
        jTextField52 = new javax.swing.JTextField();
        jTextField53 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jTextField58 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jTextField54 = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jTextField60 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel96 = new javax.swing.JLabel();
        jDateChooser13 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel35 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jLabel101 = new javax.swing.JLabel();
        jDateChooser16 = new com.toedter.calendar.JDateChooser();
        jLabel38 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jTextField59 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jTextField46 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jTextField45 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jTextField42 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jTextField41 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jTextField61 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jTextField62 = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        jTextField63 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jDateChooser10 = new com.toedter.calendar.JDateChooser();
        jLabel33 = new javax.swing.JLabel();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        jDateChooser8 = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jLabel32 = new javax.swing.JLabel();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        jLabel52 = new javax.swing.JLabel();
        jDateChooser9 = new com.toedter.calendar.JDateChooser();
        jLabel100 = new javax.swing.JLabel();
        jDateChooser15 = new com.toedter.calendar.JDateChooser();
        jLabel82 = new javax.swing.JLabel();
        jTextField35 = new javax.swing.JTextField();
        jButton32 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel14 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jFileChooser1 = new javax.swing.JFileChooser();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jFileChooser2 = new javax.swing.JFileChooser();
        jFileChooser3 = new javax.swing.JFileChooser();
        jFileChooser4 = new javax.swing.JFileChooser();
        jFileChooser5 = new javax.swing.JFileChooser();
        jFileChooser6 = new javax.swing.JFileChooser();
        jFileChooser7 = new javax.swing.JFileChooser();
        keuanganMobilQuery = java.beans.Beans.isDesignTime() ? null : blessingPUEntityManager.createQuery("SELECT k FROM KeuanganMobil k order by k desc");
        keuanganMobilList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(keuanganMobilQuery.getResultList());
        LeasingMobil = new javax.swing.JDialog();
        jLabel16 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel79 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel80 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        leasingQuery = java.beans.Beans.isDesignTime() ? null : blessingPUEntityManager.createQuery("SELECT l FROM Leasing l");
        leasingList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(leasingQuery.getResultList());
        jDialog1 = new javax.swing.JDialog();
        jButton19 = new javax.swing.JButton();
        inputPanel1 = new app.utils.inputPanel(app.table.MobilPemasukan.class);
        jLabel93 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jDialog2 = new javax.swing.JDialog();
        jButton20 = new javax.swing.JButton();
        inputPanel2 = new app.utils.inputPanel(app.table.MobilPengeluaran.class);
        jLabel81 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel91 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        bankQuery = java.beans.Beans.isDesignTime() ? null : blessingPUEntityManager.createQuery("SELECT b FROM Bank b");
        bankList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(bankQuery.getResultList());
        jComboBox10 = new javax.swing.JComboBox<>();
        FilterDialog = new javax.swing.JDialog();
        jLabel87 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel88 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jLabel90 = new javax.swing.JLabel();
        jDateChooser11 = new com.toedter.calendar.JDateChooser();
        jLabel89 = new javax.swing.JLabel();
        jDateChooser12 = new com.toedter.calendar.JDateChooser();
        jButton28 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jFileChooser8 = new javax.swing.JFileChooser();
        InformasiDialog = new javax.swing.JDialog();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        akunMobilList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList( new LinkedList<Akun>() );
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        simpanButton = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jComboBox12 = new javax.swing.JComboBox<>();
        jButton23 = new javax.swing.JButton();
        jTextField32 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BPKB = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jTextField30 = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jComboBox9, org.jdesktop.beansbinding.ELProperty.create("${selectedItem}"), jTextField8, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jButton21.setText("SET LEASING");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        addMobil.setTitle("Mobil Baru");
        addMobil.setMinimumSize(new java.awt.Dimension(800, 600));
        addMobil.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        addMobil.setSize(new java.awt.Dimension(1000, 700));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("DATA MOBIL"));
        jPanel6.setLayout(new java.awt.CardLayout());

        jPanel2.setLayout(new java.awt.GridLayout(0, 4, 10, 10));

        jButton1.setText("SIMPAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("RESET");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton9.setText("UPLOAD GAMBAR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9);

        jButton4.setText("TUTUP");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);

        jLabel20.setText("NOMOR POLISI AKTIF");
        jPanel2.add(jLabel20);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1);

        jLabel7.setText("PEMILIK SEBELUMNYA");
        jPanel2.add(jLabel7);
        jPanel2.add(jTextField6);

        jLabel21.setText("NOMOR BPKB AKTIF");
        jPanel2.add(jLabel21);
        jPanel2.add(jTextField2);

        jLabel36.setText("TANGGAL TERIMA BPKB");
        jPanel2.add(jLabel36);

        jDateChooser1.setDate(new java.util.Date());
        jPanel2.add(jDateChooser1);

        jLabel102.setText("POSISI BPKB");
        jPanel2.add(jLabel102);

        jTextField65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField65ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField65);

        jLabel103.setText("POSISI FAKTUR");
        jPanel2.add(jLabel103);

        jTextField66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField66ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField66);

        jLabel99.setText("NOMOR STNK AKTIF");
        jPanel2.add(jLabel99);
        jPanel2.add(jTextField64);

        jLabel22.setText("MERK");
        jPanel2.add(jLabel22);
        jPanel2.add(jTextField3);

        jLabel23.setText("TYPE");
        jPanel2.add(jLabel23);
        jPanel2.add(jTextField4);

        jLabel24.setText("JENIS");
        jPanel2.add(jLabel24);
        jPanel2.add(jTextField5);

        jLabel25.setText("TAHUN");
        jPanel2.add(jLabel25);
        jPanel2.add(jYearChooser1);

        jLabel26.setText("CC SILINDER");
        jPanel2.add(jLabel26);

        jYearChooser2.setYear(500);
        jPanel2.add(jYearChooser2);

        jLabel31.setText("WARNA");
        jPanel2.add(jLabel31);

        jComboBox9.setEditable(true);
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Abu-abu", "Biru", "Coklat", "Emas", "Hijau", "Hitam", "Kuning", "Magenta", "Merah", "Nila", "Oranye", "Perak", "Putih", "Ungu", "Violet" }));
        jPanel2.add(jComboBox9);

        jLabel27.setText("NO RANGKA");
        jPanel2.add(jLabel27);

        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField9);

        jLabel28.setText("NO MESIN");
        jPanel2.add(jLabel28);
        jPanel2.add(jTextField10);

        jLabel29.setText("BAHAN BAKAR");
        jPanel2.add(jLabel29);

        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField11);

        jLabel34.setText("TANGGAL PEMBELIAN");
        jPanel2.add(jLabel34);

        jDateChooser2.setDate(new java.util.Date());
        jPanel2.add(jDateChooser2);

        jLabel83.setText("PENJUAL");
        jPanel2.add(jLabel83);

        jTextField37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField37ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField37);

        jLabel84.setText("NOMOR HP PENJUAL");
        jPanel2.add(jLabel84);

        jTextField43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField43ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField43);

        jLabel30.setText("KETERANGAN");
        jPanel2.add(jLabel30);

        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField15);

        jLabel97.setText("LAP. PERJALANAN KE");
        jPanel2.add(jLabel97);

        jYearChooser3.setEndYear(3000);
        jYearChooser3.setMinimum(-1);
        jYearChooser3.setYear(0);
        jPanel2.add(jYearChooser3);

        jLabel98.setText("TANGGAL UNIT SAMPAI");
        jPanel2.add(jLabel98);

        jDateChooser14.setDate(new java.util.Date());
        jPanel2.add(jDateChooser14);

        jPanel6.add(jPanel2, "card4");

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jButton12.setText("KEMBALI");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/view/panel/mobil/1.PNG"))); // NOI18N
        jLabel6.setText("1");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Tampilan Depan"));
        jLabel6.setMaximumSize(new java.awt.Dimension(400, 200));
        jLabel6.setPreferredSize(new java.awt.Dimension(400, 200));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6);

        jTextField7.setText("1");
        jTextField7.setEnabled(false);
        jPanel1.add(jTextField7);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/view/panel/mobil/1.PNG"))); // NOI18N
        jLabel3.setText("2");
        jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tampilan Belakang"));
        jLabel3.setMaximumSize(new java.awt.Dimension(400, 200));
        jLabel3.setPreferredSize(new java.awt.Dimension(400, 200));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3);

        jTextField16.setText("2");
        jTextField16.setEnabled(false);
        jPanel1.add(jTextField16);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/view/panel/mobil/1.PNG"))); // NOI18N
        jLabel5.setText("3");
        jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tampilan Samping Kiri"));
        jLabel5.setMaximumSize(new java.awt.Dimension(400, 200));
        jLabel5.setPreferredSize(new java.awt.Dimension(400, 200));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5);

        jTextField13.setText("3");
        jTextField13.setEnabled(false);
        jPanel1.add(jTextField13);

        jPanel4.add(jPanel1);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        jButton33.setText("TUTUP");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton33);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/view/panel/mobil/1.PNG"))); // NOI18N
        jLabel4.setText("4");
        jLabel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tampilan Samping Kanan"));
        jLabel4.setMaximumSize(new java.awt.Dimension(400, 200));
        jLabel4.setPreferredSize(new java.awt.Dimension(400, 200));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel4);

        jTextField14.setText("4");
        jTextField14.setEnabled(false);
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField14);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/view/panel/mobil/1.PNG"))); // NOI18N
        jLabel2.setText("5");
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Interior I"));
        jLabel2.setMaximumSize(new java.awt.Dimension(400, 200));
        jLabel2.setPreferredSize(new java.awt.Dimension(400, 200));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel2);

        jTextField17.setText("5");
        jTextField17.setEnabled(false);
        jTextField17.setFocusable(false);
        jPanel3.add(jTextField17);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/app/view/panel/mobil/1.PNG"))); // NOI18N
        jLabel1.setText("6");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Interior II"));
        jLabel1.setMaximumSize(new java.awt.Dimension(400, 200));
        jLabel1.setPreferredSize(new java.awt.Dimension(400, 200));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel1);

        jTextField18.setText("6");
        jTextField18.setEnabled(false);
        jPanel3.add(jTextField18);

        jPanel4.add(jPanel3);

        jPanel6.add(jPanel4, "card4");

        addMobil.getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        editMobil.setTitle("DATA MOBIL");
        editMobil.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        editMobil.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        editMobil.getContentPane().setLayout(new java.awt.GridLayout(1, 0));
        this.editMobil.setSize(1000, 600);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("DATA MOBIL"));
        jPanel15.setLayout(new java.awt.GridLayout(0, 2));

        jPanel16.setLayout(new java.awt.GridLayout(0, 1, 0, 1));

        jButton35.setText("PRINT DATA MOBIL");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton35);

        jLabel107.setText("DEBITUR");
        jPanel16.add(jLabel107);

        jLabel55.setText("NOMOR POLISI AKTIF");
        jPanel16.add(jLabel55);

        jLabel56.setText("NOMOR BPKB");
        jPanel16.add(jLabel56);

        jLabel57.setText("MERK");
        jPanel16.add(jLabel57);

        jLabel58.setText("TYPE");
        jPanel16.add(jLabel58);

        jLabel59.setText("JENIS");
        jPanel16.add(jLabel59);

        jLabel60.setText("TAHUN");
        jPanel16.add(jLabel60);

        jLabel61.setText("CC SILINDER");
        jPanel16.add(jLabel61);

        jLabel62.setText("WARNA");
        jPanel16.add(jLabel62);

        jLabel63.setText("NO RANGKA");
        jPanel16.add(jLabel63);

        jLabel64.setText("NO MESIN");
        jPanel16.add(jLabel64);

        jLabel65.setText("BAHAN BAKAR");
        jPanel16.add(jLabel65);

        jLabel66.setText("KETERANGAN");
        jPanel16.add(jLabel66);

        jLabel42.setText("STATUS");
        jPanel16.add(jLabel42);

        jLabel37.setText("LEASING");
        jPanel16.add(jLabel37);

        jLabel104.setText("NOMINAL LEASING (IDR)");
        jPanel16.add(jLabel104);

        jLabel105.setText("TERPENUHI (IDR)");
        jPanel16.add(jLabel105);

        jLabel106.setText("SISA");
        jPanel16.add(jLabel106);

        jPanel15.add(jPanel16);

        jPanel17.setLayout(new java.awt.GridLayout(0, 1, 0, 1));

        jButton13.setText("SIMPAN");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton13);

        jTextField27.setEditable(false);
        jTextField27.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.debitur.nama}"), jTextField27, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jTextField27);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.noPolisiAktif}"), jTextField47, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextField47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField47ActionPerformed(evt);
            }
        });
        jPanel17.add(jTextField47);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.noBpkb}"), jTextField48, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jTextField48);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.merk}"), jTextField49, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jTextField49);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.type}"), jTextField50, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jTextField50);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.jenis}"), jTextField51, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jTextField51);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.tahun}"), jTextField52, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jTextField52);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.silinder}"), jTextField53, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jTextField53);

        jComboBox3.setEditable(true);
        jComboBox3.setModel(app.utils.ColorList.warna);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.warna}"), jComboBox3, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jComboBox3);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.noRangka}"), jTextField55, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jTextField55);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.noMesin}"), jTextField56, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jTextField56);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bahanBakar}"), jTextField57, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextField57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField57ActionPerformed(evt);
            }
        });
        jPanel17.add(jTextField57);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.keterangan}"), jTextField58, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jTextField58);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "READY", "OPEN", "CLOSE", "SELESAI" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.statusMobil}"), jComboBox2, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jComboBox2);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${leasingList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox4);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.listleasing.leasingLeasingId}"), jComboBox4, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jComboBox4);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.listleasing.nominal}"), jFormattedTextField6, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jFormattedTextField6);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.listleasing.TERPENUHI}"), jFormattedTextField7, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jFormattedTextField7);

        jFormattedTextField8.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.listleasing.TERPENUHI-selectedElement.listleasing.nominal}"), jFormattedTextField8, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        jPanel17.add(jFormattedTextField8);

        jPanel15.add(jPanel17);

        editMobil.getContentPane().add(jPanel15);

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("DATA MOBIL"));
        jPanel11.setLayout(new java.awt.GridLayout(0, 2, 2, 2));

        jLabel8.setText("REF MOBIL");
        jPanel11.add(jLabel8);

        jTextField19.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.mobilId}"), jTextField19, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });
        jPanel11.add(jTextField19);

        jLabel108.setText("ARSIP");
        jPanel11.add(jLabel108);

        jTextField25.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.arsip}"), jTextField25, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });
        jPanel11.add(jTextField25);

        jLabel9.setText("NO BPKB ");
        jPanel11.add(jLabel9);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.noBpkb}"), jTextField20, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jTextField20);

        jLabel10.setText("NO POLISI AKTIF");
        jPanel11.add(jLabel10);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.noPolisiAktif}"), jTextField21, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jTextField21);

        jLabel11.setText("NO POLISI LAMA");
        jPanel11.add(jLabel11);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.noPolisiLama}"), jTextField31, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jTextField31);

        jLabel12.setText("NAMA PEMILIK LAMA");
        jPanel11.add(jLabel12);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.pemilikLama}"), jTextField22, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jTextField22);

        jLabel13.setText("NAMA PEMILIK BARU");
        jPanel11.add(jLabel13);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.pemilikBaru}"), jTextField23, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });
        jPanel11.add(jTextField23);

        jLabel85.setText("PENJUAL");
        jPanel11.add(jLabel85);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.penjual}"), jTextField54, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextField54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField54ActionPerformed(evt);
            }
        });
        jPanel11.add(jTextField54);

        jLabel86.setText("NOMOR HP PENJUAL");
        jPanel11.add(jLabel86);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.no_Hp_Penjual}"), jTextField60, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jTextField60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField60ActionPerformed(evt);
            }
        });
        jPanel11.add(jTextField60);

        jLabel95.setText("LAPORAN PERJALANAN KE");
        jPanel11.add(jLabel95);

        jFormattedTextField5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.dpPertama}"), jFormattedTextField5, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jFormattedTextField5);

        jLabel18.setText("HARGA BELI");
        jPanel11.add(jLabel18);

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.hargaPembelian}"), jFormattedTextField2, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jFormattedTextField2);

        jLabel19.setText("HARGA JUAL");
        jPanel11.add(jLabel19);

        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.hargaJual}"), jFormattedTextField3, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jFormattedTextField3);

        jLabel96.setText("UNIT SAMPAI KUPANG");
        jPanel11.add(jLabel96);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.tanggalSampaiKupang}"), jDateChooser13, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jDateChooser13);

        jLabel15.setText("TANGGAL BELI/READY");
        jPanel11.add(jLabel15);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.tanggalBeli}"), jDateChooser3, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jDateChooser3);

        jLabel35.setText("TANGGAL JUAL/OPEN");
        jPanel11.add(jLabel35);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.tanggalJual}"), jDateChooser4, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jDateChooser4);

        jLabel101.setText("TANGGAL PELUNASAN/CLOSE");
        jPanel11.add(jLabel101);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.tangglPelunasanPembelian}"), jDateChooser16, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jDateChooser16);

        jLabel38.setText("TOTAL PEMASUKAN");
        jPanel11.add(jLabel38);

        jTextField33.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTextField39, org.jdesktop.beansbinding.ELProperty.create("${text}"), jTextField33, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jTextField33);

        jLabel39.setText("TOTAL PENGELUARAN");
        jPanel11.add(jLabel39);

        jTextField34.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTextField38, org.jdesktop.beansbinding.ELProperty.create("${text}"), jTextField34, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jTextField34);

        jLabel41.setText("TOTAL PROFIT");
        jPanel11.add(jLabel41);

        jTextField36.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTextField59, org.jdesktop.beansbinding.ELProperty.create("${text}"), jTextField36, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel11.add(jTextField36);

        jScrollPane7.setViewportView(jPanel11);

        jTabbedPane2.addTab("DATA", jScrollPane7);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "KEUANGAN MOBIL"));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jTable4.setDefaultEditor(Date.class, new JDateChooserCellEditor());
        jTable4.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
        jTable4.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable4.setDefaultEditor(java.math.BigInteger.class, new app.utils.TablePopupEditor());
        jTable4.setDefaultEditor(app.table.Bank.class, new javax.swing.DefaultCellEditor(jComboBox8));
        jTable4.setDefaultEditor(app.table.Trips.class, new javax.swing.DefaultCellEditor(jComboBox7));
        jTable4.setAutoCreateRowSorter(true);
        jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable4.setRowHeight(22);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, keuanganMobilList, jTable4);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("Id");
        columnBinding.setColumnClass(Long.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggal}"));
        columnBinding.setColumnName("Tanggal");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemasukan}"));
        columnBinding.setColumnName("Pemasukan");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pengeluaran");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${transaksi.bankId}"));
        columnBinding.setColumnName("Sumber");
        columnBinding.setColumnClass(app.table.Bank.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tripsTripsId}"));
        columnBinding.setColumnName("Perjalanan");
        columnBinding.setColumnClass(app.table.Trips.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jTable4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable4PropertyChange(evt);
            }
        });
        jScrollPane5.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(5).setMinWidth(200);
            jTable4.getColumnModel().getColumn(5).setPreferredWidth(300);
            jTable4.getColumnModel().getColumn(6).setPreferredWidth(200);
        }

        jPanel12.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        jPanel18.setLayout(new java.awt.GridLayout(0, 2));

        jLabel44.setText("TOTAL PEMASUKAN");
        jPanel18.add(jLabel44);

        jTextField39.setEditable(false);
        jTextField39.setText("DEBIT");
        jPanel18.add(jTextField39);

        jLabel43.setText("TOTAL PENGELUARAN");
        jPanel18.add(jLabel43);

        jTextField38.setEditable(false);
        jPanel18.add(jTextField38);

        jLabel67.setText("TOTAL PROFIT");
        jPanel18.add(jLabel67);

        jTextField59.setEditable(false);
        jTextField59.setText("PROFIT");
        jPanel18.add(jTextField59);

        jButton18.setText("PEMASUKAN");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton18);

        jButton10.setText("PENGELUARAN");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton10);

        jButton16.setText("SIMPAN");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton16);

        jButton8.setText("HAPUS");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable4, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jButton8, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton8);

        jPanel12.add(jPanel18, java.awt.BorderLayout.PAGE_START);

        jTabbedPane2.addTab("KEUANGAN", jPanel12);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("DATA MOBIL"));
        jPanel13.setLayout(new java.awt.GridLayout(2, 0));

        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        jLabel46.setText("FILE GAMBAR KTP");
        jPanel10.add(jLabel46);

        jLabel50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel50.setMinimumSize(new java.awt.Dimension(300, 150));
        jLabel50.setPreferredSize(new java.awt.Dimension(300, 150));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.debitur.gambar1}"), jLabel50, org.jdesktop.beansbinding.BeanProperty.create("icon"));
        bindingGroup.addBinding(binding);

        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel50MouseClicked(evt);
            }
        });
        jPanel10.add(jLabel50);

        jPanel13.add(jPanel10);

        jPanel19.setLayout(new java.awt.GridLayout(0, 2));

        jLabel53.setText("NAMA");
        jPanel19.add(jLabel53);

        jTextField46.setMinimumSize(new java.awt.Dimension(400, 20));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.debitur.nama}"), jTextField46, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel19.add(jTextField46);

        jLabel47.setText("ALAMAT");
        jPanel19.add(jLabel47);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.debitur.alamat}"), jTextField45, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel19.add(jTextField45);

        jLabel49.setText("NOMOR IDENTITAS");
        jPanel19.add(jLabel49);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.debitur.noKtp}"), jTextField42, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel19.add(jTextField42);

        jLabel48.setText("NOMOR HP");
        jPanel19.add(jLabel48);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.debitur.noHp}"), jTextField41, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel19.add(jTextField41);

        jLabel68.setText("NAMA ORANG KE-2");
        jPanel19.add(jLabel68);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.debitur.bank}"), jTextField61, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel19.add(jTextField61);

        jLabel51.setText("NOMOR HP KE-2");
        jPanel19.add(jLabel51);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.debitur.pembayaran}"), jTextField44, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel19.add(jTextField44);

        jLabel45.setText("FOTO KTP");
        jPanel19.add(jLabel45);

        jTextField40.setEditable(false);
        jTextField40.setMinimumSize(new java.awt.Dimension(400, 20));
        jTextField40.setPreferredSize(new java.awt.Dimension(300, 25));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.debitur.scan}"), jTextField40, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel19.add(jTextField40);

        jButton5.setText("SIMPAN");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton5);

        jPanel13.add(jPanel19);

        jTabbedPane2.addTab("DEBITUR", jPanel13);

        jPanel21.setLayout(new java.awt.GridLayout(0, 2));

        jLabel70.setText("NO BPKB AKTIF");
        jPanel21.add(jLabel70);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.noBpkb}"), jTextField24, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jTextField24);

        jLabel92.setText("NO BPKB LAMA");
        jPanel21.add(jLabel92);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.status}"), jTextField62, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jTextField62);

        jLabel94.setText("NO STNK");
        jPanel21.add(jLabel94);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.stnk}"), jTextField63, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jTextField63);

        jLabel71.setText("ATAS NAMA BPKB ");
        jPanel21.add(jLabel71);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.anBpkb}"), jTextField26, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jTextField26);

        jLabel77.setText("POSISI BPKB ");
        jPanel21.add(jLabel77);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.posisi}"), jTextField28, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jTextField28);

        jLabel78.setText("POSISI FAKTUR BPKB ");
        jPanel21.add(jLabel78);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.noPolisiAktif}"), jTextField29, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jTextField29);

        jLabel69.setText("TANGGAL TERIMA");
        jPanel21.add(jLabel69);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.tglTerima}"), jDateChooser10, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jDateChooser10);

        jLabel33.setText("TANGGAL CB");
        jPanel21.add(jLabel33);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.tglCb}"), jDateChooser7, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jDateChooser7);

        jLabel40.setText("TANGGAL KEMBALI CB");
        jPanel21.add(jLabel40);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.tglKembaliCb}"), jDateChooser8, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jDateChooser8);

        jLabel14.setText("TANGGAL BBN");
        jPanel21.add(jLabel14);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.tglBbn}"), jDateChooser5, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jDateChooser5);

        jLabel32.setText("TANGGAL KEMBALI BBN");
        jPanel21.add(jLabel32);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.tglKembaliBbn}"), jDateChooser6, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jDateChooser6);

        jLabel52.setText("TANGGAL LEASING");
        jPanel21.add(jLabel52);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.tglLeasing}"), jDateChooser9, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jDateChooser9);

        jLabel100.setText("TANGGAL EXP PAJAK");
        jPanel21.add(jLabel100);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.tanggalExp}"), jDateChooser15, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jDateChooser15);

        jLabel82.setText("KETRANGAN");
        jPanel21.add(jLabel82);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bpkb.ket}"), jTextField35, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jPanel21.add(jTextField35);

        jButton32.setText("SIMPAN");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton32);

        jTabbedPane2.addTab("BPKB", jPanel21);

        jScrollPane6.setPreferredSize(new java.awt.Dimension(1862, 170));

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true), "GAMBAR MOBIL"));
        jPanel14.setLayout(new java.awt.GridLayout(2, 2, 10, 10));

        jLabel54.setText("1");
        jLabel54.setPreferredSize(new java.awt.Dimension(300, 120));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.gambar1}"), jLabel54, org.jdesktop.beansbinding.BeanProperty.create("icon"));
        bindingGroup.addBinding(binding);

        jLabel54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel54MouseClicked(evt);
            }
        });
        jPanel14.add(jLabel54);

        jLabel73.setText("3");
        jLabel73.setPreferredSize(new java.awt.Dimension(300, 120));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.gambar3}"), jLabel73, org.jdesktop.beansbinding.BeanProperty.create("icon"));
        bindingGroup.addBinding(binding);

        jLabel73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel73MouseClicked(evt);
            }
        });
        jPanel14.add(jLabel73);

        jLabel75.setText("5");
        jLabel75.setPreferredSize(new java.awt.Dimension(300, 120));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.gambar5}"), jLabel75, org.jdesktop.beansbinding.BeanProperty.create("icon"));
        bindingGroup.addBinding(binding);

        jLabel75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel75MouseClicked(evt);
            }
        });
        jPanel14.add(jLabel75);

        jLabel72.setText("2");
        jLabel72.setPreferredSize(new java.awt.Dimension(300, 120));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.gambar2}"), jLabel72, org.jdesktop.beansbinding.BeanProperty.create("icon"));
        bindingGroup.addBinding(binding);

        jLabel72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel72MouseClicked(evt);
            }
        });
        jPanel14.add(jLabel72);

        jLabel74.setText("4");
        jLabel74.setPreferredSize(new java.awt.Dimension(300, 120));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.gambar4}"), jLabel74, org.jdesktop.beansbinding.BeanProperty.create("icon"));
        bindingGroup.addBinding(binding);

        jLabel74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel74MouseClicked(evt);
            }
        });
        jPanel14.add(jLabel74);

        jLabel76.setText("6");
        jLabel76.setPreferredSize(new java.awt.Dimension(300, 120));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.gambar6}"), jLabel76, org.jdesktop.beansbinding.BeanProperty.create("icon"));
        bindingGroup.addBinding(binding);

        jLabel76.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel76MouseClicked(evt);
            }
        });
        jPanel14.add(jLabel76);

        jScrollPane6.setViewportView(jPanel14);

        jTabbedPane2.addTab("GAMBAR", jScrollPane6);

        editMobil.getContentPane().add(jTabbedPane2);

        jFileChooser1.setAcceptAllFileFilterUsed(false);
        jFileChooser1.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        jFileChooser1.setApproveButtonText("Open");
        jFileChooser1.setApproveButtonToolTipText("");
        jFileChooser1.setDialogTitle("SCAN KTP");
        jFileChooser1.setFileFilter(new FileNameExtensionFilter(
            "Image files(jpg,png,bmp)", ImageIO.getReaderFileSuffixes()));
    jFileChooser1.setDragEnabled(true);
    jFileChooser1.setRequestFocusEnabled(false);
    jFileChooser1.setSelectedFiles(null);
    jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFileChooser1ActionPerformed(evt);
        }
    });

    jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    eLProperty = org.jdesktop.beansbinding.ELProperty.create("${perjalananList}");
    jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox7);
    bindingGroup.addBinding(jComboBoxBinding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable4, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jComboBox7, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bankList}");
    jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox8);
    bindingGroup.addBinding(jComboBoxBinding);
    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable4, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jComboBox8, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    jFileChooser2.setAcceptAllFileFilterUsed(false);
    jFileChooser2.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    jFileChooser2.setApproveButtonText("Open");
    jFileChooser2.setApproveButtonToolTipText("");
    jFileChooser2.setDialogTitle("SCAN KTP");
    jFileChooser2.setFileFilter(new FileNameExtensionFilter(
        "Image files(jpg,png,bmp)", ImageIO.getReaderFileSuffixes()));
jFileChooser2.setDragEnabled(true);
jFileChooser2.setRequestFocusEnabled(false);
jFileChooser2.setSelectedFiles(null);
jFileChooser2.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jFileChooser2ActionPerformed(evt);
    }
    });

    jFileChooser3.setAcceptAllFileFilterUsed(false);
    jFileChooser3.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    jFileChooser3.setApproveButtonText("Open");
    jFileChooser3.setApproveButtonToolTipText("");
    jFileChooser3.setDialogTitle("SCAN KTP");
    jFileChooser3.setFileFilter(new FileNameExtensionFilter(
        "Image files(jpg,png,bmp)", ImageIO.getReaderFileSuffixes()));
jFileChooser3.setDragEnabled(true);
jFileChooser3.setRequestFocusEnabled(false);
jFileChooser3.setSelectedFiles(null);
jFileChooser3.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jFileChooser3ActionPerformed(evt);
    }
    });

    jFileChooser4.setAcceptAllFileFilterUsed(false);
    jFileChooser4.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    jFileChooser4.setApproveButtonText("Open");
    jFileChooser4.setApproveButtonToolTipText("");
    jFileChooser4.setDialogTitle("SCAN KTP");
    jFileChooser4.setFileFilter(new FileNameExtensionFilter(
        "Image files(jpg,png,bmp)", ImageIO.getReaderFileSuffixes()));
jFileChooser4.setDragEnabled(true);
jFileChooser4.setRequestFocusEnabled(false);
jFileChooser4.setSelectedFiles(null);
jFileChooser4.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jFileChooser4ActionPerformed(evt);
    }
    });

    jFileChooser5.setAcceptAllFileFilterUsed(false);
    jFileChooser5.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    jFileChooser5.setApproveButtonText("Open");
    jFileChooser5.setApproveButtonToolTipText("");
    jFileChooser5.setDialogTitle("SCAN KTP");
    jFileChooser5.setFileFilter(new FileNameExtensionFilter(
        "Image files(jpg,png,bmp)", ImageIO.getReaderFileSuffixes()));
jFileChooser5.setDragEnabled(true);
jFileChooser5.setRequestFocusEnabled(false);
jFileChooser5.setSelectedFiles(null);
jFileChooser5.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jFileChooser5ActionPerformed(evt);
    }
    });

    jFileChooser6.setAcceptAllFileFilterUsed(false);
    jFileChooser6.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    jFileChooser6.setApproveButtonText("Open");
    jFileChooser6.setApproveButtonToolTipText("");
    jFileChooser6.setDialogTitle("SCAN KTP");
    jFileChooser6.setFileFilter(new FileNameExtensionFilter(
        "Image files(jpg,png,bmp)", ImageIO.getReaderFileSuffixes()));
jFileChooser6.setDragEnabled(true);
jFileChooser6.setRequestFocusEnabled(false);
jFileChooser6.setSelectedFiles(null);
jFileChooser6.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jFileChooser6ActionPerformed(evt);
    }
    });

    jFileChooser7.setAcceptAllFileFilterUsed(false);
    jFileChooser7.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
    jFileChooser7.setApproveButtonText("Open");
    jFileChooser7.setApproveButtonToolTipText("");
    jFileChooser7.setDialogTitle("SCAN KTP");
    jFileChooser7.setFileFilter(new FileNameExtensionFilter(
        "Image files(jpg,png,bmp)", ImageIO.getReaderFileSuffixes()));
jFileChooser7.setDragEnabled(true);
jFileChooser7.setRequestFocusEnabled(false);
jFileChooser7.setSelectedFiles(null);
jFileChooser7.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        jFileChooser7ActionPerformed(evt);
    }
    });

    LeasingMobil.setTitle("Leasing Set");
    LeasingMobil.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
    LeasingMobil.setType(java.awt.Window.Type.POPUP);
    LeasingMobil.getContentPane().setLayout(new java.awt.GridLayout(0, 1));

    jLabel16.setText("NOMINAL LEASING (IDR)");
    LeasingMobil.getContentPane().add(jLabel16);

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.listleasing.nominal}"), jFormattedTextField1, org.jdesktop.beansbinding.BeanProperty.create("value"));
    bindingGroup.addBinding(binding);

    LeasingMobil.getContentPane().add(jFormattedTextField1);

    jLabel79.setText("TERPENUHI (IDR)");
    LeasingMobil.getContentPane().add(jLabel79);

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.listleasing.TERPENUHI}"), jFormattedTextField4, org.jdesktop.beansbinding.BeanProperty.create("value"));
    bindingGroup.addBinding(binding);

    LeasingMobil.getContentPane().add(jFormattedTextField4);

    jLabel80.setText("LEASING");
    LeasingMobil.getContentPane().add(jLabel80);

    jButton17.setText("SET LEASING");
    jButton17.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton17ActionPerformed(evt);
        }
    });
    LeasingMobil.getContentPane().add(jButton17);

    jDialog1.setTitle("Pemasukan Mobil");
    jDialog1.setModal(true);
    jDialog1.setSize(new java.awt.Dimension(400, 400));
    jDialog1.setType(java.awt.Window.Type.POPUP);

    jButton19.setText("Simpan");
    jButton19.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton19ActionPerformed(evt);
        }
    });
    jDialog1.getContentPane().add(jButton19, java.awt.BorderLayout.PAGE_START);

    jLabel93.setText("Bank");
    inputPanel1.add(jLabel93);

    jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bankList}");
    jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox5);
    bindingGroup.addBinding(jComboBoxBinding);

    inputPanel1.add(jComboBox5);

    jDialog1.getContentPane().add(inputPanel1, java.awt.BorderLayout.CENTER);

    jDialog2.setTitle("Pengeluaran Mobil");
    jDialog2.setModal(true);
    jDialog2.setSize(new java.awt.Dimension(400, 400));
    jDialog2.setType(java.awt.Window.Type.POPUP);

    jButton20.setText("Simpan");
    jButton20.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton20ActionPerformed(evt);
        }
    });
    jDialog2.getContentPane().add(jButton20, java.awt.BorderLayout.PAGE_START);

    jLabel81.setText("Bank");
    inputPanel2.add(jLabel81);

    jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bankList}");
    jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox6);
    bindingGroup.addBinding(jComboBoxBinding);

    inputPanel2.add(jComboBox6);

    jLabel91.setText("Laporan Perjalanan");
    inputPanel2.add(jLabel91);

    eLProperty = org.jdesktop.beansbinding.ELProperty.create("${perjalananList}");
    jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox13);
    bindingGroup.addBinding(jComboBoxBinding);

    inputPanel2.add(jComboBox13);

    jDialog2.getContentPane().add(inputPanel2, java.awt.BorderLayout.CENTER);

    jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "READY", "OPEN", "CLOSE", "SELESAI" }));

    FilterDialog.getContentPane().setLayout(new java.awt.GridLayout(0, 2));
    FilterDialog.setSize(400, 500);

    jLabel87.setText("STATUS MOBIL");
    FilterDialog.getContentPane().add(jLabel87);

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SEMUANYA", "READY", "OPEN", "CLOSE", "SELESAI" }));
    FilterDialog.getContentPane().add(jComboBox1);

    jLabel88.setText("PILIHAN");
    FilterDialog.getContentPane().add(jLabel88);

    jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TANGGAL MOBIL BELI", "TANGGAL MOBIL JUAL", "TANGGAL MOBIL SAMPAI", "TANGGAL MOBIL LUNAS" }));
    FilterDialog.getContentPane().add(jComboBox11);

    jLabel90.setText("TANGGAL AWAL");
    FilterDialog.getContentPane().add(jLabel90);

    jDateChooser11.setDate(new Date());
    jDateChooser11.setDateFormatString("dd-MM-yyyy");
    FilterDialog.getContentPane().add(jDateChooser11);

    jLabel89.setText("TANGGAL AKHIR");
    FilterDialog.getContentPane().add(jLabel89);

    jDateChooser12.setDate(new Date());
    jDateChooser12.setDateFormatString("dd-MM-yyyy");
    FilterDialog.getContentPane().add(jDateChooser12);

    jButton28.setText("FILTER");
    jButton28.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton28ActionPerformed(evt);
        }
    });
    FilterDialog.getContentPane().add(jButton28);

    jButton30.setText("KEMBALI");
    jButton30.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton30ActionPerformed(evt);
        }
    });
    FilterDialog.getContentPane().add(jButton30);

    jFileChooser8.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);

    InformasiDialog.setSize(900, 500);
    InformasiDialog.getContentPane().setLayout(new java.awt.GridLayout(2, 0));

    jTable6.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }
    ));
    jScrollPane8.setViewportView(jTable6);

    InformasiDialog.getContentPane().add(jScrollPane8);

    jTable5.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
    jTable5.setAutoCreateRowSorter(true);

    jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, akunMobilList, jTable5);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomor}"));
    columnBinding.setColumnName("Arsip");
    columnBinding.setColumnClass(Integer.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${akun}"));
    columnBinding.setColumnName("Akun");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggalClose}"));
    columnBinding.setColumnName("Tanggal Close");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemasukan}"));
    columnBinding.setColumnName("Pemasukan");
    columnBinding.setColumnClass(java.math.BigInteger.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
    columnBinding.setColumnName("Pengeluaran");
    columnBinding.setColumnClass(java.math.BigInteger.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${profit}"));
    columnBinding.setColumnName("Profit");
    columnBinding.setColumnClass(java.math.BigInteger.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
    columnBinding.setColumnName("Keterangan");
    columnBinding.setColumnClass(String.class);
    bindingGroup.addBinding(jTableBinding);
    jTableBinding.bind();
    jScrollPane4.setViewportView(jTable5);
    if (jTable5.getColumnModel().getColumnCount() > 0) {
        jTable5.getColumnModel().getColumn(0).setMaxWidth(50);
    }

    InformasiDialog.getContentPane().add(jScrollPane4);

    setLayout(new java.awt.BorderLayout());

    jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

    jPanel5.setLayout(new java.awt.BorderLayout());

    jButton11.setText("LIHAT MOBIL");

    binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jButton11, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
    bindingGroup.addBinding(binding);

    jButton11.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton11ActionPerformed(evt);
        }
    });
    jPanel7.add(jButton11);

    jButton3.setText("TAMBAH");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });
    jPanel7.add(jButton3);

    simpanButton.setText("SIMPAN");
    simpanButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            simpanButtonActionPerformed(evt);
        }
    });
    jPanel7.add(simpanButton);

    jButton6.setText("HAPUS");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton6ActionPerformed(evt);
        }
    });
    jPanel7.add(jButton6);

    jButton22.setText("REFRESH/ TAMPILKAN SEMUA");
    jButton22.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton22ActionPerformed(evt);
        }
    });
    jPanel7.add(jButton22);

    jButton14.setText("FILTER TANGGAL");
    jButton14.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton14ActionPerformed(evt);
        }
    });
    jPanel7.add(jButton14);

    jButton29.setText("FILTER STATUS");
    jButton29.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton29ActionPerformed(evt);
        }
    });
    jPanel7.add(jButton29);

    jComboBox12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "READY", "OPEN", "CLOSE", "SELESAI" }));
    jPanel7.add(jComboBox12);

    jButton23.setText("CARI");
    jButton23.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton23ActionPerformed(evt);
        }
    });
    jPanel7.add(jButton23);

    jTextField32.setPreferredSize(new java.awt.Dimension(150, 30));
    jPanel7.add(jTextField32);

    jButton31.setText("PRINT DATA");
    jButton31.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton31ActionPerformed(evt);
        }
    });
    jPanel7.add(jButton31);

    jButton34.setText("INFORMASI MOBIL");
    jButton34.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton34ActionPerformed(evt);
        }
    });
    jPanel7.add(jButton34);

    jPanel5.add(jPanel7, java.awt.BorderLayout.NORTH);

    jTable1.setDefaultEditor(Date.class, new JDateChooserCellEditor());
    //jTable1.setDefaultEditor(long.class, new app.utils.IntegerEditor(0, 1000000000));
    jTable1.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
    jTable1.setDefaultEditor(long.class, new app.utils.TablePopupEditor());
    jTable1.setAutoCreateRowSorter(true);
    jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jTable1.setRowHeight(24);

    eLProperty = org.jdesktop.beansbinding.ELProperty.create("${mobilList}");
    jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable1);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomor}"));
    columnBinding.setColumnName("No");
    columnBinding.setColumnClass(Integer.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${mobilId}"));
    columnBinding.setColumnName("Ref");
    columnBinding.setColumnClass(Integer.class);
    columnBinding.setEditable(false);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${merk}"));
    columnBinding.setColumnName("Merk");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${type}"));
    columnBinding.setColumnName("Type");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tahun}"));
    columnBinding.setColumnName("Tahun");
    columnBinding.setColumnClass(Integer.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${warna}"));
    columnBinding.setColumnName("Warna");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noPolisiAktif}"));
    columnBinding.setColumnName("No Polisi Aktif");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noPolisiLama}"));
    columnBinding.setColumnName("No Polisi Lama");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${debitur.nama}"));
    columnBinding.setColumnName("Debitur");
    columnBinding.setColumnClass(String.class);
    columnBinding.setEditable(false);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${statusMobil}"));
    columnBinding.setColumnName("Status");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemilikBaru}"));
    columnBinding.setColumnName("Pemilik Baru");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemilikLama}"));
    columnBinding.setColumnName("Pemilik Lama");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jenis}"));
    columnBinding.setColumnName("Jenis");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${silinder}"));
    columnBinding.setColumnName("Silinder");
    columnBinding.setColumnClass(Integer.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noRangka}"));
    columnBinding.setColumnName("No Rangka");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noMesin}"));
    columnBinding.setColumnName("No Mesin");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bahanBakar}"));
    columnBinding.setColumnName("Bahan Bakar");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bpkb.noBpkb}"));
    columnBinding.setColumnName("Bpkb");
    columnBinding.setColumnClass(String.class);
    columnBinding.setEditable(false);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hargaPembelian}"));
    columnBinding.setColumnName("Harga Beli");
    columnBinding.setColumnClass(Long.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hargaJual}"));
    columnBinding.setColumnName("Harga Jual");
    columnBinding.setColumnClass(Long.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggalBeli}"));
    columnBinding.setColumnName("Tanggal Beli");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggalJual}"));
    columnBinding.setColumnName("Tanggal Jual");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggalSampaiKupang}"));
    columnBinding.setColumnName("Tanggal Mobil Sampai");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tangglPelunasanPembelian}"));
    columnBinding.setColumnName("Tanggal Pelunasan");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${penjual}"));
    columnBinding.setColumnName("Penjual");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${no_Hp_Penjual}"));
    columnBinding.setColumnName("NO HP Penjual");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dpPertama}"));
    columnBinding.setColumnName("Lap. Perjalanan");
    columnBinding.setColumnClass(Long.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
    columnBinding.setColumnName("Keterangan");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${arsip}"));
    columnBinding.setColumnName("Arsip");
    columnBinding.setColumnClass(Integer.class);
    bindingGroup.addBinding(jTableBinding);
    jTableBinding.bind();binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${hapus}"), jTable1, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
    bindingGroup.addBinding(binding);

    jScrollPane1.setViewportView(jTable1);
    if (jTable1.getColumnModel().getColumnCount() > 0) {
        jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
        jTable1.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(jComboBox10)
        );
    }

    jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

    jTabbedPane1.addTab("MOBIL", jPanel5);

    BPKB.setLayout(new java.awt.BorderLayout());

    jTable2.setAutoCreateRowSorter(true);
    jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jTable2.setDefaultEditor(Date.class, new JDateChooserCellEditor());
    jTable2.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
    jTable2.setRowHeight(24);

    eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bpkbList1}");
    jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable2);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${mobil}"));
    columnBinding.setColumnName("No Polisi Aktif");
    columnBinding.setColumnClass(app.table.Mobil.class);
    columnBinding.setEditable(false);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bpkbId}"));
    columnBinding.setColumnName("Ref");
    columnBinding.setColumnClass(Integer.class);
    columnBinding.setEditable(false);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noBpkb}"));
    columnBinding.setColumnName("No BPKB Aktif");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${status}"));
    columnBinding.setColumnName("No BPKB Lama");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${anBpkb}"));
    columnBinding.setColumnName("An Bpkb");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglTerima}"));
    columnBinding.setColumnName("Tgl Terima");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglCb}"));
    columnBinding.setColumnName("Tgl Cb");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglKembaliCb}"));
    columnBinding.setColumnName("Tgl Kembali Cb");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglBbn}"));
    columnBinding.setColumnName("Tgl Bbn");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglKembaliBbn}"));
    columnBinding.setColumnName("Tgl Kembali Bbn");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglLeasing}"));
    columnBinding.setColumnName("Tgl Leasing");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggalExp}"));
    columnBinding.setColumnName("TGL EXP");
    columnBinding.setColumnClass(java.util.Date.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${posisi}"));
    columnBinding.setColumnName("Posisi BPKB");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noPolisiAktif}"));
    columnBinding.setColumnName("Posisi Faktur");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${stnk}"));
    columnBinding.setColumnName("No STNK");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${ket}"));
    columnBinding.setColumnName("Keterangan");
    columnBinding.setColumnClass(String.class);
    bindingGroup.addBinding(jTableBinding);
    jTableBinding.bind();
    jScrollPane2.setViewportView(jTable2);
    if (jTable2.getColumnModel().getColumnCount() > 0) {
        jTable2.getColumnModel().getColumn(0).setMinWidth(200);
    }

    BPKB.add(jScrollPane2, java.awt.BorderLayout.CENTER);

    jButton7.setText("SIMPAN");
    jButton7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton7ActionPerformed(evt);
        }
    });
    jPanel8.add(jButton7);

    jButton24.setText("CARI");
    jButton24.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton24ActionPerformed(evt);
        }
    });
    jPanel8.add(jButton24);

    jTextField12.setPreferredSize(new java.awt.Dimension(200, 40));
    jPanel8.add(jTextField12);

    jButton27.setText("TAMPILKAN SEMUA");
    jButton27.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton27ActionPerformed(evt);
        }
    });
    jPanel8.add(jButton27);

    BPKB.add(jPanel8, java.awt.BorderLayout.PAGE_START);

    jTabbedPane1.addTab("BPKB", BPKB);

    jPanel9.setLayout(new java.awt.BorderLayout());

    jTable3.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
    jTable3.setAutoCreateRowSorter(true);
    jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    jTable3.setRowHeight(24);

    eLProperty = org.jdesktop.beansbinding.ELProperty.create("${debiturList1}");
    jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable3);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${mobil}"));
    columnBinding.setColumnName("Mobil Ref");
    columnBinding.setColumnClass(app.table.Mobil.class);
    columnBinding.setEditable(false);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${debiturId}"));
    columnBinding.setColumnName("Ref");
    columnBinding.setColumnClass(Integer.class);
    columnBinding.setEditable(false);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nama}"));
    columnBinding.setColumnName("Nama");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${alamat}"));
    columnBinding.setColumnName("Alamat");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noHp}"));
    columnBinding.setColumnName("No Hp");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noKtp}"));
    columnBinding.setColumnName("No Ktp");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bank}"));
    columnBinding.setColumnName("Nama Orang ke-2");
    columnBinding.setColumnClass(String.class);
    columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pembayaran}"));
    columnBinding.setColumnName("No Hp 2");
    columnBinding.setColumnClass(String.class);
    bindingGroup.addBinding(jTableBinding);
    jTableBinding.bind();
    jScrollPane3.setViewportView(jTable3);
    if (jTable3.getColumnModel().getColumnCount() > 0) {
        jTable3.getColumnModel().getColumn(0).setMinWidth(175);
    }

    jPanel9.add(jScrollPane3, java.awt.BorderLayout.CENTER);

    jButton15.setText("SIMPAN");
    jButton15.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton15ActionPerformed(evt);
        }
    });
    jPanel20.add(jButton15);

    jButton25.setText("CARI DEBITUR");
    jButton25.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton25ActionPerformed(evt);
        }
    });
    jPanel20.add(jButton25);

    jTextField30.setPreferredSize(new java.awt.Dimension(200, 40));
    jPanel20.add(jTextField30);

    jButton26.setText("TAMPILKAN SEMUA");
    jButton26.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton26ActionPerformed(evt);
        }
    });
    jPanel20.add(jButton26);

    jPanel9.add(jPanel20, java.awt.BorderLayout.PAGE_START);

    jTabbedPane1.addTab("DEBITUR", jPanel9);

    add(jTabbedPane1, java.awt.BorderLayout.CENTER);

    bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    public List<Trips> getPerjalananList() {
        return PerjalananList;
    }

    public void setPerjalananList(List<Trips> PerjalananList) {
        this.PerjalananList = PerjalananList;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.addMobil.hide();
        System.out.println("app.view.panel.mobil.panelMobil.jButton1ActionPerformed()");
        //data baru
        Mobil baru = new Mobil();
        Bpkb bpkb = new Bpkb();
        Debitur debitur = new Debitur();
//        KeuanganMobil dp = new KeuanganMobil();
        //isi data baru
        baru.setNoPolisiAktif(this.jTextField1.getText());
        baru.setPemilikLama(this.jTextField6.getText());
        baru.setDpPertama(jYearChooser3.getYear());
        baru.setTanggalSampaiKupang(jDateChooser14.getDate());
        bpkb.setNoBpkb(this.jTextField2.getText());
        bpkb.setStnk(this.jTextField64.getText());
        bpkb.setTglTerima(this.jDateChooser1.getDate());
        bpkb.setPosisi(this.jTextField65.getText());
        bpkb.setNoPolisiAktif(this.jTextField66.getText());
//        bpkb.setNoPolisiAktif(baru.getNoPolisiAktif());
        baru.setMerk(this.jTextField3.getText());
        baru.setType(this.jTextField4.getText());
        baru.setJenis(this.jTextField5.getText());
        baru.setTahun(this.jYearChooser1.getYear());
        baru.setSilinder(this.jYearChooser2.getYear());
        baru.setWarna(this.jTextField8.getText());
        baru.setNoRangka(this.jTextField9.getText());
        baru.setNoMesin(this.jTextField10.getText());
        baru.setBahanBakar(this.jTextField11.getText());        
        baru.setPenjual(this.jTextField37.getText());
        baru.setNo_Hp_Penjual(this.jTextField43.getText());
        baru.setHargaPembelian(0);
//        baru.setHargaPembelian((Integer) (int) (long) this.jFormattedTextField1.getValue());
//        baru.setDpPertama((Integer) (int) (long) this.jFormattedTextField1.getValue());
        baru.setTanggalBeli(this.jDateChooser2.getDate());     
        baru.setKeterangan(this.jTextField15.getText());
        baru.setStatusMobil("READY");
//        Trips t = (Trips )this.jComboBox7.getSelectedItem();
//        if (t.getPerjalananke() == -1) {
//               javax.swing.JOptionPane.showMessageDialog(null, "Perjalanan tidak di set");                            
//        }
//        else
//        { 
//            app.table.Perjalanan p = new app.table.Perjalanan();
//            String pakai = this.jFormattedTextField1.getValue().toString();
//            BigInteger b = new BigInteger(pakai);
//            p.setPakai( b);
//            p.setTripsTripsId(t);
//            baru.setPerjalanan(p);
//            javax.swing.JOptionPane.showMessageDialog(null, "Perjalanan di set "+ t
//                    +"\nYang di pakai = "+b);                            
//        }
//        dp.setKredit((long) this.jFormattedTextField1.getValue());
//        dp.setTanggal(baru.getTanggalBeli());
//        dp.setKeterangan("Dp Pertama");
        //relasikan data
        baru.setBpkb(bpkb);
        baru.setDebitur(debitur);        
        BagiLaba bagiLaba = new BagiLaba();
        bagiLaba.setM(baru);
        this.persist(baru);
        this.persist(bagiLaba);
        this.debiturList1.add(debitur);
        this.bpkbList1.add(bpkb);
        this.mobilList.add(0,baru);        
        this.addMobil.hide();
//        baru.setKeuanganMobils(dp);
        System.out.println("Trying Save image");
        try {
//        if (!gambar1.exists())                 
//        FileUtils.copyURLToFile();
        baru.setTampilanDepan(pindahGambar(gambar1, baru.getMobilId()+"depan").getCanonicalPath());
//        if (!gambar2.exists())                 
        baru.setTampilanSmpKiri(pindahGambar(gambar2, baru.getMobilId()+"kiri").getCanonicalPath());
//        if (!gambar3.exists())                 
        baru.setTampilanSmpKanan(pindahGambar(gambar3, baru.getMobilId()+"kanan").getCanonicalPath());
//        if (!gambar4.exists())                 
        baru.setTampilanBlkg(pindahGambar(gambar4, baru.getMobilId()+"belakang").getCanonicalPath());
//        if (!gambar5.exists())                 
        baru.setInteriorI(pindahGambar(gambar5, baru.getMobilId()+"in1").getCanonicalPath());
//        if (!gambar6.exists())                 
        baru.setInteriorIi(pindahGambar(gambar6, baru.getMobilId()+"in2").getCanonicalPath());
        System.out.println("Trying Save image = done");
        } catch (IOException ex) {
            this.jButton22ActionPerformed(evt);
            System.out.println("Trying Save image = failed");
                    javax.swing.JOptionPane
                            .showMessageDialog(null,  "Error Gambar atau gambar kosong\nTekan Tombol 'Refresh'");
            //Logger.getLogger(panelMobil.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {

        }
        this.blessingPUEntityManager.merge(baru);
        if (!this.blessingPUEntityManager.getTransaction().isActive()) 
                this.blessingPUEntityManager.getTransaction().begin();
        this.blessingPUEntityManager.getTransaction().commit();

    }//GEN-LAST:event_jButton1ActionPerformed
    File pindahGambar(File source,String gmb) throws IOException
    {
        
        File destination=new File("C:/Users/blessing/sd/"+gmb+".jpg");
        if(!destination.exists())
                destination.createNewFile();
        System.out.println(source.toPath());
        System.out.println(destination.toPath());
        FileChannel sumber = null;
        FileChannel target = null;
        try {
            sumber = new java.io.RandomAccessFile(source, "rw").getChannel();
            target = new java.io.RandomAccessFile(destination, "rw").getChannel();
            long position = 0;
            long count    = sumber.size();
            sumber.transferTo(position, count, target);
           }
        catch (IOException ex) {
                    javax.swing.JOptionPane
                            .showMessageDialog(null,  "Error Gambar atau gambar kosong\n"+ex.getMessage());
            }
        finally {
            if(source != null) 
                { sumber.close(); }
            if(destination != null) 
                {target.close();  }
        }
        return destination;
    }
    public List<Mobil> getMobilList() {
        return mobilList;
    }

    public void setMobilList(List<Mobil> mobilList) {
//        this.bindingGroup.unbind();
        this.mobilList = mobilList;
//        this.bindingGroup.bind();

    }

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        System.out.println("app.view.panel.mobil.panelMobil.jButton4ActionPerformed()");
        if (!this.blessingPUEntityManager.getTransaction().isActive()) 
                this.blessingPUEntityManager.getTransaction().begin();
        this.blessingPUEntityManager.getTransaction().commit();        // TODO add your handling code here:
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//        this.addMobil.setSize(800, 800);
        this.addMobil.setLocationRelativeTo(null);
        this.addMobil.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        System.out.println("app.view.panel.mobil.panelMobil.jButton6ActionPerformed()");
        if (!this.blessingPUEntityManager.getTransaction().isActive()) 
                this.blessingPUEntityManager.getTransaction().begin();        
        System.out.println(this.getHapus().getDebitur());
        System.out.println(this.getHapus().getBpkb());     
        List<KeuanganMobil> KM = this.getHapus().getKeuanganMobils();
        this.keuanganMobilList.removeAll(KM);
        this.debiturList1.remove(this.getHapus().getDebitur());
        this.bpkbList1.remove(this.getHapus().getBpkb());
        this.blessingPUEntityManager.remove(this.getHapus());
        this.blessingPUEntityManager.getTransaction().commit();
        this.mobilList.remove(this.getHapus());        
//        javax.swing.JOptionPane.showMessageDialog(this.getRootPane(), 
//                (remove ? "Berhasil":"Gagal" )+" Menghapus\nNama Product = "+ this.jTextField1.getText()
//        );        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed
    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
    int returnVal = this.jFileChooser1.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
            this.jTextField7.setText(this.jFileChooser1.getSelectedFile().getCanonicalPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } else {
        System.out.println("File access cancelled by user.");
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked
    File gambar1;
    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        gambar1 = jFileChooser1.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(gambar1);
                ImageIcon ii = new javax.swing.ImageIcon(new ImageIcon
                                (gambar1.toString()).getImage().getScaledInstance(300, 140, Image.SCALE_DEFAULT));
                jLabel6.setIcon(ii);
            } catch (IOException ex) {
            }
        // TODO add your handling code here:
    }//GEN-LAST:event_jFileChooser1ActionPerformed
    File gambar2,gambar3,gambar4,gambar5,gambar6,ktpScan;
    private void jFileChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser2ActionPerformed
        gambar2 = jFileChooser2.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(gambar2);
                ImageIcon ii = new javax.swing.ImageIcon(new ImageIcon
                                (gambar2.toString()).getImage().getScaledInstance(300, 140, Image.SCALE_DEFAULT));
                jLabel5.setIcon(ii);
            } catch (IOException ex) {
            }

        // TODO add your handling code here:
    }//GEN-LAST:event_jFileChooser2ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    int returnVal = this.jFileChooser2.showOpenDialog(this);
    
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
            this.jTextField13.setText(this.jFileChooser2.getSelectedFile().getCanonicalPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jFileChooser3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser3ActionPerformed
        gambar3 = jFileChooser3.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(gambar3);
                ImageIcon ii = new javax.swing.ImageIcon(new ImageIcon
                                (gambar3.toString()).getImage().getScaledInstance(300, 140, Image.SCALE_DEFAULT));
                jLabel4.setIcon(ii);
            } catch (IOException ex) {
            }
        // TODO add your handling code here:
    }//GEN-LAST:event_jFileChooser3ActionPerformed

    private void jFileChooser4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser4ActionPerformed
        // TODO add your handling code here:
        gambar4 = jFileChooser4.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(gambar4);
                ImageIcon ii = new javax.swing.ImageIcon(new ImageIcon
                                (gambar4.toString()).getImage().getScaledInstance(300, 140, Image.SCALE_DEFAULT));
                jLabel3.setIcon(ii);
            } catch (IOException ex) {
            }
    }//GEN-LAST:event_jFileChooser4ActionPerformed

    private void jFileChooser5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser5ActionPerformed
        // TODO add your handling code here:
                gambar5 = jFileChooser5.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(gambar5);
                ImageIcon ii = new javax.swing.ImageIcon(new ImageIcon
                                (gambar5.toString()).getImage().getScaledInstance(300, 140, Image.SCALE_DEFAULT));
                jLabel2.setIcon(ii);
            } catch (IOException ex) {
            }
    }//GEN-LAST:event_jFileChooser5ActionPerformed

    private void jFileChooser6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser6ActionPerformed
        // TODO add your handling code here:
                gambar6 = jFileChooser6.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(gambar6);
                ImageIcon ii = new javax.swing.ImageIcon(new ImageIcon
                                (gambar6.toString()).getImage().getScaledInstance(300, 140, Image.SCALE_DEFAULT));
                jLabel1.setIcon(ii);
            } catch (IOException ex) {
            }
    }//GEN-LAST:event_jFileChooser6ActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
    int returnVal = this.jFileChooser3.showOpenDialog(this);    
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
            this.jTextField14.setText(this.jFileChooser3.getSelectedFile().getCanonicalPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        int returnVal = this.jFileChooser4.showOpenDialog(this);    
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
            this.jTextField16.setText(this.jFileChooser4.getSelectedFile().getCanonicalPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
         int returnVal = this.jFileChooser5.showOpenDialog(this);    
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
            this.jTextField17.setText(this.jFileChooser5.getSelectedFile().getCanonicalPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
    int returnVal = this.jFileChooser6.showOpenDialog(this);    
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        try {
            this.jTextField18.setText(this.jFileChooser6.getSelectedFile().getCanonicalPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseClicked
        int returnVal = this.jFileChooser7.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.jTextField40.setText(this.jFileChooser7.getSelectedFile().getCanonicalPath());                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel50MouseClicked

    private void jTextField47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField47ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField47ActionPerformed

    private void jTextField57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField57ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField57ActionPerformed
    private void Hitung()
    {
        System.out.println("app.view.panel.mobil.panelMobil.Hitung()");
        String title = "Data Mobil " 
                + this.hapus.getType() + ";"
                + this.hapus.getTahun() + ";"
                + this.hapus.getWarna()+ ";"
                +this. hapus.getNoPolisiAktif() + ";"
                +this. hapus.getNoPolisiLama() + ";"
                ;
        this.editMobil.setTitle(title);
        List<KeuanganMobil> Km = this.hapus.getKeuanganMobils();
        keuanganMobilList.clear();
        keuanganMobilList.addAll(Km);
        BigInteger p = BigInteger.ZERO;
        BigInteger tk = BigInteger.ZERO;
        BigInteger td = BigInteger.ZERO;
        for (KeuanganMobil k : Km) {
                td = td.add(k.getPemasukan());
                tk = tk.add(k.getPengeluaran());
        }
        this.jTextField38.setText(Rp.format(tk) );
        this.jTextField39.setText(Rp.format(td) );
        this.jTextField59.setText(Rp.format(td.subtract(tk)) );
    
        try {
        ((app.view.FixPanel.PanelBank)ShowRoom.jPanel5).Reset();            
        } catch (Exception e) {
        }
    this.bankList.clear();
    this.bankList.addAll(bankQuery.getResultList());
    this.PerjalananList.clear();
    this.PerjalananList.add(null);    
    this.PerjalananList.addAll(query.getResultList());

    }
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        this.Hitung();
        this.editMobil.setLocationRelativeTo(null);        
        this.editMobil.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    public List<KeuanganMobil> getKeuanganMobilList() {
        return keuanganMobilList;
    }

    public void setKeuanganMobilList(List<KeuanganMobil> keuanganMobilList) {
        this.keuanganMobilList = keuanganMobilList;
    }

    private void jFileChooser7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser7ActionPerformed
            ktpScan = jFileChooser7.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(ktpScan);
                ImageIcon ii = new javax.swing.ImageIcon(new ImageIcon
                                (ktpScan.toString()).getImage().getScaledInstance(300, 140, Image.SCALE_DEFAULT));
                jLabel50.setIcon(ii);
            } catch (IOException ex) {
            }
        // TODO add your handling code here:
    }//GEN-LAST:event_jFileChooser7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.simpanButtonActionPerformed(evt);

//        jButton13ActionPerformed(evt);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.simpanButtonActionPerformed(evt);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
//        this.hapus.getKeuanganMobils()]        
        this.jDialog2.setLocationRelativeTo(null);
        this.jDialog2.show();

    }//GEN-LAST:event_jButton10ActionPerformed

        private final DecimalFormat Rp = new DecimalFormat("#,##0");

    private void UpdatePDP()
    {   
        System.out.println("app.view.panel.mobil.panelMobil.UpdatePDP()");
        
    }
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

//        this.UpdatePDP();
        this.simpanButtonActionPerformed(evt);
        jButton22ActionPerformed(evt);
        this.editMobil.hide();
//        int row = jTable1.getSelectedRow();
//        jTable1.setRowSelectionInterval(row, row);
//        jTable1.scrollRectToVisible(jTable1.getCellRect(row, 0, true));

//        this.editMobil.show();
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTable4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable4PropertyChange

    }//GEN-LAST:event_jTable4PropertyChange

    public List<Leasing> getLeasingList() {
        return leasingList;
    }

    public void setLeasingList(List<Leasing> leasingList) {
        this.leasingList = leasingList;
    }

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        this.simpanButtonActionPerformed(evt);
        this.Hitung();
        if (!this.blessingPUEntityManager.getTransaction().isActive()) 
                this.blessingPUEntityManager.getTransaction().begin(); 
                java.util.Collection data = query.getResultList();
        java.util.Collection mob = mobilQuery.getResultList();
        for (Object object : mob) {
            blessingPUEntityManager.refresh(object);                        
        }
//        jButton22ActionPerformed(evt);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
//            if (this.getHapus().getListleasing() == null) {
//                        Listleasing l = new Listleasing();
//                        l.setLeasingLeasingId((Leasing) this.jComboBox4.getSelectedItem());
//                        this.getHapus().setListleasing(l);
//            }
//            else {
//                        this.getHapus()
//                                .getListleasing()
//                                .setLeasingLeasingId((Leasing) this.jComboBox4.getSelectedItem());            
//            }            
            this.LeasingMobil.hide();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
//        jDialog1.setSize(400, 400);
        jDialog1.setLocationRelativeTo(null);
        jDialog1.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        System.out.println("app.view.panel.mobil.panelMobil.jButton8ActionPerformed()");
        if (!this.blessingPUEntityManager.getTransaction().isActive()) 
                this.blessingPUEntityManager.getTransaction().begin();        
        List<KeuanganMobil> KM = this.getHapus().getKeuanganMobils();
        KeuanganMobil Hapus = KM.get(jTable4.convertRowIndexToModel(jTable4.getSelectedRow()));
        KM.remove(Hapus);
        this.getHapus().setKeuanganMobils(KM);
        this.blessingPUEntityManager.remove(Hapus);
        this.blessingPUEntityManager.getTransaction().commit();
        this.Hitung();
    }//GEN-LAST:event_jButton8ActionPerformed

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        KeuanganMobil dp = (KeuanganMobil) this.inputPanel1.getTarget();
        Saldo ts = new Saldo();
        ts.setBankId((Bank) this.jComboBox5.getSelectedItem());
        dp.setTransaksi(ts);
        dp.setMobils(hapus);
        this.keuanganMobilList.add(dp);
        this.persist(dp);
        this.hapus.getKeuanganMobils().add(dp);
        this.Hitung();
        this.jDialog1.hide();
             simpanButtonActionPerformed(evt);
             try {
            ((app.view.FixPanel.PanelBank)ShowRoom.jPanel5).Reset();            
        } catch (Exception e) {
        }
            this.bankList.clear();
//            this.bankList.add(null);    
            this.bankList.addAll(bankQuery.getResultList());

    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        KeuanganMobil dp = (KeuanganMobil) this.inputPanel2.getTarget();
        Saldo ts = new Saldo();
        ts.setBankId((Bank) this.jComboBox6.getSelectedItem());
        dp.setTransaksi(ts);
        dp.setMobils(hapus);
        dp.setTripsTripsId((Trips) jComboBox13.getSelectedItem());
//        dp.setPerjalananKe(PROPERTIES);
        boolean simpan = utilsPanel.simpan(blessingPUEntityManager, dp);
        if (simpan) {
            this.keuanganMobilList.add(dp);
            this.hapus.getKeuanganMobils().add(dp);            
            this.jDialog2.hide();
            this.Hitung();
            simpanButtonActionPerformed(evt);
        }
        try {
            ((app.view.FixPanel.PanelBank)ShowRoom.jPanel5).Reset();            
        } catch (Exception e) {
        }
            this.bankList.clear();
            this.bankList.addAll(bankQuery.getResultList());

//        this.persist(dp);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        this.simpanButtonActionPerformed(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        this.LeasingMobil.setSize(300, 300);
        this.LeasingMobil.setLocationRelativeTo(null);
        this.LeasingMobil.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed
                Map<String, Integer> sort = new TreeMap<>();
                Map<Integer, Integer> tahun = new TreeMap<>();
    private void hitungArsip()
        {
                sort = new TreeMap<>();
                tahun = new TreeMap<>();
                Query createNativeQuery = blessingPUEntityManager.createNativeQuery("SELECT NO_POLISI_AKTIF FROM BLESSING.MOBIL WHERE TANGGL_PELUNASAN_PEMBELIAN IS NOT NULL AND STATUS_MOBIL = 'CLOSE' ORDER BY TANGGL_PELUNASAN_PEMBELIAN");
                List resultList = createNativeQuery.getResultList();
                int i = 0;
                for (Object object : resultList) {
                    i++;
                    sort.put(object.toString(), i);
            }
        }
    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        System.out.println("app.view.panel.mobil.panelMobil.jButton22ActionPerformed()");
        if (!this.blessingPUEntityManager.getTransaction().isActive()) 
            blessingPUEntityManager.getTransaction().begin(); 
            blessingPUEntityManager.getTransaction().rollback();
//        java.util.Collection mob =
        hitungArsip();
        List<Mobil> resultList = mobilQuery.getResultList();
        int a = 1;
        int i = 0;
        for (Mobil mobil : resultList) {
            blessingPUEntityManager.refresh(mobil); 
            mobil.setNomor(a);
            a++;
            if (sort.get(mobil.getNoPolisiAktif()) != null) {
                int year = (mobil.getTangglPelunasanPembelian().getYear()-100) * 1000;
                Integer val = tahun.get(year);
                if (val != null) {
                    tahun.put(year, val+1);
                }
                else                {
                    tahun.put(year, 1);
                    }
                i = year + tahun.get(year);
                sort.put(mobil.getNoPolisiAktif(), i);
                mobil.setArsip(i);
            }
        }
        
        this.mobilList.clear();
        this.mobilList.addAll(resultList);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
//        String pilihan = (String) this.jComboBox1.getSelectedItem();
//        System.out.println("pilihan = " + pilihan==null?"kosong":pilihan);
//        mobilList.removeIf(a -> !a.getStatusMobil().equals(pilihan));
        this.FilterDialog.setLocationRelativeTo(null);        
        this.FilterDialog.show();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        String cari = "%";
        cari += this.jTextField32.getText();
        cari += "%";
        System.out.println("cari = " + cari);
//                                + " d.nama LIKE :cari1 "
//                                + " OR d.bank LIKE :cari1" 
//                                + " OR d.noHp LIKE :cari1" 
//                                + " OR d.alamat LIKE :cari1"
//                                + " OR d.noKtp LIKE :cari1"                               

        TypedQuery<Mobil> createQuery = 
                blessingPUEntityManager
                        .createQuery(
                                " SELECT b from Mobil b WHERE" +
                                " b.bahanBakar LIKE :cari1 " +
                                " OR b.keterangan LIKE :cari1" +
                                " OR b.merk LIKE :cari1" +
                                " OR b.jenis LIKE :cari1" +
                                " OR b.noMesin LIKE :cari1" +
                                " OR b.noPolisiAktif LIKE :cari1" +
                                " OR b.noPolisiLama LIKE :cari1" +
                                " OR b.noRangka LIKE :cari1" +
                                " OR b.pemilikBaru LIKE :cari1" +
                                " OR b.pemilikLama LIKE :cari1" +
                                " OR b.penjual LIKE :cari1" +
                                " OR b.type LIKE :cari1" +
                                " OR b.debitur.nama LIKE :cari1" +
                                " OR b.debitur.bank LIKE :cari1" +
                                " OR b.debitur.noKtp LIKE :cari1" +
                                " OR b.debitur.alamat LIKE :cari1" +
                                " OR b.debitur.noHp LIKE :cari1" +
                                " OR b.warna LIKE :cari1"
                                ,app.table.Mobil.class)
                .setParameter("cari1", cari)
                ;
        System.out.println("createQuery = " + createQuery.getResultList().size());
        if (createQuery.getResultList().isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null,  "Pencarian kosong");                                    
        }
        else    {
        mobilList.clear();        
        }
        mobilList.addAll(createQuery.getResultList());
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        bpkbList1.clear();
        String cari = "%";
        cari += this.jTextField12.getText();
        cari += "%";
        System.out.println("cari = " + cari);
        TypedQuery<Bpkb> createQuery = 
                blessingPUEntityManager
                        .createQuery(
                                "SELECT b FROM Bpkb b WHERE"
                                        + " b.anBpkb LIKE :cari1\n" +
                                        " OR b.ket LIKE :cari1\n" +
                                        " OR b.noBpkb LIKE :cari1\n" +
                                        " OR b.noPolisiAktif LIKE :cari1\n" +
                                        " OR b.posisi LIKE :cari1\n" +
                                        " OR b.status LIKE :cari1\n" +
                                        " OR b.mobil.noPolisiAktif LIKE :cari1\n" +
                                        " OR b.mobil.merk LIKE :cari1\n" +
                                        " OR b.mobil.noMesin LIKE :cari1\n" +
                                        " OR b.mobil.noPolisiLama LIKE :cari1\n" +
                                        " OR b.mobil.noRangka LIKE :cari1\n" +
                                        " OR b.mobil.type LIKE :cari1\n" +
                                        " OR b.mobil.warna LIKE :cari1\n" +
                                        " OR b.stnk LIKE :cari1 "
                                + " order by b desc"
                                ,app.table.Bpkb.class)
                .setParameter("cari1", cari)
                ;
        System.out.println("createQuery = " + createQuery.getResultList().size());
        bpkbList1.addAll(createQuery.getResultList());
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        debiturList1.clear();        
        String cari = "%";
        cari += this.jTextField30.getText();
        cari += "%";
        System.out.println("cari = " + cari);
        TypedQuery<Debitur> createQuery = 
                blessingPUEntityManager
                        .createQuery("Select d from Debitur d where"
                                + " d.nama LIKE :cari1 "
                                + " OR d.bank LIKE :cari1" 
                                + " OR d.noHp LIKE :cari1" 
                                + " OR d.alamat LIKE :cari1"
                                + " OR d.noKtp LIKE :cari1"                               
                                + " OR d.mobil.noPolisiAktif LIKE :cari1"         +                      
                                        " OR d.mobil.merk LIKE :cari1\n" +
                                        " OR d.mobil.noMesin LIKE :cari1\n" +
                                        " OR d.mobil.noPolisiLama LIKE :cari1\n" +
                                        " OR d.mobil.noRangka LIKE :cari1\n" +
                                        " OR d.mobil.type LIKE :cari1\n" +
                                        " OR d.mobil.warna LIKE :cari1\n" 
                                + " OR d.pembayaran LIKE :cari1"
                                ,app.table.Debitur.class)
                .setParameter("cari1", cari)
                ;
//                                                " OR b.mobil.noPolisiAktif LIKE :cari1\n" +

        System.out.println("createQuery = " + createQuery.getResultList().size());
        debiturList1.addAll(createQuery.getResultList());
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        debiturList1.clear();
        debiturList1.addAll(debiturQuery1.getResultList());
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        bpkbList1.clear();
        bpkbList1.addAll(bpkbQuery1.getResultList());
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jTextField37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField37ActionPerformed

    private void jTextField43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField43ActionPerformed

    private void jTextField54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField54ActionPerformed

    private void jTextField60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField60ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField60ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        if (!this.blessingPUEntityManager.getTransaction().isActive()) 
            blessingPUEntityManager.getTransaction().begin(); 
            blessingPUEntityManager.getTransaction().rollback();
        String pilihan = (String) this.jComboBox1.getSelectedItem();
        String que = " SELECT m FROM Mobil m ";
        if (this.jComboBox1.getSelectedIndex() != 0)
            {
                que += "WHERE m.statusMobil = :cari AND ";
            }
        else 
        {
            que += " WHERE ";
        }
        
        int x = this.jComboBox11.getSelectedIndex();
        switch (x) {
            case 0:                
               que +=" m.tanggalBeli BETWEEN :startDate AND :endDate";
                break;
            case 1:                
               que +=" m.tanggalJual BETWEEN :startDate AND :endDate";
                break;
            case 2:                
               que +=" m.tangglPelunasanPembelian BETWEEN :startDate AND :endDate";
                break;
            case 3:                
               que +=" m.tanggalSampaiKupang BETWEEN :startDate AND :endDate";
                break;
            default:
                throw new AssertionError();
        }
//        if (this.jComboBox11.getSelectedIndex() == 0) {
//             que +=" m.tanggalBeli BETWEEN :startDate AND :endDate";
//        }
//        else    
//            {
//              que += " m.tanggalJual BETWEEN :startDate AND :endDate";
//        }
        System.out.println("que = " + que);        
//        mobilQuery = java.beans.Beans.isDesignTime() ? null : blessingPUEntityManager.createQuery("SELECT m FROM Mobil m WHERE  ");
        TypedQuery createQuery = blessingPUEntityManager
                .createQuery(que, app.table.Mobil.class)
//                    .setParameter("cari", pilihan)
                    .setParameter("startDate",this.jDateChooser11.getDate(), TemporalType.TIMESTAMP)
                    .setParameter("endDate", this.jDateChooser12.getDate(), TemporalType.TIMESTAMP)
                ;
       if (this.jComboBox1.getSelectedIndex() != 0)
            {
                createQuery.setParameter("cari", pilihan);
            }

        List<Mobil> resultList = createQuery.getResultList();
        int a = 1;
        for (Mobil mobil : resultList) {
            blessingPUEntityManager.refresh(mobil); 
            mobil.setNomor(a);
            a++;
        }
        this.mobilList.clear();
        this.mobilList.addAll(resultList);

        FilterDialog.hide();
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        if (!this.blessingPUEntityManager.getTransaction().isActive()) 
            blessingPUEntityManager.getTransaction().begin(); 
            blessingPUEntityManager.getTransaction().rollback();
        String pilihan = (String) this.jComboBox12.getSelectedItem();
        TypedQuery createQuery = blessingPUEntityManager
                .createQuery("SELECT m FROM Mobil m WHERE m.statusMobil = :cari" , app.table.Mobil.class)
                    .setParameter("cari", pilihan);
        List<Mobil> resultList = createQuery.getResultList();
        int a = 1;
        for (Mobil mobil : resultList) {
            blessingPUEntityManager.refresh(mobil); 
            mobil.setNomor(a);
            a++;
        }
        this.mobilList.clear();
        this.mobilList.addAll(resultList);
                    
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed
final static  DecimalFormat IDR = new DecimalFormat("###0");                            
    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        FilterDialog.hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30ActionPerformed
public void FileSave() throws IOException
{
   JFileChooser chooser=new JFileChooser(".");
   FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files","xls","excel");
   chooser.addChoosableFileFilter(filter);
   chooser.setFileFilter(filter);
   chooser.setFileSelectionMode(chooser.FILES_AND_DIRECTORIES);
   chooser.setDialogTitle("Save File");
   File filetemp = new File( System.getProperties().getProperty("user.home"), 
           "Data Mobil "+
                   new Date().toString().replace(":", "-")                   
                   +".xls");
   chooser.setSelectedFile(filetemp);
//   int returnVal1=chooser.showSaveDialog(this);
    while ( chooser.getSelectedFile().exists()) {
        JOptionPane.showMessageDialog(this,"File telah ada\nGanti Nama");
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.CANCEL_OPTION)
        {
            System.out.println("Cancel was selected");
            return;
        }
    }
   File file1 = chooser.getSelectedFile();
              List a = mobilList;
              Function fungsi = d -> d==null?"":d;         
              SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              Function tanggal = d -> d==null?" ":formator.format(d);
              List<File> cvs = new java.util.LinkedList<>(); 
              cvs.add(new File(chooser.getSelectedFile().getParentFile(), "Daftar Mobil.CSV"));
              WriteStep data = CSVUtil.of(new File(chooser.getSelectedFile().getParentFile(), "Daftar Mobil.CSV"))
                        .type(app.table.Mobil.class)
                            .properties(
                                    Tuple.of("REF/No","nomor", d-> d),
                                    Tuple.of("Status Mobil","statusMobil", fungsi),
                                    Tuple.of("Harga Pembelian","hargaPembelian", fungsi),
                                    Tuple.of("Harga Pembelian","hargaJual", fungsi),
                                    Tuple.of("Tanggal Beli","tanggalBeli", d -> d == null? " ": formator.format(d)),
                                    Tuple.of("Tanggal Smp Kupang","tanggalSampaiKupang", d -> d == null? " ": formator.format(d)),
                                    Tuple.of("Tanggal Jual","tanggalJual", d -> d == null? " ": formator.format(d)),
                                    Tuple.of("Tanggal Pelunas","tangglPelunasanPembelian", d -> d == null? " ": formator.format(d)),
                                    Tuple.of("No Polisi Aktif","noPolisiAktif", fungsi),
                                    Tuple.of("No Polisi Lama","noPolisiLama", fungsi),
                                    Tuple.of("Merk","merk", fungsi),
                                    Tuple.of("Type","type", fungsi),
                                    Tuple.of("Tahun","tahun", fungsi),
                                    Tuple.of("Warna","warna", fungsi),
                                    Tuple.of("Jenis","jenis", fungsi),
                                    Tuple.of("BBM","bahanBakar", fungsi),
                                    Tuple.of("No Mesin","noMesin", fungsi),
                                    Tuple.of("No Rangka","noRangka", fungsi),
                                    Tuple.of("Pemilik Baru","pemilikBaru", fungsi),
                                    Tuple.of("Pemilik Lama","pemilikLama", fungsi),
                                    Tuple.of("Silinder","silinder", fungsi),
                                    Tuple.of("Penjual","penjual", fungsi),
                                    Tuple.of("Hp Penjual","no_Hp_Penjual", fungsi),
                                    Tuple.of("Ref BPKB","bpkb.bpkbId", fungsi),
                                    Tuple.of("Atas Nama BPKB","bpkb.anBpkb", fungsi),
                                    Tuple.of("Ref Pembeli","debitur.debiturId", fungsi),
                                    Tuple.of("Nama Pembeli","debitur.nama", fungsi),
                                    Tuple.of("Total Pemasukan","totalPemasukan", fungsi),
                                    Tuple.of("Total Pengeluaran","totalPengeluaran", fungsi),
                                    Tuple.of("Total Profit","totalProfit", fungsi),
                                    Tuple.of("Leasing","listleasing.leasingLeasingId.nama", fungsi),
                                    Tuple.of("keterangan","keterangan", fungsi)
                    ).dataList(a);
              data.write();
              cvs.add(new File(chooser.getSelectedFile().getParentFile(), "Daftar Debitur.CSV"));
              WriteStep debitur = CSVUtil.of(new File(chooser.getSelectedFile().getParentFile(), "Daftar debitur.CSV"))
                        .type(app.table.Debitur.class)
                            .properties(
                                    Tuple.of("debitur REF","debitur.debiturId", d -> d==null?" ":d),
                                    Tuple.of("No Polisi Aktif","noPolisiAktif", fungsi),
                                    Tuple.of("No Polisi Lama","noPolisiLama", fungsi),
                                    Tuple.of("Merk","merk", fungsi),
                                    Tuple.of("Type","type", fungsi),
                                    Tuple.of("Nama","debitur.nama", d -> d==null?" ":d),
                                    Tuple.of("Nomot HP","debitur.noHp", d -> d==null?" ":d),
                                    Tuple.of("Nomor Identitas","debitur.noKtp", d -> d==null?" ":d),
//                                    Tuple.of("norek","norek", d -> d==null?" ":d),
                                    Tuple.of("Nama ke-2","debitur.bank", d -> d==null?" ":d),
                                    Tuple.of("Nomor HP ke-2","debitur.pembayaran", d -> d==null?" ":d),
                                    Tuple.of("Alamat","debitur.alamat", d -> d==null?" ":d )
//                                    Tuple.of("scan","scan", d -> d==null?" ":d),
//                                    Tuple.of("mobil REF","mobil", d -> d==null?" ":d)
                    ).dataList(a);
              debitur.write();
              cvs.add(new File(chooser.getSelectedFile().getParentFile(), "Daftar BPKB.CSV"));
              WriteStep bpkb = CSVUtil.of(new File(chooser.getSelectedFile().getParentFile(), "Daftar BPKB.CSV"))
                        .type(app.table.Bpkb.class)
                            .properties(
                                    Tuple.of("REF","bpkb.bpkbId", d -> d==null?" ":d),
                                    Tuple.of("No Polisi Aktif","noPolisiAktif", fungsi),
                                    Tuple.of("No Polisi Lama","noPolisiLama", fungsi),
                                    Tuple.of("Merk","merk", fungsi),
                                    Tuple.of("Type","type", fungsi),                                    
                                    Tuple.of("Atas Nama BPKB","bpkb.anBpkb", d -> d==null?" ":d),
                                    Tuple.of("No BPKB","bpkb.noBpkb", d -> d==null?" ":d),
                                    Tuple.of("No BPKB Lama","bpkb.status", d -> d==null?" ":d),
                                    Tuple.of("Posisi BPKB","bpkb.posisi", d -> d==null?" ":d),
                                    Tuple.of("Posisi Faktur","bpkb.noPolisiAktif", d -> d==null?" ":d),
                                    Tuple.of("STNK","bpkb.stnk", d -> d==null?" ":d),
                                    Tuple.of("tgl Bbn","bpkb.tglBbn", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Kembali Bbn","bpkb.tglKembaliBbn", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Cb","bpkb.tglCb", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Kembali Cb","bpkb.tglKembaliCb", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Leasing","bpkb.tglLeasing", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Terima","bpkb.tglTerima", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Pajak EXP","bpkb.tanggalExp", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("Keterangan","bpkb.ket", d -> d==null?" ":d),
                                    Tuple.of("mobil REF","bpkb.mobil", d -> d==null?" ":d)
                    ).dataList(a);
              bpkb.write();
              

                          //mobil laporan
            for (Mobil mobil : mobilList) {
                    String mo = 
                            mobil.getNoPolisiAktif()+ "-" +
//                            mobil.getMerk() + "-" +
                            mobil.getType().replace("/", " ")+ "-" +
//                            mobil.getJenis().replace("/", " ")+ "-" +
                            mobil.getTahun()+ "-" +
                            mobil.getWarna()+ "-" +
                            mobil.getStatusMobil()+ "-" +
                            mobil.getDebitur().getNama() + "-"+
                            mobil.getMobilId()+".CSV" ;
//                      mo = st.replaceAll("\\s+","");
                      File Folder =new File(chooser.getSelectedFile().getParentFile(), "Data Mobil");
                      Folder.mkdirs();
                      File p = new File(Folder, mo);
                      cvs.add(p);
                      List<KeuanganMobil> b = mobil.getKeuanganMobils();
                      KeuanganMobil total1 = new MobilPemasukan();
                      total1.setId(0l);
                      total1.setKeterangan("Total Pemasukan");
                      KeuanganMobil total2 = new MobilPengeluaran();
                      total2.setId(0l);
                      total2.setKeterangan("Total Pengeluaran");
                      KeuanganMobil laba = new MobilPemasukan();
                      laba.setKeterangan("Profit");
                      BigInteger temp1 = BigInteger.ZERO;
                      BigInteger temp2 = BigInteger.ZERO;
                      Saldo saldo1 = new Saldo();
                      Bank bank = new Bank();
                      saldo1.setBankId(bank);                      
                      for (KeuanganMobil m : b) {                          
                          temp1 = temp1.add(m.getPemasukan());
                          temp2 = temp2.add(m.getPengeluaran());
                      }
                      total1.setTransaksi(saldo1);
                      total2.setTransaksi(saldo1);
                      laba.setTransaksi(saldo1);
                      total1.setJumlah(temp1);
                      total2.setJumlah(temp2);
                      BigInteger profit = BigInteger.ZERO;
                      profit = profit.subtract(temp2);
                      profit = profit.add(temp1);
                      laba.setJumlah(profit);
                      b.add(total1);
                      b.add(total2);
                      b.add(laba);
                      List c = b;
                      WriteStep dataList = CSVUtil.of(p)
                        .type(app.table.KeuanganMobil.class)
                            .properties(
                                Tuple.of("Ref", "id", null),
                                Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                                Tuple.of("Keterangan", "keterangan", d -> d),
                                Tuple.of("Pemasukan", "pemasukan", d -> IDR.format(d) ),
                                Tuple.of("Pengeluaran", "pengeluaran", d -> IDR.format(d) ),
                                Tuple.of("Bank", "transaksi.bankId.namaBank", d -> d==null?"":d)
                ).dataList(c);
                    try {
                    dataList.write();
                } catch (Exception e) {
                    e.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    return ;
                } 

        }
            //end
            ExcelConverter(cvs, chooser.getSelectedFile());
            System.out.println("\n File Berhasil Di Print");
            JOptionPane.showMessageDialog(this,"File Created. \n"+ file1);
            Desktop.getDesktop().open(file1);
}
    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        try {
            FileSave();
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(panelMobil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void FileExcel()
    {
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("Data Mobil");

    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        List<JTextField> list = new LinkedList<>();
        list.add(jTextField1);
        list.add(jTextField65);
        list.add(jTextField66);
        list.add(jTextField2);
        list.add(jTextField3);
        list.add(jTextField4);
        list.add(jTextField5);
        list.add(jTextField6);
        list.add(jTextField7);
        list.add(jTextField9);
        list.add(jTextField10);
        list.add(jTextField11);
        list.add(jTextField15);
        list.add(jTextField37);
        list.add(jTextField43);
        list.add(jTextField64);
        list.add(jTextField7);
        list.add(jTextField16);
        list.add(jTextField13);
        list.add(jTextField14);
        list.add(jTextField17);
        list.add(jTextField18);        
        list.forEach((te) -> {
            te.setText(null);
        });
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        simpanButtonActionPerformed(evt);        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.addMobil.hide();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        CardLayout cardLayout = (CardLayout) jPanel6.getLayout();
        cardLayout.next(jPanel6);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        CardLayout cardLayout = (CardLayout) jPanel6.getLayout();
        cardLayout.next(jPanel6);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        this.addMobil.hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField65ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField65ActionPerformed

    private void jTextField66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField66ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField66ActionPerformed
    public static TableModel toTableModel(Map<?,?> map) {
        DefaultTableModel model = new DefaultTableModel(
            new Object[] { "Mobil", "Jumlah" }, 0
        );
        for (Map.Entry<?,?> entry : map.entrySet()) {
            model.addRow(new Object[] { entry.getKey(), entry.getValue() });
        }
        return model;
    }
    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        this.InformasiDialog.setLocationRelativeTo(null);        
        this.InformasiDialog.show();
        long total = jTable1.getRowCount();
        Map<String, Long> collect = 
                mobilList.stream()
                        .collect(Collectors.groupingBy(l  -> l.getStatusMobil(), Collectors.counting())); 
        collect.put("Total Mobil", total);

        java.util.List<Akun> temp = new LinkedList<>();
        BigInteger totalMasuk = BigInteger.ZERO;
        BigInteger totalKeluar = BigInteger.ZERO;

        for (Mobil mobil : mobilList) {
//            i++;
            BigInteger pemasukan = BigInteger.ZERO;
            BigInteger pengeluaran = BigInteger.ZERO;
            List<KeuanganMobil> KM = mobil.getKeuanganMobil2();
            for (KeuanganMobil k : KM) {
                pemasukan = pemasukan.add(k.getPemasukan());
                pengeluaran = pengeluaran.add(k.getPengeluaran());
                totalMasuk = totalMasuk.add(k.getPemasukan());
                totalKeluar = totalKeluar.add(k.getPengeluaran());
            }
            int i = 0;
            if (sort.get(mobil.getNoPolisiAktif()) != null) {
                i =  sort.get(mobil.getNoPolisiAktif());                
            }
            Akun veh = new Akun(i)
                    .setAkun(
//                            mobil+ " " +
                            mobil.getMerk()+ " " +
                            mobil.getType()+ " " +
                            mobil.getTahun()+ " " +
                            mobil.getWarna()+ " " +
                            mobil.getNoPolisiAktif()+" "+
                                    mobil.getDebitur().getNama()
                    )
                    .setPemasukan(pemasukan)
                    .setPengeluaran(pengeluaran)
                    ;
            veh.setKeterangan(mobil.getStatusMobil());
            veh.setTanggalClose(mobil.getTangglPelunasanPembelian());
            temp.add(veh);
        }
        akunMobilList.clear();
        Akun Hasil = new Akun()
                .setAkun("Total")
                .setPemasukan(totalMasuk)
                .setPengeluaran(totalKeluar)
//                .setProfit(totalMasuk.subtract(totalKeluar))
                ;
        Hasil.setProfit(totalMasuk.subtract(totalKeluar));
        
        akunMobilList.add(Hasil);
        akunMobilList.addAll(temp);
        this.jTable6.setModel(toTableModel(new TreeMap<>(collect)));
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jLabel76MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel76MouseClicked
        int returnVal = this.jFileChooser7.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.getHapus().setInteriorIi(this.jFileChooser7.getSelectedFile().getCanonicalPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel76MouseClicked

    private void jLabel74MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel74MouseClicked
        int returnVal = this.jFileChooser7.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.getHapus().setTampilanSmpKanan(this.jFileChooser7.getSelectedFile().getCanonicalPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel74MouseClicked

    private void jLabel72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseClicked
        int returnVal = this.jFileChooser7.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.getHapus().setTampilanSmpKanan(this.jFileChooser7.getSelectedFile().getCanonicalPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel72MouseClicked

    private void jLabel75MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel75MouseClicked
        int returnVal = this.jFileChooser7.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.getHapus().setInteriorI(this.jFileChooser7.getSelectedFile().getCanonicalPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel75MouseClicked

    private void jLabel73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel73MouseClicked
        int returnVal = this.jFileChooser7.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.getHapus().setTampilanSmpKiri(this.jFileChooser7.getSelectedFile().getCanonicalPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel73MouseClicked

    private void jLabel54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel54MouseClicked
        int returnVal = this.jFileChooser7.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                this.getHapus().setTampilanDepan(this.jFileChooser7.getSelectedFile().getCanonicalPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("File access cancelled by user.");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel54MouseClicked

    private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
   JFileChooser chooser=new JFileChooser(".");
   FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files","xls","excel");
   chooser.addChoosableFileFilter(filter);
   chooser.setFileFilter(filter);
   chooser.setFileSelectionMode(chooser.FILES_AND_DIRECTORIES);
   chooser.setDialogTitle("Save File");
   File filetemp = new File( System.getProperties().getProperty("user.home"), 
           "Data Mobil "+                            
                hapus.getNoPolisiAktif()+ "-" +
                hapus.getType().replace("/", " ")+ "-" +
                hapus.getTahun()+ "-" +
                hapus.getWarna()+ "-" +
                hapus.getStatusMobil()+ "-" +
                hapus.getDebitur().getNama() + "-"+
                hapus.getMobilId()+" "+
                   new Date().toString().replace(":", "-")                   
                   +".xls");
   chooser.setSelectedFile(filetemp);
   int returnVal1=chooser.showSaveDialog(this);
    while ( chooser.getSelectedFile().exists()) {
        JOptionPane.showMessageDialog(this,"File telah ada\nGanti Nama");
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.CANCEL_OPTION)
        {
            System.out.println("Cancel was selected");
            return;
        }
    }
    File file1 = chooser.getSelectedFile();
    List<Mobil> singleMobil = new LinkedList<>();
    singleMobil.add(hapus);
                  SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");

    //
        String[] items1 = {"INFO", "Value"};
        String[] items2 = {"REF/No", hapus.getMobilId()+""};
        String[] items3 = {"Harga Pembelian", hapus.getHargaPembelian()+""};
        String[] items4 = {"Harga Penjualan", hapus.getHargaJual()+""};
        String[] items5 = {"Tanggal Beli", hapus.getTanggalBeli()==null?"":formator.format(hapus.getTanggalBeli()) };
        String[] items6 = {"Tanggal smp Kupang", hapus.getTanggalSampaiKupang()==null?"":formator.format(hapus.getTanggalSampaiKupang())};
        String[] items7 = {"Tanggal Jual", hapus.getTanggalJual()==null?"":formator.format(hapus.getTanggalJual())};
        String[] items8 = {"Tanggal Pelunasan", hapus.getTangglPelunasanPembelian()==null?"":formator.format(hapus.getTangglPelunasanPembelian())};
        String[] items9 = {"No Polisi Aktif", hapus.getNoPolisiAktif()};
        String[] items10 = {"No Polisi Lama", hapus.getNoPolisiLama()};
        String[] items11 = {"Merk", hapus.getMerk()};
        String[] items12 = {"Type", hapus.getType()};
        String[] items13 = {"Tahun Mobil", hapus.getTahun()+""};
        String[] items14 = {"Warna Mobil", hapus.getWarna()};
        String[] items15 = {"Jenis Mobil", hapus.getJenis()};
        String[] items16 = {"BBM Mobil", hapus.getBahanBakar()};
        String[] items17 = {"No Mesin Mobil", hapus.getNoMesin()};
        String[] items18 = {"No Rangka Mobil", hapus.getNoRangka()};
        String[] items19 = {"Pemilik Baru Mobil", hapus.getPemilikBaru()};
        String[] items20 = {"Pemilik Lama", hapus.getPemilikLama()};
        String[] items21 = {"Silinder Mobil", hapus.getSilinder()+""};
        String[] items22 = {"Penjual Mobil", hapus.getPenjual()};
        String[] items23 = {"Hp Penjual", hapus.getNo_Hp_Penjual()};
        String[] items24 = {"REF BPKB", hapus.getBpkb().getBpkbId()+""};
        String[] items25 = {"Atas Nama BPKB", hapus.getBpkb().getAnBpkb()};
        String[] items26 = {"REF Pembeli Mobil", hapus.getDebitur().getDebiturId()+""};
        String[] items27 = {"Nama Pembeli", hapus.getDebitur().getNama()};
        String[] items28 = {"Total Pemasukan Mobil", hapus.getTotalPemasukan().toString()};
        String[] items29 = {"Total Pengeluaran Mobil", hapus.getTotalPengeluaran().toString()};
        String[] items30 = {"Total Profit Mobil", hapus.getTotalProfit().toString()};
        String[] items31 = {"Leasing Mobil", hapus.getListleasing().getLeasingLeasingId().getNama()};
        String[] items32 = {"Keterangan Mobil", hapus.getKeterangan()};

        List<String[]> entries = new ArrayList<>();
        entries.add(items1);
        entries.add(items2);
        entries.add(items3);
        entries.add(items4);
        entries.add(items5);
        entries.add(items6);
        entries.add(items7);
        entries.add(items8);
        entries.add(items9);
        entries.add(items10);
        entries.add(items11);
        entries.add(items12);
        entries.add(items13);
        entries.add(items14);
        entries.add(items15);
        entries.add(items16);
        entries.add(items17);
        entries.add(items18);
        entries.add(items19);
        entries.add(items20);
        entries.add(items21);
        entries.add(items22);
        entries.add(items23);
        entries.add(items24);
        entries.add(items25);
        entries.add(items26);
        entries.add(items27);
        entries.add(items28);
        entries.add(items29);
        entries.add(items30);
        entries.add(items31);
        entries.add(items32);

        File file = new File(chooser.getSelectedFile().getParentFile(), "INFO "+hapus.getMobilId()+".CSV");
        System.out.println("file = " + file.getAbsolutePath());
        try 
            (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeAll(entries);
        } catch (IOException ex) {
        }
    //
              List a = singleMobil;
              Function fungsi = d -> d==null?"":d;         
              Function tanggal = d -> d==null?" ":formator.format(d);
              List<File> cvs = new java.util.LinkedList<>(); 
              cvs.add(file);
              cvs.add(new File(chooser.getSelectedFile().getParentFile(), "Daftar Mobil.CSV"));
              WriteStep data = CSVUtil.of(new File(chooser.getSelectedFile().getParentFile(), "Daftar Mobil.CSV"))
                        .type(app.table.Mobil.class)
                            .properties(
                                    Tuple.of("REF/No","nomor", d-> d),
                                    Tuple.of("Status Mobil","statusMobil", fungsi),
                                    Tuple.of("Harga Pembelian","hargaPembelian", fungsi),
                                    Tuple.of("Harga Penjualan ","hargaJual", fungsi),
                                    Tuple.of("Tanggal Beli","tanggalBeli", d -> d == null? " ": formator.format(d)),
                                    Tuple.of("Tanggal Smp Kupang","tanggalSampaiKupang", d -> d == null? " ": formator.format(d)),
                                    Tuple.of("Tanggal Jual","tanggalJual", d -> d == null? " ": formator.format(d)),
                                    Tuple.of("Tanggal Pelunas","tangglPelunasanPembelian", d -> d == null? " ": formator.format(d)),
                                    Tuple.of("No Polisi Aktif","noPolisiAktif", fungsi),
                                    Tuple.of("No Polisi Lama","noPolisiLama", fungsi),
                                    Tuple.of("Merk","merk", fungsi),
                                    Tuple.of("Type","type", fungsi),
                                    Tuple.of("Tahun","tahun", fungsi),
                                    Tuple.of("Warna","warna", fungsi),
                                    Tuple.of("Jenis","jenis", fungsi),
                                    Tuple.of("BBM","bahanBakar", fungsi),
                                    Tuple.of("No Mesin","noMesin", fungsi),
                                    Tuple.of("No Rangka","noRangka", fungsi),
                                    Tuple.of("Pemilik Baru","pemilikBaru", fungsi),
                                    Tuple.of("Pemilik Lama","pemilikLama", fungsi),
                                    Tuple.of("Silinder","silinder", fungsi),
                                    Tuple.of("Penjual","penjual", fungsi),
                                    Tuple.of("Hp Penjual","no_Hp_Penjual", fungsi),
                                    Tuple.of("Ref BPKB","bpkb.bpkbId", fungsi),
                                    Tuple.of("Atas Nama BPKB","bpkb.anBpkb", fungsi),
                                    Tuple.of("Ref Pembeli","debitur.debiturId", fungsi),
                                    Tuple.of("Nama Pembeli","debitur.nama", fungsi),
                                    Tuple.of("Total Pemasukan","totalPemasukan", fungsi),
                                    Tuple.of("Total Pengeluaran","totalPengeluaran", fungsi),
                                    Tuple.of("Total Profit","totalProfit", fungsi),
                                    Tuple.of("Leasing","listleasing.leasingLeasingId.nama", fungsi),
                                    Tuple.of("keterangan","keterangan", fungsi)
                    ).dataList(a);
              data.write();
              cvs.add(new File(chooser.getSelectedFile().getParentFile(), "Daftar Debitur.CSV"));
              WriteStep debitur = CSVUtil.of(new File(chooser.getSelectedFile().getParentFile(), "Daftar debitur.CSV"))
                        .type(app.table.Debitur.class)
                            .properties(
                                    Tuple.of("debitur REF","debitur.debiturId", d -> d==null?" ":d),
                                    Tuple.of("No Polisi Aktif","noPolisiAktif", fungsi),
                                    Tuple.of("No Polisi Lama","noPolisiLama", fungsi),
                                    Tuple.of("Merk","merk", fungsi),
                                    Tuple.of("Type","type", fungsi),
                                    Tuple.of("Nama","debitur.nama", d -> d==null?" ":d),
                                    Tuple.of("Nomot HP","debitur.noHp", d -> d==null?" ":d),
                                    Tuple.of("Nomor Identitas","debitur.noKtp", d -> d==null?" ":d),
                                    Tuple.of("Nama ke-2","debitur.bank", d -> d==null?" ":d),
                                    Tuple.of("Nomor HP ke-2","debitur.pembayaran", d -> d==null?" ":d),
                                    Tuple.of("Alamat","debitur.alamat", d -> d==null?" ":d )
                    ).dataList(a);
              debitur.write();
              cvs.add(new File(chooser.getSelectedFile().getParentFile(), "Daftar BPKB.CSV"));
              WriteStep bpkb = CSVUtil.of(new File(chooser.getSelectedFile().getParentFile(), "Daftar BPKB.CSV"))
                        .type(app.table.Bpkb.class)
                            .properties(
                                    Tuple.of("REF","bpkb.bpkbId", d -> d==null?" ":d),
                                    Tuple.of("No Polisi Aktif","noPolisiAktif", fungsi),
                                    Tuple.of("No Polisi Lama","noPolisiLama", fungsi),
                                    Tuple.of("Merk","merk", fungsi),
                                    Tuple.of("Type","type", fungsi),                                    
                                    Tuple.of("Atas Nama BPKB","bpkb.anBpkb", d -> d==null?" ":d),
                                    Tuple.of("No BPKB","bpkb.noBpkb", d -> d==null?" ":d),
                                    Tuple.of("No BPKB Lama","bpkb.status", d -> d==null?" ":d),
                                    Tuple.of("Posisi BPKB","bpkb.posisi", d -> d==null?" ":d),
                                    Tuple.of("Posisi Faktur","bpkb.noPolisiAktif", d -> d==null?" ":d),
                                    Tuple.of("STNK","bpkb.stnk", d -> d==null?" ":d),
                                    Tuple.of("tgl Bbn","bpkb.tglBbn", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Kembali Bbn","bpkb.tglKembaliBbn", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Cb","bpkb.tglCb", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Kembali Cb","bpkb.tglKembaliCb", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Leasing","bpkb.tglLeasing", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Terima","bpkb.tglTerima", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tgl Pajak EXP","bpkb.tanggalExp", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("Keterangan","bpkb.ket", d -> d==null?" ":d),
                                    Tuple.of("mobil REF","bpkb.mobil", d -> d==null?" ":d)
                    ).dataList(a);
              bpkb.write();
              

                          //mobil laporan
            for (Mobil mobil : singleMobil) {
                    String mo = 
                            mobil.getNoPolisiAktif()+ "-" +
//                            mobil.getMerk() + "-" +
                            mobil.getType().replace("/", " ")+ "-" +
//                            mobil.getJenis().replace("/", " ")+ "-" +
                            mobil.getTahun()+ "-" +
                            mobil.getWarna()+ "-" +
                            mobil.getStatusMobil()+ "-" +
                            mobil.getDebitur().getNama() + "-"+
                            mobil.getMobilId()+".CSV" ;
//                      mo = st.replaceAll("\\s+","");
                      File Folder =new File(chooser.getSelectedFile().getParentFile(), "Data Mobil");
                      Folder.mkdirs();
                      File p = new File(Folder, mo);
                      cvs.add(p);
                      List<KeuanganMobil> b = mobil.getKeuanganMobils();
                      KeuanganMobil total1 = new MobilPemasukan();
                      total1.setId(0l);
                      total1.setKeterangan("Total Pemasukan");
                      KeuanganMobil total2 = new MobilPengeluaran();
                      total2.setId(0l);
                      total2.setKeterangan("Total Pengeluaran");
                      KeuanganMobil laba = new MobilPemasukan();
                      laba.setKeterangan("Profit");
                      BigInteger temp1 = BigInteger.ZERO;
                      BigInteger temp2 = BigInteger.ZERO;
                      Saldo saldo1 = new Saldo();
                      Bank bank = new Bank();
                      saldo1.setBankId(bank);                      
                      for (KeuanganMobil m : b) {                          
                          temp1 = temp1.add(m.getPemasukan());
                          temp2 = temp2.add(m.getPengeluaran());
                      }
                      total1.setTransaksi(saldo1);
                      total2.setTransaksi(saldo1);
                      laba.setTransaksi(saldo1);
                      total1.setJumlah(temp1);
                      total2.setJumlah(temp2);
                      BigInteger profit = BigInteger.ZERO;
                      profit = profit.subtract(temp2);
                      profit = profit.add(temp1);
                      laba.setJumlah(profit);
                      b.add(total1);
                      b.add(total2);
                      b.add(laba);
                      List c = b;
                      WriteStep dataList = CSVUtil.of(p)
                        .type(app.table.KeuanganMobil.class)
                            .properties(
                                Tuple.of("Ref", "id", null),
                                Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                                Tuple.of("Keterangan", "keterangan", d -> d),
                                Tuple.of("Pemasukan", "pemasukan", d -> IDR.format(d) ),
                                Tuple.of("Pengeluaran", "pengeluaran", d -> IDR.format(d) ),
                                Tuple.of("Bank", "transaksi.bankId.namaBank", d -> d==null?"":d)
                ).dataList(c);
                    try {
                    dataList.write();
                } catch (Exception e) {
                    e.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    return ;
                } 

        }
            //end
        try {
            ExcelConverter(cvs, chooser.getSelectedFile());
            System.out.println("\n File Berhasil Di Print");
            JOptionPane.showMessageDialog(this,"File Created. \n"+ file1);
            Desktop.getDesktop().open(filetemp);
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(panelMobil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton35ActionPerformed
        private AtomicBoolean stop;
    public List<Bpkb> getBpkbList1() {
        return bpkbList1;
    }

    public void setBpkbList1(List<Bpkb> bpkbList1) {
        this.bindingGroup.unbind();
        this.bpkbList1 = bpkbList1;
        this.bindingGroup.bind();
    }

    public List<Debitur> getDebiturList1() {
        return debiturList1;
    }

    public void setDebiturList1(List<Debitur> debiturList1) {
        this.bindingGroup.bind();
        this.debiturList1 = debiturList1;
        this.bindingGroup.bind();
    }
//    private Mobil baru;
//    private java.util.List<Akun> AkunMobilList;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BPKB;
    private javax.swing.JDialog FilterDialog;
    private javax.swing.JDialog InformasiDialog;
    private javax.swing.JDialog LeasingMobil;
    private java.util.List<app.table.Trips> PerjalananList;
    private javax.swing.JDialog addMobil;
    private java.util.List<Akun> akunMobilList;
    private java.util.List<app.table.Bank> bankList;
    private javax.persistence.Query bankQuery;
    private javax.persistence.EntityManager blessingPUEntityManager;
    private java.util.List<app.table.Bpkb> bpkbList1;
    private javax.persistence.Query bpkbQuery1;
    private java.util.List<app.table.Debitur> debiturList1;
    private javax.persistence.Query debiturQuery1;
    private javax.swing.JDialog editMobil;
    private app.utils.inputPanel inputPanel1;
    private app.utils.inputPanel inputPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser10;
    private com.toedter.calendar.JDateChooser jDateChooser11;
    private com.toedter.calendar.JDateChooser jDateChooser12;
    private com.toedter.calendar.JDateChooser jDateChooser13;
    private com.toedter.calendar.JDateChooser jDateChooser14;
    private com.toedter.calendar.JDateChooser jDateChooser15;
    private com.toedter.calendar.JDateChooser jDateChooser16;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private com.toedter.calendar.JDateChooser jDateChooser8;
    private com.toedter.calendar.JDateChooser jDateChooser9;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JFileChooser jFileChooser3;
    private javax.swing.JFileChooser jFileChooser4;
    private javax.swing.JFileChooser jFileChooser5;
    private javax.swing.JFileChooser jFileChooser6;
    private javax.swing.JFileChooser jFileChooser7;
    private javax.swing.JFileChooser jFileChooser8;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JFormattedTextField jFormattedTextField7;
    private javax.swing.JFormattedTextField jFormattedTextField8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private com.toedter.calendar.JYearChooser jYearChooser2;
    private com.toedter.calendar.JYearChooser jYearChooser3;
    private java.util.List<app.table.KeuanganMobil> keuanganMobilList;
    private javax.persistence.Query keuanganMobilQuery;
    private java.util.List<app.table.Leasing> leasingList;
    private javax.persistence.Query leasingQuery;
    private java.util.List<app.table.Mobil> mobilList;
    private javax.persistence.Query mobilQuery;
    private javax.persistence.Query query;
    private javax.swing.JButton simpanButton;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    
    public void persist(Object object) {
        if (!this.blessingPUEntityManager.getTransaction().isActive())         
            blessingPUEntityManager.getTransaction().begin();
        try {
            blessingPUEntityManager.persist(object);
            blessingPUEntityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            blessingPUEntityManager.getTransaction().rollback();
        } finally {
//            blessingPUEntityManager.close();
            System.out.println(object+"=persisted");
        }
    }
    private Mobil hapus = new Mobil();

    public Mobil getHapus() {
        return hapus;
    }

    public void setHapus(Mobil hapus) {
        this.hapus = hapus;
    }
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
            java.util.logging.Logger.getLogger(panelLeasing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(panelLeasing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(panelLeasing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(panelLeasing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new panelMobil());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    
}
