/**
* IkkatsuReferForm  一括照会画面フォームクラス
*
*	作成日    2003/03/31
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
* 		2003/09/10（ＮＩＩ）岡田 夏美
* 			・プレス発注数が副資材在庫＋副資材未入庫計より大きかった場合のエラーの表示方法を変更
* 			（行ごとでなく、全体で一つのメッセージ。エラーのあった行のセルを赤くする）
* 		2005/05/23（ＮＩＩ）蛭田
* 			・発注書一括出力対応（ユーザーＩＤの追加）
* 			・Ｋ社の場合、副資材在庫数のチェックを外すように変更
* 			・発注数マイナス入力可に修正
*/

package com.jds.prodord.reference.ikkatsurefer;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;
import com.jds.prodord.indicate.teiansuurefer.*;
public class IkkatsuReferForm extends ActionForm {

	public static final String COMMAND_PRSCOMMENT = "COMMAND_PRSCOMMENT";
	public static final String COMMAND_HACHUSHIJI = "COMMAND_HACHUSHIJI";
	public static final String COMMAND_DENPYOHAKKOU = "COMMAND_DENPYOHAKKOU";
	public static final String COMMAND_FKUSHIZAIHACHU = "COMMAND_FKUSHIZAIHACHU";
	
	public static final String COMMAND_TEIANSYOKAI = TeiansuuReferForm.COMMAND_TEIANSYOKAI;
	public static final String COMMAND_JIDOUHACHU = TeiansuuReferForm.COMMAND_JIDOUHACHU;
	
	public static final String SUCCESS_HACHUSHIJI = "SUCCESS_HACHUSHIJI";
	public static final String SUCCESS_DENPYOHAKKOU = "SUCCESS_DENPYOHAKKOU";
	public static final String COMMAND_CLEAR = "COMMAND_CLEAR";
	
	public static final String NEXT = "NEXT";
	public static final String PREV = "PREV";
	public static final String SUCCESS_PREVNEXT = "SUCCESS_PREVNEXT";
	
	public static final String ZAIKO_JUBUN = "在庫十分";
	public static final String JIKAI_HACHU = "次回発注";
	public static final String HACHU_HITYO = "発注必要";
	
//	public static final int MAX_ROW_COUNT = 10;
	public static final int MAX_ROW_COUNT = 100;

    private ArrayList details = new ArrayList();

	private String command = "";
    private String count = "";
    private String newWindow = "";//印刷用画面を開くかどうかのフラグ
    private ArrayList check_prs1_index = new ArrayList();
    private ArrayList check_prs2_index = new ArrayList();
    private ArrayList check_fuk1_index = new ArrayList();
    private ArrayList check_prsHacSuu_index = new ArrayList();
    
    private int currentPage;//現在のページ
    private int disp_currentPage;//現在のページ（表示用）
    
    private int pageCount;//ページ数
    private int referredMaxPage;//参照された最大ページ
    private int allRowCount;//全ページの件数
    private ArrayList vosList = new ArrayList();//VOのリスト（ページごと）
    
    private HashMap ketCod_map = new HashMap();//形態コードと形態名称を格納
    
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
		
	private String hyoKbn = "";//2011/05/25 add

	
	public IkkatsuReferForm(){
		super();
//System.out.println("コンストラクタ : "+command);
		this.removeIkkatsuReferVO();
		this.setCommand("INIT");
	}
	
	
	//-----------------------------------------------------------
	//-----------------------------------------------------------
	//バリューオブジェクトの格納
	private ArrayList voList = new ArrayList();
	
