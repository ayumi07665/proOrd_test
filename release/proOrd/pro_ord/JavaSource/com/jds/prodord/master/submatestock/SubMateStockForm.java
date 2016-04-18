/**
* SubMateStockForm  副資材在庫メンテナンス フォームクラス
*
*	作成日    2003/08/18
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
*	変更日    2003/09/18
*	変更者    （ＮＩＩ）村上  博基 
*      変更内容  サンプル区分のget/set追加
* 		 2004/06/29（ＮＩＩ）蛭田
* 			・コピー＆ペースト機能追加
*/
package com.jds.prodord.master.submatestock;
import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class SubMateStockForm extends ActionForm {

	public static final String COMMAND_JIKKOU = "実行";
	public static final String COMMAND_CLEAR = "クリア";
	public static final String COMMAND_PASTE = "貼り付け";//2004/06/29 add
		
	private String command = "";
	private ArrayList rows = new ArrayList();
	public static final int DEFAULT_ROW_COUNT = 1;

	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String prcKbn = "";	
	private String smpKbnSel = "";	

	private String kigoBan0 = "";
	private String kigoBan1 = "";
	private String kigoBan2 = "";
	private String kigoBan3 = "";	
	private String kigoBan4 = "";	
	private String kigoBan5 = "";	
	private String kigoBan6 = "";	
	private String kigoBan7 = "";	
	private String kigoBan8 = "";	
	private String kigoBan9 = "";	
		
	private String atai = "1";
	private String cpPaste =""; //2004/06/29 add
	private String hyoKbn =""; 

	
	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList kigbng_arr = new ArrayList();

 //-----  初期処理設定  ------------------------------------------------------------------------

	public SubMateStockForm(){
		super();
		this.clearAll();
		this.setSize(DEFAULT_ROW_COUNT);
		this.setCommand("INIT");
	}

//-----  get/set設定  -------------------------------------------------------------------------

	 //***  押したボタンのget/set  ***
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		command = s;
	}

     //***  クエリ代表会社識別コード  ***
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
    }

    //***  クエリ会社識別コード  ***
    public String getQueryKaiSkbCod(){
    	return queryKaiSkbCod;
    }
    public void setQueryKaiSkbCod(String s){
    	queryKaiSkbCod = s;
    }

    //***  クエリ会社識別コードリストのget/set  ***
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }

	//***  処理区分のget/set  ***
	public String getPrcKbn() {
		return prcKbn;
	}
	public void setPrcKbn(String prcKbn) {
		this.prcKbn = prcKbn;
	}

	//***  サンプル区分のget/set  ***  //2003/09/18
	public String getSmpKbnSel() {
		return smpKbnSel;
	}
	public void setSmpKbnSel(String smpKbnSel) {
		this.smpKbnSel = smpKbnSel;
	}

	//*** 記号番号０のget/set  ***
	public String getKigoBan0() {
		return kigoBan0;
	}
	public void setKigoBan0(String kigoBan0) {
		this.kigoBan0 = kigoBan0;
	}

	//*** 記号番号１のget/set  ***
	public String getKigoBan1() {
		return kigoBan1;
	}
	public void setKigoBan1(String kigoBan1) {
		this.kigoBan1 = kigoBan1;
	}

	//*** 記号番号２のget/set  ***
	public String getKigoBan2() {
		return kigoBan2;
	}
	public void setKigoBan2(String kigoBan2) {
		this.kigoBan2 = kigoBan2;
	}

	//*** 記号番号３のget/set  ***
	public String getKigoBan3() {
		return kigoBan3;
	}
	public void setKigoBan3(String kigoBan3) {
		this.kigoBan3 = kigoBan3;
	}

	//*** 記号番号４のget/set  ***
	public String getKigoBan4() {
		return kigoBan4;
	}
	public void setKigoBan4(String kigoBan4) {
		this.kigoBan4 = kigoBan4;
	}

	//*** 記号番号５のget/set  ***
	public String getKigoBan5() {
		return kigoBan5;
	}
	public void setKigoBan5(String kigoBan5) {
		this.kigoBan5 = kigoBan5;
	}

	//*** 記号番号６のget/set  ***
	public String getKigoBan6() {
		return kigoBan6;
	}
	public void setKigoBan6(String kigoBan6) {
		this.kigoBan6 = kigoBan6;
	}

	//*** 記号番号７のget/set  ***
	public String getKigoBan7() {
		return kigoBan7;
	}
	public void setKigoBan7(String kigoBan7) {
		this.kigoBan7 = kigoBan7;
	}

	//*** 記号番号８のget/set  ***
	public String getKigoBan8() {
		return kigoBan8;
	}
	public void setKigoBan8(String kigoBan8) {
		this.kigoBan8 = kigoBan8;
	}

	//*** 記号番号９のget/set  ***
	public String getKigoBan9() {
		return kigoBan9;
	}
	public void setKigoBan9(String kigoBan9) {
		this.kigoBan9 = kigoBan9;
	}
	
	//*** 記号番号arrのget  ***
	public ArrayList getKigo_arr(){
		ArrayList kigo_arr = new ArrayList();
		kigo_arr.add(kigoBan0.trim());kigo_arr.add(kigoBan1.trim());kigo_arr.add(kigoBan2.trim());
		kigo_arr.add(kigoBan3.trim());kigo_arr.add(kigoBan4.trim());
		kigo_arr.add(kigoBan5.trim());kigo_arr.add(kigoBan6.trim());kigo_arr.add(kigoBan7.trim());
		kigo_arr.add(kigoBan8.trim());kigo_arr.add(kigoBan9.trim());
		return kigo_arr;
	}

	//***  値（ＦｏｒｍＲｏｗの値を入れる・JavaScriptで使用）***
	public String  getAtai() {
		return atai;
	}
	public void setAtai(String atai) {
		this.atai = atai;
	}
	
	
	/**
	 * @return
	 */
	public String getCpPaste() {
		return cpPaste;
	}

	/**
	 * @param string
	 */
	public void setCpPaste(String string) {
		cpPaste = string;
	}

	//*** 表示区分  ***
	public String getHyoKbn() {
		return hyoKbn;
	}
	public void setHyoKbn(String s) {
		this.hyoKbn = s;
	}
	
	/**検索用記号番号**/
	public ArrayList getKigbng_arr() {
		return kigbng_arr;
	}

	public void setKigbng_arr(ArrayList kigbng_arr) {
		this.kigbng_arr = kigbng_arr;
	}



