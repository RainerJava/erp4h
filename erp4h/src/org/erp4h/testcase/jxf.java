package org.erp4h.testcase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.JXTaskPane;
import org.erp4h.common.utils.XTextField;
import javax.swing.JTextField;
import java.awt.Color;

public class jxf extends JXFrame {

	private JPanel contentPane;

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
	 */
	public jxf() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getRootPaneExt().getContentPane().setLayout(null);
		
		XTextField textField = new XTextField();
		textField.setBackground(new Color(240, 255, 255));
		textField.setBounds(110, 58, 243, 34);
		getRootPaneExt().getContentPane().add(textField);
		
		XTextField textField_1 = new XTextField();
		textField_1.setBounds(110, 109, 243, 44);
		getRootPaneExt().getContentPane().add(textField_1);

//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
	}
}
