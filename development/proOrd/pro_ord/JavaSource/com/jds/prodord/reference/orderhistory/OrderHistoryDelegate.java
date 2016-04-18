/**
* OrderHistoryDelegate  発注履歴照会画面 処理実行クラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）蛭田 敬子
* 処理概要    処理を実行するクラス。
*
*	[変更履歴]
*        2003/06/26（ＮＩＩ）岡田 夏美
* 			・訂正伝票発行機能 修正（チェックされた行のみではなく、同一の発注書番号を持つ全ての行について、
* 			  発注書データが存在するかどうかをチェックし、存在しない場合はINSERTする）
* 		 2003/07/11（ＮＩＩ）岡田 夏美
* 			・分類コード=副資材(1 or 2)のとき、直送区分＝'0'固定追加。
* 		 2003/08/06（ＮＩＩ）岡田 夏美 
* 			・訂正送信、訂正伝票発行時、在庫テーブルの更新追加
* 		 2003/09/29（ＮＩＩ）岡田 夏美 
* 			・FTBHIN12.SMPKBN追加に伴って、HIN12とZAI01の在庫数調整メソッド追加
* 		 2003/10/22（ＮＩＩ）岡田 夏美
* 			・訂正送信は未出力のみ → 訂正送信は未出力・出力済両方を対象とするように修正
*		 2004/01/16,20（ＮＩＩ）森
*			・訂正伝票発行時に発注数が変更されていたらコメントの自動表示 
*		 2004/01/22	（ＮＩＩ）森
*			・入庫数を取消できるように修正	
* 		 2004/02/27 （ＮＩＩ）森
* 			・完納サインを更新するように修正		
* 		 2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
*		 2004/08/02（ＮＩＩ）落合
*			・品番マスターでチェックをかけずに、発注履歴テーブルを参照するため、
*			  品番存在チェックをコメントアウト
*			・納期変更に伴う入庫日更新を追加(訂正送信・訂正伝票発行)
* 		 2005/05/25（ＮＩＩ）蛭田
* 			・納品先名が'ＪＡＲＥＤ'の場合、直送区分"0"の修正
* 		 2005/09/15（ＮＩＩ）蛭田
* 			・訂正送信の場合、履歴更新日を発注履歴テーブル・発注書データに更新
* 		 2005/09/27（ＮＩＩ）蛭田
* 			・訂正回数MAX９回に変更
* 		 2006/01/25（ＮＩＩ）田端
* 			・ダウンロード項目に注残数追加
* 		 2006/08/08 （ＮＩＩ）岡田 夏美
* 			・発注履歴データ取得時に、テーブルを結合して一度で取得するように変更
*		 200802/25（ＮＩＩ）田中
*  			・完納済みはHAC01.データ交換FLGに'1'をセット
*		 2008/06/02（ＮＩＩ）田中
* 			・ＶＡＰ社　単価・金額の修正
**/

package com.jds.prodord.reference.orderhistory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.jds.prodord.common.CheckCommon;
import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.DateUtils;
import com.jds.prodord.common.OrderUtils;
import com.jds.prodord.common.ProcessLogUtils;
import com.jds.prodord.common.StringUtils;
import com.jds.prodord.common.SystemException;

public class OrderHistoryDelegate {

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	public static final String HACSAKI_NOT_EXIST = "HACSAKI_NOT_EXIST";
	public static final String KIGBNG_NOT_EXIST = "KIGBNG_NOT_EXIST";
	public static final String HACBNG_EXIST = "HACBNG_EXIST";
	public static final String EXCEED_REINPUT = "EXCEED_REINPUT";

