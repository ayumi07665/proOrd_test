/**
* ArticleNumberForm  品番マスターメンテナンスフォームクラス
*
*	作成日    2003/08/25
*	作成者    （ＮＩＩ）村上 博基
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
* 
* 	[変更履歴]
* 		 2003/10/07（ＮＩＩ）岡田  夏美
* 			・検索・登録時にFormにVOを保存、更新・削除時はそのVOを利用して
* 			  処理を行なうように変更
*/

package com.jds.prodord.master.articlenumber;

import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class ArticleNumberForm extends ActionForm {

	public static final String COMMAND_JITTKOU = "実行";
	public static final String COMMAND_CLEAR = "クリア";

	private String command = "";
	private String daiKaiSkbCod = "";
	private String queryDaiKaiSkbCod = "";
	private String[] kaiSkbCod = null;
	private ArrayList KaiSkbCodList = null;
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;

	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList vl_ketCod = new ArrayList();
	private ArrayList lb_ketCod = new ArrayList();

	private String prcKbn = "";
	private String kigoBan = "";
	private String artist = "";
	private String title = "";
	private String[] ketCod = null;

	private String year = "";
	private String month = "";
	private String day = "";	
	private String prsMkrCod = "";
	private String jytPrsMkr = "";
	private String hukSizCod = "";
	private String dbName = "";
	private boolean findedFlag = false;
	private String hyoKbn = "";

//=============================================================================

	public ArticleNumberForm(){
		super();
		this.setCommand("INIT");
	}
	
//----------------------------------------- 2003/10/07 add okada ---

	private ArticleNumberVO vo = new ArticleNumberVO();
	
	public ArticleNumberVO getArticleNumberVO(){
		return vo;
	}
	public void setArticleNumberVO(ArticleNumberVO vo){
		this.vo = vo;
	}
	public void removeArticleNumberVO(){
		vo = new ArticleNumberVO();
	}
	
//-------------------------------------------------------------------	
	


     //***  ボタンのget/set  ***
	public String getCommand(){
		return command;
	}
	public void setCommand(String s){
		this.command = s;
	}


     //***  代表会社識別コードのget/set  ***
    public String getDaiKaiSkbCod(){
    	return daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String s){
    	this.daiKaiSkbCod = s;
    }

     //***  会社識別コードのget/set  ***
    public String[] getKaiSkbCod(){
    	return kaiSkbCod;
    }
    public void setKaiSkbCod(String[] s){
    	this.kaiSkbCod = s;
    }

	//***  クエリ会社識別コードのget/set  ***
	public String getQueryKaiSkbCod() {
		return queryKaiSkbCod;
	}
	public void setQueryKaiSkbCod(String queryKaiSkbCod) {
		this.queryKaiSkbCod = queryKaiSkbCod;
	}

     //***  会社識別コードリストのget/set  ***
    public ArrayList getKaiSkbCodList(){
    	return KaiSkbCodList;
    }
    public void setKaiSkbCodList(ArrayList arr){
    	this.KaiSkbCodList = arr;
    }

	//***  クエリ会社識別コードリストのget/set  ***
	public ArrayList getQueryKaiSkbCodList() {
		return queryKaiSkbCodList;
	}
	public void setQueryKaiSkbCodList(ArrayList arr) {
		this.queryKaiSkbCodList = arr;
	}

	//***  Gets the vl_kaiSkbCod  ***
	public Collection getVl_kaiSkbCod() {
		return (Collection)vl_kaiSkbCod;
	}

	public void setKaiSkbCodOptions(ArrayList queryKaiSkbCodList){
		vl_kaiSkbCod.clear();
		for(int i=0; i<queryKaiSkbCodList.size();i++){
			vl_kaiSkbCod.add(queryKaiSkbCodList.get(i)+"");
		}
	}

	//***  Gets the vl_ketCod・lb_ketCod  ***
	public Collection getVl_ketCod() {
		return (Collection)vl_ketCod;
	}
	public Collection getLb_ketCod(){
		return (Collection)lb_ketCod;
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

	//*** 処理区分  のget/set  ***
	public String getPrcKbn() {
		return prcKbn;
	}
	public void setPrcKbn(String prcKbn) {
		this.prcKbn = prcKbn;
	}

     //***  記号番号のget/set  ***
    public String getKigoBan(){
    	return kigoBan;
    }
    public void setKigoBan(String s){
    	this.kigoBan = s;
    }

     //***  アーティストのget/set  ***
    public String getArtist(){
    	return artist;
    }
    public void setArtist(String s){
    	this.artist = s;
    } 
    
     //***  タイトルのget/set  ***
    public String getTitle(){
    	return title;
    }
    public void setTitle(String s){
    	this.title = s;
    }


     //***  形態コードのget/set  ***
    public String[] getKetCod(){
    	return ketCod;
    }
    public void setKetCod(String[] str){
    	this.ketCod = str;
    }


     //***  年のget/set  ***
    public String getYear(){
    	return year;
    }
    public void setYear(String s){
    	this.year = s;
    }

     //***  月のget/set  ***
    public String getMonth(){
    	return month;
    }
    public void setMonth(String s){
    	this.month = s;
    }

     //***  日のget/set  ***
    public String getDay(){
    	return day;
    }
    public void setDay(String s){
    	this.day = s;
    }

    //***  プレスメーカーコードのget/set  ***
    public String getPrsMkrCod(){
    	return prsMkrCod;
    }
    public void setPrsMkrCod(String s){
    	this.prsMkrCod = s;
    }
    //***  受託プレスメーカーコードのget/set  ***
    public String getJytPrsMkr(){
    	return jytPrsMkr;
    }
    public void setJytPrsMkr(String s){
    	this.jytPrsMkr = s;
    } 
    
    //***  副資材発注先コードのget/set  ***
    public String getHukSizCod(){
    	return hukSizCod;
    }
    public void setHukSizCod(String s){
    	this.hukSizCod = s;
    }

    //***  データベース名のget/set  ***
    public String getDbName(){
    	return dbName;
    }
    public void setDbName(String s){
		if(s.equals("HIN01")){
			this.dbName = "品番マスター";
		}else if(s.equals("HIN02")){
			this.dbName = "カタログ外品番";
		}else{
			this.dbName = "";			
		}
    }

	/**
	 * Gets the hidUpdDte
	 * @return Returns a int
	 */
/*	public int getHidUpdDte() {
		return hidUpdDte;
	}
	public void setHidUpdDte(int hidUpdDte) {
		this.hidUpdDte = hidUpdDte;
	}
*/

	/**
	 * Gets the hidUpdJkk
	 * @return Returns a int
	 */
/*	public int getHidUpdJkk() {
		return hidUpdJkk;
	}
	public void setHidUpdJkk(int hidUpdJkk) {
		this.hidUpdJkk = hidUpdJkk;
	}
*/

	//*** Gets the queryDaiKaiSkbCod  ***
	public String getQueryDaiKaiSkbCod() {
		return queryDaiKaiSkbCod;
	}
	public void setQueryDaiKaiSkbCod(String queryDaiKaiSkbCod) {
		this.queryDaiKaiSkbCod = queryDaiKaiSkbCod;
	}

	/**
	 * Gets the findedFlag
	 * @return Returns a boolean
	 */
	public boolean getFindedFlag() {
		return findedFlag;
	}
	public void setFindedFlag(boolean findedFlag) {
		this.findedFlag = findedFlag;
	}

	//*** 表示区分  のget/set  ***
	public String getHyoKbn() {
		return hyoKbn;
	}
	public void setHyoKbn(String s) {
		this.hyoKbn = s;
	}

//=====  値入力判定  ================================================================

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){

	HttpSession session = req.getSession();
	//メッセージをセッションから取り除く
	session.removeAttribute(CommonConst.INFOMSG_KEY);
	
	if(command.equals("INIT"))
		return null;
	
	ActionErrors errors = new ActionErrors();
		
	if(command.equals(COMMAND_JITTKOU)){
		//更新権限チェック
		if(!prcKbn.equals("1")){
			boolean authorityChecked = false;
			authorityChecked = CheckCommon.checkHyoKbn(this.getQueryDaiKaiSkbCod(),this.getHyoKbn());
			if(!authorityChecked){
				errors.add("",new ActionError("errors.cannot","このアカウントで","登録／更新／削除が"));
				return errors;	
			}
		}
		
		final int kubun = Integer.parseInt(prcKbn);

		switch(kubun){
//-----  (照会)  --------------------------------------------------------
			case 1:
				//記号番号
				if(kigoBan.trim().equals("")){
					errors.add("",new ActionError("errors.input.required","記号番号"));
				}else{
				//半角数字のみ
				if(!StringUtils.isAscii(kigoBan.trim()))
					errors.add("",new ActionError("errors.input.1","記号番号","半角英数"));
				}

				break;
//-----  (登録)  --------------------------------------------------------
			case 2:
				//記号番号
				if(kigoBan.trim().equals("")){
					errors.add("",new ActionError("errors.input.required","記号番号"));
				}else{
				//半角数字のみ
				if(!StringUtils.isAscii(kigoBan.trim()))
					errors.add("",new ActionError("errors.input.1","記号番号","半角英数"));
				}
				//アーティスト
				if(!artist.trim().equals("")){
					//全角のみ
					if(StringUtils.containsAsciiOrHarfWidthKatakana(artist.trim()))
						errors.add("",new ActionError("errors.input.1","アーティスト","全角"));
				}
				//タイトル
				if(!title.trim().equals("")){
					if(StringUtils.containsAsciiOrHarfWidthKatakana(title.trim()))
						errors.add("",new ActionError("errors.input.1","タイトル","全角"));
				}
				//発売日
				if(year.trim().equals("") || month.trim().equals("") || day.trim().equals("")){
					errors.add("",new ActionError("errors.input.required","発売日"));
				}else{
					if(!year.trim().equals("") && !month.trim().equals("") && !day.trim().equals("")){
						if(!CheckCommon.validateAsDate(year,month,day))
							errors.add("",new ActionError("errors.input.1","発売日","日付"));
					}		
				}
				//プレスメーカーコード
				if(!prsMkrCod.trim().equals("")){
					//半角数字のみ
					if(!StringUtils.isAscii(prsMkrCod.trim()))
						errors.add("",new ActionError("errors.input.1","プレスメーカーコード","半角英数"));
				}
				//受託プレスメーカーコード
				if(!jytPrsMkr.trim().equals("")){
					//半角数字のみ
					if(!StringUtils.isAscii(jytPrsMkr.trim()))
						errors.add("",new ActionError("errors.input.1","受託プレスメーカーコード","半角英数"));
				}
				//副資材発注先
				if(!hukSizCod.trim().equals("")){
					//半角数字のみ
					if(!StringUtils.isAscii(hukSizCod.trim()))
						errors.add("",new ActionError("errors.input.1","副資材発注先コード","半角英数"));
				}

				break;
//-----  (変更)  --------------------------------------------------------
			case 3:
				if(!this.getArticleNumberVO().getKaiSkbCod().equals(kaiSkbCod[0]))
					errors.add("",new ActionError("errors.update.notselected","更新"));
				else if(!this.getArticleNumberVO().getKigoBan().equals(DataFormatUtils.mk_srykig(kigoBan.trim())))
					errors.add("",new ActionError("errors.update.notselected","更新"));
				else{
					//記号番号
					if(kigoBan.trim().equals("")){
						errors.add("",new ActionError("errors.input.required","記号番号"));
					}else{
						//半角数字のみ
						if(!StringUtils.isAscii(kigoBan.trim()))
							errors.add("",new ActionError("errors.input.1","記号番号","半角英数"));
					}
					//アーティスト
					if(!artist.trim().equals("")){
						//全角のみ
						if(StringUtils.containsAsciiOrHarfWidthKatakana(artist.trim()))
							errors.add("",new ActionError("errors.input.1","アーティスト","全角"));
					}
					//タイトル
					if(!title.trim().equals("")){
						if(StringUtils.containsAsciiOrHarfWidthKatakana(title.trim()))
							errors.add("",new ActionError("errors.input.1","タイトル","全角"));
					}
					//発売日
					if(year.trim().equals("") || month.trim().equals("") || day.trim().equals("")){
						errors.add("",new ActionError("errors.input.required","発売日"));
					}else{
						if(!year.trim().equals("") && !month.trim().equals("") && !day.trim().equals("")){
							if(!CheckCommon.validateAsDate(year,month,day))
								errors.add("",new ActionError("errors.input.1","発売日","日付"));
						}
					}
					//プレスメーカーコード
					if(!prsMkrCod.trim().equals("")){
						//半角数字のみ
						if(!StringUtils.isAscii(prsMkrCod.trim()))
							errors.add("",new ActionError("errors.input.1","プレスメーカーコード","半角英数"));
					}
					//受託プレスメーカーコード
					if(!jytPrsMkr.trim().equals("")){
						//半角数字のみ
						if(!StringUtils.isAscii(jytPrsMkr.trim()))
							errors.add("",new ActionError("errors.input.1","受託プレスメーカーコード","半角英数"));
					}
					//副資材発注先
					if(!hukSizCod.trim().equals("")){
						//半角数字のみ
						if(!StringUtils.isAscii(hukSizCod.trim()))
							errors.add("",new ActionError("errors.input.1","副資材発注先コード","半角英数"));
					}
				}

				break;

//-----  (削除)  --------------------------------------------------------
			case 4:
				if(!this.getArticleNumberVO().getKaiSkbCod().equals(kaiSkbCod[0]))
					errors.add("",new ActionError("errors.update.notselected","更新"));
				else if(!this.getArticleNumberVO().getKigoBan().equals(DataFormatUtils.mk_srykig(kigoBan.trim())))
					errors.add("",new ActionError("errors.update.notselected","更新"));

				break;
//--------------------------------------------------------------------
			}  //switch終了
		}  //if(command.equals(this.COMMAND_JIKKOU)){終了
		return errors.empty() ? null : errors;
	}



	public void reset(ActionMapping maping,HttpServletRequest req){
			this.command = "INIT";
	}

//----------------------------------------------------------------------------------

	public void clearAll(){
		command = "";
		this.kaiSkbCod = null;
	    this.prcKbn = "";
        this.kigoBan = "";
        this.artist = "";
        this.title = "";
        this.ketCod = null;
		this.year = "";
		this.month = "";
		this.day = "";
	    this.prsMkrCod = "";
	    this.jytPrsMkr = "";
	    this.hukSizCod = "";
		this.dbName = "";
		this.findedFlag = false;
  	}

	public void clearTableField1(){
		command = "";
	    artist = "";
	    title = "";
	    ketCod = null;
		year = "";
		month = "";
		day = "";
	    prsMkrCod = "";
	    jytPrsMkr = "";
	    hukSizCod = "";
		dbName = "";
		findedFlag = false;
	}


}