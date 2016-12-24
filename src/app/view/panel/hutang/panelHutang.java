/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.panel.hutang;

import app.table.Bank;
import app.table.Bayarhutang;
import app.table.BayarhutangPengeluaran;
import app.table.Saldo;
import com.toedter.calendar.JDateChooserCellEditor;
import java.awt.EventQueue;
import java.beans.Beans;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.RollbackException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author SEED
 */
public class panelHutang extends JPanel {
    
    public panelHutang() {
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
        query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT h FROM Hutang h");
        list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
        jButton3 = new javax.swing.JButton();
        bankQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT b FROM Bank b");
        bankList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(bankQuery.getResultList());
        jComboBox2 = new javax.swing.JComboBox<>();
        jDialog4 = new javax.swing.JDialog();
        inputPanel3 = new app.utils.inputPanel(app.table.BayarhutangPengeluaran.class);
        newButton2 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jDialog5 = new javax.swing.JDialog();
        inputPanel4 = new app.utils.inputPanel(app.table.BayarhutangPemasukan.class);
        newButton3 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        newDetailButton = new javax.swing.JButton();
        jDialog1 = new javax.swing.JDialog();
        inputPanel1 = new app.utils.inputPanel(app.table.Hutang.class);
        newButton = new javax.swing.JButton();
        jComboBox5 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        detailScrollPane = new javax.swing.JScrollPane();
        detailTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        deleteDetailButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        jButton3.setText("Simpan");
        jButton3.addActionListener(formListener);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KAS", "BRI", "BCA", " " }));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${resultList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, bankQuery, eLProperty, jComboBox2);
        bindingGroup.addBinding(jComboBoxBinding);

        jDialog4.setTitle("HUTANG PENGELUARAN");
        jDialog4.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog4.getContentPane().add(inputPanel3, java.awt.BorderLayout.CENTER);

        newButton2.setText("Tambah");
        newButton2.addActionListener(formListener);
        jDialog4.getContentPane().add(newButton2, java.awt.BorderLayout.PAGE_START);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bankList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox3);
        bindingGroup.addBinding(jComboBoxBinding);

        jDialog4.getContentPane().add(jComboBox3, java.awt.BorderLayout.PAGE_END);

        jDialog5.setTitle("HUTANG PEMASUKAN");
        jDialog5.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog5.getContentPane().add(inputPanel4, java.awt.BorderLayout.CENTER);

