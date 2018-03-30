/**
* IkkatsuReferDAO  一括照会画面データアクセスオブジェクトクラス
*
*	作成日    2003/03/31
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    品番マスター(FTBHIN01)、在庫テーブル(FTBZAI01)、発注履歴テーブル(FTBHAC02)、在庫日数マスター(FTBMST04)、
* 			 形態ランク別条件マスター(FTBMST05)、副資材マスター(FTBFUK01)、副資材パターンマスター(FTBFUK02)、
* 			 発注書データ(FTBHAC01)にアクセスするクラス。
* 
*	[変更履歴]
*		2003/05/06（ＮＩＩ）蛭田 敬子
* 			・｢プレスコメント入力｣ 機能の追加。(発注先存在チェック&名称取得ﾒｿｯﾄﾞ追加)
* 		2003/07/01
* 			・納品先名の取得追加。
* 		2003/07/04
* 			・発注指示のとき、直送区分＝'0'追加。
* 		2003/07/16 （ＮＩＩ）岡田 夏美
* 			・ソート条件にソートキー追加。
* 			・検索時、品番が指定されていた場合、
* 			  (修正前)品番 OR その他の条件 → 品番のみで、その他の条件は加えないように変更。
* 		2004/02/25　(ＮＩＩ）森
* 			・副資材コード・仕入先の展開をパターンマスタから付属構成マスタに変更* 
* 		2004/03/04 (ＮＩＩ）森
* 			・VAPのときは必ず副資材コードが表示されるように修正
* 		2004/03/29 (NII) 森
* 			・品番マスタのテーブル名変更
*		2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
*		2004/04/21 (NII)森
* 			・税込定価表示に伴う修正
*		2005/02/03（ＮＩＩ）蛭田
*			・場所コードで納品先名を取得するように修正
*		2005/05/19（ＮＩＩ）蛭田
*			・発注書一括出力対応
*			　（発注履歴テーブルFTBHAC02・発注書データFTBHAC01に'USRID'登録）
* 		2005/09/09（ＮＩＩ）蛭田
* 			・VAPの場合、副資材名称取得時、ｿｰﾄ条件追加（副資材ｺｰﾄﾞ）
* 		2006/08/08 （ＮＩＩ）岡田 夏美
* 			・提案数算出用データ取得時に、テーブルを結合して一度で取得するように変更
* 
*/

package com.jds.prodord.reference.ikkatsurefer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jds.prodord.common.CommonConst;
import com.jds.prodord.common.CommonDAO;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.DateUtils;
import com.jds.prodord.common.QueryUtils;
import com.jds.prodord.common.StringUtils;

public class IkkatsuReferDAO extends CommonDAO{
	
	public IkkatsuReferDAO() throws SQLException{
    	super();
	}	

	//-------------------------------------------------------------------------------------------------

	public static final int SYRZMI = 2;//出力済
	public static final int HACZMI = 1;//発注済
	public static final int MIHACHU = 0;//未発注
	
	//提案数算出用データ検索******************************************************************
	
