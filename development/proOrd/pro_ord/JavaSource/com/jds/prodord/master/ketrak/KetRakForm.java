/**
* KetRakForm  形態ランク別条件マスターメンテナンスフォームクラス
*
*	作成日    2003/05/02
*	作成者    （ＮＩＩ）今井 美季
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
*        2003/05/15（ＮＩＩ）蛭田 敬子
* 			・会社識別コード、形態コード ドロップダウン対応。
* 		 2003/06/02（ＮＩＩ）
* 			・｢クリア｣ボタン追加。｢登録｣｢削除｣の入力チェック修正。
* 		 2003/08/01（ＮＩＩ）岡田 夏美
* 			・形態名称ドロップダウンのラベル変更（コードのみ → コードと名称漢字２併記に）。
*/

package com.jds.prodord.master.ketrak;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class KetRakForm extends ActionForm {
	
	private String command = "";
	private ArrayList rows = new ArrayList();	
	public static final int DEFAULT_ROW_COUNT = 1;
	
	private String prcKbn = "";
	public static final String COMMAND_CLEAR = "クリア";
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String hyoKbn    = "";

	private String[] kaiSkbCod = null;
	private String rank = "";
	private String[] ketCod = null;
	private String ikkatsu_ssnRedTim = "";
	private String ikkatsu_minZaiSuu = "";
	private String ikkatsu_minRotSuu = "";		
	private String ikkatsu_mrmSuu    = "";

	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList vl_ketCod = new ArrayList();
	private ArrayList lb_ketCod = new ArrayList();
	
	public KetRakForm(){
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
    
    //表示区分
    public String getHyoKbn(){
    	return hyoKbn;
    }
    public void setHyoKbn(String s){
    	hyoKbn = s;
    }

	//2003/05/15
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
	
	//2003/05/27
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
	
	//------------------------------------------------------------
	
	//バリューオブジェクトの格納
	private ArrayList voList = new ArrayList();
	
    public void setKetRakVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addKetRakVO(KetRakVO fmVO){

		voList.add(fmVO);
		
	}
	public ArrayList getKetRakVO(){
		
		return voList;	
		
	}
	public void setKetRakVO(int i, KetRakVO fmVO){
    	
		voList.set(i, fmVO);
		
	}
    public KetRakVO getKetRakVO(int i){
		
		return (KetRakVO)voList.get(i);	
		
	}
	public void removeKetRakVO(){
    	
		voList.clear();
		
	}
	public void removeKetRakVO(int i){
    	voList.remove(i);
	}
	
	//チェックボックスをリセットするメソッド
	public void setCheck_false(){
		for(int i = 0; i < this.getSize();i++){
			this.getFormRow(i).setExecute(false);
		}
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
//    /**
//     * 入力会社識別コードのセット
//     * 
//     * @param kaiSkbCod 会社識別コード
//     */
//    public void setKaiSkbCod(String s) {
//    	
//        this.kaiSkbCod = s;
//
//    }
//    /**
//     * 入力会社識別コードの取得
//     * 
//     * @return 会社識別コード
//     */
//    public String getKaiSkbCod() {
//
//        return this.kaiSkbCod;
//
//    }
    /**
     * 入力ランクのセット
     * 
     * @param rank ランク
     */
    public void setRank(String s) {

        this.rank = s;

    }
    /**
     * 入力ランクの取得
     * 
     * @return ランク
     */
    public String getRank() {

        return this.rank;

    }
//    /**
//     * 入力形態コードのセット
//     * 
//     * @param ketCod 形態コード
//     */
//    public void setKetCod(String s) {
//
//        this.ketCod = s;
//
//    }
//    /**
//     * 入力形態コードの取得
//     * 
//     * @return 形態コード
//     */
//    public String getKetCod() {
//
//        return this.ketCod;
//
//    }

//3004/05/15
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
	 * Gets the ikkatsu_ssnRedTim
	 * @return Returns a String
	 */
	public String getIkkatsu_ssnRedTim() {
		return ikkatsu_ssnRedTim;
	}
	/**
	 * Sets the ikkatsu_ssnRedTim
	 * @param ikkatsu_ssnRedTim The ikkatsu_ssnRedTim to set
	 */
	public void setIkkatsu_ssnRedTim(String ikkatsu_ssnRedTim) {
		this.ikkatsu_ssnRedTim = ikkatsu_ssnRedTim;
	}


	/**
	 * Gets the ikkatsu_minZaiSuu
	 * @return Returns a String
	 */
	public String getIkkatsu_minZaiSuu() {
		return ikkatsu_minZaiSuu;
	}
	/**
	 * Sets the ikkatsu_minZaiSuu
	 * @param ikkatsu_minZaiSuu The ikkatsu_minZaiSuu to set
	 */
	public void setIkkatsu_minZaiSuu(String ikkatsu_minZaiSuu) {
		this.ikkatsu_minZaiSuu = ikkatsu_minZaiSuu;
	}


	/**
	 * Gets the ikkatsu_minRotSuu
	 * @return Returns a String
	 */
	public String getIkkatsu_minRotSuu() {
		return ikkatsu_minRotSuu;
	}
	/**
	 * Sets the ikkatsu_minRotSuu
	 * @param ikkatsu_minRotSuu The ikkatsu_minRotSuu to set
	 */
	public void setIkkatsu_minRotSuu(String ikkatsu_minRotSuu) {
		this.ikkatsu_minRotSuu = ikkatsu_minRotSuu;
	}


	/**
	 * Gets the ikkatsu_mrmSuu
	 * @return Returns a String
	 */
	public String getIkkatsu_mrmSuu() {
		return ikkatsu_mrmSuu;
	}
	/**
	 * Sets the ikkatsu_mrmSuu
	 * @param ikkatsu_mrmSuu The ikkatsu_mrmSuu to set
	 */
	public void setIkkatsu_mrmSuu(String ikkatsu_mrmSuu) {
		this.ikkatsu_mrmSuu = ikkatsu_mrmSuu;
	}

	//------------------------------------------------------------表

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
     //生産リードタイム
    public String getSsnRedTim(int i){
    	return getFormRow(i).ssnRedTim;
    }
    public void setSsnRedTim(int i , String s){
    	getFormRow(i).ssnRedTim = s;
    }
     //最小在庫数
    public String getMinZaiSuu(int i){
    	return getFormRow(i).minZaiSuu;
    }
    public void setMinZaiSuu(int i , String s){
    	getFormRow(i).minZaiSuu = s;
    }
     //最小ロット数
    public String getMinRotSuu(int i){
    	return getFormRow(i).minRotSuu;
    }
    public void setMinRotSuu(int i , String s){
    	getFormRow(i).minRotSuu = s;
    }
     //まるめ数量
    public String getMrmSuu(int i){
    	return getFormRow(i).mrmSuu;
    }
    public void setMrmSuu(int i , String s){
    	getFormRow(i).mrmSuu = s;
    }


	//---------------------------------------------------------------------------------
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

	//---------------------------------------------------------------------------------
	public void clearAll(){
		command = "";
		
		prcKbn = "";
        this.kaiSkbCod = null;
        this.rank = "";
        this.ketCod = null;
        this.ikkatsu_ssnRedTim = "";
		this.ikkatsu_minZaiSuu = "";
		this.ikkatsu_minRotSuu = "";
		this.ikkatsu_mrmSuu = "";
		setSize(DEFAULT_ROW_COUNT);
		clearTableField();
  	}
	
	
	public void removeAllRow(){
		rows.clear();
	}
	
	
	public List getFormRowList(){
		return rows;
	}

	//---------------------------------------------------------------------------------
	public static class FormRow {
		private boolean execute = false;
		
		private String outKaiSkbCod = "";
		private String outRank   = "";
		private String outKetCod = "";
		private String ssnRedTim = "";
		private String minZaiSuu = "";
		private String minRotSuu = "";		
		private String mrmSuu    = "";

	     //処理対象
	    public boolean getExecute(){
	    	return execute;
	    }
	    public void setExecute(boolean b){
	    	execute = b;
	    }
	

	    
		public void clear(){
			execute = false;
			outKaiSkbCod = "";
			outRank = "";
			outKetCod = "";
			ssnRedTim = "";
			minZaiSuu = "";
			minRotSuu = "";
			mrmSuu = "";
			
		}
		
		public boolean isBlank(){
			if(!ssnRedTim.equals(""))
				return false;
			if(!minZaiSuu.equals(""))
				return false;
			if(!minRotSuu.equals(""))
				return false;
			if(!mrmSuu.equals(""))
				return false;
			return true;
		}
		
			/**
		 * Gets the outKaiSkbCod
		 * @return Returns a String
		 */
		public String getOutKaiSkbCod() {
			return outKaiSkbCod;
		}
		/**
		 * Sets the outKaiSkbCod
		 * @param outKaiSkbCod The outKaiSkbCod to set
		 */
		public void setOutKaiSkbCod(String outKaiSkbCod) {
			this.outKaiSkbCod = outKaiSkbCod;
		}

		/**
		 * Gets the outRank
		 * @return Returns a String
		 */
		public String getOutRank() {
			return outRank;
		}
		/**
		 * Sets the outRank
		 * @param outRank The outRank to set
		 */
		public void setOutRank(String outRank) {
			this.outRank = outRank;
		}

		/**
		 * Gets the outKetCod
		 * @return Returns a String
		 */
		public String getOutKetCod() {
			return outKetCod;
		}
		/**
		 * Sets the outKetCod
		 * @param outKetCod The outKetCod to set
		 */
		public void setOutKetCod(String outKetCod) {
			this.outKetCod = outKetCod;
		}

	
		/**
		 * Gets the ssnRedTim
		 * @return Returns a String
		 */
		public String getSsnRedTim() {
			return ssnRedTim;
		}
		/**
		 * Sets the ssnRedTim
		 * @param ssnRedTim The ssnRedTim to set
		 */
		public void setSsnRedTim(String ssnRedTim) {
			this.ssnRedTim = ssnRedTim;
		}

		/**
		 * Gets the minZaiSuu
		 * @return Returns a String
		 */
		public String getMinZaiSuu() {
			return minZaiSuu;
		}
		/**
		 * Sets the minZaiSuu
		 * @param minZaiSuu The minZaiSuu to set
		 */
		public void setMinZaiSuu(String minZaiSuu) {
			this.minZaiSuu = minZaiSuu;
		}

	
		/**
		 * Gets the minRotSuu
		 * @return Returns a String
		 */
		public String getMinRotSuu() {
			return minRotSuu;
		}
		/**
		 * Sets the minRotSuu
		 * @param minRotSuu The minRotSuu to set
		 */
		public void setMinRotSuu(String minRotSuu) {
			this.minRotSuu = minRotSuu;
		}

		/**
		 * Gets the mrmSuu
		 * @return Returns a String
		 */
		public String getMrmSuu() {
			return mrmSuu;
		}
		/**
		 * Sets the mrmSuu
		 * @param mrmSuu The mrmSuu to set
		 */
		public void setMrmSuu(String mrmSuu) {
			this.mrmSuu = mrmSuu;
		}

}

	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//メッセージをセッションから取り除く
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		
		if(command.equals("INIT"))
			return null;
		if(rank == null || ketCod == null)//2003/06/25 add okada
			return null;
		
		ActionErrors errors = new ActionErrors();

		//処理区分：１（照会），２（登録），３（変更），４（削除）
		//    		１･･･会社識別＋ランクor形態      ２・４･･･フルキー指定      ３･･･検索結果に対して

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

		//検索・登録・削除のとき
		if(!prcKbn.equals("3")){
			if(!ikkatsu_ssnRedTim.equals("") || !ikkatsu_minZaiSuu.equals("") || 
			   !ikkatsu_minRotSuu.equals("") || !ikkatsu_mrmSuu.equals("")){
			   	errors.add("",new ActionError("errors.input.prohibited","一括変更用入力エリア"));
				return errors;
			}
			//登録時
			if(prcKbn.equals("2")){
				for(int i = 0; i < rows.size();i++){
				    FormRow  row = getFormRow(i);
					if(!row.outKaiSkbCod.equals("")||!row.outRank.equals("")||!row.outKetCod.equals("")){
						errors.add("",new ActionError("errors.insert.exist_results"));
						return errors;
					}
					if(row.ssnRedTim.equals("")||row.minZaiSuu.equals("")||row.minRotSuu.equals("")||row.mrmSuu.equals("")){
						errors.add("",new ActionError("errors.input.requiredall"));
						return errors;
					}
				}
				//ランク
				if(rank.equals("")){
					errors.add("",new ActionError("errors.input.required","ランク"));		
				}
				//形態コード
				if(ketCod[0].equals("")){
					errors.add("",new ActionError("errors.input.required","形態コード"));	
				}
			//検索時
			}else if(prcKbn.equals("1")){
				if(rank.equals("")&&ketCod[0].equals("")){
					errors.add("",new ActionError("errors.input.required","ランク、または形態コード"));	
				}
			}	
		//登録・更新のとき
		}if(prcKbn.equals("2") || prcKbn.equals("3")){
			
			boolean select = false;
			boolean ikkatsuHenko = false;
			//更新時
			if(prcKbn.equals("3")){
				//一括変更かどうか
				if(!ikkatsu_ssnRedTim.equals("") || !ikkatsu_minZaiSuu.equals("") || 
				   !ikkatsu_minRotSuu.equals("") || !ikkatsu_mrmSuu.equals("")){
				   	ikkatsuHenko = true;
				}
				if(ikkatsuHenko){
					if(!ikkatsu_ssnRedTim.equals("") && !StringUtils.isAsciiDigit(ikkatsu_ssnRedTim.trim()))
						errors.add("",new ActionError("errors.input.1","生産リードタイム一括変更","半角数字"));
					if(!ikkatsu_minZaiSuu.equals("") && !StringUtils.isAsciiDigit(ikkatsu_minZaiSuu.trim()))
						errors.add("",new ActionError("errors.input.1","最小在庫数一括変更","半角数字"));
					if(!ikkatsu_minRotSuu.equals("") && !StringUtils.isAsciiDigit(ikkatsu_minRotSuu.trim()))
						errors.add("",new ActionError("errors.input.1","最小ロット数一括変更","半角数字"));
					if(!ikkatsu_mrmSuu.equals("") && !StringUtils.isAsciiDigit(ikkatsu_mrmSuu.trim()))
						errors.add("",new ActionError("errors.input.1","まるめ数量一括変更","半角数字"));
					if(!errors.empty())
						select = true;
				}
			}
			for(int i = 0; i < rows.size();i++){
				FormRow row = getFormRow(i);
				//更新時
				if(prcKbn.equals("3")){
					if(!ikkatsuHenko){//一括変更でなければ、チェックのない行は読み飛ばす
						if(row == null || !row.execute)
							continue;
					}
					select = true;
						
					if(row.outKaiSkbCod.equals("")||row.outRank.equals("")||row.outKetCod.equals("")){
						errors.add("",new ActionError("errors.update.notselected","更新"));
						break;
					}
				}	
				if(!ikkatsu_ssnRedTim.equals("") && row.ssnRedTim.equals("") ){
					errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"生産リードタイム"));		
				}else{
					if(!StringUtils.isAsciiDigit(row.ssnRedTim.trim())){
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"生産リードタイム","半角数字"));
					}
				}
				if(!ikkatsu_minZaiSuu.equals("") && row.minZaiSuu.equals("") ){
					errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"最小在庫数"));		
				}else{
					if(!StringUtils.isAsciiDigit(row.minZaiSuu.trim())){
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"最小在庫数","半角数字"));
					}
				}
				if(!ikkatsu_minRotSuu.equals("") && row.minRotSuu.equals("") ){
					errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"最小ロット数"));		
				}else{
					if(!StringUtils.isAsciiDigit(row.minRotSuu.trim())){
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"最小ロット数","半角数字"));
					}
				}
				if(!ikkatsu_mrmSuu.equals("") && row.mrmSuu.equals("") ){
					errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"まるめ数量"));		
				}else{
					if(!StringUtils.isAsciiDigit(row.mrmSuu.trim())){
						errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"まるめ数量","半角数字"));
					}
				}
			}
			//更新時
			if(prcKbn.equals("3")){
				if(!select){
					errors.add("",new ActionError("errors.input.required","処理対象"));
				}
			}
		}
		boolean select = false;
		//削除の時
		if(prcKbn.equals("4")){
			for(int i = 0; i < rows.size();i++){
				FormRow row = getFormRow(i);
				if(row.outKaiSkbCod.equals("")||row.outRank.equals("")||row.outKetCod.equals("")){
					errors.add("",new ActionError("errors.update.notselected","削除"));
				}
				if(row.execute){
					select = true;
					break;
				}else{
					select = false;
				}
			}
			if(!select)
				errors.add("",new ActionError("errors.input.required","処理対象"));	
		}
		return errors.empty() ? null : errors;	
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
		setCheck_false();//2003/05/22 add okada
	}
	


}

