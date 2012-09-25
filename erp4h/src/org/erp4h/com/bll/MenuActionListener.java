package org.erp4h.com.bll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.erp4h.testcase.abc;


public class MenuActionListener implements ActionListener {
	@Override
	@SuppressWarnings({ "rawtypes" })
	public void actionPerformed(ActionEvent e) {
		System.out.println("Selected: " + e.getActionCommand());
		System.out.println("Selected: " + e.getSource());
		try {
			Class.forName("Center.abc");
			System.out.println("OK");
			Class c   = Class.forName("Center.abc"); 
			abc a = (abc)c.newInstance();
			a.setVisible(true);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
}