	public IkkatsuReferVO[] findTeiansuuData(com.jds.prodord.indicate.teiansuurefer.TeiansuuReferVO queryVO)throws SQLException{

		String sql = 
					"SELECT " +
						"HIN01.DAIKAISKBCOD," +
						"HIN01.KAISKBCOD," +
						"HIN01.KIGBNG," +
						"HIN01.HJIHNB," +
						"HIN01.ARTKJ," +
						"HIN01.TITKJ," +
						"HIN01.KETCOD," +
						"HIN01.HBIDTE," +
						"HIN01.TOMRAK," +
						"HIN01.PRSMKRCOD," +
						"HIN01.JYTPRSMKR," +
						"HIN01.FUKSZIMKR," +
						"HIN01.SETSUU," +
						"MST06.KETNMKJ," +
						"HIN01.ZEIKMITKA," +
						"VALUE(ZAI01.KAISKBCOD, '') ZAI01FLG," +
						"ZAI01.AZISUU," +
						"ZAI01.TNASEKZAN," +
						"ZAI01.JUCZAN," +
						"ZAI01.MHIKSUU," +
						"ZAI01.RSVSUM," +
						"ZAI01.TODURISUU," +
						"ZAI01.B1DURI," +
						"ZAI01.B2DURI," +
						"ZAI01.B3DURI," +
						"ZAI01.B4DURI," +
						"ZAI01.B5DURI," +
						"ZAI01.NJIHKNURI," +
						"(ZAI01.TOWURI + ZAI01.TODURISUU)AS TOWURI," +
						"ZAI01.B1WURI," +
						"ZAI01.B2WURI," +
						"(ZAI01.TOMURI + ZAI01.TODURISUU)AS TOMURI," +
						"ZAI01.B1MURI," +
						"ZAI01.B2MURI," +
						"(ZAI01.TOMHPN + ZAI01.TODHPNSUU)AS TOMHPN," +
						"ZAI01.B1MHPN," +
						"ZAI01.B2MHPN," +
						"(ZAI01.TOMTNA + ZAI01.TODTNASUU)AS TOMTNA," +
						"ZAI01.B1MTNA," +
						"ZAI01.B2MTNA," +
						"ZAI01.PRSMNYKEI," +
						"ZAI01.PRSHACRUI," +
						"ZAI01.FUKMNYKEI," +
						"ZAI01.FUKHACRUI," +
						"ZAI01.FUKZAISUU," +
						"ZAI01.PRSNYOKEI," +
						"ZAI01.FUKNYOKEI," +
						"VALUE(MST05.DAIKAISKBCOD, '') MST05FLG," +
						"MST05.SSNREDTIM," +
						"MST05.MRMSUU," +
						"VALUE(MST04.DAIKAISKBCOD, '') MST04FLG," +
						"MST04.KSNNISUU," +
						"HAC02.SYRZMISGN " + 
					"FROM " +
						"FTBHIN01 HIN01 ";

			 sql += "INNER JOIN FTBMST06 MST06 ON ";
						//CRの場合 MST06のDAIKAISKBCODは会社識別コードが入っているため
						if(queryVO.getKaiSkbCod().equals(CommonConst.KAICOD_CR)){
							sql+= "HIN01.KAISKBCOD = MST06.DAIKAISKBCOD AND ";
						}else{
							sql+= "HIN01.DAIKAISKBCOD = MST06.DAIKAISKBCOD AND ";
						}
						sql += "HIN01.KETCOD = MST06.KETCOD ";

			 sql += "LEFT OUTER JOIN FTBZAI01 ZAI01 ON " +
					   "HIN01.DAIKAISKBCOD = ZAI01.KAISKBCOD AND " +
					   "HIN01.KIGBNG = ZAI01.KIGBNG ";

			 sql += "LEFT OUTER JOIN FTBMST05 MST05 ON " +
						"HIN01.DAIKAISKBCOD = MST05.DAIKAISKBCOD AND " +
						"HIN01.KAISKBCOD = MST05.KAISKBCOD AND " +
						"HIN01.TOMRAK = MST05.TOMRAK AND " +
						"HIN01.KETCOD = MST05.KETCOD AND " +
						"MST05.UPDKBN <> 'D' ";

			 sql += "LEFT OUTER JOIN FTBMST04 MST04 ON " +
						"HIN01.DAIKAISKBCOD = MST04.DAIKAISKBCOD AND " +
						"HIN01.KAISKBCOD = MST04.KAISKBCOD AND " +
						"HIN01.TOMRAK = MST04.TOMRAK AND " +
						"HIN01.KETCOD = MST04.KETCOD AND " +
						"MST04.UPDKBN <> 'D' ";

			 sql += "LEFT OUTER JOIN " +
						"(SELECT DISTINCT TBL1.DAIKAISKBCOD, TBL1.KAISKBCOD, TBL1.KIGBNG, TBL2.SYRZMISGN FROM " +
							"(SELECT DAIKAISKBCOD, KAISKBCOD, KIGBNG, MAX(SYRSUU) AS MAXSYRSUU FROM FTBHAC02 " +
							 "WHERE " +
								"HACSYODTE = " + (new DateUtils()).getDate6() + " AND " +
								"BUNCOD = '0' " +
								"GROUP BY DAIKAISKBCOD, KAISKBCOD, KIGBNG, HACSYODTE, BUNCOD " +
							") TBL1 " +
							"INNER JOIN " +
							"FTBHAC02 TBL2 ON " +
							"TBL1.DAIKAISKBCOD = TBL2.DAIKAISKBCOD AND " +
							"TBL1.KAISKBCOD = TBL2.KAISKBCOD AND " +
							"TBL1.KIGBNG = TBL2.KIGBNG AND " +
							"TBL1.MAXSYRSUU = TBL2.SYRSUU " +
						") HAC02 ON " +
						"HIN01.DAIKAISKBCOD = HAC02.DAIKAISKBCOD AND " +
						"HIN01.KAISKBCOD = HAC02.KAISKBCOD AND " +
						"HIN01.KIGBNG = HAC02.KIGBNG ";

		sql += "WHERE HIN01.DAIKAISKBCOD = '" + queryVO.getDaiKaiSkbCod() + "'";					

		//選択された会社識別コード
		if(queryVO.getKaiSkbCod_arr().size() > 0){
			sql += " AND HIN01.KAISKBCOD "
				+ QueryUtils.editArrayToQuery(queryVO.getKaiSkbCod_arr());
		}
		//ランク
		if(queryVO.getRank_arr().size() > 0){
			sql += " AND HIN01.TOMRAK "
				+ QueryUtils.editArrayToQuery(queryVO.getRank_arr());
		}			
		//形態名称
		for (int i = 0; i < queryVO.getKetCod_arr().size(); i++) {
			if (i == 0)
				sql += " AND MST06.KETNMKJ IN (G'";
			sql += queryVO.getKetCod_arr().get(i).toString();
			if (i == queryVO.getKetCod_arr().size() - 1) {
				sql += "')";
			} else {
				sql += "',G'";
			}
		}
		//メーカー分類
		if(queryVO.getMkrBun_arr().size() > 0){
			sql += " AND HIN01.MKRBUN "
				+ QueryUtils.editArrayToQuery(queryVO.getMkrBun_arr());
		}			
		//邦洋区分
		if(queryVO.getHyoKbn_arr().size() > 0){
			sql += " AND HIN01.KREHYOKBN "
				+ QueryUtils.editArrayToQuery(queryVO.getHyoKbn_arr());
		}
		//会社識別コード
		String sql_kai = "";
		if(queryVO.getQueryKaiSkbCodList().size() > 0){
			sql_kai = " AND HIN01.KAISKBCOD "
			+ QueryUtils.editArrayToQuery(queryVO.getQueryKaiSkbCodList());
		}
		//記号番号
		if(queryVO.getKigBng_arr().size() > 0){
			sql += " AND HIN01.KIGBNG "
				+ QueryUtils.editArrayToQuery(queryVO.getKigBng_arr());
		}
		if (queryVO.getCommand().equals(IkkatsuReferForm.COMMAND_JIDOUHACHU)) {
			sql += " AND HIN01.TOMRAK <> 'N1'";
		}
		//ソート順
		sql += " ORDER BY";
		if (queryVO.getSort_rank())
			sql += " HIN01.TOMRAK,";
		if (queryVO.getSort_ketCod())
			sql += " HIN01.KETCOD,";
		if (queryVO.getSort_sortKey())
			sql += " HIN01.SRTKEY,";
		sql += " HIN01.KIGBNG";

String[] param = {};
debug("findTeiansuuData sql : " + QueryUtils.fillInQuestion(sql, param));
			
		ArrayList finded_Arr = new ArrayList();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()){
				IkkatsuReferVO fmVO = new IkkatsuReferVO();
				
				//品番データ(HIN01)---------------------------------------------------
				fmVO.setDaiKaiSkbCod(rs.getString("DAIKAISKBCOD").trim());
				fmVO.setKaiSkbCod(rs.getString("KAISKBCOD").trim());
				fmVO.setKigBng(rs.getString("KIGBNG").trim());
				fmVO.setHjiHnb(rs.getString("HJIHNB").trim());
				fmVO.setArtKj(StringUtils.removeSuffix(rs.getString("ARTKJ"),"　"));
				fmVO.setTitKj(StringUtils.removeSuffix(rs.getString("TITKJ"),"　"));
				fmVO.setKetCod(rs.getString("KETCOD").trim());
				fmVO.setHbiDte(rs.getString("HBIDTE").trim());
				fmVO.setTomRak(rs.getString("TOMRAK").trim());
				if(!rs.getString("JYTPRSMKR").trim().equals("")){//受託プレスメーカーがあれば
					fmVO.setPrsMkrCod(rs.getString("JYTPRSMKR").trim());//受託プレスメーカーをセット
				}else{//なければ
					fmVO.setPrsMkrCod(rs.getString("PRSMKRCOD").trim());//プレスメーカーコードをセット
				}
				fmVO.setNhnCod(fmVO.getPrsMkrCod());
				fmVO.setFukSziMkr(rs.getString("FUKSZIMKR").trim());
				fmVO.setSetSuu(rs.getString("SETSUU").trim());
				fmVO.setKetNm(StringUtils.removeSuffix(rs.getString("KETNMKJ"),"　"));
				String wk_ziktik =
					DataFormatUtils.setFormatString(
						rs.getString("ZEIKMITKA").trim().substring(0,rs.getString("ZEIKMITKA").trim().indexOf(".")));
				fmVO.setZikTik(wk_ziktik); 
				
				fmVO.setQueryKaiSkbCod(queryVO.getKaiSkbCod());
				fmVO.setBshCod(queryVO.getBshCod());
				fmVO.setUsrId(queryVO.getUsrId()); 
				
				//在庫データ(ZAI01)---------------------------------------------------
				if(!rs.getString("ZAI01FLG").equals("")){
					fmVO.setExsitZai01(true);
					fmVO.setAziSuu(rs.getString("AZISUU").trim());
					fmVO.setTnaSekZan(rs.getString("TNASEKZAN").trim());
					fmVO.setJucZan(rs.getString("JUCZAN").trim());
					fmVO.setMhikSuu(rs.getString("MHIKSUU").trim());
					fmVO.setRsvSum(rs.getString("RSVSUM").trim());
					fmVO.setTodUriSuu(rs.getString("TODURISUU").trim());
					fmVO.setB1dUri(rs.getString("B1DURI").trim());
					fmVO.setB2dUri(rs.getString("B2DURI").trim());
					fmVO.setB3dUri(rs.getString("B3DURI").trim());
					fmVO.setB4dUri(rs.getString("B4DURI").trim());
					fmVO.setB5dUri(rs.getString("B5DURI").trim());
					fmVO.setAvgUri(rs.getString("NJIHKNURI").trim());
					fmVO.setTowUri(rs.getString("TOWURI").trim());
					fmVO.setB1wUri(rs.getString("B1WURI").trim());
					fmVO.setB2wUri(rs.getString("B2WURI").trim());
					fmVO.setTomUri(rs.getString("TOMURI").trim());
					fmVO.setB1mUri(rs.getString("B1MURI").trim());
					fmVO.setB2mUri(rs.getString("B2MURI").trim());
					fmVO.setTomHpn(rs.getString("TOMHPN").trim());
					fmVO.setB1mHpn(rs.getString("B1MHPN").trim());
					fmVO.setB2mHpn(rs.getString("B2MHPN").trim());
					fmVO.setTomTna(rs.getString("TOMTNA").trim());
					fmVO.setB1mTna(rs.getString("B1MTNA").trim());
					fmVO.setB2mTna(rs.getString("B2MTNA").trim());
					fmVO.setPrsMnyKei(rs.getString("PRSMNYKEI").trim());
					fmVO.setPrsHacRui(rs.getString("PRSHACRUI").trim());
					fmVO.setFukMnyKei(rs.getString("FUKMNYKEI").trim());
					fmVO.setFukHacRui(rs.getString("FUKHACRUI").trim());
					fmVO.setFukZaiSuu(rs.getString("FUKZAISUU").trim());
					fmVO.setPrsNyoKei(rs.getString("PRSNYOKEI").trim());
					fmVO.setFukNyoKei(rs.getString("FUKNYOKEI").trim());
				}
				
				//形態ランク別条件マスターデータ(MST05)------------------------------
				if(!rs.getString("MST05FLG").equals("")){
					fmVO.setExsitMst05(true);
					fmVO.setSsnRedTim(rs.getInt("SSNREDTIM"));
					fmVO.setMrmSuu(rs.getInt("MRMSUU"));
				}
				
				//在庫日数マスターデータ(MST04)--------------------------------------
				if(!rs.getString("MST04FLG").equals("")){
					fmVO.setExsitMst04(true);
					fmVO.setKsnNisuu(rs.getInt("KSNNISUU"));
				}
				
				//発注済サイン(HAC02)------------------------------------------------
				//結果なし
				if(rs.getString("SYRZMISGN") == null){
					fmVO.setHacZmiSgn(MIHACHU);//未発注						
				//結果あり かつ 出力済だったら
				}else if(rs.getString("SYRZMISGN").equals("1")){
					fmVO.setHacZmiSgn(SYRZMI);//出力済
				//結果あり かつ 出力済でなかったら
				}else{
					fmVO.setHacZmiSgn(HACZMI);//発注済
				}

				finded_Arr.add(fmVO);
			}

		}finally{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
		}
		return (IkkatsuReferVO[])finded_Arr.toArray(new IkkatsuReferVO[finded_Arr.size()]);
							
	}
	
	//発注履歴テーブル検索（プレス）*******************************************************************
	
	public void findHac02Prs(IkkatsuReferVO fmVO)throws SQLException{
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
	
	public void findHac02Fuk(IkkatsuReferVO fmVO)throws SQLException{
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
						fmVO.setFukHacNyo1(rs.getString("NYOSUU").trim());
						fmVO.setFukHacNki1(rs.getString("NKI").trim());
						fmVO.setFukKbn1(rs.getString("SINKYUKBN").trim());
						break;
					case 1:
						fmVO.setFukHacSuu2(rs.getString("HACSUU").trim());
						fmVO.setFukHacNyo2(rs.getString("NYOSUU").trim());
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
	

//************************************************************************************************
	
	//発注履歴テーブル登録*******************************************************************
	
	public void insertHac02(IkkatsuReferVO fmVO,int upddte,int updjkk,String syrsuu,String seqno,String syrzmisgn)throws SQLException{
		String sql = "INSERT INTO FTBHAC02 (DAIKAISKBCOD,KAISKBCOD,HACSYODTE"
					 + ",HACSYOBNG,SYRSUU,SEQNO,HACCOD,SYRZMISGN,SINKYUKBN,GYOBNG,KIGBNG,TITKJ"
					 + ",BUNCOD,PRSFUKSZICOD,SETSUU,HACSUU,NYOSUU,NKI,NHNMEIKJ,CYKKBN"
					 + ",UPDKBN,UPDDTE,UPDJKK,FUKZAISUU,USRID,RRKTBL,TAN) "
					 + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sql);
	
			ps.setString(1,fmVO.getDaiKaiSkbCod());
			ps.setString(2,fmVO.getKaiSkbCod());
			
			ps.setInt(3,upddte);
			ps.setString(4,"");
			ps.setString(5,syrsuu);
			ps.setString(6,seqno);
			ps.setString(7,fmVO.getPrsMkrCod());
			ps.setString(8,syrzmisgn);
			ps.setString(9,"0");//旧譜
			ps.setString(10,"");
			ps.setString(11,fmVO.getKigBng());
			ps.setString(12,fmVO.getTitKj());
			ps.setString(13,"0");//プレス
			ps.setString(14,"");//プレス発注時、副資材コードはブランク
			ps.setString(15,fmVO.getSetSuu());
			ps.setString(16,fmVO.getPrsHacSuu());
			ps.setString(17,"0");
			ps.setInt(18,fmVO.getPrsNki());
			ps.setString(19,DataFormatUtils.getSkoNm(fmVO.getBshCod()));//2005/02/03 add
			ps.setString(20,"0");
			ps.setString(21,"A");
			ps.setInt(22,upddte);
			ps.setInt(23,updjkk);
			ps.setString(24,fmVO.getFukSziSuu());//2003/07/24 add
			ps.setString(25,fmVO.getUsrId());//2005/05/19 add
			ps.setString(26,""); //2005/08/26 add
			ps.setString(27,"0");

			ps.executeUpdate();
	
		}finally{
			if(ps != null)
				ps.close();
		}			
	}

	
	//発注先存在チェック&名称取得****************************************************************
	
	public boolean hasHacSakiMei(IkkatsuReferVO fmVO)throws SQLException{
		String sql = "SELECT SIRHACCOD, SIRHACNM1 || SIRHACNM2 AS HACNM FROM FTBMST03 "
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
				fmVO.setHacNm(rs.getString("HACNM").trim());
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

	public static final int nhnMei = 0;//納品先名
	public static final int fukHacMei = 1;//副資材発注先名
	//発注先マスター検索(納品先名称取得)*******************************************************************
	
	public void findMst03(IkkatsuReferVO fmVO,int flg)throws SQLException{
		String sql = "SELECT SIRHACNM1 || SIRHACNM2 AS HACNM FROM FTBMST03 "
					+ " WHERE KAISKBCOD = ? AND SIRHACCOD = ?"
					;

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
}

