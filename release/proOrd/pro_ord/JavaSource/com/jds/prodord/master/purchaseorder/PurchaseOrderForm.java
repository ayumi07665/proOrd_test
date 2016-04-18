/**
* PurchaseOrderForm  仕入発注先マスター照会フォームクラス
*
*	作成日    2003/05/19
*	作成者    （ＮＩＩ）村上 博基
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*/

package com.jds.prodord.master.purchaseorder;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class PurchaseOrderForm extends ActionForm {

	public static final String COMMAND_JITTKOU = "実行";
	public static final String COMMAND_CLEAR = "クリア";
	
	private String command = "";
	private String daiKaiSkbCod = "";
	private String QueryDaiKaiSkbCod = "";

	private String hattyuCod = "";
	private String orderName1 = "";
	private String orderName2 = "";
	private String orderAdr1 = "";
	private String orderAdr2 = "";
	private String telNum = "";
	private String postNum = "";

	private ArrayList KaiSkbCodList = null;

//=============================================================================

	public PurchaseOrderForm(){
		super();
		this.setCommand("INIT");

	}

     //***  ボタンのget/set  ***
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		command = s;
	}

     //***  代表会社識別コードのget/set  ***
    public String getDaiKaiSkbCod(){
    	return daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String s){
    	daiKaiSkbCod = s;
    }
        
	//*** クエリ代表会社識別コードのget/set  ***
	public String getQueryDaiKaiSkbCod() {
		return QueryDaiKaiSkbCod;
	}
	public void setQueryDaiKaiSkbCod(String queryDaiKaiSkbCod) {
		QueryDaiKaiSkbCod = queryDaiKaiSkbCod;
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
    public ArrayList getKaiSkbCodList(){
    	return KaiSkbCodList;
    }
    public void setKaiSkbCodList(ArrayList arr){
    	KaiSkbCodList = arr;
    }

//---------------------------------------------------------------------------------

		//入力チェック

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
			HttpSession session = req.getSession();
			//メッセージをセッションから取り除く
			session.removeAttribute(CommonConst.INFOMSG_KEY);
		
			if(command.equals("INIT")){
				return null;
			}
				ActionErrors errors = new ActionErrors();
			if(command.equals(COMMAND_JITTKOU)){
				//必須
					if(hattyuCod.trim().equals("")){
							errors.add("",new ActionError("errors.input.required","発注先コード"));
				}else {

					//半角数字のみ
					if(!StringUtils.isAscii(hattyuCod.trim()))
							errors.add("",new ActionError("errors.input.1","発注先コード","半角英数"));
			
				}
			}

		return errors.empty() ? null : errors;

	}

	public void reset(ActionMapping maping,HttpServletRequest req){
			this.command = "INIT";
	}


	//****  値をクリア  ****
	public void clearAll(){
		command = "";

        this.hattyuCod = "";
        this.orderName1 = "";
        this.orderName2 = "";
        this.orderAdr1 = "";
        this.orderAdr2 = "";
	    this.telNum = "";
	    this.postNum = "";

  	}

	public void clearTableField1(){
		command = "";
	    hattyuCod = "";
	    orderName1 = "";
	    orderName2 = "";
	    orderAdr1 = "";
	    orderAdr2 = "";
	    telNum = "";
	    postNum = "";
	}


}