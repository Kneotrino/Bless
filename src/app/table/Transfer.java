/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.io.Serializable;
import java.math.BigInteger;
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
@app.ListUrutan({"tanggal","keterangan","jumlah"})
public class Transfer extends Laporan implements Serializable {

    @OneToOne(mappedBy = "transfer",cascade = CascadeType.ALL)
    private Perjalanan perjalanan;
    public Perjalanan getPerjalanan() {
        return perjalanan;
    }

    public void setPerjalanan(Perjalanan perjalanan) {
        this.perjalanan = perjalanan;
    }
}