//------------------------------------------------------

	//	バリューオブジェクトの格納
	private ArrayList voList = new ArrayList();

    public void setSubMateStockVO(ArrayList arr){
    	voList.clear();
		voList.addAll(arr);
	}

	public void addSubMateStockVO(SubMateStockVO fmVO){
		voList.add(fmVO);
	}
 	public ArrayList getSubMateStockVO(){
		return voList;	
	}

	public void setSubMateStockVO(int i, SubMateStockVO fmVO){
		voList.set(i, fmVO);
	}

	public SubMateStockVO getSubMateStockVO(int i){
		return (SubMateStockVO)voList.get(i);
	}

	public void removeSubMateStockVO(){
		voList.clear();
	}

	public void removeSubMateStockVO(int i){
    	voList.remove(i);
	}



//---------------------------------------------------------------

	public void clearAll(){
		command = "";
		prcKbn = "1";
		smpKbnSel = ""; //2003/09/18
		kigoBan0 = "";
		kigoBan1 = "";
		kigoBan2 = "";
		kigoBan3 = "";
		kigoBan4 = "";
		kigoBan5 = "";
		kigoBan6 = "";
		kigoBan7 = "";
		kigoBan8 = "";
		kigoBan9 = "";
		cpPaste = ""; //2004/06/29 add
		atai = Integer.toString(DEFAULT_ROW_COUNT);

		setSize(DEFAULT_ROW_COUNT);
		clearTableField();
  	}

	public void pointClear(){
		prcKbn = "1";
	}

	public void removeAllRow(){
		rows.clear();
	}
	
	
	public List getFormRowList(){
		return rows;
	}

