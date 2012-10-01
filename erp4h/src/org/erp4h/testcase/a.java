package org.erp4h.testcase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.erp4h.bll.SystemLogin;
import org.erp4h.common.SystemParameters;
import java.awt.Toolkit;

public class a extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					a frame = new a();
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
	public a() throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage(a.class.getResource("/Icon/Military_Services/british_army.png")));
		setName("frmLogin");
		setTitle(SystemParameters.APPLICATION_NAME+" - Dang nhap");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		SystemLogin systemLogin = new SystemLogin();
		contentPane.add(systemLogin, BorderLayout.CENTER);

	}

}
