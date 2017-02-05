/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.FixPanel.akuntansi;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author SEED
 */
public class Akun implements Serializable
{    
    private static final long serialVersionUID = 3041287746818525952L;
    private int nomor;
    private String akun = "-";
    private java.math.BigInteger Pemasukan = BigInteger.ZERO;
    private java.math.BigInteger Pengeluaran= BigInteger.ZERO;    
    private BigInteger Profit = BigInteger.ZERO;

    public static final String PROP_PROFIT = "Profit";

    /**
     * Get the value of Profit
     *
     * @return the value of Profit
     */
    public BigInteger getProfit() {
        return Profit = Pemasukan.subtract(Pengeluaran);
    }

    private String Keterangan;

    public static final String PROP_KETERANGAN = "Keterangan";

    /**
     * Get the value of Keterangan
     *
     * @return the value of Keterangan
     */
    public String getKeterangan() {
        return Keterangan;
    }
    public Boolean getOpen() {
        return Keterangan.equals("OPEN");
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

    public int getNomor() {
        return nomor;
    }

    public Akun setNomor(int nomor) {
        this.nomor = nomor;
        return this;
    }

    public String getAkun() {
        return akun;
    }

    public Akun setAkun(String akun) {
        this.akun = akun;
         return this;
    }

    public BigInteger getPemasukan() {
        return Pemasukan;
    }

    public Akun setPemasukan(BigInteger Pemasukan) {
        this.Pemasukan = Pemasukan;
        return this;
    }
    public Akun addPemasukan(BigInteger Pemasukan) {
        this.Pemasukan = this.Pemasukan.add(Pemasukan);
        return this;
    }
    public Akun subPemasukan(BigInteger Pemasukan) {
//        System.out.println("Pemasukan = " + Pemasukan);
        this.Pemasukan = this.Pemasukan.subtract(Pemasukan);
//        System.out.println("this.Pemasukan = " + this.Pemasukan);
        return this;
    }

    public BigInteger getPengeluaran() {
        return Pengeluaran;
    }

    public Akun addPengeluaran(BigInteger Pengeluaran) {
        this.Pengeluaran = this.Pengeluaran.add(Pengeluaran);
        return this;
    }
    public Akun DividePengeluaran() {
        this.Pengeluaran = this.Pengeluaran.divide(new BigInteger("4"));
        return this;
    }
    public Akun subPengeluaran(BigInteger Pengeluaran) {
        this.Pengeluaran = this.Pengeluaran.subtract(Pengeluaran);
        return this;
    }
    public Akun setPengeluaran(BigInteger Pengeluaran) {
        this.Pengeluaran = Pengeluaran;
        return this;
    }
    public Akun() {        
    }
    public Akun(int x) {
        nomor = x;
    }
    @Override
    public String toString() {
        return "["
                + " No = "+ nomor 
                + " Akun = "+ akun 
                + " Pemasukan = "+ Pemasukan 
                + " Pengeluaran = "+ Pengeluaran + " ]\n";
    }
}
