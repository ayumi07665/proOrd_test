/**
* IkkatsuReferFormRow  一括照会画面フォーム行クラス
*
*	作成日    2003/03/31
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの結果のうちの繰り返し項目（右のフレーム）を格納するクラス。
*
*	[変更履歴]
*        2003/05/12（ＮＩＩ）蛭田 敬子
* 			・index,出力済サイン 追加。
*/

package com.jds.prodord.reference.ikkatsurefer;



public class IkkatsuReferFormRow implements java.io.Serializable{
	
	
    /**
     * 代表会社識別コード
     */
    private String daiKaiSkbCod = "";
    
    /**
     * 会社識別コード
     */
    private String kaiSkbCod = "";
    	
    /**
     * 記号番号
     */
    private String kigBng = "";
    
   	
    
    // --------------------------------------------------- Instance Variables    
    /**
     * 当日売上
     */
    private String todUriSuu = "";
    

    /**
     * 前日売上
     */
    private String b1dUri = "";
    

    /**
     * ２日前売上
     */
    private String b2dUri = "";
    

    /**
     * ３日前売上
     */
    private String b3dUri = "";
    

    /**
     * ４日前売上
     */
    private String b4dUri = "";
    

    /**
     * ５日前売上
     */
    private String b5dUri = "";
    

    /**
     * 当週売上
     */
    private String towUri = "";
    

    /**
     * 前週売上
     */
    private String b1wUri = "";
    

    /**
     * 前々週売上
     */
    private String b2wUri = "";


    /**
     * 当月売上
     */
    private String tomUri = "";
    

    /**
     * 前月売上
     */
    private String b1mUri = "";
    

    /**
     * 前々月売上
     */
    private String b2mUri = "";
    

    /**
     * 当月返品
     */
    private String tomHpn = "";
    

    /**
     * 前月返品
     */
    private String b1mHpn = "";
    

    /**
     * 前々月返品
     */
    private String b2mHpn = "";
    

    /**
     * 当月棚上
     */
    private String tomTna = "";
    

    /**
     * 前月棚上
     */
    private String b1mTna = "";
    

    /**
     * 前々月棚上
     */
    private String b2mTna = "";
    

    /**
     * プレス発注数
     */
    private String prsHacSuu = "";
    

    /**
     * プレス納期(年)
     */
    private String prsNkiYear = "";
    

    /**
     * プレス納期(月)
     */
    private String prsNkiMonth = "";
    

    /**
     * プレス納期(日)
     */
    private String prsNkiDay = "";
    

    /**
     * プレス発注先
     */
    private String prsMkrCod = "";


    /**
     * 直送区分
     */
    private String choksoKbn = "";    


    /**
     * プレス未入庫計
     */
    private String prsMnyKei = "";
    

    /**
     * プレス発注歴１
     */
    private String prsHacSuu1 = "";
    

    /**
     * プレス入庫歴１
     */
    private String prsHacNyo1 = "";
    

    /**
     * プレス納期１
     */
    private String prsHacNki1 = "";
    

    /**
     * プレス発注累計
     */
    private String prsHacRui = "";
    

    /**
     * プレス発注歴２
     */
    private String prsHacSuu2 = "";
    

    /**
     * プレス入庫歴２
     */
    private String prsHacNyo2 = "";
    

    /**
     * プレス納期２
     */
    private String prsHacNki2 = "";
    

    /**
     * プレスチェックボックス1
     */
    private boolean check_prs1 = false;
    

    /**
     * プレスチェックボックス2
     */
    private boolean check_prs2 = false;

    /**
     * 副資材発注数
     */
    private String fukHacSuu = "";
    

    /**
     * 副資材納期(年)
     */
    private String fukNkiYear = "";
    

    /**
     * 副資材納期(月)
     */
    private String fukNkiMonth = "";
    

    /**
     * 副資材納期(日)
     */
    private String fukNkiDay = "";
    

    /**
     * 副資材発注先
     */
    private String fukMkrCod = "";
    
    
    /**
     * 副資材在庫数
     */
    private String fukZaiSuu = "";


    /**
     * 副資材未入庫計
     */
    private String fukMnyKei = "";
    

    /**
     * 副資材発注歴１
     */
    private String fukHacSuu1 = "";
    

    /**
     * 副資材入庫歴１
     */
    private String fukHacNyo1 = "";
    

