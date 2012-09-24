/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：FaceContents.java
*
*     記述				：
*     
*     作成日			：2009/08/27   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.common.constants.screen;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import com.fas.jface.BaseLineBorder;
import com.fas.jface.ComplementDashedBorder;
import com.fas.jface.EmptyLeftBorder;

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

public class FaceContants {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public FaceContants() {
		
	}

    /** */
    public static EmptyBorder border = new EmptyBorder( new Insets( 0, 0, 0, 10 ) );
    /** */
    public static EmptyBorder border1 = new EmptyBorder( new Insets( 0, 20, 0, 10 ) );
    /** */
    public static Color LBL_MODE_BACKGOURD = Color.PINK;
    /** */
    public static Color LBL_ENABLE_BACKGOURD = Color.CYAN;
    /** */
    public static Color LBL_DISABLE_BACKGOURD = Color.GREEN;   
    /** */
    public static AbstractBorder LABEL_BORDER = new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED, Color.WHITE, Color.GRAY), new EmptyBorder(1,1,1,1));
    /** */
    public static AbstractBorder BEVEL_BORDER = new CompoundBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED, Color.WHITE, Color.GRAY), new EmptyBorder(1,1,1,1));
    /** */
    public static Border PANEL_BORDER = BorderFactory.createEtchedBorder();
    /** */
    public static TitledBorder LBL_BORDER = new TitledBorder("");
    /** */
    public static TitledBorder TITLE_BORDER = BorderFactory.createTitledBorder("");
    /** */
    public static LineBorder MENU_BUTTON_BORDER = (new BaseLineBorder(Color.BLACK, 1));
    /** */
    public static LineBorder LINE_BORDER = (new BaseLineBorder(Color.BLACK, 1));
    /** */
    public static LineBorder LINE_BORDER_GRAY = (new BaseLineBorder(Color.GRAY, 1));
    /** */
	public static EmptyLeftBorder EMPTY_LEFT_BORDER = new EmptyLeftBorder(Color.BLACK, 1, new Insets(1, 5, 1, 1));
    /** */
    public static Border TABLE_HEADER_BORDER = new EtchedBorder(EtchedBorder.LOWERED);
    /** */
    public static Border DOT_LINE_BORDER = new ComplementDashedBorder();
    /** */
    public static int FRAME_TOP = 4;
    /** */
    public static int FRAME_LEFT = 4;
    /** */
    public static int FRAME_RIGHT = 4;
    /** */
    public static int FRAME_BOTTOM = 4;
    
    /** HEIGHT */
    public static int LABLE_HEIGHT = 22;
    public static int TEXT_HEIGHT = 22;
    public static int COMBO_HEIGHT = 22;
    public static int BUTTON_HEIGHT = 22;
    public static int COMBOBOX_LIST_HEIGHT = 21;
    
    public static int BUTTON_HEIGHT_SPACE = 3;
    public static int MENU_BUTTON_HEIGHT = 31;
    
    public static int TABLE_HEADER_HEIGHT = 24;
    public static int TABLE_ROW_HEIGHT = 22;

    public static int VERTICAL_SPACE = 4;
    public static int HORIZONTAL_SPACE = 5;
    public static int HORIZONTAL_SPACE_PANEL = 15;
    
    public static int BIG_LABLE_HEIGHT = 140;
    
    /** WIDTH */
    public static int POPUP_WIDTH = 200;
    public static int COMBOX_WIDTH = 150;
    public static int SCROLL_WIDTH = 170;
    public static int LBL_INFOR_LENGTH = 300;
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public static void setFrameInsets(int top, int left, int bottom, int right) {
    	FRAME_TOP = top;
    	FRAME_LEFT = left;
    	FRAME_BOTTOM = bottom;
    	FRAME_RIGHT = right;
    }
}