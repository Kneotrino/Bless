/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import app.ListUrutan;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "ASSET", catalog = "", schema = "BLESSING")
 @ListUrutan({"namaAsset","hargaBeli","keterangan","status","stock","tanggalStock","gambar" })
@NamedQueries({
    @NamedQuery(name = "Asset.findAll", query = "SELECT a FROM Asset a"),
    @NamedQuery(name = "Asset.findByAssetId", query = "SELECT a FROM Asset a WHERE a.assetId = :assetId"),
    @NamedQuery(name = "Asset.findByNamaAsset", query = "SELECT a FROM Asset a WHERE a.namaAsset = :namaAsset"),
    @NamedQuery(name = "Asset.findByHargaBeli", query = "SELECT a FROM Asset a WHERE a.hargaBeli = :hargaBeli"),
    @NamedQuery(name = "Asset.findByKeterangan", query = "SELECT a FROM Asset a WHERE a.keterangan = :keterangan"),
    @NamedQuery(name = "Asset.findByStatus", query = "SELECT a FROM Asset a WHERE a.status = :status"),
    @NamedQuery(name = "Asset.findByStock", query = "SELECT a FROM Asset a WHERE a.stock = :stock"),
    @NamedQuery(name = "Asset.findByTanggalStock", query = "SELECT a FROM Asset a WHERE a.tanggalStock = :tanggalStock")})

public class Asset implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ASSET_ID", nullable = false)
    @GeneratedValue
    private Integer assetId;
    @Column(name = "NAMA_ASSET", length = 64)
    private String namaAsset;
    @Column(name = "HARGA_BELI")
    private BigInteger hargaBeli;
    @Column(name = "KETERANGAN", length = 64)
    private String keterangan;
    @Column(name = "STATUS", length = 32)
    private String status;
    @Column(name = "STOCK")
    private Integer stock;
    @Column(name = "TANGGAL_STOCK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalStock;
    @Lob
    @Column(name = "GAMBAR")
    private byte[] gambar;

    public Asset() {
    }

    public Asset(Integer assetId) {
        this.assetId = assetId;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        Integer oldAssetId = this.assetId;
        this.assetId = assetId;
        changeSupport.firePropertyChange("assetId", oldAssetId, assetId);
    }

    public String getNamaAsset() {
        return namaAsset;
    }

    public void setNamaAsset(String namaAsset) {
        String oldNamaAsset = this.namaAsset;
        this.namaAsset = namaAsset;
        changeSupport.firePropertyChange("namaAsset", oldNamaAsset, namaAsset);
    }

    public BigInteger getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(BigInteger hargaBeli) {
        BigInteger oldHargaBeli = this.hargaBeli;
        this.hargaBeli = hargaBeli;
        changeSupport.firePropertyChange("hargaBeli", oldHargaBeli, hargaBeli);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        String oldStatus = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldStatus, status);
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        Integer oldStock = this.stock;
        this.stock = stock;
        changeSupport.firePropertyChange("stock", oldStock, stock);
    }

    public Date getTanggalStock() {
        return tanggalStock;
    }

    public void setTanggalStock(Date tanggalStock) {
        Date oldTanggalStock = this.tanggalStock;
        this.tanggalStock = tanggalStock;
        changeSupport.firePropertyChange("tanggalStock", oldTanggalStock, tanggalStock);
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        byte[] oldGambar = this.gambar;
        this.gambar = gambar;
        changeSupport.firePropertyChange("gambar", oldGambar, gambar);
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assetId != null ? assetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asset)) {
            return false;
        }
        Asset other = (Asset) object;
        if ((this.assetId == null && other.assetId != null) || (this.assetId != null && !this.assetId.equals(other.assetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.view.panel.inven.Asset[ assetId=" + assetId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