    /**
     * 副資材納期１
     */
    private String fukHacNki1 = "";
    

    /**
     * 副資材発注累計
     */
    private String fukHacRui = "";
    

    /**
     * 副資材発注歴２
     */
    private String fukHacSuu2 = "";
    

    /**
     * 副資材入庫歴２
     */
    private String fukHacNyo2 = "";
    

    /**
     * 副資材納期２
     */
    private String fukHacNki2 = "";
    

    /**
     * 副資材チェックボックス1
     */
    private boolean check_fuk1 = false;
    
    
    /**
     * 副資材チェックフラグ
     */
    private String check_flg = "";
    
    
    /**
     * 副資材チェックサイン
     */
    private String check_sgn = "";
    
    
    /**
     * プレス強制発注用チェックボックス
     */
    private boolean check_prsHacSuu = false;
    
    /**
     * プレス区分１
     */
    private String prsKbn1 = "";
    /**
     * プレス区分２
     */
    private String prsKbn2 = "";
    /**
     * 副資材区分１
     */
    private String fukKbn1 = "";
    /**
     * 副資材区分２
     */
    private String fukKbn2 = "";
    /**
     * プレス入庫累計
     */
    private String prsNyoKei = "";
    /**
     * 副資材入庫累計
     */
    private String fukNyoKei = "";
   
    
	//2003/05/15
    /**
     * index
     */
    private int index = 0;
        
    /**
     * 出力済サイン
     */
    private String syrZmiSgn = "";
    
    /**
     * 発注数エラーサイン
     */
    private boolean hacSuu_errSgn = false;

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
    
    

    // ----------------------------------------------------------- Properties

    /**
     * 当日売上のセット
     * 
     * @param todUriSuu 当日売上
     */
    public void setTodUriSuu(String todUriSuu) {

        this.todUriSuu = todUriSuu;

    }


    /**
     * 当日売上の取得
     * 
     * @return 当日売上
     */
    public String getTodUriSuu() {

        return this.todUriSuu;

    }


    /**
     * 前日売上のセット
     * 
     * @param b1dUri 前日売上
     */
    public void setB1dUri(String b1dUri) {

        this.b1dUri = b1dUri;

    }


    /**
     * 前日売上の取得
     * 
     * @return 前日売上
     */
    public String getB1dUri() {

        return this.b1dUri;

    }


    /**
     * ２日前売上のセット
     * 
     * @param b2dUri ２日前売上
     */
    public void setB2dUri(String b2dUri) {

        this.b2dUri = b2dUri;

    }


    /**
     * ２日前売上の取得
     * 
     * @return ２日前売上
     */
    public String getB2dUri() {

        return this.b2dUri;

    }


    /**
     * ３日前売上のセット
     * 
     * @param b3dUri ３日前売上
     */
    public void setB3dUri(String b3dUri) {

        this.b3dUri = b3dUri;

    }


    /**
     * ３日前売上の取得
     * 
     * @return ３日前売上
     */
    public String getB3dUri() {

        return this.b3dUri;

    }


    /**
     * ４日前売上のセット
     * 
     * @param b4dUri ４日前売上
     */
    public void setB4dUri(String b4dUri) {

        this.b4dUri = b4dUri;

    }


    /**
     * ４日前売上の取得
     * 
     * @return ４日前売上
     */
    public String getB4dUri() {

        return this.b4dUri;

    }


    /**
     * ５日前売上のセット
     * 
     * @param b5dUri ５日前売上
     */
    public void setB5dUri(String b5dUri) {

        this.b5dUri = b5dUri;

    }


    /**
     * ５日前売上の取得
     * 
     * @return ５日前売上
     */
    public String getB5dUri() {

        return this.b5dUri;

    }


    /**
     * 当週売上のセット
     * 
     * @param towUri 当週売上
     */
    public void setTowUri(String towUri) {

        this.towUri = towUri;

    }


    /**
     * 当週売上の取得
     * 
     * @return 当週売上
     */
    public String getTowUri() {

        return this.towUri;

    }


    /**
     * 前週売上のセット
     * 
     * @param b1wUri 前週売上
     */
    public void setB1wUri(String b1wUri) {

        this.b1wUri = b1wUri;

    }


    /**
     * 前週売上の取得
     * 
     * @return 前週売上
     */
    public String getB1wUri() {

        return this.b1wUri;

    }


