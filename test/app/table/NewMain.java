/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
//import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author SEED
 */
public class NewMain {
    public static boolean  password()
    {
        List<String> pass = new LinkedList<>();
        pass.add("admin");
        pass.add("blessing");
        pass.add("alki");
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Masukan password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (okCxl == JOptionPane.OK_OPTION) {
              String password = new String(pf.getPassword());
              System.err.println("You entered: " + password);
              for (String pas : pass) {
                    if (pas.equals(password)) {
                        return true;
                    }
                }
            }
            else if (okCxl == JOptionPane.CANCEL_OPTION) {
                System.exit(okCxl);
        }
            return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        while (!password()) {            
            JOptionPane.showMessageDialog(null, "Password Salah");                        
        }
        Date t = new Date();
                        String backupdirectory ="c:/mybackups/"
                                + t.getDate()+"-"
                                + (t.getMonth() + 1)+"-"
                                + (t.getYear() + 1900)+
                                "/sas";
                        System.out.println("backupdirectory = " + backupdirectory);
                        File f = new File(backupdirectory);
                        f.mkdirs();

    List<String> properties = new ArrayList<String>();
//    Class<?> cl = app.table.Saham.class;
        Class<?> cl = app.table.Barang.class;
        Method[] methods = cl.getMethods();
        List<Method> MethodList = Arrays.asList(methods);                
        for (Method method : MethodList) {
            System.out.println("method.getName() = " + method.getName());
            if (method.getName().startsWith("Print") && !method.getName().equals("Printing") ) {
                properties.add(method.getName());                
            }
        }
        for (String property : properties) {
            System.out.println("property = " + property);
        }
// check all declared fields
    for (Field field : cl.getDeclaredFields()) {        
    // if field is private then look for setters/getters
    if (Modifier.isPrivate(field.getModifiers())) {

        // changing 1st letter to upper case
        String name = field.getName();
        String upperCaseName = name.substring(0, 1).toUpperCase()
                + name.substring(1);
        // and have getter and setter
        try {
            String simpleType = field.getType().getSimpleName();
            //for boolean property methods should be isProperty and setProperty(propertyType)
            if (simpleType.equals("Boolean") || simpleType.equals("boolean")) {
                if ((cl.getDeclaredMethod("is" + upperCaseName) != null)
                        && (cl.getDeclaredMethod("set" + upperCaseName,
                                field.getType()) != null)) {
                    properties.add(name);
                }
            } 
            //for not boolean property methods should be getProperty and setProperty(propertyType)
            else {
                if ((cl.getDeclaredMethod("get" + upperCaseName) != null)
                        && (cl.getDeclaredMethod("set" + upperCaseName,
                                field.getType()) != null)) {
                    properties.add(name);
                }
            }
        } catch (NoSuchMethodException | SecurityException e) {
            // if there is no method nothing bad will happen
        }
    }
}
//                            Tuple.of("Keterangan", "keterangan", d -> d),
    for (String property:properties)
        System.out.println("Tuple.of(\""+property+"\",\""+property+"\""+", fungsi),");    
//        System.out.println("Tuple.of(\""+property+"\",\""+property+"\""+", d-> d),");    
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
