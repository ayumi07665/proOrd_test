/**
* PrsOrderLeftRow プレス・副資材発注画面Left行クラス
*
*	作成日    2003/04/28
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの結果のうちの繰り返し項目（左のフレーム）を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
* 		 2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		 2004/04/21 (NII)森
* 			・税込定価表示に伴う修正
* 
*/

package com.jds.prodord.order.prsorder;


public class PrsOrderLeftRow implements java.io.Serializable{
	
    /**
     * 代表会社識別コード
     */
    private String daiKaiSkbCod = "";
    
    /**
     * 会社識別コード
     */
    private String kaiSkbCod = "";
    
    
//-------------------------------------------------------------    	
    /**
     * 形態コード
     */
    private String ketCod = "";
    
    /**
     * 副資材パターン
     */
    private String fukSziPtn = "";
       
    /**
     * 形態名称
     */
    private String ketNm = "";

    /**
     * 記号番号
     */
    private String kigBng = "";
    
	/**
     * 表示品番
     */
    private String hjiHnb = "";
    
    /**
     * アーティスト(漢字)
     */
    private String artKj = "";
    
    /**
     * 発売日
     */
    private String hbiDte = "";

    /**
     * タイトル(漢字)
     */
    private String titKj = "";
    
    /**
     * 当月ランク
     */
    private String tomRak = "";
    
	/**
	 * 税込定価 //2004.04.21 add
	 */
	private String zikTik = "";

	/**
     * 区分
     */
    private String kbn = ""; 
    
    /**
     * 棚上積送
     */
    private String tnaSekZan = "";

    /**
     * 注残
     */
    private String jucZan = "";
    
    /**
     * 未引当
     */
    private String mhikSuu = "";
    
    /**
     * リザーブ
     */
    private String rsvSum = "";
    

    /**
     * 在庫日数
     */
    private String ksnNisuu = "";
    
//----------

    /**
     * 提案数
     */
    private String teianSuu = "";
    

    /**
     * 不足在庫数
     */
    private String fskZaiSuu = "";
    

    /**
     * 平均売上
     */
    private String avgUri = "";

//--------------

    /**
     * 副資材在庫数
     */
    private String fukZaiSuu = "";


    /**
     * 副資材発注歴１
     */
    private String fukHacSuu1 = "";


    /**
     * 副資材発注累計
     */
    private String fukHacRui = "";
    

	/**
     * 基準行フラグ
     */
    private String basedRowFlg = "";

//-----------------------------------------------------------------------------

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
	 * Gets the 副資材パターン
	 * @return Returns a String
	 */
	public String getFukSziPtn() {
		return fukSziPtn;
	}
	/**
	 * Sets the 副資材パターン
	 * @param fukSziPtn The fukSziPtn to set
	 */
	public void setFukSziPtn(String fukSziPtn) {
		this.fukSziPtn = fukSziPtn;
	}
	
