package org.erp4h.testcase;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;

public class abc extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					abc frame = new abc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JPanel contentPane;

	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public abc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(10, 11, 422, 251);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(90, 49, 46, 14);
		panel.add(lblNewLabel);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(90, 84, 97, 23);
		panel.add(chckbxNewCheckBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(90, 114, 211, 23);
		panel.add(passwordField);
		
		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(10, 11, 187, 80);
		panel.add(internalFrame);
		internalFrame.setVisible(true);
		
		JComboBox comboBox = new JComboBox();
		tabbedPane.addTab("New tab", null, comboBox, null);
		
		System.out.println(this.getComponentCount());
		System.out.println(this.contentPane.getComponentCount());
		for(int i=0;i<contentPane.getComponentCount();i++){
			System.out.println(this.contentPane.getComponent(i).getClass()+"	"+
		this.contentPane.getComponent(i).getName());
		}
		for(int i=0;i<this.getComponentCount();i++){
			System.out.println(this.getComponent(i).getClass()+"	"+
		this.getComponent(i).getName());
		}		
	}
}
