/**
* IndicatePrintVO  発注書出力指示バリューオブジェクトクラス
*
*	作成日    2003/04/23
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    画面から取得したデータを格納するクラス。
*
*	[変更履歴]
*       2003/06/05（ＮＩＩ）蛭田 敬子
* 			・「再出力」の条件項目（発注書日付、発注先コード）追加。
* 		2005/05/25（ＮＩＩ）蛭田 
* 			・発注書一括出力対応（ユーザーＩＤの追加）
* 		2005/09/09（ＮＩＩ）蛭田
* 			・VAP社副資材ｺｰﾄﾞ複数対応（副資材コードの追加）
*/

package com.jds.prodord.indicate.indicateprint;

import java.util.*;
public class IndicatePrintVO {

	private String command = "";

	private String daiKaiSkbCod = "";
	private String kaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
		
	private String hacSyoBng_frm = "";
	private String hacSyoBng_to = "";
	private int hacSyoDte_frm = 0;
	private int hacSyoDte_to = 0;
	private String hacCod_K = "";

	private String syrSuu = "";
	private String seqNo = "";
	
	private ArrayList hacCod_arr = new ArrayList();

    private String hacSyoDte = "";
    private String hacSyoBng = "";
    private String gyoBng = "";
    private String hacCod = "";

    private String fukSziNm = "";//副資材名称
    
    private String usrId = "";
	private String fukSziCod = "";
    
    /**
     * コマンドのセット
     * 
     * @param command コマンド
     */
    public void setCommand(String command) {

        this.command = command;

    }


    /**
     * コマンドの取得
     * 
     * @return コマンド
     */
    public String getCommand() {

        return this.command;

    }

    /**
     * 代表会社識別コードのセット
     * 
     * @param daiKaiSkbCod 表会社識別コード
     */
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {

        this.daiKaiSkbCod = daiKaiSkbCod;

    }


    /**
     * 代表会社識別コードの取得
     * 
     * @return 代表会社識別コード
     */
    public String getDaiKaiSkbCod() {

        return this.daiKaiSkbCod;

    }
    
    /**
     * 会社識別コードのセット
     * 
     * @param kaiSkbCod 会社識別コード
     */
    public void setKaiSkbCod(String kaiSkbCod) {

        this.kaiSkbCod = kaiSkbCod;

    }


    /**
     * 会社識別コードの取得
     * 
     * @return 会社識別コード
     */
    public String getKaiSkbCod() {

        return this.kaiSkbCod;

    }
    
    //会社識別コードリスト
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }
    

