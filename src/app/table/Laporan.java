/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "LAPORAN", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Laporan.findAll", query = "SELECT l FROM Laporan l")
    , @NamedQuery(name = "Laporan.findById", query = "SELECT l FROM Laporan l WHERE l.id = :id")
    , @NamedQuery(name = "Laporan.findByDtype", query = "SELECT l FROM Laporan l WHERE l.dtype = :cari")
    , @NamedQuery(name = "Laporan.findByJumlah", query = "SELECT l FROM Laporan l WHERE l.jumlah = :jumlah")
    , @NamedQuery(name = "Laporan.findBySaldo", query = "SELECT l FROM Laporan l WHERE l.saldo = :saldo")
    , @NamedQuery(name = "Laporan.findByTanggal", query = "SELECT l FROM Laporan l WHERE l.tanggal = :tanggal")
    , @NamedQuery(name = "Laporan.findByTipe", query = "SELECT l FROM Laporan l WHERE l.tipe = :tipe")})
public class Laporan implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "DTYPE", length = 31)
    private String dtype;
    @Column(name = "JUMLAH", nullable = false)
    private BigInteger jumlah = BigInteger.ZERO;
    @Lob
    @Column(name = "KETERANGAN")
    private String keterangan;
    @Column(name = "SALDO")
    private BigInteger saldo;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal = new Date();
    @Column(name = "TIPE", length = 255)
    private String tipe;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Saldo Transaksi;

    public Saldo getTransaksi() {
        return Transaksi;
    }

    public void setTransaksi(Saldo Transaksi) {
        this.Transaksi = Transaksi;
    }
    
    public Laporan() {
//        this.Transaksi.setBankId(new Bank(1));
    }

    public Laporan(Long id) {
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

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        String oldDtype = this.dtype;
        this.dtype = dtype;
        changeSupport.firePropertyChange("dtype", oldDtype, dtype);
    }
        @Transient
        boolean name = (this instanceof Pemasukan) || (this instanceof BayarJasaPemasukan) ;
    public BigInteger getJumlah() {
        return jumlah;
    }
        
    public BigInteger getPemasukan() {
        return name? jumlah :BigInteger.ZERO ;
    }
    
    public BigInteger getPengeluaran() {
        return name? BigInteger.ZERO : jumlah;
    }
    


    public void setPemasukan(BigInteger jumlah) {
        BigInteger oldJumlah = this.jumlah;
        this.jumlah = jumlah;
        changeSupport.firePropertyChange("jumlah", oldJumlah, jumlah);
    }
    public void setJumlah(BigInteger jumlah) {
        BigInteger oldJumlah = this.jumlah;
        this.jumlah = jumlah;
        changeSupport.firePropertyChange("jumlah", oldJumlah, jumlah);
    }
    public void setPengeluaran(BigInteger jumlah) {
        BigInteger oldJumlah = this.jumlah;
        this.jumlah = jumlah;
        changeSupport.firePropertyChange("jumlah", oldJumlah, jumlah);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public BigInteger getSaldo() {
        return saldo;
    }

    public void setSaldo(BigInteger saldo) {
        BigInteger oldSaldo = this.saldo;
        this.saldo = saldo;
        changeSupport.firePropertyChange("saldo", oldSaldo, saldo);
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        Date oldTanggal = this.tanggal;
        this.tanggal = tanggal;
        changeSupport.firePropertyChange("tanggal", oldTanggal, tanggal);
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        String oldTipe = this.tipe;
        this.tipe = tipe;
        changeSupport.firePropertyChange("tipe", oldTipe, tipe);
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
        if (!(object instanceof Laporan)) {
            return false;
        }
        Laporan other = (Laporan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Laporan[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
