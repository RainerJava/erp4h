/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：BaseDatePicker.java
 *
 *     記述				：Space or Alt + Down	--> show Calendar
 *     					: Enter					--> set Date
 *     					: Shift Insert on Calendar or 2 click on link	--> set Today
 *     					: Insert or 1 Click on link		--> return to this Month
 *     					: PageUp, PageDown 				--> change Month
 *     					: Home, End		 				--> change Month
 *     					: input DD						--> current YYYYMM + input
 *     					: input MDD or MM/DD				--> current YYYY + 0 + input
 *     					: input MMDD or MM/DD			--> current YYYY + input
 *     					: input YYMMDD or YY/MM/DD		--> 20 + input
 *     					: input YYYY/MM/DD or YYYYMMDD	--> YYYY/MM/DD
 *     
 *     作成日			：2010/06/21   
 *
 *     作成者			：Hung
 *
 *     備考			：
 *
 **************************************************************************************/

package com.fas.jface.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import org.jdesktop.swingx.JXDatePicker;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.ImageConstants;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.calendar.HayashiDatePickerUI;
import com.fas.vo.holiday.HolidayVo;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD> <BR>
 * 
 * @author KIKAKU7
 * 
 */
public class BaseMonthPicker extends JXDatePicker {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM");
	protected JFormattedTextField editor;
	protected HayashiDatePickerUI hayashiDatePickerUI;
	
	public BaseMonthPicker() {
		super();
		init();
	}

	public BaseMonthPicker(Date selection, Locale locale) {
		super(selection, locale);
		init();
	}

	public BaseMonthPicker(Date selected) {
		super(selected);
		init();
	}

	public BaseMonthPicker(Locale locale) {
		super(locale);
		init();
	}

	private void init() {
		/*
		 * Locale setting
		 */
		// Locale.setDefault(new Locale("ja", "JP"));
		
		/*
		 * JXMonthView setting
		 */
		getMonthView().setShowingLeadingDays(true);
		getMonthView().setShowingTrailingDays(true);

		getMonthView().setTodayBackground(Color.BLUE);
		getMonthView().setDayForeground(Calendar.SATURDAY, ColorConstants.CALENDAR_DOYOUBI_COLOR);
		getMonthView().setDayForeground(Calendar.SUNDAY, ColorConstants.CALENDAR_NICHIYOUBI_COLOR);
		getMonthView().setFont(new FontUIResource("ＭＳ ゴシック", Font.PLAIN, 14));
		UIManager.put("JXMonthView.boxPaddingX", 3);
		UIManager.put("JXMonthView.boxPaddingY", 3);
		// UIManager.put("JXMonthView.monthStringBackground", UIManager.getColor("Button.background"));
		// UIManager.put("JXMonthView.weekOfTheYearForeground", UIManager.getColor("Button.background"));
		UIManager.put("JXMonthView.unselectableDayForeground", new ColorUIResource(Color.LIGHT_GRAY));
		UIManager.put("JXMonthView.flaggedDayForeground", new ColorUIResource(ColorConstants.CALENDAR_YASUMINOHI_COLOR));
		UIManager.put("JXMonthView.leadingDayForeground", new ColorUIResource(Color.GRAY));
		UIManager.put("JXMonthView.trailingDayForeground", new ColorUIResource(Color.GRAY));
		// UIManager.put("JXMonthView.font", new FontUIResource("ＭＳ ゴシック", Font.PLAIN, 14));
		UIManager.put("JXDatePicker.arrowIcon", ImageConstants.CALENDAR_BUTTON_ICON);
		UIManager.put("JXDatePicker.numColumns", 20);

		/*
		 * editor setting
		 */
		setFormats(dateFormat);
		hayashiDatePickerUI = new HayashiDatePickerUI();
		setUI(hayashiDatePickerUI);

		// JFormattedTextField
		editor = getEditor();
		editor.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 14));

		editor.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					setText(getText());
					transferFocus();
				} else if (e.getKeyChar() == KeyEvent.VK_0) {
					return;
				}
			}
		});

		// add a focus listener
		FocusListener focusListener = new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				editor.setBackground(ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						editor.selectAll();
					}
				});
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				setDate(getDate());
				editor.setBackground(ColorConstants.DEFAULT_TEXT_BACKGROUND_COLOR);

				if (getDate() == null) {
					editor.setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
				}
			}
		};

		editor.addFocusListener(focusListener);

		// 休みの日
		getMonthView().setFlaggedDates(getYasumiNohi());
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return yyyyMMdd
	 */
	public int getIntegerValue() {
		int invDate = 0;

		try {			
			invDate = DateUtils.getDateWithyyyymm(getText());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return invDate;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return yyyyMM
	 */
	public int getIntegerYearMonth() {
		
		String strDate = "" + getIntegerValue();
		
		if (strDate.length() == 8){
			return 0;
		} else {
			return Integer.parseInt(strDate.substring(0, 6));			
		}
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return yyyy
	 */
	public int getIntegerYear() {
		
		String strDate = "" + getIntegerValue();
		
		if (strDate.length() == 8){
			return Integer.parseInt(strDate.substring(0, 4));
		} else {
			return 0;
		}
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return MMdd
	 */
	public int getIntegerMonthDate() {
		
		String strDate = "" + getIntegerValue(); //yyyyMMdd
		
		if (strDate.length() == 8){
			return Integer.parseInt(strDate.substring(4, 8));
		} else {
			return 0;
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
	public String getText() {
		return editor.getText();
	}

	public void setDate(Date date) {
		super.setDate(date);
		if (date != null && dateFormat != null) {
			editor.setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
		}
	}

	public Date getDate() {
		return super.getDate();
	}
	

	public void setText(String t) {
		if (!t.isEmpty()) {
			t = StringUtils.getYYYYMM(t);

			try {
				setDate(DateUtils.convertToDate(t));
			} catch (Exception e) {
				getEditor().setText("");
				e.printStackTrace();
			}
		}
		
	}

	public void setText(int t) {
		if (t > 0) {
			setText("" + t);
		}
	}

	private Date[] getYasumiNohi() {
		List<Date> lstYasumi = new ArrayList<Date>();
		DateFormat dfm = new SimpleDateFormat("yyyyMMdd");

		for (HolidayVo dataVo : ApplicationConstants.LST_HOLIDAY_DATE) {
			Date date = null;
			try {
				date = dfm.parse(dataVo.getShujitsubi());
				lstYasumi.add(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		Date[] holidays = new Date[lstYasumi.size()];
		for (int i = 0; i < lstYasumi.size(); i++) {
			holidays[i] = lstYasumi.get(i);
		}

		return holidays;
	}

}
