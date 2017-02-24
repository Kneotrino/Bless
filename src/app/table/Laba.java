/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Table(name = "LABA", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Laba.findAll", query = "SELECT l FROM Laba l")
    , @NamedQuery(name = "Laba.findById", query = "SELECT l FROM Laba l WHERE l.id = :id")
    , @NamedQuery(name = "Laba.findByJumlah", query = "SELECT l FROM Laba l WHERE l.jumlah = :jumlah")
    , @NamedQuery(name = "Laba.findByTipe", query = "SELECT l FROM Laba l WHERE l.tipe = :tipe")})
public class Laba implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "JUMLAH", nullable = false)
    private long jumlah;
    @Lob
    @Column(name = "KETERANGAN")
    private String keterangan;
    @Column(name = "TIPE", length = 255)
    private String tipe;
    @JoinColumn(name = "LAPORANLABA_REF", referencedColumnName = "REF")
    @ManyToOne
    private Laporanlaba laporanlabaRef;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date Tanggal = new Date();

    public static final String PROP_TANGGAL = "Tanggal";

    /**
     * Get the value of Tanggal
     *
     * @return the value of Tanggal
     */
    public Date getTanggal() {
        return Tanggal;
    }

    /**
     * Set the value of Tanggal
     *
     * @param Tanggal new value of Tanggal
     */
    public void setTanggal(Date Tanggal) {
        Date oldTanggal = this.Tanggal;
        this.Tanggal = Tanggal;
        changeSupport.firePropertyChange(PROP_TANGGAL, oldTanggal, Tanggal);
    }

    public Laba() {
    }

    public Laba(Long id) {
        this.id = id;
    }

    public Laba(Long id, long jumlah) {
        this.id = id;
        this.jumlah = jumlah;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public long getJumlah() {
        return jumlah;
    }

    public void setJumlah(long jumlah) {
        long oldJumlah = this.jumlah;
        this.jumlah = jumlah;
        changeSupport.firePropertyChange("jumlah", oldJumlah, jumlah);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        String oldTipe = this.tipe;
        this.tipe = tipe;
        changeSupport.firePropertyChange("tipe", oldTipe, tipe);
    }

    public Laporanlaba getLaporanlabaRef() {
        return laporanlabaRef;
    }

    public void setLaporanlabaRef(Laporanlaba laporanlabaRef) {
        Laporanlaba oldLaporanlabaRef = this.laporanlabaRef;
        this.laporanlabaRef = laporanlabaRef;
        changeSupport.firePropertyChange("laporanlabaRef", oldLaporanlabaRef, laporanlabaRef);
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
        if (!(object instanceof Laba)) {
            return false;
        }
        Laba other = (Laba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Laba[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
