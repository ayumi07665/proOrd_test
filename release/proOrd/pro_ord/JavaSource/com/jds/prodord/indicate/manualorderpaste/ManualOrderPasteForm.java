package com.jds.prodord.indicate.manualorderpaste;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;
/**
 * ManualOrderPasteForm  マニュアル発注指示一括貼付フォームクラス。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>Httpリクエストとリクエストの処理結果を格納するクラス</dd>
 * <br>
 * <b>変更履歴</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>・</td>
 * 	</tr>
 * </table>
 */
public class ManualOrderPasteForm extends ActionForm {
	
	public static final String PRS = "0";
	public static final String FKS = "1";

	public final int ROW_MAX = 200;
	
	public static final String NOT_HIN_EXIST = "label.kigbng";
	public static final String NOT_FKS_EXIST = "label.fukshizai";
		
	private String command = "";
	LinkedList formRows = new LinkedList();
    
	//プレス／副資材
	private String prsFks = "";
	//区分
	private String kbn = "";
	
	private ArrayList prsFksList = new ArrayList();
	private ArrayList kbnList = new ArrayList();

	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String bshCod = "";
	private String usrId = "";
	
	private String cpPaste = ""; 
	private String cpPasteMode ="1";
	
	private String prsFksChangeFlg = "0";
	
	//*****************************************************
	
	public ManualOrderPasteForm(){
		super();
		this.clearAll();
		this.setCommand("INIT");
	}
		

	//メニューからきたときはtrueをセット
	private boolean fromMenuSgn = false;
	public boolean getFromMenuSgn(){
		return fromMenuSgn;
	}
	public void setFromMenuSgn(boolean fromMenuSgn){
			this.fromMenuSgn = fromMenuSgn;
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
	
	//---------------------------------------------------------------------------------

	/**
	 * プレス／副資材のセット
	 * @param string
	 */
	public void setPrsFks(String prsFks) {
		this.prsFks = prsFks;
	}
	/**
	 * プレス／副資材の取得
	 * @return
	 */
	public String getPrsFks() {
		return this.prsFks;
	}

    /**
     * 区分のセット
     * 
     * @param kbn 区分
     */
    public void setKbn(String kbn) {
        this.kbn = kbn;
    }
    /**
     * 区分の取得
     * 
     * @return 区分
     */
    public String getKbn() {
        return this.kbn;
    }

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
		setKbn(CommonConst.SINPU);
		setPrsFks(PRS);
		
		setFormRows(new LinkedList());
		setCpPaste("");
		setCpPasteMode("1");
		setPrsFksChangeFlg("0");

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
			//明細入力有無チェック
			boolean exist = false;
			
			int i = 0;
			for (Iterator iter = getFormRows().iterator(); iter.hasNext(); i++) {
				ManualOrderPasteFormRow row = (ManualOrderPasteFormRow) iter.next();
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
				if (getPrsFks().equals(FKS)) {
					if (StringUtils.isBlankOrNull(row.getFukSziCod())){
						errors.add("ROW_"+i, new ActionError("errors.input.required",getMessage("label.fukshizai")));
					}else 
					if (!StringUtils.isAscii(row.getFukSziCod())) {
						errors.add("ROW_"+i, new ActionError("errors.input.1",getMessage("label.fukshizai"),"半角英数"));
					}
				}

				// 発注数量ﾁｪｯｸ
				if (StringUtils.isBlankOrNull(row.getSuuRyo())){
					errors.add("ROW_"+i, new ActionError("errors.input.required",getMessage("label.hacsryo")));
				}else
				if (!StringUtils.isAsciiDigit(row.getSuuRyo())||Integer.parseInt(row.getSuuRyo())<=0){
					errors.add("ROW_"+i, new ActionError("errors.input.incorrect",getMessage("label.hacsryo")));
				}else 
				if (row.getSuuRyo().length()>7) {
					errors.add("ROW_"+i, new ActionError("errors.input.1",getMessage("label.hacsryo"),"7字以内"));
				}

				// 備考２チェック
				if(!row.getBiKou2().trim().equals("")){
					if (!StringUtils.isAsciiOrHarfWidthKatakana(row.getBiKou2())) 
						errors.add("ROW_"+i, new ActionError("errors.input.1",getMessage("label.bikou2"),"半角"));
				}
			}

			// 明細未入力だった場合、エラー
			if (!exist)
				errors.add("", new ActionError("errors.input.requiredall"));
			
		}

