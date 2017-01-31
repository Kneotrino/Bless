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
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name = "BPKBTITIPAN", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bpkbtitipan.findAll", query = "SELECT b FROM Bpkbtitipan b")
    , @NamedQuery(name = "Bpkbtitipan.findByBpkbId", query = "SELECT b FROM Bpkbtitipan b WHERE b.bpkbId = :bpkbId")
    , @NamedQuery(name = "Bpkbtitipan.findByAnBpkb", query = "SELECT b FROM Bpkbtitipan b WHERE b.anBpkb = :anBpkb")
    , @NamedQuery(name = "Bpkbtitipan.findByKet", query = "SELECT b FROM Bpkbtitipan b WHERE b.ket = :ket")
    , @NamedQuery(name = "Bpkbtitipan.findByNoBpkb", query = "SELECT b FROM Bpkbtitipan b WHERE b.noBpkb = :noBpkb")
    , @NamedQuery(name = "Bpkbtitipan.findByNoPolisiAktif", query = "SELECT b FROM Bpkbtitipan b WHERE b.noPolisiAktif = :noPolisiAktif")
    , @NamedQuery(name = "Bpkbtitipan.findByPosisi", query = "SELECT b FROM Bpkbtitipan b WHERE b.posisi = :posisi")
    , @NamedQuery(name = "Bpkbtitipan.findByStatus", query = "SELECT b FROM Bpkbtitipan b WHERE b.status = :status")
    , @NamedQuery(name = "Bpkbtitipan.findByStnk", query = "SELECT b FROM Bpkbtitipan b WHERE b.stnk = :stnk")
    , @NamedQuery(name = "Bpkbtitipan.findByTglBbn", query = "SELECT b FROM Bpkbtitipan b WHERE b.tglBbn = :tglBbn")
    , @NamedQuery(name = "Bpkbtitipan.findByTglCb", query = "SELECT b FROM Bpkbtitipan b WHERE b.tglCb = :tglCb")
    , @NamedQuery(name = "Bpkbtitipan.findByTglKembaliBbn", query = "SELECT b FROM Bpkbtitipan b WHERE b.tglKembaliBbn = :tglKembaliBbn")
    , @NamedQuery(name = "Bpkbtitipan.findByTglKembaliCb", query = "SELECT b FROM Bpkbtitipan b WHERE b.tglKembaliCb = :tglKembaliCb")
    , @NamedQuery(name = "Bpkbtitipan.findByTglLeasing", query = "SELECT b FROM Bpkbtitipan b WHERE b.tglLeasing = :tglLeasing")
    , @NamedQuery(name = "Bpkbtitipan.findByTglTerima", query = "SELECT b FROM Bpkbtitipan b WHERE b.tglTerima = :tglTerima")})
//@ListUrutan({"anBpkb", "posisi", "tglBbn"})
 @ListUrutan({"anBpkb","ket","noBpkb","noPolisiAktif","posisi","status","stnk","tglTerima"})

