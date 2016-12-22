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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "PEGAWAI", catalog = "", schema = "BLESSING")
@XmlRootElement
 @app.ListUrutan({"nama","alamat","nomorhp","pass","status","tanggalGajian","tanggalMasuk" })
public class Pegawai implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false) 
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "ALAMAT", length = 255)
    private String alamat;
    @Column(name = "NAMA", length = 255)
    private String nama;
    @Column(name = "NOMORHP", length = 32)
    private String nomorhp;
    @Column(name = "PASS", length = 255)
    private String pass;
    @Column(name = "STATUS", length = 25)
    private String status;
    @Column(name = "TANGGAL_GAJIAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalGajian;
    @Column(name = "TANGGAL_MASUK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalMasuk;
    @OneToMany(mappedBy = "pegawaigajiid"
            , fetch = FetchType.LAZY
            ,cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<Pegawaigaji> pegawaigajiList;

    public Pegawai() {
    }

    public Pegawai(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        String oldAlamat = this.alamat;
        this.alamat = alamat;
        changeSupport.firePropertyChange("alamat", oldAlamat, alamat);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    public String getNomorhp() {
        return nomorhp;
    }

    public void setNomorhp(String nomorhp) {
        String oldNomorhp = this.nomorhp;
        this.nomorhp = nomorhp;
        changeSupport.firePropertyChange("nomorhp", oldNomorhp, nomorhp);
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        String oldPass = this.pass;
        this.pass = pass;
        changeSupport.firePropertyChange("pass", oldPass, pass);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        String oldStatus = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldStatus, status);
    }

    public Date getTanggalGajian() {
        return tanggalGajian;
    }

    public void setTanggalGajian(Date tanggalGajian) {
        Date oldTanggalGajian = this.tanggalGajian;
        this.tanggalGajian = tanggalGajian;
        changeSupport.firePropertyChange("tanggalGajian", oldTanggalGajian, tanggalGajian);
    }

    public Date getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(Date tanggalMasuk) {
        Date oldTanggalMasuk = this.tanggalMasuk;
        this.tanggalMasuk = tanggalMasuk;
        changeSupport.firePropertyChange("tanggalMasuk", oldTanggalMasuk, tanggalMasuk);
    }

    @XmlTransient
    public List<Pegawaigaji> getPegawaigajiList() {
        return pegawaigajiList;
    }

    public void setPegawaigajiList(List<Pegawaigaji> pegawaigajiList) {
        this.pegawaigajiList = pegawaigajiList;
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
        if (!(object instanceof Pegawai)) {
            return false;
        }
        Pegawai other = (Pegawai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ REF=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
