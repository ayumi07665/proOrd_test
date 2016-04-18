/**
* ManualOrderDAO  マニュアル発注指示データアクセスオブジェクトクラス
*
*	作成日    2003/04/25
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    品番マスター(FTBHIN01)、在庫テーブル(FTBZAI01)、発注履歴テーブル(FTBHAC02)、
* 			 副資材マスター(FTBFUK01)、副資材パターンマスター(FTBFUK02)、にアクセスするクラス。
* 
*	[変更履歴]
*		2003/06/16（ＮＩＩ）蛭田 敬子
*  			・条件項目（会社・メーカー分類・形態コード・邦洋区分）の追加。
*		2003/06/23
* 			・｢副資材発注｣ 追加。
* 		2003/06/30
* 			・副資材発注のとき、納品先名取得 追加。
* 		2003/07/23 （ＮＩＩ）岡田 夏美
* 			・ソート条件追加（形態コード、ソートキー）。
* 		2003/08/26 （ＮＩＩ）岡田 夏美
* 			・新品番マスター(HIN02)対応 追加
* 		2004/02/25　(ＮＩＩ）森
* 			・副資材コード・仕入先の展開をパターンマスタから付属構成マスタに変更 
* 		2004/03/04 (ＮＩＩ）森
* 			・VAPのときは必ず副資材コードが表示されるように修正
* 		2004/03/29 (NII) 森
* 			・品番マスタのテーブル名変更
* 		2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		2004/04/21 (NII)森
* 			・税込定価表示に伴う修正
* 		2005/05/25（ＮＩＩ）蛭田
* 			・発注書一括出力対応（ユーザーＩＤの追加）
* 		2005/09/09（ＮＩＩ）蛭田
* 			・VAPの場合、副資材名称取得時、ｿｰﾄ条件追加（副資材ｺｰﾄﾞ）
* 		2006/05/10（ＮＩＩ）田端 康教
* 			・キング社のランクをＣまで
* 		2007/02/13 （ＮＩＩ）岡田 夏美
* 			・品番マスター検索(１件ずつ)のとき、形態名称マスターとの結合を外部結合に修正
* 
***/

package com.jds.prodord.indicate.manualorder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.StringUtils;
import com.jds.prodord.order.prsorder.PrsOrderVO;

public class ManualOrderDAO extends CommonDAO{
	
	public ManualOrderDAO() throws SQLException{
    	super();
	}
	
