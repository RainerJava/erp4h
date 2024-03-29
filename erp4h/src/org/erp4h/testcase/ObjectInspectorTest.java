package org.erp4h.testcase;

import java.awt.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

/**
 * 10. This program demonstrates how to use a custom tree 11. model. It displays
 * the fields of an object. 12.
 */
public class ObjectInspectorTest {
	public static void main(String[] args) {
		JFrame frame = new ObjectInspectorFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.show();
	}
}

/**
 * This frame holds the object tree.
 */
class ObjectInspectorFrame extends JFrame {
	public ObjectInspectorFrame() {
		setTitle("ObjectInspectorTest");
		setSize(WIDTH, HEIGHT);

		// we inspect this frame object

		Variable v = new Variable(getClass(), "this", this);
		ObjectTreeModel model = new ObjectTreeModel();
		model.setRoot(v);

		// construct and show tree

		tree = new JTree(model);
		getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
	}

	private JTree tree;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;
}

/**
 * This tree model describes the tree structure of a Java object. Children are
 * the objects that are stored in in variables.
 */
class ObjectTreeModel implements TreeModel {
	/**
	 * Constructs an empty tree.
	 */
	public ObjectTreeModel() {
		root = null;
	}

	/**
	 * Sets the root to a given variable.
	 * 
	 * @param v
	 *            the variable that is being described by th
	 */
	public void setRoot(Variable v) {
		Variable oldRoot = v;
		root = v;
		fireTreeStructureChanged(oldRoot);
	}

	@Override
	public Object getRoot() {
		return root;
	}

	@Override
	public int getChildCount(Object parent) {
		return ((Variable) parent).getFields().size();
	}

	@Override
	public Object getChild(Object parent, int index) {
		ArrayList fields = ((Variable) parent).getFields();
		Field f = (Field) fields.get(index);
		Object parentValue = ((Variable) parent).getValue();
		try {
			return new Variable(f.getType(), f.getName(), f.get(parentValue));
		} catch (IllegalAccessException e) {
			return null;
		}
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		int n = getChildCount(parent);
		for (int i = 0; i < n; i++)
			if (getChild(parent, i).equals(child))
				return i;
		return -1;
	}

	@Override
	public boolean isLeaf(Object node) {
		return getChildCount(node) == 0;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		listenerList.add(TreeModelListener.class, l);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		listenerList.remove(TreeModelListener.class, l);
	}

	protected void fireTreeStructureChanged(Object oldRoot) {
		TreeModelEvent event = new TreeModelEvent(this,
				new Object[] { oldRoot });
		EventListener[] listeners = listenerList
				.getListeners(TreeModelListener.class);
		for (int i = 0; i < listeners.length; i++)
			((TreeModelListener) listeners[i]).treeStructureChanged(event);
	}

	private Variable root;
	private EventListenerList listenerList = new EventListenerList();
}

/**
 * A variable with a type, name, and value.
 */
class Variable {
	/**
	 * Construct a variable
	 * 
	 * @param aType
	 *            the type
	 * @param aName
	 *            the name
	 * @param aValue
	 *            the value
	 */
	public Variable(Class aType, String aName, Object aValue) {
		type = aType;
		name = aName;
		value = aValue;
		fields = new ArrayList<Field>();

		/*
		 * find all fields if we have a class type except we don't expand
		 * strings and null values
		 */

		if (!type.isPrimitive() && !type.isArray()
				&& !type.equals(String.class) && value != null) {
			// get fields from the class and all superclasse
			for (Class c = value.getClass(); c != null; c = c.getSuperclass()) {
				Field[] f = c.getDeclaredFields();
				AccessibleObject.setAccessible(f, true);

				// get all nonstatic fields
				for (int i = 0; i < f.length; i++)
					if ((f[i].getModifiers() & Modifier.STATIC)==0)
						fields.add(f[i]);
			}
		}
	}

	/**
	 * Gets the value of this variable.
	 * 
	 * @return the value 191.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Gets all nonstatic fields of this variable.
	 * 
	 * @return an array list of variables describing the f
	 */
	public ArrayList getFields() {
		return fields;
	}

	@Override
	public String toString() {
		String r = type + " " + name;
		if (type.isPrimitive())
			r += "=" + value;
		else if (type.equals(String.class))
			r += "=" + value;
		else if (value == null)
			r += "=null";
		return r;
	}

	private Class type;
	private String name;
	private Object value;
	private ArrayList fields;
}