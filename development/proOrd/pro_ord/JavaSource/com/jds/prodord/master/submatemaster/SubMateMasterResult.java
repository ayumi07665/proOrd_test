/**
* SubMateMasterResult  副資材マスターメンテナンス  結果クラス
*
*	作成日    2003/06/24
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    処理結果を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.submatemaster;

import java.util.*;

public class SubMateMasterResult {

		private SubMateMasterVO fmVO;
		private boolean success;
		private String desc;
		
		public HashMap map = null;
		final static String KEY_KAISKBCOD = "kai";
		
//==========================================================================================

		public SubMateMasterResult(SubMateMasterVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		public SubMateMasterVO getVO(){
			
			return fmVO;
		}
		public SubMateMasterVO getOutSubMateMaster(){
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


