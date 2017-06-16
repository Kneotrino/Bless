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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author SEED
 */
@Entity
@ListUrutan({"tanggal","keterangan","jumlah"})
public class BayarSewaMasuk extends Bayarsewa implements Serializable {    

    @OneToOne(mappedBy = "ModalTahan",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Laporanlaba laporanlaba;

    public Laporanlaba getLaporanlaba() {
        return laporanlaba;
    }

    public void setLaporanlaba(Laporanlaba laporanlaba) {
        this.laporanlaba = laporanlaba;
    }
    @Override
    public String getKelas() {
        return "Pemasukan Modal Di Tahan";
    }

}
