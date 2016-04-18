/**
* IkkatsuReferVO  一括照会画面バリューオブジェクトクラス
*
*	作成日    2003/03/31
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    品番マスター(FTBHIN01)、在庫テーブル(FTBZAI01)、発注履歴テーブル(FTBHAC02)、在庫日数マスター(FTBMST04)
* 			 副資材マスター(FTBFUK01)、形態ランク別条件マスター(FTBMST05)から取得したデータを格納するクラス。
*
*	[変更履歴]
*       2003/05/06（ＮＩＩ）蛭田 敬子
* 			・プレス発注画面に渡すデータ、発注先名称の追加。
* 		2003/07/01
* 			・納品先コード、納品先名の追加。
* 		2004/02/25　(ＮＩＩ）森
* 			・副資材コード・仕入先の展開をパターンマスタから付属構成マスタに変更
* 		2004/03/29 (NII) 森
* 			・品番マスタのテーブル名変更
* 		2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		2004/04/21 (NII)森
* 			・税込定価表示に伴う修正
*		2005/02/03（ＮＩＩ）蛭田
*			・場所コードの追加
*		2005/05/19（ＮＩＩ）蛭田
*			・ユーザーＩＤの追加
**/

package com.jds.prodord.reference.ikkatsurefer;

import java.util.*;
import com.jds.prodord.common.*;
import com.jds.prodord.order.prsorder.*;

public class IkkatsuReferVO implements CommonOrderVO{
	
	/**
     * 件数
     */
    private int count = 0;
        
    /**
     * 代表会社識別コード
     */
    private String daiKaiSkbCod = "";
    /**
     * 会社識別コード(ユーザー)
     */
    private String queryKaiSkbCod = "";
    
    /**
     * 会社識別コード
     */
    private String kaiSkbCod = "";
    /**
     * 場所コード
     */
    private String bshCod = "";
	/**
	 * ユーザーＩＤ
	 */
	private String usrId = "";
    
//--------------------------------------------------------

    /**
     * 在庫テーブル(FTBZAI01)存在フラグ
     */
    private boolean exsitZai01 = false;
    
    /**
     * 発注履歴テーブル(FTBHAC02)存在フラグ（プレス）
     */
    private boolean exsitHac02Prs = false;
    
    /**
     * 発注履歴テーブル(FTBHAC02)存在フラグ（副資材）
     */
    private boolean exsitHac02Fuk = false;
    
    /**
     * 在庫日数マスター(FTBMST04)存在フラグ
     */
    private boolean exsitMst04 = false;
    
    /**
     * 形態ランク別条件マスター(FTBMST05)存在フラグ
     */
    private boolean exsitMst05 = false; 
    
    /**
     * 副資材マスター(FTBFUK01)存在フラグ
     */
    private boolean exsitFuk01 = false; 

    

//--品番マスター(FTBHIN01)--------------------------------------------------------------

    /**
     * 形態コード
     */
    private String ketCod = "";
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
     * プレス発注先
     */
    private String prsMkrCod = "";
    /**
     * 副資材メーカーコード(副資材パターンコード)
     */
    private String fukSziMkr = "";
    /**
     * セット数
     */
    private String setSuu = "";
	/**
	 * 税込定価
	 */
	private String zikTik = "";
    

//--形態名称マスター(FTBZMST06)---------------------------------------------------------
    
    /**
     * 形態名称
     */
    private String ketNm = "";
    
       
//--在庫テーブル(FTBZAI01)--------------------------------------------------------------

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
     * 平均売上
     */
    private String avgUri = "";
    /**
     * 前々月棚上
     */
    private String b2mTna = "";
    /**
     * プレス未入庫計
     */
    private String prsMnyKei = "";
    /**
     * プレス発注累計
     */
    private String prsHacRui = "";
    /**
     * 副資材在庫数(ZAI01)
     */
    private String fukZaiSuu = "";
    /**
     * 副資材数(HIN12)
     */
    private String fukSziSuu = "";
    /**
     * 副資材未入庫計
     */
    private String fukMnyKei = "";
    /**
     * 副資材発注累計
     */
    private String fukHacRui = "";
    /**
     * プレス入庫累計
     */
    private String prsNyoKei = "";
    /**
     * 副資材入庫累計
     */
    private String fukNyoKei = "";
     
//---------------------------------------------------------------------------------------------

