/**
* PrsOrderForm  プレス・副資材発注画面フォームクラス
*
*	作成日    2003/04/28
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
* 		2003/05/01（ＮＩＩ）蛭田 敬子
* 			・入力チェック追加
* 		2003/01/13（ＮＩＩ）森　さやか
* 			・納入先クリア追加に伴う修正
* 		2004/03/08　(ＮＩＩ) 森
* 			・新旧区分"その他"追加に伴う修正
*		2005/02/03（ＮＩＩ）蛭田
*			・場所コードの追加
*		2005/05/26（ＮＩＩ）蛭田
*			・発注数マイナス入力可に修正
*		2005/09/07（ＮＩＩ）蛭田
*			・副資材発注画面、副資材コードをプルダウン項目に変更（VAP社対応）
*		2005/09/21（ＮＩＩ）蛭田
*			・納期一括変更機能追加
*/

package com.jds.prodord.order.prsorder;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class PrsOrderForm extends ActionForm {

	public static final String COMMAND_HACHUSHIJI = "COMMAND_HACHUSHIJI";
	public static final String COMMAND_DENPYOHAKKOU = "COMMAND_DENPYOHAKKOU";
	public static final String COMMAND_PRSHACHU = "COMMAND_PRSHACHU";
	public static final String COMMAND_FKUSHIZAIHACHU = "COMMAND_FKUSHIZAIHACHU";
	public static final String COMMAND_KBNHENKO = "COMMAND_KBNHENKO";	
	public static final String COMMAND_NHNCLEAR = "COMMAND_NHNCLEAR";//2004.01.13 add
	public static final String COMMAND_NKICHANGE = "COMMAND_NKICHANGE";//2005.09.21 add
	
	public static final String ManualOrder = "ManualOrder";
	public static final String ManualOrderPaste = "ManualOrderPaste";
	public static final String IkkatsuRefer = "IkkatsuRefer";
	
	public static final String PRSHACHU = "0";
	public static final String FUKHACHU = "1";
	
	public static final String SUCCESS_HACHUSHIJI = "SUCCESS_HACHUSHIJI";
	public static final String SUCCESS_DENPYOHAKKOU = "SUCCESS_DENPYOHAKKOU";
	public static final String SUCCESS_KBNHENKO = "SUCCESS_KBNHENKO";
	public static final String SUCCESS_NHNCLEAR = "SUCCESS_NHNCLEAR";//2004.01.13 add
	public static final String SUCCESS_NKICHANGE = "SUCCESS_NKICHANGE";//2005.09.21 add
	
	public static final String COMMAND_CLEAR = "COMMAND_CLEAR";
	public static final String COMMAND_BACK = "COMMAND_BACK";
	
	public static final String NEXT = "NEXT";
	public static final String PREV = "PREV";
	public static final String SUCCESS_PREVNEXT = "SUCCESS_PREVNEXT";
	
//	public static final int MAX_ROW_COUNT = 5;
	public static final int MAX_ROW_COUNT = 100;
	
    private ArrayList details = new ArrayList();

	private String command = "";
    private String count = "";
    private String newWindow = "";//印刷用画面を開くかどうかのフラグ
    private String pre_page = "";//前のページ（どのページから遷移してきたか）
    private String prs_FukSgn = "";//プレス発注画面か副資材発注画面かのサイン
    
    private ArrayList check_prs1_index = new ArrayList();
    private ArrayList check_fuk1_index = new ArrayList();
    
    private int currentPage;//現在のページ
    private int disp_currentPage;//現在のページ（表示用）
    private int pageCount;//ページ数
    private int referredMaxPage;//参照された最大ページ
    private int allRowCount;//全ページの件数
    
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String bshCod = "";
	private String hyoKbn = "";
	
	private String kbn = "";
	
	public ArrayList fukSziList = new ArrayList(); //2005/09/07 add
	private String nki_Y ="";
	private String nki_M ="";
	private String nki_D ="";


	public PrsOrderForm(){
		super();
//System.out.println("コンストラクタ : "+command);
		this.removePrsOrderVO();
		this.setCommand("INIT");
	}
	
	

	//-----------------------------------------------------------
	//-----------------------------------------------------------
	//バリューオブジェクトの格納
	private ArrayList voList = new ArrayList();
	
    public void setPrsOrderVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addPrsOrderVO(PrsOrderVO fmVO){

		voList.add(fmVO);
		
	}
	public ArrayList getPrsOrderVO(){
		ArrayList arr = new ArrayList();
		for(int i = 0; i < voList.size(); i++){
			arr.add(voList.get(i));
		}
		return arr;	
		
	}
    public PrsOrderVO getPrsOrderVO(int i){
		
		return (PrsOrderVO)voList.get(i);	
		
	}
	public void setPrsOrderVO(int i, PrsOrderVO fmVO){
    	
		voList.set(i, fmVO);
		
	}
	public void removePrsOrderVO(){
    	
		voList.clear();
		
	}	
	public void removePrsOrderVO(int i){
    	
		voList.remove(i);
		
	}
	

	//-----------------------------------------------------------フッター
	 //押したボタン
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		command = s;
	}
	
	//区分
	public String getKbn(){
		return kbn;
	}
	public void setKbn(String s){
		kbn = s;
	}


	//-----------------------------------------------------------見出し
	
     //代表会社識別コード
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
    }
    //会社識別コード
    public String getQueryKaiSkbCod(){
    	return queryKaiSkbCod;
    }
    public void setQueryKaiSkbCod(String s){
    	queryKaiSkbCod = s;
    }
    
    //会社識別コード
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList s){
    	queryKaiSkbCodList = s;
    }
