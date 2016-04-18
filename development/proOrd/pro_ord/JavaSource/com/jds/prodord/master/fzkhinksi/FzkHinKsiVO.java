/**
* FzkHinKsiVO  付属品構成マスターメンテナンスバリューオブジェクトクラス
*
*	作成日    2004/02/13
*	作成者    （ＮＩＩ）森
* 処理概要    画面から取得したデータを格納するクラス。
*
*	[変更履歴]
*             日付 名前
* 		 2004/03/29 (NII) 森
* 			・品番マスタのテーブル名変更
* 		 2004/04/02 (NII) 森
* 			・副資材発注の修正(分類コード１複数存在に対応) 
* 		 2004/06/23（ＮＩＩ）蛭田
*			・｢発注先名称｣追加 
*/

package com.jds.prodord.master.fzkhinksi;

import java.util.*;
public class FzkHinKsiVO  {
	
	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiSkbCod = new String();
	private String hinban = new String();
	private String title = new String();
	private String hbidte = new String();
	private String ketNm = new String();
	private String ketCod = new String();
	private String setsuu = new String();
	private String fuksziCod = new String();
	private String fuksziNm = new String();
	private String sirSk = new String();
	private String hacNm = new String(); //2004/06/23 add

	private String bunCodString = new String();
	private String fuksziCod06String = "";
	private String fuksziCod08String = "";
	private String sirSk08String = "";

	private ArrayList linenoArr = null;
		
	private int updDte;
	private int updJkk;
	
	private boolean check_bx = false;
	private boolean exsitHin01 = false;
	private boolean exsitHin02 = false;
	private boolean exsitMst08 = false;

	private boolean flag = false;

    //ユーザーの代表会社識別コード
    public String getDaiKaiSkbCod() {
        return this.daiKaiSkbCod;
    }
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {
        this.daiKaiSkbCod = daiKaiSkbCod;
    }


    //ユーザーの会社識別コード
	public String getQueryKaiSkbCod() {
		return queryKaiSkbCod;
	}
	public void setQueryKaiSkbCod(String queryKaiSkbCod) {
		this.queryKaiSkbCod = queryKaiSkbCod;
	}

    
    //会社識別コードリスト
    public ArrayList getQueryKaiSkbCodList(){
    	return queryKaiSkbCodList;
    }
    public void setQueryKaiSkbCodList(ArrayList arr){
    	queryKaiSkbCodList = arr;
    }

    
//-------------------------------------------------------------入力エリア

    /**
     * 会社識別コードのセット
     * 
     * @param kaiSkbCod 会社識別コード
     */
    public String getKaiSkbCod() {

        return this.kaiSkbCod;

    }
   
    /**
     * 会社識別コードの取得
     * 
     * @return 会社識別コード
     */
    public void setKaiSkbCod(String kaiSkbCod) {

        this.kaiSkbCod = kaiSkbCod;

    }
 
	/**
	 * 品番の取得
	 * @return 品番
	 */
	public String getHinban() {
		return hinban;
	}
	/**
	 * 品番のセット
	 * @param hinban
	 */
	public void setHinban(String hinban) {
		this.hinban = hinban;
	}


	/**
	 * タイトルの取得
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * タイトルのセット
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 発売日の取得
	 * @return hbidte
	 */
	public String getHbidte() {
		return hbidte;
	}
	/**
	 * 発売日のセット
	 * @param hbidte
	 */
	public void setHbidte(String hbidte) {
		this.hbidte = hbidte;
	}

   /**
  	* 形態名称の取得
  	* 
  	* @return ketNm
  	*/
	public String getKetNm() {
		return ketNm;
 	}
 	/**
  	* 形態名称のセット
  	* 
  	* @param ketNm
  	*/
 	public void setKetNm(String ketNm) {
	 	this.ketNm = ketNm;
 	}

	/**
	 * 形態コードの取得
	 * 
	 * @return ketNm
	 */
	 public String getKetCod() {
		 return ketCod;
	 }
	 /**
	 * 形態コードのセット
	 * 
	 * @param ketNm
	 */
	 public void setKetCod(String ketCod) {
		 this.ketCod = ketCod;
	 }

	/**
	 * セット数の取得
	 * @return setsuu
	 */
	public String getSetsuu() {
		return setsuu;
	}
	/**
	 * セット数のセット
	 * @param setsuu
	 */
	public void setSetsuu(String setsuu) {
		this.setsuu = setsuu;
	}

	/**
	 * 副資材コードの取得
	 * @return fuksziCod
	 */
	public String getFuksziCod() {
		return fuksziCod;
	}
	/**
	 * 副資材コードのセット
	 * @param fuksziCod
	 */
	public void setFuksziCod(String fuksziCod) {
		this.fuksziCod = fuksziCod;
	}

	/**
	 * 副資材名称の取得
	 * @return fuksziNm
	 */
	public String getFuksziNm() {
		return fuksziNm;
	}
	/**
	 * 副資材名称のセット
	 * @param string
	 */
	public void setFuksziNm(String fuksziNm) {
		this.fuksziNm = fuksziNm;
	}


	/**
	 * 仕入先の取得
	 * @return sirSk
	 */
	public String getSirSk() {
		return sirSk;
	}
	/**
	 * 仕入先のセット
	 * @param sirSk
	 */
	public void setSirSk(String sirSk) {
		this.sirSk = sirSk;
	}

