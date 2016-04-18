/**
* FzkHinKsiPasteForm  付属品構成マスター一括貼り付けフォームクラス
*
*	作成日    2007/09/30
*	作成者    （ＮＩＩ）田中
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
*
*/

package com.jds.prodord.master.fzkhinksipaste;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class FzkHinKsiPasteForm extends ActionForm {
	
	public final int ROW_MAX = 200;
	
	public static final int NOT_HIN_EXIST = 0;//品番なし
	public static final int NOT_FKS_EXIST = 1;//副資材なし
	public static final int NOT_SIR_EXIST = 2;//仕入先なし
	public static final int MST08_EXIST   = 3;//付属品構成マスター登録済み
		
	private String command = "";
	LinkedList formRows = new LinkedList();
    
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private String kaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String cpPaste = ""; 
	private String cpPasteMode ="1";
	
	private String hyoKbn ="";
	//*****************************************************
	
	//メニューからきたときはtrueをセット
	private boolean fromMenuSgn = false;
	public boolean getFromMenuSgn(){
		return fromMenuSgn;
	}
	public void setFromMenuSgn(boolean fromMenuSgn){
			this.fromMenuSgn = fromMenuSgn;
	}
	
	public FzkHinKsiPasteForm(){
		super();
		this.clearAll();
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
    
	//会社識別コード
	public String getKaiSkbCod(){
		return kaiSkbCod;
	}
	public void setKaiSkbCod(String s){
		kaiSkbCod = s;
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
    
	//---------------------------------------------------------------------------------

	/**
	 * コピー＆ペースト
	 * @return
	 */
	public String getCpPaste() {
		return cpPaste;
	}

	/**
	 * コピー＆ペースト
	 * @param string
	 */
	public void setCpPaste(String string) {
		cpPaste = string;
	}

	//---------------------------------------------------------------------------------

	public void clearAll(){
		command = "";
		
		setFormRows(new LinkedList());
		setCpPaste("");
		setCpPasteMode("1");

	}
	
	
	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//メッセージをセッションから取り除く
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		
		if(command.equals("INIT"))
			return null;
		if(command.equals(getMessage("button.clear")))
			return null;
		
		ActionErrors errors = new ActionErrors();
		
		//貼付
		if(command.equals(getMessage("button.paste"))){
			//入力なし
			if(StringUtils.isBlankOrNull(getCpPaste())){
				errors.add("",new ActionError("errors.input.required",getMessage("label.pastedeta")));
			}
			//最大明細数超過
			else if(DataFormatUtils.getDataRowCount(getCpPaste()) > ROW_MAX){
				errors.add("",new ActionError("errors.exceed",getMessage("label.pastedeta"),getMessage("label.maxpasterow")));
			}

		}
		//実行
		else if(command.equals(getMessage("button.execute"))){
			//更新権限チェック
			boolean authorityChecked = false;
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","このアカウントで","登録／更新／削除が"));
				return errors;	
			}

			//明細入力有無チェック
			boolean exist = false;
			
			int i = 0;
			ArrayList key = new ArrayList();

			for (Iterator iter = getFormRows().iterator(); iter.hasNext(); i++) {
				FzkHinKsiPasteFormRow row = (FzkHinKsiPasteFormRow) iter.next();
				// 品番が入力されていなかったら、次の行へ
				if (StringUtils.isBlankOrNull(row.getKigBng())) {
					continue;
				}

				//入力あり				
				exist = true;
				// 半角チェック
				if (!StringUtils.isAsciiOrHarfWidthKatakana(row.getKigBng())) {
					errors.add("ROW_"+i, new ActionError("errors.input.1",getMessage("label.kigbng"),"半角"));
				}

				// 副資材ﾁｪｯｸ
				if (StringUtils.isBlankOrNull(row.getFukSziCod())){
					errors.add("ROW_"+i, new ActionError("errors.input.required",getMessage("label.fukshizai")));
				}else
				if (!StringUtils.isAscii(row.getFukSziCod())) {
					errors.add("ROW_"+i, new ActionError("errors.ascii",getMessage("label.fukshizai")));
				}

				// 仕入先ﾁｪｯｸ
				if (StringUtils.isBlankOrNull(row.getSirSk())){
					errors.add("ROW_"+i, new ActionError("errors.input.required",getMessage("label.sirhaccod")));
				}else
				if (!StringUtils.isAscii(row.getSirSk())) {
					errors.add("ROW_"+i, new ActionError("errors.ascii",getMessage("label.sirhaccod")));
				}

				//重複データチェック

				if(key.indexOf(row.getKigBng().trim() + row.getFukSziCod().trim()) != -1){
					errors.add("ROW_"+i, new ActionError("errors.insert.duplicate"));
				}
				key.add(row.getKigBng().trim() + row.getFukSziCod().trim());

			}

			// 明細未入力だった場合、エラー
			if (!exist)
				errors.add("", new ActionError("errors.input.requiredall"));
			
		}

		return errors.empty() ? null : errors;		
	}
	
	//---------------------------------------------------------------------------------

	public int getSize(){
		return formRows.size();
	}
	

	public void setSize(int size){
		if(size > formRows.size()){
			int j = size - this.getSize();
			for(int k=0; k<j; k++){
				formRows.add(new FzkHinKsiPasteFormRow());			  	
			}	
		}else if(size < formRows.size()){
			for(int i = formRows.size();i > size;i--){
				formRows.remove((i-1));
			}
		}
	}
	

	public FzkHinKsiPasteFormRow getFormRow(int i){
		FzkHinKsiPasteFormRow formRow = (FzkHinKsiPasteFormRow)formRows.get(i);
		if(formRow == null){
			formRow = new FzkHinKsiPasteFormRow();
			formRows.set(i,formRow);
		}
		return formRow;
	}


	public void clearTableField(){
		for(int i = 0;i < formRows.size();i++){
			FzkHinKsiPasteFormRow formRow = (FzkHinKsiPasteFormRow)formRows.get(i);
			if(formRow != null)
				formRow.clear();
		}
	}


	public void clearTableField(int i){
		FzkHinKsiPasteFormRow formRow = (FzkHinKsiPasteFormRow)formRows.get(i);
		if(formRow != null)
			formRow.clear();
	}


	public void removeTableField(int i){
		formRows.remove(i);
	}



//---------------------------------------------------------------------------------------------------
	
	public void reset(ActionMapping maping,HttpServletRequest req){
			this.command = "INIT";
	}
	


	//-------------------------------------------------------------メッセージ
	
	/**
	 * リソースファイルから、指定されたキーに対応するメッセージを取得します。
	 * @param key
	 * @return keyに対応するメッセージ
	 * @see CommonMessages#getString(String)
	 */
	public String getMessage(String key){
		return ResourceMessages.getString(key);		
	}

	/**
	 * @return
	 */
	public LinkedList getFormRows() {
		return formRows;
	}

	/**
	 * @param list
	 */
	public void setFormRows(LinkedList list) {
		formRows = list;
	}

	/**
	 * @return
	 */
	public String getCpPasteMode() {
		return cpPasteMode;
	}

	/**
	 * @param string
	 */
	public void setCpPasteMode(String string) {
		cpPasteMode = string;
	}


}
