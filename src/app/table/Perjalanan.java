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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "PERJALANAN", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perjalanan.findAll", query = "SELECT p FROM Perjalanan p")
    , @NamedQuery(name = "Perjalanan.findById", query = "SELECT p FROM Perjalanan p WHERE p.id = :id")
    , @NamedQuery(name = "Perjalanan.findByKirim", query = "SELECT p FROM Perjalanan p WHERE p.kirim = :kirim")
    , @NamedQuery(name = "Perjalanan.findByPakai", query = "SELECT p FROM Perjalanan p WHERE p.pakai = :pakai")
    , @NamedQuery(name = "Perjalanan.findByPerjalananKe", query = "SELECT p FROM Perjalanan p WHERE p.perjalananKe = :perjalananKe")
    , @NamedQuery(name = "Perjalanan.findBySaldo", query = "SELECT p FROM Perjalanan p WHERE p.saldo = :saldo")
    , @NamedQuery(name = "Perjalanan.findByTanggal", query = "SELECT p FROM Perjalanan p WHERE p.tanggal = :tanggal")})
public class Perjalanan implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Lob
    @Column(name = "KETERANGAN")
    private String keterangan;
    @Column(name = "KIRIM")
    private BigInteger kirim;
    @Column(name = "PAKAI")
    private BigInteger pakai;
    @Column(name = "PERJALANAN_KE")
    private Integer perjalananKe;
    @Column(name = "SALDO")
    private BigInteger saldo;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @JoinColumn(name = "TRIPS_TRIPS_ID", referencedColumnName = "TRIPS_ID")
    @ManyToOne
    private Trips tripsTripsId;

    public Perjalanan() {
    }

    public Perjalanan(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public BigInteger getKirim() {
        return kirim;
    }

    public void setKirim(BigInteger kirim) {
        BigInteger oldKirim = this.kirim;
        this.kirim = kirim;
        changeSupport.firePropertyChange("kirim", oldKirim, kirim);
    }

    public BigInteger getPakai() {
        return pakai;
    }

    public void setPakai(BigInteger pakai) {
        BigInteger oldPakai = this.pakai;
        this.pakai = pakai;
        changeSupport.firePropertyChange("pakai", oldPakai, pakai);
    }

    public Integer getPerjalananKe() {
        return perjalananKe;
    }

    public void setPerjalananKe(Integer perjalananKe) {
        Integer oldPerjalananKe = this.perjalananKe;
        this.perjalananKe = perjalananKe;
        changeSupport.firePropertyChange("perjalananKe", oldPerjalananKe, perjalananKe);
    }

    public BigInteger getSaldo() {
        return saldo;
    }

    public void setSaldo(BigInteger saldo) {
        BigInteger oldSaldo = this.saldo;
        this.saldo = saldo;
        changeSupport.firePropertyChange("saldo", oldSaldo, saldo);
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        Date oldTanggal = this.tanggal;
        this.tanggal = tanggal;
        changeSupport.firePropertyChange("tanggal", oldTanggal, tanggal);
    }

    public Trips getTripsTripsId() {
        return tripsTripsId;
    }

    public void setTripsTripsId(Trips tripsTripsId) {
        Trips oldTripsTripsId = this.tripsTripsId;
        this.tripsTripsId = tripsTripsId;
        changeSupport.firePropertyChange("tripsTripsId", oldTripsTripsId, tripsTripsId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perjalanan)) {
            return false;
        }
        Perjalanan other = (Perjalanan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Perjalanan[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
