
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
        String n = "Saya makan nasi ayam di rumah makan padang.";
        String[] kata = n.split(" ");
        for (String i : kata) {
            StringBuilder uni = new StringBuilder("");
            
            int temp = 0;
            for (int j = 0; j < i.length(); j++) {
                if (i.charAt(j) == 'a' )
                    {
                        temp++;
                    }
                if (temp > 1)
                    {
                        StringBuilder myName = new StringBuilder(i);
                        myName.setCharAt(j, 'o');
//                        System.out.println(myName);
                        uni.append(myName);
                        break;
                    }
            }
            if (temp <2) {
                uni.append(i);
                
            }
            System.out.println(uni);
//            System.out.println(temp);
        }
    
//            char[] j = i.toCharArray();
//            for (char c : j) {
//                
//                int temp = 0;
//                if (c=='a' ) {
//                    temp++;
//                }
//                System.out.println(temp);
//                
//                if (temp >2) {
//                    System.out.println(temp);
//                    System.out.println(j);
//                }
//            }
        
//        System.out.println(kata);
//        EntityManager blessingPUEntityManager = 
//                java.beans.Beans.isDesignTime() ? 
//                    null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();
//        Query mobilQuery = java.beans.Beans.isDesignTime() ? null : blessingPUEntityManager.createQuery("SELECT m FROM Mobil m");
//        List mobilList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : mobilQuery.getResultList();
//        
//        ObservableList<app.table.Mobil> list = FXCollections.observableArrayList(mobilList);        
//        System.out.println(mobilList);
//        System.out.println(list.toString());
    }
    
}
