/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author SEED
 */
public class ColorList {
    public static String[] model = new String[]{ "Putih", 
                                                    "Silver", 
                                                    "Merah", 
                                                    "Biru", "Lainnya" };
    
//    public static DefaultComboBoxModel<String> warna;
    public static DefaultComboBoxModel<String> warna = new DefaultComboBoxModel(ColorList.model);
    
}
