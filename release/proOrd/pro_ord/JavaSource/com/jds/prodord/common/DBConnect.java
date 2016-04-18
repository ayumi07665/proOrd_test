package com.jds.prodord.common;

import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnect {

	private static DataSource ds;
	
	public Connection getDBConnection(String user, String pass){
	    Connection conn = null;
		try
		{
			if(ds == null){
				InitialContext initCtx = new InitialContext();
				ds = (DataSource)initCtx.lookup(com.jds.prodord.common.CommonConst.DS_JNDI_NAME);
			}
			conn = ds.getConnection(user,pass);
		}
		catch(Exception ex)
		{
			SystemException se = new SystemException(ex);
			se.printStackTrace();
			return null;
		}
		return conn;
	}

	public Connection getDBConnection(){
	    Connection conn = null;
		try
		{
			if(ds == null){
				InitialContext initCtx = new InitialContext();
				ds = (DataSource)initCtx.lookup(com.jds.prodord.common.CommonConst.DS_JNDI_NAME);
			}
			conn = ds.getConnection();
		}
		catch(Exception ex)
		{
			SystemException se = new SystemException(ex);
			se.printStackTrace();
			return null;
		}
		return conn;
	}

	public Connection getDBConnection(String user, String pass, boolean commitMode){
		Connection conn = this.getDBConnection(user,pass);
		try{
			conn.setAutoCommit(commitMode);
		}catch(Exception ex){
			SystemException se = new SystemException(ex);
			se.printStackTrace();
			return null;
		}
		return conn;
	}

	public Connection getDBConnection(boolean commitMode){
		Connection conn = this.getDBConnection();
		try{
			conn.setAutoCommit(commitMode);
		}catch(Exception ex){
			SystemException se = new SystemException(ex);
			se.printStackTrace();
			return null;
		}
		return conn;
	}
}


