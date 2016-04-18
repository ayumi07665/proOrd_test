/**
*ArtcleNumberDelegate 品番マスターメンテナンス処理実行クラス
*
*	作成日    2003/08/25
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    品番マスター照会処理を実行するクラス。
*
*         2003/09/11（ＮＩＩ）村上  博基
* 			・品番マスターでも更新できるように処理変更。
*/

package com.jds.prodord.master.articlenumber;


import com.jds.prodord.common.*;

import java.sql.*;
import java.util.*;


public class ArticleNumberDelegate {

	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	public static final String CANNOT_DELETE = "CANNOT_DELETE";
	public static final String NOTEXIST_HACCOD = "NOTEXIST_HACCOD";
	public static final String EXIST_HAC02 = "EXIST_HAC02";



//*****  検索   ****************************************************

	public ArticleNumberVO doFind(ArticleNumberVO fmVO)throws SQLException{

//		System.out.println("Delegateにて設定を行います");

		ArticleNumberDAO fmDao = null;
		ArticleNumberVO finded = null;

		try{

			fmDao = new ArticleNumberDAO();
			finded = fmDao.find(fmVO);									

		}finally{
			try{
				fmDao.close();
			}catch(SQLException sqle2){
				sqle2.printStackTrace();
			}
		}
		return finded;
	}


//*****  登録   ****************************************************

	public ArticleNumberResult doInsert(ArticleNumberVO fmVO)throws SQLException{


//		System.out.println("Delegateにて設定を行います");

		ArticleNumberDAO fmDao = null;
		ArticleNumberVO finded = null;
		ArticleNumberResult result = null;
		boolean endTransaction = false;

		try{
			fmDao = new ArticleNumberDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始

			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();
					
			//まず検索
			boolean error = false;
				int returnCode = fmDao.selectForUpdate(fmVO);
				switch(returnCode){
					case ArticleNumberDAO.NOT_EXIST://まだない
						result = new ArticleNumberResult(fmVO,true,"");
						break;					
					case ArticleNumberDAO.NOT_MODIFIED://すでにある
					case ArticleNumberDAO.MODIFIED:
						result = new ArticleNumberResult(fmVO,false,EXIST);
						error = true;
						break;					
					case ArticleNumberDAO.LOGICAL_DELETE://論理削除レコードが残ってる
						result = new ArticleNumberResult(fmVO,false,LOGICAL_DELETE);
						error = true;
						break;					
				}


			if(!error){ //selectForUpdateが成功

				//発注先コード存在チェック
				result = this.existHacCod(fmVO);
				
				//エラーがあったらrollbackして終了
				if((result != null && !result.isSuccess())){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}
				
				//エラーのレコードがなければinsert実行
				try{
					fmDao.insert(fmVO,upddte,updjkk);
				}catch(SQLException sqle2){
					error = true;
					result = new ArticleNumberResult(fmVO,false,"");
				}
				
				//エラーならロールバック
				if(error){
					fmDao.rollback();
					endTransaction = true;
				//エラーがなければコミット
				}else{
					//正常に成功
					result = new ArticleNumberResult(fmVO,true,"");
					fmDao.commit();
					endTransaction = true;
		  			 //バリューオブジェクトに更新日時をセット
					fmVO.setUpddte(upddte);
					fmVO.setUpdjkk(updjkk);
					fmVO.setDbName("HIN02");//2003/10/07 add okada
				}


			} //if(!error){(外)終了

				}catch(SQLException sqle2){//一意制約でエラーになった
					result = new ArticleNumberResult(fmVO,false,"");

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
				} //try終了

		return result;
		
	}

//*****  更新   ****************************************************

