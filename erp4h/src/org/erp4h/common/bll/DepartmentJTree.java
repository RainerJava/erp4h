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

/**
 * @author hieulv
 *{@code}
 */
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

		// tao menu popup
		popup = makePopup();
		// tao tree
		TreeNode root = makeDepartmentTree(1);
		model = new DefaultTreeModel(root);
		this.setModel(model);
		// xu ly chi chon 1 nut tren tree
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
		// xu ly khi right click tren tree
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

	// Tao menu popup
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

	/**
	 * Tao tree voi tham so dua vao de sap xep
	 * @param order 1 sap xep theo ten, 2 sap xep theo ma
	 * @return root
	 * @throws Exception
	 */
	private TreeNode makeDepartmentTree(int order) throws Exception {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Khoa phong");
		if (order == 1) {
			ArrayList<KhoaPhongDTO> arrKhoaPhong = new DepartmentBLL()
					.getArrKhoaPhong(null, "TenKhoaPhong");
			for (int i = 0; i < arrKhoaPhong.size(); i++) {
				DefaultMutableTreeNode kp = new DefaultMutableTreeNode(
						arrKhoaPhong.get(i));
				root.add(kp);
			}
		} else {
			ArrayList<KhoaPhongDTO> arrKhoaPhong = new DepartmentBLL()
					.getArrKhoaPhong(null, "KhoaPhongID");
			for (int i = 0; i < arrKhoaPhong.size(); i++) {
				DefaultMutableTreeNode kp = new DefaultMutableTreeNode(
						arrKhoaPhong.get(i));
				root.add(kp);
			}
		}
		return root;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("orderByName")) {
			TreeNode root;
			try {
				root = makeDepartmentTree(1);
				model = new DefaultTreeModel(root);
				this.setModel(model);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("orderByID")) {
			TreeNode root;
			try {
				root = makeDepartmentTree(2);
				model = new DefaultTreeModel(root);
				this.setModel(model);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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