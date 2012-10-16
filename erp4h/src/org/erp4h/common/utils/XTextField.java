package org.erp4h.common.utils;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * @author hieulv
 * 
 */
public class XTextField extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XTextField() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		setBackground(Color.WHITE);
		HandleClass handle = new HandleClass();
		addMouseListener(handle);
		addFocusListener(handle);
	}

	private class HandleClass implements MouseListener, FocusListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			setBorder(BorderFactory.createLineBorder(new Color(100, 255, 255)));
			setBackground(Color.WHITE);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(hasFocus()){
				setBorder(BorderFactory.createLineBorder(new Color(100, 255, 255)));
				setBackground(new Color(246, 255, 255));
			}else{
				setBorder(BorderFactory.createLineBorder(Color.GRAY));
				setBackground(Color.WHITE);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void focusGained(FocusEvent e) {
			setBorder(BorderFactory.createLineBorder(new Color(100, 255, 255)));
			setBackground(new Color(246, 255, 255));
		}

		@Override
		public void focusLost(FocusEvent e) {
			setBorder(BorderFactory.createLineBorder(Color.GRAY));
			setBackground(Color.WHITE);
		}
	}
}
