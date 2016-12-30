/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
//        Object b = new app.table.Trips();
//        Field[] fields = b.getClass().getDeclaredFields();
//            System.out.print(" @app.ListUrutan({");
//        for (Field field : fields) {
//            System.out.print(",\"" + field.getName()+"\"");
//        }
//            System.out.print(" })\n");
            List<app.table.Debitur> dp = new ArrayList<>();
            LinkedHashMap<String,String> nilai;
            nilai = new LinkedHashMap<>();            
            nilai.put("REF 5", "getDebiturId");
            nilai.put("REF 3", "getDebiturId");
            nilai.put("REF 1", "getDebiturId");
            nilai.put("REF 2", "getDebiturId");
            nilai.put("REF 4", "getDebiturId");
            System.out.println(nilai);
    }
    
}
