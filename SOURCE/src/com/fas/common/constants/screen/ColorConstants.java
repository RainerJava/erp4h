/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：ColorConstants.java
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

import java.awt.Color;
import java.awt.SystemColor;

/**
 * <DL>
 * <DT>クラス記述：</DT>
 * <DD></DD>
 * <BR>
 * <DT>変更歴史：</DT>
 * <DD>著作者: PC13</DD><BR>
 * <DD></DD>
 * </DL>
 */

public class ColorConstants {

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public ColorConstants() {
	}

	/** パネルの背景色 */
	public static Color DEFAULT_BACK_COLOR = SystemColor.control;
	/** フレーム画面の背景色 */
	public static Color FRAME_DEFAULT_COLOR = SystemColor.control;
	/** フレーム画面のテキスト色 */
	public static Color FRAME_DEFAULT_FORE_COLOR = new Color(51, 153, 102);
	/** パネル画面の背景色 */
	public static Color PANEL_DEFAULT_COLOR = SystemColor.control;
	/** ステータスバーの背景色 */
	public static Color STATUS_BAR_DEFAULT_COLOR = SystemColor.control;

	/** 選択CELLの文字の色 */
	public static Color TABLE_SETECTION_FOREGROUND_COLOR = Color.WHITE;
	/** 選択行の文字の色 */
	public static Color TABLE_ROLLOVER_FOREGROUND_COLOR = Color.BLACK;
	/** 行背景色 */
	public static Color TABLE_ROLLOVER_BACKGROUND_COLOR = Color.YELLOW;
	/** 行選択 */
	public static Color SELECTED_ROW = new Color(200, 240, 255);
	/** */
	public static Color BASE_LINE_COLOR = Color.RED;
	/** */
	public static Color POINT_COLOR = new Color(0, 128, 0);
	/** テーブルヘッダ色 */
	public static Color TABLE_HEADER_BACKGROUND_COLOR = new Color(169, 169, 245);
	/** Table Header Color when Focus */
	public static Color TABLE_HEADER_FOCUS_BACKGROUND_COLOR = new Color(169, 200, 245);
	/** テーブルヘッダ色 */
	public static Color TABLE_HEADER_FORE_COLOR = Color.BLACK;
	/** テーブルヘッダ色 */
	public static Color TABLE_EVEN_ROW_BACKGROUND_COLOR = Color.WHITE;
	/** テーブルヘッダ色 */
	public static Color TABLE_EVEN_ROW_FORE_COLOR = Color.BLACK;
	/** テーブルヘッダ色 */
	public static Color TABLE_ODD_ROW_BACKGROUND_COLOR = new Color(246, 246, 246);
	/** テーブルヘッダ色 */
	public static Color TABLE_ODD_ROW_FORE_COLOR = Color.BLACK;
	/** テーブルヘッダ色 */
	public static Color TABLE_ROW_FOCUS_BACKGROUND_COLOR = new Color(249, 138, 245);
	/** テーブルヘッダ色 */
	public static Color TABLE_ROW_FOCUS_FORE_COLOR = Color.BLACK;
	/** */
	public static Color TABLE_SHIYOUFUKA_FORE_COLOR = Color.RED;
	/** */
	public static Color TABLE_LINE_COLOR = new Color(230, 230, 230);
	/** Cell */
	public static Color TABLE_CELL_FOCUS_BACKGROUND_COLOR = Color.CYAN;

	/** Seperatorの背景色 */
	public static Color DEFAULT_SEPERATOR_BACK_COLOR = new Color(255, 255, 255);
	/** カレンダーパネルの背景色 */
	public static Color CALENDAR_DEFAULT_BACK_COLOR = SystemColor.WHITE;
	/** */
	public static Color CALENDAR_DEFAULT_HEADER_BACK_COLOR = SystemColor.control;
	/** カレンダー日曜日の色 */
	public static Color CALENDAR_NICHIYOUBI_COLOR = Color.RED;
	/** カレンダー土曜日の色 */
	public static Color CALENDAR_DOYOUBI_COLOR = Color.BLUE;
	/** カレンダー休みの日の色 */
	public static Color CALENDAR_YASUMINOHI_COLOR = Color.RED;
	/** カレンダー当日の色 */
	public static Color CALENDAR_KYOU_COLOR = Color.GRAY;
	/** 土曜日の色 */
	public static Color SATURDAY_COLOR = Color.BLUE;
	/** 日曜日の色 */
	public static Color SUNDAY_COLOR = Color.RED;
	/** 祝日の色 */
	public static Color HOLIDAY_BACK_COLOR = new Color(255, 170, 170);
	/** 特別日の色 */
	public static Color SPECIALDAY_BACK_COLOR = new Color(215, 198, 253);

