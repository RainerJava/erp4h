package JTreeDemo;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

import Demo.ExitListener;
import Demo.WindowUtilities;

public class SelectableTree extends JFrame implements TreeSelectionListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public static void main(String[] args) {
    new SelectableTree();
  }

  private JTree tree;
  private JTextField currentSelectionField;
  
  public SelectableTree() {
    super("JTree Selections");
    WindowUtilities.setNativeLookAndFeel();
    addWindowListener(new ExitListener());
    Container content = getContentPane();
    DefaultMutableTreeNode root =
      new DefaultMutableTreeNode("Root");
    DefaultMutableTreeNode child;
    DefaultMutableTreeNode grandChild;
    for(int childIndex=1; childIndex<4; childIndex++) {
      child = new DefaultMutableTreeNode("Child " + childIndex);
      root.add(child);
      for(int grandChildIndex=1; grandChildIndex<4; grandChildIndex++) {
        grandChild =
          new DefaultMutableTreeNode("Grandchild " + childIndex +
                                     "." + grandChildIndex);
        child.add(grandChild);
      }
    }
    tree = new JTree(root);
    tree.addTreeSelectionListener(this);
    content.add(new JScrollPane(tree), BorderLayout.CENTER);
    currentSelectionField = new JTextField("Current Selection: NONE");
    content.add(currentSelectionField, BorderLayout.SOUTH);
    setSize(250, 275);
    setVisible(true);
  }

  public void valueChanged(TreeSelectionEvent event) {
    currentSelectionField.setText
      ("Current Selection: " +
       tree.getLastSelectedPathComponent().toString());
  }
}