/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：JapanCalendarText.java
*
*     記述				：
*     
*     作成日			：2009/11/16   
*
*     作成者			：PC12
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface.text;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DefaultFormatter;
import javax.swing.text.MaskFormatter;
import com.fas.common.constants.screen.ColorConstants;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC12</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class JapanCalendarText extends ValidateText implements FocusListener {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private SimpleDateFormat dateFormat;		

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param name
	 * @param maxlength
	 * @param setFormat
	 */
	public JapanCalendarText(String name, int maxlength, String dateFormatPattern) {
		this(name, 18, maxlength, dateFormatPattern);
		addFocusListener(this);
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param name
	 * @param length
	 * @param maxlength
	 * @param setFormat
	 */
	public JapanCalendarText(String name, int length, int maxlength, String dateFormatPattern) {
		
		super(getDateFormatter(dateFormatPattern));

		dateFormat = new SimpleDateFormat(dateFormatPattern);
		setColumns(length);
		setName(name);
		
		addFocusListener(this);

	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param pattern
	 * @return
	 */
	private static DefaultFormatter getDateFormatter(String pattern) {
		
        MaskFormatter format = null;
        
        try {
            format = new MaskFormatter(pattern.replaceAll("yy", "##").replaceAll("MM", "##").replaceAll("dd", "##").replaceAll("mm", "##"));
            format.setPlaceholderCharacter('_');
        } catch (ParseException e) {
        }
        
        return format;
    }

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param date
	 */
	public void setDateValue(Date date) {
		if (date != null) {
			String value = dateFormat.format(date);
			setText(value);
		}else
			setText("");
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	public Date getDateValue() {

		String value = getText();
		Date date = null;
		dateFormat.setLenient(false);
		try {
			if (value.indexOf("_") == -1 && !"".equals(value)) {
				date = dateFormat.parse(value);
			}
		} catch (Throwable t) {
			//systemError(getFrame(), t);
		}
		return date;
	}
	
    /* (non-Javadoc)
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    public void focusGained(FocusEvent e) {
    	this.setBackground(ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR);
    }

    /* (non-Javadoc)
     * @see com.hanbai.jface.base.text.ValidateText#focusLost(java.awt.event.FocusEvent)
     */
    public void focusLost(FocusEvent e) {
    	this.setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);
    }

}

