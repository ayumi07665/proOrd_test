/**
* IkkatsuReferDelegate  一括照会画面 処理実行クラス
*
*	作成日    2003/03/31
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理を実行するクラス。
*
*	[変更履歴]
*       2003/05/06（ＮＩＩ）蛭田 敬子
* 			・｢プレスコメント入力｣ 機能の追加。(エラーチェック追加)
* 		2003/07/01
* 			・副資材発注のとき、納品先名の取得 追加。
* 		2003/07/17（ＮＩＩ）岡田 夏美
* 			・可能数の計算からリザーブを削除。
* 			・プレス納期の設定方法変更。
* 			・品番指定時、条件による抽出を行わない。
* 		2003/07/24（ＮＩＩ）岡田 夏美
* 			・発注履歴書き込み時、プレス発注分マイナスする前の副資材在庫数を取得
* 			  発注履歴・発注書データの副資材在庫数にセット
* 		2003/07/31（ＮＩＩ）岡田 夏美
* 			・提案数の計算方法変更 (小数点以下切り上げ(+0.99) → 四捨五入(+0.5))
* 			・可能数の計算方法変更 ((在庫数 + 棚上積送残 + プレス未入庫計) -（注残 + 未引当) … + プレス未入庫計追加)
* 		2003/08/06（ＮＩＩ）岡田 夏美 
* 			・発注指示、伝票発行時、在庫テーブルの更新追加
* 		2003/08/19（ＮＩＩ）岡田 夏美 
* 			・抽出条件：(生産リードタイム * 平均売上)が(可能数 + プレス未入庫計)以下だったら表示しない
*			  から、+ プレス未入庫計 を削除   …7/31の修正で、可能数の計算にプレス未入庫計が加えられたため
* 		2003/08/19（ＮＩＩ）岡田 夏美 
* 			・抽出条件：指示画面で「非対象データ表示」が選択された場合、
* 			  指定された範囲に応じて、データを抽出する機能を追加
* 		2003/12/16（ＮＩＩ）森 
* 			・副資材発注に関する情報の変更
* 		2005/05/24（ＮＩＩ）蛭田
* 			・Ｋ社の場合、納期（当日日付＋７日）に修正
* 		2006/08/08 （ＮＩＩ）岡田 夏美
* 			・提案数算出用データ取得時に、テーブルを結合して一度で取得するように変更
* 		 2006/12/19（ＮＩＩ）田中
* 			・ＦＸ社　納期デフォルト値変更
*/

package com.jds.prodord.reference.ikkatsurefer;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

import com.jds.prodord.common.*;
import com.jds.prodord.indicate.teiansuurefer.*;

public class IkkatsuReferDelegate {

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	public static final String HACSAKI_NOT_EXIST = "HACSAKI_NOT_EXIST";