	/*************************************************************************************************
	 * 		検索(照会)
	 * 
	 ************************************************************************************************/
	public OrderHistoryResult doFindForRefer(OrderHistoryVO queryVO)throws SQLException{

ProcessLogUtils prcTime = new ProcessLogUtils("発注履歴照会", 1);
prcTime.start();
		
		OrderHistoryDAO fmDao = null;
		OrderHistoryVO[] finded = new OrderHistoryVO[0];
		OrderHistoryResult result = null;
		
		try{
			fmDao = new OrderHistoryDAO();

ProcessLogUtils countTime = new ProcessLogUtils("発注履歴件数取得", 2);
countTime.start();
			int count = fmDao.getHac02Count(queryVO);
countTime.outTotal(count);
countTime.end();
			if(count > OrderHistoryMidForm.MAX_REFER_COUNT){
				result = new OrderHistoryResult(null,false,EXCEED_REINPUT);
				result.setCount(count);
			}else if(count == 0){
				result = new OrderHistoryResult(null,true,"");
				result.setResultList(finded);//長さ0の配列をResultに詰める
			}else{

ProcessLogUtils selectTime = new ProcessLogUtils("発注履歴テーブル検索", 2);
selectTime.start();

				//発注履歴テーブル検索
				finded = fmDao.findHac02Data(queryVO);
				result = new OrderHistoryResult(null,true,"");
				result.setResultList(finded);//結果をResultに詰める

selectTime.end();

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
prcTime.outTotal(finded.length);
prcTime.end();
		return result;
	}
	
	/*************************************************************************************************
	 * 		検索(ダウンロード)
	 * 
	 ************************************************************************************************/
	public OrderHistoryResult doFindForDownload(OrderHistoryVO queryVO)throws SQLException{

ProcessLogUtils prcTime = new ProcessLogUtils("発注履歴照会(ﾀﾞｳﾝﾛｰﾄﾞ)", 1);
prcTime.start();
		
		OrderHistoryDAO fmDao = null;
		OrderHistoryVO[] finded = new OrderHistoryVO[0];
		OrderHistoryResult result = null;
		
		try{
			fmDao = new OrderHistoryDAO();

ProcessLogUtils selectTime = new ProcessLogUtils("発注履歴テーブル検索", 2);
selectTime.start();

			//発注履歴テーブル検索
			finded = fmDao.findHac02Data(queryVO);
			result = new OrderHistoryResult(null,true,"");
			result.setResultList(finded);//結果をResultに詰める

selectTime.end();


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
prcTime.outTotal(finded.length);
prcTime.end();
		return result;
	}

	/**********************************************************************************************
	 * 		実行
	 * 
	 *********************************************************************************************/
	public OrderHistoryResult doJikkou(OrderHistoryVO fmVO)throws SQLException{

		OrderHistoryDAO fmDao = null;
		OrderHistoryResult result = new OrderHistoryResult(fmVO,true,"");

		boolean endTransaction = false;
		try{
			fmDao = new OrderHistoryDAO();
			
			boolean error = false;

			//発注先存在チェック
			if(!fmVO.getHacCod_H().equals("")){
			    if(!CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),fmVO.getHacCod_H())){			    	
				    result = new OrderHistoryResult(fmVO,false,HACSAKI_NOT_EXIST);
				    error = true;
			    }
			}  
			//エラーがなければ
			if(!error){
				//正常に成功
				result = new OrderHistoryResult(fmVO,true,"");
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
		return result;
		
	}


	/********************************************************************************************
	 * 		訂正送信
	 * 
	 *******************************************************************************************/
	public OrderHistoryResult[] doTeiseiSousin(OrderHistoryVO[] fmVOs)throws SQLException{

ProcessLogUtils prcTime = new ProcessLogUtils("訂正送信", 1);
prcTime.start();

		OrderHistoryDAO fmDao = null;
		OrderHistoryResult[] results = null;
		
		boolean endTransaction = false;

		try{
			fmDao = new OrderHistoryDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始
			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;

			//まず検索
			results = this.doSelectForUpdate(fmDao,fmVOs);
			int count = 0;
			for(int i = 0; i < results.length;i++){
				if(results[i] == null)
					continue;
				count++;
				if(!results[i].isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return results;
				}
			}

			//エラーのレコードがなければupdate実行
			if(!error){
				HashMap keyMap = new HashMap();
ProcessLogUtils updTime = new ProcessLogUtils("発注履歴関連データ更新", 2);
updTime.start();
updTime.outTotal(count);

				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0;i < fmVOs.length;i++){
					
					if(fmVOs[i] == null)
						continue;

					try{
						//HIN12とZAI01の在庫数の調整をする 2003/09/29 add
						adjustZaiSuu(fmVOs[i],fmDao,upddte,updjkk);
						//発注数を変更した場合変更内容をコメントへ　2004/01/21 add 
						setComent(fmVOs[i]); 
						//bshCodから取得できるように修正 2005/05/25 add
						String cykKbn = getCykKbn(fmVOs[i].getNhnMeiKj(),fmVOs[i].getBunCod(),fmVOs[i].getBshCod());
						//入庫数取消ができるように修正　2004/01/22 add 
						setNyusu(fmVOs[i],cykKbn);
						//入庫日更新を追加 2004/08/02 add
						setNyukobi(fmVOs[i],cykKbn);

						/*-- 出力済の場合、履歴更新日のセット --------------------------------------------*/
						if(fmVOs[i].getSyrZmiSgn().equals("1")){

							String rrkTbl = DataFormatUtils.makeRrkTblStr(fmVOs[i].getRrkTbl(), upddte);
	
							//VOに変更した履歴更新日をセット
							fmVOs[i].setRrkTbl(rrkTbl);
	
							//訂正送信した更新履歴日を発注書番号分更新するためのKey			  	  
							if(!keyMap.containsKey(fmVOs[i].getUpdateKey()))
								keyMap.put(fmVOs[i].getUpdateKey(),fmVOs[i]);
						}
						/*-------------------------------------------------------------------------------*/
						
						//発注履歴テーブル更新
						fmDao.updateHac02(fmVOs[i],upddte,updjkk,fmVOs[i].getSyrZmiSgn(),cykKbn);
						
						if(fmVOs[i].getSyrZmiSgn().equals("1")){//出力済だったら
							//チェックされた行の発注書データが存在していたら
							if(fmDao.hasHac01(fmVOs[i])){
								//bshCodから取得できるように修正 2005/05/25 add
								fmDao.updateHac01(fmVOs[i],upddte,updjkk,getCykKbn(fmVOs[i].getNhnMeiKj(),fmVOs[i].getBunCod(),fmVOs[i].getBshCod()));
							}//発注書データが消えてしまっていたら
							else{
								
								//完納済みは送信しない　2008/02/19
								int dtaExcFlg = 0;
								if(fmVOs[i].getKnuSgn().equals("1")){
									dtaExcFlg = 1;
								}else{
									dtaExcFlg = 0;
								}

								//発注書データ書き込み
								orderUtils.insertHac01(
									fmVOs[i].getDaiKaiSkbCod(),//代表会社
									fmVOs[i].getKaiSkbCod(),//会社
									fmVOs[i].getHacSyoDte(),//発注書日付
									fmVOs[i].getSyrSuu(),//処理回数
									fmVOs[i].getSeqNo(),//SEQNO
									dtaExcFlg,//データ交換フラグ
									upddte,
									updjkk);
							}
						}
						
					}catch(Exception e){
						SystemException se = new SystemException(e);
						se.printStackTrace();
						error = true;
						results[i] = new OrderHistoryResult(fmVOs[i],false,"");
						break;
					}
					//正常に成功
					results[i] = new OrderHistoryResult(fmVOs[i],true,"");
					results[i].setKeyMap(keyMap);
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
						fmVOs[i].setUpdDte(upddte);
						fmVOs[i].setUpdJkk(updjkk);
						fmVOs[i].setHacSuuOld(fmVOs[i].getHacSuu());
						fmVOs[i].setSinKyuKbnOld(fmVOs[i].getSinKyuKbn());
						fmVOs[i].setCheck_Mid(false);  //2004.01.21 add
						fmVOs[i].setTan2Old(fmVOs[i].getTan2());
						fmVOs[i].setKinOld(fmVOs[i].getKin());

					}				
				}
updTime.end();

				//エラーなしでkeyMapありの場合
				if(!error && !keyMap.isEmpty()){
ProcessLogUtils rrkTblTime = new ProcessLogUtils("履歴更新日更新", 2);
rrkTblTime.start();

					//ﾁｪｯｸされた行の発注書番号分すべて履歴更新日を更新 2005/09/15 add
					try {
						Iterator keySet = keyMap.keySet().iterator();
						while (keySet.hasNext()) {
							String key = (String) keySet.next();
							OrderHistoryVO value = (OrderHistoryVO) keyMap.get(key);

							OrderHistoryVO queryVO = new OrderHistoryVO();
							//発注履歴テーブル更新
							fmDao.updateHac(queryVO.getPartOfQuery(key),value.getRrkTbl(),upddte,updjkk,OrderHistoryDAO.HAC02);
							//発注書データ更新
							fmDao.updateHac(queryVO.getPartOfQuery(key),value.getRrkTbl(),upddte,updjkk,OrderHistoryDAO.HAC01);
							//※発注書データが消えてる分はIndicatePrintの方で登録してくれる
						
						}
					} catch (Exception e) {
						SystemException se = new SystemException(e);
						se.printStackTrace();
						error = true;
						results[0] = new OrderHistoryResult(fmVOs[0],false,"");
					}

					//エラーならロールバック
					if(error){
						fmDao.rollback();
						endTransaction = true;
					//エラーがなければコミット
					}else{
						fmDao.commit();
						endTransaction = true;
					}
rrkTblTime.end();
				}
			}

		}finally{
			if(fmDao != null){
				try {
					if(!endTransaction)//exception でcommit or rollback できなかったときは ここで rollback
						fmDao.rollback();
	
					fmDao.close();
		
				} catch (SQLException e) {
					SystemException se = new SystemException(e);
					se.printStackTrace();
				}
			}
		}

prcTime.end();
		return results;
		
	}


	 /************************************************************************************************
	 * 		selectForUpdate  更新が呼ぶ
	 * 
	 ************************************************************************************************/
	private OrderHistoryResult[] doSelectForUpdate(OrderHistoryDAO fmDao,OrderHistoryVO[] fmVOs)throws SQLException{

		OrderHistoryResult[] results = new OrderHistoryResult[fmVOs.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null){
					continue;
				}

				int returnCode = fmDao.selectForUpdate(fmVOs[i]);
				switch(returnCode){
					case OrderHistoryDAO.NOT_MODIFIED:
						results[i] = new OrderHistoryResult(fmVOs[i],true,"");
						break;					
					case OrderHistoryDAO.MODIFIED:
					case OrderHistoryDAO.NOT_EXIST:
					case OrderHistoryDAO.LOGICAL_DELETE:
						results[i] = new OrderHistoryResult(fmVOs[i],false,ANOTHER_USER_UPDATE);
						break;
				}
			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return results;
	}
	

	/********************************************************************************************
	 * 		訂正伝票発行
	 * 
	 *******************************************************************************************/
	public OrderHistoryResult[] doDnpHakkou(OrderHistoryVO[] fmVOs)throws SQLException{

ProcessLogUtils prcTime = new ProcessLogUtils("訂正伝票発行", 1);
prcTime.start();

		OrderHistoryDAO fmDao = null;
	
		OrderHistoryResult[] results = new OrderHistoryResult[fmVOs.length];

		boolean endTransaction = false;
		try{
			fmDao = new OrderHistoryDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始

			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			boolean error = false;
			//まず検索
			results = this.doSelectForUpdate(fmDao,fmVOs);
			int count = 0;
			for(int i = 0; i < results.length;i++){
				if(results[i] == null)
					continue;
				count++;
				if(!results[i].isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return results;
				}
			}

			//エラーのレコードがなければupdate&insert実行
			if(!error){
ProcessLogUtils updTime = new ProcessLogUtils("発注履歴関連データ更新", 2);
updTime.start();
updTime.outTotal(count);

				HashMap keyMap = new HashMap();
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0;i < fmVOs.length;i++){
					
					if(fmVOs[i] == null)
						continue;

					try{
						//HIN12とZAI01の在庫数の調整をする 2003/09/29 add
						adjustZaiSuu(fmVOs[i],fmDao,upddte,updjkk);
						//発注数を変更した場合変更内容をコメントへ　2004/01/16 add 
						setComent(fmVOs[i]); 
						//bshCodから取得できるように修正 2005/05/25 add
						String cykKbn = getCykKbn(fmVOs[i].getNhnMeiKj(),fmVOs[i].getBunCod(),fmVOs[i].getBshCod());
						//入庫数取消ができるように修正　2004/01/22 add
						setNyusu(fmVOs[i],cykKbn); 
						//入庫日更新を追加 2004/08/03 add
						setNyukobi(fmVOs[i],cykKbn);
						
						/*-- 出力済の場合、履歴更新日のセット --------------------------------------------*/
						if(fmVOs[i].getSyrZmiSgn().equals("1")){
							
							String rrkTbl = DataFormatUtils.makeRrkTblStr(fmVOs[i].getRrkTbl(), upddte);

							//VOに変更した履歴更新日をセット
							fmVOs[i].setRrkTbl(rrkTbl);
							
							//訂正送信した更新履歴日を発注書番号分更新するためのKey			  	  
							if(!keyMap.containsKey(fmVOs[i].getUpdateKey()))
								keyMap.put(fmVOs[i].getUpdateKey(),fmVOs[i]);
						}
						/*-------------------------------------------------------------------------------*/

						//発注履歴テーブル更新
						fmDao.updateHac02(fmVOs[i],upddte,updjkk,fmVOs[i].getSyrZmiSgn(),cykKbn);
						
						//発注書データ更新
						//2003/06/26 修正 okada
						OrderHistoryVO queryVO = new OrderHistoryVO();
						this.setVO_forCheck(queryVO,fmVOs[i]);//検索条件用VOに値をセット
						OrderHistoryVO[] finded_forCheck = fmDao.findHac02(queryVO);//発注履歴検索
						
						//チェックされた行の発注書データが存在していたら
						if(fmDao.hasHac01(fmVOs[i])){
							//bshCodから取得できるように修正 2005/05/25 add
							fmDao.updateHac01(fmVOs[i],upddte,updjkk,getCykKbn(fmVOs[i].getNhnMeiKj(),fmVOs[i].getBunCod(),fmVOs[i].getBshCod()));
						}
						//同一の発注書番号を持つ全ての行について、発注書データの存在をチェック
						for(int j = 0; j < finded_forCheck.length; j++){
							//発注書データが存在しなかったら
							if(!fmDao.hasHac01(finded_forCheck[j])){

								//完納済みは送信しない　2008/02/19
								int dtaExcFlg = 0;
								if(finded_forCheck[j].getKnuSgn().equals("1")){
									dtaExcFlg = 1;
								}else{
									dtaExcFlg = 0;
								}

								//発注書データ書き込み
								orderUtils.insertHac01(
									finded_forCheck[j].getDaiKaiSkbCod(),//代表会社
									finded_forCheck[j].getKaiSkbCod(),//会社
									finded_forCheck[j].getHacSyoDte(),//発注書日付
									finded_forCheck[j].getSyrSuu(),//処理回数
									finded_forCheck[j].getSeqNo(),//SEQNO
									dtaExcFlg,//データ交換フラグ
									upddte,
									updjkk);
							}
						}

					}catch(Exception e){
						SystemException se = new SystemException(e);
						se.printStackTrace();
						error = true;
						results[i] = new OrderHistoryResult(fmVOs[i],false,"");
						break;
					}
					//正常に成功
					results[i] = new OrderHistoryResult(fmVOs[i],true,"");
					results[i].setKeyMap(keyMap);

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
					   fmVOs[i].setUpdDte(upddte);
					   fmVOs[i].setUpdJkk(updjkk);
					   fmVOs[i].setHacSuuOld(fmVOs[i].getHacSuu());
					   fmVOs[i].setSinKyuKbnOld(fmVOs[i].getSinKyuKbn());
					   fmVOs[i].setCheck_Mid(false);  //2004.01.20 add
				       fmVOs[i].setTan2Old(fmVOs[i].getTan2());
 	 				   fmVOs[i].setKinOld(fmVOs[i].getKin());

				   }
			   }

updTime.end();
			}

		}finally{
			if(fmDao != null){
				try {
					if(!endTransaction)//exception でcommit or rollback できなかったときは ここで rollback
						fmDao.rollback();
	
					fmDao.close();
		
				} catch (SQLException e) {
					SystemException se = new SystemException(e);
					se.printStackTrace();
				}
			}
		}

prcTime.end();
		return results;
		
	}
		
	/** 
	 * HIN12とZAI01の在庫数を調整する 2003/09/29 add
	 */
	private void adjustZaiSuu(OrderHistoryVO vo,OrderHistoryDAO fmDao, int upddte,int updjkk)throws SQLException{
							
		int hacsuu_Old = 0;
		int hacsuu = 0;
		int sabun = 0;
		
		hacsuu_Old = Integer.parseInt(vo.getHacSuuOld());
		hacsuu	= Integer.parseInt(vo.getHacSuu());
		sabun = hacsuu_Old - hacsuu;
		
		OrderUtils orderUtils = new OrderUtils(fmDao);
	
		//プレス発注のとき
		if(vo.getBunCod().equals("0")){
			if(vo.isShinpu()){
				//区分変更タイプに応じて、FTBHIN12の存在チェックをし、なければINSERTを行う
				checkAndInsertHin12(vo,fmDao,upddte,updjkk);
				//発注数は変更されていないとき
				if(sabun == 0){
					switch(vo.getKbnHenkoType()){
						case OrderHistoryVO.FROM_SMP_TO_OTHER:
							//副資材在庫テーブル更新①(ｻﾝﾌﾟﾙ区分=△ から ｻﾝﾌﾟﾙ区分=Sへ在庫を移す)
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",hacsuu,upddte,updjkk);
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",-hacsuu,upddte,updjkk);
							//在庫テーブル更新
							orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),hacsuu,upddte,updjkk,OrderUtils.PRSHAC);
							break;
						case OrderHistoryVO.FROM_OTHER_TO_SMP:
							//副資材在庫テーブル更新①(ｻﾝﾌﾟﾙ区分=S から ｻﾝﾌﾟﾙ区分=△へ在庫を移す)
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",hacsuu,upddte,updjkk);
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",-hacsuu,upddte,updjkk);
							//在庫テーブル更新
							orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),-hacsuu,upddte,updjkk,OrderUtils.PRSHAC);
							break;
					}
				}//発注数に変更があったとき
				else{
					switch(vo.getKbnHenkoType()){
						case OrderHistoryVO.FROM_SMP_TO_OTHER:
							//副資材在庫テーブル更新①
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",hacsuu_Old,upddte,updjkk);
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",-hacsuu,upddte,updjkk);
							//在庫テーブル更新
							orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),hacsuu,upddte,updjkk,OrderUtils.PRSHAC);
							break;
						case OrderHistoryVO.FROM_OTHER_TO_SMP:
							//副資材在庫テーブル更新①
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",hacsuu_Old,upddte,updjkk);
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",-hacsuu,upddte,updjkk);
							//在庫テーブル更新
							orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),-hacsuu,upddte,updjkk,OrderUtils.PRSHAC);
							break;
						case OrderHistoryVO.ELSE:
							//副資材在庫テーブル更新①
							orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getSmpKbn(),sabun,upddte,updjkk);
							//在庫テーブル更新
							if(vo.getSmpKbn().equals(""))
								orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),sabun,upddte,updjkk,OrderUtils.PRSHAC);
							break;
					}
				}
			
			}else{
				if(sabun != 0){
					//副資材在庫テーブル更新
					orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getSmpKbn(),sabun,upddte,updjkk);
				}
			}
		}
		//副資材発注のとき
		else{
			//発注数は変更されていないとき
			if(sabun == 0){
				//まだ納期を迎えていない
				if(upddte <= vo.getNki()){
					if(vo.isShinpu()){
						switch(vo.getKbnHenkoType()){
							case OrderHistoryVO.FROM_SMP_TO_OTHER:
								//副資材在庫テーブル更新②(ｻﾝﾌﾟﾙ区分=S から ｻﾝﾌﾟﾙ区分=△へ在庫を移す)
								orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",vo.getPrsFukSziCod(),-hacsuu,upddte,updjkk);
								if(orderUtils.findHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getBunCod(),vo.getPrsFukSziCod(),"") == null)
									orderUtils.insertHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getPrsFukSziCod(),hacsuu,vo.getBunCod(),"",upddte,updjkk);
								else
									orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",vo.getPrsFukSziCod(),hacsuu,upddte,updjkk);
								//在庫テーブル更新
								orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),hacsuu,upddte,updjkk,OrderUtils.FUKHAC);
								break;
							case OrderHistoryVO.FROM_OTHER_TO_SMP:
								//副資材在庫テーブル更新②(ｻﾝﾌﾟﾙ区分=△ から ｻﾝﾌﾟﾙ区分=Sへ在庫を移す)
								orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"",vo.getPrsFukSziCod(),-hacsuu,upddte,updjkk);
								if(orderUtils.findHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getBunCod(),vo.getPrsFukSziCod(),"S") == null)
									orderUtils.insertHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),vo.getPrsFukSziCod(),hacsuu,vo.getBunCod(),"S",upddte,updjkk);
								else
									orderUtils.updateHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),"S",vo.getPrsFukSziCod(),hacsuu,upddte,updjkk);
								//在庫テーブル更新
								orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),-hacsuu,upddte,updjkk,OrderUtils.FUKHAC);
								break;
						}
					}
				}
			}//発注数に変更があったとき ※夜間に更新が行われるため、HIN12の更新はなし
			else{
				switch(vo.getKbnHenkoType()){
					case OrderHistoryVO.FROM_SMP_TO_OTHER:
						//在庫テーブル更新(発注累計・未入庫計に発注数を足しこむ)
						orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),hacsuu,upddte,updjkk,OrderUtils.FUKHAC);
						break;
					case OrderHistoryVO.FROM_OTHER_TO_SMP:
						//在庫テーブル更新(発注累計・未入庫計から前回発注数を引く)
						orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),-hacsuu_Old,upddte,updjkk,OrderUtils.FUKHAC);
						break;
					case OrderHistoryVO.ELSE:
						if(vo.getSmpKbn().equals(""))
							orderUtils.updateZai01(vo.getDaiKaiSkbCod(),vo.getKigBng(),-sabun,upddte,updjkk,OrderUtils.FUKHAC);
						break;
				}
			}
		}
		
		
	}
	
	/** 
	 * 区分変更タイプに応じて、
	 * FTBHIN12の存在チェックをし、なければINSERTを行う
	 */
	private void checkAndInsertHin12(OrderHistoryVO vo,OrderHistoryDAO fmDao,int upddte,int updjkk)throws SQLException{
		ArrayList arr = new ArrayList();
		String smpKbn = "";
		if(vo.getKbnHenkoType() == OrderHistoryVO.FROM_SMP_TO_OTHER)
			arr = fmDao.findHin12(vo,"S");
		else if(vo.getKbnHenkoType() == OrderHistoryVO.FROM_OTHER_TO_SMP){
			arr = fmDao.findHin12(vo,"");
			smpKbn = "S";
		}else if(vo.getKbnHenkoType() == OrderHistoryVO.ELSE)
			return;

		OrderUtils orderUtils = new OrderUtils(fmDao);
		for(int i = 0; i < arr.size(); i++){
			String[] str = (String[])arr.get(i);
			if(orderUtils.findHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),str[0],str[1],smpKbn) == null)
				orderUtils.insertHin12(vo.getDaiKaiSkbCod(),vo.getKaiSkbCod(),vo.getKigBng(),str[1],0,str[0],smpKbn,upddte,updjkk);
		}
		
	}
	
	/** 
	 * %タカセ%または%ＪＡＲＥＤ% → 直送区分 = "0" それ以外 → 直送区分 = "1"
	 * 副資材発注のとき → 直送区分 = "0"
	 */
	private final String getCykKbn(String str,String bunCod,String bshCod){
		if(!bunCod.equals("0") && !bunCod.equals("")){//副資材発注のとき
			return	"0";
		}
		return DataFormatUtils.getCykKbn(str, bshCod);
	}
	
	/** 
	 * 発注履歴検索のためのVOに値をセット（発注書データ存在チェック用）
	 */
	private void setVO_forCheck(OrderHistoryVO queryVO, OrderHistoryVO vo){
		queryVO.setDaiKaiSkbCod(vo.getDaiKaiSkbCod());
		ArrayList arr = new ArrayList();
		arr.add(vo.getKaiSkbCod());
		queryVO.setKaiSkbCodList(arr);
		queryVO.setHacSyoDteFrm_H(Integer.parseInt(vo.getHacSyoDte()));
		queryVO.setHacSyoBngFrm_H(vo.getHacSyoBng());
	}

	/** 
	 * 発注数変更時のコメント自動設定　2004.01.16 add
	 */
	private void setComent(OrderHistoryVO queryVO){
		if(!queryVO.getCmt().equals("")){
			return ;
		}
		if(queryVO.getHacSuu().equals(queryVO.getHacSuuOld())){
			return;
		}else{
			queryVO.setCmt(
				"発注数訂正"
					+ StringUtils.toFullANS(queryVO.getHacSuuOld())
					+ "を"
					+ StringUtils.toFullANS(queryVO.getHacSuu()));
		}

	}

	/** 
	 * 発注数変更時の入庫数の設定　2004.02.27 upd
	 */
	private void setNyusu(OrderHistoryVO queryVO,String cykKbn){
		if(queryVO.getHacSuu().equals(queryVO.getHacSuuOld())){
			return ;
		}
		if(Integer.parseInt(queryVO.getHacSuu()) < Integer.parseInt(queryVO.getHacSuuOld())){
			if((Integer.parseInt(queryVO.getNyoSuu()) > 0 ) && 
				(Integer.parseInt(queryVO.getNyoSuu()) > Integer.parseInt(queryVO.getHacSuu()))){
				queryVO.setNyoSuu(queryVO.getHacSuu());
			}else{
				return;
			}
		}else{
			queryVO.setKnuSgn(" ");
		}
	}

	/**
	 * 入庫日の設定<br>
	 * 「プレス かつ 直送区分='1'」または「副資材」の場合、
	 * 入庫日が0ではない かつ 納期が当日以前だったら、
	 * 入庫日に納期をセット
	 */
	private void setNyukobi(OrderHistoryVO vo, String cykKbn){

		//「プレス かつ 直送区分='1'」または「副資材」の場合
		if((vo.getBunCod().equals("0") && cykKbn.equals("1"))
			|| (vo.getBunCod().equals("1") || vo.getBunCod().equals("2"))){
				
			//入庫日が0ではない かつ 納期が当日以前だったら
			if(!vo.getNyoDte().equals("0")){
				if(vo.getNki() <= new DateUtils().getDate6Int()){
					//入庫日に納期をセット
					vo.setNyoDte(String.valueOf(vo.getNki()));
				}
			}
		}
	}
}