	/** テキストボックスの背景色 */
	public static Color DEFAULT_RAJIO_BACKGROUND_COLOR = SystemColor.control;
	/** テキストボックスのテキスト色 */
	public static Color DEFAULT_RAJIO_FORE_COLOR = Color.BLACK;
	/** フォクスがあるテキストボックスの背景色 */
	public static Color DEFAULT_RAJIO_FOCUS_BACKGROUND_COLOR = SystemColor.control;
	/** フォクスがあるテキストボックスのテキスト色 */
	public static Color DEFAULT_RAJIO_FOCUS_FORE_COLOR = Color.BLACK;
	/** 無効があるテキストボックスの背景色 */
	public static Color DEFAULT_RAJIO_DISABLE_BACKGROUND_COLOR = SystemColor.control;
	/** 無効があるテキストボックスのテキスト色 */
	public static Color DEFAULT_RAJIO_DISABLE_FORE_COLOR = Color.GRAY;
	/** 無効のテキストボックス色 */
	public static final Color TEXT_DISABLE_FORE_COLOR = Color.BLUE;
	/** テキストの背景色 */
	public static final Color DEFAULT_TEXT_BACK_COLOR = Color.WHITE;

	/* チェックボックスの背景色 */
	public static Color DEFAULT_CHECKBOX_BACKGROUND_COLOR = SystemColor.control;
	/** チェックボックスのテキスト色 */
	public static Color DEFAULT_CHECKBOX_FORE_COLOR = Color.BLACK;
	/** フォクスがあるチェックボックスの背景色 */
	public static Color DEFAULT_CHECKBOX_FOCUS_BACKGROUND_COLOR = Color.YELLOW;
	/** フォクスがあるチェックボックスのテキスト色 */
	public static Color DEFAULT_CHECKBOX_FOCUS_FORE_COLOR = Color.BLACK;
	/** 無効があるチェックボックスの背景色 */
	public static Color DEFAULT_CHECKBOX_DISABLE_BACKGROUND_COLOR = SystemColor.control;
	/** 無効があるチェックボックスのテキスト色 */
	public static Color DEFAULT_CHECKBOX_DISABLE_FORE_COLOR = Color.GRAY;

	/** テキストボックスの背景色 */
	public static Color DEFAULT_TEXT_BACKGROUND_COLOR = Color.WHITE;
	/** テキストボックスのテキスト色 */
	public static Color DEFAULT_TEXT_FORE_COLOR = Color.BLACK;
	/** フォクスがあるテキストボックスの背景色 */
	public static Color DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR = Color.YELLOW;
	/** フォクスがあるテキストボックスのテキスト色 */
	public static Color DEFAULT_TEXT_FOCUS_FORE_COLOR = Color.BLACK;
	/** 無効があるテキストボックスの背景色 */
	public static Color DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR = SystemColor.control;
	/** 無効があるテキストボックスのテキスト色 */
	public static Color DEFAULT_TEXT_DISABLE_FORE_COLOR = Color.GRAY;

	/** レーベルの背景色 */
	public static Color DEFAULT_LABEL_BACKGROUND_COLOR = SystemColor.control;
	/** レーベルのテキスト色 */
	public static Color DEFAULT_LABEL_FORE_COLOR = Color.BLACK;
	/** フォクスがあるレーベルの背景色 */
	public static Color DEFAULT_LABEL_FOCUS_BACKGROUND_COLOR = SystemColor.control;
	/** フォクスがあるレーベルのテキスト色 */
	public static Color DEFAULT_LABEL_FOCUS_FORE_COLOR = Color.BLACK;
	/** 無効があるレーベルの背景色 */
	public static Color DEFAULT_LABEL_DISABLE_BACKGROUND_COLOR = SystemColor.control;
	/** 無効があるレーベルのテキスト色 */
	public static Color DEFAULT_LABEL_DISABLE_FORE_COLOR = Color.GRAY;