    /**
     * 前々週売上のセット
     * 
     * @param b2wUri 前々週売上
     */
    public void setB2wUri(String b2wUri) {

        this.b2wUri = b2wUri;

    }


    /**
     * 前々週売上の取得
     * 
     * @return 前々週売上
     */
    public String getB2wUri() {

        return this.b2wUri;

    }

    /**
     * 当月売上のセット
     * 
     * @param tomUri 当月売上
     */
    public void setTomUri(String tomUri) {

        this.tomUri = tomUri;

    }


    /**
     * 当月売上の取得
     * 
     * @return 当月売上
     */
    public String getTomUri() {

        return this.tomUri;

    }


    /**
     * 前月売上のセット
     * 
     * @param b1mUri 前月売上
     */
    public void setB1mUri(String b1mUri) {

        this.b1mUri = b1mUri;

    }


    /**
     * 前月売上の取得
     * 
     * @return 前月売上
     */
    public String getB1mUri() {

        return this.b1mUri;

    }


    /**
     * 前々月売上のセット
     * 
     * @param b2mUri 前々月売上
     */
    public void setB2mUri(String b2mUri) {

        this.b2mUri = b2mUri;

    }


    /**
     * 前々月売上の取得
     * 
     * @return 前々月売上
     */
    public String getB2mUri() {

        return this.b2mUri;

    }


    /**
     * 当月返品のセット
     * 
     * @param tomHpn 当月返品
     */
    public void setTomHpn(String tomHpn) {

        this.tomHpn = tomHpn;

    }


    /**
     * 当月返品の取得
     * 
     * @return 当月返品
     */
    public String getTomHpn() {

        return this.tomHpn;

    }


    /**
     * 前月返品のセット
     * 
     * @param b1mHpn 前月返品
     */
    public void setB1mHpn(String b1mHpn) {

        this.b1mHpn = b1mHpn;

    }


    /**
     * 前月返品の取得
     * 
     * @return 前月返品
     */
    public String getB1mHpn() {

        return this.b1mHpn;

    }


    /**
     * 前々月返品のセット
     * 
     * @param b2mHpn 前々月返品
     */
    public void setB2mHpn(String b2mHpn) {

        this.b2mHpn = b2mHpn;

    }


    /**
     * 前々月返品の取得
     * 
     * @return 前々月返品
     */
    public String getB2mHpn() {

        return this.b2mHpn;

    }


    /**
     * 当月棚上のセット
     * 
     * @param tomTna 当月棚上
     */
    public void setTomTna(String tomTna) {

        this.tomTna = tomTna;

    }


    /**
     * 当月棚上の取得
     * 
     * @return 当月棚上
     */
    public String getTomTna() {

        return this.tomTna;

    }


    /**
     * 前月棚上のセット
     * 
     * @param b1mTna 前月棚上
     */
    public void setB1mTna(String b1mTna) {

        this.b1mTna = b1mTna;

    }


    /**
     * 前月棚上の取得
     * 
     * @return 前月棚上
     */
    public String getB1mTna() {

        return this.b1mTna;

    }


    /**
     * 前々月棚上のセット
     * 
     * @param b2mTna 前々月棚上
     */
    public void setB2mTna(String b2mTna) {

        this.b2mTna = b2mTna;

    }


    /**
     * 前々月棚上の取得
     * 
     * @return 前々月棚上
     */
    public String getB2mTna() {

        return this.b2mTna;

    }


    /**
     * プレス発注数のセット
     * 
     * @param prsHacSuu プレス発注数
     */
    public void setPrsHacSuu(String prsHacSuu) {

        this.prsHacSuu = prsHacSuu;

    }


    /**
     * プレス発注数の取得
     * 
     * @return プレス発注数
     */
    public String getPrsHacSuu() {

        return this.prsHacSuu;

    }


    /**
     * プレス納期(年)のセット
     * 
     * @param prsNkiYear プレス納期(年)
     */
    public void setPrsNkiYear(String prsNkiYear) {

        this.prsNkiYear = prsNkiYear;

    }


    /**
     * プレス納期(年)の取得
     * 
     * @return プレス納期(年)
     */
    public String getPrsNkiYear() {

        return this.prsNkiYear;

    }


    /**
     * プレス納期(月)のセット
     * 
     * @param prsNkiMonth プレス納期(月)
     */
    public void setPrsNkiMonth(String prsNkiMonth) {

        this.prsNkiMonth = prsNkiMonth;

    }


