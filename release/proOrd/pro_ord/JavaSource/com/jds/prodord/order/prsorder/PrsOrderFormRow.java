/**
* PrsOrderFormRow vXEÞ­æÊtH[sNX
*
*	ì¬ú    2003/04/28
*	ì¬Ò    imhhjªc Äü
* Tv    HttpNGXgÆNGXgÌÊÌ¤¿ÌJèÔµÚiEÌt[jði[·éNXB
*
*	[ÏXð]
*             út ¼O
*  Eàe
*/

package com.jds.prodord.order.prsorder;



public class PrsOrderFormRow implements java.io.Serializable{
	
	
    /**
     * ã\ïÐ¯ÊR[h
     */
    private String daiKaiSkbCod = "";
    
    /**
     * ïÐ¯ÊR[h
     */
    private String kaiSkbCod = "";
    	
    /**
     * LÔ
     */
    private String kigBng = "";
    
   	
    /**
     * Rg
     */
    private String comment = "";  
    
    /**
     * [iæ¼
     */
    private String nhnMei = "";
    
    /**
     * ­æ¼Ì
     */
    private String hacNm = ""; 
    
    
    
    // --------------------------------------------------- Instance Variables    
    /**
     * úã
     */
    private String todUriSuu = "";
    

    /**
     * Oúã
     */
    private String b1dUri = "";
    

    /**
     * QúOã
     */
    private String b2dUri = "";
    

    /**
     * RúOã
     */
    private String b3dUri = "";
    

    /**
     * SúOã
     */
    private String b4dUri = "";
    

    /**
     * TúOã
     */
    private String b5dUri = "";
    

    /**
     * Tã
     */
    private String towUri = "";
    

    /**
     * OTã
     */
    private String b1wUri = "";
    

    /**
     * OXTã
     */
    private String b2wUri = "";


    /**
     * ã
     */
    private String tomUri = "";
    

    /**
     * Oã
     */
    private String b1mUri = "";
    

    /**
     * OXã
     */
    private String b2mUri = "";
    

    /**
     * Ôi
     */
    private String tomHpn = "";
    

    /**
     * OÔi
     */
    private String b1mHpn = "";
    

    /**
     * OXÔi
     */
    private String b2mHpn = "";
    

    /**
     * Iã
     */
    private String tomTna = "";
    

    /**
     * OIã
     */
    private String b1mTna = "";
    

    /**
     * OXIã
     */
    private String b2mTna = "";
    

    /**
     * vX­
     */
    private String prsHacSuu = "";
    

    /**
     * vX[ú(N)
     */
    private String prsNkiYear = "";
    

    /**
     * vX[ú()
     */
    private String prsNkiMonth = "";
    

    /**
     * vX[ú(ú)
     */
    private String prsNkiDay = "";
    

    /**
     * vX­æ
     */
    private String prsMkrCod = "";


    /**
     * ¼æª
     */
    private String choksoKbn = "";    


    /**
     * vX¢üÉv
     */
    private String prsMnyKei = "";
    

    /**
     * vX­ðP
     */
    private String prsHacSuu1 = "";
    

    /**
     * vXüÉðP
     */
    private String prsHacNyo1 = "";
    

    /**
     * vX[úP
     */
    private String prsHacNki1 = "";
    

    /**
     * vX­Ýv
     */
    private String prsHacRui = "";
    

    /**
     * vX­ðQ
     */
    private String prsHacSuu2 = "";
    

    /**
     * vXüÉðQ
     */
    private String prsHacNyo2 = "";
    

    /**
     * vX[úQ
     */
    private String prsHacNki2 = "";
    

    /**
     * vX`FbN{bNX1
     */
    private boolean check_prs1 = false;
    

    /**
     * vX`FbN{bNX2
     */
    private boolean check_prs2 = false;

    /**
     * Þ­
     */
    private String fukHacSuu = "";
    

    /**
     * Þ[ú(N)
     */
    private String fukNkiYear = "";
    

    /**
     * Þ[ú()
     */
    private String fukNkiMonth = "";
    

    /**
     * Þ[ú(ú)
     */
    private String fukNkiDay = "";
    

    /**
     * ÞR[h
     */
    private String fukSziCod = "";
    
    
    /**
     * ÞÝÉ
     */
    private String fukZaiSuu = "";


    /**
     * Þ¢üÉv
     */
    private String fukMnyKei = "";
    

