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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@XmlRootElement
@ListUrutan({"tanggal","keterangan","jumlah"})
public class Penyesuaian extends Laporan implements Serializable {

    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
            return false;
    }

    @Override
    public String toString() {
        return "app.table.Penyesuaian[ id=" + this.getId() + " ]";
    }
    
}
