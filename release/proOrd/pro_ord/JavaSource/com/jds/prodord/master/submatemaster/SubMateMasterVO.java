/**
* SubMateMasterVO  副資材マスターメンテナンス  バリューオブジェクトクラス
*
*	作成日    2003/06/24
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    画面から取得したデータを格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.submatemaster;

import java.util.*;
public class SubMateMasterVO {

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiSkbCod = new String();
	private String hukSziCod = new String();

	private String hidDaiKaiSkbCod = new String();
	private String hidKaiSkbCod = new String();
	private String outHukSziCod = new String();
	private String outBunruiCod = new String();
	private String outHatcCod = new String();
	private String outHukSziMei = new String();
	
	private int hidUpdDte;
	private int hidUpdJkk;

	private int updDte;
	private int updJkk;
	

    //ユーザーの代表会社識別コード
    public String getDaiKaiSkbCod() {
        return this.daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {
        this.daiKaiSkbCod = daiKaiSkbCod;
    }


    //ユーザーの会社識別コード
	public String getQueryKaiSkbCod() {
		return queryKaiSkbCod;
	}
	public void setQueryKaiSkbCod(String queryKaiSkbCod) {
		this.queryKaiSkbCod = queryKaiSkbCod;
	}

    
    //会社識別コードリスト
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }

    
//-----  入力エリア  -----------------------------------------

	//***  会社識別コードのget/set  ***
    public String getKaiSkbCod() {
        return this.kaiSkbCod;
    }
    public void setKaiSkbCod(String kaiSkbCod) {
        this.kaiSkbCod = kaiSkbCod;
    }

	//***  副資材ｺｰﾄﾞのget/set  ***
	public String getHukSziCod() {
		return hukSziCod;
	}
	public void setHukSziCod(String hukSziCod) {
		this.hukSziCod = hukSziCod;
	}

	//***  Gets the hidDaiKaiSkbCod  ***
	public String getHidDaiKaiSkbCod() {
		return hidDaiKaiSkbCod;
	}
	public void setHidDaiKaiSkbCod(String hidDaiKaiSkbCod) {
		this.hidDaiKaiSkbCod = hidDaiKaiSkbCod;
	}

	//***  Gets the hidKaiSkbCod  ***
	public String getHidKaiSkbCod() {
		return hidKaiSkbCod;
	}
	public void setHidKaiSkbCod(String hidKaiSkbCod) {
		this.hidKaiSkbCod = hidKaiSkbCod;
	}

	//*** Gets the out副資材ｺｰﾄﾞ  ***
	public String getOutHukSziCod() {
		return outHukSziCod;
	}
	public void setOutHukSziCod(String outHukSziCod) {
		this.outHukSziCod = outHukSziCod;
	}

	//***  Gets the out分類コード  ***
	public String getOutBunruiCod() {
		return outBunruiCod;
	}
	public void setOutBunruiCod(String outBunruiCod) {
		this.outBunruiCod = outBunruiCod;
	}

	//***  Gets the out発注ｺｰﾄﾞ  ***
	public String getOutHatcCod() {
		return outHatcCod;
	}
	public void setOutHatcCod(String outHatcCod) {
		this.outHatcCod = outHatcCod;
	}

	//***  Gets the out副資材名  ***
	public String getOutHukSziMei() {
		return outHukSziMei;
	}
	public void setOutHukSziMei(String outHukSziMei) {
		this.outHukSziMei = outHukSziMei;
	}

	//***  Gets the hidUpdDte  ***
	public int getHidUpdDte() {
		return hidUpdDte;
	}
	public void setHidUpdDte(int hidUpdDte) {
		this.hidUpdDte = hidUpdDte;
	}

	//***  Gets the hidUpdJkk  ***
	public int getHidUpdJkk() {
		return hidUpdJkk;
	}
	public void setHidUpdJkk(int hidUpdJkk) {
		this.hidUpdJkk = hidUpdJkk;
	}

	//***  更新日付のget/set  ***
	public int getUpdDte() {
		return updDte;
	}
	public void setUpdDte(int updDte) {
		this.updDte = updDte;
	}

	//***  更新時間のget/set  ***
	public int getUpdJkk() {
		return updJkk;
	}
	public void setUpdJkk(int updJkk) {
		this.updJkk = updJkk;
	}


}
