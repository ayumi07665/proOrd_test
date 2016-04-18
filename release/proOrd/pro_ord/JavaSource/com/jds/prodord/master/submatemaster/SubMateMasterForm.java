/**
* SubMateMasterForm  副資材マスターメンテナンス  フォームクラス
*
*	作成日    2003/0/16
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*           2004/03/15（ＮＩＩ）森
* 			・発注先コードはブランク登録可に変更
* 
*/

package com.jds.prodord.master.submatemaster;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class SubMateMasterForm extends ActionForm {


	public static final String COMMAND_JIKKOU = "実行";
	public static final String COMMAND_CLEAR = "クリア";
		
	private String command = "";
	private ArrayList rows = new ArrayList();
	public static final int DEFAULT_ROW_COUNT = 1;

	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String hyoKbn = ""; //add 2011/05/30
	
	private String prcKbn;
	private String[] kaiSkbCod = null;
	private String hukSziCod = "";

	private ArrayList vl_kaiSkbCod = new ArrayList();



//-----  初期処理設定  ------------------------------------------------------------------------

	public SubMateMasterForm(){
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

	//*** 処理区分のget/set  ***
	public String getPrcKbn() {
		return prcKbn;
	}
	public void setPrcKbn(String prcKbn) {
		this.prcKbn = prcKbn;
	}

    //*** 入力会社識別コードのget/set  ***
    public void setKaiSkbCod(String[] s) {
        this.kaiSkbCod = s;
    }
     public String[] getKaiSkbCod() {
        return this.kaiSkbCod;
    }

   //*** 副資材ｺｰﾄﾞのget/set  ***
   	public String getHukSziCod() {
		return hukSziCod;
	}
	public void setHukSziCod(String hukSziCod) {
		this.hukSziCod = hukSziCod;
	}

	//*** 表示区分のget/set  ***
	public String getHyoKbn() {
		return hyoKbn;
	}
	public void setHyoKbn(String s) {
		this.hyoKbn = s;
	}


//------------------------------------------------------

	//***  会社識別コード  ***
	public Collection getVl_kaiSkbCod(){
		return (Collection)vl_kaiSkbCod;
	}

	public void setKaiSkbCodOptions(ArrayList queryKaiSkbCodList){
		vl_kaiSkbCod.clear();
		for(int i = 0; i<queryKaiSkbCodList.size(); i++){
			vl_kaiSkbCod.add(queryKaiSkbCodList.get(i)+"");
		}
	}


	//-------------------------------------------------------------

	//	バリューオブジェクトの格納
	private ArrayList voList = new ArrayList();

    public void setSubMateMasterVO(ArrayList arr){
    	voList.clear();
		voList.addAll(arr);
	}


	public void addSubMateMasterVO(SubMateMasterVO fmVO){
		voList.add(fmVO);
	}

	public ArrayList getSubMateMasterVO(){
		return voList;	
	}

	public void setSubMateMasterVO(int i, SubMateMasterVO fmVO){
		voList.set(i, fmVO);
	}

    public SubMateMasterVO getSubMateMasterVO(int i){
		return (SubMateMasterVO)voList.get(i);	
	}

	public void removeSubMateMasterVO(){
		voList.clear();
	}

	public void removeSubMateMasterVO(int i){
    	voList.remove(i);
	}


	//チェックボックスをリセットするメソッド
	public void setCheck_false(){
		for(int i = 0; i < this.getSize();i++){
			this.getFormRow(i).setExecute(false);
		}
	}

//---------------------------------------------------------------

     //処理対象
    public boolean getExecute(int i){
    	return getFormRow(i).execute;
    }
    public void setExecute(int i,boolean b){
    	getFormRow(i).execute = b;
    }

     //代表会社識別コード
    public String getHidDaiKaiSkbCod(int i){
    	return getFormRow(i).hidDaiKaiSkbCod;
    }
    public void setHidDaiKaiSkbCod(int i , String s){
    	getFormRow(i).hidDaiKaiSkbCod = s;
    }

     //会社識別ｺｰﾄﾞ
    public String getHidKaiSkbCod(int i){
    	return getFormRow(i).hidKaiSkbCod;
    }
    public void setHidKaiSkbCod(int i , String s){
    	getFormRow(i).hidKaiSkbCod = s;
    }


    //副資材コード
    public String getOutHukSziCod(int i){
    	return getFormRow(i).outHukSziCod;
    }
    public void setOutHukSziCod(int i , String s){
    	getFormRow(i).outHukSziCod = s;
    }

     //分類コード
    public String getOutBunruiCod(int i){
    	return getFormRow(i).outBunruiCod;
    }
    public void setOutBunruiCod(int i , String s){
    	getFormRow(i).outBunruiCod = s;
    }

      //発注先ｺｰﾄﾞ
    public String getOutHatcCod(int i){
    	return getFormRow(i).outHatcCod;
    }
    public void setOutHatcCod(int i , String s){
    	getFormRow(i).outHatcCod = s;
    }


     //副資材名
    public String getOutHukSziMei(int i){
    	return getFormRow(i).outHukSziMei;
    }
    public void setOutHukSziMei(int i , String s){
    	getFormRow(i).outHukSziMei = s;
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

//======================================================

	public void clearAll(){
		command = "";
		
		prcKbn = "";
        this.kaiSkbCod = null;
		this.hukSziCod = "";
		setSize(DEFAULT_ROW_COUNT);
		clearTableField();
//	System.out.println("フィールド内の値をクリアしました。");
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

		private String hidDaiKaiSkbCod = "";		
		private String hidKaiSkbCod = "";		
		private String outHukSziCod = "";
		private String outBunruiCod   = "";
		private String outHatcCod = "";
		private String outHukSziMei = "";
		private int hidUpdDte;
		private int hidUpdJkk;
			    
	//---------------------------------------------------
	     //処理対象
	    public boolean getExecute(){
	    	return execute;
	    }
	    public void setExecute(boolean b){
	    	execute = b;
	    }

		//***  hid代表会社識別ｺｰﾄﾞ  ***
	    public String getHidDaiKaiSkbCod(){
	    	return hidDaiKaiSkbCod;
	    }
	    public void setHidDaiKaiSkbCod(String hidDaiKaiSkbCod){
	    	this.hidDaiKaiSkbCod = hidDaiKaiSkbCod;
	    }

		//***  hid会社識別ｺｰﾄﾞ  ***
	    public String getHidKaiSkbCod(){
	    	return hidKaiSkbCod;
	    }
	    public void setHidKaiSkbCod(String hidKaiSkbCod){
	    	this.hidKaiSkbCod = hidKaiSkbCod;
	    }

		//***  out副資材コード  ***
	    public String getOutHukSziCod(){
	    	return outHukSziCod;
	    }
	    public void setOutHukSziCod(String outHukSziCod){
	    	this.outHukSziCod = outHukSziCod;
	    }

		//***  out分類コード  ***
	    public String getOutBunruiCod(){
	    	return outBunruiCod;
	    }
	    public void setOutBunruiCod(String outBunruiCod){
	    	this.outBunruiCod = outBunruiCod;
	    }

		//***  out発注先コード  ***
	    public String getOutHatcCod(){
	    	return outHatcCod;
	    }
	    public void setOutHatcCod(String outHatcCod){
	    	this.outHatcCod = outHatcCod;
	    }

		//***  out副資材名  ***
	    public String getOutHukSziMei(){
	    	return outHukSziMei;
	    }
	    public void setOutHukSziMei(String outHukSziMei){
	    	this.outHukSziMei = outHukSziMei;
	    }
		//***  hid更新日  ***
	    public int getHidUpdDte(){
	    	return hidUpdDte;
	    }
	    public void setHidUpdDte(int hidUpdDte){
	    	this.hidUpdDte = hidUpdDte;
	    }
		//***  hid更新時間  ***
	    public int getHidUpdJkk(){
	    	return hidUpdJkk;
	    }
	    public void setHidUpdJkk(int hidUpdJkk){
	    	this.hidUpdJkk = hidUpdJkk;
	    }




//--------------------------------------------
	
		public void clear(){
			execute = false;

			hidKaiSkbCod = "";		
			outHukSziCod = "";
			outBunruiCod = "";
			outHatcCod = "";
			outHukSziMei = "";
//			hidUpdDte = 0;
//			hidUpdJkk = 0;

		}
		
	}


//=====  値入力判定  ================================================================

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//メッセージをセッションから取り除く
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;

		ActionErrors errors = new ActionErrors();

		if(command.equals(COMMAND_JIKKOU)){

			//更新権限チェック
			if(!prcKbn.equals("1")){
				boolean authorityChecked = false;
				authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
				if(!authorityChecked){
					errors.add("",new ActionError("errors.cannot","このアカウントで","登録／更新／削除が"));
					return errors;	
				}
			}

			//String型 prcKbn を int型 kubunに変換
			final int kubun = Integer.parseInt(prcKbn);

			boolean select = false;  //処理対象判定用フラグ

			switch(kubun){
			//-----  （照会）   --------------------------------------------
			case 1:
				//***  会社識別コードの入力判定  ***
				//特になし

				//***  副資材ｺｰﾄﾞの入力判定  ***
				if(!hukSziCod.equals("") && !StringUtils.isAscii(hukSziCod.trim()))
					errors.add("",new ActionError("errors.input.1","副資材コード","半角英数"));

				break;
			//-----  (登録)  --------------------------------------------
			case 2:
				//***  検索結果クリア判定  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(!row.outHukSziCod.trim().equals("")){
						select = true;
						break;
					}
				}

				if(select){
					errors.add("",new ActionError("errors.insert.exist_results"));
					return errors;
				}
				//***  会社識別コードの入力判定  ***
				//特になし

				//***  副資材ｺｰﾄﾞの入力判定  ***
				if(hukSziCod.equals(""))
					errors.add("",new ActionError("errors.input.required","副資材コード"));

				if(!StringUtils.isAscii(hukSziCod.trim()))
					errors.add("",new ActionError("errors.input.1","副資材コード","半角英数"));					
				else if(hukSziCod.trim().length() != 3)
					errors.add("",new ActionError("errors.input.1","副資材コード","３桁"));

				//***  分類コードの入力判定  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(row.outBunruiCod.trim().equals(""))
						errors.add("",new ActionError("errors.input.required","分類コード"));
					else if(!StringUtils.isAsciiDigit(row.outBunruiCod.trim()))
						errors.add("",new ActionError("errors.input.1","分類コード","半角数字"));
					else if(!row.outBunruiCod.trim().equals("1") &&!row.outBunruiCod.trim().equals("2"))
						errors.add("",new ActionError("errors.input.1","分類コード","1または2"));

				}
								
				//***  発注先ｺｰﾄﾞの入力判定  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
//2004.03.15　upd 発注先コードはスペースでも可に変更
//					if(row.outHatcCod.trim().equals(""))
//						errors.add("",new ActionError("errors.input.required","発注先コード"));
//					else if(!StringUtils.isAsciiDigit(row.outHatcCod.trim()))
					if(!row.outHatcCod.trim().equals("")){
						if(!StringUtils.isAsciiDigit(row.outHatcCod.trim()))
							errors.add("",new ActionError("errors.input.1","発注先コード","半角数字"));
					}
				}

				//***  副資材名称の入力判定  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(row.outHukSziMei.trim().equals(""))
						errors.add("",new ActionError("errors.input.required","副資材名"));
					if(StringUtils.containsAsciiOrHarfWidthKatakana(row.outHukSziMei.trim()))
							errors.add("",new ActionError("errors.input.1","副資材名","全角"));
					break;
				}

				break;

			//-----  （変更）  --------------------------------------------
			case 3:  
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
				
					if(row.outHukSziCod.trim().equals("")){
						errors.add("",new ActionError("errors.update.notselected","更新"));    //更新は検索したデータに対して行ってください
						select = true;
						break;
					}

					if(row == null || !row.execute)
						continue;
					else
						select = true;

