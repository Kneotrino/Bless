/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.FixPanel.akuntansi;

import app.view.FixPanel.*;
import app.table.Bank;
import app.table.Laporan;
import app.table.Saldo;
import static app.utils.ExcelConverter.ExcelConverter;
import app.view.ShowRoom;
import javaslang.Tuple;
import app.view.utilsPanel;
import com.joobar.csvbless.CSVUtil;
import com.joobar.csvbless.WriteStep;
import com.toedter.calendar.JDateChooserCellEditor;
import com.toedter.calendar.JYearChooser;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.Beans;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static javaslang.API.LinkedMap;
import static javaslang.API.LinkedMap;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.swing.DefaultCellEditor;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author SEED
 */
public class panelControl extends JPanel {
    
    public panelControl() {
        initComponents();
        if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();
        }
        
    }
    
    private Class<? extends Laporan> clazz;
    private Object table;

    public Object getTable() {
        return table;
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }
    
    
    public panelControl(Object kelas) {
        this.table = kelas;
        this.clazz = (Class) kelas;
        System.out.println("clazz.getName() = " + clazz.getSimpleName());
        initComponents();
        if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();
        }
        String info = clazz.getSimpleName()
                .equals("Pengeluaran") ? "Operasional":clazz.getSimpleName();
        this.jLabel2.setText("Laporan "+  info + " Bulan ini"
        );
    }
      Map m1 = new HashMap();
      int temp = -1;
    public panelControl(int code)
    {
      temp = code;
      m1.put(0, "Pemasukan");
      m1.put(1, "Pengeluaran");
        System.out.println("Laporan = " + m1.get(code));
        initComponents();
            if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();
        }
            String temp = "Pengeluaran";
            this.jLabel2.setText("Laporan " + m1.get(code) + " bulan ini");
            this.jButton1.hide();
            this.newButton.hide();
            this.deleteButton.hide();
            Rest();
    }
