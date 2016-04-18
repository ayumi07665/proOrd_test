/**
* OrderHistoryMidForm  発注履歴照会画面フォームクラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）蛭田 敬子
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
* 		2003/10/22（ＮＩＩ）岡田 夏美
* 			・訂正送信は未出力のみ → 訂正送信は未出力・出力済両方を対象とするように修正
* 		2004/01/20（ＮＩＩ）森
* 			・訂正伝票発行ボタン押下時、発注数を変更したらコメントを自動表示
* 		2004/01/22（ＮＩＩ）森
* 			・入庫取消ができるように変更
* 		2004/03/08 (ＮＩＩ) 森
* 			・新旧区分"その他"追加に伴う修正
* 		2005/05/25（ＮＩＩ）蛭田
* 			・納品先名が'ＪＡＲＥＤ'の場合、直送区分"0"の修正
* 			・発注数の入力チェック修正
*		2005/09/01（ＮＩＩ）蛭田
*			・明細に単価の項目追加
*		2007/12/25（ＮＩＩ）田中
* 			・マニュアル発注指示一括貼付　備考２追加対応
* 		2008/02/25（ＮＩＩ）田中
* 			・発注数に訂正なければ発注数＜入庫数でもエラーとしない
* 		2008/06/02（ＮＩＩ）田中
* 			・ＶＡＰ社　単価・金額の修正
*/

