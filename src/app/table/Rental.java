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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "RENTAL", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rental.findAll", query = "SELECT r FROM Rental r"),
    @NamedQuery(name = "Rental.findByRentalid", query = "SELECT r FROM Rental r WHERE r.rentalid = :rentalid"),
    @NamedQuery(name = "Rental.findByTglmulai", query = "SELECT r FROM Rental r WHERE r.tglmulai = :tglmulai"),
    @NamedQuery(name = "Rental.findByTglselesai", query = "SELECT r FROM Rental r WHERE r.tglselesai = :tglselesai"),
    @NamedQuery(name = "Rental.findByPemakai", query = "SELECT r FROM Rental r WHERE r.pemakai = :pemakai"),
    @NamedQuery(name = "Rental.findByJammulai", query = "SELECT r FROM Rental r WHERE r.jammulai = :jammulai"),
    @NamedQuery(name = "Rental.findByJamselesai", query = "SELECT r FROM Rental r WHERE r.jamselesai = :jamselesai"),
    @NamedQuery(name = "Rental.findByDriver", query = "SELECT r FROM Rental r WHERE r.driver = :driver")})
 @ListUrutan({"tglmulai","tglselesai","pemakai",
//     "jammulai",
//     "jamselesai",
     "driver" })
public class Rental implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)    @GeneratedValue

    @Column(name = "RENTALID", nullable = false)
    private Integer rentalid;
    @Column(name = "TGLMULAI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglmulai;
    @Column(name = "TGLSELESAI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglselesai;
    @Column(name = "PEMAKAI", length = 255)    
    private String pemakai;
    @Column(name = "LABA", length = 255)
    private String LABA = "OPEN";

    public static final String PROP_LABA = "LABA";
    @OneToOne(mappedBy = "r",cascade = CascadeType.ALL)
    private BagiLaba bagiLaba;

    /**
     * Get the value of LABA
     *
     * @return the value of LABA
     */
    public String getLABA() {
        return LABA;
    }

    /**
     * Set the value of LABA
     *
     * @param LABA new value of LABA
     */
    public void setLABA(String LABA) {
        String oldLABA = this.LABA;
        this.LABA = LABA;
        changeSupport.firePropertyChange(PROP_LABA, oldLABA, LABA);
    }

    @Column(name = "JAMMULAI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jammulai = new Date();
    @Column(name = "JAMSELESAI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jamselesai = new Date();
    @Column(name = "DRIVER", length = 255)
    private String driver;
    @OneToMany(mappedBy = "rentalId",cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    @OrderBy("tanggal ASC")
    private List<Bayarrental> bayarrentalList;
    @ManyToOne
    private Mobilrental mobilrental;

    public Mobilrental getMobilrental() {
        return mobilrental;
    }

    public void setMobilrental(Mobilrental mobilrental) {
        this.mobilrental = mobilrental;
    }
    public Rental() {
    }

    public Rental(Integer rentalid) {
        this.bagiLaba = new BagiLaba();
        this.rentalid = rentalid;
    }

    public Integer getRentalid() {
        return rentalid;
    }

    public void setRentalid(Integer rentalid) {
        Integer oldRentalid = this.rentalid;
        this.rentalid = rentalid;
        changeSupport.firePropertyChange("rentalid", oldRentalid, rentalid);
    }

    public Date getTglmulai() {
        return tglmulai;
    }

    public void setTglmulai(Date tglmulai) {
        Date oldTglmulai = this.tglmulai;
        this.tglmulai = tglmulai;
        changeSupport.firePropertyChange("tglmulai", oldTglmulai, tglmulai);
    }

    public Date getTglselesai() {
        return tglselesai;
    }

    public void setTglselesai(Date tglselesai) {
        Date oldTglselesai = this.tglselesai;
        this.tglselesai = tglselesai;
        changeSupport.firePropertyChange("tglselesai", oldTglselesai, tglselesai);
    }

    public String getPemakai() {
        return pemakai;
    }

    public void setPemakai(String pemakai) {
        String oldPemakai = this.pemakai;
        this.pemakai = pemakai;
        changeSupport.firePropertyChange("pemakai", oldPemakai, pemakai);
    }

    public Date getJammulai() {
        return jammulai;
    }
    
    public int getJammulai0() {
        return ((jammulai ==null)?0:jammulai.getHours());
    }
    public void setJammulai0(int jam) {
        int oldJam = this.jammulai.getHours();
        Date d = this.jammulai;
        d.setHours(jam);
        this.jammulai = d;
        changeSupport.firePropertyChange("jammulai0", oldJam, jammulai);        
    }
    
    public int getjamSelesai0() {
        return ((jamselesai ==null)?0:jamselesai.getHours());
    }
    public void setJamSelesai0(int jam) {
        int oldJam = this.jamselesai.getHours();
        Date d = this.jamselesai;
        d.setHours(jam);
        this.jamselesai = d;
        changeSupport.firePropertyChange("jamselesai0", oldJam, jamselesai);        
    }
    

    public void setJammulai(Date jammulai) {
        Date oldJammulai = this.jammulai;
        this.jammulai = jammulai;
        changeSupport.firePropertyChange("jammulai", oldJammulai, jammulai);
    }
    
    public BigInteger gettotalProfit()
    {
        return gettotalPemasukan().subtract(gettotalPengeluaran());
    }
    public  BigInteger gettotalPemasukan()
    {
        return Util.getTotalPemasukan(bayarrentalList);
    }
    public  BigInteger gettotalPengeluaran()
    {
        return Util.getTotalPengeluaran(bayarrentalList);
    }
    public Date getJamselesai() {
        return jamselesai;
    }
    public void setJamselesai(Date jamselesai) {
        Date oldJamselesai = this.jamselesai;
        this.jamselesai = jamselesai;
        changeSupport.firePropertyChange("jamselesai", oldJamselesai, jamselesai);
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        String oldDriver = this.driver;
        this.driver = driver;
        changeSupport.firePropertyChange("driver", oldDriver, driver);
    }

    @XmlTransient
    public List<Bayarrental> getBayarrentalList() {
        return (List<Bayarrental>) app.table.Util.hitungSaldo(bayarrentalList);
    }

    public void setBayarrentalList(List<Bayarrental> bayarrentalList) {
        this.bayarrentalList = bayarrentalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rentalid != null ? rentalid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rental)) {
            return false;
        }
        Rental other = (Rental) object;
        if ((this.rentalid == null && other.rentalid != null) || (this.rentalid != null && !this.rentalid.equals(other.rentalid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Jasa Rental[ Ref=" + rentalid 
                +"-"
                +pemakai
                +"-"
                +mobilrental                
                + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
