/**
* FzkHinKsiForm  付属品構成マスターメンテナンスフォームクラス
*
*	作成日    2004/02/12
*	作成者    （ＮＩＩ）森
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
*
*/

package com.jds.prodord.master.fzkhinksi;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class FzkHinKsiForm extends ActionForm {
	
	private String command = "";
	
	private ArrayList rows = new ArrayList();	

	public static final int DEFAULT_ROW_COUNT = 1;
	public static final int MIN_OF_BRANK_ROW_COUNT = 10;
	
	private String prcKbn = "";
	public static final String COMMAND_CLEAR = "クリア";
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;

	private String[] kaiSkbCod = null;

	private ArrayList vl_kaiSkbCod = new ArrayList();

	private String hinban = "";
	private String title = "";	
	private String hbidte = "";
	private String ketNm = "";
	private String ketCod = "";
	private String setsuu = "";
	private String hyoKbn = "";
	
	int count = 0;
	boolean updatable = false;
		
	public FzkHinKsiForm(){
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
    
	public Collection getVl_kaiSkbCod(){
		return (Collection)vl_kaiSkbCod;
	}

	public void setKaiSkbCodOptions(ArrayList queryKaiSkbCodList){
		vl_kaiSkbCod.clear();
		for(int i = 0; i<queryKaiSkbCodList.size(); i++){
			vl_kaiSkbCod.add(queryKaiSkbCodList.get(i)+"");
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
	private FzkHinKsiVO vo = new FzkHinKsiVO();
	
    public void setFzkHinKsiVO(FzkHinKsiVO vo){
		this.vo = vo;
		
	}
	public FzkHinKsiVO getFzkHinKsiVO(){
		
		return vo;	
		
	}	
	public void clearFzkHinKsiVO(){
    	
		this.vo = new FzkHinKsiVO();
		
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
	 *  品番の取得
	 * @return
	 */
	public String getHinban() {
		return hinban;
	}
	/**
	 * 品番のセット
	 * @param string
	 */
	public void setHinban(String hinban) {
		this.hinban = hinban;
	}

	/**
	 * タイトルの取得
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * タイトルのセット
	 * @param string
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 *　発売日の取得
	 * @return　hbidte
	 */
	public String getHbidte() {
		return hbidte;
	}
	/**
	 * 発売日のセット
	 * @param string
	 */
	public void setHbidte(String hbidte) {
		this.hbidte = hbidte;
	}

	/**
	 * 形態名称の取得
	 * @return
	 */
	public String getKetNm() {
		return ketNm;
	}
	/**
	 * 形態名称のセット
	 * @param string
	 */
	public void setKetNm(String ketNm) {
		this.ketNm = ketNm;
	}

	/**
	 * 形態コードの取得
	 * @return
	 */
	public String getKetCod() {
		return ketCod;
	}

	/**
	 * 形態コードのセット
	 * @param ketCd
	 */
	public void setKetCod(String ketCod) {
		this.ketCod = ketCod;
	}

	/**
	 * セット数の取得
	 * @return
	 */
	public String getSetsuu() {
		return setsuu;
	}
	/**
	 * セット数のセット
	 * @param string
	 */
	public void setSetsuu(String setsuu) {
		this.setsuu = setsuu;
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
		    	rows.add(new FzkHinKsiFormRow());			  	
		  	}	
		}else if(size < rows.size()){
			for(int i = rows.size();i > size;i--){
				rows.remove((i-1));
			}
		}
	}
	
	public FzkHinKsiFormRow getFormRow(int i){
		FzkHinKsiFormRow fr = (FzkHinKsiFormRow)rows.get(i);
		if(fr == null){
			fr = new FzkHinKsiFormRow();
			rows.set(i,fr);
		}
		return fr;
	}

	public void clearTableField(){
		for(int i = 0;i < rows.size();i++){
			FzkHinKsiFormRow fr = (FzkHinKsiFormRow)rows.get(i);
			if(fr != null)
				fr.clear();
		}
	}

	public void clearTableField(int i){
		FzkHinKsiFormRow fr = (FzkHinKsiFormRow)rows.get(i);
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
		
		this.prcKbn = "";
		this.title = "";
		this.hbidte = "";
		this.setsuu = "";
		this.ketNm = "";
		this.ketCod = "";
		this.hinban = "";
		this.kaiSkbCod = null;
		setSize(DEFAULT_ROW_COUNT);
		clearTableField();
		this.clearFzkHinKsiVO();

  	}
	
	
	public void removeAllRow(){
		rows.clear();
	}
	
	public ArrayList getFormRowList(){
		return rows;
	}
//	チェックボックスをリセットするメソッド
	public void setCheck_false(){
		for(int i = 0; i < this.getSize();i++){
			this.getFormRow(i).setCheck_bx(false);
		}
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

		if(this.getHinban().equals(""))
			errors.add("",new ActionError("errors.input.required","品番"));
		
		if((errors.empty()) && (prcKbn.equals("2") || prcKbn.equals("3") || prcKbn.equals("4"))){

			FzkHinKsiVO fmVO = this.getFzkHinKsiVO();
			if(this.getHinban().equals(fmVO.getHinban())){
			//2 or 3：登録又は更新
				if(prcKbn.equals("2") || prcKbn.equals("3")){
			
					boolean select = false;
					ArrayList list = new ArrayList();
					int check_count = 0;
					for(int i = 0; i < rows.size();i++){
						FzkHinKsiFormRow row = this.getFormRow(i);

						if(row == null || !row.getCheck_bx())
							continue;
					
						//副資材コード：必須
						if(row.getFuksziCod().trim().equals("")){
							errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"副資材コード"));
						}else {
						//副資材コード：半角数字のみ
							if(!StringUtils.isAscii(row.getFuksziCod().trim()))
								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"副資材コード","半角英数"));
						}
						//必須
						if(row.getSirSk().trim().equals("")){
							errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"仕入先コード"));
						}else {
						//半角数字のみ
							if(!StringUtils.isAscii(row.getSirSk().trim()))
								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"仕入先コード","半角英数"));
						}

						//副資材コード：重複
						if(errors.empty()){
							if(list.indexOf(row.getFuksziCod().trim()) != -1)
								errors.add("",new ActionError("errors.insert.duplicate_no",String.valueOf(i+1),"副資材コード"));
						}
						list.add(row.getFuksziCod().trim());			
						select = true;
						check_count = check_count + 1; 
					}

					if(!select)
						errors.add("",new ActionError("errors.input.required","構成"));
						
					if(check_count > 40)
						errors.add("",new ActionError("errors.cannot","構成","41件以上登録"));
						
				}
			}else{
				errors.add("",new ActionError("errors.update.notselected","登録・更新・削除"));
			}
		}
		return errors.empty() ? null : errors;	
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
		setCheck_false();
	}


}	