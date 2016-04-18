/**
* OrderHistoryFormRow  発注履歴照会画面フォーム行クラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）蛭田 敬子
* 処理概要    Httpリクエストとリクエストの結果のうちの繰り返し項目を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
* 		2004/01/22 （ＮＩＩ）森
* 			・直送区分追加による修正
* 		2005/05/25（ＮＩＩ）蛭田
* 			・場所コード追加
* 		2005/09/01（ＮＩＩ）蛭田
* 			・金額の追加
*		2007/12/25（ＮＩＩ）田中
* 			・マニュアル発注指示一括貼付　備考２追加対応
*		2008/03/07（ＮＩＩ）田中
* 			・単価の追加
*		2008/06/02（ＮＩＩ）田中
* 			・ＶＡＰ社　単価・金額の修正
* 
*/

package com.jds.prodord.reference.orderhistory;



public class OrderHistoryFormRow implements java.io.Serializable{
	
	public Object clone(){
		OrderHistoryFormRow row = new OrderHistoryFormRow();
		row.setDaiKaiSkbCod(new String(this.daiKaiSkbCod));
		row.setKaiSkbCod(new String(this.kaiSkbCod));
		row.setHacCod(new String(this.hacCod));
		row.setSinKyuKbn(new String(this.sinKyuKbn));
		row.setKigBng(new String(this.kigBng));
		row.setHbiDte(new String(this.hbiDte));
		row.setCheck_Mid(this.check_Mid);
		row.setHacSyoBng(new String(this.hacSyoBng));
		row.setHacSyoDte(new String(this.hacSyoDte));
		row.setSyrZmiSgn(new String(this.syrZmiSgn));
		row.setPrsFukSziCod(new String(this.prsFukSziCod));
		row.setFukSzisuu(new String(this.fukSzisuu));
		row.setHacSuu(new String(this.hacSuu));
		row.setHacSuuOld(new String(this.hacSuuOld));
		row.setNyoSuu(new String(this.nyoSuu));
		row.setNkiYear(new String(this.nkiYear));
		row.setNkiMonth(new String(this.nkiMonth));
		row.setNkiDay(new String(this.nkiDay));
		row.setGyoBng(new String(this.gyoBng));
		row.setNhnMeiKj(new String(this.nhnMeiKj));
		row.setBreakflg(this.breakflg);
		row.setHjiHnb(new String(this.hjiHnb));
		row.setBunCod(new String(this.bunCod));
		row.setCmt(new String(this.cmt));
		row.setNyoDte(new String(this.nyoDte));
		row.setCykkbn(new String(this.Cykkbn));	//2004.01.22 add
		row.setBshCod(new String(this.bshCod));	//2005.05.25 add
		row.setKin(new String(this.kin));          //2005.09.13 add
		row.setBiKou2(new String(this.biKou2));          //2007.12.25 add
		row.setTan2(new String(this.tan2));          //2008.03.07 add
		row.setTan2Old(new String(this.tan2Old));
		row.setKinOld(new String(this.kinOld));
				
		return row;
	}
	
	
    /**
     * 代表会社識別コード
     */
    private String daiKaiSkbCod = "";
    
    /**
     * 会社識別コード
     */
    private String kaiSkbCod = "";
    
    /**
     * 場所コード
     */
    private String bshCod = "";
	
    
    // --------------------------------------------------- Instance Variables    


    /**
     * 発注先
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
     * チェックボックス
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
     * 分類コード
     */
    private String prsFukSziCod = "";
    

    /**
     * 在庫数
     */
    private String fukSzisuu = "";
   
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
   
    private String nkiYear = "";
    private String nkiMonth = "";
    private String nkiDay = "";
    

    /**
     * 発注番号
     */
    private String gyoBng = "";
    

    /**
     * 納品名
     */
    private String nhnMeiKj = "";
    
    
    /** 発注先・区分・記号番号・発売日ブレークフラグ
	 */
    private boolean breakflg = false;
    
    
    /**
     * 表示品番
     */
    private String hjiHnb = "";
    
    /**
     * 分類コード
     */
    private String bunCod = "";

    /**
     * コメント
     */
    private String cmt = "";
    
    /**
     * 入庫日
     */
    private String nyoDte = "";  
    
//2004.01.22 add
	/**
	 * 直送区分
	 */
	private String Cykkbn = "";  
    
//2005.09.13 add
	/**
	 * 金額
	 */
	private String kin = "";
	private String kinOld = "";
	
//	2007.12.20 add
	  /**
	   * 備考２
	   */
	  private String biKou2 = "";

//	2008.03.07 add
	  /**
	   * 単価
	   */
	  private String tan2 = "";
	  private String tan2Old = "";
	
	//---------------------------------------------------------------------------

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


    // ----------------------------------------------------------- Properties


