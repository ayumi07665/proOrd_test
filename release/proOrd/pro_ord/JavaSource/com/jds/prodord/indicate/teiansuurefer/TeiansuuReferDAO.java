/**
* TeiansuuReferDAO  提案数照会指示データアクセスオブジェクトクラス
*
*	作成日    2003/02/20
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    条件テーブル（VTBCNL09）、会社マスター（FTBKAI01）、品番マスター（FTBHIN01）にアクセスするクラス。
* 
*	[変更履歴]
* 		 2004/03/29 (NII) 森
* 			・品番マスタのテーブル名変更
*		  2006/05/10（ＮＩＩ）田端 康教
*			・Ｋ社のランク条件変更対応
* 		
*/

package com.jds.prodord.indicate.teiansuurefer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jds.prodord.common.CommonDAO;

public class TeiansuuReferDAO extends CommonDAO{

	
	public TeiansuuReferDAO() throws SQLException{
    	super();
	}
	
	public static final int EXIST = 0;//存在する
	public static final int NOT_EXIST = 1;//存在しない
	public static final int RANK_ERR = 2;//存在するがランクエラー
//**品番存在チェック******************************************************************

	public int hasKigBng(TeiansuuReferVO fmVO, String kigBng)throws SQLException{	
		
		String[] RANK = TeiansuuReferForm.makeRank(fmVO.getDaiKaiSkbCod());
			
		if(kigBng.equals(""))
			return EXIST;
		String query = "";
		
		String sql = "SELECT KIGBNG,TOMRAK "
					+ " FROM FTBHIN01 "
					+ " WHERE DAIKAISKBCOD = '" + fmVO.getDaiKaiSkbCod() + "'"
					;
					
		//会社識別コード
        for(int i=0; i<fmVO.getQueryKaiSkbCodList().size(); i++){
          if(i == 0)
          	sql +=  " AND KAISKBCOD IN ('";
          sql += fmVO.getQueryKaiSkbCodList().get(i).toString();
          if(i == fmVO.getQueryKaiSkbCodList().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }
        
		sql		   += " AND UPDKBN <> 'D' ";
		//まずは省略記号で検索
		query	= sql + " AND SRYKIG = '" + kigBng + "'";

		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()){
				
				boolean err_flg = true;
				String rank = rs.getString("TOMRAK").trim();
				for(int i=0; i<RANK.length; i++){
					if(rank.equals(RANK[i]))
						err_flg = false;
				}
				if(err_flg){
					return RANK_ERR;
				}else{
					kigBng = rs.getString("KIGBNG").trim();//検索された記号番号をセットしなおす
					return EXIST;
				}	
			}else{
				//省略品番で検索して結果なしだったら、記号番号で検索しなおす
				query	= sql + " AND KIGBNG = '" + kigBng + "'";
				rs = stmt.executeQuery(query);
				
				if(rs.next()){
					boolean err_flg = true;
					String rank = rs.getString("TOMRAK").trim();
					for(int i=0; i<RANK.length; i++){
						if(rank.equals(RANK[i]))
							err_flg = false;
					}
					if(err_flg){
						return RANK_ERR;
					}else{
						return EXIST;
					}	
				}
				return NOT_EXIST;
			}
			
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}			

	}


}

