/**
* PurchaseOrderVO  仕入発注先マスター照会バリューオブジェクトクラス
*
*	作成日    2002/05/19
*	作成者    （ＮＩＩ）村上  博基 
* 処理概要    仕入発注先マスター照会から取得したデータを格納するクラス。
*
*/

package com.jds.prodord.master.purchaseorder;

import java.util.*;

public class PurchaseOrderVO  {

	private String DaiKaiSkbCod = "";
	private String queryDaiKaiSkbCod = "";

	private String hattyuCod = "";
	private String orderName1 = "";
	private String orderName2 = "";
	private String orderAdr1 = "";
	private String orderAdr2 = "";
	private String telNum = "";
	private String postNum = "";

	private ArrayList KaiSkbCodList = null;
	private ArrayList KaiSkbCod_arr = null;
	
	private boolean finded_flg = true;
	
//=================================================================================


     //***  代表会社識別コードのset/get  ***
	public String getDaiKaiSkbCod(){
		return DaiKaiSkbCod;
	}	
	public void setDaiKaiSkbCod(String s){
		DaiKaiSkbCod = s;
	}	

     //***  クエリ代表会社識別コードのget/set  ***
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
    }


     //***  発注先コードのget/set  ***
	public String getHattyuCod(){
		return hattyuCod;
	}
	public void setHattyuCod(String s){
		hattyuCod = s;
	}

     //***  発注先名称１のget/set  ***
	public String getOrderName1(){
		return orderName1;
	}
	public void setOrderName1(String s){
		orderName1 = s;
	}

     //***  発注先名称２のget/set  ***
	public String getOrderName2(){
		return orderName2;
	}
	public void setOrderName2(String s){
		orderName2 = s;
	}

     //***  発注先住所１のget/set  ***
	public String getOrderAdr1(){
		return orderAdr1;
	}
	public void setOrderAdr1(String s){
		orderAdr1 = s;
	}

     //***  発注先住所２のget/set  ***
	public String getOrderAdr2(){
		return orderAdr2;
	}
	public void setOrderAdr2(String s){
		orderAdr2 = s;
	}

     //***  電話番号のget/set  ***
	public String getTelNum(){
		return telNum;
	}
	public void setTelNum(String s){
		telNum = s;
	}

     //***  郵便番号のget/set  ***
	public String getPostNum(){
		return postNum;
	}
	public void setPostNum(String s){
		postNum = s;
	}


     //***  会社識別コードリストのget/set  ***
    public ArrayList getKaiSkbCod_arr(){
    	return KaiSkbCod_arr;
    }
    public void setKaiSkbCod_arr(ArrayList arr){
    	KaiSkbCod_arr = arr;
    }

     //***  会社識別コードリストのget/set  ***
    public ArrayList getKaiSkbCodList(){
    	return KaiSkbCodList;
    }
    public void setKaiSkbCodList(ArrayList arr){
    	KaiSkbCodList = arr;
    }


	//検索結果 ありorなし フラグ	
	public boolean getFinded_flg(){
		return finded_flg;
	}	
	public void setFinded_flg(boolean b){
		finded_flg = b;
	}
}