public class Bpkbtitipan implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)     
    @GeneratedValue
    @Column(name = "BPKB_ID", nullable = false)
    private Integer bpkbId;
    @Column(name = "AN_BPKB", length = 64)
    private String anBpkb;
    @Column(name = "KET", length = 128)
    private String ket;
    @Column(name = "NO_BPKB", length = 64)
    private String noBpkb;
    @Column(name = "NO_POLISI_AKTIF", length = 64)
    private String noPolisiAktif;
    @Column(name = "POSISI", length = 64)
    private String posisi;
    @Column(name = "STATUS", length = 32)
    private String status;
    @Column(name = "STNK", length = 64)
    private String stnk;
    @Column(name = "TGL_BBN")
    @Temporal(TemporalType.DATE)
    private Date tglBbn;
    @Column(name = "TGL_CB")
    @Temporal(TemporalType.DATE)
    private Date tglCb;
    @Column(name = "TGL_KEMBALI_BBN")
    @Temporal(TemporalType.DATE)
    private Date tglKembaliBbn;
    @Column(name = "TGL_KEMBALI_CB")
    @Temporal(TemporalType.DATE)
    private Date tglKembaliCb;
    @Column(name = "TGL_LEASING")
    @Temporal(TemporalType.DATE)
    private Date tglLeasing;
    @Column(name = "TGL_TERIMA")
    @Temporal(TemporalType.DATE)
    private Date tglTerima = new Date();
    @OneToMany(targetEntity = Bayarjasa.class,cascade = CascadeType.ALL)
    @OrderBy("tanggal ASC")
    private List<Bayarjasa> bayarjasaList;

    public Bpkbtitipan() {
    }

    public Bpkbtitipan(Integer bpkbId) {
        this.bpkbId = bpkbId;
    }

    public Integer getBpkbId() {
        return bpkbId;
    }

    public void setBpkbId(Integer bpkbId) {
        Integer oldBpkbId = this.bpkbId;
        this.bpkbId = bpkbId;
        changeSupport.firePropertyChange("bpkbId", oldBpkbId, bpkbId);
    }

    public String getAnBpkb() {
        return anBpkb;
    }

    public void setAnBpkb(String anBpkb) {
        String oldAnBpkb = this.anBpkb;
        this.anBpkb = anBpkb;
        changeSupport.firePropertyChange("anBpkb", oldAnBpkb, anBpkb);
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        String oldKet = this.ket;
        this.ket = ket;
        changeSupport.firePropertyChange("ket", oldKet, ket);
    }

    public String getNoBpkb() {
        return noBpkb;
    }

    public void setNoBpkb(String noBpkb) {
        String oldNoBpkb = this.noBpkb;
        this.noBpkb = noBpkb;
        changeSupport.firePropertyChange("noBpkb", oldNoBpkb, noBpkb);
    }

    public String getNoPolisiAktif() {
        return noPolisiAktif;
    }

    public void setNoPolisiAktif(String noPolisiAktif) {
        String oldNoPolisiAktif = this.noPolisiAktif;
        this.noPolisiAktif = noPolisiAktif;
        changeSupport.firePropertyChange("noPolisiAktif", oldNoPolisiAktif, noPolisiAktif);
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        String oldPosisi = this.posisi;
        this.posisi = posisi;
        changeSupport.firePropertyChange("posisi", oldPosisi, posisi);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        String oldStatus = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldStatus, status);
    }

    public String getStnk() {
        return stnk;
    }

    public void setStnk(String stnk) {
        String oldStnk = this.stnk;
        this.stnk = stnk;
        changeSupport.firePropertyChange("stnk", oldStnk, stnk);
    }

    public Date getTglBbn() {
        return tglBbn;
    }

    public void setTglBbn(Date tglBbn) {
        Date oldTglBbn = this.tglBbn;
        this.tglBbn = tglBbn;
        changeSupport.firePropertyChange("tglBbn", oldTglBbn, tglBbn);
    }

    public Date getTglCb() {
        return tglCb;
    }

    public void setTglCb(Date tglCb) {
        Date oldTglCb = this.tglCb;
        this.tglCb = tglCb;
        changeSupport.firePropertyChange("tglCb", oldTglCb, tglCb);
    }

    public Date getTglKembaliBbn() {
        return tglKembaliBbn;
    }

    public void setTglKembaliBbn(Date tglKembaliBbn) {
        Date oldTglKembaliBbn = this.tglKembaliBbn;
        this.tglKembaliBbn = tglKembaliBbn;
        changeSupport.firePropertyChange("tglKembaliBbn", oldTglKembaliBbn, tglKembaliBbn);
    }

    public Date getTglKembaliCb() {
        return tglKembaliCb;
    }

    public void setTglKembaliCb(Date tglKembaliCb) {
        Date oldTglKembaliCb = this.tglKembaliCb;
        this.tglKembaliCb = tglKembaliCb;
        changeSupport.firePropertyChange("tglKembaliCb", oldTglKembaliCb, tglKembaliCb);
    }

    public Date getTglLeasing() {
        return tglLeasing;
    }

    public void setTglLeasing(Date tglLeasing) {
        Date oldTglLeasing = this.tglLeasing;
        this.tglLeasing = tglLeasing;
        changeSupport.firePropertyChange("tglLeasing", oldTglLeasing, tglLeasing);
    }

    public Date getTglTerima() {
        return tglTerima;
    }

    public void setTglTerima(Date tglTerima) {
        Date oldTglTerima = this.tglTerima;
        this.tglTerima = tglTerima;
        changeSupport.firePropertyChange("tglTerima", oldTglTerima, tglTerima);
    }

    @XmlTransient
    public List<Bayarjasa> getBayarjasaList() {
        
//        org.jdesktop.observablecollections.ObservableCollections.observableList(bayarjasaList);
        return (List<Bayarjasa>) app.table.Util.hitungSaldo(bayarjasaList);
    }

    public void setBayarjasaList(List<Bayarjasa> bayarjasaList) {
//        org.jdesktop.observablecollections.ObservableCollections.observableList(bayarjasaList);
        this.bayarjasaList = bayarjasaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bpkbId != null ? bpkbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bpkbtitipan)) {
            return false;
        }
        Bpkbtitipan other = (Bpkbtitipan) object;
        if ((this.bpkbId == null && other.bpkbId != null) || (this.bpkbId != null && !this.bpkbId.equals(other.bpkbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " [ bpkbId REF=" + bpkbId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
