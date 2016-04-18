/**
* TeiansuuReferForm  提案数照会指示フォームクラス
*
*	作成日    2003/02/20
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
*         2003/07/16 （ＮＩＩ）岡田 夏美
* 			・ソート条件にソートキー追加。
* 		  2003/08/21 （ＮＩＩ）岡田 夏美
* 			・記号番号入力欄を５→１０個に
* 		  2003/09/11 （ＮＩＩ）岡田 夏美
* 			・提案数照会結果画面から戻ってきた時、検索条件の入力内容を保持しておくように修正
* 		  2003/09/19（ＮＩＩ）岡田 夏美
* 			・対象データのみ表示・非対象データ表示 選択チェックボックス
* 		  2003/10/09（ＮＩＩ）岡田 夏美
* 			・対象データのみ表示チェックボックスを削除
* 			  非対象データ表示選択時には、ドロップダウンで範囲を選択する
* 		  2003/12/12  (NII)  森
* 			・品番指定数を10→15へ
*		  2004/06/28（ＮＩＩ）蛭田
*			・コピー＆ペースト機能追加
*		  2006/05/10（ＮＩＩ）田端 康教
*			・Ｋ社のランク条件変更対応
*/

package com.jds.prodord.indicate.teiansuurefer;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class TeiansuuReferForm extends ActionForm {

//	public TeiansuuReferForm() {
//		
//	}
	public static final String COMMAND_TEIANSYOKAI = "COMMAND_TEIANSYOKAI";
	public static final String COMMAND_JIDOUHACHU = "COMMAND_JIDOUHACHU";
	public static final String COMMAND_CLEAR = "COMMAND_CLEAR";
//	public static final String COMMAND_PASTE = "貼り付け";//2004/06/29 add
		
	public static final boolean RB_ALL = true;
	public static final boolean RB_SELECT = false;
	
	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList vl_ketCod = new ArrayList();
	private ArrayList vl_rank = new ArrayList();
	private ArrayList vl_mkrBun = new ArrayList();
	private ArrayList vl_hyoKbn = new ArrayList();
	private ArrayList lb_hyoKbn = new ArrayList();
	private ArrayList vl_taisyoRange = new ArrayList();
	private ArrayList lb_taisyoRange = new ArrayList();
	
//	public static final String[] RANK = {"N1","N2","N3","S","A","B","C","D"};//"D"も追加 2003/06/20
	public static final String[] VL_HYOKBN = {"H","Y"};
	public static final String[] LB_HYOKBN = {"邦楽","洋楽"};
	public static final String[] VL_TAISYORANGE = {"1","2","3","4","5"};
	public static final String[] LB_TAISYORANGE = {"0","1　～　10","11　～　30","31　～　50","51　～　100"};
	
	
	private String command = "";
	
	private boolean rb_kaiSkbCod = true;
	private boolean rb_rank = true;
	private boolean rb_ketCod = true;
	private boolean rb_mkrBun = true;
	private boolean rb_hyoKbn = true;
	private boolean rb_kigBng = true;
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String[] kaiSkbCod = null;
	private String[] rank = null;
	private String[] ketCod = null;
	private String[] mkrBun = null;
	private String[] hyoKbn = null;
	private String kigBng1 = "";
	private String kigBng2 = "";
	private String kigBng3 = "";
	private String kigBng4 = "";
	private String kigBng5 = "";
	private String kigBng6 = "";
	private String kigBng7 = "";
	private String kigBng8 = "";
	private String kigBng9 = "";
	private String kigBng10 = "";
	// 品番指定数を10→15へ 2003/12/12 add　st****
	private String kigBng11 = "";
	private String kigBng12 = "";
	private String kigBng13 = "";
	private String kigBng14 = "";
	private String kigBng15 = "";
	// 品番指定数を10→15へ 2003/12/12 add　****

	private boolean sort_rank = false;
	private boolean sort_ketCod = false;
	private boolean sort_sortKey = false;
	
	private boolean hitaisyo = false;
	private String taisyoRange = "";
	
	private String cpPaste = ""; //2004/06/28 add
	private String paste   = "";
	
	// 提案数照会結果画面から戻ってきた時のため、 ************
	// チェックボックスの値を保持する 2003/09/11 add
	private boolean[] checkBoxValue = new boolean[4];
	
	public void setCheckBoxValueToArray(){
		checkBoxValue[0] = sort_rank;//ランク
		checkBoxValue[1] = sort_ketCod;//形態コード
		checkBoxValue[2] = sort_sortKey;//ソートキー
		checkBoxValue[3] = hitaisyo;//非対象データも表示
	}
	public void setCheckBoxValue(){
		sort_rank = checkBoxValue[0];
		sort_ketCod = checkBoxValue[1];
		sort_sortKey = checkBoxValue[2];
		hitaisyo = checkBoxValue[3];
	}
	public void clearCheckBoxValue(){
		for(int i = 0; i < checkBoxValue.length; i++){
			checkBoxValue[i] = false;
		}
	}
	//*****************************************************
	
	//メニューからきたときはtrueをセット
	private boolean fromMenuSgn = false;
	public boolean getFromMenuSgn(){
		return fromMenuSgn;
	}
	public void setFromMenuSgn(boolean fromMenuSgn){
		this.fromMenuSgn = fromMenuSgn;
	}
	
	public TeiansuuReferForm(){
		super();
		this.clearAll();
		this.setCommand("INIT");
		this.setOptions();//選択項目のオプションをセット
		
	}
	
	//-----------------------------------------------------------
	
	public Collection getVl_ketCod(){
		return (Collection)vl_ketCod;
	}
	public Collection getVl_kaiSkbCod(){
		return (Collection)vl_kaiSkbCod;
	}
	public Collection getVl_rank(){
		return (Collection)vl_rank;
	}
	public Collection getVl_mkrBun(){
		return (Collection)vl_mkrBun;
	}
	public Collection getVl_hyoKbn(){
		return (Collection)vl_hyoKbn;
	}
	public Collection getLb_hyoKbn(){
		return (Collection)lb_hyoKbn;
	}
	public Collection getVl_taisyoRange(){
		return (Collection)vl_taisyoRange;
	}
	public Collection getLb_taisyoRange(){
		return (Collection)lb_taisyoRange;
	}
	
	public void setRankOptions(String daiKaiSkbCod){
		vl_rank.clear();
	
		String[] RANK = makeRank(daiKaiSkbCod);
		for(int i = 0; i<RANK.length; i++){
			vl_rank.add(RANK[i]);
		}
	}
	
	//選択項目のオプションをセットするメソッド
	public void setOptions(){
		if(vl_hyoKbn.size() == 0){
			for(int i = 0; i<VL_HYOKBN.length; i++){
				vl_hyoKbn.add(VL_HYOKBN[i]);
			}
		}
		if(lb_hyoKbn.size() == 0){
			for(int i = 0; i<LB_HYOKBN.length; i++){
				lb_hyoKbn.add(LB_HYOKBN[i]);
			}
		}
		if(vl_taisyoRange.size() == 0){
			for(int i = 0; i<VL_TAISYORANGE.length; i++){
				vl_taisyoRange.add(VL_TAISYORANGE[i]);
			}
		}
		if(lb_taisyoRange.size() == 0){
			for(int i = 0; i<LB_TAISYORANGE.length; i++){
				lb_taisyoRange.add(LB_TAISYORANGE[i]);
			}
		}
	}
	public void setKaiSkbCodOptions(ArrayList queryKaiSkbCodList){
		vl_kaiSkbCod.clear();
		for(int i = 0; i<queryKaiSkbCodList.size(); i++){
			vl_kaiSkbCod.add(queryKaiSkbCodList.get(i)+"");
		}
	}
	public void setKetCodOptions(ArrayList ketNmList){
		vl_ketCod.clear();
		for(int i = 0; i<ketNmList.size(); i++){
			vl_ketCod.add(ketNmList.get(i)+"");
		}
	}
	public void setMkrBunOptions(ArrayList mkrBunList){
		vl_mkrBun.clear();
		for(int i = 0; i<mkrBunList.size(); i++){
			vl_mkrBun.add(mkrBunList.get(i)+"");
		}
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
     * 会社識別コードのセット
     * 
     * @param kaiSkbCod 会社識別コード
     */
    public void setKaiSkbCod(String[] s) {
    	
        this.kaiSkbCod = s;

    }
    /**
     * 会社識別コードの取得
     * 
     * @return 会社識別コード
     */
    public String[] getKaiSkbCod() {

        return this.kaiSkbCod;

    }
    /**
     * ランクのセット
     * 
     * @param rank ランク
     */
    public void setRank(String[] s) {

        this.rank = s;

    }
    /**
     * ランクの取得
     * 
     * @return ランク
     */
    public String[] getRank() {

        return this.rank;

    }
    /**
     * 形態コードのセット
     * 
     * @param ketCod 形態コード
     */
    public void setKetCod(String[] s) {

        this.ketCod = s;

    }
    /**
     * 形態コードの取得
     * 
     * @return 形態コード
     */
    public String[] getKetCod() {

        return this.ketCod;

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
     * 邦洋区分のセット
     * 
     * @param hyoKbn 邦洋区分
     */
    public void setHyoKbn(String[] s) {

        this.hyoKbn = s;

    }
    /**
     * 邦洋区分の取得
     * 
     * @return 邦洋区分
     */
    public String[] getHyoKbn() {

        return this.hyoKbn;

    }
    /**
     * 記号番号１のセット
     * 
     * @param kigBng1 記号番号１
     */
    public void setKigBng1(String s) {

        this.kigBng1 = s;

    }
    /**
     * 記号番号１の取得
     * 
     * @return 記号番号１
     */
    public String getKigBng1() {

        return this.kigBng1;

    }
    /**
     * 記号番号２のセット
     * 
     * @param kigBng2 記号番号２
     */
    public void setKigBng2(String s) {

        this.kigBng2 = s;

    }
    /**
     * 記号番号２の取得
     * 
     * @return 記号番号２
     */
    public String getKigBng2() {

        return this.kigBng2;

    }
    /**
     * 記号番号３のセット
     * 
     * @param kigBng3 記号番号３
     */
    public void setKigBng3(String s) {

        this.kigBng3 = s;

    }
    /**
     * 記号番号３の取得
     * 
     * @return 記号番号３
     */
    public String getKigBng3() {

        return this.kigBng3;

    }
    /**
     * 記号番号４のセット
     * 
     * @param kigBng4 記号番号４
     */
    public void setKigBng4(String s) {

        this.kigBng4 = s;

    }
    /**
     * 記号番号４の取得
     * 
     * @return 記号番号４
     */
    public String getKigBng4() {

        return this.kigBng4;

    }
    /**
     * 記号番号５のセット
     * 
     * @param kigBng5 記号番号５
     */
    public void setKigBng5(String s) {

        this.kigBng5 = s;

    }
    /**
     * 記号番号５の取得
     * 
     * @return 記号番号５
     */
    public String getKigBng5() {

        return this.kigBng5;

    }
    /**
     * 記号番号のセット６
     * 
     * @param kigBng 記号番号６
     */
    public void setKigBng6(String s) {

        this.kigBng6 = s;

    }
    /**
     * 記号番号の取得６
     * 
     * @return 記号番号６
     */
    public String getKigBng6() {

        return this.kigBng6;

    }
    /**
     * 記号番号のセット７
     * 
     * @param kigBng 記号番号７
     */
    public void setKigBng7(String s) {

        this.kigBng7 = s;

    }
    /**
     * 記号番号の取得７
     * 
     * @return 記号番号７
     */
    public String getKigBng7() {

        return this.kigBng7;

    }
    /**
     * 記号番号のセット８
     * 
     * @param kigBng 記号番号８
     */
    public void setKigBng8(String s) {

        this.kigBng8 = s;

    }
    /**
     * 記号番号の取得８
     * 
     * @return 記号番号８
     */
    public String getKigBng8() {

        return this.kigBng8;

    }
    /**
     * 記号番号のセット９
     * 
     * @param kigBng 記号番号９
     */
    public void setKigBng9(String s) {

        this.kigBng9 = s;

    }
    /**
     * 記号番号の取得９
     * 
     * @return 記号番号９
     */
    public String getKigBng9() {

        return this.kigBng9;

    }
    /**
     * 記号番号のセット１０
     * 
     * @param kigBng 記号番号１０
     */
    public void setKigBng10(String s) {

        this.kigBng10 = s;

    }
    /**
     * 記号番号の取得１０
     * 
     * @return 記号番号１０
     */
    public String getKigBng10() {

        return this.kigBng10;

    }

	// 品番指定数を10→15へ 2003/12/12 add　st****

	/**
     * 記号番号のセット１１
     * 
	 * @param kigBng 記号番号１１
	 */
	public void setKigBng11(String string) {

		this.kigBng11 = string;

	}

	/**
     * 記号番号の取得１１
     * 
	 * @return　記号番号１１
	 */
	public String getKigBng11() {

		return this.kigBng11;

	}

	/**
     * 記号番号のセット１２
     * 
	 * @param kigBng 記号番号１２
	 */
	public void setKigBng12(String string) {

		this.kigBng12 = string;

	}

	/**
     * 記号番号の取得１２
     * 
	 * @return　記号番号１２
	 */
	public String getKigBng12() {

		return this.kigBng12;

	}

	/**
     * 記号番号のセット１３
     * 
	 * @param kigBng 記号番号１３
	 */
	public void setKigBng13(String string) {

		this.kigBng13 = string;

	}

	/**
     * 記号番号の取得１３
     * 
	 * @return　記号番号１３
	 */
	public String getKigBng13() {

		return this.kigBng13;

	}

	/**
     * 記号番号のセット１４
     * 
	 * @param kigBng 記号番号１４
	 */
	public void setKigBng14(String string) {

		this.kigBng14 = string;

	}

	/**
     * 記号番号の取得１４
     * 
	 * @return　記号番号１４
	 */
	public String getKigBng14() {

		return this.kigBng14;

	}

	/**
     * 記号番号のセット１５
     * 
	 * @param kigBng 記号番号１５
	 */
	public void setKigBng15(String string) {

		this.kigBng15 = string;

	}

	/**
     * 記号番号の取得１５
     * 
	 * @return　記号番号１５
	 */
	public String getKigBng15() {
		
		return this.kigBng15;

	}

	// 品番指定数を10→15へ 2003/12/12 add　ed ****

    /**
     * ラジオボタン-会社のセット
     * 
     * @param rb_KaiSkbCod ラジオボタン-会社
     */
    public void setRb_kaiSkbCod(boolean b) {

        this.rb_kaiSkbCod = b;

    }
    /**
     * ラジオボタン-会社の取得
     * 
     * @return ラジオボタン-会社
     */
    public boolean getRb_kaiSkbCod() {

        return this.rb_kaiSkbCod;

    }
    /**
     * ラジオボタン-ランクのセット
     * 
     * @param rbrank ラジオボタン-ランク
     */
    public void setRb_rank(boolean b) {

        this.rb_rank = b;

    }
    /**
     * ラジオボタン-ランクの取得
     * 
     * @return ラジオボタン-ランク
     */
    public boolean getRb_rank() {

        return this.rb_rank;

    }
    /**
     * ラジオボタン-形態コードのセット
     * 
     * @param rbketCod ラジオボタン-形態コード
     */
    public void setRb_ketCod(boolean b) {

        this.rb_ketCod = b;

    }
    /**
     * ラジオボタン-形態コードの取得
     * 
     * @return ラジオボタン-形態コード
     */
    public boolean getRb_ketCod() {

        return this.rb_ketCod;

    }
    /**
     * ラジオボタン-メーカー分類のセット
     * 
     * @param rb_mkrBun ラジオボタン-メーカー分類
     */
    public void setRb_mkrBun(boolean b) {

        this.rb_mkrBun = b;

    }
    /**
     * ラジオボタン-メーカー分類の取得
     * 
     * @return ラジオボタン-メーカー分類
     */
    public boolean getRb_mkrBun() {

        return this.rb_mkrBun;

    }
    /**
     * ラジオボタン-邦洋区分のセット
     * 
     * @param rb_hyoKbn ラジオボタン-邦洋区分
     */
    public void setRb_hyoKbn(boolean b) {

        this.rb_hyoKbn = b;

    }
    /**
     * ラジオボタン-邦洋区分の取得
     * 
     * @return ラジオボタン-邦洋区分
     */
    public boolean getRb_hyoKbn() {

        return this.rb_hyoKbn;

    }
    /**
     * ラジオボタン-記号番号のセット
     * 
     * @param rb_kigBng ラジオボタン-記号番号
     */
    public void setRb_kigBng(boolean b) {

        this.rb_kigBng = b;

    }
    /**
     * ラジオボタン-記号番号の取得
     * 
     * @return ラジオボタン-記号番号
     */
    public boolean getRb_kigBng() {

        return this.rb_kigBng;

    }
    /**
     * ソート条件-ランクのセット
     * 
     * @param sort_rank ソート条件-ランク
     */
    public void setSort_rank(boolean b) {

        this.sort_rank = b;

    }
    /**
     * ソート条件-ランクの取得
     * 
     * @return ソート条件-ランク
     */
    public boolean getSort_rank() {

        return this.sort_rank;

    }
    /**
     * ソート条件-形態コードのセット
     * 
     * @param sort_ketCod ソート条件-形態コード
     */
    public void setSort_ketCod(boolean b) {

        this.sort_ketCod = b;

    }
    /**
     * ソート条件-形態コードの取得
     * 
     * @return ソート条件-形態コード
     */
    public boolean getSort_ketCod() {

        return this.sort_ketCod;

    }
   
    /**
     * ソート条件-ソートキーのセット
     * 
     * @param sort_sortKey ソート条件-ソートキー
     */
    public void setSort_sortKey(boolean b) {

        this.sort_sortKey = b;

    }
    /**
     * ソート条件-ソートキーの取得
     * 
     * @return ソート条件-ソートキー
     */
    public boolean getSort_sortKey() {

        return this.sort_sortKey;

    }

	/**
	 * Gets the hitaisyo
	 * @return Returns a boolean
	 */
	public boolean getHitaisyo() {
		return hitaisyo;
	}
	/**
	 * Sets the hitaisyo
	 * @param hitaisyo The hitaisyo to set
	 */
	public void setHitaisyo(boolean hitaisyo) {
		this.hitaisyo = hitaisyo;
	}
	
	
	/**
	 * Gets the taisyoRange
	 * @return Returns a String
	 */
	public String getTaisyoRange() {
		return taisyoRange;
	}
	/**
	 * Sets the taisyoRange
	 * @param taisyoRange The taisyoRange to set
	 */
	public void setTaisyoRange(String taisyoRange) {
		this.taisyoRange = taisyoRange;
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
	
	/**
	 * ｢貼り付け｣ボタン
	 * @return
	 */
	public String getPaste() {
		return paste;
	}

	/**
	 * ｢貼り付け｣ボタン
	 * @param string
	 */
	public void setPaste(String string) {
		paste = string;
	}


	//---------------------------------------------------------------------------------
	public void clearAll(){
		command = "";
		
		this.kaiSkbCod = null;
        this.rank = null;
        this.ketCod = null;
        this.mkrBun = null;
        this.hyoKbn = null;
        this.kigBng1 = "";
	    this.kigBng2 = "";
	    this.kigBng3 = "";
	    this.kigBng4 = "";
	    this.kigBng5 = "";
	    this.kigBng6 = "";
	    this.kigBng7 = "";
	    this.kigBng8 = "";
	    this.kigBng9 = "";
	    this.kigBng10 = "";
		// 品番指定数を10→15へ 2003/12/16 add　st****
		this.kigBng11 = "";
		this.kigBng12 = "";
		this.kigBng13 = "";
		this.kigBng14 = "";
		this.kigBng15 = "";		
		// 品番指定数を10→15へ 2003/12/16 add　ed****	    
	    
	    this.taisyoRange = null;
        
        this.setRb_default();
        this.setCheckBoxValueToArray();
        
        this.cpPaste=""; //2004/06/29 add
        this.paste="";
       
  	}
	
	//ラジオボタン・チェックボックスをデフォルトにセットするメソッド
	public void setRb_default(){
		
		setRb_kaiSkbCod(RB_SELECT);
		setRb_rank(RB_SELECT);
		setRb_ketCod(RB_SELECT);//会社・ランク・形態はデフォルトで"選択"
		setRb_mkrBun(RB_ALL);
		setRb_hyoKbn(RB_ALL);
		setRb_kigBng(RB_ALL);
		setSort_rank(false);
        setSort_ketCod(false);
		setSort_sortKey(true);
		setHitaisyo(false);
	}
	
	//チェックボックスをリセットするメソッド
	public void setCheck_false(){
		
		setSort_rank(false);
		setSort_ketCod(false);
		setSort_sortKey(false);
		setHitaisyo(false);
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
		//発注提案照会 または 発注数自動発注のとき
		if(command.equals(COMMAND_TEIANSYOKAI) || command.equals(COMMAND_JIDOUHACHU)){	

			//品番がＡＬＬ指定のとき
			if(rb_kigBng){ //trueならばALL
					
				//会社識別コード
				if(!rb_kaiSkbCod){ //falseならばSELECT
					if(kaiSkbCod == null){
						errors.add("",new ActionError("errors.input.required","会社識別コード"));		
					}
				}
				//ランク
				if(!rb_rank){ //falseならばSELECT
					if(rank == null){
						errors.add("",new ActionError("errors.input.required","ランク"));		
					}else{
						if(rank.length == 1 && rank[0].equals("N1") && command.equals(COMMAND_JIDOUHACHU))
							errors.add("",new ActionError("errors.input.prohibited","自動発注時、ランクN1のみ"));
					}
				}
				//形態コード
				if(!rb_ketCod){ //falseならばSELECT
					if(ketCod == null){
						errors.add("",new ActionError("errors.input.required","形態コード"));		
					}
				}
				//メーカー分類
				if(!rb_mkrBun){ //falseならばSELECT
					if(mkrBun == null){
						errors.add("",new ActionError("errors.input.required","メーカー分類"));		
					}
				}
			}
			//品番
			if(!rb_kigBng){ //falseならばSELECT
				ArrayList kigBng = this.getKigBng_List();
				if(this.isBlank(kigBng)){
					errors.add("",new ActionError("errors.input.required","品番"));		
				}else{
					//半角英数のみ
					this.isAscii(kigBng,"品番",errors);
				}	
			}
		
		}
		return errors.empty() ? null : errors;		
	}
	/** すべてブランクかどうかチェックするメソッド */
	public boolean isBlank(ArrayList arr){		
		for(int i = 0; i<arr.size(); i++){
			String _kigBng = arr.get(i)+"";
			if(!_kigBng.trim().equals("")){
				return false;
			}
		}
		return true;
	}
	/** 半角英数かどうかチェックするメソッド */
	public void isAscii(ArrayList arr, String param, ActionErrors errors){
		for(int i = 0; i<arr.size(); i++){
			String _kigBng = arr.get(i)+"";
			if(!_kigBng.trim().equals("") && !StringUtils.isAscii(_kigBng.trim())){
				String str = param + "(" + (i+1) + "番目)";
				errors.add("",new ActionError("errors.input.1",str,"半角英数"));
			}
		}
	}
	//記号番号をArrayListにセットして返す
	public ArrayList getKigBng_List(){
		ArrayList kigBng = new ArrayList();
		kigBng.add(kigBng1);kigBng.add(kigBng2);kigBng.add(kigBng3);
		kigBng.add(kigBng4);kigBng.add(kigBng5);
		kigBng.add(kigBng6);kigBng.add(kigBng7);kigBng.add(kigBng8);
		kigBng.add(kigBng9);kigBng.add(kigBng10);
	//品番指定数　10→15　add　2003/12/12　
		kigBng.add(kigBng11);kigBng.add(kigBng12);kigBng.add(kigBng13);
		kigBng.add(kigBng14);kigBng.add(kigBng15);
		return kigBng;
	}
	
	//--------------------------------------------------------------------------------------------
	
	public void reset(ActionMapping maping,HttpServletRequest req){
		this.command = "INIT";
		this.setCheck_false();//チェックボックスをリセット
	}
	
	static String[] makeRank(String daiKaiSkbCod){
		if(daiKaiSkbCod.equals(com.jds.prodord.common.CommonConst.KAICOD_K)){
			String[] RANK = {"N1","N2","N3","S","A","B","C"};
			return RANK;
		}else{
			String[] RANK = {"N1","N2","N3","S","A","B","C","D"};
			return RANK;
		}
	}


}

