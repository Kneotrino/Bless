/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;

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
        String Mob = "mdd ,ddd, dd,";
        Mob = Mob.replace(",", "");
        System.out.println("Mob = " + Mob);
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
