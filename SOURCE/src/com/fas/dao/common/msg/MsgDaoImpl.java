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
package com.fas.dao.common.msg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fas.common.DBConnection;
import com.fas.common.DBConnectionManager;
import com.fas.common.constants.XMLContants;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.StringUtils;
import com.fas.dao.base.BaseDao;
import com.fas.vo.msg.MstMsgVo;

/**
 * @author PC13
 *
 */
public class MsgDaoImpl extends BaseDao implements MsgDao {

	/** */
	static Logger logger = Logger.getLogger(MsgDaoImpl.class);
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public MsgDaoImpl() {
	}
	
	public Map<String, MstMsgVo> getMapMsg() throws DaoException {
		
		logger.info("MsgDaoImpl.getMapMsg 開始。");
		
		String strSQL = getSQL(XMLContants.FILE_M_MSG, XMLContants.M_MSG500);
		DBConnection con = null;
		Map<String, MstMsgVo> mapMsg = new HashMap<String, MstMsgVo>();
		MstMsgVo dataVo = null;

		try {

			con = DBConnectionManager.getConnection();
			logger.info(strSQL);
			PreparedStatement preStmt = con.prepareStatement(strSQL);
			ResultSet rs = null;
			rs = preStmt.executeQuery();

			while (rs.next()) {
				dataVo = new MstMsgVo();
				dataVo.setMsgCd(StringUtils.nulToString(rs.getString("MSG_CD")));
				dataVo.setMsg1(StringUtils.nulToString(rs.getString("MSG_TEXT1")));
				dataVo.setMsg2(StringUtils.nulToString(rs.getString("MSG_TEXT2")));
				dataVo.setMsg3(StringUtils.nulToString(rs.getString("MSG_TEXT3")));
				dataVo.setMsg4(StringUtils.nulToString(rs.getString("MSG_TEXT4")));
				dataVo.setButtonType(StringUtils.nulToString(rs.getString("MSG_BTN")));
				mapMsg.put(StringUtils.nulToString(rs.getString("MSG_CD")), dataVo);
			} 
		} catch (SQLException e) {
			logger.error(e.getStackTrace());
			throw new DaoException(e);
		} finally {
			if (con != null) {
				try {
					DBConnectionManager.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		logger.info("MsgDaoImpl.getMapMsg 終了。");
		
		return mapMsg;			
	}

}
