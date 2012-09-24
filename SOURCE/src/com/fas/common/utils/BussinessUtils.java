/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：HayashiUtils.java
*
*     記述				：
*     
*     作成日			：2009/12/10   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.common.utils;

import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.exception.BizException;
import com.fas.jface.MessageBox;
import com.fas.service.system.memp.MEmpService;
import com.fas.service.system.memp.MEmpServiceImpl;
import com.fas.vo.memp.MEmpVo;


/**
 * @author PC13
 *
 */
public class BussinessUtils {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public BussinessUtils() {
	}

//	/**
//	 * <DL>
//	 *   <DT>メソッド記述：</DT>
//	 * 		<DD></DD>
//	 * <BR>
//	 *
//	 * </DL>
//	 * @param sectCode
//	 * @return
//	 */
//	public static String getSectName(String sectCode) {
//		
//		try {
//			
//			SectService sectService = new SectServiceImpl();
//			
//			SectVo sectVo = sectService.getSectVo(sectCode);
//			
//			if (sectVo != null) {
//				return sectVo.getSectName();
//			} 
//		} catch (BizException e) {
//			e.printStackTrace();
//			MessageBox.error(MessageConstants.SYS_ERR_MSG);
//		}
//		
//		return "";
//	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param empCode
	 * @return
	 */
	public static String getTantoushaName(String empCode) {
		
		try {
			
			MEmpService service = new MEmpServiceImpl();
			MEmpVo empVo = service.getByKey(empCode);
			
			if (empVo != null) {
				return empVo.getEmpName();
			} 
		} catch (BizException e) {
			MessageBox.error(MessageConstants.SYS_ERR_MSG);
		}
		
		return "";
	}
	
//	/**
//	 * <DL>
//	 *   <DT>メソッド記述：</DT>
//	 * 		<DD></DD>
//	 * <BR>
//	 *
//	 * </DL>
//	 * @param custRCode
//	 * @return
//	 */
//	public static String getCustRName(String custRCode) {
//		
//		try {
//			
//			MCustRService service = new MCustRServiceImpl();
//			MCustRVo dataVo = service.getMCustRVo(custRCode);
//			
//			if (dataVo != null) {
//				return dataVo.getCustName();
//			} 
//		} catch (BizException e) {
//			MessageBox.error(MessageConstants.SYS_ERR_MSG);
//		}
//		
//		return "";
//	}
	
//	/**
//	 * <DL>
//	 *   <DT>メソッド記述：</DT>
//	 * 		<DD></DD>
//	 * <BR>
//	 *
//	 * </DL>
//	 * @param lclsCode
//	 * @return
//	 */
//	public static String getLclsName(String lclsCode) {
//		
//		try {
//			
//			MLclsService service = new MLclsServiceImpl();
//			MLclsVo dataVo = service.getLclsVoByKey(lclsCode);
//			
//			if (dataVo != null) {
//				return dataVo.getLclsName();
//			} 
//		} catch (BizException e) {
//			MessageBox.error(MessageConstants.SYS_ERR_MSG);
//		}
//		
//		return "";
//	}
//	
//	/**
//	 * <DL>
//	 *   <DT>メソッド記述：</DT>
//	 * 		<DD></DD>
//	 * <BR>
//	 *
//	 * </DL>
//	 * @param prodCode
//	 * @return
//	 */
//	public static String getLMSName(String lmsCode) {
//		
//		try {
//			
//			LMSService lmsService = new LMSServiceImpl();
//			
//			LMSVo lmsVo = lmsService.getLMSVo(lmsCode);
//			
//			if (lmsCode != null) {
//				return lmsVo.getName();
//			} 
//		} catch (BizException e) {
//			e.printStackTrace();
//			MessageBox.error(MessageConstants.SYS_ERR_MSG);
//		}
//		
//		return "";
//	}
//	
//	/**
//	 * <DL>
//	 *   <DT>メソッド記述：</DT>
//	 * 		<DD></DD>
//	 * <BR>
//	 *
//	 * </DL>
//	 * @param prodCode
//	 * @return
//	 */
//	public static String getprodName(String prodCode) {
//		
//		try {
//			
//			ProdService prodService = new ProdServiceImpl();
//			
//			ProdVo prodVo = prodService.getProdVo(prodCode);
//			
//			if (prodVo != null) {
//				return (prodVo.getProdcName() +"("+ prodVo.getProdName() + ")");
//			} 
//		} catch (BizException e) {
//			e.printStackTrace();
//			MessageBox.error(MessageConstants.SYS_ERR_MSG);
//		}
//		
//		return "";
//	}
//	
//	/**
//	 * <DL>
//	 *   <DT>メソッド記述：</DT>
//	 * 		<DD></DD>
//	 * <BR>
//	 *
//	 * </DL>
//	 * @param custCode
//	 * @return
//	 */
//	public static String getCustName(String custCode) {
//		
//		try {
//			
//			MSupplyService supplyService = new MSupplyServiceImpl();
//			
//			MSupplyVo supplyVo = supplyService.getMSupplyVo(custCode);
//			
//			if (supplyVo != null) {
//				return (supplyVo.getCustName());
//			} 
//		} catch (BizException e) {
//			e.printStackTrace();
//			MessageBox.error(MessageConstants.SYS_ERR_MSG);
//		}
//		
//		return "";
//	}
}
