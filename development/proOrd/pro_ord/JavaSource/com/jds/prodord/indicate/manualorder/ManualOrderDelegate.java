/**
* ManualOrderDelegate  マニュアル発注指示実行クラス
*
*	作成日    2003/04/25
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    マニュアル発注指示を実行するクラス。
*
*	[変更履歴]
*        2003/06/16（ＮＩＩ）蛭田 敬子
*  			・条件項目（会社・メーカー分類・形態コード・邦洋区分）の追加。
* 			・納期の自動設定。
* 		 2003/06/23
* 			・｢副資材発注｣ 追加。
* 		 2003/06/30
* 			・副資材発注のとき、納品先名取得 追加。
* 		 2003/07/11
* 			・品番指定時、品番マスターが存在しなかった場合、
* 			  代表会社･会社･分類コード="1'で副資材マスターを参照し、副資材コードを取得 追加。
* 		 2003/07/17（ＮＩＩ）岡田 夏美
*  			・副資材発注時、発注数にデフォルトで副資材発注数１（最新の発注歴）をセット。
* 		 2003/12/19（ＮＩＩ）森
* 			・全ての品番を発注する前提でチェックを全品番付けて表示する
* 		 2004/03/04（ＮＩＩ）森
* 			・VAPのときは必ず副資材コードが表示されるようにログイン時の会社識別コードをVOにセット
* 		 2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
*		 2005/02/03（ＮＩＩ）蛭田
*			・場所コードで納品先名を取得するように修正
*		 2005/05/25（ＮＩＩ）蛭田
*			・発注書一括出力対応（ユーザーＩＤの追加）
*			・Ｋ社の場合、納期（当日日付＋７日）に修正
* 		 2006/05/10（ＮＩＩ）田端 康教
* 			・キング社のランクをＣまで
* 		 2006/12/19（ＮＩＩ）田中
* 			・ＦＸ社　納期デフォルト値変更
*
*/

package com.jds.prodord.indicate.manualorder;

import com.jds.prodord.order.prsorder.*;
import java.sql.*;
import java.util.*;
import com.jds.prodord.common.*;


public class ManualOrderDelegate {


