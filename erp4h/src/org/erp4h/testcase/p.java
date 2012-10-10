package org.erp4h.testcase;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class p extends JPanel {

	/**
	 * Create the panel.
	 */
	public p() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

	}

}
