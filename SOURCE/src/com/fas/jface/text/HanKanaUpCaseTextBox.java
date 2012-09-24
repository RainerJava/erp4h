/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名	：
 * 
 *     ファイル名		：InsAccountFrame.java
 *
 *     記述				：
 *     
 *     作成日			：2009/10/16   
 *
 *     作成者			：PC12
 *
 *     備考				：契約者マスタ（ユーザーマスタ）
 *
 **************************************************************************************/

package com.fas.jface.text;

import java.awt.event.FocusEvent;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.utils.JapaneseString;

/**
 * @author PC12
 * pad 0 to the left of textbox when lost focus
 *
 */
public class HanKanaUpCaseTextBox extends BaseInputText {
	private static final long serialVersionUID = 1L;

	public HanKanaUpCaseTextBox() {
		super();
		this.setIMType(ImeText.IM_HALFKANA);
	}
	
	public HanKanaUpCaseTextBox(int maxLength) {
		super();
		this.setMaxLength(maxLength);
		this.setIMType(ImeText.IM_HALFKANA);
	}

    protected  boolean checkMode(String charString){
        char[] cr=charString.toCharArray();
        char c=cr[0];
        return (JapaneseString.isHalfwidthKatakana(c) || c == ' ' || c == '.');
    }
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	public void focusLost(FocusEvent e) {
		super.focusLost(e);
		if (this.getText().length() > 0)
			this.setText(JapaneseString.toUpcaseKatakana(this.getText()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	public void focusGained(FocusEvent e) {
		this.setBackground(ColorConstants.DEFAULT_TEXT_FOCUS_BACKGROUND_COLOR);
	}
}