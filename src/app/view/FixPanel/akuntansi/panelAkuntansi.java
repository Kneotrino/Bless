/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.view.FixPanel.akuntansi;

//import app.table.Akuntansi;
import app.table.Bank;
import app.table.Bpkbtitipan;
import app.table.Hutang;
import app.table.KeuanganMobil;
import app.table.Laporan;
import app.table.Mobil;
import app.table.Rental;
import static app.utils.ExcelConverter.ExcelConverter;
import com.joobar.csvbless.CSVUtil;
import com.joobar.csvbless.WriteStep;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javaslang.Tuple;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author SEED
 */
public class panelAkuntansi extends JPanel {
    private java.util.List<Akun> AkuntansiList = new ArrayList<>();
    private java.util.List<Akun> LabaList = new ArrayList<>();
    private java.util.List<Akun> LaporanPenyesuaian = new ArrayList<>();
    private List<Akun> ProfitMobil = new ArrayList<>();
    private List<Akun> ProfitJasa = new ArrayList<>();

    public List<Akun> getProfitJasa() {
        return ProfitJasa;
    }
    private List<Akun> Open = new ArrayList<>();
    private List<Akun> Closed = new ArrayList<>();

    public List<Akun> getOpen() {
        return Open;
    }

    public List<Akun> getClosed() {
        return Closed;
    }

    /**
     * Get the value of ProfitMobil
     *
     * @return the value of ProfitMobil
     */
    public List<Akun> getProfitMobil() {
        return ProfitMobil;
    }
    /**
     * Set the value of ProfitMobil
     *
     * @param ProfitMobil new value of ProfitMobil
     */
    public void setProfitMobil(List<Akun> ProfitMobil) {
        this.ProfitMobil = ProfitMobil;
    }

    public List<Akun> getLaporanPenyesuaian() {
        return LaporanPenyesuaian;
    }

    public void setLaporanPenyesuaian(List<Akun> LaporanPenyesuaian) {
        this.LaporanPenyesuaian = LaporanPenyesuaian;
    }

    public List<Akun> getAkuntansiList() {
        return AkuntansiList;
    }

