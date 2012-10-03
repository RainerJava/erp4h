package org.erp4h.testcase;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class MainClass extends JFrame {
  public MainClass(String name) {
    getContentPane().setLayout(new FlowLayout());
    JLabel labelTwo = new JLabel("www.java2s.com");
    labelTwo.setBorder(BorderFactory.createEtchedBorder());
    
    add(labelTwo);
    
    
    JLabel labelThree = new JLabel("MatteBorder");
    labelThree.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.pink));
    add(labelThree);
    
    
    JLabel labelFour = new JLabel("TitledBorder");
    labelFour.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(10, 10,
        10, 10, Color.pink), "Title", TitledBorder.RIGHT, TitledBorder.BOTTOM));
    add(labelFour);
    
    JLabel labelSix = new JLabel("CompoundBorder");
    Border one = BorderFactory.createEtchedBorder();
    Border two = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.blue);
    labelSix.setBorder(BorderFactory.createCompoundBorder(one, two));
    add(labelSix);
    
  }

  public static void main(String[] args) {
    JFrame frame = new MainClass("javax.swing.JButton");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

  }
}