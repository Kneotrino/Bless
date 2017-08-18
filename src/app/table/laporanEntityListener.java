/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

/**
 *
 * @author SEED
 */
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
 
import app.table.Laporan;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class laporanEntityListener {
        Logger logger = Logger.getLogger("MyLog");  
        Date date = new Date();
        FileHandler fh; 

    public laporanEntityListener() {
            try {
                String temp = "C:/LogBlessing-"+
                        date.getDate()+"."+
                        (date.getMonth()+1)+"."+
                        (date.getYear()+1900)+"-"+
                        date.getHours()+"."+
                        date.getMinutes()+
                        ".log";
                this.fh = new FileHandler(temp);
                logger.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();  
                fh.setFormatter(formatter);  
        // the following statement is used to log any messages  
                logger.info("Log di mulai "+temp);              
            } catch (IOException ex) {
                Logger.getLogger(laporanEntityListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(laporanEntityListener.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

        @PrePersist
	public void methodInvokedBeforePersist(Laporan emp) {
            String temp = emp.isName()?"Pemasukan":"Pengeluaran";
            logger.info("Simpan laporan "+temp+" Ref= " 
                        + emp.getId()+"\tTanggal ="
                        + emp.getTanggal()+"\tKeterangan ="
                        + emp.getKeterangan()+"\tNominal = "
                        + emp.getJumlah()+"\tTable ="
                        + emp.getKelas()+"\tBank ="
                        + emp.getTransaksi().getBankId().getNamaBank()+"\t"
                );
	}
 
	@PostPersist
	public void methodInvokedAfterPersist(Laporan emp) {
		logger.info("Berhasil menyimpan laporan = " + emp.getId());
	}
 
	@PreUpdate
	public void methodInvokedBeforeUpdate(Laporan emp) {
            String temp = emp.isName()?"Pemasukan":"Pengeluaran";
            Map<String, Object> oldValue = emp.getOldValue();
            logger.info("Mengubah laporan "+temp+" sebelumnya Ref= " 
                        + emp.getId()+"\tTanggal ="+ oldValue.get("tanggal")
                    +"\tKeterangan ="                        + oldValue.get("keterangan")
                    +"\tNominal = "                        + oldValue.get("jumlah")
                    +"\tStatus ="                        + oldValue.get("tipe")
                    +"\tTable ="                        + emp.getKelas()
                    +"\tBank ="                        + oldValue.get("bank")+"\t"
                );
	}
 
	@PostUpdate
	public void methodInvokedAfterUpdate(Laporan emp) {
            String temp = emp.isName()?"Pemasukan":"Pengeluaran";
            logger.info("Berhasil Mengubah laporan "+temp+" Sesudahnya Ref= " 
                        + emp.getId()+"\tTanggal ="
                        + emp.getTanggal()+"\tKeterangan ="
                        + emp.getKeterangan()+"\tNominal = "
                        + emp.getJumlah()+"\tStatus ="
                        + emp.getTipe()+"\tTable ="
                        + emp.getKelas()+"\tBank ="
                        + emp.getTransaksi().getBankId().getNamaBank()+"\t"
                );
	}
 
	@PreRemove
	private void methodInvokedBeforeRemove(Laporan emp) {
            String temp = emp.isName()?"Pemasukan":"Pengeluaran";
            logger.info("Hapus laporan "+temp+" Ref= " 
                        + emp.getId()+"\tTanggal ="
                        + emp.getTanggal()+"\tKeterangan ="
                        + emp.getKeterangan()+"\tNominal = "
                        + emp.getJumlah()+"\tTable ="
                        + emp.getKelas()+"\tBank ="
                        + emp.getTransaksi().getBankId().getNamaBank()+"\t"
                );

//		System.out.println("Removing employee with id = " + emp.getIdEmployee());
	}
 
	@PostRemove
	public void methodInvokedAfterRemove(Laporan emp) {
		logger.info("Berhasil menghapus laporan = " + emp.getId());
//		System.out.println("Removed employee with id = " + emp.getIdEmployee() );
	}    
}
