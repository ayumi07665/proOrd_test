/**
* StockDaysForm  在庫日数マスターメンテナンスフォームクラス
*
*	作成日    2003/06/09
*	作成者    （ＮＩＩ）村上  博基
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
*            2003/08/01（ＮＩＩ）岡田 夏美
* 		・形態名称ドロップダウンのラベル変更（コードのみ → コードと名称漢字２併記に）。
* 
*/

package com.jds.prodord.master.stockdays;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class StockDaysForm extends ActionForm {


	public static final String COMMAND_JIKKOU = "実行";
	public static final String COMMAND_CLEAR = "クリア";
		
	private String command = "";
	private ArrayList rows = new ArrayList();
	public static final int DEFAULT_ROW_COUNT = 1;

	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String prcKbn;
	private String[] kaiSkbCod = null;
	private String rank = "";
	private String[] ketCod = null;
	private String stockDays = "";
	private String hyoKbn = ""; //add 2011/05/30

	
	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList vl_ketCod = new ArrayList();
	private ArrayList lb_ketCod = new ArrayList();


//-----  初期処理設定  ------------------------------------------------------------------------

	public StockDaysForm(){
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

    //*** 入力ランクのセット     ***
    public void setRank(String s) {
        this.rank = s;
    }
    public String getRank() {
        return this.rank;
    }

   //*** 入力形態コードのget/set  ***
    public void setKetCod(String[] s) {
        this.ketCod = s;
    }
    public String[] getKetCod() {
        return this.ketCod;
    }



   //*** 在庫日数一括処理のget/set  ***
    public void setStockDays(String s) {
        this.stockDays = s;
    }
    public String getStockDays() {
        return this.stockDays;
    }

    //*** 表示区分のget/set  ***
    public void setHyoKbn(String s) {
        this.hyoKbn = s;
    }
    public String getHyoKbn() {
        return this.hyoKbn;
    }


//------------------------------------------------------

	//***  形態コード・会社識別コード  ***
	public Collection getVl_ketCod(){
		return (Collection)vl_ketCod;
	}
	public Collection getLb_ketCod(){
		return (Collection)lb_ketCod;
	}
	public Collection getVl_kaiSkbCod(){
		return (Collection)vl_kaiSkbCod;
	}

	public void setKaiSkbCodOptions(ArrayList queryKaiSkbCodList){
		vl_kaiSkbCod.clear();
		for(int i = 0; i<queryKaiSkbCodList.size(); i++){
			vl_kaiSkbCod.add(queryKaiSkbCodList.get(i)+"");
		}
	}
	public void setKetCodOptions(ArrayList ketCodList,ArrayList ketNm2List){
		vl_ketCod.clear();
		vl_ketCod.add("");
		lb_ketCod.clear();
		lb_ketCod.add("");
		
		for(int i = 0; i< ketCodList.size(); i++){
			vl_ketCod.add(ketCodList.get(i)+"");
			lb_ketCod.add(ketCodList.get(i)+"　"+ketNm2List.get(i));
		}
	}


	//-------------------------------------------------------------

	//	バリューオブジェクトの格納
	private ArrayList voList = new ArrayList();

    public void setStockDaysVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}


	public void addStockDaysVO(StockDaysVO fmVO){

		voList.add(fmVO);
		
	}

	public ArrayList getStockDaysVO(){
		
		return voList;	
		
	}

	public void setStockDaysVO(int i, StockDaysVO fmVO){

		voList.set(i, fmVO);
		
	}
    public StockDaysVO getStockDaysVO(int i){
		
		return (StockDaysVO)voList.get(i);	
		
	}

	public void removeStockDaysVO(){
    	
		voList.clear();
		
	}
	public void removeStockDaysVO(int i){
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


     //会社識別コード
    public String getOutKaiSkbCod(int i){
    	return getFormRow(i).outKaiSkbCod;
    }
    public void setOutKaiSkbCod(int i , String s){
    	getFormRow(i).outKaiSkbCod = s;
    }


     //ランク
    public String getOutRank(int i){
    	return getFormRow(i).outRank;
    }
    public void setOutRank(int i , String s){
    	getFormRow(i).outRank = s;
    }


     //形態コード
    public String getOutKetCod(int i){
    	return getFormRow(i).outKetCod;
    }
    public void setOutKetCod(int i , String s){
    	getFormRow(i).outKetCod = s;
    }


     //在庫日数
    public String getOutStockDays(int i){
    	return getFormRow(i).outStockDays;
    }
    public void setOutStockDays(int i , String s){
    	getFormRow(i).outStockDays = s;
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
        this.rank = "";
        this.ketCod = null;
		this.stockDays = "";
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
		
		private String outKaiSkbCod = "";
		private String outRank   = "";
		private String outKetCod = "";
		private String outStockDays = "";
	    
	//---------------------------------------------------
	     //処理対象
	    public boolean getExecute(){
	    	return execute;
	    }
	    public void setExecute(boolean b){
	    	execute = b;
	    }

		
	//***  Gets the outKaiSkbCod  
		public String getOutKaiSkbCod() {
			return outKaiSkbCod;
		}
		public void setOutKaiSkbCod(String outKaiSkbCod) {
			this.outKaiSkbCod = outKaiSkbCod;
		}

	//***  Gets the outRank  ***
		public String getOutRank() {
			return outRank;
		}
		public void setOutRank(String outRank) {
			this.outRank = outRank;
		}


	//***  Gets the outKetCod  ***
		public String getOutKetCod() {
			return outKetCod;
		}
		public void setOutKetCod(String outKetCod) {
			this.outKetCod = outKetCod;
		}

	
	//***  Gets the outStockDays  ***
		public String getOutStockDays() {
			return outStockDays;
		}
		public void setOutStockDays(String outStockDays) {
			this.outStockDays = outStockDays;
		}


	//-------------------------------------------------
	
		public void clear(){
			execute = false;
			outKaiSkbCod = "";
			outRank = "";
			outKetCod = "";
			outStockDays = "";
			
		}
		
		public boolean isBlank(){
			if(!outStockDays.equals(""))
				return false;

			return true;
		}


	}


//=====  値入力判定  ================================================================

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//メッセージをセッションから取り除く
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;
		if(rank == null || ketCod == null)//2003/06/25 add okada
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

			//-----  （照会）  会社識別コード＋(ランク or 形態コード)  --------------------------------------------
			case 1:
				//***  在庫日数一括変更の入力判定  ***
				if(!stockDays.trim().equals(""))
					errors.add("",new ActionError("errors.input.prohibited","在庫日数一括変更"));
				//***  ランクor形態コード入力存在判定  ***
				if(rank.equals("")&&ketCod[0].equals(""))
					errors.add("",new ActionError("errors.input.required","ランク、または形態コード"));
				break;

			//-----  (登録)  会社識別コード＋ランク+形態コード + 在庫日数  --------------------------------------------
			case 2:

				//***  ランクの入力判定  ***
				if(rank.equals(""))
					errors.add("",new ActionError("errors.input.required","ランク"));
				//***  形態コードの入力判定  ***
				if(ketCod[0].equals(""))
					errors.add("",new ActionError("errors.input.required","形態コード"));
				//***  在庫日数一括変更の入力判定  ***
				if(!stockDays.trim().equals(""))
					errors.add("",new ActionError("errors.input.prohibited","在庫日数一括変更"));
				//***  在庫日数の入力判定  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(row.outStockDays.trim().equals(""))
						errors.add("",new ActionError("errors.input.required","在庫日数"));
					else if(!StringUtils.isAsciiDigit(row.outStockDays.trim()))
						errors.add("",new ActionError("errors.input.1","在庫日数","半角数字"));
					break;
				}
				//***  検索結果クリア判定  ***
				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
						if(!row.outKaiSkbCod.trim().equals("")||!row.outRank.trim().equals("")||!row.outKetCod.trim().equals("")){
							select = true;
							break;
						}
				}	
				if(select)
					errors.add("",new ActionError("errors.insert.exist_results"));
				break;

			//-----  （変更）  検索結果に対して + チェックボックス + （在庫日数 or 在庫日数一括変更）   --------------------------------------------
			case 3:  

				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
				
					if(row.outKaiSkbCod.equals("")||row.outRank.equals("")||row.outKetCod.equals("")){
						errors.add("",new ActionError("errors.update.notselected","更新"));    //更新は検索したデータに対して行ってください
						select = true;
						break;
					}

					if(row == null || !row.execute)
						continue;
					else
						select = true;

					boolean flag = false;

					//***  在庫日数の入力判定  ***
					if(row.outStockDays.equals("")) 
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"在庫日数"));
					else if(!StringUtils.isAsciiDigit(row.outStockDays.trim()))
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"在庫日数","半角数字"));
				}

				//***  在庫日数一括変更の入力判定  ***				
				if(!stockDays.equals("") && !StringUtils.isAsciiDigit(stockDays.trim()))
					errors.add("",new ActionError("errors.input.1","在庫日数一括変更の値","半角数字"));
			
				if(!select && stockDays.equals(""))
					errors.add("",new ActionError("errors.input.required","処理対象または在庫日数一括変更"));  //処理対象を指定してください

				break;
			
			//-----  （削除）  検索結果に対して + チェックボックス  --------------------------------------------
			case 4:

				//***  在庫日数一括変更の入力判定  ***
				if(!stockDays.trim().equals(""))
					errors.add("",new ActionError("errors.input.prohibited","在庫日数一括変更"));

				for(int i = 0; i < rows.size();i++){
					FormRow row = getFormRow(i);
					if(row.outKaiSkbCod.equals("") || row.outRank.equals("") || row.outKetCod.equals("")){
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

