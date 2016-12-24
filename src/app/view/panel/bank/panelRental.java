/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.panel.bank;

import app.table.Bank;
import app.table.Bayarrental;
import app.table.Laporan;
import app.table.Mobilrental;
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
public class panelRental extends JPanel {
    
    public panelRental() {
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
        query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT r FROM Rental r");
        list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
        query1 = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT m FROM Mobilrental m");
        jDialog1 = new javax.swing.JDialog();
        inputPanel1 = new app.utils.inputPanel(app.table.Rental.class);
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        newButton = new javax.swing.JButton();
        mobilrentalQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT m FROM Mobilrental m");
        mobilrentalList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(mobilrentalQuery.getResultList());
        jDialog2 = new javax.swing.JDialog();
        inputPanel2 = new app.utils.inputPanel(app.table.BayarRentalPemasukan.class);
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        newButton2 = new javax.swing.JButton();
        jDialog3 = new javax.swing.JDialog();
        inputPanel3 = new app.utils.inputPanel(app.table.BayarRentalPenngeluaran.class);
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        newButton3 = new javax.swing.JButton();
        bankQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT b FROM Bank b");
        bankList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(bankQuery.getResultList());
        newDetailButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelMobilRental1 = new app.view.panel.bank.PanelMobilRental();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        newButton1 = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        deleteDetailButton = new javax.swing.JButton();
        newDetailButton2 = new javax.swing.JButton();
        newDetailButton1 = new javax.swing.JButton();
        detailScrollPane = new javax.swing.JScrollPane();
        detailTable = new javax.swing.JTable();

        FormListener formListener = new FormListener();

        jDialog1.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog1.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setText("MOBIL");
        inputPanel1.add(jLabel1);

        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboBox1.setMinimumSize(new java.awt.Dimension(56, 30));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${list}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, panelMobilRental1, eLProperty, jComboBox1);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBox1.addActionListener(formListener);
        inputPanel1.add(jComboBox1);

        newButton.setText("Tambah");
        newButton.addActionListener(formListener);
        inputPanel1.add(newButton);

        jDialog1.getContentPane().add(inputPanel1);

        jDialog2.setTitle("Rental Pemasukan");
        jDialog2.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog2.setType(java.awt.Window.Type.POPUP);

        jLabel2.setText("Tujuan");
        inputPanel2.add(jLabel2);

        jComboBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboBox2.setMinimumSize(new java.awt.Dimension(56, 30));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bankList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox2);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBox2.addActionListener(formListener);
        inputPanel2.add(jComboBox2);

        newButton2.setText("Tambah");
        newButton2.addActionListener(formListener);
        inputPanel2.add(newButton2);

        jDialog2.getContentPane().add(inputPanel2, java.awt.BorderLayout.CENTER);

        jDialog3.setTitle("Rental Pengeluaran");

        jLabel3.setText("Tujuan");
        inputPanel3.add(jLabel3);

        jComboBox3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboBox3.setMinimumSize(new java.awt.Dimension(56, 30));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${bankList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jComboBox3);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBox3.addActionListener(formListener);
        inputPanel3.add(jComboBox3);

        newButton3.setText("Tambah");
        newButton3.addActionListener(formListener);
        inputPanel3.add(newButton3);

        jDialog3.getContentPane().add(inputPanel3, java.awt.BorderLayout.CENTER);

        newDetailButton.setText("Simpan");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), newDetailButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        newDetailButton.addActionListener(formListener);

        setLayout(new java.awt.GridLayout(1, 0));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPane1.addMouseListener(formListener);
        jTabbedPane1.addTab("MOBIL RENTAL", panelMobilRental1);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        newButton1.setText("Baru");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, panelMobilRental1, org.jdesktop.beansbinding.ELProperty.create("${list != null}"), newButton1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        newButton1.addActionListener(formListener);
        jPanel4.add(newButton1);

        deleteButton.setText("Hapus Transaksi");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), deleteButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        deleteButton.addActionListener(formListener);
        jPanel4.add(deleteButton);

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(formListener);
        jPanel4.add(refreshButton);

        saveButton.setText("Simpan");
        saveButton.addActionListener(formListener);
        jPanel4.add(saveButton);

        jPanel3.add(jPanel4);

        masterTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        masterTable.setCellSelectionEnabled(true);
        masterTable.setRowHeight(25);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${rentalid}"));
        columnBinding.setColumnName("Rentalid");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglmulai}"));
        columnBinding.setColumnName("Tglmulai");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglselesai}"));
        columnBinding.setColumnName("Tglselesai");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemakai}"));
        columnBinding.setColumnName("Pemakai");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jammulai0}"));
        columnBinding.setColumnName("Jammulai0");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jamSelesai0}"));
        columnBinding.setColumnName("Jam Selesai0");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${driver}"));
        columnBinding.setColumnName("Driver");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${mobilrental}"));
        columnBinding.setColumnName("Mobilrental");
        columnBinding.setColumnClass(app.table.Mobilrental.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        masterScrollPane.setViewportView(masterTable);
        if (masterTable.getColumnModel().getColumnCount() > 0) {
            masterTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            masterTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        jPanel3.add(masterScrollPane);

        deleteDetailButton.setText("Hapus List");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, detailTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), deleteDetailButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        deleteDetailButton.addActionListener(formListener);
        jPanel1.add(deleteDetailButton);

        newDetailButton2.setText("Pemasukan");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), newDetailButton2, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        newDetailButton2.addActionListener(formListener);
        jPanel1.add(newDetailButton2);

        newDetailButton1.setText("Pengeluaran");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), newDetailButton1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        newDetailButton1.addActionListener(formListener);
        jPanel1.add(newDetailButton1);

        jPanel3.add(jPanel1);

        detailTable.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
        detailTable.setDefaultEditor(java.math.BigInteger.class, new app.utils.TablePopupEditor());
        masterTable.setDefaultEditor(Integer.class, new app.utils.TablePopupEditor());
        detailTable.setDefaultEditor(Date.class, new JDateChooserCellEditor());
        detailTable.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        masterTable.setDefaultEditor(String.class, new app.utils.TablePopupEditor());
        masterTable.setDefaultEditor(Date.class, new JDateChooserCellEditor());

        detailTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        detailTable.setRowHeight(25);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bayarrentalList}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, eLProperty, detailTable);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("REF");
        columnBinding.setColumnClass(Long.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${rentalId.rentalid}"));
        columnBinding.setColumnName("REF Rental");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggal}"));
        columnBinding.setColumnName("Tanggal");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jumlah}"));
        columnBinding.setColumnName("Jumlah");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jenis}"));
        columnBinding.setColumnName("Dtype");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${transaksi.bankId.namaBank}"));
        columnBinding.setColumnName("Tujuan/Sumber");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        jTableBinding.setSourceUnreadableValue(java.util.Collections.emptyList());
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        detailScrollPane.setViewportView(detailTable);

        jPanel3.add(detailScrollPane);

        jTabbedPane1.addTab("TRANSAKSI RENTAL", jPanel3);

        jTabbedPane1.setSelectedIndex(1);

        add(jTabbedPane1);

        bindingGroup.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == newButton1) {
                panelRental.this.newButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == deleteButton) {
                panelRental.this.deleteButtonActionPerformed(evt);
            }
            else if (evt.getSource() == deleteDetailButton) {
                panelRental.this.deleteDetailButtonActionPerformed(evt);
            }
            else if (evt.getSource() == newDetailButton) {
                panelRental.this.newDetailButtonActionPerformed(evt);
            }
            else if (evt.getSource() == newDetailButton2) {
                panelRental.this.newDetailButton2ActionPerformed(evt);
            }
            else if (evt.getSource() == newDetailButton1) {
                panelRental.this.newDetailButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == refreshButton) {
                panelRental.this.refreshButtonActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton) {
                panelRental.this.saveButtonActionPerformed(evt);
            }
            else if (evt.getSource() == jComboBox1) {
                panelRental.this.jComboBox1ActionPerformed(evt);
            }
            else if (evt.getSource() == newButton) {
                panelRental.this.newButtonActionPerformed(evt);
            }
            else if (evt.getSource() == jComboBox2) {
                panelRental.this.jComboBox2ActionPerformed(evt);
            }
            else if (evt.getSource() == newButton2) {
                panelRental.this.newButton2ActionPerformed(evt);
            }
            else if (evt.getSource() == jComboBox3) {
                panelRental.this.jComboBox3ActionPerformed(evt);
            }
            else if (evt.getSource() == newButton3) {
                panelRental.this.newButton3ActionPerformed(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jTabbedPane1) {
                panelRental.this.jTabbedPane1MouseClicked(evt);
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    
    private void deleteDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDetailButtonActionPerformed
        int index = masterTable.getSelectedRow();
        app.table.Rental r = list.get(masterTable.convertRowIndexToModel(index));
        Collection<app.table.Bayarrental> bs = r.getBayarrentalList();
        int[] selected = detailTable.getSelectedRows();
        List<app.table.Bayarrental> toRemove = new ArrayList<app.table.Bayarrental>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            selected[idx] = detailTable.convertRowIndexToModel(selected[idx]);
            int count = 0;
            Iterator<app.table.Bayarrental> iter = bs.iterator();
            while (count++ < selected[idx]) {
                iter.next();
            }
            app.table.Bayarrental b = iter.next();
            toRemove.add(b);
            entityManager.remove(b);
        }
        bs.removeAll(toRemove);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_deleteDetailButtonActionPerformed

    private void newDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDetailButtonActionPerformed
        boolean pem = evt.getSource() == newButton2;
        int index = masterTable.getSelectedRow();
        app.table.Rental r = list.get(masterTable.convertRowIndexToModel(index));
        Collection<app.table.Bayarrental> bs = r.getBayarrentalList();
        if (bs == null) {
            bs = new LinkedList<app.table.Bayarrental>();
            r.setBayarrentalList((List) bs);
        }
//        Laporan ran = new Laporan();
        app.table.Bayarrental b = new app.table.Bayarrental();
        Saldo ts = new app.table.Saldo();
        if (pem) {
            b = (Bayarrental) this.inputPanel2.getTarget();
            ts.setBankId((Bank) this.jComboBox2.getSelectedItem()); 
//            System.out.println("Pemasukan");
        } else {
            b = (Bayarrental) this.inputPanel3.getTarget();
            ts.setBankId((Bank) this.jComboBox3.getSelectedItem()); 
//            System.out.println("Pengeluaran");
        }
        b.setTransaksi(ts);
        entityManager.persist(b);
        b.setRentalId(r);
        bs.add(b);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);
        int row = bs.size() - 1;
        detailTable.setRowSelectionInterval(row, row);
        detailTable.scrollRectToVisible(detailTable.getCellRect(row, 0, true));
    }//GEN-LAST:event_newDetailButtonActionPerformed
    
    @SuppressWarnings("unchecked")
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        entityManager.getTransaction().rollback();
        entityManager.getTransaction().begin();
        java.util.List data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int[] selected = masterTable.getSelectedRows();
        List<app.table.Rental> toRemove = new ArrayList<app.table.Rental>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            app.table.Rental r = list.get(masterTable.convertRowIndexToModel(selected[idx]));
            toRemove.add(r);
            entityManager.remove(r);
        }
        list.removeAll(toRemove);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
