/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：PasswordInputText.java
 *
 *     記述				：
 *     
 *     作成日			：2009/10/05   
 *
 *     作成者			：PC13
 *
 *     備考				：
 *
 **************************************************************************************/

package com.fas.jface.text;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.StyleConstants;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.utils.JapaneseCheckUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.utils.RequiredImpl;
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
public class PasswordInputText extends JPasswordField implements Validation {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private RequiredImpl required = new RequiredImpl();
	/** */
	private int maxLength = -1;
	/** */
	private String message = "";

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param maxlength
	 */
	public PasswordInputText(int maxlength) {
		super(maxlength);
		maxLength = maxlength;
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public PasswordInputText() {
		this(20);
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	public void setText(String t) {
		super.setText(t);
	}

	public void setEditable(boolean b) {
		super.setEditable(b);
		if (b == true) {
			setFocusable(true);
			setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
		} else {
			setFocusable(false);
			setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);
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
	private void init() {

		addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent e) {
				// setBackground(ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR);
				if (isEditable() == true) {
					setBackground(ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR);
					selectAll();
				} else {
					setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);
				}
			}

			public void focusLost(FocusEvent e) {
				// setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
				if (isEditable() == true)
					setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
				else
					setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);
			}
		});

		// Make the ENTER key act like the TAB key
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
				if (key == KeyEvent.VK_ENTER)
					transferFocus();
			}
		});
		
		setBorder(FaceContants.LINE_BORDER_GRAY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hanbai.jface.base.validate.Validation#setComponent(java.awt.Component)
	 */
	public void setComponent(Component componet) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hanbai.jface.base.validate.Validation#getMessage()
	 */
	public String getMessage() {
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hanbai.jface.base.validate.Validation#setMessage(java.lang.String)
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hanbai.jface.base.validate.Validation#getComponent()
	 */
	public Component getComponent() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hanbai.jface.base.gui.Validation#check()
	 */
	public boolean check() {
		String text = new String(getPassword());
		boolean check = true;
		check = required.execute(text, null);
		if (check == false) {
			message = required.getMessage();
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JTextField#createDefaultModel()
	 */
	protected Document createDefaultModel() {
		return new InputControllDocument(this);
	}

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD>* 入力文字列の属性を保存しておく必要があるため、 PlainDocumentではなく、
	 * DefaultStyledDocumentを拡張しています。
	 * </DD> <BR>
	 * 
	 * @author PC13
	 * 
	 */
	class InputControllDocument extends DefaultStyledDocument {

		/** */
		private static final long serialVersionUID = 1L;
		/** */
		JTextField parent = null;

		/**
		 * <DL>
		 * <DT>コンストラクター記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @param initParent
		 */
		public InputControllDocument(JTextField initParent) {
			super();
			parent = initParent;
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @param str
		 */
		protected void setText(String str) {
			try {
				super.remove(0, getLength());
				super.insertString(0, str, null);
			} catch (BadLocationException e) {
				// ignore
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.text.AbstractDocument#remove(int, int)
		 */
		public void remove(int offs, int length) throws BadLocationException {
			super.remove(offs, length);
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @param offs
		 * @param length
		 * @throws BadLocationException
		 */
		public void superRemove(int offs, int length) throws BadLocationException {
			super.remove(offs, length);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.text.AbstractDocument#insertString(int, java.lang.String, javax.swing.text.AttributeSet)
		 */
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

			if (str == null)
				return;

			if ((a != null) && (a.isDefined(StyleConstants.ComposedTextAttribute))) {
				super.insertString(offs, str, a);
				return;
			}

			// 入力文字列を一文字ずつ判定
			for (int i = 0; i < str.length(); i++) {
				int checkOffs = offs + i;
				char checkChar = str.charAt(i);

				/*
				 * MaxLength以上の文字切り捨て処理
				 */
				if (maxLength != -1) {
					int fieldLength = StringUtils.getBytes(parent.getText()).length;
					int insertStrLength = StringUtils.getBytes(String.valueOf(checkChar)).length;
					if (fieldLength + insertStrLength > maxLength) {
						return;
					}
				}

				if (JapaneseCheckUtils.checkHankaku(checkChar) == false || JapaneseCheckUtils.isKatakana(checkChar)) {
					return;
				}

				super.insertString(checkOffs, String.valueOf(checkChar), a);
			}
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>maxLength を戻します。</DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：maxLengthを設定する</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param maxLength
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

}
