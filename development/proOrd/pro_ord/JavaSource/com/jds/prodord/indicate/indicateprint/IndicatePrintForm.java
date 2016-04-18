/**
* IndicatePrintForm  発注書出力指示フォームクラス
*
*	作成日    2003/04/23
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
*       2003/06/05（ＮＩＩ）蛭田 敬子
* 			・「再出力」の条件項目（発注書日付、発注先コード）追加。
*       2003/07/16（ＮＩＩ）岡田 夏美
* 			・発注書日付Fromに当日日付をセットするメソッド 追加。
* 		2005/05/23（ＮＩＩ）蛭田
* 			・発注書一括出力対応（ユーザーＩＤの追加）
* 
*/

package com.jds.prodord.indicate.indicateprint;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

import java.text.*;

public class IndicatePrintForm extends ActionForm {

	public static final String COMMAND_CLEAR = "クリア";
	public static final String COMMAND_DENPYOHAKKOU = "伝票発行";
	public static final String COMMAND_SAISYRYK = "再出力";
	
	public static final String SUCCESS_DENPYOHAKKOU = "SUCCESS_DENPYOHAKKOU";
	public static final String SUCCESS_SAISYRYK = "SUCCESS_SAISYRYK";
	
	public static final String DENPYOHAKKOU = "1";
	public static final String SAISYRYK = "2";
	
	public static final boolean SELECT_ALL = true;
	public static final boolean SELECT_SEL = false;
	
	private String command = "";
	private String newWindow = "";//印刷用画面を開くかどうかのフラグ
	private String syoriFlg = "";//コマンド再セットのためのフラグ
	
	private boolean rb_select = true;
	
	private String hacCod1 = "";
	private String hacCod2 = "";
	private String hacCod3 = "";
	
	private String hacSyoDte_Frm_Y = "";
    private String hacSyoDte_Frm_M = "";
    private String hacSyoDte_Frm_D = "";
    private String hacSyoDte_to_Y = "";
    private String hacSyoDte_to_M = "";
    private String hacSyoDte_to_D = "";
    private String hacCod = "";

	private String hacSyoBng_frm = "";
	private String hacSyoBng_to = "";
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String usrId = "";//2005/05/23 add
	private String hyoKbn = "";//2011/05/30 add
	
	public IndicatePrintForm(){
		super();
		this.clearAll();
		this.setRb_select(SELECT_ALL);
		this.setCommand("INIT");
	}
		

	//-----------------------------------------------------------
	 //押したボタン
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		command = s;
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
     * コマンド再セットのためのフラグ
     */
    public void setSyoriFlg(String s) {
        this.syoriFlg = s;
    }
    public String getSyoriFlg() {
        return this.syoriFlg;
    }
	//-----------------------------------------------------------
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

	//2003/06/04
    /**
     * 発注書日付(年)Fromのセット
     * 
     * @param hacSyoDte_Frm_Y 発注書日付(年)From
     */
    public void setHacSyoDte_Frm_Y(String hacSyoDte_Frm_Y) {
        this.hacSyoDte_Frm_Y = hacSyoDte_Frm_Y;
    }
    /**
     * 発注書日付(年)Fromの取得
     * 
     * @return 発注日(年)From
     */
    public String getHacSyoDte_Frm_Y() {
        return this.hacSyoDte_Frm_Y;
    }
    /**
     * 発注書日付(月)Fromのセット
     * 
     * @param hacSyoDte_Frm_M 発注書日付(月)From
     */
    public void setHacSyoDte_Frm_M(String hacSyoDte_Frm_M) {
        this.hacSyoDte_Frm_M = hacSyoDte_Frm_M;
    }
    /**
     * 発注書日付(月)Fromの取得
     * 
     * @return 発注書日付(月)From
     */
    public String getHacSyoDte_Frm_M() {
        return this.hacSyoDte_Frm_M;
    }
    /**
     * 発注書日付(日)Fromのセット
     * 
     * @param hacSyoDte_Frm_D 発注日(日)From
     */
    public void setHacSyoDte_Frm_D(String hacSyoDte_Frm_D) {
        this.hacSyoDte_Frm_D = hacSyoDte_Frm_D;
    }
    /**
     * 発注書日付(日)Fromの取得
     * 
     * @return 発注書日付(日)From
     */
    public String getHacSyoDte_Frm_D() {
        return this.hacSyoDte_Frm_D;
    }
    /**
     * 発注書日付(年)Toのセット
     * 
     * @param queryHacDteTo_Y 発注書日付(年)To
     */
    public void setHacSyoDte_to_Y(String hacSyoDte_to_Y) {
        this.hacSyoDte_to_Y = hacSyoDte_to_Y;
    }
    /**
     * 発注書日付(年)Toの取得
     * 
     * @return 発注書日付(年)To
     */
    public String getHacSyoDte_to_Y() {
        return this.hacSyoDte_to_Y;
    }
    /**
     * 発注書日付(月)Toのセット
     * 
     * @param queryHacDteTo_M 発注書日付(月)To
     */
    public void setHacSyoDte_to_M(String hacSyoDte_to_M) {
        this.hacSyoDte_to_M = hacSyoDte_to_M;
    }
    /**
     * 発注書日付(月)Toの取得
     * 
     * @return 発注書日付(月)To
     */
    public String getHacSyoDte_to_M() {
        return this.hacSyoDte_to_M;
    }
    /**
     * 発注書日付(日)Toのセット
     * 
     * @param queryHacDteTo_D 発注書日付(日)To
     */
    public void setHacSyoDte_to_D(String hacSyoDte_to_D) {
        this.hacSyoDte_to_D = hacSyoDte_to_D;
    }
    /**
     * 発注書日付(日)Toの取得
     * 
     * @return 発注書日付(日)To
     */
    public String getHacSyoDte_to_D() {
        return this.hacSyoDte_to_D;
    }


