/**
* KetRakDAO  形態ランク別マスターメンテナンス  データアクセスオブジェクトクラス
*
*	作成日    2003/05/02
*	作成者    （ＮＩＩ）今井 美季
* 処理概要    形態ランク別マスター（FTBMST05）にアクセスするクラス。
* 
*	[変更履歴]
*		2003/05/22（ＮＩＩ）岡田 夏美
* 			・更新時の排他制御対応。
* 		2003/05/27（ＮＩＩ）蛭田 敬子
* 			・ソート条件の追加。
* 		2003/06/10
* 			・｢登録｣のとき、論理削除済のレコードはDELETEして登録。
* 			・｢削除｣のとき、論理削除Ｘ→DELETE。
*/

package com.jds.prodord.master.ketrak;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jds.prodord.common.CommonDAO;

public class KetRakDAO extends CommonDAO{

	
	public KetRakDAO() throws SQLException{
    	super();
	}


	/**
	 * 照会
	 * 
	 */
	public KetRakVO[] find(KetRakVO fmVO)throws SQLException{

		String sql = "SELECT KAISKBCOD,TOMRAK,KETCOD,SSNREDTIM,MINZAISUU,MINROTSUU,MRMSUU,"
					+ "UPDDTE,UPDJKK FROM FTBMST05 "
					+ " WHERE DAIKAISKBCOD = '" + fmVO.getDaiKaiSkbCod() + "'"
					+ " AND KAISKBCOD = '" + fmVO.getKaiSkbCod() + "'" ;
		if(!fmVO.getRank().equals("")){
			sql += " AND TOMRAK = '" + fmVO.getRank() + "'";
		}
		if(!fmVO.getKetCod().equals("")){
			sql += " AND KETCOD = '" + fmVO.getKetCod() + "'";
		}	   
			sql	+= " AND UPDKBN <> 'D' ";
			
		//2003/05/27
		if(!(!fmVO.getRank().equals("") && !fmVO.getKetCod().equals(""))){
			if(!fmVO.getRank().equals(""))
				sql += " ORDER BY KETCOD";
		    if(!fmVO.getKetCod().equals(""))
				sql += " ORDER BY TOMRAK";
		}

		List lis = new ArrayList();

		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				KetRakVO resultVO = new KetRakVO();
				resultVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());//2003/05/22 add okada
				resultVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				resultVO.setRank(rs.getString("TOMRAK").trim());
				resultVO.setKetCod(rs.getString("KETCOD").trim());
				resultVO.setSsnRedTim(String.valueOf(rs.getInt("SSNREDTIM")));
				resultVO.setMinZaiSuu(String.valueOf(rs.getInt("MINZAISUU")));
				resultVO.setMinRotSuu(String.valueOf(rs.getInt("MINROTSUU")));
				resultVO.setMrmSuu(String.valueOf(rs.getInt("MRMSUU")));

				resultVO.setUpdDte(rs.getInt("UPDDTE"));
				resultVO.setUpdJkk(rs.getInt("UPDJKK"));

