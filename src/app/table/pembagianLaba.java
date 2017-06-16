/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import app.ListUrutan;
import java.io.Serializable;
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
public class pembagianLaba extends Laporan implements Serializable {

    @OneToOne(mappedBy = "Laba")
    private Saham saham;

    public Saham getSaham() {
        return saham;
    }

    public void setSaham(Saham saham) {
        this.saham = saham;
    }
    @Override
    public String getKelas() {
        return "Pembagian Laba";
    }
    
}
