/**
* SubMateStockVO  副資材在庫メンテナンス バリューオブジェクトクラス
*
*	作成日    2003/08/06
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    画面から取得したデータを格納するクラス。
*
*	[変更履歴]
*	変更日    2003/09/18
*	変更者    （ＮＩＩ）村上  博基 
*      変更内容  サンプル区分のget/set追加
*/

package com.jds.prodord.master.submatestock;
import java.util.*;

public class SubMateStockVO {
 	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String kaiSkbCod = new String();
	private String smpKbn = "";	 //2003/09/18

	private String hidDaiKaiSkbCod = new String();
	private ArrayList kigoBan_arr = null;
	private String kigoBan = new String();
	private String hjiHnb = new String();
	private String huksizaiCod = new String();
	private String huksizaiMei = new String();
	private String huksizaiZaiko = new String();
	private String teiseiSuu = new String();

	private int hidUpdDte;
	private int hidUpdJkk;

	private boolean flag = false;

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

	//*** Gets the hidDaiKaiSkbCod  ***
	public String getHidDaiKaiSkbCod() {
		return hidDaiKaiSkbCod;
	}
	public void setHidDaiKaiSkbCod(String hidDaiKaiSkbCod) {
		this.hidDaiKaiSkbCod = hidDaiKaiSkbCod;
	}

	//***  サンプル区分のget/set  *** //2003/09/18
	public String getSmpKbn() {
		return smpKbn;
	}
	public void setSmpKbn(String smpKbn) {
		this.smpKbn = smpKbn;
	}

	//***  記号番号_arrのget/set  ***
    public ArrayList getKigoBan_arr() {
        return this.kigoBan_arr;
    }
    public void setKigoBan_arr(ArrayList kigoBan_arr) {
        this.kigoBan_arr = kigoBan_arr;
    }

	//***  記号番号のget/set  ***
    public String getKigoBan() {
        return this.kigoBan;
    }
    public void setKigoBan(String kigoBan) {
        this.kigoBan = kigoBan;
    }

	//***  表示品番のget/set  ***
    public String getHjiHnb() {
        return this.hjiHnb;
    }
    public void setHjiHnb(String hjiHnb) {
        this.hjiHnb = hjiHnb;
    }
	//***  副資材コードのget/set  ***
	public String getHuksizaiCod() {
		return huksizaiCod;
	}
	public void setHuksizaiCod(String huksizaiCod) {
		this.huksizaiCod = huksizaiCod;
	}

	//***  副資材名称のget/set  ***
	public String getHuksizaiMei() {
		return huksizaiMei;
	}
	public void setHuksizaiMei(String huksizaiMei) {
		this.huksizaiMei = huksizaiMei;
	}


	//***  副資材在庫数のget/set  ***
	public String getHuksizaiZaiko() {
		return huksizaiZaiko;
	}
	public void setHuksizaiZaiko(String huksizaiZaiko) {
		this.huksizaiZaiko = huksizaiZaiko;
	}

	//***  訂正数のget/set  ***
	public String getTeiseiSuu() {
		return teiseiSuu;
	}
	public void setTeiseiSuu(String teiseiSuu) {
		this.teiseiSuu = teiseiSuu;
	} 	//***  Gets the updDte  ***
	public int getHidUpdDte() {
		return hidUpdDte;
	}
	public void setHidUpdDte(int hidUpdDte) {
		this.hidUpdDte = hidUpdDte;
	}

	//***  Gets the updJkk  ***
	public int getHidUpdJkk() {
		return hidUpdJkk;
	}
	public void setHidUpdJkk(int hidUpdJkk) {
		this.hidUpdJkk = hidUpdJkk;
	}

	/**
	 * Gets the flag
	 * @return Returns a boolean
	 */
	public boolean getFlag() {
		return flag;
	}
	/**
	 * Sets the flag
	 * @param flag The flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
 }
