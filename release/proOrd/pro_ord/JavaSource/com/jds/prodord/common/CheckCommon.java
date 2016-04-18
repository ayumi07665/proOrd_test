package com.jds.prodord.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CheckCommon {
	
public static final int errors_internal = 1; //�����G���[
public static final int offservice = 2;      //�T�[�r�X���ԊO
public static final int success = 0;         //�G���[�Ȃ�

	
	/**���t�^�`�F�b�N���\�b�h  YY-MM-DD*/
	public static boolean validateAsDate(String year,String month,String day){
		try{
			
			if(Integer.parseInt(year.trim(),10) < 0)
				return false;
			if((Integer.parseInt(month.trim(),10) < 1) || (Integer.parseInt(month.trim(),10) > 12))
				return false;
			if(Integer.parseInt(day.trim(),10) < 1)
				return false;
				
			String s = 	(2000 + Integer.parseInt(year.trim(),10)) + "-" + month.trim() + "-" + day.trim();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
			sdf.setLenient(false);
			sdf.parse(s);
				
		}catch(ParseException nfe){
			return false;
		}catch(NumberFormatException nfe){
			return false;
		}
		return true;		
	}

	
	public String getEscapedString(String _s){
		if(_s == null){
			return _s;
		}
		if(_s.length() < 1){
			return _s;
		}
		
		StringBuffer sb = new StringBuffer();
		String tmp = new String();
		
		for(int i=0; i<_s.length(); i++){
			tmp = _s.substring(i,i+1);
			if(tmp.equals("<")){
				tmp = "&lt;";
			}
			if(tmp.equals(">")){
				tmp = "&gt;";
			}
			if(tmp.equals("&")){
				tmp = "&amp;";
			}
			sb.append(tmp);
		}
		return sb.toString();
	}
	
	
	/**
	 * �a��(14�ȏ�=�a��Ƃ��Ĕ���)�𐼗�(�Q��)�ɕϊ����郁�\�b�h
	 * @param year
	 * @return �ϊ���̕�����
	 */	
	public static String convertWarekiToSeireki(String year){
		if(year.equals(""))
			return year;
		if(Integer.parseInt(year) > 13){
			int year_ = Integer.parseInt(year) - 12;
			year = StringUtils.lpad(year_+"",2,"0");
		}
		return year;
	}
	

	
	/**
	 * �T�[�r�X���ԓ����ǂ����`�F�b�N���郁�\�b�h
	 * @return int ���茋�ʁi1:�����G���[, 2:�T�[�r�X���ԊO, 0:�G���[�Ȃ��j
	 */	
	//2002/12/16 add
	public static int checkBatchDate(){
		
		// �������e�[�u������o�b�`���t�E�T�[�r�X����FROM�`TO�擾
		String batdate = "";
		String svcFrom = "";
		String svcTo   = "";
		boolean chkByDate = true;
		boolean chkByTime = true;
		String FLG_TRUE = "1";
		String FLG_FALSE = "0";
		boolean error = false;

		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
   		try{
	    	com.jds.prodord.common.DBConnect db2c = new com.jds.prodord.common.DBConnect();
		   	conn = db2c.getDBConnection();
	     	stmt = conn.createStatement();
		
			String query ="SELECT SUBSTR(TXT,1,22) AS BATDATE FROM VTBCNL09 " 
				+	" WHERE KAISKBCOD = 'JDS' AND KEYFLD LIKE 'M25010F%'";
		    	rs = stmt.executeQuery(query);
		
		   	if(rs.next()){
		   		error = false;
				batdate = rs.getString("BATDATE").substring(0,8);
				svcFrom = rs.getString("BATDATE").substring(8,14);
				svcTo   = rs.getString("BATDATE").substring(14,20);
				if(rs.getString("BATDATE").substring(20,21).equals(FLG_FALSE)){
					chkByDate = false;
				}
				if(rs.getString("BATDATE").substring(21,22).equals(FLG_FALSE)){
					chkByTime = false;
				}
		   	}else{
		    	error = true;
			}
			rs.close();
			
	    	if(error){
				if(conn != null){
					conn.close();
				}
				//�����G���[
	    		return errors_internal;
	    	}
		
			// -1. �T�[�r�X���ԓ��^�O�̃`�F�b�N
		
			Date sysdate = new Date(System.currentTimeMillis());
		
			if(chkByTime){
		 		int chktime = Integer.parseInt((new SimpleDateFormat("HHmmss")).format(sysdate));
				if(chktime >= Integer.parseInt(svcFrom) &&
				   chktime <= Integer.parseInt(svcTo)){
				}else{
					if(conn != null){
						conn.close();
					}
					//�T�[�r�X���ԊO
					return offservice;    		
				}
			}
		
			// 0. �ғ����^��ғ����̃`�F�b�N
			String chkdate = (new SimpleDateFormat("yyyyMMdd")).format(sysdate);
		
			// �o�b�`���t�Ń`�F�b�N���邩�ۂ��𐧌�
			if(chkByDate){
				if(batdate.equals(chkdate)){
				}else{
					if(conn != null){
						conn.close();
					}
					//�T�[�r�X���ԊO
					return offservice; 
				}
			}
   		}catch(SQLException sqle1){
			SystemException se = new SystemException(sqle1);
			se.printStackTrace();

	   		try{
	     		if(conn != null){
 	    			conn.close();
	  		   	}
	   		}catch(SQLException sqle){
				se = new SystemException(sqle);
				se.printStackTrace();
	   		}
	  	
	   	}finally{
	   		try{
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
	     		if(conn != null){
 	    			conn.close();
	  		   	}
	   		}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
	   		}
	   	}
		//�G���[�Ȃ�
		return success;
	}
	
	/**
	 * �����i�Ԏ擾���\�b�h
	 * @return String �i�ԁi���ʂȂ��Fnull�j
	 */	
	//2003/06/16 add
	public static String getKigBng(String daiKakSkbCod, ArrayList kaiSkbCod_arr, String _sryKig){
		
		String returnKigBng = "";	
		
		if(_sryKig.equals("")){
			return "";
		}
		String sryKig = DataFormatUtils.mk_srykig(_sryKig);
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		try{
			com.jds.prodord.common.DBConnect db2c = new com.jds.prodord.common.DBConnect();
		    conn = db2c.getDBConnection();
	     	stmt = conn.createStatement();
		
			String query = "";
			
			String sql = "SELECT KIGBNG "
						+ " FROM FTBHIN01 "
						+ " WHERE DAIKAISKBCOD = '" + daiKakSkbCod + "'"
						;
						
			//��Ў��ʃR�[�h
	        for(int i=0; i<kaiSkbCod_arr.size(); i++){
	          if(i == 0)
	          	sql +=  " AND KAISKBCOD IN ('";
	          sql += kaiSkbCod_arr.get(i).toString();
	          if(i == kaiSkbCod_arr.size() - 1){
	            sql  += "')";
	          }else{
	            sql  += "','";
	          }
	        }
	
			sql		   += " AND UPDKBN <> 'D' ";
			//�܂��͏ȗ��L���Ō���
			query	= sql + " AND SRYKIG = '" + sryKig + "'";
		
			rs = stmt.executeQuery(query);
			
			if(rs.next()){
				returnKigBng = rs.getString("KIGBNG").trim();//�������ꂽ�L���ԍ����Z�b�g���Ȃ���
			}else{
				//�ȗ��i�ԂŌ������Č��ʂȂ���������A�L���ԍ��Ō������Ȃ���
				query	= sql + " AND KIGBNG = '" + sryKig + "'";
				rs = stmt.executeQuery(query);
				
				if(rs.next()){
					returnKigBng = sryKig;
				}else{
					returnKigBng = null;
				}
			}
		
		}catch(SQLException sqle1){
			SystemException se = new SystemException(sqle1);
			se.printStackTrace();

	   		try{
	     		if(conn != null)
 	    			conn.close();
	   		}catch(SQLException sqle){
				se = new SystemException(sqle);
				se.printStackTrace();
	   		}
	  	
	   	}finally{
	   		try{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
	     		if(conn != null)
 	    			conn.close();
	   		}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
	   		}
	   	}			
		return returnKigBng;
	}
	/**
	 * �����i�Ԏ擾���\�b�h
	 * getKigBng�̌������FTBHIN02�ɕύX�������\�b�h
	 * @return String �i�ԁi���ʂȂ��Fnull�j
	 */	
	public static String getKigBng2(String daiKakSkbCod, ArrayList kaiSkbCod_arr, String _sryKig){
		
		String returnKigBng = "";	
		
		if(_sryKig.equals("")){
			return "";
		}
		String sryKig = DataFormatUtils.mk_srykig(_sryKig);
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		try{
			com.jds.prodord.common.DBConnect db2c = new com.jds.prodord.common.DBConnect();
		    conn = db2c.getDBConnection();
	     	stmt = conn.createStatement();
		
			String query = "";
			
			String sql = "SELECT KIGBNG "
						+ " FROM FTBHIN02 "
						+ " WHERE DAIKAISKBCOD = '" + daiKakSkbCod + "'"
						;
						
			//��Ў��ʃR�[�h
	        for(int i=0; i<kaiSkbCod_arr.size(); i++){
	          if(i == 0)
	          	sql +=  " AND KAISKBCOD IN ('";
	          sql += kaiSkbCod_arr.get(i).toString();
	          if(i == kaiSkbCod_arr.size() - 1){
	            sql  += "')";
	          }else{
	            sql  += "','";
	          }
	        }
	
			sql		   += " AND UPDKBN <> 'D' ";
			//�܂��͏ȗ��L���Ō���
			query	= sql + " AND SRYKIG = '" + sryKig + "'";
		
			rs = stmt.executeQuery(query);
			
			if(rs.next()){
				returnKigBng = rs.getString("KIGBNG").trim();//�������ꂽ�L���ԍ����Z�b�g���Ȃ���
			}else{
				//�ȗ��i�ԂŌ������Č��ʂȂ���������A�L���ԍ��Ō������Ȃ���
				query	= sql + " AND KIGBNG = '" + sryKig + "'";
				rs = stmt.executeQuery(query);
				
				if(rs.next()){
					returnKigBng = sryKig;
				}else{
					returnKigBng = null;
				}
			}
		
		}catch(SQLException sqle1){
			SystemException se = new SystemException(sqle1);
			se.printStackTrace();

	   		try{
	     		if(conn != null)
 	    			conn.close();
	   		}catch(SQLException sqle){
				se = new SystemException(sqle);
				se.printStackTrace();
	   		}
	  	
	   	}finally{
	   		try{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
	     		if(conn != null)
 	    			conn.close();
	   		}catch(SQLException sqle){
				SystemException se = new SystemException(sqle);
				se.printStackTrace();
	   		}
	   	}			
		return returnKigBng;
	}
	
	/**
	 * �����摶�݃`�F�b�N
	 * @return boolen ���ʂ���Ftrue ���ʂȂ��Ffalse
	 */ 
	public static boolean hasHacCod(String daiKaiSkbCod, String hacCod)throws SQLException{
		boolean ret;
		String sql = "SELECT SIRHACCOD FROM FTBMST03 "
					+ " WHERE KAISKBCOD = ? AND SIRHACCOD = ?"
					;

		ResultSet rs = null;
		PreparedStatement ps = null;	
		Connection conn = null;
		
		try{
 			com.jds.prodord.common.DBConnect db2c = new com.jds.prodord.common.DBConnect();
		   	conn = db2c.getDBConnection();

			ps = conn.prepareStatement(sql);
			
			ps.setString(1,daiKaiSkbCod);
			ps.setString(2,hacCod);

			rs = ps.executeQuery();
			
			if(rs.next())
				ret = true;
			else
				ret = false;
			
		}catch(SQLException sqle){
			throw sqle;
		}finally{
			try{
				if(rs != null)
					rs.close();
				if(ps != null)
					ps.close();
			}finally{
				if(conn != null)
					conn.close();
			}
		}
		return ret;
	}

	/**
	 * �X�V�����`�F�b�N
	 * @return boolen ��������Ftrue �����Ȃ��Ffalse
	 */ 
	public static boolean checkHyoKbn(String daiKaiSkbCod, String hyoKbn){
		boolean ret;
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)){
			if(hyoKbn.equals("2"))
				ret = true;
			else
				ret = false;
		}else{
			if(hyoKbn.equals("0"))
				ret = true;
			else
				ret = false;
		}
		
		return ret;

	}

}

