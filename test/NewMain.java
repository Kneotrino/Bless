
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SEED
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManager blessingPUEntityManager = 
                java.beans.Beans.isDesignTime() ? 
                    null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();
        Query mobilQuery = java.beans.Beans.isDesignTime() ? null : blessingPUEntityManager.createQuery("SELECT m FROM Mobil m");
        List mobilList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : mobilQuery.getResultList();
        
        ObservableList<app.table.Mobil> list = FXCollections.observableArrayList(mobilList);        
        System.out.println(mobilList);
        System.out.println(list.toString());
    }
    
}
