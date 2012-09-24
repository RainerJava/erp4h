/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：PermissionPolicy.java
*
*     記述				：
*     
*     作成日			：2010/02/08   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.common;

import java.util.HashMap;
import java.util.Map;

import com.fas.common.utils.NumberUtils;
import com.fas.vo.exectl1.Exectl1Vo;
import com.fas.vo.exectl2.Exectl2Vo;
import com.fas.vo.menuexe.MenuExeVo;
import com.fas.vo.menugrp.MenuGrpVo;

/**
 * @author PC13
 *
 */
public class PermissionPolicy {

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public PermissionPolicy() {
	}
	
	/** */
	public static String MNG_ALL_PERMISSION = "0";
	/** */
	public static String MNG_VIEW_PERMISSION = "5";
	/** */
	public static String MNG_VIEW_NO_PERMISSION = "9";
	
	/** */
	public static String MNEXE_ALL_PERMISSION = "0";
	/** */
	public static String MNEXE_VIEW_DATA_PERMISSION = "2";
	/** */
	public static String MNEXE_VIEW_MN_PERMISSION = "5";
	/** */
	public static String MNEXE_VIEW_NO_PERMISSION = "9";

	/** */
	public static Map<String, Exectl1Vo> MAP_EXECTL1VO = new HashMap<String, Exectl1Vo>();

	/** */
	public static Map<String, Exectl2Vo> MAP_EXECTL2VO = new HashMap<String, Exectl2Vo>();
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userId
	 * @param exeVo
	 * @return
	 */
	public static boolean checkMnGrpPermission(String userId, MenuGrpVo grpVo) {
		
		boolean isPer = false;
		String strKey = userId + grpVo.getMenugrpCode();
		Exectl1Vo exec1Vo = MAP_EXECTL1VO.get(strKey);
		
		if (exec1Vo != null) {
			isPer = true;
		}
		
		return isPer;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param accessLevel
	 * @param userId
	 * @param grpVo
	 * @return
	 */
	public static boolean checkMnGrpPermission(String accessLevel, String userId, MenuGrpVo grpVo) {
		
		boolean isPer = false;
		String strKey = userId + grpVo.getMenugrpCode();
		Exectl1Vo exec1Vo = MAP_EXECTL1VO.get(strKey);
		int iAccess = NumberUtils.getIntFromString(accessLevel);
		
		
		if (exec1Vo != null) {
			int iKengen = NumberUtils.getIntFromString(exec1Vo.getControlType().trim());
			if (iAccess >= iKengen) {
				isPer = true;
			}
		}

		return isPer;
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param accessLevel
	 * @param userId
	 * @param exeVo
	 * @return
	 */
	public static boolean checkMnExePermission(String accessLevel, String userId, MenuExeVo exeVo) {

		boolean isPer = false;
		String strKey = userId + exeVo.getMenugrpCode() + exeVo.getMenuexeCode();
		Exectl2Vo exec2Vo = MAP_EXECTL2VO.get(strKey);
		int iAccess = NumberUtils.getIntFromString(accessLevel);
		
		if (exec2Vo != null) {
			int iKengen = NumberUtils.getIntFromString(exec2Vo.getControlType().trim());
			if (iAccess >= iKengen) {
				isPer = true;
			}
		}
		
		return isPer;
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param userId
	 * @param exeVo
	 * @return
	 */
	public static boolean checkMnExePermission(String userId, MenuExeVo exeVo) {

		boolean isPer = false;
		String strKey = userId + exeVo.getMenugrpCode() + exeVo.getMenuexeCode();
		Exectl2Vo exec2Vo = MAP_EXECTL2VO.get(strKey);
		
		if (exec2Vo != null) {
			isPer = true;
		}
		
		return isPer;
	}	
}