package com.jds.prodord.reference.orderhistory;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class OrderHistoryMidForm extends ActionForm {

	public static final String COMMAND_DOWNLOAD =  "COMMAND_DOWNLOAD";
	public static final String COMMAND_TEIDENHAKKOU = "COMMAND_TEIDENHAKKOU";
	public static final String COMMAND_TEISOU = "COMMAND_TEISOU";
	public static final String SUCCESS_TEIDENHAKKOU = "SUCCESS_TEIDENHAKKOU";
	
	public static final String SYRZMI = "出力済";
	public static final String MISYRYK = "未出力";
	
	public static final String PRS = "プレス";
	public static final String SZI = "副資材";
	
	public static final String NEXT = "NEXT";
	public static final String PREV = "PREV";
	
	public static final int MAX_ROW_COUNT = 100;//1ページに表示する件数
	public static final int MAX_REFER_COUNT = 1000;//最大検索件数

    private ArrayList details = new ArrayList();

	private String command = "";
    private String count = "";
    private String newWindow = "";//印刷用画面を開くかどうかのフラグ
    
	private String queryDaiKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String hyoKbn = ""; //add 2011/05/30
	
	private String teidenflg = "";
	
    private int currentPage;//現在のページ
    private int disp_currentPage;//現在のページ（表示用）
    private int pageCount;//ページ数
    private int allRowCount;//全ページの件数
    private ArrayList vosList = new ArrayList();//VOのリスト（ページごと）
    private ArrayList check_mid_index = new ArrayList();

	public OrderHistoryMidForm(){
		super();
		this.removeOrderHistoryVO();
		this.removeAllRow();
		this.setCommand("INIT");
	}
	
    //-----------------------------------------------------------
	//-----------------------------------------------------------
	//バリューオブジェクトの格納
	private ArrayList voList = new ArrayList();
	
    public void setOrderHistoryVO(ArrayList arr){
    	
    	voList.clear();
		voList.addAll(arr);
		
	}
	public void addOrderHistoryVO(OrderHistoryVO fmVO){

		voList.add(fmVO);
		
	}
	public ArrayList getOrderHistoryVO(){
		
		return voList;	
		
	}
	public void setOrderHistoryVO(int i, OrderHistoryVO fmVO){
    	
		voList.set(i, fmVO);
		
	}
    public OrderHistoryVO getOrderHistoryVO(int i){
		
		return (OrderHistoryVO)voList.get(i);	
		
	}
	public void removeOrderHistoryVO(){
    	
		voList.clear();
		
	}	
	public void removeOrderHistoryVO(int i){
    	
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

	//-----------------------------------------------------------見出し
	
     //代表会社識別コード
    public String getQueryDaiKaiSkbCod(){
    	return queryDaiKaiSkbCod;
    }
    public void setQueryDaiKaiSkbCod(String s){
    	queryDaiKaiSkbCod = s;
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
     * 訂正伝票発行setフラグ
	 *   １＝> SUCCESS_TEIDENHAKKOU をセット
     */
    public void setTeiDenflg(String s) {
        this.teidenflg = s;
    }
    public String getTeiDenflg() {
        return this.teidenflg;
    }
    
	//---------------------------------------------------------------------------------
	
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
    public String getDisp_currentPage() {
		int i = this.currentPage + 1;
        return i+"";

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
    public String getPageCount() {

        return this.pageCount+"";

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
    public String getAllRowCount() {

        return this.allRowCount+"";

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
	 * VOのリスト（結果全体）のクリア
	 */
	public void removeVosList() {

		this.vosList.clear();
    
	}
    
    /**
     * VOのリスト（ページ単位）の取得
     * 
     * @return VOのリスト（ページ単位）
     */
    public OrderHistoryVO[] getVosList(int i) {
		ArrayList arr = (ArrayList)this.vosList.get(i);
        return (OrderHistoryVO[])arr.toArray(new OrderHistoryVO[arr.size()]);

    
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

	//---------------------------------------------------------------------------------
	public int getSize(){
		return details.size();
	}
	
	public void setSize(int size){
		if(size > details.size()){
			int j = size - this.getSize();			
		  for(int k=0; k<j; k++){
		    details.add(new OrderHistoryFormRow());			  	
		  }						
		}else if(size < details.size()){
			for(int i = details.size();i > size;i--){
				details.remove((i-1));
			}
		}
	}

	
	public OrderHistoryFormRow getFormRow(int i){
		OrderHistoryFormRow fr = (OrderHistoryFormRow)details.get(i);
		if(fr == null){
			fr = new OrderHistoryFormRow();
			details.set(i,fr);
		}
		return fr;
	}
	

	public void clearTableField(){
		for(int i = 0;i < details.size();i++){
			OrderHistoryFormRow fr = (OrderHistoryFormRow)details.get(i);
			if(fr != null)
	            details.set(i,new OrderHistoryFormRow());			
		}	
	}
	


	public void clearAll(){
		command = "";
		queryDaiKaiSkbCod = "";
		clearTableField();		
	}
		
	public ArrayList getFormRowList(){
		return details;
	}
	
// 2004.01.19 add コメント自動表示のため
	public ArrayList getFormRowCloneList(){

		ArrayList arr = new ArrayList();		

		for(int i = 0; i < details.size();i++){
			arr.add(((OrderHistoryFormRow)details.get(i)).clone());
		}
		return arr;
	}
	
	public void setFormRowList(ArrayList arr){
		details = arr;
	}
	
	public void removeAllRow(){
		details.clear();
	}
		
	//チェックボックスをリセットするメソッド
	public void setCheck_false(){
		for(int i = 0; i < this.getSize();i++){
			OrderHistoryFormRow row  = this.getFormRow(i);
			row.setCheck_Mid(false);
		}
	}
	
	//チェックされているチェックボックスのインデックス
	
	public ArrayList getCheck_Mid_index(){
		return check_mid_index;
	}
	public void setCheck_Mid_index(ArrayList arr){
		check_mid_index = arr;
	}
	
	
	public void setCheck_index(ArrayList arr1){
		check_mid_index = arr1;
	}
	
	public void setOrderHistoryVO_all(int i, OrderHistoryVO fmVO){
    	
		int pageIdx = i / MAX_ROW_COUNT;
		int rowIdx = i % MAX_ROW_COUNT;
//System.out.println(pageIdx+" : "+rowIdx);
		ArrayList pageVOs = (ArrayList)vosList.get(pageIdx);
		pageVOs.set(rowIdx,fmVO);

	}
	
	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		HttpSession session = req.getSession();
		//メッセージをセッションから取り除く
		session.removeAttribute(CommonConst.INFOMSG_KEY);
		
		if(command.equals("INIT"))
			return null;
			
		//訂正伝票発行にする。
		if(this.teidenflg.equals("1")){
			this.command = SUCCESS_TEIDENHAKKOU;			
		}
		
		ActionErrors errors = new ActionErrors();
		//「訂正送信」＆｢訂正伝票発行｣  押下時			
		if(command.equals(COMMAND_TEISOU) || command.equals(COMMAND_TEIDENHAKKOU)){

			//更新権限チェック
			boolean authorityChecked = false;
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","このアカウントで","登録／更新／削除が"));
				return errors;	
			}

			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
			
			for(int i = 0; i < this.getSize();i++){
				OrderHistoryFormRow row  = this.getFormRow(i);
				if(row == null)
					continue;

				if(row.getCheck_Mid()){

					/** 備考２ 2007/12/21 add **/
					if(!row.getBiKou2().trim().equals("")){
						if (!StringUtils.isAsciiOrHarfWidthKatakana(row.getBiKou2())) 
							errors.add(""+i, new ActionError("errors.input.1_row",String.valueOf(i+1),"備考２","半角"));
					}

					/** 発注数 **/
					if(row.getHacSuu().trim().equals("")){
						errors.add("",new ActionError("errors.input.required_row",String.valueOf(i+1),"発注数"));
					}else{
						//2005/05/30 発注数マイナス入力可に修正
						try{
							if(!StringUtils.isAscii(row.getHacSuu().trim()))
								throw new NumberFormatException();
							int hacSuu = Integer.parseInt(row.getHacSuu().trim());
							
							//2004.01.22 add 入庫取消ができるように変更
							String bunCod = (row.getBunCod().equals("プレス"))? "0" : "";
							//bshCodから取得できるように修正 2005/05/25 add
							row.setCykkbn(this.getCykKbn(row.getNhnMeiKj(),bunCod,row.getBshCod()));
							
							int hacSuuOld = Integer.parseInt(row.getHacSuuOld().trim());
							int nyoSuu = Integer.parseInt(StringUtils.replace(row.getNyoSuu().trim(),",",""));

							//入庫数=0で発注数がプラスからマイナスに変わった場合 2005/06/03 add
							if(nyoSuu == 0 && hacSuuOld > 0 && hacSuu < 0){
								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"発注数","入庫数より大きい数"));
							}
							//入庫数<>0の場合
							if(nyoSuu != 0 && bunCod.equals("0") && row.getCykkbn().equals("0")){
//								if(hacSuu < nyoSuu)
								if(hacSuuOld != hacSuu && hacSuu < nyoSuu) //発注数の訂正がなければエラーにしないよう変更 2008/02/13
									errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"発注数","入庫数より大きい数"));
							}

						}catch (NumberFormatException e) {
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"発注数","半角数字"));
						}
					}
					
					/** 金額 2005/09/13 add **/
//					if(!row.getKin().trim().equals("")){
//						if(!StringUtils.isAsciiDigit(String.valueOf(row.getKin().trim())))
//							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"金額","半角数字"));
//					}

					/** 金額 2007/12/25 少数点以下入力可能へ変更 **/
					if(!row.getKin().trim().equals("")){
						if(!StringUtils.isFloat(String.valueOf(row.getKin().trim()),2))
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"金額","整数部9桁・小数部2桁"));
						if(!(Double.parseDouble(row.getKin().trim()) < 1000000000))
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"金額","整数部9桁・小数部2桁"));

						//ＶＡＰ社単価算出対応　金額／発注数が7桁を超えた場合
						if(row.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP)){
							double kin = Double.parseDouble(row.getKin().trim());
							int hacSuu = Integer.parseInt(row.getHacSuu().trim());
	
							if((kin / hacSuu) >= 10000000){
								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"金額／発注数","7桁以下"));
							}
						}
					}
					/** 単価 2008/03/07 add **/
					if(!row.getTan2().trim().equals("")){
						if(!StringUtils.isFloat(String.valueOf(row.getTan2().trim()),2))
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"単価","整数部7桁・小数部2桁"));
						if(!(Double.parseDouble(row.getTan2().trim()) < 10000000))
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"単価","整数部7桁・小数部2桁"));

						//ＶＡＰ社金額算出対応　単価＊発注数が9桁を超えた場合
						if(row.getDaiKaiSkbCod().equals(CommonConst.KAICOD_VAP)){
							double tan2 = Double.parseDouble(row.getTan2().trim());
							int hacSuu = Integer.parseInt(row.getHacSuu().trim());
	
							if((tan2 * hacSuu) >= 1000000000){
								errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"単価＊発注数","9桁以下"));
							}
						}
					}
					
					/** 納期 **/
					if(!row.getNkiYear().equals("")||!row.getNkiMonth().equals("")||!row.getNkiDay().equals("")){
						//日付型
						if(!CheckCommon.validateAsDate(row.getNkiYear(),row.getNkiMonth(),row.getNkiDay())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"納期","日付"));
						}
					}

					/** 納品先 **/
					if(!row.getNhnMeiKj().equals("")){
						//全角のみ
						if(StringUtils.containsAsciiOrHarfWidthKatakana(row.getNhnMeiKj().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"納品先","全角"));
						}
					}
					/** コメント **/
					if(!row.getCmt().equals("")){
						//全角のみ
						if(StringUtils.containsAsciiOrHarfWidthKatakana(row.getCmt().trim())){
							errors.add("",new ActionError("errors.input.1_row",String.valueOf(i+1),"コメント","全角"));
						}
					}
					/** 新旧区分 **/	//2004.03.08 add
					if((row.getBunCod().equals("プレス"))&&(row.getSinKyuKbn().equals("その他"))){
						errors.add("",new ActionError("errors.input.prohibited_row",String.valueOf(i+1),"プレスの場合、区分のその他"));
					}

					// ｢訂正伝票発行｣押下時、未出力は指定不可
					if(command.equals(COMMAND_TEIDENHAKKOU)){
						if(row.getSyrZmiSgn().equals(MISYRYK))
							errors.add("",new ActionError("errors.input.prohibited_row",String.valueOf(i+1),"訂正伝票発行時、未出力の行"));
					}
				}
			}
					
			//処理対象指定なし
			boolean executeChecked = false;
			if(command.equals(COMMAND_TEIDENHAKKOU)){
				for(int i = 0; i < this.getSize();i++){
					OrderHistoryFormRow row  = this.getFormRow(i);
						executeChecked = true;
				}
			}else if(command.equals(COMMAND_TEISOU)){
				for(int i = 0; i < this.getSize();i++){
					OrderHistoryFormRow row  = this.getFormRow(i);
						executeChecked = true;
				}
			}
			if(!executeChecked){
				errors.add("",new ActionError("errors.input.required","処理対象"));
			}
			
		}
		
		return errors.empty() ? null : errors;

	}
	
	
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		for(int i=0; i<this.getSize(); i++){
			getFormRow(i).setCheck_Mid(false);
		}
		if(!this.command.equals(SUCCESS_TEIDENHAKKOU)){
			this.setNewWindow("0");
			this.command = "INIT";
		}
	}
	
	//pageの初期化
	public void clear_page(){
		this.setCurrentPage(-1);
		this.setPageCount(0);
		this.setAllRowCount(0);
	}
	
	/** 
	 * %タカセ%または%ＪＡＲＥＤ% → 直送区分 = "0" それ以外 → 直送区分 = "1"
	 * 副資材発注のとき → 直送区分 = "0"
	 */
	private final String getCykKbn(String str,String bunCod,String bshCod){
		if(!bunCod.equals("0") && !bunCod.equals("")){//副資材発注のとき
			return	"0";
		}
		return DataFormatUtils.getCykKbn(str, bshCod);
	}


}

