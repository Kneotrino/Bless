/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import app.ListUrutan;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author SEED
 */
@Entity
@ListUrutan({"tanggal","keterangan","jumlah"})
public class Prive extends Laporan implements Serializable {    
    @OneToOne(mappedBy = "prive")
    private Saham saham;

    public Saham getSaham() {
        return saham;
    }

    public void setSaham(Saham saham) {
        this.saham = saham;
    }
    @Override
    public String getKelas() {
        return "Penarikan Modal";
    }

}