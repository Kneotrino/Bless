/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.table;

import java.lang.reflect.Field;

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
        Object b = new app.table.Trips();
        Field[] fields = b.getClass().getDeclaredFields();
            System.out.print(" @app.ListUrutan({");
        for (Field field : fields) {
            System.out.print(",\"" + field.getName()+"\"");
        }
            System.out.print(" })\n");
    }
    
}