    /**
     * Þ­ðP
     */
    private String fukHacSuu1 = "";
    

    /**
     * ÞüÉðP
     */
    private String fukHacNyo1 = "";
    

    /**
     * Þ[úP
     */
    private String fukHacNki1 = "";
    

    /**
     * Þ­Ýv
     */
    private String fukHacRui = "";
    

    /**
     * Þ­ðQ
     */
    private String fukHacSuu2 = "";
    

    /**
     * ÞüÉðQ
     */
    private String fukHacNyo2 = "";
    

    /**
     * Þ[úQ
     */
    private String fukHacNki2 = "";
    

    /**
     * Þ`FbN{bNX1
     */
    private boolean check_fuk1 = false;
    
    
    /**
     * Þ`FbNtO
     */
    private String check_flg = "";
    
    /**
     * oÍÏTC
     */
    private String syrZmiSgn = "";
    
    /**
     * vXæªP
     */
    private String prsKbn1 = "";
    
    /**
     * vXæªQ
     */
    private String prsKbn2 = "";
    
    /**
     * ÞæªP
     */
    private String fukKbn1 = "";
    
    /**
     * ÞæªQ
     */
    private String fukKbn2 = "";
    
	/**
     * vXüÉÝv
     */
    private String prsNyoKei = "";
    /**
     * ÞüÉÝv
     */
    private String fukNyoKei = "";
	/**
	 * õlQ
	 */
	private String biKou2 = "";
	
	
	//---------------------------------------------------------------------------

    /**
     * ã\ïÐ¯ÊR[hÌZbg
     * 
     * @param daiKaiSkbCod ã\ïÐ¯ÊR[h
     */
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {

        this.daiKaiSkbCod = daiKaiSkbCod;

    }


    /**
     * ã\ïÐ¯ÊR[hÌæ¾
     * 
     * @return ã\ïÐ¯ÊR[h
     */
    public String getDaiKaiSkbCod() {

        return this.daiKaiSkbCod;

    }
    
    /**
     * ïÐ¯ÊR[hÌZbg
     * 
     * @param kaiSkbCod 
     */
    public void setKaiSkbCod(String kaiSkbCod) {

        this.kaiSkbCod = kaiSkbCod;

    }


    /**
     * ïÐ¯ÊR[hÌæ¾
     * 
     * @return ïÐ¯ÊR[h
     */
    public String getKaiSkbCod() {

        return this.kaiSkbCod;

    }


    /**
     * LÔÌZbg
     * 
     * @param kigBng LÔ
     */
    public void setKigBng(String s) {

        this.kigBng = s;

    }


    /**
     * LÔÌæ¾
     * 
     * @return LÔ
     */
    public String getKigBng() {

        return this.kigBng;

    }
    
    
	/**
     * RgÌZbg
     * 
     * @param comment Rg
     */
    public void setComment(String comment) {


        this.comment = comment;


    }




    /**
     * RgÌæ¾
     * 
     * @return Rg
     */
    public String getComment() {


        return this.comment;


    }


	/**
	 * [iæ¼Ìæ¾
	 * 
	 * @return nhnMei
	 */
	public String getNhnMei() {
		
		return nhnMei;
		
	}
	/**
	 * [iæ¼ÌZbg
	 * 
	 * @param nhnMei [iæ¼
	 */
	public void setNhnMei(String nhnMei) {
		
		this.nhnMei = nhnMei;
		
	}
	
	/**
	 * ­æ¼Ìæ¾
	 * 
	 * @return ­æ¼
	 */
	public String getHacNm() {
		
		return hacNm;
		
	}
	
	/**
	 * ­æ¼ÌZbg
	 * 
	 * @param hacNm ­æ¼
	 */
	public void setHacNm(String hacNm) {
		
		this.hacNm = hacNm;
		
	}

	 
    // ----------------------------------------------------------- Properties

    /**
     * úãÌZbg
     * 
     * @param todUriSuu úã
     */
    public void setTodUriSuu(String todUriSuu) {

        this.todUriSuu = todUriSuu;

    }


    /**
     * úãÌæ¾
     * 
     * @return úã
     */
    public String getTodUriSuu() {

        return this.todUriSuu;

    }


    /**
     * OúãÌZbg
     * 
     * @param b1dUri Oúã
     */
    public void setB1dUri(String b1dUri) {

        this.b1dUri = b1dUri;

    }


