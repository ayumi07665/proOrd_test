/**
* KetSziTblFormRow  形態別構成資材マスターメンテナンスフォーム行クラス
*
*	作成日    2004/01/30
*	作成者    （ＮＩＩ）森
* 処理概要    Httpリクエストとリクエストの結果のうちの繰り返し項目を格納するクラス。
*
*	[変更履歴]
*        2004/
*/

package com.jds.prodord.master.ketszitbl;

import java.util.*;


public class KetSziTblFormRow implements java.io.Serializable{
	
	public KetSziTblFormRow(){
		for(int i = 0; i < fuksziCod.length; i++){
			fuksziCod[i] = new FuksziCod();
		}
	}
	

	private String ketCod = "";
	private String ketNm   = "";
	private String ketNm2 = "";
	
	private FuksziCod[] fuksziCod = new FuksziCod[40];

	private boolean check_bx = false;

	/**
	 * チェックボックスのセット
	 * @param check_bx チェックボックス
	 */
	 public void setCheck_bx(boolean check_bx) {
		 this.check_bx = check_bx;
	 }

	/**
	* チェックボックスの取得
	* 
	* @return チェックボックス
	*/
	 public boolean getCheck_bx() {
		 return this.check_bx;
	 }


	/**
	 * Gets the ketCod
	 * @return Returns a String
	 */
	public String getKetCod() {
		return ketCod;
	}
	/**
	 * Sets the ketCod
	 * @param ketCod The ketCod to set
	 */
	public void setKetCod(String ketCod) {
		this.ketCod = ketCod;
	}


	/**
	 * Gets the ketNm
	 * @return Returns a String
	 */
	public String getKetNm() {
		return ketNm;
	}
	/**
	 * Sets the ketNm
	 * @param ketNm The ketNm to set
	 */
	public void setKetNm(String ketNm) {
		this.ketNm = ketNm;
	}


	/**
	 * Gets the ketNm2
	 * @return Returns a String
	 */
	public String getKetNm2() {
		return ketNm2;
	}
	/**
	 * Sets the ketNm2
	 * @param ketNm2 The ketNm2 to set
	 */
	public void setKetNm2(String ketNm2) {
		this.ketNm2 = ketNm2;
	}



	/**
	 * @return
	 */
	public FuksziCod[] getFuksziCod() {
		return fuksziCod;
	}

	/**
	 * @param cods
	 */
	public void setFuksziCod(FuksziCod[] cods) {
		fuksziCod = cods;
	}
	
	public void setFuksziCodArr(ArrayList cods) {
		for(int i = 0;i<cods.size();i++){
			fuksziCod[i].setVal(cods.get(i).toString());
		}
	}
	
	public void clear(){
		check_bx = false;
		ketCod = "";
		ketNm = "";
		ketNm2 = "";
		for(int i = 0;i<fuksziCod.length;i++){
			fuksziCod[i].setVal("");
		}
		
	}
	
	/**副資材コードクラス*/
	public class FuksziCod {
		
		private String fuksziCod = "";
		
		/**
		 * @return
		 */
		public String getVal() {
			return fuksziCod;
		}

		/**
		 * @param string
		 */
		public void setVal(String string) {
			fuksziCod = string;
		}

	}

	public ArrayList getFuksziCodArr(){
		ArrayList fukcodarr = new ArrayList();
		for(int i = 0; i<fuksziCod.length; i++){
			try{
				fukcodarr.add(fuksziCod[i].getVal());
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return fukcodarr;
	}

}
