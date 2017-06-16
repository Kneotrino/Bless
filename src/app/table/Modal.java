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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@XmlRootElement
@ListUrutan({"tanggal","keterangan","jumlah"})
public class Modal extends Laporan implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @Column(name = "PERSENTASE")
    private Short persentase;
    @Transient    
    private String Persen;
    public static final String PROP_PERSEN = "Persen";
    @OneToOne(mappedBy = "modal")
    private Saham saham;

    public Saham getSaham() {
        return saham;
    }

    public void setSaham(Saham saham) {
        this.saham = saham;
    }
    @Override
    public String getKelas() {
        return "Pemasukan Modal";
    }    
    /**
     * Get the value of Persen
     *
     * @return the value of Persen
     */
    public String getPersen() {
        return Persen;
    }

    /**
     * Set the value of Persen
     *
     * @param Persen new value of Persen
     */
    public void setPersen(String Persen) {
        String oldPersen = this.Persen;
        this.Persen = Persen;
        changeSupport.firePropertyChange(PROP_PERSEN, oldPersen, Persen);
    }


    public Modal() {
    }
    public Short getPersentase() {
        return persentase;
    }

    public void setPersentase(Short persentase) {
        Short oldPersentase = this.persentase;
        this.persentase = persentase;
        changeSupport.firePropertyChange("persentase", oldPersentase, persentase);
    }


    @Override
    public String toString() {
        return "app.table.Modal[ id=" + this.getId() + " ]";
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    @Override
    public String getJenis()
    {
        return "Table modal "+this.getSaham().getInvestorId().getNama();
    }
    
}
