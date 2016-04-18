/**
* IkkatsuReferVO  発注書印刷バリューオブジェクトクラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    発注書データ(FTBHAC01)、会社マスター(FTBKAI01)から取得したデータを格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
* 		 2004/04/21 (NII)森
* 			・税込定価表示に伴う修正
*		2005/05/19（NII）蛭田
* 			・会社名漢字２(KAINMKJ2)、発売日の追加
* 		2005/08/29（NII）蛭田
* 			・更新履歴日、金額の追加
*		2007/12/25（ＮＩＩ）田中
* 			・マニュアル発注指示一括貼付　備考２追加対応
*		2008/03/10（ＮＩＩ）田中
* 			・単価追加対応
*		2014/02/19（ＮＩＩ）森
* 			・税抜単価対応
*/

package com.jds.prodord.print.printorders;

import java.util.ArrayList;

public class PrintOrdersVO {


	/**
     * 代表会社識別コード
     */
    private String daiKaiSkbCod;
	
	/**
     * 会社識別コード
     */
    private String kaiSkbCod;
    
    /**
     * 会社郵便番号
     */
    private String kaiYbnNo;
    

    /**
     * 会社住所１
     */
    private String kaiAdr1Kj;
    

    /**
     * 会社住所２
     */
    private String kaiAdr2Kj;
    

    /**
     * 会社電話番号
     */
    private String kaiTelNo;
    

    /**
     * 会社FAX番号
     */
    private String kaiFaxBng;

    
    /**
     * 会社ロゴsrc
     */
    private String kaiLogoSrc;
    
    /**
     * 会社名
     */
    private String kaiNmKj;

	/**
	 * 会社名２
	 */
	private String kaiNmKj2;    

    /**
     * 発注書日付
     */
    private String hacSyoDte;
    

    /**
     * 発注書番号
     */
    private String hacSyoBng;
    

    /**
     * 発注先コード
     */
    private String hacCod;
    

    /**
     * 発注先名称
     */
    private String sirHacNm;
    
    
    /**
     * 処理回数
     */
    private String syrSuu;
    
	/**
	 * 税込定価
	 */
	private String zikTik;
	
	/**
	 * 定価（税抜）
	 */
	private String tka;
	
	/**
	 * 更新履歴日
	 */
	private String rrkDte;

//-----------------------------------------------------------------------------------
 
	//ページレイアウト    
    private ArrayList pageLayout = null;
    
//-----------------------------------------------------------------------------------

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
     * 表示品番
     */
    private String hjiHnb = "";


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
    private String ketCod = "";

	/**
     * 形態名称
     */
    private String ketNm = "";
    
    /**
     * プレス副資材コード
     */
    private String prsFukSziCod = "";
    

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
     * 分類コード
     */
    private String bunCod;
    
    /**
     * シーケンスNo
     */
    private String seqNo;
    
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

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    

    /**
     * 
     * 代表会社識別コード
     * 
     */
    public String getDaiKaiSkbCod(){
    	return daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String s){
    	daiKaiSkbCod = s;
    }
    
    /**
     * 
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
    
    /**
     * 会社郵便番号のセット
     * 
     * @param kaiYbnNo 会社郵便番号
     */
    public void setKaiYbnNo(String kaiYbnNo) {

        this.kaiYbnNo = kaiYbnNo;

    }


    /**
     * 会社郵便番号の取得
     * 
     * @return 会社郵便番号
     */
    public String getKaiYbnNo() {

        return this.kaiYbnNo;

    }


    /**
     * 会社住所１のセット
     * 
     * @param kaiAdr1Kj 会社住所１
     */
    public void setKaiAdr1Kj(String kaiAdr1Kj) {

        this.kaiAdr1Kj = kaiAdr1Kj;

    }


    /**
     * 会社住所１の取得
     * 
     * @return 会社住所１
     */
    public String getKaiAdr1Kj() {

        return this.kaiAdr1Kj;

    }


    /**
     * 会社住所２のセット
     * 
     * @param kaiAdr2Kj 会社住所２
     */
    public void setKaiAdr2Kj(String kaiAdr2Kj) {

        this.kaiAdr2Kj = kaiAdr2Kj;

    }


    /**
     * 会社住所２の取得
     * 
     * @return 会社住所２
     */
    public String getKaiAdr2Kj() {

        return this.kaiAdr2Kj;

    }


    /**
     * 会社電話番号のセット
     * 
     * @param kaiTelNo 会社電話番号
     */
    public void setKaiTelNo(String kaiTelNo) {

        this.kaiTelNo = kaiTelNo;

    }


    /**
     * 会社電話番号の取得
     * 
     * @return 会社電話番号
     */
    public String getKaiTelNo() {

        return this.kaiTelNo;

    }


    /**
     * 会社FAX番号のセット
     * 
     * @param kaiFaxBng 会社FAX番号
     */
    public void setKaiFaxBng(String kaiFaxBng) {

        this.kaiFaxBng = kaiFaxBng;

    }


    /**
     * 会社FAX番号の取得
     * 
     * @return 会社FAX番号
     */
    public String getKaiFaxBng() {

        return this.kaiFaxBng;

    }


