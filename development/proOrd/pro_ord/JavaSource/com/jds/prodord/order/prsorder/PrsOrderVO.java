/**
* PrsOrderVO  プレス・副資材発注画面バリューオブジェクトクラス
*
*	作成日    2003/04/28
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    品番マスター(FTBHIN01)、在庫テーブル(FTBZAI01)、発注履歴テーブル(FTBHAC02)、
* 			 発注先マスター(FTBHAC03)副資材マスター(FTBFUK01)から取得したデータを格納するクラス。
*
*	[変更履歴]
*        2003/05/12 （ＮＩＩ）蛭田 敬子
* 			・index,出力済サイン,発注済フラグ の追加。
* 		 2003/06/30
* 			・納品先コード 追加。
* 		 2004/02/25　(ＮＩＩ）森
* 			・副資材コード・仕入先の展開をパターンマスタから付属構成マスタに変更
* 		 2004/03/29 (NII) 森
* 			・品番マスタのテーブル名変更
* 		 2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		 2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応)
* 		 2004/04/21 (NII)森
* 			・税込定価表示に伴う修正
*		 2005/02/03（ＮＩＩ）蛭田
*			・場所コードの追加
*		 2005/05/25（ＮＩＩ）蛭田
*			・ユーザーＩＤの追加
*		 2007/12/25（ＮＩＩ）田中
*			・マニュアル発注指示一括貼付　備考２追加対応
* 
*/
package com.jds.prodord.order.prsorder;

import java.util.*;
import com.jds.prodord.common.*;

public class PrsOrderVO implements CommonOrderVO{
	
	/**
     * 前のページ（どのページから遷移してきたか）
     */
    private String pre_page = "";
	
	/**
     * 件数
     */
    private int count = 0;
        
    /**
     * 代表会社識別コード
     */
    private String daiKaiSkbCod = "";
    
    /**
     * 会社識別コード
     */
    private String kaiSkbCod = "";
    
    /**
     * 会社識別コード（ユーザー）
     */
    private String queryKaiSkbCod = "";
    
    /**
     * 場所コード
     */
    private String bshCod = "";
//--------------------------------------------------------

	
	/**
     * 品番マスター(FTBHIN01)存在フラグ
     */
    private boolean exsitHin01 = false;
	
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

    
//-------------------------------------------------------------------------------------

	/**
     * 区分
     */
    private String kbn = "";   

//--発注先マスター(FTBMST03)------------------------------------------------------------

    /**
     * 発注先名称
     */
    private String hacNm = "";  
      
       
//--在庫テーブル(FTBZAI01)--------------------------------------------------------------

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
     * プレス発注数
     */
    private String prsHacSuu = "";
    /**
     * プレス納期
     */
    private int prsNki = 0;
    /**
     * 直送区分
     */
    private String choksoKbn = "";  
    /**
     * コメント
     */
    private String comment;  
    /**
     * 納品先名
     */
    private String nhnMei;  
     
//--発注履歴テーブル(FTBHAC02)--------------------------------------------------------------

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
     * 副資材納期２
     */
    private String fukHacNki2 = "";
    /**
     * 副資材区分２
     */
    private String fukKbn2 = "";
//    /**
//     * 出力済サイン
//     */
//    private String syrZmiSgn = "";
     /**
     * 出力済サイン
     */
    private int syrZmiSgn_prs = 0;
     /**
     * 出力済サイン
     */
    private int syrZmiSgn_fuk = 0;
    /**
     * ユーザーＩＤ
     */
    private String usrId = "";
    
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
     * 副資材パターン
     */
    private String fukSziPtn = "";
    /**
	 * 副資材コードarr
	 */
	private ArrayList fukSziCod_arr = new ArrayList();
    /**
     * 副資材コード
     */
    private String fukSziCod = "";
    /**
     * 基準行フラグ
     */
    private String basedRowFlg = "";
	/**
	 * 分類コード
	 */
	private String bunCod = "";
	/**
	 * 副資材発注先arr
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
     
//--副資材在庫マスター(FTBHIN12)--------------------------------------------------   
    
    /**
     * 副資材数(HIN12)
     */
    private String fukSziSuu = "";
    
//-------------------------------------------------------------------------------------
    
    /**
     * プレスチェックボックス1
     */
    private boolean check_prs1 = false;

    /**
     * 副資材チェックボックス1
     */
    private boolean check_fuk1 = false;
	

	private int updDte;
	private int updJkk;
	

	/**
	 * 提案数照会画面でのindex
	 */
	private int index = 0;
	
	/**
	 * 発注済フラグ
	 */
	private boolean hacFlg = false;
	
	/**
	 * プレス発注画面か副資材発注画面かのサイン
	 */
	private String prs_FukSgn = "";
	
	//納品先コード
	private String nhnCod ="";

	/**
	 * 備考２
	 */
	private String biKou2 = "";
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
//-----------------------------------------------------------------------------

	/**
     * 品番マスター(FTBHIN01)存在フラグのセット
     * 
     * @param exsitZai01 品番マスター(FTBHIN01)存在フラグ
     */
    public void setExsitHin01(boolean exsitHin01) {

        this.exsitHin01 = exsitHin01;

    }

