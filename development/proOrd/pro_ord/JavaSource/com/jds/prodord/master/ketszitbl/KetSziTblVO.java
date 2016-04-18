/**
* KetSziTblVO  形態別構成資材マスターメンテナンスバリューオブジェクトクラス
*
*	作成日    2004/02/01
*	作成者    （ＮＩＩ）森
* 処理概要    画面から取得したデータを格納するクラス。
*
*	[変更履歴]
*             日付 名前
*  ・内容
*/

package com.jds.prodord.master.ketszitbl;


import java.util.*;
import com.jds.prodord.common.*;

public class KetSziTblVO  {
	
	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	
	private String kaiSkbCod = new String();
	private String ketCod = new String();
	private String ketCodList[] = null;
	private String ketNm = new String();
	private String ketNm2 = new String();

	private String fuksziCodString = "";
		
	private int updDte;
	private int updJkk;

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
	 * 形態コードリストのセット
	 * 
	 * @param ketCodlist[] 形態コードリスト
	 */
	public String[] getKetCodList() {
		return ketCodList;
	}

	/**
	 * 形態コードリストの取得
	 * 
	 * @return 形態コードリスト
	 */
	public void setKetCodList(String[] strings) {
		ketCodList = strings;
	}

    /**
     * 形態コードのセット
     * 
     * @param ketCod 形態コード
     */
	public void setKetCod(String ketCod) {
		this.ketCod = ketCod;
	}
    /**
     * 形態コードの取得
     * 
     * @return 形態コード
     */
	public String getKetCod() {
		return ketCod;
	}

   /**
  	* 形態名称の取得
  	* 
  	* @return 形態名称
  	*/
	public String getKetNm() {
		return ketNm;
 	}
 	/**
  	* 形態名称のセット
  	* 
  	* @param 形態名称
  	*/
 	public void setKetNm(String ketNm) {
	 	this.ketNm = ketNm;
 	}

 	/**
  	* 形態名称2の取得
  	* 
  	* @return 形態名称2
  	*/
 	public String getKetNm2() {
	 	return ketNm2;
 	}
 	/**
  	* 形態名称２のセット
  	* 
  	* @param 形態名称2
  	*/
 	public void setKetNm2(String ketNm2) {
	 	this.ketNm2 = ketNm2;
 	}

	/**
	 * @return
	 */
	public String getFuksziCodString() {
		return fuksziCodString;
	}
	public void setFuksziCodString(String sziTbl) {
		fuksziCodString = sziTbl;
	}

	/**
	 * @param string
	 */
	public void setFuksziCodString(KetSziTblFormRow.FuksziCod[] fuksziCod) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<fuksziCod.length;i++){
			sb.append(StringUtils.rpad(fuksziCod[i].getVal(),3," "));
		}
		fuksziCodString = sb.toString();
	}
	
	public void removeFukusziCodSpace(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<fuksziCodString.length(); i+=3){
			sb.append(fuksziCodString.substring(i, i+3).trim());
		}
		fuksziCodString = sb.toString();		
	}

	public ArrayList getFuksziCodArr(){
		ArrayList fuksziCod = new ArrayList();
		for(int i = 0; i<fuksziCodString.length(); i+=3){
			try{
				fuksziCod.add(fuksziCodString.substring(i, i+3));
			}catch(IndexOutOfBoundsException e){
				break;
			}
		}
		return fuksziCod;
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



}