//--------------------------------------------------------------------

    /**
     * 件数のセット
     */
    public void setCount(int i) {
        this.count = i+"";
    }
    
    /**
     * 件数の取得
     */
    public String getCount() {
        return this.count;
    }
    
    /**
     * 印刷用画面を開くかどうかのフラグ
     */
    public void setNewWindow(String s) {
        this.newWindow = s;
    }
    public String getNewWindow() {
        return this.newWindow;
    }
    
	/**
	 * pre_pageの取得
	 * 
	 * @return pre_page
	 */
	public String getPre_page() {
		
		return pre_page;
		
	}
	/**
	 * pre_pageのセット
	 * 
	 * @param pre_page
	 */
	public void setPre_page(String pre_page) {
		
		this.pre_page = pre_page;
		
	}
	
	/**
	 * プレス発注画面か副資材発注画面かのサインの取得
	 * 
	 * @param prs_FukSgn
	 */
	public String getPrs_FukSgn() {
		
		return prs_FukSgn;
		
	}
	/**
	 * プレス発注画面か副資材発注画面かのサインのセット
	 * 
	 * @param prs_FukSgn
	 */
	public void setPrs_FukSgn(String prs_FukSgn) {
		
		this.prs_FukSgn = prs_FukSgn;
		
	}

	/**
	 * @return
	 */
	public String getNki_Y() {
		return nki_Y;
	}

	/**
	 * @param string
	 */
	public void setNki_Y(String string) {
		nki_Y = string;
	}

	/**
	 * @return
	 */
	public String getNki_M() {
		return nki_M;
	}

	/**
	 * @param string
	 */
	public void setNki_M(String string) {
		nki_M = string;
	}

	/**
	 * @return
	 */
	public String getNki_D() {
		return nki_D;
	}

	/**
	 * @param string
	 */
	public void setNki_D(String string) {
		nki_D = string;
	}


	//--------------------------------------------------------------------


    /**
     * 現在のページのセット
     * 
     * @param currentPage 現在のページ
     */
    public void setCurrentPage(int currentPage) {


        this.currentPage = currentPage;


    }
    /**
     * 現在のページの取得
     * 
     * @return 現在のページ
     */
    public int getCurrentPage() {


        return this.currentPage;


    }
    
    /**
     * 現在のページのセット（表示用）
     * 
     * @param disp_currentPage 現在のページ（表示用）
     */
    public void setDisp_currentPage(int disp_currentPage) {


        this.disp_currentPage = disp_currentPage;


    }
    /**
     * 現在のページの取得（表示用）
     * 
     * @return 現在のページ（表示用）
     */
    public int getDisp_currentPage() {
		int i = this.currentPage + 1;
        return i;


    }
    /**
     * ページ数のセット
     * 
     * @param pageCount ページ数
     */
    public void setPageCount(int pageCount) {


        this.pageCount = pageCount;


    }
    /**
     * ページ数の取得
     * 
     * @return ページ数
     */
    public int getPageCount() {


        return this.pageCount;


    }
    
    /**
     * 参照された最大ページのセット
     * 
     * @param referredMaxPage 参照された最大ページ
     */
    public void setReferredMaxPage(int referredMaxPage) {
		
		if(this.referredMaxPage < referredMaxPage)
        	this.referredMaxPage = referredMaxPage;

    }
    public void clearReferredMaxPage() {

        	this.referredMaxPage = 0;

    }
    /**
     * 参照された最大ページの取得
     * 
     * @return 参照された最大ページ
     */
    public int getReferredMaxPage() {


        return this.referredMaxPage;


    }
     /**
     * 全ページの件数のセット
     * 
     * @param allRowCount 全ページの件数
     */
    public void setAllRowCount(int allRowCount) {


        this.allRowCount = allRowCount;


    }
    /**
     * 全ページの件数の取得
     * 
     * @return 全ページの件数
     */
    public int getAllRowCount() {


        return this.allRowCount;


    }
    /**
     * VOのリスト（ページ単位）の取得
     * 
     * @return VOのリスト（ページ単位）
     */
    public PrsOrderVO[] getVosList(int i) {
    	ArrayList arr = null;
    	try{
//System.out.println("i * MAX_ROW_COUNT : (i + 1) * MAX_ROW_COUNT  "+i * MAX_ROW_COUNT+" : "+(i + 1) * MAX_ROW_COUNT);
//System.out.println("voList.size() : "+voList.size());
			int max = (voList.size() < ((i + 1) * MAX_ROW_COUNT))? voList.size() : (i + 1) * MAX_ROW_COUNT;

			arr = new ArrayList(this.voList.subList(i * MAX_ROW_COUNT, max));
//System.out.println("arr.size() : "+arr.size());
    	}catch(IllegalArgumentException e){
			return null;
		}
        return (PrsOrderVO[])arr.toArray(new PrsOrderVO[arr.size()]);
    }
    
	
	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
	
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();			
		  for(int k=0; k<j; k++){
		    details.add(new PrsOrderFormRow());			  	
		  }						
		}else if(size < details.size()){
			for(int i = details.size();i > size;i--){
				details.remove((i-1));
			}
		}
	}

	
	public PrsOrderFormRow getFormRow_R(int i){
		PrsOrderFormRow fr = (PrsOrderFormRow)details.get(i);
		if(fr == null){
			fr = new PrsOrderFormRow();
			details.set(i,fr);
		}
		return fr;
	}
	

	public void clearTableField(){
		for(int i = 0;i < details.size();i++){
			PrsOrderFormRow fr = (PrsOrderFormRow)details.get(i);
			if(fr != null)
	            details.set(i,new PrsOrderFormRow());			
		}	
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
	 * 表示区分の取得
	 * @return
	 */
	public String getHyoKbn() {
		return hyoKbn;
	}

	/**
	 * 表示区分のセット
	 * @param string
	 */
	public void setHyoKbn(String string) {
		hyoKbn = string;
	}


	public void clearAll(){
		command = "";
		queryDaiKaiSkbCod = "";
		clearTableField();		
	}
		
	public ArrayList getFormRowList_R(){
		return details;
	}
	
	public void setFormRowList_R(ArrayList arr){
		details = arr;
	}
	
	public void removeAllRow(){
		details.clear();
	}
		
	//チェックボックスをリセットするメソッド
	public void setCheck_false(){
		for(int i = 0; i < this.getSize();i++){
			PrsOrderFormRow row  = this.getFormRow_R(i);
			row.setCheck_prs1(false);
			row.setCheck_fuk1(false);
		}
	}
	
	//チェックされているチェックボックスのインデックス
	
	public ArrayList getCheck_prs1_index(){
		return check_prs1_index;
	}
	public void setCheck_prs1_index(ArrayList arr){
		check_prs1_index = arr;
	}
	
	public ArrayList getCheck_fuk1_index(){
		return check_fuk1_index;
	}
	public void setCheck_fuk1_index(ArrayList arr){
		check_fuk1_index = arr;
	}
	
	public void setCheck_index(ArrayList arr1,ArrayList arr2){
		check_prs1_index = arr1;
		check_fuk1_index = arr2;
	}
	
	/**
	 * 副資材コードプルダウン項目取得
	 * @return
	 */
	public ArrayList getFukSziList() {
		return fukSziList;
	}

	/**
	 * 副資材コードプルダウン項目設定
	 * @param list
	 */
	public void setFukSziList(ArrayList list) {
		fukSziList = list;
	}

	/**
	 * 渡された副資材名称のラベルを取得して返します
	 */
	public String getFukSziNmOptionsLabel(String value){
		String ret = "";
		int i = 0;
		for (Iterator iter = getFukSziList().iterator(); iter.hasNext(); i++) {
			LabelValueBean lvb = (LabelValueBean) iter.next();
			if(lvb.getValue().equals(value)){
				ret = lvb.getLabel().substring(4);
				break;
			}
		}
		return ret;
	}
	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
//System.out.println("PrsOrderForm--> validate");
		HttpSession session = req.getSession();
		//メッセージをセッションから取り除く
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;
		if(command.equals(COMMAND_CLEAR))
			return null;
			
	
		ActionErrors errors = new ActionErrors();

		//発注指示 または 伝票発行 のとき			
		if(command.equals(COMMAND_HACHUSHIJI)||command.equals(COMMAND_DENPYOHAKKOU)){
			//更新権限チェック
			boolean authorityChecked = false;
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","このアカウントで","登録／更新／削除が"));
				return errors;	
			}
			//まだ参照されていないページがあったら
			if(this.referredMaxPage < this.voList.size() / MAX_ROW_COUNT){
				errors.add("",new ActionError("errors.page.notreferredto"));
				return errors;
			}
		}
		//発注指示 または 伝票発行 または 前100件次100件 のとき			
		if(command.equals(COMMAND_HACHUSHIJI)||command.equals(COMMAND_DENPYOHAKKOU)
		||command.equals(NEXT)||command.equals(PREV)){

			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
			
			for(int i = 0; i < this.getSize();i++){
				PrsOrderFormRow row  = this.getFormRow_R(i);
				
				if(row == null)
					continue;
				
				if(row.getCheck_prs1()){
//System.out.println("入力チェック");
					/** 副資材コード **/	//2004.03.02 add
  					if(this.getPrs_FukSgn().equals(PrsOrderForm.FUKHACHU)){
						if(row.getFukSziCod().trim().equals("")){
							//付属品構成マスタにデータがあるときのみ
							errors.add("",new ActionError("errors.regist_row",String.valueOf(i+1),"副資材コード"));
							continue;
						}
					}

					/** 発注数 **/
					if(row.getPrsHacSuu().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"発注数"));
					}else{			
//						//半角数字のみ
//						if(!StringUtils.isAsciiDigit(row.getPrsHacSuu().trim())){
//							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"発注数","半角数字"));
//						}
						//発注数マイナス入力可に修正 2005/05/26 add
						try{
							if(!StringUtils.isAscii(row.getPrsHacSuu().trim()))
								throw new NumberFormatException();
							int hacSuu = Integer.parseInt(row.getPrsHacSuu().trim());
						}catch (NumberFormatException e) {
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"プレス発注数","半角数字"));
						}
					}					

					/** 納期 **/
					if(row.getPrsNkiYear().trim().equals("") || row.getPrsNkiMonth().trim().equals("") || row.getPrsNkiDay().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"納期"));
					}else{			
						//日付のみ
						if(!CheckCommon.validateAsDate(row.getPrsNkiYear(),row.getPrsNkiMonth(),row.getPrsNkiDay())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"納期","日付"));
						}
