/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseVo.java
*
*     記述				：
*     
*     作成日			：2009/04/28   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.vo.base;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public class ReportBaseVo extends Object implements JRDataSource {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3535293706344932363L;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public ReportBaseVo() {
	}

	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean next() throws JRException {
		// TODO Auto-generated method stub
		return false;
	}

}

