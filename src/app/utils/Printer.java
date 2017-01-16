/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;
import app.table.Laporan;
import com.joobar.csvbless.CSVUtil;
import java.awt.EventQueue;
import java.io.File;
import javaslang.Tuple;
import app.table.Util;
import java.util.List;
import javax.persistence.TypedQuery;
/**
 *
 * @author SEED
 */
public class Printer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run()                        
            {
                    System.out.println("Mulai print");
                    File f = new File("D:\\Print\\Laporan.CSV");         
//                    f.mkdirs();
                    System.out.println("f = " + f);
                    System.out.println("folder f = " + f.isDirectory());                    
                    List<Object> list = getList(Laporan.class); 
                    System.out.println("list.size() = " + list.size());
                    CSVUtil.of(f)
                            .type(Laporan.class)
                            .properties(
                                    Tuple.of("foo", "jumlah", null),
                                    Tuple.of("Goo", "tanggal", null)
                            ).data(
                                    list
                            ).write();                    
                    System.out.println("Akhir print");
            }});
       }    
    public static  List getList(Class kelas)
    {
        String que = "SELECT en FROM " + kelas.getSimpleName() + " en ";
        TypedQuery createQuery = Util.manager.createQuery(que, kelas);   
        for (int i = 0; i < 10; i++) {
            
        }
        return createQuery.getResultList();
    }

}
