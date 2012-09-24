/**
 * 
 */
package com.fas.jface.text;

/**
 * @author Bui Ngoc Viet
 *
 */

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.NumberUtils;
import com.fas.common.constants.screen.ColorConstants;

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

public class INumberText extends ValidateText implements FocusListener, ActionListener {

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
	public INumberText(String name, boolean isNvarchar, boolean isDouble, int iIntegerDigit, int iFractionDigits) 
	{
		super(getTimeFormatter( isNvarchar, isDouble, iIntegerDigit, iFractionDigits));
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
		
		addActionListener(this);		
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
	private static DefaultFormatter getTimeFormatter(boolean isNvarchar, boolean isDouble, int iIntegerDigit, int iFractionDigits) 
	{	
		MaskFormatter format = null;

		String strFormat = "";
		if(isNvarchar == true)
		{
			for(int i=0;i < iIntegerDigit;i++)
			{
				strFormat += "*";
			}
		}
		if(isNvarchar == false && isDouble == false) //isInteger
		{
			for(int i=0;i < iIntegerDigit;i++)
			{
				strFormat += "#";
			}
		}
		if(isNvarchar == false && isDouble == true) //isDouble
		{			
//			for(int i=0;i < iIntegerDigit+iFractionDigits;i++)
//			{
//				strFormat += "#|.";
//			}
			NumberFormatter formatter = null;
			formatter = new NumberFormatter(new DecimalFormat("#0.000"));
            formatter.setValueClass(Double.class);
            return formatter;

		}
		try 
		{			
			format = new MaskFormatter(strFormat);
			//format.setPlaceholderCharacter('_');
		}
		catch (ParseException e) {
		}
		return format;
	}

	public class MyKeyListener extends KeyAdapter{
	    public void keyPressed(KeyEvent ke){
	      char i = ke.getKeyChar();
	      String str = Character.toString(i);
	      if( str == "e" )
	    	  setText("Error");
	      else
	    	  setText(str);
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
		setText(time);
		setValue(time);
	}

    private boolean validate(String time) {
//		if(time.indexOf("_") != -1)
//			return false;
//		int iH = NumberUtils.getIntFromString(time.substring(0,2));
//		if(iH <0 || iH >23)
//			return false;
//		int iM = NumberUtils.getIntFromString(time.substring(3,5));
//		if(iM <0 || iM >59)
//			return false;
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
   

	 public void textValueChanged (TextEvent evt) {
		 try  {
		        Double.valueOf (getText()).doubleValue ();
		      }
		      catch  (NumberFormatException e)  {
		        setText ("0.0");
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
		//setDateValue(getText());
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

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
