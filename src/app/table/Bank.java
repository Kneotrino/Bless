/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import app.ListUrutan;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "BANK", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bank.findAll", query = "SELECT b FROM Bank b"),
    @NamedQuery(name = "Bank.findByBankId", query = "SELECT b FROM Bank b WHERE b.bankId = :bankId"),
    @NamedQuery(name = "Bank.findByNorek", query = "SELECT b FROM Bank b WHERE b.norek = :norek"),
    @NamedQuery(name = "Bank.findByNama", query = "SELECT b FROM Bank b WHERE b.nama = :nama"),
    @NamedQuery(name = "Bank.findByAlamat", query = "SELECT b FROM Bank b WHERE b.alamat = :alamat"),
    @NamedQuery(name = "Bank.findByNomorHp", query = "SELECT b FROM Bank b WHERE b.nomorHp = :nomorHp"),
    @NamedQuery(name = "Bank.findByNomorKtp", query = "SELECT b FROM Bank b WHERE b.nomorKtp = :nomorKtp"),
    @NamedQuery(name = "Bank.findByTotalSaldo", query = "SELECT b FROM Bank b WHERE b.totalSaldo = :totalSaldo"),
    @NamedQuery(name = "Bank.findByTotalDebit", query = "SELECT b FROM Bank b WHERE b.totalDebit = :totalDebit"),
    @NamedQuery(name = "Bank.findByTotalKredit", query = "SELECT b FROM Bank b WHERE b.totalKredit = :totalKredit"),
    @NamedQuery(name = "Bank.findByNamaBank", query = "SELECT b FROM Bank b WHERE b.namaBank = :namaBank")})
 @ListUrutan({"namaBank","norek","nama","alamat","nomorHp","nomorKtp","totalDebit","totalKredit" })
public class Bank implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "BANK_ID", nullable = false)
    private Integer bankId;
    @Column(name = "NOREK", length = 32)
    private String norek;
    @Column(name = "NAMA", length = 64)
    private String nama;
    @Column(name = "ALAMAT", length = 64)
    private String alamat;
    @Column(name = "NOMOR_HP", length = 64)
    private String nomorHp;
    @Column(name = "NOMOR_KTP", length = 64)
    private String nomorKtp;
    @Column(name = "TOTAL_SALDO")
    private BigInteger totalSaldo;
    @Column(name = "TOTAL_DEBIT")
    private BigInteger totalDebit;
    @Column(name = "TOTAL_KREDIT")
    private BigInteger totalKredit;
    @Column(name = "NAMA_BANK", length = 32)
    private String namaBank;
    @OneToMany(mappedBy = "bankId", fetch = FetchType.EAGER , cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    @OrderBy("tanggal ASC")
    private List<Saldo> saldoList;

    public Bank() {
    }

    public Bank(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        Integer oldBankId = this.bankId;
        this.bankId = bankId;
        changeSupport.firePropertyChange("bankId", oldBankId, bankId);
    }

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        String oldNorek = this.norek;
        this.norek = norek;
        changeSupport.firePropertyChange("norek", oldNorek, norek);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        String oldAlamat = this.alamat;
        this.alamat = alamat;
        changeSupport.firePropertyChange("alamat", oldAlamat, alamat);
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        String oldNomorHp = this.nomorHp;
        this.nomorHp = nomorHp;
        changeSupport.firePropertyChange("nomorHp", oldNomorHp, nomorHp);
    }

    public String getNomorKtp() {
        return nomorKtp;
    }

    public void setNomorKtp(String nomorKtp) {
        String oldNomorKtp = this.nomorKtp;
        this.nomorKtp = nomorKtp;
        changeSupport.firePropertyChange("nomorKtp", oldNomorKtp, nomorKtp);
    }

    public BigInteger getTotalSaldo() {
        return totalSaldo;
    }

    public void setTotalSaldo(BigInteger totalSaldo) {
        BigInteger oldTotalSaldo = this.totalSaldo;
        this.totalSaldo = totalSaldo;
        changeSupport.firePropertyChange("totalSaldo", oldTotalSaldo, totalSaldo);
    }

    public BigInteger getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(BigInteger totalDebit) {
        BigInteger oldTotalDebit = this.totalDebit;
        this.totalDebit = totalDebit;
        changeSupport.firePropertyChange("totalDebit", oldTotalDebit, totalDebit);
    }

    public BigInteger getTotalKredit() {
        return totalKredit;
    }

    public void setTotalKredit(BigInteger totalKredit) {
        BigInteger oldTotalKredit = this.totalKredit;
        this.totalKredit = totalKredit;
        changeSupport.firePropertyChange("totalKredit", oldTotalKredit, totalKredit);
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        String oldNamaBank = this.namaBank;
        this.namaBank = namaBank;
        changeSupport.firePropertyChange("namaBank", oldNamaBank, namaBank);
    }

    @XmlTransient
    public List<Saldo> getSaldoList() {
//        this.saldoList = org.jdesktop.observablecollections.ObservableCollections.observableList(this.saldoList);
        return saldoList;
    }
//    public BigInteger getTotalSaldoNow()
//    {
//        BigInteger temp = BigInteger.ZERO;
//        for (Saldo saldo : saldoList) {
//            temp = temp.add(saldo.getLaporan().getPemasukan());
//            temp = temp.subtract(saldo.getLaporan().getPengeluaran());
//        }
//        return temp;
//    }

    public void setSaldoList(List<Saldo> saldoList) {
//        this.saldoList = org.jdesktop.observablecollections.ObservableCollections.observableList(this.saldoList);
        List<Saldo> oldSaldo = this.saldoList; 
        this.saldoList = saldoList;
        changeSupport.firePropertyChange("saldo", oldSaldo, saldoList);
//        this.saldoList = saldoList;
//        BigInteger temp = BigInteger.ZERO;
//        for (Saldo saldo : saldoList) {
//            temp = temp.add(saldo.getLaporan().getPemasukan());
//            temp = temp.subtract(saldo.getLaporan().getPengeluaran());
//        }
//        setTotalSaldo(temp);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankId != null ? bankId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bank)) {
            return false;
        }
        Bank other = (Bank) object;
        if ((this.bankId == null && other.bankId != null) || (this.bankId != null && !this.bankId.equals(other.bankId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "[ Tujuan/Sumber ="+namaBank+";REF=" + bankId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }   
    @PostPersist
    public void PostSimpan()
    {    
        javax.swing.JOptionPane.showMessageDialog(null,  "Berhasil Menyimpan\nNama Bank ="+ namaBank);
    }
    @PostUpdate
    public void PostUpdate()
    {    
        javax.swing.JOptionPane.showMessageDialog(null,  "Berhasil Update\nNama Bank ="+ namaBank);
    }
    @PostRemove
    public void PostHapus()
    {    
        javax.swing.JOptionPane.showMessageDialog(null,  "Berhasil Menghapus\nNama Bank ="+ namaBank);
    }
}
