/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ImageFileUtils.java
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

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

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

public class ImageFileUtils {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ImageFileUtils() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param key
	 * @return
	 */
	public static ImageIcon getResourceFile(String key) {
		
		ClassLoader loader = ImageFileUtils.class.getClassLoader();
		URL url = loader.getResource(key);
		ImageIcon image = null;
		
		try {
			image = new ImageIcon(url);
		} catch (Throwable e) {
			System.out.println(url);
			e.printStackTrace();
		}
		
		return image;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param key
	 * @return
	 */
	public static ImageIcon getImageFromFile(String strPath) {
		
		ImageIcon image = null;
		
		try {
			image = new ImageIcon(strPath);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param key
	 * @param width
	 * @param height
	 * @return
	 */
	public static Image getImageFile(String key) {
		
		ClassLoader loader = ImageFileUtils.class.getClassLoader();
		URL url = loader.getResource(key);
		Image image = null;
		
		try {
			image = (new ImageIcon(url)).getImage();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return image;
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
		ImageIcon f = getResourceFile("image/hanbai_icon.gif");
		System.out.println(f);
	}

}

