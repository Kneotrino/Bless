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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
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
@XmlRootElement
public class Akuntansi implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMOR", nullable = false)
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="MySequenceGenerator")
//    @SequenceGenerator(allocationSize=1, schema="myschema",  name="MySequenceGenerator", sequenceName = "mysequence")
    private Integer nomor = 1;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @Lob
    @Column(name = "KET", length = 32700)
    private String ket;
    @Column(name = "KAS")
    private BigInteger kas;
    @Column(name = "PIUTANG")
    private BigInteger piutang;
    @Column(name = "ASSET")
    private BigInteger asset;
    @Column(name = "HUTANG")
    private BigInteger hutang;
    @Column(name = "MODAL")
    private BigInteger modal;

    public Akuntansi() {
    }

    public Akuntansi(Integer nomor) {
        this.nomor = nomor;
    }

    public Integer getNomor() {
        return nomor;
    }

    public void setNomor(Integer nomor) {
        Integer oldNomor = this.nomor;
        this.nomor = nomor;
        changeSupport.firePropertyChange("nomor", oldNomor, nomor);
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        Date oldTanggal = this.tanggal;
        this.tanggal = tanggal;
        changeSupport.firePropertyChange("tanggal", oldTanggal, tanggal);
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        String oldKet = this.ket;
        this.ket = ket;
        changeSupport.firePropertyChange("ket", oldKet, ket);
    }

    public BigInteger getKas() {
        return kas;
    }

    public void setKas(BigInteger kas) {
        BigInteger oldKas = this.kas;
        this.kas = kas;
        changeSupport.firePropertyChange("kas", oldKas, kas);
    }

    public BigInteger getPiutang() {
        return piutang;
    }

    public void setPiutang(BigInteger piutang) {
        BigInteger oldPiutang = this.piutang;
        this.piutang = piutang;
        changeSupport.firePropertyChange("piutang", oldPiutang, piutang);
    }

    public BigInteger getAsset() {
        return asset;
    }

    public void setAsset(BigInteger asset) {
        BigInteger oldAsset = this.asset;
        this.asset = asset;
        changeSupport.firePropertyChange("asset", oldAsset, asset);
    }

    public BigInteger getHutang() {
        return hutang;
    }

    public void setHutang(BigInteger hutang) {
        BigInteger oldHutang = this.hutang;
        this.hutang = hutang;
        changeSupport.firePropertyChange("hutang", oldHutang, hutang);
    }

    public BigInteger getModal() {
        return modal;
    }

    public void setModal(BigInteger modal) {
        BigInteger oldModal = this.modal;
        this.modal = modal;
        changeSupport.firePropertyChange("modal", oldModal, modal);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nomor != null ? nomor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Akuntansi)) {
            return false;
        }
        Akuntansi other = (Akuntansi) object;
        if ((this.nomor == null && other.nomor != null) || (this.nomor != null && !this.nomor.equals(other.nomor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Akuntansi[ nomor=" + nomor + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
