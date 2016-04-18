/**
*FzkHinKsiDelegate  付属品構成マスターメンテナンス実行クラス
*
*	作成日    2004/02/13
*	作成者    （ＮＩＩ）森
* 処理概要    付属品構成マスターメンテナンスをするクラス。
*
*	[変更履歴]
* 		 2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		 2004/06/23（ＮＩＩ）蛭田
* 			・明細に｢発注先名称｣追加
* 
*/

package com.jds.prodord.master.fzkhinksi;
import com.jds.prodord.common.*;

import java.sql.*;
import java.util.*;

public class FzkHinKsiDelegate {

	
	/**
	 * 付属品構成マスターメンテナンス
	 * 
	 */
	public static final String ANOTHER_USER_UPDATE = "ANOTHER_USER_UPDATE";
	public static final String EXIST = "EXIST";	
	public static final String NOT_EXIST = "NOT_EXIST";
	public static final String LOGICAL_DELETE = "LOGICAL_DELETE";
	
	public static final String COD_NOT_EXIST = "COD_NOT_EXIST";


	/**
	 * 検索
	 * 
	 */
	public FzkHinKsiVO[] doFind(FzkHinKsiVO queryVO)throws SQLException{
		FzkHinKsiDAO fmDao = null;
		
		FzkHinKsiVO resultVO = new FzkHinKsiVO();
		FzkHinKsiVO[] finded = null;
		
		try{
			fmDao = new FzkHinKsiDAO();
			
			//品番マスター検索
			if(!fmDao.findHin01_row(queryVO,resultVO,FzkHinKsiDAO.HIN01))
				fmDao.findHin01_row(queryVO,resultVO,FzkHinKsiDAO.HIN02);

			fmDao.findMST06(queryVO,resultVO);

			fmDao.findMst08(resultVO);	
			ArrayList fukSziCod08Arr = resultVO.get08FuksziCodArr();
			ArrayList sirSk08Arr = resultVO.get08SirSkCodArr();
			ArrayList fukSziCodArr = resultVO.get06FuksziCodArr();
			if((fukSziCod08Arr.size()==0)&&(fukSziCodArr.size()==0)&&(resultVO.getExsitHin01())){
				fukSziCodArr.add("");
				resultVO.setFlag(true);
			}
			for(int i = 0; i < fukSziCodArr.size(); i++){
				int index = fukSziCod08Arr.indexOf(fukSziCodArr.get(i));
				if(index < 0)
					fukSziCod08Arr.add(fukSziCodArr.get(i));
				else
					continue;
			}
			
			finded = new FzkHinKsiVO[fukSziCod08Arr.size()];
			for(int i = 0; i < fukSziCod08Arr.size(); i++){
				if(i == 0)
					finded[i] = resultVO;
				else
					finded[i] = new FzkHinKsiVO();
				
				finded[i].setDaiKaiSkbCod(resultVO.getDaiKaiSkbCod());
				finded[i].setKaiSkbCod(resultVO.getKaiSkbCod());
				finded[i].setFuksziCod(fukSziCod08Arr.get(i)+"");
				fmDao.findFuk01(finded[i]);	
			}
			
			ArrayList fukCodArr = resultVO.get08FuksziCodArr();
			for(int i = 0; i < finded.length; i++){
				int index = fukCodArr.indexOf(finded[i].getFuksziCod());
				if(index < 0)
					continue;
				else
					finded[i].setCheck_bx(true);
					finded[i].setSirSk(sirSk08Arr.get(index)+"");
					
				fmDao.findMst03(finded[i]); //2004/06/23 add
			}
	

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

//	-----  登録処理  --------------------------------------------------------------

	public FzkHinKsiResult doInsert(FzkHinKsiVO fmVO)throws SQLException{

		FzkHinKsiDAO fmDao = null;
		FzkHinKsiResult result = null;
		
		boolean endTransaction = false;
		boolean error = false;
		boolean delete_flg = false;

		try{
			fmDao = new FzkHinKsiDAO();
			fmDao.setAutoCommit(false);  //DBのトランザクション開始
			
			//登録日時
			DateUtils dateUtils = new DateUtils();
			int upddte = dateUtils.getDate6Int();
			int updjkk = dateUtils.getTime6Int();


			//まず検索
			int returnCode = fmDao.selectForUpdate(fmVO);
			switch(returnCode){
				case FzkHinKsiDAO.NOT_EXIST://まだない
					result = new FzkHinKsiResult(fmVO,true,"");
					break;					
				case FzkHinKsiDAO.NOT_MODIFIED://すでにある
				case FzkHinKsiDAO.MODIFIED:
					result = new FzkHinKsiResult(fmVO,false,EXIST);
					error = true;
					break;					
				case FzkHinKsiDAO.LOGICAL_DELETE://論理削除レコードが残ってる
					result = new FzkHinKsiResult(fmVO,true,"");
					delete_flg = true;
					break;					
			}
			
			  if(!error){
				//存在チェック
				result = doCheck_exist(fmDao,fmVO);
					if(result != null && !result.isSuccess()){
						error = true;
					}
			  }

			  if(error){
				  fmDao.rollback();
				  endTransaction = true;
				  fmDao.close();
				  return result;
			  }

			  //ソート
			  doSort(fmVO);
			  
			  //登録実行
			  fmDao.insert(fmVO,upddte,updjkk);
	
			  fmDao.commit();
			  endTransaction = true;
	
			  fmVO.setUpdDte(upddte);
			  fmVO.setUpdJkk(updjkk);


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
					  

//	-----  更新処理  --------------------------------------------------------------

	public FzkHinKsiResult doUpdate(FzkHinKsiVO fmVO)throws SQLException{

		  FzkHinKsiDAO fmDao = null;
		  FzkHinKsiResult result = null;
		
		  boolean endTransaction = false;
		  boolean error = false;

		  try{
			  fmDao = new FzkHinKsiDAO();
			  fmDao.setAutoCommit(false);  //DBのトランザクション開始
			
			  //更新日時
			  DateUtils dateUtils = new DateUtils();
			  int upddte = dateUtils.getDate6Int();
			  int updjkk = dateUtils.getTime6Int();


			  //まず検索
				result = doSelectForUpdate(fmDao,fmVO);
				if(result != null && !result.isSuccess()){
					error = true;
				}

			  if(!error){
			  	//存在チェック
				result = doCheck_exist(fmDao,fmVO);

				if(result != null && !result.isSuccess()){
					error = true;
				}

			  }

			  if(error){
				  fmDao.rollback();
				  endTransaction = true;
				  fmDao.close();
				  return result;
			  }

				//ソート
				doSort(fmVO);

			  //更新実行
			  fmDao.update(fmVO,upddte,updjkk);
	
			  fmDao.commit();
			  endTransaction = true;
	
			  fmVO.setUpdDte(upddte);
			  fmVO.setUpdJkk(updjkk);


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
					  
//	-----  削除処理  --------------------------------------------------------------

	  public FzkHinKsiResult doDelete(FzkHinKsiVO fmVO)throws SQLException{

		  FzkHinKsiDAO fmDao = null;
		  FzkHinKsiResult result = null;
		
		  boolean endTransaction = false;
		  boolean error = false;

		  try{
			  fmDao = new FzkHinKsiDAO();
			  fmDao.setAutoCommit(false);  //DBのトランザクション開始
			
			  //削除日時
			  DateUtils dateUtils = new DateUtils();
			  int upddte = dateUtils.getDate6Int();
			  int updjkk = dateUtils.getTime6Int();

			  //まず検索
				result = doSelectForUpdate(fmDao,fmVO);

				if(result != null && !result.isSuccess()){
					error = true;
				}
				
			  if(error){
				  fmDao.rollback();
				  endTransaction = true;
				  fmDao.close();
				  return result;
			  }

			  //削除実行
			  fmDao.delete(fmVO,upddte,updjkk);
	
			  fmDao.commit();
			  endTransaction = true;
	
			  fmVO.setUpdDte(upddte);
			  fmVO.setUpdJkk(updjkk);


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
					  

	/**
	 * selectForUpdate  更新*削除が呼ぶ
	 * 
	 */

	private FzkHinKsiResult doSelectForUpdate(FzkHinKsiDAO fmDao,FzkHinKsiVO fmVO)throws SQLException{
		FzkHinKsiResult result = null;

		try{
			boolean error = false;
			
			int returnCode = fmDao.selectForUpdate(fmVO);
					
			switch(returnCode){
				case FzkHinKsiDAO.NOT_MODIFIED:
					result = new FzkHinKsiResult(fmVO,true,"");
					break;					
				case FzkHinKsiDAO.MODIFIED:
					result = new FzkHinKsiResult(fmVO,false,ANOTHER_USER_UPDATE);
					break;					
				case FzkHinKsiDAO.NOT_EXIST:
					result = new FzkHinKsiResult(fmVO,false,NOT_EXIST);
					break;						
				case FzkHinKsiDAO.LOGICAL_DELETE:
					result = new FzkHinKsiResult(fmVO,false,LOGICAL_DELETE);
					break;

			}

		}catch(SQLException sqle){
			throw sqle;
		}
		return result;
	}

	/**
	 * 
	 * 存在チェック
	 * 
	 */
	private FzkHinKsiResult doCheck_exist(FzkHinKsiDAO fmDao,FzkHinKsiVO fmVO)throws SQLException{
		boolean error = false;
		FzkHinKsiResult result = null;

		ArrayList fukSziCodArr = fmVO.get08FuksziCodArr();
		ArrayList sirSkCodArr = fmVO.get08SirSkCodArr();
		ArrayList line_Arr = fmVO.getLineArr();
		ArrayList errors_fuk = new ArrayList();
		ArrayList errors_sir = new ArrayList();
		fmVO.clearBunCod();
		for(int i = 0; i < fukSziCodArr.size();i++){
			if(!fmDao.hasFukSziCod(fmVO,fukSziCodArr.get(i)+"")){
				// 副資材コードが存在しなかったら
				errors_fuk.add(line_Arr.get(i));
				error = true;
			}else{
				errors_fuk.add(-1+"");
			}

			if(!fmDao.hassirSkCod(fmVO,sirSkCodArr.get(i)+"")){
				// 仕入先コードが存在しなかったら
				errors_sir.add(line_Arr.get(i));
				error = true;
			}else{
				errors_sir.add(-1+"");
			}
		  }
				  
		if(error){
			result = new FzkHinKsiResult(fmVO,false,COD_NOT_EXIST);
			result.setMap(FzkHinKsiResult.KEY_FUKSZICOD,errors_fuk);
			result.setMap(FzkHinKsiResult.KEY_SIRSKCOD,errors_sir);
		}else{
			result = new FzkHinKsiResult(fmVO,true,"");
		}

		return result;
	}

   /**
	* @param fmVO
	*/
	private void doSort(FzkHinKsiVO fmVO) {
		ArrayList fuk_arr = fmVO.get08FuksziCodArr();
		ArrayList sir_arr = fmVO.get08SirSkCodArr();
		ArrayList bun_arr = fmVO.getBunCodArr();
		TreeMap tm = new TreeMap();
		
		for(int i = 0; i < fuk_arr.size();i++){
			HashMap map = new HashMap();
			map.put("fuk_cod",fuk_arr.get(i));
			map.put("sir_cod",sir_arr.get(i));
			map.put("bun_cod",bun_arr.get(i));
			
			tm.put(fuk_arr.get(i),map);
		}
		
		ArrayList map_arr = new ArrayList();
		HashMap map_bun1 = new HashMap();
		Iterator iter = tm.values().iterator(); //昇順
		
		while (iter.hasNext()) {
			HashMap map = (HashMap)iter.next();
			if(map_bun1.isEmpty() && map.get("bun_cod").toString().equals("1"))
				map_bun1 = map;
			else
				map_arr.add(map);			
		}
		if (!map_bun1.isEmpty()) {
			map_arr.add(0,map_bun1);
		}

		StringBuffer sb_fukSziCod = new StringBuffer();
		StringBuffer sb_sirSkCod = new StringBuffer();
		for(int i = 0; i < map_arr.size();i++){	
			HashMap map = (HashMap)map_arr.get(i);		
			sb_fukSziCod.append(map.get("fuk_cod"));	
			sb_sirSkCod.append(StringUtils.rpad((String) map.get("sir_cod"),6," "));
		}
		fmVO.setFuksziCod08String(sb_fukSziCod.toString());
		fmVO.setSirSk08String(sb_sirSkCod.toString());

	}
		
}

