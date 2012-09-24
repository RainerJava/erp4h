/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：InspectTable.java
*
*     記述				：
*     
*     作成日			：2009/10/14   
*
*     作成者			：HccHung
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.table;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTableHeader;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.jface.label.BaseLabel;

/**
 * @author HccHung
 *
 */
public class InspectTable extends JXTable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    private boolean edited=false;
    
    /**
     * 
     */
    private boolean isBorderRequire = true;
    
    public InspectTable(InspectTableModel model){
	    super(model);
	    init();
	}
    
    public InspectTable(DefaultTableModel model){
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
		setColumnSelectionAllowed(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// Select only 1 row
		
		//set Color and Highlight
		setSelectionBackground(ColorConstants.TABLE_ROW_FOCUS_BACKGROUND_COLOR);
	  	setSelectionForeground(ColorConstants.TABLE_ROW_FOCUS_FORE_COLOR);
		setColumnMargin(2);
	  	
		setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);// 列のリサイズ制御をオフ

		addHighlighter(new RolloverHighlighter(ColorConstants.TABLE_ROLLOVER_BACKGROUND_COLOR, ColorConstants.TABLE_ROLLOVER_FOREGROUND_COLOR));
		
		setRolloverEnabled(true);// 色を変わります。

		setRowHeight(FaceContants.TABLE_ROW_HEIGHT);
		
		setSearchable(null);
		setColumnControlVisible(false);
		setHorizontalScrollEnabled(true);
		setFont(FontConstants.TABLE_HEADER_FONT);
		isBorderRequire = true;
		packAll();
		
		// set border when focus
		this.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusGained(FocusEvent fe) {
		    	if (isBorderRequire) {
		    		setBorder(BorderFactory.createLineBorder(ColorConstants.TABLE_ROLLOVER_BACKGROUND_COLOR));
		    		getTableHeader().setBorder(BorderFactory.createRaisedBevelBorder());
		    		getTableHeader().setBackground(ColorConstants.TABLE_HEADER_FOCUS_BACKGROUND_COLOR);
		    	}
		    }
		    @Override
		    public void focusLost(FocusEvent fe) {
		    	if (isBorderRequire) {
			    	setBorder(BorderFactory.createEmptyBorder());
					getTableHeader().setBorder(new EtchedBorder(EtchedBorder.LOWERED));
					getTableHeader().setBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
		    	}
		    }
		});
		
		setToolTipText("項目を選択して下さい。");
	}
	
	/**
	 * @author PC12 change Row color when mouse move over
	 */
	class RolloverHighlighter extends ColorHighlighter {
		RolloverHighlighter(Color backGround, Color foreGrond) {
			super.setBackground(backGround);
			super.setForeground(foreGrond);
			super.setHighlightPredicate(HighlightPredicate.ROLLOVER_ROW);
		}
	}
	
    /* (non-Javadoc)
     * @see org.jdesktop.swingx.JXTable#createDefaultTableHeader()
     */
    @Override
    protected JTableHeader createDefaultTableHeader() {
    	
    	JXTableHeader tableHeader = new JXTableHeader(columnModel){

			private static final long serialVersionUID = 1L;

			@Override
            public void updateUI() {
                super.updateUI();
                // need to do in updateUI to survive toggling of LAF
                if (getDefaultRenderer() instanceof JLabel) {
                	((JLabel) getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
                }
            }
        };
        tableHeader.setBackground(ColorConstants.TABLE_HEADER_BACKGROUND_COLOR);
        tableHeader.setForeground(ColorConstants.TABLE_HEADER_FORE_COLOR);
        tableHeader.setReorderingAllowed(true);
        //tableHeader.setPreferredSize(new Dimension(0, FaceContants.TABLE_HEADER_HEIGHT));
        Dimension d = tableHeader.getPreferredSize(); 
        tableHeader.setPreferredSize(new Dimension(d.width + 400, FaceContants.TABLE_HEADER_HEIGHT ));
        tableHeader.setToolTipText("列見出しを選択すると並び替え出来ます。");
		//Make header Table Lowerer
		Border TABLE_HEADER_BORDER = new EtchedBorder(EtchedBorder.LOWERED);
		tableHeader.setBorder(TABLE_HEADER_BORDER);
		tableHeader.setFont(FontConstants.TABLE_HEADER_FONT);
		//table Header mouse event
        tableHeader.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				getTableHeader().setCursor(null);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				double dHeight = getTableHeader().getPreferredSize().getHeight();
				double dWidth = getColumnModel().getTotalColumnWidth();
				getTableHeader().setPreferredSize(new Dimension((int)dWidth, (int)dHeight));
				getTableHeader().revalidate();
				getTableHeader().repaint();
			}
        	
        });
        
        return tableHeader;
    }

	public boolean isBorderRequire() {
		return isBorderRequire;
	}

	public void setBorderRequire(boolean isBorderRequire) {
		this.isBorderRequire = isBorderRequire;
	}
}
