package org.erp4h.bll;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class SystemTreeView extends JPanel{
	JPopupMenu pm;
	DefaultTreeModel model;
	JTree tree;
	
	public SystemTreeView(){
		TreeNode root = makeTree();
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setEditable(true);
		
		//
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane, BorderLayout.CENTER);
		//

		pm=makePopup();
	}

	private JPopupMenu makePopup() {
		// TODO Auto-generated method stub
		return null;
	}

	private TreeNode makeTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Quan ly he thong");
		DefaultMutableTreeNode sysUser= new DefaultMutableTreeNode("He thong nhuoi dung");
		root.add(sysUser);
		DefaultMutableTreeNode sysMenu= new DefaultMutableTreeNode("He thong Menu");
		root.add(sysMenu);
		DefaultMutableTreeNode sysReport= new DefaultMutableTreeNode("He thong bao cao");
		root.add(sysReport);
		return root;	
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SystemTreeView());
		frame.show();
	}
}