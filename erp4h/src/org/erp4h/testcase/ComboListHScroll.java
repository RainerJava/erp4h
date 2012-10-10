package org.erp4h.testcase;

import java.awt.Component;
import javax.swing.*;
 
public class ComboListHScroll {
 
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
 
      @Override
      public void run() {
        new ComboListHScroll().makeUI();
      }
    });
  }
 
  public void makeUI() {
    Object[] data = {"One", "Two", "Three", "FourFiveSizSevenEight"};
    JComboBox comboBox = new JComboBox(data);
    comboBox.setPrototypeDisplayValue(data[0]);
    comboBox.setRenderer(new ListHscrollCellRenderer());
 
    JFrame frame = new JFrame();
    frame.add(comboBox);
    frame.pack();
 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
 
class ListHscrollCellRenderer extends DefaultListCellRenderer {
 
  @Override
  public Component getListCellRendererComponent(JList list, Object value,
          int index, boolean isSelected, boolean cellHasFocus) {
    setHScrollFor(list);
    return super.getListCellRendererComponent(list, value,
            index, isSelected, cellHasFocus);
  }
 
  private void setHScrollFor(JList list) {
    JScrollPane scrollPane =
            (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, list);
    if (scrollPane.getHorizontalScrollBar() == null
            || scrollPane.getHorizontalScrollBarPolicy()
            != JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED) {
      scrollPane.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
  }
}
