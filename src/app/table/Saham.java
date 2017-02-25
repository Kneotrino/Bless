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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "SAHAM", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Saham.findAll", query = "SELECT s FROM Saham s"),
    @NamedQuery(name = "Saham.findById", query = "SELECT s FROM Saham s WHERE s.id = :id")})
public class Saham implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @javax.persistence.GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    @JoinColumn(name = "INVESTOR_ID", referencedColumnName = "ID")
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Investor investorId;
    @OneToOne(cascade = {CascadeType.ALL})
    private Modal modal;
    @OneToOne(cascade = {CascadeType.ALL})
    private Prive prive;    
    @OneToOne(cascade = {CascadeType.ALL})
    private pembagianLaba Laba;
//    @OneToOne
    @OneToOne(cascade = {CascadeType.ALL})
    private Relasi relasi;

    public Relasi getRelasi() {
        return relasi;
    }

    public void setRelasi(Relasi relasi) {
        this.relasi = relasi;
    }
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date Tanggal = new Date();    
    private String Keterangan = "";
    public pembagianLaba getLaba() {
        return Laba;
    }

    public void setLaba(pembagianLaba Laba) {
        this.Laba = Laba;
    }
    @Transient
    private app.table.Bank b;

    public Bank getB() {
        if (modal != null) {
            return modal.getTransaksi().getBankId();
        }
        else if (prive != null)
        {
            return prive.getTransaksi().getBankId();
        }
        else if (Laba != null)
        {
            return Laba.getTransaksi().getBankId();
        }
        return b;
    }

    public void setB(Bank b) {
         if (modal != null) {
            modal.getTransaksi().setBankId(b);
        }
        else if (prive != null)
        {
            prive.getTransaksi().setBankId(b);
        }
        else if (Laba != null)
        {
            Laba.getTransaksi().setBankId(b);
        }
        this.b = b;
    }
    public static final String PROP_KETERANGAN = "Keterangan";

    /**
     * Get the value of Keterangan
     *
     * @return the value of Keterangan
     */
    public String getKeterangan() {
         if (modal != null) {
            return modal.getKeterangan();
        }
        else if (prive != null)
        {
            return prive.getKeterangan();
        }    
        else if (Laba != null)
        {
            return Laba.getKeterangan();
        }    
        return Keterangan;
    }

    /**
     * Set the value of Keterangan
     *
     * @param Keterangan new value of Keterangan
     */
    public void setKeterangan(String Keterangan) {
        String oldKeterangan = this.Keterangan;
        this.Keterangan = Keterangan;
        if (modal != null) {
            modal.setKeterangan(Keterangan);
        }
        else if (prive != null)
        {
            prive.setKeterangan(Keterangan);
        }
        else if (Laba  != null)
        {
            Laba.setKeterangan(Keterangan);
        }
    
        changeSupport.firePropertyChange(PROP_KETERANGAN, oldKeterangan, Keterangan);
    }

    public static final String PROP_TANGGAL = "Tanggal";

    /**
     * Get the value of Tanggal
     *
     * @return the value of Tanggal
     */
    public Date getTanggal() {
        if (modal != null) {
            return modal.getTanggal();
        }
        else if (prive != null)
        {
            return prive.getTanggal();
        }
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
        if (modal != null) {
            modal.setTanggal(Tanggal);
        }
        else if (prive != null)
        {
            prive.setTanggal(Tanggal);
        }
        changeSupport.firePropertyChange(PROP_TANGGAL, oldTanggal, Tanggal);
    }

    public Modal getModal() {
        return modal;
    }

    public void setModal(Modal modal) {
        this.modal = modal;
    }

    public Prive getPrive() {
        return prive;
    }

    public void setPrive(Prive prive) {
        this.prive = prive;
    }
    public Saham() {
    }

    public Saham(Long id) {
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

    public Investor getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Investor investorId) {
        Investor oldInvestorId = this.investorId;
        this.investorId = investorId;
        changeSupport.firePropertyChange("investorId", oldInvestorId, investorId);
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
        if (!(object instanceof Saham)) {
            return false;
        }
        Saham other = (Saham) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Saham[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    public BigInteger getmod()
    {
        BigInteger temp = BigInteger.ZERO;
        if (modal != null) {
            temp = modal.getJumlah();
        }
        return temp;
    }
    public BigInteger getpri()
    {
        BigInteger temp = BigInteger.ZERO;
        if (prive != null) {
            temp = prive.getJumlah();
        }
        return temp;
    }
    public BigInteger getlab()
    {
        BigInteger temp = BigInteger.ZERO;
        if (Laba != null) {
            temp = Laba.getJumlah();
        }
        return temp;
    }
//        else if (Laba != null)
//        {
//            temp = Laba.getJumlah();
//        }
//        else if (prive != null){
//            temp = prive.getJumlah();
//        }
}