    public void setIkkatsuReferVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addIkkatsuReferVO(IkkatsuReferVO fmVO){

		voList.add(fmVO);
		
	}
	public void setIkkatsuReferVO(int i, IkkatsuReferVO fmVO){
    	
		voList.set(i, fmVO);
		
	}
	public void setIkkatsuReferVO_all(int i, IkkatsuReferVO fmVO){
    	
		int pageIdx = i / MAX_ROW_COUNT;
		int rowIdx = i % MAX_ROW_COUNT;
//System.out.println(pageIdx+" : "+rowIdx);
		ArrayList pageVOs = (ArrayList)vosList.get(pageIdx);
		pageVOs.set(rowIdx,fmVO);

	}
	public void removeIkkatsuReferVO(){
    	
		voList.clear();
		
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
    //会社識別コード
    public String getQueryKaiSkbCod(){
    	return queryKaiSkbCod;
    }
    public void setQueryKaiSkbCod(String s){
    	queryKaiSkbCod = s;
    }
    //表示区分
    public String getHyoKbn(){
    	return hyoKbn;
    }
    public void setHyoKbn(String s){
    	hyoKbn = s;
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
    public static int getCurrentPage(int i) {


        return  i / MAX_ROW_COUNT;


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
     * VOのリスト（結果全体）のセット
     * 
     * @param vosList VOのリスト（結果全体）
     */
    public void setVosList(ArrayList vosList) {

        this.vosList = vosList;

    }
    /**
     * VOのリスト（結果全体）の取得
     * 
     * @return VOのリスト（結果全体）
     */
    public ArrayList getVosList() {

        return this.vosList;

    
    }
    /**
     * VOのリスト（ページ単位）の取得
     * 
     * @return VOのリスト（ページ単位）
     */
    public IkkatsuReferVO[] getVosList(int i) {
		ArrayList arr = (ArrayList)this.vosList.get(i);
        return (IkkatsuReferVO[])arr.toArray(new IkkatsuReferVO[arr.size()]);
    }
    /**
     * VOのリスト（ページ単位）の取得
     * 
     * @return VOのリスト（ページ単位）
     */
    public void setVosList(int i, ArrayList vos) {
		vosList.set(i, vos);
    }
    /**
     * 全ページのVOのリスト（結果全体）の取得
     * 
     * @return 全ページのVOのリスト（結果全体）
     */
    public ArrayList getAllVos() {
		ArrayList returnArr = new ArrayList();
		for(int i = 0; i < this.vosList.size(); i++){
			returnArr.addAll((Collection)vosList.get(i));
		}
        return returnArr;
    }
	/**
     * VOのリスト（結果全体）の削除
     * 
     * 
     */
    public void removeVosList() {

        this.vosList.clear();

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

	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
	
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();			
		  for(int k=0; k<j; k++){
		    details.add(new IkkatsuReferFormRow());			  	
		  }						
		}else if(size < details.size()){
			for(int i = details.size();i > size;i--){
				details.remove((i-1));
			}
		}
	}

	
	public IkkatsuReferFormRow getFormRow_R(int i){
		IkkatsuReferFormRow fr = (IkkatsuReferFormRow)details.get(i);
		if(fr == null){
			fr = new IkkatsuReferFormRow();
			details.set(i,fr);
		}
		return fr;
	}
	

	public void clearTableField(){
		for(int i = 0;i < details.size();i++){
			IkkatsuReferFormRow fr = (IkkatsuReferFormRow)details.get(i);
			if(fr != null)
	            details.set(i,new IkkatsuReferFormRow());			
		}	
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
			IkkatsuReferFormRow row  = this.getFormRow_R(i);
			row.setCheck_prs1(false);
			row.setCheck_prs2(false);
			row.setCheck_fuk1(false);
			row.setCheck_prsHacSuu(false);
		}
	}
	
	//チェックされているチェックボックスのインデックス
	
	public ArrayList getCheck_prs1_index(){
		return check_prs1_index;
	}
	public void setCheck_prs1_index(ArrayList arr){
		check_prs1_index = arr;
	}
	
	public ArrayList getCheck_prs2_index(){
		return check_prs2_index;
	}
	public void setCheck_prs2_index(ArrayList arr){
		check_prs2_index = arr;
	}
	
	public ArrayList getCheck_fuk1_index(){
		return check_fuk1_index;
	}
	public void setCheck_fuk1_index(ArrayList arr){
		check_fuk1_index = arr;
	}
	
	public ArrayList getCheck_prsHacSuu_index(){
		return check_prsHacSuu_index;
	}
	public void setCheck_prsHacSuu_index(ArrayList arr){
		check_prsHacSuu_index = arr;
	}
	
	public void setCheck_index(ArrayList arr1,ArrayList arr2,ArrayList arr3,ArrayList arr4){
		check_prs1_index = arr1;
		check_prs2_index = arr2;
		check_fuk1_index = arr3;
		check_prsHacSuu_index = arr4;
	}
	
	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
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
		}

		//発注指示 または 伝票発行 または プレスコメント入力 または 副資材発注 のとき	
		if(command.equals(COMMAND_HACHUSHIJI)||command.equals(COMMAND_DENPYOHAKKOU)||command.equals(COMMAND_PRSCOMMENT)||command.equals(COMMAND_FKUSHIZAIHACHU)){
			if(this.referredMaxPage < this.vosList.size()-1){//まだ参照されていないページがあったら
				errors.add("",new ActionError("errors.page.notreferredto"));
				return errors;
			}
		}

