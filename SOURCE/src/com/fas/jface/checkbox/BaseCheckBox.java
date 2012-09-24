/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：HanbaiCheckBox.java
 *
 *     記述				：
 *     
 *     作成日			：2009/12/21   
 *
 *     作成者			：PC13
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.jface.checkbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;
import com.fas.common.utils.StringUtils;
import com.fas.jface.button.ActionButton;

/**
 * @author PC13
 * 
 */
public class BaseCheckBox extends JCheckBox {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private String chkLabel = "";
	/** */
	private String unChkLabel = "";
	/** */
	private boolean isAllowChangeColor = true;
	private ActionButton btnSelect = null;

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	public BaseCheckBox() {
		super();
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param icon
	 */
	public BaseCheckBox(Icon icon) {
		super(icon);
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param icon
	 * @param selected
	 */
	public BaseCheckBox(Icon icon, boolean selected) {
		super(icon, selected);
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param text
	 */
	public BaseCheckBox(String text) {
		super(text);
		init();
		chkLabel = text;
		unChkLabel = text;
		
	}

	/**
	 * <DL>
	 * <DT>クラス記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * <DT>変更歴史：</DT>
	 * <DD>著作者: PC14</DD><BR>
	 * <DD></DD>
	 * </DL>
	 */
	public void setBtnSelect(ActionButton btnBase) {
		btnSelect = btnBase;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 */
	private void init() {

		setFont(FontConstants.LABEL_TEXT_FONT);
		setBackground(ColorConstants.DEFAULT_CHECKBOX_BACKGROUND_COLOR);
		setOpaque(true);

		// Make the ENTER key act like the TAB key
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				int key = evt.getKeyCode();
			
				if(key == KeyEvent.VK_NUMPAD0 || key == KeyEvent.VK_NUMPAD1 ){				
					if(isSelected()){
						setSelected(false);
						boolean selected = false;
						String newLabel = (selected ? chkLabel : unChkLabel);
						setText(newLabel);
						
						
					}else{
						setSelected(true);
						boolean selected = true;
						String newLabel = (selected ? chkLabel : unChkLabel);
						setText(newLabel);
						
					}
				} 
				
				if (key == KeyEvent.VK_ENTER)
					transferFocus();
			}
		});

		// Set Focus color
		this.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (isAllowChangeColor()) {
					setBackground(ColorConstants.DEFAULT_CHECKBOX_FOCUS_BACKGROUND_COLOR);
				}
				if (btnSelect != null) {
					btnSelect.setEnabled(true);
					btnSelect.setClick(true);
				}
			}

			public void focusLost(FocusEvent e) {
				if (isAllowChangeColor()) {
					setBackground(ColorConstants.DEFAULT_CHECKBOX_BACKGROUND_COLOR);
				}
				if (btnSelect != null) {
					SwingUtilities.invokeLater(new Runnable() {

						public void run() {
							btnSelect.setEnabled(false);
						}
					});
				}
			}
		});

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (StringUtils.isValid(chkLabel)
						&& StringUtils.isValid(unChkLabel)) {
					AbstractButton abstractButton = (AbstractButton) actionEvent
							.getSource();
					boolean selected = abstractButton.getModel().isSelected();
					String newLabel = (selected ? chkLabel : unChkLabel);
					abstractButton.setText(newLabel);
				}
			}
		};

		addActionListener(actionListener);
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return the chkLabel
	 */
	public String getChkLabel() {
		return chkLabel;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述： if check box has format as Code:Name</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return the getCode
	 */
	public String getCode() {
		String lable = super.getLabel();
		String code = "";

		if (StringUtils.isValid(lable) && StringUtils.containsMatch(lable, ":")) {
			code = lable.substring(0, lable.indexOf(":"));
		}

		return code;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param chkLabel
	 *            the chkLabel to set
	 */
	public void setChkLabel(String chkLabel) {
		this.chkLabel = chkLabel;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @return the unChkLabel
	 */
	public String getUnChkLabel() {
		return unChkLabel;
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * 
	 * </DL>
	 * 
	 * @param unChkLabel
	 *            the unChkLabel to set
	 */
	public void setUnChkLabel(String unChkLabel) {
		this.unChkLabel = unChkLabel;
	}

	public void setAllowChangeColor(boolean isAllowChangeColor) {
		this.isAllowChangeColor = isAllowChangeColor;
	}

	public boolean isAllowChangeColor() {
		return isAllowChangeColor;
	}
}