    /**
     * OúãÌæ¾
     * 
     * @return Oúã
     */
    public String getB1dUri() {

        return this.b1dUri;

    }


    /**
     * QúOãÌZbg
     * 
     * @param b2dUri QúOã
     */
    public void setB2dUri(String b2dUri) {

        this.b2dUri = b2dUri;

    }


    /**
     * QúOãÌæ¾
     * 
     * @return QúOã
     */
    public String getB2dUri() {

        return this.b2dUri;

    }


    /**
     * RúOãÌZbg
     * 
     * @param b3dUri RúOã
     */
    public void setB3dUri(String b3dUri) {

        this.b3dUri = b3dUri;

    }


    /**
     * RúOãÌæ¾
     * 
     * @return RúOã
     */
    public String getB3dUri() {

        return this.b3dUri;

    }


    /**
     * SúOãÌZbg
     * 
     * @param b4dUri SúOã
     */
    public void setB4dUri(String b4dUri) {

        this.b4dUri = b4dUri;

    }


    /**
     * SúOãÌæ¾
     * 
     * @return SúOã
     */
    public String getB4dUri() {

        return this.b4dUri;

    }


    /**
     * TúOãÌZbg
     * 
     * @param b5dUri TúOã
     */
    public void setB5dUri(String b5dUri) {

        this.b5dUri = b5dUri;

    }


    /**
     * TúOãÌæ¾
     * 
     * @return TúOã
     */
    public String getB5dUri() {

        return this.b5dUri;

    }


    /**
     * TãÌZbg
     * 
     * @param towUri Tã
     */
    public void setTowUri(String towUri) {

        this.towUri = towUri;

    }


    /**
     * TãÌæ¾
     * 
     * @return Tã
     */
    public String getTowUri() {

        return this.towUri;

    }


    /**
     * OTãÌZbg
     * 
     * @param b1wUri OTã
     */
    public void setB1wUri(String b1wUri) {

        this.b1wUri = b1wUri;

    }


    /**
     * OTãÌæ¾
     * 
     * @return OTã
     */
    public String getB1wUri() {

        return this.b1wUri;

    }


    /**
     * OXTãÌZbg
     * 
     * @param b2wUri OXTã
     */
    public void setB2wUri(String b2wUri) {

        this.b2wUri = b2wUri;

    }


    /**
     * OXTãÌæ¾
     * 
     * @return OXTã
     */
    public String getB2wUri() {

        return this.b2wUri;

    }

    /**
     * ãÌZbg
     * 
     * @param tomUri ã
     */
    public void setTomUri(String tomUri) {

        this.tomUri = tomUri;

    }


    /**
     * ãÌæ¾
     * 
     * @return ã
     */
    public String getTomUri() {

        return this.tomUri;

    }


    /**
     * OãÌZbg
     * 
     * @param b1mUri Oã
     */
    public void setB1mUri(String b1mUri) {

        this.b1mUri = b1mUri;

    }


    /**
     * OãÌæ¾
     * 
     * @return Oã
     */
    public String getB1mUri() {

        return this.b1mUri;

    }


    /**
     * OXãÌZbg
     * 
     * @param b2mUri OXã
     */
    public void setB2mUri(String b2mUri) {

        this.b2mUri = b2mUri;

    }


    /**
     * OXãÌæ¾
     * 
     * @return OXã
     */
    public String getB2mUri() {

        return this.b2mUri;

    }


    /**
     * ÔiÌZbg
     * 
     * @param tomHpn Ôi
     */
    public void setTomHpn(String tomHpn) {

        this.tomHpn = tomHpn;

    }


    /**
     * ÔiÌæ¾
     * 
     * @return Ôi
     */
    public String getTomHpn() {

        return this.tomHpn;

    }


    /**
     * OÔiÌZbg
     * 
     * @param b1mHpn OÔi
     */
    public void setB1mHpn(String b1mHpn) {

        this.b1mHpn = b1mHpn;

    }


    /**
     * OÔiÌæ¾
     * 
     * @return OÔi
     */
    public String getB1mHpn() {

        return this.b1mHpn;

    }


    /**
     * OXÔiÌZbg
     * 
     * @param b2mHpn OXÔi
     */
    public void setB2mHpn(String b2mHpn) {

        this.b2mHpn = b2mHpn;

    }


