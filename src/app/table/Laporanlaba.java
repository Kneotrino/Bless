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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
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
@Table(name = "LAPORANLABA", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Laporanlaba.findAll", query = "SELECT l FROM Laporanlaba l")
    , @NamedQuery(name = "Laporanlaba.findByRef", query = "SELECT l FROM Laporanlaba l WHERE l.ref = :ref")
    , @NamedQuery(name = "Laporanlaba.findByKe", query = "SELECT l FROM Laporanlaba l WHERE l.ke = :ke")
    , @NamedQuery(name = "Laporanlaba.findByKeterangan", query = "SELECT l FROM Laporanlaba l WHERE l.keterangan = :keterangan")
    , @NamedQuery(name = "Laporanlaba.findByTanggal", query = "SELECT l FROM Laporanlaba l WHERE l.tanggal = :tanggal")})
public class Laporanlaba implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "REF", nullable = false)
    private Integer ref;
    @Column(name = "KE")
    private Integer ke;
    @Column(name = "KETERANGAN", length = 255)
    private String keterangan;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @OneToMany(mappedBy = "laporanlabaRef", cascade = {CascadeType.ALL})
    private List<Laba> labaList;
    @PostPersist
    public void logPersist()
    {
        try {
            javax.swing.JOptionPane.showMessageDialog(null,"Berhasil Menyimpan \n");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,"Gagal Menyimpan \n");
        }
            
    }
    @PostRemove
    public void logRemove()
    {
        try {
            javax.swing.JOptionPane.showMessageDialog(null,"Berhasil Menghapus \n");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,"Gagal Menghapus \n");
        }
            
    }

    public Laporanlaba() {
    }

    public Laporanlaba(Integer ref) {
        this.ref = ref;
    }

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        Integer oldRef = this.ref;
        this.ref = ref;
        changeSupport.firePropertyChange("ref", oldRef, ref);
    }

    public Integer getKe() {
        return ke;
    }

    public void setKe(Integer ke) {
        Integer oldKe = this.ke;
        this.ke = ke;
        changeSupport.firePropertyChange("ke", oldKe, ke);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        Date oldTanggal = this.tanggal;
        this.tanggal = tanggal;
        changeSupport.firePropertyChange("tanggal", oldTanggal, tanggal);
    }

    @XmlTransient
    public List<Laba> getLabaList() {
        return labaList;
    }

    public void setLabaList(List<Laba> labaList) {
        this.labaList = labaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ref != null ? ref.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Laporanlaba)) {
            return false;
        }
        Laporanlaba other = (Laporanlaba) object;
        if ((this.ref == null && other.ref != null) || (this.ref != null && !this.ref.equals(other.ref))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Laporanlaba[ ref=" + ref + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
