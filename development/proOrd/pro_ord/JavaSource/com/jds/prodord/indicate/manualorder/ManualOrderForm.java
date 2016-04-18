/**
* ManualOrderForm  マニュアル発注指示フォームクラス
*
*	作成日    2003/04/25
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    Httpリクエストとリクエストの処理結果を格納するクラス。
*
*	[変更履歴]
*        2003/06/16（ＮＩＩ）蛭田 敬子
*  			・条件項目（会社・メーカー分類・形態コード・邦洋区分）の追加。
* 		 2003/07/23 （ＮＩＩ）岡田 夏美
* 			・ソート条件追加（形態コード、ソートキー）。
* 		 2003/09/11 （ＮＩＩ）岡田 夏美
* 			・プレス・副資材発注画面から戻ってきた時、検索条件の入力内容を保持しておくように修正
* 		 2004/03/08　(ＮＩＩ) 森
* 			・新旧区分"その他"追加に伴う修正
*		 2004/07/02（ＮＩＩ）蛭田
*			・コピー＆ペースト機能追加
*		 2005/02/03（ＮＩＩ）蛭田
*			・場所コードの追加
*		 2005/05/25（ＮＩＩ）蛭田
*			・ユーザーＩＤの追加
* 		 2006/05/10（ＮＩＩ）田端 康教
* 			・キング社のランクをＣまで
* 
*/

package com.jds.prodord.indicate.manualorder;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;
import com.jds.prodord.common.*;

public class ManualOrderForm extends ActionForm {

	public static final String COMMAND_CLEAR = "COMMAND_CLEAR";
	public static final String COMMAND_PRSHACHU = "COMMAND_PRSHACHU";
	public static final String COMMAND_FUKUHACHU = "COMMAND_FUKUHACHU";
//	public static final String COMMAND_SONOTAHACHU = "COMMAND_SONOTAHACHU";
	public static final String COMMAND_PASTE = "貼り付け";//2004/06/29 add
	
	public static final boolean SELECT_HBI = true;
	public static final boolean SELECT_HIN = false;
	
	public static final String KYUHU = "0";
	public static final String SINPU = "1";
	public static final String SAMPLE = "2";
	public static final String DEMOBAN = "3";
	
//	public static final String[] RANK = {"N0","N1","N2","N3","S","A","B","C","D"};
	public static final String[] VL_HYOKBN = {"H","Y"};
	public static final String[] LB_HYOKBN = {"邦楽","洋楽"};
		
	private String command = "";
	
	private ArrayList vl_kaiSkbCod = new ArrayList();
	private ArrayList vl_ketCod = new ArrayList();
	private ArrayList vl_mkrBun = new ArrayList();
	private ArrayList vl_hyoKbn = new ArrayList();
	private ArrayList lb_hyoKbn = new ArrayList();
	public static final boolean RB_ALL = true;
	public static final boolean RB_SELECT = false;
	
	private boolean rb_select = true;
	private boolean rb_kaiSkbCod = true;
	private boolean rb_ketCod = true;
	private boolean rb_mkrBun = true;
	private boolean rb_hyoKbn = true;
	private boolean rb_kigBng = true;
	
	//発売日１
    private FormDate hbiDte1 = new FormDate();
    //発売日２
    private FormDate hbiDte2 = new FormDate();
    //発売日３
    private FormDate hbiDte3 = new FormDate();
    //発売日４
    private FormDate hbiDte4 = new FormDate();
    //発売日５
    private FormDate hbiDte5 = new FormDate();
    
    
	//区分
	private String kbn = "";
	
	//記号番号１～１５
	private KigBng kigBng1 = new KigBng();
	private KigBng kigBng2 = new KigBng();
	private KigBng kigBng3 = new KigBng();
	private KigBng kigBng4 = new KigBng();
	private KigBng kigBng5 = new KigBng();
	private KigBng kigBng6 = new KigBng();
	private KigBng kigBng7 = new KigBng();
	private KigBng kigBng8 = new KigBng();
	private KigBng kigBng9 = new KigBng();
	private KigBng kigBng10 = new KigBng();
	private KigBng kigBng11 = new KigBng();
	private KigBng kigBng12 = new KigBng();
	private KigBng kigBng13 = new KigBng();
	private KigBng kigBng14 = new KigBng();
	private KigBng kigBng15 = new KigBng();
	
