package com.fas.jface.button;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.jface.utils.ValidationUtils;
import com.fas.jface.validate.Validation;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD>
 * <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: PC13</DD><BR>
 * <DD></DD>
 * </DL>
 */
public class ActionButton extends JButton {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	private static boolean isPress = false;
	/** */
	private static boolean isFocus = false;
	/** */
	private boolean isSearchEnable = false;
	/** */
	private boolean affterDoDisable = false;
	/** */
	private boolean isClick = false;

	/** */
	private List validates = new ArrayList();
	/** */
	private DefaultActionAdapter adapter = new DefaultActionAdapter();

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public static void clearPressed() {
		isPress = false;
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public ActionButton() {
		super();
		setFont(FontConstants.BUTTON_TEXT_FONT);
		setMargin(new Insets(1, 1, 1, 1));
		setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
		setRolloverEnabled(true);

		initKey();
		affterDoDisable = false;
		isClick = false;

		super.addActionListener(adapter);
		super.addMouseListener(new RolloverListener());
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param name
	 */
	public ActionButton(String name) {
		super(name);
		setFont(FontConstants.BUTTON_TEXT_FONT);
		setMargin(new Insets(1, 1, 1, 1));
		setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
		FontMetrics fm = getFontMetrics(getFont());
		int Len = fm.stringWidth(name);
		if (67 > Len) {
			setPreferredSize(new Dimension(100, 25));
		}
		initKey();
		affterDoDisable = false;
		isClick = false;
		super.addActionListener(adapter);
		super.addMouseListener(new RolloverListener());
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param name
	 * @param flag
	 */
	public ActionButton(String name, boolean flag) {
		super(name);
		super.addActionListener(adapter);
		setFont(FontConstants.BUTTON_TEXT_FONT);
		setBackground(ColorConstants.DEFAULT_BUTTON_BACKGROUND_COLOR);
		if (flag) {
			setPreferredSize(new Dimension(100, 25));
		} else {
			Insets ins = getMargin();
			ins.left = 0;
			ins.right = 0;
			setMargin(ins);
		}
		initKey();
		setMargin(new Insets(1, 1, 1, 1));
		affterDoDisable = false;
		isClick = false;
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param name
	 * @param icons
	 */
	public ActionButton(String name, Icon icons) {
		super(name, icons);
		super.addActionListener(adapter);
		setPreferredSize(new Dimension(100, 25));
		initKey();
		isClick = false;
		setMargin(new Insets(1, 1, 1, 1));
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param validation
	 */
	@SuppressWarnings("unchecked")
	public void addValidateComponent(Validation validation) {
		validates.add(validation);
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param pos
	 * @param validation
	 */
	@SuppressWarnings("unchecked")
	public void addValidateComponent(int pos, Validation validation) {
		validates.add(pos, validation);
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param validation
	 */
	public void removeValidateComponent(Validation validation) {
		validates.remove(validation);
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return
	 */
	protected Component getParentFrame() {
		return getParent();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return
	 */
	private boolean check() {

		for (int i = 0; i < validates.size(); i++) {
			Validation validation = (Validation) validates.get(i);
			if (ValidationUtils.check(validation) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param l
	 */
	public void addAction(Action l) {
		super.removeActionListener(adapter);
		adapter.action = l;
		super.addActionListener(adapter);
	}

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @author PC13
	 * 
	 */
	protected class DefaultActionAdapter implements ActionListener {

		Action action = null;

		public DefaultActionAdapter() {

		}

		/*
		 * (Javadoc なし)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			if (isPress == false) {
				isPress = true;
				if (getParentFrame() != null) {
					getParentFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				}
				setEnabled(false);
				try {
					if (check() == true && action != null) {
						action.execute();
					}
				} finally {
					if (getParentFrame() != null) {
						getParentFrame().setCursor(null);
					}
				}
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if (affterDoDisable == false) {
							setEnabled(true);

							// foucusが許可か判定
							if (!(isFocus())) {
								requestFocus();
							}
						} else {
							affterDoDisable = false;
						}
						isPress = false;
					}
				});
			} else {
			}
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public void execAction() {
		adapter.action.execute();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return the isPress
	 */
	public static boolean isPress() {
		return isPress;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param isPress
	 *            the isPress to set
	 */
	public static void setPress(boolean isPress) {
		ActionButton.isPress = isPress;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return the isFocus
	 */
	public static boolean isFocus() {
		return isFocus;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param isFocus
	 *            the isFocus to set
	 */
	public static void setFocus(boolean isFocus) {
		ActionButton.isFocus = isFocus;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	private void initKey() {

		super.registerKeyboardAction(super.getActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), JComponent.WHEN_FOCUSED);

		super.registerKeyboardAction(super.getActionForKeyStroke(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), JComponent.WHEN_FOCUSED);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.AbstractButton#setEnabled(boolean)
	 */
	public void setEnabled(boolean b) {
		if (isSearchEnable) {
			if (b == true) {
				super.setEnabled(b);
				setForeground(ColorConstants.DEFAULT_BUTTON_FORE_COLOR);
			} else {
				isSearchEnable = false;
			}
		} else {
			super.setEnabled(b);
			if (b == true) {
				setForeground(ColorConstants.DEFAULT_BUTTON_FORE_COLOR);
			} else {
				setForeground(ColorConstants.DEFAULT_BUTTON_DISABLE_FORE_COLOR);
			}
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return the isSearchEnable
	 */
	public boolean isSearchEnable() {
		return isSearchEnable;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param isSearchEnable
	 *            the isSearchEnable to set
	 */
	public void setSearchEnable(boolean isSearchEnable) {
		this.isSearchEnable = isSearchEnable;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return the affterDoDisable
	 */
	public boolean isAffterDoDisable() {
		return affterDoDisable;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param affterDoDisable
	 *            the affterDoDisable to set
	 */
	public void setAffterDoDisable(boolean affterDoDisable) {
		this.affterDoDisable = affterDoDisable;
	}

	/**
	 * @return the isClick
	 */
	public boolean isClick() {
		return isClick;
	}

	/**
	 * @param isClick
	 *            the isClick to set
	 */
	public void setClick(boolean isClick) {
		this.isClick = isClick;
	}

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @author NQHung
	 * 
	 */
	private final class RolloverListener extends MouseInputAdapter {

		private Color origForeColor;

		public void mouseEntered(MouseEvent e) {
			if (isEnabled()) {
				origForeColor = getForeground();
				setForeground(ColorConstants.EXE_BUTTON_ROLLOVER_FORE_COLOR);
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		}

		public void mouseExited(MouseEvent e) {
			if (getForeground() == ColorConstants.EXE_BUTTON_ROLLOVER_FORE_COLOR) {
				setForeground(origForeColor);
			}
			getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
