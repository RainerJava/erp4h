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
import com.fas.common.constants.dbtable.MCtlConstants;
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
public class BaseDatePicker extends JXDatePicker {

	/** */
	private static final long serialVersionUID = 1L;

	/** */
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd(E)");
	protected JFormattedTextField editor;
	protected HayashiDatePickerUI hayashiDatePickerUI;
	
	/* 
	 * M_CTL	SYSTEM    	DATE_NEN
	 * 日付　年設定処理範囲
	 * ｼｽﾃﾑ日付が設定値内で且つ、入力された月日が設定値より小さい場合+1年にする。
	 */
	protected boolean isDeliveryChange = true;
	private int deliveryMMddFrom = 0;
	private int deliveryMMddTo = 0;

	public BaseDatePicker() {
		super();
		init();
	}

	public BaseDatePicker(Date selection, Locale locale) {
		super(selection, locale);
		init();
	}

	public BaseDatePicker(Date selected) {
		super(selected);
		init();
	}

	public BaseDatePicker(Locale locale) {
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
		
		//日付　年設定処理範囲
		if (isDeliveryChange){
			String dateNen = MCtlConstants.getValue("SYSTEMDATE_NEN"); //1201～1231
			if (dateNen.length() == 9){
				try {
					deliveryMMddFrom = Integer.parseInt(dateNen.substring(0,4));
					deliveryMMddTo = Integer.parseInt(dateNen.substring(5,9));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
			}
		}
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
		return DateUtils.getIntFromDate(getDate());
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
			return Integer.parseInt(strDate.substring(0, 6));
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
			String dateText = dateFormat.format(date);
			// System.out.println("setDate " + dateText);
			if (dateText.length() > 8) {
				if (isYasumiNohi(getIntegerValue())) {
					editor.setForeground(ColorConstants.CALENDAR_YASUMINOHI_COLOR);
				} else {
					if ("日".equalsIgnoreCase(DateUtils.getDayOfWeek(dateText))) {
						editor.setForeground(ColorConstants.CALENDAR_NICHIYOUBI_COLOR);
					} else if ("土".equalsIgnoreCase(DateUtils.getDayOfWeek(dateText))) {
						editor.setForeground(ColorConstants.CALENDAR_DOYOUBI_COLOR);
					} else {
						editor.setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
					}
				}
			} else {
				editor.setForeground(ColorConstants.DEFAULT_TEXT_FORE_COLOR);
			}
		}
	}
	
	public void setDateToday() {
		Date dateObfi = new Date();
		setDate(dateObfi);
	}

	public Date getDate() {
		return super.getDate();
	}

	public void setText(String t) {
		if (!t.isEmpty()) {
			
			t = StringUtils.getYYYYMMDD(t);
			if (DateUtils.isValidDate(t, "yyyyMMdd")){
				try {
					setDate(DateUtils.convertToDate(t));
				} catch (Exception e) {
					e.printStackTrace();
					this.getEditor().setText("");
				}
			} else {
				this.getEditor().setText("");
			}
		}
		
		//ｼｽﾃﾑ日付が設定値内で且つ、入力された月日が設定値より小さい場合+1年にする。
		if (DateUtils.getCurrentMonthDate() >= deliveryMMddFrom
				&& DateUtils.getCurrentMonthDate() <= deliveryMMddTo
				&& getIntegerMonthDate() < deliveryMMddFrom
				&& getIntegerYear() == DateUtils.getCurrentYear() ){

			setText(getIntegerValue() + 10000);
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

	private boolean isYasumiNohi(int yyyymmdd) {

		boolean isYasumi = false;

		String strDate = "" + yyyymmdd;
		/** 祝日の日のチェック */
		for (int i = 0; i < ApplicationConstants.LST_HOLIDAY_DATE.size(); i++) {
			HolidayVo dataVo = ApplicationConstants.LST_HOLIDAY_DATE.get(i);
			if (strDate.equalsIgnoreCase(dataVo.getShujitsubi().trim())) {
				isYasumi = true;
				break;
			}
		}

		return isYasumi;
	}

	//swingX1.6 chu*a lam duoc
	private String getYasumiNoTooltip(int yy, int mm, int dd) {

		String strYasumi = "";

		String strDate = yy + StringUtils.fillNumberMaxLen("" + mm, 2) + StringUtils.fillNumberMaxLen("" + dd, 2);
		/** 祝日の日のチェック */
		for (int i = 0; i < ApplicationConstants.LST_HOLIDAY_DATE.size(); i++) {
			HolidayVo dataVo = ApplicationConstants.LST_HOLIDAY_DATE.get(i);
			if (strDate.equalsIgnoreCase(dataVo.getShujitsubi().trim())) {
				strYasumi = dataVo.getShujitsumei();
				break;
			}
		}

		return strYasumi;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the isDeliveryChange
	 */
	public boolean isDeliveryChange() {
		return isDeliveryChange;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param isDeliveryChange the isDeliveryChange to set
	 */
	public void setDeliveryChange(boolean isDeliveryChange) {
		this.isDeliveryChange = isDeliveryChange;
	}

}
