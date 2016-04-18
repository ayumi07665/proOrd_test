package com.jds.prodord.master.fzkhinksipaste;

import java.util.LinkedList;
/**
* FzkHinKsiPasteResult  付属品構成マスター一括貼り付け結果クラス
*
*	作成日    2007/09/30
*	作成者    （ＮＩＩ）田中
* 処理概要    処理結果を格納するクラス。
*
*	[変更履歴]
*/

public class FzkHinKsiPasteResult {

	private FzkHinKsiPasteVO fmVO;
	private boolean success;
	private String desc;
	
	/**
	 * コンストラクタ
	 * @param fmVO マニュアル発注指示一括貼付バリューオブジェクトクラス
	 * @param success
	 * @param desc
	 */
	public FzkHinKsiPasteResult(FzkHinKsiPasteVO fmVO,boolean success,String desc){
		this.fmVO = fmVO;
		this.success = success;
		this.desc = desc;
	}
	
	public LinkedList errList = null;

	public FzkHinKsiPasteVO getVO(){
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