    /**
     * プレス納期(月)の取得
     * 
     * @return プレス納期(月)
     */
    public String getPrsNkiMonth() {

        return this.prsNkiMonth;

    }


    /**
     * プレス納期(日)のセット
     * 
     * @param prsNkiDay プレス納期(日)
     */
    public void setPrsNkiDay(String prsNkiDay) {

        this.prsNkiDay = prsNkiDay;

    }


    /**
     * プレス納期(日)の取得
     * 
     * @return プレス納期(日)
     */
    public String getPrsNkiDay() {

        return this.prsNkiDay;

    }


    /**
     * プレス発注先のセット
     * 
     * @param prsMkrCod プレス発注先
     */
    public void setPrsMkrCod(String prsMkrCod) {

        this.prsMkrCod = prsMkrCod;

    }


    /**
     * プレス発注先の取得
     * 
     * @return プレス発注先
     */
    public String getPrsMkrCod() {

        return this.prsMkrCod;

    }

  
    /**
     * 直送区分のセット
     * 
     * @param choksoKbn 直送区分
     */
    public void setChoksoKbn(String choksoKbn) {

        this.choksoKbn = choksoKbn;

    }


    /**
     * 直送区分の取得
     * 
     * @return 直送区分
     */
    public String getChoksoKbn() {

        return this.choksoKbn;

    }

    /**
     * プレス未入庫計のセット
     * 
     * @param prsMnyKei プレス未入庫計
     */
    public void setPrsMnyKei(String prsMnyKei) {

        this.prsMnyKei = prsMnyKei;

    }


    /**
     * プレス未入庫計の取得
     * 
     * @return プレス未入庫計
     */
    public String getPrsMnyKei() {

        return this.prsMnyKei;

    }


    /**
     * プレス発注歴１のセット
     * 
     * @param prsHacSuu1 プレス発注歴１
     */
    public void setPrsHacSuu1(String prsHacSuu1) {

        this.prsHacSuu1 = prsHacSuu1;

    }


    /**
     * プレス発注歴１の取得
     * 
     * @return プレス発注歴１
     */
    public String getPrsHacSuu1() {

        return this.prsHacSuu1;

    }


    /**
     * プレス入庫歴１のセット
     * 
     * @param prsHacNyo1 プレス入庫歴１
     */
    public void setPrsHacNyo1(String prsHacNyo1) {

        this.prsHacNyo1 = prsHacNyo1;

    }


    /**
     * プレス入庫歴１の取得
     * 
     * @return プレス入庫歴１
     */
    public String getPrsHacNyo1() {

        return this.prsHacNyo1;

    }


    /**
     * プレス納期１のセット
     * 
     * @param prsHacNki1 プレス納期１
     */
    public void setPrsHacNki1(String prsHacNki1) {

        this.prsHacNki1 = prsHacNki1;

    }


    /**
     * プレス納期１の取得
     * 
     * @return プレス納期１
     */
    public String getPrsHacNki1() {

        return this.prsHacNki1;

    }


    /**
     * プレス発注累計のセット
     * 
     * @param prsHacRui プレス発注累計
     */
    public void setPrsHacRui(String prsHacRui) {

        this.prsHacRui = prsHacRui;

    }


    /**
     * プレス発注累計の取得
     * 
     * @return プレス発注累計
     */
    public String getPrsHacRui() {

        return this.prsHacRui;

    }


    /**
     * プレス発注歴２のセット
     * 
     * @param prsHacSuu2 プレス発注歴２
     */
    public void setPrsHacSuu2(String prsHacSuu2) {

        this.prsHacSuu2 = prsHacSuu2;

    }


    /**
     * プレス発注歴２の取得
     * 
     * @return プレス発注歴２
     */
    public String getPrsHacSuu2() {

        return this.prsHacSuu2;

    }


    /**
     * プレス入庫歴２のセット
     * 
     * @param prsHacNyo2 プレス入庫歴２
     */
    public void setPrsHacNyo2(String prsHacNyo2) {

        this.prsHacNyo2 = prsHacNyo2;

    }


    /**
     * プレス入庫歴２の取得
     * 
     * @return プレス入庫歴２
     */
    public String getPrsHacNyo2() {

        return this.prsHacNyo2;

    }


