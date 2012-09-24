/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：BaseCalendarText.java
 *
 *     記述				：
 *     
 *     作成日			：2009/12/16   
 *
 *     作成者			：PC12
 *
 *     備考				：
 *
 **************************************************************************************/

package com.fas.jface.text;

import java.awt.Component;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.MaskFormatter;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.DateUtils;
import com.fas.jface.calendar.BaseCalendar;
import com.fas.jface.gui.BaseFrame;

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

public class BaseCalendarText extends ValidateText implements FocusListener {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	protected BaseCalendar calendar;
	/** */
	protected SimpleDateFormat dateFormat;

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param name
	 * @param maxlength
	 * @param setFormat
	 */
	public BaseCalendarText(String name, int maxlength, String dateFormatPattern) {
		this(name, 20, maxlength, dateFormatPattern);
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
	 * @param length
	 * @param maxlength
	 * @param setFormat
	 */
	public BaseCalendarText(String name, int length, int maxlength, String dateFormatPattern) {

		super(getDateFormatter(dateFormatPattern));
		setFont(FontConstants.TEXT_FIELD_FONT);
		if (dateFormatPattern.length() > 9) {
			dateFormat = new SimpleDateFormat(dateFormatPattern.substring(0, 10));
		} else {
			dateFormat = new SimpleDateFormat(dateFormatPattern);
		}
		
		setColumns(length);
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
	private static DefaultFormatter getDateFormatter(String pattern) {

		MaskFormatter format = null;

		try {
			format = new MaskFormatter(pattern.replaceAll("yyyy", "####").replaceAll("MM", "##").replaceAll("dd", "##").replaceAll("mm", "##").replaceAll("E", "*"));
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

		Date date = getDateValue();

		if (date == null) {
			date = new Date();
		}

		calendar = new BaseCalendar(this, date);
		calendar.setVisible(true);
    	
		Date dtDate = calendar.getSelectedDate();

		if (dtDate != null) {
			setDateValue(dtDate);
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
	public void showCalendarDialog(BaseFrame frame) {

		Date date = getDateValue();

		if (date == null) {
			date = new Date();
		}

		calendar = new BaseCalendar(frame,this, date);
		calendar.setVisible(true);
    	
		Date dtDate = calendar.getSelectedDate();

		if (dtDate != null) {
			setDateValue(dtDate);
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
	public void showCalendarDialog(Component parentFrame) {

		Date date = getDateValue();

		if (date == null) {
			date = new Date();
		}

		calendar = new BaseCalendar(this, date);
		calendar.setParentFrame(parentFrame);
		calendar.setVisible(true);
    	
		Date dtDate = calendar.getSelectedDate();

		if (dtDate != null) {
			setDateValue(dtDate);
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
	 * @param date
	 */
	public void setDateValue(Date date) {
		if (date != null) {
			String dateText = dateFormat.format(date);
			
			if (dateText.length() > 8) {
				if (DateUtils.isYasumiNohi(dateText.replaceAll("/", ""))) {
					setForeground(ColorConstants.CALENDAR_NICHIYOUBI_COLOR);
				} else {
					if ("日".equalsIgnoreCase(DateUtils.getDayOfWeek(dateText))) {
						setForeground(ColorConstants.CALENDAR_NICHIYOUBI_COLOR);
					} else if ("土".equalsIgnoreCase(DateUtils.getDayOfWeek(dateText))) {
						setForeground(ColorConstants.CALENDAR_DOYOUBI_COLOR);
					} else {
						setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
					}
				}
				
				dateText = dateText + "(" + DateUtils.getDayOfWeek(dateText) + ")";
			} else {
				setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
			}
			
			setText(dateText);
			setValue(dateText);
			
		} else{
			setText("");
			setValue(null);
		}
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
	public Date getDateValue() {

		String textDate = "";

		textDate = getText();
		if (textDate.length() > 9) {
			textDate = textDate.substring(0, 10);
		}
		Date date = null;
		dateFormat.setLenient(false);
		try {
			if (textDate.indexOf("_") == -1 && !"".equals(textDate)) {
				date = dateFormat.parse(textDate);
			}
		} catch (Throwable t) {
		}
		return date;
	}
	
	/**
	 * @return integer value of Test
	 */
	public int getIntegerValue(){
		int invDate = 0;
		
		try {
			invDate = DateUtils.getDateWithyyyymmdd(this.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return invDate;
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
		setDateValue(getDateValue());
		this.setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
		
		if (getDateValue() == null) {
			this.setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
		}
	}

	/**
	 * @param parentFrame the parentFrame to set
	 */
	public void setParentFrame(Component parentFrame) {
		calendar.setParentFrame(parentFrame);
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
