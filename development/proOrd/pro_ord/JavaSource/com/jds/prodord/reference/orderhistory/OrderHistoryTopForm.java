/**
* OrderHistoryTopForm  発注履歴照会画面検索条件フォームクラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）蛭田 敬子
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
* 		2004/03/31	(NII)森
* 			・指定条件に入庫日を追加
* 		2005/05/25（ＮＩＩ）蛭田
* 			・場所コードの追加
* 		2005/09/16（ＮＩＩ）蛭田
* 			・条件項目に発注書順を追加
*/

package com.jds.prodord.reference.orderhistory;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class OrderHistoryTopForm extends ActionForm {

	public static final String COMMAND_JIKKOU = "実行";
	public static final String COMMAND_CLEAR = "クリア";
	
	public static final String PRCKBN_SEARCH = "1";
	public static final String PRCKBN_DOWNLOAD = "2";

    private ArrayList details = new ArrayList();

	private String command = "";
    
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod  = "";
	private ArrayList queryKaiSkbCodList = null;
	private String hyoKbn = "";
	private String bshCod = "";
	private String prcKbn = "";
	
	/**
     * 発注書選択ラジオボタン
     *   ＜未出力・出力済・ＡＬＬ＞
     */
    private String rb_HacSyo = "";
    

    /**
     * 分類コード選択ラジオボタン
     *   ＜プレス・資材・ＡＬＬ＞
     */
    private String rb_BunCod = "";

	/**
     * 入庫状況選択ラジオボタン
     *   ＜未入庫・ＡＬＬ＞
     */
    private String rb_Nyuko = "";
	

    /**
     * 発注先コード
     */
    private String queryHacCod = "";
    
    /**
     * 記号番号
     */
    private String queryKigBng = "";
    
    /**
     * 発売日
     */
    private String queryHbiDte_Y = "";
    private String queryHbiDte_M = "";
    private String queryHbiDte_D = "";
    
    /**
     * 発注書番号From&To
     */
    private boolean checkHacBng = true;
    private String queryHacBngFrm = "";
    private String queryHacBngTo = "";
    
    /**
     * 発注日From&To
     */
    private boolean checkHacDte = true;
    private String queryHacDteFrm_Y = "";
    private String queryHacDteFrm_M = "";
    private String queryHacDteFrm_D = "";
    private String queryHacDteTo_Y = "";
    private String queryHacDteTo_M = "";
    private String queryHacDteTo_D = "";
    
    /**
     * 納期From&To
     */
    private boolean checkNki = true;
    private String queryNkiFrm_Y = "";
    private String queryNkiFrm_M = "";
    private String queryNkiFrm_D = "";
    private String queryNkiTo_Y = "";
    private String queryNkiTo_M = "";
    private String queryNkiTo_D = "";
    
    /**
     * メーカー分類
     */
    private boolean checkMkrBun = true;
    private String[] mkrBun = null;
	private ArrayList vl_mkrBun = new ArrayList();
	
	public Collection getVl_mkrBun(){
		return (Collection)vl_mkrBun;
	}
	public void setMkrBunOptions(ArrayList mkrBunList){
		vl_mkrBun.clear();
		for(int i = 0; i<mkrBunList.size(); i++){
			vl_mkrBun.add(mkrBunList.get(i)+"");
		}
	}
	
	/**
     * 区分
     */
    private boolean checkKbn = true;
	private String kbn = "";
	
 	public OrderHistoryTopForm(){
		super();
//System.out.println("Topコンストラクタ : "+command);
		this.removeOrderHistoryVO();
		this.setCommand("INIT");
		clear_text();
 	}	

	/**
	 * 入庫日From&To
	 */
	private boolean checkNyoDte = true;
	private String queryNyoDteFrm_Y = "";
	private String queryNyoDteFrm_M = "";
	private String queryNyoDteFrm_D = "";
	private String queryNyoDteTo_Y = "";
	private String queryNyoDteTo_M = "";
	private String queryNyoDteTo_D = "";
 	
	/**
	 * 発注書順
	 */
	private boolean chkHacJun = true;

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

//--------------------------------------------------------------------
    /**
     * 発注書選択ラジオボタンのセット
     * 
     * @param choiceHacSyo 発注書選択ラジオボタン
     */
    public void setRb_HacSyo(String rb_HacSyo) {

        this.rb_HacSyo = rb_HacSyo;
    }


    /**
     * 発注書選択ラジオボタンの取得
     * 
     * @return 発注書選択ラジオボタン
     */
    public String getRb_HacSyo() {

        return this.rb_HacSyo;

    }


    /**
     * 分類コード選択ラジオボタンのセット
     * 
     * @param choiceBunCod 分類コード選択ラジオボタン
     */
    public void setRb_BunCod(String rb_BunCod) {

        this.rb_BunCod = rb_BunCod;

    }


    /**
     * 分類コード選択ラジオボタンの取得
     * 
     * @return 分類コード選択ラジオボタン
     */
    public String getRb_BunCod() {

        return this.rb_BunCod;

    }

	/**
     * 入庫状況選択ラジオボタンのセット
     * 
     * @param rb_Nyuko 入庫状況選択ラジオボタン
     */
    public void setRb_Nyuko(String rb_Nyuko) {

        this.rb_Nyuko = rb_Nyuko;

    }


    /**
     * 入庫状況選択ラジオボタンの取得
     * 
     * @return 入庫状況選択ラジオボタン
     */
    public String getRb_Nyuko() {

        return this.rb_Nyuko;

    }


    /**
     * 発注先コードのセット
     * 
     * @param queryHacCod 発注先コード
     */
    public void setQueryHacCod(String queryHacCod) {

        this.queryHacCod = queryHacCod;

    }


    /**
     * 発注先コードの取得
     * 
     * @return 発注先コード
     */
    public String getQueryHacCod() {

        return this.queryHacCod;

    }


    /**
     * 記号番号のセット
     * 
     * @param queryKigBng 記号番号
     */
    public void setQueryKigBng(String queryKigBng) {

        this.queryKigBng = queryKigBng;

    }


    /**
     * 記号番号の取得
     * 
     * @return 記号番号
     */
    public String getQueryKigBng() {

        return this.queryKigBng;

    }

//** 発売日 **//
    /**
     * 発売日(年)のセット
     * 
     * @param queryHbiDte_Y 発売日(年)
     */
    public void setQueryHbiDte_Y(String queryHbiDte_Y) {
        this.queryHbiDte_Y = queryHbiDte_Y;
    }
    /**
     * 発売日(年)の取得
     * 
     * @return 発売日(年)
     */
    public String getQueryHbiDte_Y() {
        return this.queryHbiDte_Y;
    }
    /**
     * 発売日(月)のセット
     * 
     * @param queryHbiDte_M 発売日(月)
     */
    public void setQueryHbiDte_M(String queryHbiDte_M) {
        this.queryHbiDte_M = queryHbiDte_M;
    }
    /**
     * 発売日(月)の取得
     * 
     * @return 発売日(月)
     */
    public String getQueryHbiDte_M() {
        return this.queryHbiDte_M;
    }
    /**
     * 発売日(日)のセット
     * 
     * @param queryHbiDte_D 発売日(日)
     */
    public void setQueryHbiDte_D(String queryHbiDte_D) {
        this.queryHbiDte_D = queryHbiDte_D;
    }
    /**
     * 発売日(日)の取得
     * 
     * @return 発売日(日)
     */
    public String getQueryHbiDte_D() {
        return this.queryHbiDte_D;
    }


//** 発注書番号 **//
    /**
     * 発注書番号チェックのセット
     * 
     * @param checkHacBng 発注書番号チェック
     */
    public void setCheckHacBng(boolean checkHacBng) {
        this.checkHacBng = checkHacBng;
    }
    /**
     * 発注書番号チェックの取得
     * 
     * @return 発注書番号チェック
     */
    public boolean getCheckHacBng() {
        return this.checkHacBng;
    }
    /**
     * 発注書番号Fromのセット
     * 
     * @param queryHacBngFrm 発注書番号From
     */
    public void setQueryHacBngFrm(String queryHacBngFrm) {
        this.queryHacBngFrm = queryHacBngFrm;
    }
    /**
     * 発注書番号Fromの取得
     * 
     * @return 発注書番号From
     */
    public String getQueryHacBngFrm() {
        return this.queryHacBngFrm;
    }
    /**
     * 発注書番号Toのセット
     * 
     * @param queryHacBngTo 発注書番号To
     */
    public void setQueryHacBngTo(String queryHacBngTo) {
        this.queryHacBngTo = queryHacBngTo;
    }
    /**
     * 発注書番号Toの取得
     * 
     * @return 発注書番号To
     */
    public String getQueryHacBngTo() {
        return this.queryHacBngTo;
    }

//** 発注日 **//
    /**
     * 発注日チェックのセット
     * 
     * @param checkHacDte 発注日チェック
     */
    public void setCheckHacDte(boolean checkHacDte) {
        this.checkHacDte = checkHacDte;
    }
    /**
     * 発注日チェックの取得
     * 
     * @return 発注日チェック
     */
    public boolean getCheckHacDte() {
        return this.checkHacDte;
    }
    /**
     * 発注日(年)Fromのセット
     * 
     * @param queryHacDteFrm_Y 発注日(年)From
     */
    public void setQueryHacDteFrm_Y(String queryHacDteFrm_Y) {
        this.queryHacDteFrm_Y = queryHacDteFrm_Y;
    }
    /**
     * 発注日(年)Fromの取得
     * 
     * @return 発注日(年)From
     */
    public String getQueryHacDteFrm_Y() {
        return this.queryHacDteFrm_Y;
    }
    /**
     * 発注日(月)Fromのセット
     * 
     * @param queryHacDteFrm_M 発注日(月)From
     */
    public void setQueryHacDteFrm_M(String queryHacDteFrm_M) {
        this.queryHacDteFrm_M = queryHacDteFrm_M;
    }
    /**
     * 発注日(月)Fromの取得
     * 
     * @return 発注日(月)From
     */
    public String getQueryHacDteFrm_M() {
        return this.queryHacDteFrm_M;
    }
    /**
     * 発注日(日)Fromのセット
     * 
     * @param queryHacDteFrm_D 発注日(日)From
     */
    public void setQueryHacDteFrm_D(String queryHacDteFrm_D) {
        this.queryHacDteFrm_D = queryHacDteFrm_D;
    }
    /**
     * 発注日(日)Fromの取得
     * 
     * @return 発注日(日)From
     */
    public String getQueryHacDteFrm_D() {
        return this.queryHacDteFrm_D;
    }
    /**
     * 発注日(年)Toのセット
     * 
     * @param queryHacDteTo_Y 発注日(年)To
     */
    public void setQueryHacDteTo_Y(String queryHacDteTo_Y) {
        this.queryHacDteTo_Y = queryHacDteTo_Y;
    }
    /**
     * 発注日(年)Toの取得
     * 
     * @return 発注日(年)To
     */
    public String getQueryHacDteTo_Y() {
        return this.queryHacDteTo_Y;
    }
    /**
     * 発注日(月)Toのセット
     * 
     * @param queryHacDteTo_M 発注日(月)To
     */
    public void setQueryHacDteTo_M(String queryHacDteTo_M) {
        this.queryHacDteTo_M = queryHacDteTo_M;
    }
    /**
     * 発注日(月)Toの取得
     * 
     * @return 発注日(月)To
     */
    public String getQueryHacDteTo_M() {
        return this.queryHacDteTo_M;
    }
    /**
     * 発注日(日)Toのセット
     * 
     * @param queryHacDteTo_D 発注日(日)To
     */
    public void setQueryHacDteTo_D(String queryHacDteTo_D) {
        this.queryHacDteTo_D = queryHacDteTo_D;
    }
    /**
     * 発注日(日)Toの取得
     * 
     * @return 発注日(日)To
     */
    public String getQueryHacDteTo_D() {
        return this.queryHacDteTo_D;
    }


//** 納期 **//
    /**
     * 納期チェックのセット
     * 
     * @param checkNki 納期チェック
     */
    public void setCheckNki(boolean checkNki) {
        this.checkNki = checkNki;
    }
    /**
     * 納期チェックの取得
     * 
     * @return 納期チェック
     */
    public boolean getCheckNki() {
        return this.checkNki;
    }    
    /**
     * 納期(年)Fromのセット
     * 
     * @param queryNkiFrm_Y 納期(年)From
     */
    public void setQueryNkiFrm_Y(String queryNkiFrm_Y) {
        this.queryNkiFrm_Y = queryNkiFrm_Y;
    }
    /**
     * 納期(年)Fromの取得
     * 
     * @return 納期(年)From
     */
    public String getQueryNkiFrm_Y() {
        return this.queryNkiFrm_Y;
    }
    /**
     * 納期(月)Fromのセット
     * 
     * @param queryNkiFrm_M 納期(月)From
     */
    public void setQueryNkiFrm_M(String queryNkiFrm_M) {
        this.queryNkiFrm_M = queryNkiFrm_M;
    }
    /**
     * 納期(月)Fromの取得
     * 
     * @return 納期(月)From
     */
    public String getQueryNkiFrm_M() {
        return this.queryNkiFrm_M;        
    }
    /**
     * 納期(日)Fromのセット
     * 
     * @param queryNkiFrm_D 納期(日)From
     */
    public void setQueryNkiFrm_D(String queryNkiFrm_D) {
        this.queryNkiFrm_D = queryNkiFrm_D;
    }
    /**
     * 納期(日)Fromの取得
     * 
     * @return 納期(日)From
     */
    public String getQueryNkiFrm_D() {
        return this.queryNkiFrm_D;
    }
    /**
     * 納期(年)Toのセット
     * 
     * @param queryNkiTo_Y 納期(年)To
     */
    public void setQueryNkiTo_Y(String queryNkiTo_Y) {
        this.queryNkiTo_Y = queryNkiTo_Y;
    }
    /**
     * 納期(年)Toの取得
     * 
     * @return 納期(年)To
     */
    public String getQueryNkiTo_Y() {
        return this.queryNkiTo_Y;
    }
    /**
     * 納期(月)Toのセット
     * 
     * @param queryNkiTo_M 納期(月)To
     */
    public void setQueryNkiTo_M(String queryNkiTo_M) {
        this.queryNkiTo_M = queryNkiTo_M;
    }
    /**
     * 納期(月)Toの取得
     * 
     * @return 納期(月)To
     */
    public String getQueryNkiTo_M() {
        return this.queryNkiTo_M;
    }
    /**
     * 納期(日)Toのセット
     * 
     * @param queryNkiTo_D 納期(日)To
     */
    public void setQueryNkiTo_D(String queryNkiTo_D) {
        this.queryNkiTo_D = queryNkiTo_D;
    }
    /**
     * 納期(日)Toの取得
     * 
     * @return 納期(日)To
     */
     public String getQueryNkiTo_D() {
        return this.queryNkiTo_D;
    }
    
	/**
     * メーカー分類チェックのセット
     * 
     * @param checkNki メーカー分類チェック
     */
    public void setCheckMkrBun(boolean checkMkrBun) {
        this.checkMkrBun = checkMkrBun;
    }
    /**
     * メーカー分類チェックの取得
     * 
     * @return メーカー分類チェック
     */
    public boolean getCheckMkrBun() {
        return this.checkMkrBun;
    } 
	/**
     * メーカー分類のセット
     * 
     * @param mkrBun メーカー分類
     */
    public void setMkrBun(String[] s) {

        this.mkrBun = s;

    }
    /**
     * メーカー分類の取得
     * 
     * @return メーカー分類
     */
    public String[] getMkrBun() {

        return this.mkrBun;

    }
    
    /**
     * 区分チェックのセット
     * 
     * @param kbn 区分チェック
     */
    public void setCheckKbn(boolean checkKbn) {
        this.checkKbn = checkKbn;
    }
    /**
     * 区分チェックの取得
     * 
     * @return 区分チェック
     */
    public boolean getCheckKbn() {
        return this.checkKbn;
    } 
	/**
     * 区分のセット
     * 
     * @param kbn 区分
     */
    public void setKbn(String s) {

        this.kbn = s;

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
	 * 入庫日チェックのセット
	 * 
	 * @param checkNyoDte　入庫日チェック
	 */
	public void setCheckNyoDte(boolean checkNyoDte) {
		this.checkNyoDte = checkNyoDte;
	}

	/**
	 * 入庫日チェックの取得
	 * 
	 * @return checkNyoDte 入庫日チェック
	 */
	public boolean getCheckNyoDte() {
		return checkNyoDte;
	}

	/**
	 *入庫日(年)Fromのセット
	 *
	 *  @param queryNyoDteFrm_Y　入庫日(年)From
	 */
	public void setQueryNyoDteFrm_Y(String queryNyoDteFrm_Y) {
		this.queryNyoDteFrm_Y = queryNyoDteFrm_Y;
	}

	/**
	 * 入庫日(年)Fromの取得
	 * 
	 * @return　入庫日(年)From
	 */
	public String getQueryNyoDteFrm_Y() {
		return queryNyoDteFrm_Y;
	}

	/**
	 * 入庫日(月)Fromのセット
	 * 
	 * @param queryNyoDteFrm_M　入庫日(月)From
	 */
	public void setQueryNyoDteFrm_M(String queryNyoDteFrm_M) {
		this.queryNyoDteFrm_M = queryNyoDteFrm_M;
	}


	/**
	 * 入庫日(月)Fromの取得
	 * 
	 * @return　入庫日(月)From
	 **/
	public String getQueryNyoDteFrm_M() {
		return queryNyoDteFrm_M;
	}

	/**
	 * 入庫日(日)Fromのセット
	 * 
	 * @param queryNyoDteFrm_D　入庫日(日)From
	 */
	public void setQueryNyoDteFrm_D(String queryNyoDteFrm_D) {
		this.queryNyoDteFrm_D = queryNyoDteFrm_D;
	}

	/**
	 * 入庫日(日)Fromの取得
	 * 
	 * @return　入庫日(日)From
	 */
	public String getQueryNyoDteFrm_D() {
		return queryNyoDteFrm_D;
	}

	/**
	 * 入庫日(年)toのセット
	 * 
	 * @param queryNyoDteTo_Y 入庫日(年)to
	 */
	public void setQueryNyoDteTo_Y(String queryNyoDteTo_Y) {
		this.queryNyoDteTo_Y = queryNyoDteTo_Y;
	}

	/**
	 * 入庫日(年)toの取得
	 * 
	 * @return　 入庫日(年)to
	 */
	public String getQueryNyoDteTo_Y() {
		return queryNyoDteTo_Y;
	}

	/**
	 * 入庫日(月)toのセット
	 * 
	 * @param queryNyoDteTo_M 入庫日(月)to
	 */
	public void setQueryNyoDteTo_M(String queryNyoDteTo_M) {
		this.queryNyoDteTo_M = queryNyoDteTo_M;
	}

	/**
	 * 入庫日(月)toの取得
	 * 
	 * @return 入庫日(月)to
	 */
	public String getQueryNyoDteTo_M() {
		return queryNyoDteTo_M;
	}


	/**
	 * 入庫日(月)toの取得
	 * 
	 * @return 入庫日(月)to
	 */
	public String getQueryNyoDteTo_D() {
		return queryNyoDteTo_D;
	}

	/**
	 * 入庫日(月)toの取得
	 * 
	 * @param queryNyoDteTo_D 入庫日(月)to
	 */
	public void setQueryNyoDteTo_D(String queryNyoDteTo_D) {
		this.queryNyoDteTo_D = queryNyoDteTo_D;
	}


	/**
	 * チェックボックス発注書順の取得
	 * @return
	 */
	public boolean isChkHacJun() {
		return chkHacJun;
	}

	/**
	 * チェックボックス発注書順の設定
	 * @param b
	 */
	public void setChkHacJun(boolean b) {
		chkHacJun = b;
	}

	/**
	 * @return
	 */
	public String getPrcKbn() {
		return prcKbn;
	}

	/**
	 * @param string
	 */
	public void setPrcKbn(String string) {
		prcKbn = string;
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

	
	public OrderHistoryFormRow getFormRow_R(int i){
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
	
	
	//表示区分
	public void setHyoKbn(String hyoKbn){
		this.hyoKbn = hyoKbn;
	}
	
	public String getHyoKbn(){
		return  this.hyoKbn;
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
	public void reset_checkbox(){
		this.setCheckHacBng(false);
		this.setCheckHacDte(false);
		this.setCheckNki(false);		
		this.setCheckKbn(false);
		this.setCheckNyoDte(false);
		this.setChkHacJun(false);
	}
	//初期化メソッド(text)
	public void clear_text(){
		this.setPrcKbn(PRCKBN_SEARCH);
		this.setRb_HacSyo("2");
		this.setRb_BunCod("1");
		this.setRb_Nyuko("2");
		this.setCheckHacBng(false);
		this.setCheckHacDte(false);
		this.setCheckNki(false);
		this.setCheckMkrBun(false);
		this.setCheckKbn(false);	
		this.setCheckNyoDte(false);	
		this.setQueryHacCod("");
		this.setQueryKigBng("");
		this.setQueryHbiDte_Y("");
		this.setQueryHbiDte_M("");
		this.setQueryHbiDte_D("");
		this.setQueryHacBngFrm("");
		this.setQueryHacBngTo("");
		this.setQueryHacDteFrm_Y("");
		this.setQueryHacDteFrm_M("");
		this.setQueryHacDteFrm_D("");
		this.setQueryHacDteTo_Y("");
		this.setQueryHacDteTo_M("");
		this.setQueryHacDteTo_D("");
		this.setQueryNkiFrm_Y("");
		this.setQueryNkiFrm_M("");
		this.setQueryNkiFrm_D("");
		this.setQueryNkiTo_Y("");
		this.setQueryNkiTo_M("");
		this.setQueryNkiTo_D("");
		this.setMkrBun(null);
		this.setKbn("");
		this.setQueryNyoDteFrm_Y("");
		this.setQueryNyoDteFrm_M("");
		this.setQueryNyoDteFrm_D("");
		this.setQueryNyoDteTo_Y("");
		this.setQueryNyoDteTo_M("");
		this.setQueryNyoDteTo_D("");
		this.setChkHacJun(false);
	}
	

	//---------------------------------------------------------------------------------
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest req){
		if(command.equals("INIT"))
			return null;
		if(command.equals(COMMAND_CLEAR))
			return null;
		
		ActionErrors errors = new ActionErrors();

		//実行のとき				
		if(command.equals(COMMAND_JIKKOU)){
			DateUtils dateUtils = new DateUtils();
			int today = dateUtils.getDate6Int();
        
	/** 入力チェック **/
 	    	/** 発注先コード **/
			if(!queryHacCod.trim().equals("")){ //

    			//半角のみ
				if(!StringUtils.isAscii(queryHacCod.trim())){
					errors.add("",new ActionError("errors.input.1","発注先コード","半角"));
				}
			}
             /** 記号番号 **/
            if(!queryKigBng.trim().equals("")){
            	
            	//半角
            	if(!StringUtils.isAscii(queryKigBng.trim())){
            		errors.add("",new ActionError("errors.input.1","記号番号","半角"));
            	}
            }
            
 			/** 発売日 **/
    		if(!queryHbiDte_Y.trim().equals("") || !queryHbiDte_M.trim().equals("") || !queryHbiDte_D.trim().equals("")){
            	//半角
            	if( (!StringUtils.isAscii(queryHbiDte_Y.trim()) && !queryHbiDte_Y.trim().equals("")) ||
            	    (!StringUtils.isAscii(queryHbiDte_M.trim()) && !queryHbiDte_M.trim().equals("")) || 
            	    (!StringUtils.isAscii(queryHbiDte_D.trim()) && !queryHbiDte_D.trim().equals("")) ){
            		errors.add("",new ActionError("errors.input.1","発売日","半角"));
            	}else{
				//日付型
					if(!CheckCommon.validateAsDate(queryHbiDte_Y,queryHbiDte_M,queryHbiDte_D)){
							errors.add("",new ActionError("errors.input.1","発売日","日付"));
					}
				}
			}
			
			/** 発注書番号From&To **/
			if(rb_HacSyo.equals("1")){
				if(checkHacBng || !queryHacBngFrm.trim().equals("") || !queryHacBngTo.trim().equals("")){
					errors.add("",new ActionError("errors.input.prohibited","未出力選択時、発注書番号"));
				}
			}else{		
				if(checkHacBng){
					boolean isAsciiDigitErr = false;
					if(queryHacBngFrm.trim().equals("")){
						errors.add("",new ActionError("errors.input.required","発注書番号(From)"));
					}else if(!StringUtils.isAsciiDigit(queryHacBngFrm.trim())){
						errors.add("",new ActionError("errors.input.1","発注書番号(From)","半角数字"));
						isAsciiDigitErr = true;
					}
					if(!queryHacBngTo.trim().equals("")){
						if(!StringUtils.isAsciiDigit(queryHacBngTo.trim())){
							errors.add("",new ActionError("errors.input.1","発注書番号(To)","半角数字"));
							isAsciiDigitErr = true;
						}
						if(!queryHacBngFrm.trim().equals("") && !isAsciiDigitErr){
							int hacBngFrm = Integer.parseInt(queryHacBngFrm);
							int hacBngTo = Integer.parseInt(queryHacBngTo);
							if(hacBngFrm > hacBngTo){
								errors.add("",new ActionError("errors.input.incorrect","発注書番号の範囲指定"));
							}
						}
					}
				}else{
					if(!queryHacBngFrm.trim().equals("") || !queryHacBngTo.trim().equals("")){
						errors.add("",new ActionError("errors.input.prohibited","発注書番号"));
					}
				}
			}		

			/** 発注日From&To **/
		    if(checkHacDte){//発注日入力可のとき
		    	boolean hacDte_required = false;
		    	//Fromがどれかブランク
		    	if(queryHacDteFrm_Y.trim().equals("") || queryHacDteFrm_M.trim().equals("") || queryHacDteFrm_D.trim().equals("")){
					hacDte_required = true;
		    	    errors.add("",new ActionError("errors.input.required","発注日(From)"));
		    	}else{
		    		//半角
             		if((!StringUtils.isAscii(queryHacDteFrm_Y.trim()) && !queryHacDteFrm_Y.trim().equals("")) || 
		    		   (!StringUtils.isAscii(queryHacDteFrm_M.trim()) && !queryHacDteFrm_M.trim().equals("")) || 
            	       (!StringUtils.isAscii(queryHacDteFrm_D.trim()) && !queryHacDteFrm_D.trim().equals("")) ){
            				errors.add("",new ActionError("errors.input.1","発注日","半角"));
      	      		}else{

		    		//From日付型チェック
		    	   		if(!CheckCommon.validateAsDate(queryHacDteFrm_Y,queryHacDteFrm_M,queryHacDteFrm_D)){
							errors.add("",new ActionError("errors.input.1","発注日(From)","日付"));		
		    	   		}else{
							int hacdte_from = Integer.parseInt(queryHacDteFrm_Y.trim() + 
						   	 	StringUtils.lpad(queryHacDteFrm_M.trim(),2,"0") + 
							 	StringUtils.lpad(queryHacDteFrm_D.trim(),2,"0"));
								//当日以降Ｘ
					    		if(hacdte_from > today)
					    			errors.add("",new ActionError("errors.input.1","発注日(From)","当日以前の日付"));
				   		}
            	    }
		        }
		        if(!(queryHacDteTo_Y.trim().equals("") && queryHacDteTo_M.trim().equals("") && queryHacDteTo_D.trim().equals(""))){
		    		if(queryHacDteFrm_Y.trim().equals("") || queryHacDteFrm_M.trim().equals("") || queryHacDteFrm_D.trim().equals("")){
		    	    	if(!hacDte_required)
		    	    		errors.add("",new ActionError("errors.input.required","発注日(From)"));
		    		}
		    		if(queryHacDteTo_Y.trim().equals("") || queryHacDteTo_M.trim().equals("") || queryHacDteTo_D.trim().equals("")){
		    	    	errors.add("",new ActionError("errors.input.required","発注日(To)"));
		    		}else{ 
		    		//To日付型チェック
		    	   		if(!CheckCommon.validateAsDate(queryHacDteTo_Y,queryHacDteTo_M,queryHacDteTo_D)){
							errors.add("",new ActionError("errors.input.1","発注日(To)","日付"));		
		    	   		}else{
							int hacdte_to = Integer.parseInt(queryHacDteTo_Y.trim() + 
						 	  	 StringUtils.lpad(queryHacDteTo_M.trim(),2,"0") + 
								 StringUtils.lpad(queryHacDteTo_D.trim(),2,"0"));
							//当日以降Ｘ
					  		if(hacdte_to > today)
					    		errors.add("",new ActionError("errors.input.1","発注日","当日以前の日付"));

							int hacdte_from = Integer.parseInt(queryHacDteFrm_Y.trim() + 
						   	 	StringUtils.lpad(queryHacDteFrm_M.trim(),2,"0") + 
							 	StringUtils.lpad(queryHacDteFrm_D.trim(),2,"0"));
							if(!queryHacDteFrm_Y.trim().equals("") && !queryHacDteFrm_M.trim().equals("") && !queryHacDteFrm_D.trim().equals("")){
								if(hacdte_to < hacdte_from)
					    			errors.add("",new ActionError("errors.input.incorrect","発注日の範囲指定"));
							}		 	
				 		}
		     	    }
		        }		
		    }else{
		    	if(!queryHacDteFrm_Y.trim().equals("") || !queryHacDteFrm_M.trim().equals("")|| 
		    	   !queryHacDteFrm_D.trim().equals("") || !queryHacDteTo_Y.trim().equals("") || 
		    	   !queryHacDteTo_M.trim().equals("")  || !queryHacDteTo_D.trim().equals("")){
		    		errors.add("",new ActionError("errors.input.prohibited","発注日"));
		    	}
  		    }
		    
		    /** 納期From&To **/
		    if(checkNki){//納期入力可のとき
		    	boolean nki_required = false;
		    	//Fromがどれかブランク
		    	if(queryNkiFrm_Y.trim().equals("") || queryNkiFrm_M.trim().equals("") || queryNkiFrm_D.trim().equals("")){
		    	    nki_required = true;
		    	    errors.add("",new ActionError("errors.input.required","納期(From)"));
		    	}else{
		    		//半角
             		if((!StringUtils.isAscii(queryNkiFrm_Y.trim()) && !queryNkiFrm_Y.trim().equals("")) || 
		    		   (!StringUtils.isAscii(queryNkiFrm_M.trim()) && !queryNkiFrm_M.trim().equals("")) || 
            	       (!StringUtils.isAscii(queryNkiFrm_D.trim()) && !queryNkiFrm_D.trim().equals("")) ){
            				errors.add("",new ActionError("errors.input.1","納期","半角"));
      	      		}else{
		    			//From日付型チェック
		    	   		if(!CheckCommon.validateAsDate(queryNkiFrm_Y,queryNkiFrm_M,queryNkiFrm_D)){
							errors.add("",new ActionError("errors.input.1","納期(From)","日付"));		
				   		}
		        	}
		    	}
		        if(!(queryNkiTo_Y.trim().equals("") && queryNkiTo_M.trim().equals("") && queryNkiTo_D.trim().equals(""))){
		    		if(queryNkiFrm_Y.trim().equals("") || queryNkiFrm_M.trim().equals("") || queryNkiFrm_D.trim().equals("")){
		    	    	if(!nki_required)
		    	    		errors.add("",new ActionError("errors.input.required","納期(From)"));
		    		}
		    		if(queryNkiTo_Y.trim().equals("") || queryNkiTo_M.trim().equals("") || queryNkiTo_D.trim().equals("")){
		    	    	errors.add("",new ActionError("errors.input.required","納期(To)"));
		    		}else{ 
		    		//To日付型チェック
		    	   		if(!CheckCommon.validateAsDate(queryNkiTo_Y,queryNkiTo_M,queryNkiTo_D)){
							errors.add("",new ActionError("errors.input.1","納期(To)","日付"));		
		    	   		}else{
							int nki_to = Integer.parseInt(queryNkiTo_Y.trim() + 
						 	  	 StringUtils.lpad(queryNkiTo_M.trim(),2,"0") + 
								 StringUtils.lpad(queryNkiTo_D.trim(),2,"0"));

							int nki_from = Integer.parseInt(queryNkiFrm_Y.trim() + 
						   	 	StringUtils.lpad(queryNkiFrm_M.trim(),2,"0") + 
							 	StringUtils.lpad(queryNkiFrm_D.trim(),2,"0"));

							if(!queryNkiFrm_Y.trim().equals("") && !queryNkiFrm_M.trim().equals("") && !queryNkiFrm_D.trim().equals("")){
								if(nki_to < nki_from){
					    			errors.add("",new ActionError("errors.input.incorrect","納期の範囲指定"));
								}	
							} 	
				 		}
		     	    }
		        }		
		    }else{
		    	if(!queryNkiFrm_Y.trim().equals("") || !queryNkiFrm_M.trim().equals("")|| 
		    	   !queryNkiFrm_D.trim().equals("") || !queryNkiTo_Y.trim().equals("") || 
		    	   !queryNkiTo_M.trim().equals("")  || !queryNkiTo_D.trim().equals("")){
		    		errors.add("",new ActionError("errors.input.prohibited","納期"));
		    	}
  		    }
  		    //メーカー分類
			if(checkMkrBun){
				if(mkrBun == null){
					errors.add("",new ActionError("errors.input.required","メーカー分類"));		
				}
			}
			/** 入庫日From&To **/  //2004/04/01 add
			if(checkNyoDte){//入庫日入力可のとき
				boolean nyodte_required = false;
				//Fromがどれかブランク
				if(queryNyoDteFrm_Y.trim().equals("") || queryNyoDteFrm_M.trim().equals("") || queryNyoDteFrm_D.trim().equals("")){
					nyodte_required = true;
					errors.add("",new ActionError("errors.input.required","入庫日(From)"));
				}else{
					//半角
					if((!StringUtils.isAscii(queryNyoDteFrm_Y.trim()) && !queryNyoDteFrm_Y.trim().equals("")) || 
					   (!StringUtils.isAscii(queryNyoDteFrm_M.trim()) && !queryNyoDteFrm_M.trim().equals("")) || 
					   (!StringUtils.isAscii(queryNyoDteFrm_D.trim()) && !queryNyoDteFrm_D.trim().equals("")) ){
							errors.add("",new ActionError("errors.input.1","入庫日","半角"));
					}else{
						//From日付型チェック
						if(!CheckCommon.validateAsDate(queryNyoDteFrm_Y,queryNyoDteFrm_M,queryNyoDteFrm_D)){
							errors.add("",new ActionError("errors.input.1","入庫日(From)","日付"));		
						}
					}
				}
				if(!(queryNyoDteTo_Y.trim().equals("") && queryNyoDteTo_M.trim().equals("") && queryNyoDteTo_D.trim().equals(""))){
					if(queryNyoDteTo_Y.trim().equals("") || queryNyoDteTo_M.trim().equals("") || queryNyoDteTo_D.trim().equals("")){
						errors.add("",new ActionError("errors.input.required","入庫日(To)"));
					}else{ 
					//To日付型チェック
						if(!CheckCommon.validateAsDate(queryNyoDteTo_Y,queryNyoDteTo_M,queryNyoDteTo_D)){
							errors.add("",new ActionError("errors.input.1","入庫日(To)","日付"));		
						}else{
							int nyodte_to = Integer.parseInt(queryNyoDteTo_Y.trim() + 
								 StringUtils.lpad(queryNyoDteTo_M.trim(),2,"0") + 
								 StringUtils.lpad(queryNyoDteTo_D.trim(),2,"0"));

							int nyodte_from = Integer.parseInt(queryNyoDteFrm_Y.trim() + 
								StringUtils.lpad(queryNyoDteFrm_M.trim(),2,"0") + 
								StringUtils.lpad(queryNyoDteFrm_D.trim(),2,"0"));

							if(!queryNyoDteFrm_Y.trim().equals("") && !queryNyoDteFrm_M.trim().equals("") && !queryNyoDteFrm_D.trim().equals("")){
								if(nyodte_to < nyodte_from){
									errors.add("",new ActionError("errors.input.incorrect","入庫日の範囲指定"));
								}	
							} 	
						}
					}
				}		
			}else{
				if(!queryNyoDteFrm_Y.trim().equals("") || !queryNyoDteFrm_M.trim().equals("")|| 
				   !queryNyoDteFrm_D.trim().equals("") || !queryNyoDteTo_Y.trim().equals("") || 
				   !queryNyoDteTo_M.trim().equals("")  || !queryNyoDteTo_D.trim().equals("")){
					errors.add("",new ActionError("errors.input.prohibited","入庫日"));
				}
			}

		}
		
		
		return errors.empty() ? null : errors;
	}
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
		reset_checkbox();
		clear_text();
	}

}

