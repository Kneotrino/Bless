/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;

import java.math.BigInteger;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RHINO
 */
public class NominalRender extends DefaultTableCellRenderer {

    public NominalRender() {
        setHorizontalAlignment( javax.swing.JLabel.CENTER );
//        setHorizontalAlignment( javax.swing.JLabel.RIGHT );
    }
    
    @Override
    public void setValue(Object param) {
        try
        {
            boolean minus = false;
            if (param != null) {
                BigInteger temp = (BigInteger) param;                
                minus = temp.min(BigInteger.ZERO).equals(BigInteger.ZERO);
            }
            DecimalFormat numberFormat = new DecimalFormat("IDR #,##0");
            if (!minus) {
                numberFormat = new DecimalFormat("(IDR #,##0)");
            }
            if (param != null)
                param = numberFormat.format(param);            
        }
        catch(IllegalArgumentException e) {}
        super.setValue(param);
    }    
}