	/**
     * 
     * 会社ロゴsrcのセット
     * 
     * @param kaiLogoSrc 会社ロゴsrc
     */
    public void setKaiLogoSrc(String kaiLogoSrc) {
    	
        this.kaiLogoSrc = kaiLogoSrc;
    }


    /**
     * 会社ロゴsrcの取得
     * 
     * @return 会社ロゴsrc
     */
    public String getKaiLogoSrc() {

        return "/img/" + this.kaiLogoSrc;

    }

    /**
     * 会社名のセット
     * 
     * @param kaiNmKj 会社名
     */
    public void setKaiNmKj(String kaiNmKj) {

        this.kaiNmKj = kaiNmKj;

    }


    /**
     * 会社名の取得
     * 
     * @return 会社名
     */
    public String getKaiNmKj() {

        return this.kaiNmKj;

    }


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
     * 発注先名称のセット
     * 
     * @param sirHacNm 発注先名称
     */
    public void setSirHacNm(String sirHacNm) {

        this.sirHacNm = sirHacNm;

    }


    /**
     * 発注先名称の取得
     * 
     * @return 発注先名称
     */
    public String getSirHacNm() {

        return this.sirHacNm;

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
	 * @param zikTik　税込定価
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
	 * @param zikTik　定価(税抜)
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

	/**
	 * 会社名漢字２の取得
	 * @return
	 */
	public String getKaiNmKj2() {
		return kaiNmKj2;
	}

	/**
	 * 会社名漢字２のセット
	 * @param string
	 */
	public void setKaiNmKj2(String string) {
		kaiNmKj2 = string;
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
	 * 履歴更新日の取得
	 * @return
	 */
	public String getRrkDte() {
		return rrkDte;
	}

	/**
	 * 履歴更新日の設定
	 * @param string
	 */
	public void setRrkDte(String string) {
		rrkDte = string;
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
	 * @param i
	 */
	public void setKin(String string) {
		kin = string;
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
	 * @param i
	 */
	public void setTan2(String string) {
		tan2 = string;
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
	 * @param i
	 */
	public void setBiKou2(String string) {
		biKou2 = string;
	}

//-----------------------------------------------------------------------------------
 
	//ページレイアウト    
    public void setPageLayout(ArrayList arr){
    	this.pageLayout = arr;
    }
    public ArrayList getPageLayout(){
    	return this.pageLayout;
    }
    
	/* (非 Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("zikTik : " + zikTik + ", ");
		sb.append("tka : " + tka + ", ");
		sb.append("titKj : " + titKj + ", ");
		sb.append("syrSuu : " + syrSuu + ", ");
		sb.append("sirHacNm : " + sirHacNm + ", ");
		sb.append("sinKyuKbn : " + sinKyuKbn + ", ");
		sb.append("setSuu : " + setSuu + ", ");
		sb.append("seqNo : " + seqNo + ", ");
		sb.append("rrkDte : " + rrkDte + ", ");
		sb.append("prsFukSziCod : " + prsFukSziCod + ", ");
		sb.append("nki : " + nki + ", ");
		sb.append("nhnMeiKj : " + nhnMeiKj + ", ");
		sb.append("kigBng : " + kigBng + ", ");
		sb.append("ketNm : " + ketNm + ", ");
		sb.append("ketCod : " + ketCod + ", ");
		sb.append("kaiYbnNo : " + kaiYbnNo + ", ");
		sb.append("kaiTelNo : " + kaiTelNo + ", ");
		sb.append("kaiSkbCod : " + kaiSkbCod + ", ");
		sb.append("kaiNmKj2     : " + kaiNmKj2     + ", ");
		sb.append("kaiNmKj : " + kaiNmKj + ", ");
		sb.append("kaiLogoSrc : " + kaiLogoSrc + ", ");
		sb.append("kaiFaxBng : " + kaiFaxBng + ", ");
		sb.append("kaiAdr2Kj : " + kaiAdr2Kj + ", ");
		sb.append("kaiAdr1Kj : " + kaiAdr1Kj + ", ");
		sb.append("hjiHnb : " + hjiHnb + ", ");
		sb.append("hbiDte : " + hbiDte + ", ");
		sb.append("hacSyoDte : " + hacSyoDte + ", ");
		sb.append("hacSyoBng : " + hacSyoBng + ", ");
		sb.append("hacSuu : " + hacSuu + ", ");
		sb.append("hacCod : " + hacCod + ", ");
		sb.append("gyoBng : " + gyoBng + ", ");
		sb.append("fukSziSuu : " + fukSziSuu + ", ");
		sb.append("fukMeiKj : " + fukMeiKj + ", ");
		sb.append("daiKaiSkbCod : " + daiKaiSkbCod + ", ");
		sb.append("comment : " + comment + ", ");
		sb.append("bunCod : " + bunCod + ", ");
		sb.append("artKj : " + artKj + ", ");
		sb.append("kin : " + kin);
		sb.append("biKou2 : " + biKou2);
		sb.append("tan2 : " + tan2);

		return sb.toString();
	}
	
}

