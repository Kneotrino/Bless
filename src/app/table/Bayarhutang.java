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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
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
@Table(catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bayarhutang.findAll", query = "SELECT b FROM Bayarhutang b")
    , @NamedQuery(name = "Bayarhutang.findByBayarhutangid", query = "SELECT b FROM Bayarhutang b WHERE b.bayarhutangid = :bayarhutangid")
    , @NamedQuery(name = "Bayarhutang.findByPembayaran", query = "SELECT b FROM Bayarhutang b WHERE b.pembayaran = :pembayaran")
    , @NamedQuery(name = "Bayarhutang.findBySisa", query = "SELECT b FROM Bayarhutang b WHERE b.sisa = :sisa")
    , @NamedQuery(name = "Bayarhutang.findByTanggal", query = "SELECT b FROM Bayarhutang b WHERE b.tanggal = :tanggal")})
public class Bayarhutang implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)    @GeneratedValue

    private Integer bayarhutangid;
    @Lob
    private String keterangan;
    private BigInteger pembayaran;
    private BigInteger sisa;
    @Column(name="tanggal", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @JoinColumn(name = "HUTANGID", referencedColumnName = "HUTANGID")
    @ManyToOne
    private Hutang hutangid;

    public Bayarhutang() {
    }
    @PrePersist
    public void setting()
    {
        this.tanggal = new Date();
    }
    public Bayarhutang(Integer bayarhutangid) {
        this.bayarhutangid = bayarhutangid;
    }

    public Integer getBayarhutangid() {
        return bayarhutangid;
    }

    public void setBayarhutangid(Integer bayarhutangid) {
        Integer oldBayarhutangid = this.bayarhutangid;
        this.bayarhutangid = bayarhutangid;
        changeSupport.firePropertyChange("bayarhutangid", oldBayarhutangid, bayarhutangid);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public BigInteger getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(BigInteger pembayaran) {
        BigInteger oldPembayaran = this.pembayaran;
        this.pembayaran = pembayaran;
        changeSupport.firePropertyChange("pembayaran", oldPembayaran, pembayaran);
    }

    public BigInteger getSisa() {
        return sisa;
    }

    public void setSisa(BigInteger sisa) {
        BigInteger oldSisa = this.sisa;
        this.sisa = sisa;
        changeSupport.firePropertyChange("sisa", oldSisa, sisa);
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        Date oldTanggal = this.tanggal;
        this.tanggal = tanggal;
        changeSupport.firePropertyChange("tanggal", oldTanggal, tanggal);
    }

    public Hutang getHutangid() {
        return hutangid;
    }

    public void setHutangid(Hutang hutangid) {
        Hutang oldHutangid = this.hutangid;
        this.hutangid = hutangid;
        changeSupport.firePropertyChange("hutangid", oldHutangid, hutangid);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bayarhutangid != null ? bayarhutangid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bayarhutang)) {
            return false;
        }
        Bayarhutang other = (Bayarhutang) object;
        if ((this.bayarhutangid == null && other.bayarhutangid != null) || (this.bayarhutangid != null && !this.bayarhutangid.equals(other.bayarhutangid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Bayarhutang[ bayarhutangid=" + bayarhutangid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
