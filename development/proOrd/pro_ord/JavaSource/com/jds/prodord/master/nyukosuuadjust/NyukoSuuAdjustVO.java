/**
* NyukoSuuAdjustVO  入庫数調整バリューオブジェクトクラス
*
*	作成日    2003/09/30
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    画面から取得したデータを格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.nyukosuuadjust;

import java.util.*;
import com.jds.prodord.common.*;

public class NyukoSuuAdjustVO  {

	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;

	private String hacCod_H = "";
	private int hacDteFrm_H = 0;
	private int hacDteTo_H = 0;
	private int nkiFrm_H = 0;
	private int nkiTo_H = 0;
	private ArrayList kigBng_arr_H = new ArrayList();
	
	private String daiKaiSkbCod = "";
	private String kaiSkbCod = "";
	private String syrSuu = "";
	private String seqNo = "";
	private String hbiDte = "";
	
	private String hacCod = "";
	private String kbn   = "";
	private String kigBng = "";
	private String hjiHnb = "";
	private int hacSyoDte = 0;
	private String hacSyoBng = "";
	private String gyoBng = "";		
	private String hacSuu = "";  
	private String nyoSuu = "";
	private String knuSgn = "";
	private String nki = "";

	private int updDte;
	private int updJkk;
	

    //ユーザーの代表会社識別コード
    public String getQueryDaiKaiSkbCod() {
        return this.queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String queryDaiKaiSkbCod) {
        this.queryDaiKaiSkbCod = queryDaiKaiSkbCod;
    }


    //ユーザーの会社識別コード
	public String getQueryKaiSkbCod() {
		return queryKaiSkbCod;
	}
	public void setQueryKaiSkbCod(String queryKaiSkbCod) {
		this.queryKaiSkbCod = queryKaiSkbCod;
	}

    
    //会社識別コードリスト
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }

    
//-------------------------------------------------------------検索条件

	
	/**
	 * Gets the hacCod_H
	 * @return Returns a String
	 */
	public String getHacCod_H() {
		return hacCod_H;
	}
	/**
	 * Sets the hacCod_H
	 * @param hacCod_H The hacCod_H to set
	 */
	public void setHacCod_H(String hacCod_H) {
		this.hacCod_H = hacCod_H;
	}

	/** 
     * Gets the hacDteFrm_H
	 * @return Returns a int
     */
	public int getHacDteFrm_H() {
		return hacDteFrm_H;
	}
	/**
	 * Sets the hacDteFrm_H
	 * @param date The hacDteFrm_H to set
	 */
	public void setHacDteFrm_H(NyukoSuuAdjustForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hacDteFrm_H = 0;
		else
			hacDteFrm_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
	/** 
     * Gets the hacDteTo_H
	 * @return Returns a int
     */
	public int getHacDteTo_H() {
		return hacDteTo_H;
	}
	/**
	 * Sets the hacDteTo_H
	 * @param date The hacDteTo_H to set
	 */
	public void setHacDteTo_H(NyukoSuuAdjustForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hacDteTo_H = 0;
		else
			hacDteTo_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
	
	/** 
     * Gets the nkiFrm_H
	 * @return Returns a int
     */
	public int getNkiFrm_H() {
		return nkiFrm_H;
	}
	/**
	 * Sets the nkiFrm_H
	 * @param date The hacDteFrm_H to set
	 */
	public void setNkiFrm_H(NyukoSuuAdjustForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			nkiFrm_H = 0;
		else
			nkiFrm_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
	/** 
     * Gets the nkiTo_H
	 * @return Returns a int
     */
	public int getNkiTo_H() {
		return nkiTo_H;
	}
	/**
	 * Sets the nkiTo_H
	 * @param date The nkiTo_H to set
	 */
	public void setNkiTo_H(NyukoSuuAdjustForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			nkiTo_H = 0;
		else
			nkiTo_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
	
	/**
	 * Gets the kigBng_arr_H
	 * @return Returns a ArrayList
	 */
	public ArrayList getKigBng_arr_H() {
		return kigBng_arr_H;
	}
	/**
	 * Sets the kigBng_arr_H
	 * @param kigBng_arr_H The kigBng_arr_H to set
	 */
	public void setkigBng_arr_H(ArrayList kigBng_arr_H) {
		this.kigBng_arr_H = kigBng_arr_H;
	}

//------------------------------------------------------------------- 行


	/**
	 * Gets the daiKaiSkbCod
	 * @return Returns a String
	 */
	public String getDaiKaiSkbCod() {
		return daiKaiSkbCod;
	}
	/**
	 * Sets the daiKaiSkbCod
	 * @param daiKaiSkbCod The daiKaiSkbCod to set
	 */
	public void setDaiKaiSkbCod(String daiKaiSkbCod) {
		this.daiKaiSkbCod = daiKaiSkbCod;
	}


	/**
	 * Gets the kaiSkbCod
	 * @return Returns a String
	 */
	public String getKaiSkbCod() {
		return kaiSkbCod;
	}
	/**
	 * Sets the kaiSkbCod
	 * @param kaiSkbCod The kaiSkbCod to set
	 */
	public void setKaiSkbCod(String kaiSkbCod) {
		this.kaiSkbCod = kaiSkbCod;
	}


	/**
	 * Gets the syrSuu
	 * @return Returns a String
	 */
	public String getSyrSuu() {
		return syrSuu;
	}
	/**
	 * Sets the syrSuu
	 * @param syrSuu The syrSuu to set
	 */
	public void setSyrSuu(String syrSuu) {
		this.syrSuu = syrSuu;
	}


	/**
	 * Gets the seqNo
	 * @return Returns a String
	 */
	public String getSeqNo() {
		return seqNo;
	}
	/**
	 * Sets the seqNo
	 * @param seqNo The seqNo to set
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}


	/**
	 * Gets the hbiDte
	 * @return Returns a String
	 */
	public String getHbiDte() {
		return hbiDte;
	}
	/**
	 * Sets the hbiDte
	 * @param hbiDte The hbiDte to set
	 */
	public void setHbiDte(String hbiDte) {
		this.hbiDte = hbiDte;
	}
	
//--------------------------------------------------------------------------行  表示項目

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
	 * Gets the hjiHnb
	 * @return Returns a String
	 */
	public String getHjiHnb() {
		return hjiHnb;
	}
	/**
	 * Sets the hjiHnb
	 * @param hjiHnb The hjiHnb to set
	 */
	public void setHjiHnb(String hjiHnb) {
		this.hjiHnb = hjiHnb;
	}

	/**
	 * Gets the hacSyoDte
	 * @return Returns a String
	 */
	public int getHacSyoDte() {
		return hacSyoDte;
	}
	/**
	 * Sets the hacSyoDte
	 * @param hacSyoDte The hacSyoDte to set
	 */
	public void setHacSyoDte(int hacSyoDte) {
		this.hacSyoDte = hacSyoDte;
	}
	public void setHacSyoDte(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			hacSyoDte = 0;
		else
			hacSyoDte = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
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
	
	/**
	 * Gets サンプル区分
	 * @return Returns a String
	 */
	public String getSmpKbn(){
		String smpKbn = DataFormatUtils.getSmpKbn(kbn, hbiDte, String.valueOf(hacSyoDte));
		return smpKbn;
	}

	/**
	 * Gets the updDte
	 * @return Returns a int
	 */
	public int getUpdDte() {
		return updDte;
	}
	/**
	 * Sets the updDte
	 * @param updDte The updDte to set
	 */
	public void setUpdDte(int updDte) {
		this.updDte = updDte;
	}

	/**
	 * Gets the updJkk
	 * @return Returns a int
	 */
	public int getUpdJkk() {
		return updJkk;
	}
	/**
	 * Sets the updJkk
	 * @param updJkk The updJkk to set
	 */
	public void setUpdJkk(int updJkk) {
		this.updJkk = updJkk;
	}


}