				lis.add(resultVO);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}	
					
		return (KetRakVO[])lis.toArray(new KetRakVO[lis.size()]);
	
	}
	

	/**
	 * 登録
	 * 
	 */
	public void insert(KetRakVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "INSERT INTO FTBMST05 (DAIKAISKBCOD,KAISKBCOD,TOMRAK,KETCOD"
					 + ",SSNREDTIM,MINZAISUU,MINROTSUU,MRMSUU,UPDKBN,UPDDTE,UPDJKK) "
					 + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getRank());
			ps.setString(4,fmVo.getKetCod());
			ps.setString(5,fmVo.getSsnRedTim());
			ps.setString(6,fmVo.getMinZaiSuu());
			ps.setString(7,fmVo.getMinRotSuu());
			ps.setString(8,fmVo.getMrmSuu());
			ps.setString(9,"A");
			ps.setInt(10,upddte);
			ps.setInt(11,updjkk);
			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}			
	}


	


	/**
	 * 更新
	 * 
	 */
	public void update(KetRakVO fmVo,int upddte,int updjkk)throws SQLException{
			
		String sql = "UPDATE FTBMST05 SET SSNREDTIM = ? ,MINZAISUU = ? "
					+ " ,MINROTSUU = ? ,MRMSUU = ? "
					+ " ,UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ? "
					+ " AND KETCOD = ? ";
		
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setInt(1,Integer.parseInt(fmVo.getSsnRedTim()));
			ps.setInt(2,Integer.parseInt(fmVo.getMinZaiSuu()));
			ps.setInt(3,Integer.parseInt(fmVo.getMinRotSuu()));
			ps.setInt(4,Integer.parseInt(fmVo.getMrmSuu()));
			ps.setString(5,"U");
			ps.setInt(6,upddte);
			ps.setInt(7,updjkk);
	
			ps.setString(8,fmVo.getDaiKaiSkbCod());
			ps.setString(9,fmVo.getKaiSkbCod());
			ps.setString(10,fmVo.getRank());
			ps.setString(11,fmVo.getKetCod());

			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}
	}


	/**
	 * 削除
	 * 
	 */
//	public void delete(KetRakVO fmVo,int upddte,int updjkk)throws SQLException{
//		String sql = "UPDATE FTBMST05 SET UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
//					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ? "
//					+ " AND KETCOD = ? ";
//		
//		PreparedStatement ps = null;
//		try{
//			ps = conn.prepareStatement(sql);
//
//			ps.setString(1,"D");
//			ps.setInt(2,upddte);
//			ps.setInt(3,updjkk);
//			
//			ps.setString(4,fmVo.getDaiKaiSkbCod());
//			ps.setString(5,fmVo.getKaiSkbCod());
//			ps.setString(6,fmVo.getRank());
//			ps.setString(7,fmVo.getKetCod());
//			
//			ps.executeUpdate();
//
//		}finally{
//			if(ps != null)
//				ps.close();
//		}			
//	}

	public void delete(KetRakVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "DELETE FROM FTBMST05 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ? "
					+ " AND KETCOD = ? ";
//System.out.println("sql："+sql);
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getRank());
			ps.setString(4,fmVo.getKetCod());
			
			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}			
	}

	public static final int NOT_MODIFIED = 1;
	public static final int MODIFIED = 2;
	public static final int NOT_EXIST = 3;
	public static final int LOGICAL_DELETE = 4;
	
	/**
	 * 削除/更新用検索
	 * 
	 */
	public int selectForUpdate(KetRakVO fmVo)throws SQLException{

		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK FROM FTBMST05 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ? "
					+ " AND KETCOD = ? ";

		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getRank());
			ps.setString(4,fmVo.getKetCod());
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				String updKbn = rs.getString("UPDKBN");
				int updDte = rs.getInt("UPDDTE");
				int updJkk = rs.getInt("UPDJKK");

				while(rs.next()){
				}
				if(updKbn.trim().equals("D")){
					return LOGICAL_DELETE;
				}

				//検索した時のUPDDTEなどと更新前検索のUPDDTEが異なる場合
				if(updDte != fmVo.getUpdDte() || updJkk != fmVo.getUpdJkk()){
					return MODIFIED;
				}
				return NOT_MODIFIED;			
			}else{
				return NOT_EXIST;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}			

	}
	
	/**
	 * 論理削除のものを削除
	 * 
	 */
	public void deleteMst05(KetRakVO fmVo)throws SQLException{

		String sql = "DELETE FROM FTBMST05"
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ?  AND KETCOD = ? "
					+ " AND UPDKBN = 'D'";
//System.out.println("sql："+sql);
 		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getRank());
			ps.setString(4,fmVo.getKetCod());
			
			rs = ps.executeQuery();
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}			

	}



}

