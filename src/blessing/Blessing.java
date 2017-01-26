/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blessing;

//import app.view.main;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.UIManager;

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
           File f = null;
           f = new File("C:\\Users\\bleesing");         
           System.out.println("Directory created? "+f.exists() +"\nDatabase Location :\t"+ f.getCanonicalPath());
           f = new File("C:\\Users\\blessing\\sd");         
           System.out.println("Directory created? "+f.exists() +"\nResource Location :\t"+ f.getCanonicalPath());
            if (!f.exists()) {
                f.mkdir();                
                System.out.println("Directory created? "+f.exists() +"\nResource Location :\t"+ f.getCanonicalPath());
            }

        }catch(Exception e){
           // if any error occurs
           e.printStackTrace();
        }
        feel();        
        try {
        JFrame form = new app.view.ShowRoom();
        form.show();            
        } 
        catch (org.eclipse.persistence.exceptions.DatabaseException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex);
//            System.exit(4000);            
//        }catch (Exception e) {
//            javax.swing.JOptionPane.showMessageDialog(null, e);
//            System.exit(40);            
        }
    }
    private static void feel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println("info.getName() = " + info.getName());
                if ("Nimbus".equals(info.getName())) {
                    System.out.println("info = " + info.getClassName());
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    
}
