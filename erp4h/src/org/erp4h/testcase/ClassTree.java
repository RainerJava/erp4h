package org.erp4h.testcase;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

/**
 * This class renders a class name either in plain or ita Abstract classes are
 * italic.
 */
class ClassNameTreeCellRenderer extends DefaultTreeCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font plainFont = null;
	private Font italicFont = null;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded,
				leaf, row, hasFocus);
		// get the user object
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Class<?> c = (Class<?>) node.getUserObject();

		// the first time, derive italic font from plain fo
		if (plainFont == null) {
			plainFont = getFont();
			/*
			 * the tree cell renderer is sometimes called wi label that has a
			 * null font
			 */
			if (plainFont != null)
				italicFont = plainFont.deriveFont(Font.ITALIC);
		}

		// set font to italic if the class is abstract
		if ((c.getModifiers() & Modifier.ABSTRACT) == 0)
			setFont(plainFont);
		else
			setFont(italicFont);
		return this;
	}
	public void setClosedIcon(){
		
	}
}

/**
 * This program demonstrates cell rendering by showing a tree of classes and
 * their superclasses.
 */
public class ClassTree {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		JFrame frame = new ClassTreeFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show();
	}
}

/**
 * This frame displays the class tree and a text field an add button to add more
 * classes into the tree.
 */
class ClassTreeFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode root;

	private DefaultTreeModel model;

	private JTree tree;

	private JTextField textField;

	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;
	public ClassTreeFrame() {
		setTitle("ClassTree");
		setSize(WIDTH, HEIGHT);

		// the root of the class tree is Object
		root = new DefaultMutableTreeNode(java.lang.Object.class);
		model = new DefaultTreeModel(root);
		tree = new JTree(model);

		// add this class to populate the tree with some da
		addClass(getClass());

		// set up node icons
		ClassNameTreeCellRenderer renderer = new ClassNameTreeCellRenderer();
		renderer.setClosedIcon(new ImageIcon(ClassTree.class.getResource("/Icon/Very_Basic/plus.png")));
		renderer.setOpenIcon(new ImageIcon(ClassTree.class.getResource("/Icon/Very_Basic/minus.png")));
		renderer.setLeafIcon(new ImageIcon(ClassTree.class.getResource("/Icon/Users/user.png")));
		tree.setCellRenderer(renderer);

		getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);

		addTextField();
	}
	/**
	 * Adds a new class and any parent classes that aren't yet part of the tree
	 * 
	 * @param c
	 *            the class to add
	 * @return the newly added node.
	 */
	public DefaultMutableTreeNode addClass(Class<?> c) {
		// add a new class to the tree

		// skip non-class types
		if (c.isInterface() || c.isPrimitive())
			return null;

		// if the class is already in the tree, return its
		DefaultMutableTreeNode node = findUserObject(c);
		if (node != null)
			return node;

		// class isn't present--first add class parent recu

		Class<?> s = c.getSuperclass();

		DefaultMutableTreeNode parent;
		if (s == null)
			parent = root;
		else
			parent = addClass(s);

		// add the class as a child to the parent
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
		model.insertNodeInto(newNode, parent, parent.getChildCount());

		// make node visible
		TreePath path = new TreePath(model.getPathToRoot(newNode));
		tree.makeVisible(path);

		return newNode;
	}
	/**
	 * Add the text field and "Add" button to add a new cl
	 */
	public void addTextField() {
		JPanel panel = new JPanel();

		ActionListener addListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// add the class whose name is in the text
				try {
					String text = textField.getText();
					addClass(Class.forName(text));
					// clear text field to indicate success
					textField.setText("");
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Class not found");
				}
			}
		};

		// new class names are typed into this text field
		textField = new JTextField(20);
		textField.addActionListener(addListener);
		panel.add(textField);

		JButton addButton = new JButton("Add");
		addButton.addActionListener(addListener);
		panel.add(addButton);

		getContentPane().add(panel, BorderLayout.SOUTH);
	}
	/**
	 * Finds an object in the tree.
	 * 
	 * @param obj
	 *            the object to find
	 * @return the node containing the object or null if the object is not
	 *         present in the tree
	 */
	public DefaultMutableTreeNode findUserObject(Object obj) {
		// find the node containing a user object
		Enumeration<?> e = root.breadthFirstEnumeration();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
					.nextElement();
			if (node.getUserObject().equals(obj))
				return node;
		}
		return null;
	}
}