	/** ボタンの背景色 */
	public static Color DEFAULT_BUTTON_BACKGROUND_COLOR = SystemColor.control;
	/** ボタンのテキスト色 */
	public static Color DEFAULT_BUTTON_FORE_COLOR = Color.BLACK;
	/** フォクスがあるボタンの背景色 */
	public static Color DEFAULT_BUTTON_FOCUS_BACKGROUND_COLOR = SystemColor.control;
	/** フォクスがあるボタンのテキスト色 */
	public static Color DEFAULT_BUTTON_FOCUS_FORE_COLOR = Color.BLACK;
	/** 無効があるボタンの背景色 */
	public static Color DEFAULT_BUTTON_DISABLE_BACKGROUND_COLOR = SystemColor.control;
	/** 無効があるボタンのテキスト色 */
	public static Color DEFAULT_BUTTON_DISABLE_FORE_COLOR = Color.GRAY;
	/** デフォルトボーターン色 */
	public static final Color DEFAULT_BUTTON_COLOR = Color.WHITE;

	/** TABBEDPANEの背景色 */
	public static Color DEFAULT_TABBEDPANE_BACKGROUND_COLOR = SystemColor.control;
	/** TABBEDPANEのテキスト色 */
	public static Color DEFAULT_TABBEDPANE_FORE_COLOR = Color.BLACK;

	/** Componentの背景色 */
	public static Color DEFAULT_COMPONENT_BACKGROUND_COLOR = SystemColor.control;
	/** Componentのテキスト色 */
	public static Color DEFAULT_COMPONENT_FORE_COLOR = Color.BLACK;
	/** フォクスがあるComponentの背景色 */
	public static Color DEFAULT_COMPONENT_FOCUS_BACKGROUND_COLOR = SystemColor.control;
	/** フォクスがあるComponentのテキスト色 */
	public static Color DEFAULT_COMPONENT_FOCUS_FORE_COLOR = Color.BLACK;
	/** 無効があるComponentの背景色 */
	public static Color DEFAULT_COMPONENT_DISABLE_BACKGROUND_COLOR = SystemColor.control;
	/** 無効があるComponentのテキスト色 */
	public static Color DEFAULT_COMPONENT_DISABLE_FORE_COLOR = Color.GRAY;

	/** Comboboxの背景色 */
	public static Color DEFAULT_COMBOBOX_BACKGROUND_COLOR = Color.WHITE;
	/** Comboboxのテキスト色 */
	public static Color DEFAULT_COMBOBOX_FORE_COLOR = Color.BLACK;
	/** フォクスがあるComboboxの背景色 */
	public static Color DEFAULT_COMBOBOX_FOCUS_BACKGROUND_COLOR = Color.YELLOW;
	/** */
	public static Color DEFAULT_COMBOBOX_SELECTION_BACKGROUND_COLOR = new Color(4, 4, 180);
	/** */
	public static Color DEFAULT_COMBOBOX_SELECTION_FOR_COLOR = Color.WHITE;
	/** フォクスがあるComboboxのテキスト色 */
	public static Color DEFAULT_COMBOBOX_FOCUS_FORE_COLOR = Color.BLACK;
	/** 無効があるComboboxの背景色 */
	public static Color DEFAULT_COMBOBOX_DISABLE_BACKGROUND_COLOR = SystemColor.control;
	/** 無効があるComboboxのテキスト色 */
	public static Color DEFAULT_COMBOBOX_DISABLE_FORE_COLOR = Color.GRAY;

	/** */
	public static Color MENU_LBL_BACK_COLOR = new Color(153, 255, 204);
	/** */
	public static Color MENU_BUTTON_BACK_COLOR = new Color(153, 255, 204);
	/** */
	public static Color MENU_BUTTON_FORE_COLOR = new Color(153, 255, 204);
	/** */
	public static Color MENU_BUTTON_FOCUS_BACK_COLOR = Color.YELLOW;
	/** */
	public static Color MENU_BUTTON_OVER_BACK_COLOR = new Color(255, 153, 204);
	/** */
	public static Color EXE_BUTTON_BACK_COLOR = new Color(204, 255, 204);
	/** */
	public static Color EXE_BUTTON_FORE_COLOR = new Color(204, 255, 204);
	/** */
	public static Color EXE_BUTTON_FOCUS_BACK_COLOR = Color.YELLOW;
	/** */
	public static Color EXE_BUTTON_ROLLOVER_FORE_COLOR = Color.BLUE;
	/** */
	public static Color EXE_BUTTON_OVER_BACK_COLOR = new Color(255, 153, 204);

