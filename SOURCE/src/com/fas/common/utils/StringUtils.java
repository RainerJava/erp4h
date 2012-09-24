package com.fas.common.utils;

import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.fas.common.constants.SystemConstants;

public class StringUtils {

	private StringUtils() {
	}

	/**
	 * Converts a value to a string. If the value is null then the default value is returned.
	 * 
	 * @param value
	 *            the value to convert
	 * @param defValue
	 *            default value which to return if value is null
	 * @return returns a String representation of the value or null if value is null
	 */
	public static String toString(Object value, String defValue) {
		return ((value != null) ? value.toString() : defValue);
	}

	/**
	 * Converts a value to a string. If the value is null then null will be returned.
	 * 
	 * @param value
	 *            the value to convert
	 * @return returns a String representation of the value or null if value is null
	 */
	public static String toString(Object value) {
		return toString(value, null);
	}

	/**
	 * Converts an array of objects to a string.
	 * 
	 * @param array
	 *            array of objects
	 * @param defValue
	 *            default value which to return if array is null
	 * @return returns a String representation of the array or the defaultValue if array is null
	 */
	public static String toString(Object[] array, String defValue) {
		String s = arrayToString(array, "/");
		return (s != null ? s : defValue);
	}

	/**
	 * Converts an array of objects to a string.
	 * 
	 * @param array
	 *            array of objects
	 * @return returns a String representation of the array or null if the array is null
	 */
	public static String toString(Object[] array) {
		return toString(array, null);
	}

	public static boolean containsMatch(String val, String pattern) {
		String rep = val.replaceFirst(pattern, "###match###");

		if (!rep.equals(val)) {
			return true;
		}
		return false;
	}

	/**
	 * Converts a value to a string. if the value is null an empty string is returned.
	 * 
	 * @param value
	 *            the value to convert
	 * @return returns a String representation of the Object or an empty stringif o is null
	 */
	public static String valueOf(Object value) {
		return toString(value, "");
	}

	/**
	 * Converts an objects to a string.
	 * 
	 * @param array
	 *            array of objects
	 * @return returns a String representation of the array or an empty String if the array is null
	 */
	public static String valueOf(Object[] array) {
		return toString(array, "");
	}

	/**
	 * Returns the preferred String if it is not empty ot the alternative String otherwise.
	 * 
	 * @param preferred
	 *            the preferred String
	 * @param alternative
	 *            the alternative String if the preferred String is not valid
	 * @return the preferred String if it is not empty ot the alternative String otherwise
	 */
	public static String coalesce(String preferred, String alternative) {
		return isValid(preferred) ? preferred : alternative;
	}

	/**
	 * Returns null if the value supplied is null or an empty String.
	 * 
	 * @param value
	 *            the value to check
	 * @return null if the value supplied is null or an empty String or the value as a string otherwise
	 */
	public static String nullIfEmpty(Object value) {
		if (value == null)
			return null;
		String strval = value.toString();
		return ((strval.length() == 0) ? null : strval);
	}

	/**
	 * Converts an array of objects to a string.
	 * 
	 * @param array
	 *            array of objects
	 * @return returns a String
	 */
	public static String arrayToString(Object[] array, String separator) {
		if (array == null || array.length < 1)
			return null; // Empty
		if (array.length > 1) { // multi Column Key
			StringBuilder buf = new StringBuilder();
			for (int i = 0; i < array.length; i++) {
				if (i > 0)
					buf.append(separator);
				buf.append(array[i]);
			}
			return buf.toString();
		}
		// Only one member
		return array[0].toString();
	}

	/**
	 * Converts a string to an array of objects.
	 * 
	 * @param string
	 *            the source string to parse
	 * @param separator
	 *            the separator string by which the parts are separated
	 * @return returns a String
	 */
	public static String[] stringToArray(String string, String separator) {
		if (string == null || isEmpty(separator))
			return null; // Empty
		// Count the items first
		int sepLength = separator.length();
		int count = 0;
		int pos = -1;
		while ((pos = string.indexOf(separator, pos + sepLength)) >= 0)
			count++;
		// Alloc an array
		String[] array = new String[count + 1];
		if (count > 0) {
			int beg = 0;
			for (int i = 0; true; i++) {
				int end = string.indexOf(separator, beg);
				if (end > beg) { // Add Item
					array[i] = string.substring(beg, end);
					beg = end + sepLength;
				} else { // Last Item
					array[i] = string.substring(beg);
					break;
				}
			}
		} else {
			array[0] = string;
		}
		// Only one member
		return array;
	}

