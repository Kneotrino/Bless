/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;
import app.table.Asset;
import app.table.Bank;
import app.table.Bayarhutang;
import app.table.Bayarjasa;
import app.table.Bayarrental;
import app.table.Bpkbtitipan;
import app.table.Hutang;
import app.table.Investor;
import app.table.KeuanganMobil;
import app.table.Laporan;
import app.table.Leasing;
import app.table.Mobil;
import app.table.MobilPemasukan;
import app.table.MobilPengeluaran;
import app.table.Modal;
import app.table.Pegawai;
import app.table.Pegawaigaji;
import app.table.Pemasukan;
import app.table.Pengeluaran;
import app.table.Perjalanan;
import app.table.Piutang;
import app.table.Prive;
import app.table.Rental;
import app.table.Saham;
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
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
                    
                    PrintHutang(folder);
                    PrintPiHutang(folder);
                    PrintLeasing(folder);
                    PrintRentalMobil(folder);
                    PrintInvestor(folder);
                    PrintAsset(folder);
                    PrintMobil(folder);
                    PrintJasa(folder);
                    PrintPerjalanan(folder);
                    PrintKas(folder);
                    PrintPegawai(folder);
                    PrintLaporan(folder, Laporan.class);
                    PrintLaporan(folder, Pemasukan.class);
                    PrintLaporan(folder, Pengeluaran.class);
                    PrintLaporan(folder, pembagianLaba.class);                    
                try {
                    Desktop.getDesktop().open(folder);
                } catch (IOException ex) {
                    Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }});
    }
    public static void PrintHutang(File place)
    {
              File f = new File(place, "Data Hutang-Peminjam");
              f.mkdirs();
              System.out.println("f = " + f);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final DecimalFormat IDR = new DecimalFormat("#,##0");              
              List<app.table.Hutang> resultList = getDataList(app.table.Hutang.class);
              File T = new File(f, "Daftar Peminjam.CSV");
              System.out.println("resultList = " + resultList.size());    
               WriteStep data = CSVUtil.of(T)
                        .type(app.table.Hutang.class)
                            .properties(
                                Tuple.of("REF","hutangid", d -> d==null?" ":d),
                                Tuple.of("Nama","nama", d -> d==null?" ":d),
                                Tuple.of("Alamat","alamat", d -> d==null?" ":d),
                                Tuple.of("Nomor HP","nomorhp", d -> d==null?" ":d),
                                Tuple.of("Nomor KTP","nomorktp", d -> d==null?" ":d),
                                Tuple.of("Total Pinjamnan","jumlahpinjaman", d -> d==null?" ":IDR.format(d)),
                                Tuple.of("Sisa Pelunasan","sisapinjaman", d -> d==null?" ":IDR.format(d)),
                                Tuple.of("Keterangan","keterangan", d -> d==null?" ":d),
                                Tuple.of("Tanggal Lunas","tanggallunas", d -> d==null?" ":formator.format(d)),
                                Tuple.of("Tanggal Pinjam","tanggalpinjam", d -> d==null?" ":formator.format(d)),
                                Tuple.of("Tanggal Lunas","lunas", d -> d==null?" ":d)
                            ).dataList(getDataList(app.table.Hutang.class));
              try {
                    data.write();            
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    e.printStackTrace();
                    return ;
                } 
              for (Hutang peg : resultList) {
                  String pe = peg.getHutangid()+"-"
                          +peg.getNama()+"-"
                          +peg.getKeterangan()+
                          ".CSV";
                  File p = new File(f, pe);
                  List<Bayarhutang> pegawaigajiList = peg.getBayarhutangs();
                  List a = pegawaigajiList;
                  WriteStep dataList = CSVUtil.of(p)
                        .type(app.table.Bayarhutang.class)
                            .properties(
                                Tuple.of("Ref", "id", null),
                                Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                                Tuple.of("Keterangan", "keterangan", d -> d),
                                Tuple.of("Pengeluaran/Peminjaman", "pengeluaran", d -> d==null?"0":IDR.format(d) ),
                                Tuple.of("Pemasukan/Pelunasan", "pemasukan", d -> d==null?"0":IDR.format(d) ),
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
    public static void PrintPiHutang(File place)
    {    
              File f = new File(place, "Data Pihutang-Pinjaman");
              f.mkdirs();
              System.out.println("f = " + f);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final DecimalFormat IDR = new DecimalFormat("#,##0");              
              List<app.table.Piutang> resultList = getDataList(app.table.Piutang.class);
              File T = new File(f, "Daftar Pinjaman.CSV");
              System.out.println("resultList = " + resultList.size());    
               WriteStep data = CSVUtil.of(T)
                        .type(app.table.Piutang.class)
                            .properties(
                                    Tuple.of("REF","piutangid", d -> d==null?" ":d),
                                    Tuple.of("keterangan","keterangan", d -> d==null?" ":d),
                                    Tuple.of("Tanggal Bayar","tglbyr", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("jaminan","jaminan", d -> d==null?" ":d),
                                    Tuple.of("Total Peminjaman","jumlahPelunasan", d -> d==null?" ":IDR.format(d)),
                                    Tuple.of("Total Pelunasan","jumlahPimjaman", d -> d==null?" ":IDR.format(d)),
                                    Tuple.of("sisa","sisa", d -> d==null?" ":IDR.format(d)),
                                    Tuple.of("Tanggal Awal","tglawal", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("Tanggal Akhir","tglakhir", d -> d==null?" ":formator.format(d))
                            ).dataList(getDataList(app.table.Piutang.class));
              try {
                    data.write();            
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    e.printStackTrace();
                    return ;
                } 
              for (Piutang peg : resultList) {
                  String pe = peg.getPiutangid()+"-"
                          +peg.getKeterangan()+"-"+
                          ".CSV";
                  File p = new File(f, pe);
                  List<app.table.Bayarpihutang> pegawaigajiList = peg.getBayarpihutangList();
                  List a = pegawaigajiList;
                  WriteStep dataList = CSVUtil.of(p)
                        .type(app.table.Bayarpihutang.class)
                            .properties(
                                Tuple.of("Ref", "id", null),
                                Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                                Tuple.of("Keterangan", "keterangan", d -> d),
                                Tuple.of("Peminjaman/Pemasukan", "pemasukan", d -> d==null?"0":IDR.format(d) ),
                                Tuple.of("Pelunasan/Pelunasan", "pengeluaran", d -> d==null?"0":IDR.format(d) ),
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
    public static void PrintLeasing(File place)
    {    
        File f = new File(place, "Data Leasing");
        f.mkdirs();
        System.out.println("f = " + f);
        final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
        final DecimalFormat IDR = new DecimalFormat("#,##0"); 
        List<app.table.Leasing> dataList = getDataList(app.table.Leasing.class);
        for (Leasing leasing : dataList) {
                List a = leasing.getListleasingList();
                WriteStep bpkb = CSVUtil.of(new File(f, leasing.getNama()+".CSV"))
                        .type(app.table.Listleasing.class)
                            .properties(
                                Tuple.of("REF","listleasingId", d -> d==null?" ":d),
                                Tuple.of("tglDp","tglDp", d -> d==null?" ":formator.format(d)),
                                Tuple.of("Mobil REF","mobil", d -> d==null?" ":d),
                                Tuple.of("M.Merek","mobil.merk", d -> d==null?" ":d),
                                Tuple.of("M.Tipe","mobil.type", d -> d==null?" ":d),
                                Tuple.of("M.Jenis","mobil.jenis", d -> d==null?" ":d),
                                Tuple.of("M.Tahun","mobil.tahun", d -> d==null?" ":d),
                                Tuple.of("M.Warna","mobil.warna", d -> d==null?" ":d),
                                Tuple.of("Debitur REF","mobil.debitur", d -> d==null?" ":d),
                                Tuple.of("nominal","nominal", d -> d==null?" ":IDR.format(d)),
                                Tuple.of("Terpenuhi","TERPENUHI", d -> d==null?" ":d),
                                Tuple.of("tglOk","tglOk", d -> d==null?" ":formator.format(d)),
                                Tuple.of("Leasing REF","leasingLeasingId", d -> d==null?" ":d)
                    ).dataList(a);
                try {
                    bpkb.write();            
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    e.printStackTrace();
                    return ;
                }
        }

    }
    public static void PrintRentalMobil(File place)
    {
              File f = new File(place, "Data Rental");
              f.mkdirs();
              File f1 = new File(f, "[Mobil Rental].CSV");
              System.out.println("f = " + f);
              System.out.println("f = " + f1);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final SimpleDateFormat ff = new SimpleDateFormat("dd-MM-yyyy");
              final DecimalFormat IDR = new DecimalFormat("IDR #,##0");              
              List resultList = getDataList(app.table.Mobilrental.class);
              System.out.println("resultList = " + resultList.size());    
              List<app.table.Rental> b = getDataList(app.table.Rental.class);
              for (Rental rental : b) {
                  String foo = 
                          rental.getRentalid()+ "-"+
                          rental.getPemakai()+"-"+
                          rental.getDriver()+"-"+
                          ff.format(rental.getTglmulai())+"-"
                          +".CSV";
                  File t = new File(f, foo);
                  System.out.println("foo = " + foo);
                  List as = rental.getBayarrentalList();
                  WriteStep data = CSVUtil.of(t)
                        .type(app.table.Bayarrental.class)
                            .properties(
                                Tuple.of("Ref", "id", null),
                                Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                                Tuple.of("Keterangan", "keterangan", d -> d),
                                Tuple.of("Pemasukan", "pemasukan", d -> IDR.format(d) ),
                                Tuple.of("Pengeluaran", "pengeluaran", d -> IDR.format(d) ),
                                Tuple.of("Jenis", "jenis", d -> d),
                                Tuple.of("Bank", "transaksi.bankId", d -> d)
                                )
                        .dataList(as);
                    try {
                        data.write();      
                        System.out.println("t = " + t);
                } catch (Exception e) {
                    e.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(null, "Gagal Print, Karena file sementara terbuka\n"+e);
                    return ;
                } 
                  
        }
              List a = b;
              System.out.println("a = " + a);
              WriteStep dataList = CSVUtil.of(f1)
                .type(app.table.Mobilrental.class)
                .properties(
                    Tuple.of("mobilId","mobilId", d-> d),
                    Tuple.of("noPolisiAktif","noPolisiAktif", d-> d),
                    Tuple.of("merk","merk", d-> d),
                    Tuple.of("type","type", d-> d),
                    Tuple.of("warna","warna", d-> d),
                    Tuple.of("tahun","tahun", d-> d),
                    Tuple.of("keterangan","keterangan", d-> d),
                    Tuple.of("statusMobil","statusMobil", d-> d)
                )
                .dataList(resultList);
                    try {
                    dataList.write();            
                } catch (Exception e) {
                    e.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(null, "Gagal Print, Karena file sementara terbuka\n"+e);
                    return ;
                } 
                    finally {
                    }
    
    }
    public static void PrintJasa(File place)
    {
              File f = new File(place, "Data Jasa Cabut Berkasa");
              f.mkdirs();
              System.out.println("f = " + f);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final DecimalFormat IDR = new DecimalFormat("#,##0");              
              List<app.table.Bpkbtitipan> resultList = getDataList(app.table.Bpkbtitipan.class);
              System.out.println("resultList = " + resultList.size());    
              for (Bpkbtitipan peg : resultList) {
                  String pe = 
                          peg.getBpkbId()+"-" +
                          peg.getAnBpkb()+"-" +
                          peg.getNoBpkb()+"-" +
                          peg.getNoPolisiAktif()+"-" +
                          ".CSV";
                  File p = new File(f, pe);
                  List<Bayarjasa> Sal = peg.getBayarjasaList();
                  BigInteger temp = BigInteger.ZERO;
                  List a = Sal;
                  WriteStep dataList = CSVUtil.of(p)
                        .type(app.table.Bayarjasa.class)
                            .properties(
                        Tuple.of("Ref", "id", null),
                        Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                        Tuple.of("Keterangan", "keterangan", d -> d),
                        Tuple.of("Pemasukan", "pemasukan", d -> IDR.format(d) ),
                        Tuple.of("Pengeluaran", "pengeluaran", d -> IDR.format(d) ),
                        Tuple.of("Jenis", "jenis", d -> d),
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
    public static void PrintInvestor(File place)
    {
              File T = new File(place, "Data Investor");
              T.mkdirs();
              File f = new File(T, "Data Investor.CSV");
              System.out.println("f = " + f);
              DecimalFormat df = new DecimalFormat();
              df.setMaximumFractionDigits(2);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final DecimalFormat IDR = new DecimalFormat("IDR #,##0");              
              List<app.table.Investor> resultList = getDataList(app.table.Investor.class);
              app.table.Investor temp = new Investor(0);
              BigInteger total3 = BigInteger.ZERO;
              BigInteger total4 = BigInteger.ZERO;
              for (Investor investor : resultList) {
                List<Saham> sahamList = investor.getSahamList();
                BigInteger total1 = BigInteger.ZERO;
                BigInteger total2 = BigInteger.ZERO;
                for (Saham saham : sahamList) {
                    total1 = total1.add(saham.getModal() == null? BigInteger.ZERO : saham.getModal().getJumlah());
                    total2 = total2.add(saham.getPrive()== null? BigInteger.ZERO : saham.getPrive().getJumlah());                    
                    }
                investor.setPrive(total2);
                investor.setModal(total1.subtract(total2));
                total3 = total3.add(investor.getModal());
                total4 = total4.add(total2);                
                }
                float t = total3.floatValue();
                for (Investor investor : resultList) {
                    float p = investor.getModal().floatValue();
                    investor.setPer(df.format((p/t)*100)+"%");
                }
                temp.setModal(total3);
                temp.setPrive(total4);
                temp.setPer("100%");
                resultList.add(temp);
              List a = resultList;
              System.out.println("resultList = " + resultList.size());    
              WriteStep dataList = CSVUtil.of(f)
                        .type(app.table.Bayarjasa.class)
                            .properties(
                                    Tuple.of("id","id", d-> d),
                                    Tuple.of("nama","nama", d-> d),
                                    Tuple.of("alamat","alamat", d-> d),
                                    Tuple.of("kontak","kontak", d-> d),
                                    Tuple.of("modal","modal", d-> IDR.format(d)),
                                    Tuple.of("prive","prive", d-> IDR.format(d)),
                                    Tuple.of("per","per", d-> d)
                    ).dataList(a);
                try {
                    dataList.write();            
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    e.printStackTrace();
                    return ;
                }
              List<app.table.Investor> list1 = getDataList(app.table.Investor.class);
              for (Investor peg : list1) {
                  String pe = peg.getNama()+"-"
                          +peg.getAlamat()+"-"
                          +peg.getKontak()+"-"
                          +peg.getId()+
                          ".CSV";
                  File p = new File(T, pe);
                  List<Saham> pegawaigajiList = peg.getSahamList();
                  System.out.println("pegawaigajiList = " + pegawaigajiList.size());
                  List b = pegawaigajiList;
                  WriteStep data = CSVUtil.of(p)
                        .type(app.table.Saham.class)
                            .properties(
                                Tuple.of("Ref", "id", null),
                                Tuple.of("Keterangan", "keterangan", d -> d),
                                Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
//                                Tuple.of("Pemasukan/Modal", "modal.jumlah", d -> d==null?"0":IDR.format(d) ),
//                                Tuple.of("Pengeluaran/Prive", "prive.jumlah", d -> d==null?"0":IDR.format(d) ),
                                Tuple.of("Bank", "b", d -> d)
                    ).dataList(b);
                try {
                    data.write();            
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    e.printStackTrace();
                    return ;
                }
        }        
                
    }
    public static void PrintAsset(File place)
    {
              File f = new File(place, "Data Asset.CSV");
              System.out.println("f = " + f);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final DecimalFormat IDR = new DecimalFormat("IDR #,##0");            
              List a = getDataList(app.table.Asset.class);
              WriteStep dataList = CSVUtil.of(f)
                        .type(app.table.Perjalanan.class)
                            .properties(
                        Tuple.of("Ref", "id", null),
                        Tuple.of("Tanggal", "tanggal", d -> formator.format(d)),
                        Tuple.of("Keterangan", "keterangan", d -> d),
                        Tuple.of("Jumlah", "jumlah", d -> IDR.format(d) ),
                        Tuple.of("Jenis", "jenis", d -> d),
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
              File T = new File(f, "Daftar Pegawai.CSV");
              System.out.println("resultList = " + resultList.size());    
               WriteStep data = CSVUtil.of(T)
                        .type(app.table.Pegawai.class)
                            .properties(
                                    Tuple.of("id","id", d-> d),
                                    Tuple.of("alamat","alamat", d-> d),
                                    Tuple.of("nama","nama", d-> d),
                                    Tuple.of("nomorhp","nomorhp", d-> d),
                                    Tuple.of("status","status", d-> d),
                                    Tuple.of("tanggalGajian","tanggalGajian", d-> formator.format(d)),
                                    Tuple.of("tanggalMasuk","tanggalMasuk", d-> formator.format(d))
                    ).dataList(getDataList(app.table.Pegawai.class));
              try {
                    data.write();            
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    e.printStackTrace();
                    return ;
                } 
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
        TypedQuery createQuery = EM.createQuery(que, kelas);   
        return createQuery.getResultList();
    }
    public static  List getDataList(Class kelas)
    {
        String que = "SELECT en FROM " + kelas.getSimpleName() + " en";
        TypedQuery createQuery = EM.createQuery(que, kelas);   
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
              File f = new File(place, "Data Mobil");
              f.mkdirs();
              System.out.println("f = " + f);
              final SimpleDateFormat formator = new SimpleDateFormat("dd/MM/yyyy");
              final DecimalFormat IDR = new DecimalFormat("IDR #,##0");              
              TypedQuery createQuery = EM.createQuery(
                      "SELECT m FROM Mobil m order by m desc", app.table.Mobil.class);   
              List<Mobil> resultList = createQuery.getResultList();
              System.out.println("resultList = " + resultList.size());
              List c = resultList;
              Function fungsi = d -> d==null?" ":d;
              Function tanggal = d -> d==null?" ":formator.format(d);
              WriteStep data = CSVUtil.of(new File(f, "Daftar Mobil.CSV"))
                        .type(app.table.Mobil.class)
                            .properties(
                                    Tuple.of("mobilId","mobilId", d-> d),
                                    Tuple.of("noPolisiAktif","noPolisiAktif", fungsi),
                                    Tuple.of("noPolisiLama","noPolisiLama", fungsi),
                                    Tuple.of("jenis","jenis", fungsi),
                                    Tuple.of("type","type", fungsi),
                                    Tuple.of("merk","merk", fungsi),
                                    Tuple.of("bahanBakar","bahanBakar", fungsi),
                                    Tuple.of("noMesin","noMesin", fungsi),
                                    Tuple.of("noRangka","noRangka", fungsi),
                                    Tuple.of("pemilikBaru","pemilikBaru", fungsi),
                                    Tuple.of("pemilikLama","pemilikLama", fungsi),
                                    Tuple.of("penjual","penjual", fungsi),
                                    Tuple.of("silinder","silinder", fungsi),
                                    Tuple.of("statusMobil","statusMobil", fungsi),
                                    Tuple.of("tahun","tahun", fungsi),
                                    Tuple.of("warna","warna", fungsi),
                                    Tuple.of("tanggalBeli","tanggalBeli", d -> d == null? " ": formator.format(d)),
                                    Tuple.of("tanggalJual","tanggalJual", d -> d == null? " ": formator.format(d)),
                                    Tuple.of("keterangan","keterangan", fungsi),
                                    Tuple.of("Ref Pembeli","debitur.debiturId", fungsi),
                                    Tuple.of("Ref BPKB","bpkb.bpkbId", fungsi)
                    ).dataList(getDataList(app.table.Mobil.class));
                WriteStep debitur = CSVUtil.of(new File(f, "Daftar Debitur.CSV"))
                        .type(app.table.Debitur.class)
                            .properties(
                                    Tuple.of("debitur REF","debiturId", d -> d==null?" ":d),
                                    Tuple.of("Nama","nama", d -> d==null?" ":d),
                                    Tuple.of("Alamat","alamat", d -> d==null?" ":d),
                                    Tuple.of("Nomot HP","noHp", d -> d==null?" ":d),
                                    Tuple.of("Nomor Identitas","noKtp", d -> d==null?" ":d),
//                                    Tuple.of("norek","norek", d -> d==null?" ":d),
                                    Tuple.of("Nama ke-2","bank", d -> d==null?" ":d),
                                    Tuple.of("Nomor HP ke-2","pembayaran", d -> d==null?" ":d),
                                    Tuple.of("scan","scan", d -> d==null?" ":d),
                                    Tuple.of("mobil REF","mobil", d -> d==null?" ":d)
                    ).dataList(getDataList(app.table.Debitur.class));
                WriteStep bpkb = CSVUtil.of(new File(f, "Daftar BPKB.CSV"))
                        .type(app.table.Bpkb.class)
                            .properties(
                                    Tuple.of("bpkb REF","bpkbId", d -> d==null?" ":d),
                                    Tuple.of("ket","ket", d -> d==null?" ":d),
                                    Tuple.of("anBpkb","anBpkb", d -> d==null?" ":d),
                                    Tuple.of("noBpkb","noBpkb", d -> d==null?" ":d),
                                    Tuple.of("Posisi BPKB","posisi", d -> d==null?" ":d),
                                    Tuple.of("Posisi Faktur","noPolisiAktif", d -> d==null?" ":d),
                                    Tuple.of("status","status", d -> d==null?" ":d),
                                    Tuple.of("stnk","stnk", d -> d==null?" ":d),
                                    Tuple.of("tglBbn","tglBbn", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tglKembaliBbn","tglKembaliBbn", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tglCb","tglCb", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tglKembaliCb","tglKembaliCb", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tglLeasing","tglLeasing", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("tglTerima","tglTerima", d -> d==null?" ":formator.format(d)),
                                    Tuple.of("mobil REF","mobil", d -> d==null?" ":d)
                    ).dataList(getDataList(app.table.Bpkb.class));
              try {
                    data.write();            
                    debitur.write();            
                    bpkb.write();            
                } catch (Exception e) {
                    javax.swing.JOptionPane.showMessageDialog(null
                            , "Gagal Print, Karena file sementara terbuka\n"+e);
                    e.printStackTrace();
//                    return ;
                } 
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
        public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("blessingPU");
        public static EntityManager EM = factory.createEntityManager();

}
