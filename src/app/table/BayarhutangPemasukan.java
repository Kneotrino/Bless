/**
 * This file was generated by the JPA Modeler
 */
package app.table;

import app.ListUrutan;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 * @author SEED
 */
@Entity
@ListUrutan({"tanggal","keterangan","jumlah"})
public class BayarhutangPemasukan extends Bayarhutang implements Serializable {
    @Override
    public String getKelas() {
        return "Pemasukan Peminjaman";
    }    
}