		return errors.empty() ? null : errors;		
	}
	
	//---------------------------------------------------------------------------------

	public void initOptionLists() {
		ArrayList prsFksList = new ArrayList();
		prsFksList.add(new LabelValueBean(getMessage("label.prs"), PRS));
		prsFksList.add(new LabelValueBean(getMessage("label.fukshizai"), FKS));
		setPrsFksList(prsFksList);

		ArrayList kbnList = new ArrayList();
		kbnList.add(new LabelValueBean(getMessage("select.sinpu"), CommonConst.SINPU));
		kbnList.add(new LabelValueBean(getMessage("select.kyuhu"), CommonConst.KYUHU));
		kbnList.add(new LabelValueBean(getMessage("select.sample"), CommonConst.SAMPLE));
		kbnList.add(new LabelValueBean(getMessage("select.tokhan"), CommonConst.TOKHAN));
		kbnList.add(new LabelValueBean(getMessage("select.demoban"), CommonConst.DEMOBAN));
		kbnList.add(new LabelValueBean(getMessage("select.sonota"), CommonConst.OTHERS));
		setKbnList(kbnList);

	}

	//---------------------------------------------------------------------------------
	public int getSize(){
		return formRows.size();
	}
	

	public void setSize(int size){
		if(size > formRows.size()){
			int j = size - this.getSize();
			for(int k=0; k<j; k++){
				formRows.add(new ManualOrderPasteFormRow());			  	
			}	
		}else if(size < formRows.size()){
			for(int i = formRows.size();i > size;i--){
				formRows.remove((i-1));
			}
		}
	}
	

	public ManualOrderPasteFormRow getFormRow(int i){
		ManualOrderPasteFormRow formRow = (ManualOrderPasteFormRow)formRows.get(i);
		if(formRow == null){
			formRow = new ManualOrderPasteFormRow();
			formRows.set(i,formRow);
		}
		return formRow;
	}


	public void clearTableField(){
		for(int i = 0;i < formRows.size();i++){
			ManualOrderPasteFormRow formRow = (ManualOrderPasteFormRow)formRows.get(i);
			if(formRow != null)
				formRow.clear();
		}
	}


	public void clearTableField(int i){
		ManualOrderPasteFormRow formRow = (ManualOrderPasteFormRow)formRows.get(i);
		if(formRow != null)
			formRow.clear();
	}


	public void removeTableField(int i){
		formRows.remove(i);
	}



//---------------------------------------------------------------------------------------------------
	
	public void reset(ActionMapping maping,HttpServletRequest req){
//		if(!command.equals(COMMAND_PRSHACHU) && !command.equals(COMMAND_FUKUHACHU))
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
	 * 場所コードの取得
	 * @return
	 */
	public String getBshCod() {
		return bshCod;
	}

	/**
	 * 場所コードのセット
	 * @param string
	 */
	public void setBshCod(String string) {
		bshCod = string;
	}

	/**
	 * ユーザーＩＤの取得
	 * @return
	 */
	public String getUsrId() {
		return usrId;
	}

	/**
	 * ユーザーＩＤのセット
	 * @param string
	 */
	public void setUsrId(String string) {
		usrId = string;
	}


	/**
	 * @return
	 */
	public ArrayList getPrsFksList(){
		return prsFksList;
	}

	/**
	 * @param list
	 */
	public void setPrsFksList(ArrayList prsFksList) {
		this.prsFksList = prsFksList;
	}

	/**
	 * @return
	 */
	public ArrayList getKbnList() {
		return kbnList;
	}

	/**
	 * @param list
	 */
	public void setKbnList(ArrayList kbnList) {
		this.kbnList = kbnList;
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

	/**
	 * @return
	 */
	public String getPrsFksChangeFlg() {
		return prsFksChangeFlg;
	}

	/**
	 * @param string
	 */
	public void setPrsFksChangeFlg(String string) {
		prsFksChangeFlg = string;
	}

}



