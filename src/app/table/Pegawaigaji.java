/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@XmlRootElement
public class Pegawaigaji extends Pengeluaran implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @JoinColumn(name = "PEGAWAIGAJIID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pegawai pegawaigajiid;

    public Pegawaigaji() {
    }

    public Pegawaigaji(Integer gajiid) {
    }


    public Pegawai getPegawaigajiid() {
        return pegawaigajiid;
    }

    public void setPegawaigajiid(Pegawai pegawaigajiid) {
        Pegawai oldPegawaigajiid = this.pegawaigajiid;
        this.pegawaigajiid = pegawaigajiid;
        changeSupport.firePropertyChange("pegawaigajiid", oldPegawaigajiid, pegawaigajiid);
    }

    @Override
    public int hashCode() {
        int hash = 0;
//        hash += (gajiid != null ? gajiid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pegawaigaji)) {
            return false;
        }
        Pegawaigaji other = (Pegawaigaji) object;
//        if ((this.gajiid == null && other.gajiid != null) || (this.gajiid != null && !this.gajiid.equals(other.gajiid))) {
//            return false;
//        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Pegawaigaji[ gajiid=" + this.getId()+ " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
