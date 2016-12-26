/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import app.ListUrutan;
import java.io.Serializable;
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



    
}
