/**
* SubMateMasterDAO  副資材マスターメンテナンス  データアクセスオブジェクトクラス
*
*	作成日    2003/06/24
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    副資材マスター（FTBFUK01）にアクセスするクラス。
* 
*	[変更履歴]
*			2004/04/05(NII)森
*			・副資材マスター更新時に副資材在庫テーブル(FTBHIN12)も更新する
* 
*/

package com.jds.prodord.master.submatemaster;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.StringUtils;

public class SubMateMasterDAO extends CommonDAO{

	
	public SubMateMasterDAO() throws SQLException{
    	super();
	}


//-----  照会処理  --------------------------------------------------------------
	public SubMateMasterVO[] find(SubMateMasterVO fmVO)throws SQLException{

		String sql = " SELECT KAISKBCOD,FUKSZICOD,BUNCOD,FUKSZIHACCOD "
					+ " ,FUKSZINMKJ,UPDDTE,UPDJKK "
					+ " FROM FTBFUK01 "
					+ " WHERE DAIKAISKBCOD = '" + fmVO.getDaiKaiSkbCod() + "'"
					+ " AND KAISKBCOD = '" + fmVO.getKaiSkbCod() + "'"
					;

			if(!fmVO.getHukSziCod().trim().equals(""))
				sql		+= " AND FUKSZICOD = '" + fmVO.getHukSziCod() + "'"	;

		sql		+= " AND UPDKBN <> 'D' ";
		sql		+= " ORDER BY FUKSZIHACCOD ";

//System.out.println("会社識別："+ fmVO.getKaiSkbCod());
//System.out.println("sql : "+sql);

		List lis = new ArrayList();

		ResultSet rs = null;
		Statement stmt = null;

		try{
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while(rs.next()){

				SubMateMasterVO resultVO = new SubMateMasterVO();
				resultVO.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
				resultVO.setHidDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
				resultVO.setHidKaiSkbCod(rs.getString("KAISKBCOD").trim());
				resultVO.setOutHukSziCod(rs.getString("FUKSZICOD").trim());
				resultVO.setOutBunruiCod(rs.getString("BUNCOD").trim());
				resultVO.setOutHatcCod(rs.getString("FUKSZIHACCOD").trim());
//				resultVO.setOutHukSziMei(rs.getString("FUKSZINMKJ").trim());
				resultVO.setOutHukSziMei(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"　"));

				resultVO.setHidUpdDte(rs.getInt("UPDDTE"));
				resultVO.setHidUpdJkk(rs.getInt("UPDJKK"));

				lis.add(resultVO);

			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}	
				
		return (SubMateMasterVO[])lis.toArray(new SubMateMasterVO[lis.size()]);
	
	}
	

//-----  登録処理  --------------------------------------------------------------

	public void insert(SubMateMasterVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = " INSERT INTO FTBFUK01(DAIKAISKBCOD,KAISKBCOD,FUKSZICOD "
					+ ",BUNCOD,FUKSZIHACCOD,FUKSZINMKJ,UPDKBN,UPDDTE,UPDJKK) "
					+ " VALUES (?,?,?,?,?,?,?,?,?) "
					;

//System.out.println("sql : "+sql);

		PreparedStatement ps = null;
		try{

			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getHukSziCod());
			ps.setString(4,fmVo.getOutBunruiCod());
			ps.setString(5,fmVo.getOutHatcCod());
			ps.setString(6,fmVo.getOutHukSziMei());
			ps.setString(7,"A");
			ps.setInt(8,upddte);
			ps.setInt(9,updjkk);

			ps.executeUpdate();

		}finally{
			if(ps != null)
				ps.close();
		}			
	}
	

