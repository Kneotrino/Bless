/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.awt.Image;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "DEBITUR", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Debitur.findAll", query = "SELECT d FROM Debitur d"),
    @NamedQuery(name = "Debitur.findByDebiturId", query = "SELECT d FROM Debitur d WHERE d.debiturId = :debiturId"),
    @NamedQuery(name = "Debitur.findByAlamat", query = "SELECT d FROM Debitur d WHERE d.alamat = :alamat"),
    @NamedQuery(name = "Debitur.findByBank", query = "SELECT d FROM Debitur d WHERE d.bank = :bank"),
    @NamedQuery(name = "Debitur.findByNama", query = "SELECT d FROM Debitur d WHERE d.nama = :nama"),
    @NamedQuery(name = "Debitur.findByNoHp", query = "SELECT d FROM Debitur d WHERE d.noHp = :noHp"),
    @NamedQuery(name = "Debitur.findByNoKtp", query = "SELECT d FROM Debitur d WHERE d.noKtp = :noKtp"),
    @NamedQuery(name = "Debitur.findByNorek", query = "SELECT d FROM Debitur d WHERE d.norek = :norek"),
    @NamedQuery(name = "Debitur.findByPembayaran", query = "SELECT d FROM Debitur d WHERE d.pembayaran = :pembayaran")})
public class Debitur implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "DEBITUR_ID", nullable = false)
    private Integer debiturId;
    @Column(name = "ALAMAT", length = 32)
    private String alamat;
    @Column(name = "BANK", length = 32)
    private String bank;
    @Column(name = "NAMA", length = 64)
    private String nama;
    @Column(name = "NO_HP", length = 32)
    private String noHp;
    @Column(name = "NO_KTP", length = 32)
    private String noKtp;
    @Column(name = "NOREK", length = 32)
    private String norek;
    @Column(name = "PEMBAYARAN", length = 16)
    private String pembayaran;
    @Column(name = "SCAN", length = 256)
    private String scan;

    public static final String PROP_SCAN = "scan";

    /**
     * Get the value of scan
     *
     * @return the value of scan
     */
    public String getScan() {
        return scan;
    }

    /**
     * Set the value of scan
     *
     * @param scan new value of scan
     */
    public void setScan(String scan) {
        String oldScan = this.scan;
        this.scan = scan;
        changeSupport.firePropertyChange(PROP_SCAN, oldScan, scan);
    }

//    @Column(name = "PEMBAYARAN", length = 16)
    
    @JoinColumn(name = "mobil_id")  
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "debitur")
    private Mobil mobil;

    public Mobil getMobil() {
        return mobil;
    }

    public void setMobil(Mobil mobil) {
        this.mobil = mobil;
    }

    public Debitur() {
    }

    public Debitur(Integer debiturId) {
        this.debiturId = debiturId;
    }

    public Integer getDebiturId() {
        return debiturId;
    }

    public void setDebiturId(Integer debiturId) {
        Integer oldDebiturId = this.debiturId;
        this.debiturId = debiturId;
        changeSupport.firePropertyChange("debiturId", oldDebiturId, debiturId);
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        String oldAlamat = this.alamat;
        this.alamat = alamat;
        changeSupport.firePropertyChange("alamat", oldAlamat, alamat);
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        String oldBank = this.bank;
        this.bank = bank;
        changeSupport.firePropertyChange("bank", oldBank, bank);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        String oldNoHp = this.noHp;
        this.noHp = noHp;
        changeSupport.firePropertyChange("noHp", oldNoHp, noHp);
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        String oldNoKtp = this.noKtp;
        this.noKtp = noKtp;
        changeSupport.firePropertyChange("noKtp", oldNoKtp, noKtp);
    }

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        String oldNorek = this.norek;
        this.norek = norek;
        changeSupport.firePropertyChange("norek", oldNorek, norek);
    }

    public String getPembayaran() {
        return pembayaran;
    }
    
    public  javax.swing.Icon getGambar1()
    {
        Icon ii = null;
        ImageIcon ico = new javax.swing.ImageIcon(new ImageIcon
                                        (this.scan).getImage().getScaledInstance(300, 140, Image.SCALE_DEFAULT));
        ii = ico;
        return ii;
    }
    public void setPembayaran(String pembayaran) {
        String oldPembayaran = this.pembayaran;
        this.pembayaran = pembayaran;
        changeSupport.firePropertyChange("pembayaran", oldPembayaran, pembayaran);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (debiturId != null ? debiturId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Debitur)) {
            return false;
        }
        Debitur other = (Debitur) object;
        if ((this.debiturId == null && other.debiturId != null) || (this.debiturId != null && !this.debiturId.equals(other.debiturId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[ ID=" + debiturId +";Nama= "+ nama+ " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
