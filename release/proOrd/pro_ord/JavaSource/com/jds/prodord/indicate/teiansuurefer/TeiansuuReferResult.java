/**
* TeiansuuReferResult  提案数照会指示結果クラス
*
*	作成日    2003/02/20
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理結果を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.indicate.teiansuurefer;

import java.util.*;

public class TeiansuuReferResult {

		private TeiansuuReferVO fmVO;
		private boolean success;
		private String desc;
		
		public HashMap map = null;
		final static String KEY_KAISKBCOD = "kai";
		final static String KEY_KIGBNG = "kig";
		
		public TeiansuuReferResult(TeiansuuReferVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		public TeiansuuReferVO getVO(){
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

