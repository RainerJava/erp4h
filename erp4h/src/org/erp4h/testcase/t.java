package org.erp4h.testcase;

import java.awt.EventQueue;
import javax.swing.JFrame;
import org.erp4h.common.utils.XTextField;

public class t extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					t frame = new t();
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
	public t() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 357);
		getContentPane().setLayout(null);
		
		XTextField textField = new XTextField();
		textField.setBounds(114, 85, 211, 20);
		getContentPane().add(textField);
	}
}
