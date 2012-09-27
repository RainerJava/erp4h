package org.erp4h.testcase;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.tree.*;

public class TreeWithPopup extends JPanel {
	DefaultMutableTreeNode root, node1, node2, node3;

	public TreeWithPopup() {
		MyJTree tree;
		root = new DefaultMutableTreeNode("root", true);
		node1 = new DefaultMutableTreeNode("node 1", true);
		node2 = new DefaultMutableTreeNode("node 2", true);
		node3 = new DefaultMutableTreeNode("node 3", true);
		root.add(node1);
		node1.add(node2);
		root.add(node3);
		setLayout(new BorderLayout());
		tree = new MyJTree(root);
		add(new JScrollPane(tree), "Center");
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}

	public static void main(String s[]) {
		JFrame frame = new JFrame("Tree With Popup");
		TreeWithPopup panel = new TreeWithPopup();
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setForeground(Color.black);
		frame.setBackground(Color.lightGray);
		frame.getContentPane().add(panel, "Center");
		frame.setSize(panel.getPreferredSize());
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Window win = e.getWindow();
				win.setVisible(false);
				System.exit(0);
			}
		});
	}
}