	/**
     * 発注先コードのセット
     * 
     * @param hacCod 発注先コード
     */
    public void setHacCod(String hacCod) {
        this.hacCod = hacCod;
    }
    /**
     * 発注先コードの取得
     * 
     * @return 発注先コード
     */
    public String getHacCod() {
        return this.hacCod;
    }

    /**
     * 入力欄選択ラジオボタンのセット
     * 
     * @param sort_ketCod 入力欄選択ラジオボタン
     */
    public void setRb_select(boolean b) {

        this.rb_select = b;

    }
    /**
     * 入力欄選択ラジオボタンの取得
     * 
     * @return 入力欄選択ラジオボタン
     */
    public boolean getRb_select() {

        return this.rb_select;

    }
    
    /**
     * 発注先コード１のセット
     * 
     * @param hacCod1 発注先コード１
     */
    public void setHacCod1(String hacCod1) {

        this.hacCod1 = hacCod1;

    }


    /**
     * 発注先コード１の取得
     * 
     * @return 発注先コード１
     */
    public String getHacCod1() {

        return this.hacCod1;

    }


    /**
     * 発注先コード２のセット
     * 
     * @param hacCod2 発注先コード２
     */
    public void setHacCod2(String hacCod2) {

        this.hacCod2 = hacCod2;

    }


    /**
     * 発注先コード２の取得
     * 
     * @return 発注先コード２
     */
    public String getHacCod2() {

        return this.hacCod2;

    }


    /**
     * 発注先コード３のセット
     * 
     * @param hacCod3 発注先コード３
     */
    public void setHacCod3(String hacCod3) {

        this.hacCod3 = hacCod3;

    }


    /**
     * 発注先コード３の取得
     * 
     * @return 発注先コード３
     */
    public String getHacCod3() {

        return this.hacCod3;

    }


    /**
     * 発注書番号Fromのセット
     * 
     * @param hacSyoBng_frm 発注書番号From
     */
    public void setHacSyoBng_frm(String hacSyoBng_frm) {

        this.hacSyoBng_frm = hacSyoBng_frm;

    }


    /**
     * 発注書番号Fromの取得
     * 
     * @return 発注書番号From
     */
    public String getHacSyoBng_frm() {

        return this.hacSyoBng_frm;

    }


    /**
     * 発注書番号Toのセット
     * 
     * @param hacSyoBng_to 発注書番号To
     */
    public void setHacSyoBng_to(String hacSyoBng_to) {

        this.hacSyoBng_to = hacSyoBng_to;

    }


