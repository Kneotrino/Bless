/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;
import app.table.Laporan;
import app.table.Pemasukan;
import app.table.Pengeluaran;
import com.joobar.csvbless.CSVUtil;
import java.awt.EventQueue;
import java.io.File;
import javaslang.Tuple;
import app.table.Util;
import com.joobar.csvbless.WriteStep;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                            File dir = new File("D:\\Print");
                            Printing(dir);
       }
    public static void Printing(File rootdir)
    {
        EventQueue.invokeLater(new Runnable() {
            public void run()                        
            {
                    File dir = rootdir;
                    dir.mkdirs();
                    File folderMan = new File(dir, "Management");
                    folderMan.mkdirs();
                    File folder = new File(dir, "");
                    folder.mkdirs();
//                    PrintLaporan(dir, Laporan.class);
                    PrintLaporan(dir, Pemasukan.class);
                    PrintLaporan(dir, Pengeluaran.class);
            }});
    }
    public static  List getList(Class kelas)
    {
        String que = "SELECT en FROM " + kelas.getSimpleName() + " en order by en.tanggal ";
        TypedQuery createQuery = Util.manager.createQuery(que, kelas);   
        return createQuery.getResultList();
    }
    public static void PrintLaporan(File place,Class kelas)
    {
              String filename = kelas.getSimpleName()+ ".CSV";
              File f = new File(place, filename);
              System.out.println("f = " + f);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final DecimalFormat IDR = new DecimalFormat("IDR #,##0");              
              WriteStep dataList = CSVUtil.of(f)
                .type(kelas)
                .properties(
                        Tuple.of("Ref", "id", null),
                        Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                        Tuple.of("Keterangan", "keterangan", d -> d),
                        Tuple.of("Jumlah", "jumlah", d -> IDR.format(d) ),
                        Tuple.of("Jenis", "jenis", d -> d),
                        Tuple.of("Bank", "transaksi.bankId", d -> d)
                )
                .dataList(getList(kelas));
                    try {
                    dataList.write();            
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Gagal Print, Karena file sementara terbuka\n"+e);
                } 
                int con = javax.swing.JOptionPane.showConfirmDialog(null, 
                                "Berhasil Print "+
                                kelas.getSimpleName()
                                + "\nPath = "+ filename);
                Desktop desktop = Desktop.getDesktop();
                if(!Desktop.isDesktopSupported())
                            javax.swing.JOptionPane.showMessageDialog(null
                                    , "Gagal Print, system tidak mendukung\n");
                if (con == 0) {
                            if(f.exists()) 
                                try {
                                    desktop.open(f);
                            } catch (IOException ex) {
                                Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, ex);
                            }    
                }
                
    }
}
