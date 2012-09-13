package JTreeDemo;

import java.awt.*;
import javax.swing.*;

import Demo.ExitListener;
import Demo.WindowUtilities;

public class DynamicTree extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public static void main(String[] args) {
    int n = 5; // Number of children to give each node
    if (args.length > 0)
      try {
        n = Integer.parseInt(args[0]);
      } catch(NumberFormatException nfe) {
        System.out.println("Can't parse number; using default of " + n);
      }
    new DynamicTree(n);
  }
 
  public DynamicTree(int n) {
    super("Creating a Dynamic JTree");
    WindowUtilities.setJavaLookAndFeel();
    addWindowListener(new ExitListener());
    Container content = getContentPane();
    JTree tree = new JTree(new OutlineNode(1, n));
    content.add(new JScrollPane(tree), BorderLayout.CENTER);
    setSize(300, 475);
    setVisible(true);
  }
}
