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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    @ManyToOne
    private Investor investorId;
    @OneToOne
    private Modal modal;
    @OneToOne
    private Prive prive;

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
    
}
