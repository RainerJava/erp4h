/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：MsgDaoImpl.java
*
*     記述				：
*     
*     作成日			：2009/12/07   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.common.msg;

import java.util.Map;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.common.msg.MsgDao;
import com.fas.dao.common.msg.MsgDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.msg.MstMsgVo;

/**
 * @author PC13
 *
 */
public class MsgServiceImpl extends BaseService implements MsgService {

	private MsgDao msgDao  = null;
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MsgServiceImpl() {
		msgDao = new MsgDaoImpl();
	}
	
	/* (non-Javadoc)
	 * @see com.hanbai.service.msg.MsgService#getMapMsg()
	 */
	public Map<String, MstMsgVo> getMapMsg() throws BizException {
		try {
			return msgDao.getMapMsg();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}

}
