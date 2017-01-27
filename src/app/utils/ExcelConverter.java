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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author SEED
 */
public class ExcelConverter {
   public static void ExcelConverter(File CSV, File Excel)
    {
        FileInputStream fis=null;
    try
    {
        ArrayList arList=null;
        ArrayList al=null;
        String fName = "D:\\Test.csv";
        String thisLine;
        int count=0;
           fis = new FileInputStream(CSV);
           DataInputStream myInput = new DataInputStream(fis);
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
           try
           {
               HSSFWorkbook hwb = new HSSFWorkbook();
               HSSFSheet sheet = hwb.createSheet("new sheet");
               hwb.createSheet("New sheet2");
               for(int k=0;k<arList.size();k++)
               {
                   ArrayList ardata = (ArrayList)arList.get(k);
                   HSSFRow row = sheet.createRow((short) 0+k);
                   for(int p=0;p<ardata.size();p++)
                   {
                       HSSFCell cell = row.createCell((short) p);
                       String data = ardata.get(p).toString();
                       if(data.startsWith("=")){
                           cell.setCellType(Cell.CELL_TYPE_STRING);
                           data=data.replaceAll("\"", "");
                           data=data.replaceAll("=", "");
                           cell.setCellValue(data);
                       }
                       else if(data.startsWith("\"")){
                           data=data.replaceAll("\"", "");
                           cell.setCellType(Cell.CELL_TYPE_STRING);
                           cell.setCellValue(data);
                       }else{
                           data=data.replaceAll("\"", "");
                           cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                           cell.setCellValue(data);
                       }
//*/
// cell.setCellValue(ardata.get(p).toString());
                   }
//        System.out.println();
               }
               FileOutputStream fileOut = new FileOutputStream(Excel);
               System.out.println("fileOut = " + fileOut);
               hwb.write(fileOut);
               fileOut.close();
               System.out.println("Your excel file has been generated");
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
           try {
               fis.close();
           } catch (IOException ex) {
               Logger.getLogger(ExcelConverter.class.getName()).log(Level.SEVERE, null, ex);
           }
       } //main method ends        
    }
    
    public static void main(String args[]) throws IOException
    {
            List<File> cvs = new java.util.LinkedList<>();
            cvs.add(new File("D:\\Test.csv"));
            cvs.add(new File("D:\\Test2.csv"));            
//            ExcelConverter(new File("D:\\Test.csv"), new File("D:\\Test.xls"));
//            ExcelConverter(new File("D:\\Test.csv"), new File("D:\\Test.xls"));

}
}
    
