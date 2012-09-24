/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SearchPatternComboBox.java
*
*     記述				：
*     
*     作成日			：2010/03/03  
*
*     作成者			：PC14
*
*     備考				：
*
**************************************************************************************/
package com.fas.sapp.business;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.border.Border;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.table.XFrameTable;
import com.fas.jface.table.XFrameTableModel;

/**
 * @author PC14
 *
 */
public class SearchComboBox extends BaseComboBox {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private XFrameTable XTable;
	/** */
	private XFrameTableModel XModel;
	/** */
	private Border DEFAULT_BORDER;
	/** */
	private int count = 0;
	/** */
	private boolean flgChange = true;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchComboBox(ComboBoxModel aModel) {
	    super(aModel);
	    init();
	    count = 1;
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param items
	 */
	public SearchComboBox(final Object[] items) {
	    super(items);
	    init();
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param items
	 */
	public SearchComboBox(Vector items) {
	    super(items);
	    init();
	}
	
	private void init() {
		
		DEFAULT_BORDER = getBorder();
		
		addFocusListener(new FocusListener() {

			/* (non-Javadoc)
			 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
			 */
			public void focusGained(FocusEvent e) {
				if (isEnabled()) {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_FOCUS_BACKGROUND_COLOR);
				} else {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_DISABLE_BACKGROUND_COLOR);
				}
				setBorder(FaceContants.DOT_LINE_BORDER);
			}

			/* (non-Javadoc)
			 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
			 */
			public void focusLost(FocusEvent e) {
				if (isEnabled()) {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_BACKGROUND_COLOR);
				} else {
					setBackground(ColorConstants.DEFAULT_COMBOBOX_DISABLE_BACKGROUND_COLOR);
				}
				setBorder(DEFAULT_BORDER);
			}
			
		});
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return 
	 */
	public void setTable(XFrameTable table, XFrameTableModel model) {
		this.XTable = table;
		this.XModel = model;
		count = 2;
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return 
	 */
	public void setStatus(boolean flg) {
		
		this.flgChange = flg;
	}

}