/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

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
@app.ListUrutan({"tanggal","keterangan","jumlah"})
public class Transfer extends Laporan implements Serializable {

    @OneToOne(mappedBy = "transfer")
    private Perjalanan perjalanan;

}
