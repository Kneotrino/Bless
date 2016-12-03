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
@Table(name = "BAYARRENTAL", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bayarrental.findAll", query = "SELECT b FROM Bayarrental b"),
    @NamedQuery(name = "Bayarrental.findByBayarhutangid", query = "SELECT b FROM Bayarrental b WHERE b.bayarhutangid = :bayarhutangid"),
    @NamedQuery(name = "Bayarrental.findByPembayaran", query = "SELECT b FROM Bayarrental b WHERE b.pembayaran = :pembayaran"),
    @NamedQuery(name = "Bayarrental.findByTanggal", query = "SELECT b FROM Bayarrental b WHERE b.tanggal = :tanggal"),
    @NamedQuery(name = "Bayarrental.findByBayarrentalid", query = "SELECT b FROM Bayarrental b WHERE b.bayarrentalid = :bayarrentalid")})
public class Bayarrental implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)    @GeneratedValue

    @Column(name = "BAYARHUTANGID", nullable = false)
    private Integer bayarhutangid;
    @Lob
    @Column(name = "KETERANGAN")
    private String keterangan;
    @Column(name = "PEMBAYARAN")
    private BigInteger pembayaran;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @Column(name = "BAYARRENTALID")
    private Integer bayarrentalid;
    @JoinColumn(name = "RENTAL_ID", referencedColumnName = "RENTALID")
    @ManyToOne
    private Rental rentalId;

    public Bayarrental() {
    }

    public Bayarrental(Integer bayarhutangid) {
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

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        Date oldTanggal = this.tanggal;
        this.tanggal = tanggal;
        changeSupport.firePropertyChange("tanggal", oldTanggal, tanggal);
    }

    public Integer getBayarrentalid() {
        return bayarrentalid;
    }

    public void setBayarrentalid(Integer bayarrentalid) {
        Integer oldBayarrentalid = this.bayarrentalid;
        this.bayarrentalid = bayarrentalid;
        changeSupport.firePropertyChange("bayarrentalid", oldBayarrentalid, bayarrentalid);
    }

    public Rental getRentalId() {
        return rentalId;
    }

    public void setRentalId(Rental rentalId) {
        Rental oldRentalId = this.rentalId;
        this.rentalId = rentalId;
        changeSupport.firePropertyChange("rentalId", oldRentalId, rentalId);
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
        if (!(object instanceof Bayarrental)) {
            return false;
        }
        Bayarrental other = (Bayarrental) object;
        if ((this.bayarhutangid == null && other.bayarhutangid != null) || (this.bayarhutangid != null && !this.bayarhutangid.equals(other.bayarhutangid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Bayarrental[ bayarhutangid=" + bayarhutangid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
