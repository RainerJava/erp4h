/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BizException.java
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

package com.fas.common.exception;

import com.fas.common.exception.DaoException;

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

public class BizException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public BizException() {
	}

	public BizException(DaoException de) {
		super(de.getMessage(), de.getCause());
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param message
	 */
	public BizException(String message) {
		super(message);
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param cause
	 */
	public BizException(Throwable cause) {
		super(cause);
	}

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param message
	 * @param cause
	 */
	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

}

