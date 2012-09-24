package com.fas.common.utils;

/**
 * JapaneseString contains static functions to do various tests
 * on Strings to determine if it consists one of the various types of 
 * characters used in the japanese writing system.
 *
 * There are also a functions to translate between Katakana, Hiragana,
 * and Romaji.
 *
 * @author Duane J. May <djmay@mayhoo.com>
 * @version $Id: JapaneseString.java,v 1.2 2002/04/20 18:10:24 djmay Exp $
 */
public class JapaneseString {

	private static final String P_HALF_KATAKANA_ONLY   = "^[\\uFF65-\\uFF9F]+$";
	private static final String P_HALF_KATAKANA_CODE   = "^[0-9A-Za-z\\uFF65-\\uFF9F]+$";


	
	/**
	 * Determines if this character is a Japanese Kana.
	 */
	public static boolean isKana(String str) {
		return ( isHiragana(str) || isKatakana(str) );
	}

	/**
	 * Determines if this character is one of the Japanese Hiragana.
	 */
	public static boolean isHiragana(String str) {
		int size = str.length();

		for ( int i = 0; i < size; i++ ) {
			char c = str.charAt( i );
			if ( !( ( '\u3041' <= c ) && ( c <= '\u309e' ) ) ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines if this character is one of the Japanese Katakana.
	 */

	public static boolean isKatakana(String str) {
		return ( isHalfwidthKatakana(str) || isFullwidthKatakana(str) );
	}

	/**
	 * Determines if this character is a Half width Katakana.
	 */
	public static boolean isHalfwidthKatakana(String str) {
	    return str.matches(P_HALF_KATAKANA_ONLY);
	}

	
	/**
	 * Determines if this character is a Half width Katakana.
	 */
	public static boolean isHalfwidthKatakana(char c) {
		String str = String.valueOf(c);
//		str = str.replace("�", "");
//		str = str.replace("�", "");
//		str = str.replace(" ", "");
//		str = str.replace("�", "");
		
	    return str.matches(P_HALF_KATAKANA_ONLY);
	}
	
	/**
	 * Determines if this character is a Half width Katakana or Ascii
	 */
	public static boolean isHalfwidthKatakanaOrAscii(String str) {
	    return str.matches(P_HALF_KATAKANA_CODE) ;
	}
	
	/**
	 * Determines if this character is a Full width Katakana.
	 */
	public static boolean isFullwidthKatakana(String str) {
		int size = str.length();

		for ( int i = 0; i < size; i++ ) {
			char c = str.charAt( i );
			if ( !( ( '\u30a1' <= c ) && ( c <= '\u30fe' ) ) ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines if this character is a Kanji character.
	 */
	public static boolean isKanji(String str) {
		int size = str.length();

		for ( int i = 0; i < size; i++ ) {
			char c = str.charAt( i );
			boolean charIsKanji = false;
			if ( ('\u4e00' <= c ) && ( c <= '\u9fa5' ) ) {
				charIsKanji = true;
			}
			if ( ( '\u3005' <= c ) && ( c <= '\u3007' ) ) {
				charIsKanji = true;
			}
			if ( ! charIsKanji ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines if this character could be used as part of 
	 * a romaji character.
	 */
	public static boolean isRomaji(String str) {
		int size = str.length();

		for ( int i = 0; i < size; i++ ) {
			char c = str.charAt( i );
			boolean charIsRomaji = false;
			if (('\u0041' <= c) && (c <= '\u0090')) {
				charIsRomaji = true;
			} else if (('\u0061' <= c) && (c <= '\u007a')) {
				charIsRomaji = true;
			} else if (('\u0021' <= c) && (c <= '\u003a')) {
				charIsRomaji = true;
			} else if (('\u0041' <= c) && (c <= '\u005a')) {
				charIsRomaji = true;
			}
			if ( ! charIsRomaji ) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Translates this character into the equivalent Katakana character.
	 * The function only operates on Hiragana and always returns the
	 * Full width version of the Katakana. If the character is outside the
	 * Hiragana then the origianal character is returned.
	 */
	public static String toKatakana(String str) {
		StringBuffer buf = new StringBuffer();
		int size = str.length();

		for ( int i = 0; i < size; i++ ) {
			char c = str.charAt( i );
			buf.append( JapaneseCharacter.toKatakana( c ) );
		}
		return buf.toString();
	}
	
	/**
	 * Katakana --> UpcaseKatakana
	 * @return
	 */
	public static String toUpcaseKatakana(String str) {
		
		for (int i=0; i< lowerKana.length; i++){
			str = str.replace(lowerKana[i], upperKana[i]);
		}
		return str;
	}


	/**
	 * Translates this character into the equivalent Hiragana character.
	 * The function only operates on Katakana characters
	 * If the character is outside the Full width or Half width 
	 * Katakana then the origianal character is returned.
	 */
	public static String toHiragana(String str) {
		StringBuffer buf = new StringBuffer();
		int size = str.length();

		for ( int i = 0; i < size; i++ ) {
			char c = str.charAt( i );
			buf.append( JapaneseCharacter.toHiragana( c ) );
		}
		return buf.toString();
	}


	/**
	 * Translates this character into the equivalent Romaji character.
	 * The function only operates on Hiragana and Katakana characters
	 * If the character is outside the given range then 
	 * the origianal character is returned.
	 * 
	 * The resulting string is lowercase if the input was Hiragana and
	 * UPPERCASE if the input was Katakana.
	 */
	public static String toRomaji( String str ) {
		StringBuffer buf = new StringBuffer();
		int size = str.length();
		String convert;
		boolean isHiragana;

		for ( int i = 0; i < size - 1; i++ ) {
			char c = str.charAt( i );
			char nextChar = str.charAt( i + 1 );
			isHiragana = JapaneseCharacter.isHiragana( c );
			c = JapaneseCharacter.toHiragana( c );
			nextChar = JapaneseCharacter.toHiragana( nextChar );

			if ( nextChar == '\u3087' ||
					nextChar == '\u3085' ||
					nextChar == '\u3083' )
			{
				convert = voicedRomaji[ c - 0x3041 ];
			} else {
				convert = romaji[ c - 0x3041 ];
			}
			if ( ! isHiragana ) {
				convert = convert.toUpperCase();
			} 
			buf.append( convert );
		}
		char c = str.charAt( size - 1 );
		isHiragana = JapaneseCharacter.isHiragana( c );
		c = JapaneseCharacter.toHiragana( c );
		convert = romaji[ c - 0x3041 ];
		if ( ! isHiragana ) {
			convert = convert.toUpperCase();
		} 
		buf.append( convert );

		return buf.toString();
	}

	/**
	 * The array used to map hirgana to romaji.
	 * Note the little ya, yu and yo characters need to follow
	 * a character in the voicedRomaji array.
	 */
	protected static String romaji[] = { 
		"a", "a", 
		"i", "i", 
		"u", "u", 
		"e", "e", 
		"o", "o",

		"ka", "ga", 
		"ki", "gi", 
		"ku", "gu", 
		"ke", "ge", 
		"ko", "go",

		"sa", "za", 
		"shi", "ji", 
		"su", "zu", 
		"se", "ze", 
		"so", "zo",

		"ta", "da", 
		"chi", "ji", 
		"tsu", "tsu", "zu", 
		"te", "de", 
		"to", "do",

		"na", 
		"ni", 
		"nu", 
		"ne", 
		"no",

		"ha", "ba", "pa", 
		"hi", "bi", "pi", 
		"fu", "bu", "pu", 
		"he", "be", "pe", 
		"ho", "bo", "po",

		"ma", 
		"mi", 
		"mu", 
		"me", 
		"mo",

		"a", "ya", 
		"u", "yu", 
		"o", "yo",

		"ra", 
		"ri", 
		"ru", 
		"re", 
		"ro",

		"wa", "wa", 
		"wi", "we", 
		"o", 
		"n", 

		"v", 
		"ka", 
		"ke" 

	};
   protected static String lowerKana[] = {"�", "�", "�", "�", "�", "�", "�", "�", "�"};
   
   protected static String upperKana[] = {"�", "�", "�", "�", "�", "�", "�", "�", "�"};
	
   
   /**
	 * The array used to map hirgana to romaji. This is used
	 * when the character is followed by a little ya, yu, or yo.
	 */
	protected static String voicedRomaji[] = { 
		"", "", 
		"", "", 
		"", "", 
		"", "", 
		"", "",

		"", "", 
		"ky", "gy", 
		"", "", 
		"", "", 
		"", "",

		"", "", 
		"sh", "j", 
		"", "", 
		"", "", 
		"", "",

		"", "", 
		"ch", "ji", 
		"", "", "", 
		"", "", 
		"", "",

		"", 
		"ny", 
		"", 
		"", 
		"",

		"", "", "", 
		"hy", "by", "py", 
		"", "", "", 
		"", "", "", 
		"", "", "",

		"", 
		"my", 
		"", 
		"", 
		"",

		"a", "ya", 
		"u", "yu", 
		"o", "yo",

		"", 
		"ry", 
		"", 
		"", 
		"",

		"", "", 
		"", "", 
		"", 
		"", 

		"", 
		"", 
		"" 
	};

	/**
	 * Access the array to return the correct romaji string.
	 */
	protected static String lookupRomaji( char c ) {
		return romaji[ c - 0x3041 ];
	}    
}
