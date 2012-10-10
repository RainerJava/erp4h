package org.erp4h.testcase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.JXTaskPane;
import org.erp4h.bll.UserBLL;
import org.erp4h.common.utils.XTextField;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuListener;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class jxf extends JXFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					jxf frame = new jxf();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public jxf() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getRootPaneExt().getContentPane().setLayout(null);
		
		Object[][]data=new UserBLL().getUserObj();
		
		textField = new JTextField();
		textField.setBounds(78, 43, 86, 20);
		getRootPaneExt().getContentPane().add(textField);
		textField.setColumns(10);
		final JComboBox comboBox = new JComboBox(data);
//		comboBox.addPopupMenuListener(PopupMenuListener l);
		comboBox.setBounds(78, 96, 212, 22);
		getRootPaneExt().getContentPane().add(comboBox);
		comboBox.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
//				comboBox.setPopupVisible(true);
			}
		});

//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
	}
}
