/**
* OrderHistoryVO  発注履歴照会画面バリューオブジェクトクラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）蛭田 敬子
* 処理概要    品番マスター(FTBHIN01)、在庫テーブル(FTBZAI01)、発注履歴テーブル(FTBHAC02)、在庫日数マスター(FTBMST04)
* 			 副資材マスター(FTBFUK01)、形態ランク別条件マスター(FTBMST05)から取得したデータを格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
* 		2004/01/22　（ＮＩＩ）森
* 			・入庫数取消可能に修正
* 		2004/03/29 (NII) 森
* 			・品番マスタのテーブル名変更
* 		2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		2005/05/25（ＮＩＩ）蛭田
* 			・場所コードの追加
* 		2005/09/13（ＮＩＩ）蛭田
* 			・履歴更新日・金額・発注書順(ﾁｪｯｸﾎﾞｯｸｽ)の追加
* 		2006/01/25（ＮＩＩ）田端
* 			・ダウンロード項目に注残数追加
*       2007/03/01（ＮＩＩ）田中
* 			・ダウンロード項目に副資材コード追加
*       2007/12/05（ＮＩＩ）田中
* 			・ダウンロード項目にタイトル漢字追加
*		2007/12/25（ＮＩＩ）田中
* 			・マニュアル発注指示一括貼付　備考２追加対応
*		2008/03/07（ＮＩＩ）田中
* 			・単価の追加
* 		2008/06/02（ＮＩＩ）田中
* 			・ＶＡＰ社　単価・金額の修正
*/

package com.jds.prodord.reference.orderhistory;

import java.util.*;
import com.jds.prodord.common.*;
public class OrderHistoryVO  {
	
    /**
     *	コマンド
     */
    private String command = "";    

	private String prcKbn = "";
     
    /**
     * 代表会社識別コード
     */
    private String daiKaiSkbCod = "";
    
    /**
     * 会社識別コード
     */
    private String kaiSkbCod = "";
    private ArrayList kaiSkbCodList = null;
    
    /**
     * 場所コード
     */
	private String bshCod = "";
    
//--------------------------------------------------------

    /**
     * 発注書選択(ヘッダ)
     */
    private String syrZmiSgn_H = "";

    /**
     * 分類コード選択(ヘッダ)
     */
    private String bunCod_H = "";
    
    /**
     * 入庫状況選択(ヘッダ)
     */
    private String nyuko_H = "";
    
    /**
     * 発注先コード(ヘッダ)
     */
    private String hacCod_H = "";
    
    /**
     * 記号番号(ヘッダ)
     */
    private String kigBng_H = "";
    
    /**
     * 発売日(ヘッダ)
     */
    private int hbiDte_H = 0;
    
    /**
     * 発注書番号From&To(ヘッダ)
     */
    private String hacSyoBngFrm_H = "";
    private String hacSyoBngTo_H = "";

    /**
     * 発注日Frm&To(ヘッダ)
     */
    private int hacSyoDteFrm_H = 0;
    private int hacSyoDteTo_H = 0;

    /**
     * 納期Frm&To(ヘッダ)
     */
    private int nkiFrm_H = 0;
    private int nkiTo_H = 0;
   
	/**
	 * 入庫日Frm&To(ヘッダ)
	 */
	private int nyoDteFrm_H = 0;
	private int nyoDteTo_H = 0;

    /**
     * 発注先コード
     */
    private String hacCod = "";
    
    /**
     * 区分
     */
    private String sinKyuKbn = "";
    
    /**
     * 記号番号
     */
    private String kigBng = "";
    
    /**
     * 発売日
     */
    private String hbiDte = "";
    
    /**
     * check_Midチェックボックス
     */
    private boolean check_Mid = false;    
    
    /**
     * 発注書番号
     */
    private String hacSyoBng = "";

    /**
     * 発注日
     */
    private String hacSyoDte = "";
    
    /**
     * 出力済
     */
    private String syrZmiSgn = "";
    
    /**
     * プレス副資材コード
     */
    private String prsFukSziCod = "";
    
    /**
     * 在庫数
     */
    private String fukSziSuu = "";
    
    /**
     * 発注数
     */
    private String hacSuu = "";
    private String hacSuuOld = "";
    
    
    /**
     * 入庫数
     */
    private String nyoSuu = "";
    
    /**
     * 納期
     */
    private int nki = 0;
    
    /**
     * 発注番号
     */
    private String gyoBng = "";
    
    /**
     * 区分old
     */
    private String sinKyuKbnOld = "";
    
