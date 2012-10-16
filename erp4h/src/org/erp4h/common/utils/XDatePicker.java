package org.erp4h.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.erp4h.common.SystemParameters;
import org.jdesktop.swingx.JXDatePicker;
import java.awt.Dimension;

/**
 * JXDatePicker voi giao dien tieng viet
 * 
 * @author hieulv
 * 
 */
public class XDatePicker extends JXDatePicker {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[] thu;

	public XDatePicker() {
		setMinimumSize(new Dimension(220, 22));
		setMaximumSize(new Dimension(220, 22));
		setBorder(null);
		getEditor().setText("ngay");
		setPreferredSize(new Dimension(220, 22));
		thu = new String[] { "thu 2", "thu 3", "thu 4", "thu 5", "thu 6",
				"thu 7", "Chu nhat" };

		setDate(SystemParameters.CURRENT_PC_TIME);
		setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		setLocale(SystemParameters.VN_LOCALE);
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd/MM/yyyy");
		setLinkDay(
				SystemParameters.CURRENT_PC_TIME,
				"Hom nay: " + getDayOfWeek() + " ngay "
						+ dayFormat.format(SystemParameters.CURRENT_PC_TIME));

	}

	private String getDayOfWeek() {
		String strDayOfWeek = null;
		int i = getMonthView().getCalendar().get(Calendar.DAY_OF_WEEK);
		System.out.println(getMonthView().getCalendar().get(Calendar.DAY_OF_WEEK));
		switch (i) {
		case 0:
			strDayOfWeek = thu[0];
			break;
		case 1:
			strDayOfWeek = thu[1];
			break;
		case 2:
			strDayOfWeek = thu[2];
			break;
		case 3:
			strDayOfWeek = thu[3];
			break;
		case 4:
			strDayOfWeek = thu[4];
			break;
		case 5:
			strDayOfWeek = thu[5];
			break;
		case 6:
			strDayOfWeek = thu[6];
			break;
		}
		return strDayOfWeek;
	}
}
