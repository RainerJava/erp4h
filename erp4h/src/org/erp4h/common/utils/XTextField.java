package org.erp4h.common.utils;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.UIManager;

public class XTextField extends JTextField {
	public XTextField() {
		setBorder(UIManager.getBorder("ComboBox.border"));
		setCaretColor(Color.RED);
		HandleClass handle = new HandleClass();
		addMouseListener(handle);
	}

	private class HandleClass implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
