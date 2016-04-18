/**
* ManualOrderVO  マニュアル発注指示バリューオブジェクトクラス
*
*	作成日    2003/04/25
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    品番マスター(FTBHIN01)、在庫テーブル(FTBZAI01)、発注履歴テーブル(FTBHAC02)、
* 			 副資材マスター(FTBFUK01)、副資材パターンマスター(FTBFUK02)から取得したデータを格納するクラス。
*
*	[変更履歴]
*       2003/06/16（ＮＩＩ）蛭田 敬子
*  			・条件項目（会社・メーカー分類・形態コード・邦洋区分）の追加。
* 		2004/03/29 (NII) 森
* 			・品番マスタのテーブル名変更
*		2005/02/03（ＮＩＩ）蛭田
*			・場所コードの追加
*		2005/05/25（ＮＩＩ）蛭田
*			・ユーザーＩＤの追加
*/

package com.jds.prodord.indicate.manualorder;

import java.util.*;
import com.jds.prodord.order.prsorder.*;
public class ManualOrderVO {

	private String command = "";

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String bshCod = "";
	private String usrId = "";
	
	private ArrayList hbiDte_arr = new ArrayList();
	private int hbiDte1 = 0; 
	private int hbiDte2 = 0; 
	private int hbiDte3 = 0; 
	private int hbiDte4 = 0; 
	private int hbiDte5 = 0; 
	
	private String kbn = "";
	
	private ArrayList kigBng_arr = new ArrayList();

	private ArrayList ketCod_arr = new ArrayList();
	private ArrayList mkrBun_arr = new ArrayList();
	private ArrayList hyoKbn_arr = new ArrayList();
	private ArrayList kaiSkbCod_arr = new ArrayList();

	private String fukSziMkr ="";
	private String fukSziHacSaki ="";
	private String fukSziNm ="";
	private String fukSziCod ="";
	private String fukSziPtnCod ="";

	
	private boolean exsitFuk01 = false;
	private ArrayList fukSziCod_arr = new ArrayList();


	//プレス発注画面に渡すVO
	private	PrsOrderVO[] prsVOs = null;
	
	private boolean sort_ketCod = false;
	private boolean sort_sortKey = false;

	
    /**
     * コマンドのセット
     * 
     * @param command コマンド
     */
    public void setCommand(String command) {

        this.command = command;

    }


    /**
     * コマンドの取得
     * 
     * @return コマンド
     */
    public String getCommand() {

        return this.command;

    }

    /**
     * ユーザーの代表会社識別コードのセット
     * 
     * @param daiKaiSkbCod ユーザーの表会社識別コード
     */
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {

        this.daiKaiSkbCod = daiKaiSkbCod;

    }


    /**
     * ユーザーの代表会社識別コードの取得
     * 
     * @return ユーザーの代表会社識別コード
     */
    public String getDaiKaiSkbCod() {

        return this.daiKaiSkbCod;

    }
    
    /**
     * ユーザーの会社識別コードのセット
     * 
     * @param kaiSkbCod ユーザーの会社識別コード
     */
    public void setQueryKaiSkbCod(String queryKaiSkbCod) {

        this.queryKaiSkbCod = queryKaiSkbCod;

    }


    /**
     * ユーザーの会社識別コードの取得
     * 
     * @return ユーザーの会社識別コード
     */
    public String getQueryKaiSkbCod() {

        return this.queryKaiSkbCod;

    }
    
    //会社識別コードリスト
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }
    