//						else{
//							int prsnki = Integer.parseInt(row.getPrsNkiYear().trim() + 
//									   	 StringUtils.lpad(row.getPrsNkiMonth().trim(),2,"0") + 
//										 StringUtils.lpad(row.getPrsNkiDay().trim(),2,"0"));
//							if(prsnki < today){
//								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"納期","当日以降の日付"));
//							}
//						}2003/07/16 チェックを外す
					}
					/** 発注先 **/
					if(row.getPrsMkrCod().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"発注先"));
					}else{			
						//半角英数のみ
						if(!StringUtils.isAscii(row.getPrsMkrCod().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"発注先","半角英数"));
						}
					}
					/** 納品先 **/
					if(!row.getNhnMei().trim().equals("")){
						//全角のみ
						if(StringUtils.containsAsciiOrHarfWidthKatakana(row.getNhnMei().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"納品先","全角"));
						}
					}
					/** コメント **/
					if(!row.getComment().trim().equals("")){
						//全角のみ
						if(StringUtils.containsAsciiOrHarfWidthKatakana(row.getComment().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"コメント","全角"));
						}
					}
				}
						
			}
			
//			//処理対象指定なし
//			boolean executeChecked = false;
//			
//			if(command.equals(COMMAND_HACHUSHIJI) || command.equals(COMMAND_DENPYOHAKKOU)){
//					for(int i = 0; i < this.getSize();i++){
//						PrsOrderFormRow row  = this.getFormRow_R(i);
//						if(row != null && row.getCheck_prs1()){
//						executeChecked = true;
//							break;
//						}
//					}
//			}else if(command.equals(NEXT)||command.equals(PREV))//次100件前100件
//				executeChecked = true;
//				
//			if(!executeChecked){
//				errors.add("",new ActionError("errors.input.required","処理対象"));
//			}
//			

		}
