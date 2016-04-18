/**
* IndicatePrintDelegate  発注書出力指示実行クラス
*
*	作成日    2003/04/23
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    発注書出力指示を実行するクラス。
*
*	[変更履歴]
*       2003/06/05（ＮＩＩ）蛭田 敬子
* 			・発注先コード存在チェック追加。
* 		2003/07/07
* 			・ﾃﾞｰﾀ交換ﾌﾗｸﾞ→伝票発行のとき"0"／再出力のときで発注書がない場合"1"。
*/

package com.jds.prodord.indicate.indicateprint;

import com.jds.prodord.common.*;
import java.sql.*;
import java.util.*;


public class IndicatePrintDelegate {

	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String NOT_HACCOD = "NOT_HACCOD";


	/**
	 * 伝票発行
	 * 
	 */
	public IndicatePrintResult[] doDnpHakkou(IndicatePrintVO fmVO)throws SQLException{
		IndicatePrintDAO fmDao = null;
		IndicatePrintResult[] results = new IndicatePrintResult[1];

		boolean endTransaction = false;
		try{
			fmDao = new IndicatePrintDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始
			
			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
			
			IndicatePrintVO[] finded = null;
			
			boolean error = false;
			ArrayList errors_hac = new ArrayList();
			ArrayList hac_arr = fmVO.getHacCod_arr();
			
			//発注先コード存在チェック
			for(int i = 0; i < hac_arr.size(); i++){
				if(hac_arr.get(i).toString().equals(""))
					continue;
				boolean exist = CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),hac_arr.get(i)+"");
				if(!exist){
					String err = String.valueOf(i+1);//エラーのあるindex
					errors_hac.add(err);
				    error = true;
			    }
			}
			//エラーがあれば
			if(error){
				results[0] = new IndicatePrintResult(fmVO,false,"");
				if(errors_hac.size() > 0){
					results[0].setMap(IndicatePrintResult.KEY_HACCOD,errors_hac);
				}				
			}
			//エラーがなければ
			if(!error){
				//発注履歴検索実行
				finded = fmDao.findHac02(fmVO,false);
				if(finded.length == 0){//結果が０件だったら
					results[0] = new IndicatePrintResult(fmVO,false,NOT_EXIST);
					error = true;
				}
			}
			if(!error){
				results = new IndicatePrintResult[finded.length];
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0;i < finded.length;i++){
					if(finded[i] == null)
						continue;
						
					try{
						//発注履歴の更新
						fmDao.updateHac02(finded[i],upddte,updjkk);
						if(!fmDao.hasHac01(finded[i])){
							//2003/07/07 データ交換フラグ＝'0'のセット
							int dtaExcFlg = 0;
							//発注書データ書き込み
							orderUtils.insertHac01(
								finded[i].getDaiKaiSkbCod(),//代表会社
								finded[i].getKaiSkbCod(),//会社
								finded[i].getHacSyoDte(),//発注書日付
								finded[i].getSyrSuu(),//処理回数
								finded[i].getSeqNo(),//SEQNO
								dtaExcFlg,//データ交換フラグ
								upddte,
								updjkk);
						}
					}catch(SQLException sqle2){
						error = true;
						results[i] = new IndicatePrintResult(finded[i],false,"");
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						continue;
					}
					//正常に成功
					results[i] = new IndicatePrintResult(finded[i],true,"");
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
	 * 再出力
	 * 
	 */
	public IndicatePrintResult[] doSaiSyryk(IndicatePrintVO fmVO)throws SQLException{
		IndicatePrintDAO fmDao = null;
		IndicatePrintResult[] results = new IndicatePrintResult[1];

		boolean endTransaction = false;
		try{
			fmDao = new IndicatePrintDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始
			
			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
			
			IndicatePrintVO[] finded = null;
			
			boolean error = false;
			boolean exist = true;
			//発注先コード存在チェック
			if(!fmVO.getHacCod().equals(""))
				exist = CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),fmVO.getHacCod());
			
			if(!exist){
			    results[0] = new IndicatePrintResult(fmVO,false,NOT_HACCOD);
			    error = true;
		    }else{		    
				//発注履歴検索実行
				finded = fmDao.findHac02(fmVO,true);
	
				if(finded.length == 0){//結果が０件だったら
					results[0] = new IndicatePrintResult(fmVO,false,NOT_EXIST);
					error = true;
				}
			}
			//エラーがなければ
			if(!error){
				results = new IndicatePrintResult[finded.length];
				OrderUtils orderUtils = new OrderUtils(fmDao);
				for(int i = 0; i < finded.length; i++){
					if(finded[i] == null)
						continue;
					try{
						//出力済なのに発注書データが存在しなかったら
						if(!fmDao.hasHac01(finded[i])){
							//2003/07/07 データ交換フラグ＝'1'のセット
							int dtaExcFlg = 1;
							//発注書データ書き込み
							orderUtils.insertHac01(
								finded[i].getDaiKaiSkbCod(),//代表会社
								finded[i].getKaiSkbCod(),//会社
								finded[i].getHacSyoDte(),//発注書日付
								finded[i].getSyrSuu(),//処理回数
								finded[i].getSeqNo(),//SEQNO
								dtaExcFlg,//データ交換フラグ
								upddte,
								updjkk);
						}
					}catch(SQLException sqle2){
						SystemException se = new SystemException(sqle2);
						se.printStackTrace();
						error = true;
						results[i] = new IndicatePrintResult(finded[i],false,"");
						continue;
					}
				}
				
			}
			//エラーならロールバック
			if(error){
				fmDao.rollback();
				endTransaction = true;
			}else{
				//正常に成功
				results[0] = new IndicatePrintResult(fmVO,true,"");
				results[0].setFinded_arr(new ArrayList(Arrays.asList(finded)));
				//エラーがなければコミット
				fmDao.commit();
				endTransaction = true;
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


}

