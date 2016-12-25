/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import app.ListUrutan;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
