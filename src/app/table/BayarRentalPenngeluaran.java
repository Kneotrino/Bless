/**
 * This file was generated by the JPA Modeler
 */
package app.table;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * @author SEED
 */
@Entity
@app.ListUrutan({"tanggal","keterangan","jumlah"})
public class BayarRentalPenngeluaran extends Bayarrental implements Serializable {
    @Override
    public String getKelas() {
        return "Pengeluaran Rental";
    }    
}