    /**
     * 納品先
     */
    private String nhnMeiKj = "";

     /**
     * 分類ｺｰﾄﾞ
     */
    private String bunCod = "";
     
    /**
     * 表示品番
     */
    private String hjiHnb = "";
    
    /**
     * コメント
     */
    private String cmt = "";
    
    /**
     * 入庫日
     */
    private String nyoDte = "";

	/**
	 * 注残数　　2006/1/25 add
	 */
	private String cyuzan = "";

	/**
	 * 直送区分 
	 */
	private String cykKbn = ""; //2004.01.22 add
	 
	/** 
	 * 発注番号判定フラグ
	 */
	private boolean gyoBngflg = false;

	/** 
	 * 処理回数
	 */
	private String syrSuu = "";
	
	/** 
	 * ＳＥＱＮＯ
	 */
	private String seqNo = "";
	
	/** 
	 * メーカー分類
	 */
	private String[] mkrBun = new String[0];
	
	/**
     * 区分
     */
	private String kbn_H = "";

	private String fukSziMkr = ""; 
	private String fukSziHacSaki = ""; 
	private String fukSziNm = ""; 
	private boolean exsitFuk01 = false; 

	private int updDte;
	private int updJkk;

	/** 完納サイン
	 */
	private String knuSgn = "";
	
	/**
	 * 金額
	 */
	private String kin = "";
	private String kinOld = "";
	/**
	 * 履歴更新日
	 */
	private String rrkTbl = "";
	
	/**
	 * 発注書順（ﾁｪｯｸﾎﾞｯｸｽ）
	 */
	private boolean chkHacJun = false;
	
	/**
	 * 副資材名称
	 */
	private String fukSziNmkj = "";
    
	/**
	 * タイトル漢字
	 */
	private String titKj = "";
    
	/**
	 * 備考２
	 */
	private String biKou2 = "";

	/**
	 * 単価
	 */
	private String tan2 = "";
	private String tan2Old = "";

//*************************************************************************************

    // ----------------------------------------------------------- Properties
    
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
     * @param daiKaiSkbCod 代表会社識別コード
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
     * @param kaiSkbCod 
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
    public ArrayList getKaiSkbCodList(){
    	return kaiSkbCodList;
    }
    public void setKaiSkbCodList(ArrayList arr){
    	kaiSkbCodList = arr;
    }            
//-----------------------------------------------------------------------------
//** 検索部分 **//

    /**
     * 発注書選択のセット
     * 
     * @param syrZmiSgn_H 発注書選択
     */
    public void setSyrZmiSgn_H(String syrZmiSgn_H) {
        this.syrZmiSgn_H = syrZmiSgn_H;
    }

    /**
     * 発注書選択の取得
     * 
     * @return 発注書選択
     */
    public String getSyrZmiSgn_H() {
        return this.syrZmiSgn_H;
    }

    /**
     * 分類コードのセット
     * 
     * @param bunCod_H 分類コード
     */
    public void setBunCod_H(String bunCod_H) {
        this.bunCod_H = bunCod_H;
    }

    /**
     * 分類コードの取得
     * 
     * @return 分類コード
     */
    public String getBunCod_H() {
        return this.bunCod_H;
    }
    
    /**
     * 入庫状況選択のセット
     * 
     * @param bunCod_H 入庫状況選択
     */
    public void setNyuko_H(String nyuko_H) {
        this.nyuko_H = nyuko_H;
    }

    /**
     * 入庫状況選択の取得
     * 
     * @return 入庫状況選択
     */
    public String getNyuko_H() {
        return this.nyuko_H;
    }
    
    /**
     * 発注先コードのセット
     * 
     * @param hacCod_H 発注先コード
     */
    public void setHacCod_H(String hacCod_H) {
        this.hacCod_H = hacCod_H;
    }

    /**
     * 発注先コードの取得
     * 
     * @return 発注先コード
     */
    public String getHacCod_H() {
        return this.hacCod_H;
    }

    /**
     * 記号番号のセット
     * 
     * @param kigBng_H 記号番号
     */
    public void setKigBng_H(String kigBng_H) {
        this.kigBng_H = kigBng_H;
    }

    /**
     * 記号番号の取得
     * 
     * @return 記号番号
     */
    public String getKigBng_H() {
        return this.kigBng_H;
    }


    /**
     * 発売日
     */
	public int getHbiDte_H() {
		return hbiDte_H;
	}

