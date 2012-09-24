/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BrowserLaunch.java
*
*     記述				：
*     
*     作成日			：2009/11/16   
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

import java.lang.reflect.Method; 
import javax.swing.JOptionPane; 
import java.util.Arrays; 

public class BrowserLaunch { 
	static final String[] browsers = { "firefox", "opera", "konqueror", "epiphany",
		      "seamonkey", "galeon", "kazehakase", "mozilla", "netscape" };

	 /**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param url
	 */
	public static void openURL(String url) {
      String osName = System.getProperty("os.name");
      try {
         if (osName.startsWith("Mac OS")) {
            Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
            Method openURL = fileMgr.getDeclaredMethod("openURL",
               new Class[] {String.class});
            openURL.invoke(null, new Object[] {url});
         }
         else if (osName.startsWith("Windows"))
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
         else { //assume Unix or Linux
            boolean found = false;
            for (String browser : browsers)
               if (!found) {
                  found = Runtime.getRuntime().exec(
                     new String[] {"which", browser}).waitFor() == 0;
                  if (found)
                     Runtime.getRuntime().exec(new String[] {browser, url});
                  }
            if (!found)
               throw new Exception(Arrays.toString(browsers));
            }
         } catch (Exception e) {
        	 JOptionPane.showMessageDialog(null, "エラーが発生しました。\n" + e.toString());
         }
      }
}
