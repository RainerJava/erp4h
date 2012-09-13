package Center;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import DataAccessLayer.MySQLConnectUnit;
import DataTranferObject.KhoaPhongDTO;
import QLTBTH.TableData;
import Utilities.NumberRenderer;

public class frmThietBi extends JFrame implements TreeSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MySQLConnectUnit connect;
	private JSplitPane firstSplit;
	private JTree KhoaPhong;
	private DefaultMutableTreeNode root;
	private JSplitPane secondSplit;
	private JTable ThietBi;
	private DefaultTreeModel treeModel;
	
	public frmThietBi() throws Exception{
		this.connect=DataAccessLayer.DataAccess.getDAL();
		
		initialize();
		getTree();
		
	}
	
	public void getTree() throws Exception{
		ResultSet rs=this.connect.Select("tblKhoaPhong");
		while(rs.next()){
			KhoaPhongDTO kp=new KhoaPhongDTO(rs.getInt("KhoaPhongID"),rs.getString("TenKhoaPhong"));
			DefaultMutableTreeNode node=new DefaultMutableTreeNode(kp);
			System.out.println(rs.getInt("KhoaPhongID")+"	"+rs.getString("TenKhoaPhong"));
			root.add(node);
		}
		KhoaPhong.expandRow(0);
	}
	
	public void initialize() throws Exception{
		//tạo cây
		root=new DefaultMutableTreeNode("Khoa-Ph�ng");
		treeModel=new DefaultTreeModel(root);
		KhoaPhong=new JTree(treeModel);
		KhoaPhong.addTreeSelectionListener(this);
		KhoaPhong.setPreferredSize(new Dimension(250,200));
		
		//tạo bảng
		ThietBi=new JTable(new TableData());
		ThietBi.setDefaultRenderer(Float.class, new NumberRenderer());
		ThietBi.setPreferredScrollableViewportSize(new Dimension(300,400));
		
		//tạo các panel và splitpane
		JPanel pKhoaPhong=new JPanel(new BorderLayout());
		JPanel pThietBi=new JPanel(new BorderLayout());
		JPanel pChiTiet=new JPanel(new BorderLayout());
		secondSplit=new JSplitPane(JSplitPane.VERTICAL_SPLIT,pThietBi,pChiTiet);
		secondSplit.setOneTouchExpandable(true);
		firstSplit=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pKhoaPhong,secondSplit);
		firstSplit.setOneTouchExpandable(true);
		this.add(firstSplit,BorderLayout.CENTER);
		
		pKhoaPhong.add(new JScrollPane(KhoaPhong));
		pKhoaPhong.setPreferredSize(new Dimension(200,300));
		pThietBi.add(new JScrollPane(ThietBi));
		
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
