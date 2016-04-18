package com.jds.prodord.master.fzkhinksipaste;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jds.prodord.common.CommonDAO;
//import com.jds.prodord.order.prsorder.PrsOrderVO;
/**
* FzkHinKsiPasteDAO  付属品構成マスター一括貼り付け  データアクセスオブジェクトクラス
*
*	作成日    2007/09/30
*	作成者    （ＮＩＩ）田中
* 処理概要    付属品構成マスター（FTBMST08）にアクセスするクラス。
* 
*	[変更履歴]
*/

public class FzkHinKsiPasteDAO extends CommonDAO{

	
	public FzkHinKsiPasteDAO() throws SQLException{
		super();
	}

	
//**品番存在チェック******************************************************************

//	public FzkHinKsiPasteVO getHinData(String daiKaiSkbCod, String kigBng, String tblNm)throws SQLException{	

//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		FzkHinKsiPasteVO fmVO = new FzkHinKsiPasteVO();

//		String query = "SELECT H.DAIKAISKBCOD,H.KAISKBCOD,H.KIGBNG,H.HJIHNB,H.ARTKJ,H.TITKJ,H.KETCOD,"
//					+ "H.HBIDTE,H.TOMRAK,H.PRSMKRCOD,H.FUKSZIMKR,H.SETSUU,H.JYTPRSMKR,VALUE(K.KETNMKJ,G'') AS KETNMKJ"
//					+ " ,H.ZEIKMITKA FROM "+tblNm+" H" 
//					+ " LEFT OUTER JOIN FTBMST06 K ON"
//					+ " H.KETCOD = K.KETCOD"
//					+ " WHERE H.DAIKAISKBCOD = ? "
//					+ " AND K.DAIKAISKBCOD = ? "
//					+ " AND H.KIGBNG = ? "
//					+ " AND H.UPDKBN <> 'D' ";

//		try{
//			ps = conn.prepareStatement(query);
//			ps.setString(1, daiKaiSkbCod);
//			ps.setString(2, daiKaiSkbCod);
//			ps.setString(3, kigBng);
//			rs = ps.executeQuery();

//			if(rs.next()){
//				fmVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
//				fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
//				fmVO.setKigBng(rs.getString("KIGBNG").trim());
				
//				fmVO.setExsitHin01(true);
				
//			}
//		}finally{
//			if(rs != null)
//				rs.close();
//			if(ps != null)
//				ps.close();
//		}
		
//		return fmVO;
//	}

//	**品番存在チェック******************************************************************

	  public boolean hasKigBng(String daiKaiSkbCod, String kigBng, String tblNm)throws SQLException{	

		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  FzkHinKsiPasteVO fmVO = new FzkHinKsiPasteVO();

		  String query = "SELECT H.DAIKAISKBCOD,H.KAISKBCOD,H.KIGBNG,H.HJIHNB,H.ARTKJ,H.TITKJ,H.KETCOD,"
					  + "H.HBIDTE,H.TOMRAK,H.PRSMKRCOD,H.FUKSZIMKR,H.SETSUU,H.JYTPRSMKR,VALUE(K.KETNMKJ,G'') AS KETNMKJ"
					  + " ,H.ZEIKMITKA FROM "+tblNm+" H" 
					  + " LEFT OUTER JOIN FTBMST06 K ON"
					  + " H.KETCOD = K.KETCOD"
					  + " WHERE H.DAIKAISKBCOD = ? "
					  + " AND K.DAIKAISKBCOD = ? "
					  + " AND H.KIGBNG = ? "
					  + " AND H.UPDKBN <> 'D' ";

		  try{
			  ps = conn.prepareStatement(query);
			  ps.setString(1, daiKaiSkbCod);
			  ps.setString(2, daiKaiSkbCod);
			  ps.setString(3, kigBng);
			  rs = ps.executeQuery();

			  if(rs.next()){
				  fmVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				  fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				  fmVO.setKigBng(rs.getString("KIGBNG").trim());
				
//				  fmVO.setExsitHin01(true);
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
	
	//-----  副資材コード存在チェック  ----------------------------------------

	public boolean hasFukSziCod(FzkHinKsiPasteVO fmVo, String fukSziCod)throws SQLException{
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

	public boolean hassirSkCod(FzkHinKsiPasteVO fmVo, String sirsk)throws SQLException{
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

	public static final int NOT_MODIFIED = 1;
	public static final int MODIFIED = 2;
	public static final int NOT_EXIST = 3;
	public static final int LOGICAL_DELETE = 4;	

	//-----  付属品構成マスター存在チェック  ----------------------------------------

	public boolean selectForUpdate(FzkHinKsiPasteVO fmVo, String kigbng)throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;

		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK FROM FTBMST08 "
				+ " WHERE DAIKAISKBCOD = '" + fmVo.getDaiKaiSkbCod() + "'"
				+ " AND KAISKBCOD = '" + fmVo.getQueryKaiSkbCod() + "'"
				+ " AND KIGBNG = '" + kigbng + "'"
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
					return false;
				}


				//検索した時のUPDDTEなどと更新前検索のUPDDTEが異なる場合
//				if(updDte != fmVo.getUpdDte() || updJkk != fmVo.getUpdJkk()){
//					return MODIFIED;
//				}
				return false;			
			}else{
				return true;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}			
	}

	/**
	 * 登録
	 * 
	 */
	public void insert(FzkHinKsiPasteVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "INSERT INTO FTBMST08 (DAIKAISKBCOD,KAISKBCOD,KIGBNG,"
					 + "FUKTBL,SIRTBL,UPDKBN,UPDDTE,UPDJKK) "
					 + " VALUES (?,?,?,?,?,?,?,?)";
			
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getKigBng());
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


}

