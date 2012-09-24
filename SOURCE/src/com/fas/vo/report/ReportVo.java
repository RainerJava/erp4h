package com.fas.vo.report;

import net.sf.jasperreports.engine.JRDataSource;

import com.fas.vo.base.BaseVo;

public abstract class ReportVo extends BaseVo implements JRDataSource {
	/** */
	private int iSum = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8077167683750949657L;
	/** */	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @return
	 */
	public abstract boolean isEmpty();
	/**
	 * @return the iSum
	 */
	public int getISum() {
		return iSum;
	}
	/**
	 * @param sum the iSum to set
	 */
	public void setISum(int sum) {
		iSum = sum;
	}

}
