/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "MOBILRENTAL", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mobilrental.findAll", query = "SELECT m FROM Mobilrental m")
    , @NamedQuery(name = "Mobilrental.findByMobilId", query = "SELECT m FROM Mobilrental m WHERE m.mobilId = :mobilId")
    , @NamedQuery(name = "Mobilrental.findByNoPolisiAktif", query = "SELECT m FROM Mobilrental m WHERE m.noPolisiAktif = :noPolisiAktif")
    , @NamedQuery(name = "Mobilrental.findByMerk", query = "SELECT m FROM Mobilrental m WHERE m.merk = :merk")
    , @NamedQuery(name = "Mobilrental.findByType", query = "SELECT m FROM Mobilrental m WHERE m.type = :type")
    , @NamedQuery(name = "Mobilrental.findByWarna", query = "SELECT m FROM Mobilrental m WHERE m.warna = :warna")
    , @NamedQuery(name = "Mobilrental.findByTahun", query = "SELECT m FROM Mobilrental m WHERE m.tahun = :tahun")
    , @NamedQuery(name = "Mobilrental.findByKeterangan", query = "SELECT m FROM Mobilrental m WHERE m.keterangan = :keterangan")
    , @NamedQuery(name = "Mobilrental.findByStatusMobil", query = "SELECT m FROM Mobilrental m WHERE m.statusMobil = :statusMobil")})
public class Mobilrental implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MOBIL_ID", nullable = false)
    @GeneratedValue
    private Integer mobilId;
    @Column(name = "NO_POLISI_AKTIF", length = 16)
    private String noPolisiAktif;
    @Column(name = "MERK", length = 32)
    private String merk;
    @Column(name = "TYPE", length = 32)
    private String type;
    @Column(name = "WARNA", length = 32)
    private String warna;
    @Column(name = "TAHUN")
    private Integer tahun;
    @Column(name = "KETERANGAN", length = 64)
    private String keterangan;
    @Column(name = "STATUS_MOBIL", length = 32)
    private String statusMobil;
    @OneToMany(mappedBy = "mobilrental")
    private List<Rental> rentals;

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
    

    public Mobilrental() {
    }

    public Mobilrental(Integer mobilId) {
        this.mobilId = mobilId;
    }

    public Integer getMobilId() {
        return mobilId;
    }

    public void setMobilId(Integer mobilId) {
        Integer oldMobilId = this.mobilId;
        this.mobilId = mobilId;
        changeSupport.firePropertyChange("mobilId", oldMobilId, mobilId);
    }
    public String getMobilString()
    {
        
        return null;
    }

    public String getNoPolisiAktif() {
        return noPolisiAktif;
    }

    public void setNoPolisiAktif(String noPolisiAktif) {
        String oldNoPolisiAktif = this.noPolisiAktif;
        this.noPolisiAktif = noPolisiAktif;
        changeSupport.firePropertyChange("noPolisiAktif", oldNoPolisiAktif, noPolisiAktif);
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        String oldMerk = this.merk;
        this.merk = merk;
        changeSupport.firePropertyChange("merk", oldMerk, merk);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        String oldType = this.type;
        this.type = type;
        changeSupport.firePropertyChange("type", oldType, type);
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        String oldWarna = this.warna;
        this.warna = warna;
        changeSupport.firePropertyChange("warna", oldWarna, warna);
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        Integer oldTahun = this.tahun;
        this.tahun = tahun;
        changeSupport.firePropertyChange("tahun", oldTahun, tahun);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public String getStatusMobil() {
        return statusMobil;
    }

    public void setStatusMobil(String statusMobil) {
        String oldStatusMobil = this.statusMobil;
        this.statusMobil = statusMobil;
        changeSupport.firePropertyChange("statusMobil", oldStatusMobil, statusMobil);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mobilId != null ? mobilId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mobilrental)) {
            return false;
        }
        Mobilrental other = (Mobilrental) object;
        if ((this.mobilId == null && other.mobilId != null) || (this.mobilId != null && !this.mobilId.equals(other.mobilId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "<html>Mobil Merk =" + merk
                + "Mobil Tipe =" + type+"</html>";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
