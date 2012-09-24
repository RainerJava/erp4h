package com.fas.vo.outctl;

import com.fas.vo.base.BaseVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: longtv</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public class OutCtlVo extends BaseVo {
	/** */
	private static final long serialVersionUID = 1L;
	/** */
	private String userId = "";
	/** */
	private String out1Path = "";
	/** */
	private String out2Path = "";
	/** */
	private String out3Path = "";
	/** */
	private String out4Path = "";
	/** */
	private String out5Path = "";
	/** 登録ユーザー名 */
	private String addUser = "";

	/** 登録ＰＣ名 */
	private String addPc = "";

	/** 登録日付 */
	private int addDate = 0;

	/** 登録時刻 */
	private int addTime = 0;

	/** 最終更新ユーザー名 */
	private String lastupUser = "";

	/** 最終更新ＰＣ名 */
	private String lastupPc = "";

	/** 最終更新日付 */
	private int lastupDate = 0;

	/** 最終更新時刻 */
	private int lastupTime = 0;
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public String getUserId(){
		return this.userId;
	}
	
	public void setOut1Path(String out1Path){
		this.out1Path = out1Path;
	}
	
	public String getOut1Path(){
		return this.out1Path;
	}
	
	public void setOut2Path(String out2Path){
		this.out2Path = out2Path;
	}
	
	public String getOut2Path(){
		return this.out2Path;
	}
	
	public void setOut3Path(String out3Path){
		this.out3Path = out3Path;
	}
	
	public String getOut3Path(){
		return this.out3Path;
	}
	
	public void setOut4Path(String out4Path){
		this.out4Path = out4Path;
	}
	
	public String getOut4Path(){
		return this.out4Path;
	}
	
	public void setOut5Path(String out5Path){
		this.out5Path = out5Path;
	}
	
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	* @return
	* </DL>
	*/
	public String getOut5Path(){
		return this.out5Path;
	}
	
	/**
	 * @return the addUser
	 */
	public String getAddUser() {
		return addUser;
	}

	/**
	 * @param addUser the addUser to set
	 */
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	/**
	 * @return the addPc
	 */
	public String getAddPc() {
		return addPc;
	}

	/**
	 * @param addPc the addPc to set
	 */
	public void setAddPc(String addPc) {
		this.addPc = addPc;
	}

	/**
	 * @return the addDate
	 */
	public int getAddDate() {
		return addDate;
	}

	/**
	 * @param addDate the addDate to set
	 */
	public void setAddDate(int addDate) {
		this.addDate = addDate;
	}

	/**
	 * @return the addTime
	 */
	public int getAddTime() {
		return addTime;
	}

	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}

	/**
	 * @return the lastupUser
	 */
	public String getLastupUser() {
		return lastupUser;
	}

	/**
	 * @param lastupUser the lastupUser to set
	 */
	public void setLastupUser(String lastupUser) {
		this.lastupUser = lastupUser;
	}

	/**
	 * @return the lastupPc
	 */
	public String getLastupPc() {
		return lastupPc;
	}

	/**
	 * @param lastupPc the lastupPc to set
	 */
	public void setLastupPc(String lastupPc) {
		this.lastupPc = lastupPc;
	}

	/**
	 * @return the lastupDate
	 */
	public int getLastupDate() {
		return lastupDate;
	}

	/**
	 * @param lastupDate the lastupDate to set
	 */
	public void setLastupDate(int lastupDate) {
		this.lastupDate = lastupDate;
	}

	/**
	 * @return the lastupTime
	 */
	public int getLastupTime() {
		return lastupTime;
	}

	/**
	 * @param lastupTime the lastupTime to set
	 */
	public void setLastupTime(int lastupTime) {
		this.lastupTime = lastupTime;
	}
}
