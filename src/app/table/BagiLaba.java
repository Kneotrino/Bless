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
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author SEED
 */
@Entity
public class BagiLaba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String Keterangan;
    @OneToOne(cascade = CascadeType.ALL)
    private Mobil m;
    @OneToOne(cascade = CascadeType.ALL)
    private Bpkbtitipan b;
    @OneToOne(cascade = CascadeType.ALL)
    private Rental r;
    @OneToOne(cascade = CascadeType.ALL)
    private Hutang h;

    public Rental getR() {
        return r;
    }

    public void setR(Rental r) {
        this.r = r;
    }

    public Hutang getH() {
        return h;
    }

    public void setH(Hutang h) {
        this.h = h;
    }

    public Bpkbtitipan getB() {
        return b;
    }

    public void setB(Bpkbtitipan b) {
        this.b = b;
    }

    public Mobil getM() {
        return m;
    }

    public void setM(Mobil m) {
        this.m = m;
    }
    public static final String PROP_KETERANGAN = "Keterangan";

    /**
     * Get the value of Keterangan
     *
     * @return the value of Keterangan
     */
    public List<? extends Laporan> getLaporans()
    {
        if (m != null) {
            return m.getKeuanganMobil2();
        }
        else if ( h != null)
        {
            return h.getBayarhutangs();
        }
        else if ( r!= null)
        {
            r.getBayarrentalList();
        }
        else if ( b != null)
        {
            b.getBayarjasaList();
        }
        return null;

    }
    public String getKeterangan() {
        if (m != null) {
            Keterangan = m.toString();
        }
        else if ( h != null)
        {
            Keterangan = h.toString();
        }
        else if ( r!= null)
        {
            Keterangan = r.toString();
        }
        else if ( b != null)
        {
            Keterangan = b.toString();
        }
//        System.out.println("m = " + m);
//        System.out.println("B = " + b);
//        System.out.println("r = " + r);
//        System.out.println("h = " + h);
        return Keterangan;
    }

    /**
     * Set the value of Keterangan
     *
     * @param Keterangan new value of Keterangan
     */
    public void setKeterangan(String Keterangan) {
        String oldKeterangan = this.Keterangan;
        this.Keterangan = Keterangan;
        propertyChangeSupport.firePropertyChange(PROP_KETERANGAN, oldKeterangan, Keterangan);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        propertyChangeSupport.firePropertyChange("id", oldId, id);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BagiLaba)) {
            return false;
        }
        BagiLaba other = (BagiLaba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.table.BagiLaba[ id=" + id + " ]";
    }
    @Transient 
    private BigInteger Profit = BigInteger.ZERO;

    public static final String PROP_PROFIT = "Profit";
public String getLaba()
{
    String temp = " ";
        if (m != null) {
            temp = m.getStatusMobil();
        }
        else if ( h != null)
        {
            temp = h.getLABA();
        }
        else if ( r!= null)
        {
            temp = r.getLABA();
        }
        else if ( b != null)
        {
            temp = b.getLaba();
        }
        return temp;
}
    /**
     * Get the value of Profit
     *
     * @return the value of Profit
     */
    public BigInteger getProfit() {
       if (m != null) {
           List<KeuanganMobil> keuanganMobil2 = m.getKeuanganMobil2();
           return fungsi(keuanganMobil2);
        }
        else if ( h != null)
        {
           List<Bayarhutang> bayarhutangs = h.getBayarhutangs();
           return fungsi(bayarhutangs);
        }
        else if ( r!= null)
        {
           List<Bayarrental> bayarrentalList = r.getBayarrentalList();
            return fungsi(bayarrentalList);
        }
        else if ( b != null)
        {
           List<Bayarjasa> bayarjasaList = b.getBayarjasaList();
           return fungsi(bayarjasaList);           
        }
        return Profit;
    }
    public BigInteger fungsi(List<? extends Laporan> LaporanList)
    {
        BigInteger temp = BigInteger.ZERO;
        for (Laporan laporan : LaporanList) {
                temp =temp.add(laporan.getPemasukan());
                temp =temp.subtract(laporan.getPengeluaran());
        }
        return temp;
    }

    /**
     * Set the value of Profit
     *
     * @param Profit new value of Profit
     */
    public void setProfit(BigInteger Profit) {
        BigInteger oldProfit = this.Profit;
        this.Profit = Profit;
        propertyChangeSupport.firePropertyChange(PROP_PROFIT, oldProfit, Profit);
    }

}
