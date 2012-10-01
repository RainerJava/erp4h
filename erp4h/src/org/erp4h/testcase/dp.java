package org.erp4h.testcase;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.JXDatePicker;
import org.erp4h.common.utils.DatePicker;
import javax.swing.DefaultComboBoxModel;

public class dp extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					dp frame = new dp();
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
	public dp() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JXComboBox cb= new JXComboBox();
		cb.setModel(new DefaultComboBoxModel(new String[] {"sdfsdfg", "ghfghjf", "ghfghj", "ghjkghjk"}));
		cb.setEditable(true);
		cb.setLocation(97, 72);
		cb.setSize(241, 22);
		getContentPane().add(cb);

	}
}
