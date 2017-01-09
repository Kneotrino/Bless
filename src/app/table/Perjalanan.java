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
import java.text.DecimalFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
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
@XmlRootElement
@app.ListUrutan({
"keterangan"
,"tanggal"
,"pakai"
})
public class Perjalanan extends Laporan implements Serializable {

    @Transient
    public PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    @Column(name = "KIRIM")
    private BigInteger kirim = BigInteger.ZERO;
    @Column(name = "PAKAI")
    private BigInteger pakai = BigInteger.ZERO;
    @Column(name = "PERJALANAN_KE")
    private Integer perjalananKe;
    @Column(name = "SALDO")
    private BigInteger saldo = BigInteger.ZERO;
    @JoinColumn(name = "TRIPS_TRIPS_ID", referencedColumnName = "TRIPS_ID")
    @ManyToOne
    private Trips tripsTripsId;
    @OneToOne(cascade = {CascadeType.ALL})
    private Pemasukan Kembali;
    @OneToOne(cascade = {CascadeType.ALL})
    private Transfer transfer;
//    private Pengeluaran Transfer;

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public Pemasukan getKembali() {
        return Kembali;
    }

    public void setKembali(Pemasukan Kembali) {
        this.Kembali = Kembali;
    }

//    public Pengeluaran getTransfer() {
//        return Transfer;
//    }
//
//    public void setTransfer(Pengeluaran Transfer) {
//        this.Transfer = Transfer;
//    }

    public Perjalanan() {
    }



    public BigInteger getKirim() {
        return kirim;
    }

    public void setKirim(BigInteger kirim) {
        BigInteger oldKirim = this.kirim;
        this.kirim = kirim;
        changeSupport.firePropertyChange("kirim", oldKirim, kirim);
    }

    public BigInteger getPakai() {
        return pakai;
    }

    public void setPakai(BigInteger pakai) {
        BigInteger oldPakai = this.pakai;
        this.pakai = pakai;
        changeSupport.firePropertyChange("pakai", oldPakai, pakai);
    }

    public Integer getPerjalananKe() {
        return perjalananKe;
    }

    public void setPerjalananKe(Integer perjalananKe) {
        Integer oldPerjalananKe = this.perjalananKe;
        this.perjalananKe = perjalananKe;
        changeSupport.firePropertyChange("perjalananKe", oldPerjalananKe, perjalananKe);
    }

    public BigInteger getSaldo() {
        return saldo;
    }

    public void setSaldo(BigInteger saldo) {
        BigInteger oldSaldo = this.saldo;
        this.saldo = saldo;
        changeSupport.firePropertyChange("saldo", oldSaldo, saldo);
    }

    public Trips getTripsTripsId() {
        return tripsTripsId;
    }

    public void setTripsTripsId(Trips tripsTripsId) {
        Trips oldTripsTripsId = this.tripsTripsId;
        this.tripsTripsId = tripsTripsId;
        changeSupport.firePropertyChange("tripsTripsId", oldTripsTripsId, tripsTripsId);
    }

    @Override
    public String toString() {
        return "app.table.Perjalanan[ id=" + getId() + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    public Date getTanggal2() {
        return getTanggal();
    }

    public void setTanggal2(Date tanggal) {
        if ( this.transfer == null) {
            Kembali.setTanggal(tanggal);
        }
        else 
        {
            this.transfer.setTanggal(tanggal);
        }
        setTanggal(tanggal);
        
    }
}
