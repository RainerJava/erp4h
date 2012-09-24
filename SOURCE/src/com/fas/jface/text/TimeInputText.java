/**
 * 
 */
package com.fas.jface.text;

/**
 * @author Bui Ngoc Viet
 *
 */

import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.MaskFormatter;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.NumberUtils;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD>
 * <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: PC12</DD><BR>
 * <DD></DD>
 * </DL>
 */

public class TimeInputText extends ValidateText implements FocusListener {

	/** */
	private static final long serialVersionUID = 1L;


	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param name
	 * @param length
	 * @param maxlength
	 * @param setFormat
	 */
	public TimeInputText(String name,String dateFormatPattern) {
		super(getTimeFormatter(dateFormatPattern));
		setFont(FontConstants.TEXT_FIELD_FONT);
		
		setName(name);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_SPACE) {
					showCalendarDialog();
				}
			}
		});
		setMargin(new Insets(1, 2, 0, 2));
		addFocusListener(this);
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param pattern
	 * @return
	 */
	private static DefaultFormatter getTimeFormatter(String pattern) {

		MaskFormatter format = null;

		try {
			format = new MaskFormatter("##:##");
			format.setPlaceholderCharacter('_');
		} catch (ParseException e) {
		}
		return format;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public void showCalendarDialog() {

		

	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param date
	 */
	public void setDateValue(String time) {
		if (time != null&& validate(time))
		{
			setText(time);
			setValue(time);
			
		} else{
			setText("");
			setValue(null);
		}
	}

    private boolean validate(String time) {
		if(time.indexOf("_") != -1)
			return false;
		int iH = NumberUtils.getIntFromString(time.substring(0,2));
		if(iH <0 || iH >23)
			return false;
		int iM = NumberUtils.getIntFromString(time.substring(3,5));
		if(iM <0 || iM >59)
			return false;
		return true;
	}

	/* (non-Javadoc)
     * @see javax.swing.text.JTextComponent#setText(java.lang.String)
     */
    public void setText(String t) {    	
    	super.setText(t);
    	
    	if ("".equalsIgnoreCase(t) || t == null) {
    		this.setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
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
	 * @return
	 */
//	public Date getDateValue() {
//
//		String textDate = "";
//
//		textDate = getText();
//		if (textDate.length() > 9) {
//			textDate = textDate.substring(0, 10);
//		}
//		Date date = null;
//		dateFormat.setLenient(false);
//		try {
//			if (textDate.indexOf("_") == -1 && !"".equals(textDate)) {
//				date = dateFormat.parse(textDate);
//			}
//		} catch (Throwable t) {
//		}
//		return date;
//	}

	public int getIntegerValueFloor(){
		int invTime = 0;
		
		try {
			String time = getText();
			String t = time.substring(0,2) + time.substring(3,5)+"00";
			invTime = NumberUtils.getIntFromString(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return invTime;
	}
	public int getIntegerValueCeiling(){
		int invTime = 0;
		
		try {
			String time = getText();
			String t = time.substring(0,2) + time.substring(3,5)+"59";
			invTime = NumberUtils.getIntFromString(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return invTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	public void focusGained(FocusEvent e) {
		this.setBackground(ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				selectAll();
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.linc.jface.base.text.ValidateText#focusLost(java.awt.event.FocusEvent)
	 */
	public void focusLost(FocusEvent e) {
		setDateValue(getText());
		this.setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
		
//		if (getDateValue() == null) {
//			this.setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
//		}
	}

	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		BaseCalendarText combo = new BaseCalendarText("日付", 20, 20,"yyyy/MM/dd(_)");
		JButton btn = new JButton("▼");
		frame.add(combo);
		frame.add(btn);
		frame.pack();
		frame.setVisible(true);
	}
}
