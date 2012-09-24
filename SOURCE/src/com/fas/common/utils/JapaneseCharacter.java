package com.fas.common.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * JapaneseCharacter contains static functions to do various tests
 * on characters to determine if it is one of the various types of 
 * characters used in the japanese writing system.
 *
 * There are also a functions to translate between Katakana, Hiragana,
 * and Romaji.
 *
 * @author Duane J. May <djmay@mayhoo.com>
 * @version $Id: JapaneseCharacter.java,v 1.2 2002/04/20 18:10:24 djmay Exp $
 */
public class JapaneseCharacter {

    private static final String HALFWIDTH_KATAKANA_TABLE = "。=｡;「=｢;」=｣;、=､;ー=ｰ;ッ=ｯ;ャ=ｬ;ュ=ｭ;ョ=ｮ;ァ=ｧ;ィ=ｨ;ゥ=ｩ;ェ=ｪ;ォ=ｫ;ア=ｱ;イ=ｲ;ウ=ｳ;エ=ｴ;オ=ｵ;カ=ｶ;キ=ｷ;ク=ｸ;ケ=ｹ;コ=ｺ;サ=ｻ;シ=ｼ;ス=ｽ;セ=ｾ;ソ=ｿ;タ=ﾀ;チ=ﾁ;ツ=ﾂ;テ=ﾃ;ト=ﾄ;ナ=ﾅ;ニ=ﾆ;ヌ=ﾇ;ネ=ﾈ;ノ=ﾉ;ハ=ﾊ;ヒ=ﾋ;フ=ﾌ;ヘ=ﾍ;ホ=ﾎ;マ=ﾏ;ミ=ﾐ;ム=ﾑ;メ=ﾒ;モ=ﾓ;ヤ=ﾔ;ユ=ﾕ;ヨ=ﾖ;ラ=ﾗ;リ=ﾘ;ル=ﾙ;レ=ﾚ;ロ=ﾛ;ワ=ﾜ;ヲ=ｦ;ン=ﾝ;ガ=ｶﾞ;ギ=ｷﾞ;グ=ｸﾞ;ゲ=ｹﾞ;ゴ=ｺﾞ;ザ=ｻﾞ;ジ=ｼﾞ;ズ=ｽﾞ;ゼ=ｾﾞ;ゾ=ｿﾞ;ダ=ﾀﾞ;ヂ=ﾁﾞ;ヅ=ﾂﾞ;デ=ﾃﾞ;ド=ﾄﾞ;バ=ﾊﾞ;ビ=ﾋﾞ;ブ=ﾌﾞ;ベ=ﾍﾞ;ボ=ﾎﾞ;パ=ﾊﾟ;ピ=ﾋﾟ;プ=ﾌﾟ;ペ=ﾍﾟ;ポ=ﾎﾟ";
    private static final Map<String, String> KATAKANA_TO_HALFWIDTH = new HashMap<String, String>();
    private static final Map<String, String> HALFWIDTH_TO_KATAKANA = new HashMap<String, String>();

    static {
        for (final Object entry : Collections.list(new StringTokenizer(HALFWIDTH_KATAKANA_TABLE, ";"))) {
            final String[] mapping = ((String) entry).split("\\=");
            final String kana = mapping[0];
            final String halfwidth = mapping[1];
            if (KATAKANA_TO_HALFWIDTH.put(kana, halfwidth) != null) {
                throw new IllegalArgumentException("Mapping for " + kana + " defined multiple times");
            }
            if (HALFWIDTH_TO_KATAKANA.put(halfwidth, kana) != null) {
                throw new IllegalArgumentException("Mapping for " + halfwidth + " defined multiple times");
            }
        }
    }
	
	/**
	 * Determines if this character is a Japanese Kana.
	 */
	public static boolean isKana(char c) {
		return ( isHiragana(c) || isKatakana(c) );
	}

	/**
	 * Determines if this character is one of the Japanese Hiragana.
	 */
	public static boolean isHiragana(char c) {
		return ( ( '\u3041' <= c ) && ( c <= '\u309e' ) );
	}

	/**
	 * Determines if this character is one of the Japanese Katakana.
	 */
	public static boolean isKatakana(char c) {
		return ( isHalfwidthKatakana(c) || isFullwidthKatakana(c) );
	}

	/**
	 * Determines if this character is a Half width Katakana.
	 */
	public static boolean isHalfwidthKatakana(char c) {
		return ( ( '\uff66' <= c ) && ( c <= '\uff9d' ) );
	}

	/**
	 * Determines if this character is a Full width Katakana.
	 */
	public static boolean isFullwidthKatakana(char c) {
		return ( ( '\u30a1' <= c ) && ( c <= '\u30fe' ) );
	}

	/**
	 * Determines if this character is a Kanji character.
	 */
	public static boolean isKanji(char c) {
		if ( ('\u4e00' <= c ) && ( c <= '\u9fa5' ) ) {
			return true;
		}
		if ( ( '\u3005' <= c ) && ( c <= '\u3007' ) ) {
			return true;
		}
		return false;
	}

	/**
	 * Determines if this character could be used as part of 
	 * a romaji character.
	 */
	public static boolean isRomaji(char c) {
		if (('\u0041' <= c) && (c <= '\u0090'))
			return true;
		else if (('\u0061' <= c) && (c <= '\u007a'))
			return true;
		else if (('\u0021' <= c) && (c <= '\u003a'))
			return true;
		else if (('\u0041' <= c) && (c <= '\u005a'))
			return true;
		else
			return false;
	}

	/**
	 * Translates this character into the equivalent Katakana character.
	 * The function only operates on Hiragana and always returns the
	 * Full width version of the Katakana. If the character is outside the
	 * Hiragana then the origianal character is returned.
	 */
	public static char toKatakana(char c) {
		if ( isHiragana( c ) ) {
			return (char)(c + 0x60);
		}
		return c;
	}
	
	/**
	 * Translates this character into the equivalent Katakana character.
	 * The function only operates on Hiragana and always returns the
	 * Full width version of the Katakana. If the character is outside the
	 * Hiragana then the origianal character is returned.
	 */
	public static String toHalfKatakana(char c) {
		
		if ( isHiragana(c)) {
			String strChar = KATAKANA_TO_HALFWIDTH.get(Character.toString(toKatakana(c)));
			if (strChar != null && strChar.length() > 0) {
				return strChar;
			}
		}
		return Character.toString(c);
	}	

	/**
	 * Translates this character into the equivalent Hiragana character.
	 * The function only operates on Katakana characters
	 * If the character is outside the Full width or Half width 
	 * Katakana then the origianal character is returned.
	 */
	public static char toHiragana(char c) {
		if ( isFullwidthKatakana( c ) ) {
			return (char)(c - 0x60);
		} else if ( isHalfwidthKatakana( c ) ) {
			return (char)(c - 0xcf25);
		}
		return c;
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
	public static String toRomaji( char c ) {
		if ( isHiragana( c ) ) {
			return lookupRomaji( c );
		} else if ( isKatakana( c ) ) {
			c = toHiragana( c );
			String str = lookupRomaji( c );                
			return str.toUpperCase();
		}
		return String.valueOf( c );
	}

	/**
	 * The array used to map hirgana to romaji.
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

	/**
	 * Access the array to return the correct romaji string.
	 */
	private static String lookupRomaji( char c ) {
		return romaji[ c - 0x3041 ];
	} 
	
	
	public static void main(String args[]) {
		System.out.println(toHalfKatakana('か'));
	}
}