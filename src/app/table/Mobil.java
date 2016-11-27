/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "MOBIL", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mobil.findAll", query = "SELECT m FROM Mobil m"),
    @NamedQuery(name = "Mobil.findByMobilId", query = "SELECT m FROM Mobil m WHERE m.mobilId = :mobilId"),
    @NamedQuery(name = "Mobil.findByBahanBakar", query = "SELECT m FROM Mobil m WHERE m.bahanBakar = :bahanBakar"),
    @NamedQuery(name = "Mobil.findByDpPertama", query = "SELECT m FROM Mobil m WHERE m.dpPertama = :dpPertama"),
    @NamedQuery(name = "Mobil.findByHargaJual", query = "SELECT m FROM Mobil m WHERE m.hargaJual = :hargaJual"),
    @NamedQuery(name = "Mobil.findByHargaPembelian", query = "SELECT m FROM Mobil m WHERE m.hargaPembelian = :hargaPembelian"),
    @NamedQuery(name = "Mobil.findByKeterangan", query = "SELECT m FROM Mobil m WHERE m.keterangan = :keterangan"),
    @NamedQuery(name = "Mobil.findByMerk", query = "SELECT m FROM Mobil m WHERE m.merk = :merk"),
    @NamedQuery(name = "Mobil.findByNoMesin", query = "SELECT m FROM Mobil m WHERE m.noMesin = :noMesin"),
    @NamedQuery(name = "Mobil.findByNoPolisiAktif", query = "SELECT m FROM Mobil m WHERE m.noPolisiAktif = :noPolisiAktif"),
    @NamedQuery(name = "Mobil.findByNoPolisiLama", query = "SELECT m FROM Mobil m WHERE m.noPolisiLama = :noPolisiLama"),
    @NamedQuery(name = "Mobil.findByNoRangka", query = "SELECT m FROM Mobil m WHERE m.noRangka = :noRangka"),
    @NamedQuery(name = "Mobil.findByPemilikBaru", query = "SELECT m FROM Mobil m WHERE m.pemilikBaru = :pemilikBaru"),
    @NamedQuery(name = "Mobil.findByPemilikLama", query = "SELECT m FROM Mobil m WHERE m.pemilikLama = :pemilikLama"),
    @NamedQuery(name = "Mobil.findByPenjual", query = "SELECT m FROM Mobil m WHERE m.penjual = :penjual"),
    @NamedQuery(name = "Mobil.findBySilinder", query = "SELECT m FROM Mobil m WHERE m.silinder = :silinder"),
    @NamedQuery(name = "Mobil.findByStatusMobil", query = "SELECT m FROM Mobil m WHERE m.statusMobil = :statusMobil"),
    @NamedQuery(name = "Mobil.findByTahun", query = "SELECT m FROM Mobil m WHERE m.tahun = :tahun"),
    @NamedQuery(name = "Mobil.findByTanggalBeli", query = "SELECT m FROM Mobil m WHERE m.tanggalBeli = :tanggalBeli"),
    @NamedQuery(name = "Mobil.findByTanggalJual", query = "SELECT m FROM Mobil m WHERE m.tanggalJual = :tanggalJual"),
    @NamedQuery(name = "Mobil.findByTangglPelunasanPembelian", query = "SELECT m FROM Mobil m WHERE m.tangglPelunasanPembelian = :tangglPelunasanPembelian"),
    @NamedQuery(name = "Mobil.findByType", query = "SELECT m FROM Mobil m WHERE m.type = :type"),
    @NamedQuery(name = "Mobil.findByWarna", query = "SELECT m FROM Mobil m WHERE m.warna = :warna")})
public class Mobil implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MOBIL_ID", nullable = false)
    @GeneratedValue