    /**
     * 提案数
     */
    private String teianSuu = "";
    /**
     * 不足在庫数
     */
    private String fskZaiSuu = "";
    /**
     * 可能数
     */
    private String kanouSuu = "";
    /**
     * 在庫日数
     */
    private String zaiNisuu = "";
    
//---------------------------------------------------------------------------------------------
    /**
     * プレス発注数
     */
    private String prsHacSuu = "";
    /**
     * プレス納期
     */
    private int prsNki = 0;
    /**
     * 副資材発注数
     */
    private String fukHacSuu = "";
    /**
     * 副資材納期
     */
    private int fukNki = 0;
    /**
     * 直送区分
     */
    private String choksoKbn = "";    
   
     
//--発注履歴テーブル(FTBHAC02)--------------------------------------------------------------

    /**
     * 発注済サイン
     */
    private int hacZmiSgn = 0;
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
     * プレス区分１
     */
    private String prsKbn1 = "";
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
     * プレス区分２
     */
    private String prsKbn2 = "";
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
     * 副資材区分１
     */
    private String fukKbn1 = "";
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
     * 副資材区分２
     */
    private String fukKbn2 = "";   
    
//--在庫日数マスター(FTBMST04)---------------------------------------------------------

    /**
     * 在庫日数・発注リードタイム（加算日数）
     */
    private int ksnNisuu = 0;

     
//--形態ランク別条件マスター(FTBMST05)--------------------------------------------------    
    
    /**
     * 生産リードタイム
     */
    private int ssnRedTim = 0;
    
    /**
     * まるめ数
     */
    private int mrmSuu = 0;
    
    
//--副資材マスター(FTBMST01)--------------------------------------------------    
    
    /**
     * 副資材発注先
     */
    private String fukSziHacSaki = "";
    
    /**
     * 副資材名称
     */
    private String fukSziNm = "";
    
    /**
     * 副資材コード
     */
    private String fukSziCod = "";
    
    /**
     * 副資材コードarr
     */
    private ArrayList fukSziCod_arr = new ArrayList();
    
	/**
	 * 副資材発注先コードarr 
	 */
	private ArrayList fukSziHacSaki_arr = new ArrayList(); //	2004.02.25 add
	/**
	 * 分類コードarr
	 */
	private ArrayList bunCod_arr = new ArrayList();
	/**
	 * 副資材名称arr
	 */
	private ArrayList fukSziNm_arr = new ArrayList();

    /**
     * 副資材パターンコード
     */
    private String fukSziPtn = "";
    
//-------------------------------------------------------------------------------------
    
    /**
     * プレスチェックボックス1
     */
    private boolean check_prs1 = false;
    /**
     * プレスチェックボックス2
     */
    private boolean check_prs2 = false;
    /**
     * 副資材チェックボックス1
     */
    private boolean check_fuk1 = false;
	/**
     * 副資材チェックフラグ
     */
    private boolean check_flg = false;
    /**
     * 副資材チェックサイン
     */
    private String check_sgn = "";
    /**
     * プレス強制発注用チェックボックス
     */
    private boolean check_prsHacSuu = false;

	private int updDte;
	private int updJkk;

	
	/**
     * 発注先名称
     */
    private String hacNm = "";
    /**
     * 基準行フラグ
     */
    private String basedRowFlg = "";
	/**
	 * 分類コード
	 */
	private String bunCod = "";
   
    //プレス発注画面に渡すVO
	private	PrsOrderVO[] prsVOs = null;
	
	//納品先コード
	private String nhnCod = "";
	
	//納品先名
	private String nhnMei = "";
//*************************************************************************************

    // ----------------------------------------------------------- Properties
    
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
     * 会社識別コード(ユーザー)のセット
     * 
     * @param queryKaiSkbCod 
     */
    public void setQueryKaiSkbCod(String queryKaiSkbCod) {

        this.queryKaiSkbCod = queryKaiSkbCod;

    }


