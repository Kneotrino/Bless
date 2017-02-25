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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author SEED
 */
@Entity
public class LaporanSaham implements Serializable {

    @OneToMany(mappedBy = "laporanSaham")
    private List<Laporanlaba> laporanlabas;

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @OneToMany(mappedBy = "laporanSaham", cascade = {CascadeType.ALL})
    private List<Relasi> relasis;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "laporanSaham", cascade = {CascadeType.ALL})
    private Laporanlaba laporanlaba;

    public PropertyChangeSupport getChangeSupport() {
        return changeSupport;
    }

    public void setChangeSupport(PropertyChangeSupport changeSupport) {
        this.changeSupport = changeSupport;
    }

    public List<Relasi> getRelasis() {
        this.relasis = org.jdesktop.observablecollections.ObservableCollections.observableList(this.relasis);
        return relasis;
    }

    public void setRelasis(List<Relasi> relasis) {
        relasis = org.jdesktop.observablecollections.ObservableCollections.observableList(relasis);
        this.relasis = relasis;
    }

    public Laporanlaba getLaporanlaba() {
        return laporanlaba;
    }

    public void setLaporanlaba(Laporanlaba laporanlaba) {
        this.laporanlaba = laporanlaba;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
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
        if (!(object instanceof LaporanSaham)) {
            return false;
        }
        LaporanSaham other = (LaporanSaham) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.LaporanSaham[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
