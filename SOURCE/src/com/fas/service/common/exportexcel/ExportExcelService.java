/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名		：エスイーシー化成
* 
*     ファイル名		：ExportExcel.java
*
*     記述			：
*     
*     作成日			：Apr 8, 2010   
*
*     作成者			：Bui Ngoc Viet
*
*     備考			：
*
**************************************************************************************/

package com.fas.service.common.exportexcel;

import java.sql.ResultSet;

import com.fas.common.exception.BizException;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: Bui Ngoc Viet</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public interface ExportExcelService {
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* @throws BizException
	* </DL>
	*/
//	public String export(String strSQL)throws BizException;
	public ResultSet export(String strSQL)throws BizException;
	
	
}
