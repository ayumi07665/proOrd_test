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
	
public static final int errors_internal = 1; //内部エラー
public static final int offservice = 2;      //サービス時間外
public static final int success = 0;         //エラーなし

	
	/**日付型チェックメソッド  YY-MM-DD*/
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
	 * 和暦(14以上=和暦として判定)を西暦(２桁)に変換するメソッド
	 * @param year
	 * @return 変換後の文字列
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
	 * サービス時間内かどうかチェックするメソッド
	 * @return int 判定結果（1:内部エラー, 2:サービス時間外, 0:エラーなし）
	 */	
	//2002/12/16 add
	public static int checkBatchDate(){
		
		// ※条件テーブルからバッチ日付・サービス時間FROM～TO取得
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
				//内部エラー
	    		return errors_internal;
	    	}
		
			// -1. サービス時間内／外のチェック
		
			Date sysdate = new Date(System.currentTimeMillis());
		
			if(chkByTime){
		 		int chktime = Integer.parseInt((new SimpleDateFormat("HHmmss")).format(sysdate));
				if(chktime >= Integer.parseInt(svcFrom) &&
				   chktime <= Integer.parseInt(svcTo)){
				}else{
					if(conn != null){
						conn.close();
					}
					//サービス時間外
					return offservice;    		
				}
			}
		
			// 0. 稼働日／非稼働日のチェック
			String chkdate = (new SimpleDateFormat("yyyyMMdd")).format(sysdate);
		
			// バッチ日付でチェックするか否かを制御
			if(chkByDate){
				if(batdate.equals(chkdate)){
				}else{
					if(conn != null){
						conn.close();
					}
					//サービス時間外
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
		//エラーなし
		return success;
	}
	
	/**
	 * 正式品番取得メソッド
	 * @return String 品番（結果なし：null）
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
						
			//会社識別コード
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
			//まずは省略記号で検索
			query	= sql + " AND SRYKIG = '" + sryKig + "'";
		
			rs = stmt.executeQuery(query);
			
			if(rs.next()){
				returnKigBng = rs.getString("KIGBNG").trim();//検索された記号番号をセットしなおす
			}else{
				//省略品番で検索して結果なしだったら、記号番号で検索しなおす
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
	 * 正式品番取得メソッド
	 * getKigBngの検索先をFTBHIN02に変更したメソッド
	 * @return String 品番（結果なし：null）
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
						
			//会社識別コード
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
			//まずは省略記号で検索
			query	= sql + " AND SRYKIG = '" + sryKig + "'";
		
			rs = stmt.executeQuery(query);
			
			if(rs.next()){
				returnKigBng = rs.getString("KIGBNG").trim();//検索された記号番号をセットしなおす
			}else{
				//省略品番で検索して結果なしだったら、記号番号で検索しなおす
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
	 * 発注先存在チェック
	 * @return boolen 結果あり：true 結果なし：false
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
	 * 更新権限チェック
	 * @return boolen 権限あり：true 権限なし：false
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

