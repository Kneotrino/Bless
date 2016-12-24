/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.panel.hutang;

import app.table.Bank;
import app.table.Bayarhutang;
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
        hutang = new javax.swing.JDialog();
        newButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        bankQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT b FROM Bank b");
        bankList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(bankQuery.getResultList());
        jComboBox2 = new javax.swing.JComboBox<>();
        jDialog4 = new javax.swing.JDialog();
        inputPanel3 = new app.utils.inputPanel(app.table.BayarhutangPengeluaran.class);
        newButton2 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jDialog5 = new javax.swing.JDialog();
        inputPanel4 = new app.utils.inputPanel(app.table.BayarhutangPengeluaran.class);
        newButton3 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        newDetailButton = new javax.swing.JButton();
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

        hutang.setTitle("Data");
        hutang.setAlwaysOnTop(true);
        hutang.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        hutang.getContentPane().setLayout(new java.awt.GridLayout(0, 2));

        newButton.setText("Baru");
        newButton.addActionListener(formListener);
        hutang.getContentPane().add(newButton);

        jButton3.setText("Simpan");
        jButton3.addActionListener(formListener);
        hutang.getContentPane().add(jButton3);

        jLabel10.setText("Ref Hutang");
        hutang.getContentPane().add(jLabel10);

        jTextField11.setEnabled(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.hutangid}"), jTextField11, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        hutang.getContentPane().add(jTextField11);

        jLabel1.setText("Nama");
        hutang.getContentPane().add(jLabel1);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.nama}"), jTextField2, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jTextField2, org.jdesktop.beansbinding.BeanProperty.create("editable"));
        bindingGroup.addBinding(binding);

        hutang.getContentPane().add(jTextField2);

        jLabel2.setText("Alamat");
        hutang.getContentPane().add(jLabel2);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.alamat}"), jTextField3, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jTextField3, org.jdesktop.beansbinding.BeanProperty.create("editable"));
        bindingGroup.addBinding(binding);

        hutang.getContentPane().add(jTextField3);

        jLabel3.setText("Nomor HP");
        hutang.getContentPane().add(jLabel3);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.nomorhp}"), jTextField4, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jTextField4, org.jdesktop.beansbinding.BeanProperty.create("editable"));
        bindingGroup.addBinding(binding);

        hutang.getContentPane().add(jTextField4);

        jLabel4.setText("Nomot KTP");
        hutang.getContentPane().add(jLabel4);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.nomorktp}"), jTextField5, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jTextField5, org.jdesktop.beansbinding.BeanProperty.create("editable"));
        bindingGroup.addBinding(binding);

        hutang.getContentPane().add(jTextField5);

        jLabel5.setText("Pinjaman");
        hutang.getContentPane().add(jLabel5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.jumlahpinjaman}"), jTextField6, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jTextField6, org.jdesktop.beansbinding.BeanProperty.create("editable"));
        bindingGroup.addBinding(binding);

        hutang.getContentPane().add(jTextField6);

        jLabel7.setText("Sisa Pinjaman");
        hutang.getContentPane().add(jLabel7);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.sisapinjaman}"), jTextField8, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jTextField8, org.jdesktop.beansbinding.BeanProperty.create("editable"));
        bindingGroup.addBinding(binding);

        hutang.getContentPane().add(jTextField8);

        jLabel6.setText("Tanggal Pinjaman");
        hutang.getContentPane().add(jLabel6);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.tanggalpinjam}"), jDateChooser1, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jDateChooser1, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        hutang.getContentPane().add(jDateChooser1);

        jLabel8.setText("Tanggal Lunas");
        hutang.getContentPane().add(jLabel8);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.tanggallunas}"), jDateChooser2, org.jdesktop.beansbinding.BeanProperty.create("date"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jDateChooser2, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        hutang.getContentPane().add(jDateChooser2);

        jLabel9.setText("Keterangan");
        hutang.getContentPane().add(jLabel9);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.keterangan}"), jTextField10, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), jTextField10, org.jdesktop.beansbinding.BeanProperty.create("editable"));
        bindingGroup.addBinding(binding);

        hutang.getContentPane().add(jTextField10);

        jLabel18.setText("Tujuan Pinjaman");
        hutang.getContentPane().add(jLabel18);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KAS", "BRI", "BCA", " " }));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${resultList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, bankQuery, eLProperty, jComboBox1);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBox1.addActionListener(formListener);
        hutang.getContentPane().add(jComboBox1);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KAS", "BRI", "BCA", " " }));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${resultList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, bankQuery, eLProperty, jComboBox2);
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

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), newDetailButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        newDetailButton.addActionListener(formListener);

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jButton1.setText("Edit/Tambah");
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
        masterTable.setColumnSelectionAllowed(true);
        masterTable.setRowHeight(25);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hutangid}"));
        columnBinding.setColumnName("Hutangid");
        columnBinding.setColumnClass(Integer.class);
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
        detailTable.setColumnSelectionAllowed(false);
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
            else if (evt.getSource() == newDetailButton) {
                panelHutang.this.newDetailButtonActionPerformed(evt);
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
            else if (evt.getSource() == jComboBox1) {
                panelHutang.this.jComboBox1ActionPerformed(evt);
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
            ts.setBankId((Bank) this.jComboBox2.getSelectedItem()); 
            System.out.println("Pemasukan");
        } else {
            b = (Bayarhutang) this.inputPanel3.getTarget();
            ts.setBankId((Bank) this.jComboBox3.getSelectedItem()); 
            System.out.println("Pengeluaran");
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
        app.table.Hutang h = new app.table.Hutang();
        entityManager.persist(h);
        list.add(h);
        int row = list.size() - 1;
        masterTable.setRowSelectionInterval(row, row);
        masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
    }//GEN-LAST:event_newButtonActionPerformed
    
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
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
        this.hutang.setSize(400,400);
        this.hutang.setLocationRelativeTo(null);
        this.hutang.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.jDialog5.setSize(400,400);
        this.jDialog5.setLocationRelativeTo(null);
        this.jDialog5.show();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.hutang.hide();
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

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.util.List<app.table.Bank> bankList;
    private javax.persistence.Query bankQuery;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteDetailButton;
    private javax.swing.JScrollPane detailScrollPane;
    private javax.swing.JTable detailTable;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JDialog hutang;
    private app.utils.inputPanel inputPanel3;
    private app.utils.inputPanel inputPanel4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
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
