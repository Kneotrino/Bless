/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.eclipse.persistence.sessions.Session;

/**
 *
 * @author SEED
 */
public class Util {
        public static EntityManagerFactory fact = Persistence.createEntityManagerFactory("blessingPU");
        public static EntityManager manager = fact.createEntityManager();
    public static long countBank() {
        Query query = manager.createQuery("SELECT COUNT(b) FROM Bank b");
        Long count = (Long) query.getSingleResult();
        return count;
    }
    
    public static Bank findFirst() {
        Query query = manager.createQuery("SELECT b From Bank b ORDER BY b");
        List<Bank> result = query.getResultList();
        return result.get(0);
    }
    public static List<Bank> findALL() {
        Query query = manager.createQuery("SELECT b From Bank b ORDER BY b");
        List<Bank> result = query.getResultList();
        return result;
    }
    public static void RefreshBank()
    {
            boolean active = manager.getTransaction().isActive();
            if (!active) { manager.getTransaction().begin(); }
            Query query = manager.createQuery("SELECT b From Bank b ORDER BY b");
            List<Bank> result = query.getResultList();
            for (Bank bank : result) { manager.refresh(bank); }
    }
    public static void RefreshLaporan()
    {
            boolean active = manager.getTransaction().isActive();
            if (!active) {
                manager.getTransaction().begin();            
            }
             Query query = manager.createQuery("SELECT l FROM Laporan l");
             java.util.List<app.table.Laporan> data = query.getResultList();
             data.forEach((laporan) -> {
                 manager.refresh(laporan);
            });
    }
    
    public static List<? extends Laporan> hitungSaldo
        (List<? extends Laporan> LaporanList
                , Laporan pemasukan
                , Laporan pengeluaran        
        )
    {
        BigInteger temp = BigInteger.ZERO;
        BigInteger totalPemasukan = BigInteger.ZERO;
        BigInteger totalPengeluaran = BigInteger.ZERO;
        for (Laporan lap : LaporanList) {
            temp = temp.add(lap.getPemasukan());
            temp = temp.subtract(lap.getPengeluaran());
            totalPemasukan = totalPemasukan.add(lap.getPemasukan());
            totalPengeluaran = totalPengeluaran.add(lap.getPengeluaran());
            lap.setSaldo(temp);
        }
        pemasukan.setKeterangan("Total Pemasukan");
        pemasukan.setJumlah(totalPemasukan);
        pengeluaran.setKeterangan("Total Pengeluran");
        pengeluaran.setJumlah(totalPengeluaran);
        return LaporanList;        
    }
    public static List<? extends Laporan> hitungSaldo(List<? extends Laporan> LaporanList)
    {
        BigInteger temp = BigInteger.ZERO;
        for (Laporan lap : LaporanList) {
            temp = temp.add(lap.getPemasukan());
            temp = temp.subtract(lap.getPengeluaran());
            lap.setSaldo(temp);
        }
        return LaporanList;
    }
    public static BigInteger getTotalPemasukan(List<? extends Laporan> LaporanList)
    {
        BigInteger temp = BigInteger.ZERO;
        for (Laporan laporan : LaporanList) {
            temp = temp.add(laporan.getPemasukan());
        }
        return temp;
    }
    public static BigInteger getTotalPengeluaran(List<? extends Laporan> LaporanList)
    {
        BigInteger temp = BigInteger.ZERO;
        for (Laporan laporan : LaporanList) {
            temp = temp.add(laporan.getPengeluaran());
        }
        return temp;
    }

public static void backUpDatabase()throws SQLException
{
                Date t = new Date();
                                String backupdirectory ="d:/mybackups/"
                                        + t.getDate()+"-"
                                        + (t.getMonth() + 1)+"-"
                                        + (t.getYear() + 1900)+
                                        "/"+t.getHours()+"-"+t.getMinutes();
                System.out.println("backupdirectory = " + backupdirectory);
                java.sql.Connection conn = manager.unwrap(java.sql.Connection.class);
                CallableStatement cs = conn.prepareCall("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(?)"); 
                cs.setString(1, backupdirectory);
                cs.execute(); 
                cs.close();
                System.out.println("backed up database to "+backupdirectory);
}    
public Util() {
    }

}
