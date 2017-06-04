/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author SEED
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Map<String, long> hashMap = new HashMap<String, long>();
//                EntityManager blessingPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();        
                Map<Integer, Integer>sort = new TreeMap<>();
                sort.put(2017, 1);
                sort.put(2016, 1);
                sort.put(2015, 1);
                System.out.println("sort = " + sort);
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
