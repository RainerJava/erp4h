package org.erp4h.testcase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import org.erp4h.com.bll.SystemMenu;
import org.erp4h.com.bll.absUser;
import org.erp4h.common.SystemParameters;


public class abb extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					abb frame = new abb();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public abb() throws Exception {
		super("erp4h");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		SystemParameters.PHAN_HE="PhanHeID=1";
		
		this.setJMenuBar(new SystemMenu(SystemParameters.PHAN_HE));
		absUser tu=new absUser();
		JTable table=new JTable(tu);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane jsp = new JScrollPane(table);
		contentPane.add(jsp, BorderLayout.CENTER);
	}
}
