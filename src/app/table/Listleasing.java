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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "LISTLEASING", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listleasing.findAll", query = "SELECT l FROM Listleasing l")
    , @NamedQuery(name = "Listleasing.findByListleasingId", query = "SELECT l FROM Listleasing l WHERE l.listleasingId = :listleasingId")
    , @NamedQuery(name = "Listleasing.findByNominal", query = "SELECT l FROM Listleasing l WHERE l.nominal = :nominal")
    , @NamedQuery(name = "Listleasing.findByTglDp", query = "SELECT l FROM Listleasing l WHERE l.tglDp = :tglDp")
    , @NamedQuery(name = "Listleasing.findByTglOk", query = "SELECT l FROM Listleasing l WHERE l.tglOk = :tglOk")})
@app.ListUrutan({"nominal","tglDp","tglOk" })
public class Listleasing implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)     @GeneratedValue

    @Column(name = "LISTLEASING_ID", nullable = false)
    private Integer listleasingId;
    @Basic(optional = false)
    @Column(name = "NOMINAL", nullable = false)
    private BigInteger nominal = BigInteger.ZERO;
    @Column(name = "TGL_DP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglDp;
    @Column(name = "TGL_OK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglOk;
    @JoinColumn(name = "LEASING_LEASING_ID", referencedColumnName = "LEASING_ID")
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    private Leasing leasingLeasingId;
    @OneToOne(mappedBy = "listleasing", cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    private Mobil mobil;
    @Column(name = "TERPENUHI")
    private BigInteger TERPENUHI = BigInteger.ZERO;

    public static final String PROP_TERPENUHI = "TERPENUHI";

    /**
     * Get the value of TERPENUHI
     *
     * @return the value of TERPENUHI
     */
    public BigInteger getTERPENUHI() {
        return TERPENUHI;
    }

    /**
     * Set the value of TERPENUHI
     *
     * @param TERPENUHI new value of TERPENUHI
     */
    public void setTERPENUHI(BigInteger TERPENUHI) {
        BigInteger oldTERPENUHI = this.TERPENUHI;
        this.TERPENUHI = TERPENUHI;
        changeSupport.firePropertyChange(PROP_TERPENUHI, oldTERPENUHI, TERPENUHI);
    }

    public void setMobil(Mobil mobil) {
        this.mobil = mobil;
    }

    public Mobil getMobil() {
        return mobil;
    }

    public Listleasing() {
    }

    public Listleasing(Integer listleasingId) {
        this.listleasingId = listleasingId;
    }

    public Listleasing(Integer listleasingId, BigInteger nominal) {
        this.listleasingId = listleasingId;
        this.nominal = nominal;
    }

    public Integer getListleasingId() {
        return listleasingId;
    }

    public void setListleasingId(Integer listleasingId) {
        Integer oldListleasingId = this.listleasingId;
        this.listleasingId = listleasingId;
        changeSupport.firePropertyChange("listleasingId", oldListleasingId, listleasingId);
    }

    public BigInteger getNominal() {
        return nominal;
    }

    public void setNominal(BigInteger nominal) {
        BigInteger oldNominal = this.nominal;
        this.nominal = nominal;
        changeSupport.firePropertyChange("nominal", oldNominal, nominal);
    }

    public Date getTglDp() {
        return tglDp;
    }

    public void setTglDp(Date tglDp) {
        Date oldTglDp = this.tglDp;
        this.tglDp = tglDp;
        changeSupport.firePropertyChange("tglDp", oldTglDp, tglDp);
    }

    public Date getTglOk() {
        return tglOk;
    }

    public void setTglOk(Date tglOk) {
        Date oldTglOk = this.tglOk;
        this.tglOk = tglOk;
        changeSupport.firePropertyChange("tglOk", oldTglOk, tglOk);
    }

    public Leasing getLeasingLeasingId() {
        return leasingLeasingId;
    }

    public void setLeasingLeasingId(Leasing leasingLeasingId) {
        Leasing oldLeasingLeasingId = this.leasingLeasingId;
        this.leasingLeasingId = leasingLeasingId;
        changeSupport.firePropertyChange("leasingLeasingId", oldLeasingLeasingId, leasingLeasingId);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listleasingId != null ? listleasingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listleasing)) {
            return false;
        }
        Listleasing other = (Listleasing) object;
        if ((this.listleasingId == null && other.listleasingId != null) || (this.listleasingId != null && !this.listleasingId.equals(other.listleasingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Listleasing[ listleasingId=" + listleasingId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
