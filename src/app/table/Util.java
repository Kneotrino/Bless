/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
            if (!active) {
                manager.getTransaction().begin();            
            }
            Query query = manager.createQuery("SELECT b From Bank b ORDER BY b");
            List<Bank> result = query.getResultList();
            for (Bank bank : result) {
            manager.refresh(bank);
        }

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

    public Util() {
    }

}