	public static Color CELL_MAKIN_PINK_COLOR = new Color(255, 150, 255);
	/** */
	public static Color LABEL_EDIT_BACKGROUND_COLOR = new Color(255, 215, 0);
	/** */
	public static Color LABEL_EDIT_FORE_COLOR = Color.BLACK;
	/** */
	public static Color LABEL_REGISTER_MODE_BACKGROUND_COLOR = new Color(60, 179, 113);
	/** */
	public static Color LABEL_CD_BACKGROUND_COLOR = new Color(204, 255, 204);
	/** */
	public static Color LABEL_CD_FORE_COLOR = new Color(204, 255, 204);
	/** */
	public static Color LABEL_NEW_MODE_BACKGROUND_COLOR = Color.GREEN;
	/** */
	public static Color LABEL_EDT_MODE_BACKGROUND_COLOR = Color.YELLOW;
	/** */
	public static Color LABEL_DELETE_MODE_BACKGROUND_COLOR = Color.RED;
	/** */
	public static Color LABEL_COPY_MODE_BACKGROUND_COLOR = Color.CYAN;
	/** */
	public static Color LABEL_VIEW_BACKGROUND_COLOR = new Color(43, 101, 238);


	/** */
	public static Color LABEL_REQUIRED_FOR_COLOR = Color.RED;
	/** */
	public static Color RED_COLOR = Color.RED;

	/***/
	public static Color BTN_SEARCH_FILTER_COLOR = SystemColor.PINK;

	/** add by HUNG NV 2010.08.06 11h55 */
	public static Color BORDER_BOUND_TABLE_PANEL_LOST_FOCUS = new Color(73, 139, 245);
	/***/
	public static Color BORDER_BOUND_TABLE_PANEL_GAIN_FOCUS = Color.RED;
}

