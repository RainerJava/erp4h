package org.erp4h.common.bll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.erp4h.common.dto.KhoaPhongDTO;

public class DepartmentJTree extends JTree implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPopupMenu popup;
	JMenuItem mi;
	private DefaultTreeModel model;
	final JTree thisTree = this;

	public DepartmentJTree() throws Exception {

		//
		popup=makePopup();
		//
		TreeNode root = makeDepartmentTree();
		model = new DefaultTreeModel(root);
		this.setModel(model);
		//
		this.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = thisTree.getSelectionPath();
				if (path == null)
					return;
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path
						.getLastPathComponent();
				System.out.println(selectedNode.getUserObject());
			}
		});
		//
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

	private JPopupMenu makePopup() {
		popup = new JPopupMenu();
		mi = new JMenuItem("Sap xep theo ten");
		mi.addActionListener(this);
		mi.setActionCommand("orderByName");
		popup.add(mi);
		mi = new JMenuItem("Sap xep theo ma");
		mi.addActionListener(this);
		mi.setActionCommand("orderByID");
		popup.add(mi);
		popup.setOpaque(true);
		popup.setLightWeightPopupEnabled(true);
		return popup;
	}

	private TreeNode makeDepartmentTree() throws Exception {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		ArrayList<KhoaPhongDTO> arrKhoaPhong = new DepartmentBLL()
				.getArrKhoaPhong(null,"TenKhoaPhong");
		for (int i = 0; i < arrKhoaPhong.size(); i++) {
			DefaultMutableTreeNode kp = new DefaultMutableTreeNode(
					arrKhoaPhong.get(i));
			root.add(kp);
		}
		return root;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultMutableTreeNode dmtn, node;

		TreePath path = this.getSelectionPath();
		dmtn = (DefaultMutableTreeNode) path.getLastPathComponent();
		if (e.getActionCommand().equals("orderByName")) {
			node = new DefaultMutableTreeNode("children");
			dmtn.add(node);
			// thanks to Yong Zhang for the tip for refreshing the tree struct
			((DefaultTreeModel) this.getModel())
					.nodeStructureChanged(dmtn);
		}
		if (e.getActionCommand().equals("orderByID")) {
			node = (DefaultMutableTreeNode) dmtn.getParent();
			node.removeAllChildren();
			((DefaultTreeModel) this.getModel())
					.nodeStructureChanged(dmtn);
		}
	}
}

//
// public static void main(String[] args) throws Exception {
// JTree treekp = new DepartmentTree();
// JFrame a = new JFrame();
// JScrollPane jsp = new JScrollPane(treekp);
// a.getContentPane().add(jsp, BorderLayout.CENTER);
// a.show();
// }