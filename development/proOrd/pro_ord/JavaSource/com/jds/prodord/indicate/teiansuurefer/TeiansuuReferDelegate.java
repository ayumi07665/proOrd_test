/**
* TeiansuuReferDelegate  提案数照会指示実行クラス
*
*	作成日    2003/02/20
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    提案数照会指示を実行するクラス。
*
*	[変更履歴]
*		  2006/05/10（ＮＩＩ）田端 康教
*			・Ｋ社のランク条件変更対応
*/

package com.jds.prodord.indicate.teiansuurefer;


import com.jds.prodord.common.SystemException;

import java.sql.*;
import java.util.*;


public class TeiansuuReferDelegate {

	/**
	 * 提案数照会指示
	 * 
	 */
	public TeiansuuReferResult doTeianSyokaiShiji(TeiansuuReferVO fmVO)throws SQLException{
		TeiansuuReferDAO fmDao = null;
		TeiansuuReferResult result = new TeiansuuReferResult(fmVO,true,"");
		
		

		boolean endTransaction = false;
		try{
			fmDao = new TeiansuuReferDAO();
			fmDao.setAutoCommit(false); //DBのトランザクション開始

			
			boolean error = false;
			ArrayList errors_kig = new ArrayList();
			ArrayList kig_arr = fmVO.getKigBng_arr();
			
			//品番存在チェック
			for(int i = 0; i < kig_arr.size(); i++){
				int errtype_kig = fmDao.hasKigBng(fmVO,kig_arr.get(i)+"");
				if(errtype_kig != TeiansuuReferDAO.EXIST){
					String[] err = {String.valueOf(i+1),errtype_kig+""};//[0]：エラーのあるindex,[1]：エラーの種類
					errors_kig.add(err);
				    error = true;
			    }
			}
			//エラーがあれば
			if(error){
				result = new TeiansuuReferResult(fmVO,false,"");
				if(errors_kig.size() > 0){
					result.setMap(TeiansuuReferResult.KEY_KIGBNG,errors_kig);
				}				
			}
			//エラーがなければ
			if(!error){
				//正常に成功
				result = new TeiansuuReferResult(fmVO,true,"");
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
		return result;
		
	}

}

