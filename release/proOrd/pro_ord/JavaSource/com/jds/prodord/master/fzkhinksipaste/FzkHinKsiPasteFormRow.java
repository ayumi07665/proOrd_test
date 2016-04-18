package com.jds.prodord.master.fzkhinksipaste;

/**
* FzkHinKsiPasteFormRow  付属品構成マスター一括貼り付けフォーム行クラス
*
*	作成日    2007/09/30
*	作成者    （ＮＩＩ）田中
* 処理概要    Httpリクエストとリクエストの結果のうちの繰り返し項目を格納するクラス。
*
*	[変更履歴]
*/

public class FzkHinKsiPasteFormRow implements java.io.Serializable{
	
	private String kigBng = "";
	private String fukSziCod = "";
	private String sirSk = "";

	/**
	 * 記号番号の取得
	 * @return
	 */
	public String getKigBng() {
		return kigBng;
	}
	/**
	 * 記号番号のセット
	 * @param kigBng
	 */
	public void setKigBng(String kigBng) {
		this.kigBng = kigBng.toUpperCase();
	}

	/**
	 * 副資材コードの取得
	 * @return
	 */
	public String getFukSziCod() {
		return fukSziCod;
	}
	/**
	 * 副資材コードのセット
	 * @param fuksziCod
	 */
	public void setFukSziCod(String fukSziCod) {
		this.fukSziCod = fukSziCod.toUpperCase();
	}

	/**
	 * 仕入先の取得
	 * @return
	 */
	public String getSirSk() {
		return sirSk;
	}
	/**
	 * 仕入先のセット
	 * @param string
	 */
	public void setSirSk(String sirSk) {
		this.sirSk = sirSk.toUpperCase();
	}


	public void clear(){
		kigBng = "";
		fukSziCod = "";
		sirSk = "";
	}
}
