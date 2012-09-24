/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BumonCombo.java
*
*     記述				：
*     
*     作成日			：2010/02/18   
*
*     作成者			：PC13
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
import com.fas.common.utils.StringUtils;
import com.fas.jface.combo.BaseComboBox;
import com.fas.jface.label.BaseLabel;

/**
 * @author PC13
 *
 */
public class BumonComboBox extends BaseComboBox {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private BaseLabel lblSectName = null;
	/** */
	private Border DEFAULT_BORDER;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public BumonComboBox(ComboBoxModel aModel) {
	    super(aModel);
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
	public BumonComboBox(final Object[] items) {
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
	public BumonComboBox(Vector items) {
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

    /* (non-Javadoc)
     * @see javax.swing.JComboBox#setSelectedIndex(int)
     */
    public void setSelectedIndex(int anIndex) {
        super.setSelectedIndex(anIndex);
    	if (lblSectName != null) {
    		lblSectName.setText(StringUtils.emptyIfNull((String) getSelectedValue1()));
    	}
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    public void resetValue() {
    	setSelectedIndex(0);
    	if (lblSectName != null) {
    		lblSectName.setText("");
    	}
    }
    
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return the lblSectName
	 */
	public BaseLabel getLblSectName() {
		return lblSectName;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lblSectName the lblSectName to set
	 */
	public void setLblSectName(BaseLabel lblSectName) {
		this.lblSectName = lblSectName;
	}
}