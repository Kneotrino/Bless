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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "BARANG", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barang.findAll", query = "SELECT b FROM Barang b")
    , @NamedQuery(name = "Barang.findById", query = "SELECT b FROM Barang b WHERE b.id = :id")
    , @NamedQuery(name = "Barang.findByNama", query = "SELECT b FROM Barang b WHERE b.nama = :nama")
    , @NamedQuery(name = "Barang.findByKeterangan", query = "SELECT b FROM Barang b WHERE b.keterangan = :keterangan")
    , @NamedQuery(name = "Barang.findByHargaunit", query = "SELECT b FROM Barang b WHERE b.hargaunit = :hargaunit")
    , @NamedQuery(name = "Barang.findByStock", query = "SELECT b FROM Barang b WHERE b.stock = :stock")})
@ListUrutan({"nama"
        , "stock"
        , "hargaunit"
        ,"keterangan"
        , "Tanggal"
})
public class Barang implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NAMA", length = 255)
    private String nama = "";
    @Column(name = "KETERANGAN", length = 255)
    private String keterangan = "";
    @Column(name = "HARGAUNIT")
    private BigInteger hargaunit = BigInteger.ZERO;
    @Column(name = "STOCK")
    private BigInteger stock = BigInteger.ZERO;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Tanggal = new Date();

    public static final String PROP_TANGGAL = "Tanggal";

    /**
     * Get the value of Tanggal
     *
     * @return the value of Tanggal
     */
    public Date getTanggal() {
        return Tanggal;
    }

    /**
     * Set the value of Tanggal
     *
     * @param Tanggal new value of Tanggal
     */
    public void setTanggal(Date Tanggal) {
        Date oldTanggal = this.Tanggal;
        this.Tanggal = Tanggal;
        changeSupport.firePropertyChange(PROP_TANGGAL, oldTanggal, Tanggal);
    }

    public Barang() {
    }

    public Barang(Integer id) {
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public BigInteger getHargaunit() {
        return hargaunit;
    }

    public void setHargaunit(BigInteger hargaunit) {
        BigInteger oldHargaunit = this.hargaunit;
        this.hargaunit = hargaunit;
        changeSupport.firePropertyChange("hargaunit", oldHargaunit, hargaunit);
    }

    public BigInteger getStock() {
        return stock;
    }

    public void setStock(BigInteger stock) {
        BigInteger oldStock = this.stock;
        this.stock = stock;
        changeSupport.firePropertyChange("stock", oldStock, stock);
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
        if (!(object instanceof Barang)) {
            return false;
        }
        Barang other = (Barang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Barang[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    public BigInteger getTotalHarga()
    {
        return hargaunit.multiply(stock);
    }
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

    
}