	/**
	 * プレス発注
	 * 
	 */
	public PrsOrderVO[] doPrsHachu(ManualOrderVO fmVO,ManualOrderForm fmForm)throws SQLException{
				 	
		ManualOrderDAO fmDao = null;
		PrsOrderVO[] finded = null;
				
		boolean endTransaction = false;
		try{
			fmDao = new ManualOrderDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始
			//発売日指定のとき
			if(fmVO.getKigBng_arr().size() == 0){
				//品番マスター検索
				finded = fmDao.findHin01(fmVO);
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0; i < finded.length; i++){
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
					finded[i].setUsrId(fmVO.getUsrId());//2005/05/23 add

					//副資材発注先コード・副資材名称取得
					orderUtils.findFukSziHacSaki(finded[i]);	
					//発注先マスター検索
					fmDao.findHacNm(finded[i]);
					//在庫マスター検索
					fmDao.findZai01(finded[i]);
					//発注履歴テーブル検索（プレス）
					fmDao.findHac02Prs(finded[i]);
					finded[i].setBasedRowFlg("1");//基準行フラグ
					//発注履歴テーブル検索（副資材）
					fmDao.findHac02Fuk(finded[i]);
					finded[i].setKbn(fmVO.getKbn());//区分
					finded[i].setPrsHacSuu("");//発注数
					finded[i].setChoksoKbn("0");//直送区分
					finded[i].setComment("");//コメント
					finded[i].setNhnMei(DataFormatUtils.getSkoNm(fmVO.getBshCod()));//納品先名　2005/02/03 add
					// 2003.12.19 upd 全品番にチェックをつける　
					finded[i].setCheck_prs1(true);
					//2005/05/30 納期（K社対応）
					finded[i].setPrsNki(this.calculatePrsNki(finded[i].getHbiDte(),finded[i].getDaiKaiSkbCod()));//納期
					if(i == 0){
						finded[i].setPre_page(PrsOrderForm.ManualOrder);//遷移元ページ
						finded[i].setPrs_FukSgn(PrsOrderForm.PRSHACHU);
					}
				}
			//追加品番指定のとき
			}else{
				finded = new PrsOrderVO[fmVO.getKigBng_arr().size()];
				ArrayList kig_arr = fmVO.getKigBng_arr();

				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0; i < finded.length; i++){
					finded[i] = new PrsOrderVO();

					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
					finded[i].setUsrId(fmVO.getUsrId());//2005/05/23 add

					//品番マスター検索（１件ずつ)
					if(!fmDao.findHin01_row(fmVO,kig_arr.get(i)+"",finded[i],ManualOrderDAO.HIN01))
						fmDao.findHin01_row(fmVO,kig_arr.get(i)+"",finded[i],ManualOrderDAO.HIN02);
					if(finded[i].getExsitHin01()){
						//発注先マスター検索
						fmDao.findHacNm(finded[i]);
						//在庫マスター検索
						fmDao.findZai01(finded[i]);
						//副資材発注先コード・副資材名称取得
						orderUtils.findFukSziHacSaki(finded[i]);	
					}else{
						finded[i].setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
						finded[i].setKaiSkbCod(fmVO.getQueryKaiSkbCod());//品番がマスターに存在しない時はユーザーの会社をセット
						fmDao.findFuk01_NotExistHin(finded[i]);//2003/07/11 add
					}
					//発注履歴テーブル検索（プレス）
					fmDao.findHac02Prs(finded[i]);
					finded[i].setBasedRowFlg("1");//基準行フラグ
					//発注履歴テーブル検索（副資材）
					fmDao.findHac02Fuk(finded[i]);
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
					finded[i].setKbn(fmVO.getKbn());//区分
					finded[i].setPrsHacSuu("");//発注数
					//2005/05/30 納期（K社対応）
					finded[i].setPrsNki(this.calculatePrsNki(finded[i].getHbiDte(),finded[i].getDaiKaiSkbCod()));//納期
					finded[i].setChoksoKbn("0");//直送区分
					finded[i].setComment("");//コメント
					finded[i].setNhnMei(DataFormatUtils.getSkoNm(fmVO.getBshCod()));//納品先名　2005/02/03 add
					// 2003.12.19 upd 全品番にチェックをつける　
					finded[i].setCheck_prs1(true);
					if(i == 0){
						finded[i].setPre_page(PrsOrderForm.ManualOrder);//遷移元ページ
						finded[i].setPrs_FukSgn(PrsOrderForm.PRSHACHU);
					}
				}				
			}

		}finally{
			if(fmDao != null){
				try{
					fmDao.commit();
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return finded;
	}
	
	/**
	 * 副資材発注
	 * 
	 */
	public PrsOrderVO[] doFukHachu(ManualOrderVO fmVO,ManualOrderForm fmForm)throws SQLException{
				 	
		ManualOrderDAO fmDao = null;
		PrsOrderVO[] finded = null;
		ArrayList finded_Arr = new ArrayList();
		boolean hin_flg = false;
				
		boolean endTransaction = false;
		try{
			fmDao = new ManualOrderDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始
			
			
			//発売日指定のとき
			if(fmVO.getKigBng_arr().size() == 0){
				
				//品番マスター検索
				finded = fmDao.findHin01(fmVO);
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i=0; i<finded.length; i++){
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());  //2004.03.04
					finded[i].setUsrId(fmVO.getUsrId());//2005/05/25 add
					
					//副資材発注先コード・副資材名称取得
					orderUtils.findFukSziHacSaki(finded[i]);
					finded[i].setPrsMkrCod(finded[i].getFukSziHacSaki());//副資材発注先を発注先コードにセット
				}

				for(int i = 0; i < finded.length; i++){
					//副資材マスター検索
					finded_Arr.addAll(makeFukDataList(finded[i]));
				}

				finded = (PrsOrderVO[])finded_Arr.toArray(new PrsOrderVO[finded_Arr.size()]);//配列に戻す

				for(int i = 0; i < finded.length; i++){
				
					//発注先マスター検索
//	2004.2.25 del	fmDao.findHacNm(finded[i]);
					//在庫マスター検索
					fmDao.findZai01(finded[i]);
					//発注履歴テーブル検索（プレス）
					fmDao.findHac02Prs(finded[i]);
					//発注履歴テーブル検索（副資材）
					fmDao.findHac02Fuk(finded[i]);
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
					finded[i].setKbn(fmVO.getKbn());//区分
					if(finded[i].getBasedRowFlg().equals("1"))
						finded[i].setPrsHacSuu(finded[i].getFukHacSuu1());//発注数には副資材発注数1をセット
					finded[i].setPrsNki(this.calculateFukNki(finded[i].getHbiDte(),finded[i].getDaiKaiSkbCod()));//納期
					finded[i].setChoksoKbn("0");//直送区分
					finded[i].setComment("");//コメント
					// 2003.12.19 upd 全品番にチェックをつける　
					finded[i].setCheck_prs1(true);

					fmDao.findMst03(finded[i],ManualOrderDAO.fukHacMei);//発注先名称取得
					fmDao.findMst03(finded[i],ManualOrderDAO.nhnMei);//納品先名称取得

					if(i == 0){
						finded[i].setPre_page(PrsOrderForm.ManualOrder);//遷移元ページ
						finded[i].setPrs_FukSgn(PrsOrderForm.FUKHACHU);
					}
				}
			//追加品番指定のとき
			}else{
				finded = new PrsOrderVO[fmVO.getKigBng_arr().size()];
				ArrayList kig_arr = fmVO.getKigBng_arr();
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0; i < finded.length; i++){
					finded[i] = new PrsOrderVO();
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());  //2004.03.04
					finded[i].setUsrId(fmVO.getUsrId());//2005/05/25 add

					//品番マスター検索（１件ずつ)
					if(!fmDao.findHin01_row(fmVO,kig_arr.get(i)+"",finded[i],ManualOrderDAO.HIN01))
						fmDao.findHin01_row(fmVO,kig_arr.get(i)+"",finded[i],ManualOrderDAO.HIN02);
					if(finded[i].getExsitHin01())
						orderUtils.findFukSziHacSaki(finded[i]);//2003/06/20
					finded[i].setPrsMkrCod(finded[i].getFukSziHacSaki());//副資材発注先を発注先コードにセット  2003/07/17 add
				}

				for(int i = 0; i < finded.length; i++){
					//副資材マスター検索
					finded_Arr.addAll(makeFukDataList(finded[i]));
				}

				finded = (PrsOrderVO[])finded_Arr.toArray(new PrsOrderVO[finded_Arr.size()]);//配列に戻す
				
				for(int i = 0; i < finded.length; i++){
					if(finded[i].getExsitHin01()){
						//発注先マスター検索
//	2004.2.25 del		fmDao.findHacNm(finded[i]);
						
						//在庫マスター検索
						fmDao.findZai01(finded[i]);
						fmDao.findMst03(finded[i],ManualOrderDAO.fukHacMei);//発注先名称取得
						fmDao.findMst03(finded[i],ManualOrderDAO.nhnMei);//納品先名称取得
					}else{
						finded[i].setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
						finded[i].setKaiSkbCod(fmVO.getQueryKaiSkbCod());//品番がマスターに存在しない時はユーザーの会社をセット
						//副資材マスター検索
						fmDao.findFuk01_NotExistHin(finded[i]);//2003/07/11 add
					}
					//発注履歴テーブル検索（プレス）
					fmDao.findHac02Prs(finded[i]);
					//発注履歴テーブル検索（副資材）
					fmDao.findHac02Fuk(finded[i]);
					finded[i].setQueryKaiSkbCod(fmVO.getQueryKaiSkbCod());
					finded[i].setKbn(fmVO.getKbn());//区分
					if(finded[i].getBasedRowFlg().equals("1"))
						finded[i].setPrsHacSuu(finded[i].getFukHacSuu1());//発注数には副資材発注数1をセット  2003/07/17 add
					finded[i].setPrsNki(this.calculateFukNki(finded[i].getHbiDte(),finded[i].getDaiKaiSkbCod()));//納期
					finded[i].setChoksoKbn("0");//直送区分
					finded[i].setComment("");//コメント
					// 2003.12.19 upd 全品番にチェックをつける　
					finded[i].setCheck_prs1(true);

					if(i == 0){
						finded[i].setPre_page(PrsOrderForm.ManualOrder);//遷移元ページ
						finded[i].setPrs_FukSgn(PrsOrderForm.FUKHACHU);
					}

				}
			}

		}finally{
			if(fmDao != null){
				try{
					fmDao.commit();
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return (PrsOrderVO[])finded_Arr.toArray(new PrsOrderVO[finded_Arr.size()]);//配列に戻す;
	}
	
	/**
	 * 取得してある副資材情報を元に副資材情報リストを生成します。
	 * @param fmVO
	 * @return 副資材情報リスト
	 */
	private ArrayList makeFukDataList(PrsOrderVO fmVO) {	
	
		ArrayList finded_arr = new ArrayList();
		ArrayList fukSziCod_arr = fmVO.getFukSziCod_arr(); //副資材コードarr
		ArrayList fukSziHacSaki_arr = fmVO.getFukSziHacSaki_arr();
		//仕入先コードarr　add 2004.02.25
		ArrayList bunCod_arr = fmVO.getBunCod_arr(); //分類コードarr
		ArrayList fukSziNm_arr = fmVO.getFukSziNm_arr(); //副資材名称arr
	
		fmVO.setBasedRowFlg("1");
		finded_arr.add(fmVO); //検索済みのもの(基準行フラグ=1にあたるもの)をadd
		
		if (!fmVO.getExsitHin01())
			return finded_arr;
	
		//ループを1から開始
		for (int i = 1; i < fukSziCod_arr.size(); i++) {
			PrsOrderVO vo = new PrsOrderVO();
			vo.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
			vo.setKaiSkbCod(fmVO.getKaiSkbCod());
			vo.setUsrId(fmVO.getUsrId());
			vo.setKigBng(fmVO.getKigBng());
	
			vo.setFukSziHacSaki(String.valueOf(fukSziHacSaki_arr.get(i)));
			vo.setPrsMkrCod(String.valueOf(fukSziHacSaki_arr.get(i))); //副資材発注先
			vo.setFukSziNm(String.valueOf(fukSziNm_arr.get(i)));       //副資材名称
			vo.setBunCod(String.valueOf(bunCod_arr.get(i)));           //分類コード
			vo.setFukSziCod(String.valueOf(fukSziCod_arr.get(i)));     //副資材コード
			vo.setBasedRowFlg("2");                                    //基準行フラグ
			//品番情報
			vo.setTitKj(fmVO.getTitKj());
			vo.setHjiHnb(fmVO.getHjiHnb());
			vo.setKetCod(fmVO.getKetCod());
			vo.setHbiDte(fmVO.getHbiDte());
			vo.setArtKj(fmVO.getArtKj());
			vo.setTomRak(fmVO.getTomRak());
			vo.setKetNm(fmVO.getKetNm());
			vo.setZikTik(fmVO.getZikTik()); //2004.04.21 add
			vo.setFukSziPtn(fmVO.getFukSziPtn());
			vo.setSetSuu(fmVO.getSetSuu());
			vo.setNhnCod(fmVO.getNhnCod());
			vo.setNhnMei(fmVO.getNhnMei());
			vo.setExsitHin01(fmVO.getExsitHin01());
	
			finded_arr.add(vo);
		}
		return finded_arr;
	}
	
	//--------------------------------------------------------------------
	
	/** プレス納期の算出 */
	public int calculatePrsNki(String hbiDte,String daiKaiSkbCod){
		if(hbiDte.equals("") || hbiDte.equals("0"))
			return 0;
		
		int prsnki = 0;
		//今日の日付
		DateUtils duToday = new DateUtils();
		//2005/05/30 Ｋ社の場合、納期(当日 + ７日)
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_K)||
				daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			duToday.addToDate(Calendar.DATE, 7);//７日後
			prsnki = duToday.getDate6Int();
		}else{

			//発売日
			DateUtils duHbiDte = new DateUtils(hbiDte, DateUtils.PTN_DATE_6);
			duHbiDte.addToDate(Calendar.DATE, -14);//発売日－１４日

			//2006/12/19 ＦＸ社の場合
			if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)){
				//発売日－１４日が当日より前の日付だったら
				if(duToday.getDate8Int() > duHbiDte.getDate8Int()){
					int hbiDte8 = DateUtils.convertYYYYMMDD(Integer.parseInt(hbiDte));
					if(duToday.getDate8Int() > hbiDte8){
						duToday.addToDate(Calendar.DATE, 14);//１４日後
						prsnki = duToday.getDate6Int();//１４日後
					}else{
						prsnki = duToday.getDate6Int();//当日日付
					}	
				}else{
					prsnki = duHbiDte.getDate6Int();//発売日－１４日
				}
			}else{
				//発売日－１４日が当日より前の日付だったら
				if(duToday.getDate8Int() > duHbiDte.getDate8Int())
					prsnki = duToday.getDate6Int();//当日日付
				else
					prsnki = duHbiDte.getDate6Int();//発売日－１４日
			}
		}
		return prsnki;
	}

	
	/** 副資材納期の算出 */
	public int calculateFukNki(String hbiDte,String daiKaiSkbCod){
		if(hbiDte.equals("") || hbiDte.equals("0"))
			return 0;
			
		int fuknki = 0;
		//今日の日付
		DateUtils duToday = new DateUtils();
		//発売日
		DateUtils duHbiDte = new DateUtils(hbiDte, DateUtils.PTN_DATE_6);

		//2006/12/19 ＦＸ社の場合
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)){
			duHbiDte.addToDate(Calendar.DATE, -42);//発売日－４２日
			//発売日－４２日が当日より前の日付だったら
			if(duToday.getDate8Int() > duHbiDte.getDate8Int()){
				int hbiDte8 = DateUtils.convertYYYYMMDD(Integer.parseInt(hbiDte));
				if(duToday.getDate8Int() > hbiDte8){
					duToday.addToDate(Calendar.DATE, 7);//７日後
					fuknki = duToday.getDate6Int();//７日後
				}else{
					fuknki = duToday.getDate6Int();//当日日付
				}	
			}else{
				fuknki = duHbiDte.getDate6Int();//発売日－４２日
			}
		//LAN社の場合	
		}else if(daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			duToday.addToDate(Calendar.DATE, 7);//７日後
			fuknki = duToday.getDate6Int();
		}else{
			duHbiDte.addToDate(Calendar.DATE, -21);//発売日－２１日
			//発売日－２１日が当日より前の日付だったら
			if(duToday.getDate8Int() > duHbiDte.getDate8Int())
				fuknki = duToday.getDate6Int();//当日日付
			else
				fuknki = duHbiDte.getDate6Int();//発売日－２１日
		}
		return fuknki;
	}

	
	/**
	 * ランクチェック
	 * 
	 */
	public ManualOrderResult doRankCheck(ManualOrderVO fmVO)throws SQLException{
				 	
		ManualOrderDAO fmDao = null;
		ManualOrderResult result = new ManualOrderResult(fmVO,true,"");

		boolean error = false;
		
		ArrayList kig_arr = fmVO.getKigBng_arr();
		ArrayList errors_kig = new ArrayList();
		
		boolean endTransaction = false;
		try{
			fmDao = new ManualOrderDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始

			if(kig_arr.size() > 0){
				for(int i = 0; i < kig_arr.size(); i++){
					int errtype_kig = fmDao.rankCheck(fmVO,kig_arr.get(i)+"");
					if(errtype_kig != ManualOrderDAO.EXIST){
						String[] err = {String.valueOf(i+1),errtype_kig+""};//[0]：エラーのあるindex,[1]：エラーの種類
						errors_kig.add(err);
						error = true;
					}
				}
			}else{
				
			}
			//エラーがあれば
			if(error){
				result = new ManualOrderResult(fmVO,false,"");
				if(errors_kig.size() > 0){
					result.setMap(ManualOrderResult.KEY_KIGBNG,errors_kig);
				}				
			}
			//エラーがなければ
			if(!error){
				//正常に成功
				result = new ManualOrderResult(fmVO,true,"");
			}

		}finally{
			if(fmDao != null){
				try{
					fmDao.commit();
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return result;
	}
}

