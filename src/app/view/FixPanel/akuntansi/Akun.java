/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.FixPanel.akuntansi;

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
    private String akun;
    private java.math.BigInteger Pemasukan = BigInteger.ZERO;
    private java.math.BigInteger Pengeluaran= BigInteger.ZERO;    

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
