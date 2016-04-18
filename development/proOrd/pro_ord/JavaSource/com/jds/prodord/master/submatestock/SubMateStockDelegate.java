/**
* SubMateStockDelegate  副資材在庫メンテナンス 実行クラス
*
*	作成日    2003/08/18
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    在庫日数メンテナンスを実行するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.submatestock;

import java.sql.*;
import java.util.*;
import com.jds.prodord.common.*;
public class SubMateStockDelegate {

	
//=====  副資材在庫テーブル照会  =====================================================

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";



//-----  検索処理  --------------------------------------------------------------

	public SubMateStockVO[] doFind(SubMateStockVO fmVO)throws SQLException{

		SubMateStockDAO fmDao = null;
		SubMateStockVO[] finded = null;

		try{
			fmDao = new SubMateStockDAO();

			finded = fmDao.find(fmVO);    //検索処理を実行し、結果をfindedへ

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

//-----  更新処理  --------------------------------------------------------------

	public SubMateStockResult[] doUpdate(SubMateStockVO[] fmVO)throws SQLException{


		SubMateStockDAO fmDao = null;
		SubMateStockResult[] results = null;
		boolean endTransaction = false;

		try{
			fmDao = new SubMateStockDAO();
			fmDao.setAutoCommit(false);//DBのトランザクション開始

			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
			
			boolean error = false; 
			
			//まず検索
			results = doSelectForUpdate(fmDao,fmVO);
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					error = true;
				}
			}
			
			if(error){
				fmDao.rollback();
				endTransaction = true;
				fmDao.close();
				return results;
			}

			//更新実行
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
				fmDao.update(fmVO[i],upddte,updjkk);
				results[i] = new SubMateStockResult(fmVO[i],true,"");
			} 

			fmDao.commit();
			endTransaction = true;
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
				fmVO[i].setHidUpdDte(upddte);
				fmVO[i].setHidUpdJkk(updjkk);
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


//-----  selectForUpdate  登録・更新・削除が呼ぶ  ---------

	private SubMateStockResult[] doSelectForUpdate(SubMateStockDAO fmDao,SubMateStockVO[] fmVO)throws SQLException{

		SubMateStockResult[] result = new SubMateStockResult[fmVO.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
			
				int returnCode = fmDao.selectForUpdate(fmVO[i]);

				switch(returnCode){
					case SubMateStockDAO.NOT_MODIFIED:
						result[i] = new SubMateStockResult(fmVO[i],true,"");
						break;					
					case SubMateStockDAO.MODIFIED:
						result[i] = new SubMateStockResult(fmVO[i],false,ANOTHER_USER_UPDATE);
						break;					
					case SubMateStockDAO.NOT_EXIST:
						result[i] = new SubMateStockResult(fmVO[i],false,NOT_EXIST);
						break;						
					case SubMateStockDAO.LOGICAL_DELETE:
						result[i] = new SubMateStockResult(fmVO[i],false,LOGICAL_DELETE);
						break;

				}

			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return result;
	}

//-----  記号番号存在判定処理  --------------------------------------------------------------

	public ArrayList kigoban_search(SubMateStockVO fmVO)throws SQLException{

		ArrayList kigo_ArrErr = new ArrayList();
		ArrayList kigo_ArrOk = new ArrayList();
		ArrayList retrun_Arr = new ArrayList();
		String kigbng = "";

		for(int i=0 ; i<fmVO.getKigoBan_arr().size() ; i++){
			//FTBHIN01の判定
			kigbng = CheckCommon.getKigBng(fmVO.getDaiKaiSkbCod(),fmVO.getQueryKaiSkbCodList(),(String)fmVO.getKigoBan_arr().get(i));
			if( kigbng == null){
				//FTBHIN02の判定
				kigbng = CheckCommon.getKigBng2(fmVO.getDaiKaiSkbCod(),fmVO.getQueryKaiSkbCodList(),(String)fmVO.getKigoBan_arr().get(i));
				if( kigbng == null){
					kigo_ArrErr.add(Integer.toString(i+1));
				}else{
					kigo_ArrOk.add(kigbng);
				}
			}else{
				kigo_ArrOk.add(kigbng);
			}
		}

		retrun_Arr.add(kigo_ArrErr);//エラー品番の入力番号
		retrun_Arr.add(kigo_ArrOk); //OK品番の記号番号
		
		return retrun_Arr;
	}


}