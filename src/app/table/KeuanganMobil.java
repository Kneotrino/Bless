/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "KEUANGAN_MOBIL", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KeuanganMobil.findAll", query = "SELECT k FROM KeuanganMobil k"),
    @NamedQuery(name = "KeuanganMobil.findByKeuanganMobilId", query = "SELECT k FROM KeuanganMobil k WHERE k.keuanganMobilId = :keuanganMobilId"),
    @NamedQuery(name = "KeuanganMobil.findByDebit", query = "SELECT k FROM KeuanganMobil k WHERE k.debit = :debit"),
    @NamedQuery(name = "KeuanganMobil.findByKredit", query = "SELECT k FROM KeuanganMobil k WHERE k.kredit = :kredit"),
    @NamedQuery(name = "KeuanganMobil.findByTanggal", query = "SELECT k FROM KeuanganMobil k WHERE k.tanggal = :tanggal")})
public class KeuanganMobil implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "KEUANGAN_MOBIL_ID", nullable = false)
    private Integer keuanganMobilId;
    @Column(name = "DEBIT")
    private long debit;
    @Lob
    @Column(name = "KETERANGAN")
    private String keterangan;
    @Column(name = "KREDIT")
    private long kredit;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @ManyToOne(cascade = CascadeType.ALL)
    private Mobil mobils;

    public Mobil getMobils() {
        return mobils;
    }

    public void setMobils(Mobil mobils) {
        Mobil oldMobils = this.mobils;
        this.mobils = mobils;
        changeSupport.firePropertyChange("mobils", oldMobils, mobils);
    }

    public KeuanganMobil() {
    }

    public KeuanganMobil(Integer keuanganMobilId) {
        this.keuanganMobilId = keuanganMobilId;
    }

    public Integer getKeuanganMobilId() {
        return keuanganMobilId;
    }

    public void setKeuanganMobilId(Integer keuanganMobilId) {
        Integer oldKeuanganMobilId = this.keuanganMobilId;
        this.keuanganMobilId = keuanganMobilId;
        changeSupport.firePropertyChange("keuanganMobilId", oldKeuanganMobilId, keuanganMobilId);
    }

    public long getDebit() {
        return debit;
    }

    public void setDebit(long debit) {
        long oldDebit = this.debit;
        this.debit = debit;
        changeSupport.firePropertyChange("debit", oldDebit, debit);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public long getKredit() {
        return kredit;
    }

    public void setKredit(long kredit) {
        long oldKredit = this.kredit;
        this.kredit = kredit;
        changeSupport.firePropertyChange("kredit", oldKredit, kredit);
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        Date oldTanggal = this.tanggal;
        this.tanggal = tanggal;
        changeSupport.firePropertyChange("tanggal", oldTanggal, tanggal);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keuanganMobilId != null ? keuanganMobilId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KeuanganMobil)) {
            return false;
        }
        KeuanganMobil other = (KeuanganMobil) object;
        if ((this.keuanganMobilId == null && other.keuanganMobilId != null) || (this.keuanganMobilId != null && !this.keuanganMobilId.equals(other.keuanganMobilId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.KeuanganMobil[ keuanganMobilId=" + keuanganMobilId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