    /**
     * 発注書番号Toの取得
     * 
     * @return 発注書番号To
     */
    public String getHacSyoBng_to() {

        return this.hacSyoBng_to;

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


    //---------------------------------------------------------------------------------

	public void clearAll(){
		command = "";
		this.setRb_select(SELECT_ALL);
		this.hacCod1 = "";
		this.hacCod2 = "";
		this.hacCod3 = "";
	
		this.hacSyoBng_frm = "";
		this.hacSyoBng_to = "";

		this.hacSyoDte_Frm_Y = "";
    	this.hacSyoDte_Frm_M = "";
    	this.hacSyoDte_Frm_D = "";
    	this.hacSyoDte_to_Y = "";
    	this.hacSyoDte_to_M = "";
    	this.hacSyoDte_to_D = "";
    	this.hacCod = "";
  	}
	
	//---------------------------------------------------------------------------------

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		
		if(command.equals("INIT"))
			return null;
		if(command.equals(COMMAND_CLEAR))
			return null;
			
		//コマンド再セット	
		if(syoriFlg.equals(DENPYOHAKKOU) && !command.equals(SUCCESS_DENPYOHAKKOU))
			this.command = SUCCESS_DENPYOHAKKOU;
		if(syoriFlg.equals(SAISYRYK) && !command.equals(SUCCESS_SAISYRYK))
			this.command = SUCCESS_SAISYRYK;

		ActionErrors errors = new ActionErrors();
		
		//伝票発行・再出力のとき
		if(command.equals(COMMAND_DENPYOHAKKOU) || command.equals(COMMAND_SAISYRYK)){
			//更新権限チェック
			boolean authorityChecked = false;
			
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","このアカウントで","登録／更新／削除が"));
				return errors;	
			}
		}
		
