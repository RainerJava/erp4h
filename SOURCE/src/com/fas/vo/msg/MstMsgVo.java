/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MstMsgVo.java
*
*     記述				：
*     
*     作成日			：2009/10/07   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.vo.msg;

import com.fas.vo.base.BaseVo;

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

public class MstMsgVo extends BaseVo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** メッセージコード */
	private String msgCd = "";

	/** メッセージ1 */
	private String msg1 = "";

	/** メッセージ2 */
	private String msg2 = "";

	/** メッセージ3 */
	private String msg3 = "";

	/** メッセージ4 */
	private String msg4 = "";

	/** BUTTON TYPE */
	private String buttonType = "";
	
	/** */
	private String extraInfor = "";

	/**
	 * @return the msgCd
	 */
	public String getMsgCd() {
		return msgCd;
	}

	/**
	 * @param msgCd the msgCd to set
	 */
	public void setMsgCd(String msgCd) {
		this.msgCd = msgCd;
	}

	/**
	 * @return the msg1
	 */
	public String getMsg1() {
		return msg1;
	}

	/**
	 * @param msg1 the msg1 to set
	 */
	public void setMsg1(String msg1) {
		this.msg1 = msg1;
	}

	/**
	 * @return the msg2
	 */
	public String getMsg2() {
		return msg2;
	}

	/**
	 * @param msg2 the msg2 to set
	 */
	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}

	/**
	 * @return the msg3
	 */
	public String getMsg3() {
		return msg3;
	}

	/**
	 * @param msg3 the msg3 to set
	 */
	public void setMsg3(String msg3) {
		this.msg3 = msg3;
	}

	/**
	 * @return the msg4
	 */
	public String getMsg4() {
		return msg4;
	}

	/**
	 * @param msg4 the msg4 to set
	 */
	public void setMsg4(String msg4) {
		this.msg4 = msg4;
	}

	/**
	 * @return the buttonType
	 */
	public String getButtonType() {
		return buttonType;
	}

	/**
	 * @param buttonType the buttonType to set
	 */
	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}

	/**
	 * @return the extraInfor
	 */
	public String getExtraInfor() {
		return extraInfor;
	}

	/**
	 * @param extraInfor the extraInfor to set
	 */
	public MstMsgVo setExtraInfor(String extraInfor) {
		this.extraInfor = extraInfor;
		return this;
	}
}