//        app.table.Rental r = new app.table.Rental();
        app.table.Rental r = (app.table.Rental) this.inputPanel1.getTarget();
        Object m = this.jComboBox1.getSelectedItem();
        r.setMobilrental((Mobilrental) m);
        entityManager.persist(r);
        list.add(r);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
        this.jDialog1.hide();
    }//GEN-LAST:event_newButtonActionPerformed
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<app.table.Rental> merged = new ArrayList<app.table.Rental>(list.size());
            for (app.table.Rental r : list) {
                merged.add(entityManager.merge(r));
            }
            list.clear();
            list.addAll(merged);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void newButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton1ActionPerformed
        this.jDialog1.setSize(500, 600);
        this.jDialog1.setLocationRelativeTo(null);
        this.jDialog1.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_newButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void newDetailButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDetailButton1ActionPerformed
        this.jDialog3.setSize(300, 400);
        this.jDialog3.setLocationRelativeTo(null);
        this.jDialog3.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_newDetailButton1ActionPerformed

    private void newDetailButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDetailButton2ActionPerformed
        this.jDialog2.setSize(300, 400);
        this.jDialog2.setLocationRelativeTo(null);
        this.jDialog2.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_newDetailButton2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void newButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton2ActionPerformed
        this.newDetailButtonActionPerformed(evt);
        jDialog2.hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_newButton2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void newButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton3ActionPerformed
        this.newDetailButtonActionPerformed(evt);
        jDialog3.hide();
        // TODO add your handling code here:
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
    private app.utils.inputPanel inputPanel2;
    private app.utils.inputPanel inputPanel3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private java.util.List<app.table.Rental> list;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private java.util.List<app.table.Mobilrental> mobilrentalList;
    private javax.persistence.Query mobilrentalQuery;
    private javax.swing.JButton newButton;
    private javax.swing.JButton newButton1;
    private javax.swing.JButton newButton2;
    private javax.swing.JButton newButton3;
    private javax.swing.JButton newDetailButton;
    private javax.swing.JButton newDetailButton1;
    private javax.swing.JButton newDetailButton2;
    private app.view.panel.bank.PanelMobilRental panelMobilRental1;
    private javax.persistence.Query query;
    private javax.persistence.Query query1;
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
            java.util.logging.Logger.getLogger(panelRental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(panelRental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(panelRental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(panelRental.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new panelRental());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }
    public void Refresh()
    {
            this.refreshButtonActionPerformed(null);
            this.bankList.clear();
            this.bankList.addAll(this.bankQuery.getResultList());
    }
}