	public ArticleNumberResult doUpdate(ArticleNumberVO fmVO)throws SQLException{

		ArticleNumberDAO fmDao = null;
		ArticleNumberResult result = null;
		boolean endTransaction = false;

		try{
			fmDao = new ArticleNumberDAO();
			fmDao.setAutoCommit(false);//DBのトランザクション開始

			//更新日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			//まず検索
			result = this.doSelectForUpdate(fmDao,fmVO);
				//エラーがあったらrollbackして終了
				if((result != null && !result.isSuccess())){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}

			//発注コード検索//
			result = this.existHacCod(fmVO);
				//エラーがあったらrollbackして終了
				if((result != null && !result.isSuccess())){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}

			//更新実行
				boolean error = false;
				try{
					fmDao.update(fmVO,upddte,updjkk);
				}catch(SQLException sqle2){
					error = true;
					result = new ArticleNumberResult(fmVO,false,"");
				}

				//エラーならロールバック
				if(error){
					fmDao.rollback();
					endTransaction = true;
				//エラーがなければコミット
				}else{
					result = new ArticleNumberResult(fmVO,true,"");
					fmDao.commit();
					endTransaction = true;
					fmVO.setUpddte(upddte);
					fmVO.setUpdjkk(updjkk);
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

//*****  削除   ****************************************************

	public ArticleNumberResult doDelete(ArticleNumberVO fmVO)throws SQLException{

		ArticleNumberDAO fmDao = null;
		ArticleNumberResult result = null;
		boolean endTransaction = false;

		try{
			fmDao = new ArticleNumberDAO();
			fmDao.setAutoCommit(false);//DBのトランザクション開始

			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();

			result = doSelectForUpdate(fmDao,fmVO);
				if(result != null && !result.isSuccess()){
					fmDao.rollback();
					endTransaction = true;
					fmDao.close();
					return result;
				}

			//削除実行
				boolean error = false;
				try{
					fmDao.delete(fmVO,upddte,updjkk);				
				}catch(SQLException sqle2){
					error = true;
					result = new ArticleNumberResult(fmVO,false,"");
				}

				//エラーならロールバック
				if(error){
					fmDao.rollback();
					endTransaction = true;
				//エラーがなければコミット
				}else{
					result = new ArticleNumberResult(fmVO,true,"");
					fmDao.commit();
					endTransaction = true;
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

//-----  selectForUpdate  更新・削除が呼ぶ  ---------

	private ArticleNumberResult doSelectForUpdate(ArticleNumberDAO fmDao,ArticleNumberVO fmVO)throws SQLException{

		ArticleNumberResult result = new ArticleNumberResult(fmVO,false,"");

		try{
			int returnCode;

			if(fmVO.getPrcKbn().equals("3")){  //03/09/11 追加
				returnCode = fmDao.UselectForUpdate(fmVO); //Update用
			}else{
				returnCode = fmDao.DselectForUpdate(fmVO); //Delete用
			}

				switch(returnCode){
					case ArticleNumberDAO.NOT_MODIFIED:
						result = new ArticleNumberResult(fmVO,true,"");
						break;					
					case ArticleNumberDAO.MODIFIED:
						result = new ArticleNumberResult(fmVO,false,ANOTHER_USER_UPDATE);
						break;					
					case ArticleNumberDAO.NOT_EXIST:
						result = new ArticleNumberResult(fmVO,false,NOT_EXIST);
						break;						
					case ArticleNumberDAO.LOGICAL_DELETE:
						result = new ArticleNumberResult(fmVO,false,LOGICAL_DELETE);
						break;
					case ArticleNumberDAO.CANNOT_DELETE:
						result = new ArticleNumberResult(fmVO,false,CANNOT_DELETE);
						break;
					case ArticleNumberDAO.EXIST_HAC02:
						result = new ArticleNumberResult(fmVO,false,EXIST_HAC02);
						break;
				}


		}catch(SQLException sqle){
			throw sqle;
		}
		return result;
	}
	
	//発注先コード存在チェック
	private ArticleNumberResult existHacCod(ArticleNumberVO fmVO)throws SQLException{

		ArticleNumberResult result = new ArticleNumberResult(fmVO,false,"");
		try{
				ArrayList arr = new ArrayList();
				if(!fmVO.getPrsMkrCod().equals("") && !CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),fmVO.getPrsMkrCod()))
					arr.add(ArticleNumberResult.NOTEXIST_PRS);
				if(!fmVO.getJytPrsMkr().equals("") && !CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),fmVO.getJytPrsMkr()))
					arr.add(ArticleNumberResult.NOTEXIST_JYT);
				if(!fmVO.getHukSizCod().equals("") && !CheckCommon.hasHacCod(fmVO.getDaiKaiSkbCod(),fmVO.getHukSizCod()))
					arr.add(ArticleNumberResult.NOTEXIST_FUK);
				
				if(arr.size() != 0){
					result = new ArticleNumberResult(fmVO,false,NOTEXIST_HACCOD);
					result.setError(arr);
				}else
					result = new ArticleNumberResult(fmVO,true,"");
				
		}catch(SQLException sqle){
			throw sqle;
		}
		return result;
	}

}  //クラス終了
