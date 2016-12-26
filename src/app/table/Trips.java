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
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "TRIPS", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trips.findAll", query = "SELECT t FROM Trips t")
    , @NamedQuery(name = "Trips.findByTripsId", query = "SELECT t FROM Trips t WHERE t.tripsId = :tripsId")
    , @NamedQuery(name = "Trips.findByPerjalananke", query = "SELECT t FROM Trips t WHERE t.perjalananke = :perjalananke")
    , @NamedQuery(name = "Trips.findByTanggalBerangkat", query = "SELECT t FROM Trips t WHERE t.tanggalBerangkat = :tanggalBerangkat")
    , @NamedQuery(name = "Trips.findByTanggalKembali", query = "SELECT t FROM Trips t WHERE t.tanggalKembali = :tanggalKembali")
    , @NamedQuery(name = "Trips.findByTotalKirim", query = "SELECT t FROM Trips t WHERE t.totalKirim = :totalKirim")
    , @NamedQuery(name = "Trips.findByTotalPakai", query = "SELECT t FROM Trips t WHERE t.totalPakai = :totalPakai")
    , @NamedQuery(name = "Trips.findByTotalSaldo", query = "SELECT t FROM Trips t WHERE t.totalSaldo = :totalSaldo")}
)
@app.ListUrutan({"keterangan","perjalananke","tanggalBerangkat","tanggalKembali","totalKirim","totalPakai"})
public class Trips implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)    
    @GeneratedValue

    @Column(name = "TRIPS_ID", nullable = false)
    private Integer tripsId;
    @Lob
    @Column(name = "KETERANGAN")
    private String keterangan;
    @Column(name = "PERJALANANKE")
    private Integer perjalananke;
    @Column(name = "TANGGAL_BERANGKAT")
    @Temporal(TemporalType.DATE)
    private Date tanggalBerangkat;
    @Column(name = "TANGGAL_KEMBALI")
    @Temporal(TemporalType.DATE)
    private Date tanggalKembali;
    @Column(name = "TOTAL_KIRIM")
    private BigInteger totalKirim;
    @Column(name = "TOTAL_PAKAI")
    private BigInteger totalPakai;
    @Basic(optional = false)
    @Column(name = "TOTAL_SALDO", nullable = false)
    private BigInteger totalSaldo = BigInteger.ZERO;
    @OneToMany(mappedBy = "tripsTripsId",cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<Perjalanan> perjalananList;

    public Trips() {
    }

    public Trips(Integer tripsId) {
        this.tripsId = tripsId;
    }

    public Trips(Integer tripsId, BigInteger totalSaldo) {
        this.tripsId = tripsId;
        this.totalSaldo = totalSaldo;
    }

    public Integer getTripsId() {
        return tripsId;
    }

    public void setTripsId(Integer tripsId) {
        Integer oldTripsId = this.tripsId;
        this.tripsId = tripsId;
        changeSupport.firePropertyChange("tripsId", oldTripsId, tripsId);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public Integer getPerjalananke() {
        return perjalananke;
    }

    public void setPerjalananke(Integer perjalananke) {
        Integer oldPerjalananke = this.perjalananke;
        this.perjalananke = perjalananke;
        changeSupport.firePropertyChange("perjalananke", oldPerjalananke, perjalananke);
    }

    public Date getTanggalBerangkat() {
        return tanggalBerangkat;
    }

    public void setTanggalBerangkat(Date tanggalBerangkat) {
        Date oldTanggalBerangkat = this.tanggalBerangkat;
        this.tanggalBerangkat = tanggalBerangkat;
        changeSupport.firePropertyChange("tanggalBerangkat", oldTanggalBerangkat, tanggalBerangkat);
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        Date oldTanggalKembali = this.tanggalKembali;
        this.tanggalKembali = tanggalKembali;
        changeSupport.firePropertyChange("tanggalKembali", oldTanggalKembali, tanggalKembali);
    }

    public BigInteger getTotalKirim() {
        return totalKirim;
    }

    public void setTotalKirim(BigInteger totalKirim) {
        BigInteger oldTotalKirim = this.totalKirim;
        this.totalKirim = totalKirim;
        changeSupport.firePropertyChange("totalKirim", oldTotalKirim, totalKirim);
    }

    public BigInteger getTotalPakai() {
        return totalPakai;
    }

    public void setTotalPakai(BigInteger totalPakai) {
        BigInteger oldTotalPakai = this.totalPakai;
        this.totalPakai = totalPakai;
        changeSupport.firePropertyChange("totalPakai", oldTotalPakai, totalPakai);
    }

    public BigInteger getTotalSaldo() {
        return totalSaldo;
    }

    public void setTotalSaldo(BigInteger totalSaldo) {
        BigInteger oldTotalSaldo = this.totalSaldo;
        this.totalSaldo = totalSaldo;
        changeSupport.firePropertyChange("totalSaldo", oldTotalSaldo, totalSaldo);
    }

    @XmlTransient
    public List<Perjalanan> getPerjalananList() {
        return perjalananList;
    }

    public void setPerjalananList(List<Perjalanan> perjalananList) {
        this.perjalananList = perjalananList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripsId != null ? tripsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trips)) {
            return false;
        }
        Trips other = (Trips) object;
        if ((this.tripsId == null && other.tripsId != null) || (this.tripsId != null && !this.tripsId.equals(other.tripsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Trips[ tripsId=" + tripsId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    public BigInteger getPakai()
    {
        BigInteger temp = BigInteger.ZERO;
        for (Perjalanan p : perjalananList) {
            temp = temp.add(p.getPakai());
        }
        return temp;
    }
    public BigInteger getKirim()
    {
        BigInteger temp = BigInteger.ZERO;
        for (Perjalanan p : perjalananList) {
            temp = temp.add(p.getPengeluaran());
        }
        return temp;
    }
    @Transient
    private BigInteger kembalikan;

    public static final String PROP_KEMBALIKAN = "kembalikan";

    /**
     * Get the value of kembalikan
     *
     * @return the value of kembalikan
     */
    public BigInteger getKembalikan() {
        return kembalikan;
    }

    /**
     * Set the value of kembalikan
     *
     * @param kembalikan new value of kembalikan
     */
    public void setKembalikan(BigInteger kembalikan) {
        BigInteger oldKembalikan = this.kembalikan;
        this.kembalikan = kembalikan;
        changeSupport.firePropertyChange(PROP_KEMBALIKAN, oldKembalikan, kembalikan);
    }

//    @Transient
//    private BigInteger kembalikan = BigInteger.ZERO;
    
    
    public void getKembali()
    {
        BigInteger temp = BigInteger.ZERO;
        for (Perjalanan p : perjalananList) {
            temp = temp.add(p.getPemasukan());
        }
        this.setKembalikan(temp);
    }
    
}