    /**
     * 品番マスター(FTBHIN01)存在フラグの取得
     * 
     * @return 品番マスター(FTBHIN01)存在フラグ
     */
    public boolean getExsitHin01() {

        return this.exsitHin01;

    }
    
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
     * @param hjiHnb 表示品番
     */
    public void setHjiHnb(String s) {

        this.hjiHnb = s;

    }

    /**
     * 表示品番の取得
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
	 * 税込価格
	 * @return　zikTik　税込価格
	 */
	public String getZikTik() {
		return zikTik;
	}

	/**
	 * 税込価格
	 * @param zikTik 税込価格
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
    
    // ----------------------------------------------------------- Properties Right
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
	 * 納品先名の取得
	 * 
	 * @return nhnMei
	 */
	public String getNhnMei() {
		
		return nhnMei;
		
	}
	/**
	 * 納品先名のセット
	 * 
	 * @param nhnMei 納品先名
	 */
	public void setNhnMei(String nhnMei) {
		
		this.nhnMei = nhnMei;
		
	}
	
//--------------------------------------------------------------------------
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
//------------------------------------------------------------------------------------------------------

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
	//2003/06/20	
	/**
     * 副資材コードarrのセット
     * 
     * @param fukSziCod_arr 副資材コードarr
     */
    public void setFukSziCod_arr(ArrayList fukSziCod_arr) {

        this.fukSziCod_arr = fukSziCod_arr;

    }
    /**
     * 副資材コードarrの取得
     * 
     * @return 副資材コードarr
     */
    public ArrayList getFukSziCod_arr() {

        return this.fukSziCod_arr;

    }
//2004.02.25 add 
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
     * 副資材コードのセット
     * 
     * @param fukSziCod 副資材コード
     */
    public void setFukSziCod(String fukSziCod) {

        this.fukSziCod = fukSziCod;

    }
	
