/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blessing;

import app.view.main;
import java.io.File;
import javax.persistence.EntityManager;

/**
 *
 * @author SEED
 */
public class Blessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("blessing.Blessing.main()");
        try{      
           // returns pathnames for files and directory
           File f = null;
           f = new File("C:\\Users\\bleesing");         
           // create directories
           // print
           System.out.println("Directory created? "+f.exists() +"\nDatabase Location :\t"+ f.getCanonicalPath());

        }catch(Exception e){
           // if any error occurs
           e.printStackTrace();
        }
        feel();
        EntityManager blessingPUEntityManager = 
                java.beans.Beans.isDesignTime() ? 
                    null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();
//        
        app.view.main form = new app.view.main();
        form.show();
    }

    private static void feel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    
}