	/**
	 * 検索
	 * 
	 */
	public IkkatsuReferVO[] doFind(TeiansuuReferVO queryVO)throws SQLException{

ProcessLogUtils prcTime = new ProcessLogUtils("提案数照会", 1);
prcTime.start();
int total = 0;

		IkkatsuReferDAO fmDao = null;
		ArrayList finded_Arr = new ArrayList();
		
		IkkatsuReferVO[] finded = null;
		
		//プレス納期・副資材納期の算出(全行共通)
		int prsNki = this.calculatePrsNki(queryVO.getDaiKaiSkbCod());
		int fukNki = this.calculateFukNki(queryVO.getDaiKaiSkbCod());
		
		try{

			fmDao = new IkkatsuReferDAO();

ProcessLogUtils selectTime = new ProcessLogUtils("提案数算出用データ検索", 2);
selectTime.start();

			//提案数データ検索
			finded = fmDao.findTeiansuuData(queryVO);
			
total = finded.length;			
selectTime.outTotal(total);
selectTime.end();

ProcessLogUtils computeTime = new ProcessLogUtils("導出項目のセット・抽出", 2);
computeTime.start();

			for(int i = 0; i<finded.length; i++){
				/*------------導出項目のセット------------*/
				//可能数
				this.computeKanouSuu(finded[i]);
				//不足在庫数
				this.computeFskZaiSuu(finded[i]);
				//提案数
				this.computeTeianSuu(finded[i]);

				finded_Arr.add(finded[i]);//ArrayListに格納
			}

			//品番選択なしだったら 2003/07/17 add
			if(queryVO.getKigBng_arr().size() == 0){

				//条件に合うデータを抽出  2003/09/19 対象データ区分追加
				this.extractData(finded_Arr,queryVO.getTaisyoDataKbn(),queryVO.getTaisyoRange());

			}
			finded = (IkkatsuReferVO[])finded_Arr.toArray(new IkkatsuReferVO[finded_Arr.size()]);//配列に戻す

computeTime.end();

ProcessLogUtils otherTime = new ProcessLogUtils("その他データセット", 2);
otherTime.start();

			OrderUtils orderUtils = new OrderUtils(fmDao);
			for(int i = 0; i<finded.length; i++){
				//基準行フラグ ※画面制御のためのフラグとして使用する
				finded[i].setBasedRowFlg("1");
				//副資材発注先・名称、分類コード取得
				orderUtils.findFukSziHacSaki(finded[i]);					
				//発注履歴テーブル検索（プレス）
				fmDao.findHac02Prs(finded[i]);
				//発注履歴テーブル検索（副資材）
				fmDao.findHac02Fuk(finded[i]);
				
				if(finded[i].getExsitZai01() && finded[i].getExsitMst05() && finded[i].getExsitMst04()){
					//プレス発注数
					finded[i].setPrsHacSuu(finded[i].getTeianSuu());
					
					//副資材発注数
				// 2003.12.16　upd　副資材発注に関する情報の変更　st ****
					if(!finded[i].getPrsHacSuu().equals("") && !finded[i].getFukZaiSuu().equals("") && !finded[i].getFukMnyKei().equals("")){
						//プレス提案数 >= 副資材在庫数
						//プレス発注数 >= 副資材在庫数　+　副資材未入庫計 (2003.12.16 upd)
						if(Integer.parseInt(finded[i].getPrsHacSuu()) >= Integer.parseInt(finded[i].getFukZaiSuu()) + Integer.parseInt(finded[i].getFukMnyKei())){
							finded[i].setFukHacSuu(finded[i].getFukHacSuu1());//前回副資材発注数をセット
							finded[i].setCheck_fuk1(true);
						}else{
							finded[i].setFukHacSuu("0");	
						}
						//プレス提案数 * 2 >= 副資材在庫数 
						//プレス発注数 * 2 >= 副資材在庫数 +　副資材未入庫計(2003.12.16 upd)
						int prsHacSuu_twice = Integer.parseInt(finded[i].getPrsHacSuu()) * 2;
						if(prsHacSuu_twice >= Integer.parseInt(finded[i].getFukZaiSuu()) + Integer.parseInt(finded[i].getFukMnyKei())){
							finded[i].setCheck_flg(true);
						}
				// 2003/12/16　upd　副資材発注に関する情報の変更　ed ****
					}
					
				}
				//副資材のチェックサイン
				if(!finded[i].getTeianSuu().equals(""))
					finded[i].setCheck_sgn(this.getFukSziSgnString(finded[i].getCheck_fuk1(),finded[i].getCheck_flg()));
				
				//プレス納期・副資材納期
				finded[i].setPrsNki(prsNki);
				finded[i].setFukNki(fukNki);
				
				//在庫日数
				if(finded[i].getExsitZai01()){
					this.computeZaiNiSuu(finded[i]);
				}
				//直送区分
				finded[i].setChoksoKbn("");
				if(queryVO.getCommand().equals(IkkatsuReferForm.COMMAND_JIDOUHACHU)){
					finded[i].setCheck_prs1(true);
					finded[i].setCheck_prs2(true);
					finded[i].setCheck_prsHacSuu(true);
				}
			}
			
otherTime.end();

		}finally{
			if(fmDao != null){
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}

prcTime.log("TOTAL：" + finded.length + "/" + total + "件");
prcTime.end();
		return finded;
	}
	

	/**
	 * 発注指示
	 * 
	 */
	public IkkatsuReferResult[] doHacShiji(IkkatsuReferVO[] fmVOs)throws SQLException{
		IkkatsuReferDAO fmDao = null;
	
		IkkatsuReferResult[] results = new IkkatsuReferResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new IkkatsuReferDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始


			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;			
				//発注先存在チェック
			    if(!CheckCommon.hasHacCod(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getPrsMkrCod())){			    	
				    results[i] = new IkkatsuReferResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
				    error = true;
					continue;
			    }				
			}
		 

			//エラーのレコードがなければinsert実行
			if(!error){
				//処理回数の採番
				int[] number = null;
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] != null){
						number = NumberingUtils.createNumbers(fmVOs[i].getQueryKaiSkbCod(),"",1,"");
						break;
					}
				}
				OrderUtils orderUtils = new OrderUtils(fmDao);
				java.text.DecimalFormat df = new java.text.DecimalFormat("000000");
				String syrsuu = df.format(number[0]);
				int seqno = 0;
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;

					seqno++;//fmVOs[i] == nullではなかったら
					try{
						//プレス発注分マイナスする前の副資材在庫数を取得 2003/07/24 add
						String fukSziSuu =
							orderUtils.findHin12(
								fmVOs[i].getDaiKaiSkbCod(),
								fmVOs[i].getKaiSkbCod(),
								fmVOs[i].getKigBng(),
								"1",
								fmVOs[i].getFukSziCod(),
								fmVOs[i].getSmpKbn());
						fmVOs[i].setFukSziSuu((fukSziSuu == null)? "0" : fukSziSuu);

						String syrzmisgn = "0";
						//発注履歴テーブルに書き込み
						fmDao.insertHac02(fmVOs[i],upddte,updjkk,syrsuu,StringUtils.lpad(seqno+"",3,"0"),syrzmisgn);

						//副資材在庫の存在チェック・登録(複数)
						orderUtils.insertHin12List(
							fmVOs[i].getDaiKaiSkbCod(),
							fmVOs[i].getKaiSkbCod(),
							fmVOs[i].getKigBng(),
							fmVOs[i].getSmpKbn(),
							fmVOs[i].getFukSziCod_arr(),
							fmVOs[i].getBunCod_arr(),
							upddte,
							updjkk);
						//副資材在庫をプレス発注分マイナスする
						orderUtils.updateHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),
											   fmVOs[i].getKigBng(),fmVOs[i].getSmpKbn(),(Integer.parseInt(fmVOs[i].getPrsHacSuu()) * -1),upddte,updjkk);
						//在庫テーブル更新 2003/08/06 add
						orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.PRSHAC);
					
					}catch(SQLException sqle2){
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						error = true;
						results[i] = new IkkatsuReferResult(fmVOs[i],false,"");
						continue;
					}
					//正常に成功
					results[i] = new IkkatsuReferResult(fmVOs[i],true,"");
				} 
			}
			//エラーならロールバック
			if(error){
				fmDao.rollback();
				endTransaction = true;
			//エラーがなければコミット
			}else{
				fmDao.commit();
				endTransaction = true;
				
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
					//発注済サインに"1"（発注済）をセット
					fmVOs[i].setHacZmiSgn(IkkatsuReferDAO.HACZMI);
					//チェックボックスをクリア
					fmVOs[i].setCheck_prs1(false);
					fmVOs[i].setCheck_prs2(false);
					fmVOs[i].setCheck_prsHacSuu(false);
					//プレス発注情報をセットしなおす
					this.setPrsHacJoho(fmVOs[i]);

					fmVOs[i].setUpdDte(upddte);
					fmVOs[i].setUpdJkk(updjkk);
				}
			}
		}finally{
			if(fmDao != null){
				if(!endTransaction ){//exception でcommit or rollback できなかったときは ここで rollback
					try{
						fmDao.rollback();
					}catch(SQLException sqle3){
						SystemException se = new SystemException(sqle3);
						se.printStackTrace();
					}
				}
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return results;
		
	}

	
	/**
	 * 伝票発行
	 * 
	 */
	public IkkatsuReferResult[] doDnpHakkou(IkkatsuReferVO[] fmVOs)throws SQLException{
		IkkatsuReferDAO fmDao = null;
	
		IkkatsuReferResult[] results = new IkkatsuReferResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new IkkatsuReferDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始


			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;
			int[] number = null;
					
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
					
				//発注先存在チェック
			    if(!CheckCommon.hasHacCod(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getPrsMkrCod())){			    	
				    results[i] = new IkkatsuReferResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
				    error = true;
					continue;
			    }
			}

			//エラーのレコードがなければinsert実行
			if(!error){
				//処理回数の採番
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] != null){
						number = NumberingUtils.createNumbers(fmVOs[i].getQueryKaiSkbCod(),"",1,"");
						break;
					}
				}
				OrderUtils orderUtils = new OrderUtils(fmDao);
				java.text.DecimalFormat df = new java.text.DecimalFormat("000000");
				String syrsuu = df.format(number[0]);
				int seqno = 0;
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
						
					seqno++;//fmVOs[i] == nullではなかったらSEQNO++
					try{
						//プレス発注分マイナスする前の副資材在庫数を取得 2003/07/24 add
						String fukSziSuu =
							orderUtils.findHin12(
								fmVOs[i].getDaiKaiSkbCod(),
								fmVOs[i].getKaiSkbCod(),
								fmVOs[i].getKigBng(),
								"1",
								fmVOs[i].getFukSziCod(),
								fmVOs[i].getSmpKbn());
						fmVOs[i].setFukSziSuu((fukSziSuu == null)? "0" : fukSziSuu);

						String syrzmisgn = "1";
						String _seqno = StringUtils.lpad(seqno+"",3,"0");
						//発注履歴テーブルに書き込み
						fmDao.insertHac02(fmVOs[i],upddte,updjkk,syrsuu,_seqno,syrzmisgn);
						//発注書データ書き込み
						orderUtils.insertHac01(
							fmVOs[i].getDaiKaiSkbCod(),//代表会社
							fmVOs[i].getKaiSkbCod(),//会社
							String.valueOf(upddte),//発注書日付
							syrsuu,//処理回数
							_seqno,//SEQNO
							0,//データ交換フラグ
							upddte,
							updjkk);

						//副資材在庫の存在チェック・登録(複数)
						orderUtils.insertHin12List(
							fmVOs[i].getDaiKaiSkbCod(),
							fmVOs[i].getKaiSkbCod(),
							fmVOs[i].getKigBng(),
							fmVOs[i].getSmpKbn(),
							fmVOs[i].getFukSziCod_arr(),
							fmVOs[i].getBunCod_arr(),
							upddte,
							updjkk);
						//副資材在庫をプレス発注分マイナスする
						orderUtils.updateHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),
											   fmVOs[i].getKigBng(),fmVOs[i].getSmpKbn(),(Integer.parseInt(fmVOs[i].getPrsHacSuu()) * -1),upddte,updjkk);
						//在庫テーブル更新 2003/08/06 add
						orderUtils.updateZai01(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKigBng(),
											   Integer.parseInt(fmVOs[i].getPrsHacSuu()),upddte,updjkk,OrderUtils.PRSHAC);
											   
					}catch(SQLException sqle2){
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						error = true;
						results[i] = new IkkatsuReferResult(fmVOs[i],false,"");
						continue;
					}
					//正常に成功
					results[i] = new IkkatsuReferResult(fmVOs[i],true,"");
				} 
			}
			//エラーならロールバック
			if(error){
				fmDao.rollback();
				endTransaction = true;
			//エラーがなければコミット
			}else{
				fmDao.commit();
				endTransaction = true;
				boolean flg = false;
				for(int i = 0;i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
					if(fmVOs[i] != null && !flg){//resultsに処理回数をセット
						results[i].setSyrSuu(number[0]+"");
						flg = true;
					}
					fmVOs[i].setHacZmiSgn(IkkatsuReferDAO.SYRZMI);//発注済サインに"2"（出力済）をセット
					//プレス発注情報をセットしなおす
					this.setPrsHacJoho(fmVOs[i]);
					//チェックボックスをクリア
					fmVOs[i].setCheck_prs1(false);
					fmVOs[i].setCheck_prs2(false);
					fmVOs[i].setCheck_prsHacSuu(false);
				}
			}
		}finally{
			if(fmDao != null){
				if(!endTransaction ){//exception でcommit or rollback できなかったときは ここで rollback
					try{
						fmDao.rollback();
					}catch(SQLException sqle3){
						SystemException se = new SystemException(sqle3);
						se.printStackTrace();
					}
				}
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return results;
		
	}
	
	/**
	 * 副資材発注
	 * 
	 */
	public IkkatsuReferVO[] doFukHachu(IkkatsuReferVO[] fmVOs)throws SQLException{
						
		IkkatsuReferDAO fmDao = null;
		ArrayList finded_Arr = new ArrayList();
		IkkatsuReferVO[] finded = null;
		try{
			fmDao = new IkkatsuReferDAO();
			for(int i = 0; i < fmVOs.length; i++){
				if(fmVOs[i] == null)
						continue;
				//副資材情報取得
				finded_Arr.addAll(makeFukDataList(fmVOs[i]));
			}
			finded = (IkkatsuReferVO[])finded_Arr.toArray(new IkkatsuReferVO[finded_Arr.size()]);//配列に戻す
			for(int i = 0; i < finded.length; i++){
				//発注履歴テーブル検索（副資材）
				fmDao.findHac02Fuk(finded[i]);

				//2003/07/01 
				fmDao.findMst03(finded[i],IkkatsuReferDAO.fukHacMei);//発注先名称取得
				fmDao.findMst03(finded[i],IkkatsuReferDAO.nhnMei);//納品先名称取得
			}

		}finally{
			if(fmDao != null){
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					SystemException se = new SystemException(sqle2);
					se.printStackTrace();
				}
			}
		}
		return (IkkatsuReferVO[])finded_Arr.toArray(new IkkatsuReferVO[finded_Arr.size()]);//配列に戻す
	}


	/**
	 * 取得してある副資材情報を元に副資材情報リストを生成します。
	 * @param fmVO
	 * @return 副資材情報リスト
	 */
	private ArrayList makeFukDataList(IkkatsuReferVO fmVO) {
	
		ArrayList finded_arr = new ArrayList();
		ArrayList fukSziCod_arr = fmVO.getFukSziCod_arr(); //副資材コードarr
		ArrayList fukSziHacSaki_arr = fmVO.getFukSziHacSaki_arr(); //仕入先コードarr
		ArrayList bunCod_arr = fmVO.getBunCod_arr(); //分類コードarr
		ArrayList fukSziNm_arr = fmVO.getFukSziNm_arr(); //副資材名称arr
	
		finded_arr.add(fmVO); //検索済みのもの(基準行フラグ=1にあたるもの)をadd
	
		//ループを1から開始
		for (int i = 1; i < fukSziCod_arr.size(); i++) {
			IkkatsuReferVO vo = new IkkatsuReferVO();
			vo.setDaiKaiSkbCod(fmVO.getDaiKaiSkbCod());
			vo.setKaiSkbCod(fmVO.getKaiSkbCod());
			vo.setUsrId(fmVO.getUsrId());
			vo.setKigBng(fmVO.getKigBng());
			vo.setFukSziHacSaki(String.valueOf(fukSziHacSaki_arr.get(i))); //副資材発注先
			vo.setFukSziNm(String.valueOf(fukSziNm_arr.get(i)));           //副資材名称
			vo.setBunCod(String.valueOf(bunCod_arr.get(i)));               //分類コード
			vo.setFukSziCod(String.valueOf(fukSziCod_arr.get(i)));         //副資材コード
			vo.setBasedRowFlg("2");                                        //基準行フラグ
	
			finded_arr.add(vo);
		}
	
		return finded_arr;
	}
	
	
