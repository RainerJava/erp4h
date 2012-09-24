/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：JapaneseCheckUtils.java
*
*     記述				：
*     
*     作成日			：2009/10/05   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.common.utils;

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

public class JapaneseCheckUtils {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public JapaneseCheckUtils() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>引数(_pstrData)が半角かどうかチェックします。</DD>
	 * <BR>
	 *
	 * </DL>
	 * @param trData
	 * @return
	 */
	public static boolean checkHankaku(String trData)
	{
		int intByte = trData.length() * 2;
		//SJISに変換
		//byte[] bytData2=pstrData.getBytes("MS932");
		byte[] bytData2 = null;;
        try {
		bytData2 = StringUtils.getBytes(trData);
        }catch(Exception e){
            
        }	
		//バイト数≠バイト数
		if ( intByte == (bytData2.length * 2) )
			return true;
		else
			return false;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>引数(_pstrData)が半角かどうかチェックします。</DD>
	 * <BR>
	 *
	 * </DL>
	 * @param trData
	 * @return
	 */
	public static boolean checkHankaku(char trData)
	{
		String data=Character.toString(trData);
		byte[] bytData2 = null;;
        try {
		bytData2 = StringUtils.getBytes(data);
        }catch(Exception e){
            
        }	
		//バイト数≠バイト数
		if ( bytData2.length <2 ){
				return true;
		}
		else{
			return false;			
		}
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD>引数(_pstrData)が全角かどうかチェックします．</DD>
	 * <BR>
	 *
	 * </DL>
	 * @param trData
	 * @return
	 */
	public static boolean checkZenkaku(String trData)

	{
		//ユニコードに変換
		String strData = trData.replace( '\r', '　');
		strData = strData.replace( '\t', '　');
		strData = strData.replace( '\n', '　');
	
		int intByte = strData.length() * 2;
		//SJISに変換
		//byte[] bytData2=strData.getBytes("MS932");
		byte[] bytData2=null;
        bytData2 = StringUtils.getBytes(strData);
        //バイト数≠バイト数
		if ( intByte == (bytData2.length) )
			return true;
		else
			return false;
	}
	
	public static boolean checkEisu(String data){
		char[] datas=data.toCharArray();
		for(int i=0;i<datas.length;i++){
			char c=datas[i];
			if(checkEisu(c)==false){
				return false;
		    }		
		}
		
		return true;
	}
	public static boolean isKatakana(char c){
		final char[] kanas={
				'ｱ','ｲ','ｳ','ｴ','ｵ',
				'ｶ','ｷ','ｸ','ｹ','ｺ',
				'ｻ','ｼ','ｽ','ｾ','ｿ',
				'ﾀ','ﾁ','ﾂ','ﾃ','ﾄ',
				'ﾅ','ﾆ','ﾇ','ﾈ','ﾉ',
				'ﾊ','ﾋ','ﾌ','ﾍ','ﾎ',
				'ﾏ','ﾐ','ﾑ','ﾒ','ﾓ',
				'ﾔ','ｲ','ﾕ','ｴ','ﾖ',
				'ﾗ','ﾘ','ﾙ','ﾚ','ﾛ',
				'ﾜ','ｲ','ｳ','ｴ','ｦ',
				'ｧ','ｨ','ｩ','ｪ','ｫ',
				'ｬ','ｭ','ｮ'
		};
		for(int i=0;i<kanas.length;i++){
			if(kanas[i]==c){
				return true;
			}
		}
		return false;
	}
	public static boolean checkEisu(char c){
	    if ( (c < '0' || c > '9') &&        //数字でない
	             (c < 'a' || c > 'z') &&        //小文字アルファベットでない
	             (c < 'A' || c > 'Z') ) {       //大文字アルファベットでない
	             return false;
	    }		
	    return true;
	}
	public static boolean checkLittleEi(char c){
	    if (
	         c >= 'a' && c <= 'z'){
	        return true;
	    }else{
	    	return false;
	    }
	}	
}