        newButton3.setText("Tambah");
        newButton3.addActionListener(formListener);
        jDialog5.getContentPane().add(newButton3, java.awt.BorderLayout.PAGE_START);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bankList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox4);
        bindingGroup.addBinding(jComboBoxBinding);

        jDialog5.getContentPane().add(jComboBox4, java.awt.BorderLayout.PAGE_END);

        newDetailButton.setText("Baru");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), newDetailButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        newDetailButton.addActionListener(formListener);

        jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog1.setType(java.awt.Window.Type.POPUP);

        inputPanel1.setLayout(new java.awt.GridLayout(0, 2));
        jDialog1.getContentPane().add(inputPanel1, java.awt.BorderLayout.CENTER);

        newButton.setText("Baru");
        newButton.addActionListener(formListener);
        jDialog1.getContentPane().add(newButton, java.awt.BorderLayout.PAGE_START);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bankList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox5);
        bindingGroup.addBinding(jComboBoxBinding);

        jDialog1.getContentPane().add(jComboBox5, java.awt.BorderLayout.PAGE_END);

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jButton1.setText("Input Peminjaman");
        jButton1.addActionListener(formListener);
        jPanel3.add(jButton1);

        deleteButton.setText("Hapus");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), deleteButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        deleteButton.addActionListener(formListener);
        jPanel3.add(deleteButton);

        jButton4.setText("Simpan");
        jButton4.addActionListener(formListener);
        jPanel3.add(jButton4);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        masterTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        masterTable.setRowHeight(25);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hutangid}"));
        columnBinding.setColumnName("Hutangid");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nama}"));
        columnBinding.setColumnName("Nama");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${alamat}"));
        columnBinding.setColumnName("Alamat");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomorhp}"));
        columnBinding.setColumnName("Nomorhp");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomorktp}"));
        columnBinding.setColumnName("Nomorktp");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jumlahpinjaman}"));
        columnBinding.setColumnName("Jumlahpinjaman");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${sisapinjaman}"));
        columnBinding.setColumnName("Sisapinjaman");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggalpinjam}"));
        columnBinding.setColumnName("Tanggalpinjam");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggallunas}"));
        columnBinding.setColumnName("Tanggallunas");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${lunas}"));
        columnBinding.setColumnName("Lunas");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        masterScrollPane.setViewportView(masterTable);

        jPanel1.add(masterScrollPane, java.awt.BorderLayout.CENTER);

        add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        masterTable.setDefaultEditor(Date.class, new JDateChooserCellEditor());
        masterTable.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
        masterTable.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        masterTable.setDefaultEditor(java.math.BigInteger.class, new app.utils.TablePopupEditor());
        detailTable.setDefaultEditor(Date.class, new JDateChooserCellEditor());
        detailTable.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
        detailTable.setDefaultEditor(java.math.BigInteger.class, new app.utils.TablePopupEditor());
        detailTable.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        detailTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        detailTable.setRowHeight(25);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bayarhutangs}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, eLProperty, detailTable);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("REF");
        columnBinding.setColumnClass(Long.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hutangid.hutangid}"));
        columnBinding.setColumnName("Hutang REF");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggal}"));
        columnBinding.setColumnName("Tanggal");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jumlah}"));
        columnBinding.setColumnName("Jumlah");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jenis}"));
        columnBinding.setColumnName("Jenis");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${transaksi.bankId.namaBank}"));
        columnBinding.setColumnName("Tujuan/Sumber");
        columnBinding.setColumnClass(String.class);
        jTableBinding.setSourceUnreadableValue(java.util.Collections.emptyList());
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        detailScrollPane.setViewportView(detailTable);

        jPanel2.add(detailScrollPane, java.awt.BorderLayout.CENTER);

        jButton2.setText("Pemasukan");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jButton2, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton2.addActionListener(formListener);
        jPanel4.add(jButton2);

        jButton5.setText("Pengeluaran");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jButton5, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jButton5.addActionListener(formListener);
        jPanel4.add(jButton5);

        deleteDetailButton.setText("Hapus");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, detailTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), deleteDetailButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        deleteDetailButton.addActionListener(formListener);
        jPanel4.add(deleteDetailButton);

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(formListener);
        jPanel4.add(refreshButton);

        saveButton.setText("Simpan");
        saveButton.addActionListener(formListener);
        jPanel4.add(saveButton);

        jPanel2.add(jPanel4, java.awt.BorderLayout.NORTH);

        add(jPanel2);

        bindingGroup.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButton1) {
                panelHutang.this.jButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == deleteButton) {
                panelHutang.this.deleteButtonActionPerformed(evt);
            }
            else if (evt.getSource() == jButton4) {
                panelHutang.this.jButton4ActionPerformed(evt);
            }
            else if (evt.getSource() == jButton2) {
                panelHutang.this.jButton2ActionPerformed(evt);
            }
            else if (evt.getSource() == jButton5) {
                panelHutang.this.jButton5ActionPerformed(evt);
            }
            else if (evt.getSource() == deleteDetailButton) {
                panelHutang.this.deleteDetailButtonActionPerformed(evt);
            }
            else if (evt.getSource() == refreshButton) {
                panelHutang.this.refreshButtonActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton) {
                panelHutang.this.saveButtonActionPerformed(evt);
            }
            else if (evt.getSource() == newButton) {
                panelHutang.this.newButtonActionPerformed(evt);
            }
            else if (evt.getSource() == jButton3) {
                panelHutang.this.jButton3ActionPerformed(evt);
            }
            else if (evt.getSource() == newButton2) {
                panelHutang.this.newButton2ActionPerformed(evt);
            }
            else if (evt.getSource() == newButton3) {
                panelHutang.this.newButton3ActionPerformed(evt);
            }
            else if (evt.getSource() == newDetailButton) {
                panelHutang.this.newDetailButtonActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    
    private void deleteDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDetailButtonActionPerformed
        int index = masterTable.getSelectedRow();
        app.table.Hutang h = list.get(masterTable.convertRowIndexToModel(index));
        Collection<app.table.Bayarhutang> bs = h.getBayarhutangs();
        int[] selected = detailTable.getSelectedRows();
        List<app.table.Bayarhutang> toRemove = new ArrayList<app.table.Bayarhutang>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            selected[idx] = detailTable.convertRowIndexToModel(selected[idx]);
            int count = 0;
            Iterator<app.table.Bayarhutang> iter = bs.iterator();
            while (count++ < selected[idx]) {
                iter.next();
            }
            app.table.Bayarhutang b = iter.next();
            toRemove.add(b);
            entityManager.remove(b);
        }
        bs.removeAll(toRemove);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_deleteDetailButtonActionPerformed

    private void newDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDetailButtonActionPerformed
        boolean pem = evt.getSource() == newButton3;
        int index = masterTable.getSelectedRow();
        app.table.Hutang h = list.get(masterTable.convertRowIndexToModel(index));
        Collection<app.table.Bayarhutang> bs = h.getBayarhutangs();
        if (bs == null) {
            bs = new LinkedList<app.table.Bayarhutang>();
            h.setBayarhutangs((List) bs);
        }
        app.table.Bayarhutang b = new app.table.Bayarhutang();
        Saldo ts = new app.table.Saldo();
        if (pem) {
            b = (Bayarhutang) this.inputPanel4.getTarget();
            ts.setBankId((Bank) this.jComboBox4.getSelectedItem()); 
//            System.out.println("Pemasukan");
        } else {
            b = (Bayarhutang) this.inputPanel3.getTarget();
            ts.setBankId((Bank) this.jComboBox3.getSelectedItem()); 
//            System.out.println("Pengeluaran");
        }
        b.setTransaksi(ts);
        entityManager.persist(b);
        b.setHutangid(h);
        bs.add(b);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);
        int row = bs.size() - 1;
        detailTable.setRowSelectionInterval(row, row);
        detailTable.scrollRectToVisible(detailTable.getCellRect(row, 0, true));
    }//GEN-LAST:event_newDetailButtonActionPerformed
    public void Refresh()
    {
            this.refreshButtonActionPerformed(null);
    }
    
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
        bankList.clear();
        bankList.addAll(bankQuery.getResultList());
    }//GEN-LAST:event_refreshButtonActionPerformed

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int[] selected = masterTable.getSelectedRows();
        List<app.table.Hutang> toRemove = new ArrayList<app.table.Hutang>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            app.table.Hutang h = list.get(masterTable.convertRowIndexToModel(selected[idx]));
            toRemove.add(h);
            entityManager.remove(h);
        }
        list.removeAll(toRemove);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        System.out.println("app.view.panel.hutang.panelHutang.newButtonActionPerformed()");
        app.table.Hutang h = (app.table.Hutang) this.inputPanel1.getTarget();
