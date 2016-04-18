/**
* PrsOrderResult  プレス・副資材発注画面 処理結果クラス
*
*	作成日    2003/04/28
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理結果を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/


package com.jds.prodord.order.prsorder;


public class PrsOrderResult {


		private PrsOrderVO fmVO;
		private boolean success;
		private String desc;
		
		private String syrSuu = "";
		
		public PrsOrderResult(PrsOrderVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}


		public PrsOrderVO getVO(){
			return fmVO;
		}
		public boolean isSuccess(){
			return success;
		}
		public String getDescription(){
			return desc;
		}


		//処理回数
		public void setSyrSuu(String s){
			
			this.syrSuu = s;
			
		}
		public String getSyrSuu(){
			
			return this.syrSuu;
			
		}

	
}


