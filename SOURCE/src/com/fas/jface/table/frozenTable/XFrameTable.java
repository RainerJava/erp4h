/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：XFrameTable.java
*
*     記述				：
*     
*     作成日			：2010/03/23   
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.table.frozenTable;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;



import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;

/**
 * @author PC14
 *
 */
public class XFrameTable extends JXTable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** */
    private boolean edited=false;
    /** */
    //protected TableCellRenderer genericRenderer = new XFrameTableRenderer();
    
    public XFrameTable(XFrameTableModel model){
	    super(model);
	    init();
	}
    
    public XFrameTable(DefaultTableModel model){
	    super(model);
	    init();
	}
    
    public void setEdited(boolean edited){
    	this.edited=edited;
    }
    
    public boolean isEdited(){
    	return edited;
    }
    
	void init(){

		// TABLE setting
		setRowSelectionAllowed(true); // 行選択を許可
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Select only 1 row
	  	setSelectionBackground(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR);
	  	setSelectionForeground(ColorConstants.TABLE_ROW_FOCUS_FORE_COLOR);
		setAutoResizeMode(AUTO_RESIZE_OFF);// 列のリサイズ制御をオフ
		this.getLockedTable().setAutoscrolls(true);
		// COLUMN setting
		setRowHeight(FaceContants.TABLE_ROW_HEIGHT);
		
		// HEADER setting
		 Dimension d = getTableHeader().getScrollHeader().getPreferredSize(); 
		getTableHeader().getScrollHeader().setPreferredSize(new Dimension(d.width + 200, FaceContants.TABLE_HEADER_HEIGHT + 10));
		setHeaderBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
		getTableHeader().setForeground(ColorConstants.TABLE_HEADER_FORE_COLOR);
		getTableHeader().setReorderingAllowed(true);
		//getScrollPane().setRowHeader(null);
		
		// set border when focus
		addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent fe) {
		    	setBorder(BorderFactory.createLineBorder(ColorConstants.TABLE_ROLLOVER_BACKGROUND_COLOR));
		    	setHeaderBorder(BorderFactory.createRaisedBevelBorder());
		    	setHeaderBackground(ColorConstants.TABLE_HEADER_FOCUS_BACKGROUND_COLOR);
		    }
		    @Override
		    public void focusLost(FocusEvent fe) {
		    	setBorder(BorderFactory.createEmptyBorder());
		    	setHeaderBorder(new EtchedBorder(EtchedBorder.LOWERED));
		    	setHeaderBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
		    }
		});	
		
		setToolTipText("項目を選択して下さい。");

	}
}
