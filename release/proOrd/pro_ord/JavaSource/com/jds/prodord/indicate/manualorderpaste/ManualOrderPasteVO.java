package com.jds.prodord.indicate.manualorderpaste;

import java.util.*;
import com.jds.prodord.order.prsorder.*;
/**
 * ManualOrderPasteVO  マニュアル発注指示一括貼付バリューオブジェクトクラス。<BR>
 * <dt><b>作成日: </b></dt>
 * <dd>2007/09/10</dd>
 * <dt><B>作成者: </b></dt>
 * <dd>(NII)</dd>
 * <dt><B>処理概要: </b></dt>
 * <dd>処理結果を格納するクラス。</dd>
 * <br>
 * <b>変更履歴</b><br>
 * <table border="1">
 * 	<tr>
 * 		<th width="80">修正日付</th><th width="100">修正者</th><th width="300">修正内容</th>
 * 	</tr>
 * 	<tr>
 * 		<td>yyyy/MM/dd</td>
 * 		<td>(NII)</td>
 * 		<td>・</td>
 * 	</tr>
 * </table>
 */

public class ManualOrderPasteVO {

	private String command = "";

	private String daiKaiSkbCod = "";
	private String queryKaiSkbCod = "";
	private ArrayList queryKaiSkbCodList = null;
	private String bshCod = "";
	private String usrId = "";
	
	private String kbn = "";
	private String prsFks = "";
	private String cpPaste =""; 

	private LinkedList formRows = new LinkedList();  


	//プレス発注画面に渡すVO
	private	PrsOrderVO[] prsVOs = null;
	

	
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
//    
//    //会社識別コードリスト
//    public ArrayList getQueryKaiSkbCodList(){
//    	return queryKaiSkbCodList;
//    }
//    public void setQueryKaiSkbCodList(ArrayList arr){
//    	queryKaiSkbCodList = arr;
//    }
    

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
     * プレス／副資材のセット
	 * @return
	 */
	public String getPrsFks() {
		return prsFks;
	}

	/**
     * プレス／副資材の取得
	 * @param string
	 */
	public void setPrsFks(String string) {
		prsFks = string;
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

	/**
	 * @return
	 */
	public String getCpPaste() {
		return cpPaste;
	}

	/**
	 * @param string
	 */
	public void setCpPaste(String string) {
		cpPaste = string;
	}

	/**
	 * @return
	 */
	public LinkedList getFormRows() {
		return formRows;
	}

	/**
	 * @param list
	 */
	public void setFormRows(LinkedList list) {
		formRows = list;
	}

}

