/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.panel.jasa;

import com.toedter.calendar.JDateChooserCellEditor;
import java.awt.EventQueue;
import java.beans.Beans;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author SEED
 */
public class panelJasa extends JPanel {
    
    public panelJasa() {
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
        query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT B FROM Bpkbtitipan B");
        list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
        jDialog2 = new javax.swing.JDialog();
        inputPanel1 = new app.utils.inputPanel(app.table.Bpkbtitipan.class);
        newButton = new javax.swing.JButton();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        detailScrollPane = new javax.swing.JScrollPane();
        detailTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        newDetailButton = new javax.swing.JButton();
        deleteDetailButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        jDialog2.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialog2.getContentPane().add(inputPanel1, java.awt.BorderLayout.CENTER);

        newButton.setText("Simpan");
        newButton.addActionListener(formListener);
        jDialog2.getContentPane().add(newButton, java.awt.BorderLayout.PAGE_START);

        setLayout(new java.awt.BorderLayout());

        masterTable.setDefaultEditor(Date.class, new JDateChooserCellEditor());
        masterTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        masterTable.setRowHeight(25);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bpkbId}"));
        columnBinding.setColumnName("Ref");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${anBpkb}"));
        columnBinding.setColumnName("An Bpkb");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${ket}"));
        columnBinding.setColumnName("Ket");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noBpkb}"));
        columnBinding.setColumnName("No Bpkb");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${noPolisiAktif}"));
        columnBinding.setColumnName("No Polisi Aktif");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${posisi}"));
        columnBinding.setColumnName("Posisi");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${status}"));
        columnBinding.setColumnName("Status");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${stnk}"));
        columnBinding.setColumnName("Stnk");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglBbn}"));
        columnBinding.setColumnName("Tgl Bbn");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglCb}"));
        columnBinding.setColumnName("Tgl Cb");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglKembaliBbn}"));
        columnBinding.setColumnName("Tgl Kembali Bbn");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglKembaliCb}"));
        columnBinding.setColumnName("Tgl Kembali Cb");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglLeasing}"));
        columnBinding.setColumnName("Tgl Leasing");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tglTerima}"));
        columnBinding.setColumnName("Tgl Terima");
        columnBinding.setColumnClass(java.util.Date.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        masterScrollPane.setViewportView(masterTable);

        add(masterScrollPane, java.awt.BorderLayout.CENTER);

        detailTable.setDefaultEditor(Date.class, new JDateChooserCellEditor());

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${selectedElement.bayarjasaList}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, eLProperty, detailTable);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bayarpihutangid}"));
        columnBinding.setColumnName("Ref");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pembayaran}"));
        columnBinding.setColumnName("Pembayaran");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tanggal}"));
        columnBinding.setColumnName("Tanggal");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bpkbtitipid.bpkbId}"));
        columnBinding.setColumnName("Ref bpkb");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        jTableBinding.setSourceUnreadableValue(java.util.Collections.emptyList());
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        detailScrollPane.setViewportView(detailTable);

        add(detailScrollPane, java.awt.BorderLayout.EAST);

        jButton2.setText("Baru");
        jButton2.addActionListener(formListener);
        jPanel1.add(jButton2);

        deleteButton.setText("Delete");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), deleteButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        deleteButton.addActionListener(formListener);
        jPanel1.add(deleteButton);

        newDetailButton.setText("New");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), newDetailButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        newDetailButton.addActionListener(formListener);
        jPanel1.add(newDetailButton);

        deleteDetailButton.setText("Delete");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, detailTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), deleteDetailButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        deleteDetailButton.addActionListener(formListener);
        jPanel1.add(deleteDetailButton);

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(formListener);
        jPanel1.add(refreshButton);

        saveButton.setText("Save");
        saveButton.addActionListener(formListener);
        jPanel1.add(saveButton);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        bindingGroup.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == newButton) {
                panelJasa.this.newButtonActionPerformed(evt);
            }
            else if (evt.getSource() == deleteButton) {
                panelJasa.this.deleteButtonActionPerformed(evt);
            }
            else if (evt.getSource() == newDetailButton) {
                panelJasa.this.newDetailButtonActionPerformed(evt);
            }
            else if (evt.getSource() == deleteDetailButton) {
                panelJasa.this.deleteDetailButtonActionPerformed(evt);
            }
            else if (evt.getSource() == refreshButton) {
                panelJasa.this.refreshButtonActionPerformed(evt);
            }
            else if (evt.getSource() == saveButton) {
                panelJasa.this.saveButtonActionPerformed(evt);
            }
            else if (evt.getSource() == jButton2) {
                panelJasa.this.jButton2ActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    
    private void deleteDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDetailButtonActionPerformed
        int index = masterTable.getSelectedRow();
        app.table.Bpkbtitipan B = list.get(masterTable.convertRowIndexToModel(index));
        Collection<app.table.Bayarjasa> bs = B.getBayarjasaList();
        int[] selected = detailTable.getSelectedRows();
        List<app.table.Bayarjasa> toRemove = new ArrayList<app.table.Bayarjasa>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            selected[idx] = detailTable.convertRowIndexToModel(selected[idx]);
            int count = 0;
            Iterator<app.table.Bayarjasa> iter = bs.iterator();
            while (count++ < selected[idx]) {
                iter.next();
            }
            app.table.Bayarjasa b = iter.next();
            toRemove.add(b);
            entityManager.remove(b);
        }
        bs.removeAll(toRemove);
        masterTable.clearSelection();
        masterTable.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_deleteDetailButtonActionPerformed

    private void newDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDetailButtonActionPerformed
        int index = masterTable.getSelectedRow();
        app.table.Bpkbtitipan B = list.get(masterTable.convertRowIndexToModel(index));
        Collection<app.table.Bayarjasa> bs = B.getBayarjasaList();
        if (bs == null) {
            bs = new LinkedList<app.table.Bayarjasa>();
            B.setBayarjasaList((List) bs);
        }
        app.table.Bayarjasa b = new app.table.Bayarjasa();
        entityManager.persist(b);
        b.setBpkbtitipid(B);
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
        java.util.Collection data = query.getResultList();
        for (Object entity : data) {
            entityManager.refresh(entity);
        }
        list.clear();
        list.addAll(data);
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int[] selected = masterTable.getSelectedRows();
        List<app.table.Bpkbtitipan> toRemove = new ArrayList<app.table.Bpkbtitipan>(selected.length);
        for (int idx = 0; idx < selected.length; idx++) {
            app.table.Bpkbtitipan B = list.get(masterTable.convertRowIndexToModel(selected[idx]));
            toRemove.add(B);
            entityManager.remove(B);
        }
        list.removeAll(toRemove);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        app.table.Bpkbtitipan B = (app.table.Bpkbtitipan) this.inputPanel1.getTarget() ;
        entityManager.persist(B);
        list.add(B);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));

        this.jDialog2.setSize(500, 600);
        this.jDialog2.setLocationRelativeTo(null);
        this.jDialog2.show();
    }//GEN-LAST:event_newButtonActionPerformed
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
        } catch (RollbackException rex) {
            rex.printStackTrace();
            entityManager.getTransaction().begin();
            List<app.table.Bpkbtitipan> merged = new ArrayList<app.table.Bpkbtitipan>(list.size());
            for (app.table.Bpkbtitipan B : list) {
                merged.add(entityManager.merge(B));
            }
            list.clear();
            list.addAll(merged);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.jDialog2.setSize(500, 600);
        this.jDialog2.setLocationRelativeTo(null);
        this.jDialog2.show();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteDetailButton;
    private javax.swing.JScrollPane detailScrollPane;
    private javax.swing.JTable detailTable;
    private javax.persistence.EntityManager entityManager;
    private app.utils.inputPanel inputPanel1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JPanel jPanel1;
    private java.util.List<app.table.Bpkbtitipan> list;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JButton newButton;
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
            java.util.logging.Logger.getLogger(panelJasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(panelJasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(panelJasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(panelJasa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new panelJasa());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    
}
