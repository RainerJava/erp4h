/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：XFrameTableRenderer.java
*
*     記述				：
*     
*     作成日			：2010/03/25
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface.table.frozenTable;
import java.awt.Color;
import java.awt.Component;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.utils.DateUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.label.BaseLabel;


/**
 * @author PC14
 *
 */
public class XFrameTableRenderer extends DefaultJXTabelCellRenderer {
	private static final long serialVersionUID = 1L;
	private int[] idex;
	
	public XFrameTableRenderer() {
	}
	
	public Component getTableCellRendererComponent(JXTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {		
		final int col =table.getColumnModel().getColumn(column).getModelIndex();
		// カーソル選択のカラー設定
		
		if (StringUtils.isDouble("" + value)) {
			setHorizontalAlignment(BaseLabel.RIGHT);
			if(Double.parseDouble( "" + value) < 0){
				setForeground(ColorConstants.LABEL_REQUIRED_FOR_COLOR);
			}
		}
		else{
			setHorizontalAlignment(BaseLabel.LEFT);
		}
		
		// set for see_table
		if(col == 0)setHorizontalAlignment(BaseLabel.LEFT);
		if(idex != null){
			for(int i = 0; i < idex.length;i++){
				if(row == idex[i])setForeground(ColorConstants.LABEL_REQUIRED_FOR_COLOR);
			}
		}

		String stValue = "" + value;
		String stTemp = StringUtils.subString(stValue, 10);
		// data is "YYYY/MM/DD"
		if (DateUtils.getDateWithyyyymmdd(stTemp) > 0) {
			setHorizontalAlignment(BaseLabel.CENTER);
		}
		// data is "HH:MM:SS"
		if (stValue != null) {
			String[] arrStValue = stValue.split(":");
			if (arrStValue.length > 2) {
				setHorizontalAlignment(BaseLabel.CENTER);
			}
		}		
		// data format is "x10("
		if (stValue != null) {
			int iOf = stValue.indexOf("x10");
			if (iOf > 0) {
				setHorizontalAlignment(BaseLabel.RIGHT);
			}
		}	
		
		if(stValue.trim().equals("レ")){
			setHorizontalAlignment(BaseLabel.CENTER);			
			setForeground(ColorConstants.LABEL_REQUIRED_FOR_COLOR);
		}				
		if (row % 2 == 0) {
				
			setBackground(ColorConstants.TABLE_EVEN_ROW_BACKGROUND_COLOR);
			setForeground(ColorConstants.TABLE_EVEN_ROW_FORE_COLOR);
				
		} else {				
			setBackground(ColorConstants.TABLE_ODD_ROW_BACKGROUND_COLOR);
			setForeground(ColorConstants.TABLE_ODD_ROW_FORE_COLOR);				
		}		
		if(column == 20 ){
			if(stValue.equals("0.0")){
				this.setBackground(Color.RED);	
			} else {
				this.setBackground(new Color(252, 255, 193));	
			}
			
		}
		
		if (table.isRowSelected(row)) {
			setBackground(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR);
			setForeground(ColorConstants.TABLE_ROW_FOCUS_FORE_COLOR);
		}
		if (hasFocus)
			if(column == 20 )
				this.setBackground(Color.YELLOW);
			else
			    this.setBackground(ColorConstants.TABLE_CELL_FOCUS_BACKGROUND_COLOR);	
		
		if(stValue.indexOf("," ) > -1 && StringUtils.isDouble(stValue.replaceAll(",", "")) ){
			setHorizontalAlignment(BaseLabel.RIGHT);
			
			if(Double.parseDouble(stValue.replaceAll(",", "")) < 0){
				setForeground(ColorConstants.LABEL_REQUIRED_FOR_COLOR);
			}
		}
		try
		{
			for(int i = 0; i < table.getScrollTable().getColumnCount(); i++)
			{
				if(table.getScrollTable().getColumnName(i).trim().equals("可否"))
				{
					Object objUseFlg = table.getScrollTable().getValueAt(row, i);					
					if ( "●".equals("" + objUseFlg) || "★".equals("" + objUseFlg) || "レ".equals("" + objUseFlg) || "*".equals("" + objUseFlg) || "☆".equals("" + objUseFlg) )
					{						
						setForeground(ColorConstants.TABLE_SHIYOUFUKA_FORE_COLOR);
					}
					break;
				}
			}
		}
		catch(Exception ex)
		{}
		
		
		if(column >= 0 && column < 9 ){
			setHorizontalAlignment(BaseLabel.LEFT);
		}	
		if(column == 9)
			setHorizontalAlignment(BaseLabel.RIGHT);
		
		return this;
	}
}