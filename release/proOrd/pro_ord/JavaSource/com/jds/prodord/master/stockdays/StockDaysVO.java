/**
* StockDaysVO  在庫日数マスターメンテナンスバリューオブジェクトクラス
*
*	作成日    2003/06/09
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    画面から取得したデータを格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.stockdays;

import java.util.*;
public class StockDaysVO {

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiSkbCod = new String();
	private String rank = new String();
	private String ketCod = new String();
	private String outStockDays = new String();

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

//***  ランクのget/set  ***
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}

//***  形態コードのget/set  ***
	public String getKetCod() {
		return ketCod;
	}
	public void setKetCod(String ketCod) {
		this.ketCod = ketCod;
	}


//***  在庫日数のget/set  ***
	public String getOutStockDays() {
		return outStockDays;
	}
	public void setOutStockDays(String outStockDays) {
		this.outStockDays = outStockDays;
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
