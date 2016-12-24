/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "HUTANG", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hutang.findAll", query = "SELECT h FROM Hutang h")
    , @NamedQuery(name = "Hutang.findByHutangid", query = "SELECT h FROM Hutang h WHERE h.hutangid = :hutangid")
    , @NamedQuery(name = "Hutang.findByAlamat", query = "SELECT h FROM Hutang h WHERE h.alamat = :alamat")
    , @NamedQuery(name = "Hutang.findByJumlahpinjaman", query = "SELECT h FROM Hutang h WHERE h.jumlahpinjaman = :jumlahpinjaman")
    , @NamedQuery(name = "Hutang.findByNama", query = "SELECT h FROM Hutang h WHERE h.nama = :nama")
    , @NamedQuery(name = "Hutang.findByNomorhp", query = "SELECT h FROM Hutang h WHERE h.nomorhp = :nomorhp")
    , @NamedQuery(name = "Hutang.findByNomorktp", query = "SELECT h FROM Hutang h WHERE h.nomorktp = :nomorktp")
    , @NamedQuery(name = "Hutang.findBySisapinjaman", query = "SELECT h FROM Hutang h WHERE h.sisapinjaman = :sisapinjaman")
    , @NamedQuery(name = "Hutang.findByTanggallunas", query = "SELECT h FROM Hutang h WHERE h.tanggallunas = :tanggallunas")
    , @NamedQuery(name = "Hutang.findByTanggalpinjam", query = "SELECT h FROM Hutang h WHERE h.tanggalpinjam = :tanggalpinjam")})
public class Hutang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false) @GeneratedValue
    @Column(name = "HUTANGID", nullable = false)
    private Integer hutangid;
    @Column(name = "ALAMAT", length = 255)
    private String alamat = "input";
    @Column(name = "JUMLAHPINJAMAN")
    private BigInteger jumlahpinjaman = new BigInteger("0");
    @Lob
    @Column(name = "KETERANGAN")
    private String keterangan ="input" ;
    @Column(name = "NAMA", length = 255)
    private String nama = "input";
    @Column(name = "NOMORHP", length = 255)
    private String nomorhp = "input";
    @Column(name = "NOMORKTP", length = 255)
    private String nomorktp = "input";
    @Column(name = "SISAPINJAMAN")
    private BigInteger sisapinjaman = new BigInteger("0");
    @Column(name = "TANGGALLUNAS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggallunas = new Date();
    @Column(name = "TANGGALPINJAM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalpinjam = new Date();
    @OneToMany(mappedBy = "hutangid",cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<Bayarhutang> bayarhutangs;

    public List<Bayarhutang> getBayarhutangs() {
        return bayarhutangs;
    }

    public void setBayarhutangs(List<Bayarhutang> bayarhutangs) {
        this.bayarhutangs = bayarhutangs;
    }

    public Hutang() {
    }

    public Hutang(Integer hutangid) {
        this.hutangid = hutangid;
    }

    public Integer getHutangid() {
        return hutangid;
    }

    public void setHutangid(Integer hutangid) {
        this.hutangid = hutangid;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public BigInteger getJumlahpinjaman() {
        return jumlahpinjaman;
    }

    public void setJumlahpinjaman(BigInteger jumlahpinjaman) {
        this.jumlahpinjaman = jumlahpinjaman;
    }

    public String getKeterangan() {
//        if (sisapinjaman != null) {
//        int res = this.sisapinjaman.compareTo(BigInteger.ZERO);                
//        String temp =  (res < 0)?"[Lunas]":"[Belum Lunas]";
//        System.out.println("temp = " + temp);            
//        }
        return keterangan;
    }
    public String getLunas()
    {
        if (sisapinjaman != null) {
        int res = this.sisapinjaman.compareTo(BigInteger.ZERO);                
        String temp =  (res <= 0)?"[Lunas]":"[Belum Lunas]";
        return temp;
        }
        return "Null";
    }
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorhp() {
        return nomorhp;
    }

    public void setNomorhp(String nomorhp) {
        this.nomorhp = nomorhp;
    }

    public String getNomorktp() {
        return nomorktp;
    }

    public void setNomorktp(String nomorktp) {
        this.nomorktp = nomorktp;
    }

    public BigInteger getSisapinjaman() {
        return sisapinjaman;
    }

    public void setSisapinjaman(BigInteger sisapinjaman) {
        this.sisapinjaman = sisapinjaman;
    }

    public Date getTanggallunas() {
        return tanggallunas;
    }

    public void setTanggallunas(Date tanggallunas) {
        this.tanggallunas = tanggallunas;
    }

    public Date getTanggalpinjam() {
        return tanggalpinjam;
    }

    public void setTanggalpinjam(Date tanggalpinjam) {
        this.tanggalpinjam = tanggalpinjam;
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

    
}