	//品番マスター検索***********************************************************************
	public PrsOrderVO[] findHin01(ManualOrderVO queryVO)throws SQLException{

		String[] RANK = ManualOrderForm.makeRank(queryVO.getDaiKaiSkbCod());

		String sql = "SELECT H.DAIKAISKBCOD,H.KAISKBCOD,H.KIGBNG,H.HJIHNB,H.ARTKJ,H.TITKJ,H.KETCOD,"
					+ "H.HBIDTE,H.TOMRAK,H.PRSMKRCOD,H.FUKSZIMKR,H.SETSUU,H.JYTPRSMKR,K.KETNMKJ"
					+ " ,H.ZEIKMITKA FROM FTBHIN01 H" //add 04.04.21 税込定価追加
					+ " LEFT OUTER JOIN FTBMST06 K ON"
					+ " H.KETCOD = K.KETCOD"
					+ " WHERE H.DAIKAISKBCOD = '" + queryVO.getDaiKaiSkbCod() + "'"
					+ " AND K.DAIKAISKBCOD = '" + queryVO.getQueryKaiSkbCod() + "'"
					;
					
		String sql_kai = "";   
		//会社識別コード
        for(int i=0; i<queryVO.getKaiSkbCod_arr().size(); i++){
          if(i == 0){
          	sql +=  " AND H.KAISKBCOD IN ('";
          	sql_kai +=  " AND H.KAISKBCOD IN ('";
          }
          sql += queryVO.getKaiSkbCod_arr().get(i).toString();
          sql_kai += queryVO.getKaiSkbCod_arr().get(i).toString();
          
          if(i == queryVO.getKaiSkbCod_arr().size() - 1){
            sql  += "')";
            sql_kai += "')";
          }else{
            sql  += "','";
            sql_kai  += "','";
          }
        }
        
        boolean hbi_flg = false;
        boolean ketCod_flg = false;
        boolean mkrBun_flg = false;
        boolean hyoKbn_flg = false;
        boolean kigBng_flg = false;
               	
        //発売日
        for(int i=0; i<queryVO.getHbiDte_arr().size(); i++){
          hbi_flg = true;
          if(i == 0){
          	sql +=  " AND H.HBIDTE IN (";
          }
          sql += queryVO.getHbiDte_arr().get(i).toString();
          if(i == queryVO.getHbiDte_arr().size() - 1){
            sql  += ")";
          }else{
            sql  += ",";
          }
        }
        
        //記号番号
        for(int i=0; i<queryVO.getKigBng_arr().size(); i++){
          if(i == 0){
          	sql +=  " AND H.KIGBNG IN ('";
          }
          sql += queryVO.getKigBng_arr().get(i).toString();
          if(i == queryVO.getKigBng_arr().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }

		//形態コード
        for(int i=0; i<queryVO.getKetCod_arr().size(); i++){
          if(i == 0){
          		sql +=  " AND ";
          	sql +=  "K.KETNMKJ IN (G'";
          }
          sql += queryVO.getKetCod_arr().get(i).toString();
          if(i == queryVO.getKetCod_arr().size() - 1){
            sql  += "')";
          }else{
            sql  += "',G'";
          }
        }
        //メーカー分類
        for(int i=0; i<queryVO.getMkrBun_arr().size(); i++){
          if(i == 0){
          		sql +=  " AND ";
          	sql +=  "H.MKRBUN IN ('";
          }
          sql += queryVO.getMkrBun_arr().get(i).toString();
          if(i == queryVO.getMkrBun_arr().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }
        //邦洋区分
        for(int i=0; i<queryVO.getHyoKbn_arr().size(); i++){
          if(i == 0){
          		sql +=  " AND ";
          	sql +=  "H.KREHYOKBN IN ('";
          }
          sql += queryVO.getHyoKbn_arr().get(i).toString();
          if(i == queryVO.getHyoKbn_arr().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }
        
        if(queryVO.getDaiKaiSkbCod().equals(com.jds.prodord.common.CommonConst.KAICOD_K)){
			sql += " AND H.TOMRAK IN ('";
        
			for(int i=0; i<RANK.length; i++){
				sql += RANK[i];
				if(i == RANK.length -1){
					sql  += "')";
				}else{
					sql  += "','";
				}
			}        	
        }
        
        //ソート順
        sql += " ORDER BY";
        if(hbi_flg)
        	sql += " H.HBIDTE,";
        if(queryVO.getSort_ketCod())
			sql += " H.KETCOD,";
		if(queryVO.getSort_sortKey())
			sql += " H.SRTKEY,";
		sql += " H.KIGBNG";
					
		ArrayList finded_Arr = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				PrsOrderVO fmVO = new PrsOrderVO();
				fmVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				fmVO.setKigBng(rs.getString("KIGBNG").trim());
				fmVO.setHjiHnb(rs.getString("HJIHNB").trim());
				fmVO.setArtKj(StringUtils.removeSuffix(rs.getString("ARTKJ"),"　"));
				fmVO.setTitKj(StringUtils.removeSuffix(rs.getString("TITKJ"),"　"));
				fmVO.setKetCod(rs.getString("KETCOD").trim());
				fmVO.setHbiDte(rs.getString("HBIDTE").trim());
				fmVO.setTomRak(rs.getString("TOMRAK").trim());
				String wk_ziktik = DataFormatUtils.setFormatString(rs.getString("ZEIKMITKA").trim().substring(0,rs.getString("ZEIKMITKA").trim().indexOf(".")));
				fmVO.setZikTik(wk_ziktik); //2004.04.21 add

				if(!rs.getString("JYTPRSMKR").trim().equals("")){//受託プレスメーカーがあれば
					fmVO.setPrsMkrCod(rs.getString("JYTPRSMKR").trim());//受託プレスメーカーをセット
				}else{//なければ
					fmVO.setPrsMkrCod(rs.getString("PRSMKRCOD").trim());//プレスメーカーコードをセット
				}
				fmVO.setNhnCod(fmVO.getPrsMkrCod());
				
				if(!rs.getString("FUKSZIMKR").trim().equals(""))
					fmVO.setFukSziMkr(rs.getString("FUKSZIMKR").trim());
				fmVO.setSetSuu(rs.getString("SETSUU").trim());
				fmVO.setKetNm(StringUtils.removeSuffix(rs.getString("KETNMKJ"),"　"));
				
				fmVO.setExsitHin01(true);
				finded_Arr.add(fmVO);
				
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
		return (PrsOrderVO[])finded_Arr.toArray(new PrsOrderVO[finded_Arr.size()]);
	}
	
	final static String HIN01 = "FTBHIN01";
	final static String HIN02 = "FTBHIN02";
	//品番マスター検索(１件ずつ)****************************************************************
	
	public boolean	findHin01_row(ManualOrderVO queryVO,String kigBng, PrsOrderVO prsVO, String HIN_TABLE)throws SQLException{
		boolean ret;

		String sql = "SELECT H.DAIKAISKBCOD,H.KAISKBCOD,H.KIGBNG,H.HJIHNB,H.ARTKJ,H.TITKJ,H.KETCOD,"
					+ "H.HBIDTE,H.TOMRAK,H.PRSMKRCOD,H.FUKSZIMKR,H.SETSUU,H.JYTPRSMKR,VALUE(K.KETNMKJ,G'') AS KETNMKJ"
					+ " ,H.ZEIKMITKA FROM " + HIN_TABLE + " H"
					+ " LEFT OUTER JOIN FTBMST06 K ON"
					+ " H.KETCOD = K.KETCOD"
					+ " AND K.DAIKAISKBCOD = '" + queryVO.getQueryKaiSkbCod() + "'"
					+ " WHERE H.DAIKAISKBCOD = '" + queryVO.getDaiKaiSkbCod() + "'"
					;
					
		//会社識別コード
        for(int i=0; i<queryVO.getQueryKaiSkbCodList().size(); i++){
          if(i == 0){
          	sql +=  " AND H.KAISKBCOD IN ('";
          }
          sql += queryVO.getQueryKaiSkbCodList().get(i).toString();
          
          if(i == queryVO.getQueryKaiSkbCodList().size() - 1){
            sql  += "')";
          }else{
            sql  += "','";
          }
        }

        //記号番号
        sql +=  " AND H.KIGBNG ='"+ DataFormatUtils.mk_srykig(kigBng) +"'";
        
        //ソート順
        sql += " ORDER BY H.KIGBNG";
			
		ArrayList finded_Arr = new ArrayList();
		ResultSet rs = null;
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				prsVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				prsVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				prsVO.setKigBng(rs.getString("KIGBNG").trim());
				prsVO.setHjiHnb(rs.getString("HJIHNB").trim());
				if(prsVO.getHjiHnb().equals(""))
					prsVO.setHjiHnb(kigBng);
				prsVO.setArtKj(StringUtils.removeSuffix(rs.getString("ARTKJ"),"　"));
				prsVO.setTitKj(StringUtils.removeSuffix(rs.getString("TITKJ"),"　"));
				prsVO.setKetCod(rs.getString("KETCOD").trim());
				prsVO.setHbiDte(rs.getString("HBIDTE").trim());
				prsVO.setTomRak(rs.getString("TOMRAK").trim());
				String wk_ziktik = DataFormatUtils.setFormatString(rs.getString("ZEIKMITKA").trim().substring(0,rs.getString("ZEIKMITKA").trim().indexOf(".")));
				prsVO.setZikTik(wk_ziktik); //2004.04.21 add
				if(!rs.getString("JYTPRSMKR").trim().equals("")){//受託プレスメーカーがあれば
					prsVO.setPrsMkrCod(rs.getString("JYTPRSMKR").trim());//受託プレスメーカーをセット
				}else{//なければ
					prsVO.setPrsMkrCod(rs.getString("PRSMKRCOD").trim());//プレスメーカーコードをセット
				}
				prsVO.setNhnCod(prsVO.getPrsMkrCod());
				
				prsVO.setFukSziMkr(rs.getString("FUKSZIMKR").trim());
				prsVO.setSetSuu(rs.getString("SETSUU").trim());
				prsVO.setKetNm(StringUtils.removeSuffix(rs.getString("KETNMKJ"),"　"));
				prsVO.setExsitHin01(true);
				ret = true;
				
			}else{
				if(HIN_TABLE.equals(HIN02)){
					prsVO.setDaiKaiSkbCod(queryVO.getDaiKaiSkbCod());
					prsVO.setKaiSkbCod(queryVO.getQueryKaiSkbCod());//ユーザーの会社をセット
					prsVO.setSetSuu("1");//デフォルトは"1"
					prsVO.setKigBng(kigBng);
					prsVO.setHjiHnb(kigBng);
					prsVO.setExsitHin01(false);
				}
				ret = false;
			}
		}finally{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		}
		return ret;	
	}			

	//発注先名称取得*******************************************************************
	
	public void findHacNm(PrsOrderVO fmVO)throws SQLException{
		String sql = "SELECT SIRHACNM1 || SIRHACNM2 AS HACNM FROM FTBMST03 "
					+ " WHERE KAISKBCOD = ? AND SIRHACCOD = ?"
					;

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getPrsMkrCod());

			rs = ps.executeQuery();
			
			if(rs.next()){
				fmVO.setHacNm(StringUtils.removeSuffix(rs.getString("HACNM"),"　"));
			}
				
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}
	
