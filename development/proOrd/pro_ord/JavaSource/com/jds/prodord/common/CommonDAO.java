/**
* CommonDAO  共通データアクセスオブジェクトクラス
*
*	作成日    2006/09/19
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    共通データアクセスオブジェクトクラス。
* 
*	[変更履歴]
*		yyyy/mm/dd （ＮＩＩ）
* 			・
*/

package com.jds.prodord.common;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class CommonDAO {

	protected Connection conn;

	private Logger log = Logger.getLogger(this.getClass());

	public CommonDAO() throws SQLException {
		DBConnect db2c = new DBConnect();
		conn = db2c.getDBConnection();
	}
	
	/**
	 * このDAOの持つコネクションを返します。
	 * @return
	 */
	public Connection getConnection() {
		return conn;
	}

	public void setAutoCommit(boolean b) throws SQLException {
		conn.setAutoCommit(b);
	}

	public void close() throws SQLException {
		conn.close();
	}

	public void commit() throws SQLException {
		conn.commit();
	}

	public void rollback() throws SQLException {
		conn.rollback();
	}
	
	//-----------------------------------------------------------
	
	protected void debug(Object o){
		LogUtils.debug(log, o);
	}
	
	protected void info(Object o){
		LogUtils.info(log, o);
	}
}
