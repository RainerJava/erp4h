/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：PermissionServiceImpl.java
*
*     記述				：
*     
*     作成日			：2010/02/09   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/
package com.fas.service.system.permission;

import java.util.Map;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.system.permission.PermissionDao;
import com.fas.dao.system.permission.PermissionDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.exectl1.Exectl1Vo;
import com.fas.vo.exectl2.Exectl2Vo;

/**
 * @author PC13
 *
 */
public class PermissionServiceImpl extends BaseService implements PermissionService {
	
	/** */
	private PermissionDao permissionDao;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public PermissionServiceImpl() {
		permissionDao = new PermissionDaoImpl();
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.permission.PermissionService#getMapExect1Vo()
	 */
	public Map<String, Exectl1Vo> getMapExect1Vo() throws BizException {
		try {
			return permissionDao.getMapExect1Vo();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.permission.PermissionService#getMapExect2Vo()
	 */
	public Map<String, Exectl2Vo> getMapExect2Vo() throws BizException {
		try {
			return permissionDao.getMapExect2Vo();
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
}
