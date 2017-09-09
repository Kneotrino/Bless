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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "BAYARSEWA", catalog = "", schema = "BLESSING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bayarsewa.findAll", query = "SELECT b FROM Bayarsewa b")
    , @NamedQuery(name = "Bayarsewa.findById", query = "SELECT b FROM Bayarsewa b WHERE b.id = :id")
    , @NamedQuery(name = "Bayarsewa.findByBayar", query = "SELECT b FROM Bayarsewa b WHERE b.bayar = :bayar")})
@ListUrutan({"tanggal","keterangan","jumlah"})
public class Bayarsewa extends Laporan  implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Column(name = "BAYAR")
    private Boolean bayar;
    @OneToOne(cascade = CascadeType.ALL)
    private Transfer transfer;

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }
    
    public void setTanggalSync(Date Tanggal)
    {
        this.setTanggal(Tanggal);
        if (transfer != null) {
            transfer.setTanggal(Tanggal);
        }
    }
    public Date getTanggalSync()
    {
        return this.getTanggal();
    }
    public void setKeteranganSync(String keterangan)
    {
        this.setKeterangan(keterangan);
        if (transfer != null) {
            transfer.setKeterangan(keterangan);
        }
    }
    public String getKeteranganSync()
    {
        return this.getKeterangan();
    }
    @Override
    public void setPemasukan(BigInteger jumlah) {
        if (name) {
            setJumlah(jumlah);
            if (transfer != null) {
                transfer.setJumlah(jumlah);
            }
        }
        else 
            javax.swing.JOptionPane.showMessageDialog(null,  "Pemasukan tidak bisa di edit dari pengeluaran");
    }
    public Bayarsewa() {
    }

    public Boolean getBayar() {
        return bayar;
    }

    public void setBayar(Boolean bayar) {
        Boolean oldBayar = this.bayar;
        this.bayar = bayar;
        changeSupport.firePropertyChange("bayar", oldBayar, bayar);
    }
    @Override
    public String getJenis()
    {
        return "Modal di Tahan "+ (isName()?"Masuk":"Keluar");
    }
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }

//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Bayarsewa)) {
//            return false;
//        }
//        Bayarsewa other = (Bayarsewa) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "app.table.Bayarsewa[ id=" + super.getId() + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