    /**
     * 会社識別コード(ユーザー)の取得
     * 
     * @return 会社識別コード(ユーザー)
     */
    public String getQueryKaiSkbCod() {

        return this.queryKaiSkbCod;

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
//-----------------------------------------------------------------------------

    /**
     * 在庫テーブル(FTBZAI01)存在フラグのセット
     * 
     * @param exsitZai01 在庫テーブル(FTBZAI01)存在フラグ
     */
    public void setExsitZai01(boolean exsitZai01) {

        this.exsitZai01 = exsitZai01;

    }


    /**
     * 在庫テーブル(FTBZAI01)存在フラグの取得
     * 
     * @return 在庫テーブル(FTBZAI01)存在フラグ
     */
    public boolean getExsitZai01() {

        return this.exsitZai01;

    }


    /**
     * 発注履歴テーブルPrs(FTBHAC02)存在フラグのセット
     * 
     * @param exsitHac02 発注履歴テーブルPrs(FTBHAC02)存在フラグ
     */
    public void setExsitHac02Prs(boolean exsitHac02Prs) {

        this.exsitHac02Prs = exsitHac02Prs;

    }


    /**
     * 発注履歴テーブルPrs(FTBHAC02)存在フラグの取得
     * 
     * @return 発注履歴テーブルPrs(FTBHAC02)存在フラグ
     */
    public boolean getExsitHac02Prs() {

        return this.exsitHac02Prs;

    }

    /**
     * 発注履歴テーブルFuk(FTBHAC02)存在フラグのセット
     * 
     * @param exsitHac02 発注履歴テーブルFuk(FTBHAC02)存在フラグ
     */
    public void setExsitHac02Fuk(boolean exsitHac02Fuk) {

        this.exsitHac02Fuk = exsitHac02Fuk;

    }


    /**
     * 発注履歴テーブルFuk(FTBHAC02)存在フラグの取得
     * 
     * @return 発注履歴テーブルFuk(FTBHAC02)存在フラグ
     */
    public boolean getExsitHac02Fuk() {

        return this.exsitHac02Fuk;

    }
    /**
     * 在庫日数マスター(FTBMST04)存在フラグのセット
     * 
     * @param exsitMst04 在庫日数マスター(FTBMST04)存在フラグ
     */
    public void setExsitMst04(boolean exsitMst04) {

        this.exsitMst04 = exsitMst04;

    }


    /**
     * 在庫日数マスター(FTBMST04)存在フラグの取得
     * 
     * @return 在庫日数マスター(FTBMST04)存在フラグ
     */
    public boolean getExsitMst04() {

        return this.exsitMst04;

    }


    /**
     * 形態ランク別条件マスター(FTBMST05)存在フラグのセット
     * 
     * @param exsitMst05 形態ランク別条件マスター(FTBMST05)存在フラグ
     */
    public void setExsitMst05(boolean exsitMst05) {

        this.exsitMst05 = exsitMst05;

    }


    /**
     * 形態ランク別条件マスター(FTBMST05)存在フラグの取得
     * 
     * @return 形態ランク別条件マスター(FTBMST05)存在フラグ
     */
    public boolean getExsitMst05() {

        return this.exsitMst05;

    }
    
    /**
     * 副資材マスター(FTBFUK01)存在フラグのセット
     * 
     * @param exsitMst05 副資材マスター(FTBFUK01)存在フラグ
     */
    public void setExsitFuk01(boolean exsitFuk01) {

        this.exsitFuk01 = exsitFuk01;

    }


    /**
     * 副資材マスター(FTBFUK01)存在フラグの取得
     * 
     * @return 副資材マスター(FTBFUK01)存在フラグ
     */
    public boolean getExsitFuk01() {

        return this.exsitFuk01;

    }
    
    public void setExisted(){
		
		this.exsitZai01 = true;
	    this.exsitHac02Prs = true;
	    this.exsitHac02Fuk = true;
	    this.exsitMst04 = true;
	    this.exsitMst05 = true; 
	    this.exsitFuk01 = true; 

	}
 
    // ----------------------------------------------------------- Properties Left

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
    public void setHacZmiSgn(int i) {

        this.hacZmiSgn = i;

    }


    /**
     * 発注済サインの取得
     * 
     * @return 発注済サイン
     */
    public int getHacZmiSgn() {

        return this.hacZmiSgn;

    }

    // ----------------------------------------------------------- Properties Right

    /**
     * 在庫数のセット
     * 
     * @param aZiSuu 在庫数
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
     * 可能数のセット
     * 
     * @param kanouSuu 可能数
     */
    public void setKanouSuu(String kanouSuu) {

        this.kanouSuu = kanouSuu;

    }


    /**
     * 可能数の取得
     * 
     * @return 可能数
     */
    public String getKanouSuu() {

        return this.kanouSuu;

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
     * 在庫日数・発注リードタイムのセット
     * 
     * @param ksnNisuu 在庫日数・発注リードタイム
     */
    public void setKsnNisuu(int ksnNisuu) {

        this.ksnNisuu = ksnNisuu;

    }


    /**
     * 在庫日数・発注リードタイムの取得
     * 
     * @return 在庫日数・発注リードタイム
     */
    public int getKsnNisuu() {

        return this.ksnNisuu;

    }


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
     * @param hskZaiSuu 不足在庫数
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

     //プレス納期
	public int getPrsNki() {
		return prsNki;
	}
	public void setPrsNki(int i) {
		prsNki = i;
	}
	
	public void setPrsNki(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			prsNki = 0;
		else
			prsNki = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
	public String getPrsNkiYear(){
		if(prsNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(prsNki),6,"0");
		return str.substring(0,2);
	}
	public String getPrsNkiMonth(){
		if(prsNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(prsNki),6,"0");
		return str.substring(2,4);
	}
	public String getPrsNkiDay(){
		if(prsNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(prsNki),6,"0");
		return str.substring(4,6);
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


        return this.prsKbn1;


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


        return this.prsKbn2;


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


     //副資材納期
	public int getFukNki() {
		return fukNki;
	}
	public void setFukNki(int i) {
		fukNki = i;
	}
	
	public void setFukNki(String year,String month,String day){
		if(year.trim().equals("") || month.trim().equals("") || day.trim().equals(""))
			fukNki = 0;
		else
			fukNki = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
	public String getFukNkiYear(){
		if(fukNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(fukNki),6,"0");
		return str.substring(0,2);
	}
	public String getFukNkiMonth(){
		if(fukNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(fukNki),6,"0");
		return str.substring(2,4);
	}
	public String getFukNkiDay(){
		if(fukNki <= 0)
			return "";
		String str = StringUtils.lpad(String.valueOf(fukNki),6,"0");
		return str.substring(4,6);
	}


    /**
     * 副資材メーカーコード(副資材パターンコード)のセット
     * 
     * @param fukSziMkr 副資材メーカーコード
     */
    public void setFukSziMkr(String fukSziMkr) {

        this.fukSziMkr = fukSziMkr;

    }

    /**
     * 副資材メーカーコード(副資材パターンコード)の取得
     * 
     * @return 副資材メーカーコード
     */
    public String getFukSziMkr() {

        return fukSziMkr;

    }
    
    /**
     * 副資材発注先のセット
     * 
     * @param fukSziMkr 副資材発注先
     */
    public void setFukSziHacSaki(String fukSziHacSaki) {

        this.fukSziHacSaki = fukSziHacSaki;

    }

    /**
     * 副資材発注先の取得
     * 
     * @return 副資材発注先
     */
    public String getFukSziHacSaki() {

        return this.fukSziHacSaki;

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
     * 副資材在庫数(ZAI01)のセット
     * 
     * @param fukZaiSuu 副資材在庫数(ZAI01)
     */
    public void setFukZaiSuu(String fukZaiSuu) {

        this.fukZaiSuu = fukZaiSuu;

    }


    /**
     * 副資材在庫数(ZAI01)の取得
     * 
     * @return 副資材在庫数(ZAI01)
     */
    public String getFukZaiSuu() {

        return this.fukZaiSuu;

    }
    
    /**
     * 副資材数(HIN12)のセット 
     * 
     * @param fukSziSuu 副資材数(HIN12)
     */
    public void setFukSziSuu(String fukSziSuu) {

        this.fukSziSuu = fukSziSuu;

    }


    /**
     * 副資材数(HIN12)の取得
     * 
     * @return 副資材数(HIN12)
     */
    public String getFukSziSuu() {

        return this.fukSziSuu;

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


        return this.fukKbn1;


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


        return this.fukKbn2;


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

        this.check_flg = check_flg;

    }


    /**
     * 副資材チェックフラグ
     * 
     * @return 副資材チェックフラグ
     */
    public boolean getCheck_flg() {

        return this.check_flg;

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
//------------------------------------------------------------------------------------------------------
    /**
     * 生産リードタイムのセット
     * 
     * @param ssnRedTim 生産リードタイム
     */
    public void setSsnRedTim(int ssnRedTim) {

        this.ssnRedTim = ssnRedTim;

    }


    /**
     * 生産リードタイムの取得
     * 
     * @return 生産リードタイム
     */
    public int getSsnRedTim() {

        return this.ssnRedTim;

    }
    
    
    /**
     * まるめ数のセット
     * 
     * @param mrmSuu まるめ数
     */
    public void setMrmSuu(int mrmSuu) {

        this.mrmSuu = mrmSuu;

    }


    /**
     * まるめ数の取得
     * 
     * @return まるめ数
     */
    public int getMrmSuu() {

        return this.mrmSuu;

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
    
    /**
     * セット数のセット
     * 
     * @param setSuu 
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
     * 件数のセット
     */
    public void setCount(int i) {
        this.count = i;
    }
    /**
     * 件数の取得
     */
    public int getCount() {
        return this.count;
    }
    
    /**
	 * 副資材コードarrの取得
	 * @return Returns a ArrayList
	 */
	public ArrayList getFukSziCod_arr() {
		return fukSziCod_arr;
	}
	/**
	 * 副資材コードarrのセット
	 * @param fukSziCod_arr The fukSziCod_arr to set
	 */
	public void setFukSziCod_arr(ArrayList fukSziCod_arr) {
		this.fukSziCod_arr = fukSziCod_arr;
	}
	/**
	 * 分類コード
	 * @return
	 */
	public ArrayList getBunCod_arr() {
		return bunCod_arr;
	}

	/**
	 * 分類コード
	 * @param list
	 */
	public void setBunCod_arr(ArrayList list) {
		bunCod_arr = list;
	}
	/**
	 * 副資材名称
	 * @return
	 */
	public ArrayList getFukSziNm_arr() {
		return fukSziNm_arr;
	}

	/**
	 * 副資材名称
	 * @param list
	 */
	public void setFukSziNm_arr(ArrayList list) {
		fukSziNm_arr = list;
	}
    
    
	/**
	 * 副資材コードの取得
	 * @return Returns a String
	 */
	public String getFukSziCod() {
		return fukSziCod;
	}
	/**
	 * 副資材コードのセット
	 * @param fukSziCod The fukSziCod to set
	 */
	public void setFukSziCod(String fukSziCod) {
		this.fukSziCod = fukSziCod;
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
    
    /**
	 * 副資材パターンコードの取得
	 * @return Returns a String
	 */
	public String getFukSziPtn() {
		return fukSziPtn;
	}
	/**
	 * 副資材パターンコードのセット
	 * @param fukSziPtnCod The fukSziPtnCod to set
	 */
	public void setFukSziPtn(String fukSziPtnCod) {
		this.fukSziPtn = fukSziPtnCod;
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
	 * 発注先名の取得
	 * 
	 * @return 発注先名
	 */
	public String getHacNm() {
		
		return hacNm;
		
	}
	
	/**
	 * 発注先名のセット
	 * 
	 * @param hacNm 発注先名
	 */
	public void setHacNm(String hacNm) {
		
		this.hacNm = hacNm;
		
	}

	/**
     * PrsOrderVO[]のセット
     * 
     * @param prsVOs PrsOrderVO[]
     */
    public void setPrsVOs(PrsOrderVO[] prsVOs) {

        this.prsVOs = prsVOs;
   
    }


    /**
     * PrsOrderVO[]の取得
     * 
     * @return PrsOrderVO[]
     */
    public PrsOrderVO[] getPrsVOs() {

        return this.prsVOs;

    }
	
//2003/07/01
	/**
	 * 納品先コードの取得
	 * 
	 * @return 納品先コード
	 */
	public String getNhnCod() {
		
		return nhnCod;
		
	}
	
	/**
	 * 納品先コードのセット
	 * 
	 * @param nhnCod 納品先コード
	 */
	public void setNhnCod(String nhnCod) {
		
		this.nhnCod = nhnCod;
		
	}
	
	/**
	 * 納品先名の取得
	 * 
	 * @return 納品先名
	 */
	public String getNhnMei() {
		
		return nhnMei;
		
	}
	
	/**
	 * 納品先名のセット
	 * 
	 * @param nhnCod 納品先名
	 */
	public void setNhnMei(String nhnMei) {
		
		this.nhnMei = nhnMei;
		
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
	
	/**
	 * Gets サンプル区分
	 * @return Returns a String
	 */
	public String getSmpKbn(){	
		return "";//プレス発注のみなのでブランク
	}
//	2004.02.25 add 
	/**
	 * 副資材発注先
	 * @return
	 */
	public ArrayList getFukSziHacSaki_arr() {
		return fukSziHacSaki_arr;
	}

	/**
	 * 副資材発注先
	 * @param list
	 */
	public void setFukSziHacSaki_arr(ArrayList list) {
		fukSziHacSaki_arr = list;
	}

// 2004.04.02 add
	/**
	 *  分類コードの取得
	 * @return
	 */
	public String getBunCod() {
		return bunCod;
	}

	/**
	 *  分類コードのセット
	 * @param bunCod
	 */
	public void setBunCod(String bunCod) {
		this.bunCod = bunCod;
	}

// 2004.04.21 add
	/**
	 * 税込定価
	 * @return
	 */
	public String getZikTik() {
		return zikTik;
	}

	/**
	 * @param string
	 */
	public void setZikTik(String string) {
		zikTik = string;
	}

//2005/02/03 add
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

//2005/05/19 add
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
	
	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("count : " + count + ", ");
		sb.append("daiKaiSkbCod : " + daiKaiSkbCod + ", ");
		sb.append("queryKaiSkbCod : " + queryKaiSkbCod + ", ");
		sb.append("kaiSkbCod : " + kaiSkbCod + ", ");
		sb.append("bshCod : " + bshCod + ", ");
		sb.append("usrId : " + usrId + ", ");
		sb.append("exsitZai01 : " + exsitZai01 + ", ");
		sb.append("exsitHac02Prs : " + exsitHac02Prs + ", ");
		sb.append("exsitHac02Fuk : " + exsitHac02Fuk + ", ");
		sb.append("exsitMst04 : " + exsitMst04 + ", ");
		sb.append("exsitMst05  : " + exsitMst05 + ", ");
		sb.append("exsitFuk01  : " + exsitFuk01 + ", ");
		sb.append("ketCod : " + ketCod + ", ");
		sb.append("kigBng : " + kigBng + ", ");
		sb.append("hjiHnb : " + hjiHnb + ", ");
		sb.append("artKj : " + artKj + ", ");
		sb.append("hbiDte : " + hbiDte + ", ");
		sb.append("titKj : " + titKj + ", ");
		sb.append("tomRak : " + tomRak + ", ");
		sb.append("prsMkrCod : " + prsMkrCod + ", ");
		sb.append("fukSziMkr : " + fukSziMkr + ", ");
		sb.append("setSuu : " + setSuu + ", ");
		sb.append("zikTik : " + zikTik + ", ");
		sb.append("ketNm : " + ketNm + ", ");
		sb.append("aziSuu : " + aziSuu + ", ");
		sb.append("tnaSekZan : " + tnaSekZan + ", ");
		sb.append("jucZan : " + jucZan + ", ");
		sb.append("mhikSuu : " + mhikSuu + ", ");
		sb.append("rsvSum : " + rsvSum + ", ");
		sb.append("todUriSuu : " + todUriSuu + ", ");
		sb.append("b1dUri : " + b1dUri + ", ");
		sb.append("b2dUri : " + b2dUri + ", ");
		sb.append("b3dUri : " + b3dUri + ", ");
		sb.append("b4dUri : " + b4dUri + ", ");
		sb.append("b5dUri : " + b5dUri + ", ");
		sb.append("towUri : " + towUri + ", ");
		sb.append("b1wUri : " + b1wUri + ", ");
		sb.append("b2wUri : " + b2wUri + ", ");
		sb.append("tomUri : " + tomUri + ", ");
		sb.append("b1mUri : " + b1mUri + ", ");
		sb.append("b2mUri : " + b2mUri + ", ");
		sb.append("tomHpn : " + tomHpn + ", ");
		sb.append("b1mHpn : " + b1mHpn + ", ");
		sb.append("b2mHpn : " + b2mHpn + ", ");
		sb.append("tomTna : " + tomTna + ", ");
		sb.append("b1mTna : " + b1mTna + ", ");
		sb.append("avgUri : " + avgUri + ", ");
		sb.append("b2mTna : " + b2mTna + ", ");
		sb.append("prsMnyKei : " + prsMnyKei + ", ");
		sb.append("prsHacRui : " + prsHacRui + ", ");
		sb.append("fukZaiSuu : " + fukZaiSuu + ", ");
		sb.append("fukSziSuu : " + fukSziSuu + ", ");
		sb.append("fukMnyKei : " + fukMnyKei + ", ");
		sb.append("fukHacRui : " + fukHacRui + ", ");
		sb.append("prsNyoKei : " + prsNyoKei + ", ");
		sb.append("fukNyoKei : " + fukNyoKei + ", ");
		sb.append("teianSuu : " + teianSuu + ", ");
		sb.append("fskZaiSuu : " + fskZaiSuu + ", ");
		sb.append("kanouSuu : " + kanouSuu + ", ");
		sb.append("zaiNisuu : " + zaiNisuu + ", ");
		sb.append("prsHacSuu : " + prsHacSuu + ", ");
		sb.append("prsNki : " + prsNki + ", ");
		sb.append("fukHacSuu : " + fukHacSuu + ", ");
		sb.append("fukNki : " + fukNki + ", ");
		sb.append("choksoKbn     : " + choksoKbn + ", ");
		sb.append("hacZmiSgn : " + hacZmiSgn + ", ");
		sb.append("prsHacSuu1 : " + prsHacSuu1 + ", ");
		sb.append("prsHacNyo1 : " + prsHacNyo1 + ", ");
		sb.append("prsHacNki1 : " + prsHacNki1 + ", ");
		sb.append("prsKbn1 : " + prsKbn1 + ", ");
		sb.append("prsHacSuu2 : " + prsHacSuu2 + ", ");
		sb.append("prsHacNyo2 : " + prsHacNyo2 + ", ");
		sb.append("prsHacNki2 : " + prsHacNki2 + ", ");
		sb.append("prsKbn2 : " + prsKbn2 + ", ");
		sb.append("fukHacSuu1 : " + fukHacSuu1 + ", ");
		sb.append("fukHacNyo1 : " + fukHacNyo1 + ", ");
		sb.append("fukHacNki1 : " + fukHacNki1 + ", ");
		sb.append("fukKbn1 : " + fukKbn1 + ", ");
		sb.append("fukHacSuu2 : " + fukHacSuu2 + ", ");
		sb.append("fukHacNyo2 : " + fukHacNyo2 + ", ");
		sb.append("fukHacNki2 : " + fukHacNki2 + ", ");
		sb.append("fukKbn2    : " + fukKbn2    + ", ");
		sb.append("ksnNisuu : " + ksnNisuu + ", ");
		sb.append("ssnRedTim : " + ssnRedTim + ", ");
		sb.append("mrmSuu : " + mrmSuu + ", ");
		sb.append("fukSziHacSaki : " + fukSziHacSaki + ", ");
		sb.append("fukSziNm : " + fukSziNm + ", ");
		sb.append("fukSziCod : " + fukSziCod + ", ");
		sb.append("fukSziCod_arr : " + fukSziCod_arr + ", ");
		sb.append("fukSziHacSaki_arr : " + fukSziHacSaki_arr + ", ");
		sb.append("bunCod_arr : " + bunCod_arr + ", ");
		sb.append("fukSziNm_arr : " + fukSziNm_arr + ", ");
		sb.append("fukSziPtn : " + fukSziPtn + ", ");
		sb.append("check_prs1 : " + check_prs1 + ", ");
		sb.append("check_prs2 : " + check_prs2 + ", ");
		sb.append("check_fuk1 : " + check_fuk1 + ", ");
		sb.append("check_flg : " + check_flg + ", ");
		sb.append("check_sgn : " + check_sgn + ", ");
		sb.append("check_prsHacSuu : " + check_prsHacSuu + ", ");
		sb.append("updDte : " + updDte + ", ");
		sb.append("updJkk : " + updJkk + ", ");
		sb.append("hacNm : " + hacNm + ", ");
		sb.append("basedRowFlg : " + basedRowFlg + ", ");
		sb.append("bunCod : " + bunCod + ", ");
		sb.append("prsVOs : " + prsVOs + ", ");
		sb.append("nhnCod : " + nhnCod + ", ");
		sb.append("nhnMei : " + nhnMei);

		return sb.toString();
	}

}

