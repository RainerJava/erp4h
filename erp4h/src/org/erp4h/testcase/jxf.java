package org.erp4h.testcase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.JXTaskPane;

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
		
		JXTitledSeparator titledSeparator = new JXTitledSeparator();
		getRootPaneExt().getContentPane().add(titledSeparator, BorderLayout.NORTH);
		
		JXTaskPane taskPane = new JXTaskPane();
		taskPane.setTitle("Chon phan he");
		getRootPaneExt().getContentPane().add(taskPane, BorderLayout.CENTER);

//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
	}

}
