/**
* ManualOrderResult  マニュアル発注指示結果クラス
*
*	作成日    2003/04/25
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理結果を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.indicate.manualorder;

import java.util.*;

public class ManualOrderResult {


		private ManualOrderVO fmVO;
		private boolean success;
		private String desc;
		
		public HashMap map = null;
		final static String KEY_KAISKBCOD = "kai";
		final static String KEY_KIGBNG = "kig";
		
		public ManualOrderResult(ManualOrderVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		public ManualOrderVO getVO(){
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



