/**
* IndicatePrintResult  発注書出力指示結果クラス
*
*	作成日    2003/04/23
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理結果を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.indicate.indicateprint;

import java.util.*;

public class IndicatePrintResult {

		private IndicatePrintVO fmVO;
		private boolean success;
		private String desc;
		private ArrayList finded_arr;
		
		public HashMap map = null;
		public static final String KEY_HACCOD = "haccod";
		
		public IndicatePrintResult(IndicatePrintVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		public IndicatePrintVO getVO(){
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
		
		//検索結果の配列を取得する
		public ArrayList getFinded_arr(){
			return finded_arr;		
		}
		//検索結果の配列をセットする
		public void setFinded_arr(ArrayList finded){
			finded_arr = finded;		
		}
		


	
}


