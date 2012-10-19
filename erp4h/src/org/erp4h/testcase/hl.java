package org.erp4h.testcase;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class hl extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					hl frame = new hl();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		java.util.Locale.setDefault(new Locale("vi", "VN"));
		hl hello = new hl();
		hello.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public hl() {
		setTitle("test"); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOk = new JButton(Messages.getString("hl.0")); //$NON-NLS-1$
		btnOk.setBounds(220, 239, 91, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton(Messages.getString("hl.1")); //$NON-NLS-1$
		btnCancel.setBounds(341, 239, 91, 23);
		contentPane.add(btnCancel);
	}
}
