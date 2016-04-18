/**
* FzkHinKsiDAO  付属品構成マスターメンテナンス  データアクセスオブジェクトクラス
*
*	作成日    2004/02/13
*	作成者    （ＮＩＩ）森
* 処理概要    付属品構成マスター（FTBMST08）にアクセスするクラス。
* 
*	[変更履歴]
* 			 2004/03/29 (NII) 森
* 				・品番マスタのテーブル名変更
*			 2004/06/23（ＮＩＩ）蛭田
*				・発注先名称取得メソッド追加
*
*/

package com.jds.prodord.master.fzkhinksi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.StringUtils;

public class FzkHinKsiDAO extends CommonDAO{
	
	public FzkHinKsiDAO() throws SQLException{
    	super();
	}

	/**
	 * 照会
	 * 
	 */
	final static String HIN01 = "FTBHIN01";
	final static String HIN02 = "FTBHIN02";
	//品番マスター検索(１件ずつ)****************************************************************
	
	public boolean findHin01_row(FzkHinKsiVO queryVO,FzkHinKsiVO resultVO,String HIN_TABLE)throws SQLException{
		boolean ret = false;
		String sql = "SELECT DAIKAISKBCOD,KAISKBCOD,KIGBNG,HJIHNB,TITKJ,KETCOD,"
					+ "HBIDTE,SETSUU "
					+ " FROM " + HIN_TABLE + ""
					+ " WHERE DAIKAISKBCOD = '" + queryVO.getDaiKaiSkbCod() + "'"
					+ " AND KAISKBCOD = '" + queryVO.getKaiSkbCod() + "'"
					+ " AND KIGBNG = '"+ DataFormatUtils.mk_srykig(queryVO.getHinban()) +"'"
					+ " ORDER BY KIGBNG"
					;
						
//System.out.println("sql : "+sql);
			
		ArrayList finded_Arr = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				resultVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				resultVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				resultVO.setHinban(rs.getString("KIGBNG").trim());
				resultVO.setTitle(StringUtils.removeSuffix(rs.getString("TITKJ"),"　"));
				resultVO.setKetCod(rs.getString("KETCOD").trim());
				resultVO.setHbidte(rs.getString("HBIDTE").trim());
				resultVO.setSetsuu(rs.getString("SETSUU").trim());
				resultVO.setExsitHin01(true);
				ret = true;
			}

		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
		return ret;	
	}			

	//副資材発注先コード・副資材名称取得**********************************************************
	
	public void findFuk01(FzkHinKsiVO resultVO)throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		try {
			//副資材マスター検索
			String sql = "SELECT FUKSZINMKJ FROM FTBFUK01 "
						+ " WHERE DAIKAISKBCOD = '" + resultVO.getDaiKaiSkbCod() + "'"
						+ " AND KAISKBCOD = '" + resultVO.getKaiSkbCod() + "'"
						+ " AND FUKSZICOD = '" + resultVO.getFuksziCod() + "'"
						;
							
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				resultVO.setFuksziNm(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"　"));//副資材名称
			}else{
				resultVO.setFuksziNm("　");//副資材名称
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
	}	
	
	//付属品構成マスター**********************************************************
	public void findMst08(FzkHinKsiVO resultVO)throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		try {
			String sql = "SELECT FUKTBL,SIRTBL,UPDDTE,UPDJKK FROM FTBMST08 "
						+ " WHERE DAIKAISKBCOD = '" + resultVO.getDaiKaiSkbCod() + "'"
						+ " AND KAISKBCOD = '" + resultVO.getKaiSkbCod() + "'"
						+ " AND KIGBNG = '" + resultVO.getHinban() + "'"
						;
							
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				resultVO.setFuksziCod08String(rs.getString("FUKTBL"));
				resultVO.setSirSk08String(rs.getString("SIRTBL"));
				resultVO.setUpdDte(rs.getInt("UPDDTE"));
				resultVO.setUpdJkk(rs.getInt("UPDJKK"));

				resultVO.setExsitMst08(true);
			}else{
				resultVO.setExsitMst08(false);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
	}	
	


	/**
	 * 照会
	 * 
	 */
	public void findMST06(FzkHinKsiVO fmVO,FzkHinKsiVO resultVO)throws SQLException{

		String sql = "SELECT KETCOD,KETNMKJ,UPDDTE,UPDJKK,KETNMKJ2,"
				+ "SZITBL FROM FTBMST06 "
				+ " WHERE DAIKAISKBCOD = '" + fmVO.getQueryKaiSkbCod() + "'" //(FTBMST06では)代表会社＝ユーザーの会社
				+ " AND KETCOD = '" + resultVO.getKetCod() + "'"
				+ " AND UPDKBN <> 'D' "
				+ " ORDER BY KETCOD";


		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				resultVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
				resultVO.setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
				resultVO.setKetCod(rs.getString("KETCOD").trim());
				resultVO.setKetNm(StringUtils.removeSuffix(rs.getString("KETNMKJ"),"　"));
				resultVO.setFuksziCod06String(rs.getString("SZITBL").trim());
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}	
		
	}
	
	//-----  副資材コード存在チェック  ----------------------------------------

	public boolean hasFukSziCod(FzkHinKsiVO fmVo, String fukSziCod)throws SQLException{
		if(fukSziCod.equals(""))
			return true;
		
		String sql = " SELECT FUKSZICOD,BUNCOD "
					+ " FROM FTBFUK01 "
					+ " WHERE DAIKAISKBCOD = '"+fmVo.getDaiKaiSkbCod()+"'"
					+ " AND FUKSZICOD = '" + fukSziCod + "'";

		ResultSet rs = null;
		PreparedStatement ps = null;

		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				fmVo.addBunCod(rs.getString("BUNCOD"));
				return true;
			}else{
				return false;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}

	}
	//-----  仕入先コード存在チェック  ----------------------------------------

	public boolean hassirSkCod(FzkHinKsiVO fmVo, String sirsk)throws SQLException{
		if(sirsk.equals(""))
			return true;
		
		String sql = " SELECT SIRHACCOD "
					+ " FROM FTBMST03 "
					+ " WHERE KAISKBCOD = '"+fmVo.getDaiKaiSkbCod()+"'" //mst03の会社識別コードには代表会社が入っているため
					+ " AND SIRHACCOD = '" + sirsk + "'";

		ResultSet rs = null;
		PreparedStatement ps = null;

		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				return true;
			}else{
				return false;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}

	}

	/**
	 * 登録
	 * 
	 */
	public void insert(FzkHinKsiVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "INSERT INTO FTBMST08 (DAIKAISKBCOD,KAISKBCOD,KIGBNG,"
					 + "FUKTBL,SIRTBL,UPDKBN,UPDDTE,UPDJKK) "
					 + " VALUES (?,?,?,?,?,?,?,?)";
			
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getHinban());
			ps.setString(4,fmVo.getFuksziCod08String());
			ps.setString(5,fmVo.getSirSk08String());
			ps.setString(6,"A");
			ps.setInt(7,upddte);
			ps.setInt(8,updjkk);
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

	public void update(FzkHinKsiVO fmVo,int upddte,int updjkk)throws SQLException{
			
		String sql = "UPDATE FTBMST08 SET FUKTBL = ? ,SIRTBL = ? "
					+ " ,UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ?";
		
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getFuksziCod08String().trim());
			ps.setString(2,fmVo.getSirSk08String().trim());
			ps.setString(3,"U");
			ps.setInt(4,upddte);
			ps.setInt(5,updjkk);
	
			ps.setString(6,fmVo.getDaiKaiSkbCod());
			ps.setString(7,fmVo.getKaiSkbCod());
			ps.setString(8,fmVo.getHinban());

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
	public void delete(FzkHinKsiVO fmVo,int upddte,int updjkk)throws SQLException{
			
		String sql = "DELETE FROM FTBMST08 "
				+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ?"
				;
		
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getHinban());

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
	 * 更新・削除用検索
	 * 
	 */

	public int selectForUpdate(FzkHinKsiVO fmVo)throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;

		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK FROM FTBMST08 "
				+ " WHERE DAIKAISKBCOD = '" + fmVo.getDaiKaiSkbCod() + "'"
				+ " AND KAISKBCOD = '" + fmVo.getKaiSkbCod() + "'"
				+ " AND KIGBNG = '" + fmVo.getHinban() + "'"
				;
			
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
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
			if(stmt != null)
				stmt.close();
		}			
	}
	
	//発注先名称取得 2004/06/23 add ********************************************************

	public void findMst03(FzkHinKsiVO resultVO)throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		try{
			//発注先マスター検索
			String sql = "SELECT SIRHACNM1 FROM FTBMST03 "
						+ " WHERE KAISKBCOD = '" + resultVO.getDaiKaiSkbCod() + "'"
						+ " AND SIRHACCOD = '" + resultVO.getSirSk() + "'";
							
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				resultVO.setHacNm(StringUtils.removeSuffix(rs.getString("SIRHACNM1"),"　"));
			}else{
				resultVO.setFuksziNm("　");
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}				
	}
}

