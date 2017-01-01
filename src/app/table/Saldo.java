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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "SALDO", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Saldo.findAll", query = "SELECT s FROM Saldo s"),
    @NamedQuery(name = "Saldo.findBySaldoId", query = "SELECT s FROM Saldo s WHERE s.saldoId = :saldoId"),
    @NamedQuery(name = "Saldo.findByTanggal", query = "SELECT s FROM Saldo s WHERE s.tanggal = :tanggal"),
    @NamedQuery(name = "Saldo.findByKredit", query = "SELECT s FROM Saldo s WHERE s.kredit = :kredit"),
    @NamedQuery(name = "Saldo.findByDebit", query = "SELECT s FROM Saldo s WHERE s.debit = :debit"),
    @NamedQuery(name = "Saldo.findBySaldo", query = "SELECT s FROM Saldo s WHERE s.saldo = :saldo")})
@ListUrutan({"tanggal","keterangan","jumlah"})
public class Saldo implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "SALDO_ID", nullable = false)
    private Integer saldoId;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @Column(name = "KREDIT")
    private long kredit;
    @Column(name = "DEBIT")
    private long debit;
    @Column(name = "SALDO")
    private long saldo;
    @Lob
    @Column(name = "KETERANGAN", length = 32700)
    private String keterangan;
    @JoinColumn(name = "BANK_ID", referencedColumnName = "BANK_ID")
    @ManyToOne( cascade = {
        CascadeType.MERGE
            ,CascadeType.DETACH
//            ,CascadeType.REFRESH
//            ,CascadeType.REMOVE
            ,CascadeType.REFRESH})
    private Bank bankId;
    @OneToOne(mappedBy = "Transaksi", cascade = CascadeType.ALL)
    private Laporan Laporan;

    public Laporan getLaporan() {
        return Laporan;
    }

    public void setLaporan(Laporan Laporan) {
        this.Laporan = Laporan;
    }
    public Saldo() {
    }
    @PrePersist
    void BankDefault()
    {
        if (bankId == null) {
            this.bankId = Util.findFirst();
            System.out.println("bank.id = " + this.bankId.getBankId());
        }
    }
    @PostPersist
    void justLook() {
//        System.out.println("And after persist bank.id = " + this.bankId);
    }
    public Saldo(Integer saldoId) {
        this.saldoId = saldoId;
    }

    public Integer getSaldoId() {
        return saldoId;
    }

    public void setSaldoId(Integer saldoId) {
        Integer oldSaldoId = this.saldoId;
        this.saldoId = saldoId;
        changeSupport.firePropertyChange("saldoId", oldSaldoId, saldoId);
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        Date oldTanggal = this.tanggal;
        this.tanggal = tanggal;
        changeSupport.firePropertyChange("tanggal", oldTanggal, tanggal);
    }

    public long getKredit() {
        return kredit;
    }

    public void setKredit(long kredit) {
        long oldKredit = this.kredit;
        this.kredit = kredit;
        changeSupport.firePropertyChange("kredit", oldKredit, kredit);
    }

    public long getDebit() {
        return debit;
    }

    public void setDebit(long debit) {
        long oldDebit = this.debit;
        this.debit = debit;
        changeSupport.firePropertyChange("debit", oldDebit, debit);
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        long oldSaldo = this.saldo;
        this.saldo = saldo;
        changeSupport.firePropertyChange("saldo", oldSaldo, saldo);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public Bank getBankId() {
        return bankId;
    }

    public void setBankId(Bank bankId) {
        Bank oldBankId = this.bankId;
        this.bankId = bankId;
        changeSupport.firePropertyChange("bankId", oldBankId, bankId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saldoId != null ? saldoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Saldo)) {
            return false;
        }
        Saldo other = (Saldo) object;
        if ((this.saldoId == null && other.saldoId != null) || (this.saldoId != null && !this.saldoId.equals(other.saldoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ Transaksi Id=" + saldoId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
