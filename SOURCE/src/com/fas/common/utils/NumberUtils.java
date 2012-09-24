/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：NumberUtils.java
*
*     記述				：
*     
*     作成日			：2009/07/29   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public class NumberUtils {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public NumberUtils() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param decimalPlace
	 * @param dValue
	 * @return
	 */
	public static double getRoundUp(int decimalPlace, double dValue){
		 
		double r = 0;

		BigDecimal bd = new BigDecimal(dValue);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_UP);
		r = bd.doubleValue();
		 
		return r;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：四捨五入</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param decimalPlace
	 * @param dValue
	 * @return
	 */
	public static double getRoundHalfUp(int decimalPlace, double dValue){
		 
		double r = 0;

		BigDecimal bd = new BigDecimal(dValue);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		r = bd.doubleValue();
		 
		return r;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param decimalPlace
	 * @param dValue
	 * @return
	 */
	public static double getRoundFloor(int decimalPlace, double dValue) {
		 
		double r = 0;

		BigDecimal bd = new BigDecimal(dValue);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_FLOOR);
		r = bd.doubleValue();
		 
		return r;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param decimalPlace
	 * @param dValue
	 * @return
	 */
	public static double getRoundDown(int decimalPlace, double dValue) {
		 
		double r = 0;

		BigDecimal bd = new BigDecimal(dValue);
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_DOWN);
		r = bd.doubleValue();
		 
		return r;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param dValue
	 * @return
	 */
	public static String getFormatNumber(double dValue) {

		String pattern = "###,###.###";
		
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(dValue);
		
		if (output.indexOf(".") > 0) {
			output.substring(0, output.indexOf("."));
		}
		
		return output;

	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param dValue
	 * @return
	 */
	public static String getCurrencyFormatRoundUpNumber(double dValue) {

		String pattern = "###,###.###";
		double dValueRoundUp = getRoundHalfUp(0, dValue);
		
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(dValueRoundUp);
		
		if (output.indexOf(".") > 0) {
			output = output.substring(0, output.indexOf("."));
		}
		
		return output;

	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param dValue
	 * @return
	 */
	public static String getCurrencyFormatFloorNumber(double dValue) {

		String pattern = "###,###.###";
		double dValueFloor = getRoundFloor(0, dValue);
		
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(dValueFloor);
		
		if (output.indexOf(".") > 0) {
			output = output.substring(0, output.indexOf("."));
		}
		
		return output;

	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param dValue
	 * @return
	 */
	public static int getIntFromDouble(double dValue) {
		
		String strValue = Double.toString(getRoundFloor(0, dValue));
		strValue = strValue.substring(0, strValue.indexOf("."));
		
		return Integer.parseInt(strValue);
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strValue
	 * @return
	 */
	public static int getIntFromString(String strValue) {
		
		int iValue = 0;
		try {
			iValue = Integer.parseInt(strValue);
		} catch (NumberFormatException e) {
			iValue = 0;
		}
		return iValue;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param strValue
	 * @return
	 */
	public static int getIntFromStringRemoveComma(String strValue) {
		strValue = strValue.replace(",", "");
		int iValue = 0;
		try {
			iValue = Integer.parseInt(strValue);
		} catch (NumberFormatException e) {
			iValue = 0;
		}
		return iValue;
	}
	
	public static float getFloatFromString(String strValue)
	{
		float iValue = 0;
		try {
			iValue = Float.parseFloat(strValue);
		} catch (NumberFormatException e) {
			iValue = 0;
		}
		return iValue;
	}
	
	public static double getDoubleFromString(String strValue)
	{
		double iValue = 0;
		try {
			iValue = Double.parseDouble(strValue);
		} catch (NumberFormatException e) {
			iValue = 0;
		}
		return iValue;
	}
	
	public static Double getDoubleAllowNullFromString(String strValue)
	{		
		Double iValue = null;
		try {
			iValue = Double.parseDouble(strValue);
		} catch (NumberFormatException e) {
		}
		return iValue;
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
		System.out.println(getRoundUp(0, 1.635)); // lam tron len
		System.out.println(getRoundUp(0, 1.2234)); // lam tron len
		
		System.out.println(getRoundHalfUp(0, 1.635)); // lam tron so gan nhat
		System.out.println(getRoundHalfUp(0, 1.2234)); // lam tron so gan nhat
		
		System.out.println((int)2.63425);
	}
}

