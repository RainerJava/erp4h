package org.h115.erp4h.testcase;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class TreeEditTest {
	public static void main(String[] args) {
		JFrame frame = new TreeEditFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show();
	}
}

class TreeEditFrame extends JFrame {
	private DefaultTreeModel model;
	private JTree tree;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 200;

	public TreeEditFrame() {
		setTitle("Tree edit test");
		setSize(WIDTH, HEIGHT);
		//
		TreeNode root = makeSampleTree();
		model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.setEditable(true);
		//
		JScrollPane scrollPane = new JScrollPane(tree);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		//
		makeButton();
	}

	private void makeButton() {
		JPanel panel = new JPanel();
		//
		JButton addSiblingButton = new JButton("Add sibling");
		addSiblingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (selectNode == null)
					return;
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectNode
						.getParent();
				if (parent == null)
					return;
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
						"New");
				int selectedIndex = parent.getIndex(selectNode);
				System.out.println(selectedIndex );
				model.insertNodeInto(newNode, parent, selectedIndex + 1);
				// now display new node
				TreeNode[] nodes = model.getPathToRoot(newNode);
				TreePath path = new TreePath(nodes);
				tree.scrollPathToVisible(path);
			}
		});
		panel.add(addSiblingButton);
		//
		JButton addChildButton = new JButton("Add child");
		addChildButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (selectedNode == null)
					return;
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
						"New");
				model.insertNodeInto(newNode, selectedNode,
						selectedNode.getChildCount());
				// now display new node
				TreeNode[] nodes = model.getPathToRoot(newNode);
				TreePath path = new TreePath(nodes);
				tree.scrollPathToVisible(path);
			}
		});
		panel.add(addChildButton);
		//
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (selectedNode != null && selectedNode.getParent() != null)
					model.removeNodeFromParent(selectedNode);
			}
		});
		panel.add(deleteButton);
		
		getContentPane().add(panel,BorderLayout.SOUTH);
	}

	private TreeNode makeSampleTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
		DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
		root.add(country);
		DefaultMutableTreeNode state = new DefaultMutableTreeNode("California");
		country.add(state);
		DefaultMutableTreeNode city = new DefaultMutableTreeNode("San Jose");
		state.add(city);
		city = new DefaultMutableTreeNode("Cupertino");
		state.add(city);
		state = new DefaultMutableTreeNode("Michigan");
		country.add(state);
		city = new DefaultMutableTreeNode("Ann Arbor");
		state.add(city);
		country = new DefaultMutableTreeNode("Germany");
		root.add(country);
		state = new DefaultMutableTreeNode("Schleswig-Holst");
		country.add(state);
		city = new DefaultMutableTreeNode("Kiel");
		state.add(city);
		return root;
	}

}