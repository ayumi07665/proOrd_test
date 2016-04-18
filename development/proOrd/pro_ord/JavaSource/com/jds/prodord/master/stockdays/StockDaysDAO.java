/**
* StockDaysDAO  在庫日数マスターメンテナンス  データアクセスオブジェクトクラス
*
*	作成日    2003/06/09
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    条件テーブル（FTBMST05）にアクセスするクラス。
* 
*	[変更履歴]
*		2003/
* 
*/

package com.jds.prodord.master.stockdays;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jds.prodord.common.CommonDAO;

public class StockDaysDAO extends CommonDAO{

	
	public StockDaysDAO() throws SQLException{
    	super();
	}

//-----  照会処理  --------------------------------------------------------------
	public StockDaysVO[] find(StockDaysVO fmVO)throws SQLException{

		String sql = "SELECT KAISKBCOD,TOMRAK,KETCOD,KSNNISUU,UPDDTE,UPDJKK"
					+ " FROM FTBMST04"
					+ " WHERE DAIKAISKBCOD = '" + fmVO.getDaiKaiSkbCod() + "'"
					+ " AND KAISKBCOD = '" + fmVO.getKaiSkbCod() + "'" ;

		if(!fmVO.getRank().equals(""))
			sql += " AND TOMRAK = '" + fmVO.getRank() + "'";
		
		if(!fmVO.getKetCod().equals(""))
			sql += " AND KETCOD = '" + fmVO.getKetCod() + "'";


		sql		   += " AND UPDKBN <> 'D' ";

		if(!(!fmVO.getRank().equals("") && !fmVO.getKetCod().equals(""))){
			if(!fmVO.getRank().equals(""))
				sql += " ORDER BY KETCOD";
		    if(!fmVO.getKetCod().equals(""))
				sql += " ORDER BY TOMRAK";
		}


//		System.out.println("sql : "+sql);
		List lis = new ArrayList();

		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while(rs.next()){
				StockDaysVO resultVO = new StockDaysVO();
				resultVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
				resultVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				resultVO.setRank(rs.getString("TOMRAK").trim());
				resultVO.setKetCod(rs.getString("KETCOD").trim());
				resultVO.setOutStockDays(String.valueOf(rs.getInt("KSNNISUU")));

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
				
		return (StockDaysVO[])lis.toArray(new StockDaysVO[lis.size()]);
	
	}
	

//-----  登録処理  --------------------------------------------------------------

	public void insert(StockDaysVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "INSERT INTO FTBMST04 (DAIKAISKBCOD,KAISKBCOD,TOMRAK,KETCOD"
					+	",KSNNISUU,UPDKBN,UPDDTE,UPDJKK) "
					+  "VALUES (?,?,?,?,?,?,?,?)"
					;
					 
//		System.out.println("sql : "+sql);

		PreparedStatement ps = null;
		try{

			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getRank());
			ps.setString(4,fmVo.getKetCod());
			ps.setInt(5,Integer.parseInt(fmVo.getOutStockDays()));
			ps.setString(6,"A");
			ps.setInt(7,upddte);
			ps.setInt(8,updjkk);

			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}			
	}
	

//-----  更新処理  --------------------------------------------------------------

	public void update(StockDaysVO fmVo,int upddte,int updjkk)throws SQLException{

		String sql = "UPDATE FTBMST04 SET KSNNISUU = ?,UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ? "
					+ " AND KETCOD = ?";

//		System.out.println("sql : "+sql);

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getOutStockDays());
			ps.setString(2,"U");
			ps.setInt(3,upddte);
			ps.setInt(4,updjkk);
	
			ps.setString(5,fmVo.getDaiKaiSkbCod());
			ps.setString(6,fmVo.getKaiSkbCod());
			ps.setString(7,fmVo.getRank());
			ps.setString(8,fmVo.getKetCod());

			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}
	}

//-----  削除処理  --------------------------------------------------------------

	public void delete(StockDaysVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "DELETE FROM FTBMST04"
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ? "
					+ " AND KETCOD = ? "
					;

//		System.out.println("sql : "+sql);
					
		PreparedStatement ps = null;
		try{

			ps = conn.prepareStatement(sql);

//			ps.setString(1,"D");
//			ps.setInt(2,upddte);
//			ps.setInt(3,updjkk);
			
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
	
//-----  更新・削除用検索処理  --------------------------------------------------------
	public int selectForUpdate(StockDaysVO fmVo)throws SQLException{

		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK FROM FTBMST04 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND TOMRAK = ? "
					+ " AND KETCOD = ? ";

//		System.out.println("sql : "+sql);

		ResultSet rs = null;
		PreparedStatement ps = null;
		try{

			ps = conn.prepareStatement(sql);
			StockDaysForm fmForm = new StockDaysForm();

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getRank());
			ps.setString(4,fmVo.getKetCod());

			rs = ps.executeQuery();


			if(rs.next()){
				String updKbn = rs.getString("UPDKBN");
				int updDte = rs.getInt("UPDDTE");
				int updJkk = rs.getInt("UPDJKK");

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
	

}
