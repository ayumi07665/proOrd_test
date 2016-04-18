/**
* NyukoSuuAdjustResult  入庫数調整結果クラス
*
*	作成日    2003/09/30
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理結果を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.nyukosuuadjust;


public class NyukoSuuAdjustResult {

		private NyukoSuuAdjustVO fmVO;
		private boolean success;
		private String desc;
		
		public NyukoSuuAdjustResult(NyukoSuuAdjustVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		
		public NyukoSuuAdjustVO getVO(){
			return fmVO;
		}
		public boolean isSuccess(){
			return success;
		}
		public String getDescription(){
			return desc;
		}
		
}