//==================================================

	public static class FormRow {
		private boolean execute = false;
		private boolean hidFlag = false;
		private String hidDaiKaiSkbCod = "";
		private String kigoBan = "";
		private String huksizaiCod   = "";
		private String huksizaiMei   = "";
		private String huksizaiZaiko = "";
		private String smpKbn = ""; //2003/09/18
		private boolean sign = true;
		private String teiseiSuu = "";
	    private int hidUpdDte;
	    private int hidUpdJkk;
/*	    
	//***  gets the execute ***
		public boolean getExecute(){
			return execute;
		}

	//***  Gets the hidFlag  ***  
		public boolean getHidFlag() {
			return hidFlag;
		}
		public void setHidFlag(boolean hidFlag) {
			this.hidFlag = hidFlag;
		}

	//***  Gets the hidDaiKaiSkbCod  ***
		public String getHidDaiKaiSkbCod() {
			return hidDaiKaiSkbCod;
		}
		public void setHidDaiKaiSkbCod(String hidDaiKaiSkbCod) {
			this.hidDaiKaiSkbCod = hidDaiKaiSkbCod;
		}

	//***  Gets the KigBan  
		public String getKigBan() {
			return kigoBan;
		}
		public void setKigBan(String KigoBan) {
			this.kigoBan = kigoBan;
		}

	//***  Gets the HuksizaiCod  ***
		public String getHuksizaiCod() {
			return huksizaiCod;
		}
		public void setHuksizaiCod(String HuksizaiCod) {
			this.huksizaiCod = huksizaiCod;
		}

	//***  Gets the HuksizaiMei  ***
		public String getHuksizaiMei() {
			return huksizaiMei;
		}
		public void setHuksizaiMei(String HuksizaiMei) {
			this.huksizaiMei = huksizaiMei;
		}


	//***  Gets the HuksizaiZaiko  ***
		public String getHuksizaiZaiko() {
			return huksizaiZaiko;
		}
		public void setHuksizaiZiko(String HuksizaiZaiko) {
			this.huksizaiZaiko = huksizaiZaiko;
		}

	//***  Gets the smpKbn  *** //2003/09/18
		public String getSmpKbn() {
			return smpKbn;
		}
		public void setSmpKbn(String smpKbn) {
			this.smpKbn = smpKbn;
		}

	//***  Gets the sine  ***
		public boolean getSign() {
			return sign;
		}
		public void setSign(boolean Sign) {
			this.sign = sign;
		}

	//***  Gets the TeiseiSuu  ***
		public String getTeiseiSuu() {
			return teiseiSuu;
		}
		public void setTeiseiSuu(String TeiseiSuu) {
			this.teiseiSuu = teiseiSuu;
		}

	//***  Gets the updDte  ***
		public int getHidUpdDte() {
			return hidUpdDte;
		}
		public void setHidUpdDte(int hidUpdDte) {
			this.hidUpdDte = hidUpdDte;
		}
	//***  Gets the updJkk  ***
		public int getHidUpdJkku() {
			return hidUpdJkk;
		}
		public void setHidUpdJkk(int hidUpdJkk) {
			this.hidUpdJkk = hidUpdJkk;
		}
*/

		public void clear(){
			kigoBan = "";
			huksizaiCod = "";
			huksizaiMei = "";
			huksizaiZaiko = "";
			smpKbn = "";//2003/09/24 add
			sign = true;
			teiseiSuu = "";
		}
	
	}

//-------------------------------------------------

	//Execute
	public boolean getExecute(int i){
		return getFormRow(i).execute;
	}

    //(hidden)フラグ
    public boolean getHidFlag(int i){
    	return getFormRow(i).hidFlag;
    }
    public void setHidFlag(int i , boolean b){
    	getFormRow(i).hidFlag = b;
    }

    //(hidden)代表会社
    public String getHidDaiKaiSkbCod(int i){
    	return getFormRow(i).hidDaiKaiSkbCod;
    }
    public void setHidDaiKaiSkbCod(int i , String s){
    	getFormRow(i).hidDaiKaiSkbCod = s;
    }

     //サンプル区分 //2003/09/18
    public String getSmpKbn(int i){
    	return getFormRow(i).smpKbn;
    }
    public void setSmpKbn(int i , String s){
    	getFormRow(i).smpKbn = s;
    }

     //記号番号
    public String getKigoBan(int i){
    	return getFormRow(i).kigoBan;
    }
    public void setKigoBan(int i , String s){
    	getFormRow(i).kigoBan = s;
    }

     //副資材コード
    public String getHuksizaiCod(int i){
    	return getFormRow(i).huksizaiCod;
    }
    public void setHuksizaiCod(int i , String s){
    	getFormRow(i).huksizaiCod = s;
    }

     //副資材コード
    public String getHuksizaiMei(int i){
    	return getFormRow(i).huksizaiMei;
    }
    public void setHuksizaiMei(int i , String s){
    	getFormRow(i).huksizaiMei = s;
    }

     //副資材在庫数
    public String getHuksizaiZaiko(int i){
    	return getFormRow(i).huksizaiZaiko;
    }
    public void setHuksizaiZaiko(int i , String s){
    	getFormRow(i).huksizaiZaiko = s;
    }

     //サイン
    public boolean getSign(int i){
    	return getFormRow(i).sign;
    }
    public void setSign(int i , boolean flag){
    	getFormRow(i).sign = flag;
    }

     //訂正数
    public String getTeiseiSuu(int i){
    	return getFormRow(i).teiseiSuu;
    }
    public void setTeiseiSuu(int i , String s){
    	getFormRow(i).teiseiSuu = s;
    }

     //更新日
    public int getHidUpdDte(int i){
    	return getFormRow(i).hidUpdDte;
    }
    public void setHidUpdDte(int i , int s){
    	getFormRow(i).hidUpdDte = s;
    }

     //更新時間
    public int getHidUpdJkk(int i){
    	return getFormRow(i).hidUpdJkk;
    }
    public void setHidUpdJkk(int i , int s){
    	getFormRow(i).hidUpdJkk = s;
    }