public void Rest()
{
    list.clear();
    if (m1.get(temp).equals("Pemasukan")) {
        list.addAll(
                entityManager.createQuery("SELECT l FROM Laporan l"
                        + " where l.tanggal BETWEEN :startDate AND :endDate"
                        + " AND l.name = true "
                        + "ORDER BY l.tanggal")
                .setParameter("startDate", awalBulan, TemporalType.TIMESTAMP)
                .setParameter("endDate", akhirBulan, TemporalType.TIMESTAMP)  
                .getResultList());
    }
    else    {
        list.addAll(
                entityManager.createQuery("SELECT l FROM Laporan l"
                        + " where l.tanggal BETWEEN :startDate AND :endDate"
                        + " AND l.name = false "
                        + "ORDER BY l.tanggal")
                .setParameter("startDate", awalBulan, TemporalType.TIMESTAMP)
                .setParameter("endDate", akhirBulan, TemporalType.TIMESTAMP)  
                .getResultList());
    }
       BigInteger temp = BigInteger.ZERO; 
         for (Laporan laporan : list) {
            temp = temp.add(laporan.getJumlah());
        }
         jFormattedTextField2.setValue(temp);
}
public void Restall()
{
    list.clear();
    if (m1.get(temp).equals("Pemasukan")) {
        list.addAll(
                entityManager.createQuery("SELECT l FROM Laporan l"
                        + " WHERE l.name = true "
                        + "ORDER BY l.tanggal")
                .getResultList());
    }
    else    {
        list.addAll(
                entityManager.createQuery("SELECT l FROM Laporan l"
                        + " WHERE l.name = false "
                        + "ORDER BY l.tanggal")
                .getResultList());
    }
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

        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        akhirBulan = cal.getTime();
        awalBulan = new Date(akhirBulan.getYear(), akhirBulan.getMonth(), 0);
        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();
        query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT l FROM Laporan l");
        String que = "SELECT en FROM " + clazz.getSimpleName() + " en ";
        TypedQuery<? extends Laporan> createQuery = entityManager.createQuery(que, clazz);
        list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        tanggalLabel = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jumlahLabel = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        keteranganLabel = new javax.swing.JLabel();
        keteranganField = new app.utils.MyTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        bankQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT b FROM Bank b");
        bankList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(bankQuery.getResultList());
        jComboBox1 = new javax.swing.JComboBox<>();
        jDialog2 = new javax.swing.JDialog();
        inputPanel1 = new app.utils.inputPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jDialog3 = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new app.utils.MyTextField();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        newButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        refreshButton = new javax.swing.JButton();
        refreshButton1 = new javax.swing.JButton();
        refreshButton2 = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        newButton1 = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        list.clear();
        list.addAll(createQuery.getResultList());

        jDialog1.setTitle("Data Baru");
        jDialog1.setAlwaysOnTop(true);
        jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog1.setType(java.awt.Window.Type.POPUP);
        jDialog1.getContentPane().setLayout(new java.awt.GridLayout(0, 1));
        jDialog1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closing...");
                jDialog1.hide();
                refreshButtonActionPerformed(null);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setLayout(new java.awt.GridLayout(0, 2));

        tanggalLabel.setText("Tanggal:");
        jPanel2.add(tanggalLabel);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.tanggal}"), jDateChooser1, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jDateChooser1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jPanel2.add(jDateChooser1);

        jumlahLabel.setText("Jumlah (IDR):");
        jPanel2.add(jumlahLabel);

        jFormattedTextField1.setText("jFormattedTextField1");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.jumlah}"), jFormattedTextField1, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jFormattedTextField1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jPanel2.add(jFormattedTextField1);

        keteranganLabel.setText("Keterangan:");
        jPanel2.add(keteranganLabel);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.keterangan}"), keteranganField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), keteranganField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jPanel2.add(keteranganField);

        jLabel1.setText("Status");
        jPanel2.add(jLabel1);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OPEN", "CLOSE" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.tipe}"), jComboBox3, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jComboBox3, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jPanel2.add(jComboBox3);

        jLabel4.setText("Bank Tujuan");
        jPanel2.add(jLabel4);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bankList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox2);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.transaksi.bankId}"), jComboBox2, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jPanel2.add(jComboBox2);

        jButton1.setText("Simpan");
        jButton1.addActionListener(formListener);
        jPanel2.add(jButton1);

        jDialog1.getContentPane().add(jPanel2);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bankList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox1);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.transaksi.bankId}"), jComboBox1, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jDialog2.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        inputPanel1.setLayout(new java.awt.GridLayout(0, 2));
        jDialog2.getContentPane().add(inputPanel1, java.awt.BorderLayout.CENTER);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OPEN", "CLOSE" }));

        jDialog3.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog3.setSize(450, 250);
        jDialog3.getContentPane().setLayout(new java.awt.GridLayout(0, 2));

        jLabel7.setText("Cari");
        jDialog3.getContentPane().add(jLabel7);
        jDialog3.getContentPane().add(jTextField1);

        jLabel5.setText("Tanggal Awal");
        jDialog3.getContentPane().add(jLabel5);

        jDateChooser2.setDate(new Date());
        jDialog3.getContentPane().add(jDateChooser2);

        jLabel6.setText("Tanggal Akhir");
        jDialog3.getContentPane().add(jLabel6);

        jDateChooser3.setDate(new Date());
        jDialog3.getContentPane().add(jDateChooser3);

        jLabel11.setText("Status");
        jDialog3.getContentPane().add(jLabel11);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OPEN", "CLOSE", "SEMUA" }));
        jComboBox5.addActionListener(formListener);
        jDialog3.getContentPane().add(jComboBox5);

        jButton2.setText("Filter");
        jButton2.addActionListener(formListener);
        jDialog3.getContentPane().add(jButton2);

        jButton3.setText("Tutup");
        jButton3.addActionListener(formListener);
        jDialog3.getContentPane().add(jButton3);

        setLayout(new java.awt.BorderLayout());

        masterTable.setDefaultEditor(Date.class, new JDateChooserCellEditor());
        masterTable.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
        masterTable.setDefaultEditor(java.math.BigInteger.class, new app.utils.TablePopupEditor());
        masterTable.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        masterTable.setAutoCreateRowSorter(true);
        masterTable.setRowHeight(25);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("Ref x");
        columnBinding.setColumnClass(Long.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggal}"));
        columnBinding.setColumnName("Tanggal");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jumlah}"));
        columnBinding.setColumnName("Nominal");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tipe}"));
        columnBinding.setColumnName("Status");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jenis}"));
        columnBinding.setColumnName("Tipe");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${transaksi.bankId}"));
        columnBinding.setColumnName("Bank");
        columnBinding.setColumnClass(app.table.Bank.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        masterScrollPane.setViewportView(masterTable);
        if (masterTable.getColumnModel().getColumnCount() > 0) {
            masterTable.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(jComboBox1));
        }
        masterTable.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(jComboBox4));

        add(masterScrollPane, java.awt.BorderLayout.CENTER);

        jLabel2.setText("Laporan");
        jPanel1.add(jLabel2);

        jLabel3.setText("Total");
        jPanel1.add(jLabel3);

        jFormattedTextField2.setEditable(false);
        jFormattedTextField2.setPreferredSize(new java.awt.Dimension(100, 27));
        jPanel1.add(jFormattedTextField2);

        newButton.setText("Baru");
        newButton.setEnabled(false);
        newButton.addActionListener(formListener);
        jPanel1.add(newButton);

        deleteButton.setText("Hapus");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), deleteButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        deleteButton.addActionListener(formListener);
        jPanel1.add(deleteButton);

        jMonthChooser1.setMonth(new Date().getMonth());
        jMonthChooser1.setYearChooser(getjYearChooser1());
        jPanel1.add(jMonthChooser1);
        jPanel1.add(jYearChooser1);

        refreshButton.setText("Tampilkan bulan");
        refreshButton.addActionListener(formListener);
        jPanel1.add(refreshButton);

        refreshButton1.setText("Tampilkan semuanya");
        refreshButton1.addActionListener(formListener);
        jPanel1.add(refreshButton1);

        refreshButton2.setText("Pencarian");
        refreshButton2.addActionListener(formListener);
        jPanel1.add(refreshButton2);

        saveButton.setText("Simpan");
        saveButton.setEnabled(false);
        saveButton.addActionListener(formListener);
        jPanel1.add(saveButton);

        newButton1.setText("Print");
        newButton1.addActionListener(formListener);
        jPanel1.add(newButton1);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        bindingGroup.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == newButton) {
                panelControl.this.newButtonActionPerformed(evt);
            }
            else if (evt.getSource() == deleteButton) {
                panelControl.this.deleteButtonActionPerformed(evt);
            }
            else if (evt.getSource() == refreshButton) {
                panelControl.this.refreshButtonActionPerformed(evt);
            }
            else if (evt.getSource() == refreshButton1) {
                panelControl.this.refreshButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == refreshButton2) {
                panelControl.this.refreshButton2ActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton) {
                panelControl.this.saveButtonActionPerformed(evt);
            }
            else if (evt.getSource() == newButton1) {
                panelControl.this.newButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == jButton1) {
                panelControl.this.jButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == jComboBox5) {
                panelControl.this.jComboBox5ActionPerformed(evt);
            }
            else if (evt.getSource() == jButton2) {
                panelControl.this.jButton2ActionPerformed(evt);
            }
            else if (evt.getSource() == jButton3) {
                panelControl.this.jButton3ActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents
            Calendar cal = Calendar.getInstance();
            Date akhirBulan;
            Date awalBulan;
    @SuppressWarnings("unchecked")
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        cal.set(Calendar.MONTH, jMonthChooser1.getMonth());
        cal.set(Calendar.YEAR, jYearChooser1.getYear());
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR, cal.getActualMaximum(Calendar.HOUR));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        akhirBulan = cal.getTime();        
        awalBulan = new Date(akhirBulan.getYear(), akhirBulan.getMonth(), 0);
        awalBulan.setMinutes(1);
        System.out.println("akhirBulan = " + akhirBulan);
        System.out.println("awalBulan = " + awalBulan);
