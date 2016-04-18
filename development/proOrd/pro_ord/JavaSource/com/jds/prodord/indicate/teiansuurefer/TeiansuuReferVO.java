/**
* TeiansuuReferVO  提案数照会指示バリューオブジェクトクラス
*
*	作成日    2003/02/20
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    画面から取得したデータを格納するクラス。
*
*	[変更履歴]
*		2005/02/03（ＮＩＩ）蛭田
*			・場所コードの追加
*		2005/05/23（ＮＩＩ）蛭田
*			・ユーザーＩＤの追加
*/

package com.jds.prodord.indicate.teiansuurefer;

import java.util.*;
public class TeiansuuReferVO {

	private String command = "";

	private String daiKaiSkbCod = "";
	private String kaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private ArrayList kaiSkbCod_arr = new ArrayList();
	private ArrayList rank_arr = new ArrayList();
	private ArrayList ketCod_arr = new ArrayList();
	private ArrayList mkrBun_arr = new ArrayList();
	private ArrayList hyoKbn_arr = new ArrayList();
	private ArrayList kigBng_arr = new ArrayList();
	
	private boolean sort_rank = false;
	private boolean sort_ketCod = false;
	private boolean sort_sortKey = false;
	
	private boolean hitaisyo = false;
	private String taisyoRange = "";
	
	private String bshCod = "";
	private String usrId = "";

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
    public void setKaiSkbCod(String kaiSkbCod) {

        this.kaiSkbCod = kaiSkbCod;

    }


    /**
     * ユーザーの会社識別コードの取得
     * 
     * @return ユーザーの会社識別コード
     */
    public String getKaiSkbCod() {

        return this.kaiSkbCod;

    }
    
    //会社識別コードリスト
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }
    
//-------------------------------------------------------------入力エリアの配列

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
     * ランクのセット
     * 
     * @param rank_arr ランク
     */
    public void setRank_arr(ArrayList rank_arr) {

        this.rank_arr = rank_arr;

    }


    /**
     * ランクの取得
     * 
     * @return ランク
     */
    public ArrayList getRank_arr() {

        return this.rank_arr;

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
//-------------------------------------------------------------ソート順

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
	
	
	public final static int TAISYO_ONLY = 0;
	public final static int HITAISYO_ONLY = 1;
	/**
	 * Gets the taisyoDataKbn
	 * @return Returns a int
	 */
	public int getTaisyoDataKbn(){
		if(!this.hitaisyo)
			return TAISYO_ONLY;
		else{
			return HITAISYO_ONLY;
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

