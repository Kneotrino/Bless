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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pengeluaran.findAll", query = "SELECT p FROM Pengeluaran p")
    , @NamedQuery(name = "Pengeluaran.findById", query = "SELECT p FROM Pengeluaran p WHERE p.id = :id")
    , @NamedQuery(name = "Pengeluaran.findByJumlah", query = "SELECT p FROM Pengeluaran p WHERE p.jumlah = :jumlah")
    , @NamedQuery(name = "Pengeluaran.findByTanggal", query = "SELECT p FROM Pengeluaran p WHERE p.tanggal = :tanggal")})
@ListUrutan({"tanggal","keterangan","jumlah"})
public class Pengeluaran extends Laporan implements Serializable {

    @Transient
     PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "JUMLAH")
    private BigInteger jumlah;
    @Lob
    @Column(name = "KETERANGAN")
    private String keterangan;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @OneToOne(mappedBy = "Transfer")
    private Perjalanan perjalanan;

    public Pengeluaran() {
    }

    public Pengeluaran(Long id) {
        this.id = id;
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
        if (!(object instanceof Pengeluaran)) {
            return false;
        }
        Pengeluaran other = (Pengeluaran) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String getKelas() {
        return this.getClass().getSimpleName() + " Operasional " + getTipe();
    }
    @Override
    public String toString() {
        return "app.table.Pengeluaran[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    @Override
        public String getJenis()
    {
        return "Operasional";
    }
}
