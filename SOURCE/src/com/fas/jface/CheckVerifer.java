/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：CheckVerifer.java
*
*     記述				：
*     
*     作成日			：2009/10/13   
*
*     作成者			：PC12
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface;

import javax.swing.InputVerifier;
import javax.swing.JComponent;

import com.fas.jface.utils.ValidationUtils;
import com.fas.jface.validate.Validation;

/**
 * @author PC12
 *
 */
public class CheckVerifer extends InputVerifier {

    private Validation component;
    public CheckVerifer(Validation component){
        this.component=component;
    }
    private boolean isChecked=false;
    public boolean verify(JComponent input) {
          if(isChecked){
                return false;
          }
          isChecked=true;
          
          boolean check=ValidationUtils.check(component);
          isChecked=false;
          return check;
    }

}