    /**
     * OXÔiÌæ¾
     * 
     * @return OXÔi
     */
    public String getB2mHpn() {

        return this.b2mHpn;

    }


    /**
     * IãÌZbg
     * 
     * @param tomTna Iã
     */
    public void setTomTna(String tomTna) {

        this.tomTna = tomTna;

    }


    /**
     * IãÌæ¾
     * 
     * @return Iã
     */
    public String getTomTna() {

        return this.tomTna;

    }


    /**
     * OIãÌZbg
     * 
     * @param b1mTna OIã
     */
    public void setB1mTna(String b1mTna) {

        this.b1mTna = b1mTna;

    }


    /**
     * OIãÌæ¾
     * 
     * @return OIã
     */
    public String getB1mTna() {

        return this.b1mTna;

    }


    /**
     * OXIãÌZbg
     * 
     * @param b2mTna OXIã
     */
    public void setB2mTna(String b2mTna) {

        this.b2mTna = b2mTna;

    }


    /**
     * OXIãÌæ¾
     * 
     * @return OXIã
     */
    public String getB2mTna() {

        return this.b2mTna;

    }


    /**
     * vX­ÌZbg
     * 
     * @param prsHacSuu vX­
     */
    public void setPrsHacSuu(String prsHacSuu) {

        this.prsHacSuu = prsHacSuu;

    }


    /**
     * vX­Ìæ¾
     * 
     * @return vX­
     */
    public String getPrsHacSuu() {

        return this.prsHacSuu;

    }


    /**
     * vX[ú(N)ÌZbg
     * 
     * @param prsNkiYear vX[ú(N)
     */
    public void setPrsNkiYear(String prsNkiYear) {

        this.prsNkiYear = prsNkiYear;

    }


    /**
     * vX[ú(N)Ìæ¾
     * 
     * @return vX[ú(N)
     */
    public String getPrsNkiYear() {

        return this.prsNkiYear;

    }


    /**
     * vX[ú()ÌZbg
     * 
     * @param prsNkiMonth vX[ú()
     */
    public void setPrsNkiMonth(String prsNkiMonth) {

        this.prsNkiMonth = prsNkiMonth;

    }


    /**
     * vX[ú()Ìæ¾
     * 
     * @return vX[ú()
     */
    public String getPrsNkiMonth() {

        return this.prsNkiMonth;

    }


    /**
     * vX[ú(ú)ÌZbg
     * 
     * @param prsNkiDay vX[ú(ú)
     */
    public void setPrsNkiDay(String prsNkiDay) {

        this.prsNkiDay = prsNkiDay;

    }


    /**
     * vX[ú(ú)Ìæ¾
     * 
     * @return vX[ú(ú)
     */
    public String getPrsNkiDay() {

        return this.prsNkiDay;

    }


    /**
     * vX­æÌZbg
     * 
     * @param prsMkrCod vX­æ
     */
    public void setPrsMkrCod(String prsMkrCod) {

        this.prsMkrCod = prsMkrCod;

    }


    /**
     * vX­æÌæ¾
     * 
     * @return vX­æ
     */
    public String getPrsMkrCod() {

        return this.prsMkrCod;

    }

  
    /**
     * ¼æªÌZbg
     * 
     * @param choksoKbn ¼æª
     */
    public void setChoksoKbn(String choksoKbn) {

        this.choksoKbn = choksoKbn;

    }


    /**
     * ¼æªÌæ¾
     * 
     * @return ¼æª
     */
    public String getChoksoKbn() {

        return this.choksoKbn;

    }

    /**
     * vX¢üÉvÌZbg
     * 
     * @param prsMnyKei vX¢üÉv
     */
    public void setPrsMnyKei(String prsMnyKei) {

        this.prsMnyKei = prsMnyKei;

    }


    /**
     * vX¢üÉvÌæ¾
     * 
     * @return vX¢üÉv
     */
    public String getPrsMnyKei() {

        return this.prsMnyKei;

    }


    /**
     * vX­ðPÌZbg
     * 
     * @param prsHacSuu1 vX­ðP
     */
    public void setPrsHacSuu1(String prsHacSuu1) {

        this.prsHacSuu1 = prsHacSuu1;

    }


    /**
     * vX­ðPÌæ¾
     * 
     * @return vX­ðP
     */
    public String getPrsHacSuu1() {

        return this.prsHacSuu1;

    }


