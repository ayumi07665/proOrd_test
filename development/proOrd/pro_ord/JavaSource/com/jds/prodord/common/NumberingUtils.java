package com.jds.prodord.common;
import java.sql.*;

public class NumberingUtils {
	private NumberingUtils(){
	}


	/**
	 * 発注書番号・行番号採番メソッド.
	 * NOテーブルから採番する。
	 * 
	 * @param kaiSkbCod
	 * @param hacCod
	 * @param numberOfNumbers 一度に取得する番号数 
	 * @param sinKyuKbn 新譜旧譜区分
	 * @return 採番値
	 * @throws SQLException
	 * @throws IllegalStateException テーブル上の状態が異常なとき
	 */
	public static synchronized int[] createNumbers(
		String kaiSkbCod,
		String hacCod,
		int numberOfNumbers,
		String sinKyuKbn)
		throws SQLException {
			
		int[] numbers = new int[numberOfNumbers];
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		
		try{
			
 			com.jds.prodord.common.DBConnect db2c = new com.jds.prodord.common.DBConnect();
		   	conn = db2c.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String query1 = "SELECT CURNO,JGNNO,KGNNO FROM FTBCNL08";
			
			String query =	 " WHERE KAISKBCOD = '"+ kaiSkbCod +"'"
							+" AND   HACCOD    = '"+ hacCod +"'"
							+" AND   SINKYUKBN = '"+ sinKyuKbn +"'"; //2005/06/27 add
			
			rs = stmt.executeQuery(query1+query);

			if(rs.next()){
				int curno = rs.getInt("CURNO");
				if(rs.wasNull())
					throw new java.lang.IllegalStateException("FTBCNL08.CURNO is NULL ");
					
				int jgnno = rs.getInt("JGNNO");
				if(rs.wasNull())
					throw new java.lang.IllegalStateException("FTBCNL08.JGNNO is NULL ");
				int kgnno = rs.getInt("KGNNO");
				if(rs.wasNull())
					throw new java.lang.IllegalStateException("FTBCNL08.KGNNO is NULL ");

				if(jgnno < kgnno)
					throw new java.lang.IllegalStateException("FTBCNL08.JGNNO is smaller than FTBCNL08.KGNNO");

				if(rs.next()){
					while(rs.next()){
					}
					throw new java.lang.IllegalStateException("too many recode in database table FTBCNL08 ");
				}
				
				int hanteichi = curno;
				for(int i = 0; i < numbers.length;i++){
					hanteichi++;
					if(hanteichi > jgnno)
						hanteichi = kgnno;
					
					numbers[i] = hanteichi;
				}				
				
				String query2 = "UPDATE FTBCNL08 SET CURNO = " + hanteichi;
				
				//FTBCNL08更新
				stmt.executeUpdate(query2+query);

				conn.commit();
			}else{
				throw new java.lang.IllegalStateException("no recode in database table FTBCNL08 ");
			}			
			
		}catch(SQLException sqle){
			conn.rollback();
			throw sqle;
		}catch(RuntimeException re){
			conn.rollback();
			throw re;
		}finally{
			try{
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
			}finally{
				if(conn != null)
					conn.close();
			}
		}
		return numbers;
	}

}