    /**
     * プレス納期２のセット
     * 
     * @param prsHacNki2 プレス納期２
     */
    public void setPrsHacNki2(String prsHacNki2) {

        this.prsHacNki2 = prsHacNki2;

    }


    /**
     * プレス納期２の取得
     * 
     * @return プレス納期２
     */
    public String getPrsHacNki2() {

        return this.prsHacNki2;

    }


    /**
     * プレスチェックボックス1のセット
     * 
     * @param checkprs1 プレスチェックボックス1
     */
    public void setCheck_prs1(boolean check_prs1) {

        this.check_prs1 = check_prs1;

    }


    /**
     * プレスチェックボックス1の取得
     * 
     * @return プレスチェックボックス1
     */
    public boolean getCheck_prs1() {

        return this.check_prs1;

    }


    /**
     * プレスチェックボックス2のセット
     * 
     * @param checkprs2 プレスチェックボックス2
     */
    public void setCheck_prs2(boolean check_prs2) {

        this.check_prs2 = check_prs2;

    }


    /**
     * プレスチェックボックス2の取得
     * 
     * @return プレスチェックボックス2
     */
    public boolean getCheck_prs2() {

        return this.check_prs2;

    }

    /**
     * 副資材発注数のセット
     * 
     * @param fukHacSuu 副資材発注数
     */
    public void setFukHacSuu(String fukHacSuu) {

        this.fukHacSuu = fukHacSuu;

    }


    /**
     * 副資材発注数の取得
     * 
     * @return 副資材発注数
     */
    public String getFukHacSuu() {

        return this.fukHacSuu;

    }


    /**
     * 副資材納期(年)のセット
     * 
     * @param fukNkiYear 副資材納期(年)
     */
    public void setFukNkiYear(String fukNkiYear) {

        this.fukNkiYear = fukNkiYear;

    }


    /**
     * 副資材納期(年)の取得
     * 
     * @return 副資材納期(年)
     */
    public String getFukNkiYear() {

        return this.fukNkiYear;

    }


    /**
     * 副資材納期(月)のセット
     * 
     * @param fukNkiMonth 副資材納期(月)
     */
    public void setFukNkiMonth(String fukNkiMonth) {

        this.fukNkiMonth = fukNkiMonth;

    }


    /**
     * 副資材納期(月)の取得
     * 
     * @return 副資材納期(月)
     */
    public String getFukNkiMonth() {

        return this.fukNkiMonth;

    }


    /**
     * 副資材納期(日)のセット
     * 
     * @param fukNkiDay 副資材納期(日)
     */
    public void setFukNkiDay(String fukNkiDay) {

        this.fukNkiDay = fukNkiDay;

    }


    /**
     * 副資材納期(日)の取得
     * 
     * @return 副資材納期(日)
     */
    public String getFukNkiDay() {

        return this.fukNkiDay;

    }


    /**
     * 副資材発注先のセット
     * 
     * @param fukMkrCod 副資材発注先
     */
    public void setFukMkrCod(String fukMkrCod) {

        this.fukMkrCod = fukMkrCod;

    }


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
     * 副資材発注先の取得
     * 
     * @return 副資材発注先
     */
    public String getFukMkrCod() {

        return this.fukMkrCod;

    }


    /**
     * 副資材未入庫計のセット
     * 
     * @param fukMnyKei 副資材未入庫計
     */
    public void setFukMnyKei(String fukMnyKei) {

        this.fukMnyKei = fukMnyKei;

    }


