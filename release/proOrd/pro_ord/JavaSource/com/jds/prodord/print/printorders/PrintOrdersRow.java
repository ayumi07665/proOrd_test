/**
* PrintOrdersRow  発注書印刷行クラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの結果のうちの繰り返し項目を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
* 		 2004/04/21 (NII)森
* 			・税込定価表示に伴う修正
* 		 2005/06/14 (NII)蛭田
* 			・発売日の追加（Ｋ社対応）
* 		 2005/09/13（NII）蛭田
* 			・金額の追加（VAP社対応）
* 		 2007/12/25（ＮＩＩ）田中
* 			・マニュアル発注指示一括貼付　備考２追加対応
* 		 2008/03/10（ＮＩＩ）田中
* 			・単価追加対応
*/

package com.jds.prodord.print.printorders;

public class PrintOrdersRow {


    /**
     * 新譜旧譜区分
     */
    private String sinKyuKbn;
    

    /**
     * 明細行番号
     */
    private String gyoBng;
    

    /**
     * 記号番号
     */
    private String kigBng;
    

    /**
     * タイトル漢字
     */
    private String titKj;
    
    /**
     * アーティスト(漢字)
     */
    private String artKj = "";
    
    /**
     * 形態コード
     */
    private String ketCod;
    
    /**
     * 形態名称
     */
    private String ketNm;

    /**
     * プレス副資材コード
     */
    private String prsFukSziCod;
    

    /**
     * 副資材名称
     */
    private String fukMeiKj;
    
    
	/**
     * 副資材在庫数
     */
	private String fukSziSuu;
	
	
    /**
     * セット数
     */
    private String setSuu;
    

    /**
     * 発注数量
     */
    private String hacSuu;
    

    /**
     * 納期
     */
    private String nki;
    

    /**
     * 納品先名称
     */
    private String nhnMeiKj;
    
    
    /**
     * コメント
     */
    private String comment;

	/**
	 * 税込定価
	 */
	private String zikTik;
	/**
	 * 定価(税抜)
	 */
	private String tka;
	

	/**
	 * 発売日
	 */
	private String hbiDte;
	
	/**
	 * 金額
	 */
	private String kin;

	/**
	 * 備考２
	 */
	private String biKou2;

	/**
	 * 単価
	 */
	private String tan2;

//--------------------------------------------------------------------------


    /**
     * 新譜旧譜区分のセット
     * 
     * @param sinKyuKbn 新譜旧譜区分
     */
    public void setSinKyuKbn(String sinKyuKbn) {

        this.sinKyuKbn = sinKyuKbn;

    }


    /**
     * 新譜旧譜区分の取得
     * 
     * @return 新譜旧譜区分
     */
    public String getSinKyuKbn() {

        return this.sinKyuKbn;

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
    
    
    /**
     * アーティスト(漢字)のセット
     * 
     * @param artKj アーティスト(漢字)
     */
    public void setArtKj(String s) {

        this.artKj = s;

    }


    /**
     * アーティスト(漢字)の取得
     * 
     * @return アーティスト(漢字)
     */
    public String getArtKj() {

        return this.artKj;

    }
    

	/**
     * 形態コードのセット
     * 
     * @param ketCod 形態コード
     */
    public void setKetCod(String s) {

        this.ketCod = s;

    }


    /**
     * 形態コードの取得
     * 
     * @return 形態コード
     */
    public String getKetCod() {

        return this.ketCod;

    }
    
    /**
     * 形態名称のセット
     * 
     * @param ketNm 形態名称
     */
    public void setKetNm(String s) {

        this.ketNm = s;

    }


    /**
     * 形態名称の取得
     * 
     * @return 形態名称
     */
    public String getKetNm() {

        return this.ketNm;

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
     * 副資材名称のセット
     * 
     * @param fukMeiKj 副資材名称
     */
    public void setFukMeiKj(String fukMeiKj) {

        this.fukMeiKj = fukMeiKj;

    }


    /**
     * 副資材名称の取得
     * 
     * @return 副資材名称
     */
    public String getFukMeiKj() {

        return this.fukMeiKj;

    }
    
    
    /**
     * 副資材在庫数のセット
     * 
     * @param prsFukSziCod 副資材在庫数
     */
    public void setFukSziSuu(String fukSziSuu) {

        this.fukSziSuu = fukSziSuu;

    }


    /**
     * 副資材在庫数の取得
     * 
     * @return 副資材在庫数
     */
    public String getFukSziSuu() {

        return this.fukSziSuu;

    }


    /**
     * セット数のセット
     * 
     * @param setSuu セット数
     */
    public void setSetSuu(String setSuu) {

        this.setSuu = setSuu;

    }


    /**
     * セット数の取得
     * 
     * @return セット数
     */
    public String getSetSuu() {

        return this.setSuu;

    }


    /**
     * 発注数量のセット
     * 
     * @param hacSuu 発注数量
     */
    public void setHacSuu(String hacSuu) {

        this.hacSuu = hacSuu;

    }


    /**
     * 発注数量の取得
     * 
     * @return 発注数量
     */
    public String getHacSuu() {

        return this.hacSuu;

    }


    /**
     * 納期のセット
     * 
     * @param nki 納期
     */
    public void setNki(String nki) {

        this.nki = nki;

    }


    /**
     * 納期の取得
     * 
     * @return 納期
     */
    public String getNki() {

        return this.nki;

    }


    /**
     * 納品先名称のセット
     * 
     * @param nhnMeiKj 納品先名称
     */
    public void setNhnMeiKj(String nhnMeiKj) {

        this.nhnMeiKj = nhnMeiKj;

    }


    /**
     * 納品先名称の取得
     * 
     * @return 納品先名称
     */
    public String getNhnMeiKj() {

        return this.nhnMeiKj;

    }

	/**
	 * 税込定価
	 * @return　税込定価
	 */
	public String getZikTik() {
		return zikTik;
	}

	/**
	 * 税込定価
	 * @param zikTik
	 */
	public void setZikTik(String zikTik) {
		this.zikTik = zikTik;
	}

	/**
	 * 定価(税抜)
	 * @return　定価(税抜)
	 */
	public String getTka() {
		return tka;
	}

	/**
	 * 定価(税抜)
	 * @param tka
	 */
	public void setTka(String tka) {
		this.tka = tka;
	}
	
	/**
     * コメントのセット
     * 
     * @param comment コメント
     */
    public void setComment(String comment) {

        this.comment = comment;

    }


    /**
     * コメントの取得
     * 
     * @return コメント
     */
    public String getComment() {

        return this.comment;

    }

	/**
	 * 発売日の取得
	 * @return
	 */
	public String getHbiDte() {
		return hbiDte;
	}

	/**
	 * 発売日のセット
	 * @param string
	 */
	public void setHbiDte(String string) {
		hbiDte = string;
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

//---------------------------------------------------------------------------------------


	public void clear(){
		
        this.sinKyuKbn = "";
        this.gyoBng = "";
        this.kigBng = "";
        this.titKj = "";
        this.prsFukSziCod = "";
        this.fukMeiKj = "";
        this.setSuu = "";
        this.hacSuu = "";
        this.nki = "";
        this.nhnMeiKj = "";	
		this.comment = "";	
		this.zikTik = ""; //2004.04.21 add	
		this.tka = ""; //2014.02.19 add	
		this.hbiDte = ""; //2005.06.14 add
		this.kin = "";    //2005.09.13 add
		this.biKou2 = "";
		this.tan2 = "";
	}


}

