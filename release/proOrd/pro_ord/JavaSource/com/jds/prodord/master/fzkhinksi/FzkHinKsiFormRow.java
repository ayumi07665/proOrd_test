/**
* FzkHinKsiFormRow  付属品構成マスターメンテナンスフォーム行クラス
*
*	作成日    2004/02/12
*	作成者    （ＮＩＩ）森
* 処理概要    Httpリクエストとリクエストの結果のうちの繰り返し項目を格納するクラス。
*
*	[変更履歴]
*        2004/06/23（ＮＩＩ）蛭田
* 			・｢発注先名称｣追加
*/

package com.jds.prodord.master.fzkhinksi;

public class FzkHinKsiFormRow implements java.io.Serializable{
	
	private String fuksziCod = "";
	private String fuksziNm = "";
	private String sirSk = "";
	private String hacNm = ""; //2004/06/23 add
	
	private boolean check_bx = false;


	/**
	 * 副資材コードの取得
	 * @return
	 */
	public String getFuksziCod() {
		return fuksziCod;
	}
	/**
	 * 副資材コードのセット
	 * @param fuksziCod
	 */
	public void setFuksziCod(String fuksziCod) {
		this.fuksziCod = fuksziCod;
	}

	/**
	 * 副資材名の取得
	 * @return
	 */
	public String getFuksziNm() {
		return fuksziNm;
	}

	/**
	 * 副資材名のセット
	 * @param fuksziNm
	 */
	public void setFuksziNm(String fuksziNm) {
		this.fuksziNm = fuksziNm;
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
		this.sirSk = sirSk;
	}

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
	 * 発注先名称の取得
	 * @return
	 */
	public String getHacNm() {
		return hacNm;
	}

	/**
	 * 発注先名称のセット
	 * @param string
	 */
	public void setHacNm(String string) {
		hacNm = string;
	}




	public void clear(){
		check_bx = false;
		fuksziCod = "";
		fuksziNm = "";
		sirSk = "";
		hacNm = "";
		
	}
	


}
