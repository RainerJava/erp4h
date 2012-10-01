package org.erp4h.testcase;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

public class dp extends JFrame {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		
		textField = new JTextField();
		textField.setBounds(115, 49, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JXDatePicker datePicker = new JXDatePicker();
		datePicker.setBounds(117, 97, 230, 22);
		getContentPane().add(datePicker);

	}
}
