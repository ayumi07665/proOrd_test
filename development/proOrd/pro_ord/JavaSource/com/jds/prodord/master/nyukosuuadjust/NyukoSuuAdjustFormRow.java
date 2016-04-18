/**
* NyukoSuuAdjustFormRow  入庫数調整フォーム行クラス
*
*	作成日    2003/09/30
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの結果のうちの繰り返し項目を格納するクラス。
*
*	[変更履歴]
*        2003/
*/

package com.jds.prodord.master.nyukosuuadjust;



public class NyukoSuuAdjustFormRow implements java.io.Serializable{
	
	private String hacCod = "";
	private String kbn   = "";
	private String kigBng = "";
	private String hacSyoDte = "";
	private String hacSyoBng = "";
	private String gyoBng = "";		
	private String hacSuu = "";  
	private String nyoSuu = "";
	private String knuSgn = "";
	private String nki = "";

    /**
	 * Gets 処理対象  … 完納サインが１または２ならば処理対象とする
	 * @return Returns a boolean
	 */
    public boolean getExecute(){
    	return (knuSgn.equals("1") || knuSgn.equals("2"));
    }

	/**
	 * Gets the hacCod
	 * @return Returns a String
	 */
	public String getHacCod() {
		return hacCod;
	}
	/**
	 * Sets the hacCod
	 * @param hacCod The hacCod to set
	 */
	public void setHacCod(String hacCod) {
		this.hacCod = hacCod;
	}


	/**
	 * Gets the kbn
	 * @return Returns a String
	 */
	public String getKbn() {
		return kbn;
	}
	/**
	 * Sets the kbn
	 * @param kbn The kbn to set
	 */
	public void setKbn(String kbn) {
		this.kbn = kbn;
	}


	/**
	 * Gets the kigBng
	 * @return Returns a String
	 */
	public String getKigBng() {
		return kigBng;
	}
	/**
	 * Sets the kigBng
	 * @param kigBng The kigBng to set
	 */
	public void setKigBng(String kigBng) {
		this.kigBng = kigBng;
	}


	/**
	 * Gets the hacSyoDte
	 * @return Returns a String
	 */
	public String getHacSyoDte() {
		return hacSyoDte;
	}
	/**
	 * Sets the hacSyoDte
	 * @param hacSyoDte The hacSyoDte to set
	 */
	public void setHacSyoDte(String hacSyoDte) {
		this.hacSyoDte = hacSyoDte;
	}


	/**
	 * Gets the hacSyoBng
	 * @return Returns a String
	 */
	public String getHacSyoBng() {
		return hacSyoBng;
	}
	/**
	 * Sets the hacSyoBng
	 * @param hacSyoBng The hacSyoBng to set
	 */
	public void setHacSyoBng(String hacSyoBng) {
		this.hacSyoBng = hacSyoBng;
	}


	/**
	 * Gets the gyoBng
	 * @return Returns a String
	 */
	public String getGyoBng() {
		return gyoBng;
	}
	/**
	 * Sets the gyoBng
	 * @param gyoBng The gyoBng to set
	 */
	public void setGyoBng(String gyoBng) {
		this.gyoBng = gyoBng;
	}


	/**
	 * Gets the hacSuu
	 * @return Returns a String
	 */
	public String getHacSuu() {
		return hacSuu;
	}
	/**
	 * Sets the hacSuu
	 * @param hacSuu The hacSuu to set
	 */
	public void setHacSuu(String hacSuu) {
		this.hacSuu = hacSuu;
	}


	/**
	 * Gets the nyoSuu
	 * @return Returns a String
	 */
	public String getNyoSuu() {
		return nyoSuu;
	}
	/**
	 * Sets the nyoSuu
	 * @param nyoSuu The nyoSuu to set
	 */
	public void setNyoSuu(String nyoSuu) {
		this.nyoSuu = nyoSuu;
	}


	/**
	 * Gets the knuSgn
	 * @return Returns a String
	 */
	public String getKnuSgn() {
		return knuSgn;
	}
	/**
	 * Sets the knuSgn
	 * @param knuSgn The knuSgn to set
	 */
	public void setKnuSgn(String knuSgn) {
		this.knuSgn = knuSgn;
	}
	
	/**
	 * Gets the nki
	 * @return Returns a String
	 */
	public String getNki() {
		return nki;
	}
	/**
	 * Sets the nki
	 * @param nki The nki to set
	 */
	public void setNki(String nki) {
		this.nki = nki;
	}


    
	public void clear(){
		hacCod = "";
		kbn   = "";
		kigBng = "";
		hacSyoDte = "";
		hacSyoBng = "";
		gyoBng = "";		
		hacSuu = "";  
		nyoSuu = "";
		knuSgn = "";
		nki = "";		
	}
	
	

}
