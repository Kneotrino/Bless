/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "HUTANG", catalog = "", schema = "BLESSING")
@XmlRootElement
@app.ListUrutan({
    "nama"
        ,"alamat"
        ,"nomorhp"
        ,"nomorktp"
//        ,"jumlahpinjaman"
//        ,"sisapinjaman"
        ,"tanggalpinjam" 
//        ,"keterangan"
//        ,"tanggallunas"
})
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

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false) @GeneratedValue
    @Column(name = "HUTANGID", nullable = false)
    private Integer hutangid;
    @Column(name = "ALAMAT", length = 255)
    private String alamat = "";
    @Column(name = "JUMLAHPINJAMAN")
    private BigInteger jumlahpinjaman = new BigInteger("0");
    @Lob
    @Column(name = "KETERANGAN")
    private String keterangan ="" ;
    @Column(name = "NAMA", length = 255)
    private String nama = " TOTAL";
    @Column(name = "NOMORHP", length = 255)
    private String nomorhp = "";
    @Column(name = "NOMORKTP", length = 255)
    private String nomorktp = "";
    
    @Column(name = "LABA", length = 255)
    private String LABA = "OPEN";

    public static final String PROP_LABA = "LABA";
    @OneToOne(mappedBy = "h",cascade = CascadeType.ALL)
    private BagiLaba bagiLaba = new BagiLaba();

    /**
     * Get the value of LABA
     *
     * @return the value of LABA
     */
    public String getLABA() {
        return LABA;
    }

    /**
     * Set the value of LABA
     *
     * @param LABA new value of LABA
     */
    public void setLABA(String LABA) {
        String oldLABA = this.LABA;
        this.LABA = LABA;
        changeSupport.firePropertyChange(PROP_LABA, oldLABA, LABA);
    }

    @Column(name = "SISAPINJAMAN")
    private BigInteger sisapinjaman = new BigInteger("0");
    @Column(name = "TANGGALLUNAS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggallunas = new Date();
    @Column(name = "TANGGALPINJAM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalpinjam = new Date();
    @OneToMany(mappedBy = "hutangid",cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    @OrderBy("tanggal ASC")
    private List<Bayarhutang> bayarhutangs = new LinkedList<>();
    @Column(name = "BUNGA")
    private BigInteger bunga = BigInteger.ZERO;

    public static final String PROP_BUNGA = "bunga";

    /**
     * Get the value of bunga
     *
     * @return the value of bunga
     */
    public BigInteger getBunga() {
        return bunga;
    }
    

    /**
     * Set the value of bunga
     *
     * @param bunga new value of bunga
     */
    public void setBunga(BigInteger bunga) {
        BigInteger oldBunga = this.bunga;
        this.bunga = bunga;
//        Hitung();
        changeSupport.firePropertyChange(PROP_BUNGA, oldBunga, bunga);
    }
    

    public List<Bayarhutang> getBayarhutangs() {
        List<Bayarhutang> name;
        BayarhutangPemasukan pemasukan = new BayarhutangPemasukan();
        BayarhutangPengeluaran pengeluaran = new BayarhutangPengeluaran();
        name = (List<Bayarhutang>) app.table.Util.hitungSaldo(bayarhutangs, pemasukan,pengeluaran);
        if (!name.contains(pemasukan)) {
            name.add(pemasukan);            
            name.add(pengeluaran);            
        } 
        return name;
    }

    public void setBayarhutangs(List<Bayarhutang> bayarhutangs) {
        this.bayarhutangs = bayarhutangs;
    }
public  BigInteger gettotalPemasukan()
    {
        return Util.getTotalPemasukan(bayarhutangs);
    }
    public  BigInteger gettotalPengeluaran()
    {
        return Util.getTotalPengeluaran(bayarhutangs);
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
        String oldAlamat = this.alamat;
        this.alamat = alamat;
        changeSupport.firePropertyChange("alamat", oldAlamat, alamat);
    }

    public BigInteger getJumlahpinjaman() {
        return jumlahpinjaman;
    }
    public BigInteger getJumlahKeluar() {
                BigInteger temp = BigInteger.ZERO;
        for (Bayarhutang bayars : bayarhutangs) {
            temp = temp.add(bayars.getPengeluaran());
        }
        return temp;
    }
    public BigInteger getJumlahKembali() {
                BigInteger temp = BigInteger.ZERO;
        for (Bayarhutang bayars : bayarhutangs) {
            temp = temp.add(bayars.getPemasukan());
        }
        return temp;
    }
    public BigInteger getKembaliKurangKeluar()
    {
        return getJumlahKembali().subtract(getJumlahKeluar());        
    }
    public BigInteger getKembaliKurangKeluarKurangBunga()
    {
        return getJumlahKembali().subtract(getJumlahKeluar()).subtract(getBunga());        
    }
    public BigInteger getJumlahTotal() {        
        return getJumlahKeluar().subtract(getJumlahKembali());
    }
    

    public void setJumlahpinjaman(BigInteger jumlahpinjaman) {
        BigInteger oldJumlahpinjaman = this.jumlahpinjaman;
        this.jumlahpinjaman = jumlahpinjaman;
        changeSupport.firePropertyChange("jumlahpinjaman", oldJumlahpinjaman, jumlahpinjaman);
    }

    public String getKeterangan() {
        return keterangan;
    }
    public String getLunas()
    {
        if (sisapinjaman != null) {
            int res = this.getJumlahKembali()
                    .subtract(getJumlahKeluar())
                    .subtract(getBunga())
                    .compareTo(BigInteger.ZERO);                
            String tem =  (res >= 0)?"[Lunas]":"[Belum Lunas]";
            return tem;
        }
        return "";
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
//        changeSupport.fi
    }
    @PostPersist
    public void Hitung()
    {
//        BigInteger temp = BigInteger.ZERO;
//        for (Bayarhutang bayars : bayarhutangs) {
//            temp = temp.add(bayars.getPengeluaran());
//            temp = temp.subtract(bayars.getPemasukan());            
//        }
//        temp = temp.add(bunga);
//        setSisapinjaman(temp);
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
        return "Jasa Hutang[ Ref=" 
                + hutangid 
                + "-" 
                + nama 
                + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
}
}