//--------------------------------------------------------------------------

    /**
     * 発注書番号Fromのセット
     * 
     * @param hacSyoBngfrm 発注書番号From
     */
    public void setHacSyoBng_frm(String hacSyoBng_frm) {

        this.hacSyoBng_frm = hacSyoBng_frm;

    }


    /**
     * 発注書番号Fromの取得
     * 
     * @return 発注書番号From
     */
    public String getHacSyoBng_frm() {

        return this.hacSyoBng_frm;

    }


    /**
     * 発注書番号Toのセット
     * 
     * @param hacSyoBng_to 発注書番号To
     */
    public void setHacSyoBng_to(String hacSyoBng_to) {

        this.hacSyoBng_to = hacSyoBng_to;

    }


    /**
     * 発注書番号Toの取得
     * 
     * @return 発注書番号To
     */
    public String getHacSyoBng_to() {

        return this.hacSyoBng_to;

    }


    /**
     * 発注先コードarrのセット
     * 
     * @param hacCod_arr 発注先コードarr
     */
    public void setHacCod_arr(ArrayList hacCod_arr) {

        this.hacCod_arr = hacCod_arr;

    }


    /**
     * 発注先コードarrの取得
     * 
     * @return 発注先コードarr
     */
    public ArrayList getHacCod_arr() {

        return this.hacCod_arr;

    }
    
        /**
     * 発注先コードのセット
     * 
     * @param hacCod_K 発注先コード
     */
    public void setHacCod_K(String hacCod_K) {

        this.hacCod_K = hacCod_K;

    }


    /**
     * 発注先コードの取得
     * 
     * @return 発注先コード
     */
    public String getHacCod_K() {

        return this.hacCod_K;

    }
    
    
    /**
     * 発注日Fromのセット
     * 
     * @param hacSyoDte_frm 発注日To
     */
    public void setHacSyoDte_frm(int hacSyoDte_frm) {
        this.hacSyoDte_frm = hacSyoDte_frm;
    }
    public void setHacSyoDte_frm(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			hacSyoDte_frm = 0;
		else
			hacSyoDte_frm = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * 発注日Frmの取得
     * 
     * @return 発注日Frm
     */
    public String getHacSyoDte_frm() {
        return (this.hacSyoDte_frm == 0)? "" : this.hacSyoDte_frm+"";
    }

    /**
     * 発注日Toのセット
     * 
     * @param hacSyoDteTo_H 発注日To
     */
    public void setHacSyoDte_to(int hacSyoDte_to) {
        this.hacSyoDte_to = hacSyoDte_to;
    }
	public void setHacSyoDte_to(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			hacSyoDte_to = 0;
		else
			hacSyoDte_to = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * 発注日Toの取得
     * 
     * @return 発注日To
     */
    public String getHacSyoDte_to() {
        return (this.hacSyoDte_to == 0)? "" : this.hacSyoDte_to+"";
    }

    
    /**
	 * 処理回数の取得
	 * @return Returns a String
	 */
	public String getSyrSuu() {
		return syrSuu;
	}
	/**
	 * 処理回数のセット
	 * @param syrSuu The syrSuu to set
	 */
	public void setSyrSuu(String syrSuu) {
		this.syrSuu = syrSuu;
	}
	/**
	 * シーケンスNoの取得
	 * @return Returns a String
	 */
	public String getSeqNo() {
		return seqNo;
	}
	/**
	 * シーケンスNoのセット
	 * @param seqNo The seqNo to set
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

//---------------------------------------------------------------------------------------

    /**
     * 発注書日付のセット
     * 
     * @param hacSyoDte 発注書日付
     */
    public void setHacSyoDte(String hacSyoDte) {

        this.hacSyoDte = hacSyoDte;

    }


    /**
     * 発注書日付の取得
     * 
     * @return 発注書日付
     */
    public String getHacSyoDte() {

        return this.hacSyoDte;

    }


    /**
     * 発注書番号のセット
     * 
     * @param hacSyoBng 発注書番号
     */
    public void setHacSyoBng(String hacSyoBng) {

        this.hacSyoBng = hacSyoBng;

    }


    /**
     * 発注書番号の取得
     * 
     * @return 発注書番号
     */
    public String getHacSyoBng() {

        return this.hacSyoBng;

    }
    
    /**
     * 明細行番号のセット
     * 
     * @param gyoBng 明細行番号
     */
    public void setGyoBng(String gyoBng) {

        this.gyoBng = gyoBng;

    }


    /**
     * 明細行番号の取得
     * 
     * @return 明細行番号
     */
    public String getGyoBng() {

        return this.gyoBng;

    }
    
    
    /**
     * 発注先コードのセット
     * 
     * @param hacCod 発注先コード
     */
    public void setHacCod(String hacCod) {

        this.hacCod = hacCod;

    }


    /**
     * 発注先コードの取得
     * 
     * @return 発注先コード
     */
    public String getHacCod() {

        return this.hacCod;

    }
    
    /**
     * 副資材名称のセット
     * 
     * @param fukSziMkr 副資材名称
     */
    public void setFukSziNm(String fukSziNm) {

        this.fukSziNm = fukSziNm;

    }

    /**
     * 副資材名称の取得
     * 
     * @return 副資材名称
     */
    public String getFukSziNm() {

        return this.fukSziNm;

    }

	/**
	 * ユーザーＩＤの取得
	 * @return
	 */
	public String getUsrId() {
		return usrId;
	}

	/**
	 * ユーザーＩＤのセット
	 * @param string
	 */
	public void setUsrId(String string) {
		usrId = string;
	}

	/**
	 * 副資材コードの取得
	 * @return
	 */
	public String getFukSziCod() {
		return fukSziCod;
	}

	/**
	 * 副資材コードの設定
	 * @param string
	 */
	public void setFukSziCod(String string) {
		fukSziCod = string;
	}


	/**
	 *  デバッグ用にSystem.out.printlnでオブジェクトの内容を出力するためのメソッド
	 * @see java.lang.Object#toString()
	 */
	public String getVoData(){
		StringBuffer sb = new StringBuffer();
		String delim = ", ";

		sb.append(toString(daiKaiSkbCod, "daiKaiSkbCod") + delim);
		sb.append(toString(kaiSkbCod, "kaiSkbCod") + delim);
		sb.append(toString(hacSyoBng_frm, "hacSyoBng_frm") + delim);
		sb.append(toString(hacSyoBng_to, "hacSyoBng_to") + delim);
		sb.append(toString(String.valueOf(hacSyoDte_frm), "hacSyoDte_frm") + delim);
		sb.append(toString(String.valueOf(hacSyoDte_to), "hacSyoDte_to") + delim);
		sb.append(toString(hacCod_K, "hacCod_K") + delim);
		sb.append(toString(syrSuu, "syrSuu") + delim);  
		sb.append(toString(seqNo, "seqNo") + delim);

		sb.append(toString(hacSyoDte, "hacSyoDte") + delim);
		sb.append(toString(hacSyoBng, "hacSyoBng") + delim);
		sb.append(toString(gyoBng, "gyoBng") + delim);
		sb.append(toString(hacCod, "hacCod") + delim);
		sb.append(toString(fukSziNm, "fukSziNm") + delim); 
		sb.append(toString(usrId, "usrId") + delim);
		sb.append(toString(fukSziCod, "fukSziCod") + delim);  
		
		return sb.toString();
	}
	private String toString(Object o, String prmNm){
		return prmNm + "[" + o + "]";
	}


}