    /**
     * 記号番号のセット
     * 
     * @param kigBng 記号番号
     */
    public void setKigBng(String s) {

        this.kigBng = s;

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
     * 表示品番のセット
     * 
     * @param hjiHnb 表示品番
     */
    public void setHjiHnb(String s) {

        this.hjiHnb = s;

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
     * 発売日のセット
     * 
     * @param hbiDte 発売日
     */
    public void setHbiDte(String s) {

        this.hbiDte = s;
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
     * タイトル(漢字)のセット
     * 
     * @param titKj タイトル(漢字)
     */
    public void setTitKj(String s) {

        this.titKj = s;

    }

    /**
     * タイトル(漢字)の取得
     * 
     * @return タイトル(漢字)
     */
    public String getTitKj() {

        return this.titKj;

    }

    /**
     * 当月ランクのセット
     * 
     * @param tomRak 当月ランク
     */
    public void setTomRak(String s) {

        this.tomRak = s;

    }

    /**
     * 当月ランクの取得
     * 
     * @return 当月ランク
     */
    public String getTomRak() {

        return this.tomRak;

    }
    	
	/**
	 * 税込定価
	 * @return　zikTik　税込定価
	 */
	public String getZikTik() {
		return zikTik;
	}

	/**
	 * @param string　税込定価
	 */
	public void setZikTik(String zikTik) {
		this.zikTik = zikTik;
	}

	/**
     * 区分のセット
     * 
     * @param kbn 区分
     */
    public void setKbn(String kbn) {

        this.kbn = kbn;

    }

    /**
     * 区分の取得
     * 
     * @return 区分
     */
    public String getKbn() {

        return this.kbn;

    }

    /**
     * 棚上積送のセット
     * 
     * @param tnaSekZan 棚上積送
     */
    public void setTnaSekZan(String tnaSekZan) {

        this.tnaSekZan = tnaSekZan;

    }

    /**
     * 棚上積送の取得
     * 
     * @return 棚上積送
     */
    public String getTnaSekZan() {

        return this.tnaSekZan;

    }

    /**
     * 注残のセット
     * 
     * @param jucZan 注残
     */
    public void setJucZan(String jucZan) {

        this.jucZan = jucZan;

    }

    /**
     * 注残の取得
     * 
     * @return 注残
     */
    public String getJucZan() {

        return this.jucZan;

    }

    /**
     * 未引当のセット
     * 
     * @param mhikSuu 未引当
     */
    public void setMhikSuu(String mhikSuu) {

        this.mhikSuu = mhikSuu;

    }

    /**
     * 未引当の取得
     * 
     * @return 未引当
     */
    public String getMhikSuu() {

        return this.mhikSuu;

    }

    /**
     * リザーブのセット
     * 
     * @param rsvSum リザーブ
     */
    public void setRsvSum(String rsvSum) {

        this.rsvSum = rsvSum;

    }

    /**
     * リザーブの取得
     * 
     * @return リザーブ
     */
    public String getRsvSum() {

        return this.rsvSum;

    }

    /**
     * 在庫日数のセット
     * 
     * @param knsNisuu 在庫日数
     */
    public void setKsnNisuu(String ksnNisuu) {

        this.ksnNisuu = ksnNisuu;

    }

    /**
     * 在庫日数の取得
     * 
     * @return 在庫日数
     */
    public String getKsnNisuu() {

        return this.ksnNisuu;

    }
//---------------

    /**
     * 提案数のセット
     * 
     * @param teianSuu 提案数
     */
    public void setTeianSuu(String teianSuu) {

        this.teianSuu = teianSuu;

    }

    /**
     * 提案数の取得
     * 
     * @return 提案数
     */
    public String getTeianSuu() {

        return this.teianSuu;

    }

    /**
     * 不足在庫数のセット
     * 
     * @param fskZaiSuu 不足在庫数
     */
    public void setFskZaiSuu(String fskZaiSuu) {

        this.fskZaiSuu = fskZaiSuu;

    }

    /**
     * 不足在庫数の取得
     * 
     * @return 不足在庫数
     */
    public String getFskZaiSuu() {

        return this.fskZaiSuu;

    }

    /**
     * 平均売上のセット
     * 
     * @param avgUri 平均売上
     */
    public void setAvgUri(String avgUri) {

        this.avgUri = avgUri;

    }

    /**
     * 平均売上の取得
     * 
     * @return 平均売上
     */
    public String getAvgUri() {

        return this.avgUri;

    }
//---------------

    /**
     * 副資材在庫数のセット
     * 
     * @param fukZaiSuu 副資材在庫数
     */
    public void setFukZaiSuu(String fukZaiSuu) {

        this.fukZaiSuu = fukZaiSuu;

    }

    /**
     * 副資材在庫数の取得
     * 
     * @return 副資材在庫数
     */
    public String getFukZaiSuu() {

        return this.fukZaiSuu;

    }

    /**
     * 副資材発注歴１のセット
     * 
     * @param fukHacSuu1 副資材発注歴１
     */
    public void setFukHacSuu1(String fukHacSuu1) {

        this.fukHacSuu1 = fukHacSuu1;

    }

    /**
     * 副資材発注歴１の取得
     * 
     * @return 副資材発注歴１
     */
    public String getFukHacSuu1() {

        return this.fukHacSuu1;

    }

    /**
     * 副資材発注累計のセット
     * 
     * @param fukHacRui 副資材発注累計
     */
    public void setFukHacRui(String fukHacRui) {

        this.fukHacRui = fukHacRui;

    }

    /**
     * 副資材発注累計の取得
     * 
     * @return 副資材発注累計
     */
    public String getFukHacRui() {

        return this.fukHacRui;

    }
	
	/**
	 * 基準行フラグの取得
	 * @return Returns a String
	 */
	public String getBasedRowFlg() {
		return basedRowFlg;
	}
	/**
	 * 基準行フラグのセット
	 * @param basedRowFlg The basedRowFlg to set
	 */
	public void setBasedRowFlg(String basedRowFlg) {
		this.basedRowFlg = basedRowFlg;
	}

}
