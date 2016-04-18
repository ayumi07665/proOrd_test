/**
* StockDaysDelegate  在庫日数マスターメンテナンス実行クラス
*
*	作成日    2003/06/09
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    在庫日数メンテナンスを実行するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.stockdays;


import com.jds.prodord.common.DateUtils;

import java.sql.*;

public class StockDaysDelegate {

	
//=====  在庫日数マスターメンテナンス  =====================================================

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";



//-----  検索処理  --------------------------------------------------------------

	public StockDaysVO[] doFind(StockDaysVO fmVO)throws SQLException{

		StockDaysDAO fmDao = null;
		StockDaysVO[] finded = null;

		try{
			fmDao = new StockDaysDAO();

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


//-----  登録処理  --------------------------------------------------------------

	public StockDaysResult doInsert(StockDaysVO fmVO)throws SQLException{

		StockDaysDAO fmDao = null;
		StockDaysResult result = null;
		boolean endTransaction = false;

		try{
			fmDao = new StockDaysDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始

			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			//まず検索
			boolean error = false;
				int returnCode = fmDao.selectForUpdate(fmVO);
				switch(returnCode){
					case StockDaysDAO.NOT_EXIST://まだない
						result = new StockDaysResult(fmVO,true,"");
						break;					
					case StockDaysDAO.NOT_MODIFIED://すでにある
					case StockDaysDAO.MODIFIED:
						result = new StockDaysResult(fmVO,false,EXIST);
						error = true;
						break;					
					case StockDaysDAO.LOGICAL_DELETE://論理削除レコードが残ってる
						result = new StockDaysResult(fmVO,false,LOGICAL_DELETE);
						error = true;
						break;					
				}
			 
			//エラーのレコードがなければinsert実行
			if(!error){
					try{
						fmDao.insert(fmVO,upddte,updjkk);
					}catch(SQLException sqle2){//一意制約でエラーになった
						error = true;
						result = new StockDaysResult(fmVO,false,"");
					}
					//正常に成功
					result = new StockDaysResult(fmVO,true,"");
			}

			//エラーならロールバック
			if(error){
				fmDao.rollback();
				endTransaction = true;
			//エラーがなければコミット
			}else{
				fmDao.commit();
				endTransaction = true;
	  			 //バリューオブジェクトに更新日時をセット
				fmVO.setUpdDte(upddte);
				fmVO.setUpdJkk(updjkk);
			}
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
		return result;
		
	}

//-----  更新処理  --------------------------------------------------------------

	public StockDaysResult[] doUpdate(StockDaysVO[] fmVO)throws SQLException{

		StockDaysDAO fmDao = null;
		StockDaysResult[] result = null;
		boolean endTransaction = false;

		try{
			fmDao = new StockDaysDAO();
			fmDao.setAutoCommit(false);//DBのトランザクション開始

			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();


			result = this.doSelectForUpdate(fmDao,fmVO);

			//まず検索
			for(int i = 0; i < result.length;i++){
				//エラーがあったらrollbackして終了
				if((result[i] != null && !result[i].isSuccess())){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}
			}


			//更新実行

			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
				fmDao.update(fmVO[i],upddte,updjkk);
				result[i] = new StockDaysResult(fmVO[i],true,"");
			} 

			fmDao.commit();
			endTransaction = true;
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
				fmVO[i].setUpdDte(upddte);
				fmVO[i].setUpdJkk(updjkk);
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
		return result;
	}


//-----  削除処理  --------------------------------------------------------------

	public StockDaysResult[] doDelete(StockDaysVO[] fmVO)throws SQLException{

		StockDaysDAO fmDao = null;
		StockDaysResult[] result = null;
		boolean endTransaction = false;

		try{
			fmDao = new StockDaysDAO();
			fmDao.setAutoCommit(false);//DBのトランザクション開始

			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();


			result = doSelectForUpdate(fmDao,fmVO);
			for(int i = 0; i < result.length;i++){
				if(result[i] != null && !result[i].isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}
			}
	
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
				fmDao.delete(fmVO[i],upddte,updjkk);
				result[i] = new StockDaysResult(fmVO[i],true,"");
			}		

			fmDao.commit();
			endTransaction = true;


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
		return result;
	}



//-----  selectForUpdate  登録・更新・削除が呼ぶ  ---------

	private StockDaysResult[] doSelectForUpdate(StockDaysDAO fmDao,StockDaysVO[] fmVO)throws SQLException{

		StockDaysResult[] result = new StockDaysResult[fmVO.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
			
				int returnCode = fmDao.selectForUpdate(fmVO[i]);
					
				switch(returnCode){
					case StockDaysDAO.NOT_MODIFIED:
						result[i] = new StockDaysResult(fmVO[i],true,"");
						break;					
					case StockDaysDAO.MODIFIED:
						result[i] = new StockDaysResult(fmVO[i],false,ANOTHER_USER_UPDATE);
						break;					
					case StockDaysDAO.NOT_EXIST:
						result[i] = new StockDaysResult(fmVO[i],false,NOT_EXIST);
						break;						
					case StockDaysDAO.LOGICAL_DELETE:
						result[i] = new StockDaysResult(fmVO[i],false,LOGICAL_DELETE);
						break;

				}
			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return result;
	}
	

}

