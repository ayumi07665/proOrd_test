/**
* SubMateMasterDelegate  副資材マスターメンテナンス  実行クラス
*
*	作成日    2003/06/24
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    在庫日数メンテナンスを実行するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*           2004/03/15（ＮＩＩ）森
* 			・発注先コードはブランク登録可に変更
*			2004/04/05(NII)森
*			・副資材マスター更新時に副資材在庫テーブル(FTBHIN12)も更新する
**/

package com.jds.prodord.master.submatemaster;


import com.jds.prodord.common.DateUtils;

import java.sql.*;

public class SubMateMasterDelegate {

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	public static final String NG = "NG";


//-----  検索処理  --------------------------------------------------------------

	public SubMateMasterVO[] doFind(SubMateMasterVO fmVO)throws SQLException{

		SubMateMasterDAO fmDao = null;
		SubMateMasterVO[] finded = null;

		try{
			fmDao = new SubMateMasterDAO();

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

	public SubMateMasterResult doInsert(SubMateMasterVO fmVO)throws SQLException{

		SubMateMasterDAO fmDao = null;
		SubMateMasterResult result = null;
		SubMateMasterResult result_ = null;
		boolean endTransaction = false;
		boolean error = false;
		boolean flag1 = false;
		boolean flag2 = false;


		try{
			fmDao = new SubMateMasterDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始

			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			//まず検索
				int returnCode = fmDao.selectForInsert(fmVO);
				switch(returnCode){
					case SubMateMasterDAO.NOT_EXIST://まだない
						result = new SubMateMasterResult(fmVO,true,"");
						break;
					case SubMateMasterDAO.NOT_MODIFIED://すでにある
					case SubMateMasterDAO.MODIFIED:
						result = new SubMateMasterResult(fmVO,false,EXIST);
						flag1 = true;
						break;
					case SubMateMasterDAO.LOGICAL_DELETE://論理削除レコードが残ってる
						result = new SubMateMasterResult(fmVO,false,LOGICAL_DELETE);
						flag1 = true;
						break;					
				}

				if(!fmVO.getOutHatcCod().trim().equals("")){ //2004.03.15 upd 発注先ブランクの場合があり
					//発注先を検索
					int finded = fmDao.findHatcCod(fmVO);
					switch(finded){
						case SubMateMasterDAO.NO_COD://発注先なし
							result_ = new SubMateMasterResult(fmVO,false,NG);
							flag2 = true;
							break;

						case SubMateMasterDAO.OK://発注先あり
							result_ = new SubMateMasterResult(fmVO,true,"");
							break;					
					}
				}else{
					result_ = new SubMateMasterResult(fmVO,true,"");
				}


			//検索結果の判定
			if(!flag1 && !flag2){
				error = false;
			}else{
				error = true;
			}


			//エラーのレコードがなければinsert実行
			if(!error){
					try{						
						fmDao.insert(fmVO,upddte,updjkk);
					}catch(SQLException sqle2){//一意制約でエラーになった
						error = true;
						result = new SubMateMasterResult(fmVO,false,"");
					}
					//正常に成功
					result = new SubMateMasterResult(fmVO,true,"");
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
		if(!result_.isSuccess())
			return result_;
			
		return result;
	}

//-----  更新処理  --------------------------------------------------------------

	public SubMateMasterResult[] doUpdate(SubMateMasterVO[] fmVOs)throws SQLException{


		SubMateMasterDAO fmDao = null;
		SubMateMasterResult[] results = null;
		boolean endTransaction = false;

		try{
			fmDao = new SubMateMasterDAO();
			fmDao.setAutoCommit(false);//DBのトランザクション開始

			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
			
			boolean error = false; 
			
			//まず検索
			results = doSelectForUpdate(fmDao,fmVOs);
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					error = true;
				}
			}
			
			if(!error){
				for(int i = 0; i < fmVOs.length;i++){
					if(fmVOs[i] == null)
						continue;
					if(!fmVOs[i].getOutHatcCod().trim().equals("")){ //2004.03.15 upd 発注先ブランクの場合があり
						int finded = fmDao.findHatcCod(fmVOs[i]);
						switch(finded){
							case SubMateMasterDAO.NO_COD://発注先なし
								results[i] = new SubMateMasterResult(fmVOs[i],false,NG);
								error = true;
								break;
		
							case SubMateMasterDAO.OK://発注先あり
								results[i] = new SubMateMasterResult(fmVOs[i],true,"");
								break;					
						}
					}else{
						results[i] = new SubMateMasterResult(fmVOs[i],true,"");
					}
				}
			}
			
			if(error){
				fmDao.rollback();
				endTransaction = true;
				fmDao.close();
				return results;
			}

			//更新実行
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				fmDao.update(fmVOs[i],upddte,updjkk);
				fmDao.updateHin12(fmVOs[i],upddte,updjkk);
				results[i] = new SubMateMasterResult(fmVOs[i],true,"");
			} 

			fmDao.commit();
			endTransaction = true;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				fmVOs[i].setUpdDte(upddte);
				fmVOs[i].setUpdJkk(updjkk);
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


//-----  削除処理  --------------------------------------------------------------

	public SubMateMasterResult[] doDelete(SubMateMasterVO[] fmVO)throws SQLException{

		SubMateMasterDAO fmDao = null;
		SubMateMasterResult[] result = null;
		boolean endTransaction = false;

		try{
			fmDao = new SubMateMasterDAO();
			fmDao.setAutoCommit(false);//DBのトランザクション開始

			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

		//まず検索
			result = doSelectForUpdate(fmDao,fmVO);
			for(int i = 0; i < result.length;i++){
				if(result[i] != null && !result[i].isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}
			}

		//削除実行	
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
				fmDao.delete(fmVO[i],upddte,updjkk);
				result[i] = new SubMateMasterResult(fmVO[i],true,"");
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

	private SubMateMasterResult[] doSelectForUpdate(SubMateMasterDAO fmDao,SubMateMasterVO[] fmVO)throws SQLException{

		SubMateMasterResult[] result = new SubMateMasterResult[fmVO.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVO.length;i++){
				if(fmVO[i] == null)
					continue;
			
				int returnCode = fmDao.selectForUpdate(fmVO[i]);

				switch(returnCode){
					case SubMateMasterDAO.NOT_MODIFIED:
						result[i] = new SubMateMasterResult(fmVO[i],true,"");
						break;					
					case SubMateMasterDAO.MODIFIED:
						result[i] = new SubMateMasterResult(fmVO[i],false,ANOTHER_USER_UPDATE);
						break;					
					case SubMateMasterDAO.NOT_EXIST:
						result[i] = new SubMateMasterResult(fmVO[i],false,NOT_EXIST);
						break;						
					case SubMateMasterDAO.LOGICAL_DELETE:
						result[i] = new SubMateMasterResult(fmVO[i],false,LOGICAL_DELETE);
						break;

				}


				int returnCode_ = fmDao.selectForInsert(fmVO[i]);
				switch(returnCode_){				
					case SubMateMasterDAO.NO_COD:
						result[i] = new SubMateMasterResult(fmVO[i],false,NG);
						break;
					case SubMateMasterDAO.OK:
						result[i] = new SubMateMasterResult(fmVO[i],true,"");
						break;						
				}


			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return result;
	}
	

}

