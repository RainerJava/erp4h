/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：DBConnection.java
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

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Properties;

import com.fas.common.constants.ApplicationConstants;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: </DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public class DBConnection implements Connection  {
	
	//static final Log log = LogFactory.getLog(DBConnectionPool.class);
	
    private DBConnectionPool pool;
    private Connection conn;
    private boolean isuse;
    private long timestamp;


    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param conn
     * @param pool
     */
    public DBConnection(Connection conn, DBConnectionPool pool) {
        this.conn=conn;
        this.pool=pool;
        this.isuse=false;
        this.timestamp=0;
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public synchronized boolean lease() {
       if(isuse)  {
           return false;
       } else {
    	  isuse=true;
          timestamp=System.currentTimeMillis();
          return true;
       }
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public boolean validate() {
    	
		try {
	           // conn.getMetaData();
	 		   PreparedStatement preStmt = conn.prepareStatement(ApplicationConstants.DB_SQL_CHECK_CONNECTION);
			   ResultSet rs = preStmt.executeQuery();
			   if (rs.next()) {
			   }
			   preStmt = null;
			   rs = null;
	        } catch (Exception e) {
		    return false;
		}
		return true;
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public boolean isUse() {
        return isuse;
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public long getLastUse() {
        return timestamp;
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @throws SQLException
     */
    public void close() throws SQLException {
        pool.returnConnection(this);
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    protected void expireLease() {
    	isuse=false;
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected Connection getConnection() {
        return conn;
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param sql
     * @return
     * @throws SQLException
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }
    
    /* (non-Javadoc)
     * @see java.sql.Connection#prepareStatement(java.lang.String, int, int, int)
     */
    public PreparedStatement prepareStatement(String sql, int resultSetType, 
		       int resultSetConcurrency, int resultSetHoldability)  throws SQLException {
     return conn.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
    } 

    /* (non-Javadoc)
     * @see java.sql.Connection#prepareStatement(java.lang.String, int, int)
     */
    public PreparedStatement prepareStatement(String sql, int resultSetType, 
		       int resultSetConcurrency) throws SQLException {
        return conn.prepareStatement(sql, resultSetType, resultSetConcurrency); 	
    }
    
    /* (non-Javadoc)
     * @see java.sql.Connection#prepareStatement(java.lang.String, int)
     */
    public PreparedStatement prepareStatement(String sql, int resultSetType) throws SQLException {
     return conn.prepareStatement(sql, resultSetType); 	
    } 
    
    /* (non-Javadoc)
     * @see java.sql.Connection#prepareStatement(java.lang.String, int[])
     */
    public PreparedStatement prepareStatement(String sql, int columnIndexes[]) throws SQLException {
        return conn.prepareStatement(sql, columnIndexes); 	
    }
    
    /* (non-Javadoc)
     * @see java.sql.Connection#prepareStatement(java.lang.String, java.lang.String[])
     */
    public PreparedStatement prepareStatement(String sql, String columnNames[]) throws SQLException {
        return conn.prepareStatement(sql, columnNames); 	
    } 
         
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param sql
     * @return
     * @throws SQLException
     */
    public CallableStatement prepareCall(String sql) throws SQLException {
        return conn.prepareCall(sql);
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     * @throws SQLException
     */
    public Statement createStatement() throws SQLException {
        return conn.createStatement();
    }

    /* (non-Javadoc)
     * @see java.sql.Connection#createStatement(int, int)
     */
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return conn.createStatement(resultSetType, resultSetConcurrency);
    }
    
    /* (non-Javadoc)
     * @see java.sql.Connection#createStatement(int, int, int)
     */
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
    }  
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param sql
     * @return
     * @throws SQLException
     */
    public String nativeSQL(String sql) throws SQLException {
        return conn.nativeSQL(sql);
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param autoCommit
     * @throws SQLException
     */
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        conn.setAutoCommit(autoCommit);
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     * @throws SQLException
     */
    public boolean getAutoCommit() throws SQLException {
        return conn.getAutoCommit();
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @throws SQLException
     */
    public void commit() throws SQLException {
        conn.commit();
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        conn.rollback();
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     * @throws SQLException
     */
    public boolean isClosed() throws SQLException {
        return conn.isClosed();
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     * @throws SQLException
     */
    public DatabaseMetaData getMetaData() throws SQLException {
        return conn.getMetaData();
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param readOnly
     * @throws SQLException
     */
    public void setReadOnly(boolean readOnly) throws SQLException {
        conn.setReadOnly(readOnly);
    }
  
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     * @throws SQLException
     */
    public boolean isReadOnly() throws SQLException {
        return conn.isReadOnly();
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param catalog
     * @throws SQLException
     */
    public void setCatalog(String catalog) throws SQLException {
        conn.setCatalog(catalog);
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     * @throws SQLException
     */
    public String getCatalog() throws SQLException {
        return conn.getCatalog();
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param level
     * @throws SQLException
     */
    public void setTransactionIsolation(int level) throws SQLException {
        conn.setTransactionIsolation(level);
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     * @throws SQLException
     */
    public int getTransactionIsolation() throws SQLException {
        return conn.getTransactionIsolation();
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     * @throws SQLException
     */
    public SQLWarning getWarnings() throws SQLException {
        return conn.getWarnings();
    }

    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @throws SQLException
     */
    public void clearWarnings() throws SQLException {
        conn.clearWarnings();
    }

    /* (non-Javadoc)
     * @see java.sql.Connection#getTypeMap()
     */
    public java.util.Map<String,Class<?>> getTypeMap() throws SQLException {
    	return conn.getTypeMap();
    }


    /* (non-Javadoc)
     * @see java.sql.Connection#setTypeMap(java.util.Map)
     */
    public void setTypeMap(java.util.Map<String,Class<?>> map) throws SQLException {
    	conn.setTypeMap(map);
    }
    
    
    /* (non-Javadoc)
     * @see java.sql.Connection#setHoldability(int)
     */
    public void setHoldability(int holdability) throws SQLException {
    	conn.setHoldability(holdability);
    }


    /* (non-Javadoc)
     * @see java.sql.Connection#getHoldability()
     */
    public int getHoldability() throws SQLException {
    	return conn.getHoldability();
    }
    
    /* (non-Javadoc)
     * @see java.sql.Connection#setSavepoint()
     */
    public Savepoint setSavepoint() throws SQLException {
    	return conn.setSavepoint();
    }

    /* (non-Javadoc)
     * @see java.sql.Connection#setSavepoint(java.lang.String)
     */
    public Savepoint setSavepoint(String name) throws SQLException {
    	return conn.setSavepoint(name);
    }
    
    /* (non-Javadoc)
     * @see java.sql.Connection#rollback(java.sql.Savepoint)
     */
    public void rollback(Savepoint savepoint) throws SQLException {
    	conn.rollback(savepoint);
    }

    /* (non-Javadoc)
     * @see java.sql.Connection#releaseSavepoint(java.sql.Savepoint)
     */
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
    	conn.releaseSavepoint(savepoint);
    }


	/* (non-Javadoc)
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int, int)
	 */
	public CallableStatement prepareCall(String sql, int resultSetType, 
			  int resultSetConcurrency, 
			  int resultSetHoldability) throws SQLException {
    	return conn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);		
	}

	/* (non-Javadoc)
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int)
	 */
	public CallableStatement prepareCall(String sql, int resultSetType, 
			  int resultSetConcurrency) throws SQLException {
		return conn.prepareCall(sql, resultSetType, resultSetConcurrency);		
	}

	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Blob createBlob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Clob createClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Properties getClientInfo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getClientInfo(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isValid(int timeout) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NClob createNClob() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		
	}	
}