    public void setAkuntansiList(List<Akun> AkuntansiList) {
        this.AkuntansiList = AkuntansiList;
    }
    public List getList(Class kelas)
    {
        String que = "SELECT en FROM " + kelas.getSimpleName() + " en "
                + "where en.tanggal BETWEEN :startDate AND :endDate"
                ;
        TypedQuery createQuery = entityManager.createQuery(que, kelas)
                .setParameter("startDate", awalBulan, TemporalType.TIMESTAMP)
                .setParameter("endDate", akhirBulan, TemporalType.TIMESTAMP)  
                ;
        return createQuery.getResultList();
    }
    private BigInteger sumAll(List<? extends Laporan> laporanList)
    {
        BigInteger temp = new BigInteger("0");
        for (Laporan list : laporanList) {
            temp = temp.add(list.getJumlah());
        }
        return temp;
    }
            Calendar cal = Calendar.getInstance();
            Date akhirBulan;
            Date awalBulan;
    public panelAkuntansi() {
        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();        
                 Query query = entityManager.createQuery("SELECT l FROM Laporan l");
                 java.util.List<app.table.Laporan> data = query.getResultList();
                 data.forEach((laporan) -> { entityManager.refresh(laporan);});
                 Query bankQ = entityManager.createQuery("SELECT b From Bank b ORDER BY b");
                 List<Bank> result = bankQ.getResultList();
                 result.forEach(a -> entityManager.refresh(a));
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));            
        akhirBulan = cal.getTime();
        awalBulan = new Date(akhirBulan.getYear(), akhirBulan.getMonth(), 0);
        System.out.println("awalBulan = " + awalBulan);
        System.out.println("akhirBulan = " + akhirBulan);
        Akun total = new Akun().setAkun("Neraca Total");
        int X =1;
        List<app.table.Bank> list = entityManager.createQuery("SELECT en FROM Bank en", app.table.Bank.class).getResultList();       
        Akun Kas = new Akun(X++)
                .setAkun("Gabungan Kas");                
        list.forEach((bank) -> {
            Kas.addPemasukan(bank.getFoo());
        });
        AkuntansiList.add(Kas);            
         Akun Modal = new Akun(X++)
                 .setAkun("Modal")
                 .setPengeluaran(sumAll(getList(app.table.Modal.class)));
         BigInteger mod = BigInteger.ZERO;
         List<Laporan> rangkuman = entityManager.createQuery(
                "SELECT l FROM Laporan l where l.tanggal < :endDate")
                .setParameter("endDate", awalBulan, TemporalType.DATE)  
                .getResultList();
         for (Laporan laporan : rangkuman) {
             mod = mod.add(laporan.getPemasukan());
             mod = mod.subtract(laporan.getPengeluaran());
        }
         Akun ModalSebelumnya = new Akun(X++)
                 .setAkun("Modal Sebelumnya")
                 .setPengeluaran(mod)
                 ;
         Akun Prive = new Akun(X++)
                 .setAkun("Penarikan Modal/Prive")
                 .setPemasukan(sumAll(getList(app.table.Prive.class)));
         Akun Hutang = new Akun(X++)
                 .setAkun("Hutang")
                 .setPengeluaran(sumAll(getList(app.table.BayarPihutangPemasukan.class)));
         Akun Operasional = new Akun(X++)
                 .setAkun("Beban Operasional")
                 .setPemasukan(sumAll(getList(app.table.Pengeluaran.class)))
                 ;
         Akun Pegawai = new Akun(X++)
                 .setAkun("Beban Gaji Pegawai")
                 .setPemasukan(sumAll(getList(app.table.Pegawaigaji.class)));
         Akun Asset = new Akun(X++)
                 .setAkun("Beban Biaya Asset")
                 .setPemasukan(sumAll(getList(app.table.Asset.class)));                  
        Akun Pemasukan = new Akun(X++)
                 .setAkun("Pemasukan Lainnya")
                .setPengeluaran(sumAll(getList(app.table.Pemasukan.class)))
                ;
        Akun Perjalanan = new Akun(X++)
                .setAkun("Beban Perjalanan")
                .setPemasukan(sumAll(getList(app.table.PerjalananPengeluaran.class)));
                ;
        Akun Rental = new Akun(X++)
                .setAkun("Pemasukan Rental")
                .setPengeluaran(sumAll(getList(app.table.BayarRentalPemasukan.class)))
                ;
        Akun pengeluaranRental = new Akun(X++)
                .setAkun("Beban Rental")
                .setPemasukan(sumAll(getList(app.table.BayarRentalPenngeluaran.class)));        
        Akun Jasa = new Akun()
                .setAkun("Pemasukan Jasa")
                .setPengeluaran(sumAll(getList(app.table.BayarJasaPemasukan.class)));
        Akun  bebanJasa = new Akun()
                .setAkun("Beban Jasa")
                .setPemasukan(sumAll(getList(app.table.BayarJasaPengeluaran.class)));
        Akun  bebanPeminjaman = new Akun()
                .setAkun("Beban Peminjaman/Piutang")
                .setPemasukan(sumAll(getList(app.table.BayarhutangPengeluaran.class)));
        Akun  Peminjaman = new Akun()
                .setAkun("Pemasukan Peminjaman")
                .setPengeluaran(sumAll(getList(app.table.BayarhutangPemasukan.class)));
        Akun  bebanHutang = new Akun()
                .setAkun("Pelunasan Hutang ")
                .setPemasukan(sumAll(getList(app.table.BayarPihutangPengeluaran.class)));
        Akun  sisaHutang = new Akun()
                .setAkun("Beban Hutang ")                
                ;
        Akun Mobil = new Akun()
                .setAkun("Pemasukan Mobil")
                .setPengeluaran(sumAll(getList(app.table.MobilPemasukan.class)));
        Akun bebanMobil = new Akun()
                .setAkun("Beban Mobil")
                .setPemasukan(sumAll(getList(app.table.MobilPengeluaran.class)));
        Akun bebanBunga = new Akun()
                .setAkun("Beban Bunga")
                .setPemasukan(sumAll(getList(app.table.BayarPihutangBunga.class)));
        Akun PembagianLaba = new Akun()
                .setAkun("Pembagian Laba")
                .setPemasukan(sumAll(getList(app.table.pembagianLaba.class)));
        BigInteger bayarhutang = bebanHutang.getPemasukan();
        BigInteger jumlahhutang = Hutang.getPengeluaran();
        int res = jumlahhutang.compareTo(bayarhutang);
        if (res == 0) {
        } else if (res==-1) {
            bayarhutang = bayarhutang.subtract(jumlahhutang);
            sisaHutang.setPemasukan(bayarhutang);
        } else if (res == 1) {
            jumlahhutang = jumlahhutang.subtract(bayarhutang);
            sisaHutang.setPengeluaran(jumlahhutang);
        }
        Akun Bayasewa = new Akun()
                .setAkun("Beban Sewa Ruko")
                .setPemasukan(sumAll(getList(app.table.Bayarsewa.class)));
        AkuntansiList.add(ModalSebelumnya);
        AkuntansiList.add(Modal.subPengeluaran(Prive.getPemasukan()));
        AkuntansiList.add(PembagianLaba);
        AkuntansiList.add(Mobil);        
        AkuntansiList.add(Rental);        
        AkuntansiList.add(Jasa);        
        AkuntansiList.add(Pemasukan);        
        AkuntansiList.add(Peminjaman);        
        LabaList.add(Mobil);
        LabaList.add(Rental);
        LabaList.add(Jasa);
        LabaList.add(Pemasukan);
        LabaList.add(Peminjaman);
        //Akun Pengeluaran        
        AkuntansiList.add(sisaHutang);
        AkuntansiList.add(bebanBunga);
        AkuntansiList.add(bebanMobil);
        AkuntansiList.add(bebanPeminjaman);
        AkuntansiList.add(pengeluaranRental);
        AkuntansiList.add(bebanJasa);
        AkuntansiList.add(Perjalanan);
        AkuntansiList.add(Operasional);
        AkuntansiList.add(Pegawai);                    
        AkuntansiList.add(Asset);
        AkuntansiList.add(Bayasewa);
        LabaList.add(bebanBunga);
        LabaList.add(sisaHutang);
        LabaList.add(bebanMobil);
        LabaList.add(bebanPeminjaman);
        LabaList.add(pengeluaranRental);
        LabaList.add(bebanJasa);
        LabaList.add(Perjalanan);
        LabaList.add(Operasional);
        LabaList.add(Pegawai);                    
        LabaList.add(Asset);
        LabaList.add(Bayasewa);

        X = 1;
        total.setPemasukan(BigInteger.ZERO);
        total.setPengeluaran(BigInteger.ZERO);
        for (Akun akun : AkuntansiList) {
            akun.setNomor(X++);
            total.addPengeluaran(akun.getPengeluaran());
            total.addPemasukan(akun.getPemasukan());
        }
        
        Akun laba = new Akun()
                .setAkun("Total");
        for (Akun akun : LabaList) {
            laba.addPemasukan(akun.getPemasukan());
            laba.addPengeluaran(akun.getPengeluaran());
        }
        LabaList.add(laba);
        Akun kosong = new Akun()
                .setAkun("")
                .setPengeluaran(null)
                .setPemasukan(null);
        LabaList.add( kosong );
        LabaList.add(
                new Akun()
                .setAkun("Total Pemasukan")
                .setPengeluaran(laba.getPengeluaran())
        );
        LabaList.add(
                new Akun()
                .setAkun("Total Pengeluaran")
                .setPemasukan(laba.getPemasukan())
        );
        LabaList.add( kosong );
        Akun Profit = new Akun()
                .setAkun("Profit")
                .setPengeluaran(laba.getPengeluaran())
                .subPengeluaran(laba.getPemasukan());
        Akun ProfitBersih = new Akun()
                .setAkun("Profit Bersih")
                .setPengeluaran(laba.getPengeluaran())
                .subPengeluaran(laba.getPemasukan());
        Akun LabaTahan = new Akun()
                .setAkun("25% Laba yang di tahan")
                .setPengeluaran(laba.getPengeluaran())
                .subPengeluaran(laba.getPemasukan())
                .DividePengeluaran();
