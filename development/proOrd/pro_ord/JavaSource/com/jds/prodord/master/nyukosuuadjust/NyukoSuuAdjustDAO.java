/**
* NyukoSuuAdjustDAO  入庫数調整  データアクセスオブジェクトクラス
*
*	作成日    2003/09/30
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    発注履歴（FTBHAC02）、在庫マスター（FTBZAI01）、品番マスター（FTBHIN01・HIN02）
* 			 にアクセスするクラス。
* 
*	[変更履歴]
* 		 2004/03/29 (NII) 森
* 			・品番マスタのテーブル名変更
*/

package com.jds.prodord.master.nyukosuuadjust;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.DateUtils;

public class NyukoSuuAdjustDAO extends CommonDAO{
	
	public NyukoSuuAdjustDAO() throws SQLException{
    	super();
	}


	/**
	 * 照会
	 * 
	 */
	public NyukoSuuAdjustVO[] find(NyukoSuuAdjustVO fmVO)throws SQLException{

		String sql = "SELECT A.DAIKAISKBCOD,A.KAISKBCOD,A.HACCOD,A.SINKYUKBN,A.KIGBNG,"
					+ "VALUE(B.HJIHNB,VALUE(C.HJIHNB,'')) AS HJIHNB,"
					+ "VALUE(B.HBIDTE,VALUE(C.HBIDTE,0)) AS HBIDTE,"
					+ "HACSYODTE,HACSYOBNG,GYOBNG,SYRSUU,SEQNO,HACSUU,NYOSUU,KNUSGN,NKI,"
					+ "A.UPDDTE,A.UPDJKK FROM FTBHAC02 A"
					+ " LEFT OUTER JOIN FTBHIN01 B ON"
					+ " A.DAIKAISKBCOD = B.DAIKAISKBCOD"
					+ " AND A.KAISKBCOD = B.KAISKBCOD" 
					+ " AND A.KIGBNG = B.KIGBNG"
					+ " LEFT OUTER JOIN FTBHIN02 C ON"
					+ " A.DAIKAISKBCOD = C.DAIKAISKBCOD"
					+ " AND A.KAISKBCOD = C.KAISKBCOD" 
					+ " AND A.KIGBNG = C.KIGBNG"
					+ " WHERE A.DAIKAISKBCOD = '" + fmVO.getQueryDaiKaiSkbCod() + "'";
		
		//会社識別コード
        for(int i=0; i<fmVO.getQueryKaiSkbCodList().size(); i++){
          if(i == 0)
          	sql +=  " AND A.KAISKBCOD IN ('";
          sql += fmVO.getQueryKaiSkbCodList().get(i).toString();
          
          if(i == fmVO.getQueryKaiSkbCodList().size() - 1)
            sql  += "')";
          else
            sql  += "','";
        }
					
		if(!fmVO.getHacCod_H().equals("")){
			sql += " AND A.HACCOD = '" + fmVO.getHacCod_H() + "'";
		}
		//発注日
		if(fmVO.getHacDteTo_H() != 0){
        	sql +=  " AND A.HACSYODTE BETWEEN " + fmVO.getHacDteFrm_H() +" AND " + fmVO.getHacDteTo_H() + "";
        }else{
        	if(fmVO.getHacDteFrm_H() != 0)
        		sql +=  " AND A.HACSYODTE = " + fmVO.getHacDteFrm_H() + "";
        }
        //納期
		if(fmVO.getNkiTo_H() != 0){
        	sql +=  " AND A.NKI BETWEEN " + fmVO.getNkiFrm_H() +" AND " + fmVO.getNkiTo_H() + "";
        }else{
        	if(fmVO.getNkiFrm_H() != 0)
        		sql +=  " AND A.NKI = " + fmVO.getNkiFrm_H() + "";
        }
		//記号番号
        for(int i=0; i<fmVO.getKigBng_arr_H().size(); i++){
          if(i == 0){
          	sql +=  " AND A.KIGBNG IN ('";
          }
          sql += fmVO.getKigBng_arr_H().get(i).toString();
          if(i == fmVO.getKigBng_arr_H().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }

		DateUtils dateUtils = new DateUtils();
		int today = dateUtils.getDate6Int();
		
		sql += " AND NKI < " + today + " AND HACSUU > NYOSUU"
			+ " AND KNUSGN = '' AND BUNCOD = '0'"
			+ " ORDER BY A.HACCOD,A.KIGBNG,A.HACSYODTE,A.HACSYOBNG,A.GYOBNG";
//System.out.println("sql : "+sql);		
		List lis = new ArrayList();

		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				NyukoSuuAdjustVO resultVO = new NyukoSuuAdjustVO();
				resultVO.setDaiKaiSkbCod(fmVO.getQueryDaiKaiSkbCod());
				resultVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				resultVO.setHacCod(rs.getString("HACCOD").trim());
				resultVO.setKbn(rs.getString("SINKYUKBN").trim());
				resultVO.setKigBng(rs.getString("KIGBNG").trim());
				resultVO.setHjiHnb(rs.getString("HJIHNB").trim());
				resultVO.setHacSyoDte(rs.getInt("HACSYODTE"));
				resultVO.setHacSyoBng(rs.getString("HACSYOBNG").trim());
				resultVO.setGyoBng(rs.getString("GYOBNG").trim());
				resultVO.setSyrSuu(rs.getString("SYRSUU").trim());
				resultVO.setSeqNo(rs.getString("SEQNO").trim());
				resultVO.setHacSuu(rs.getString("HACSUU"));
				resultVO.setNyoSuu(rs.getString("NYOSUU"));
				resultVO.setKnuSgn(rs.getString("KNUSGN").trim());
				resultVO.setNki(rs.getString("NKI"));
				resultVO.setHbiDte(rs.getString("HBIDTE"));				
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
					
		return (NyukoSuuAdjustVO[])lis.toArray(new NyukoSuuAdjustVO[lis.size()]);
	
	}

	/**
	 * 更新
	 * 
	 */
	public void updateHac02(NyukoSuuAdjustVO fmVo,int nyosuu,int upddte,int updjkk)throws SQLException{
			
		String sql = "UPDATE FTBHAC02 SET KNUSGN = ? ,NYOSUU = ?"
					+ " ,UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND HACSYODTE = ? "
					+ " AND HACSYOBNG = ? AND GYOBNG = ? AND SYRSUU = ?"
					+ " AND SEQNO = ?";
		
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,"1");
			ps.setInt(2,nyosuu);
			ps.setString(3,"U");
			ps.setInt(4,upddte);
			ps.setInt(5,updjkk);
			
			ps.setString(6,fmVo.getDaiKaiSkbCod());
			ps.setString(7,fmVo.getKaiSkbCod());
			ps.setString(8,fmVo.getHacSyoDte()+"");
			ps.setString(9,DataFormatUtils.formatHacSyoBng(fmVo.getHacSyoBng()));
			ps.setString(10,DataFormatUtils.formatGyoBng(fmVo.getGyoBng()));
			ps.setString(11,fmVo.getSyrSuu());
			ps.setString(12,fmVo.getSeqNo());
			
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
	public void updateZai01(NyukoSuuAdjustVO fmVo,int sa,int upddte,int updjkk)throws SQLException{
			
		String sql = "UPDATE FTBZAI01 SET PRSMNYKEI = PRSMNYKEI - ? "
					+ " ,UPDKBN = ? ,UPDDTE = ? ,UPDJKK = ? "
					+ " WHERE KAISKBCOD = ? AND KIGBNG = ? ";
		
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,sa);
			ps.setString(2,"U");
			ps.setInt(3,upddte);
			ps.setInt(4,updjkk);
	
			ps.setString(5,fmVo.getDaiKaiSkbCod());
			ps.setString(6,fmVo.getKigBng());

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
	 * 更新用検索
	 * 
	 */
	public int selectForUpdate(NyukoSuuAdjustVO fmVo)throws SQLException{

		String sql = "SELECT UPDKBN,UPDDTE,UPDJKK FROM FTBHAC02 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND HACSYODTE = ? "
					+ " AND HACSYOBNG = ? AND GYOBNG = ? AND SYRSUU = ?"
					+ " AND SEQNO = ?";

		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
			ps.setString(1,fmVo.getDaiKaiSkbCod());
			ps.setString(2,fmVo.getKaiSkbCod());
			ps.setString(3,fmVo.getHacSyoDte()+"");
			ps.setString(4,DataFormatUtils.formatHacSyoBng(fmVo.getHacSyoBng()));
			ps.setString(5,DataFormatUtils.formatGyoBng(fmVo.getGyoBng()));
			ps.setString(6,fmVo.getSyrSuu());
			ps.setString(7,fmVo.getSeqNo());			
			
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




}