		//伝票発行のとき
		if(command.equals(COMMAND_DENPYOHAKKOU)){
			
			if(rb_select == SELECT_ALL && !this.hacCod_IsBlank()){
				errors.add("",new ActionError("errors.input.prohibited","発注先コード"));
				return errors;
			}
			if(rb_select == SELECT_SEL){
				if(this.hacCod_IsBlank()){
					errors.add("",new ActionError("errors.input.required","発注先コード"));
				}else{
					if(!hacCod1.trim().equals("")){
						if(!StringUtils.isAscii(hacCod1.trim()))
							errors.add("",new ActionError("errors.input.1","発注先コード(1番目)","半角"));
					}
					if(!hacCod2.trim().equals("")){
						if(!StringUtils.isAscii(hacCod2.trim()))
							errors.add("",new ActionError("errors.input.1","発注先コード(2番目)","半角"));
					}
					if(!hacCod3.trim().equals("")){
						if(!StringUtils.isAscii(hacCod3.trim()))
							errors.add("",new ActionError("errors.input.1","発注先コード(3番目)","半角"));
					}
				}
			}
		}
		//再出力のとき
		if(command.equals(COMMAND_SAISYRYK)){
			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
			
			boolean isAsciiDigitErr = false;
			if(!hacSyoBng_frm.trim().equals("")){
				if(!StringUtils.isAsciiDigit(hacSyoBng_frm.trim())){
					errors.add("",new ActionError("errors.input.1","発注書番号(From)","半角数字"));
					isAsciiDigitErr = true;
				}
			}else{
				if(!hacSyoBng_to.trim().equals("")){
					errors.add("",new ActionError("errors.input.required","発注書番号(From)"));
				}
			}
			if(!hacSyoBng_to.trim().equals("")){
				if(!StringUtils.isAsciiDigit(hacSyoBng_to.trim())){
					errors.add("",new ActionError("errors.input.1","発注書番号(To)","半角数字"));
					isAsciiDigitErr = true;
				}else if(!hacSyoBng_frm.trim().equals("") && !isAsciiDigitErr){
					int frm = Integer.parseInt(hacSyoBng_frm.trim());
					int to = Integer.parseInt(hacSyoBng_to.trim());
					if(frm > to)
						errors.add("",new ActionError("errors.input.1","発注書番号(To)","発注書番号(From)以降の数字"));
				}
			}
				

			boolean hacDte_required = false;
	    	//Fromがどれかブランク
	    	if(hacSyoDte_Frm_Y.trim().equals("") || hacSyoDte_Frm_M.trim().equals("") || hacSyoDte_Frm_D.trim().equals("")){
				hacDte_required = true;
	    	    errors.add("",new ActionError("errors.input.required","発注書日付(From)"));
	    	}else{
	    		//半角
         		if((!StringUtils.isAscii(hacSyoDte_Frm_Y.trim()) && !hacSyoDte_Frm_Y.trim().equals("")) || 
	    		   (!StringUtils.isAscii(hacSyoDte_Frm_M.trim()) && !hacSyoDte_Frm_M.trim().equals("")) || 
        	       (!StringUtils.isAscii(hacSyoDte_Frm_D.trim()) && !hacSyoDte_Frm_D.trim().equals("")) ){
        				errors.add("",new ActionError("errors.input.1","発注書日付","半角"));
  	      		}else{

	    		//From日付型チェック
	    	   		if(!CheckCommon.validateAsDate(hacSyoDte_Frm_Y,hacSyoDte_Frm_M,hacSyoDte_Frm_D)){
						errors.add("",new ActionError("errors.input.1","発注書日付(From)","日付"));		
	    	   		}else{
						int hacdte_from = Integer.parseInt(hacSyoDte_Frm_Y.trim() + 
					   	 	StringUtils.lpad(hacSyoDte_Frm_M.trim(),2,"0") + 
						 	StringUtils.lpad(hacSyoDte_Frm_D.trim(),2,"0"));
							//当日以降Ｘ
				    		if(hacdte_from > today)
				    			errors.add("",new ActionError("errors.input.1","発注書日付","当日以前の日付"));
			   		}
        	    }
	        }
	        if(!(hacSyoDte_to_Y.trim().equals("") && hacSyoDte_to_M.trim().equals("") && hacSyoDte_to_D.trim().equals(""))){
	    		if(hacSyoDte_Frm_Y.trim().equals("") || hacSyoDte_Frm_M.trim().equals("") || hacSyoDte_Frm_D.trim().equals("")){
	    	    	if(!hacDte_required)
	    	    		errors.add("",new ActionError("errors.input.required","発注書日付(From)"));
	    		}
	    		if(hacSyoDte_to_Y.trim().equals("") || hacSyoDte_to_M.trim().equals("") || hacSyoDte_to_D.trim().equals("")){
	    	    	errors.add("",new ActionError("errors.input.required","発注書日付(To)"));
	    		}else{ 
	    		//To日付型チェック
	    	   		if(!CheckCommon.validateAsDate(hacSyoDte_to_Y,hacSyoDte_to_M,hacSyoDte_to_D)){
						errors.add("",new ActionError("errors.input.1","発注書日付(To)","日付"));		
	    	   		}else{
						int hacdte_to = Integer.parseInt(hacSyoDte_to_Y.trim() + 
					 	  	 StringUtils.lpad(hacSyoDte_to_M.trim(),2,"0") + 
							 StringUtils.lpad(hacSyoDte_to_D.trim(),2,"0"));
						//当日以降Ｘ
				  		if(hacdte_to > today)
				    		errors.add("",new ActionError("errors.input.1","発注書日付","当日以前の日付"));

						int hacdte_from = Integer.parseInt(hacSyoDte_Frm_Y.trim() + 
					   	 	StringUtils.lpad(hacSyoDte_Frm_M.trim(),2,"0") + 
						 	StringUtils.lpad(hacSyoDte_Frm_D.trim(),2,"0"));
						if(!hacSyoDte_Frm_Y.trim().equals("") && !hacSyoDte_Frm_M.trim().equals("") && !hacSyoDte_Frm_D.trim().equals("")){
							if(hacdte_to < hacdte_from)
				    			errors.add("",new ActionError("errors.input.incorrect","発注書日付の範囲指定"));
						}		 	
			 		}
	     	    }
	        }

			/** 発注先コード **/
			if(hacCod.trim().equals("")){
				errors.add("",new ActionError("errors.input.required","発注先コード"));
			}else{
    			//半角のみ
				if(!StringUtils.isAscii(hacCod.trim())){
					errors.add("",new ActionError("errors.input.1","発注先コード","半角"));
				}
			}
		}
		return errors.empty() ? null : errors;		
	}
	
//---------------------------------------------------------------------------------------------------
	
	public boolean hacCod_IsBlank(){
		if(hacCod1.trim().equals("") && hacCod2.trim().equals("") && hacCod3.trim().equals(""))
			return true;
		return false;
	}
	
	public void setHacSyoDte_Frm_today(){
		Date sysdate = new Date();
		
		hacSyoDte_Frm_Y = (new SimpleDateFormat("yy")).format(sysdate);
		hacSyoDte_Frm_M = (new SimpleDateFormat("MM")).format(sysdate);
		hacSyoDte_Frm_D = (new SimpleDateFormat("dd")).format(sysdate);
	}
	
//---------------------------------------------------------------------------------------------------
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		if(!this.command.equals(SUCCESS_DENPYOHAKKOU) 
		&& !this.command.equals(SUCCESS_SAISYRYK)){
			this.setNewWindow("0");
			this.setSyoriFlg("");
			this.command = "INIT";
		}
	}
	


}



