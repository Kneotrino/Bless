/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;
import app.table.Asset;
import app.table.Bank;
import app.table.KeuanganMobil;
import app.table.Laporan;
import app.table.Mobil;
import app.table.MobilPemasukan;
import app.table.MobilPengeluaran;
import app.table.Modal;
import app.table.Pegawai;
import app.table.Pegawaigaji;
import app.table.Pemasukan;
import app.table.Pengeluaran;
import app.table.Perjalanan;
import app.table.Prive;
import app.table.Saldo;
import app.table.Trips;
import com.joobar.csvbless.CSVUtil;
import java.awt.EventQueue;
import java.io.File;
import javaslang.Tuple;
import app.table.Util;
import app.table.pembagianLaba;
import com.joobar.csvbless.WriteStep;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                    Date p = new Date();
                    final SimpleDateFormat formator = new SimpleDateFormat("dd-MM-yyyy");
                    File dir = rootdir;
                    File folder = new File(dir, "Laporan Semua "+ formator.format(p));
                    folder.mkdirs();
                    PrintMobil(folder);
                    PrintPerjalanan(folder);
                    PrintKas(folder);
                    PrintPegawai(folder);
                    PrintLaporan(folder, Laporan.class);
                    PrintLaporan(folder, Pemasukan.class);
                    PrintLaporan(folder, Pengeluaran.class);
                    PrintLaporan(folder, Asset.class);
                    PrintLaporan(folder, Modal.class);
                    PrintLaporan(folder, Prive.class);
                    PrintLaporan(folder, pembagianLaba.class);    
                try {
                    Desktop.getDesktop().open(folder);
                } catch (IOException ex) {
                    Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
    }
    public static void PrintKas(File place)
    {
              File f = new File(place, "Data Kas");
              f.mkdirs();
              System.out.println("f = " + f);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final DecimalFormat IDR = new DecimalFormat("IDR #,##0");              
              List<app.table.Bank> resultList = getDataList(app.table.Bank.class);
              System.out.println("resultList = " + resultList.size());    
              for (Bank peg : resultList) {
                  String pe = peg.getBankId()+"-"
                          +peg.getNamaBank()+
                          ".CSV";
                  File p = new File(f, pe);
                  List<Saldo> Sal = peg.getSaldoList();
                  BigInteger temp = BigInteger.ZERO;
                  for (Saldo saldo : Sal) {
                           temp = temp.add(saldo.getLaporan().getPemasukan());
                           temp = temp.subtract(saldo.getLaporan().getPengeluaran());
                           saldo.getLaporan().setSaldo(temp);
                  }
                  List a = Sal;
                  WriteStep dataList = CSVUtil.of(p)
                        .type(app.table.Perjalanan.class)
                            .properties(
                                Tuple.of("Ref", "saldoId", null),
                                Tuple.of("Tanggal", "laporan.tanggal", d -> formator.format(d)),
                                Tuple.of("Keterangan", "laporan.keterangan", d -> d),
                                Tuple.of("Pemasukan", "laporan.pemasukan", d -> d==null?"0":IDR.format(d) ),
                                Tuple.of("Pengeluaran", "laporan.pengeluaran", d -> d==null?"0":IDR.format(d) ),
                                Tuple.of("Pengeluaran", "laporan.saldo", d -> d==null?"0":IDR.format(d) ),
                                Tuple.of("Jenis", "laporan.jenis", d -> d),
                                Tuple.of("Bank", "bankId", d -> d)
                    ).dataList(a);
                try {
                    dataList.write();            
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    e.printStackTrace();
                    return ;
                }
        }
    
    }
    public static void PrintPegawai(File place)
    {
              File f = new File(place, "Data Pegawai");
              f.mkdirs();
              System.out.println("f = " + f);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final DecimalFormat IDR = new DecimalFormat("IDR #,##0");              
              List<app.table.Pegawai> resultList = getDataList(app.table.Pegawai.class);
              System.out.println("resultList = " + resultList.size());    
              for (Pegawai peg : resultList) {
                  String pe = peg.getId()+"-"
                          +peg.getNama()+"-"
                          +peg.getStatus()+
                          ".CSV";
                  File p = new File(f, pe);
                  List<Pegawaigaji> pegawaigajiList = peg.getPegawaigajiList();
                  List a = pegawaigajiList;
                  WriteStep dataList = CSVUtil.of(p)
                        .type(app.table.Perjalanan.class)
                            .properties(
                                Tuple.of("Ref", "id", null),
                                Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                                Tuple.of("Keterangan", "keterangan", d -> d),
                                Tuple.of("Nominal", "pengeluaran", d -> d==null?"0":IDR.format(d) ),
                                Tuple.of("Bank", "transaksi.bankId", d -> d)
                    ).dataList(a);
                try {
                    dataList.write();            
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    e.printStackTrace();
                    return ;
                }
        }
    }
    public static  List getList(Class kelas)
    {
        String que = "SELECT en FROM " + kelas.getSimpleName() + " en order by en.tanggal ";
        TypedQuery createQuery = Util.manager.createQuery(que, kelas);   
        return createQuery.getResultList();
    }
    public static  List getDataList(Class kelas)
    {
        String que = "SELECT en FROM " + kelas.getSimpleName() + " en";
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
                    e.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(null, "Gagal Print, Karena file sementara terbuka\n"+e);
                    return ;
                } 
                    finally {
                        System.out.println("filename = " + filename);
//                    javax.swing.JOptionPane.showMessageDialog(null, 
//                                "Berhasil Print "+
//                                kelas.getSimpleName()
//                                + "\nPath = "+ filename.toString());
                    }
                
    }
    public static void  PrintPerjalanan (File place)
    {
              File f = new File(place, "Laporan Perjalanan");
              f.mkdirs();
              System.out.println("f = " + f);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final DecimalFormat IDR = new DecimalFormat("IDR #,##0");              
              List<app.table.Trips> resultList = getDataList(app.table.Trips.class);
              System.out.println("resultList = " + resultList.size());
              for (Trips t : resultList) {
                  String tip = 
                          "Lap.Perjalanan-"+
                          t.getPerjalananke()+"-"+
                          t.getKeterangan()+".CSV";
                      File p = new File(f, tip);            
                List<Perjalanan> PL = t.getPerjalananList();
                java.math.BigInteger saldo = new java.math.BigInteger("0");            
                for (Perjalanan a : PL) {
                    saldo = saldo.subtract(a.getPengeluaran());
                    saldo = saldo.add(a.getPemasukan());
                    a.setSaldo(saldo);
                }    
                List a = PL;
                  WriteStep dataList = CSVUtil.of(p)
                        .type(app.table.Perjalanan.class)
                            .properties(
                                Tuple.of("Ref", "id", null),
                                Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                                Tuple.of("Keterangan", "keterangan", d -> d),
                                Tuple.of("Kirim", "kirim2", d -> d==null?"0":IDR.format(d) ),
                                Tuple.of("Kembalikan", "kembali2", d -> d==null?"0":IDR.format(d) ),
                                Tuple.of("Pakai", "pengeluaran", d -> d==null?"0":IDR.format(d) ),
                                Tuple.of("Saldo", "saldo", d -> IDR.format(d) )
                    ).dataList(a);
                try {
                    dataList.write();            
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    e.printStackTrace();
                    return ;
                }                   
        }
    
    }
    public static void PrintMobil(File place)
    {
//              String filename = kelas.getSimpleName()+ ".CSV";
              File f = new File(place, "Data Mobil");
              f.mkdirs();
              System.out.println("f = " + f);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final DecimalFormat IDR = new DecimalFormat("IDR #,##0");              
              TypedQuery createQuery = Util.manager.createQuery(
                      "SELECT m FROM Mobil m order by m desc", app.table.Mobil.class);   
              List<Mobil> resultList = createQuery.getResultList();
              System.out.println("resultList = " + resultList.size());
              for (Mobil mobil : resultList) {
                    String mo = 
                            mobil.getMobilId()+ "-" +
                            mobil.getMerk() + "-" +
                            mobil.getType()+ "-" +
                            mobil.getJenis()+ "-" +
                            mobil.getTahun()+ "-" +
                            mobil.getWarna()+ "-" +
                            mobil.getStatusMobil()+ "-" +
                            mobil.getDebitur().getNama()+ ".CSV"
                            ;
                      File p = new File(f, mo);
                      List<KeuanganMobil> b = mobil.getKeuanganMobils();
//                      KeuanganMobil total1 = new MobilPemasukan();
//                      total1.setKeterangan("Total Pemasukan");
//                      KeuanganMobil total2 = new MobilPengeluaran();
//                      total2.setKeterangan("Total Pengeluaran");
//                      KeuanganMobil laba = new MobilPemasukan();
//                      laba.setKeterangan("Laba");
//                      BigInteger temp1 = BigInteger.ZERO;
//                      BigInteger temp2 = BigInteger.ZERO;
//                      Saldo saldo1 = new Saldo();
//                      Bank bank = new Bank();
//                      saldo1.setBankId(bank);                      
//                      for (KeuanganMobil m : b) {                          
//                          temp1 = temp1.add(m.getPemasukan());
//                          temp2 = temp2.add(m.getPengeluaran());
//                      }
//                      total1.setTransaksi(saldo1);
//                      total2.setTransaksi(saldo1);
//                      laba.setTransaksi(saldo1);
//                      total1.setJumlah(temp1);
//                      total2.setJumlah(temp2);
//                      laba.setJumlah(temp1.subtract(temp2));
//                      b.add(total1);
//                      b.add(total2);
//                      b.add(laba);
                      List a = b;
                      WriteStep dataList = CSVUtil.of(p)
                        .type(app.table.KeuanganMobil.class)
                            .properties(
                                Tuple.of("Ref", "id", null),
                                Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                                Tuple.of("Keterangan", "keterangan", d -> d),
                                Tuple.of("Pemasukan", "pemasukan", d -> IDR.format(d) ),
                                Tuple.of("Pengeluaran", "pengeluaran", d -> IDR.format(d) ),
                                Tuple.of("Bank", "transaksi.bankId", d -> d)
                ).dataList(a);
                    try {
                    dataList.write();            
                } catch (Exception e) {
                    e.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    return ;
                } 

        }
    }

}