	//在庫テーブル検索***********************************************************************
	public void findZai01(PrsOrderVO fmVO)throws SQLException{
		String sql = "SELECT PRSMNYKEI,PRSHACRUI,FUKMNYKEI,PRSNYOKEI,FUKNYOKEI,FUKHACRUI,FUKZAISUU"
					+ " FROM FTBZAI01 "
					+ " WHERE KAISKBCOD = ? AND KIGBNG = ?"
					;

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKigBng());

			rs = ps.executeQuery();
		
			if(rs.next()){
				fmVO.setPrsMnyKei(rs.getString("PRSMNYKEI").trim());
				fmVO.setPrsHacRui(rs.getString("PRSHACRUI").trim());
				fmVO.setFukMnyKei(rs.getString("FUKMNYKEI").trim());
				fmVO.setFukHacRui(rs.getString("FUKHACRUI").trim());
				fmVO.setFukZaiSuu(rs.getString("FUKZAISUU").trim());
				fmVO.setPrsNyoKei(rs.getString("PRSNYOKEI").trim());
				fmVO.setFukNyoKei(rs.getString("FUKNYOKEI").trim());
				fmVO.setExsitZai01(true);
			}else{
				fmVO.setExsitZai01(false);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}

//-----------------------------------------------------------------------------------
//発注履歴テーブル検索（プレス）*******************************************************************
	public void findHac02Prs(PrsOrderVO fmVO)throws SQLException{
		String sql = "SELECT HACSUU,NYOSUU,NKI,SINKYUKBN FROM FTBHAC02 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ? AND BUNCOD = ?"
					+ " ORDER BY HACSYODTE DESC"
					;

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			ps.setString(3,fmVO.getKigBng());
			ps.setString(4,"0");//プレス

			rs = ps.executeQuery();
			
			int count = 0;
			while(rs.next() && count<2){
				
				switch(count){
					case 0:
						fmVO.setPrsHacSuu1(rs.getString("HACSUU").trim());
						fmVO.setPrsHacNyo1(rs.getString("NYOSUU").trim());
						fmVO.setPrsHacNki1(rs.getString("NKI").trim());
						fmVO.setPrsKbn1(rs.getString("SINKYUKBN").trim());
						break;
					case 1:
						fmVO.setPrsHacSuu2(rs.getString("HACSUU").trim());
						fmVO.setPrsHacNyo2(rs.getString("NYOSUU").trim());
						fmVO.setPrsHacNki2(rs.getString("NKI").trim());
						fmVO.setPrsKbn2(rs.getString("SINKYUKBN").trim());
						break;
				}
				count++;
				fmVO.setExsitHac02Prs(true);
			}						
		
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}
	
