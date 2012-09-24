package com.fas.dao.common.exportexcel;

import java.sql.ResultSet;

import com.fas.common.exception.DaoException;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: Bui Ngoc Viet</DD><BR>
 * 	<DD></DD>
 * </DL>
 */
public interface ExportExcelDao {
//	public String export(String strSQL) throws DaoException;
	public ResultSet export(String strSQL) throws DaoException;
}
