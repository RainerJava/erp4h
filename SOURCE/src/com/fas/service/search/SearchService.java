/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：SearchService.java
*
*     記述				：
*     
*     作成日			：2010/02/12   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.search;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.search.MSchDspVo;
import com.fas.vo.search.SearchConditionVo;
import com.fas.vo.search.SearchVo;
import com.fas.vo.search.TrSchdspVo;

/**
 * @author PC13
 *
 */
public interface SearchService {
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param key
	 * @return
	 * @throws BizException
	 */
	public MSchDspVo getMSchDspVo(String key) throws BizException;
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param dspVo
	 * @param condition
	 * @param sortObj
	 * @return
	 * @throws BizException
	 */
	public List<SearchVo> getLstSearchVo(MSchDspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws BizException;

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param dspVo
	 * @param condition
	 * @param sortObj
	 * @return
	 * @throws BizException
	 */
	public List<SearchVo> getLstSearchVo(TrSchdspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws BizException ;
}
