/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.FixPanel;

import app.table.Bank;
import app.table.Investor;
import app.table.Laba;
import app.table.LaporanSaham;
import app.table.Laporanlaba;
import app.table.Relasi;
import app.table.Saldo;
import app.table.pembagianLaba;
import static app.utils.ExcelConverter.ExcelConverter;
import app.view.ShowRoom;
import com.joobar.csvbless.CSVUtil;
import com.joobar.csvbless.WriteStep;
import com.toedter.calendar.JDateChooserCellEditor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.beans.Beans;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import javaslang.Tuple;
import javax.persistence.RollbackException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author SEED
 */
public class PanelLaporanLaba extends JPanel {
    
    public PanelLaporanLaba() {
        initComponents();
        if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();
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

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();
        query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT L FROM Laporanlaba L");
        list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
        bankQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT b FROM Bank b");
        bankList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(bankQuery.getResultList());
        saveButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jDialog1 = new javax.swing.JDialog();
        detailScrollPane = new javax.swing.JScrollPane();
        detailTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        newDetailButton = new javax.swing.JButton();
        deleteDetailButton = new javax.swing.JButton();
        saveButton3 = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        detailScrollPane1 = new javax.swing.JScrollPane();
        detailTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        newDetailButton1 = new javax.swing.JButton();
        deleteDetailButton1 = new javax.swing.JButton();
        saveButton2 = new javax.swing.JButton();
        jDialog4 = new javax.swing.JDialog();
        inputPanel4 = new app.utils.inputPanel(app.table.pembagianLaba.class);
        jLabel4 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        query1 = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT i FROM Investor i");
        list1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query1.getResultList());
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        newButton1 = new javax.swing.JButton();
        newButton2 = new javax.swing.JButton();
        newButton4 = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        refreshButton1 = new javax.swing.JButton();
        saveButton1 = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        saveButton.setText("Simpan");
        saveButton.addActionListener(formListener);

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(formListener);

        jDialog1.setSize(1000, 700);
        jDialog1.setLocationRelativeTo(null);

