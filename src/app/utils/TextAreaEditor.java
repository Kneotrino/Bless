/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 *
 * @author SEED
 */
public class TextAreaEditor extends DefaultCellEditor {
//   protected JScrollPane scrollpane;
   protected JTextArea textarea;
   protected JButton button;
   protected JPopupMenu popup = new JPopupMenu();
   
//   protected JTextArea textarea;
  
   public TextAreaEditor() {
      super(new javax.swing.JTextField());
//      scrollpane = new JScrollPane();
      textarea = new JTextArea(); 
//      textarea.setLineWrap(true);
//      textarea.setWrapStyleWord(true);
//      textarea.setBorder(new TitledBorder("This is a JTextArea"));
//      scrollpane.getViewport().add(textarea);
   }
  
   public Component getTableCellEditorComponent(JTable table, Object value,
                                   boolean isSelected, int row, int column) {
      textarea.setText((String) value);  
      this.popup.add(this.textarea);
      return popup;
   }
  
   public Object getCellEditorValue() {
      return textarea.getText();
   }
}
