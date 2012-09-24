/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：InspectTableRenderer.java
 *
 *     記述				：
 *     
 *     作成日			：2010/02/22
 *
 *     作成者			：PC12
 *
 *     備考				：
 *
 **************************************************************************************/

package com.fas.jface.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.fas.common.utils.DateUtils;
import com.fas.common.utils.NumberUtils;
import com.fas.common.utils.StringUtils;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.label.BaseValueLabel;

/**
 * @author PC12
 * 
 */
public class InspectTableRenderer extends DefaultTableCellRenderer {
	
	private int[] align;

	public InspectTableRenderer() {

	}

	public InspectTableRenderer(int[] align) {
		this.align = new int[align.length];
		this.align = align;
	}
		
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		Component renderer = null;

		renderer = new com.fas.jface.label.BaseValueLabel("" + value);

		// カーソル選択のカラー設定
		if (table.isRowSelected(row)) {
			renderer.setBackground(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR);
			renderer.setForeground(ColorConstants.TABLE_ROW_FOCUS_FORE_COLOR);
		} else {
			if (row % 2 == 0) {
				renderer.setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
				renderer.setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
			} else {
				renderer.setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
				renderer.setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);
			}
		}

		//Define number of Column USE_FLG to show RED row		
		try
		{
			for(int i = 0; i < table.getColumnCount(); i++)
			{
				if(table.getColumnName(i) == "可否")
				{
					Object objUseFlg = table.getValueAt(row, i);
//					if ( (i != column) &&  ("●".equals("" + objUseFlg) || "★".equals("" + objUseFlg) || "レ".equals("" + objUseFlg) || "*".equals("" + objUseFlg) || "☆".equals("" + objUseFlg)) ) 
					if ( "●".equals("" + objUseFlg) || "★".equals("" + objUseFlg) || "レ".equals("" + objUseFlg) || "*".equals("" + objUseFlg) || "☆".equals("" + objUseFlg) )
					{
						renderer.setForeground(ColorConstants.TABLE_SHIYOUFUKA_FORE_COLOR);
					}
					break;
				}
			}
		}
		catch(Exception ex)
		{}
		

		if (align != null && align.length >= column) {
			{				
			((BaseValueLabel) renderer).setHorizontalAlignment(align[column]);
				int x = BaseLabel.RIGHT;
			}
			
			String numberText = (""+value).replace(",", "");
    		int iNum = NumberUtils.getIntFromString(numberText);
    		if(iNum < 0)((BaseValueLabel) renderer).setForeground(Color.red);
    		
		} else {
			if (StringUtils.isDouble("" + value)) {
				((BaseValueLabel) renderer).setHorizontalAlignment(BaseLabel.RIGHT);
			}

			if ("●".equals("" + value) || "★".equals("" + value) || "レ".equals("" + value) || "*".equals("" + value) || "☆".equals("" + value)) {
				((BaseValueLabel) renderer).setHorizontalAlignment(BaseLabel.CENTER);
			}
			
			String numberText = (""+value).replace(",", "");
    		int iNum = NumberUtils.getIntFromString(numberText);
    		if(iNum < 0)((BaseValueLabel) renderer).setForeground(Color.red);
    		
			String stValue = "" + value;
			String stTemp = StringUtils.subString(stValue, 10);
			// data is "YYYY/MM/DD"
			if (DateUtils.getDateWithyyyymmdd(stTemp) > 0) {
				((BaseValueLabel) renderer).setHorizontalAlignment(BaseLabel.CENTER);
			}
			// data is "HH:MM:SS"
			if (stValue != null) {
				String[] arrStValue = stValue.split(":");
				if (arrStValue.length > 2) {
					((BaseValueLabel) renderer).setHorizontalAlignment(BaseLabel.CENTER);
				}
			}

		}
		renderer.setFont(FontConstants.TABLE_TEXT_FONT);
	
		return renderer;
	}
}