/**
* IkkatsuReferLeftRow  一括照会画面フォーム行クラス
*
*	作成日    2003/03/31
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの結果のうちの繰り返し項目（左のフレーム）を格納するクラス。
*
*	[変更履歴]
*        2003/05/15（ＮＩＩ）蛭田 敬子
*  			・発注済フラグ 追加。
* 		 2004/04/21 (NII)森
* 			・税込定価表示に伴う修正
*/

package com.jds.prodord.reference.ikkatsurefer;



public class IkkatsuReferLeftRow implements java.io.Serializable{
	
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
	 * 税込定価
	 */
	private String zikTik = "";
    

    /**
     * 発注済サイン
     */
    private String hacZmiSgn = "";

//------

    /**
     * 在庫数
     */
    private String aziSuu = "";
    

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
    private String zaiNisuu = "";
    
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
     * 副資材入庫累計
     */
    private String fukNyoKei = "";
    

	/**
     * 発注済フラグ
     */
    private boolean hacFlg = false;

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
     * 発注済サインのセット
     * 
     * @param hacZmiSgn 発注済サイン
     */
    public void setHacZmiSgn(String s) {

        this.hacZmiSgn = s;

    }


    /**
     * 発注済サインの取得
     * 
     * @return 発注済サイン
     */
    public String getHacZmiSgn() {

        return this.hacZmiSgn;

    }

	/**
	 * 税込定価
	 * @return　zikTik 税込定価
	 */
	public String getZikTik() {
		return zikTik;
	}

	/**
	 * @param zikTik　税込定価　
	 */
	public void setZikTik(String zikTik) {
		this.zikTik = zikTik;
	}

//-----------

    /**
     * 在庫数のセット
     * 
     * @param aziSuu 在庫数
     */
    public void setAziSuu(String aziSuu) {

        this.aziSuu = aziSuu;

    }


    /**
     * 在庫数の取得
     * 
     * @return 在庫数
     */
    public String getAziSuu() {

        return this.aziSuu;

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
     * @param zaiNisuu 在庫日数
     */
    public void setZaiNisuu(String zaiNisuu) {

        this.zaiNisuu = zaiNisuu;

    }


    /**
     * 在庫日数の取得
     * 
     * @return 在庫日数
     */
    public String getZaiNisuu() {

        return this.zaiNisuu;

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
	 * Gets the fukNyoKei
	 * @return Returns a String
	 */
	public String getFukNyoKei() {
		return fukNyoKei;
	}
	/**
	 * Sets the fukNyoKei
	 * @param fukNyoKei The fukNyoKei to set
	 */
	public void setFukNyoKei(String fukNyoKei) {
		this.fukNyoKei = fukNyoKei;
	}

	/**
     * 発注済フラグのセット
     * 
     * @param hacFlg 発注済フラグ
     */
    public void setHacFlg(boolean hacFlg) {

        this.hacFlg = hacFlg;

    }


    /**
     * 発注済フラグの取得
     * 
     * @return 発注済フラグ
     */
    public boolean getHacFlg() {

        return this.hacFlg;

    }

    // --------------------------------------------------------- Public Methods


	public void clear(){

        this.ketCod = "";
        this.kigBng = "";
        this.artKj = "";
        this.hbiDte = "";
        this.titKj = "";
        this.tomRak = "";
        this.hacZmiSgn = "";
        this.zikTik = "";
        
        this.aziSuu = "";
        this.tnaSekZan = "";
        this.jucZan = "";
        this.mhikSuu = "";
        this.rsvSum = "";
        this.zaiNisuu = "";
        this.teianSuu = "";
        this.fskZaiSuu = "";
        this.avgUri = "";
        this.fukZaiSuu = "";
        this.fukHacSuu1 = "";
        this.fukHacRui = "";
        this.fukNyoKei = "";
    
    }

		
}
