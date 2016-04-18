package com.jds.prodord.indicate.manualorderpaste;

import java.util.LinkedList;

/**
 * ManualOrderPasteResult  マニュアル発注指示一括貼付処理結果クラス。<BR>
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
public class ManualOrderPasteResult {

	private ManualOrderPasteVO fmVO;
	private boolean success;
	private String desc;
	
	/**
	 * コンストラクタ
	 * @param fmVO マニュアル発注指示一括貼付バリューオブジェクトクラス
	 * @param success
	 * @param desc
	 */
	public ManualOrderPasteResult(ManualOrderPasteVO fmVO,boolean success,String desc){
		this.fmVO = fmVO;
		this.success = success;
		this.desc = desc;
	}
	
	public LinkedList errList = null;

	public ManualOrderPasteVO getVO(){
		return fmVO;
	}
	public boolean isSuccess(){
		return success;
	}
	public String getDescription(){
		return desc;
	}

	/**
	 * ｴﾗｰﾘｽﾄを取得する。
	 * @return
	 */
	public LinkedList getErrList() {
		return errList;
	}

	/**
	 * ｴﾗｰﾘｽﾄを設定する。
	 * @param list
	 */
	public void setErrList(LinkedList list) {
		errList = list;
	}

}

