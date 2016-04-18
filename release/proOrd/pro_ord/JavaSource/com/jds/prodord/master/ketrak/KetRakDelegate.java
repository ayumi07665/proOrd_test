/**
* KetRakDelegate  形態ランク別条件マスターテーブルメンテナンス実行クラス
*
*	作成日    2003/05/02
*	作成者    （ＮＩＩ）今井 美季
* 処理概要    形態ランク別条件マスターテーブルメンテナンスを実行するクラス。
*
*	[変更履歴]
*       2003/06/02（ＮＩＩ）蛭田 敬子
* 			・｢削除｣の複数指定。
* 		2003/06/10
* 			・｢登録｣のとき、論理削除済のレコードはDELETEして登録。
*/

package com.jds.prodord.master.ketrak;


import com.jds.prodord.common.DateUtils;

import java.sql.*;

public class KetRakDelegate {

	
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
	public KetRakVO[] doFind(KetRakVO fmVO)throws SQLException{
		KetRakDAO fmDao = null;

		KetRakVO[] finded = null;

		try{
			fmDao = new KetRakDAO();

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
	 * 登録
	 * 
	 */
	public KetRakResult doInsert(KetRakVO fmVO)throws SQLException{
		KetRakDAO fmDao = null;

		KetRakResult result = null;
		boolean	delete_flg = false;

		boolean endTransaction = false;
		try{
			fmDao = new KetRakDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始

			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			//まず検索
			boolean error = false;
				int returnCode = fmDao.selectForUpdate(fmVO);
				switch(returnCode){
					case KetRakDAO.NOT_EXIST://まだない
						result = new KetRakResult(fmVO,true,"");
						break;					
					case KetRakDAO.NOT_MODIFIED://すでにある
					case KetRakDAO.MODIFIED:
						result = new KetRakResult(fmVO,false,EXIST);
						error = true;
						break;					
					case KetRakDAO.LOGICAL_DELETE://論理削除レコードが残ってる
						result = new KetRakResult(fmVO,true,"");
//						error = true;
						delete_flg = true;
						break;					
				}
			

			 
			//エラーのレコードがなければinsert実行
			if(!error){
					try{
						//レコードが論理削除済だったらDELETE
						if(delete_flg){
//System.out.println("レコードが論理削除済だったらDELETE");
							fmDao.deleteMst05(fmVO);
						}
						
						fmDao.insert(fmVO,upddte,updjkk);
					}catch(SQLException sqle2){//一意制約でエラーになった
						error = true;
						result = new KetRakResult(fmVO,false,"");
					}
					//正常に成功
					result = new KetRakResult(fmVO,true,"");
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



	/**
	 * 更新
	 * 
	 */
	public KetRakResult[] doUpdate(KetRakVO[] fmVOs)throws SQLException{
		KetRakDAO fmDao = null;


		KetRakResult[] results = null;

		boolean endTransaction = false;
		try{
			fmDao = new KetRakDAO();
			fmDao.setAutoCommit(false);//DBのトランザクション開始


			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			results = this.doSelectForUpdate(fmDao,fmVOs);
			//まず検索
/**			boolean error = false;
			for(int i = 0; i<fmVOs.length; i++){
				if(fmVOs[i]==null)
					continue;
				
        		results[i] = this.doSelectForUpdate(fmDao,fmVOs[i]);
        		
			}
**/
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
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				fmDao.update(fmVOs[i],upddte,updjkk);
				results[i] = new KetRakResult(fmVOs[i],true,"");
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




	/**
	 * 削除
	 * 
	 */
	public KetRakResult[] doDelete(KetRakVO[] fmVOs)throws SQLException{
		KetRakDAO fmDao = null;

		KetRakResult[] results = null;

		boolean endTransaction = false;
		try{
			fmDao = new KetRakDAO();
			fmDao.setAutoCommit(false);//DBのトランザクション開始

			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();


				
			results = doSelectForUpdate(fmDao,fmVOs);
			for(int i = 0; i < results.length;i++){
				if(results[i] != null && !results[i].isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return results;
				}
			}
	
			
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
				fmDao.delete(fmVOs[i],upddte,updjkk);
				results[i] = new KetRakResult(fmVOs[i],true,"");
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
		return results;
	}



	/**
	 * selectForUpdate  更新と削除が呼ぶ
	 * 
	 */
	private KetRakResult[] doSelectForUpdate(KetRakDAO fmDao,KetRakVO[] fmVOs)throws SQLException{
		KetRakResult[] results = new KetRakResult[fmVOs.length];

		try{
			boolean error = false;
			for(int i = 0;i < fmVOs.length;i++){
				if(fmVOs[i] == null)
					continue;
			
				int returnCode = fmDao.selectForUpdate(fmVOs[i]);
					
				switch(returnCode){
					case KetRakDAO.NOT_MODIFIED:
						results[i] = new KetRakResult(fmVOs[i],true,"");
						break;					
					case KetRakDAO.MODIFIED:
						results[i] = new KetRakResult(fmVOs[i],false,ANOTHER_USER_UPDATE);
						break;					
					case KetRakDAO.NOT_EXIST:
						results[i] = new KetRakResult(fmVOs[i],false,NOT_EXIST);
						break;						
					case KetRakDAO.LOGICAL_DELETE:
						results[i] = new KetRakResult(fmVOs[i],false,LOGICAL_DELETE);
						break;
	
				}
			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return results;
	}

}