	/**
	 * チェックボックスのセット
	 * @param check_bx チェックボックス
	 */
	 public void setCheck_bx(boolean check_bx) {
		 this.check_bx = check_bx;
	 }

	/**
	* チェックボックスの取得
	* 
	* @return チェックボックス
	*/
	 public boolean getCheck_bx() {
		 return this.check_bx;
	 }

	/**
	 * Gets the updDte
	 * @return Returns a int
	 */
	public int getUpdDte() {
		return updDte;
	}
	/**
	 * Sets the updDte
	 * @param updDte The updDte to set
	 */
	public void setUpdDte(int updDte) {
		this.updDte = updDte;
	}


	/**
	 * Gets the updJkk
	 * @return Returns a int
	 */
	public int getUpdJkk() {
		return updJkk;
	}
	/**
	 * Sets the updJkk
	 * @param updJkk The updJkk to set
	 */
	public void setUpdJkk(int updJkk) {
		this.updJkk = updJkk;
	}

	/**
	 * 品番マスター(FTBHIN01)存在フラグのセット
	 * 
	 * @param exsitZai01 品番マスター(FTBHIN01)存在フラグ
	 */
	public void setExsitHin01(boolean exsitHin01) {
		this.exsitHin01 = exsitHin01;
	}
	/**
	 * 品番マスター(FTBHIN01)存在フラグの取得
	 * 
	 * @return 品番マスター(FTBHIN01)存在フラグ
	 */
	public boolean getExsitHin01() {
		return this.exsitHin01;
	}

	/**
	 * 付属品マスター(FTBMST08)存在フラグのセット
	 * @param exsitMst08 付属品マスター(FTBMST08)存在フラグ
	 */
	public void setExsitMst08(boolean exsitMst08) {
		this.exsitMst08 = exsitMst08;
	}
	/**
	 * 付属品マスター(FTBMST08)存在フラグの取得
	 * @return exsitMst08 付属品マスター(FTBMST08)存在フラグ
	 */
	public boolean getExsitMst08() {
		return this.exsitMst08;
	}


	/**
	 * @return
	 */
	public String getFuksziCod06String() {
		return fuksziCod06String;
	}
	public void setFuksziCod06String(String sziTbl) {
		fuksziCod06String = sziTbl;
	}

	/**
	 * @param string
	 */
	
	public void removeFukusziCod06Space(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<fuksziCod06String.length(); i+=3){
			sb.append(fuksziCod06String.substring(i, i+3).trim());
		}
		fuksziCod06String = sb.toString();		
	}

	public ArrayList get06FuksziCodArr(){
		ArrayList fuksziCod = new ArrayList();
		for(int i = 0; i<fuksziCod06String.length(); i+=3){
			try{
				fuksziCod.add(fuksziCod06String.substring(i, i+3));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return fuksziCod;
	}

	/**
	 * @return
	 */
	public String getFuksziCod08String() {
		return fuksziCod08String;
	}
	public void setFuksziCod08String(String sziTbl) {
		fuksziCod08String = sziTbl;
	}

	/**
	 * @param string
	 */
	
	public void removeFukusziCod08Space(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<fuksziCod08String.length(); i+=3){
			sb.append(fuksziCod08String.substring(i, i+3).trim());
		}
		fuksziCod08String = sb.toString();		
	}
	public ArrayList get08FuksziCodArr(){
		ArrayList fuksziCod = new ArrayList();
		for(int i = 0; i<fuksziCod08String.trim().length(); i+=3){
			try{
				fuksziCod.add(fuksziCod08String.substring(i, i+3));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return fuksziCod;
	}

	/**
	 * @return
	 */
	public String getSirSk08String() {
		return sirSk08String;
	}
	public void setSirSk08String(String sirTbl) {
		sirSk08String = sirTbl;
	}

	/**
	 * @param string
	 */
	
	public void removeSirSk08Space(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<sirSk08String.length(); i+=6){
			sb.append(sirSk08String.substring(i, i+6));
		}
		fuksziCod08String = sb.toString();		
	}
	public ArrayList get08SirSkCodArr(){
		ArrayList sirSk = new ArrayList();
		for(int i = 0; i<sirSk08String.length(); i+=6){
			try{
				sirSk.add(sirSk08String.substring(i, i+6));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return sirSk;
	}
	
	public ArrayList getBunCodArr(){
		ArrayList bunCod = new ArrayList();
		for(int i = 0; i<bunCodString.length(); i+=1){
			try{
				bunCod.add(bunCodString.substring(i, i+1));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return bunCod;
	}
	public void addBunCod(String bunCod){
		bunCodString += bunCod;
	}
	public void clearBunCod(){
		bunCodString = "";
	}

	/**
	 * 行番号の取得
	 * @return lineArr
	 */
	public ArrayList getLineArr() {
		return linenoArr;
	}

	/**
	 * 行番号のセット
	 * @param list
	 */
	public void setLineArr(ArrayList linenoArr) {
		this.linenoArr = linenoArr;
	}

	/**
	 * @return
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * @param b
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}


	/**
	 * 発注先名称の取得
	 * @return
	 */
	public String getHacNm() {
		return hacNm;
	}

	/**
	 * 発注先名称のセット
	 * @param string
	 */
	public void setHacNm(String string) {
		hacNm = string;
	}

}

