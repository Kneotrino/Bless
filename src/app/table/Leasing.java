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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "LEASING", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Leasing.findAll", query = "SELECT l FROM Leasing l")
    , @NamedQuery(name = "Leasing.findByLeasingId", query = "SELECT l FROM Leasing l WHERE l.leasingId = :leasingId")
    , @NamedQuery(name = "Leasing.findByNama", query = "SELECT l FROM Leasing l WHERE l.nama = :nama")})
 @ListUrutan({"nama"})
public class Leasing implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)     @GeneratedValue

    @Column(name = "LEASING_ID", nullable = false)
    private Integer leasingId;
    @Column(name = "NAMA", length = 255)
    private String nama;
    @OneToMany(mappedBy = "leasingLeasingId")
    private List<Listleasing> listleasingList;
    public Leasing() {
    }

    public Leasing(Integer leasingId) {
        this.leasingId = leasingId;
    }

    public Integer getLeasingId() {
        return leasingId;
    }

    public void setLeasingId(Integer leasingId) {
        Integer oldLeasingId = this.leasingId;
        this.leasingId = leasingId;
        changeSupport.firePropertyChange("leasingId", oldLeasingId, leasingId);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    @XmlTransient
    public List<Listleasing> getListleasingList() {
        return listleasingList;
    }

    public void setListleasingList(List<Listleasing> listleasingList) {
        this.listleasingList = listleasingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leasingId != null ? leasingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leasing)) {
            return false;
        }
        Leasing other = (Leasing) object;
        if ((this.leasingId == null && other.leasingId != null) || (this.leasingId != null && !this.leasingId.equals(other.leasingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ REF=" + leasingId + "; Nama = "+this.nama+" ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
