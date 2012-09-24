/**
 * 
 */
package com.fas.vo.flog;

import com.fas.vo.base.BaseVo;

/**
 * @author Bui Ngoc Viet
 *
 */
public class FLogConditionVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4087030875006663025L;
	private boolean isLogForUser;
	private String name;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the dateFrom
	 */
	public int getDateFrom() {
		return dateFrom;
	}
	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(int dateFrom) {
		this.dateFrom = dateFrom;
	}
	/**
	 * @return the dateTo
	 */
	public int getDateTo() {
		return dateTo;
	}
	/**
	 * @param dateTo the dateTo to set
	 */
	public void setDateTo(int dateTo) {
		this.dateTo = dateTo;
	}
	/**
	 * @return the timeFrom
	 */
	public int getTimeFrom() {
		return timeFrom;
	}
	/**
	 * @param timeFrom the timeFrom to set
	 */
	public void setTimeFrom(int timeFrom) {
		this.timeFrom = timeFrom;
	}
	/**
	 * @return the timeTo
	 */
	public int getTimeTo() {
		return TimeTo;
	}
	/**
	 * @param timeTo the timeTo to set
	 */
	public void setTimeTo(int timeTo) {
		TimeTo = timeTo;
	}

	/**
	 * @return the pCName
	 */
	public String getPCName() {
		return PCName;
	}
	/**
	 * @param pCName the pCName to set
	 */
	public void setPCName(String pCName) {
		PCName = pCName;
	}
	/**
	 * @return the aD
	 */
	public boolean isAD() {
		return AD;
	}
	/**
	 * @param aD the aD to set
	 */
	public void setAD(boolean aD) {
		AD = aD;
	}
	/**
	 * @return the cL
	 */
	public boolean isCL() {
		return CL;
	}
	/**
	 * @param cL the cL to set
	 */
	public void setCL(boolean cL) {
		CL = cL;
	}
	/**
	 * @return the dE
	 */
	public boolean isDE() {
		return DE;
	}
	/**
	 * @param dE the dE to set
	 */
	public void setDE(boolean dE) {
		DE = dE;
	}
	/**
	 * @return the iN
	 */
	public boolean isIN() {
		return IN;
	}
	/**
	 * @param iN the iN to set
	 */
	public void setIN(boolean iN) {
		IN = iN;
	}
	/**
	 * @return the lI
	 */
	public boolean isLI() {
		return LI;
	}
	/**
	 * @param lI the lI to set
	 */
	public void setLI(boolean lI) {
		LI = lI;
	}
	/**
	 * @return the lO
	 */
	public boolean isLO() {
		return LO;
	}
	/**
	 * @param lO the lO to set
	 */
	public void setLO(boolean lO) {
		LO = lO;
	}
	/**
	 * @return the oP
	 */
	public boolean isOP() {
		return OP;
	}
	/**
	 * @param oP the oP to set
	 */
	public void setOP(boolean oP) {
		OP = oP;
	}
	/**
	 * @return the uP
	 */
	public boolean isUP() {
		return UP;
	}
	/**
	 * @param uP the uP to set
	 */
	public void setUP(boolean uP) {
		UP = uP;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @param isLogForUser the isLogForUser to set
	 */
	public void setLogForUser(boolean isLogForUser) {
		this.isLogForUser = isLogForUser;
	}
	/**
	 * @return the isLogForUser
	 */
	public boolean isLogForUser() {
		return isLogForUser;
	}
	/**
	 * @param menuExeGrp the menuExeGrp to set
	 */
	public void setMenuExeGrp(String menuExeGrp) {
		this.menuExeGrp = menuExeGrp;
	}
	/**
	 * @return the menuExeGrp
	 */
	public String getMenuExeGrp() {
		return menuExeGrp;
	}
	/**
	 * @param menuExeCode the menuExeCode to set
	 */
	public void setMenuExeCode(String menuExeCode) {
		this.menuExeCode = menuExeCode;
	}
	public boolean isCheckAll()
	{
		return isAD() && isCL() && isDE() && isIN() && isLI()&&isLO()&&isUP()&&isOP();
	}
	public boolean isDontCheck()
	{
		return !(isAD() ||isCL() || isDE() || isIN() || isLI()||isLO()||isUP()||isOP());
	}
	/**
	 * @return the menuExeCode
	 */
	public String getMenuExeCode() {
		return menuExeCode;
	}
	private int dateFrom;
	private int dateTo;
	private int timeFrom;
	private int TimeTo;
	private String menuExeGrp ="";
	private String menuExeCode ="";
	private String PCName="";
	private boolean AD;
	private boolean CL;
	private boolean DE;
	private boolean IN;
	
	private boolean LI;
	private boolean LO;
	private boolean OP;
	private boolean UP;
}
