/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：ImageContants.java
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

package com.fas.common.constants.screen;

import javax.swing.ImageIcon;

import com.fas.common.utils.ImageFileUtils;

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
public class ImageConstants {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ImageConstants() {
	}

	/**	Common */
	public static ImageIcon SYS_LOGO = null;

	public static ImageIcon CMP_LOGO = null;

	public static final ImageIcon PRINTER = ImageFileUtils.getResourceFile("image/printer.gif");

	public static final ImageIcon TITLE = ImageFileUtils.getResourceFile("image/style.png");

	public static ImageIcon BUTTON_ICON = ImageFileUtils.getResourceFile("image/button_icon.png");

	public static final ImageIcon CALENDAR_BUTTON_ICON = ImageFileUtils.getResourceFile("image/calendar.png");

	public static final ImageIcon HELP_ICON = ImageFileUtils.getResourceFile("image/help.png");

	public static ImageIcon LOGIN_BACK_GROUND_IMAGE = ImageFileUtils.getResourceFile("image/background.jpg");

	public static final ImageIcon SUPPORT_ICON = ImageFileUtils.getResourceFile("image/support.png");
	
	public static final ImageIcon USER_ICON = ImageFileUtils.getResourceFile("image/user.png");
	
	public static final ImageIcon QUESTION = ImageFileUtils.getResourceFile("image/question.png");

	/**	Menu */
	public static final ImageIcon MENU_EDIT_ICON = ImageFileUtils.getResourceFile("image/edit.png");

	public static final ImageIcon MENU_DEL_ICON = ImageFileUtils.getResourceFile("image/del.png");

	public static final ImageIcon MENU_DEL_ALL_ICON = ImageFileUtils.getResourceFile("image/del_all.png");

	public static final ImageIcon MENU_COPY_ICON = ImageFileUtils.getResourceFile("image/copy.png");

	public static final ImageIcon MENU_NEW_ICON = ImageFileUtils.getResourceFile("image/add.png");
	
	public static final ImageIcon MENU_SELECT_ICON = ImageFileUtils.getResourceFile("image/select.png");
	
	/**	CUSTOMER PROJECT  */
	public static final ImageIcon PROJECT = ImageFileUtils.getResourceFile("image/Japan_icon.png");

	public static final ImageIcon INTERNET = ImageFileUtils.getResourceFile("image/internet.png");
	
	public static final ImageIcon HELMET = ImageFileUtils.getResourceFile("image/helmet.png");
	
	public static final ImageIcon TOOL = ImageFileUtils.getResourceFile("image/tool.png");
	
	public static final ImageIcon BOAT = ImageFileUtils.getResourceFile("image/boat.png");
	

}