//		2004.03.08 add
		if(command.equals(COMMAND_KBNHENKO) && (this.getPrs_FukSgn().equals("0"))){
			if(this.getKbn().equals("5")){
				errors.add("",new ActionError("errors.input.prohibited","プレス発注の場合、区分のその他"));
				return errors;
			}
		}		
		if(command.equals(COMMAND_PRSHACHU)){
			if(this.getKbn().equals("5")){
				errors.add("",new ActionError("errors.input.prohibited","プレス発注の場合、区分のその他"));
				return errors;
			}
			PrsOrderLeftFrame leftFrame = (PrsOrderLeftFrame)session.getAttribute(CommonConst.BEAN_NAME_PRSORDER_LEFT);
			PrsOrderLeftRow formRow_L = leftFrame.getFormRow_L(0);
			if(formRow_L.getKbn().equals("その他")){
				errors.add("",new ActionError("errors.input.prohibited","プレス発注の場合、区分のその他"));
				return errors;			
			}		
		}		
		
		return errors.empty() ? null : errors;
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
//System.out.println("reset : "+command);			
		if(!this.command.equals(SUCCESS_DENPYOHAKKOU))
			this.setNewWindow("0");
		if(!this.command.equals(SUCCESS_HACHUSHIJI) 
		&& !this.command.equals(SUCCESS_DENPYOHAKKOU)
//		2004.01.13 add
//		&& !this.command.equals(this.SUCCESS_PREVNEXT) && !this.command.equals(this.SUCCESS_KBNHENKO))
		&& !this.command.equals(SUCCESS_PREVNEXT) 
		&& !this.command.equals(SUCCESS_KBNHENKO) 
		&& !this.command.equals(SUCCESS_NHNCLEAR)
		&& !this.command.equals(SUCCESS_NKICHANGE)) //2005/09/21 add
			this.command = "INIT";
		this.setCheck_false();//チェックボックスをリセット
	}
	


}