//---------------------------------------------------------------------------------------------
	
	/** 可能数の計算 */
	public void computeKanouSuu(IkkatsuReferVO fmVO){
		if(!fmVO.getExsitZai01())
			return;

		int azisuu    = Integer.parseInt(fmVO.getAziSuu());
		int tnasekzan = Integer.parseInt(fmVO.getTnaSekZan());
//		int rsvsum    = Integer.parseInt(fmVO.getRsvSum());//2003/07/16 削除
		int juczan    = Integer.parseInt(fmVO.getJucZan());
		int mhiksuu   = Integer.parseInt(fmVO.getMhikSuu());
		int prsmnykei = Integer.parseInt(fmVO.getPrsMnyKei());//2003/07/31 add
		
		//（(在庫数 + 棚上積送残 + プレス未入庫計) -（注残 + 未引当)） をVOにセット
		int kanousuu = (azisuu + tnasekzan + prsmnykei) - (juczan + mhiksuu);
		fmVO.setKanouSuu(kanousuu+"");
		
	}
	/** 不足在庫数の計算 */
	public void computeFskZaiSuu(IkkatsuReferVO fmVO){
		if(!fmVO.getExsitZai01() || !fmVO.getExsitMst04())
			return;
			
		int hackankaku = fmVO.getKsnNisuu();
		int avguri     = Integer.parseInt(fmVO.getAvgUri());
		int kanousuu   = Integer.parseInt(fmVO.getKanouSuu());

		//（（平均売上 * 発注間隔(在庫日数)） - （可能数）をVOにセット
		int fskzaisuu = (avguri * hackankaku) - (kanousuu);
		fmVO.setFskZaiSuu(fskzaisuu+"");
		
	}

	/** 提案数の計算 */
	public void computeTeianSuu(IkkatsuReferVO fmVO){
		if(!fmVO.getExsitZai01() || !fmVO.getExsitMst05() || !fmVO.getExsitMst04())
			return;

		double fskzaisuu = Double.parseDouble(fmVO.getFskZaiSuu());
		double mrmsuu    = (double)fmVO.getMrmSuu();
		double d         = 0.5;
		
		int i = 0;
		
		/*不足在庫／まるめ数量 + 0.5 を 小数点以下切り捨てした結果、
		  ０になってしまった場合は１とする 2003/08/05 add  */
		if(fskzaisuu > 0){
			if((int)(fskzaisuu / mrmsuu + d) == 0)
				i = 1;
			else
				i = (int)(fskzaisuu / mrmsuu + d);
		}
		
		//（不足在庫／まるめ数量 + 0.5 を 小数点以下切り捨て）× まるめ数量 をVOにセット
		int teiansuu = (int)(i * mrmsuu);
		fmVO.setTeianSuu(teiansuu+"");
		
	}
	
	/** 在庫日数の計算 */
	public void computeZaiNiSuu(IkkatsuReferVO fmVO){
		if(!fmVO.getExsitZai01())
			return;

		int kanousuu  = (fmVO.getKanouSuu().equals(""))? 0 : Integer.parseInt(fmVO.getKanouSuu());
		int avguri    = (fmVO.getAvgUri().equals(""))? 0 : Integer.parseInt(fmVO.getAvgUri());
		
		if(avguri == 0){
			fmVO.setZaiNisuu("0");
			return;	
		}else if(kanousuu < 0){
			fmVO.setZaiNisuu("0");
			return;
		}
		
		//（可能数 ／ 平均売上）小数第２位を四捨五入 をVOにセット
		BigDecimal kanousuu_b = new BigDecimal(kanousuu);
		BigDecimal avguri_b = new BigDecimal(avguri);
		BigDecimal zainisuu = kanousuu_b.divide(avguri_b, 1, BigDecimal.ROUND_HALF_UP);//小数第２位を四捨五入

		fmVO.setZaiNisuu(zainisuu+"");		
	}

	/** データの抽出 *///2003/09/19 対象データ区分追加 2003/10/09 非対象選択時の対象範囲選択機能追加
	public void extractData(ArrayList arr, int taisyoDataKbn, String taisyoRange){
//System.out.println("----------------------------------------");
			
		for(int i = 0; i<arr.size(); i++){
			IkkatsuReferVO fmVO = (IkkatsuReferVO)arr.get(i);
			int ssnredtim = fmVO.getSsnRedTim();
			int avguri    = (fmVO.getAvgUri().equals(""))? 0 : Integer.parseInt(fmVO.getAvgUri());
			int kanousuu  = (fmVO.getKanouSuu().equals(""))? 0 : Integer.parseInt(fmVO.getKanouSuu());
//			int prsmnykei = (fmVO.getPrsMnyKei().equals(""))? 0 : Integer.parseInt(fmVO.getPrsMnyKei()); 2003/08/19 削除

			boolean remove_flg = false;	
			//（生産リードタイム * 平均売上 <= 可能数） のものを取り除く
			if((ssnredtim * avguri) <= kanousuu){
				remove_flg = true;
//System.out.println("生産リードタイム * 平均売上 <= 可能数 : " + fmVO.getKigBng());
			//提案数 = 0 のものを取り除く
			}else if(fmVO.getTeianSuu().equals("0")){
//System.out.println("提案数 = 0 : " + fmVO.getKigBng());
				remove_flg = true;
			//発注済サイン <> 未発注ものを取り除く
			}else if(fmVO.getHacZmiSgn() != IkkatsuReferDAO.MIHACHU){
//System.out.println("発注済サイン != 未発注 : " + fmVO.getKigBng());
				remove_flg = true;
			}
			if(taisyoDataKbn == TeiansuuReferVO.TAISYO_ONLY){
				if(remove_flg){//非対象データは削除
					arr.remove(i);
					i--;
				}
			}else if(taisyoDataKbn == TeiansuuReferVO.HITAISYO_ONLY){//2003/10/09 修正
				if(!remove_flg){//対象データを削除、非対象データを残す
					arr.remove(i);
					i--;
					continue;
				}
				int azisuu    = Integer.parseInt((fmVO.getAziSuu().equals(""))? "0" : fmVO.getAziSuu());
				switch(Integer.parseInt(taisyoRange)){
					case 1:
						remove_flg = (azisuu == 0);//範囲内ならば true : 範囲外ならば false
						break;
					case 2:
						remove_flg = (0 < azisuu && azisuu <= 10);
						break;
					case 3:
						remove_flg = (10 < azisuu && azisuu <= 30);
						break;
					case 4:
						remove_flg = (30 < azisuu && azisuu <= 50);
						break;
					case 5:
						remove_flg = (50 < azisuu && azisuu <= 100);
						break;			
				}
				if(!remove_flg){//非対象の中でも範囲外のものを削除
					arr.remove(i);
					i--;
				}	
			}
		}
//System.out.println("----------------------------------------");
	}
	
	/** プレス納期の算出 */
	public int calculatePrsNki(String daiKaiSkbCod){
		int prsnki = 0;
		DateUtils dateUtils = new DateUtils();
		int youbi = dateUtils.getDayOfWeek();

		//Ｋ社の場合 2005/05/24 add  LAN（BAN)社の場合2013/06/03 add
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_K) ||
				daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			dateUtils.addToDate(Calendar.DATE, 7);//７日後
		//FX社の場合 2006/12/19
		}else if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)){
			dateUtils.addToDate(Calendar.DATE, 14);//１４日後
		}else{
			//月曜日だったら
			if(youbi == Calendar.MONDAY){
				dateUtils.addToDate(Calendar.DATE, 4);//４日後
			//火～金曜日だったら
			}else if(youbi != Calendar.SUNDAY && youbi != Calendar.SATURDAY){
				dateUtils.addToDate(Calendar.DATE, 6);//６日後
			}
		}
 		prsnki = dateUtils.getDate6Int();
		return prsnki;
	}
	
	/** 副資材納期の算出 */
	public int calculateFukNki(String daiKaiSkbCod){
		int fuknki = 0;
		DateUtils dateUtils = new DateUtils();
		int youbi = dateUtils.getDayOfWeek();

		//FX社の場合 2006/12/19　LAN（BAN)社の場合2013/06/03 add
		if(daiKaiSkbCod.equals(CommonConst.KAICOD_FX)||
				daiKaiSkbCod.equals(CommonConst.KAICOD_BAN)){
			dateUtils.addToDate(Calendar.DATE, 7);//７日後
		}else{
			//月曜日だったら
			if(youbi == Calendar.MONDAY){
				dateUtils.addToDate(Calendar.DATE, 7);//７日後
			//火～金曜日だったら
			}else if(youbi != Calendar.SUNDAY && youbi != Calendar.SATURDAY){
				dateUtils.addToDate(Calendar.DATE, 6);//６日後
			}
		}
		fuknki = dateUtils.getDate6Int();
		return fuknki;
	}
	
	
	/** プレス発注情報をセットしなおす */
	public void setPrsHacJoho(IkkatsuReferVO fmVO){
		fmVO.setPrsHacSuu2(new String(fmVO.getPrsHacSuu1()));
		fmVO.setPrsHacNyo2(new String(fmVO.getPrsHacNyo1()));
		if(!fmVO.getPrsHacNki1().equals(""))
			fmVO.setPrsHacNki2(new String(fmVO.getPrsHacNki1()));
		else
			fmVO.setPrsHacNki2("");
		fmVO.setPrsKbn2(new String(fmVO.getPrsKbn1()));
		
		fmVO.setPrsHacSuu1(fmVO.getPrsHacSuu());
		fmVO.setPrsHacNyo1("0");
		fmVO.setPrsHacNki1(fmVO.getPrsNki()+"");
		fmVO.setPrsKbn1(DataFormatUtils.getKbnCod(DataFormatUtils.KYUHU));
		
	}
	
	/** 副資材チェックサインの表示文字列を返す **/
	private String getFukSziSgnString(boolean check, boolean flg){
		String fukszisgn = "";
		if(flg){
			if(check)
				fukszisgn = IkkatsuReferForm.HACHU_HITYO;
			else
				fukszisgn = IkkatsuReferForm.JIKAI_HACHU;	
		}else{
			fukszisgn = IkkatsuReferForm.ZAIKO_JUBUN;			
		}
		return fukszisgn;
	}
	
	/** 2003/05/06 ****************************************************************
	 * 		エラーチェック														  *
	 ******************************************************************************/
	public IkkatsuReferResult[] doCheck(IkkatsuReferVO[] fmVOs)throws SQLException{
		IkkatsuReferDAO fmDao = null;
		IkkatsuReferResult[] results = new IkkatsuReferResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new IkkatsuReferDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始
			
			boolean error = false;
					
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
					
				//発注先存在チェック
			    if(!fmDao.hasHacSakiMei(fmVOs[i])){
				    results[i] = new IkkatsuReferResult(fmVOs[i],false,HACSAKI_NOT_EXIST);
				    error = true;
					continue;
			    }else{//エラーがなければ
			    	//正常に成功
					results[i] = new IkkatsuReferResult(fmVOs[i],true,"");
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
		return results;
		
	}

}