	public void setHbiDte_H(int i) {
		hbiDte_H = i;
	}
	
	public void setHbiDte_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			hbiDte_H = 0;
		else
			hbiDte_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}



    /**
     * 発注書番号Fromのセット
     * 
     * @param hacSyoBngFrm_H 発注書番号From
     */
    public void setHacSyoBngFrm_H(String hacSyoBngFrm_H) {
        this.hacSyoBngFrm_H = hacSyoBngFrm_H;
    }

    /**
     * 発注書番号Fromの取得
     * 
     * @return 発注書番号From
     */
    public String getHacSyoBngFrm_H() {
        return this.hacSyoBngFrm_H;
    }

    /**
     * 発注書番号Toのセット
     * 
     * @param hacSyoBngTo_H 発注書番号To
     */
    public void setHacSyoBngTo_H(String hacSyoBngTo_H) {
        this.hacSyoBngTo_H = hacSyoBngTo_H;
    }

    /**
     * 発注書番号Toの取得
     * 
     * @return 発注書番号To
     */
    public String getHacSyoBngTo_H() {
        return this.hacSyoBngTo_H;
    }





    /**
     * 発注日Fromのセット
     * 
     * @param hacSyoDteFrm_H 発注日To
     */
    public void setHacSyoDteFrm_H(int hacSyoDteFrm_H) {
        this.hacSyoDteFrm_H = hacSyoDteFrm_H;
    }
    public void setHacSyoDteFrm_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			hacSyoDteFrm_H = 0;
		else
			hacSyoDteFrm_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * 発注日Frmの取得
     * 
     * @return 発注日Frm
     */
    public String getHacSyoDteFrm_H() {
        return (this.hacSyoDteFrm_H == 0)? "" : this.hacSyoDteFrm_H+"";
    }

    /**
     * 発注日Toのセット
     * 
     * @param hacSyoDteTo_H 発注日To
     */
    public void setHacSyoDteTo_H(int hacSyoDteTo_H) {
        this.hacSyoDteTo_H = hacSyoDteTo_H;
    }
	public void setHacSyoDteTo_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			hacSyoDteTo_H = 0;
		else
			hacSyoDteTo_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * 発注日Toの取得
     * 
     * @return 発注日To
     */
    public String getHacSyoDteTo_H() {
        return (this.hacSyoDteTo_H == 0)? "" : this.hacSyoDteTo_H+"";
    }






    /**
     * 納期Frmのセット
     * 
     * @param NkiFrm_H 納期Frm
     */
    public void setNkiFrm_H(int nkiFrm_H) {
        this.nkiFrm_H = nkiFrm_H;
    }
	public void setNkiFrm_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			nkiFrm_H = 0;
		else
			nkiFrm_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * 納期Frmの取得
     * 
     * @return 納期Frm
     */
    public String getNkiFrm_H() {
        return (this.nkiFrm_H == 0)? "" : this.nkiFrm_H+"";
    }

    /**
     * 納期Toのセット
     * 
     * @param NkiTo_H 納期To
     */
    public void setNkiTo_H(int nkiTo_H) {
        this.nkiTo_H = nkiTo_H;
    }
	public void setNkiTo_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			nkiTo_H = 0;
		else
			nkiTo_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /**
     * 納期Toの取得
     * 
     * @return 納期To
     */
    public String getNkiTo_H() {
        return (this.nkiTo_H == 0)? "" : this.nkiTo_H+"";
    }


	/**
	 * 入庫日Fromのセット
	 * 
	 * @param nyoDteFrm_H
	 */
	public void setNyoDteFrm_H(int nyoDteFrm_H) {
		this.nyoDteFrm_H = nyoDteFrm_H;
	}
	public void setNyoDteFrm_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			nyoDteFrm_H = 0;
		else
			nyoDteFrm_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}

	/**
	 * 入庫日fromの取得
	 * 
	 * @return 入庫日from
	 */
	public String getNyoDteFrm_H() {
		return (this.nyoDteFrm_H == 0)? "" : this.nyoDteFrm_H+"";
	}

	/**
	 * 入庫日Toのセット
	 * 
	 * @param i
	 */
	public void setNyoDteTo_H(int nyoDteTo_H) {
		this.nyoDteTo_H = nyoDteTo_H;
	}
	public void setNyoDteTo_H(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			nyoDteTo_H = 0;
		else
			nyoDteTo_H = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}

	/**
	 * @return
	 */
	public String getNyoDteTo_H() {
		return (this.nyoDteTo_H == 0)? "" : this.nyoDteTo_H+"";
	}


