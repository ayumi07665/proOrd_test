/**
* ArticleNumberResult  品番マスターメンテナンス結果クラス
*
*	作成日    2003/08/25
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    処理結果を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.articlenumber;

import java.util.*;

public class ArticleNumberResult {

		private ArticleNumberVO fmVO;
		private boolean success;
		private String desc;
		
		public ArrayList err = new ArrayList();
		final static String NOTEXIST_PRS = "プレスメーカーコード";
		final static String NOTEXIST_JYT = "受託プレスメーカーコード";
		final static String NOTEXIST_FUK = "副資材メーカーコード";
		
//==========================================================================================

		public ArticleNumberResult(ArticleNumberVO fmVO,boolean success,String desc){

			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}

		public ArticleNumberVO getVO(){
			return fmVO;
		}

		public boolean isSuccess(){
			return success;
		}

		public String getDescription(){
			return desc;
		}

		//エラーのある項目の格納されたArrayListを取得するメソッド
		public ArrayList getError(){
			return err;
		}

		//ArrayListにエラーのある項目のリストをセットするメソッド
		public void setError(ArrayList arr){
			err = arr;
		}
		
	
}

