/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：DBConnectionManager.java
*
*     記述				：
*     
*     作成日			：2009/04/28   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.common;

import java.sql.SQLException;

import com.fas.common.constants.ApplicationConstants;

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

public class DBConnectionManager {
	
	static private DBConnectionManager _instance = null;

    static private DBConnectionPool pool;

    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public DBConnectionManager() throws ClassNotFoundException, 
                               InstantiationException, IllegalAccessException,
                                SQLException
    {
	    Class.forName(ApplicationConstants.DRIVER).newInstance();	  
        pool = new DBConnectionPool();
    }

    static public DBConnectionManager instance() throws Exception {
        if(null == _instance) {
           _instance = new DBConnectionManager();
        }
        return _instance;
    }
    
    public static DBConnection getConnection() throws SQLException {
        return pool.getConnection();
    }
    
    public static void releaseConnection(DBConnection conn) throws SQLException {
    	conn.close();
    }	  
}

