/**
* PrintOrdersRow  ­óüsNX
*
*	ì¬ú    2003/04/18
*	ì¬Ò    imhhjªc Äü
* Tv    HttpNGXgÆNGXgÌÊÌ¤¿ÌJèÔµÚði[·éNXB
*
*	[ÏXð]
*             út ¼O
*  Eàe
* 		 2004/04/21 (NII)X
* 			EÅè¿\¦Éº¤C³
* 		 2005/06/14 (NII)gc
* 			E­úÌÇÁijÐÎj
* 		 2005/09/13iNIIjgc
* 			EàzÌÇÁiVAPÐÎj
* 		 2007/12/25imhhjc
* 			E}jA­w¦ê\t@õlQÇÁÎ
* 		 2008/03/10imhhjc
* 			EP¿ÇÁÎ
*/

package com.jds.prodord.print.printorders;

public class PrintOrdersRow {


    /**
     * Væª
     */
    private String sinKyuKbn;
    

    /**
     * ¾×sÔ
     */
    private String gyoBng;
    

    /**
     * LÔ
     */
    private String kigBng;
    

    /**
     * ^Cg¿
     */
    private String titKj;
    
    /**
     * A[eBXg(¿)
     */
    private String artKj = "";
    
    /**
     * `ÔR[h
     */
    private String ketCod;
    
    /**
     * `Ô¼Ì
     */
    private String ketNm;

    /**
     * vXÞR[h
     */
    private String prsFukSziCod;
    

    /**
     * Þ¼Ì
     */
    private String fukMeiKj;
    
    
	/**
     * ÞÝÉ
     */
	private String fukSziSuu;
	
	
    /**
     * Zbg
     */
    private String setSuu;
    

    /**
     * ­Ê
     */
    private String hacSuu;
    

    /**
     * [ú
     */
    private String nki;
    

    /**
     * [iæ¼Ì
     */
    private String nhnMeiKj;
    
    
    /**
     * Rg
     */
    private String comment;

	/**
	 * Åè¿
	 */
	private String zikTik;
	/**
	 * è¿(Å²)
	 */
	private String tka;
	

	/**
	 * ­ú
	 */
	private String hbiDte;
	
	/**
	 * àz
	 */
	private String kin;

	/**
	 * õlQ
	 */
	private String biKou2;

	/**
	 * P¿
	 */
	private String tan2;

//--------------------------------------------------------------------------


    /**
     * VæªÌZbg
     * 
     * @param sinKyuKbn Væª
     */
    public void setSinKyuKbn(String sinKyuKbn) {

        this.sinKyuKbn = sinKyuKbn;

    }


    /**
     * VæªÌæ¾
     * 
     * @return Væª
     */
    public String getSinKyuKbn() {

        return this.sinKyuKbn;

    }


    /**
     * ¾×sÔÌZbg
     * 
     * @param gyoBng ¾×sÔ
     */
    public void setGyoBng(String gyoBng) {

        this.gyoBng = gyoBng;

    }


    /**
     * ¾×sÔÌæ¾
     * 
     * @return ¾×sÔ
     */
    public String getGyoBng() {

        return this.gyoBng;

    }


