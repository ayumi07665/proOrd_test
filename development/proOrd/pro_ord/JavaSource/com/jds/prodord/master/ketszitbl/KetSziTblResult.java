/**
* KetSziTblResult  形態別構成資材マスターテーブルメンテナンス結果クラス
*
*	作成日    2004/02/04
*	作成者    （ＮＩＩ）森
* 処理概要    処理結果を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.ketszitbl;

import java.util.*;

public class KetSziTblResult {


		private KetSziTblVO fmVO;
		private boolean success;
		private String desc;
		
		public HashMap map = null;
		final static String KEY_FUKSZICOD = "FUKSZICOD";
			
		public KetSziTblResult(KetSziTblVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		public KetSziTblVO getVO(){
			return fmVO;
		}
		public boolean isSuccess(){
			return success;
		}
		public String getDescription(){
			return desc;
		}
		//HashMapからエラーのある項目のindexを取得するメソッド
		public ArrayList getMap(String key){
			if(map == null || map.get(key) == null)
				return null;
			return (ArrayList)map.get(key);			
		}
		//HashMapにエラーのある項目とindexをセットするメソッド
		public void setMap(String key, ArrayList errIndex){
			if(map == null)
				map = new HashMap();
				
			map.put(key,errIndex);			
		}
		


	
}


