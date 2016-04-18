/**
* KetSziTblForm  形態別構成資材マスターメンテナンスフォームクラス
*
*	作成日    2004/01/30
*	作成者    （ＮＩＩ）森
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
*
*/

package com.jds.prodord.master.ketszitbl;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class KetSziTblForm extends ActionForm {
	
	private String command = "";
	
	private ArrayList rows = new ArrayList();	

	public static final int DEFAULT_ROW_COUNT = 1;
	
	private String prcKbn = "";
	public static final String COMMAND_CLEAR = "クリア";
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;

	private String[] kaiSkbCod = null;
	private String[] ketCod = null;

	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList vl_ketCod = new ArrayList();
	private ArrayList lb_ketCod = new ArrayList();

	private String hyoKbn = "";
	
	int count = 0;
	boolean updatable = false;
		
	public KetSziTblForm(){
		super();
		this.clearAll();
		this.setSize(DEFAULT_ROW_COUNT);
		this.setCommand("INIT");
	}
		

	//-----------------------------------------------------------フッター
	 //押したボタン
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		command = s;
	}

	//-----------------------------------------------------------見出し
     //代表会社識別コード
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
    }
    //------------------------------------------------------------
    //会社識別コード
    public String getQueryKaiSkbCod(){
    	return queryKaiSkbCod;
    }
    public void setQueryKaiSkbCod(String s){
    	queryKaiSkbCod = s;
    }
    
    //会社識別コードリスト
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }
    
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
		lb_ketCod.clear();
		
		for(int i = 0; i< ketCodList.size(); i++){
			vl_ketCod.add(ketCodList.get(i)+"");
			lb_ketCod.add(ketCodList.get(i)+"　"+ketNm2List.get(i));
		}
	}
	
    //表示区分
    public String getHyoKbn(){
    	return hyoKbn;
    }
    public void setHyoKbn(String s){
    	hyoKbn = s;
    }
    
	//------------------------------------------------------------
	
	//バリューオブジェクトの格納
	private ArrayList voList = new ArrayList();
	
    public void setKetSziTblVO(ArrayList arr){
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addKetSziTblVO(KetSziTblVO fmVO){
		voList.add(fmVO);
		
	}
	public ArrayList getKetSziTblVO(){
		
		return voList;	
		
	}
	public void setKetSziTblVO(int i, KetSziTblVO fmVO){
    	voList.set(i, fmVO);
		
	}
    public KetSziTblVO getKetSziTblVO(int i){
		
		return (KetSziTblVO)voList.get(i);
	}
	
	public void removeKetSziTblVO(){
    	
		voList.clear();
		
	}
	public void removeKetSziTblVO(int i){
    	voList.remove(i);
	}

	
	//-------------------------------------------------------------------

	/**
	 * Gets the prcKbn
	 * @return Returns a String
	 */
	public String getPrcKbn() {
		return prcKbn;
	}
	/**
	 * Sets the prcKbn
	 * @param prcKbn The prcKbn to set
	 */
	public void setPrcKbn(String prcKbn) {
		this.prcKbn = prcKbn;
	}

	//---------------------------------------------------------------------------------
	
	/**
	 * 会社識別コードのセット
	 */
	public void setKaiSkbCod(String[] s) {
		this.kaiSkbCod = s;
	}
	/**
	 * 会社識別コードの取得
	 */
	public String[] getKaiSkbCod() {
		return this.kaiSkbCod;
	}

	/**
	 * 形態コードのセット
	 */
	public void setKetCod(String[] s) {
		this.ketCod = s;
	}
	/**
	 * 形態コードの取得
	 */
	public String[] getKetCod() {
		return this.ketCod;
	}
		

	/**
	 * Gets the count
	 * @return Returns a int
	 */
	public int getCount() {
		return count;
	}
	/**
	 * Sets the count
	 * @param count The count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * Gets the updatable
	 * @return Returns a boolean
	 */
	public boolean isUpdatable() {
		return updatable;
	}
	public String getUpdatable() {
		if(updatable)
			return "true";
		else
			return "false";
	}
	/**
	 * Sets the updatable
	 * @param updatable The updatable to set
	 */
	public void setUpdatable(boolean updatable) {
		this.updatable = updatable;
	}

	//---------------------------------------------------------------------------------
	public int getSize(){
		return rows.size();
	}
	
	public void setSize(int size){
		if(size > rows.size()){
			int j = size - this.getSize();
			for(int k=0; k<j; k++){
		    	rows.add(new KetSziTblFormRow());			  	
		  	}	
		}else if(size < rows.size()){
			for(int i = rows.size();i > size;i--){
				rows.remove((i-1));
			}
		}
	}
	
	public KetSziTblFormRow getFormRow(int i){
		KetSziTblFormRow fr = (KetSziTblFormRow)rows.get(i);
		if(fr == null){
			fr = new KetSziTblFormRow();
			rows.set(i,fr);
		}
		return fr;
	}

	public void clearTableField(){
		for(int i = 0;i < rows.size();i++){
			KetSziTblFormRow fr = (KetSziTblFormRow)rows.get(i);
			if(fr != null)
				fr.clear();
		}
	}

	public void clearTableField(int i){
		KetSziTblFormRow fr = (KetSziTblFormRow)rows.get(i);
		if(fr != null)
			fr.clear();
	}

	public void removeTableField(int i){
    	rows.remove(i);
	}

	//---------------------------------------------------------------------------------
	public void clearAll(){
		command = "";
		count = 0;
		
		prcKbn = "";
		ketCod = null;
		setSize(DEFAULT_ROW_COUNT);
		clearTableField();

  	}
	
	
	public void removeAllRow(){
		rows.clear();
	}
	
	public ArrayList getFormRowList(){
		return rows;
	}


	//---------------------------------------------------------------------------------


	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//メッセージをセッションから取り除く
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;
		
		ActionErrors errors = new ActionErrors();

		if(command.equals(COMMAND_CLEAR))
			return null;

		//更新権限チェック
		if(!prcKbn.equals("1")){
			boolean authorityChecked = false;
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","このアカウントで","登録／更新／削除が"));
				return errors;	
			}
		}

		//3：更新
		if(prcKbn.equals("3")){

			boolean select = false;

			for(int i = 0; i < rows.size();i++){
				KetSziTblFormRow row = this.getFormRow(i);

				if(row == null || !row.getCheck_bx())
					continue;
					
				for(int j = 0; j < row.getFuksziCod().length;j++){
					if(!row.getFuksziCod()[j].getVal().trim().equals("")){
						if(!StringUtils.isAscii(row.getFuksziCod()[j].getVal()))
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"副資材コード("+(j+1)+"番目)","半角英数"));
						else if(row.getFuksziCod()[j].getVal().length() != 3)
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"副資材コード("+(j+1)+"番目)","３桁"));
					}
				}

				for(int j = 1; j < row.getFuksziCodArr().size();j++){
					List subList = row.getFuksziCodArr().subList(0,j);
					if(!row.getFuksziCodArr().get(j).equals("")){						
						if(subList.indexOf(row.getFuksziCodArr().get(j)) != -1){						
							errors.add("",new ActionError("errors.insert.duplicate_no",String.valueOf(i+1),"副資材コード("+(j+1)+"番目)"));
							break;
						}
					}
				}

				select = true;

				//更新時
				if(prcKbn.equals("3")){
					select = true;
					if(row.getKetCod().equals("")){
						errors.add("",new ActionError("errors.update.notselected","更新"));
					}
				}	
			}
			if(!select)
				errors.add("",new ActionError("errors.input.required","処理対象"));
		}
		return errors.empty() ? null : errors;	
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
	}
}	