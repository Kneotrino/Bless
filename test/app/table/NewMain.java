/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.PropertyUtilsBean;

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
        Object b = new app.table.Investor();
        Field[] fields = b.getClass().getDeclaredFields();
            System.out.print(" @app.ListUrutan({");
        for (Field field : fields) {
            System.out.print(",\"" + field.getName()+"\"");
        }
//            System.out.print(" })\n");
//            List<app.table.Debitur> dp = new ArrayList<>();
//            LinkedHashMap<String,String> nilai;
//            nilai = new LinkedHashMap<>();            
//            nilai.put("REF 5", "getDebiturId");
//            nilai.put("REF 3", "getDebiturId");
//            nilai.put("REF 1", "getDebiturId");
//            nilai.put("REF 2", "getDebiturId");
//            nilai.put("REF 4", "getDebiturId");
//            System.out.println(nilai);
//        NewMain.createStringQuery(null, "");
    }
    
    public static String createStringQuery(Class<?> entClazz, String keyword) {
        Bpkb mobil = new Bpkb();
        mobil.setStatus("Foobar");
        PropertyUtilsBean util = new PropertyUtilsBean();
        Map<String, Object> describe = null;
        try {
            describe = util.describe(mobil);
            Set<String> keys = describe.keySet();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        describe.remove("class");
        ArrayList<String> keys = new ArrayList<>(describe.keySet());
        String clsName = entClazz.getSimpleName();
        String qstr = "SELECT v FROM " + clsName +
                        "WHERE ";
        StringBuilder builder = new StringBuilder();
        builder = builder.append("SELECT v FROM ")
                .append(clsName)
                .append(" v WHERE ");
        for (int i = 0; i < keys.size(); i++) {
            String prop = keys.get(i);
            builder = builder.append("v.")
                    .append(prop)
                    .append(" CONTAINS ")
                    .append("%")
                    .append(keyword)
                    .append("%");
        }
        
        return null;
    }
}
