/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：DBConnectionPool.java
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
public class DBConnectionPool {
	
	/** */
	static final Log log = LogFactory.getLog(DBConnectionPool.class);

	/** */
	private Vector<Object> connections;
   
	/** */
	private ConnectionReaper reaper;

	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public DBConnectionPool() {
	      connections = new Vector<Object>(ApplicationConstants.POOLSIZE);
	      reaper = new ConnectionReaper(this);
	      reaper.start();		
	}

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public synchronized void reapConnections() {

      long lState = System.currentTimeMillis() - ApplicationConstants.TIMEOUT;
      Enumeration<Object> connList = connections.elements();
      
      while((connList != null) && (connList.hasMoreElements())) {
    	  DBConnection conn = (DBConnection) connList.nextElement();

          if((!conn.isUse()) && (lState >conn.getLastUse()) && 
                                            (!conn.validate())) {
        	  removeConnection(conn);
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
	 */	
   public synchronized void closeConnections() {
        
      Enumeration<Object> connList = connections.elements();

      while((connList != null) && (connList.hasMoreElements())) {
          DBConnection conn = (DBConnection) connList.nextElement();
          removeConnection(conn);
      }
   }

    /**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param conn
	 */
	private synchronized void removeConnection(DBConnection conn) {
       connections.removeElement(conn);
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
	public synchronized DBConnection getConnection() throws SQLException {
	
		   DBConnection c;

		   for(int i = 0; i < connections.size(); i++) {
	           
			   c = (DBConnection)connections.elementAt(i);
	           
	           if (c.lease()) {
	        	   try {
	        		   PreparedStatement preStmt = c.prepareStatement(ApplicationConstants.DB_SQL_CHECK_CONNECTION);
	        		   preStmt.executeQuery();
	        	   } catch (Exception e) {
	        		   connections.remove(i);
	        		   Connection conn = DriverManager.getConnection(ApplicationConstants.DB_URL, ApplicationConstants.DB_USER, ApplicationConstants.DB_PASSWORD);
	        		   c = new DBConnection(conn, this);
	        		   
	        		   if (ApplicationConstants.DB_TYPE == ApplicationConstants.DB2) {

	        			   Statement stmt = c.createStatement();

	        			   stmt.execute("SET CURRENT SCHEMA = '" + ApplicationConstants.DB_SCHEMA + "'");

	        			   stmt.close();
	        		   }
	        		   
	        		   c.lease();
	        		   connections.insertElementAt(c, i);
	        		   return  c;
	        	   }
	        	   return  c;
	           }
	       }
			try {
				Class.forName("com.ibm.db2.jcc.DB2Driver");
//				Class.forName("com.ibm.as400.access.AS400JDBCDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       Connection conn = DriverManager.getConnection(ApplicationConstants.DB_URL, ApplicationConstants.DB_USER, ApplicationConstants.DB_PASSWORD);
	       c = new DBConnection(conn, this);
	       
	       if (ApplicationConstants.DB_TYPE == ApplicationConstants.DB2) {
	    	   
		       Statement stmt = c.createStatement();
		       
		       stmt.execute("SET CURRENT SCHEMA = '" + ApplicationConstants.DB_SCHEMA + "'");
		       
		       stmt.close();
	       }
	       
	       c.lease();
	       connections.addElement(c);
	       return  c;
	 } 

	/**
	 * <DL>
	 *   <DT>メソッド記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 * @param conn
	 */
	public synchronized void returnConnection(DBConnection conn) {
      conn.expireLease();
	}
	
}

class ConnectionReaper extends Thread {

    private DBConnectionPool pool;
    private final long delay=300000;

    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param pool
     */
    ConnectionReaper(DBConnectionPool pool) {
        this.pool=pool;
    }

    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    public void run() {
        while(true) {
           try {
              sleep(delay);
           } catch( InterruptedException e) { }
           pool.reapConnections();
        }
    }
}