    /**
     * vXüÉðPÌZbg
     * 
     * @param prsHacNyo1 vXüÉðP
     */
    public void setPrsHacNyo1(String prsHacNyo1) {

        this.prsHacNyo1 = prsHacNyo1;

    }


    /**
     * vXüÉðPÌæ¾
     * 
     * @return vXüÉðP
     */
    public String getPrsHacNyo1() {

        return this.prsHacNyo1;

    }


    /**
     * vX[úPÌZbg
     * 
     * @param prsHacNki1 vX[úP
     */
    public void setPrsHacNki1(String prsHacNki1) {

        this.prsHacNki1 = prsHacNki1;

    }


    /**
     * vX[úPÌæ¾
     * 
     * @return vX[úP
     */
    public String getPrsHacNki1() {

        return this.prsHacNki1;

    }


    /**
     * vX­ÝvÌZbg
     * 
     * @param prsHacRui vX­Ýv
     */
    public void setPrsHacRui(String prsHacRui) {

        this.prsHacRui = prsHacRui;

    }


    /**
     * vX­ÝvÌæ¾
     * 
     * @return vX­Ýv
     */
    public String getPrsHacRui() {

        return this.prsHacRui;

    }


    /**
     * vX­ðQÌZbg
     * 
     * @param prsHacSuu2 vX­ðQ
     */
    public void setPrsHacSuu2(String prsHacSuu2) {

        this.prsHacSuu2 = prsHacSuu2;

    }


    /**
     * vX­ðQÌæ¾
     * 
     * @return vX­ðQ
     */
    public String getPrsHacSuu2() {

        return this.prsHacSuu2;

    }


    /**
     * vXüÉðQÌZbg
     * 
     * @param prsHacNyo2 vXüÉðQ
     */
    public void setPrsHacNyo2(String prsHacNyo2) {

        this.prsHacNyo2 = prsHacNyo2;

    }


    /**
     * vXüÉðQÌæ¾
     * 
     * @return vXüÉðQ
     */
    public String getPrsHacNyo2() {

        return this.prsHacNyo2;

    }


    /**
     * vX[úQÌZbg
     * 
     * @param prsHacNki2 vX[úQ
     */
    public void setPrsHacNki2(String prsHacNki2) {

        this.prsHacNki2 = prsHacNki2;

    }


    /**
     * vX[úQÌæ¾
     * 
     * @return vX[úQ
     */
    public String getPrsHacNki2() {

        return this.prsHacNki2;

    }


    /**
     * vX`FbN{bNX1ÌZbg
     * 
     * @param checkprs1 vX`FbN{bNX1
     */
    public void setCheck_prs1(boolean check_prs1) {

        this.check_prs1 = check_prs1;

    }


    /**
     * vX`FbN{bNX1Ìæ¾
     * 
     * @return vX`FbN{bNX1
     */
    public boolean getCheck_prs1() {

        return this.check_prs1;

    }


    /**
     * vX`FbN{bNX2ÌZbg
     * 
     * @param checkprs2 vX`FbN{bNX2
     */
    public void setCheck_prs2(boolean check_prs2) {

        this.check_prs2 = check_prs2;

    }


    /**
     * vX`FbN{bNX2Ìæ¾
     * 
     * @return vX`FbN{bNX2
     */
    public boolean getCheck_prs2() {

        return this.check_prs2;

    }

    /**
     * Þ­ÌZbg
     * 
     * @param fukHacSuu Þ­
     */
    public void setFukHacSuu(String fukHacSuu) {

        this.fukHacSuu = fukHacSuu;

    }


    /**
     * Þ­Ìæ¾
     * 
     * @return Þ­
     */
    public String getFukHacSuu() {

        return this.fukHacSuu;

    }


    /**
     * Þ[ú(N)ÌZbg
     * 
     * @param fukNkiYear Þ[ú(N)
     */
    public void setFukNkiYear(String fukNkiYear) {

        this.fukNkiYear = fukNkiYear;

    }


    /**
     * Þ[ú(N)Ìæ¾
     * 
     * @return Þ[ú(N)
     */
    public String getFukNkiYear() {

        return this.fukNkiYear;

    }


    /**
     * Þ[ú()ÌZbg
     * 
     * @param fukNkiMonth Þ[ú()
     */
    public void setFukNkiMonth(String fukNkiMonth) {

        this.fukNkiMonth = fukNkiMonth;

    }


