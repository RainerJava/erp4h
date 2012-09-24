package com.fas.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fas.common.constants.ApplicationConstants;
import com.fas.vo.holiday.HolidayVo;

public class DateUtils {
	private final static String[] day = { "日", "月", "火", "水", "木", "金", "土" };

	private final static String[] arrDays = { "(日)", "(月)", "(火)", "(水)", "(木)", "(金)", "(土)" };

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>int(yyyyMMdd)</DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return
	 */
	public static int getCurrentDate() {
		int date = 0;
		try {
			String pattern = "yyyyMMdd";
			String result = new SimpleDateFormat(pattern).format(new Date());
			date = Integer.parseInt(result);

		} catch (Exception ex) {
			ex.fillInStackTrace();
		}
		return date;
	}
	
	public static int getIntDate() {
		return getCurrentDate();
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
	public static int getIntFromDate(Date date) {
		int intValue = 0;
		try {
			String pattern = "yyyyMMdd";
			String result = new SimpleDateFormat(pattern).format(date);
			intValue = Integer.parseInt(result);
			
		} catch (Exception ex) {
			ex.fillInStackTrace();
		}
		return intValue;
	}
	

	

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return yyyyMM
	 */
	public static int getCurrentYearMonth() {
		int date = 0;
		try {
			String pattern = "yyyyMM";
			String result = new SimpleDateFormat(pattern).format(new Date());
			date = Integer.parseInt(result);

		} catch (Exception ex) {
		}
		return date;
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
	public static int getCurrentYear() {
		int date = 0;
		try {
			String pattern = "yyyy";
			String result = new SimpleDateFormat(pattern).format(new Date());
			date = Integer.parseInt(result);
			
		} catch (Exception ex) {
		}
		return date;
	}
	
	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return MMdd
	 */
	public static int getCurrentMonthDate() {
		int date = 0;
		try {
			String pattern = "MMdd";
			String result = new SimpleDateFormat(pattern).format(new Date());
			date = Integer.parseInt(result);
			
		} catch (Exception ex) {
		}
		return date;
	}

	public static int getPrevMonth(int yyyyMM) {
		int date = 0;
		try {
			String month = yyyyMM + "";
			String sDate = addMonth(month.substring(0, 4) + "/" + month.substring(4, 6) + "/01", -1);
			sDate = sDate.replaceAll("/", "");
			date = Integer.parseInt(sDate.substring(0, 6));
		} catch (Exception ex) {
		}
		return date;
	}
	
	public static Date getPrevMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		return calendar.getTime();
	}

	public static int getNextMonth(int yyyyMM) {
		int date = 0;
		try {
			String month = yyyyMM + "";
			String sDate = addMonth(month.substring(0, 4) + "/" + month.substring(4, 6) + "/01", 1);
			sDate = sDate.replaceAll("/", "");
			date = Integer.parseInt(sDate.substring(0, 6));
		} catch (Exception ex) {
		}
		return date;
	}
	
	public static Date getNextMonth(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}
	
	public static Date getNextYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 1);
		return calendar.getTime();
	}
	
	public static Date getPreYear(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, -1);
		return calendar.getTime();
	}

	public static String getCurrentDateString() {
		String pattern = "yyyyMMdd";
		String result = new SimpleDateFormat(pattern).format(new Date());
		return result;
	}
	
	public static long getIntTime() {
		String pattern = "HHmmssSS";
		String result = new SimpleDateFormat(pattern).format(new Date());
		return Long.parseLong(result);
	}
	
	public static String getTimeWithSplit(int HHmmss) {
		if (HHmmss < 9999)
			return "";
		String result = "" + HHmmss;
		result = StringUtils.padLeft(result, '0', 6);
		
		result = result.substring(0, 2) + ":" + result.substring(2, 4) + ":" + result.substring(4, 6);
		
		return result;
	}

	public static int getITime() {
		String pattern = "HHmmss";
		String result = new SimpleDateFormat(pattern).format(new Date());
		return Integer.parseInt(result);
	}
	
	public static String getTime() {
		String pattern = "HHmmss";
		String result = new SimpleDateFormat(pattern).format(new Date());
		return result;
	}

	public static String getDateCurrentWithSplit() {
		String pattern = "yyyy/MM/dd";
		String result = new SimpleDateFormat(pattern).format(new Date());
		return result;
	}

	public static String getCurrentDateHour() {
		String pattern = "yyyy/MM/dd HH:mm:ss";
		String result = new SimpleDateFormat(pattern).format(new Date());
		return result;
	}

	public static String getCurrentDateWithSplitYobi() {
		String pattern = "yyyy/MM/dd";
		String result = new SimpleDateFormat(pattern).format(new Date());
		result += "(" + DateUtils.getDayOfWeek(result) + ")";
		return result;
	}

	public static String getDateWithSplit(int yyyymmdd) {
		if (yyyymmdd < 100000)
			return "";

		String patternIn = "yyyyMMdd";
		String patternOut = "yyyy/MM/dd";
		SimpleDateFormat formatter = new SimpleDateFormat(patternIn);
		Date dateIn;
		try {
			dateIn = formatter.parse(yyyymmdd + "");
		} catch (ParseException e) {
			dateIn = new Date();
			e.printStackTrace();
		}
		String result = new SimpleDateFormat(patternOut).format(dateIn);
		return result;
	}

	public static String getDateWithSplitYobi(int yyyymmdd) {
		String patternIn = "yyyyMMdd";
		String patternOut = "yyyy/MM/dd";
		SimpleDateFormat formatter = new SimpleDateFormat(patternIn);
		Date dateIn;
		
		if (yyyymmdd < 99999)
			return "";
		
		try {
			dateIn = formatter.parse(yyyymmdd + "");
		} catch (ParseException e) {
			dateIn = new Date();
			e.printStackTrace();
		}
		String result = new SimpleDateFormat(patternOut).format(dateIn);
		result += "(" + DateUtils.getDayOfWeek(yyyymmdd) + ")";
		return result;
	}
	
	public static String getDateNihonWithSplitYobi(int yyyymmdd) {
		
		String strReturn = "";
		String strOut = yyyymmdd + "";

		if (strOut.length() >7) {
			strReturn = strOut.substring(0, 4) + "年" + strOut.substring(4, 6) + "月" + strOut.substring(6, 8) + "日";
		}
		strReturn += "(" + DateUtils.getDayOfWeek(yyyymmdd) + ")";
		return strReturn;
	}

	public static String getDateWithSplitYobi(String yyyymmdd) {
		String pattern = "yyyy/MM/dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date dateIn;
		try {
			dateIn = formatter.parse(yyyymmdd + "");
		} catch (ParseException e) {
			dateIn = new Date();
			e.printStackTrace();
		}
		String result = new SimpleDateFormat(pattern).format(dateIn);
		result += "(" + DateUtils.getDayOfWeek(yyyymmdd) + ")";
		return result;
	}
	
	public static String getDateWithSplitYobi(Date dateIn) {
		String pattern = "yyyy/MM/dd";
		String result = new SimpleDateFormat(pattern).format(dateIn);
		result += "(" + DateUtils.getDayOfWeek(dateIn) + ")";
		return result;
	}
	
	public static String getDateWithKanji(int yyyymm) {
		if (yyyymm < 99999)
			return "";
		
		String result = yyyymm + "";
		result =  result.substring(0, 4) + "年" + result.substring(4, 6)  + "月";
		
		return result;
	}
	
	public static String getDateWithKanji(String yyyymm) {
		if (yyyymm.length() != 6)
			return "";
		
		String result = yyyymm;
		result =  result.substring(0, 4) + "年　" + result.substring(4, 6)  + "月　";
		
		return result;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param yyyy
	 *            /mm/dd
	 * @return int(yyyyMMdd)
	 */
	public static int getDateWithyyyymmdd(String yyyymmdd) {
		String patternIn = "yyyy/MM/dd";
		String patternOut = "yyyyMMdd";
		SimpleDateFormat formatter = new SimpleDateFormat(patternIn);
		Date dateIn;
		
		if (yyyymmdd.length() > 10) {
			yyyymmdd = yyyymmdd.substring(0, 10);
		}
		
		try {
			dateIn = formatter.parse(yyyymmdd + "");
		} catch (ParseException e) {
			dateIn = new Date();
			//eat the exception :D
			//e.printStackTrace();
			return -1;
		}
		String result = new SimpleDateFormat(patternOut).format(dateIn);
		return Integer.parseInt(result);
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param yyyymmdd
	 * @return
	 */
	public static String getNihonDate(String yyyymmdd) {
		
		String strReturn = "";
		String strOut = yyyymmdd + "";

		if (strOut.length() >7) {
			strReturn = strOut.substring(0, 4) + "年" + strOut.substring(4, 6) + "月" + strOut.substring(6, 8) + "日";
		}

		return strReturn;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param yyyymmdd
	 * @return
	 */
	public static String getNihonDateWithBun(String yyyymmdd) {
		
		String strReturn = "";
		String strOut = yyyymmdd + "";

		if (strOut.length() >7) {
			strReturn = strOut.substring(0, 4) + "年" + strOut.substring(4, 6) + "月" + "分";
		}

		return strReturn;
	}

	
	public static int getDateWithyymmdd(String yymmdd) {
		String patternIn = "yy/MM/dd";
		String patternOut = "yyMMdd";
		SimpleDateFormat formatter = new SimpleDateFormat(patternIn);
		Date dateIn;
		try {
			dateIn = formatter.parse(yymmdd + "");
		} catch (ParseException e) {
			dateIn = new Date();
			e.printStackTrace();
		}
		String result = new SimpleDateFormat(patternOut).format(dateIn);
		return Integer.parseInt(result);
	}

	
	public static int getDateWithyyyymm(String yyyymm) {
		String patternIn = "yyyy/MM";
		String patternOut = "yyyyMM";
		SimpleDateFormat formatter = new SimpleDateFormat(patternIn);
		Date dateIn;
		try {
			dateIn = formatter.parse(yyyymm + "");
		} catch (ParseException e) {
			dateIn = new Date();
			//eat the exception :D
			//e.printStackTrace();
			return -1;
		}
		String result = new SimpleDateFormat(patternOut).format(dateIn);
		return Integer.parseInt(result);
	}
	
	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param yyyymmdd
	 * @param addDay
	 * @return
	 */
	public static int getDateWithyyyymmdd(String yyyymmdd, int addDay) {
		String patternIn = "yyyy/MM/dd";
		String patternOut = "yyyyMMdd";
		SimpleDateFormat formatter = new SimpleDateFormat(patternIn);
		Date dateIn;
		try {
			dateIn = formatter.parse(yyyymmdd + "");
		} catch (ParseException e) {
			dateIn = new Date();
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateIn);
		cal.add(Calendar.DATE, addDay);
		String result = new SimpleDateFormat(patternOut).format(cal.getTime());
		return Integer.parseInt(result);
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param yyyymmdd
	 * @return
	 */
	public static String getFormatDateYYMMDD(String yyyymmdd) {

		String strReturn = "";

		if (yyyymmdd.length() > 7) {
			strReturn = yyyymmdd.substring(2,4) + "/" + yyyymmdd.substring(4,6) + "/" + yyyymmdd.substring(6,8);
		}
		
		return strReturn;
	}
	
	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>日月火水木金土の週間</DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param yyyy
	 *            /MM/dd
	 * @return
	 */
	public static String getDayOfWeek(String yyyyMMdd) {
		String pattern = "yyyy/MM/dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date utilDate = null;
		GregorianCalendar gc = new GregorianCalendar();
		try {
			utilDate = formatter.parse(yyyyMMdd);
		} catch (ParseException e) {
			return "";
		}
		gc.setTime(utilDate);
		return day[gc.get(Calendar.DAY_OF_WEEK) - 1];
	}

	public static String getDayOfWeek(int yyyyMMdd) {
		String pattern = "yyyyMMdd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		java.util.Date utilDate = null;
		GregorianCalendar gc = new GregorianCalendar();
		try {
			utilDate = formatter.parse(yyyyMMdd + "");
		} catch (ParseException e) {
			return "";
		}
		gc.setTime(utilDate);
		return day[gc.get(Calendar.DAY_OF_WEEK) - 1];
	}
	
	public static String getDayOfWeek(Date utilDate) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(utilDate);
		return day[gc.get(Calendar.DAY_OF_WEEK) - 1];
	}

	@SuppressWarnings("deprecation")
	public static boolean getCompareDate(Date date1, Date date2) {
		if ((date1.getYear() == date2.getYear()) && (date1.getMonth() == date2.getMonth()) && (date1.getDate() == date2.getDate())) {
			return true;

		} else {
			return false;

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
	 * @param strDate
	 * @return
	 */
	public static String getFirstDayOfMonth(String strDate) {

		String strFirstDayOfMonth = "";

		try {

			SimpleDateFormat sdf;
			Calendar cal;
			Date date;
			sdf = new SimpleDateFormat("yyyy/MM/dd");
			date = sdf.parse(strDate);
			cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			strFirstDayOfMonth = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strFirstDayOfMonth;

	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strDate
	 * @return
	 */
	public static int getLDayOfMonth(String strDate) {

		int iLDayOfMonth = 0;

		try {

			SimpleDateFormat sdf;
			Calendar cal;
			Date date;
			sdf = new SimpleDateFormat("yyyyMMdd");
			date = sdf.parse(strDate);
			cal = Calendar.getInstance();
			cal.setTime(date);
			int lastDate = cal.getActualMaximum(Calendar.DATE);
			cal.set(Calendar.DATE, lastDate);

			iLDayOfMonth = Integer.parseInt(sdf.format(cal.getTime()));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return iLDayOfMonth;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strDate
	 * @return
	 */
	public static int getIFirstDayOfMonth(String strDate) {

		int iFDayOfMonth = 0;

		try {

			SimpleDateFormat sdf;
			Calendar cal;
			Date date;
			sdf = new SimpleDateFormat("yyyyMMdd");
			date = sdf.parse(strDate);
			cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, 1);

			iFDayOfMonth = Integer.parseInt(sdf.format(cal.getTime()));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return iFDayOfMonth;

	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strDate
	 * @return
	 */
	public static String getLastDayOfMonth(String strDate) {

		String strLastDayOfMonth = "";

		try {

			SimpleDateFormat sdf;
			Calendar cal;
			Date date;
			sdf = new SimpleDateFormat("yyyy/MM/dd");
			date = sdf.parse(strDate);
			cal = Calendar.getInstance();
			cal.setTime(date);
			int lastDate = cal.getActualMaximum(Calendar.DATE);
			cal.set(Calendar.DATE, lastDate);

			strLastDayOfMonth = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strLastDayOfMonth;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strDate1
	 * @param strDate2
	 * @return
	 */
	public static int getMonthBetweenDate(String strDate1, String strDate2) {

		int iMonth = 0;

		try {

			SimpleDateFormat sdf;
			Calendar cal;
			sdf = new SimpleDateFormat("yyyy/MM/dd");
			cal = Calendar.getInstance();

			Date date1 = sdf.parse(strDate1);
			cal.setTime(date1);

			int iYear1 = cal.get(Calendar.YEAR);
			int iMonth1 = cal.get(Calendar.MONTH);

			Date date2 = sdf.parse(strDate2);
			cal.setTime(date2);
			int iYear2 = cal.get(Calendar.YEAR);
			int iMonth2 = cal.get(Calendar.MONTH);

			iMonth = (iYear2 - iYear1) * 12 + iMonth2 - iMonth1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return iMonth;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strDate
	 * @return
	 */
	public static String addMonth(String strDate, int iMonth) {

		String strResult = "";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			Date date = sdf.parse(strDate);
			cal.setTime(date);

			cal.add(Calendar.MONTH, iMonth);

			strResult = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strResult;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strDate
	 * @return
	 */
	public static String getDayName(String strDate) {

		try {
			if (strDate == null || "".equalsIgnoreCase(strDate.trim()) || "0".equalsIgnoreCase(strDate.trim())) {

				return "";

			} else {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Calendar cal = Calendar.getInstance();
				Date date = sdf.parse(strDate);
				cal.setTime(date);
				int day = cal.get(Calendar.DAY_OF_WEEK);

				return arrDays[day - 1];

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
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
	 * @param strDate
	 * @return
	 */
	public static String getPrevWeek(String strDate) {

		String strFDayPreWeek = "";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			Date date = sdf.parse(strDate);
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_YEAR, -7);

			strFDayPreWeek = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strFDayPreWeek;
	}

	public static String getNextWeek(String strDate) {

		String strFDayNextWeek = "";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			Date date = sdf.parse(strDate);
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_YEAR, 7);

			strFDayNextWeek = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strFDayNextWeek;
	}

	public static Date getPrevWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		return calendar.getTime();
	}
	
	public static Date getNextWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		return calendar.getTime();
	}
	

	public static String getFirstDayOfWeek(String strDate) {

		String strFDayOfWeek = "";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			Date date = sdf.parse(strDate);
			cal.setTime(date);

			while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				
				cal.add(Calendar.DAY_OF_YEAR, -1);
			}

			strFDayOfWeek = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strFDayOfWeek;
	}
	
	public static Date getFirstDayOfWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}
		return calendar.getTime();
	}

	public static String getLastDayOfWeek(String strDate) {

		String strLDayOfWeek = "";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			Date date = sdf.parse(strDate);
			cal.setTime(date);

			while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
				cal.add(Calendar.DAY_OF_YEAR, 1);
			}

			strLDayOfWeek = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strLDayOfWeek;
	}
	
	public static Date getLastDayOfWeek(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		return calendar.getTime();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strDate
	 * @return
	 */
	public static String getNextDay(String strDate) {

		String strNextDay = "";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			Date date = sdf.parse(strDate);
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_YEAR, 1);

			strNextDay = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strNextDay;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strDate
	 * @return
	 */
	public static String getPreDay(String strDate) {

		String strPreDay = "";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			Date date = sdf.parse(strDate);
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_YEAR, -1);

			strPreDay = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strPreDay;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strDate
	 *            yyyy/MM/dd
	 * @param iDay
	 * @return
	 */
	public static String addDay(String strDate, int iDay) {

		String strResult = "";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			Date date = sdf.parse(strDate);
			cal.setTime(date);

			cal.add(Calendar.DAY_OF_YEAR, iDay);

			strResult = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strResult;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param iDate
	 * @return
	 */
	public static String getDayOfWeekFormat(String strDate) {

		String strReturn = "";

		try {
			if (strDate.length() < 8) {
				strReturn = "";
			} else {
				strReturn += strDate.substring(0, 4);
				strReturn += "/" + strDate.substring(4, 6);
				strReturn += "/" + strDate.substring(6, 8);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Calendar cal = Calendar.getInstance();
				Date date = sdf.parse(strReturn);
				cal.setTime(date);
				int day = cal.get(Calendar.DAY_OF_WEEK);

				strReturn = strReturn.substring(2);
				strReturn += arrDays[day - 1];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strReturn;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param iDate
	 * @return
	 */
	public static String getFullDayOfWeekFormat(String strDate) {

		String strReturn = "";

		try {
			if (strDate.length() < 8) {
				strReturn = "";
			} else {
				strReturn += strDate.substring(0, 4);
				strReturn += "/" + strDate.substring(4, 6);
				strReturn += "/" + strDate.substring(6, 8);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				Calendar cal = Calendar.getInstance();
				Date date = sdf.parse(strReturn);
				cal.setTime(date);
				int day = cal.get(Calendar.DAY_OF_WEEK);

				strReturn += arrDays[day - 1];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strReturn;
	}

	public static String getSengetsuDaiYonNichi(int yyyyMM) {

		String strFDayOfWeek = "";
		String strDate = yyyyMM + "";
		strDate = strDate.substring(0, 4) + "/" + strDate.substring(4, 6) + "/01";
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar cal = Calendar.getInstance();
			Date date = sdf.parse(strDate);
			cal.setTime(date);

			while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				cal.add(Calendar.DAY_OF_YEAR, -1);
			}

			strFDayOfWeek = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strFDayOfWeek;
	}

	public static String getRaigetsuDaiIchiDo(int yyyyMM) {

		String strFDayOfWeek = "";
		String strDate = yyyyMM + "";
		strDate = strDate.substring(0, 4) + "/" + strDate.substring(4, 6) + "/01";
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat sdfFist = new SimpleDateFormat("yyyy/MM/01");
			Calendar cal = Calendar.getInstance();
			Date date = sdf.parse(strDate);
			cal.setTime(date);
			cal.add(Calendar.MONTH, 1);

			strDate = sdfFist.format(cal.getTime());

			date = sdf.parse(strDate);
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_YEAR, -1);

			while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
				cal.add(Calendar.DAY_OF_YEAR, 1);
			}

			strFDayOfWeek = sdf.format(cal.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return strFDayOfWeek;
	}

	/**
	 * 指定した日付を指定した形式で返す
	 * 
	 * @param pDate
	 *            日付
	 * @param format
	 *            形式
	 * @return フォーマット後の日付文字列
	 */
	public static String dateFormat(Date pDate, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(pDate);
	}

	/**
	 * 指定した日付(String)をDATEクラスとして返す
	 * 
	 * @param _pstrDate
	 *            String 日付
	 * @return Date
	 * @throws Exception
	 */
	public static Date convertToDate(String _pstrDate) throws Exception {
		DateFormat df = DateFormat.getDateInstance();
		if (_pstrDate.length() == 8)//is format yyyyMMdd
			_pstrDate = _pstrDate.substring(0, 4) + "/" + _pstrDate.substring(4, 6) + "/" + _pstrDate.substring(6, 8);
		else if (_pstrDate.length() > 8)//is format yyyy/MM/dd ...
			_pstrDate = _pstrDate.substring(0, 4) + "/" + _pstrDate.substring(5, 7) + "/" + _pstrDate.substring(8, 10);
		else 
			return null;
		return df.parse(_pstrDate);
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
	public static String getCurrentYYYYMM() {

		String strDate = "2009/11";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");

			Calendar cal = Calendar.getInstance();
			Date date = new Date();
			cal.setTime(date);

			strDate = sdf.format(cal.getTime());

		} catch (Exception e) {
			strDate = "";
			e.printStackTrace();
		}

		return strDate;
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
	public static String getCurrentTimeHHmmss() {

		String strTime = "20:10:00";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

			Calendar cal = Calendar.getInstance();
			Date date = new Date();
			cal.setTime(date);

			strTime = sdf.format(cal.getTime());

		} catch (Exception e) {
			strTime = "";
			e.printStackTrace();
		}

		return strTime;
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
	public static String getCurrentDateTime() {

		String strTime = "2009/10/10 20:10:00";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			Calendar cal = Calendar.getInstance();
			Date date = new Date();
			cal.setTime(date);

			strTime = sdf.format(cal.getTime());

		} catch (Exception e) {
			strTime = "";
			e.printStackTrace();
		}

		return strTime;
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
	public static String getCurrentMM() {

		String strDate = "01";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("MM");

			Calendar cal = Calendar.getInstance();
			Date date = new Date();
			cal.setTime(date);

			strDate = sdf.format(cal.getTime());

		} catch (Exception e) {
			strDate = "";
			e.printStackTrace();
		}

		return strDate;
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
	public static String getCurrentyyyy() {

		String strDate = "2009";

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

			Calendar cal = Calendar.getInstance();
			Date date = new Date();
			cal.setTime(date);

			strDate = sdf.format(cal.getTime());

		} catch (Exception e) {
			strDate = "";
			e.printStackTrace();
		}

		return strDate;
	}
	
	public static int getMinusMonth(int iMonth) {

		int iDate = 0;

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			Calendar cal = Calendar.getInstance();
			Date date = new Date();
			cal.setTime(date);
			cal.add(Calendar.MONTH, 0 - iMonth);
			
			iDate = Integer.parseInt(sdf.format(cal.getTime()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return iDate;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strDate
	 * @param strFormat
	 * @return
	 */
	public static boolean isValidFormat(String strDate, String strFormat) {

		try {

			strDate = strDate.replaceAll("/", "-");

			SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
			Date date = formatter.parse(strDate);
			String strDateOut = formatter.format(date);

			if (strDate.equalsIgnoreCase(strDateOut)) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			// e.printStackTrace();
			return false;
		} catch (Exception ie) {
			return false;
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
	 * @param strDate
	 * @return
	 */
	public static String getGetsubi(String strDate) {

		String strReturn = null;

		try {

			SimpleDateFormat sdfout = new SimpleDateFormat("MMdd");
			SimpleDateFormat sdfin = new SimpleDateFormat("yyyy/MM/dd");

			Calendar cal = Calendar.getInstance();
			Date date = sdfin.parse(strDate);
			cal.setTime(date);

			strReturn = sdfout.format(cal.getTime());

		} catch (Exception e) {
			strReturn = null;
			e.printStackTrace();
		}

		return strReturn;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strDate
	 * @return
	 */
	public static String getFullGetsubi(String strDate) {

		String strReturn = "";

		try {

			SimpleDateFormat sdfout = new SimpleDateFormat("yyyy");
			Calendar cal = Calendar.getInstance();
			Date date = new Date();
			cal.setTime(date);

			strReturn = sdfout.format(cal.getTime());

			strReturn = strReturn + "/" + strDate.substring(0, 2) + "/" + strDate.substring(2, 4);

		} catch (Exception e) {
			strReturn = "";
			e.printStackTrace();
		}

		return strReturn;
	}

	/**
	 * @param intDate
	 * @return
	 */
	public static int getWarekiFromYYYYMMDD(int intDate) {
		String strReturn = "";
		if (intDate <= 9999999)
			return 0;

		if (intDate >= 18691119 && intDate <= 19120729) {
			strReturn += intDate - 18680000;
			strReturn = StringUtils.padLeft(strReturn, '0', 6);
			strReturn = StringUtils.padLeft(strReturn, '1', 7);
		} else if (intDate >= 19120730 && intDate <= 19251225) {
			strReturn += intDate - 19110000;
			strReturn = StringUtils.padLeft(strReturn, '0', 6);
			strReturn = StringUtils.padLeft(strReturn, '2', 7);
		} else if (intDate >= 19251226 && intDate <= 19890107) {
			strReturn += intDate - 19250000;
			strReturn = StringUtils.padLeft(strReturn, '0', 6);
			strReturn = StringUtils.padLeft(strReturn, '3', 7);
		} else if (intDate >= 19890108 && intDate <= 99991231) {
			strReturn += intDate - 19880000;
			strReturn = StringUtils.padLeft(strReturn, '0', 6);
			strReturn = StringUtils.padLeft(strReturn, '4', 7);
		}

		return Integer.parseInt(strReturn);
	}

	/**
	 * @param strDate
	 * @return
	 */
	public static String getWarekiFromYYYYMMDD(String strDate) {
		int intReturn = 0;
		if (StringUtils.isValid(strDate) && strDate.length() == 8)
			intReturn = getWarekiFromYYYYMMDD(Integer.parseInt(strDate));

		return "" + intReturn;
	}

	/**
	 * @param intDate
	 * @return
	 */
	public static int getReirekiFromJYYMMDD(int intDate) {
		int intResult = 0;
		if (intDate <= 999999)
			return 0;

		if (intDate >= 1000000 && intDate <= 1999999) {
			intResult = intDate - 1000000 + 18680000;
		} else if (intDate >= 2000000 && intDate <= 2999999) {
			intResult = intDate - 2000000 + 19110000;
		} else if (intDate >= 3000000 && intDate < 4000000) {
			intResult = intDate - 3000000 + 19250000;
		} else if (intDate >= 4000000) {
			intResult = intDate - 4000000 + 19880000;
		}

		return intResult;
	}

	/**
	 * @param strDate
	 * @return
	 */
	public static String getReirekiFromJYYMMDD(String strDate) {
		int intResult = 0;

		if (StringUtils.isValid(strDate) && strDate.length() == 7)
			intResult = getReirekiFromJYYMMDD(Integer.parseInt(strDate));

		return "" + intResult;

	}
	
	/**
	 * @param date		: JYYMMDD
	 * @param format	: MM-dd-yyyy, MM.dd.yyyy, dd.MM.yyyy, MM/dd/yyyy
	 * @return
	 */
	public static boolean isValidJapanDate(String date) {
		date = getReirekiFromJYYMMDD(date);
		
		date = getDateWithSplit(Integer.parseInt(date));
		
		return isValidDate(date, "yyyy/MM/dd");
	}

	/**
	 * @param date
	 * @param format : MM-dd-yyyy, MM.dd.yyyy, dd.MM.yyyy, MM/dd/yyyy
	 * @return
	 */
	public static boolean isValidDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		Date testDate = null;

		try {
			testDate = sdf.parse(date);
		} catch (ParseException e) {
			return false;
		}

		// dateformat.parse will accept any date as long as it's in the format
		// you defined, it simply rolls dates over, for example, december 32
		// becomes jan 1 and december 0 becomes november 30
		// This statement will make sure that once the string
		// has been checked for proper formatting that the date is still the
		// date that was entered, if it's not, we assume that the date is invalid
		if (!sdf.format(testDate).equals(date)) {
			return false;
		}

		return true;
	} // end isValidDate

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strNengetsu
	 * @return
	 */
	public static String getNengetsuFormat(String strNengetsu) {
		
		String strReturn  = "";
		if (strNengetsu.length() > 3) {
			strReturn = strNengetsu.substring(0, 2) + "年" + strNengetsu.substring(2, 4) + "月"; 
		}
		return strReturn;
	}
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strNengetsu
	 * @return
	 */
	public static String getMonthDay(String yyyymmdd) {

		if(yyyymmdd.indexOf("20") == 0 && yyyymmdd.length() == 8)
		{
			return yyyymmdd.substring(4, 6) + "/" + yyyymmdd.substring(6, 8);	
		}else{
			return "";
		}
		
	}
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strDate
     * @return
     */
    public static boolean isYasumiNohi(String strDate) {
    	
    	boolean isYasumi = false;

     	/**祝日の日のチェック */
    	for (int i = 0; i < ApplicationConstants.LST_HOLIDAY_DATE.size(); i ++) {
    		HolidayVo dataVo = ApplicationConstants.LST_HOLIDAY_DATE.get(i);
    		if (strDate.equalsIgnoreCase(dataVo.getShujitsubi().trim())) {
				isYasumi = true;
				break;
    		}
    	}
    	
    	return isYasumi;
    }
  	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getMinusMonth(6));
	}

}