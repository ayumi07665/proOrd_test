/**
* OrderHistoryCsvVO  発注履歴照会画面CSVバリューオブジェクトクラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）蛭田 敬子
* 処理概要    VOに格納されているデータをCSV用に格納し直すクラス。
*
*	[変更履歴]
* 		2005/09/15（ＮＩＩ）蛭田
* 			・明細項目に金額の追加
* 		2006/01/25（ＮＩＩ）田端
* 			・ダウンロード項目に注残数追加
*       2007/03/06（ＮＩＩ）田中
* 			・ダウンロード項目に副資材名称追加
*       2007/12/05（ＮＩＩ）田中
* 			・ダウンロード項目にタイトル漢字追加
*		2007/12/25（ＮＩＩ）田中
* 			・マニュアル発注指示一括貼付　備考２追加対応
*		2008/03/07（ＮＩＩ）田中
* 			・単価の追加
*/

package com.jds.prodord.reference.orderhistory;

import com.jds.prodord.common.*;
public class OrderHistoryCsvVO implements CommonCsvVoInterface{
	
 	
	private final int[] typeBox = {0,0,0,0,0,1,0,0,1,0,0,1,1,1,1,1,1,1,0,0,1,0,0}; //CommonCsvVoInterfaceのタイプにならう
	
	public int getColSize(){
		return 23;
	} 	
	

    /**
     * 発注先コード
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
     * 表示品番
     */
    private String hjiHnb = "";
    
	/**
	 * タイトル漢字
	 */
	private String titKj = "";
    
    /**
     * 発売日
     */
    private String hbiDte = "";
    
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
    private String bunCod = "";
    
    /**
     * 金額  2008/02/07 add
     */
    private String tan2 = "";
    
	/**
	 * 金額  2005/09/15 add
	 */
	private String kin = "";
    
    /**
     * プレス副資材残
     */
    private String fukSziSuu = "";
    
    /**
     * 発注数
     */
    private String hacSuu = "";
    
    /**
     * 入庫数
     */
    private String nyoSuu = "";
    
    /**
     * 納期
     */
    private String nki = "";
    
    /**
     * 発注番号
     */
    private String gyoBng = "";
    
    /**
     * 納品先
     */
    private String nhnMeiKj = "";
	
	/**
     * コメント
     */
    private String cmt = "";
    
    /**
     * 入庫日
     */
    private String nyodte = "";
    
    /**
     * 注残数
     */
    private String cyuzan = "";

	/**
	 * プレス副資材コード
	 */
	private String prsFukSziCod = "";

	/**
	 * 副資材名称
	 */
	private String fukSziNmkj = "";

	/**
	 * 備考２
	 */
	private String biKou2 = "";

//*************************************************************************************

	public int getColumnType(int i){
		return typeBox[i];
	}

	public String getColumn(int i){
		String returnStr = "";
		switch(i){
			case 0: 
				returnStr = this.getHacCod();break;
			case 1: 
				returnStr = this.getSinKyuKbn();break;
			case 2: 
				returnStr = this.getKigBng();break;
			case 3: 
				returnStr = this.getHjiHnb();break;
			case 4: 
				returnStr = this.getTitkj();break;
			case 5: 
				returnStr = this.getHbiDte();break;
			case 6: 
				returnStr = this.getHacSyoBng();break;
			case 7: 
				returnStr = this.getGyoBng();break;
			case 8: 
				returnStr = this.getHacSyoDte();break;
			case 9: 
				returnStr = this.getSyrZmiSgn();break;
			case 10: 
				returnStr = this.getBunCod();break;
			case 11: 
				returnStr = this.getTan2();break; //2008/02/07 add
			case 12: 
				returnStr = this.getKin();break; //2005/09/15 add
			case 13: 
				returnStr = this.getFukSziSuu();break;
			case 14: 
				returnStr = this.getHacSuu();break;
			case 15: 
				returnStr = this.getNyoSuu();break;
			case 16: 
				returnStr = this.getNyoDte();break;
			case 17: 
				returnStr = this.getNki();break;
			case 18: 
				returnStr = this.getNhnMeiKj();break;
			case 19: 
				returnStr = this.getCmt();break;
			case 20:
				returnStr = this.getCyuzan();break;
			case 21:
				returnStr = this.getFukSziNmkj();break;
			case 22:
				returnStr = this.getBiKou2();break;
		}
		return returnStr;
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
	 * タイトル漢字
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
	public String getTitkj() {
		return this.titKj;
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
     * プレス副資材残のセット
     * 
     * @param fukSziSuu プレス副資材残
     */
    public void setFukSziSuu(String fukSziSuu) {

        this.fukSziSuu = fukSziSuu;

    }


    /**
     * プレス副資材残の取得
     * 
     * @return プレス副資材残
     */
    public String getFukSziSuu() {

        return this.fukSziSuu;

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

   /**
    * 納期
    */
	public String getNki() {
		return this.nki;

	}
	public void setNki(String nki) {
		this.nki = nki;
	}
	

    /**
     * 発注番号のセット
     * 
     * @param gyoBng 発注番号
     */
    public void setGyoBng(String gyoBng) {

        this.gyoBng = gyoBng;

    }


    /**
     * 発注番号の取得
     *     * @return 発注番号
     */
    public String getGyoBng() {

        return this.gyoBng;

    }

    /**
     * 納品先のセット
     * 
     * @param nhnMeiKj 納品先
     */
    public void setNhnMeiKj(String nhnMeiKj) {

        this.nhnMeiKj = nhnMeiKj;

    }


    /**
     * 納品先の取得
     * 
     * @return 納品先
     */
    public String getNhnMeiKj() {

        return this.nhnMeiKj;

    }
 
    /**
     * コメントのセット
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
     * 入庫日のセット
     * 
     * @param nyodte 入庫日
     */
    public void setNyoDte(String nyodte) {

        this.nyodte = nyodte;

    }


    /**
     * 入庫日の取得
     * 
     * @return 入庫日
     */
    public String getNyoDte() {

        return this.nyodte;

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
	 * 注残数の取得
	 * @return
	 */
	public String getCyuzan() {
		return cyuzan;
	}

	/**
	 * 注残数の設定
	 * @param string
	 */
	public void setCyuzan(String string) {
		cyuzan = string;
	}

	/**
	 * プレス副資材コードのセット
	 * 
	 * @param fukSziSuu プレス副資材コード
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
	 * @param fukSziNmkj 副資材名称
	 */
	public void setFukSziNmkj(String fukSziNmkj) {

		this.fukSziNmkj = fukSziNmkj;

	}


	/**
	 * 副資材名称の取得
	 * 
	 * @return 副資材名称
	 */
	public String getFukSziNmkj() {

		return this.fukSziNmkj;

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

}