	/**
	 * Converts an collection of objects to a string.
	 * 
	 * @param c
	 *            the collection to add
	 * @return returns a String
	 */
	public static String collectionToString(Collection<? extends Object> c, String separator) {
		if (c == null || c.size() == 0)
			return null; // Empty
		// Iterator
		StringBuilder buf = new StringBuilder();
		boolean addSep = false;
		Iterator<?> i = c.iterator();
		while (i.hasNext()) {
			if (addSep)
				buf.append(separator);
			buf.append(valueOf(i.next()));
			addSep = true;
		}
		return buf.toString();
	}

	/**
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return (s == null || s.trim().length() == 0);
	}

	/**
	 * @param s
	 * @return
	 */
	public static boolean isValid(String s) {
		return (s != null && s.trim().length() > 0);
	}

	/**
	 * @param s
	 * @return
	 */
	public static boolean isEmail(String s) {
		int indexOfAtChar = s.indexOf("@");
		if (indexOfAtChar > 0) {
			int indexOfDotChar = s.indexOf(".", indexOfAtChar);
			if (indexOfDotChar > 0) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * Validates a given string. If the string is empty then null is returned. Otherwise the trimmed string is returned.
	 * 
	 * @param s
	 *            the string to validate
	 * @return the string or null if s was empty.
	 */
	public static String validate(String s) {
		if (s == null)
			return null;
		s = s.trim();
		if (s.length() == 0)
			return null;
		return s;
	}

	/**
	 * Replaces all occances of first character in a string by a string.
	 * 
	 * @param source
	 *            the original String.
	 * @param find
	 *            the String to be replaced
	 * @param replace
	 *            the replacement string
	 * 
	 * @return a new string with all occurances of <code>find</code> in <code>source</code> replaced by <code>replace</code>
	 */
	public static String replace(String source, String find, String replace) {
		// Check params
		if (source == null || find == null || find.length() == 0)
			return source;
		// Find the character
		int index = source.indexOf(find);
		if (index < 0)
			return source;
		if (replace == null)
			replace = "";
		// replace and find again
		int len = find.length();
		return source.substring(0, index) + replace + replace(source.substring(index + len), find, replace);
	}

	public static String replaceAll(String source, String find, String replace) {
		if (source == null)
			return null;
		if (find == null) {
			find = "";
		}
		if (replace == null) {
			replace = "";
		}
		int fromLength = find.length();
		int start = source.indexOf(find);
		if (start == -1)
			return source;

		boolean greaterLength = (replace.length() >= fromLength);

		StringBuilder buffer;
		// If the "to" parameter is longer than (or
		// as long as) "from", the final length will
		// be at least as large
		if (greaterLength) {
			if (find.equals(replace))
				return source;
			buffer = new StringBuilder(source.length());
		} else {
			buffer = new StringBuilder();
		}

		char[] origChars = source.toCharArray();

		int copyFrom = 0;
		while (start != -1) {
			buffer.append(origChars, copyFrom, start - copyFrom);
			buffer.append(replace);
			copyFrom = start + fromLength;
			start = source.indexOf(find, copyFrom);
		}
		buffer.append(origChars, copyFrom, origChars.length - copyFrom);

		return buffer.toString();
	}

	public static String replaceBRbyLF(String s) {
		return replaceAll(replaceAll(s, "<br/>", "\n\n"), "<br />", "\n\n");
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param s
	 * @return
	 */
	public static String replaceLFByBR(String s) {
		if (s == null) {
			return "";
		} else
			return s.replaceAll("\n\n", "<br/>").replaceAll("\r\n", "<br/>");
	}

	public static String replace(String source, String replaceBy, int beginIndex, int endIndex) {
		if (source == null || replaceBy == null || replaceBy.length() == 0)
			return source;
		String strLeft = source.substring(0, beginIndex);
		String strRight = source.substring(endIndex, source.length() - 1);
		return strLeft + strRight;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strValue
	 * @return
	 */
	public static String trimString(String strValue) {
		return (strValue != null ? strValue : "").trim();
	}

	public static String trimLeftRight(String orig) {
		return trimLeft(trimRight(orig));
	}

	public static String trimRight(String orig) {
		if (orig == null)
			return null;
		String str = orig.trim();

		return str;
	}

	public static String trimLeft(String orig) {
		if (orig == null)
			return null;

		int space = 0;
		boolean meet = false;

		for (int i = 0; i < orig.length(); i++) {
			if (orig.charAt(i) == ' ' && meet == false) {
				space = space + 1;
			} else
				meet = true;
		}
		return orig.substring(space, orig.length());
	}

	/**
	 * @param orig
	 * @return
	 */
	public static String trimAll(String orig) {
		if (orig == null)
			return "";
		String str = orig.trim();
		StringBuilder strBuf = new StringBuilder(str.length());
		boolean hasSpace = false;
		for (int i = 0, j = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				if (!hasSpace) {
					strBuf.append(' ');
					hasSpace = true;
				}
			} else {
				j = str.indexOf(' ', i);
				if (j == -1)
					j = str.length();
				strBuf.append(str.substring(i, j));
				hasSpace = false;
				i = j - 1;
			}
		}

		return strBuf.toString();
	}

	public static String trimAllVN(String orig) {
		if (orig == null)
			return "";

		String strTemp = ""; 
		
		for (int i = 0; i < orig.length(); i++) {
			if (!(orig.charAt(i) == '　') && !(orig.charAt(i) == ' ') && !(orig.charAt(i) == ' ')) {
				strTemp +=  Character.toString(orig.charAt(i));
			}
		}
		
		return strTemp;
	}

	/**
	 * Test if a String has a numeric equivalent
	 * 
	 * @param number
	 * @return True if String is a number; False if it is not
	 */
	public static boolean isInteger(final String number) {
		boolean isInteger;

		try {
			Integer.parseInt(number);
			isInteger = true;
		} catch (NumberFormatException nfe) {
			// eat the exception
			isInteger = false;
		}

		return isInteger;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isColorInt(final String number) {

		boolean isColor = false;

		try {
			Integer iValue = Integer.parseInt(number);
			if (0 <= iValue && iValue <= 255) {
				isColor = true;
			}
		} catch (NumberFormatException nfe) {
		}

		return isColor;
	}

	/**
	 * Test if a String is Code: A-Z 0-9
	 * 
	 * @param String
	 * @return True if String is a code; False if it is not
	 */
	public static boolean isCode(final String code) {
		char c;
		String codeTrim = code.trim();
		try {
			for (int index = 0; index < codeTrim.length(); index++) {
				c = codeTrim.charAt(index);
				// if ((c>='A' && c<='Z') || (c>='0' && c<='9') || (c>='a' && c<='z') || (c>='０' && c<='９')) {
				if ((c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) {
					continue;
				} else
					return false;
			}
			return true;
		} catch (Exception nfe) {
			// eat the exception
			return false;
		}
	}

	/**
	 * Test if a String is Tel Number: 0-9, ０－９, -, ー
	 * 
	 * @param String
	 * @return True if String is a tel; False if it is not
	 */
	public static boolean isTel(final String tel) {
		char c;
		try {
			for (int index = 0; index < tel.length(); index++) {
				c = tel.charAt(index);
				// if ((c>='0' && c<='9') || (c>='０' && c<='９') || c=='-' || c=='ー') {
				if ((c >= '0' && c <= '9') || c == '-') {
					continue;
				} else
					return false;
			}
			return true;
		} catch (Exception nfe) {
			// eat the exception
			return false;
		}
	}

	/**
	 * Convert String to Tel: 0-9,-
	 * 
	 * @param String
	 * @return True if String is a code; False if it is not
	 */
	public static String getTel(final String tel) {
		char c;
		String result = "";
		HashMap<String, String> numberMap = new HashMap<String, String>();

		numberMap.put("０", "0");
		numberMap.put("１", "1");
		numberMap.put("２", "2");
		numberMap.put("３", "3");
		numberMap.put("４", "4");
		numberMap.put("５", "5");
		numberMap.put("６", "6");
		numberMap.put("７", "7");
		numberMap.put("８", "8");
		numberMap.put("９", "9");

		for (int index = 0; index < tel.length(); index++) {
			c = tel.charAt(index);

			if (c >= '０' && c <= '９') {
				c = numberMap.get("" + c).charAt(0);
			}

			if (c == 'ー') {
				c = '-';
			}

			result = result + c;
		}
		numberMap = null;
		return result;
	}

	/**
	 * Test if a String is Code: A-Z 0-9
	 * 
	 * @param String
	 * @return True if String is a code; False if it is not
	 */
	public static String filterCode(final String inString) {
		char c;
		String result = "";

		for (int index = 0; index < inString.length(); index++) {
			c = inString.charAt(index);

			if ((c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) {
				result = result + c;
			}
		}
		return result;
	}

	public static String filterNumberic(final String inString) {
		char c;
		String result = "";

		for (int index = 0; index < inString.length(); index++) {
			c = inString.charAt(index);

			if (c >= '0' && c <= '9') {
				result = result + c;
			}
		}
		return result;
	}

	public static String getYYYYMMDD(final String inString) {
		char c;
		String result = "";

		for (int index = 0; index < inString.length(); index++) {
			c = inString.charAt(index);

			if ((c >= '0' && c <= '9') || c == '/') {
				result = result + c;
			}
		}
		
		if (result.length() == 1) { // d
			result = DateUtils.getCurrentYYYYMM() + "0" + result;
		} else if (result.length() == 2) { // dd
			result = DateUtils.getCurrentYYYYMM() + result;
		} else if (result.length() == 3) {
			if (result.indexOf("/") > 0) { // M/d
				result = DateUtils.getCurrentyyyy() + "0" + result.charAt(0) + "0" + result.charAt(2);
			} else {
				// Mdd
				result = DateUtils.getCurrentyyyy() + "0" + result;
			}
		} else if (result.length() == 4) {
			if (result.indexOf("/") == 2) { // MM/d
				result = DateUtils.getCurrentyyyy() + result.substring(0, 2) + "0" + result.substring(3, 4);
			} else if (result.indexOf("/") == 1) { // M/dd
				result = DateUtils.getCurrentyyyy() + "0" + result.substring(0, 1) + result.substring(2, 4);
			} else {
				result = DateUtils.getCurrentyyyy() + result;
			}
		} else if (result.length() == 5) {
			if (result.indexOf("/") == 2) { // MM/dd
				result = DateUtils.getCurrentyyyy() + result.substring(0, 2) + result.substring(3, 5);
			}
		} else if (result.length() == 6) { // yyMMdd
			if (result.indexOf("/") < 0) {
				result = "20" + result;
			}
		} else if (result.length() == 8) {
			if (result.indexOf("/") < 0) { // yyyyMMdd
				;
			} else { // yy/MM/dd
				result = "20" + result;
			}
		} else if (result.length() == 10) { // yyyy/MM/dd

		}

		result = filterNumberic(result);

		return result;
	}
	
	public static String getYYYYMM(final String inString) {
		char c;
		String result = "";

		for (int index = 0; index < inString.length(); index++) {
			c = inString.charAt(index);

			if ((c >= '0' && c <= '9') || c == '/') {
				result = result + c;
			}
		}

		if (result.length() == 1) { // m
			result = DateUtils.getCurrentyyyy() + "0" + result + "01";
		} else if (result.length() == 2) { // mm
			if (Integer.parseInt(result) > 12){
				return "";
			}
			result = DateUtils.getCurrentyyyy() + result + "01";
		} else if (result.length() == 3) {
			if (result.indexOf("/") > 0) { // y/m
				result = "" + DateUtils.getCurrentDate();
			} else {
				// yyM
				result = "20"+ result.substring(0, 2) + "0" + result.substring(2, 3) + "01";
			}
		} else if (result.length() == 4) {
			if (result.indexOf("/") == 2) { // yy/M
				result = "20"+ result.substring(0, 2) + "0" + result.substring(3, 4) + "01";
			} else if (result.indexOf("/") == 1) { // y/MM
				result = "200" + result.substring(0, 1) + result.substring(2, 4) + "01";
			} else {
				result = "" + DateUtils.getCurrentDate();
			}
		} else if (result.length() == 5) {
			if (result.indexOf("/") == 2) { // yy/MM
				result = "20" + result.substring(0, 2) + result.substring(3, 5) + "01";
			}
		} else if (result.length() == 6) { // YYYYMM
			if (result.indexOf("/") < 0) {
				result = result + "01";
			}else if (result.indexOf("/") == 0){ //		yyyy/M
				result = result.substring(0, 4) + result.substring(5, 6) + "01";;
			}
		} else if (result.length() == 7) {
			if (result.indexOf("/") == 4) { // yyyy/MM
				result = result + "01";
			}
		}

		result = filterNumberic(result);

		return result;
	}

	public static String filterNonAscii(String inString) {
		// Create the encoder and decoder for the character encoding
		Charset charset = Charset.forName("US-ASCII");
		CharsetDecoder decoder = charset.newDecoder();
		CharsetEncoder encoder = charset.newEncoder();
		// This line is the key to removing "unmappable" characters.
		encoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
		String result = inString;

		try {
			// Convert a string to bytes in a ByteBuffer
			java.nio.ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(inString));

			// Convert bytes in a ByteBuffer to a character ByteBuffer and then to a string.
			CharBuffer cbuf = decoder.decode(bbuf);
			result = cbuf.toString();
		} catch (CharacterCodingException cce) {
			System.out.println("Exception during character encoding/decoding: " + cce.getMessage());
		}

		return result;
	}

	/**
	 * Test if a String has a numeric equivalent
	 * 
	 * @param number
	 * @return True if String is a number; False if it is not
	 */
	public static boolean isDouble(final String number) {
		boolean isDoubler;

		try {
			Double.parseDouble(number);
			isDoubler = true;
		} catch (NumberFormatException nfe) {
			// eat the exception
			isDoubler = false;
		}

		return isDoubler;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strValue
	 * @return
	 */
	public static String nulToString(String strValue) {
		return (strValue != null ? strValue.trim() : "");
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strValue
	 * @return
	 */
	public static int getLengthUTF8(String strValue) {

		int length = 0;

		if (!isValid(strValue)) {
			length = 0;
		} else {
			try {
				length = strValue.getBytes("UTF-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				length = 0;
			}
		}

		return length;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strValue
	 * @return
	 */
	public static int getLengthByShiftJisEncode(String strValue) {

		int length = 0;

		if (!isValid(strValue)) {
			length = 0;
		} else {
			try {
				length = strValue.getBytes("Shift_JIS").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				length = strValue.length();
			}
		}

		return length;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param strValue
	 * @return </DL>
	 */
	public static boolean isAlphaBet(String strValue) {

		int iUtf8Len = getLengthUTF8(strValue);

		if (iUtf8Len == emptyIfNull(strValue).length()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD>シフト文字を考慮した結果の文字桁数</DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strValue
	 * @return
	 */
	public static int getDB2LenString(String strValue) {

		int iLen = 0;
		int iCount = 0;
		int iPreCount = 0;
		int iCurrentCount = 0;

		if (strValue == null || "".equalsIgnoreCase(strValue)) {
			iLen = 0;
		} else {

			for (int i = 0; i < strValue.length(); i++) {

				/** 　一時取り出す */
				try {
					iCount = strValue.substring(i, i + 1).getBytes("Shift_JIS").length;
					iCurrentCount = iCount;
				} catch (UnsupportedEncodingException e) {
					iCount = 2;
				}

				/** 一文目のみ処理 */
				if (i == 0) {
					iPreCount = iCount;

					if (iCount >= 2) {
						iLen = 1;
					}
				}

				/** 全角＜＝＞半角が変わった場合 */
				if (iCurrentCount != iPreCount)
					iLen += 1;

				/** 文字バイトのカワントアップセット */
				iLen += iCount;

				/** ワークへセット */
				iPreCount = iCount;
			}
		}

		if (iCurrentCount >= 2)
			iLen++;

		return iLen;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strValue
	 * @param iMaxLen
	 * @return
	 */
	public static boolean checkDB2LenString(String strValue, int iMaxLen) {

		int iLen = getDB2LenString(strValue);

		if (iLen > iMaxLen)
			return false;

		return true;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strValue
	 * @param iMaxLen
	 * @return
	 */
	public static boolean checkLenString(String strValue, int iMaxLen) {

		int iLen = StringUtils.getLengthByShiftJisEncode(strValue);

		if (iLen > iMaxLen)
			return false;

		return true;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strValue
	 * @param width
	 * @return
	 */
	public static String tooltip(String strValue, int width) {
		strValue = nulToString(strValue);
		String tooltip = strValue;
		if (strValue.length() > width) {
			String displayString = strValue.substring(0, width);
			tooltip = displayString + "<span onmouseout=\"hideTooltip()\"";
			tooltip += " onmouseover=\"showTooltip(event,'" + strValue + "');return false\">" + "...</span>";
		}
		return tooltip;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strValue
	 * @param strTooltip
	 * @return
	 */
	public static String tooltip(String strValue, String strTooltip) {
		strValue = nulToString(strValue);
		strTooltip = nulToString(strTooltip);
		String tooltip = strValue;
		if (strTooltip.length() > 0) {
			tooltip = "<span onmouseout=\"hideTooltip()\"";
			tooltip += " onmouseover=\"showTooltip(event,'" + strTooltip + "');return false\">" + strValue + "</span>";
		}
		return tooltip;
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
	public static int getNumberDate(String strDate) {

		int iNumberValue = 0;
		String strTemp = "";

		if (StringUtils.isValid(strDate) == false) {
			iNumberValue = 0;
		} else {
			try {
				strTemp = strDate.replace("/", "").replace(":", "");
				iNumberValue = Integer.parseInt(strTemp);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				iNumberValue = 0;
			}
		}

		return iNumberValue;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param iHour
	 * @return
	 */
	public static String getFormatHour(int iHour) {

		String strHour = "";

		if ((iHour > 9) || (iHour < -9)) {
			strHour = "" + iHour;
		} else if ((iHour >= 0) && (iHour <= 9)) {
			strHour = "0" + iHour;
		} else if ((iHour < 0) && (iHour >= -9)) {
			strHour = "-0" + (-1) * iHour;
		}
		return strHour;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param iHour
	 * @return
	 */
	public static String getFormatMinute(int iMinute) {

		String strHour = "";

		if (iMinute > 9) {
			strHour = "" + iMinute;
		} else {
			strHour = "0" + iMinute;
		}
		return strHour;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param value
	 * @param c
	 * @param len
	 * @return
	 */
	public static String rightLen(String value, Character c, int len) {
		value = nulToString(value).trim();
		String temp = value;
		if (value.length() < len) {
			for (int i = 0; i < len - value.length(); i++) {
				temp = c + temp;
			}

		}
		return temp;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param value
	 * @param c
	 * @param len
	 * @return
	 */
	public static String padRight(String value, Character c, int len) {
		value = nulToString(value).trim();
		String temp = value;
		if (value.length() < len) {
			for (int i = 0; i < len - value.length(); i++) {
				temp = temp + c;
			}

		}
		return temp;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param value
	 * @param c
	 * @param len
	 * @return
	 */
	public static String padLeft(String value, Character c, int len) {
		value = nulToString(value).trim();
		String temp = value;
		if (value.length() < len) {
			for (int i = 0; i < len - value.length(); i++) {
				temp = c + temp;
			}

		}
		return temp;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param time
	 * @return
	 */
	public static int getHourHHMMSS(String time) {
		try {
			if ((StringUtils.nulToString(time)).trim().length() > 0) {
				time = rightLen(time, '0', 6);
				return Integer.parseInt(time.substring(0, 2));
			} else {
				return 0;
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
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
	 * @param iTime
	 * @return
	 */
	public static String getFormatTimeSum(Integer iTimeSum) {
		if (iTimeSum != null && iTimeSum > 0) {
			return StringUtils.getFormatHour(iTimeSum / 10000) + ":" + StringUtils.getFormatMinute((iTimeSum % 10000) / 100);
		} else {
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
	 * @param iTime
	 * @return
	 */
	public static String getFormatTime(Integer iTime) {
		if (iTime != null && iTime > 0) {
			return StringUtils.getFormatHour(iTime / 10000) + ":" + StringUtils.getFormatMinute((iTime % 10000) / 100);
		} else {
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
	 * @param iTime
	 * @return
	 */
	public static String getFormatDoubleTime(Double iTime) {
		if (iTime != null && iTime > 0) {
			return StringUtils.getFormatDoubleHour(NumberUtils.getRoundFloor(0, iTime / 10000)) + ":"
							+ StringUtils.getFormatDoubleMinute(NumberUtils.getRoundFloor(0, (iTime % 10000) / 100));
		} else {
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
	 * @param iTime
	 * @return
	 */
	public static String getFormatDoubleTimeMinute(Double iTime) {
		if (iTime != null && iTime > 0) {
			return StringUtils.getFormatHourWithoutZezo(NumberUtils.getRoundFloor(0, iTime / 60)) + "."
							+ StringUtils.getFormatDoubleMinute(iTime - 60 * NumberUtils.getRoundFloor(0, iTime / 60));
		} else {
			return "0.0";
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
	 * @param iHour
	 * @return
	 */
	public static String getFormatHourWithoutZezo(double iHour) {

		String strHour = "";

		strHour = "" + iHour;

		if (strHour.indexOf(".") > -1) {
			strHour = strHour.substring(0, strHour.indexOf("."));
		}

		return strHour;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param iHour
	 * @return
	 */
	public static String getFormatDoubleHour(double iHour) {

		String strHour = "";

		if ((iHour > 9) || (iHour < -9)) {
			strHour = "" + iHour;
		} else if ((iHour >= 0) && (iHour <= 9)) {
			strHour = "0" + iHour;
		} else if ((iHour < 0) && (iHour >= -9)) {
			strHour = "-0" + (-1) * iHour;
		}

		if (strHour.indexOf(".") > -1) {
			strHour = strHour.substring(0, strHour.indexOf("."));
		}

		return strHour;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param iHour
	 * @return
	 */
	public static String getFormatDoubleMinute(double iMinute) {

		String strMinute = "";

		if (iMinute > 9) {
			strMinute = "" + iMinute;
		} else {
			strMinute = "0" + iMinute;
		}

		if (strMinute.indexOf(".") > -1) {
			strMinute = strMinute.substring(0, strMinute.indexOf("."));
		}

		return strMinute;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param time
	 * @return
	 */
	public static int getMinuteHHMMSS(String time) {
		try {
			if ((StringUtils.nulToString(time)).trim().length() > 0) {
				time = rightLen(time, '0', 6);
				return Integer.parseInt(time.substring(2, 4));
			} else {
				return 0;
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
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
	 * @param iDate
	 * @return
	 */
	public static String getFormatDate(int iDate) {

		String strDate = "" + iDate;
		String strReturn = "";

		if (strDate.length() < 8) {
			strReturn = "";
		} else {
			strReturn += strDate.substring(0, 4);
			strReturn += "/" + strDate.substring(4, 6);
			strReturn += "/" + strDate.substring(6, 8);
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
	public static String getFormatDate(String strDate) {

		String strReturn = "";

		if (strDate.length() < 8) {
			strReturn = "";
		} else {
			strReturn += strDate.substring(0, 4);
			strReturn += "/" + strDate.substring(4, 6);
			strReturn += "/" + strDate.substring(6, 8);
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
	 * @param iValue
	 * @return
	 */
	public static String convertIntegerToStr(Integer iValue) {

		if (iValue == null)
			return "";
		else
			return iValue.toString();

	}
	
	public static String convertIntegerToStrWithZero(Integer iValue, int length) {
		String str = convertIntegerToStr(iValue);
		if(str.length() < length){
			while(str.length() < length) {
				str = "0" + str;
			}
		}
		return str;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param iValue
	 * @return </DL>
	 */
	public static String convertFloatToStr(Float iValue) {

		if (iValue == null)
			return "";
		else
			return iValue.toString();

	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @param iValue
	* @return
	* </DL>
	*/
	public static String convertDoubleToStr(Double iValue) {
		
		if (iValue == null)  return "";
		else return Double.toString(iValue);
		
	}
	
	public static Integer convertStrToInteger(String strValue) {

		Integer iValue = 0;

		try {
			iValue = Integer.parseInt(strValue);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return iValue;
	}

	public static Double convertStrToDouble(String strValue) {
		Double iValue = new Double(0);

		try {
			iValue = Double.parseDouble(strValue);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return iValue;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strValue
	 * @return
	 */
	public static String convertCSVStr(Object strValue) {

		String strReturn = "\"\"";

		if (strValue != null) {
			strReturn = "\"" + strValue.toString().trim() + "\"";
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
	 * @param obj
	 * @return
	 */
	public static String objectToStr(Object obj) {

		String strReturn = "";

		if (obj != null) {
			strReturn = obj.toString();
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
	 * @param strData
	 * @return
	 */
	public static byte[] getBytes(String strData) {

		byte ret[];

		try {
			ret = strData.getBytes(SystemConstants.STRING_GET_BYTES_ENCODE);
		} catch (UnsupportedEncodingException e) {
			ret = new byte[0];
		}
		return ret;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strIn
	 * @param iLen
	 * @return
	 */
	public static String getStrByLen(String strIn, int iLen) {

		String strTmp = "";

		if (strIn != null) {
			if (strIn.length() <= iLen) {
				strTmp = strIn;
			} else {
				strTmp = strIn.substring(0, iLen);
			}
		}

		return strTmp;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strIn
	 * @return
	 */
	public static String emptyIfNull(String strIn) {

		String strTmp = "";

		if (strIn != null) {
			strTmp = strIn.trim();
		}

		return strTmp;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strIn
	 * @param iLen
	 * @return
	 */
	public static String subString(String strIn, int iLen) {

		String strTmp = "";

		if (strIn != null) {
			if (strIn.length() > iLen) {
				strTmp = strIn.substring(0, iLen);
			} else {
				strTmp = strIn;
			}
		}

		return strTmp;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strIn
	 * @param iFrom
	 * @param iLen
	 * @return
	 */
	public static String subString(String strIn, int iFrom, int iLen) {

		String strTmp = "";

		if (strIn != null) {
			if (strIn.length() > (iFrom + iLen)) {
				strTmp = strIn.substring(iFrom, iFrom + iLen);
			} else if (strIn.length() > iFrom) {
				strTmp = strIn.substring(iFrom);
			}
		}

		return strTmp;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(String str1, String str2) {
		if (emptyIfNull(str1).trim().equalsIgnoreCase(emptyIfNull(str2).trim())) {
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
	 * @param strValue
	 * @return
	 */
	public static String fillMaxLen(String strValue, int iMax) {

		String strReturn = "";

		int iLen = 0;

		if (strValue == null) {
			iLen = 0;
		} else {
			strReturn = strValue;
			iLen = strValue.length();
		}

		for (int i = 0; i < (iMax - iLen); i++) {
			strReturn = strReturn + " ";
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
	 * @param strValue
	 * @return
	 */
	public static String fillNumberMaxLen(String strValue, int iMax) {

		String strReturn = "";

		int iLen = 0;

		if (strValue == null) {
			iLen = 0;
		} else {
			strReturn = strValue.trim();
			iLen = strReturn.length();
		}

		for (int i = 0; i < (iMax - iLen); i++) {
			strReturn = "0" + strReturn;
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
	 * @param s
	 * @return
	 */
	public static final String escapeHTML(String s) {
		StringBuffer sb = new StringBuffer();
		int n = s.length();

		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			switch (c) {
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '&':
					sb.append("&amp;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				case ' ':
					sb.append("&nbsp;");
					break;

				default:
					sb.append(c);
					break;
			}
		}
		return sb.toString();
	}

	public static int getLengthInteger(String decimal) {
		int indexOfPoint = 0;

		decimal = StringUtils.replaceAll(decimal, ",", "");
		decimal = StringUtils.replaceAll(decimal, "-", "");

		// if 0000.12312 --> length Integer = 0
		while (decimal.length() > 0 && "0".equals(decimal.substring(0, 1))) {
			decimal = decimal.substring(1, decimal.length());
		}

		if (isValid(decimal)) {
			indexOfPoint = decimal.indexOf(".");
			if (indexOfPoint > -1) {
				indexOfPoint = decimal.indexOf(".");
			} else {
				indexOfPoint = decimal.length();
			}
		}

		return indexOfPoint;
	}

	public static int getLengthFraction(String decimal) {
		int result = 0;

		decimal = StringUtils.replaceAll(decimal, ",", "");
		decimal = StringUtils.replaceAll(decimal, "-", "");

		if (isValid(decimal) && decimal.length() > getLengthInteger(decimal)) {
			result = decimal.length() - getLengthInteger(decimal) - 1;
		}
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
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static int compareS(String str1, String str2) {
		return emptyIfNull(str1).compareTo(emptyIfNull(str2));
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strIn
	 * @param iFrom
	 * @param iLen
	 * @return
	 */
	public static String subStringUseEncode(String strIn, int iFrom, int iLen) {

		String strTmp = "";
		int iCurrLen = 0;
		int i = iFrom;

		if (strIn != null) {
			while ((iCurrLen < iLen) && (i < strIn.length())) {
				try {
					iCurrLen += strIn.substring(i, i + 1).getBytes("Shift_JIS").length;
					strTmp += strIn.substring(i, i + 1);
				} catch (UnsupportedEncodingException e) {
					iCurrLen += 2;
					strTmp += strIn.substring(i, i + 1);
				}
				i++;
			}
		}

		return strTmp;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param strIn
	 * @param idex
	 * @return
	 */
	public static String decimalPoint(double dblIn, String strIdex) {

		String strIn = String.valueOf(dblIn);

		return decimalPoint(strIn, strIdex);
	}

	public static String decimalPoint(String dblIn, String strIdex) {

		if (!isInteger(strIdex))
			return "";
		if (isValid(dblIn) == false)
			return "";

		String strIn = dblIn;
		int idex = Integer.parseInt(strIdex);

		String strOut = "";
		int iSize = 0;
		String str1 = "";
		String str2 = "";

		if (strIn.indexOf(".") >= 0) {
			str1 = strIn.substring(0, strIn.indexOf("."));
			if (idex == 0)
				return str1;
			str2 = strIn.substring(strIn.indexOf(".") + 1, strIn.length());
			iSize = str2.length();
			if (iSize > idex) {
				strOut = str1 + "." + subString(str2, idex);
				return strOut;
			} else {
				for (int i = 0; i < idex - iSize; i++) {
					strIn = strIn + "0";
				}
				return strIn;
			}
		} else {
			for (int i = 0; i < idex; i++) {
				strIn = strIn + "0";
			}
			return strIn;
		}
	}
	
	public static boolean equals(String str1, String str2) {
		String s1 = trimAll(emptyIfNull(str1));
		String s2 = trimAll(emptyIfNull(str2));
		return s1.equals(s2);
	}
}