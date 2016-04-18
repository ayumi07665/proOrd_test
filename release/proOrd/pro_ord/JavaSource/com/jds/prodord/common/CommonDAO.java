/**
* CommonDAO  ���ʃf�[�^�A�N�Z�X�I�u�W�F�N�g�N���X
*
*	�쐬��    2006/09/19
*	�쐬��    �i�m�h�h�j���c �Ĕ�
* �����T�v    ���ʃf�[�^�A�N�Z�X�I�u�W�F�N�g�N���X�B
* 
*	[�ύX����]
*		yyyy/mm/dd �i�m�h�h�j
* 			�E
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
	 * ����DAO�̎��R�l�N�V������Ԃ��܂��B
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