//        try {
//            ((app.view.FixPanel.PanelBank)ShowRoom.jPanel5).Reset();          
//        } catch (NullPointerException e) {
//        }
        this.bankList.clear();
        this.bankList.addAll(bankQuery.getResultList());
         if (temp != -1) {            
            System.out.println("Laporan = " + m1.get(temp));
            Rest();
        }
        else {        
        System.out.println("Kelas name = " + clazz.getSimpleName());    
        String clzName = this.clazz.getSimpleName();
//        String que = "SELECT en FROM " + clzName + " en "
//                + "where en.tanggal BETWEEN :startDate AND :endDate"
//                ;SELECT * FROM BLESSING.LAPORAN 
//WHERE MONTH(LAPORAN.TANGGAL) = 1 


        System.out.println("startDate = "+ awalBulan);
        TypedQuery<? extends Laporan> createQuery = 
                entityManager.createQuery("SELECT en FROM " + clzName + " en " + "where FUNC('MONTH', en.tanggal) = :startDate "
                        + "AND FUNC('YEAR', en.tanggal) = :endDate", clazz)
                .setParameter("startDate", jMonthChooser1.getMonth()+1)
                .setParameter("endDate", jYearChooser1.getYear() );
        List<? extends Laporan> res = createQuery.getResultList();           
            for (Laporan re : res) {
                entityManager.refresh(re);
            }
        list.clear();
        list.addAll((Collection<? extends Laporan>) res);
         }
         BigInteger temp = BigInteger.ZERO; 
         for (Laporan laporan : list) {
            temp = temp.add(laporan.getJumlah());
        }        
         jFormattedTextField2.setValue(temp);
    }//GEN-LAST:event_refreshButtonActionPerformed

    public JYearChooser getjYearChooser1() {
        return jYearChooser1;
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int[] selected = masterTable.getSelectedRows();
        List<app.table.Laporan> toRemove = new ArrayList<app.table.Laporan>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            app.table.Laporan l = list.get(masterTable.convertRowIndexToModel(selected[idx]));
            toRemove.add(l);
            entityManager.remove(l);
        }
        list.removeAll(toRemove);
        int jawab = javax.swing.JOptionPane.showConfirmDialog(null,  "Pastikan Sebelum Anda Ingin Menghapus Transaksi\nApakah anda ingin menghapus transaksi ini?");
        if (jawab == javax.swing.JOptionPane.OK_OPTION) {
            this.saveButtonActionPerformed(evt);            
        }
        else {
            this.refreshButtonActionPerformed(evt);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed
        public  void Refresh()
        {
                this.refreshButtonActionPerformed(null);
        }
        public void Input(java.awt.event.ActionEvent evt)
        {
            newButtonActionPerformed(evt);
        }
    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        masterTable.clearSelection();        
        Laporan nw = null;
        try {
            Constructor<? extends Laporan> cons = clazz.getConstructor();
            nw = cons.newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(panelControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        nw.setTransaksi(new Saldo());
        nw.getTransaksi().setBankId((Bank) this.jComboBox1.getSelectedItem());
        entityManager.persist(nw);
//        utilsPanel.simpan(entityManager, nw);
        list.add(nw);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
        this.jDialog1.setSize(400,300);
        this.jDialog1.setLocationRelativeTo(null);
        this.jDialog1.show();
    }//GEN-LAST:event_newButtonActionPerformed
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        this.jDialog1.setVisible(false);
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<app.table.Laporan> merged = new ArrayList<app.table.Laporan>(list.size());
            for (app.table.Laporan l : list) {
                merged.add(entityManager.merge(l));
            }
            list.clear();
            list.addAll(merged);
        }
        this.refreshButtonActionPerformed(evt);
//        this.refreshButtonActionPerformed(evt);
    }//GEN-LAST:event_saveButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ( clazz == app.table.Pengeluaran.class) {
            BigInteger value = (BigInteger) jFormattedTextField1.getValue();
            Bank bank = (Bank) jComboBox2.getSelectedItem();
            if (bank.getTotalSaldo().subtract(value).compareTo(BigInteger.ZERO) == -1 ) {
                 javax.swing.JOptionPane.showMessageDialog(null,  
                        "Saldo Minus \nNama Bank ="+ bank.getNamaBank()+"\nSaldo = " + bank.getTotalSaldo());
                 this.refreshButtonActionPerformed(evt);
            }
        }
        this.saveButtonActionPerformed(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void refreshButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButton1ActionPerformed
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();        
         if (temp != -1) {            
            System.out.println("Laporan all = " + m1.get(temp));
            Restall();
        } else
         {
             String que = "SELECT en FROM " + clazz.getSimpleName() + " en ";
            TypedQuery<? extends Laporan> createQuery = 
                    entityManager.createQuery(que, clazz)
                    ;      
            app.table.Util.RefreshLaporan();
            list.clear();
            list.addAll(createQuery.getResultList());
         }
         BigInteger temp = BigInteger.ZERO; 
         for (Laporan laporan : list) {
            temp = temp.add(laporan.getJumlah());
        }
         jFormattedTextField2.setValue(temp);
    }//GEN-LAST:event_refreshButton1ActionPerformed

    private void refreshButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButton2ActionPerformed
    
        jDialog3.setLocationRelativeTo(null);
        jDialog3.show();

        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButton2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        awalBulan = jDateChooser2.getDate();
        akhirBulan = jDateChooser3.getDate();
        awalBulan.setHours(0);
        awalBulan.setMinutes(0);
        awalBulan.setSeconds(0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(awalBulan);
        cal.add(Calendar.MINUTE, -1);
        awalBulan = cal.getTime();
//        cal.set
        akhirBulan.setHours(23);
        akhirBulan.setMinutes(59);
        System.out.println("awalBulan = " + awalBulan);
        System.out.println("akhirBulan = " + akhirBulan);
                String huruf = "%";
        huruf += this.jTextField1.getText();
        huruf += "%";   
        System.out.println("huruf = " + huruf);
//        try {
//        ((app.view.FixPanel.PanelBank)ShowRoom.jPanel5).Reset();                       
//        } catch (Exception e) {
//        }
        this.bankList.clear();
        this.bankList.addAll(bankQuery.getResultList());
        String cari = jComboBox5.getSelectedItem().toString();
        String cari1 = " AND en.tipe = '"+cari+"'";
            if (cari.equals("SEMUA")) {
            cari1 = "";
        }

        if (temp != -1) {            
            System.out.println("Laporan = " + m1.get(temp));
                    String bool = "";
            if (m1.get(temp).equals("Pemasukan")) {
                    bool = "true";
                }
            else
                {
                    bool = "false";
                }
        Query setParameter = entityManager.createQuery("SELECT en FROM Laporan en"
                    + " where en.keterangan LIKE :carian AND en.tanggal BETWEEN :startDate AND :endDate"
                    + " AND en.name = " +bool 
                    + cari1
                    + " ORDER BY en.tanggal")
                    .setParameter("startDate", awalBulan, TemporalType.TIMESTAMP)
                    .setParameter("endDate", akhirBulan, TemporalType.TIMESTAMP)
                .setParameter("carian", huruf);
            System.out.println("setParameter = " + setParameter);
       List<? extends Laporan> res = setParameter.getResultList();           
            for (Laporan re : res) {
                entityManager.refresh(re);
            }
        list.clear();
        list.addAll((Collection<? extends Laporan>) res);

        }
        else {      
        String clzName = this.clazz.getSimpleName();
        System.out.println("Kelas name = " + clazz.getSimpleName());    
//        String que = "SELECT en FROM " + clzName + " en "
//                + "where en.tanggal BETWEEN :startDate AND :endDate"
//                ;
        TypedQuery<? extends Laporan> createQuery = 
                entityManager.createQuery("SELECT en FROM " + clzName + " en "
                + "where en.keterangan LIKE :carian AND en.tanggal BETWEEN :startDate AND :endDate "+cari1+" order by en.tanggal asc", clazz)
                .setParameter("startDate", awalBulan, TemporalType.TIMESTAMP)
                .setParameter("endDate", akhirBulan, TemporalType.TIMESTAMP) 
                .setParameter("carian", huruf);
            System.out.println("createQuery = " + createQuery);
        List<? extends Laporan> res = createQuery.getResultList();           
            for (Laporan re : res) {
                entityManager.refresh(re);
            }
        list.clear();
        list.addAll((Collection<? extends Laporan>) res);
         }
         BigInteger temp = BigInteger.ZERO; 
         for (Laporan laporan : list) {
            temp = temp.add(laporan.getJumlah());
        }        
         jFormattedTextField2.setValue(temp);
         jDialog3.hide();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jDialog3.hide();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void newButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton1ActionPerformed
        System.out.println("app.view.FixPanel.panelMaster.newButton1ActionPerformed()");
           JFileChooser chooser=new JFileChooser(".");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files","xls","excel");
            chooser.addChoosableFileFilter(filter);
            chooser.setFileFilter(filter);
            chooser.setFileSelectionMode(chooser.FILES_AND_DIRECTORIES);
            chooser.setDialogTitle("Save File");
//        Date date = new Date();
            File filetemp = new File( System.getProperties().getProperty("user.home"), 
           "Data Laporan "+clazz.getSimpleName() +".xls");
   chooser.setSelectedFile(filetemp);
//   int returnVal1=chooser.showSaveDialog(this);
    while ( chooser.getSelectedFile().exists()) {
        JOptionPane.showMessageDialog(this,"File telah ada\nGanti Nama");
        chooser.showSaveDialog(this);        
    }
       File file1 = chooser.getSelectedFile();
       List a = list;
       WriteStep dataList = CSVUtil.of(new File(file1.getParentFile(), "Data Laporan.CSV"))
            .type(clazz)
            .properties(
                Tuple.of("Ref", "id", d -> d==null?"":d),
                Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                Tuple.of("Jumlah", "jumlah", d -> d==null?"":d ),
                Tuple.of("Status", "tipe", d -> d==null?"":d),
                Tuple.of("Tipe", "jenis", d -> d==null?"":d),
                Tuple.of("Bank", "transaksi.bankId.namaBank", d -> d==null?"":d),
                Tuple.of("Keterangan", "keterangan", d -> d==null?"":d)
        )
            .dataList(a);
        try {
            dataList.write();
            List<File> cvs = new java.util.LinkedList<>();
            cvs.add(new File(file1.getParentFile(), "Data Laporan.CVS"));
            ExcelConverter(cvs, file1);
            Desktop.getDesktop().open(file1);
                } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Gagal Print, Karena file sementara terbuka\n"+e);
            return ;
                } 
            finally {
                        JOptionPane.showMessageDialog(this,"File Created. \n"+ file1);
            }

    }//GEN-LAST:event_newButton1ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed
                    final SimpleDateFormat formator = new SimpleDateFormat("dd-MM-yyyy");


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.util.List<app.table.Bank> bankList;
    private javax.persistence.Query bankQuery;
    private javax.swing.JButton deleteButton;
    private javax.persistence.EntityManager entityManager;
    private app.utils.inputPanel inputPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JLabel jumlahLabel;
    private javax.swing.JTextField keteranganField;
    private javax.swing.JLabel keteranganLabel;
    private java.util.List<app.table.Laporan> list;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JButton newButton;
    private javax.swing.JButton newButton1;
    private javax.persistence.Query query;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton refreshButton1;
    private javax.swing.JButton refreshButton2;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel tanggalLabel;
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
            java.util.logging.Logger.getLogger(panelControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(panelControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(panelControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(panelControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new panelControl(app.table.Pegawaigaji.class));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
//    LaporanPemasukan();
    }
    public static void LaporanPemasukan()
    {
                EventQueue.invokeLater(() -> {
                app.table.Util.RefreshBank();
                app.table.Util.RefreshLaporan();
                javax.swing.JDialog jDialog1 = new JDialog();
                jDialog1.setSize(1200, 700);
                jDialog1.setLocationRelativeTo(null);
                jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
                jDialog1.getContentPane().add(new panelControl(1));
                jDialog1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jDialog1.show();
        });
    }
        public static void LaporanPengeluaran()
    {
                EventQueue.invokeLater(() -> {
                app.table.Util.RefreshBank();
                app.table.Util.RefreshLaporan();
                javax.swing.JDialog jDialog1 = new JDialog();
                jDialog1.setSize(1200, 700);
                jDialog1.setLocationRelativeTo(null);
                jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
                jDialog1.getContentPane().add(new panelControl(0));
                jDialog1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jDialog1.show();
        });
    }
}