//
///** *********************************************************************************
// *     
// *     会社名			：林兼コンピューター株式会社
// *
// *     プロジェクト名	：
// * 
// *     ファイル名		：ColorConstants.java
// *
// *     記述				：
// *     
// *     作成日			：2009/10/05   
// *
// *     作成者			：PC13
// *
// *     備考				：
// *
// **************************************************************************************/
//
//package com.fas.common.constants.screen;
//
//import java.awt.Color;
//import java.awt.SystemColor;
//
///**
// * <DL>
// * <DT>クラス記述：</DT>
// * <DD></DD>
// * <BR>
// * <DT>変更歴史：</DT>
// * <DD>著作者: PC13</DD><BR>
// * <DD></DD>
// * </DL>
// */
//
//public class ColorConstants {
//
//	/**
//	 * <DL>
//	 * <DT>コンストラクター記述：</DT>
//	 * <DD></DD>
//	 * <BR>
//	 * 
//	 * </DL>
//	 */
//	public ColorConstants() {
//	}
//
//	/** パネルの背景色 */
//	public static Color DEFAULT_BACK_COLOR = SystemColor.control;
//	/** フレーム画面の背景色 */
//	public static Color FRAME_DEFAULT_COLOR = SystemColor.control;
//	/** フレーム画面のテキスト色 */
//	public static Color FRAME_DEFAULT_FORE_COLOR = new Color(51, 153, 102);
//	/** パネル画面の背景色 */
//	public static Color PANEL_DEFAULT_COLOR = SystemColor.control;
//	/** ステータスバーの背景色 */
//	public static Color STATUS_BAR_DEFAULT_COLOR = SystemColor.control;
//
//	/** 選択CELLの文字の色 */
//	public static Color TABLE_SETECTION_FOREGROUND_COLOR = Color.WHITE;
//	/** 選択行の文字の色 */
//	public static Color TABLE_ROLLOVER_FOREGROUND_COLOR = Color.BLACK;
//	/** 行背景色 */
//	public static Color TABLE_ROLLOVER_BACKGROUND_COLOR = Color.YELLOW;
//	/** 行選択 */
//	public static Color SELECTED_ROW = new Color(200, 240, 255);
//	/** */
//	public static Color BASE_LINE_COLOR = Color.RED;
//	/** */
//	public static Color POINT_COLOR = new Color(0, 128, 0);
//	/** テーブルヘッダ色 */
//	public static Color TABLE_HEADER_BACKGROUND_COLOR = new Color(169, 169, 245);
//	/** Table Header Color when Focus*/
//	public static Color TABLE_HEADER_FOCUS_BACKGROUND_COLOR = new Color(169, 200, 245);
//	/** テーブルヘッダ色 */
//	public static Color TABLE_HEADER_FORE_COLOR = Color.BLACK;
//	/** テーブルヘッダ色 */
//	public static Color TABLE_EVEN_ROW_BACKGROUND_COLOR = Color.WHITE;
//	/** テーブルヘッダ色 */
//	public static Color TABLE_EVEN_ROW_FORE_COLOR = Color.BLACK;
//	/** テーブルヘッダ色 */
//	public static Color TABLE_ODD_ROW_BACKGROUND_COLOR = new Color(246, 246, 246);
//	/** テーブルヘッダ色 */
//	public static Color TABLE_ODD_ROW_FORE_COLOR = Color.BLACK;
//	/** テーブルヘッダ色 */
//	public static Color TABLE_ROW_FOCUS_BACKGROUND_COLOR = new Color(249, 138, 245);
//	/** テーブルヘッダ色 */
//	public static Color TABLE_ROW_FOCUS_FORE_COLOR = Color.BLACK;
//	/** */
//	public static Color TABLE_SHIYOUFUKA_FORE_COLOR = Color.RED;
//	/** */
//	public static Color TABLE_LINE_COLOR = new Color(230, 230, 230);
//	/** Cell */
//	public static Color TABLE_CELL_FOCUS_BACKGROUND_COLOR = Color.CYAN;
//
//	/** Seperatorの背景色 */
//	public static Color DEFAULT_SEPERATOR_BACK_COLOR = new Color(255, 255, 255);
//	/** カレンダーパネルの背景色 */
//	public static Color CALENDAR_DEFAULT_BACK_COLOR = SystemColor.WHITE;
//	/** */
//	public static Color CALENDAR_DEFAULT_HEADER_BACK_COLOR = SystemColor.control;
//	/** カレンダー日曜日の色 */
//	public static Color CALENDAR_NICHIYOUBI_COLOR = Color.RED;
//	/** カレンダー土曜日の色 */
//	public static Color CALENDAR_DOYOUBI_COLOR = Color.BLUE;
//	/** カレンダー休みの日の色 */
//	public static Color CALENDAR_YASUMINOHI_COLOR = Color.RED;
//	/** カレンダー当日の色 */
//	public static Color CALENDAR_KYOU_COLOR = Color.GRAY;
//	/** 土曜日の色 */
//	public static Color SATURDAY_COLOR = Color.BLUE;
//	/** 日曜日の色 */
//	public static Color SUNDAY_COLOR = Color.RED;
//	/** 祝日の色 */
//	public static Color HOLIDAY_BACK_COLOR = new Color(255, 170, 170);
//	/** 特別日の色 */
//	public static Color SPECIALDAY_BACK_COLOR = new Color(215, 198, 253);
//
//	/** テキストボックスの背景色 */
//	public static Color DEFAULT_RAJIO_BACKGROUND_COLOR = SystemColor.control;
//	/** テキストボックスのテキスト色 */
//	public static Color DEFAULT_RAJIO_FORE_COLOR = Color.BLACK;
//	/** フォクスがあるテキストボックスの背景色 */
//	public static Color DEFAULT_RAJIO_FOCUS_BACKGROUND_COLOR = SystemColor.control;
//	/** フォクスがあるテキストボックスのテキスト色 */
//	public static Color DEFAULT_RAJIO_FOCUS_FORE_COLOR = Color.BLACK;
//	/** 無効があるテキストボックスの背景色 */
//	public static Color DEFAULT_RAJIO_DISABLE_BACKGROUND_COLOR = SystemColor.control;
//	/** 無効があるテキストボックスのテキスト色 */
//	public static Color DEFAULT_RAJIO_DISABLE_FORE_COLOR = Color.GRAY;
//	/** 無効のテキストボックス色 */
//	public static final Color TEXT_DISABLE_FORE_COLOR = Color.BLUE;
//	/** テキストの背景色 */
//	public static final Color DEFAULT_TEXT_BACK_COLOR = Color.WHITE;
//
//	/* チェックボックスの背景色 */
//	public static Color DEFAULT_CHECKBOX_BACKGROUND_COLOR = SystemColor.control;
//	/** チェックボックスのテキスト色 */
//	public static Color DEFAULT_CHECKBOX_FORE_COLOR = Color.BLACK;
//	/** フォクスがあるチェックボックスの背景色 */
//	public static Color DEFAULT_CHECKBOX_FOCUS_BACKGROUND_COLOR = Color.YELLOW;
//	/** フォクスがあるチェックボックスのテキスト色 */
//	public static Color DEFAULT_CHECKBOX_FOCUS_FORE_COLOR = Color.BLACK;
//	/** 無効があるチェックボックスの背景色 */
//	public static Color DEFAULT_CHECKBOX_DISABLE_BACKGROUND_COLOR = SystemColor.control;
//	/** 無効があるチェックボックスのテキスト色 */
//	public static Color DEFAULT_CHECKBOX_DISABLE_FORE_COLOR = Color.GRAY;
//
//	/** テキストボックスの背景色 */
//	public static Color DEFAULT_TEXT_BACKGROUND_COLOR = Color.WHITE;
//	/** テキストボックスのテキスト色 */
//	public static Color DEFAULT_TEXT_FORE_COLOR = Color.BLACK;
//	/** フォクスがあるテキストボックスの背景色 */
//	public static Color DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR = Color.YELLOW;
//	/** フォクスがあるテキストボックスのテキスト色 */
//	public static Color DEFAULT_TEXT_FOCUS_FORE_COLOR = Color.BLACK;
//	/** 無効があるテキストボックスの背景色 */
//	public static Color DEFAULT_TEXT_DISABLE_BACKGROUND_COLOR = SystemColor.control;
//	/** 無効があるテキストボックスのテキスト色 */
//	public static Color DEFAULT_TEXT_DISABLE_FORE_COLOR = Color.GRAY;
//
//	/** レーベルの背景色 */
//	public static Color DEFAULT_LABEL_BACKGROUND_COLOR = SystemColor.control;
//	/** レーベルのテキスト色 */
//	public static Color DEFAULT_LABEL_FORE_COLOR = Color.BLACK;
//	/** フォクスがあるレーベルの背景色 */
//	public static Color DEFAULT_LABEL_FOCUS_BACKGROUND_COLOR = SystemColor.control;
//	/** フォクスがあるレーベルのテキスト色 */
//	public static Color DEFAULT_LABEL_FOCUS_FORE_COLOR = Color.BLACK;
//	/** 無効があるレーベルの背景色 */
//	public static Color DEFAULT_LABEL_DISABLE_BACKGROUND_COLOR = SystemColor.control;
//	/** 無効があるレーベルのテキスト色 */
//	public static Color DEFAULT_LABEL_DISABLE_FORE_COLOR = Color.GRAY;
//
//	/** ボタンの背景色 */
//	public static Color DEFAULT_BUTTON_BACKGROUND_COLOR = SystemColor.control;
//	/** ボタンのテキスト色 */
//	public static Color DEFAULT_BUTTON_FORE_COLOR = Color.BLACK;
//	/** フォクスがあるボタンの背景色 */
//	public static Color DEFAULT_BUTTON_FOCUS_BACKGROUND_COLOR = SystemColor.control;
//	/** フォクスがあるボタンのテキスト色 */
//	public static Color DEFAULT_BUTTON_FOCUS_FORE_COLOR = Color.BLACK;
//	/** 無効があるボタンの背景色 */
//	public static Color DEFAULT_BUTTON_DISABLE_BACKGROUND_COLOR = SystemColor.control;
//	/** 無効があるボタンのテキスト色 */
//	public static Color DEFAULT_BUTTON_DISABLE_FORE_COLOR = Color.GRAY;
//	/** デフォルトボーターン色 */
//	public static final Color DEFAULT_BUTTON_COLOR = Color.WHITE;
//
//	/** TABBEDPANEの背景色 */
//	public static Color DEFAULT_TABBEDPANE_BACKGROUND_COLOR = SystemColor.control;
//	/** TABBEDPANEのテキスト色 */
//	public static Color DEFAULT_TABBEDPANE_FORE_COLOR = Color.BLACK;
//
//	/** Componentの背景色 */
//	public static Color DEFAULT_COMPONENT_BACKGROUND_COLOR = SystemColor.control;
//	/** Componentのテキスト色 */
//	public static Color DEFAULT_COMPONENT_FORE_COLOR = Color.BLACK;
//	/** フォクスがあるComponentの背景色 */
//	public static Color DEFAULT_COMPONENT_FOCUS_BACKGROUND_COLOR = SystemColor.control;
//	/** フォクスがあるComponentのテキスト色 */
//	public static Color DEFAULT_COMPONENT_FOCUS_FORE_COLOR = Color.BLACK;
//	/** 無効があるComponentの背景色 */
//	public static Color DEFAULT_COMPONENT_DISABLE_BACKGROUND_COLOR = SystemColor.control;
//	/** 無効があるComponentのテキスト色 */
//	public static Color DEFAULT_COMPONENT_DISABLE_FORE_COLOR = Color.GRAY;
//
//	/** Comboboxの背景色 */
//	public static Color DEFAULT_COMBOBOX_BACKGROUND_COLOR = Color.WHITE;
//	/** Comboboxのテキスト色 */
//	public static Color DEFAULT_COMBOBOX_FORE_COLOR = Color.BLACK;
//	/** フォクスがあるComboboxの背景色 */
//	public static Color DEFAULT_COMBOBOX_FOCUS_BACKGROUND_COLOR = Color.YELLOW;
//	/** */
//	public static Color DEFAULT_COMBOBOX_SELECTION_BACKGROUND_COLOR = new Color(4, 4, 180);
//	/** */
//	public static Color DEFAULT_COMBOBOX_SELECTION_FOR_COLOR = Color.WHITE;
//	/** フォクスがあるComboboxのテキスト色 */
//	public static Color DEFAULT_COMBOBOX_FOCUS_FORE_COLOR = Color.BLACK;
//	/** 無効があるComboboxの背景色 */
//	public static Color DEFAULT_COMBOBOX_DISABLE_BACKGROUND_COLOR = SystemColor.control;
//	/** 無効があるComboboxのテキスト色 */
//	public static Color DEFAULT_COMBOBOX_DISABLE_FORE_COLOR = Color.GRAY;
//
//	/** */
//	public static Color MENU_LBL_BACK_COLOR = new Color(153, 255, 204);
//	/** */
//	public static Color MENU_BUTTON_BACK_COLOR = new Color(153, 255, 204);
//	/** */
//	public static Color MENU_BUTTON_FORE_COLOR = new Color(153, 255, 204);
//	/** */
//	public static Color MENU_BUTTON_FOCUS_BACK_COLOR = Color.YELLOW;
//	/** */
//	public static Color MENU_BUTTON_OVER_BACK_COLOR = new Color(255, 153, 204);
//	/** */
//	public static Color EXE_BUTTON_BACK_COLOR = new Color(204, 255, 204);
//	/** */
//	public static Color EXE_BUTTON_FORE_COLOR = new Color(204, 255, 204);
//	/** */
//	public static Color EXE_BUTTON_FOCUS_BACK_COLOR = Color.YELLOW;
//	/** */
//	public static Color EXE_BUTTON_OVER_BACK_COLOR = new Color(255, 153, 204);
//
//	/** */
//	public static Color LABEL_EDIT_BACKGROUND_COLOR = new Color(255, 215, 0);
//	/** */
//	public static Color LABEL_EDIT_FORE_COLOR = Color.BLACK;
//	/** */
//	public static Color LABEL_REGISTER_MODE_BACKGROUND_COLOR = new Color(60, 179, 113);
//	/** */
//	public static Color LABEL_CD_BACKGROUND_COLOR = new Color(204, 255, 204);
//	/** */
//	public static Color LABEL_CD_FORE_COLOR = new Color(204, 255, 204);
//	/** */
//	public static Color LABEL_NEW_MODE_BACKGROUND_COLOR = Color.GREEN;
//	/** */
//	public static Color LABEL_EDT_MODE_BACKGROUND_COLOR = Color.YELLOW;
//	/** */
//	public static Color LABEL_DELETE_MODE_BACKGROUND_COLOR = Color.YELLOW;
//	/** */
//	public static Color LABEL_COPY_MODE_BACKGROUND_COLOR = Color.CYAN;
//
//	/** */
//	public static Color LABEL_REQUIRED_FOR_COLOR = Color.RED;
//	/** */
//	public static Color RED_COLOR = Color.RED;
//
//	/***/
//	public static Color BTN_SEARCH_FILTER_COLOR = SystemColor.PINK;
//	
//	public static Color CELL_MAKIN_PINK_COLOR = new Color(255, 150, 255);
//	
//	public static Color EXE_BUTTON_ROLLOVER_FORE_COLOR = Color.BLUE;
//
//	public static Color BORDER_BOUND_TABLE_PANEL_GAIN_FOCUS = Color.RED;
//}