		//発注指示 または 伝票発行 または プレスコメント入力 または 次100件前100件 のとき				
		if(command.equals(COMMAND_HACHUSHIJI)||command.equals(COMMAND_DENPYOHAKKOU)||command.equals(COMMAND_PRSCOMMENT)
		 ||command.equals(NEXT)||command.equals(PREV)){
		 	

			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
			
			boolean check_prs;
			boolean check_fuk;
			
			boolean prsHacSuu_err;
			boolean prsHacSuu_err2 = false;
			
			for(int i = 0; i < this.getSize();i++){
				IkkatsuReferFormRow row  = this.getFormRow_R(i);
				check_prs = false;
				check_fuk = false;
				prsHacSuu_err = false;
				
				if(row == null)
					continue;
				if(command.equals(COMMAND_HACHUSHIJI)){
					if(row.getCheck_prs1())
						check_prs = true;
				}else if(command.equals(COMMAND_DENPYOHAKKOU)){
					if(row.getCheck_prs2())
						check_prs = true;
				 //2003/05/08 ｢プレスコメント入力｣のとき
				}else if(command.equals(COMMAND_PRSCOMMENT)||command.equals(NEXT)||command.equals(PREV)){
					if(row.getCheck_prs1()||row.getCheck_prs2())
						check_prs = true;
				}

				if(check_prs){
					//プレス発注数
					if(row.getPrsHacSuu().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"プレス発注数"));
						prsHacSuu_err = true;
					}else{
//						//半角数字のみ
//						if(!StringUtils.isAsciiDigit(row.getPrsHacSuu().trim())){
						//発注数マイナス入力可に修正 2005/05/24 add
						try{
							if(!StringUtils.isAscii(row.getPrsHacSuu().trim()))
								throw new NumberFormatException();
							int hacSuu = Integer.parseInt(row.getPrsHacSuu().trim());
						}catch (NumberFormatException e) {
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"プレス発注数","半角数字"));
							prsHacSuu_err = true;
						}
					}
					//プレス納期
					if(row.getPrsNkiYear().trim().equals("") || row.getPrsNkiMonth().trim().equals("") || row.getPrsNkiDay().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"プレス納期"));
					}else{			
						//日付のみ
						if(!CheckCommon.validateAsDate(row.getPrsNkiYear(),row.getPrsNkiMonth(),row.getPrsNkiDay())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"プレス納期","日付"));
						}
//						else{
//							int prsnki = Integer.parseInt(row.getPrsNkiYear().trim() + 
//									   	 StringUtils.lpad(row.getPrsNkiMonth().trim(),2,"0") + 
//										 StringUtils.lpad(row.getPrsNkiDay().trim(),2,"0"));
//							if(prsnki < today){
//								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"プレス納期","当日以降の日付"));
//							}
//						}2003/07/16 チェックを外す
					}
					//プレス発注先
					if(row.getPrsMkrCod().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"プレス発注先"));
					}else{			
						//半角英数のみ
						if(!StringUtils.isAscii(row.getPrsMkrCod().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"プレス発注先","半角英数"));
						}
					}
					row.setHacSuu_errSgn(false);
					if(!queryDaiKaiSkbCod.equals(CommonConst.KAICOD_K)){ //Ｋ社の場合、副資材在庫数のチェックはしない 2005/05/24 add					
						//強制発注用チェックボックスがチェックされていなかったら
						if(!row.getCheck_prsHacSuu()){
							if(!prsHacSuu_err){
								int prsHacSuu = (!row.getPrsHacSuu().trim().equals(""))?Integer.parseInt(row.getPrsHacSuu().trim()):0;
								int fukZaiSuu = (!row.getFukZaiSuu().equals(""))? Integer.parseInt(row.getFukZaiSuu()) : 0;
								int fukMnyKei = (!row.getFukMnyKei().equals(""))? Integer.parseInt(row.getFukMnyKei()) : 0;
								//プレス発注数が副資材在庫＋副資材未入庫計より大きかったら
								if(prsHacSuu > (fukZaiSuu + fukMnyKei)){
		//								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"プレス発注数","副資材在庫数以下の数字"));
									row.setHacSuu_errSgn(true);
									prsHacSuu_err2 = true;
								}
							}
						}
					}
				}		
			}
			if(prsHacSuu_err2)
				errors.add("",new ActionError("errors.input.1","プレス発注数","副資材在庫数以下"));
		}
		
		
		return errors.empty() ? null : errors;
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){		
		if(!this.command.equals(SUCCESS_DENPYOHAKKOU))
			this.setNewWindow("0");
		if(!this.command.equals(SUCCESS_HACHUSHIJI) 
		&& !this.command.equals(SUCCESS_DENPYOHAKKOU)
		&& !this.command.equals(SUCCESS_PREVNEXT))
			this.command = "INIT";
		this.setCheck_false();//チェックボックスをリセット
	}

}