    /**
     * 発注先のセット
     * 
     * @param hacCod 発注先
     */
    public void setHacCod(String hacCod) {
        this.hacCod = hacCod;
    }
    /**
     * 発注先の取得
     * 
     * @return 発注先
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
     * チェックボックスのセット
     * 
     * @param checkMid チェックボックス
     */
    public void setCheck_Mid(boolean check_Mid) {
        this.check_Mid = check_Mid;
    }
    /**
     * チェックボックスの取得
     * 
     * @return チェックボックス
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
     * 分類コードのセット
     * 
     * @param prsFukSziCod 分類コード
     */
    public void setPrsFukSziCod(String prsFukSziCod) {
        this.prsFukSziCod = prsFukSziCod;
    }
    /**
     * 分類コードの取得
     * 
     * @return 分類コード
     */
    public String getPrsFukSziCod() {
        return this.prsFukSziCod;
    }


    /**
     * 在庫数のセット
     * 
     * @param fukSzisuu 在庫数
     */
    public void setFukSzisuu(String fukSzisuu) {
        this.fukSzisuu = fukSzisuu;
    }
     /**
     * 在庫数の取得
     * 
     * @return 在庫数
     */
    public String getFukSzisuu() {
        return this.fukSzisuu;
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
     * @param hacSuuOld 発注数Old
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


//** 納期  **//
    /**
     * 納期(年)のセット
     * 
     * @param nkiYear 納期(年)
     */
    public void setNkiYear(String nkiYear) {
        this.nkiYear = nkiYear;
    }
    /**
     * 納期(年)の取得
     * 
     * @return 納期(年)
     */
    public String getNkiYear() {
        return this.nkiYear;
    }
    /**
     * 納期(月)のセット
     * 
     * @param nkiMonth 納期(月)
     */
    public void setNkiMonth(String nkiMonth) {
        this.nkiMonth = nkiMonth;
    }
    /**
     * 納期(月)の取得
     * 
     * @return 納期(月)
     */
    public String getNkiMonth() {
        return this.nkiMonth;
    }
    /**
     * 納期(日)のセット
     * 
     * @param nkiDay 納期(日)
     */
    public void setNkiDay(String nkiDay) {
        this.nkiDay = nkiDay;
    }

    /**
     * 納期(日)の取得
     * 
     * @return 納期(日)
     */
    public String getNkiDay() {
        return this.nkiDay;
    }




    /**
     * 発注番号のセット
     * 
     * @param hacBng 発注番号
     */
    public void setGyoBng(String gyoBng) {
        this.gyoBng = gyoBng;
    }
    /**
     * 発注番号の取得
     * 
     * @return 発注番号
     */
    public String getGyoBng() {
        return this.gyoBng;
    }



    /**
     * 納品名のセット
     * 
     * @param nhnMeiKj 納品名
     */
    public void setNhnMeiKj(String nhnMeiKj) {
        this.nhnMeiKj = nhnMeiKj;
    }
    /**
     * 納品名の取得
     * 
     * @return 納品名
     */
    public String getNhnMeiKj() {
        return this.nhnMeiKj;
    }

	/** 発注先・区分・記号番号・発売日ブレークフラグ
	 */
    public boolean getBreakflg(){
		return breakflg;
	}	
	public void setBreakflg(boolean s){
		breakflg = s;
	}	
	/** チェックボックス初期化メソッド
	 */
	public void clear_check(){
		this.setCheck_Mid(false);
    }


    /**
     * 表示品番
     * 
     * @param hjiHnb 表示品番
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
     * 分類コード
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
     * コメント
     * 
     * @param cmt コメント
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
	 * 備考２
	 * 
	 * @param biKou2 備考２
	 */
	public void setBiKou2(String biKou2) {
		this.biKou2 = biKou2;
	}
	/**
	 * 備考２の取得
	 * 
	 * @return 備考２
	 */
	public String getBiKou2() {
		return this.biKou2;
	}
    
//2004.01.22 add
	/**
 	* @param Cykkbn 直送区分
 	*/
	public void setCykkbn(String Cykkbn) {
		this.Cykkbn = Cykkbn;
	}

	/**
	* @return  直送区分
	*/
	public String getCykkbn() {
		return this.Cykkbn;
	}
	
//2005.05.25 add
	/**
	 * 場所コードの取得
	 * @return
	 */
	public String getBshCod() {
		return bshCod;
	}

	/**
	 * 場所コードのセット
	 * @param string
	 */
	public void setBshCod(String string) {
		bshCod = string;
	}

//2005.09.13 add
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

//	2008.03.07 add
	  /**
	   * 単価の取得
	   * @return
	   */
	  public String getTan2() {
		  return tan2;
	  }
	
	  /**
	   *単価の設定
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
		return tan2Old;
	}
	
	/**
	 *単価Oldの設定
	 * @param string
	 */
	public void setTan2Old(String string) {
		tan2Old = string;
	}

	/**
	 * 金額Oldの取得
	 * @return
	 */
	public String getKinOld() {
		return kinOld;
	}
	
	/**	
	 *金額Oldの設定
	 * @param string
	 */
	public void setKinOld(String string) {
		kinOld = string;
	}

}