	private String queryDaiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String bshCod = "";//2005/02/03 add
	private String usrId = "";//2005/05/23 add
	
	private String[] kaiSkbCod = null;
	private String[] ketCod = null;
	private String[] mkrBun = null;
	private String[] hyoKbn = null;
	
	private boolean sort_ketCod = false;
	private boolean sort_sortKey = false;
	
	private String cpPaste =""; //2004/06/29 add
	private String paste ="";
	
	// 提案数照会結果画面から戻ってきた時のため、 ************
	// チェックボックスの値を保持する 2003/09/11 add
	private boolean[] checkBoxValue = new boolean[2];
	
	public void setCheckBoxValueToArray(){
		checkBoxValue[0] = sort_ketCod;//形態コード
		checkBoxValue[1] = sort_sortKey;//ソートキー
	}
	public void setCheckBoxValue(){
		sort_ketCod = checkBoxValue[0];
		sort_sortKey = checkBoxValue[1];
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
	
	public ManualOrderForm(){
		super();
		this.clearAll();
		this.setRb_select(SELECT_HBI);
		this.setCommand("INIT");
		this.setOptions();//選択項目のオプションをセット
		this.setRb_default();
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

	public Collection getVl_ketCod(){
		return (Collection)vl_ketCod;
	}
	public Collection getVl_kaiSkbCod(){
		return (Collection)vl_kaiSkbCod;
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
     * 発売日１のセット
     * 
     * @param hbiDte1 発売日１
     */
    public void setHbiDte1(FormDate hbiDte1) {

        this.hbiDte1 = hbiDte1;

    }


    /**
     * 発売日１の取得
     * 
     * @return 発売日１
     */
    public FormDate getHbiDte1() {

        return this.hbiDte1;

    }


    /**
     * 発売日２のセット
     * 
     * @param hbiDte2 発売日２
     */
    public void setHbiDte2(FormDate hbiDte2) {

        this.hbiDte2 = hbiDte2;

    }


    /**
     * 発売日２の取得
     * 
     * @return 発売日２
     */
    public FormDate getHbiDte2() {

        return this.hbiDte2;

    }


    /**
     * 発売日３のセット
     * 
     * @param hbiDte3 発売日３
     */
    public void setHbiDte3(FormDate hbiDte3) {

        this.hbiDte3 = hbiDte3;

    }


    /**
     * 発売日３の取得
     * 
     * @return 発売日３
     */
    public FormDate getHbiDte3() {

        return this.hbiDte3;

    }


    /**
     * 発売日４のセット
     * 
     * @param hbiDte4 発売日４
     */
    public void setHbiDte4(FormDate hbiDte4) {

        this.hbiDte4 = hbiDte4;

    }


    /**
     * 発売日４の取得
     * 
     * @return 発売日４
     */
    public FormDate getHbiDte4() {

        return this.hbiDte4;

    }


    /**
     * 発売日５のセット
     * 
     * @param hbiDte5 発売日５
     */
    public void setHbiDte5(FormDate hbiDte5) {

        this.hbiDte5 = hbiDte5;

    }


    /**
     * 発売日５の取得
     * 
     * @return 発売日５
     */
    public FormDate getHbiDte5() {

        return this.hbiDte5;

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
     * 記号番号１のセット
     * 
     * @param kigBng1 記号番号１
     */
    public void setKigBng1(KigBng kigBng1) {

        this.kigBng1 = kigBng1;

    }


    /**
     * 記号番号１の取得
     * 
     * @return 記号番号１
     */
    public KigBng getKigBng1() {

        return this.kigBng1;

    }


    /**
     * 記号番号２のセット
     * 
     * @param kigBng2 記号番号２
     */
    public void setKigBng2(KigBng kigBng2) {

        this.kigBng2 = kigBng2;

    }


    /**
     * 記号番号２の取得
     * 
     * @return 記号番号２
     */
    public KigBng getKigBng2() {

        return this.kigBng2;

    }


    /**
     * 記号番号３のセット
     * 
     * @param kigBng3 記号番号３
     */
    public void setKigBng3(KigBng kigBng3) {

        this.kigBng3 = kigBng3;

    }


    /**
     * 記号番号３の取得
     * 
     * @return 記号番号３
     */
    public KigBng getKigBng3() {

        return this.kigBng3;

    }


    /**
     * 記号番号４のセット
     * 
     * @param kigBng4 記号番号４
     */
    public void setKigBng4(KigBng kigBng4) {

        this.kigBng4 = kigBng4;

    }


    /**
     * 記号番号４の取得
     * 
     * @return 記号番号４
     */
    public KigBng getKigBng4() {

        return this.kigBng4;

    }


    /**
     * 記号番号５のセット
     * 
     * @param kigBng5 記号番号５
     */
    public void setKigBng5(KigBng kigBng5) {

        this.kigBng5 = kigBng5;

    }


    /**
     * 記号番号５の取得
     * 
     * @return 記号番号５
     */
    public KigBng getKigBng5() {

        return this.kigBng5;

    }


    /**
     * 記号番号６のセット
     * 
     * @param kigBng6 記号番号６
     */
    public void setKigBng6(KigBng kigBng6) {

        this.kigBng6 = kigBng6;

    }


    /**
     * 記号番号６の取得
     * 
     * @return 記号番号６
     */
    public KigBng getKigBng6() {

        return this.kigBng6;

    }


    /**
     * 記号番号７のセット
     * 
     * @param kigBng7 記号番号７
     */
    public void setKigBng7(KigBng kigBng7) {

        this.kigBng7 = kigBng7;

    }


    /**
     * 記号番号７の取得
     * 
     * @return 記号番号７
     */
    public KigBng getKigBng7() {

        return this.kigBng7;

    }


    /**
     * 記号番号８のセット
     * 
     * @param kigBng8 記号番号８
     */
    public void setKigBng8(KigBng kigBng8) {

        this.kigBng8 = kigBng8;

    }


    /**
     * 記号番号８の取得
     * 
     * @return 記号番号８
     */
    public KigBng getKigBng8() {

        return this.kigBng8;

    }


    /**
     * 記号番号９のセット
     * 
     * @param kigBng9 記号番号９
     */
    public void setKigBng9(KigBng kigBng9) {

        this.kigBng9 = kigBng9;

    }


    /**
     * 記号番号９の取得
     * 
     * @return 記号番号９
     */
    public KigBng getKigBng9() {

        return this.kigBng9;

    }


    /**
     * 記号番号１０のセット
     * 
     * @param kigBng10 記号番号１０
     */
    public void setKigBng10(KigBng kigBng10) {

        this.kigBng10 = kigBng10;

    }


    /**
     * 記号番号１０の取得
     * 
     * @return 記号番号１０
     */
    public KigBng getKigBng10() {

        return this.kigBng10;

    }


    /**
     * 記号番号１１のセット
     * 
     * @param kigBng11 記号番号１１
     */
    public void setKigBng11(KigBng kigBng11) {

        this.kigBng11 = kigBng11;

    }


    /**
     * 記号番号１１の取得
     * 
     * @return 記号番号１１
     */
    public KigBng getKigBng11() {

        return this.kigBng11;

    }


    /**
     * 記号番号１２のセット
     * 
     * @param kigBng12 記号番号１２
     */
    public void setKigBng12(KigBng kigBng12) {

        this.kigBng12 = kigBng12;

    }


    /**
     * 記号番号１２の取得
     * 
     * @return 記号番号１２
     */
    public KigBng getKigBng12() {

        return this.kigBng12;

    }


    /**
     * 記号番号１３のセット
     * 
     * @param kigBng13 記号番号１３
     */
    public void setKigBng13(KigBng kigBng13) {

        this.kigBng13 = kigBng13;

    }


    /**
     * 記号番号１３の取得
     * 
     * @return 記号番号１３
     */
    public KigBng getKigBng13() {

        return this.kigBng13;

    }


    /**
     * 記号番号１４のセット
     * 
     * @param kigBng14 記号番号１４
     */
    public void setKigBng14(KigBng kigBng14) {

        this.kigBng14 = kigBng14;

    }


    /**
     * 記号番号１４の取得
     * 
     * @return 記号番号１４
     */
    public KigBng getKigBng14() {

        return this.kigBng14;

    }


    /**
     * 記号番号１５のセット
     * 
     * @param kigBng15 記号番号１５
     */
    public void setKigBng15(KigBng kigBng15) {

        this.kigBng15 = kigBng15;

    }


    /**
     * 記号番号１５の取得
     * 
     * @return 記号番号１５
     */
    public KigBng getKigBng15() {

        return this.kigBng15;

    }
    
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
		this.setRb_select(SELECT_HBI);
		this.setKbn(SINPU);
		
		ArrayList hbiDte = this.getHbiDte_List();
		ArrayList kigBng = this.getKigBng_List();
		
		for(int i = 0; i<hbiDte.size(); i++){
			((FormDate)hbiDte.get(i)).clear();
		}
		for(int i = 0; i<kigBng.size(); i++){
			((KigBng)kigBng.get(i)).clear();
		}
		kaiSkbCod = null;
		ketCod = null;
		mkrBun = null;
		hyoKbn = null;
		this.setRb_default();
		this.setCheckBoxValueToArray();
		
		this.cpPaste="";//2004/06/29 add
  	}
	
	
	//ラジオボタンをデフォルトにセットするメソッド
	public void setRb_default(){
		
		setRb_kaiSkbCod(RB_SELECT);
		setRb_ketCod(RB_SELECT);
		setRb_mkrBun(RB_ALL);
		setRb_hyoKbn(RB_ALL);
		
		setSort_ketCod(false);
		setSort_sortKey(true);
		
	}
	//チェックボックスをリセットするメソッド
	public void setCheck_false(){
		
		setSort_ketCod(false);
		setSort_sortKey(false);
		
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
		
		ArrayList hbiDte = this.getHbiDte_List();
		ArrayList kigBng = this.getKigBng_List();
		
		//プレス発注 または 副資材発注のとき
		if(command.equals(COMMAND_PRSHACHU) || command.equals(COMMAND_FUKUHACHU)){
			

			if(rb_select == SELECT_HBI){
				//会社識別コード
				if(!rb_kaiSkbCod){ //falseならばSELECT
					if(kaiSkbCod == null){
						errors.add("",new ActionError("errors.input.required","会社識別コード"));		
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
				if(this.hbiDte_IsBlank(hbiDte)){
					errors.add("",new ActionError("errors.input.required","発売日"));
				}else{
					//セットで入力
					this.hbiDte_IsSet(hbiDte,"発売日",errors);
					//日付型で入力
					this.hbiDte_IsDate(hbiDte,"発売日",errors);
				}
			}
			if(rb_select == SELECT_HIN){
				if(this.kigBng_IsBlank(kigBng)){
					errors.add("",new ActionError("errors.input.required","追加品番"));
					return errors;
				}else{
					//半角英数のみ
					this.kigBng_IsAscii(kigBng,"品番",errors);
				}
			}
		}

		if(command.equals(COMMAND_PRSHACHU)){	//2004.03.08 add
			if(this.getKbn().equals("5")){
				errors.add("",new ActionError("errors.input.prohibited","プレス発注の場合、区分のその他"));
				return errors;
			}
		}

		return errors.empty() ? null : errors;		
	}
	
//--入力チェックメソッド------------------------------------------------------------------------------
	
	/** 発売日がすべてブランクかどうかチェックするメソッド */
	public boolean hbiDte_IsBlank(ArrayList hbiDte){
		for(int i = 0; i<hbiDte.size(); i++){
			FormDate _hbiDte = (FormDate)hbiDte.get(i);
			if(!_hbiDte.isBlank())
				return false;
		}
		return true;
	}
	/** 発売日がセットで入力されてるかどうかチェックするメソッド */
	public void hbiDte_IsSet(ArrayList hbiDte, String param, ActionErrors errors){
		for(int i = 0; i<hbiDte.size(); i++){
			FormDate _hbiDte = (FormDate)hbiDte.get(i);
			if(!(_hbiDte.isSet() || _hbiDte.isBlank())){
				String str = param + "(" + (i+1) + "番目)";
				errors.add("",new ActionError("errors.input.1",str,"セット"));
			}
		}
	}
	/** 発売日が日付で入力されてるかどうかチェックするメソッド */
	public void hbiDte_IsDate(ArrayList hbiDte, String param, ActionErrors errors){
		for(int i = 0; i<hbiDte.size(); i++){
			FormDate _hbiDte = (FormDate)hbiDte.get(i);
			if(_hbiDte.isSet() && !_hbiDte.isDate()){
				String str = param + "(" + (i+1) + "番目)";
				errors.add("",new ActionError("errors.input.1",str,"日付"));
			}
		}
	}
	
	
	/** 追加品番（記号番号）がすべてブランクかどうかチェックするメソッド */
	public boolean kigBng_IsBlank(ArrayList kigBng){		
		for(int i = 0; i<kigBng.size(); i++){
			KigBng _kigBng = (KigBng)kigBng.get(i);
			if(!_kigBng.isBlank()){
				return false;
			}
		}
		return true;
	}
	/** 追加品番（記号番号）半角英数かどうかチェックするメソッド */
	public void kigBng_IsAscii(ArrayList kigBng, String param, ActionErrors errors){
		for(int i = 0; i<kigBng.size(); i++){
			KigBng _kigBng = (KigBng)kigBng.get(i);	
			if(!_kigBng.isBlank() && !_kigBng.isAscii()){
				String str = param + "(" + (i+1) + "番目)";
				errors.add("",new ActionError("errors.input.1",str,"半角英数"));
			}
		}
	}
	
	
	
	//発売日をArrayListにセットして返す
	public ArrayList getHbiDte_List(){
		ArrayList hbiDte = new ArrayList();
		hbiDte.add(getHbiDte1());hbiDte.add(getHbiDte2());hbiDte.add(getHbiDte3());
		hbiDte.add(getHbiDte4());hbiDte.add(getHbiDte5());
		return hbiDte;
	}
	//記号番号をArrayListにセットして返す
	public ArrayList getKigBng_List(){
		ArrayList kigBng = new ArrayList();
		kigBng.add(getKigBng1());kigBng.add(getKigBng2());
		kigBng.add(getKigBng3());kigBng.add(getKigBng4());
		kigBng.add(getKigBng5());kigBng.add(getKigBng6());
		kigBng.add(getKigBng7());kigBng.add(getKigBng8());
		kigBng.add(getKigBng9());kigBng.add(getKigBng10());
		kigBng.add(getKigBng11());kigBng.add(getKigBng12());
		kigBng.add(getKigBng13());kigBng.add(getKigBng14());
		kigBng.add(getKigBng15());
		return kigBng;
	}

	//2004/07/02 add
	public void setKigBng_List(ArrayList list){

		for(int i=0;i<list.size();i++){

			KigBng kigbng = new KigBng((String)list.get(i));

			if(getKigBng1().isBlank()){
				setKigBng1(kigbng);
				continue;
			}
			if(getKigBng2().isBlank()){
				setKigBng2(kigbng);
				continue;
			}
			if(getKigBng3().isBlank()){
				setKigBng3(kigbng);
				continue;
			}
			if(getKigBng4().isBlank()){
				setKigBng4(kigbng);
				continue;
			}
			if(getKigBng5().isBlank()){
				setKigBng5(kigbng);
				continue;
			}
			if(getKigBng6().isBlank()){
				setKigBng6(kigbng);
				continue;
			}
			if(getKigBng7().isBlank()){
				setKigBng7(kigbng);
				continue;
			}
			if(getKigBng8().isBlank()){
				setKigBng8(kigbng);
				continue;
			}
			if(getKigBng9().isBlank()){
				setKigBng9(kigbng);
				continue;
			}
			if(getKigBng10().isBlank()){
				setKigBng10(kigbng);
				continue;
			}
			if(getKigBng11().isBlank()){
				setKigBng11(kigbng);
				continue;
			}
			if(getKigBng12().isBlank()){
				setKigBng12(kigbng);
				continue;
			}
			if(getKigBng13().isBlank()){
				setKigBng13(kigbng);
				continue;
			}
			if(getKigBng14().isBlank()){
				setKigBng14(kigbng);
				continue;
			}
			if(getKigBng15().isBlank()){
				setKigBng15(kigbng);
				continue;
			}

		}

	}

	//記号番号をArrayListにセットして返す
	public ArrayList getKigBngStr_List(){
		ArrayList kigBng = new ArrayList();
		kigBng.add(getKigBng1().getKigBng());kigBng.add(getKigBng2().getKigBng());
		kigBng.add(getKigBng3().getKigBng());kigBng.add(getKigBng4().getKigBng());
		kigBng.add(getKigBng5().getKigBng());kigBng.add(getKigBng6().getKigBng());
		kigBng.add(getKigBng7().getKigBng());kigBng.add(getKigBng8().getKigBng());
		kigBng.add(getKigBng9().getKigBng());kigBng.add(getKigBng10().getKigBng());
		kigBng.add(getKigBng11().getKigBng());kigBng.add(getKigBng12().getKigBng());
		kigBng.add(getKigBng13().getKigBng());kigBng.add(getKigBng14().getKigBng());
		kigBng.add(getKigBng15().getKigBng());
		return kigBng;
	}
	

//---------------------------------------------------------------------------------------------------
	
	public void reset(ActionMapping maping,HttpServletRequest req){
//System.out.println("reset : "+command);
		this.setCheck_false();//チェックボックスをリセット
		if(!command.equals(COMMAND_PRSHACHU) && !command.equals(COMMAND_FUKUHACHU))
			this.command = "INIT";
	}
	
//---------------------------------------------------------------------------------------------------

  //ランクリスト作成
  static String[] makeRank(String daiKaiSkbCod){
	  if(daiKaiSkbCod.equals(com.jds.prodord.common.CommonConst.KAICOD_K)){
		  String[] RANK = {"N0","N1","N2","N3","S","A","B","C"};
		  return RANK;
	  }else{
		  String[] RANK = {"N0","N1","N2","N3","S","A","B","C","D"};
		  return RANK;
	  }
  }

	
	/**日付クラス*/
	public class FormDate {
		private String year = "";	
		private String month = "";	
		private String day = "";	
		
		/**
	     * 年のセット
	     * 
	     * @param year 年
	     */
	    public void setYear(String year) {
	        this.year = year;
	    }
	    /**
	     * 年の取得
	     * 
	     * @return 年
	     */
	    public String getYear() {
	        return this.year;
	    }
	    /**
	     * 月のセット
	     * 
	     * @param month 月
	     */
	    public void setMonth(String month) {
	        this.month = month;
	    }
	    /**
	     * 月の取得
	     * 
	     * @return 月
	     */
	    public String getMonth() {
	        return this.month;
	    }
	    /**
	     * 日のセット
	     * 
	     * @param day 日
	     */
	    public void setDay(String day) {
	        this.day = day;
	    }
	    /**
	     * 日の取得
	     * 
	     * @return 日
	     */
	    public String getDay() {
	        return this.day;
	    }
	    
	    public boolean isBlank(){
	    	
			if(!this.year.trim().equals("")){
				return false;
			}
			if(!this.month.trim().equals("")){
				return false;
			}
			if(!this.day.trim().equals("")){
				return false;
			}
			return true;
	    }
		
		public boolean isSet(){
	    	return (!this.year.trim().equals("") && !this.month.trim().equals("") && !this.day.trim().equals(""));
	    }
	    
	    public boolean isDate(){
	    	return CheckCommon.validateAsDate(this.year.trim(),this.month.trim(),this.day.trim());
	    }
	    
	    public void clear(){
	    	year = "";	
			month = "";	
			day = "";	    	
	    }
	    
	}
	/**記号番号クラス*/
	public class KigBng {
		private String kigBng = "";
		
		public KigBng(){
		}
		
		public KigBng(String kigBng){
			this.kigBng = kigBng;
		}
		
		/**
	     * 記号番号のセット
	     * 
	     * @param kigBng 記号番号
	     */
	    public void setKigBng(String kigBng) {
	
	        this.kigBng = kigBng;
	
	    }
	    /**
	     * 記号番号の取得
	     * 
	     * @return 記号番号
	     */
	    public String getKigBng() {
	
	        return this.kigBng.trim();
	
	    }
	    public boolean isBlank(){
	    	return this.kigBng.trim().equals("");
	    }
	    public boolean isAscii(){
	    	return StringUtils.isAscii(this.kigBng.trim());
	    }
	    public void clear(){
	    	kigBng = "";   	
	    }
	}

	//2005/02/03
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

}