    /**
     * Þ[ú()Ìæ¾
     * 
     * @return Þ[ú()
     */
    public String getFukNkiMonth() {

        return this.fukNkiMonth;

    }


    /**
     * Þ[ú(ú)ÌZbg
     * 
     * @param fukNkiDay Þ[ú(ú)
     */
    public void setFukNkiDay(String fukNkiDay) {

        this.fukNkiDay = fukNkiDay;

    }


    /**
     * Þ[ú(ú)Ìæ¾
     * 
     * @return Þ[ú(ú)
     */
    public String getFukNkiDay() {

        return this.fukNkiDay;

    }


    /**
     * ÞR[hÌZbg
     * 
     * @param fukSziCod ÞR[h
     */
    public void setFukSziCod(String fukSziCod) {

        this.fukSziCod = fukSziCod;

    }
	
	/**
     * ÞR[hÌæ¾
     * 
     * @return ÞR[h
     */
    public String getFukSziCod() {

        return this.fukSziCod;

    }

    /**
     * ÞÝÉÌZbg
     * 
     * @param fukZaiSuu ÞÝÉ
     */
    public void setFukZaiSuu(String fukZaiSuu) {

        this.fukZaiSuu = fukZaiSuu;

    }


    /**
     * ÞÝÉÌæ¾
     * 
     * @return ÞÝÉ
     */
    public String getFukZaiSuu() {

        return this.fukZaiSuu;

    }


    /**
     * Þ¢üÉvÌZbg
     * 
     * @param fukMnyKei Þ¢üÉv
     */
    public void setFukMnyKei(String fukMnyKei) {

        this.fukMnyKei = fukMnyKei;

    }


    /**
     * Þ¢üÉvÌæ¾
     * 
     * @return Þ¢üÉv
     */
    public String getFukMnyKei() {

        return this.fukMnyKei;

    }


    /**
     * Þ­ðPÌZbg
     * 
     * @param fukHacSuu1 Þ­ðP
     */
    public void setFukHacSuu1(String fukHacSuu1) {

        this.fukHacSuu1 = fukHacSuu1;

    }


    /**
     * Þ­ðPÌæ¾
     * 
     * @return Þ­ðP
     */
    public String getFukHacSuu1() {

        return this.fukHacSuu1;

    }


    /**
     * ÞüÉðPÌZbg
     * 
     * @param fukHacNyo1 ÞüÉðP
     */
    public void setFukHacNyo1(String fukHacNyo1) {

        this.fukHacNyo1 = fukHacNyo1;

    }


    /**
     * ÞüÉðPÌæ¾
     * 
     * @return ÞüÉðP
     */
    public String getFukHacNyo1() {

        return this.fukHacNyo1;

    }


    /**
     * Þ[úPÌZbg
     * 
     * @param fukHacNki1 Þ[úP
     */
    public void setFukHacNki1(String fukHacNki1) {

        this.fukHacNki1 = fukHacNki1;

    }


    /**
     * Þ[úPÌæ¾
     * 
     * @return Þ[úP
     */
    public String getFukHacNki1() {

        return this.fukHacNki1;

    }


    /**
     * Þ­ÝvÌZbg
     * 
     * @param fukHacRui Þ­Ýv
     */
    public void setFukHacRui(String fukHacRui) {

        this.fukHacRui = fukHacRui;

    }


    /**
     * Þ­ÝvÌæ¾
     * 
     * @return Þ­Ýv
     */
    public String getFukHacRui() {

        return this.fukHacRui;

    }


    /**
     * Þ­ðQÌZbg
     * 
     * @param fukHacSuu2 Þ­ðQ
     */
    public void setFukHacSuu2(String fukHacSuu2) {

        this.fukHacSuu2 = fukHacSuu2;

    }


    /**
     * Þ­ðQÌæ¾
     * 
     * @return Þ­ðQ
     */
    public String getFukHacSuu2() {

        return this.fukHacSuu2;

    }


    /**
     * ÞüÉðQÌZbg
     * 
     * @param fukHacNyo2 ÞüÉðQ
     */
    public void setFukHacNyo2(String fukHacNyo2) {

        this.fukHacNyo2 = fukHacNyo2;

    }


    /**
     * ÞüÉðQÌæ¾
     * 
     * @return ÞüÉðQ
     */
    public String getFukHacNyo2() {

        return this.fukHacNyo2;

    }


