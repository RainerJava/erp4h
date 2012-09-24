/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：NumberText.java
 *
 *     記述				：
 *     
 *     作成日			：2009/10/14   
 *
 *     作成者			：PC12
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.jface.text;

import java.awt.Color;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

import com.fas.common.constants.screen.ColorConstants;

public class NumberText extends BaseInputText {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public NumberText() {
		super();
		setHorizontalAlignment(JTextField.RIGHT);
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
	public NumberText(String name) {
		this(name, 20);
		setHorizontalAlignment(JTextField.RIGHT);
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
	 * @param columns
	 */
	public NumberText(String name, int columns) {
		this(name, columns, 0);
		setHorizontalAlignment(JTextField.RIGHT);
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
	 * @param columns
	 * @param maxlength
	 */
	public NumberText(String name, int columns, int maxlength) {
		super(name, columns, ImeText.IM_NONE, maxlength);
		setHorizontalAlignment(JTextField.RIGHT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.text.AbstractInputText#checkMode(java.lang.String)
	 */
	protected boolean checkMode(String charString) {
		char[] cr = charString.toCharArray();
		char c = cr[0];
		return (Character.isDigit(c) || charString.equalsIgnoreCase(".") || charString.equalsIgnoreCase("-"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.text.AbstractInputText#setText(java.lang.String)
	 */
	public void setText(String str) {
		if (str != null && !"".equals(str)) {
			str = str.trim();
			long number = 0;
			try {
				number = Long.parseLong(str);
			} catch (NumberFormatException e) {
				//eate the exception;
				super.setText("");
			}
			super.setText(Long.toString(number));
			
			if (number >= 0) {
	    		this.setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
			} else {
				this.setForeground(Color.RED);
			}
			
		} else
			super.setText("");
	}
	
	public void setTextBeginWithZero(String str) {
		if (str != null && !"".equals(str)) {
			str = str.trim();
			long number = 0;
			try {
				number = Long.parseLong(str);
			} catch (NumberFormatException e) {
				// eate the exception;
				super.setText("");
			}
			String num = Long.toString(number);
			while (num.length() < str.length()) {
				num = "0" + num;				
			}
			super.setText(num);
		} else
			super.setText("");
	}


	public long getNumberValue() {
		long result = 0;
		try {
			result = Long.parseLong(this.getText());
		} catch (NumberFormatException e) {
			// eat the exception
		}
		return result;
	}
	
    public void focusLost(FocusEvent e) {
    	if (this.isEditable() == true){
    		this.setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
    	}
    	else {
    		this.setBackground(ColorConstants.DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR);
    	}

    	setText(getText());
    }
}
