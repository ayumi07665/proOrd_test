/**
* PrintOrdersQueryVO  発注書印刷 検索条件用バリューオブジェクトクラス
*
*	作成日    2003/04/18
*	作成者    （ＮＩＩ）岡田 夏美
* 処理概要    発注書データ検索条件用のデータを格納するクラス。
*
*	[変更履歴]
* 		2005/09/14（ＮＩＩ）蛭田
* 			・訂正伝票発行フラグの追加
*/

package com.jds.prodord.print.printorders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.jds.prodord.common.DataFormatUtils;
import com.jds.prodord.common.StringUtils;
import com.jds.prodord.common.SystemException;

public class PrintOrdersQueryVO  {

	private String daiKaiSkbCod = "";
	private String kaiSkbCod = "";
	private ArrayList kaiSkbCod_arr = new ArrayList();
	private String syrSuu = "";
	
	private ArrayList partOfQuery_arr = new ArrayList();
	private boolean hasHacSyoBng = false;
	private boolean teiDnpHakkou = false;
	private int updDte;
	private int updJkk;
	

    /**
     * 代表会社識別コードのセット
     * 
     * @param daiKaiSkbCod 代表会社識別コード
     */
    public void setDaiKaiSkbCod(String daiKaiSkbCod) {

        this.daiKaiSkbCod = daiKaiSkbCod;

    }


    /**
     * 代表会社識別コードの取得
     * 
     * @return 代表会社識別コード
     */
    public String getDaiKaiSkbCod() {

        return this.daiKaiSkbCod;

    }
 

	/**
	 * 会社識別コードのセット
	 * 
	 * @param kaiSkbCod 会社識別コード
	 */
	public void setKaiSkbCod(String kaiSkbCod) {

		this.kaiSkbCod = kaiSkbCod;

	}


	/**
	 * 会社識別コードの取得
	 * 
	 * @return 会社識別コード
	 */
	public String getKaiSkbCod() {

		return this.kaiSkbCod;

	}
 
 
    /**
     * 会社識別コードarrのセット
     * 
     * @param kaiSkbCod_arr 会社識別コードarr
     */
    public void setKaiSkbCod_arr(ArrayList kaiSkbCod_arr) {

        this.kaiSkbCod_arr = kaiSkbCod_arr;

    }


    /**
     * 会社識別コードarrの取得
     * 
     * @return 会社識別コードarr
     */
    public ArrayList getKaiSkbCod_arr() {

        return this.kaiSkbCod_arr;

    }


	/**
     * 処理回数のセット
     * 
     * @param syrSuu 処理回数
     */
	public void setSyrSuu(String s){
		
		this.syrSuu = StringUtils.lpad(s,6,"0");
		
	}
	/**
     * 処理回数の取得
     * 
     * @return 処理回数
     */
	public String getSyrSuu(){
		
		return this.syrSuu;
		
	}
	
    
    /**
	 * Gets the hasHacSyoBng
	 * @return Returns a boolean
	 */
	public boolean getHasHacSyoBng() {
		return hasHacSyoBng;
	}
	/**
	 * Sets the hasHacSyoBng
	 * @param hasHacSyoBng The hasHacSyoBng to set
	 */
	public void setHasHacSyoBng(boolean hasHacSyoBng) {
		this.hasHacSyoBng = hasHacSyoBng;
	}


	/**
	 * 訂正伝票発行フラグの取得
	 * @return
	 */
	public boolean isTeiDnpHakkou() {
		return teiDnpHakkou;
	}

	/**
	 * 訂正伝票発行フラグの設定
	 * @param b
	 */
	public void setTeiDnpHakkou(boolean b) {
		teiDnpHakkou = b;
	}


	/**
	 * 更新日付を返します。
	 * 発注履歴照会：訂正伝票発行から来たときは、発注履歴照会画面から渡された日付を返す
	 * @return
	 */
	public int getUpdDte() {
		if(updDte == 0){//更新日付がセットされていなかったらここで取得
			Date sysdate = new Date(System.currentTimeMillis());
			updDte = Integer.parseInt((new SimpleDateFormat("yyMMdd")).format(sysdate),10);
		}
		return updDte;
	}

	/**
	 * 更新時刻を返します。
	 * 発注履歴照会：訂正伝票発行から来たときは、発注履歴照会画面から渡された時刻を返す
	 * @return
	 */
	public int getUpdJkk() {
		if(updJkk == 0){//更新時刻がセットされていなかったらここで取得
			Date sysdate = new Date(System.currentTimeMillis());
			updJkk = Integer.parseInt((new SimpleDateFormat("HHmmss")).format(sysdate),10);
		}
		return updJkk;
	}

	/**
	 * @param i
	 */
	public void setUpdDte(int i) {
		updDte = i;
	}

	/**
	 * @param i
	 */
	public void setUpdJkk(int i) {
		updJkk = i;
	}

//--------------------------------------------------------------------------------   

	/**
	 * Sets the partOfQuery_arr
	 * @param partOfQuery_arr The partOfQuery_arr to set
	 * (KAISKBCOD%HACSYODTE%HACSYOBNG%HACCOD)
	 */
	public void setPartOfQuery_arr(ArrayList partOfQuery_arr) {
		this.partOfQuery_arr = partOfQuery_arr;
	}
	public ArrayList getPartOfQuery_arr() {
		return partOfQuery_arr;
	}

	public String getPartOfQuery(String tblNm){
				
		String query = "";
		try {
			for(int i = 0; i < partOfQuery_arr.size(); i++){
				String[] query_arr = StringUtils.split(partOfQuery_arr.get(i).toString(), "%");
				if(i == 0)
					query +=  " AND ((";
				query += tblNm + ".KAISKBCOD = '" + query_arr[0]
					+ "' AND " + tblNm + ".HACSYODTE = " + query_arr[1]
				    +  " AND " + tblNm + ".HACSYOBNG = '" + DataFormatUtils.formatHacSyoBng(query_arr[2].trim())
					+ "' AND " + tblNm + ".HACCOD = '" + query_arr[3] + "'";
				if(i == partOfQuery_arr.size() - 1)
					query += "))";
				else
					query += ") OR (";
					
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			SystemException se = new SystemException(e);
			se.printStackTrace();
			throw e;
		}
		return query;
	}

}