//-----------------------------------------------------

	public int getSize(){
		return rows.size();
	}
	
	public void setSize(int size){
		if(size > rows.size()){
			int j = size - this.getSize();
			rows.addAll(Collections.nCopies(j,null));
		}else if(size < rows.size()){
			for(int i = rows.size();i > size;i--){
				rows.remove((i-1));
			}
		}
	}

	public FormRow getFormRow(int i){
		FormRow fr = (FormRow)rows.get(i);
		if(fr == null){
			fr = new FormRow();
			rows.set(i,fr);
		}
		return fr;
	}

	public void clearTableField(){
		for(int i = 0;i < rows.size();i++){
			FormRow fr = (FormRow)rows.get(i);
			if(fr != null)
				fr.clear();
		}
		
	}

	public void clearTableField(int i){
		FormRow fr = (FormRow)rows.get(i);
		if(fr != null)
			fr.clear();
	}
 	public void removeTableField(int i){
    	rows.remove(i);
	}

//=====  照会入力判定  ================================================================
 	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//メッセージをセッションから取り除く
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;

		ActionErrors errors = new ActionErrors();

		if(command.equals(COMMAND_JIKKOU)){
//			-----  入力判定  ---------------------------------------------------------------
			//更新権限チェック
			if(!prcKbn.equals("1")){
				boolean authorityChecked = false;
				authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
				if(!authorityChecked){
					errors.add("",new ActionError("errors.cannot","このアカウントで","登録／更新／削除が"));
					return errors;	
				}
			}

			if(prcKbn.equals("1")){  //照会
				ArrayList kigo_arr = getKigo_arr();
			
				boolean blank = true;
				for(int i = 0; i < kigo_arr.size(); i++){
					if(!kigo_arr.get(i).toString().equals("")){
						blank = false;
						break;
					}
				}
				if(blank){
					errors.add("",new ActionError("errors.input.required","記号番号"));
					return errors;
				}

				for(int i =0; i < kigo_arr.size(); i++){
					if(kigo_arr.get(i).toString().equals(""))
						continue;
					if(!StringUtils.isAscii((String)kigo_arr.get(i)))
						errors.add("",new ActionError("errors.input.1","記号番号("+ (i+1) +"番目)","半角英数"));
				}
			}else{  //更新

				boolean blank = true;
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
				
					if(row.kigoBan.equals("")){
						errors.add("",new ActionError("errors.update.notselected","更新"));    //更新は検索したデータに対して行ってください
						return errors;
					}else if(!row.teiseiSuu.trim().equals("")){
						if(!StringUtils.isAsciiDigit(row.teiseiSuu.trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"訂正数","半角数字"));		
						}else{//2003/10/27 add
							if(!row.sign){
								if(Integer.parseInt(getSubMateStockVO(i).getHuksizaiZaiko()) + Integer.parseInt(row.teiseiSuu.trim()) > 999999)
									errors.add("",new ActionError("errors.input.incorrect","訂正数"));
								}else{
									if(Integer.parseInt(getSubMateStockVO(i).getHuksizaiZaiko()) - Integer.parseInt(row.teiseiSuu.trim()) < (-999999))
										errors.add("",new ActionError("errors.input.incorrect","訂正数"));
								}
						}
						if(!row.teiseiSuu.equals("0"))
							blank = false;
					}		
				}
				if(blank){
					errors.add("",new ActionError("errors.input.required","訂正数"));
				}
//------------------------------------------------------------------------------------
			}  //  if(prcKbn().equals("1")){  終了
		}  //if(command.equals(this.COMMAND_JIKKOU)){  終了
		return errors.empty() ? null : errors;	  //入力判定終了
	
	}

//====================================================================================


	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
	}

//=====  配列へ空の文字列の要素を追加するメソッド  ==========================================

	public ArrayList addBlankElement(ArrayList arr){

	for(int z = arr.size(); z<10; z++){
		arr.add("");
	}
		return arr;
	}



}
