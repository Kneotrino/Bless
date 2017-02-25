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
import java.util.LinkedList;
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
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
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
@Table(name = "LAPORANLABA", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Laporanlaba.findAll", query = "SELECT l FROM Laporanlaba l")
    , @NamedQuery(name = "Laporanlaba.findByRef", query = "SELECT l FROM Laporanlaba l WHERE l.ref = :ref")
    , @NamedQuery(name = "Laporanlaba.findByKe", query = "SELECT l FROM Laporanlaba l WHERE l.ke = :ke")
    , @NamedQuery(name = "Laporanlaba.findByKeterangan", query = "SELECT l FROM Laporanlaba l WHERE l.keterangan = :keterangan")
    , @NamedQuery(name = "Laporanlaba.findByTanggal", query = "SELECT l FROM Laporanlaba l WHERE l.tanggal = :tanggal")})
public class Laporanlaba implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "REF", nullable = false)
    private Integer ref;
    @Column(name = "KE")
    private Integer ke;
    @Column(name = "KETERANGAN", length = 255)
    private String keterangan;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @OneToMany(mappedBy = "laporanlabaRef", cascade = {CascadeType.ALL})
    private List<Laba> labaList;
    @OneToOne( cascade = {CascadeType.ALL})
    private LaporanSaham laporanSaham = new LaporanSaham();    
    private BigInteger Persen;
    @OneToOne( cascade = {CascadeType.ALL})
    private BayarSewaMasuk ModalTahan;

    public static final String PROP_MODALTAHAN = "ModalTahan";

    /**
     * Get the value of ModalTahan
     *
     * @return the value of ModalTahan
     */
    public BayarSewaMasuk getModalTahan() {
        return ModalTahan;
    }

    /**
     * Set the value of ModalTahan
     *
     * @param ModalTahan new value of ModalTahan
     */
    public void setModalTahan(BayarSewaMasuk ModalTahan) {
        BayarSewaMasuk oldModalTahan = this.ModalTahan;
        this.ModalTahan = ModalTahan;
        changeSupport.firePropertyChange(PROP_MODALTAHAN, oldModalTahan, ModalTahan);
    }


    public static final String PROP_PERSEN = "Persen";

    /**
     * Get the value of Persen
     *
     * @return the value of Persen
     */
    public BigInteger getPersen() {
        return Persen;
    }

    /**
     * Set the value of Persen
     *
     * @param Persen new value of Persen
     */
    public void setPersen(BigInteger Persen) {
        BigInteger oldPersen = this.Persen;
        this.Persen = Persen;
        changeSupport.firePropertyChange(PROP_PERSEN, oldPersen, Persen);
    }

    

    public LaporanSaham getLaporanSaham() {
        return laporanSaham;
    }

    public void setLaporanSaham(LaporanSaham laporanSaham) {
        this.laporanSaham = laporanSaham;
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
//            javax.swing.JOptionPane.showMessageDialog(null,"Berhasil Menghapus \n");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null,"Gagal Menghapus \n");
        }
            
    }
    @Transient
    private BigInteger TMasuk = BigInteger.ZERO;

    public static final String PROP_TMASUK = "TMasuk";
    @Transient
    private BigInteger TKeluar = BigInteger.ZERO;

    public static final String PROP_TKELUAR = "TKeluar";

    /**
     * Get the value of TKeluar
     *
     * @return the value of TKeluar
     */
    public BigInteger getTKeluar() {
        TKeluar = BigInteger.ZERO;
        for (Laba laba : labaList) {
            TKeluar = TKeluar.add(laba.getPengeluaran());
        }
        return TKeluar;
    }

    /**
     * Set the value of TKeluar
     *
     * @param TKeluar new value of TKeluar
     */
    public void setTKeluar(BigInteger TKeluar) {
        BigInteger oldTKeluar = this.TKeluar;
        this.TKeluar = TKeluar;
        changeSupport.firePropertyChange(PROP_TKELUAR, oldTKeluar, TKeluar);
    }

    /**
     * Get the value of TMasuk
     *
     * @return the value of TMasuk
     */
    public BigInteger getTMasuk() {
        TMasuk = BigInteger.ZERO;
        for (Laba laba : labaList) {
            TMasuk = TMasuk.add(laba.getJumlah());
        }
        return TMasuk;
    }
    public BigInteger getProfit()
    {
        return TMasuk.subtract(TKeluar);
    }

    /**
     * Set the value of TMasuk
     *
     * @param TMasuk new value of TMasuk
     */
    public void setTMasuk(BigInteger TMasuk) {
        BigInteger oldTMasuk = this.TMasuk;
        this.TMasuk = TMasuk;
        changeSupport.firePropertyChange(PROP_TMASUK, oldTMasuk, TMasuk);
    }

    public Laporanlaba() {
        laporanSaham = new LaporanSaham();
        List<Relasi> relasis = new LinkedList<>();
        laporanSaham.setRelasis(relasis);
    }

    public Laporanlaba(Integer ref) {
        this.ref = ref;
    }

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        Integer oldRef = this.ref;
        this.ref = ref;
        changeSupport.firePropertyChange("ref", oldRef, ref);
    }

    public Integer getKe() {
        return ke;
    }

    public void setKe(Integer ke) {
        Integer oldKe = this.ke;
        this.ke = ke;
        changeSupport.firePropertyChange("ke", oldKe, ke);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        Date oldTanggal = this.tanggal;
        this.tanggal = tanggal;
        changeSupport.firePropertyChange("tanggal", oldTanggal, tanggal);
    }

    @XmlTransient
    public List<Laba> getLabaList() {
//        BigInteger a = BigInteger.ZERO;
//        BigInteger b = BigInteger.ZERO;
//        Laba pem = new Laba();
//        pem.setKeterangan("Total Pemasukan");
//        Laba peng = new Laba();
//        peng.setKeterangan("Total Pengeluaran");
//        for (Laba laba : labaList) {
//            a = a.add(laba.getJumlah());
//        }
//        pem.setJumlah(a);
//        labaList.add(pem);
        return labaList;
    }

    public void setLabaList(List<Laba> labaList) {
        this.labaList = labaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ref != null ? ref.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Laporanlaba)) {
            return false;
        }
        Laporanlaba other = (Laporanlaba) object;
        if ((this.ref == null && other.ref != null) || (this.ref != null && !this.ref.equals(other.ref))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.Laporanlaba[ ref=" + ref + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