//--------------------------------------------------------------------------

    
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
     * 発売日1
     * 
     */
	public int getHbiDte1() {
		return hbiDte1;
	}
	public void setHbiDte1(ManualOrderForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hbiDte1 = 0;
		else
			hbiDte1 = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /** 
     * 発売日2
     * 
     */
	public int getHbiDte2() {
		return hbiDte2;
	}
	public void setHbiDte2(ManualOrderForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hbiDte2 = 0;
		else
			hbiDte2 = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /** 
     * 発売日3
     * 
     */
	public int getHbiDte3() {
		return hbiDte3;
	}
	public void setHbiDte3(ManualOrderForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hbiDte3 = 0;
		else
			hbiDte3 = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /** 
     * 発売日4
     * 
     */
	public int getHbiDte4() {
		return hbiDte4;
	}
	public void setHbiDte4(ManualOrderForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hbiDte4 = 0;
		else
			hbiDte4 = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    /** 
     * 発売日5
     * 
     */
	public int getHbiDte5() {
		return hbiDte5;
	}
	public void setHbiDte5(ManualOrderForm.FormDate date){
		String year = date.getYear().trim();
		String month = date.getMonth().trim();
		String day = date.getDay().trim();
		if(year.equals("") || month.equals("") || day.equals(""))
			hbiDte5 = 0;
		else
			hbiDte5 = (Integer.parseInt(year,10)*10000) + (Integer.parseInt(month,10)*100) + Integer.parseInt(day,10);
	}
    
//-------------------------------------------------------------入力エリアの配列


    /**
     * 発売日のセット
     * 
     * @param hbiDte_arr 発売日
     */
    public void setHbiDte_arr(ArrayList hbiDte_arr) {

        this.hbiDte_arr = hbiDte_arr;
   
    }


    /**
     * 発売日の取得
     * 
     * @return 発売日
     */
    public ArrayList getHbiDte_arr() {
    	hbiDte_arr.clear();
    	if(hbiDte1 != 0)
	    	hbiDte_arr.add(hbiDte1+"");
	    if(hbiDte2 != 0)
	    	hbiDte_arr.add(hbiDte2+"");
	    if(hbiDte3 != 0)
	    	hbiDte_arr.add(hbiDte3+"");
	    if(hbiDte4 != 0)
	    	hbiDte_arr.add(hbiDte4+"");
	    if(hbiDte5 != 0)
	    	hbiDte_arr.add(hbiDte5+"");

        return this.hbiDte_arr;

    }



    /**
     * 記号番号のセット
     * 
     * @param kigBng_arr 記号番号
     */
    public void setKigBng_arr(ArrayList kigBng_arr) {

        this.kigBng_arr = kigBng_arr;
   
    }


    /**
     * 記号番号の取得
     * 
     * @return 記号番号
     */
    public ArrayList getKigBng_arr() {

        return this.kigBng_arr;

    }
    
//2003/06/16
    /**
     * 会社識別コードのセット
     * 
     * @param kaiSkbCod_arr 会社識別コード
     */
    public void setKaiSkbCod_arr(ArrayList kaiSkbCod_arr) {

        this.kaiSkbCod_arr = kaiSkbCod_arr;

    }


    /**
     * 会社識別コードの取得
     * 
     * @return 会社識別コード
     */
    public ArrayList getKaiSkbCod_arr() {

        return this.kaiSkbCod_arr;

    }
    
	/**
     * 形態コードのセット
     * 
     * @param ketCod_arr 形態コード
     */
    public void setKetCod_arr(ArrayList ketCod_arr) {

        this.ketCod_arr = ketCod_arr;

    }


    /**
     * 形態コードの取得
     * 
     * @return 形態コード
     */
    public ArrayList getKetCod_arr() {

        return this.ketCod_arr;

    }


    /**
     * メーカー分類のセット
     * 
     * @param mkrBun_arr メーカー分類
     */
    public void setMkrBun_arr(ArrayList mkrBun_arr) {

        this.mkrBun_arr = mkrBun_arr;

    }


    /**
     * メーカー分類の取得
     * 
     * @return メーカー分類
     */
    public ArrayList getMkrBun_arr() {

        return this.mkrBun_arr;

    }


    /**
     * 邦洋区分のセット
     * 
     * @param hyoKbn_arr 邦洋区分
     */
    public void setHyoKbn_arr(ArrayList hyoKbn_arr) {

        this.hyoKbn_arr = hyoKbn_arr;

    }


    /**
     * 邦洋区分の取得
     * 
     * @return 邦洋区分
     */
    public ArrayList getHyoKbn_arr() {

        return this.hyoKbn_arr;

    }    



    //-----------------------------------------------------------------------------------
    

	/**
     * PrsOrderVO[]のセット
     * 
     * @param prsVOs PrsOrderVO[]
     */
    public void setPrsVOs(PrsOrderVO[] prsVOs) {

        this.prsVOs = prsVOs;
   
    }


    /**
     * PrsOrderVO[]の取得
     * 
     * @return PrsOrderVO[]
     */
    public PrsOrderVO[] getPrsVOs() {

        return this.prsVOs;

    }
    //--------------------------------------------------------------------------------------------
    
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

