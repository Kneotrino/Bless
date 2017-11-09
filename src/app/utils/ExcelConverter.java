/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
/**
 *
 * @author SEED
 */
public class ExcelConverter {
   public static void ExcelConverter(List<File> cvs, File Excel)
    {
        List<FileInputStream> fis=new LinkedList<>();
        List<DataInputStream> dat = new LinkedList<>();
        HSSFWorkbook hwb = new HSSFWorkbook();

    try
    {
        ArrayList arList=null;
        ArrayList al=null;
        String fName = "D:\\Test.csv";
        String thisLine;
        int count=0;
           for (File cv : cvs) {
            FileInputStream input = new FileInputStream(cv);
            fis.add(input);
            dat.add(new DataInputStream(input));
            }
           for (DataInputStream myInput : dat) {
           int i=0;
           arList = new ArrayList();
           while ((thisLine = myInput.readLine()) != null)
           {
               al = new ArrayList();
               String strar[] = thisLine.split(",");
               for(int j=0;j<strar.length;j++)
               { al.add(strar[j]); }
               arList.add(al);
               i++;
           }            
        }

           try
           {
               for (File cv : cvs) {
//                   System.out.println("cv = " + cv.getName());
                   HSSFSheet sheet = hwb.createSheet(cv.getName());   
                   String formatStr = "";
                    HSSFCellStyle style = hwb.createCellStyle();
                    HSSFDataFormat format = hwb.createDataFormat();
                    style.setDataFormat(format.getFormat(formatStr));
                   FileInputStream input = new FileInputStream(cv);
                   DataInputStream baca = new DataInputStream(input);
                   arList = new ArrayList();
                   int i=0;
                   while ((thisLine = baca.readLine()) != null)
                   {
                   al = new ArrayList();
                   String strar[] = thisLine.split(",");
                   for(int j=0;j<strar.length;j++)
                        { al.add(strar[j]); }
                   arList.add(al);
                   i++;
                   }
                   HSSFRow header = sheet.createRow(0);
                   HSSFCell createCell = header.createCell(0);
                   createCell.setCellValue(cv.getName());
               for(int k=0;k<arList.size();k++)
               {
                   ArrayList ardata = (ArrayList)arList.get(k);
                   HSSFRow row = sheet.createRow((short) 1+k);
                   for(int p=0;p<ardata.size();p++)
                   {
                       HSSFCell cell = row.createCell((short) p);
                       Object dot = ardata.get(p);
                       String data = dot.toString(); 
                       data=data.replaceAll("\"", "");
                       if (isNumeric(data)) {
                           try {
                                Long a = Long.valueOf(data);
                                cell.setCellValue(a);                               
                           } catch (Exception e) {                               
                           }
                       }
//                       cell.setCellStyle(style);
//                       cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                       else if(data.startsWith("=")){
                           data=data.replaceAll("\"", "");
                           data=data.replaceAll("=", "");
                           cell.setCellValue(data);
                       }
                       else if(data.startsWith("\"")){
                           data=data.replaceAll("\"", "");
                           cell.setCellValue(data);
                       }else{
                           data=data.replaceAll("\"", "");
                           cell.setCellValue(data);
                       }

               }
                   }
               }
               FileOutputStream fileOut = new FileOutputStream(Excel);
               hwb.write(fileOut);
               fileOut.close();
               JOptionPane.showMessageDialog(null,"Your excel file has been generated = "+Excel.getCanonicalPath());
               System.out.println("Your excel file has been generated = "+Excel.getCanonicalPath());
           }
           catch ( Exception ex ) {
               ex.printStackTrace();
           } //main method ends
        } 
    catch ( FileNotFoundException ex ) {
           Logger.getLogger(ExcelConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
           Logger.getLogger(ExcelConverter.class.getName()).log(Level.SEVERE, null, ex);
       } finally {
            for (File cv : cvs) {
                try {
                    Files.delete(cv.toPath());
                } catch (Exception ex) {
                }
        }
        
       } //main method ends        
    }
    public static boolean isNumeric(String str)
{
  NumberFormat formatter = NumberFormat.getInstance();
  ParsePosition pos = new ParsePosition(0);
  formatter.parse(str, pos);
  return str.length() == pos.getIndex();
}
    public static void main(String args[]) throws IOException
    {
//            List<File> cvs = new java.util.LinkedList<>();
//            cvs.add(new File("D:\\Test.csv"));
//            ExcelConverter(cvs, new File("Test.xls"));
        System.out.println(isNumeric("2433"));
        System.out.println("300");
        String data = "400";
        System.out.println("data = " + data);

}
}
    
