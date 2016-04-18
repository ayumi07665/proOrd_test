/**
* IkkatsuReferResult  一括照会画面 処理結果クラス
*
*	作成日    2003/03/31
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    処理結果を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.reference.ikkatsurefer;

public class IkkatsuReferResult {


		private IkkatsuReferVO fmVO;
		private boolean success;
		private String desc;
		
		private String syrsuu = "";
		
		public IkkatsuReferResult(IkkatsuReferVO fmVO,boolean success,String desc){
			this.fmVO = fmVO;
			this.success = success;
			this.desc = desc;
		}
		public IkkatsuReferVO getVO(){
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
			
			this.syrsuu = s;
			
		}
		public String getSyrSuu(){
			
			return this.syrsuu;
			
		}

	
}