//	2004.01.22 add
	/**
	 * 直送区分
	 * 
	 * @param nyoDte 直送区分
	 */
	public void setCykKbn(String cykKbn) {
		this.cykKbn = cykKbn;
	}
	/**
	 * 直送区分の取得
	 * 
	 * @return 直送区分
	 */
	public String getCykKbn() {
		return this.cykKbn;
	}
    

//結果画面-------------------------------------------------------------------- 

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
     * 区分のセット
     * 
     * @param sinKyuKbn 区分
     */
    public void setSinKyuKbn(String sinKyuKbn) {

        this.sinKyuKbn = sinKyuKbn;

    }


    /**
     * 区分の取得
     * 
     * @return 区分
     */
    public String getSinKyuKbn() {

        return this.sinKyuKbn;

    }


    /**
     * 記号番号のセット
     * 
     * @param kigBng 記号番号
     */
    public void setKigBng(String kigBng) {

        this.kigBng = kigBng;

    }


    /**
     * 記号番号の取得
     * 
     * @return 記号番号
     */
    public String getKigBng() {

        return this.kigBng;

    }


    /**
     * 発売日のセット
     * 
     * @param hbiDte 発売日
     */
    public void setHbiDte(String hbiDte) {

        this.hbiDte = hbiDte;

    }


    /**
     * 発売日の取得
     * 
     * @return 発売日
     */
    public String getHbiDte() {

        return this.hbiDte;

    }

    /**
     * check_Midのセット
     */
    public void setCheck_Mid(boolean check_Mid) {
        this.check_Mid = check_Mid;
    }

    /**
     * check_Midの取得
     */
    public boolean getCheck_Mid() {
        return this.check_Mid;
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
     * 発注日のセット
     * 
     * @param hacSyoDte 発注日
     */
    public void setHacSyoDte(String hacSyoDte) {

        this.hacSyoDte = hacSyoDte;

    }


    /**
     * 発注日の取得
     * 
     * @return 発注日
     */
    public String getHacSyoDte() {

        return this.hacSyoDte;

    }


    /**
     * 出力済のセット
     * 
     * @param syrZmiSgn 出力済
     */
    public void setSyrZmiSgn(String syrZmiSgn) {

        this.syrZmiSgn = syrZmiSgn;

    }


    /**
     * 出力済の取得
     * 
     * @return 出力済
     */
    public String getSyrZmiSgn() {

        return this.syrZmiSgn;

    }


    /**
     * プレス副資材コードのセット
     * 
     * @param prsFukSziCod プレス副資材コード
     */
    public void setPrsFukSziCod(String prsFukSziCod) {

        this.prsFukSziCod = prsFukSziCod;

    }


    /**
     * プレス副資材コードの取得
     * 
     * @return プレス副資材コード
     */
    public String getPrsFukSziCod() {

        return this.prsFukSziCod;

    }


    /**
     * 在庫数のセット
     * 
     * @param fukSziSuu 在庫数
     */
    public void setFukSziSuu(String fukSziSuu) {

        this.fukSziSuu = fukSziSuu;

    }


    /**
     * 在庫数の取得
     * 
     * @return 在庫数
     */
    public String getFukSziSuu() {

        return this.fukSziSuu;

    }

    /**
     * 発注数のセット
     * 
     * @param hacSuu 発注数
     */
    public void setHacSuu(String hacSuu) {

        this.hacSuu = hacSuu;

    }


    /**
     * 発注数の取得
     * 
     * @return 発注数
     */
    public String getHacSuu() {

        return this.hacSuu;

    }


	/**
     * 発注数Oldのセット
     * 
     * @param hacSuu 発注数Old
     */
    public void setHacSuuOld(String hacSuuOld) {

        this.hacSuuOld = hacSuuOld;

    }


    /**
     * 発注数Oldの取得
     * 
     * @return 発注数Old
     */
    public String getHacSuuOld() {

        return this.hacSuuOld;

    }

    /**
     * 入庫数のセット
     * 
     * @param nyoSuu 入庫数
     */
    public void setNyoSuu(String nyoSuu) {

        this.nyoSuu = nyoSuu;

    }


    /**
     * 入庫数の取得
     * 
     * @return 入庫数
     */
    public String getNyoSuu() {

        return this.nyoSuu;

    }

   /**
    * 納期
    */
	public int getNki() {
		return nki;

	}
	public void setNki(int i) {
		nki = i;
	}
	
	public void setNki(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			nki = 0;
		else
			nki = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
	public String getNkiYear(){
		if(nki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(nki),6,"0");
		return str.substring(0,2);
	}
	public String getNkiMonth(){
		if(nki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(nki),6,"0");
		return str.substring(2,4);
	}
	public String getNkiDay(){
		if(nki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(nki),6,"0");
		return str.substring(4,6);
	}

    /**
     * 発注番号のセット
     * 
     * @param gyoBng 発注番号
     */
    public void setGyoBng(String gyoBng) {

        this.gyoBng = gyoBng;

    }


    /**
     * 発注番号の取得
     *     * @return 発注番号
     */
    public String getGyoBng() {

        return this.gyoBng;

    }

    /**
     * 区分Oldのセット
     * 
     * @param kbnOld 区分Old
     */
    public void setSinKyuKbnOld(String sinKyuKbnOld) {

        this.sinKyuKbnOld = sinKyuKbnOld;

    }


    /**
     * 区分Oldの取得
     * 
     * @return 区分Old
     */
    public String getSinKyuKbnOld() {

        return this.sinKyuKbnOld;

    }
    /**
     * 納品先のセット
     * 
     * @param nhnMeiKj 納品先
     */
    public void setNhnMeiKj(String nhnMeiKj) {

        this.nhnMeiKj = nhnMeiKj;

    }


    /**
     * 納品先の取得
     * 
     * @return 納品先
     */
    public String getNhnMeiKj() {

        return this.nhnMeiKj;

    }
    

    /**
     * 分類コードのセット
     * 
     * @param bunCod 分類コード
     */
    public void setBunCod(String bunCod) {

        this.bunCod = bunCod;

    }


    /**
     * 分類コードの取得
     * 
     * @return 分類コード
     */
    public String getBunCod() {

        return this.bunCod;

    }
 
    /**
     * 表示品番のセット
     * 
     * @param hjihnb 表示品番
     */
    public void setHjiHnb(String hjiHnb) {

        this.hjiHnb = hjiHnb;

    }


    /**
     * 表示品番の取得
     * 
     * @return 表示品番
     */
    public String getHjiHnb() {

        return this.hjiHnb;

    }

    /**
     * コメントのセット
     * 
     * @param hjihnb コメント
     */
    public void setCmt(String cmt) {

        this.cmt = cmt;

    }


    /**
     * コメントの取得
     * 
     * @return コメント
     */
    public String getCmt() {

        return this.cmt;

    }
    
    /**
     * 入庫日
     * 
     * @param nyoDte 入庫日
     */
    public void setNyoDte(String nyoDte) {
        this.nyoDte = nyoDte;
    }
    /**
     * 入庫日の取得
     * 
     * @return 入庫日
     */
    public String getNyoDte() {
        return this.nyoDte;
    }
    
	/**
	 * 注残数の取得
	 * @return
	 */
	public String getCyuzan() {
		return cyuzan;
	}

	/**
	 * 注残数の設定
	 * @param string
	 */
	public void setCyuzan(String string) {
		cyuzan = string;
	}
  
	/**
	 * 副資材名称のセット
	 * 
	 * @param prsFukSziCod 副資材名称
	 */
	public void setFukSziNmkj(String fukSziNmkj) {

		this.fukSziNmkj = fukSziNmkj;

	}

	/**
	 * 副資材名称の取得
	 * 
	 * @return 副資材名称
	 */
	public String getFukSziNmkj() {

		return this.fukSziNmkj;

	}

	/**
	 * タイトル漢字のセット
	 * 
	 * @param titKj タイトル漢字
	 */
	public void setTitKj(String titKj) {

		this.titKj = titKj;

	}

	/**
	 * タイトル漢字の取得
	 * 
	 * @return タイトル漢字
	 */
	public String getTitKj() {

		return this.titKj;

	}

    // ----------------------------------------------------------- Properties Right
  
	/** 発注番号判定フラグ
	 */
	public boolean getGyoBngflg(){
		return gyoBngflg;
	}	
	public void setGyoBngflg(boolean s){
		gyoBngflg = s;
	}	

	
	/** 副資材
	 */
	public String getFukSziMkr(){
		return fukSziMkr;
	}	
	public void setFukSziMkr(String s){
		fukSziMkr = s;
	}	


	public String getFukSziHacSaki(){
		return fukSziHacSaki;
	}	
	public void setFukSziHacSaki(String s){
		fukSziHacSaki = s;
	}
	
	public String getFukSziNm(){
		return fukSziNm;
	}	
	public void setFukSziNm(String s){
		fukSziNm = s;
	}	
			

	public boolean getExsitFuk01(){
		return exsitFuk01;
	}	
	public void setExsitFuk01(boolean s){
		exsitFuk01 = s;
	}
	
	

	public int getUpdDte(){
		return updDte;
	}	
	public void setUpdDte(int s){
		updDte = s;
	}	
	public int getUpdJkk(){
		return updJkk;
	}	
	public void setUpdJkk(int s){
		updJkk = s;
	}	

	/**
     * 処理回数
     * 
     * @param nyoDte 処理回数
     */
    public void setSyrSuu(String syrSuu) {
        this.syrSuu = syrSuu;
    }
    /**
     * 処理回数の取得
     * 
     * @return 処理回数
     */
    public String getSyrSuu() {
        return this.syrSuu;
    }
    
    /**
     * ＳＥＱＮＯ
     * 
     * @param nyoDte ＳＥＱＮＯ
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
    /**
     * ＳＥＱＮＯの取得
     * 
     * @return ＳＥＱＮＯ
     */
    public String getSeqNo() {
        return this.seqNo;
    }
    /**
     * メーカー分類のセット
     * 
     * @param mkrBun メーカー分類
     */
    public void setMkrBun(String[] s) {

        this.mkrBun = s;

    }
    /**
     * メーカー分類の取得
     * 
     * @return メーカー分類
     */
    public String[] getMkrBun() {

        return this.mkrBun;

    }
    /**
     * 区分のセット
     * 
     * @param kbn 区分
     */
    public void setKbn_H(String s) {

        this.kbn_H = s;

    }
    /**
     * 区分の取得
     * 
     * @return 区分
     */
    public String getKbn_H() {

        return this.kbn_H;

    }
    
	/**
	 * 完納サイン
	 * @knuSgn
	 */
	public String getKnuSgn() {
		return knuSgn;
	}

	/**
	 * @param knuSgn
	 */
	public void setKnuSgn(String knuSgn) {
		this.knuSgn = knuSgn;
	}  
//--------------------------------------------------------------------------------------  
    
    /**
	 * Gets サンプル区分
	 * @return Returns a String
	 */
	public String getSmpKbn(){
		String smpKbn = DataFormatUtils.getSmpKbn(sinKyuKbn, hbiDte, hacSyoDte);
		return smpKbn;
	}
	
	/**
	 * 新譜かどうか(発注日＜発売日)を返す
	 */
	public boolean isShinpu(){
		if(this.hbiDte.equals(""))
				return false;
			
		String _hbiDte = StringUtils.lpad(this.hbiDte,6,"0");
    	int  y   = Integer.parseInt(_hbiDte.substring(0,2));

    	if(y >= 80)
    		_hbiDte = "19" + _hbiDte;//1980～1999
    	else
    		_hbiDte = "20" + _hbiDte;//2000～

		int hbiDte = Integer.parseInt(_hbiDte);
		int hacSyoDte = Integer.parseInt("20" + StringUtils.lpad(this.hacSyoDte,6,"0"));
		
		if(hacSyoDte < hbiDte)
			return true;
			
		return false;
	}
	
	public static final int FROM_SMP_TO_OTHER = 1;
	public static final int FROM_OTHER_TO_SMP = 2;
	public static final int ELSE = 0;
	/**
	 * 区分がどのように変更されたか、タイプを返す
	 */
	public int getKbnHenkoType(){
		String sample = DataFormatUtils.getKbnCod(DataFormatUtils.SAMPLE);
		if(this.sinKyuKbnOld.equals(sample)){
			if(this.sinKyuKbn.equals(sample))
				return ELSE;
			else
				return FROM_SMP_TO_OTHER;
		}else{
			if(this.sinKyuKbn.equals(sample))
				return FROM_OTHER_TO_SMP;
		}
		return ELSE;		
	}
	
	
	/**
	 * @return
	 */
	public static int getELSE() {
		return ELSE;
	}

	/**
	 * @return
	 */
	public String getBshCod() {
		return bshCod;
	}

	/**
	 * @param string
	 */
	public void setBshCod(String string) {
		bshCod = string;
	}


	/**
	 * 発注書順（ﾁｪｯｸﾎﾞｯｸｽ）の取得
	 * @return
	 */
	public boolean isChkHacJun() {
		return chkHacJun;
	}

	/**
	 * 発注書順（ﾁｪｯｸﾎﾞｯｸｽ）の設定
	 * @param b
	 */
	public void setChkHacJun(boolean b) {
		chkHacJun = b;
	}

	/**
	 * 金額の取得
	 * @return
	 */
	public String getKin() {
		return kin;
	}

	/**
	 * 金額の設定
	 * @param string
	 */
	public void setKin(String string) {
		kin = string;
	}

	/**
	 * 金額Oldの取得
	 * @return
	 */
	public String getKinOld() {
		return kinOld;
	}

	/**
	 * 金額Oldの設定
	 * @param string
	 */
	public void setKinOld(String string) {
		kinOld = string;
	}

	/**
	 * 単価の取得
	 * @return
	 */
	public String getTan2() {
		return tan2;
	}

	/**
	 * 単価の設定
	 * @param string
	 */
	public void setTan2(String string) {
		tan2 = string;
	}


	/**
	 * 単価Oldの取得
	 * @return
	 */
	public String getTan2Old() {

		return this.tan2Old;

	}

	/**
	 * 単価Oldのセット
	 * @param string
	 */
	public void setTan2Old(String tan2Old) {

		this.tan2Old = tan2Old;

	}

	/**
	 * 備考２の取得
	 * @return
	 */
	public String getBiKou2() {
		return biKou2;
	}

	/**
	 * 備考２の設定
	 * @param string
	 */
	public void setBiKou2(String string) {
		biKou2 = string;
	}

	/**
	 * 履歴更新日の取得
	 * @return
	 */
	public String getRrkTbl() {
		return rrkTbl;
	}

	/**
	 * 履歴更新日の設定
	 * @param string
	 */
	public void setRrkTbl(String string) {
		rrkTbl = string;
	}

	/**
	 * 履歴更新日キーの取得
	 * @return
	 */
	public String getUpdateKey() {
		String updateKey = daiKaiSkbCod + "%" +
						   kaiSkbCod + "%" +
					       hacSyoDte + "%" +
					       hacSyoBng + "%" +
					       hacCod;
		return updateKey;
	}

	/**
	 * @return
	 */
	public String getPrcKbn() {
		return prcKbn;
	}

	/**
	 * @param string
	 */
	public void setPrcKbn(String string) {
		prcKbn = string;
	}


	/**
	 * 条件キーをSQLの状態で取得します。
	 * @param map
	 * @return query
	 */
	public String getPartOfQuery(String key){

		String query = "";
		try {
			String[] keys = StringUtils.split(key, "%");
			
			query = " DAIKAISKBCOD = '" + keys[0] + "'"
				  + " AND KAISKBCOD = '" + keys[1] + "'"
				  + " AND HACSYODTE = " + keys[2]
				  + " AND HACSYOBNG = '" + DataFormatUtils.formatHacSyoBng(keys[3].trim()) + "'"
				  + " AND HACCOD = '" + keys[4] + "'";
			
		}catch (ArrayIndexOutOfBoundsException e) {
			SystemException se = new SystemException(e);
			se.printStackTrace();
			throw e;
		}

		return query;
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
		sb.append(toString(hacSyoBng, "hacSyoBng") + delim);
		sb.append(toString(hacSyoDte, "hacSyoDte") + delim);
		sb.append(toString(syrSuu, "syrSuu") + delim);
		sb.append(toString(gyoBng, "gyoBng") + delim);
		sb.append(toString(seqNo, "seqNo") + delim);

		sb.append(toString(kigBng, "kigBng") + delim);
		sb.append(toString(bshCod, "bshCod") + delim);  
		sb.append(toString(hacCod, "hacCod") + delim);
		sb.append(toString(hacSuuOld, "hacSuuOld") + delim); 
		sb.append(toString(sinKyuKbn, "sinKyuKbn") + delim);
		sb.append(toString(sinKyuKbnOld, "sinKyuKbnOld") + delim);
		sb.append(toString(syrZmiSgn, "syrZmiSgn") + delim);
		sb.append(toString(prsFukSziCod, "prsFukSziCod") + delim);  
		sb.append(toString(fukSziSuu, "fukSziSuu") + delim);  
		sb.append(toString(hacSuu, "hacSuu") + delim);  
		sb.append(toString(bunCod, "bunCod") + delim);
		sb.append(toString(cykKbn, "cykKbn") + delim);
		sb.append(toString(String.valueOf(updDte), "updDte") + delim);
		sb.append(toString(String.valueOf(updJkk), "updJkk") + delim);
		sb.append(toString(rrkTbl, "rrkTbl") + delim);
		
		return sb.toString();
	}
	private String toString(Object o, String prmNm){
		return prmNm + "[" + o + "]";
	}
	
	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("command : " + command + ", ");
		sb.append("daiKaiSkbCod : " + daiKaiSkbCod + ", ");
		sb.append("kaiSkbCod : " + kaiSkbCod + ", ");
		sb.append("kaiSkbCodList : " + kaiSkbCodList + ", ");
		sb.append("bshCod : " + bshCod + ", ");
		sb.append("syrZmiSgn_H : " + syrZmiSgn_H + ", ");
		sb.append("bunCod_H : " + bunCod_H + ", ");
		sb.append("nyuko_H : " + nyuko_H + ", ");
		sb.append("hacCod_H : " + hacCod_H + ", ");
		sb.append("kigBng_H : " + kigBng_H + ", ");
		sb.append("hbiDte_H : " + hbiDte_H + ", ");
		sb.append("hacSyoBngFrm_H : " + hacSyoBngFrm_H + ", ");
		sb.append("hacSyoBngTo_H : " + hacSyoBngTo_H + ", ");
		sb.append("hacSyoDteFrm_H : " + hacSyoDteFrm_H + ", ");
		sb.append("hacSyoDteTo_H : " + hacSyoDteTo_H + ", ");
		sb.append("nkiFrm_H : " + nkiFrm_H + ", ");
		sb.append("nkiTo_H : " + nkiTo_H + ", ");
		sb.append("nyoDteFrm_H : " + nyoDteFrm_H + ", ");
		sb.append("nyoDteTo_H : " + nyoDteTo_H + ", ");
		sb.append("hacCod : " + hacCod + ", ");
		sb.append("sinKyuKbn : " + sinKyuKbn + ", ");
		sb.append("kigBng : " + kigBng + ", ");
		sb.append("hbiDte : " + hbiDte + ", ");
		sb.append("check_Mid : " + check_Mid + ", ");
		sb.append("hacSyoBng : " + hacSyoBng + ", ");
		sb.append("hacSyoDte : " + hacSyoDte + ", ");
		sb.append("syrZmiSgn : " + syrZmiSgn + ", ");
		sb.append("prsFukSziCod : " + prsFukSziCod + ", ");
		sb.append("fukSziSuu : " + fukSziSuu + ", ");
		sb.append("hacSuu : " + hacSuu + ", ");
		sb.append("hacSuuOld : " + hacSuuOld + ", ");
		sb.append("nyoSuu : " + nyoSuu + ", ");
		sb.append("nki : " + nki + ", ");
		sb.append("gyoBng : " + gyoBng + ", ");
		sb.append("sinKyuKbnOld : " + sinKyuKbnOld + ", ");
		sb.append("nhnMeiKj : " + nhnMeiKj + ", ");
		sb.append("bunCod : " + bunCod + ", ");
		sb.append("hjiHnb : " + hjiHnb + ", ");
		sb.append("cmt : " + cmt + ", ");
		sb.append("nyoDte : " + nyoDte + ", ");
		sb.append("cyuzan : " + cyuzan + ", ");
		sb.append("cykKbn : " + cykKbn + ", ");
		sb.append("gyoBngflg : " + gyoBngflg + ", ");
		sb.append("syrSuu : " + syrSuu + ", ");
		sb.append("seqNo : " + seqNo + ", ");
		sb.append("mkrBun : [");
		if(mkrBun != null){
			for (int i = 0; i < mkrBun.length; i++) {
				sb.append(mkrBun[i] + ", ");			
			}
		}
		sb.append("], ");
		sb.append("kbn_H : " + kbn_H + ", ");
		sb.append("fukSziMkr : " + fukSziMkr + ", ");
		sb.append("fukSziHacSaki : " + fukSziHacSaki + ", ");
		sb.append("fukSziNm : " + fukSziNm + ", ");
		sb.append("exsitFuk01 : " + exsitFuk01 + ", ");
		sb.append("updDte : " + updDte + ", ");
		sb.append("updJkk : " + updJkk + ", ");
		sb.append("knuSgn : " + knuSgn + ", ");
		sb.append("kin : " + kin + ", ");
		sb.append("rrkTbl : " + rrkTbl + ", ");
		sb.append("chkHacJun : " + chkHacJun + ", ");
		sb.append("bikou2 : " + biKou2 + ", ");
		sb.append("tan2 : " + tan2);
		sb.append("tan2Old : " + tan2Old);

		return sb.toString();
	}


}