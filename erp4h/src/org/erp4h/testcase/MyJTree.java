package org.erp4h.testcase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class MyJTree extends JTree implements ActionListener {
	JPopupMenu popup;
	JMenuItem mi;

	MyJTree(DefaultMutableTreeNode dmtn) {
		super(dmtn);
		// define the popup
		popup = new JPopupMenu();
		mi = new JMenuItem("Insert a children");
		mi.addActionListener(this);
		mi.setActionCommand("insert");
		popup.add(mi);
		mi = new JMenuItem("Remove this node");
		mi.addActionListener(this);
		mi.setActionCommand("remove");
		popup.add(mi);
		popup.setOpaque(true);
		popup.setLightWeightPopupEnabled(true);

		final JTree thisTree = this;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// thanks to urbanq for the bug fix!
				int row = thisTree.getRowForLocation(e.getX(), e.getY());
				if (row == -1)
					return;
				thisTree.setSelectionRow(row);
				if (e.isPopupTrigger()) {
					popup.show((JComponent) e.getSource(), e.getX(), e.getY());
				}
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		DefaultMutableTreeNode dmtn, node;

		TreePath path = this.getSelectionPath();
		dmtn = (DefaultMutableTreeNode) path.getLastPathComponent();
		if (ae.getActionCommand().equals("insert")) {
			node = new DefaultMutableTreeNode("children");
			dmtn.add(node);
			// thanks to Yong Zhang for the tip for refreshing the tree struct
			((DefaultTreeModel) this.getModel())
					.nodeStructureChanged(dmtn);
		}
		if (ae.getActionCommand().equals("remove")) {
			node = (DefaultMutableTreeNode) dmtn.getParent();
			node.removeAllChildren();
			((DefaultTreeModel) this.getModel())
					.nodeStructureChanged(dmtn);
		}
	}
}