        masterTable.setDefaultEditor(Date.class, new JDateChooserCellEditor());
        masterTable.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
        masterTable.setDefaultEditor(Integer.class, new app.utils.TablePopupEditor());
        masterTable.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        masterTable.setDefaultEditor(java.math.BigInteger.class, new app.utils.TablePopupEditor());
        detailTable.setDefaultEditor(Date.class, new JDateChooserCellEditor());
        detailTable.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
        detailTable.setDefaultEditor(java.math.BigInteger.class, new app.utils.TablePopupEditor());
        detailTable.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        detailTable.setAutoCreateRowSorter(true);
        detailTable.setRowHeight(25);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${selectedElement.labaList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, eLProperty, detailTable);
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
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tipe}"));
        columnBinding.setColumnName("Tipe");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jumlah}"));
        columnBinding.setColumnName("Pemasukan");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pengeluaran");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        jTableBinding.setSourceUnreadableValue(java.util.Collections.emptyList());
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        detailScrollPane.setViewportView(detailTable);
        if (detailTable.getColumnModel().getColumnCount() > 0) {
            detailTable.getColumnModel().getColumn(0).setMaxWidth(100);
            detailTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            detailTable.getColumnModel().getColumn(2).setPreferredWidth(350);
        }

        jDialog1.getContentPane().add(detailScrollPane, java.awt.BorderLayout.CENTER);

        newDetailButton.setText("Baru");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), newDetailButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        newDetailButton.addActionListener(formListener);
        jPanel2.add(newDetailButton);

        deleteDetailButton.setText("Delete");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, detailTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), deleteDetailButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        deleteDetailButton.addActionListener(formListener);
        jPanel2.add(deleteDetailButton);

        saveButton3.setText("Simpan");
        saveButton3.addActionListener(formListener);
        jPanel2.add(saveButton3);

        jDialog1.getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jDialog2.setSize(1000, 700);
        jDialog2.setLocationRelativeTo(null);

        detailTable1.setDefaultEditor(Date.class, new JDateChooserCellEditor());
        detailTable1.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
        detailTable1.setDefaultEditor(java.math.BigInteger.class, new app.utils.TablePopupEditor());
        detailTable1.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        detailTable1.setAutoCreateRowSorter(true);
        detailTable1.setRowHeight(25);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${selectedElement.laporanSaham.relasis}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, eLProperty, detailTable1);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saham.id}"));
        columnBinding.setColumnName("REF");
        columnBinding.setColumnClass(Long.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saham.tanggal}"));
        columnBinding.setColumnName("Tanggal");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saham.keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saham.laba.jumlah}"));
        columnBinding.setColumnName("Jumlah");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saham.investorId}"));
        columnBinding.setColumnName("Investor");
        columnBinding.setColumnClass(app.table.Investor.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saham.b}"));
        columnBinding.setColumnName("Sumber");
        columnBinding.setColumnClass(app.table.Bank.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        detailScrollPane1.setViewportView(detailTable1);

        jDialog2.getContentPane().add(detailScrollPane1, java.awt.BorderLayout.CENTER);

        newDetailButton1.setText("Baru");
        newDetailButton1.addActionListener(formListener);
        jPanel3.add(newDetailButton1);

        deleteDetailButton1.setText("Delete");
        deleteDetailButton1.addActionListener(formListener);
        jPanel3.add(deleteDetailButton1);

        saveButton2.setText("Simpan");
        saveButton2.addActionListener(formListener);
        jPanel3.add(saveButton2);

        jDialog2.getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jDialog4.setSize(500, 500);
        jDialog4.setLocationRelativeTo(null);
        jDialog4.setModal(true);

        jLabel4.setText("Investor");
        inputPanel4.add(jLabel4);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${list1}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox5);
        bindingGroup.addBinding(jComboBoxBinding);

        inputPanel4.add(jComboBox5);

        jLabel3.setText("Bank");
        inputPanel4.add(jLabel3);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bankList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox4);
        bindingGroup.addBinding(jComboBoxBinding);

        inputPanel4.add(jComboBox4);

        jButton4.setText("Simpan");
        jButton4.addActionListener(formListener);
        inputPanel4.add(jButton4);

        jDialog4.getContentPane().add(inputPanel4, java.awt.BorderLayout.CENTER);

        setLayout(new java.awt.BorderLayout());

        masterTable.setAutoCreateRowSorter(true);
        masterTable.setCellSelectionEnabled(true);
        masterTable.setRowHeight(25);

        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${ref}"));
        columnBinding.setColumnName("Ref");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${ke}"));
        columnBinding.setColumnName("Ke");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggal}"));
        columnBinding.setColumnName("Tanggal");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${TKeluar}"));
        columnBinding.setColumnName("T.Keluar");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${TMasuk}"));
        columnBinding.setColumnName("T.Masuk");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${profit}"));
        columnBinding.setColumnName("Profit");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${modalTahan.jumlah}"));
        columnBinding.setColumnName("Modal Di Tahan");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totalSiapBagi}"));
        columnBinding.setColumnName("T. Siap di bagi");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totalBagi}"));
        columnBinding.setColumnName("T. Pembagian");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${sisaBigInteger}"));
        columnBinding.setColumnName("Sisa");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        masterScrollPane.setViewportView(masterTable);
        masterTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        add(masterScrollPane, java.awt.BorderLayout.CENTER);

        newButton1.setText("Print");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), newButton1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        newButton1.addActionListener(formListener);
        jPanel1.add(newButton1);

        newButton2.setText("Lihat Detail");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), newButton2, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        newButton2.addActionListener(formListener);
        jPanel1.add(newButton2);

        newButton4.setText("Lihat Pembagian Laba");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), newButton4, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        newButton4.addActionListener(formListener);
        jPanel1.add(newButton4);

        newButton.setText("Baru");
        newButton.addActionListener(formListener);
        jPanel1.add(newButton);

        deleteButton.setText("Hapus");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), deleteButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        deleteButton.addActionListener(formListener);
        jPanel1.add(deleteButton);

        refreshButton1.setText("Refresh");
        refreshButton1.addActionListener(formListener);
        jPanel1.add(refreshButton1);

        saveButton1.setText("Simpan");
        saveButton1.addActionListener(formListener);
        jPanel1.add(saveButton1);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        bindingGroup.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == newButton1) {
                PanelLaporanLaba.this.newButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == newButton2) {
                PanelLaporanLaba.this.newButton2ActionPerformed(evt);
            }
            else if (evt.getSource() == newButton4) {
                PanelLaporanLaba.this.newButton4ActionPerformed(evt);
            }
            else if (evt.getSource() == newButton) {
                PanelLaporanLaba.this.newButtonActionPerformed(evt);
            }
            else if (evt.getSource() == deleteButton) {
                PanelLaporanLaba.this.deleteButtonActionPerformed(evt);
            }
            else if (evt.getSource() == refreshButton1) {
                PanelLaporanLaba.this.refreshButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton1) {
                PanelLaporanLaba.this.saveButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == refreshButton) {
                PanelLaporanLaba.this.refreshButtonActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton) {
                PanelLaporanLaba.this.saveButtonActionPerformed(evt);
            }
            else if (evt.getSource() == newDetailButton) {
                PanelLaporanLaba.this.newDetailButtonActionPerformed(evt);
            }
            else if (evt.getSource() == deleteDetailButton) {
                PanelLaporanLaba.this.deleteDetailButtonActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton3) {
                PanelLaporanLaba.this.saveButton3ActionPerformed(evt);
            }
            else if (evt.getSource() == newDetailButton1) {
                PanelLaporanLaba.this.newDetailButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == deleteDetailButton1) {
                PanelLaporanLaba.this.deleteDetailButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton2) {
                PanelLaporanLaba.this.saveButton2ActionPerformed(evt);
            }
            else if (evt.getSource() == jButton4) {
                PanelLaporanLaba.this.jButton4ActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    
    private void deleteDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDetailButtonActionPerformed
        int index = masterTable.getSelectedRow();
        app.table.Laporanlaba L = list.get(masterTable.convertRowIndexToModel(index));
        Collection<app.table.Laba> ls = L.getLabaList();
        int[] selected = detailTable.getSelectedRows();
        List<app.table.Laba> toRemove = new ArrayList<app.table.Laba>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            selected[idx] = detailTable.convertRowIndexToModel(selected[idx]);
            int count = 0;
            Iterator<app.table.Laba> iter = ls.iterator();
            while (count++ < selected[idx]) {
                iter.next();
            }
            app.table.Laba l = iter.next();
            toRemove.add(l);
            entityManager.remove(l);
        }
        ls.removeAll(toRemove);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_deleteDetailButtonActionPerformed

    private void newDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDetailButtonActionPerformed
        int index = masterTable.getSelectedRow();
        app.table.Laporanlaba L = list.get(masterTable.convertRowIndexToModel(index));
        Collection<app.table.Laba> ls = L.getLabaList();
        if (ls == null) {
            ls = new LinkedList<app.table.Laba>();
            L.setLabaList((List) ls);
        }
        app.table.Laba l = new app.table.Laba();
        entityManager.persist(l);
        l.setLaporanlabaRef(L);
        ls.add(l);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);
        int row = ls.size() - 1;
        detailTable.setRowSelectionInterval(row, row);
        detailTable.scrollRectToVisible(detailTable.getCellRect(row, 0, true));
    }//GEN-LAST:event_newDetailButtonActionPerformed
    
    @SuppressWarnings("unchecked")
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        java.util.Collection data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);
       ((app.view.FixPanel.PanelBank)ShowRoom.jPanel5).Reset();
        bankList.clear();
        bankList.addAll(bankQuery.getResultList());
        list1.clear();
        list1.addAll(query1.getResultList());
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int[] selected = masterTable.getSelectedRows();
        List<app.table.Laporanlaba> toRemove = new ArrayList<app.table.Laporanlaba>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            app.table.Laporanlaba L = list.get(masterTable.convertRowIndexToModel(selected[idx]));
            toRemove.add(L);
            entityManager.remove(L);
        }
        list.removeAll(toRemove);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        app.table.Laporanlaba L = new app.table.Laporanlaba();
        entityManager.persist(L);
        list.add(L);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }//GEN-LAST:event_newButtonActionPerformed
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            jDialog1.hide();
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
//            refreshButtonActionPerformed(evt);
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<app.table.Laporanlaba> merged = new ArrayList<app.table.Laporanlaba>(list.size());
            for (app.table.Laporanlaba L : list) {
                merged.add(entityManager.merge(L));
            }
            list.clear();
            list.addAll(merged);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void newButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton1ActionPerformed
   JFileChooser chooser=new JFileChooser(".");
   FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files","xls","excel");
   chooser.addChoosableFileFilter(filter);
   chooser.setFileFilter(filter);
   chooser.setFileSelectionMode(chooser.FILES_AND_DIRECTORIES);
   chooser.setDialogTitle("Save File");
   File filetemp = new File( System.getProperties().getProperty("user.home"), "Data Detail.xls");
   chooser.setSelectedFile(filetemp);
   int returnVal1=chooser.showSaveDialog(this);
   if (returnVal1 == JFileChooser.APPROVE_OPTION) 
        {
             File file1 = chooser.getSelectedFile();
             SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
             Function fungsi = d -> d==null?" ":d;         
             Function tanggal = d -> d==null?" ":formator.format(d);
             List<File> cvs = new java.util.LinkedList<>(); 
             cvs.add(new File(chooser.getSelectedFile().getParentFile(), "Daftar Detail.CSV"));
             cvs.add(new File(chooser.getSelectedFile().getParentFile(), "Daftar Pembagian Laba.CSV"));
             int index = masterTable.getSelectedRow();
             app.table.Laporanlaba L = list.get(masterTable.convertRowIndexToModel(index));
             List a = L.getLabaList();
             List b = L.getLaporanSaham().getRelasis();
             WriteStep data = CSVUtil.of(new File(chooser.getSelectedFile().getParentFile(), "Daftar Detail.CSV"))
                        .type(app.table.Mobil.class)
                            .properties(
                                    Tuple.of("REF","id", fungsi),
                                    Tuple.of("Tanggal","tanggal", tanggal),
                                    Tuple.of("Keterangan","keterangan", fungsi),
                                    Tuple.of("Tipe","tipe", fungsi),
                                    Tuple.of("Pemasukan","jumlah", fungsi),
                                    Tuple.of("Pengeluaran","pengeluaran", fungsi)
                    ).dataList(a); 
             WriteStep data1 = CSVUtil.of(new File(chooser.getSelectedFile().getParentFile(), "Daftar Pembagian Laba.CSV"))
                        .type(app.table.Relasi.class)
                            .properties(
                                    Tuple.of("REF","saham.id", fungsi),
                                    Tuple.of("Tanggal","saham.tanggal", tanggal),
                                    Tuple.of("Keterangan","saham.keterangan", fungsi),
                                    Tuple.of("Jumlah","saham.laba.jumlah", fungsi),
                                    Tuple.of("Investor","saham.investorId", fungsi),
                                    Tuple.of("Sumber","saham.b", fungsi)
                    ).dataList(b); 
             try {
              data.write();
              data1.write();
              ExcelConverter(cvs, chooser.getSelectedFile());           
              Desktop.getDesktop().open(chooser.getSelectedFile());
            } catch (Exception e) {
                e.printStackTrace();
            }
                    
        }
    
        // TODO add your handling code here:
    }//GEN-LAST:event_newButton1ActionPerformed

    private void newButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton2ActionPerformed
        jDialog1.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_newButton2ActionPerformed

    private void refreshButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButton1ActionPerformed
        refreshButtonActionPerformed(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButton1ActionPerformed

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton1ActionPerformed
        saveButtonActionPerformed(evt);
        refreshButtonActionPerformed(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButton1ActionPerformed

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

    private void newDetailButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDetailButton1ActionPerformed
        jDialog4.show();
    }//GEN-LAST:event_newDetailButton1ActionPerformed

    private void deleteDetailButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDetailButton1ActionPerformed
        int index = masterTable.getSelectedRow();
        app.table.Laporanlaba L = list.get(masterTable.convertRowIndexToModel(index));
        Collection<app.table.Relasi> ls = L.getLaporanSaham().getRelasis();
        int[] selected = detailTable1.getSelectedRows();
        List<app.table.Relasi> toRemove = new ArrayList<app.table.Relasi>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            selected[idx] = detailTable1.convertRowIndexToModel(selected[idx]);
            int count = 0;
            Iterator<app.table.Relasi> iter = ls.iterator();
            while (count++ < selected[idx]) {
                iter.next();
            }
            app.table.Relasi l = iter.next();
            toRemove.add(l);
            entityManager.remove(l);
        }
        ls.removeAll(toRemove);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);        // TODO add your handling code here:
    }//GEN-LAST:event_deleteDetailButton1ActionPerformed

    private void saveButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton2ActionPerformed
        jDialog2.hide();
        saveButtonActionPerformed(evt);
        refreshButtonActionPerformed(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButton2ActionPerformed

    private void newButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton4ActionPerformed
        jDialog2.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_newButton4ActionPerformed
    Laporanlaba hapus;
    public Laporanlaba getHapus() {
        return hapus;
    }

    public void setHapus(Laporanlaba hapus) {
        this.hapus = hapus;
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int index = masterTable.getSelectedRow();
        app.table.Laporanlaba L = list.get(masterTable.convertRowIndexToModel(index));
        List<Relasi> ls = L.getLaporanSaham().getRelasis();
        if (ls == null) {
            ls = new LinkedList<app.table.Relasi>();
            L.setLabaList((List) ls);
        }
        app.table.Relasi l = new app.table.Relasi();
        app.table.Saham saham = new app.table.Saham();
        app.table.pembagianLaba laba = (app.table.pembagianLaba) inputPanel4.getTarget();
        app.table.Saldo s1 = new Saldo();
        s1.setBankId((Bank) jComboBox4.getSelectedItem());
        laba.setTransaksi(s1);        
        saham.setLaba(laba);
        laba.setSaham(saham);
        saham.setInvestorId((Investor) jComboBox5.getSelectedItem());
        l.setSaham(saham);
        l.setLaporanSaham(L.getLaporanSaham());
        saham.setRelasi(l);
        entityManager.persist(laba);        
        entityManager.persist(l);
        entityManager.persist(saham); 
        ls.add(l);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);
        int row = ls.size() - 1;
        detailTable1.setRowSelectionInterval(row, row);
        detailTable1.scrollRectToVisible(detailTable.getCellRect(row, 0, true));        
        jDialog4.hide();
        saveButtonActionPerformed(evt);
//        refreshButtonActionPerformed(evt);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void saveButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton3ActionPerformed
        saveButtonActionPerformed(evt);
        refreshButtonActionPerformed(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_saveButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.util.List<app.table.Bank> bankList;
    private javax.persistence.Query bankQuery;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteDetailButton;
    private javax.swing.JButton deleteDetailButton1;
    private javax.swing.JScrollPane detailScrollPane;
    private javax.swing.JScrollPane detailScrollPane1;
    private javax.swing.JTable detailTable;
    private javax.swing.JTable detailTable1;
    private javax.persistence.EntityManager entityManager;
    private app.utils.inputPanel inputPanel4;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private java.util.List<app.table.Laporanlaba> list;
    private java.util.List<app.table.Investor> list1;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JButton newButton;
    private javax.swing.JButton newButton1;
    private javax.swing.JButton newButton2;
    private javax.swing.JButton newButton4;
    private javax.swing.JButton newDetailButton;
    private javax.swing.JButton newDetailButton1;
    private javax.persistence.Query query;
    private javax.persistence.Query query1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton refreshButton1;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton saveButton1;
    private javax.swing.JButton saveButton2;
    private javax.swing.JButton saveButton3;
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
            java.util.logging.Logger.getLogger(PanelLaporanLaba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelLaporanLaba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelLaporanLaba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelLaporanLaba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new PanelLaporanLaba());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public void Refresh() {
        refreshButtonActionPerformed(null);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Investor> getList1() {
        return list1;
    }

    public void setList1(List<Investor> list1) {
        this.list1 = list1;
    }
    
}