    /**
     * Þ[úQÌZbg
     * 
     * @param fukHacNki2 Þ[úQ
     */
    public void setFukHacNki2(String fukHacNki2) {

        this.fukHacNki2 = fukHacNki2;

    }


    /**
     * Þ[úQÌæ¾
     * 
     * @return Þ[úQ
     */
    public String getFukHacNki2() {

        return this.fukHacNki2;

    }


    /**
     * Þ`FbN{bNX1ÌZbg
     * 
     * @param checkfuk1 Þ`FbN{bNX1
     */
    public void setCheck_fuk1(boolean check_fuk1) {

        this.check_fuk1 = check_fuk1;

    }


    /**
     * Þ`FbN{bNX1Ìæ¾
     * 
     * @return Þ`FbN{bNX1
     */
    public boolean getCheck_fuk1() {

        return this.check_fuk1;

    }

	/**
     * Þ`FbNtO
     * 
     * @param check_flg Þ`FbNtO
     */
    public void setCheck_flg(boolean check_flg) {
		
        this.check_flg = check_flg+"";

    }


    /**
     * Þ`FbNtO
     * 
     * @return Þ`FbNtO
     */
    public String getCheck_flg() {

        return this.check_flg;

    }

	/**
     * oÍÏTC
     * 
     * @param syrZmiSgn oÍÏTC
     */
    public void setSyrZmiSgn(String syrZmiSgn) {
		
        this.syrZmiSgn = syrZmiSgn;

    }


    /**
     * oÍÏTC
     * 
     * @return oÍÏTC
     */
    public String getSyrZmiSgn() {

        return this.syrZmiSgn;

    }
    
    /**
     * vXæªPÌZbg
     * 
     * @param prsKbn1 vXæªP
     */
    public void setPrsKbn1(String prsKbn1) {


        this.prsKbn1 = prsKbn1;


    }

    /**
     * vXæªPÌæ¾
     * 
     * @return vXæªP
     */
    public String getPrsKbn1() {

		if(this.prsKbn1 == null || this.prsKbn1.equals(""))
			return this.prsKbn1;
        return this.prsKbn1.substring(0,1);

    }

	/**
     * vXæªQÌZbg
     * 
     * @param prsKbn2 vXæªQ
     */
    public void setPrsKbn2(String prsKbn2) {


        this.prsKbn2 = prsKbn2;


    }

    /**
     * vXæªQÌæ¾
     * 
     * @return vXæªQ
     */
    public String getPrsKbn2() {

		if(this.prsKbn2 == null || this.prsKbn2.equals(""))
			return this.prsKbn2;
        return this.prsKbn2.substring(0,1);

    }

	/**
     * ÞæªPÌZbg
     * 
     * @param prsKbn1 ÞæªP
     */
    public void setFukKbn1(String fukKbn1) {


        this.fukKbn1 = fukKbn1;


    }

    /**
     * ÞæªPÌæ¾
     * 
     * @return ÞæªP
     */
    public String getFukKbn1() {

		if(this.fukKbn1 == null || this.fukKbn1.equals(""))
			return this.fukKbn1;
        return this.fukKbn1.substring(0,1);

    }

	/**
     * ÞæªQÌZbg
     * 
     * @param prsKbn2 ÞæªQ
     */
    public void setFukKbn2(String fukKbn2) {


        this.fukKbn2 = fukKbn2;


    }

    /**
     * ÞæªQÌæ¾
     * 
     * @return ÞæªQ
     */
    public String getFukKbn2() {

		if(this.fukKbn2 == null || this.fukKbn2.equals(""))
			return this.fukKbn2;
        return this.fukKbn2.substring(0,1);

    }
	/**
	 * õlQ
	 * 
	 * @param biKou2 õlQ
	 */
	public void setBiKou2(String biKou2) {
		
		this.biKou2 = biKou2;

	}


	/**
	 * õlQ
	 * 
	 * @return õlQ
	 */
	public String getBiKou2() {

		return this.biKou2;

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
        this.fukSziCod = "";
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
        this.prsKbn1 = "";
        this.prsKbn2 = "";
        this.fukKbn1 = "";
        this.fukKbn2 = "";
        this.prsNyoKei = "";
        this.fukNyoKei = "";
		this.biKou2 = "";//2007
    
    }

}