	/**
     * 副資材コードの取得
     * 
     * @return 副資材コード
     */
    public String getFukSziCod() {

        return this.fukSziCod;

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
	 * 分類コードの取得
	 * @return bunCod
	 */
	public String getBunCod() {
		return bunCod;
	}

	/**
	 * 分類コードのセット
	 * @param bunCod
	 */
	public void setBunCod(String bunCod) {
		this.bunCod = bunCod;
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
	 * pre_pageの取得
	 * 
	 * @return pre_page
	 */
	public String getPre_page() {
		
		return pre_page;
		
	}
	/**
	 * pre_pageのセット
	 * 
	 * @param pre_page
	 */
	public void setPre_page(String pre_page) {
		
		this.pre_page = pre_page;
		
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
     * indexのセット
     */
    public void setIndex(int i) {
        this.index = i;
    }
    /**
     * indexの取得
     */
    public int getIndex() {
        return this.index;
    }
    
//    /**
//     * 出力済サインのセット
//     */
//    public void setSyrZmiSgn(String syrZmiSgn) {
//        this.syrZmiSgn = syrZmiSgn;
//    }
//    /**
//     * 出力済サインの取得
//     */
//    public String getSyrZmiSgn() {
//        return this.syrZmiSgn;
//    }
    
    /**
     * 出力済サインのセット
     */
    public void setSyrZmiSgn_prs(int syrZmiSgn_prs) {
        this.syrZmiSgn_prs = syrZmiSgn_prs;
    }
    /**
     * 出力済サインの取得
     */
    public int getSyrZmiSgn_prs() {
        return this.syrZmiSgn_prs;
    }
    
    /**
     * 出力済サインのセット
     */
    public void setSyrZmiSgn_fuk(int syrZmiSgn_fuk) {
        this.syrZmiSgn_fuk = syrZmiSgn_fuk;
    }
    /**
     * 出力済サインの取得
     */
    public int getSyrZmiSgn_fuk() {
        return this.syrZmiSgn_fuk;
    }
    /**
     * 発注済フラグのセット
     */
    public void setHacFlg(boolean hacFlg) {
        this.hacFlg = hacFlg;
    }
    /**
     * 発注済フラグの取得
     */
    public boolean getHacFlg() {
        return this.hacFlg;
    }
	
	/**
	 * プレス発注画面か副資材発注画面かのサインの取得
	 * 
	 * @param prs_FukSgn
	 */
	public String getPrs_FukSgn() {
		
		return prs_FukSgn;
		
	}
	/**
	 * プレス発注画面か副資材発注画面かのサインのセット
	 * 
	 * @param prs_FukSgn
	 */
	public void setPrs_FukSgn(String prs_FukSgn) {
		
		this.prs_FukSgn = prs_FukSgn;
		
	}

//2003/06/30
    /**
     * 納品先コードのセット
     * 
     * @param nhnCod 納品先コード
     */
    public void setNhnCod(String nhnCod) {

        this.nhnCod = nhnCod;

    }

    /**
     * 納品先コードの取得
     * 
     * @return 納品先コード
     */
    public String getNhnCod() {

        return this.nhnCod;

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
		String today = new DateUtils().getDate6();
		String smpKbn = DataFormatUtils.getSmpKbn(kbn, hbiDte, today);
		return smpKbn;
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
	 * ユーザーＩＤ取得
	 * @return
	 */
	public String getUsrId() {
		return usrId;
	}

	/**
	 * ユーザーＩＤセット
	 * @param string
	 */
	public void setUsrId(String string) {
		usrId = string;
	}
	
	/**
	 * 備考２のセット
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

	
	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("pre_page : " + pre_page + ", ");
		sb.append("count : " + count + ", ");
		sb.append("daiKaiSkbCod : " + daiKaiSkbCod + ", ");
		sb.append("kaiSkbCod : " + kaiSkbCod + ", ");
		sb.append("queryKaiSkbCod : " + queryKaiSkbCod + ", ");
		sb.append("bshCod : " + bshCod + ", ");
		sb.append("exsitHin01 : " + exsitHin01 + ", ");
		sb.append("exsitZai01 : " + exsitZai01 + ", ");
		sb.append("exsitHac02Prs : " + exsitHac02Prs + ", ");
		sb.append("exsitHac02Fuk : " + exsitHac02Fuk + ", ");
		sb.append("exsitFuk01 : " + exsitFuk01 + ", ");
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
		sb.append("kbn : " + kbn + ", ");
		sb.append("hacNm : " + hacNm + ", ");
		sb.append("prsMnyKei : " + prsMnyKei + ", ");
		sb.append("prsHacRui : " + prsHacRui + ", ");
		sb.append("fukZaiSuu : " + fukZaiSuu + ", ");
		sb.append("fukMnyKei : " + fukMnyKei + ", ");
		sb.append("fukHacRui : " + fukHacRui + ", ");
		sb.append("prsNyoKei : " + prsNyoKei + ", ");
		sb.append("fukNyoKei : " + fukNyoKei + ", ");
		sb.append("prsHacSuu : " + prsHacSuu + ", ");
		sb.append("prsNki : " + prsNki + ", ");
		sb.append("choksoKbn : " + choksoKbn + ", ");
		sb.append("comment : " + comment + ", ");
		sb.append("nhnMei : " + nhnMei + ", ");
		sb.append("prsHacSuu1 : " + prsHacSuu1 + ", ");
		sb.append("prsHacNyo1 : " + prsHacNyo1 + ", ");
		sb.append("prsHacNki1 : " + prsHacNki1 + ", ");
		sb.append("prsKbn1 : " + prsKbn1 + ", ");
		sb.append("prsHacSuu2 : " + prsHacSuu2 + ", ");
		sb.append("prsHacNyo2 : " + prsHacNyo2 + ", ");
		sb.append("prsKbn2 : " + prsKbn2 + ", ");
		sb.append("fukHacSuu1 : " + fukHacSuu1 + ", ");
		sb.append("fukHacNki1 : " + fukHacNki1 + ", ");
		sb.append("fukKbn1 : " + fukKbn1 + ", ");
		sb.append("fukHacSuu2 : " + fukHacSuu2 + ", ");
		sb.append("fukHacNki2 : " + fukHacNki2 + ", ");
		sb.append("fukKbn2 : " + fukKbn2 + ", ");
		sb.append("syrZmiSgn_prs : " + syrZmiSgn_prs + ", ");
		sb.append("syrZmiSgn_fuk : " + syrZmiSgn_fuk + ", ");
		sb.append("usrId : " + usrId + ", ");
		sb.append("fukSziHacSaki : " + fukSziHacSaki + ", ");
		sb.append("fukSziNm : " + fukSziNm + ", ");
		sb.append("fukSziPtn : " + fukSziPtn + ", ");
		sb.append("fukSziCod_arr : " + fukSziCod_arr + ", ");
		sb.append("fukSziCod : " + fukSziCod + ", ");
		sb.append("basedRowFlg : " + basedRowFlg + ", ");
		sb.append("bunCod : " + bunCod + ", ");
		sb.append("fukSziHacSaki_arr : " + fukSziHacSaki_arr + ", ");
		sb.append("bunCod_arr : " + bunCod_arr + ", ");
		sb.append("fukSziNm_arr : " + fukSziNm_arr + ", ");
		sb.append("fukSziSuu : " + fukSziSuu + ", ");
		sb.append("check_prs1 : " + check_prs1 + ", ");
		sb.append("check_fuk1 : " + check_fuk1 + ", ");
		sb.append("updDte : " + updDte + ", ");
		sb.append("updJkk : " + updJkk + ", ");
		sb.append("index : " + index + ", ");
		sb.append("hacFlg : " + hacFlg + ", ");
		sb.append("prs_FukSgn : " + prs_FukSgn + ", ");
		sb.append("nhnCod : " + nhnCod + ", ");
		sb.append("hacFlg : " + hacFlg + ", ");
		sb.append("prs_FukSgn : " + prs_FukSgn + ", ");
		sb.append("nhnCod : " + nhnCod);

		return sb.toString();
	}
}

