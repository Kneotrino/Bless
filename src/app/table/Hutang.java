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
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
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
@Table(catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hutang.findAll", query = "SELECT h FROM Hutang h")
    , @NamedQuery(name = "Hutang.findByHutangid", query = "SELECT h FROM Hutang h WHERE h.hutangid = :hutangid")
    , @NamedQuery(name = "Hutang.findByTanggallunas", query = "SELECT h FROM Hutang h WHERE h.tanggallunas = :tanggallunas")
    , @NamedQuery(name = "Hutang.findByTanggalpinjam", query = "SELECT h FROM Hutang h WHERE h.tanggalpinjam = :tanggalpinjam")
    , @NamedQuery(name = "Hutang.findByAlamat", query = "SELECT h FROM Hutang h WHERE h.alamat = :alamat")
    , @NamedQuery(name = "Hutang.findByJumlahpinjaman", query = "SELECT h FROM Hutang h WHERE h.jumlahpinjaman = :jumlahpinjaman")
    , @NamedQuery(name = "Hutang.findByNama", query = "SELECT h FROM Hutang h WHERE h.nama = :nama")
    , @NamedQuery(name = "Hutang.findByNomorhp", query = "SELECT h FROM Hutang h WHERE h.nomorhp = :nomorhp")
    , @NamedQuery(name = "Hutang.findByNomorktp", query = "SELECT h FROM Hutang h WHERE h.nomorktp = :nomorktp")
    , @NamedQuery(name = "Hutang.findBySisapinjaman", query = "SELECT h FROM Hutang h WHERE h.sisapinjaman = :sisapinjaman")})
public class Hutang implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)    @GeneratedValue

    private Integer hutangid;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggallunas;
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalpinjam;
    @Column(length = 255)
    private String alamat;
    private BigInteger jumlahpinjaman;
    @Lob
    private String keterangan;
    @Column(length = 255)
    private String nama;
    @Column(length = 255)
    private String nomorhp;
    @Column(length = 255)
    private String nomorktp;
    private BigInteger sisapinjaman;
    @OneToMany(mappedBy = "hutangid", cascade = CascadeType.ALL)
    private List<Bayarhutang> bayarhutangList;    
    @Transient
    private BigInteger TotalPembayaran = new BigInteger("0"); 

    public static final String PROP_TOTALPEMBAYARAN = "TotalPembayaran";

    /**
     * Get the value of TotalPembayaran
     *
     * @return the value of TotalPembayaran
     */
    public BigInteger getTotalPembayaran() {        
        return total();
    }

    /**
     * Set the value of TotalPembayaran
     *
     * @param TotalPembayaran new value of TotalPembayaran
     */
    public void setTotalPembayaran(BigInteger TotalPembayaran) {        
        BigInteger oldTotalPembayaran = this.TotalPembayaran;
        this.TotalPembayaran = TotalPembayaran;
        changeSupport.firePropertyChange(PROP_TOTALPEMBAYARAN, oldTotalPembayaran, TotalPembayaran);
    }

    @PrePersist
    public void setting()
    {
        this.tanggalpinjam = new Date();
        this.tanggallunas = new Date();
    }
    public Hutang() {
    }

    public Hutang(Integer hutangid) {
        this.hutangid = hutangid;
    }
    public BigInteger total()
    {
        BigInteger temp = new BigInteger("0");
        long t = 0l;
        for (Bayarhutang b : bayarhutangList) {
            long a = b.getPembayaran().longValue();
            t+=a;
        }
        return BigInteger.valueOf(t) ;
    }
    public Integer getHutangid() {
        return hutangid;
    }

    public void setHutangid(Integer hutangid) {
        Integer oldHutangid = this.hutangid;
        this.hutangid = hutangid;
        changeSupport.firePropertyChange("hutangid", oldHutangid, hutangid);
    }

    public Date getTanggallunas() {
        return tanggallunas;
    }

    public void setTanggallunas(Date tanggallunas) {
        Date oldTanggallunas = this.tanggallunas;
        this.tanggallunas = tanggallunas;
        changeSupport.firePropertyChange("tanggallunas", oldTanggallunas, tanggallunas);
    }

    public Date getTanggalpinjam() {
        return tanggalpinjam;
    }

    public void setTanggalpinjam(Date tanggalpinjam) {
        Date oldTanggalpinjam = this.tanggalpinjam;
        this.tanggalpinjam = tanggalpinjam;
        changeSupport.firePropertyChange("tanggalpinjam", oldTanggalpinjam, tanggalpinjam);
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        String oldAlamat = this.alamat;
        this.alamat = alamat;
        changeSupport.firePropertyChange("alamat", oldAlamat, alamat);
    }

    public BigInteger getJumlahpinjaman() {
        return jumlahpinjaman;
    }

    public void setJumlahpinjaman(BigInteger jumlahpinjaman) {
        BigInteger oldJumlahpinjaman = this.jumlahpinjaman;
        this.jumlahpinjaman = jumlahpinjaman;
        changeSupport.firePropertyChange("jumlahpinjaman", oldJumlahpinjaman, jumlahpinjaman);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
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

    public String getNomorktp() {
        return nomorktp;
    }

    public void setNomorktp(String nomorktp) {
        String oldNomorktp = this.nomorktp;
        this.nomorktp = nomorktp;
        changeSupport.firePropertyChange("nomorktp", oldNomorktp, nomorktp);
    }

    public BigInteger getSisapinjaman() {
        return sisapinjaman;
    }

    public void setSisapinjaman(BigInteger sisapinjaman) {
        BigInteger oldSisapinjaman = this.sisapinjaman;
        this.sisapinjaman = sisapinjaman;
        changeSupport.firePropertyChange("sisapinjaman", oldSisapinjaman, sisapinjaman);
    }

    @XmlTransient
    public List<Bayarhutang> getBayarhutangList() {
        return bayarhutangList;
    }

    public void setBayarhutangList(List<Bayarhutang> bayarhutangList) {
        this.bayarhutangList = bayarhutangList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hutangid != null ? hutangid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hutang)) {
            return false;
        }
        Hutang other = (Hutang) object;
        if ((this.hutangid == null && other.hutangid != null) || (this.hutangid != null && !this.hutangid.equals(other.hutangid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Hutang[ hutangid=" + hutangid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