//        app.table.Hutang h = new app.table.Hutang();
        h.setSisapinjaman(h.getJumlahpinjaman());
        BayarhutangPengeluaran bp = new app.table.BayarhutangPengeluaran();
        Saldo ts = new app.table.Saldo();
        ts.setBankId((Bank) jComboBox5.getSelectedItem());
        bp.setTransaksi(ts);
        bp.setJumlah(h.getJumlahpinjaman());
        bp.setHutangid(h);        
        List<app.table.Bayarhutang> bh = h.getBayarhutangs();
        bh = bh == null ? new ArrayList<>() : bh;
        bh.add((app.table.Bayarhutang)bp);
        h.setBayarhutangs(bh);        
        entityManager.persist(bp);
        entityManager.persist(h);
        list.add(h);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
        jDialog1.hide();
//        this.refreshButtonActionPerformed(evt);
    }//GEN-LAST:event_newButtonActionPerformed
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
//            list.clear();
//            list.addAll(query.getResultList());
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<app.table.Hutang> merged = new ArrayList<app.table.Hutang>(list.size());
            for (app.table.Hutang h : list) {
                merged.add(entityManager.merge(h));
            }
            list.clear();
            list.addAll(merged);
        }
//        this.refreshButtonActionPerformed(evt);
    }//GEN-LAST:event_saveButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.jDialog1.setSize(800,600);
        this.jDialog1.setLocationRelativeTo(null);
        this.jDialog1.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.jDialog5.setSize(400,400);
        this.jDialog5.setLocationRelativeTo(null);
        this.jDialog5.show();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.saveButtonActionPerformed(evt);
        this.refreshButtonActionPerformed(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.saveButtonActionPerformed(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.jDialog4.setSize(400,400);
        this.jDialog4.setLocationRelativeTo(null);
        this.jDialog4.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void newButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton2ActionPerformed
        // TODO add your handling code here:
        this.newDetailButtonActionPerformed(evt);
        this.jDialog4.hide();
    }//GEN-LAST:event_newButton2ActionPerformed

    private void newButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton3ActionPerformed
        // TODO add your handling code here:
        this.newDetailButtonActionPerformed(evt);
        this.jDialog5.hide();
    }//GEN-LAST:event_newButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.util.List<app.table.Bank> bankList;
    private javax.persistence.Query bankQuery;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteDetailButton;
    private javax.swing.JScrollPane detailScrollPane;
    private javax.swing.JTable detailTable;
    private javax.persistence.EntityManager entityManager;
    private app.utils.inputPanel inputPanel1;
    private app.utils.inputPanel inputPanel3;
    private app.utils.inputPanel inputPanel4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private java.util.List<app.table.Hutang> list;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JButton newButton;
    private javax.swing.JButton newButton2;
    private javax.swing.JButton newButton3;
    private javax.swing.JButton newDetailButton;
    private javax.persistence.Query query;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton saveButton;
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
            java.util.logging.Logger.getLogger(panelHutang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(panelHutang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(panelHutang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(panelHutang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new panelHutang());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    
}
