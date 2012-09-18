package Demo;

import javax.swing.*;

import TreeTest.TristateCheckBox2;

import java.awt.*;

public class TristateCheckBoxTest {
  public static void main(String args[]) throws Exception {
    JFrame frame = new JFrame("TristateCheckBoxTest");
    frame.getContentPane().setLayout(new GridLayout(0, 1, 5, 5));
    final TristateCheckBox2 swingBox = new TristateCheckBox2(
        "Testing the tristate checkbox");
    swingBox.setMnemonic('T');
    frame.getContentPane().add(swingBox);
    frame.getContentPane().add(new JCheckBox(
        "The normal checkbox"));
    UIManager.setLookAndFeel(
        UIManager.getSystemLookAndFeelClassName());
    final TristateCheckBox2 winBox = new TristateCheckBox2(
        "Testing the tristate checkbox",
        TristateCheckBox2.SELECTED);
    frame.getContentPane().add(winBox);
    final JCheckBox winNormal = new JCheckBox(
        "The normal checkbox");
    frame.getContentPane().add(winNormal);
    // wait for 3 seconds, then enable all check boxes
    new Thread() { {start();}
      public void run() {
        try {
          winBox.setEnabled(false);
          winNormal.setEnabled(false);
          Thread.sleep(3000);
          winBox.setEnabled(true);
          winNormal.setEnabled(true);
        } catch (InterruptedException ex) { }
      }
    };
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.show();
  }
}
