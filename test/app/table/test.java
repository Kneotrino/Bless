/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author SEED
 */
public class test {

    /**
     * @param args the command line arguments
     */
        private static BigInteger sumAll(List<? extends Laporan> laporanList)
    {
        BigInteger temp = new BigInteger("0");
        for (Laporan list : laporanList) {
            temp = temp.add(list.getPemasukan());
            temp = temp.subtract(list.getPengeluaran());
        }
        return temp;
    }
    public static void main(String[] args) {
        // TODO code application logic here
//        Map<String, long> hashMap = new HashMap<String, long>();
                EntityManager blessingPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();        
                Date hitung =new Date(117, 2, 1);
                System.out.println("hitung = " + hitung);
                Calendar cal = Calendar.getInstance();
                cal.setTime(hitung);
                Date awalBulan = cal.getTime();
                System.out.println("awalBulan = " + awalBulan);
                cal.add(Calendar.MONTH, 1);
                Date akhirBulan = cal.getTime();
                System.out.println("akhirBulan = " + akhirBulan);
                List<Laporan> rangkumanBank = blessingPUEntityManager.createQuery(
                           "SELECT l FROM Laporan l where l.tanggal < :endDate")
                           .setParameter("endDate", awalBulan, TemporalType.DATE)  
                           .getResultList();
                
                List<Laporan> gabunganKas = blessingPUEntityManager.createQuery(
                           "SELECT l FROM Laporan l where l.tanggal < :endDate")
                           .setParameter("endDate", akhirBulan, TemporalType.DATE)  
                           .getResultList();
                
                    System.out.println("Modal Sebelumnya = " + sumAll(rangkumanBank));
                    System.out.println("Modal Sebelumnya = " + rangkumanBank.size());
                    System.out.println("Kas gabungan = " + sumAll(gabunganKas));
                    System.out.println("Kas gabungan = " + gabunganKas.size());
                    
                    System.out.println("Profit = " + sumAll(gabunganKas).subtract(sumAll(rangkumanBank)));
                    System.out.println("Profit = " + ( gabunganKas.size() - rangkumanBank.size() ));

                cal.setTime(hitung);
                cal.add(Calendar.MINUTE, -1);
                awalBulan = cal.getTime();
                cal.setTime(hitung);
                System.out.println("awalBulan = " + awalBulan);
                cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
                cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
                cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));                
                akhirBulan = cal.getTime();
                System.out.println("akhirBulan = " + akhirBulan);
                List<Laporan> list = 
                    blessingPUEntityManager.createQuery("SELECT l FROM Laporan l"
                        + " where l.tanggal BETWEEN :startDate AND :endDate"
                        + " AND l.name = true "
                        + "ORDER BY l.tanggal")
                .setParameter("startDate", awalBulan, TemporalType.TIMESTAMP)
                .setParameter("endDate", akhirBulan, TemporalType.TIMESTAMP)  
                .getResultList();
//                
                List<Laporan> list2 = 
                    blessingPUEntityManager.createQuery("SELECT l FROM Laporan l"
                        + " where l.tanggal BETWEEN :startDate AND :endDate"
                        + " AND l.name = false "
                        + "ORDER BY l.tanggal")
                .setParameter("startDate", awalBulan, TemporalType.TIMESTAMP)
                .setParameter("endDate", akhirBulan, TemporalType.TIMESTAMP)  
                .getResultList();
                System.out.println("Total pemasukan = " + sumAll(list));
                System.out.println("Total pemasukan = " + list.size());
                System.out.println("Total pengeluaran = " + sumAll(list2));
                System.out.println("Total pengeluaran = " + list2.size());
                System.out.println("Profit = " +( list2.size() + list.size() ));
                System.out.println("Pasiva = " + sumAll(rangkumanBank).add(sumAll(list)));
                System.out.println("aktiva = " +  sumAll(gabunganKas).add(sumAll(list2).negate()));
//                
//                BigInteger TotalPemasukan = BigInteger.ZERO;
//                for (Laporan laporan : list2) {
//                    TotalPemasukan = TotalPemasukan.add(laporan.getPengeluaran());
//        }
//                System.out.println("TotalPemasukan = " + TotalPemasukan);
//                        Map<String, Long> collect = 
//                list .stream().collect(Collectors.groupingBy(l  -> l.getDtype(), Collectors.summingLong( a -> a.getPemasukan().longValue())));        
//                        Map<String, Long> collect2 = 
//                list2 .stream().collect(Collectors.groupingBy(l  -> l.getDtype(), Collectors.summingLong( a -> a.getPengeluaran().longValue())));        
//                        System.out.println("collect2 = " + collect2);
//                        System.out.println("collect = " + collect);
                        
//        collect.put("Total Transaksi", total);

                
                
                
                
//                Map<Integer, Integer>sort = new TreeMap<>();
//                sort.put(2017, 1);
//                sort.put(2016, 1);
//                sort.put(2015, 1);
//                System.out.println("sort = " + sort);
//                Query createNativeQuery = blessingPUEntityManager.createNativeQuery("SELECT YEAR(TANGGL_PELUNASAN_PEMBELIAN),NO_POLISI_AKTIF FROM BLESSING.MOBIL WHERE TANGGL_PELUNASAN_PEMBELIAN IS NOT NULL ORDER BY TANGGL_PELUNASAN_PEMBELIAN");
//                List<Object[]> resultList = createNativeQuery.getResultList();
//                
//                long i = 0;
//                for (Object[] object : resultList) {
//                    i++;
//                    System.out.print("Nopol = " + object[1]);
//                    System.out.println(";Tahun = " + object[0]);
////                    sort.put(object.toString(), i);
//            }
//                System.out.println("sort = " + sort);
//        String Mob = "mdd ,ddd, dd,";
//        Mob = Mob.replace(",", "");
//        System.out.println("Mob = " + Mob);
//        EntityManager entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();        
////        List<app.table.Hutang> list = entityManager.createQuery("SELECT h FROM Hutang h", app.table.Hutang.class).getResultList();       
//        List<app.table.BayarhutangPengeluaran> list = entityManager.createQuery("SELECT h FROM BayarhutangPengeluaran h", app.table.BayarhutangPengeluaran.class).getResultList();       
//        BigInteger totalHutang = BigInteger.ZERO;
//        for (BayarhutangPengeluaran bayarhutangPengeluaran : list) {
//                totalHutang = totalHutang.add(bayarhutangPengeluaran.getPengeluaran());
//        }
//        System.out.println("totalHutang = " + totalHutang);
    }
    
}
