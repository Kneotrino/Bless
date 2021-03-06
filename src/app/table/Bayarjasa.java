/**
 * This file was generated by the JPA Modeler
 */
package app.table;

import app.ListUrutan;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author SEED
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Bayarjasa.findAll", query = "SELECT b FROM Bayarjasa b")
})
@XmlRootElement
@ListUrutan({"tanggal","keterangan","jumlah"})
public class Bayarjasa extends Laporan implements Serializable {

    @ManyToOne(targetEntity = Bpkbtitipan.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST })
    private Bpkbtitipan bpkbtitipid;

    public Bpkbtitipan getBpkbtitipid() {
        return this.bpkbtitipid;
    }

    public void setBpkbtitipid(Bpkbtitipan bpkbtitipid) {
        this.bpkbtitipid = bpkbtitipid;
    }
        @Override
    public String getJenis()
    {
        return "Table Jasa";
    }

}