//        ProfitBersih.subPengeluaran(LabaTahan.getPengeluaran());
        ProfitBersih.subPengeluaran(PembagianLaba.getPemasukan());
//        LabaList.add(
//                Profit
//        );
        LabaList.add(
                PembagianLaba
        );
//        LabaList.add(
//              LabaTahan   
//        );        
        LabaList.add(
              ProfitBersih   
        );        
        AkuntansiList.add(total);
        //Penyesuaian
//        BigInteger totalProfit = BigInteger.ZERO;
        Akun totalProfit = new Akun()
                .setAkun("Total " + Calendar.getInstance().get(Calendar.YEAR));
        for (int i = 1; i < 13; i++) {
            Akun temp = new Akun(i);
            LaporanPenyesuaian.add( ProfitBulanan(temp) );
            totalProfit.addPemasukan( temp.getPemasukan());
            totalProfit.addPengeluaran(temp.getPengeluaran());
        }
        LaporanPenyesuaian.add(totalProfit);
        TypedQuery createQuery = entityManager.createQuery("SELECT m FROM Mobil m", app.table.Mobil.class);
        TypedQuery createQuery1 = entityManager.createQuery("SELECT m FROM Bpkbtitipan m", app.table.Bpkbtitipan.class);
        TypedQuery createQuery2 = entityManager.createQuery("SELECT m FROM Rental m", app.table.Rental.class);
        TypedQuery createQuery3 = entityManager.createQuery("SELECT m FROM Hutang m", app.table.Hutang.class);
        List<Mobil> mobilList = createQuery.getResultList();
        List<Bpkbtitipan> bpkb = createQuery1.getResultList();
        List<Rental> rental = createQuery2.getResultList();
        List<Hutang> HutangList = createQuery3.getResultList();
        
        int i = 0;
        Akun TotalMobil = new Akun()
                .setAkun("Total Profit Mobil");
        TotalMobil.setKeterangan("----");
        for (Mobil mobil : mobilList) {
            i++;
            BigInteger pemasukan = BigInteger.ZERO;
            BigInteger pengeluaran = BigInteger.ZERO;
            List<KeuanganMobil> KM = mobil.getKeuanganMobil2();
            for (KeuanganMobil k : KM) {
                pemasukan = pemasukan.add(k.getPemasukan());
                pengeluaran = pengeluaran.add(k.getPengeluaran());
            }
            Akun veh = new Akun(i)
                    .setAkun(
                            mobil+ " " +
                            mobil.getMerk()+ " " +
                            mobil.getType()+ " " +
                            mobil.getWarna()+ " " +
                            mobil.getTahun()+ " " 
                    )
                    .setPemasukan(pemasukan)
                    .setPengeluaran(pengeluaran)
                    ;
            veh.setKeterangan(mobil.getStatusMobil());
            TotalMobil.addPemasukan(pemasukan)
                    .addPengeluaran(pengeluaran);
            ProfitMobil.add(veh);
        }
            ProfitMobil.add(TotalMobil);
            Closed.addAll(ProfitMobil);
            String op = "OPEN";
            String cl = "CLOSE";
            for (Bpkbtitipan b : bpkb) {
            Akun temp = new Akun()
                    .setAkun(b.toString());
            temp.setKeterangan(b.getLaba());
            temp.setPemasukan(b.gettotalPemasukan());
            temp.setPengeluaran(b.gettotalPengeluaran());
            ProfitJasa.add(temp);
        }
            for (Rental r : rental) {
            Akun temp = new Akun()
                    .setAkun(r.toString());
            temp.setKeterangan(r.getLABA());
            temp.setPemasukan(r.gettotalPemasukan());
            temp.setPengeluaran(r.gettotalPengeluaran());
            ProfitJasa.add(temp);            
        }
            for (Hutang r : HutangList) {
            Akun temp = new Akun()
                    .setAkun(r.toString());
            temp.setKeterangan(r.getLABA());
            temp.setPemasukan(r.gettotalPemasukan());
            temp.setPengeluaran(r.gettotalPengeluaran());
            ProfitJasa.add(temp);            
        }
            Akun ProfitJasaTotal = new Akun()
                    .setAkun("Profit Jasa Total");
                    ProfitJasaTotal.setKeterangan("");
            for (Akun akun : ProfitJasa) {
            ProfitJasaTotal.addPemasukan(akun.getPemasukan());
            ProfitJasaTotal.addPengeluaran(akun.getPengeluaran());
        }
            ProfitJasa.add(ProfitJasaTotal);
            Closed.addAll(ProfitJasa);
            Closed.removeIf(a -> !a.getKeterangan().equals(cl));
            Open.addAll(ProfitMobil);
            Open.addAll(ProfitJasa);
            Open.removeIf(a -> !a.getKeterangan().equals(op));
            Akun TO = new Akun();
                TO.setAkun("Total Open ");
                for (Akun akun : Open) {
                    TO.addPemasukan(akun.getPemasukan());
                    TO.addPengeluaran(akun.getPengeluaran());
                }
            Akun TC = new Akun();
                TC.setAkun("Total Close ");
                for (Akun akun : Closed) {
                    TC.addPemasukan(akun.getPemasukan());
                    TC.addPengeluaran(akun.getPengeluaran());
        }
            Open.add(TO);
            Closed.add(TC);
            Akun LabaMobilTahan = new Akun()
                    .setPemasukan(TC.getProfit().divide(new BigInteger("4")))
                    ;
            LabaMobilTahan.setAkun("Laba di tahan 25 %");
            Akun BayarRuko = new Akun()
                    .setAkun("Bayar Ruko Tahun " + Calendar.getInstance().get(Calendar.YEAR))
                    .setPengeluaran(sumAll(getYearList(app.table.Bayarsewa.class)));

            Closed.add(new Akun());
            Closed.add(LabaMobilTahan);
            Closed.add(BayarRuko);
            Closed.add(new Akun()
                    .setAkun("Sisa Laba Di tahan " + Calendar.getInstance().get(Calendar.YEAR))
                    .setPemasukan(LabaMobilTahan.getPemasukan())
                    .setPengeluaran(BayarRuko.getPengeluaran())
            );
                    
            
        initComponents();
    }
    public String getMonth(int month) {
    return new DateFormatSymbols().getMonths()[month-1];
}
    public Akun ProfitBulanan(Akun profit)
    {
      profit.setAkun("Bulan "+profit.getNomor() 
              + " "+getMonth(profit.getNomor()) + " " + Calendar.getInstance().get(Calendar.YEAR));
      BigInteger pemasukan = BigInteger.ZERO;
      BigInteger pengeluaran = BigInteger.ZERO;
        //add pemasukan
      pemasukan = 
      pemasukan.add(sumAll(getMonthList(app.table.MobilPemasukan.class,profit.getNomor())));
      pemasukan = 
      pemasukan.add(sumAll(getMonthList(app.table.BayarRentalPemasukan.class,profit.getNomor())));
      pemasukan = 
      pemasukan.add(sumAll(getMonthList(app.table.BayarJasaPemasukan.class,profit.getNomor())));
      pemasukan = 
      pemasukan.add(sumAll(getMonthList(app.table.Pemasukan.class,profit.getNomor())));
      pemasukan = 
      pemasukan.add(sumAll(getMonthList(app.table.BayarhutangPemasukan.class,profit.getNomor())));
      pemasukan = 
              pemasukan.add(sumAll(getMonthList(app.table.BayarPihutangPemasukan.class,profit.getNomor())));
      //add pengeluaran
      pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.MobilPengeluaran.class,profit.getNomor())));
      pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.BayarPihutangPengeluaran.class,profit.getNomor())));
      pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.BayarhutangPengeluaran.class,profit.getNomor())));
      pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.BayarRentalPenngeluaran.class,profit.getNomor())));
      pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.BayarJasaPengeluaran.class,profit.getNomor())));
      pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.PerjalananPengeluaran.class,profit.getNomor())));
     pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.Pengeluaran.class,profit.getNomor())));
      pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.Pegawaigaji.class,profit.getNomor())));
      pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.Asset.class,profit.getNomor())));
      pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.pembagianLaba.class,profit.getNomor())));      
      pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.BayarPihutangBunga.class,profit.getNomor())));      
      pengeluaran = 
              pengeluaran.add(sumAll(getMonthList(app.table.Bayarsewa.class,profit.getNomor())));      
      //set pemasukan dan pengeluaran
      profit.setPemasukan(pemasukan);
      profit.setPengeluaran(pengeluaran);
      getMonthList(Laporan.class, profit.getNomor()-1);
      return profit;
    }
    
    public List getMonthList(Class kelas,int Month)
    {
        String que = "SELECT en FROM " + kelas.getSimpleName() + " en "
                + "where en.tanggal BETWEEN :startDate AND :endDate"
                ;
       Date bulanAwal = new Date(akhirBulan.getYear(), Month-1, 0);
       Date bulanAkhir = new Date(akhirBulan.getYear(), Month, 0);
       TypedQuery createQuery = entityManager.createQuery(que, kelas)
                .setParameter("startDate", bulanAwal, TemporalType.TIMESTAMP)
                .setParameter("endDate", bulanAkhir, TemporalType.TIMESTAMP)  
                ;
        return createQuery.getResultList();
    }
    public List getYearList(Class kelas)
    {
        String que = "SELECT en FROM " + kelas.getSimpleName() + " en "
                + "where en.tanggal BETWEEN :startDate AND :endDate"
                ;
       Date bulanAwal = new Date(akhirBulan.getYear(), 0, 0);
        System.out.println("Tahun Awal = " + bulanAwal);
       Date bulanAkhir = new Date(akhirBulan.getYear()+1, 0, 0);
        System.out.println("Tahun Akhir = " + bulanAkhir);
       TypedQuery createQuery = entityManager.createQuery(que, kelas)
                .setParameter("startDate", bulanAwal, TemporalType.TIMESTAMP)
                .setParameter("endDate", bulanAkhir, TemporalType.TIMESTAMP)  
                ;
        return createQuery.getResultList();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("blessingPU").createEntityManager();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jFileChooser1 = new javax.swing.JFileChooser();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();

        FormListener formListener = new FormListener();

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LAPORAN PERUBAHAN MODAL", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable2.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable3.setAutoCreateRowSorter(true);
        jTable3.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NERACA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable2.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable4.setAutoCreateRowSorter(true);
        jTable4.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jFileChooser1.setApproveButtonText("Print");
        jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        jFileChooser1.addActionListener(formListener);

        setPreferredSize(new java.awt.Dimension(1500, 1000));
        setLayout(new java.awt.BorderLayout());

        jButton1.setText("Print");
        jButton1.addActionListener(formListener);
        add(jButton1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(317, 400));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(467, 800));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NERACA SALDO BULAN INI", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable1.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${akuntansiList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomor}"));
        columnBinding.setColumnName("No");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${akun}"));
        columnBinding.setColumnName("Akun");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemasukan}"));
        columnBinding.setColumnName("Aktiva(Harta + Pengeluaran)");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pasiva(Hutang + Modal + Pemasukan)");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jTabbedPane1.addTab("NERACA SALDO BULAN INI", jScrollPane1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TABEL LABA/RUGI BULAN INI", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable2.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable2.setAutoCreateRowSorter(true);
        jTable2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${labaList}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable2);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${akun}"));
        columnBinding.setColumnName("Akun");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pemasukan");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemasukan}"));
        columnBinding.setColumnName("Pengeluaran");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(jTable2);

        jTabbedPane1.addTab("LABA/RUGI", jScrollPane2);

        jScrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Laporan Profit Bersih Bulanan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable5.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable5.setAutoCreateRowSorter(true);
        jTable5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${laporanPenyesuaian}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable5);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${akun}"));
        columnBinding.setColumnName("Akun");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${profit}"));
        columnBinding.setColumnName("Profit");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pengeluaran");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemasukan}"));
        columnBinding.setColumnName("Pemasukan");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane6.setViewportView(jTable5);

        jTabbedPane1.addTab("LAPORAN PROFIT BERSIH TAHUNAN", jScrollPane6);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "STATUS SEMUA MOBIL", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable6.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable6.setAutoCreateRowSorter(true);
        jTable6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${profitMobil}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable6);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomor}"));
        columnBinding.setColumnName("Nomor");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${akun}"));
        columnBinding.setColumnName("Mobil");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${profit}"));
        columnBinding.setColumnName("Profit");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pengeluaran");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemasukan}"));
        columnBinding.setColumnName("Pemasukan");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane5.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jTabbedPane1.addTab("STATUS MOBIL", jScrollPane5);

        jScrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "STATUS SEMUA JASA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable9.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable9.setAutoCreateRowSorter(true);
        jTable9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${profitJasa}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable9);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomor}"));
        columnBinding.setColumnName("Nomor");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${akun}"));
        columnBinding.setColumnName("Akun");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${profit}"));
        columnBinding.setColumnName("Profit");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemasukan}"));
        columnBinding.setColumnName("Pemasukan");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pengeluaran");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane9.setViewportView(jTable9);
        if (jTable9.getColumnModel().getColumnCount() > 0) {
            jTable9.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jTabbedPane1.addTab("STATUS JASA", jScrollPane9);

        jScrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "STATUS OPEN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable7.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable7.setAutoCreateRowSorter(true);
        jTable7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${open}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable7);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomor}"));
        columnBinding.setColumnName("Nomor");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${akun}"));
        columnBinding.setColumnName("Mobil");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${profit}"));
        columnBinding.setColumnName("Profit");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pengeluaran");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemasukan}"));
        columnBinding.setColumnName("Pemasukan");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane7.setViewportView(jTable7);
        if (jTable7.getColumnModel().getColumnCount() > 0) {
            jTable7.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jTabbedPane1.addTab("PROFIT OPEN", jScrollPane7);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "STATUS CLOSED", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        jTable8.setDefaultRenderer(java.math.BigInteger.class, new app.utils.NominalRender());
        jTable8.setAutoCreateRowSorter(true);
        jTable8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${closed}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable8);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nomor}"));
        columnBinding.setColumnName("No");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${akun}"));
        columnBinding.setColumnName("Mobil");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${keterangan}"));
        columnBinding.setColumnName("Keterangan");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${profit}"));
        columnBinding.setColumnName("Profit");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pengeluaran}"));
        columnBinding.setColumnName("Pengeluaran");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${pemasukan}"));
        columnBinding.setColumnName("Pemasukan");
        columnBinding.setColumnClass(java.math.BigInteger.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane8.setViewportView(jTable8);
        if (jTable8.getColumnModel().getColumnCount() > 0) {
            jTable8.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jTabbedPane1.addTab("PROFIT CLOSE", jScrollPane8);

        jPanel1.add(jTabbedPane1);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButton1) {
                panelAkuntansi.this.jButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == jFileChooser1) {
                panelAkuntansi.this.jFileChooser1ActionPerformed(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jFileChooser1.showSaveDialog(jPanel1);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        File root = jFileChooser1.getSelectedFile();
        List<File> cvs = new java.util.LinkedList<>();
        System.out.println("root = " + root);
        Date p = new Date();
        final SimpleDateFormat formator = new SimpleDateFormat("dd-MM-yyyy");
        File akun = new File(root, "Neraca "+formator.format(p)+".CSV");
        File laba = new File(root, "Laba-Rugi "+formator.format(p)+".CSV");
        File lap = new File(root, "Laporan profit "+formator.format(p)+".CSV");
        File mob = new File(root, "Mobil profit "+formator.format(p)+".CSV");
        File jas = new File(root, "Jasa profit "+formator.format(p)+".CSV");
        File mob1 = new File(root, "Open Profit "+formator.format(p)+".CSV");
        File mob2 = new File(root, "Close Profit "+formator.format(p)+".CSV");
        cvs.add(akun);
        cvs.add(laba);
        cvs.add(lap);
        cvs.add(mob);
        cvs.add(jas);
        cvs.add(mob1);
        cvs.add(mob2);
        
        List a = AkuntansiList;
        List b = LabaList;
        List c = LaporanPenyesuaian;
        List e = ProfitMobil;
        List h = Open;
        List g = Closed;
        List j = ProfitJasa;
        final DecimalFormat IDR = new DecimalFormat("###0");              
        Function f = d -> d==null?"0":IDR.format(d);
        WriteStep AkunPrinter = CSVUtil.of(akun)
                .type(app.view.FixPanel.akuntansi.Akun.class)
                .properties(
                        Tuple.of("No", "nomor", null),
                        Tuple.of("Akun", "akun", null),
                        Tuple.of("Aktiva(Harta + Pengeluaran)", "pemasukan", f),
                        Tuple.of("Pasiva(Hutang + Modal + Pemasukan)", "pengeluaran", f)
                )
                .dataList(a);        
        WriteStep LabaPrinter = CSVUtil.of(laba)
                .type(app.view.FixPanel.akuntansi.Akun.class)
                .properties(
                        Tuple.of("No", "nomor", null),
                        Tuple.of("Akun", "akun", null),
                        Tuple.of("Pemasukan", "pengeluaran", f),
                        Tuple.of("Pengeluaran", "pemasukan", f)
                )
                .dataList(b);        
        WriteStep Simpulan = CSVUtil.of(lap)
                .type(app.view.FixPanel.akuntansi.Akun.class)
                .properties(
                        Tuple.of("No", "nomor", null),
                        Tuple.of("Akun", "akun", null),
                        Tuple.of("Pengeluaran", "pengeluaran", f),
                        Tuple.of("Pemasukan", "pemasukan", f),
                        Tuple.of("Profit", "profit", f)
                )
                .dataList(c);        
        WriteStep MobilPrint = CSVUtil.of(mob)
                .type(app.view.FixPanel.akuntansi.Akun.class)
                .properties(
                        Tuple.of("No", "nomor", null),
                        Tuple.of("Mobil", "akun", null),
                        Tuple.of("Status", "keterangan", null),
                        Tuple.of("Pengeluaran", "pengeluaran", f),
                        Tuple.of("Pengeluaran", "pemasukan", f),
                        Tuple.of("Profit", "profit", f)
                )
                .dataList(e);    
        
        WriteStep MobilPrint1 = CSVUtil.of(mob1)
                .type(app.view.FixPanel.akuntansi.Akun.class)
                .properties(
                        Tuple.of("No", "nomor", null),
                        Tuple.of("Mobil", "akun", null),
                        Tuple.of("Status", "keterangan", null),
                        Tuple.of("Pengeluaran", "pengeluaran", f),
                        Tuple.of("Pengeluaran", "pemasukan", f),
                        Tuple.of("Profit", "profit", f)
                )
                .dataList(h);        
        WriteStep MobilPrint2 = CSVUtil.of(mob2)
                .type(app.view.FixPanel.akuntansi.Akun.class)
                .properties(
                        Tuple.of("No", "nomor", null),
                        Tuple.of("Mobil", "akun", null),
                        Tuple.of("Status", "keterangan", null),
                        Tuple.of("Pengeluaran", "pengeluaran", f),
                        Tuple.of("Pengeluaran", "pemasukan", f),
                        Tuple.of("Profit", "profit", f)
                )
                .dataList(h);        
        WriteStep JasaPrint = CSVUtil.of(jas)
                .type(app.view.FixPanel.akuntansi.Akun.class)
                .properties(
                        Tuple.of("No", "nomor", null),
                        Tuple.of("Mobil", "akun", null),
                        Tuple.of("Status", "keterangan", null),
                        Tuple.of("Pengeluaran", "pengeluaran", f),
                        Tuple.of("Pengeluaran", "pemasukan", f),
                        Tuple.of("Profit", "profit", f)
                )
                .dataList(j);        
        try {
                 AkunPrinter.write();
                 LabaPrinter.write();
                 Simpulan.write();
                 MobilPrint.write();
                 MobilPrint1.write();
                 MobilPrint2.write();
                 JasaPrint.write();
                 
         
        } catch (Exception ex) {
            javax
                    .swing
                    .JOptionPane.showMessageDialog(null, 
                    "Gagal Print, Karena file sementara terbuka\n"+ex);     
            
        }
            int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
                    "Berhasil Print apakah anada ingin membuka File?"
            );
            Desktop desktop = Desktop.getDesktop();
            try {
                File T = new File(root,"Akuntasi-Laba-Rugi-Profit.xls");
                ExcelConverter(cvs, new File(root,"Akuntasi-Laba-Rugi-Profit.xls"));
                if(!Desktop.isDesktopSupported()){
                        System.out.println("Desktop is not supported");
                        return;
                }
                if (confirm == 0) {
                    if(T.exists()) desktop.open(T);
//                    if(laba.exists()) desktop.open(laba);                    
//                    if(lap.exists()) desktop.open(lap);                    
                }
            } catch (Exception ex) {
                    Logger.getLogger(panelAkuntansi.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("end");
        // TODO add your handling code here:
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    public List<Akun> getLabaList() {
        return LabaList;
    }

    public void setLabaList(List<Akun> LabaList) {
        this.LabaList = LabaList;
    }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JButton jButton1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(panelAkuntansi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(panelAkuntansi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(panelAkuntansi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(panelAkuntansi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(() -> {
                System.out.println("app.view.FixPanel.akuntansi.panelAkuntansi.main()");
                javax.swing.JFrame jDialog1 = new JFrame("Akuntasi");
                try {
                jDialog1.setSize(1200, 700);
                jDialog1.setLocationRelativeTo(null);
                jDialog1.getContentPane().add(new panelAkuntansi());
                jDialog1.show();
                jDialog1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } catch (Exception e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null, e);
                jDialog1 = null;
//                System.exit(100);
            }
        });
    }
    
}
