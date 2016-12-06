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
@Table(name = "BAYARJASA", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bayarjasa.findAll", query = "SELECT b FROM Bayarjasa b")
    , @NamedQuery(name = "Bayarjasa.findByBayarpihutangid", query = "SELECT b FROM Bayarjasa b WHERE b.bayarpihutangid = :bayarpihutangid")
    , @NamedQuery(name = "Bayarjasa.findByPembayaran", query = "SELECT b FROM Bayarjasa b WHERE b.pembayaran = :pembayaran")
    , @NamedQuery(name = "Bayarjasa.findByTanggal", query = "SELECT b FROM Bayarjasa b WHERE b.tanggal = :tanggal")})
public class Bayarjasa implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BAYARPIHUTANGID", nullable = false)
    private Integer bayarpihutangid;
    @Lob
    @Column(name = "KETERANGAN")
    private String keterangan;
    @Column(name = "PEMBAYARAN")
    private BigInteger pembayaran;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @JoinColumn(name = "BPKBTITIPID", referencedColumnName = "BPKB_ID")
    @ManyToOne
    private Bpkbtitipan bpkbtitipid;

    public Bayarjasa() {
    }

    public Bayarjasa(Integer bayarpihutangid) {
        this.bayarpihutangid = bayarpihutangid;
    }

    public Integer getBayarpihutangid() {
        return bayarpihutangid;
    }

    public void setBayarpihutangid(Integer bayarpihutangid) {
        Integer oldBayarpihutangid = this.bayarpihutangid;
        this.bayarpihutangid = bayarpihutangid;
        changeSupport.firePropertyChange("bayarpihutangid", oldBayarpihutangid, bayarpihutangid);
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

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        Date oldTanggal = this.tanggal;
        this.tanggal = tanggal;
        changeSupport.firePropertyChange("tanggal", oldTanggal, tanggal);
    }

    public Bpkbtitipan getBpkbtitipid() {
        return bpkbtitipid;
    }

    public void setBpkbtitipid(Bpkbtitipan bpkbtitipid) {
        Bpkbtitipan oldBpkbtitipid = this.bpkbtitipid;
        this.bpkbtitipid = bpkbtitipid;
        changeSupport.firePropertyChange("bpkbtitipid", oldBpkbtitipid, bpkbtitipid);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bayarpihutangid != null ? bayarpihutangid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bayarjasa)) {
            return false;
        }
        Bayarjasa other = (Bayarjasa) object;
        if ((this.bayarpihutangid == null && other.bayarpihutangid != null) || (this.bayarpihutangid != null && !this.bayarpihutangid.equals(other.bayarpihutangid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Bayarjasa[ bayarpihutangid=" + bayarpihutangid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