//		System.out.println("Form:select:" + select);

					boolean flag = false;

					//***  分類コードの入力判定  ***
					if(row.outBunruiCod.trim().equals(""))
						errors.add("",new ActionError("errors.input.required","分類コード"));

					if(!StringUtils.isAsciiDigit(row.outBunruiCod.trim()))
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"分類コード","半角"));
					else if(!row.outBunruiCod.trim().equals("1") &&!row.outBunruiCod.trim().equals("2"))
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"分類コード","1または2"));

					//***  発注先ｺｰﾄﾞの入力判定  ***
					if(!row.outHatcCod.trim().equals("")){
						if(!StringUtils.isAsciiDigit(row.outHatcCod.trim()))
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"発注先コード","半角"));
					}

					//***  副資材名の入力判定  ***
					if(row.outHukSziMei.trim().equals(""))
						errors.add("",new ActionError("errors.input.required","副資材名"));
					if(StringUtils.containsAsciiOrHarfWidthKatakana(row.outHukSziMei.trim()))
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"副資材名","全角"));
				}

				if(!select)
					errors.add("",new ActionError("errors.input.required","処理対象"));  //処理対象を指定してください

				break;
			
			//-----  （削除）  --------------------------------------------
			case 4:

				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(row.outBunruiCod.equals("")||row.outHukSziMei.equals("")){
						errors.add("",new ActionError("errors.update.notselected","削除"));    //削除は検索したデータに対して行ってください
						select = true;
						break;
					}

					if(row == null || !row.execute)
						continue;
					else
						select = true;
				}

				if(!select)
					errors.add("",new ActionError("errors.input.required","処理対象"));  //処理対象を指定してください

				break;

//--------------------------------------------------------------------------------------
			}  //switch終了
		}  //if(command.equals(this.COMMAND_JIKKOU)){  終了

		//入力判定終了
		return errors.empty() ? null : errors;	
	}


//====================================================================================

	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
		setCheck_false();
	}


}