	//発注履歴テーブル検索（副資材）*******************************************************************
	public void findHac02Fuk(PrsOrderVO fmVO)throws SQLException{
		String sql = "SELECT HACSUU,NYOSUU,NKI,SINKYUKBN FROM FTBHAC02 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND KIGBNG = ? AND BUNCOD = ? AND PRSFUKSZICOD = ?"
					+ " ORDER BY HACSYODTE DESC"
					;

		ResultSet rs = null;	
		PreparedStatement ps = null;	

		try{
			ps = conn.prepareStatement(sql);
			
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			ps.setString(3,fmVO.getKigBng());
			ps.setString(4,fmVO.getBunCod());
			ps.setString(5,fmVO.getFukSziCod());

			rs = ps.executeQuery();
			
			int count = 0;
			while(rs.next() && count<2){
				
				switch(count){
					case 0:
						fmVO.setFukHacSuu1(rs.getString("HACSUU").trim());
						fmVO.setFukHacNki1(rs.getString("NKI").trim());
						fmVO.setFukKbn1(rs.getString("SINKYUKBN").trim());
						break;
					case 1:
						fmVO.setFukHacSuu2(rs.getString("HACSUU").trim());
						fmVO.setFukHacNki2(rs.getString("NKI").trim());
						fmVO.setFukKbn2(rs.getString("SINKYUKBN").trim());
						break;
				}
				count++;
				fmVO.setExsitHac02Fuk(true);
			}
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}
		
//-------------------------------------------------------------------------------------------
	public static final int EXIST = 0;//存在する
	public static final int NOT_EXIST = 1;//存在しない
	public static final int RANK_ERR = 2;//存在するがランクエラー
//**品番存在チェック******************************************************************
	public int hasKigBng(ManualOrderVO fmVO, String kigBng)throws SQLException{		

		String[] RANK = ManualOrderForm.makeRank(fmVO.getDaiKaiSkbCod());
		if(kigBng.equals(""))
			return EXIST;
		String query = "";
		
		String sql = "SELECT KIGBNG"
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
//				for(int i=0; i<ManualOrderForm.RANK.length; i++){
//					if(rank.equals(ManualOrderForm.RANK[i]))
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
//					for(int i=0; i<ManualOrderForm.RANK.length; i++){
//						if(rank.equals(ManualOrderForm.RANK[i]))
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


	public static final int nhnMei = 0;//納品先名
	public static final int fukHacMei = 1;//副資材発注先名
	//発注先マスター検索(納品先名称取得)*******************************************************************
	
	public void findMst03(PrsOrderVO fmVO,int flg)throws SQLException{
		String sql = "SELECT SIRHACNM1 || SIRHACNM2 AS HACNM FROM FTBMST03 "
					+ " WHERE KAISKBCOD = ? AND SIRHACCOD = ?";

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);

			ps.setString(1,fmVO.getDaiKaiSkbCod());
			if(flg==nhnMei)
				ps.setString(2,fmVO.getNhnCod());
			if(flg==fukHacMei)
				ps.setString(2,fmVO.getFukSziHacSaki());

			rs = ps.executeQuery();
			
			if(rs.next()){
				if(flg==nhnMei)
					fmVO.setNhnMei(StringUtils.removeSuffix(rs.getString("HACNM"),"　"));
				if(flg==fukHacMei)
					fmVO.setHacNm(StringUtils.removeSuffix(rs.getString("HACNM"),"　"));				
			}else{
				if(flg==nhnMei)
					fmVO.setNhnMei("");
				if(flg==fukHacMei)
					fmVO.setHacNm("");				
			}
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		
	}
	
	//副資材マスター検索(品番マスターなしの場合)*******************************************************************
	public void findFuk01_NotExistHin(PrsOrderVO fmVO)throws SQLException{

		// Kの場合
		// 副資材発注は行なわない
		if (fmVO.getDaiKaiSkbCod().equals(CommonConst.KAICOD_K))
			return;
			
		String sql = "SELECT FUKSZINMKJ,FUKSZICOD,BUNCOD FROM FTBFUK01 "
					+ " WHERE DAIKAISKBCOD = ? AND KAISKBCOD = ? AND BUNCOD = '1'"
					+ " ORDER BY FUKSZICOD"; //2005/09/14 add

		ResultSet rs = null;	
		PreparedStatement ps = null;	
		
		try{
			ps = conn.prepareStatement(sql);
				
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			 
			rs = ps.executeQuery();
			
			ArrayList fukSziCod_arr = new ArrayList();
			ArrayList bunCod_arr = new ArrayList();
			if(rs.next()){
				fmVO.setFukSziCod(rs.getString("FUKSZICOD").trim());
				fmVO.setFukSziNm(StringUtils.removeSuffix(rs.getString("FUKSZINMKJ"),"　"));
				fmVO.setBasedRowFlg("1"); //2004/08/17 add
				fmVO.setBunCod(rs.getString("BUNCOD").trim());

				bunCod_arr.add(rs.getString("BUNCOD").trim());
				fukSziCod_arr.add(rs.getString("FUKSZICOD").trim());
			}		
			fmVO.setBunCod_arr(bunCod_arr);    //分類コード
			fmVO.setFukSziCod_arr(fukSziCod_arr); //副資材コード
							
		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
	}	
//	**ランクチェック******************************************************************
	  public int rankCheck(ManualOrderVO fmVO, String kigBng)throws SQLException{		

	  	  String[] RANK = ManualOrderForm.makeRank(fmVO.getDaiKaiSkbCod());
		  if(kigBng.equals(""))
			  return EXIST;
		  String query = "";
		
		  String sql = "SELECT KIGBNG,TOMRAK"
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
		  query	= sql + " AND KIGBNG = '"+ DataFormatUtils.mk_srykig(kigBng) +"'";

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
//			  }else{
//				  //省略品番で検索して結果なしだったら、記号番号で検索しなおす
//				  query	= sql + 
//				  rs = stmt.executeQuery(query);
//				
//				  if(rs.next()){
//					  boolean err_flg = true;
//					  String rank = rs.getString("TOMRAK").trim();
//					  for(int i=0; i<RANK.length; i++){
//						  if(rank.equals(RANK[i]))
//							  err_flg = false;
//					  }
//					  if(err_flg){
//						  return RANK_ERR;
//					  }else{
//						return EXIST;
//					  }
//				  }
			  }
			return EXIST;
			
		  }finally{
			  if(rs != null)
				  rs.close();
			  if(stmt != null)
				  stmt.close();
		  }			
	  }
}