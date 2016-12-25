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
@Table(name = "PIUTANG", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Piutang.findAll", query = "SELECT p FROM Piutang p")
    , @NamedQuery(name = "Piutang.findByPiutangid", query = "SELECT p FROM Piutang p WHERE p.piutangid = :piutangid")
    , @NamedQuery(name = "Piutang.findByPimjaman", query = "SELECT p FROM Piutang p WHERE p.pimjaman = :pimjaman")
    , @NamedQuery(name = "Piutang.findBySisa", query = "SELECT p FROM Piutang p WHERE p.sisa = :sisa")
    , @NamedQuery(name = "Piutang.findByTglbyr", query = "SELECT p FROM Piutang p WHERE p.tglbyr = :tglbyr")
    , @NamedQuery(name = "Piutang.findByTglawal", query = "SELECT p FROM Piutang p WHERE p.tglawal = :tglawal")
    , @NamedQuery(name = "Piutang.findByTglakhir", query = "SELECT p FROM Piutang p WHERE p.tglakhir = :tglakhir")
    , @NamedQuery(name = "Piutang.findByNominal", query = "SELECT p FROM Piutang p WHERE p.nominal = :nominal")})
@app.ListUrutan({"jaminan","nominal","pimjaman","sisa","keterangan","tglbyr","tglawal","tglakhir" })
public class Piutang implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false) @GeneratedValue
    @Column(name = "PIUTANGID", nullable = false)
    private Integer piutangid;
    @Lob
    @Column(name = "KETERANGAN")
    private String keterangan;
    @Lob
    @Column(name = "JAMINAN")
    private String jaminan;
    @Column(name = "PIMJAMAN")
    private BigInteger pimjaman;
    @Column(name = "SISA")
    private BigInteger sisa;
    @Column(name = "TGLBYR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglbyr;
    @Column(name = "TGLAWAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglawal;
    @Column(name = "TGLAKHIR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglakhir;
    @Column(name = "NOMINAL")
    private BigInteger nominal;
    @OneToMany(mappedBy = "pihutangid",cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<Bayarpihutang> bayarpihutangList;

    public Piutang() {
    }

    public Piutang(Integer piutangid) {
        this.piutangid = piutangid;
    }

    public Integer getPiutangid() {
        return piutangid;
    }

    public void setPiutangid(Integer piutangid) {
        Integer oldPiutangid = this.piutangid;
        this.piutangid = piutangid;
        changeSupport.firePropertyChange("piutangid", oldPiutangid, piutangid);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public String getJaminan() {
        return jaminan;
    }

    public void setJaminan(String jaminan) {
        String oldJaminan = this.jaminan;
        this.jaminan = jaminan;
        changeSupport.firePropertyChange("jaminan", oldJaminan, jaminan);
    }

    public BigInteger getPimjaman() {
        return pimjaman;
    }

    public void setPimjaman(BigInteger pimjaman) {
        BigInteger oldPimjaman = this.pimjaman;
        this.pimjaman = pimjaman;
        changeSupport.firePropertyChange("pimjaman", oldPimjaman, pimjaman);
    }

    public BigInteger getSisa() {
        return sisa;
    }

    public void setSisa(BigInteger sisa) {
        BigInteger oldSisa = this.sisa;
        this.sisa = sisa;
        changeSupport.firePropertyChange("sisa", oldSisa, sisa);
    }

    public Date getTglbyr() {
        return tglbyr;
    }

    public void setTglbyr(Date tglbyr) {
        Date oldTglbyr = this.tglbyr;
        this.tglbyr = tglbyr;
        changeSupport.firePropertyChange("tglbyr", oldTglbyr, tglbyr);
    }

    public Date getTglawal() {
        return tglawal;
    }

    public void setTglawal(Date tglawal) {
        Date oldTglawal = this.tglawal;
        this.tglawal = tglawal;
        changeSupport.firePropertyChange("tglawal", oldTglawal, tglawal);
    }

    public Date getTglakhir() {
        return tglakhir;
    }

    public void setTglakhir(Date tglakhir) {
        Date oldTglakhir = this.tglakhir;
        this.tglakhir = tglakhir;
        changeSupport.firePropertyChange("tglakhir", oldTglakhir, tglakhir);
    }

    public BigInteger getNominal() {
        return nominal;
    }

    public void setNominal(BigInteger nominal) {
        BigInteger oldNominal = this.nominal;
        this.nominal = nominal;
        changeSupport.firePropertyChange("nominal", oldNominal, nominal);
    }

    @XmlTransient
    public List<Bayarpihutang> getBayarpihutangList() {
        return bayarpihutangList;
    }

    public void setBayarpihutangList(List<Bayarpihutang> bayarpihutangList) {
        this.bayarpihutangList = bayarpihutangList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piutangid != null ? piutangid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piutang)) {
            return false;
        }
        Piutang other = (Piutang) object;
        if ((this.piutangid == null && other.piutangid != null) || (this.piutangid != null && !this.piutangid.equals(other.piutangid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Piutang[ piutangid=" + piutangid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
