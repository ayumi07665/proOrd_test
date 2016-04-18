/**
* NyukoSuuAdjustDelegate  入庫数調整実行クラス
*
*	作成日    2003/09/30
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    入庫数調整を実行するクラス。
*
*	[変更履歴]
*       2003/10/28（ＮＩＩ）岡田 夏美
* 			・"入庫数変更による完納"処理を追加。
*/

package com.jds.prodord.master.nyukosuuadjust;
import com.jds.prodord.common.*;

import java.sql.*;

public class NyukoSuuAdjustDelegate {

	
	/**
	 * 形態ランク別条件マスターテーブルメンテナンス
	 * 
	 */
	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";


	/**
	 * 検索
	 * 
	 */
	public NyukoSuuAdjustVO[] doFind(NyukoSuuAdjustVO fmVO)throws SQLException{
		NyukoSuuAdjustDAO fmDao = null;

		NyukoSuuAdjustVO[] finded = null;

		try{
			fmDao = new NyukoSuuAdjustDAO();

			finded = fmDao.find(fmVO);

		}finally{
			if(fmDao != null){
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					sqle2.printStackTrace();
				}
			}
		}
		return finded;
	}



	/**
	 * 入庫数調整
	 * 
	 */
	public NyukoSuuAdjustResult[] doNyukoSuuAdjust(NyukoSuuAdjustVO[] fmVOs)throws SQLException{
		NyukoSuuAdjustDAO fmDao = null;


		NyukoSuuAdjustResult[] results = null;

		boolean endTransaction = false;
		try{
			fmDao = new NyukoSuuAdjustDAO();
			fmDao.setAutoCommit(false);//DBのトランザクション開始


			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
			
			//まず検索
			results = this.doSelectForUpdate(fmDao,fmVOs);
			
			for(int i = 0; i < results.length;i++){
				//エラーがあったらrollbackして終了
				if((results[i] != null && !results[i].isSuccess())){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return results;
				}
			}
			
			//更新実行
			OrderUtils orderUtils = new OrderUtils(fmDao);
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				try{
					//"完納"選択のとき
					if(fmVOs[i].getKnuSgn().equals("1")){
						//完納サイン＝１、入庫数はそのまま
						fmDao.updateHac02(fmVOs[i],Integer.parseInt(fmVOs[i].getNyoSuu()),upddte,updjkk);
						//未入庫計から発注数と入庫数の差をマイナス
						int sa = Integer.parseInt(fmVOs[i].getHacSuu()) - Integer.parseInt(fmVOs[i].getNyoSuu());
						fmDao.updateZai01(fmVOs[i],sa,upddte,updjkk);
						orderUtils.updateHin12(fmVOs[i].getDaiKaiSkbCod(),fmVOs[i].getKaiSkbCod(),fmVOs[i].getKigBng(),fmVOs[i].getSmpKbn(),sa,upddte,updjkk);
					}//"入庫数変更による完納"選択のとき 2003/10/28 add
					else if(fmVOs[i].getKnuSgn().equals("2")){
						//完納サイン＝１、入庫数＝発注数で更新
						fmDao.updateHac02(fmVOs[i],Integer.parseInt(fmVOs[i].getHacSuu()),upddte,updjkk);
						//未入庫計から発注数をマイナス
						fmDao.updateZai01(fmVOs[i],Integer.parseInt(fmVOs[i].getHacSuu()),upddte,updjkk);
						//HIN12の更新はなし
					}
				}catch(SQLException sqle){
					sqle.printStackTrace();
					results[i] = new NyukoSuuAdjustResult(fmVOs[i],false,"");
					continue;
				}
				//正常に成功
				results[i] = new NyukoSuuAdjustResult(fmVOs[i],true,"");
			} 


			fmDao.commit();
			endTransaction = true;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				fmVOs[i].setUpdDte(upddte);
				fmVOs[i].setUpdJkk(updjkk);
				if(fmVOs[i].getKnuSgn().equals("2"))
					fmVOs[i].setNyoSuu(fmVOs[i].getHacSuu());
			}


		}catch(SQLException sqle){
			if(fmDao != null){
				try{
					fmDao.rollback();
					endTransaction = true;
				}catch(SQLException sqle3){
					sqle3.printStackTrace();
				}
			}
			throw sqle;
		}finally{
			if(fmDao != null){
				if(!endTransaction ){
					//exception でcommit or rollback できなかったときは ここで rollback
					try{
						fmDao.rollback();
					}catch(SQLException sqle3){
						sqle3.printStackTrace();
					}
				}
				try{
					fmDao.close();
				}catch(SQLException sqle2){
					sqle2.printStackTrace();
				}
			}
		}
		return results;
	}



	/**
	 * selectForUpdate  更新が呼ぶ
	 * 
	 */
	private NyukoSuuAdjustResult[] doSelectForUpdate(NyukoSuuAdjustDAO fmDao,NyukoSuuAdjustVO[] fmVOs)throws SQLException{
		NyukoSuuAdjustResult[] results = new NyukoSuuAdjustResult[fmVOs.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
			
				int returnCode = fmDao.selectForUpdate(fmVOs[i]);
					
				switch(returnCode){
					case NyukoSuuAdjustDAO.NOT_MODIFIED:
						results[i] = new NyukoSuuAdjustResult(fmVOs[i],true,"");
						break;					
					case NyukoSuuAdjustDAO.MODIFIED:
						results[i] = new NyukoSuuAdjustResult(fmVOs[i],false,ANOTHER_USER_UPDATE);
						break;					
					case NyukoSuuAdjustDAO.NOT_EXIST:
						results[i] = new NyukoSuuAdjustResult(fmVOs[i],false,NOT_EXIST);
						break;						
					case NyukoSuuAdjustDAO.LOGICAL_DELETE:
						results[i] = new NyukoSuuAdjustResult(fmVOs[i],false,LOGICAL_DELETE);
						break;
	
				}
			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return results;
	}

}

