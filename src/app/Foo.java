/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.utils.MyTextField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static javaslang.API.*;
import javaslang.Tuple;
import javaslang.Tuple3;
import javaslang.Tuple4;
import javaslang.collection.HashSet;
import javaslang.collection.List;
import javaslang.collection.Set;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.undo.UndoManager;

/**
 *
 * @author SEED
 */
public class Foo {
    
    private String createLabelName(String str) {
        String[] r = str.split("(?=\\p{Upper})");
        return String.join(" ", Arrays.stream(r).collect(Collectors.toList()));
    }
    
    private String getName(Method method) {
        String name = method.getName();
        int n = name.length();
        String labName = null;
        String tmp = null;
        if (name.startsWith("is")) {
            tmp = name.substring(2, n);
        } else {
            tmp = name.substring(3, n);
        }
        labName = createLabelName(tmp);
        return labName;
    }
    
    public Vector<Field> getAllFields(Class clazz) {
        return getAllFieldsRec(clazz, new Vector<Field>());
    }

    private Vector<Field> getAllFieldsRec(Class clazz, Vector<Field> vector) {
        Class superClazz = clazz.getSuperclass();
        if(superClazz != null){
            getAllFieldsRec(superClazz, vector);
        }
        Vector<Field> vec = new Vector<>(Arrays.asList(clazz.getDeclaredFields()));
        vector.addAll(vec);
        return vector;
    }
    
    public List<Tuple3<Class<?>, JLabel, JComponent>>  doFuck(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        System.out.println("methods = " + methods.length);
        
        HashSet<Class<?>> pass = HashSet.of(String.class,  Date.class);
        return List.<Method>ofAll(Arrays.stream(methods))
                .filter(meth -> pass.contains(meth.getReturnType()))
                .map((Method meth) -> {
                    Class<?> rt = meth.getReturnType();
                    JLabel label = null;
                    JComponent comp = null;
                    
                    if (rt.equals(String.class)) {
                        comp = new javax.swing.JTextField("");
                        label = new JLabel();
                        label.setText(getName(meth));
                        
                    } 
                    else if (rt.equals(Date.class)) {
                        comp = new JDateChooser();
                        label = new JLabel();
                        label.setText(getName(meth));
                    }                    
                    else if (rt.equals(Calendar.class)) {
                        comp = new JCalendar();
                        label = new JLabel();
                        label.setText(getName(meth));
                    }                    

                    
                    return Tuple.of(rt, label, comp);
                });
    }
    
    public List<Tuple4<Class<?>, JLabel, JComponent, String>> doAnotherFuckingThing(Class<?> clazz) throws NoSuchFieldException {
        ListUrutan annotation;
        annotation = clazz.getAnnotation(ListUrutan.class);
        if (annotation == null) {
            throw new RuntimeException("Class " + clazz.getName() + " tidak mempunyai annotation ListUrutan");
        }
        
        String[] urutan = annotation.value();
        List<Tuple4<Class<?>, JLabel, JComponent, String>> result = List.empty();
        for (int i = 0; i < urutan.length; i++) {
            String str = urutan[i];
            Field field = this.getFieldTillRoot(clazz, str);
            Class<?> fieldType = field.getType();
            String labText = this.createLabelName(field.getName());
            JLabel label = new JLabel(labText);
            JComponent comp = null;
            Object ob = null;
            
            if (fieldType.equals(Date.class)) {
                comp = new JDateChooser(new java.util.Date());
            }
            else if ( Number.class.isAssignableFrom(fieldType)) {
                try {
                    ob = org.apache.commons.beanutils.ConstructorUtils.invokeConstructor(fieldType, "0");
                    comp = new javax.swing.JFormattedTextField(ob);                                             
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException ex) {
                    Logger.getLogger(Foo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else 
            {
                    comp = new MyTextField();
            }              
            result = result.append(Tuple.of(fieldType, label, comp, str));
        }
        
        return result;
    }
    
    private Field getFieldTillRoot(Class<?> cls, String name) {
//        System.out.println("class name = " + cls.getName());
        Field field = getField(cls, name);
        if (field != null) {
            return field;
        } else {
            Class<?> sup = cls.getSuperclass();
            return getFieldTillRoot(sup, name);
        }
    }
    
    private Field getField(Class<?> cls, String name) {
        try {
            return cls.getDeclaredField(name);
        } catch (NoSuchFieldException | SecurityException ex) {
//            Logger.getLogger(Foo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void main(String[] args) {
        (new Foo()).doFuck(app.table.Bpkbtitipan.class).forEach(tup -> {
            Class<?> tipeField = tup._1();
            JLabel label = tup._2();
            JComponent comp = tup._3();
            
            if (tipeField.equals(Date.class)) {
                JDateChooser chooser = (JDateChooser) comp;
                /// ;sdklsdjs
            }
        });
    }
}