    /**
     * LÔÌZbg
     * 
     * @param kigBng LÔ
     */
    public void setKigBng(String kigBng) {

        this.kigBng = kigBng;

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
     * ^Cg¿ÌZbg
     * 
     * @param titKj ^Cg¿
     */
    public void setTitKj(String titKj) {

        this.titKj = titKj;

    }


    /**
     * ^Cg¿Ìæ¾
     * 
     * @return ^Cg¿
     */
    public String getTitKj() {

        return this.titKj;

    }
    
    
    /**
     * A[eBXg(¿)ÌZbg
     * 
     * @param artKj A[eBXg(¿)
     */
    public void setArtKj(String s) {

        this.artKj = s;

    }


    /**
     * A[eBXg(¿)Ìæ¾
     * 
     * @return A[eBXg(¿)
     */
    public String getArtKj() {

        return this.artKj;

    }
    

	/**
     * `ÔR[hÌZbg
     * 
     * @param ketCod `ÔR[h
     */
    public void setKetCod(String s) {

        this.ketCod = s;

    }


    /**
     * `ÔR[hÌæ¾
     * 
     * @return `ÔR[h
     */
    public String getKetCod() {

        return this.ketCod;

    }
    
    /**
     * `Ô¼ÌÌZbg
     * 
     * @param ketNm `Ô¼Ì
     */
    public void setKetNm(String s) {

        this.ketNm = s;

    }


    /**
     * `Ô¼ÌÌæ¾
     * 
     * @return `Ô¼Ì
     */
    public String getKetNm() {

        return this.ketNm;

    }

    /**
     * vXÞR[hÌZbg
     * 
     * @param prsFukSziCod vXÞR[h
     */
    public void setPrsFukSziCod(String prsFukSziCod) {

        this.prsFukSziCod = prsFukSziCod;

    }


    /**
     * vXÞR[hÌæ¾
     * 
     * @return vXÞR[h
     */
    public String getPrsFukSziCod() {

        return this.prsFukSziCod;

    }


    /**
     * Þ¼ÌÌZbg
     * 
     * @param fukMeiKj Þ¼Ì
     */
    public void setFukMeiKj(String fukMeiKj) {

        this.fukMeiKj = fukMeiKj;

    }


    /**
     * Þ¼ÌÌæ¾
     * 
     * @return Þ¼Ì
     */
    public String getFukMeiKj() {

        return this.fukMeiKj;

    }
    
    
    /**
     * ÞÝÉÌZbg
     * 
     * @param prsFukSziCod ÞÝÉ
     */
    public void setFukSziSuu(String fukSziSuu) {

        this.fukSziSuu = fukSziSuu;

    }


    /**
     * ÞÝÉÌæ¾
     * 
     * @return ÞÝÉ
     */
    public String getFukSziSuu() {

        return this.fukSziSuu;

    }


    /**
     * ZbgÌZbg
     * 
     * @param setSuu Zbg
     */
    public void setSetSuu(String setSuu) {

        this.setSuu = setSuu;

    }


    /**
     * ZbgÌæ¾
     * 
     * @return Zbg
     */
    public String getSetSuu() {

        return this.setSuu;

    }


    /**
     * ­ÊÌZbg
     * 
     * @param hacSuu ­Ê
     */
    public void setHacSuu(String hacSuu) {

        this.hacSuu = hacSuu;

    }


    /**
     * ­ÊÌæ¾
     * 
     * @return ­Ê
     */
    public String getHacSuu() {

        return this.hacSuu;

    }


    /**
     * [úÌZbg
     * 
     * @param nki [ú
     */
    public void setNki(String nki) {

        this.nki = nki;

    }


    /**
     * [úÌæ¾
     * 
     * @return [ú
     */
    public String getNki() {

        return this.nki;

    }


    /**
     * [iæ¼ÌÌZbg
     * 
     * @param nhnMeiKj [iæ¼Ì
     */
    public void setNhnMeiKj(String nhnMeiKj) {

        this.nhnMeiKj = nhnMeiKj;

    }


    /**
     * [iæ¼ÌÌæ¾
     * 
     * @return [iæ¼Ì
     */
    public String getNhnMeiKj() {

        return this.nhnMeiKj;

    }

	/**
	 * Åè¿
	 * @return@Åè¿
	 */
	public String getZikTik() {
		return zikTik;
	}

	/**
	 * Åè¿
	 * @param zikTik
	 */
	public void setZikTik(String zikTik) {
		this.zikTik = zikTik;
	}

	/**
	 * è¿(Å²)
	 * @return@è¿(Å²)
	 */
	public String getTka() {
		return tka;
	}

	/**
	 * è¿(Å²)
	 * @param tka
	 */
	public void setTka(String tka) {
		this.tka = tka;
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
	 * ­úÌæ¾
	 * @return
	 */
	public String getHbiDte() {
		return hbiDte;
	}

	/**
	 * ­úÌZbg
	 * @param string
	 */
	public void setHbiDte(String string) {
		hbiDte = string;
	}

	/**
	 * àzÌæ¾
	 * @return
	 */
	public String getKin() {
		return kin;
	}

	/**
	 * àzÌÝè
	 * @param string
	 */
	public void setKin(String string) {
		kin = string;
	}

	/**
	 * õlQÌæ¾
	 * @return
	 */
	public String getBiKou2() {
		return biKou2;
	}

	/**
	 * õlQÌÝè
	 * @param string
	 */
	public void setBiKou2(String string) {
		biKou2 = string;
	}

	/**
	 * P¿Ìæ¾
	 * @return
	 */
	public String getTan2() {
		return tan2;
	}

	/**
	 * P¿ÌÝè
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

