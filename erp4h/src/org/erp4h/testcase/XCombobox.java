package org.erp4h.testcase;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.erp4h.dto.NhanVienDTO;
import org.erp4h.tax.NhanVienBLL;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class XCombobox extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XCombobox frame = new XCombobox();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public XCombobox() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		ArrayList<NhanVienDTO> arrNhanVien=new NhanVienBLL().getArrNhanVien();
		DefaultComboBoxModel model =new DefaultComboBoxModel();
		for(int i=0;i<arrNhanVien.size();i++)
			model.addElement(arrNhanVien.get(i).tosString());
		JComboBox cb = new JComboBox(model);
		cb.setBounds(10, 81, 432, 22);
		cb.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
		
			}
		});
		contentPane.setLayout(null);
		contentPane.add(cb);
		System.out.println(cb.getComponent(0).getClass().getName());
		System.out.println(cb.getComponent(1).getClass().getName());
		
		cb.remove(0);
		JLabel l=new JLabel("abcde");
		l.setBounds(0, -50, 156, 14);
		cb.add(l);
		System.out.println(cb.getComponentCount());
		cb.getc
	}
}