    /**
     * 副資材未入庫計の取得
     * 
     * @return 副資材未入庫計
     */
    public String getFukMnyKei() {

        return this.fukMnyKei;

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
     * 副資材入庫歴１のセット
     * 
     * @param fukHacNyo1 副資材入庫歴１
     */
    public void setFukHacNyo1(String fukHacNyo1) {

        this.fukHacNyo1 = fukHacNyo1;

    }


    /**
     * 副資材入庫歴１の取得
     * 
     * @return 副資材入庫歴１
     */
    public String getFukHacNyo1() {

        return this.fukHacNyo1;

    }


    /**
     * 副資材納期１のセット
     * 
     * @param fukHacNki1 副資材納期１
     */
    public void setFukHacNki1(String fukHacNki1) {

        this.fukHacNki1 = fukHacNki1;

    }


    /**
     * 副資材納期１の取得
     * 
     * @return 副資材納期１
     */
    public String getFukHacNki1() {

        return this.fukHacNki1;

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
     * 副資材発注歴２のセット
     * 
     * @param fukHacSuu2 副資材発注歴２
     */
    public void setFukHacSuu2(String fukHacSuu2) {

        this.fukHacSuu2 = fukHacSuu2;

    }


    /**
     * 副資材発注歴２の取得
     * 
     * @return 副資材発注歴２
     */
    public String getFukHacSuu2() {

        return this.fukHacSuu2;

    }


    /**
     * 副資材入庫歴２のセット
     * 
     * @param fukHacNyo2 副資材入庫歴２
     */
    public void setFukHacNyo2(String fukHacNyo2) {

        this.fukHacNyo2 = fukHacNyo2;

    }


    /**
     * 副資材入庫歴２の取得
     * 
     * @return 副資材入庫歴２
     */
    public String getFukHacNyo2() {

        return this.fukHacNyo2;

    }


    /**
     * 副資材納期２のセット
     * 
     * @param fukHacNki2 副資材納期２
     */
    public void setFukHacNki2(String fukHacNki2) {

        this.fukHacNki2 = fukHacNki2;

    }


    /**
     * 副資材納期２の取得
     * 
     * @return 副資材納期２
     */
    public String getFukHacNki2() {

        return this.fukHacNki2;

    }


    /**
     * 副資材チェックボックス1のセット
     * 
     * @param checkfuk1 副資材チェックボックス1
     */
    public void setCheck_fuk1(boolean check_fuk1) {

        this.check_fuk1 = check_fuk1;

    }


    /**
     * 副資材チェックボックス1の取得
     * 
     * @return 副資材チェックボックス1
     */
    public boolean getCheck_fuk1() {

        return this.check_fuk1;

    }

	/**
     * 副資材チェックフラグ
     * 
     * @param check_flg 副資材チェックフラグ
     */
    public void setCheck_flg(boolean check_flg) {
		
        this.check_flg = check_flg+"";

    }


    /**
     * 副資材チェックフラグ
     * 
     * @return 副資材チェックフラグ
     */
    public String getCheck_flg() {

        return this.check_flg;

    }
    
    /**
     * 副資材チェックサイン
     * 
     * @param check_flg 副資材チェックサイン
     */
    public void setCheck_sgn(String check_sgn) {
		
        this.check_sgn = check_sgn;

    }


    /**
     * 副資材チェックサイン
     * 
     * @return 副資材チェックサイン
     */
    public String getCheck_sgn() {

        return this.check_sgn;

    }

	/**
     * プレス強制発注用チェックボックス
     * 
     * @param check_prsHacSuu プレス強制発注用チェックボックス
     */
    public void setCheck_prsHacSuu(boolean check_prsHacSuu) {
		
        this.check_prsHacSuu = check_prsHacSuu;

    }


    /**
     * プレス強制発注用チェックボックス
     * 
     * @return プレス強制発注用チェックボックス
     */
    public boolean getCheck_prsHacSuu() {

        return this.check_prsHacSuu;

    }

	/**
     * index
     * 
     * @param index index
     */
    public void setIndex(int index) {
		
        this.index = index;

    }


    /**
     * index
     * 
     * @return index
     */
    public int getIndex() {

        return this.index;

    }

    /**
     * 出力済サイン
     * 
     * @param syrZmiSgn 出力済サイン
     */
    public void setSyrZmiSgn(String syrZmiSgn) {
		
        this.syrZmiSgn = syrZmiSgn;

    }


    /**
     * 出力済サイン
     * 
     * @return 出力済サイン
     */
    public String getSyrZmiSgn() {

        return this.syrZmiSgn;

    }
    
    /**
     * 発注数エラーサイン
     * 
     * @param hacSuu_errSgn 発注数エラーサイン
     */
    public void setHacSuu_errSgn(boolean hacSuu_errSgn) {
		
        this.hacSuu_errSgn = hacSuu_errSgn;

    }


    /**
     * 発注数エラーサイン
     * 
     * @return 発注数エラーサイン
     */
    public boolean getHacSuu_errSgn() {

        return this.hacSuu_errSgn;

    }
    
	
    /**
     * プレス区分１のセット
     * 
     * @param prsKbn1 プレス区分１
     */
    public void setPrsKbn1(String prsKbn1) {


        this.prsKbn1 = prsKbn1;


    }

    /**
     * プレス区分１の取得
     * 
     * @return プレス区分１
     */
    public String getPrsKbn1() {

		if(this.prsKbn1 == null || this.prsKbn1.equals(""))
			return this.prsKbn1;
        return this.prsKbn1.substring(0,1);


    }

	/**
     * プレス区分２のセット
     * 
     * @param prsKbn2 プレス区分２
     */
    public void setPrsKbn2(String prsKbn2) {


        this.prsKbn2 = prsKbn2;


    }

    /**
     * プレス区分２の取得
     * 
     * @return プレス区分２
     */
    public String getPrsKbn2() {

		if(this.prsKbn2 == null || this.prsKbn2.equals(""))
			return this.prsKbn2;
        return this.prsKbn2.substring(0,1);


    }

	/**
     * 副資材区分１のセット
     * 
     * @param prsKbn1 副資材区分１
     */
    public void setFukKbn1(String fukKbn1) {


        this.fukKbn1 = fukKbn1;


    }

    /**
     * 副資材区分１の取得
     * 
     * @return 副資材区分１
     */
    public String getFukKbn1() {

		if(this.fukKbn1 == null || this.fukKbn1.equals(""))
			return this.fukKbn1;
        return this.fukKbn1.substring(0,1);

    }

	/**
     * 副資材区分２のセット
     * 
     * @param prsKbn2 副資材区分２
     */
    public void setFukKbn2(String fukKbn2) {


        this.fukKbn2 = fukKbn2;


    }

    /**
     * 副資材区分２の取得
     * 
     * @return 副資材区分２
     */
    public String getFukKbn2() {

		if(this.fukKbn2 == null || this.fukKbn2.equals(""))
			return this.fukKbn2;
        return this.fukKbn2.substring(0,1);

    }
	
	/**
	 * Gets the prsNyoKei
	 * @return Returns a String
	 */
	public String getPrsNyoKei() {
		return prsNyoKei;
	}
	/**
	 * Sets the prsNyoKei
	 * @param prsNyoKei The prsNyoKei to set
	 */
	public void setPrsNyoKei(String prsNyoKei) {
		this.prsNyoKei = prsNyoKei;
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
	
     public void clear(){
    
        this.todUriSuu = "";
        this.b1dUri = "";
        this.b2dUri = "";
        this.b3dUri = "";
        this.b4dUri = "";
        this.b5dUri = "";
        this.towUri = "";
        this.b1wUri = "";
        this.b2wUri = "";
        this.tomUri = "";
        this.b1mUri = "";
        this.b2mUri = "";
        this.tomHpn = "";
        this.b1mHpn = "";
        this.b2mHpn = "";
        this.tomTna = "";
        this.b1mTna = "";
        this.b2mTna = "";
        this.prsHacSuu = "";
        this.prsNkiYear = "";
        this.prsNkiMonth = "";
        this.prsNkiDay = "";
        this.prsMkrCod = "";
    	this.choksoKbn = ""; 
        this.prsMnyKei = "";
        this.prsHacSuu1 = "";
        this.prsHacNyo1 = "";
        this.prsHacNki1 = "";
        this.prsHacRui = "";
        this.prsHacSuu2 = "";
        this.prsHacNyo2 = "";
        this.prsHacNki2 = "";
        this.check_prs1 = false;
        this.check_prs2 = false;
        this.fukHacSuu = "";
        this.fukNkiYear = "";
        this.fukNkiMonth = "";
        this.fukNkiDay = "";
        this.fukMkrCod = "";
        this.fukZaiSuu = "";
        this.fukMnyKei = "";
        this.fukHacSuu1 = "";
        this.fukHacNyo1 = "";
        this.fukHacNki1 = "";
        this.fukHacRui = "";
        this.fukHacSuu2 = "";
        this.fukHacNyo2 = "";
        this.fukHacNki2 = "";
        this.check_fuk1 = false;
        this.check_flg = "";
        this.check_sgn = "";
		this.check_prsHacSuu = false;
    	this.hacSuu_errSgn = false;
    	this.prsKbn1 = "";
        this.prsKbn2 = "";
        this.fukKbn1 = "";
        this.fukKbn2 = "";
        this.prsNyoKei = "";
        this.fukNyoKei = "";
    }

}
