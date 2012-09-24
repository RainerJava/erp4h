/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SoundUtils.java
*
*     記述				：
*     
*     作成日			：2010/02/24   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.common.utils;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;

/**
 * @author PC13
 *
 */
public class SoundUtils {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SoundUtils() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param filePath
	 */
	@SuppressWarnings("deprecation")
	public static void playSound(String filePath) {
		try {
			File file = new File(filePath);
			AudioClip clip = Applet.newAudioClip(file.toURL());
			clip.play();
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String args[]) {
		playSound("C:\\ringin.wav");
		
	}
}
