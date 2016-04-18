/**
* KetRakVO  形態ランク別条件マスターメンテナンスバリューオブジェクトクラス
*
*	作成日    2003/05/02
*	作成者    （ＮＩＩ）今井 美季
* 処理概要    画面から取得したデータを格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.ketrak;

import java.util.*;
public class KetRakVO  {

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiSkbCod = new String();
	private String rank = new String();
	private String ketCod = new String();
	private String ssnRedTim = new String();
	private String minZaiSuu = new String();
	private String minRotSuu = new String();
	private String mrmSuu = new String();

	private int updDte;
	private int updJkk;
	

    //ユーザーの代表会社識別コード
    public String getDaiKaiSkbCod() {
        return this.daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {
        this.daiKaiSkbCod = daiKaiSkbCod;
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

    
//-------------------------------------------------------------入力エリア

    /**
     * 会社識別コードのセット
     * 
     * @param kaiSkbCod 会社識別コード
     */
    public String getKaiSkbCod() {

        return this.kaiSkbCod;

    }
   
    /**
     * 会社識別コードの取得
     * 
     * @return 会社識別コード
     */
    public void setKaiSkbCod(String kaiSkbCod) {

        this.kaiSkbCod = kaiSkbCod;

    }

    /**
     * ランクのセット
     * 
     * @param ランク
     */
	public void setRank(String rank) {
		this.rank = rank;
	}
    /**
     * ランクの取得
     * 
     * @return ランク
     */
	public String getRank() {
		return rank;
	}

    /**
     * 形態コードのセット
     * 
     * @param ketCod 形態コード
     */
	public void setKetCod(String ketCod) {
		this.ketCod = ketCod;
	}
    /**
     * 形態コードの取得
     * 
     * @return 形態コード
     */
	public String getKetCod() {
		return ketCod;
	}


//-------------------------------------------------------------------------

	/**
	 * Gets the ssnRedTim
	 * @return Returns a String
	 */
	public String getSsnRedTim() {
		return ssnRedTim;
	}
	/**
	 * Sets the ssnRedTim
	 * @param ssnRedTim The ssnRedTim to set
	 */
	public void setSsnRedTim(String ssnRedTim) {
		this.ssnRedTim = ssnRedTim;
	}


	/**
	 * Gets the minZaiSuu
	 * @return Returns a String
	 */
	public String getMinZaiSuu() {
		return minZaiSuu;
	}
	/**
	 * Sets the minZaiSuu
	 * @param minZaiSuu The minZaiSuu to set
	 */
	public void setMinZaiSuu(String minZaiSuu) {
		this.minZaiSuu = minZaiSuu;
	}


	/**
	 * Gets the minRotSuu
	 * @return Returns a String
	 */
	public String getMinRotSuu() {
		return minRotSuu;
	}
	/**
	 * Sets the minRotSuu
	 * @param minRotSuu The minRotSuu to set
	 */
	public void setMinRotSuu(String minRotSuu) {
		this.minRotSuu = minRotSuu;
	}


	/**
	 * Gets the mrmSuu
	 * @return Returns a String
	 */
	public String getMrmSuu() {
		return mrmSuu;
	}
	/**
	 * Sets the mrmSuu
	 * @param mrmSuu The mrmSuu to set
	 */
	public void setMrmSuu(String mrmSuu) {
		this.mrmSuu = mrmSuu;
	}



//-----------------------------------------------------------------------
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