//        (strategy=GenerationType.TABLE) 
    private Integer mobilId;
    @Column(name = "BAHAN_BAKAR", length = 32)
    private String bahanBakar;
    @Column(name = "DP_PERTAMA")
    private Integer dpPertama;
    @Column(name = "HARGA_JUAL")
    private Integer hargaJual;
    @Column(name = "HARGA_PEMBELIAN")
    private Integer hargaPembelian;
    @Lob
    @Column(name = "INTERIOR_I")
    private String interiorI;
    @Lob
    @Column(name = "INTERIOR_II")
    private String interiorIi;
    @Column(name = "KETERANGAN", length = 64)
    private String keterangan;
    @Column(name = "MERK", length = 32)
    private String merk;
    @Column(name = "JENIS", length = 32)
    private String jenis;


    @Column(name = "NO_MESIN", length = 32)
    private String noMesin;
    @Column(name = "NO_POLISI_AKTIF", length = 16)
    private String noPolisiAktif;
    @Column(name = "NO_POLISI_LAMA", length = 16)
    private String noPolisiLama;
    @Column(name = "NO_RANGKA", length = 32)
    private String noRangka;
    @Column(name = "PEMILIK_BARU", length = 64)
    private String pemilikBaru;
    @Column(name = "PEMILIK_LAMA", length = 64)
    private String pemilikLama;
    @Column(name = "PENJUAL", length = 32)
    private String penjual;
    @Column(name = "SILINDER")
    private Integer silinder;
    @Column(name = "STATUS_MOBIL", length = 32)
    private String statusMobil;
    @Column(name = "TAHUN")
    private Integer tahun;
    @Lob
    @Column(name = "TAMPILAN_BLKG")
    private String tampilanBlkg;
    @Lob
    @Column(name = "TAMPILAN_DEPAN")
    private String tampilanDepan;
    @Lob
    @Column(name = "TAMPILAN_SMP_KANAN")
    private String tampilanSmpKanan;
    @Lob
    @Column(name = "TAMPILAN_SMP_KIRI")
    private String tampilanSmpKiri;
    @Column(name = "TANGGAL_BELI")
    @Temporal(TemporalType.DATE)
    private Date tanggalBeli;
    @Column(name = "TANGGAL_JUAL")
    @Temporal(TemporalType.DATE)
    private Date tanggalJual;
    @Column(name = "TANGGL_PELUNASAN_PEMBELIAN")
    @Temporal(TemporalType.DATE)
    private Date tangglPelunasanPembelian;
    @Column(name = "TYPE", length = 32)
    private String type;
    @Column(name = "WARNA", length = 32)
    private String warna;
    @JoinColumn(name = "debitur_id")    
    @OneToOne(cascade = CascadeType.ALL)
    private Debitur debitur;
    @JoinColumn(name = "bpkb_id")    
    @OneToOne(cascade = CascadeType.ALL)
    private Bpkb bpkb;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "mobils")
    private List<KeuanganMobil> keuanganMobils;
    @Transient 
    private String no_bpkb;

    public String getNo_bpkb() {
        return this.getBpkb().getNoBpkb();
    }
    public List<KeuanganMobil> getKeuanganMobils() {
        return keuanganMobils;
    }

    public void setKeuanganMobils(List<KeuanganMobil> keuanganMobils) {
        this.keuanganMobils = keuanganMobils;
    }
    
    public Bpkb getBpkb() {
        return bpkb;
    }
    
    public void setBpkb(Bpkb bpkb) {
        this.bpkb = bpkb;
    }
    public Debitur getDebitur() {
        return debitur;
    }

    public void setDebitur(Debitur debitur) {
        this.debitur = debitur;
    }
    public Mobil() {
    }
    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public Mobil(Integer mobilId) {
        this.mobilId = mobilId;
    }

    public Integer getMobilId() {
        return mobilId;
    }

    public void setMobilId(Integer mobilId) {
        Integer oldMobilId = this.mobilId;
        this.mobilId = mobilId;
        changeSupport.firePropertyChange("mobilId", oldMobilId, mobilId);
    }

    public String getBahanBakar() {
        return bahanBakar;
    }

    public void setBahanBakar(String bahanBakar) {
        String oldBahanBakar = this.bahanBakar;
        this.bahanBakar = bahanBakar;
        changeSupport.firePropertyChange("bahanBakar", oldBahanBakar, bahanBakar);
    }

    public Integer getDpPertama() {
        return dpPertama;
    }

    public void setDpPertama(Integer dpPertama) {
        Integer oldDpPertama = this.dpPertama;
        this.dpPertama = dpPertama;
        changeSupport.firePropertyChange("dpPertama", oldDpPertama, dpPertama);
    }

    public Integer getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(Integer hargaJual) {
        Integer oldHargaJual = this.hargaJual;
        this.hargaJual = hargaJual;
        changeSupport.firePropertyChange("hargaJual", oldHargaJual, hargaJual);
    }

    public Integer getHargaPembelian() {
        return hargaPembelian;
    }

    public void setHargaPembelian(Integer hargaPembelian) {
        Integer oldHargaPembelian = this.hargaPembelian;
        this.hargaPembelian = hargaPembelian;
        changeSupport.firePropertyChange("hargaPembelian", oldHargaPembelian, hargaPembelian);
    }

    public String getInteriorI() {
        return interiorI;
    }

    public void setInteriorI(String interiorI) {
        String oldInteriorI = this.interiorI;
        this.interiorI = interiorI;
        changeSupport.firePropertyChange("interiorI", oldInteriorI, interiorI);
    }

    public String getInteriorIi() {
        return interiorIi;
    }

    public void setInteriorIi(String interiorIi) {
        String oldInteriorIi = this.interiorIi;
        this.interiorIi = interiorIi;
        changeSupport.firePropertyChange("interiorIi", oldInteriorIi, interiorIi);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        String oldMerk = this.merk;
        this.merk = merk;
        changeSupport.firePropertyChange("merk", oldMerk, merk);
    }

    public String getNoMesin() {
        return noMesin;
    }

    public void setNoMesin(String noMesin) {
        String oldNoMesin = this.noMesin;
        this.noMesin = noMesin;
        changeSupport.firePropertyChange("noMesin", oldNoMesin, noMesin);
    }

    public String getNoPolisiAktif() {
        return noPolisiAktif;
    }

    public void setNoPolisiAktif(String noPolisiAktif) {
        String oldNoPolisiAktif = this.noPolisiAktif;
        this.noPolisiAktif = noPolisiAktif;
        changeSupport.firePropertyChange("noPolisiAktif", oldNoPolisiAktif, noPolisiAktif);
    }

    public String getNoPolisiLama() {
        return noPolisiLama;
    }

    public void setNoPolisiLama(String noPolisiLama) {
        String oldNoPolisiLama = this.noPolisiLama;
        this.noPolisiLama = noPolisiLama;
        changeSupport.firePropertyChange("noPolisiLama", oldNoPolisiLama, noPolisiLama);
    }

    public String getNoRangka() {
        return noRangka;
    }

    public void setNoRangka(String noRangka) {
        String oldNoRangka = this.noRangka;
        this.noRangka = noRangka;
        changeSupport.firePropertyChange("noRangka", oldNoRangka, noRangka);
    }

    public String getPemilikBaru() {
        return pemilikBaru;
    }

    public void setPemilikBaru(String pemilikBaru) {
        String oldPemilikBaru = this.pemilikBaru;
        this.pemilikBaru = pemilikBaru;
        changeSupport.firePropertyChange("pemilikBaru", oldPemilikBaru, pemilikBaru);
    }

    public String getPemilikLama() {
        return pemilikLama;
    }

    public void setPemilikLama(String pemilikLama) {
        String oldPemilikLama = this.pemilikLama;
        this.pemilikLama = pemilikLama;
        changeSupport.firePropertyChange("pemilikLama", oldPemilikLama, pemilikLama);
    }

    public String getPenjual() {
        return penjual;
    }

    public void setPenjual(String penjual) {
        String oldPenjual = this.penjual;
        this.penjual = penjual;
        changeSupport.firePropertyChange("penjual", oldPenjual, penjual);
    }

    public Integer getSilinder() {
        return silinder;
    }

    public void setSilinder(Integer silinder) {
        Integer oldSilinder = this.silinder;
        this.silinder = silinder;
        changeSupport.firePropertyChange("silinder", oldSilinder, silinder);
    }

    public String getStatusMobil() {
        return statusMobil;
    }

    public void setStatusMobil(String statusMobil) {
        String oldStatusMobil = this.statusMobil;
        this.statusMobil = statusMobil;
        changeSupport.firePropertyChange("statusMobil", oldStatusMobil, statusMobil);
    }

    public Integer getTahun() {
        return tahun;
    }

    public void setTahun(Integer tahun) {
        Integer oldTahun = this.tahun;
        this.tahun = tahun;
        changeSupport.firePropertyChange("tahun", oldTahun, tahun);
    }

    public String getTampilanBlkg() {
        return tampilanBlkg;
    }

    public void setTampilanBlkg(String tampilanBlkg) {
        String oldTampilanBlkg = this.tampilanBlkg;
        this.tampilanBlkg = tampilanBlkg;
        changeSupport.firePropertyChange("tampilanBlkg", oldTampilanBlkg, tampilanBlkg);
    }

    public String getTampilanDepan() {
        return tampilanDepan;
    }

    public void setTampilanDepan(String tampilanDepan) {
        String oldTampilanDepan = this.tampilanDepan;
        this.tampilanDepan = tampilanDepan;
        changeSupport.firePropertyChange("tampilanDepan", oldTampilanDepan, tampilanDepan);
    }

    public String getTampilanSmpKanan() {
        return tampilanSmpKanan;
    }

    public void setTampilanSmpKanan(String tampilanSmpKanan) {
        String oldTampilanSmpKanan = this.tampilanSmpKanan;
        this.tampilanSmpKanan = tampilanSmpKanan;
        changeSupport.firePropertyChange("tampilanSmpKanan", oldTampilanSmpKanan, tampilanSmpKanan);
    }

    public String getTampilanSmpKiri() {
        return tampilanSmpKiri;
    }

    public void setTampilanSmpKiri(String tampilanSmpKiri) {
        String oldTampilanSmpKiri = this.tampilanSmpKiri;
        this.tampilanSmpKiri = tampilanSmpKiri;
        changeSupport.firePropertyChange("tampilanSmpKiri", oldTampilanSmpKiri, tampilanSmpKiri);
    }

    public Date getTanggalBeli() {
        return tanggalBeli;
    }

    public void setTanggalBeli(Date tanggalBeli) {
        Date oldTanggalBeli = this.tanggalBeli;
        this.tanggalBeli = tanggalBeli;
        changeSupport.firePropertyChange("tanggalBeli", oldTanggalBeli, tanggalBeli);
    }

    public Date getTanggalJual() {
        return tanggalJual;
    }

    public void setTanggalJual(Date tanggalJual) {
        Date oldTanggalJual = this.tanggalJual;
        this.tanggalJual = tanggalJual;
        changeSupport.firePropertyChange("tanggalJual", oldTanggalJual, tanggalJual);
    }

    public Date getTangglPelunasanPembelian() {
        return tangglPelunasanPembelian;
    }

    public void setTangglPelunasanPembelian(Date tangglPelunasanPembelian) {
        Date oldTangglPelunasanPembelian = this.tangglPelunasanPembelian;
        this.tangglPelunasanPembelian = tangglPelunasanPembelian;
        changeSupport.firePropertyChange("tangglPelunasanPembelian", oldTangglPelunasanPembelian, tangglPelunasanPembelian);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        String oldType = this.type;
        this.type = type;
        changeSupport.firePropertyChange("type", oldType, type);
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        String oldWarna = this.warna;
        this.warna = warna;
        changeSupport.firePropertyChange("warna", oldWarna, warna);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mobilId != null ? mobilId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mobil)) {
            return false;
        }
        Mobil other = (Mobil) object;
        if ((this.mobilId == null && other.mobilId != null) || (this.mobilId != null && !this.mobilId.equals(other.mobilId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Mobil[ mobilId=" + mobilId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