//-----  更新処理  --------------------------------------------------------------

	public void update(SubMateMasterVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "UPDATE FTBFUK01 SET BUNCOD = ?,FUKSZIHACCOD = ?,FUKSZINMKJ = ? "
					+ " ,UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND FUKSZICOD = ? "
					;

//System.out.println("sql : "+sql);

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getOutBunruiCod());
			ps.setString(2,fmVo.getOutHatcCod());
			ps.setString(3,fmVo.getOutHukSziMei());
			ps.setString(4,"U");
			ps.setInt(5,upddte);
			ps.setInt(6,updjkk);
	
			ps.setString(7,fmVo.getHidDaiKaiSkbCod());
			ps.setString(8,fmVo.getHidKaiSkbCod());
			ps.setString(9,fmVo.getOutHukSziCod());

			ps.executeUpdate();


		}finally{
			if(ps != null)
				ps.close();
		}
	}

	public void updateHin12(SubMateMasterVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "UPDATE FTBHIN12 SET BUNCOD = ? ,UPDKBN = 'U',UPDDTE = " + upddte + ",UPDJKK = " + updjkk
				 + " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND FUKSZICOD = ? ";

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getOutBunruiCod());
	
			ps.setString(2,fmVo.getHidDaiKaiSkbCod());
			ps.setString(3,fmVo.getHidKaiSkbCod());
			ps.setString(4,fmVo.getOutHukSziCod());

			ps.executeUpdate();


		}finally{
			if(ps != null)
				ps.close();
		}
	}
//-----  削除処理  --------------------------------------------------------------

	public void delete(SubMateMasterVO fmVo,int upddte,int updjkk)throws SQLException{
		String sql = "DELETE FROM FTBFUK01"
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND FUKSZICOD = ? "
					;

//System.out.println("sql : "+sql);
					
		PreparedStatement ps = null;
		try{

			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getHidDaiKaiSkbCod());
			ps.setString(2,fmVo.getHidKaiSkbCod());
			ps.setString(3,fmVo.getOutHukSziCod());

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

		
//-----  登録用検索処理  --------------------------------------------------------
	public int selectForInsert(SubMateMasterVO fmVo)throws SQLException{
		String sql = " SELECT BUNCOD,FUKSZIHACCOD,FUKSZINMKJ,UPDKBN,UPDDTE,UPDJKK "
					+ " FROM FTBFUK01 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND FUKSZICOD = ? "
				    + " AND UPDKBN <> 'D' "
					;
		
//System.out.println("sql : "+sql);

  		ResultSet rs = null;
		PreparedStatement ps = null;
		try{

			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getHukSziCod());
//System.out.println("getHukSziCod："+fmVo.getHukSziCod());

			rs = ps.executeQuery();


				if(rs.next()) //登録重複
					return MODIFIED;
				else
					return NOT_EXIST;
		
		}finally{

			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}

	}


//-----  更新・削除用検索処理  --------------------------------------------------------
	public int selectForUpdate(SubMateMasterVO fmVo)throws SQLException{

		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK "
					+ " FROM FTBFUK01 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND FUKSZICOD = ? "
					;

		//System.out.println("sql : "+sql);

		ResultSet rs = null;
		PreparedStatement ps = null;
		try{

			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVo.getHidDaiKaiSkbCod());
			ps.setString(2,fmVo.getHidKaiSkbCod());
			ps.setString(3,fmVo.getOutHukSziCod());

//System.out.println("b-1："+fmVo.getHidUpdDte());
//System.out.println("b-2："+fmVo.getHidUpdJkk());


//System.out.println("SelectForUpdate実行");
			rs = ps.executeQuery();


			if(rs.next()){
				String updKbn = rs.getString("UPDKBN");
				int updDte = rs.getInt("UPDDTE");
				int updJkk = rs.getInt("UPDJKK");

				if(updKbn.trim().equals("D")){
					return LOGICAL_DELETE;
				}


//System.out.println("a-1："+updDte);
//System.out.println("a-2："+updJkk);



		//検索した時のUPDDTEなどと更新前検索のUPDDTEが異なる場合
				if(updDte != fmVo.getHidUpdDte() || updJkk != fmVo.getHidUpdJkk()){
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



	public static final int NO_COD = 1;
	public static final int OK = 2;
	
//-----  登録・更新用発注先ｺｰﾄﾞ検索処理  --------------------------------------------------------

	public int findHatcCod(SubMateMasterVO fmVo)throws SQLException{
		String sql = "SELECT KAISKBCOD,SIRHACCOD "
					+ " FROM FTBMST03 "
					+ " WHERE KAISKBCOD = '"+fmVo.getDaiKaiSkbCod()+"'"
					+ " AND SIRHACCOD = '"+fmVo.getOutHatcCod()+"'"
					;


//System.out.println("sql : "+sql);

  		ResultSet rs = null;
		PreparedStatement ps = null;

		try{

   			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				if(rs.getString("SIRHACCOD").trim().equals(fmVo.getOutHatcCod())){
					return OK;  //OK
				}
			}
			return  NO_COD;//NG

		}finally{

			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}

	}



}