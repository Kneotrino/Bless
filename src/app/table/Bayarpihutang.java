/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import app.ListUrutan;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@XmlRootElement
@ListUrutan({"tanggal","keterangan","jumlah"})
public class Bayarpihutang extends Laporan implements Serializable {
    @JoinColumn(name = "PIHUTANGID", referencedColumnName = "PIUTANGID")
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Piutang pihutangid;

    public Piutang getPihutangid() {
        return pihutangid;
    }

    public void setPihutangid(Piutang pihutangid) {
        this.pihutangid = pihutangid;
    }

    public Bayarpihutang() {
    }
    public BigInteger getBunga()
    {
        return this instanceof app.table.BayarPihutangBunga ? getJumlah():BigInteger.ZERO;
    }
    public void setBunga(BigInteger jumlah) {
       if (this instanceof app.table.BayarPihutangBunga) 
            setJumlah(jumlah);                    
        else 
            javax.swing.JOptionPane.showMessageDialog(null,  "Tidak bisa di edit");
    }
    
    @Override
        public BigInteger getPengeluaran() {
        if (this instanceof app.table.BayarPihutangBunga) {
            return BigInteger.ZERO;
        }
        else if (this instanceof app.table.BayarPihutangPemasukan)
            return BigInteger.ZERO;
        else if (this instanceof app.table.BayarPihutangPengeluaran)
            return getJumlah();
        return BigInteger.ONE;
    }
    @Override
       public void setPengeluaran(BigInteger jumlah) {
       if (this instanceof app.table.BayarPihutangBunga) {
            javax.swing.JOptionPane.showMessageDialog(null,  "Pengeluran tidak bisa di edit");
        }
        else if (this instanceof app.table.BayarPihutangPemasukan)
            javax.swing.JOptionPane.showMessageDialog(null,  "Pengeluran tidak bisa di edit");
        else if (this instanceof app.table.BayarPihutangPengeluaran)
            setJumlah(jumlah);
    }



    
}
