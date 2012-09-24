/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseDao.java
*
*     記述				：
*     
*     作成日			：2009/05/14   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.dao.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.fas.common.constants.DataTypeConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.common.utils.StringUtils;
import com.fas.common.utils.XMLUtils;
import com.fas.vo.base.EntityVo;

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

public class BaseDao {

	private int paramCount = 0;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */	
	public BaseDao() {
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param lstEntity
	 * @param preStmt
	 * @throws SQLException
	 */
	protected void setParameter(List<EntityVo> lstEntity, PreparedStatement preStmt) throws SQLException {
		
		EntityVo eVo = null;
		
		if (lstEntity != null) {
			for (int i = 1; i <= lstEntity.size(); i++) {
				eVo = (EntityVo) lstEntity.get(i - 1);
				
				switch (eVo.getValueType()) {
					case DataTypeConstants.BOOLEAN_TYPE:
						preStmt.setBoolean(i, eVo.isBoolValue());
						break;
					case DataTypeConstants.BYTE_TYPE:
						preStmt.setByte(i, eVo.getByteValue());
						break;
					case DataTypeConstants.DOUBLE_TYPE:
						preStmt.setDouble(i, eVo.getDoubleValue());
						break;
					case DataTypeConstants.FLOAT_TYPE:
						preStmt.setFloat(i, eVo.getFloatValue());
						break;
					case DataTypeConstants.INT_TYPE:
						preStmt.setInt(i, eVo.getIntValue());
						break;
					case DataTypeConstants.LONG_TYPE:
						preStmt.setLong(i, eVo.getLongValue());
						break;
					case DataTypeConstants.OBJECT_TYPE:
						if (eVo.getObjectValue() != null) {
							preStmt.setObject(i, eVo.getObjectValue());
						} else {
							preStmt.setNull(i, Types.JAVA_OBJECT);
						}
						break;
					case DataTypeConstants.STRING_TYPE:
						if (eVo.getStringValue() != null) {
							preStmt.setObject(i, eVo.getStringValue());
						} else {
							preStmt.setNull(i, Types.VARCHAR);
						}
						break;
					default:
						if (eVo.getObjectValue() != null) {
							preStmt.setObject(i, eVo.getObjectValue());
						} else {
							preStmt.setNull(i, Types.JAVA_OBJECT);
						}							
						break;							
				}
			}
		}		
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param preStmt
	 */
	protected void resetParameter(PreparedStatement preStmt){
		paramCount = 0;
		
	}
	
	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param value
	 * @param type
	 * @param preStmt
	 * @throws SQLException
	 */
	protected void setParameter(Object value, int type, PreparedStatement preStmt) throws SQLException {
		if (paramCount ==0) preStmt.clearParameters();
		paramCount ++;
		switch (type) {
		case Types.VARCHAR:
			String v =  (String)value;
			if (v.length() == 0) {
				v = " ";
			}
			preStmt.setString(paramCount, v);
			break;
		case Types.BOOLEAN:
			preStmt.setBoolean(paramCount, (Boolean)value);
			break;
		case Types.INTEGER:
			if (value == null) {
				preStmt.setInt(paramCount, 0);
			}else {
				preStmt.setInt(paramCount, ((Integer)value).intValue());
			}
			
			break;
		case Types.FLOAT:
			if (value == null) {
				preStmt.setFloat(paramCount, 0);
			}else{
				preStmt.setFloat(paramCount, (Float)value);
			}
			break;
		case Types.DOUBLE:
			if (value == null) {
				preStmt.setDouble(paramCount, 0);
			}else{
				preStmt.setDouble(paramCount, (Double)value);
			}
			break;
		}
	}
	
	/**
	 * @param xmlFilePath
	 * @param xmlTag
	 * @return
	 * @throws DaoException
	 */
	protected String getSQL(String xmlFilePath, String xmlTag) throws DaoException {
		
		String strSQL = "";
		
		try {
			strSQL = XMLUtils.getSQLFromXML(xmlFilePath, xmlTag);
			if (!StringUtils.isValid(strSQL)) {
				throw new DaoException(MessageConstants.SYS_ERR_MSG_XML_SQL_READ);
			}
		} catch (BizException e) {
			throw new DaoException(MessageConstants.SYS_ERR_MSG_XML_SQL_READ);
		}
		
		return strSQL;
	}
}

